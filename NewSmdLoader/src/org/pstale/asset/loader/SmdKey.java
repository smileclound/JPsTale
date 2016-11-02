package org.pstale.asset.loader;

import com.jme3.asset.ModelKey;

public class SmdKey extends ModelKey {

	public enum SMDTYPE {
		STAGE3D, STAGE_OBJ, PAT3D, MODEL, BONE;
	}
	
	SMDTYPE type;

	public SmdKey(String name, SMDTYPE type) {
		super(name);
		this.type = type;
	}
	
	
}
