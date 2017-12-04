package com.jme3.scene.plugins.smd;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.jme3.asset.AssetInfo;
import com.jme3.asset.AssetLoader;
import com.jme3.scene.plugins.smd.geom.AnimateModel;
import com.jme3.util.LittleEndien;

/**
 * inx为精灵角色模型的索引文件，其中记录了模型文件、骨骼文件、动画文件各自的路径。
 * 
 * 最重要的是，inx文件记录了如何把动画分解成子动画的索引。
 * 
 * @author yanmaoyuan
 * 
 */
public class InxLoader implements AssetLoader {

    static Logger log = Logger.getLogger(InxLoader.class);
    
    @Override
    public Object load(AssetInfo assetInfo) throws IOException {
        AnimateModel modelInfo = new AnimateModel();
        modelInfo.loadData(new LittleEndien(assetInfo.openStream()));
        return modelInfo;
    }
}
