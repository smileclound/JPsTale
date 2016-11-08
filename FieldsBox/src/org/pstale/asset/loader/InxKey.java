package org.pstale.asset.loader;

import org.pstale.asset.struct.PAT3D;

import com.jme3.asset.AssetKey;
import com.jme3.asset.AssetProcessor;
import com.jme3.asset.CloneableAssetProcessor;
import com.jme3.asset.cache.AssetCache;
import com.jme3.asset.cache.WeakRefAssetCache;

public class InxKey extends AssetKey<Object> {

	SMDTYPE type;
	PAT3D bone;

	public InxKey(String name, SMDTYPE type) {
		super(name);
		this.type = type;
	}

	public PAT3D getBone() {
		return bone;
	}

	public void setBone(PAT3D bone) {
		this.bone = bone;
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
		if (!(other instanceof InxKey))
			return false;

		InxKey key = (InxKey) other;
		if (!name.equals(key.name))
			return false;

		if (type != key.type)
			return false;

		if ((bone != null) != (key.bone != null))
			return false;

		return true;
	}
}
