package com.jme3.scene.plugins.smd;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.pstale.assets.utils.AnimationBuilder;
import org.pstale.assets.utils.AssetNameUtils;
import org.pstale.assets.utils.ModelBuilder;
import org.pstale.assets.utils.SceneBuilder;

import com.jme3.animation.AnimControl;
import com.jme3.animation.Animation;
import com.jme3.animation.Skeleton;
import com.jme3.asset.AssetInfo;
import com.jme3.asset.AssetLoader;
import com.jme3.asset.AssetManager;
import com.jme3.scene.plugins.inx.AnimateModel;
import com.jme3.scene.plugins.smd.animation.PAT3D;
import com.jme3.scene.plugins.smd.stage.Stage;
import com.jme3.util.LittleEndien;

/**
 * 精灵场景加载器
 * 
 * @author yanmaoyuan
 * 
 */
public class SmdLoader implements AssetLoader {

    static Logger log = Logger.getLogger(SmdLoader.class);
    
    private AssetManager manager = null;
    private SmdKey key = null;

    @Override
    public Object load(AssetInfo assetInfo) throws IOException {
        // 确认用户使用了SmdKey
        if (!(assetInfo.getKey() instanceof SmdKey)) {
            log.warn("用户未使用SmdKey来加载模型:" + key.getName());
            throw new RuntimeException("请使用SmdKey来加载精灵的smd模型。");
        }

        this.key = (SmdKey) assetInfo.getKey();
        this.manager = assetInfo.getManager();
        ModelBuilder.setAssetManager(manager);
        ModelBuilder.setFolder(key.getFolder());
        SceneBuilder.setFolder(key.getFolder());

        /**
         * 若用户使用了SmdKey，就根据type来决定采用哪种方式来加载模型。
         */
        switch (key.type) {
        case STAGE3D: {// 直接返回STAGE3D对象
            Stage stage3D = new Stage();
            stage3D.loadFile(new LittleEndien(assetInfo.openStream()));
            return stage3D;
        }
        case PAT3D: {// 直接返回PAT3D对象
            // load smb
            PAT3D skeleton = new PAT3D();
            skeleton.loadFile(new LittleEndien(assetInfo.openStream()), null);
            return skeleton;
        }
        case PAT3D_BIP: {// 有动画的舞台物体
            // 后缀名改为smb
            String smbFile = AssetNameUtils.changeExt(key.getName(), "smb");
            PAT3D skeleton = (PAT3D) manager.loadAsset(new SmdKey(smbFile, SMDTYPE.PAT3D));

            // 再加载smd文件
            key = (SmdKey) assetInfo.getKey();
            LittleEndien in = new LittleEndien(assetInfo.openStream());
            PAT3D pat = new PAT3D();
            pat.loadFile(in, skeleton);
            return ModelBuilder.buildModel(pat, key.getName());
        }
        case PAT3D_VISUAL: {// 舞台物体，无动画
            LittleEndien in = new LittleEndien(assetInfo.openStream());
            PAT3D pat = new PAT3D();
            pat.loadFile(in, key.getBone());
            return ModelBuilder.buildModel(pat, key.getName());
        }
        case MODELINFO: {// inx 文件
            LittleEndien in = new LittleEndien(assetInfo.openStream());
            AnimateModel modelInfo = new AnimateModel();
            modelInfo.loadData(in);
            return modelInfo;
        }
        case MODELINFO_ANIMATION: {
            LittleEndien in = new LittleEndien(assetInfo.openStream());
            AnimateModel modelInfo = new AnimateModel();
            modelInfo.loadData(in);

            // 有共享数据?
            String linkFile = modelInfo.linkFile;
            if (linkFile.length() > 0) {
                SmdKey linkFileKey = new SmdKey(linkFile, SMDTYPE.MODELINFO);
                AnimateModel mi = (AnimateModel) manager.loadAsset(linkFileKey);
                modelInfo.animationFile = mi.animationFile;
            }

            PAT3D BipPattern = null;
            // 读取动画
            if (modelInfo.animationFile.length() > 0) {
                // 后缀名改为smb
                String smbFile = AssetNameUtils.changeExt(modelInfo.animationFile, "smb");
                smbFile = AssetNameUtils.getName(smbFile);
                BipPattern = (PAT3D) manager.loadAsset(new SmdKey(key.getFolder() + smbFile, SMDTYPE.PAT3D));

                // 生成动画
                Skeleton ske = AnimationBuilder.buildSkeleton(BipPattern);
                Animation anim = AnimationBuilder.buildAnimation(BipPattern, ske);
                AnimControl ac = new AnimControl(ske);
                ac.addAnim(anim);
                return ac;
            } else {
                return null;
            }
        }
        case MODELINFO_MODEL: {
            LittleEndien in = new LittleEndien(assetInfo.openStream());
            AnimateModel modelInfo = new AnimateModel();
            modelInfo.loadData(in);

            // 有共享数据?
            String linkFile = modelInfo.linkFile;
            if (linkFile.length() > 0) {
                SmdKey linkFileKey = new SmdKey(linkFile, SMDTYPE.MODELINFO);
                AnimateModel mi = (AnimateModel) manager.loadAsset(linkFileKey);
                modelInfo.animationFile = mi.animationFile;
            }

            PAT3D BipPattern = null;
            // 读取动画
            if (modelInfo.animationFile.length() > 0) {
                // 后缀名改为smb
                String smbFile = AssetNameUtils.changeExt(modelInfo.animationFile, "smb");
                smbFile = AssetNameUtils.getName(smbFile);
                BipPattern = (PAT3D) manager.loadAsset(new SmdKey(key.getFolder() + smbFile, SMDTYPE.PAT3D));
            }

            // 读取网格
            String smdFile = AssetNameUtils.changeExt(modelInfo.modelFile, "smd");
            smdFile = AssetNameUtils.getName(smdFile);

            SmdKey smdKey = new SmdKey(key.getFolder() + smdFile, SMDTYPE.PAT3D_VISUAL);
            smdKey.setBone(BipPattern);
            return manager.loadAsset(smdKey);
        }
        default:
            return null;
        }
    }


}
