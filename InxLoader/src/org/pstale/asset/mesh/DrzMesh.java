package org.pstale.asset.mesh;

import java.util.ArrayList;
import java.util.HashMap;

import org.pstale.asset.anim.DrzAnimation;

public class DrzMesh {

	public String MeshFileName = "";

	public int MeshType = -1;
	
	public ArrayList<DrzSubMesh> subMeshList = new ArrayList<DrzSubMesh>();

	public int SubMaterialNum;

	public HashMap<Integer, DrzMaterials> MeshMaterials = new HashMap<Integer, DrzMaterials>();

	public ArrayList<DrzLight> mLightList = null;

	// Animation
	public DrzAnimation mAnimation = null;
}
