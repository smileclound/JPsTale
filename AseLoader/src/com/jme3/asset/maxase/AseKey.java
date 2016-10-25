package com.jme3.asset.maxase;

import com.jme3.asset.AssetKey;
import com.jme3.asset.AssetProcessor;
import com.jme3.asset.CloneableAssetProcessor;
import com.jme3.asset.cache.AssetCache;
import com.jme3.asset.cache.WeakRefCloneAssetCache;
import com.jme3.scene.Spatial;

/**
 * Don't think i really need this Class, may be remove it later.
 * @author yanmaoyuan
 *
 */
public class AseKey extends AssetKey<Spatial> {

    public AseKey(String name) {
        super(name);
    }

    public AseKey() {
        super();
    }
    
    @Override
    public Class<? extends AssetCache> getCacheType(){
        return WeakRefCloneAssetCache.class;
    }
    
    @Override
    public Class<? extends AssetProcessor> getProcessorType(){
        return CloneableAssetProcessor.class;
    }

}
