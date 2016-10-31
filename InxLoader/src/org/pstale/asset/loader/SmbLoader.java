package org.pstale.asset.loader;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import org.pstale.asset.anim.DRZPOSITIONKEY;
import org.pstale.asset.anim.DRZROTATIONKEY;
import org.pstale.asset.anim.DRZSCALEKEY;
import org.pstale.asset.anim.DrzAnimation;
import org.pstale.asset.anim.DrzAnimationNode;
import org.pstale.asset.anim.DrzAnimationSet;
import org.pstale.asset.anim.DrzSubAnimation;
import org.pstale.asset.anim.Keyframe;
import org.pstale.asset.anim.MotionControl;
import org.pstale.asset.anim.SubAnimation;
import org.pstale.asset.base.AbstractLoader;

import com.jme3.animation.AnimControl;
import com.jme3.animation.Animation;
import com.jme3.animation.Bone;
import com.jme3.animation.BoneTrack;
import com.jme3.animation.Skeleton;
import com.jme3.animation.SkeletonControl;
import com.jme3.math.Matrix4f;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;

/**
 * SMB文件解析器
 * @author yanmaoyuan
 *
 */
public class SmbLoader extends AbstractLoader {
	
	Node rootNode;
	DrzAnimation drzAnimation = null;

	protected static HashMap<String, BONEBUFF> mBoneMeshDataInfo = new HashMap<String, BONEBUFF>();

	@Override
	public Object parse(InputStream inputStream) throws IOException {
		
		if (key instanceof SmbKey) {
			drzAnimation = ((SmbKey)key).getDrzAnimation();
		} else {
			return null;
		}
		
		mBoneMeshDataInfo.clear();
		
		/**
		 * 注意这个数据结构只是暂时使用!
		 */
		rootNode = new Node("SMB animation");
		
		getByteBuffer(inputStream);

		String head = getString();
		if (!"SMD Model data Ver 0.62".equals(head)) {
			return createBox();
		}
		
		buffer.position(24);
		final int boneNum = getInt();
		final int MaterialNum = getInt();

		// check number of materials
		if (MaterialNum > 0) {
			System.err.println("Error while reading SMB file. MaterialNum(" + MaterialNum + ") must be 0.");
			return false;
		}

		// check number bones.
		if (boneNum < 1) {
			System.err.println("Error while reading SMB file. No Bone Defintion.");
			return false;
		}

		
		// Read number of Bones
		for (int sa = 0; sa < drzAnimation.mSubAnimationNum; sa++) {
			drzAnimation.mSubAnimtion[sa].SetAnimationNodeNum(boneNum);

			drzAnimation.mSubAnimtion[sa].mTicksPerSecond = 160 * 25;
			drzAnimation.mSubAnimtion[sa].mAllAniDurationTime = 0;
		}

		// Read bones
		int boneoffset = 556 + boneNum * 40;
		for (int i = 0; i < boneNum; i++) {
			// Check header
			buffer.position(boneoffset);
			if (!((buffer.get() == 'D') || (buffer.get() == 'C') || (buffer.get() == 'B'))) {
				System.err.println("Error: Invalid GeoObject header. Bone(" + i + ")");
				return false;
			}

			// Check max Bone Number
			if (drzAnimation.mSubAnimationNum > 128) {
				System.err.println("to many bones(" + drzAnimation.mSubAnimationNum + ") max (128)  in smb file.");
				return false;
			}

			// Read Vertex, Face etc
			int mFaceNum = getInt(boneoffset + 80);
			int mVertexNum = getInt(boneoffset + 84);
			int mTVFaceNum = getInt(boneoffset + 92);

			buffer.position(boneoffset + 172);
			String BoneName = getString();
			buffer.position(boneoffset + 204);
			String ParentBoneName = getString();

			// Create the BoneObject
			BONEBUFF mBoneMeshTrans = new BONEBUFF();

			// Load BoneMeshDataInfo
			mBoneMeshTrans.s1 = getInt(boneoffset + 304);
			mBoneMeshTrans.s5 = getInt();
			mBoneMeshTrans.s9 = getInt();

			mBoneMeshTrans.s2 = getInt(boneoffset + 304 + 16);
			mBoneMeshTrans.s6 = getInt();
			mBoneMeshTrans.s10 = getInt();

			mBoneMeshTrans.s3 = getInt(boneoffset + 304 + 32);
			mBoneMeshTrans.s7 = getInt();
			mBoneMeshTrans.s11 = getInt();

			mBoneMeshTrans.s4 = getInt(boneoffset + 304 + 48);
			mBoneMeshTrans.s8 = getInt();
			mBoneMeshTrans.s12 = getInt();

			mBoneMeshDataInfo.put(BoneName, mBoneMeshTrans);

			// Set AniKeyNums
			int mNumRot = getInt(boneoffset + 684);
			int mNumPos = getInt();
			int mNumScl = getInt();
			int numunk = mNumRot;

			for (int sa = 0; sa < drzAnimation.mSubAnimationNum; sa++) // max 128
			{
				DrzAnimationNode node = drzAnimation.mSubAnimtion[sa].mAnimNode[i];

				node.mBoneIndex = i;

				node.NodeName = BoneName;
				node.ParentNodeName = ParentBoneName;

				// Parse Bone Matrix
				ParseMatrix(boneoffset + 240, node.mOrgFileTransform, node.mTMRotation, node.mTMPos, node.mTMScale);

				// Read AnimationFrames
				int SubAniByteOffset = sa * 16;

				// find rotation offsets
				node.mlRotAnimationStartFrame = getInt(boneoffset + 696 + SubAniByteOffset);
				node.mlRotAnimationEndFrame = getInt(boneoffset + 696 + SubAniByteOffset + 4);
				int mRotMatOffset = getInt(boneoffset + 696 + SubAniByteOffset + 8);
				int RotKeyNum = getInt(boneoffset + 696 + SubAniByteOffset + 12);

				// find position offsets
				node.mlPosAnimationStartFrame = getInt(boneoffset + 1208 + SubAniByteOffset);
				node.mlPosAnimationEndFrame = getInt(boneoffset + 1208 + SubAniByteOffset + 4);
				int mPosMatOffset = getInt(boneoffset + 1208 + SubAniByteOffset + 8);
				int PosKeyNum = getInt(boneoffset + 1208 + SubAniByteOffset + 12);

				// find scale offsets
				node.mlSclAnimationStartFrame = getInt(boneoffset + 1720 + SubAniByteOffset);
				node.mlSclAnimationEndFrame = getInt(boneoffset + 1720 + SubAniByteOffset + 4);
				int SclMatOffset = getInt(boneoffset + 1720 + SubAniByteOffset + 8);
				int SclKeyNum = getInt(boneoffset + 1720 + SubAniByteOffset + 12);

				// parse all rotation keys
				if (RotKeyNum > 0) {
					int rotbuf = boneoffset + 2236 + (mVertexNum * 24) + (mFaceNum * 36) + (mTVFaceNum * 32);
					ParseRotationAniBlock(rotbuf, node, RotKeyNum, mRotMatOffset,
							drzAnimation.mSubAnimtion[0].mAnimNode[i]);
				}

				// parse all Position Keys
				if (PosKeyNum > 0) {
					int posbuf = boneoffset + 2236 + (mVertexNum * 24) + (mFaceNum * 36) + (mTVFaceNum * 32) + (mNumRot * 20);
					ParsePositionAniBlock(posbuf, node, PosKeyNum, mPosMatOffset,
							drzAnimation.mSubAnimtion[0].mAnimNode[i]);
				}

				// CONTROL_SCALE_TRACK
				if (SclKeyNum > 0) {
					int sclbuf = boneoffset + 2236 + (mVertexNum * 24) + (mFaceNum * 36) + (mTVFaceNum * 32) + (mNumRot * 20) + (mNumPos * 16);
					ParseScaleAniBlock(sclbuf, node, SclKeyNum, SclMatOffset,
							drzAnimation.mSubAnimtion[0].mAnimNode[i]);
				}
			}

			// Now we increase the position of the objbuf for the next object
			boneoffset += 2236;
			boneoffset += mVertexNum * 24;
			boneoffset += mFaceNum * 36;
			boneoffset += mTVFaceNum * 32;
			boneoffset += mNumRot * 20;
			boneoffset += mNumPos * 16;
			boneoffset += mNumScl * 16;
			boneoffset += numunk * 64;
		}

		addNodes(drzAnimation);

		if (drzAnimation.mSubAnimationNum > 0) {
			buildSkeleton();
			makeAnimation();
		}
		
		return rootNode;
	}
	
	private void ParseMatrix(final int offset, final Matrix4f pMatrix,
			final Quaternion pTMRotation, final Vector3f pTMPos,
			final Vector3f pTMScale) {
		buffer.position(offset);

		// *TM_ROW0
		pMatrix.m00 = getPTDouble();
		pMatrix.m02 = getPTDouble();
		pMatrix.m01 = getPTDouble();
		pMatrix.m03 = getPTDouble();

		// *TM_ROW2
		pMatrix.m20 = getPTDouble();
		pMatrix.m22 = getPTDouble();
		pMatrix.m21 = getPTDouble();
		pMatrix.m23 = getPTDouble();

		// *TM_ROW1
		pMatrix.m10 = getPTDouble();
		pMatrix.m12 = getPTDouble();
		pMatrix.m11 = getPTDouble();
		pMatrix.m13 = getPTDouble();

		// *TM_ROW3
		pMatrix.m30 = getPTDouble();
		pMatrix.m32 = getPTDouble();
		pMatrix.m31 = getPTDouble();
		pMatrix.m33 = getPTDouble();

		// TMRotation
		buffer.position(offset + 388);
		float x = getFloat();
		float y = getFloat();
		float z = getFloat();
		float w = getFloat();

		pTMRotation.set(-x, -y, -z, w);

		// *TM_SCALE
		x = getPTDouble();
		y = getPTDouble();
		z = getPTDouble();
		pTMScale.set(x, y, z);

		// *TM_POS
		x = getPTDouble();
		y = getPTDouble();
		z = getPTDouble();
		pTMPos.set(x, y, z);
	}

	private void ParseRotationAniBlock(final int _offset,
			final DrzAnimationNode ppAniNode, final int RotKeyNum,
			final int RotMatOffset, final DrzAnimationNode AseExportRootNode) {

		int buf = _offset;
		buf += RotMatOffset * 20;

		Quaternion cur = new Quaternion();
		for (int i = RotMatOffset; i < RotMatOffset + RotKeyNum; i++) {
			int time = getInt(buf);
			float x = getFloat();
			float y = getFloat();
			float z = getFloat();
			float w = getFloat();

			Quaternion q = new Quaternion(-x, -y, -z, w);

			if (i - RotMatOffset != 0) {
				cur.multLocal(q);
			} else {
				cur.set(q);
			}

			DRZROTATIONKEY rotKey = new DRZROTATIONKEY();
			rotKey.mTime = time - ppAniNode.mlRotAnimationStartFrame;
			rotKey.mValue = new Quaternion(cur);

			// need this to get to my quaternion conventions
			ppAniNode.mRotationKeys.add(rotKey);

			// We need non reversed and stand alone Quaternion for ase files. So
			// i create an export list, insteed reverse the dx quats
			DRZROTATIONKEY AseExportRotKey = new DRZROTATIONKEY();
			AseExportRotKey.mTime = time;
			AseExportRotKey.mValue = new Quaternion(y, -z, x, w);
			AseExportRootNode.mAseExportRotationKeys.add(AseExportRotKey);

			buf += 20;
		}
	}

	private void ParsePositionAniBlock(final int _offset,
			final DrzAnimationNode ppAniNode, final int PosKeyNum,
			final int PosMatOffset, final DrzAnimationNode AseExportRootNode) {
		int buf = _offset;
		buf += PosMatOffset * 16;
		for (int i = PosMatOffset; i < PosMatOffset + PosKeyNum; i++) {
			int time = getInt(buf);
			float x = getFloat();
			float y = getFloat();
			float z = getFloat();

			DRZPOSITIONKEY posKey = new DRZPOSITIONKEY();
			posKey.mTime = time - ppAniNode.mlPosAnimationStartFrame;
			posKey.mValue = new Vector3f(x, y, z);
			ppAniNode.mPositionKeys.add(posKey);

			DRZPOSITIONKEY posKey2 = new DRZPOSITIONKEY();
			posKey2.mTime = time;
			posKey2.mValue = new Vector3f(x, y, z);
			AseExportRootNode.mAseExportPositionKeys.add(posKey2);

			buf += 16;
		}
	}

	private void ParseScaleAniBlock(final int _offset,
			final DrzAnimationNode ppAniNode, final int SclKeyNum,
			final int SclMatOffset, final DrzAnimationNode AseExportRootNode) {
		int buf = _offset;
		buf += SclMatOffset * 16;
		for (int i = SclMatOffset; i < SclMatOffset + SclKeyNum; i++) {
			int time = getInt(buf);
			float x = getPTDouble();
			float y = getPTDouble();
			float z = getPTDouble();

			DRZSCALEKEY sclKey = new DRZSCALEKEY();
			sclKey.mTime = time - ppAniNode.mlSclAnimationStartFrame;
			sclKey.mValue = new Vector3f(x, y, z);
			ppAniNode.mScaleKeys.add(sclKey);

			DRZSCALEKEY sclKey2 = new DRZSCALEKEY();
			sclKey2.mTime = time;
			sclKey2.mValue = new Vector3f(z, y, x);
			AseExportRootNode.mAseExportScaleKeys.add(sclKey2);

			buf += 16;
		}
	}
	
	/**
	 * 计算骨骼之间的继承关系，以及动画的时长。
	 * 
	 * @param animation
	 * @return
	 */
	private boolean addNodes(final DrzAnimation animation) {
		// List<Animation> animList
		final DrzSubAnimation[] animList = animation.mSubAnimtion;

		for (int sa = 0; sa < animation.mSubAnimationNum; sa++)// max 128
		{
			// Animation anim = animList.get(sa);
			DrzSubAnimation anim = animList[sa];

			int iHighestFirstFrame = 0;
			int iHighestLastFrame = 0;

			// Process each Node
			for (int i = 0; i < anim.mAnimNodeNum; i++) {
				// Tracks in Animation
				DrzAnimationNode boneTrack = anim.mAnimNode[i];

				// Get highest last frame of current subanimation
				if (boneTrack.mlRotAnimationEndFrame > iHighestLastFrame) {
					iHighestLastFrame = boneTrack.mlRotAnimationEndFrame;
				}
				if (boneTrack.mlPosAnimationEndFrame > iHighestLastFrame) {
					iHighestLastFrame = boneTrack.mlPosAnimationEndFrame;
				}
				if (boneTrack.mlSclAnimationEndFrame > iHighestLastFrame) {
					iHighestLastFrame = boneTrack.mlSclAnimationEndFrame;
				}

				// Get highest first frame of current subanimation
				if (boneTrack.mlRotAnimationStartFrame > iHighestFirstFrame) {
					iHighestFirstFrame = boneTrack.mlRotAnimationStartFrame;
				}
				if (boneTrack.mlPosAnimationStartFrame > iHighestFirstFrame) {
					iHighestFirstFrame = boneTrack.mlPosAnimationStartFrame;
				}
				if (boneTrack.mlSclAnimationStartFrame > iHighestFirstFrame) {
					iHighestFirstFrame = boneTrack.mlSclAnimationStartFrame;
				}
			}

			// Set SubANimation Duration
			anim.mAllAniDurationTime = iHighestLastFrame - iHighestFirstFrame;
		}

		SetupAnimationSets(animation);

		return true;

		/*****************************************
		 * ASE Loader http://read.pudn.com/downloads177/sourcecode/graph/824287/
		 * CASEMeshFileLoader.cpp__.htm
		 *****************************************/
	}
	
	private void SetupAnimationSets(DrzAnimation pAnimation) {
		if (pAnimation == null)
			return;

		// 动画轨迹数目
		int animCount = pAnimation.mAnimationSetMap.size();
		for (int i = 0; i < animCount; i++) {
			DrzAnimationSet animSet = drzAnimation.mAnimationSetMap.get(i);

			// Get AnimationTime
			animSet.SetStartTime = animSet.AnimationStartKey / pAnimation.mSubAnimtion[0].mTicksPerSecond;
			animSet.SetEndTime1 = animSet.AnimationEndKey1 / pAnimation.mSubAnimtion[0].mTicksPerSecond;

			// set the start time of this animationset
			animSet.AnimationDurationTime = animSet.SetEndTime1 - animSet.SetStartTime;
		}
	}
	
	void buildSkeleton() {
		DrzAnimationNode[] nodes = drzAnimation.mSubAnimtion[0].mAnimNode;

		HashMap<String, Bone> boneMap = new HashMap<String, Bone>();
		List<Bone> boneList = new ArrayList<Bone>();
		for (int i = 0; i < nodes.length; i++) {
			DrzAnimationNode obj = nodes[i];

			Bone bone = new Bone(obj.NodeName);
			bone.setBindTransforms(obj.mTMPos, obj.mTMRotation, obj.mTMScale);

			boneMap.put(obj.NodeName, bone);
			boneList.add(bone);

			// I AM YOUR FATHER!!!
			if (obj.ParentNodeName.length() > 0) {
				Bone parent = boneMap.get(obj.ParentNodeName);
				parent.addChild(bone);
			}

		}

		Bone[] bones = boneList.toArray(new Bone[boneList.size()]);
		Skeleton ske = new Skeleton(bones);

		AnimControl ac = new AnimControl(ske);
		rootNode.addControl(ac);

		SkeletonControl sc = new SkeletonControl(ske);
		rootNode.addControl(sc);
	}

	private Keyframe getOrMakeKeyframe(SortedMap<Long, Keyframe> keyframes,
			long time) {
		Keyframe k = keyframes.get(time);
		if (k == null) {
			k = new Keyframe();
			keyframes.put(time, k);
		}
		return k;
	}

	void makeAnimation() {
		AnimControl ac = rootNode.getControl(AnimControl.class);
		Skeleton ske = ac.getSkeleton();

		// 解析动画数据
		for (int i = 0; i < drzAnimation.mSubAnimationNum; i++) {

			DrzSubAnimation animation = drzAnimation.mSubAnimtion[i];

			// 计算动画时长
			float length = (float) animation.mAllAniDurationTime
					/ (float) animation.mTicksPerSecond;
			Animation anim = new Animation(i + "", length);

			// Calculate tracks
			TreeMap<Long, Keyframe> keyframes = new TreeMap<Long, Keyframe>();
			for (DrzAnimationNode track : animation.mAnimNode) {
				int targetBoneIndex = ske.getBoneIndex(track.NodeName);

				for (DRZPOSITIONKEY key : track.mPositionKeys) {
					Keyframe frame = getOrMakeKeyframe(keyframes, key.mTime);
					frame.translation = key.mValue;
				}

				for (DRZROTATIONKEY key : track.mRotationKeys) {
					Keyframe frame = getOrMakeKeyframe(keyframes, key.mTime);
					frame.rotation = key.mValue;
				}

				for (DRZSCALEKEY key : track.mScaleKeys) {
					Keyframe frame = getOrMakeKeyframe(keyframes, key.mTime);
					frame.scale = key.mValue;
				}

				int size = keyframes.size();

				// BoneTrack with no keyframes
				if (size <= 0) {
					continue;
				}

				float[] times = new float[size];
				Vector3f[] translations = new Vector3f[size];
				Quaternion[] rotations = new Quaternion[size];
				Vector3f[] scales = new Vector3f[size];

				int j = 0;
				for (long time : keyframes.keySet()) {
					times[j] = (float) time / 4000f;
					Keyframe frame = keyframes.get(time);
					translations[j] = frame.translation;
					rotations[j] = frame.rotation;
					scales[j] = frame.scale;

					assert translations[j] != null;
					assert rotations[j] != null;
					assert scales[j] != null;

					j++;
				}

				BoneTrack boneTrack = new BoneTrack(targetBoneIndex, times,
						translations, rotations, scales);
				anim.addTrack(boneTrack);
			}

			ac.addAnim(anim);

		}

		// 动作控制器
		HashMap<Integer, SubAnimation> subAnimSet = new HashMap<Integer, SubAnimation>();
		MotionControl mc = new MotionControl(subAnimSet);
		rootNode.addControl(mc);
		int animCount = drzAnimation.mAnimationSetMap.size();
		for (int id = 0; id < animCount; id++) {
			DrzAnimationSet animSet = drzAnimation.mAnimationSetMap.get(id);

			int animIndex = animSet.SubAnimationIndex;
			int type = animSet.AnimationTypeId;
			boolean repeat = animSet.Repeat;
			float startTime = 160 * (float) animSet.SetStartTime;
			float endTime = 160 * (float) animSet.SetEndTime1;
			float length = endTime - startTime;

			SubAnimation anim = new SubAnimation(id, type, startTime, endTime, length, repeat, animIndex);
			subAnimSet.put(id, anim);
		}
	}
}
