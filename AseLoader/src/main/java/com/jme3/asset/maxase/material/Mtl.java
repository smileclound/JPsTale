package com.jme3.asset.maxase.material;

import java.io.Serializable;
import java.util.HashMap;

import com.jme3.asset.maxase.CONSTANT;

public class Mtl implements CONSTANT, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4212328481291681121L;
	public int id = -1;
	public String name;
	
	public String clazz = "Standard";
	
	public boolean twoSide = false;

	public TextureMap diffuseMap = null;
	public TextureMap opacityMap = null;

	public HashMap<Integer, Mtl> subMtls = null;
	
	@Override
	public boolean equals(Object a) {
		if (!(a instanceof Mtl)) {
			
			return false;
		}
		Mtl mat = (Mtl)a;
		
		if ((mat.name == null) != (name == null))
			return false;
		if ((mat.clazz == null) != (clazz == null))
			return false;
		if ((mat.diffuseMap == null) != (diffuseMap == null))
			return false;
		
		return (name == null || name.equals(mat.name))
			&& (clazz == null || clazz.equals(mat.clazz))
			&& twoSide == mat.twoSide
			&& (diffuseMap == null || diffuseMap.equals(mat.diffuseMap));
	}
	
	public int ScriptState = 0;
	public String strScript = null;
	public int BlendType = 0;
	public void process() {
		
		for(int cnt=0;cnt<MATS_SCRIPT_MAX;cnt++) {
			if ( name.contains(MaterialFormScript[cnt].script) ) {
				ScriptState |= MaterialFormScript[cnt].code;
			}
		}
		if (ScriptState > 0) {
			strScript = name;
		}
		for(int cnt=0;cnt<MATS_BLEND_MAX;cnt++) {
			if ( name.contains(MaterialFormBlend[cnt].script) ) {					
				BlendType  = MaterialFormBlend[cnt].code;
				break;
			}
		}
	}
	
}