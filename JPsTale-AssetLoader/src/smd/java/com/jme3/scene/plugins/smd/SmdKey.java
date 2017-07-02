package com.jme3.scene.plugins.smd;

import com.jme3.asset.AssetKey;
import com.jme3.asset.AssetProcessor;
import com.jme3.asset.CloneableAssetProcessor;
import com.jme3.asset.cache.AssetCache;
import com.jme3.asset.cache.WeakRefAssetCache;

public class SmdKey extends AssetKey<Object> {

    SMDTYPE type;

    public SmdKey(String name, SMDTYPE type) {
        super(name);
        this.type = type;
    }

    @Override
    public Class<? extends AssetCache> getCacheType() {
        return WeakRefAssetCache.class;
    }

    @Override
    public Class<? extends AssetProcessor> getProcessorType() {
        return CloneableAssetProcessor.class;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof SmdKey))
            return false;

        SmdKey key = (SmdKey) other;
        if (!name.equals(key.name))
            return false;

        if (type != key.type)
            return false;

        return true;
    }
}
