package com.jme3.scene.plugins.smd;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.pstale.assets.AssetFactory;

import com.jme3.asset.AssetInfo;
import com.jme3.asset.AssetLoader;
import com.jme3.scene.plugins.smd.geom.AnimateModel;
import com.jme3.scene.plugins.smd.geom.PAT3D;
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
    
    private SmdKey key = null;

    @Override
    public Object load(AssetInfo assetInfo) throws IOException {
        // 确认用户使用了SmdKey
        if (!(assetInfo.getKey() instanceof SmdKey)) {
            log.warn("用户未使用SmdKey来加载模型:" + key.getName());
            throw new RuntimeException("请使用SmdKey来加载精灵的smd模型。");
        }

        this.key = (SmdKey) assetInfo.getKey();
        AssetFactory.setFolder(key.getFolder());

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
            PAT3D skeleton = new PAT3D();
            skeleton.loadFile(new LittleEndien(assetInfo.openStream()));
            return skeleton;
        }
        case MODELINFO: {// inx 文件
            AnimateModel modelInfo = new AnimateModel();
            modelInfo.loadData(new LittleEndien(assetInfo.openStream()));
            return modelInfo;
        }
        default:
            return null;
        }
    }


}
