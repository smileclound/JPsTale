package com.jme3.scene.plugins.inx.anim;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DrzInxInfo {
	public String InxFile = "";
	public String SmdFile = "";
	public String SmbFile = "";
	public String ChainInxFile = "";
	public String SharedInxFile = "";

	public HashMap<Integer, DrzAnimationSet> mAnimationSetMap = new HashMap<Integer, DrzAnimationSet>();

	public List<DrzInxMeshInfo> meshDefInfo = new ArrayList<DrzInxMeshInfo>();
}