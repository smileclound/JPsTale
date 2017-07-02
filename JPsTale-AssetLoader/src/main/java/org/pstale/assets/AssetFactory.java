package org.pstale.assets;

import static com.jme3.scene.plugins.smd.SMDTYPE.MODELINFO_MODEL;
import static org.pstale.constants.SceneConstants.scale;

import java.io.File;
import java.util.ArrayList;

import org.pstale.assets.utils.AssetNameUtils;
import org.pstale.assets.utils.ModelBuilder;
import org.pstale.assets.utils.SceneBuilder;
import org.pstale.entity.field.RespawnList;
import org.pstale.entity.field.StartPoint;
import org.pstale.entity.item.ItemInfo;
import org.pstale.utils.FileLocator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jme3.asset.AssetManager;
import com.jme3.asset.TextureKey;
import com.jme3.asset.plugins.ClasspathLocator;
import com.jme3.audio.plugins.WAVLoader;
import com.jme3.material.Material;
import com.jme3.material.RenderState;
import com.jme3.material.RenderState.BlendMode;
import com.jme3.material.RenderState.FaceCullMode;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.Node;
import com.jme3.scene.SceneGraphVisitor;
import com.jme3.scene.Spatial;
import com.jme3.scene.plugins.smd.SMDTYPE;
import com.jme3.scene.plugins.smd.SmdKey;
import com.jme3.scene.plugins.smd.SmdLoader;
import com.jme3.scene.plugins.smd.geom.AnimateModel;
import com.jme3.scene.plugins.smd.geom.PAT3D;
import com.jme3.scene.plugins.smd.material._Material;
import com.jme3.scene.plugins.smd.stage.Stage;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Quad;
import com.jme3.script.plugins.character.CharInfoLoader;
import com.jme3.script.plugins.character.Monster;
import com.jme3.script.plugins.field.CharacterTransform;
import com.jme3.script.plugins.field.SpcLoader;
import com.jme3.script.plugins.field.SpmLoader;
import com.jme3.script.plugins.field.SppLoader;
import com.jme3.script.plugins.item.ItemLoader;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture.WrapMode;

/**
 * 模型工厂
 * 
 * @author yanmaoyuan
 *
 */
public class AssetFactory {

    static Logger logger = LoggerFactory.getLogger(AssetFactory.class);

    static AssetManager assetManager;
    static String folder = "/";

    /**
     * 设置资源目录
     * 
     * @param folder
     */
    public static void setFolder(String f) {
        folder = f;
    }

    public static void setAssetManager(final AssetManager manager) {
        assetManager = manager;
        assetManager.registerLoader(SmdLoader.class, "smd", "smb", "inx");
        assetManager.registerLoader(WAVLoader.class, "bgm");
        assetManager.registerLoader(SpcLoader.class, "spc");
        assetManager.registerLoader(SpmLoader.class, "spm");
        assetManager.registerLoader(SppLoader.class, "spp");
        assetManager.registerLoader(CharInfoLoader.class, "inf", "npc");
        assetManager.registerLoader(ItemLoader.class, "txt");

        // 注册资源加载路径
        assetManager.registerLocator("/", ClasspathLocator.class);
        assetManager.registerLocator("/", FileLocator.class);
        if (new File("I:/game/PTCN-RPT1.0").exists()) {
            assetManager.registerLocator("I:/game/PTCN-RPT1.0", FileLocator.class);
        } else {
            assetManager.registerLocator("D:/Priston Tale/PTCN3550/PTCN3550", FileLocator.class);
        }
    }

    public static AssetManager getAssetManager() {
        return assetManager;
    }

    /**
     * 获得一个刷怪点标记
     * 
     * @return
     */
    public static Spatial loadFlag() {
        Spatial flag;
        try {
            Node wow = AssetFactory.loadStageObj("char/flag/wow.smd", false);
            wow.depthFirstTraversal(new SceneGraphVisitor() {
                @Override
                public void visit(Spatial spatial) {
                    if (spatial instanceof Geometry) {
                        Geometry geom = (Geometry) spatial;
                        geom.getMaterial().getAdditionalRenderState().setFaceCullMode(FaceCullMode.Off);
                    }
                }
            });

            flag = wow;
        } catch (Exception e) {
            logger.debug("无法加载旗帜", e);
            flag = new Geometry("flag", new Box(1 / scale, 1 / scale, 1 / scale));
            Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
            mat.setColor("Color", ColorRGBA.Red);
            flag.setMaterial(mat);
        }

        return flag;
    }

    /**
     * 创建一个Loading标记
     * 
     * @return
     */
    public static Spatial getLoadingFlag() {
        Quad quad = new Quad(80, 20);
        Geometry geom = new Geometry("loading", quad);
        Material mat = new Material(assetManager, "Shader/Misc/Scroll.j3md");
        Texture tex = assetManager.loadTexture("Interface/loading.png");
        tex.setWrap(WrapMode.Repeat);
        mat.setTexture("ColorMap", tex);
        mat.setFloat("Speed", 2);
        mat.setColor("Color", ColorRGBA.Magenta);
        geom.setMaterial(mat);

        return geom;
    }

    /**
     * 加载模型的索引文件
     * 
     * @param name
     * @return
     */
    public static AnimateModel loadInx(final String name) {
        String inx = AssetNameUtils.changeExt(name, "inx");
        return (AnimateModel) assetManager.loadAsset(new SmdKey(inx, SMDTYPE.MODELINFO));
    }

    /**
     * 读取地形数据
     * 
     * @param name
     * @return
     */
    public static Stage loadSmdStage(final String name) {
        String smd = AssetNameUtils.changeExt(name, "smd");
        Stage stage = (Stage) assetManager.loadAsset(new SmdKey(smd, SMDTYPE.STAGE3D));
        return stage;
    }

    public static PAT3D loadSmb(final String name) {
        String smb = AssetNameUtils.changeExt(name, "smb");
        PAT3D skeleton = (PAT3D) assetManager.loadAsset(new SmdKey(smb, SMDTYPE.PAT3D));
        return skeleton;
    }
    
    public static PAT3D loadSmd(final String name) {
        String smb = AssetNameUtils.changeExt(name, "smd");
        PAT3D model = (PAT3D) assetManager.loadAsset(new SmdKey(smb, SMDTYPE.PAT3D));
        return model;
    }

    /**
     * 加载地图
     * 
     * @param name
     * @return
     * @throws Exception
     */
    public static Node loadStage3D(final String name) {
        String smd = AssetNameUtils.changeExt(name, "smd");
        SmdKey key = new SmdKey(smd, SMDTYPE.STAGE3D);
        Stage stage = (Stage) assetManager.loadAsset(key);
        return SceneBuilder.buildScene3D(stage, smd);
    }

    public static Mesh loadStage3DMesh(final String name) {
        String smd = AssetNameUtils.changeExt(name, "smd");
        SmdKey key = new SmdKey(smd, SMDTYPE.STAGE3D);
        setFolder(key.getFolder());
        Stage stage = (Stage) assetManager.loadAsset(key);
        return SceneBuilder.buildCollisionMesh(stage);
    }

    public static Node loadStageObj(final String name, final boolean bip) {
        PAT3D model = loadSmd(name);
        if (bip) {
            // load smb first
            PAT3D skeleton = loadSmb(name);
            model.setSkeleton(skeleton);
        }
        
        AssetFactory.setFolder(AssetNameUtils.getFolder(name));
        return ModelBuilder.buildModel(model, AssetNameUtils.getSimpleName(name));
    }


    public static Node loadCharacter(String path) {
        AnimateModel modelInfo = loadInx(path);
        
        String folder = AssetNameUtils.getFolder(path);

        // 有共享数据?
        String linkFile = modelInfo.linkFile;
        if (linkFile.length() > 0) {
            AnimateModel mi = AssetFactory.loadInx(linkFile);
            modelInfo.motionFile = mi.motionFile;
        }

        PAT3D skeleton = null;
        // 读取动画
        if (modelInfo.motionFile.length() > 0) {
            // 后缀名改为smb
            String smbFile = AssetNameUtils.changeExt(modelInfo.motionFile, "smb");
            String name = AssetNameUtils.getName(smbFile);
            skeleton = AssetFactory.loadSmb(folder + name);
        }
        
        // 读取网格
        String smdFile = AssetNameUtils.changeExt(modelInfo.modelFile, "smd");
        smdFile = AssetNameUtils.getName(smdFile);
        PAT3D model = AssetFactory.loadSmd(folder + smdFile);
        model.setSkeleton(skeleton);
        
        return ModelBuilder.buildModel(model, path);
    }
    
    public static Node loadNPC(final String name) {
        String inx = AssetNameUtils.changeExt(name, "inx");
        return (Node) assetManager.loadAsset(new SmdKey(inx, MODELINFO_MODEL));
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<StartPoint> loadSpp(final String name) {
        String path = String.format("GameServer/Field/%s.ase.spp", AssetNameUtils.getSimpleName(name));
        try {
            ArrayList<StartPoint> spp = (ArrayList<StartPoint>) assetManager.loadAsset(path);
            return spp;
        } catch (Exception e) {
            return null;
        }
    }

    public static RespawnList loadSpm(final String name) {
        String path = String.format("GameServer/Field/%s.ase.spm", AssetNameUtils.getSimpleName(name));
        try {
            RespawnList creatures = (RespawnList) assetManager.loadAsset(path);
            return creatures;
        } catch (Exception e) {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<CharacterTransform> loadSpc(final String name) {
        String path = String.format("GameServer/Field/%s.ase.spc", AssetNameUtils.getSimpleName(name));
        try {
            ArrayList<CharacterTransform> npcs = (ArrayList<CharacterTransform>) assetManager.loadAsset(path);
            return npcs;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 导入NPC的脚本文件
     * 
     * @param name
     * @return
     */
    public static Monster loadNpcScript(final String name) {
        String path = String.format("GameServer/NPC/%s.npc", AssetNameUtils.getSimpleName(name));

        try {
            Monster info = (Monster) assetManager.loadAsset(path);
            return info;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 导入怪物的脚本文件
     * 
     * @param name
     * @return
     */
    public static Monster loadMonsterScript(final String name) {
        String path = String.format("GameServer/Monster/%s.inf", AssetNameUtils.getSimpleName(name));

        try {
            Monster info = (Monster) assetManager.loadAsset(path);
            return info;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 导入装备的脚本
     * 
     * @param name
     * @return
     */
    public static ItemInfo loadItemScript(String name) {
        String path = String.format("GameServer/OpenItem/%s.txt", AssetNameUtils.getSimpleName(name));

        try {
            ItemInfo info = (ItemInfo) assetManager.loadAsset(path);
            return info;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 创建纹理
     * 
     * @param name
     */
    public static Texture createTexture(String name) {
        name = AssetNameUtils.getName(name);
        Texture texture = null;
        try {
            TextureKey texKey = new TextureKey(folder + name);
            texKey.setGenerateMips(true);
            texture = assetManager.loadTexture(texKey);
            texture.setWrap(WrapMode.Repeat);
            texture.setAnisotropicFilter(4);
        } catch (Exception ex) {
            texture = assetManager.loadTexture("Common/Textures/MissingTexture.png");
            texture.setWrap(WrapMode.EdgeClamp);
        }
        return texture;
    }

    /**
     * 创建感光材质
     * 
     * @param m
     * @return
     */
    public static Material createLightMaterial(_Material m) {
        Material mat = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
        mat.setColor("Diffuse", new ColorRGBA(m.Diffuse.r, m.Diffuse.g, m.Diffuse.b, 1));
        mat.setColor("Ambient", new ColorRGBA(1f, 1f, 1f, 1f));
        mat.setColor("Specular", new ColorRGBA(0, 0, 0, 1));
        // mat.setBoolean("UseMaterialColors", true);

        // 设置贴图
        if (m.TextureCounter > 0) {
            mat.setTexture("DiffuseMap", createTexture(m.smTexture[0].Name));
        }
        if (m.TextureCounter > 1) {
            mat.setBoolean("SeparateTexCoord", true);
            mat.setTexture("LightMap", createTexture(m.smTexture[1].Name));
        }

        return mat;
    }

    /**
     * 创建一个忽略光源的材质。
     * 
     * @param m
     * @return
     */
    public static Material createMiscMaterial(_Material m) {
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        // mat.setColor("Color", new ColorRGBA(m.Diffuse.r, m.Diffuse.g,
        // m.Diffuse.b, 1));
        mat.setColor("Color", ColorRGBA.White);

        // 设置贴图
        if (m.TextureCounter > 0) {
            mat.setTexture("ColorMap", createTexture(m.smTexture[0].Name));
        }
        if (m.TextureCounter > 1) {
            mat.setBoolean("SeparateTexCoord", true);
            mat.setTexture("LightMap", createTexture(m.smTexture[1].Name));
        }

        return mat;
    }
    /**
     * 创建一个匀速切换帧的材质。
     * 
     * @param m
     * @return
     */
    public static Material createShiftMaterial(_Material m) {
        Material mat = new Material(assetManager, "Shader/Misc/Shift.j3md");

        // 画面的切换时间间隔
        float ShiftSpeed = (1 << m.Shift_FrameSpeed) / 1000f;
        mat.setFloat("ShiftSpeed", ShiftSpeed);

        // 设置贴图
        Texture tex;
        for (int i = 0; i < m.AnimTexCounter; i++) {
            tex = createTexture(m.smAnimTexture[i].Name);
            mat.setTexture("Tex" + (i + 1), tex);
        }

        return mat;
    }

    /**
     * 创建一个卷轴动画材质。
     * 
     * @param m
     * @return
     */
    public static Material createScrollMaterial(_Material m) {
        Material mat = new Material(assetManager, "Shader/Misc/Scroll.j3md");

        // 画面的卷动速度
        float speed = 1f;

        int n = m.TextureFormState[0];
        if (n >= 6 && n <= 14) {
            speed = 15 - n;
        }

        if (n >= 15 && n <= 18) {
            int factor = 18 - n + 4;
            speed = (128 >> factor) / 256f;
        }

        mat.setFloat("Speed", speed);

        // 设置贴图
        Texture tex = createTexture(m.smTexture[0].Name);
        mat.setTexture("ColorMap", tex);

        if (m.TextureCounter > 1) {
            mat.setBoolean("SeparateTexCoord", true);
            mat.setTexture("LightMap", createTexture(m.smTexture[1].Name));
        }

        return mat;
    }

    /**
     * 创建一个原地转圈的动画材质。 Water动画专用
     * 
     * @param m
     * @return
     */
    public static Material createRoundMaterial(_Material m) {
        Material mat = new Material(assetManager, "Shader/Misc/Round.j3md");

        // 设置贴图
        Texture tex = createTexture(m.smTexture[0].Name);
        mat.setTexture("ColorMap", tex);

        if (m.TextureCounter > 1) {
            mat.setBoolean("SeparateTexCoord", true);
            mat.setTexture("LightMap", createTexture(m.smTexture[1].Name));
        }

        return mat;
    }
    
    /**
     * 设置材质的RenderState
     * 
     * @param m
     * @param mat
     */
    public static void setRenderState(_Material m, Material mat) {
        RenderState rs = mat.getAdditionalRenderState();

        switch (m.BlendType) {
        case 0:// SMMAT_BLEND_NONE
            rs.setBlendMode(BlendMode.Off);
            break;
        case 1:// SMMAT_BLEND_ALPHA
            rs.setBlendMode(BlendMode.Alpha);
            break;
        case 2:// SMMAT_BLEND_COLOR
            rs.setBlendMode(BlendMode.Color);
            break;
        case 3:// SMMAT_BLEND_SHADOW
            break;
        case 4:// SMMAT_BLEND_LAMP
            rs.setBlendMode(BlendMode.Additive);
            break;
        case 5:// SMMAT_BLEND_ADDCOLOR
            rs.setBlendMode(BlendMode.Additive);
            break;
        case 6:
            break;
        default:
            logger.info("Unknown BlendType=" + m.BlendType);
        }

        if (m.TwoSide == 1) {
            rs.setFaceCullMode(FaceCullMode.Off);
        }

        if (m.TextureType == 0x0001) {
            // 动画默认显示2面
            rs.setFaceCullMode(FaceCullMode.Off);
        }

        // 透明物体
        if (m.MapOpacity != 0 || m.Transparency != 0) {
            // 这个值设置得稍微大一些，这样草、花等图片的边缘就会因为透明度不够而过滤掉像素。
            mat.setFloat("AlphaDiscardThreshold", 0.75f);
            // 虽然已经过时，但是还是写上以防不测。
            // rs.setAlphaTest(true);
            // rs.setAlphaFallOff(0.6f);
            rs.setDepthWrite(true);
            rs.setDepthTest(true);
            rs.setColorWrite(true);

            // 透明物体不裁剪面
            rs.setFaceCullMode(FaceCullMode.Off);
        }
    }
}
