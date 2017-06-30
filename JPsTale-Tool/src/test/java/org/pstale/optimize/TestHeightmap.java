package org.pstale.optimize;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.pstale.utils.FileLocator;
import org.pstale.utils.GaussianBlur;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.AssetManager;
import com.jme3.asset.DesktopAssetManager;
import com.jme3.bounding.BoundingBox;
import com.jme3.collision.CollisionResults;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Ray;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.plugins.smd.SMDTYPE;
import com.jme3.scene.plugins.smd.SmdKey;
import com.jme3.scene.plugins.smd.SmdLoader;
import com.jme3.terrain.geomipmap.TerrainLodControl;
import com.jme3.terrain.geomipmap.TerrainQuad;
import com.jme3.terrain.heightmap.ImageBasedHeightMap;
import com.jme3.texture.Texture;

/**
 * 使用网格和射线进行碰撞检测，生成高度图。
 * @author yanmaoyuan
 * 
 */
public class TestHeightmap extends SimpleApplication {

    static Logger logger = LoggerFactory.getLogger(TestHeightmap.class);

    private final static int MAP_SIZE = 513;
    private static float scaleX = 1f;
    private static float scaleY = 1f;
    private static float scaleZ = 1f;

    public static void main(String[] args) {

        // 初始化资源管理器
        AssetManager assetManager = new DesktopAssetManager();
        assetManager.registerLoader(SmdLoader.class, "smd");
        if (new File("I:/game/PTCN-RPT1.0").exists()) {
            assetManager.registerLocator("I:/game/PTCN-RPT1.0", FileLocator.class);
        } else {
            assetManager.registerLocator("D:/Priston Tale/PTCN3550/PTCN3550", FileLocator.class);
        }

        // 读取地图的smd文件
        Mesh mesh = (Mesh) assetManager.loadAsset(new SmdKey("Field/forest/fore-1.smd", SMDTYPE.STAGE3D_COLLISION));
        // 包围盒
        BoundingBox bb = (BoundingBox) mesh.getBound();
        
        Vector3f max = new Vector3f();
        Vector3f min = new Vector3f();
        bb.getMin(min);
        bb.getMax(max);

        scaleX = (max.x - min.x) / (MAP_SIZE-1);
        scaleY = (max.y - min.y) / 255;
        scaleZ = (max.z - min.z) / (MAP_SIZE-1);
        
        if (!new File("heightMap.png").exists()) {
            paintHeightMap(mesh);
        }
        
        TestHeightmap app = new TestHeightmap();
        app.start();
    }
    
    private static void paintHeightMap(Mesh mesh) {
        Geometry geom = new Geometry("", mesh);
        
        // 包围盒
        BoundingBox bb = (BoundingBox) mesh.getBound();
        
        Vector3f max = new Vector3f();
        Vector3f min = new Vector3f();
        bb.getMin(min);
        bb.getMax(max);
        
        float width = max.x - min.x;
        float height = max.z - min.z;
        
        
        /**
         * 射线
         */
        Ray ray = new Ray();
        // 方向
        ray.setDirection(new Vector3f(0, -1, 0));
        // 原点
        ray.setOrigin(new Vector3f(min.x, max.y+100, min.z));
        // 长度
        ray.setLimit(max.y - min.y + 200);
        
        float dx = width / (MAP_SIZE-1);
        float dy = height / (MAP_SIZE-1);
        logger.debug("min={} max={} w={} h={} dx={} dy={}", min, max, width, height, dx, dy);
        
        CollisionResults results = new CollisionResults();
        Vector3f origin = new Vector3f(min.x, max.y+100, min.z);
        
        BufferedImage image = new BufferedImage(MAP_SIZE, MAP_SIZE, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = (Graphics2D) image.getGraphics();
        
        for(int y=0; y<MAP_SIZE; y++) {
            origin.x = min.x;
            for(int x=0; x<MAP_SIZE; x++) {
                // 每次取4个点，算平均值
                results.clear();
                ray.setOrigin(origin);
                geom.collideWith(ray, results);
                
                float h = 0;
                if (results.size() > 0) {
                    //Vector3f near = results.getClosestCollision().getContactPoint();
                    Vector3f far = results.getFarthestCollision().getContactPoint();
                    
                    h = far.y - min.y;
                }
                
                int gray = (int) (255 * h / (max.y-min.y));
                g.setColor(new Color(gray, gray, gray));
                g.drawOval(x, y, 1, 1);
                origin.x += dx;
            }
            origin.z += dy;
        }
        
        try {
            ImageIO.write(image, "png", new File("heightmap.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void simpleInitApp() {
        flyCam.setMoveSpeed(100);
        
        assetManager.registerLocator("/", FileLocator.class);
        Texture texture = assetManager.loadTexture("heightmap.png");
        
        ImageBasedHeightMap heightMap = new ImageBasedHeightMap(texture.getImage(), 1f);
        heightMap.load();
        float[] data = heightMap.getHeightMap();
        
        GaussianBlur filter = new GaussianBlur();
        filter.setSigma(2);
        filter.filter(data, MAP_SIZE, MAP_SIZE);
        //filter.filter(data, MAP_SIZE, MAP_SIZE);
        
        TerrainQuad terrain = new TerrainQuad("", 33, MAP_SIZE, heightMap.getHeightMap());
        terrain.addControl(new TerrainLodControl(terrain, cam));
        
        Material mat = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
        mat.setColor("Diffuse", ColorRGBA.White);
        mat.setTexture("DiffuseMap", assetManager.loadTexture("colormap.png"));
        terrain.setMaterial(mat);
        
        rootNode.attachChild(terrain);
        terrain.scale(scaleX, scaleY, scaleZ);
        terrain.scale(0.05f);
        
        rootNode.addLight(new DirectionalLight(new Vector3f(-1, -2, -3).normalizeLocal()));
    }
}
