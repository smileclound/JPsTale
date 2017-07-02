package org.pstale.app;

import org.pstale.assets.AssetFactory;
import org.pstale.assets.utils.AnimationBuilder;
import org.pstale.assets.utils.AssetNameUtils;

import com.jme3.animation.Skeleton;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.debug.SkeletonDebugger;
import com.jme3.scene.plugins.smd.geom.AnimateModel;
import com.jme3.scene.plugins.smd.geom.PAT3D;

/**
 * 测试生成骨骼
 * 
 * @author yanmaoyuan
 *
 */
public class TestSkeleton extends TestBase {

    @Override
    public void init() {

        String path = "char/npc/arad/arad.inx";

        // 加载模型说明文件
        AnimateModel modelInfo = AssetFactory.loadInx(path);
        String folder = AssetNameUtils.getFolder(path);
        
        // 读取动画
        if (modelInfo.motionFile.length() > 0) {
            // 提取smb文件中的数据
            String smbFile = AssetNameUtils.changeExt(modelInfo.motionFile, "smb");
            String name = AssetNameUtils.getName(smbFile);
            
            PAT3D skeleton = AssetFactory.loadSmb(folder + name);
            
            // 生成骨骼
            Skeleton ske = AnimationBuilder.buildSkeleton(skeleton);
            
            // 显示骨骼
            Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
            mat.setColor("Color", ColorRGBA.Green);
            
            SkeletonDebugger debugger = new SkeletonDebugger("skeleton", ske);
            debugger.setMaterial(mat);
            debugger.scale(0.2f);
            debugger.rotate(-FastMath.HALF_PI, 0, 0);
            
            rootNode.attachChild(debugger);
        }

        cam.setLocation(new Vector3f(0, 5, 10));
        flyCam.setMoveSpeed(10);
    }

    public static void main(String[] args) {
        new TestSkeleton().start();
    }

}
