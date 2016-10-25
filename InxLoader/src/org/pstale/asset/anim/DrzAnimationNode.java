package org.pstale.asset.anim;

import java.util.ArrayList;
import java.util.List;

import com.jme3.math.Matrix4f;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;

public class DrzAnimationNode {
	public int mBoneIndex;

	public String NodeName;
	public String ParentNodeName;

	public boolean hasParent;
	public DrzAnimationNode mParentNode;

	public int mChildNum;
	public DrzAnimationNode[] mChildNode;

	public Matrix4f mOrgFileTransform = new Matrix4f();
	public Matrix4f mTransformation = new Matrix4f();
	public Matrix4f mOffsetMatrix = new Matrix4f();
	public Matrix4f ResultTransform = new Matrix4f();

	public Vector3f mTMPos = new Vector3f();
	public Vector3f mTMScale = new Vector3f();
	public Quaternion mTMRotation = new Quaternion();

	public List<DRZSCALEKEY> mScaleKeys = new ArrayList<DRZSCALEKEY>();
	public List<DRZPOSITIONKEY> mPositionKeys = new ArrayList<DRZPOSITIONKEY>();
	public List<DRZROTATIONKEY> mRotationKeys = new ArrayList<DRZROTATIONKEY>();

	public List<DRZSCALEKEY> mAseExportScaleKeys = new ArrayList<DRZSCALEKEY>();
	public List<DRZPOSITIONKEY> mAseExportPositionKeys = new ArrayList<DRZPOSITIONKEY>();
	public List<DRZROTATIONKEY> mAseExportRotationKeys = new ArrayList<DRZROTATIONKEY>();

	public int mlRotAnimationStartFrame;
	public int mlRotAnimationEndFrame;
	public int mlPosAnimationStartFrame;
	public int mlPosAnimationEndFrame;
	public int mlSclAnimationStartFrame;
	public int mlSclAnimationEndFrame;
}