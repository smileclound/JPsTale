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
import org.pstale.asset.base.ByteReader;
import org.pstale.asset.mesh.DrzFaceTextureId;
import org.pstale.asset.mesh.DrzMaterials;
import org.pstale.asset.mesh.DrzMesh;
import org.pstale.asset.mesh.DrzSubMesh;

import com.jme3.animation.AnimControl;
import com.jme3.animation.Animation;
import com.jme3.animation.Bone;
import com.jme3.animation.BoneTrack;
import com.jme3.animation.Skeleton;
import com.jme3.animation.SkeletonControl;
import com.jme3.material.Material;
import com.jme3.material.RenderState;
import com.jme3.material.RenderState.FaceCullMode;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Matrix4f;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.math.Vector4f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.Node;
import com.jme3.scene.VertexBuffer.Type;
import com.jme3.util.BufferUtils;

public class ImportSmd extends ByteReader {

	AbstractLoader loader;

	DrzMesh mesh;

	Node rootNode = new Node();

	class BONEBUFF {
		public int s1;
		public int s2;
		public int s3;
		public int s4;
		public int s5;
		public int s6;
		public int s7;
		public int s8;
		public int s9;
		public int s10;
		public int s11;
		public int s12;
	}

	private HashMap<String, BONEBUFF> mBoneMeshDataInfo = new HashMap<String, BONEBUFF>();

	public ImportSmd(AbstractLoader loader, DrzMesh mesh) {
		this.loader = loader;

		this.mesh = mesh;
	}

	/**
	 * 加载骨骼动画数据
	 * 
	 * @param inputStream
	 * @throws IOException
	 */
	public synchronized boolean loadSmb(InputStream inputStream)
			throws IOException {
		getByteBuffer(inputStream);

		buffer.position(24);
		final int boneNum = getInt();
		final int MaterialNum = getInt();

		// check number of materials
		if (MaterialNum > 0) {
			System.out.println("Error while reading SMB file. MaterialNum("
					+ MaterialNum + ") must be 0.");
			return false;
		}

		// check number bones.
		if (boneNum < 1) {
			System.out
					.println("Error while reading SMB file. No Bone Defintion.");
			return false;
		}

		// Read number of Bones
		for (int sa = 0; sa < mesh.mAnimation.mSubAnimationNum; sa++) {
			mesh.mAnimation.mSubAnimtion[sa].SetAnimationNodeNum(boneNum);

			mesh.mAnimation.mSubAnimtion[sa].mTicksPerSecond = 160 * 25;
			mesh.mAnimation.mSubAnimtion[sa].mAllAniDurationTime = 0;
		}

		// Read bones
		int boneoffset = 556 + boneNum * 40;
		for (int i = 0; i < boneNum; i++) {
			// Check header
			buffer.position(boneoffset);
			if (!((buffer.get() == 'D') || (buffer.get() == 'C') || (buffer
					.get() == 'B'))) {
				System.out.println("Error: Invalid GeoObject header. Bone(" + i
						+ ")");
				return false;
			}

			// Check max Bone Number
			if (mesh.mAnimation.mSubAnimationNum > 128) {
				System.out.println("to many bones("
						+ mesh.mAnimation.mSubAnimationNum
						+ ") max (128)  in smb file.");
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

			for (int sa = 0; sa < mesh.mAnimation.mSubAnimationNum; sa++) // max
																			// 128
			{
				DrzAnimationNode node = mesh.mAnimation.mSubAnimtion[sa].mAnimNode[i];

				node.mBoneIndex = i;

				node.NodeName = BoneName;
				node.ParentNodeName = ParentBoneName;

				// Parse Bone Matrix
				ParseMatrix(boneoffset + 240, node.mOrgFileTransform,
						node.mTMRotation, node.mTMPos, node.mTMScale);

				// Read AnimationFrames
				int SubAniByteOffset = sa * 16;

				// find rotation offsets
				node.mlRotAnimationStartFrame = getInt(boneoffset + 696
						+ SubAniByteOffset);
				node.mlRotAnimationEndFrame = getInt(boneoffset + 696
						+ SubAniByteOffset + 4);
				int mRotMatOffset = getInt(boneoffset + 696 + SubAniByteOffset
						+ 8);
				int RotKeyNum = getInt(boneoffset + 696 + SubAniByteOffset + 12);

				// find position offsets
				node.mlPosAnimationStartFrame = getInt(boneoffset + 1208
						+ SubAniByteOffset);
				node.mlPosAnimationEndFrame = getInt(boneoffset + 1208
						+ SubAniByteOffset + 4);
				int mPosMatOffset = getInt(boneoffset + 1208 + SubAniByteOffset
						+ 8);
				int PosKeyNum = getInt(boneoffset + 1208 + SubAniByteOffset
						+ 12);

				// find scale offsets
				node.mlSclAnimationStartFrame = getInt(boneoffset + 1720
						+ SubAniByteOffset);
				node.mlSclAnimationEndFrame = getInt(boneoffset + 1720
						+ SubAniByteOffset + 4);
				int SclMatOffset = getInt(boneoffset + 1720 + SubAniByteOffset
						+ 8);
				int SclKeyNum = getInt(boneoffset + 1720 + SubAniByteOffset
						+ 12);

				// parse all rotation keys
				if (RotKeyNum > 0) {
					int rotbuf = boneoffset + 2236 + (mVertexNum * 24)
							+ (mFaceNum * 36) + (mTVFaceNum * 32);
					ParseRotationAniBlock(rotbuf, node, RotKeyNum,
							mRotMatOffset,
							mesh.mAnimation.mSubAnimtion[0].mAnimNode[i]);
				}

				// parse all Position Keys
				if (PosKeyNum > 0) {
					int posbuf = boneoffset + 2236 + (mVertexNum * 24)
							+ (mFaceNum * 36) + (mTVFaceNum * 32)
							+ (mNumRot * 20);
					ParsePositionAniBlock(posbuf, node, PosKeyNum,
							mPosMatOffset,
							mesh.mAnimation.mSubAnimtion[0].mAnimNode[i]);
				}

				// CONTROL_SCALE_TRACK
				if (SclKeyNum > 0) {
					int sclbuf = boneoffset + 2236 + (mVertexNum * 24)
							+ (mFaceNum * 36) + (mTVFaceNum * 32)
							+ (mNumRot * 20) + (mNumPos * 16);
					ParseScaleAniBlock(sclbuf, node, SclKeyNum, SclMatOffset,
							mesh.mAnimation.mSubAnimtion[0].mAnimNode[i]);
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

		addNodes(mesh.mAnimation);

		if (mesh.mAnimation.mSubAnimationNum > 0) {
			printSkeleton();
			makeAnimation();
		}

		return true;
	}

	void printSkeleton() {
		DrzAnimationNode[] nodes = mesh.mAnimation.mSubAnimtion[0].mAnimNode;

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
		for (int i = 0; i < mesh.mAnimation.mSubAnimationNum; i++) {

			DrzSubAnimation animation = mesh.mAnimation.mSubAnimtion[i];

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
		int animCount = mesh.mAnimation.mAnimationSetMap.size();
		for (int id = 0; id < animCount; id++) {
			DrzAnimationSet animSet = mesh.mAnimation.mAnimationSetMap.get(id);

			int animIndex = animSet.SubAnimationIndex;
			int type = animSet.AnimationTypeId;
			boolean repeat = animSet.Repeat;
			float startTime = 160 * (float) animSet.SetStartTime;
			float endTime = 160 * (float) animSet.SetEndTime1;
			float length = endTime - startTime;

			SubAnimation anim = new SubAnimation(id, type, startTime, endTime,
					length, repeat, animIndex);
			subAnimSet.put(id, anim);
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
			DrzAnimationSet animSet = mesh.mAnimation.mAnimationSetMap.get(i);

			// Get AnimationTime
			animSet.SetStartTime = animSet.AnimationStartKey
					/ pAnimation.mSubAnimtion[0].mTicksPerSecond;
			animSet.SetEndTime1 = animSet.AnimationEndKey1
					/ pAnimation.mSubAnimtion[0].mTicksPerSecond;

			// set the start time of this animationset
			animSet.AnimationDurationTime = animSet.SetEndTime1
					- animSet.SetStartTime;
		}
	}

	/**
	 * 加载模型的网格数据
	 * 
	 * @param inputStream
	 * @throws IOException
	 */
	public synchronized boolean loadSmd(InputStream inputStream)
			throws IOException {
		getByteBuffer(inputStream);

		// Parse Materials
		buffer.position(24);
		int meshNum = getInt();
		buffer.position(28);
		mesh.SubMaterialNum = getInt();

		// Parse Materials
		int MaterialOffset = 556 + (meshNum * 40) + 88;
		int MaterialByteSize = ParseMaterials(MaterialOffset);

		// read meshes
		int meshOffset = MaterialOffset + MaterialByteSize;
		for (int i = 0; i < meshNum; i++) {
			DrzSubMesh subMesh = new DrzSubMesh();

			// Check header
			buffer.position(meshOffset);
			if (!((buffer.get() == 'D') || (buffer.get() == 'C') || (buffer
					.get() == 'B'))) {
				System.out.println("Invalid mesh header. mesh(" + i + ")\n");
				return false;
			}

			// check if model has bones
			boolean hasBones = false;
			buffer.position(meshOffset + 16);
			if (getInt() != 0)
				hasBones = true;

			// Read Vertex, Face etc
			buffer.position(meshOffset + 80);
			int mFaceNum = getInt();
			int mVertexNum = getInt();
			getInt();// mUnkonwnNum
			int mTVFaceNum = getInt();

			buffer.position(meshOffset + 172);
			subMesh.NodeName = getString();
			buffer.position(meshOffset + 204);
			subMesh.NodeParentName = getString();

			// ParseMatrix(meshOffset + 240, subMesh.meshMatrix,
			// subMesh.mTMRotation, subMesh.mTMPos, subMesh.mTMScale);

			// Read vertices
			if (mVertexNum > 0) {
				buffer.position();
				if (ParseVertexData(meshOffset + 2236, mVertexNum, mFaceNum,
						mTVFaceNum, hasBones, subMesh) == false)
					return false;
			}

			// Read Faces & MatId
			if (mFaceNum > 0) {
				ParseFaceBlock(meshOffset + 2236 + (mVertexNum * 24), subMesh,
						mFaceNum);
			}

			// Read tverts && tvfaces
			if (mTVFaceNum > 0) {
				ParseUVFaceVerticesBlock(meshOffset + 2236 + (mVertexNum * 24)
						+ (mFaceNum * 36), subMesh, mTVFaceNum);
			}

			// Set AniKeyNums
			buffer.position(meshOffset + 684);
			// int mNumRot = getUnsignedInt();
			// int mNumPos = getUnsignedInt();
			// int mNumScl = getUnsignedInt();
			// int numunk = mNumRot;

			// Set position of the next mesh
			meshOffset += 2236;
			meshOffset += mVertexNum * 24;
			meshOffset += mFaceNum * 36;
			meshOffset += mTVFaceNum * 32;

			if (hasBones) {
				meshOffset += mVertexNum * 32; // physique
			}

			// Add the Mesh to the List
			mesh.subMeshList.add(subMesh);

			// printMesh(subMesh, mVertexNum, mFaceNum, mTVFaceNum);

			createMesh(subMesh);
		}

		return true;
	}

	/**
	 * 打印网格数据
	 * 
	 * @param subMesh
	 * @param mVertexNum
	 * @param mFaceNum
	 * @param mTVFaceNum
	 */
	void printMesh(DrzSubMesh subMesh, int mVertexNum, int mFaceNum,
			int mTVFaceNum) {

		AnimControl ac = rootNode.getControl(AnimControl.class);
		Skeleton ske = ac.getSkeleton();

		Mesh mesh = new Mesh();

		Geometry geom = new Geometry(subMesh.NodeName, mesh);
		rootNode.attachChild(geom);

		// geom.setMaterial(loader.getDefaultMaterial());

		assert mVertexNum == subMesh.mBoneAssignmentList.size();

		// 顶点
		Vector3f[] vertex = subMesh.mVertexList.toArray(new Vector3f[] {});// 顶点
		mesh.setBuffer(Type.Position, 3, BufferUtils.createFloatBuffer(vertex));

		// 面索引
		int f[] = new int[mFaceNum * 3];
		for (int inx = 0; inx < mFaceNum; inx++) {
			DrzFaceTextureId id = subMesh.mFaceList.get(inx);
			f[inx * 3] = id.a;
			f[inx * 3 + 1] = id.b;
			f[inx * 3 + 2] = id.c;

			int mat_id = id.material_id;
			assert mat_id <= this.mesh.SubMaterialNum;
		}
		mesh.setBuffer(Type.Index, 3, f);

		// UV 纹理映射
		Vector2f[] texCoord = subMesh.mUvVertexList1.toArray(new Vector2f[] {});
		mesh.setBuffer(Type.TexCoord, 2,
				BufferUtils.createFloatBuffer(texCoord));

		// 顶点法线
		Vector3f[] vnorm = new Vector3f[mVertexNum];
		for (int inx = 0; inx < mVertexNum; inx++) {
			vnorm[inx] = new Vector3f();
		}
		mesh.setBuffer(Type.Normal, 3, BufferUtils.createFloatBuffer(vnorm));

		// 骨骼
		byte bi[] = new byte[mVertexNum * 4];// bone index
		float bw[] = new float[mVertexNum * 4];// bone weight
		for (int inx = 0; inx < mVertexNum; inx++) {
			String bone = subMesh.mBoneAssignmentList.get(inx);
			int targetBoneIndex = ske.getBoneIndex(bone);

			bi[inx * 4] = (byte) targetBoneIndex;
			bi[inx * 4 + 1] = (byte) targetBoneIndex;
			bi[inx * 4 + 2] = (byte) targetBoneIndex;
			bi[inx * 4 + 3] = (byte) targetBoneIndex;

			bw[inx * 4] = 1;
			bw[inx * 4 + 1] = 0;
			bw[inx * 4 + 2] = 0;
			bw[inx * 4 + 3] = 0;
		}

		// 绑定动画
		mesh.setBuffer(Type.BoneIndex, 4, bi);
		mesh.setBuffer(Type.BoneWeight, 1, bw);
		mesh.setMaxNumWeights(1);
		mesh.generateBindPose(true);

		mesh.setStatic();
		mesh.updateBound();
		mesh.updateCounts();
	}

	private Material createMaterial(DrzMaterials drzMat) {
		Material mat = new Material(loader.manager,
				"Common/MatDefs/Misc/Unshaded.j3md");
		mat.setColor("Color", ColorRGBA.White);
		mat.setFloat("AlphaDiscardThreshold", 0.01f);
		RenderState rs = mat.getAdditionalRenderState();

		if (drzMat.IsDoubleSided)
			rs.setFaceCullMode(FaceCullMode.Off);

		if (drzMat.texture_name_count == 0) {
			rs.setFaceCullMode(FaceCullMode.FrontAndBack);
		} else {
			mat.setTexture("ColorMap",
					loader.createTexture(drzMat.TextureUniqueIdent1));
		}

		return mat;
	}

	void createMesh(DrzSubMesh submesh) {

		if (submesh.mFaceList == null)
			return;
		if (submesh.mFaceList.size() < 1)
			return;

		AnimControl ac = rootNode.getControl(AnimControl.class);
		Skeleton ske = null;
		if (ac != null) {
			ske = ac.getSkeleton();
		}

		for (int mat_id = 0; mat_id < mesh.SubMaterialNum; mat_id++) {
			// 统计材质为mat_id的面一共有多少个
			int size = 0;
			for (int i = 0; i < submesh.mFaceList.size(); i++) {
				if (submesh.mFaceList.get(i).material_id != mat_id)
					continue;
				size++;
			}
			if (size < 1)
				continue;

			// 生成材质
			DrzMaterials dMat = mesh.MeshMaterials.get(mat_id);
			Material mat = createMaterial(dMat);

			Mesh mesh = new Mesh();
			Geometry geom = new Geometry(submesh.NodeName + "#" + mat_id, mesh);
			rootNode.attachChild(geom);
			geom.setMaterial(mat);

			Vector3f[] position = new Vector3f[size * 3];
			int[] f = new int[size * 3];
			Vector2f[] uv = new Vector2f[size * 3];
			Vector3f[] vn = new Vector3f[size * 3];
			byte bi[] = new byte[size * 12];
			Vector4f[] bw = new Vector4f[size * 3];

			int index = 0;
			// Prepare MeshData
			for (int i = 0; i < submesh.mFaceList.size(); i++) {
				DrzFaceTextureId id = submesh.mFaceList.get(i);
				// Check the MaterialIndex
				if (id.material_id != mat_id)
					continue;

				// 顶点 VERTEX
				position[index * 3 + 0] = submesh.mVertexList.get(id.a);
				position[index * 3 + 1] = submesh.mVertexList.get(id.b);
				position[index * 3 + 2] = submesh.mVertexList.get(id.c);

				// 面 FACE
				if (i < submesh.mFaceList.size()) {
					f[index * 3 + 0] = index * 3 + 0;
					f[index * 3 + 1] = index * 3 + 1;
					f[index * 3 + 2] = index * 3 + 2;
				}

				// 纹理映射 UV
				if (submesh.mUvVertexList1.size() > i * 3) {
					// UvCoords
					uv[index * 3 + 0] = submesh.mUvVertexList1.get(i * 3);
					uv[index * 3 + 1] = submesh.mUvVertexList1.get(i * 3 + 1);
					uv[index * 3 + 2] = submesh.mUvVertexList1.get(i * 3 + 2);
				}

				// 顶点法线
				vn[index * 3 + 0] = new Vector3f();
				vn[index * 3 + 1] = new Vector3f();
				vn[index * 3 + 2] = new Vector3f();

				// 骨骼
				if (ske != null) {
					String boneA = submesh.mBoneAssignmentList.get(id.a);
					String boneB = submesh.mBoneAssignmentList.get(id.b);
					String boneC = submesh.mBoneAssignmentList.get(id.c);
					byte biA = (byte) ske.getBoneIndex(boneA);
					byte biB = (byte) ske.getBoneIndex(boneB);
					byte biC = (byte) ske.getBoneIndex(boneC);

					// 骨骼索引
					bi[index * 12 + 0] = biA;
					bi[index * 12 + 1] = biA;
					bi[index * 12 + 2] = biA;
					bi[index * 12 + 3] = biA;
					bi[index * 12 + 4] = biB;
					bi[index * 12 + 5] = biB;
					bi[index * 12 + 6] = biB;
					bi[index * 12 + 7] = biB;
					bi[index * 12 + 8] = biC;
					bi[index * 12 + 9] = biC;
					bi[index * 12 + 10] = biC;
					bi[index * 12 + 11] = biC;

					// 骨骼权重
					bw[index * 3 + 0] = new Vector4f(1, 0, 0, 0);
					bw[index * 3 + 1] = new Vector4f(1, 0, 0, 0);
					bw[index * 3 + 2] = new Vector4f(1, 0, 0, 0);
				}
				index++;
			}

			mesh.setBuffer(Type.Position, 3,
					BufferUtils.createFloatBuffer(position));
			mesh.setBuffer(Type.Index, 3, f);
			mesh.setBuffer(Type.Normal, 3, BufferUtils.createFloatBuffer(vn));
			mesh.setBuffer(Type.TexCoord, 2, BufferUtils.createFloatBuffer(uv));

			// 骨骼
			if (ske != null) {
				mesh.setBuffer(Type.BoneIndex, 4, bi);
				mesh.setBuffer(Type.BoneWeight, 1,
						BufferUtils.createFloatBuffer(bw));
				mesh.setMaxNumWeights(1);
				mesh.generateBindPose(true);
			}

			mesh.setStatic();
			mesh.updateBound();
			mesh.updateCounts();
		}
	}

	/**
	 * Calculate face normals
	 * 
	 * @param faceNormals
	 * @param vertexs
	 * @param indices
	 */
	protected void calculateFaceNormals(Vector3f[] faceNormals,
			Vector3f[] vertexs, int[] indices) {
		for (int i = 0; i < faceNormals.length; i++) {
			int index0 = indices[i * 3];
			int index1 = indices[i * 3 + 1];
			int index2 = indices[i * 3 + 2];

			Vector3f v1 = vertexs[index1].subtract(vertexs[index0]);
			Vector3f v2 = vertexs[index2].subtract(vertexs[index0]);

			faceNormals[i] = v1.cross(v2).normalize();
		}
	}

	private int ParseMaterials(final int offset) {
		buffer.position(offset);

		// Output Materials
		int matt = 0;

		int buf = offset;

		if (mesh.SubMaterialNum > 0) {

			for (int i = 0; i < mesh.SubMaterialNum; i++) {
				int submatbuf = buf + matt;

				DrzMaterials drzMat = new DrzMaterials();
				drzMat.MatIndex = i;

				buffer.position(submatbuf);
				drzMat.MatNameIndex = getInt();

				// Copy raw data
				buffer.position(submatbuf);
				buffer.get(drzMat.MaterialRawInfo, 0, 320);

				// Get Mat TwoSided
				buffer.position(submatbuf + 124);
				if (getInt() == 1) {
					drzMat.IsDoubleSided = true;
				} else
					drzMat.IsDoubleSided = false;

				// Get Mat-Diffuse
				buffer.position(submatbuf + 132);
				drzMat.Diffuse = getVector3f();

				// Get Mat Transparency
				drzMat.Transparency = getFloat();

				// Get Mat Selfillum
				drzMat.Selfillum = getFloat();

				// Mat solid
				buffer.position(submatbuf + 168);
				if (getInt() == 1) {
					drzMat.IsSolidState = true;
				}

				// Read using texture names.
				buffer.position(submatbuf + 320);
				int texture_names_len = getInt();
				if (texture_names_len > 0) {
					int textture_name_offset = 0;
					do {
						buffer.position(submatbuf + 324 + textture_name_offset);
						String TextureName = getString();

						if (TextureName.length() > 0) {
							int start = TextureName.lastIndexOf("\\") + 1;
							String path = TextureName.substring(start);

							if (drzMat.texture_name_count == 0) {
								drzMat.TextureUniqueIdent1 = path;
							} else {
								drzMat.TextureUniqueIdent2 = path;
							}

							drzMat.texture_name_count++;
						}

						textture_name_offset += TextureName.length() + 1;
					} while (textture_name_offset < texture_names_len);

				}

				buffer.position(submatbuf + 4);
				int ExtentMaterialInfoNum = getInt();

				// Extendet material info?
				if (ExtentMaterialInfoNum > 0) {
					// Addional MaterialInfo
					for (int c = 1; c < ExtentMaterialInfoNum; c++) {
						// SHOWERROR( "Material: " + tostr(i) +
						// "\nMaterialName:" +
						// ppMesh->mMaterials[i].MaterialName + "\nMapName: " +
						// ppMesh->mMaterials[i].MapName + "\nTexturePath: " +
						// ppMesh->mMaterials[i].mTextureName, __FILE__,
						// __LINE__ );
						// std::String cnt_mapname = tostr( mapName( submatbuf,
						// c ) );

						// int bid = c + (readbool( submatbuf, 108 ) ? 1 : 0);
						// std::String cnt_bitmap = tostr( mapBitmap( submatbuf,
						// bid ) );
					}
				}

				buffer.position(submatbuf + 304);
				if (getInt() != 0)
					System.out
							.println("WARNING: FIND MATOFFSET 304 poitive value");

				mesh.MeshMaterials.put(i, drzMat);

				buffer.position(submatbuf + 320);
				matt += (324 + getInt());
			}
		}
		return matt;
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

	private boolean ParseVertexData(int offset, int mVertexNum, int mFaceNum,
			int mTVFaceNum, boolean hasBones, DrzSubMesh submesh) {
		buffer.position(offset);
		submesh.mVertexList = new ArrayList<Vector3f>();
		submesh.mBoneAssignmentList = new ArrayList<String>();

		int vertoffset = offset;
		int physoffset = offset + (mVertexNum * 24) + (mFaceNum * 36)
				+ (mTVFaceNum * 32);

		BONEBUFF BoneBuff = new BONEBUFF();
		if (hasBones == false) {
			// Just this object itself has the data!
			int matbuf = offset + 304;

			// 4x4 matrix
			BoneBuff.s1 = getInt(matbuf + 0);
			BoneBuff.s2 = getInt(matbuf + 16);
			BoneBuff.s3 = getInt(matbuf + 32);
			BoneBuff.s4 = getInt(matbuf + 48);
			BoneBuff.s5 = getInt(matbuf + 4);
			BoneBuff.s6 = getInt(matbuf + 20);
			BoneBuff.s7 = getInt(matbuf + 36);
			BoneBuff.s8 = getInt(matbuf + 52);
			BoneBuff.s9 = getInt(matbuf + 8);
			BoneBuff.s10 = getInt(matbuf + 24);
			BoneBuff.s11 = getInt(matbuf + 40);
			BoneBuff.s12 = getInt(matbuf + 56);

		}

		for (int k = 0; k < mVertexNum; k++) {
			buffer.position(vertoffset);
			long res1 = getInt();
			long res2 = getInt();
			long res3 = getInt();

			long v1, v2, v3;

			boolean BoneFound = false;

			String strBoneName = null;

			if (hasBones == true) {
				// Since this one has bones, we must go through the bone file
				buffer.position(physoffset);
				strBoneName = getString();
				if (mBoneMeshDataInfo.containsKey(strBoneName) == true) {
					BoneBuff = mBoneMeshDataInfo.get(strBoneName);
					BoneFound = true;
				}

				submesh.mBoneAssignmentList.add(strBoneName);
			}

			if (BoneFound == true) {
				// reverting..
				v1 = -((res2 * BoneBuff.s11 * BoneBuff.s2 - res2 * BoneBuff.s10
						* BoneBuff.s3 - res1 * BoneBuff.s11 * BoneBuff.s6
						+ res1 * BoneBuff.s10 * BoneBuff.s7 - res3
						* BoneBuff.s2 * BoneBuff.s7 + res3 * BoneBuff.s3
						* BoneBuff.s6 + BoneBuff.s12 * BoneBuff.s2
						* BoneBuff.s7 - BoneBuff.s12 * BoneBuff.s3
						* BoneBuff.s6 - BoneBuff.s11 * BoneBuff.s2
						* BoneBuff.s8 + BoneBuff.s11 * BoneBuff.s4
						* BoneBuff.s6 + BoneBuff.s10 * BoneBuff.s3
						* BoneBuff.s8 - BoneBuff.s10 * BoneBuff.s4
						* BoneBuff.s7) << 8)
						/ (BoneBuff.s1 * BoneBuff.s11 * BoneBuff.s6
								- BoneBuff.s11 * BoneBuff.s2 * BoneBuff.s5
								- BoneBuff.s1 * BoneBuff.s10 * BoneBuff.s7
								+ BoneBuff.s10 * BoneBuff.s3 * BoneBuff.s5
								+ BoneBuff.s2 * BoneBuff.s7 * BoneBuff.s9 - BoneBuff.s3
								* BoneBuff.s6 * BoneBuff.s9);
				v2 = ((res2 * BoneBuff.s1 * BoneBuff.s11 - res1 * BoneBuff.s11
						* BoneBuff.s5 - res3 * BoneBuff.s1 * BoneBuff.s7 + res3
						* BoneBuff.s3 * BoneBuff.s5 - res2 * BoneBuff.s3
						* BoneBuff.s9 + res1 * BoneBuff.s7 * BoneBuff.s9
						+ BoneBuff.s1 * BoneBuff.s12 * BoneBuff.s7
						- BoneBuff.s12 * BoneBuff.s3 * BoneBuff.s5
						- BoneBuff.s1 * BoneBuff.s11 * BoneBuff.s8
						+ BoneBuff.s11 * BoneBuff.s4 * BoneBuff.s5
						+ BoneBuff.s3 * BoneBuff.s8 * BoneBuff.s9 - BoneBuff.s4
						* BoneBuff.s7 * BoneBuff.s9) << 8)
						/ (BoneBuff.s1 * BoneBuff.s11 * BoneBuff.s6
								- BoneBuff.s11 * BoneBuff.s2 * BoneBuff.s5
								- BoneBuff.s1 * BoneBuff.s10 * BoneBuff.s7
								+ BoneBuff.s10 * BoneBuff.s3 * BoneBuff.s5
								+ BoneBuff.s2 * BoneBuff.s7 * BoneBuff.s9 - BoneBuff.s3
								* BoneBuff.s6 * BoneBuff.s9);
				v3 = -((res2 * BoneBuff.s1 * BoneBuff.s10 - res1 * BoneBuff.s10
						* BoneBuff.s5 - res3 * BoneBuff.s1 * BoneBuff.s6 + res3
						* BoneBuff.s2 * BoneBuff.s5 - res2 * BoneBuff.s2
						* BoneBuff.s9 + res1 * BoneBuff.s6 * BoneBuff.s9
						+ BoneBuff.s1 * BoneBuff.s12 * BoneBuff.s6
						- BoneBuff.s12 * BoneBuff.s2 * BoneBuff.s5
						- BoneBuff.s1 * BoneBuff.s10 * BoneBuff.s8
						+ BoneBuff.s10 * BoneBuff.s4 * BoneBuff.s5
						+ BoneBuff.s2 * BoneBuff.s8 * BoneBuff.s9 - BoneBuff.s4
						* BoneBuff.s6 * BoneBuff.s9) << 8)
						/ (BoneBuff.s1 * BoneBuff.s11 * BoneBuff.s6
								- BoneBuff.s11 * BoneBuff.s2 * BoneBuff.s5
								- BoneBuff.s1 * BoneBuff.s10 * BoneBuff.s7
								+ BoneBuff.s10 * BoneBuff.s3 * BoneBuff.s5
								+ BoneBuff.s2 * BoneBuff.s7 * BoneBuff.s9 - BoneBuff.s3
								* BoneBuff.s6 * BoneBuff.s9);

				float x = (float) v1 / 256.0f;
				float y = (float) v2 / 256.0f;
				float z = (float) v3 / 256.0f;
				Vector3f vert = new Vector3f(x, y, z);
				submesh.mVertexList.add(vert);

			} else {
				System.out
						.println("Vertex read error! No valid mesh matrix found. BoneName=["
								+ strBoneName + "] hasBone=" + hasBones);
				return false;
			}

			// next vertex...
			vertoffset += 24;
			physoffset += 32;
		}

		return true;
	}

	private void ParseFaceBlock(final int offset, final DrzSubMesh submesh,
			final int mFaceNum) {
		buffer.position(offset);
		submesh.mFaceList = new ArrayList<DrzFaceTextureId>();

		int buf = offset;
		for (int k = 0; k < mFaceNum; k++) {
			buffer.position(buf);
			submesh.mFaceList.add(new DrzFaceTextureId(getShort(), getShort(),
					getShort(), getShort()));
			// next face
			buf += 36;
		}
	}

	private void ParseUVFaceVerticesBlock(final int offset,
			final DrzSubMesh submesh, final int UVFaceNum) {
		buffer.position(offset);
		submesh.mUvVertexList1 = new ArrayList<Vector2f>();

		int uvoff = offset;

		for (int i = 0; i < UVFaceNum; i++) {
			submesh.mUvVertexList1.add(new Vector2f(getFloat(uvoff + 0),
					1 - getFloat(uvoff + 12)));
			submesh.mUvVertexList1.add(new Vector2f(getFloat(uvoff + 4),
					1 - getFloat(uvoff + 16)));
			submesh.mUvVertexList1.add(new Vector2f(getFloat(uvoff + 8),
					1 - getFloat(uvoff + 20)));

			// next
			uvoff += 32;
		}
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
}
