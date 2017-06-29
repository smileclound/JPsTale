package org.pstale.test;

import com.jme3.animation.AnimControl;
import com.jme3.animation.Skeleton;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.debug.SkeletonDebugger;
import com.jme3.scene.plugins.smd.SMDTYPE;
import com.jme3.scene.plugins.smd.SmdKey;
import com.jme3.scene.plugins.smd.SmdLoader;

/**
 * 测试动画播放
 * 
 * @author yanmaoyuan
 *
 */
public class TestAnimation extends TestBase {

    @Override
    public void init() {
        SmdLoader.USE_LIGHT = false;
        SmdLoader.LOG_ANIMATION = false;

        float scale = 0.1f;

        Node npc = (Node) assetManager.loadAsset(new SmdKey("char/npc/arad/arad.inx", SMDTYPE.MODELINFO_MODEL));
        npc.scale(scale);
        rootNode.attachChild(npc);

        AnimControl ac = npc.getControl(AnimControl.class);

        if (ac != null) {
            final Skeleton skel = ac.getSkeleton();
            SkeletonDebugger skeletonDebug = new SkeletonDebugger("skeleton", skel);
            final Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
            mat.setColor("Color", ColorRGBA.Green);
            mat.getAdditionalRenderState().setDepthTest(false);
            skeletonDebug.setMaterial(mat);
            rootNode.attachChild(skeletonDebug);
            skeletonDebug.scale(scale);
            ac.createChannel().setAnim("Anim");
        }
        cam.setLocation(new Vector3f(0, 5, 10));
    }

    public static void main(String[] args) {
        new TestAnimation().start();

    }

}
