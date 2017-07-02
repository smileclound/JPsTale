package org.pstale.app;

import org.pstale.assets.AssetFactory;
import org.pstale.assets.utils.AnimationBuilder;
import org.pstale.assets.utils.AssetNameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.animation.Animation;
import com.jme3.animation.LoopMode;
import com.jme3.animation.Skeleton;
import com.jme3.animation.SkeletonControl;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.debug.SkeletonDebugger;
import com.jme3.scene.plugins.smd.geom.AnimateModel;
import com.jme3.scene.plugins.smd.geom.MotionInfo;
import com.jme3.scene.plugins.smd.geom.PAT3D;

/**
 * 测试生成骨骼
 * 
 * @author yanmaoyuan
 *
 */
public class TestSubAnimation extends TestBase {

    static Logger logger = LoggerFactory.getLogger(TestSubAnimation.class);
    
    AnimChannel channel;
    @Override
    public void init() {

        String path = "char/monster/death_knight/death_knight.inx";

        // 加载模型说明文件
        AnimateModel modelInfo = AssetFactory.loadInx(path);
        String folder = AssetNameUtils.getFolder(path);

        // 共享动作数据
        if (modelInfo.motionLinkFile.length() > 0) {
            logger.debug("Use link file: {}", modelInfo.motionLinkFile);

            String inx = AssetNameUtils.changeExt(modelInfo.motionLinkFile, "inx");
            String name = AssetNameUtils.getName(inx);

            AnimateModel mi = AssetFactory.loadInx(folder + name);

            modelInfo.motionFile = mi.motionFile;

            modelInfo.subMotions = mi.subMotions;
            modelInfo.subMotionCount = mi.subMotionCount;
        }
        
        // 读取动画
        if (modelInfo.motionFile.length() > 0) {
            // 提取smb文件中的数据
            String smbFile = AssetNameUtils.changeExt(modelInfo.motionFile, "smb");
            String name = AssetNameUtils.getName(smbFile);

            PAT3D skeleton = AssetFactory.loadSmb(folder + name);
            
            // 生成骨骼
            Skeleton ske = AnimationBuilder.buildSkeleton(skeleton);
            showSkeleton(ske);
            
            AnimControl ac = new AnimControl(ske);
            int count = modelInfo.subMotionCount - 10;
            for (int i = 0; i < count; i++) {
                MotionInfo motion = modelInfo.subMotions[i + 10];
                Animation anim = AnimationBuilder.buildAnimation(skeleton, motion);
                ac.addAnim(anim);
            }
            
            rootNode.addControl(ac);

            rootNode.addControl(new SkeletonControl(ske));
            
            channel = ac.createChannel();
            
            channel.setAnim("Idle");
            channel.setLoopMode(LoopMode.Loop);
        }
        
        cam.setLocation(new Vector3f(0, 5, 10));
        flyCam.setMoveSpeed(10);
    }

    private void showSkeleton(Skeleton ske) {
        // 显示骨骼
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Green);

        SkeletonDebugger debugger = new SkeletonDebugger("skeleton", ske);
        debugger.setMaterial(mat);
        debugger.scale(0.1f);
        debugger.rotate(-FastMath.HALF_PI, 0, 0);

        rootNode.attachChild(debugger);
    }

    public static void main(String[] args) {
        new TestSubAnimation().start();
    }

}
