package org.pstale.asset.loader;

import com.jme3.asset.AssetKey;
import com.jme3.asset.AssetProcessor;
import com.jme3.asset.CloneableAssetProcessor;
import com.jme3.asset.cache.AssetCache;
import com.jme3.asset.cache.WeakRefAssetCache;

public class SmdKey extends AssetKey<Object> {

	public enum SMDTYPE {
		STAGE3D, STAGE_OBJ, STAGE_OBJ_BIP, PAT3D, MODEL, BONE;
	}
	
	SMDTYPE type;

	public SmdKey(String name, SMDTYPE type) {
		super(name);
		this.type = type;
	}
	
    @Override
    public Class<? extends AssetCache> getCacheType(){
        return WeakRefAssetCache.class;
    }
    
    @Override
    public Class<? extends AssetProcessor> getProcessorType(){
        return CloneableAssetProcessor.class;
    }
	
}
