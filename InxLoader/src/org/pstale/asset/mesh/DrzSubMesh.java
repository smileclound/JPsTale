package org.pstale.asset.mesh;

import java.util.ArrayList;
import java.util.List;

import com.jme3.math.ColorRGBA;
import com.jme3.math.Matrix4f;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;

public class DrzSubMesh {
	public String NodeName;
	public String NodeParentName;

	public DrzBox BoundBox = new DrzBox();

	public Matrix4f meshMatrix = new Matrix4f();

	public Vector3f mTMPos = new Vector3f();
	public Vector3f mTMScale = new Vector3f();
	public Quaternion mTMRotation = new Quaternion();

	public List<Vector3f> mVertexList = new ArrayList<Vector3f>();
	public List<ColorRGBA> mVertexColorList = null;
	public List<DrzFaceTextureId> mFaceList = null;
	public List<Vector2f> mUvVertexList1 = null;
	public List<Vector2f> mUvVertexList2 = null;
	public List<String> mBoneAssignmentList = null;
}
