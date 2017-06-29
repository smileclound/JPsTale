package com.jme3.scene.plugins.inx.mesh;

import com.jme3.math.Vector3f;

public class DrzMaterials {
	public int MatIndex;
	public String TextureUniqueIdent1;
	public String TextureUniqueIdent2;
	public int MatNameIndex;
	public boolean IsDoubleSided = false;
	public Vector3f Diffuse = new Vector3f();
	public float Transparency = 0.0f;
	public float Selfillum = 0.0f;
	public boolean IsSolidState = false;
	public byte[] MaterialRawInfo = new byte[320];
	
	public int texture_name_count;
	
}