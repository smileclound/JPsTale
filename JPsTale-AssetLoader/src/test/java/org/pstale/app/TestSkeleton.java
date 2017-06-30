package org.pstale.app;

import com.jme3.animation.AnimControl;
import com.jme3.animation.Skeleton;
import com.jme3.animation.SkeletonControl;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.debug.SkeletonDebugger;
import com.jme3.scene.plugins.smd.SMDTYPE;
import com.jme3.scene.plugins.smd.SmdKey;

/**
 * 测试动画播放
 * 
 * @author yanmaoyuan
 *
 */
public class TestSkeleton extends TestBase {

    @Override
    public void init() {

        float scale = 0.1f;

        AnimControl ac = (AnimControl) assetManager
                .loadAsset(new SmdKey("char/npc/arad/arad.inx", SMDTYPE.MODELINFO_ANIMATION));
        rootNode.addControl(ac);

        // 播放动画
        // ac.createChannel().setAnim("Anim");

        // 创建骨骼
        Skeleton ske = ac.getSkeleton();
        SkeletonControl sc = new SkeletonControl(ske);

        rootNode.addControl(sc);

        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Green);
        mat.getAdditionalRenderState().setDepthTest(false);

        SkeletonDebugger skeletonDebug = new SkeletonDebugger("skeleton", ske);
        skeletonDebug.setMaterial(mat);
        skeletonDebug.scale(scale);
        skeletonDebug.rotate(-FastMath.HALF_PI, 0, 0);

        rootNode.attachChild(skeletonDebug);

        cam.setLocation(new Vector3f(0, 5, 10));
    }

    public static void main(String[] args) {
        new TestSkeleton().start();

    }

}
