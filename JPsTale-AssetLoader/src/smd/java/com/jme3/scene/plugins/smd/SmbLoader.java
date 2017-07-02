package com.jme3.scene.plugins.smd;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.jme3.asset.AssetInfo;
import com.jme3.asset.AssetLoader;
import com.jme3.scene.plugins.smd.geom.PAT3D;
import com.jme3.util.LittleEndien;

/**
 * 精灵场景加载器
 * 
 * @author yanmaoyuan
 * 
 */
public class SmbLoader implements AssetLoader {

    static Logger log = Logger.getLogger(SmbLoader.class);
    
    @Override
    public Object load(AssetInfo assetInfo) throws IOException {
        PAT3D skeleton = new PAT3D();
        skeleton.loadFile(new LittleEndien(assetInfo.openStream()));
        return skeleton;
    }


}
