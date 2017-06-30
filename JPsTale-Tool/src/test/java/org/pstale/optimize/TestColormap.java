package org.pstale.optimize;

import java.io.File;

import org.pstale.assets.ModelFactory;
import org.pstale.utils.FileLocator;

import com.jme3.app.SimpleApplication;
import com.jme3.bounding.BoundingBox;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.texture.FrameBuffer;
import com.jme3.texture.Image.Format;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture2D;
import com.jme3.ui.Picture;

public class TestColormap extends SimpleApplication {
    private Spatial scene;
    private ViewPort offView;
    
    public Texture2D setupOffscreenView(){
        Camera offCamera = new Camera(512, 512);

        offView = renderManager.createPreView("Offscreen View", offCamera);
        offView.setClearFlags(true, true, true);
        offView.setBackgroundColor(ColorRGBA.DarkGray);

        // create offscreen framebuffer
        FrameBuffer offBuffer = new FrameBuffer(512, 512, 1);

        //setup framebuffer's cam
        offCamera.setFrustumPerspective(45f, 1f, 1f, 1000f);
        offCamera.setLocation(new Vector3f(0f, 0f, -5f));
        offCamera.lookAtDirection(new Vector3f(0f, -1f, 0f), Vector3f.UNIT_Y);

        //setup framebuffer's texture
        Texture2D offTex = new Texture2D(512, 512, Format.RGBA8);
        offTex.setMinFilter(Texture.MinFilter.Trilinear);
        offTex.setMagFilter(Texture.MagFilter.Bilinear);

        //setup framebuffer to use texture
        offBuffer.setDepthBuffer(Format.Depth);
        offBuffer.setColorTexture(offTex);
        
        //set viewport to render to offscreen framebuffer
        offView.setOutputFrameBuffer(offBuffer);

        
        BoundingBox bb = (BoundingBox) scene.getWorldBound();
        Vector3f center = bb.getCenter();
        offCamera.setLocation(center.add(0, 100, 0));
        
        // attach the scene to the viewport to be rendered
        offView.attachScene(scene);
        
        return offTex;
    }

    
    @Override
    public void simpleInitApp() {
        if (new File("I:/game/PTCN-RPT1.0").exists()) {
            assetManager.registerLocator("I:/game/PTCN-RPT1.0", FileLocator.class);
        } else {
            assetManager.registerLocator("D:/Priston Tale/PTCN3550/PTCN3550", FileLocator.class);
        }
        ModelFactory.setAssetManager(assetManager);

        scene = ModelFactory.loadStage3D("Field/forest/fore-1.smd");
        scene.scale(0.05f);
        rootNode.attachChild(scene);
        
        BoundingBox bb = (BoundingBox) scene.getWorldBound();
        Vector3f center = bb.getCenter();
        cam.setLocation(center);
        cam.lookAtDirection(new Vector3f(0, -1, 0), Vector3f.UNIT_Y);
        cam.setParallelProjection(true);
        cam.setFrustum(-1000, 1000, -bb.getXExtent(), bb.getXExtent(), bb.getZExtent(), -bb.getZExtent());
        
//        Texture2D offTex = setupOffscreenView();
//        
//        Picture pic = new Picture();
//        pic.setTexture(assetManager, offTex, true);
//        pic.setWidth(256);
//        pic.setWidth(256);
//        pic.setPosition(0, 256);
//        guiNode.attachChild(pic);
    }
    
    @Override
    public void simpleUpdate(float tpf){
    }

    public static void main(String[] args) {
        TestColormap app = new TestColormap();
        app.start();
    }

}
