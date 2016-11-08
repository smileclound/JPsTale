package org.pstale.asset.struct;

import java.io.IOException;
import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;

import org.pstale.asset.loader.SmdLoader;

import com.jme3.animation.AnimControl;
import com.jme3.animation.Animation;
import com.jme3.animation.Bone;
import com.jme3.animation.BoneTrack;
import com.jme3.animation.Skeleton;
import com.jme3.animation.SkeletonControl;
import com.jme3.material.Material;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.Node;
import com.jme3.util.LittleEndien;

/**
 * size = 1228
 */
public class PAT3D extends Flyweight {
	// DWORD Head;
	OBJ3D[] obj3d = new OBJ3D[128];
	byte[] TmSort = new byte[128];

	PAT3D TmParent;

	MATERIAL_GROUP smMaterialGroup;// 材质组

	int MaxFrame;
	int Frame;

	int SizeWidth, SizeHeight; // 臭捞 承捞 狼 弥措摹

	int nObj3d;
	// LPDIRECT3DTEXTURE2 *hD3DTexture;

	POINT3D Posi;
	POINT3D Angle;
	POINT3D CameraPosi;

	int dBound;
	int Bound;

	FRAME_POS[] TmFrame = new FRAME_POS[OBJ_FRAME_SEARCH_MAX];
	int TmFrameCnt;

	int TmLastFrame;
	POINT3D TmLastAngle;

	public PAT3D() {
		nObj3d = 0;
		// hD3DTexture = 0;
		TmParent = null;

		MaxFrame = 0;
		Frame = 0;

		SizeWidth = 0;
		SizeHeight = 0;

		Bound = 0;
		dBound = 0;

		TmFrameCnt = 0;

		TmLastFrame = -1;

		TmLastAngle = new POINT3D();
		TmLastAngle.x = -1;
		TmLastAngle.y = -1;
		TmLastAngle.z = -1;

		for (int i = 0; i < 128; i++) {
			TmSort[i] = (byte) i;
		}

		smMaterialGroup = null;
	}
	
	public void loadData(LittleEndien in) throws IOException {

		in.readInt();// Head
		for (int i = 0; i < 128; i++) {
			in.readInt();
		}
		in.readFully(TmSort);

		in.readInt();// smPAT3D *TmParent;

		in.readInt();// smMATERIAL_GROUP *smMaterialGroup; //皋飘府倔 弊缝

		MaxFrame = in.readInt();
		Frame = in.readInt();

		SizeWidth = in.readInt();
		SizeHeight = in.readInt();

		nObj3d = in.readInt();
		in.readInt();// LPDIRECT3DTEXTURE2 *hD3DTexture;

		Posi = new POINT3D();
		Posi.loadData(in);
		Angle = new POINT3D();
		Angle.loadData(in);
		CameraPosi = new POINT3D();
		CameraPosi.loadData(in);

		dBound = in.readInt();
		Bound = in.readInt();

		for (int i = 0; i < OBJ_FRAME_SEARCH_MAX; i++) {
			TmFrame[i] = new FRAME_POS();
			TmFrame[i].loadData(in);
		}
		TmFrameCnt = in.readInt();

		TmLastFrame = in.readInt();
		TmLastAngle = new POINT3D();
		TmLastAngle.loadData(in);

	}

	public void loadFile(LittleEndien in, String NodeName, PAT3D BipPat) throws IOException {

		FILE_HEADER header = new FILE_HEADER();
		header.loadData(in);

		// 读取Obj3D物体信息
		FILE_OBJINFO[] FileObjInfo = new FILE_OBJINFO[header.objCounter];
		for (int i = 0; i < header.objCounter; i++) {
			FileObjInfo[i] = new FILE_OBJINFO();
			FileObjInfo[i].loadData(in);
		}

		// 记录文件头中的动画的帧数，拷贝每帧的数据。
		TmFrameCnt = header.tmFrameCounter;
		for (int i = 0; i < 32; i++) {
			TmFrame[i] = header.TmFrame[i];
		}

		// 读取材质
		// 骨骼文件(.smb)中不包含材质，因此可能没有这一段数据。
		if (header.matCounter > 0) {
			smMaterialGroup = new MATERIAL_GROUP();
			smMaterialGroup.loadData(in);
		}

		if (NodeName != null) {
			log.debug("NodeName != null && NodeName == " + NodeName);
			// 加载指定名称的3D物体
			for (int i = 0; i < header.objCounter; i++) {
				if (NodeName.equals(FileObjInfo[i].NodeName)) {
					// 跳过i个OBJ3D，直接读取其中一个。
					int offset = i * 2236;
					in.skip(offset);
					
					OBJ3D obj = new OBJ3D();
					obj.loadData(in);
					obj.loadFile(in, BipPat);
					addObject(obj);
					break;
				}
			}
		} else {
			// 读取全部3D对象
			for (int i = 0; i < header.objCounter; i++) {
				OBJ3D obj = new OBJ3D();
				// 读取OBJ3D对象，共2236字节
				obj.loadData(in);
				obj.loadFile(in, BipPat);
				addObject(obj);
			}
			linkObject();
		}

		TmParent = BipPat;
	}

	boolean addObject(OBJ3D obj) {
		// 限制物体的数量，最多128个
		if (nObj3d < 128) {
			obj3d[nObj3d] = obj;
			nObj3d++;

			// 统计动画帧数
			int frame = 0;
			if (obj.TmRotCnt > 0 && obj.TmRot != null)
				frame = obj.TmRot[obj.TmRotCnt - 1].frame;
			if (obj.TmPosCnt > 0 && obj.TmPos != null)
				frame = obj.TmPos[obj.TmPosCnt - 1].frame;
			if (MaxFrame < frame)
				MaxFrame = frame;

			// 农扁 承捞 汲沥
			if (SizeWidth < obj.maxX)
				SizeWidth = obj.maxX;
			if (SizeWidth < obj.maxZ)
				SizeWidth = obj.maxZ;
			if (SizeHeight < obj.maxY)
				SizeHeight = obj.maxY;

			// 官款爹 胶其绢 蔼
			if (Bound < obj.Bound) {
				Bound = obj.Bound;
				dBound = obj.dBound;
			}

			return true;
		}

		return false;
	}

	/**
	 * 计算物体之间的父子关系。
	 */
	void linkObject() {
		for (int i = 0; i < nObj3d; i++) {
			if (obj3d[i].NodeParent != null) {
				for (int k = 0; k < nObj3d; k++) {
					if (obj3d[i].NodeParent.equals(obj3d[k].NodeName)) {
						obj3d[i].pParent = obj3d[k];
						break;
					}
				}
			} else {
				log.debug("j = 0");
			}
		}

		int NodeCnt = 0;

		// 清零
		for (int i = 0; i < 128; i++) {
			TmSort[i] = 0;
		}

		// 首先记录根节点
		for (int i = 0; i < nObj3d; i++) {
			if (obj3d[i].pParent == null)
				TmSort[NodeCnt++] = (byte) i;
		}

		// 何葛俊 崔妨乐绰 磊侥阑 茫酒 鉴辑措肺 历厘
		for (int j = 0; j < nObj3d; j++) {
			for (int i = 0; i < nObj3d; i++) {
				if (obj3d[i].pParent != null
						&& obj3d[TmSort[j]] == obj3d[i].pParent) {
					TmSort[NodeCnt++] = (byte) i;
				}
			}
		}
	}

	/**
	 * 根据结点名称，查询Obj3D对象。
	 * 
	 * @param name
	 * @return
	 */
	OBJ3D getObjectFromName(String name) {
		for (int i = 0; i < nObj3d; i++) {
			if (obj3d[i].NodeName.equals(name)) {
				return obj3d[i];
			}
		}
		return null;
	}

	/**
	 * 生成骨骼
	 */
	Skeleton buildSkeleton() {

		HashMap<String, Bone> boneMap = new HashMap<String, Bone>();
		Bone[] bones = new Bone[nObj3d];
		for (int i = 0; i < nObj3d; i++) {
			OBJ3D obj = obj3d[i];

			// 创建一个骨头
			Bone bone = new Bone(obj.NodeName);
			bones[i] = bone;

			// 设置初始POSE
			if (OPEN_GL_AXIS) {
				Vector3f translation = new Vector3f(-obj.py, obj.pz,
						-obj.px);
				Quaternion rotation = new Quaternion(-obj.qy, obj.qz,
						-obj.qx, -obj.qw);
				Vector3f scale = new Vector3f(obj.sy, obj.sz, obj.sx);

				bone.setBindTransforms(translation, rotation, scale);
			} else {
				Vector3f translation = new Vector3f(obj.px, obj.py, obj.pz);
				Quaternion rotation = new Quaternion(-obj.qx, -obj.qy,
						-obj.qz, obj.qw);
				Vector3f scale = new Vector3f(obj.sx, obj.sy, obj.sz);

				bone.setBindTransforms(translation, rotation, scale);
			}

			// 建立父子关系
			boneMap.put(obj.NodeName, bone);
			if (obj.NodeParent != null) {
				Bone parent = boneMap.get(obj.NodeParent);
				if (parent != null)
					parent.addChild(bone);
			}

		}

		// 生成骨架
		return new Skeleton(bones);
	}

	/**
	 * 生成骨骼
	 * 
	 * @param ske
	 */
	Animation buildAnimation(Skeleton ske) {

		// 统计帧数
		int maxFrame = 0;
		for (int i = 0; i < nObj3d; i++) {
			OBJ3D obj = obj3d[i];
			if (obj.TmRotCnt > 0) {
				if (obj.TmRot[obj.TmRotCnt - 1].frame > maxFrame) {
					maxFrame = obj.TmRot[obj.TmRotCnt - 1].frame;
				}
			}
			if (obj.TmPosCnt > 0) {
				if (obj.TmPos[obj.TmPosCnt - 1].frame > maxFrame) {
					maxFrame = obj.TmPos[obj.TmPosCnt - 1].frame;
				}
			}
			if (obj.TmScaleCnt > 0) {
				if (obj.TmScale[obj.TmScaleCnt - 1].frame > maxFrame) {
					maxFrame = obj.TmScale[obj.TmScaleCnt - 1].frame;
				}
			}

			if (LOG_ANIMATION) {
				log.debug(obj.NodeName + " 最大帧=" + maxFrame);
				log.debug("TmPos:" + obj.TmPosCnt + " TmRot:"
						+ obj.TmRotCnt + " TmScl:" + obj.TmScaleCnt);
			}
		}

		// 计算动画时常
		float length = (maxFrame) / framePerSecond;

		if (LOG_ANIMATION) {
			log.debug("动画总时长=" + length);
		}

		Animation anim = new Animation("Anim", length);

		/**
		 * 统计每个骨骼的关键帧
		 */
		for (int i = 0; i < nObj3d; i++) {
			OBJ3D obj = obj3d[i];

			if (LOG_ANIMATION) {
				log.debug("TmPos:" + obj.TmPosCnt + " TmRot:"
						+ obj.TmRotCnt + " TmScl:" + obj.TmScaleCnt);
			}

			/**
			 * 统计关键帧。
			 */
			TreeMap<Integer, Keyframe> keyframes = new TreeMap<Integer, Keyframe>();
			for (int j = 0; j < obj.TmPosCnt; j++) {
				TM_POS pos = obj.TmPos[j];
				Keyframe k = getOrMakeKeyframe(keyframes, pos.frame);
				if (OPEN_GL_AXIS) {
					k.translation = new Vector3f(-pos.y, pos.z, -pos.x);
				} else {
					k.translation = new Vector3f(pos.x, pos.y, pos.z);
				}
			}

			for (int j = 0; j < obj.TmRotCnt; j++) {
				TM_ROT rot = obj.TmRot[j];
				Keyframe k = getOrMakeKeyframe(keyframes, rot.frame);
				if (OPEN_GL_AXIS) {
					k.rotation = new Quaternion(-rot.y, rot.z, -rot.x,
							-rot.w);
				} else {
					k.rotation = new Quaternion(rot.x, rot.y, rot.z, rot.w);
				}
			}

			Quaternion ori = new Quaternion(0, 0, 0, 1);
			for (Keyframe k : keyframes.values()) {
				if (k.rotation != null) {
					// ori.multLocal(k.rotation);
					ori = k.rotation.mult(ori);
					k.rotation.set(ori);
				}
			}

			for (int j = 0; j < obj.TmScaleCnt; j++) {
				TM_SCALE scale = obj.TmScale[j];
				Keyframe k = getOrMakeKeyframe(keyframes, scale.frame);
				if (OPEN_GL_AXIS) {
					k.scale = new Vector3f(scale.z, scale.y, scale.x);
				} else {
					k.scale = new Vector3f(scale.x, scale.y, scale.z);
				}
			}

			if (LOG_ANIMATION) {
				log.debug("Track[" + obj.NodeName + "]:");
			}

			/**
			 * 计算动画数据。 为BoneTrack准备数据。
			 */
			int size = keyframes.size();
			if (size == 0) {
				if (LOG_ANIMATION) {
					log.debug("  没有关键帧");
				}
				continue;// 继续检查下一个骨骼
			}

			float[] times = new float[size];
			Vector3f[] translations = new Vector3f[size];
			Quaternion[] rotations = new Quaternion[size];
			Vector3f[] scales = new Vector3f[size];

			/**
			 * 由于精灵中的pose动画、rotate动画、scale动画的数量不一定相同，
			 * 因此keyframe中有些属性的值可能是null。 如果某一帧缺少其他的数据，那么复用上一帧的数据。
			 */
			Keyframe last = null;
			/**
			 * 这个变量用来记录已经解析到了第几个Keyframe。 当n=0时，初始化last变量的值。
			 * 在循环的末尾，总是将last的引用指向当前Keyframe对象。
			 */
			int n = 0;
			for (Integer frame : keyframes.keySet()) {
				// 获取当前帧
				Keyframe current = keyframes.get(frame);

				// 检查pose动画
				if (current.translation == null) {
					if (n == 0) {
						current.translation = new Vector3f(0, 0, 0);
					} else {// 复用上一帧的数据
						current.translation = new Vector3f(last.translation);
					}
				}

				// 检查rotate动画
				if (current.rotation == null) {
					if (n == 0) {
						current.rotation = new Quaternion(0, 0, 0, 1);
					} else {
						current.rotation = new Quaternion(last.rotation);
					}
				}

				// 检查scale动画
				if (current.scale == null) {
					if (n == 0) {
						current.scale = new Vector3f(1, 1, 1);
					} else {
						current.scale = new Vector3f(last.scale);
					}
				}

				times[n] = frame / framePerSecond;
				translations[n] = current.translation;
				rotations[n] = current.rotation.normalizeLocal();
				scales[n] = current.scale;

				if (LOG_ANIMATION) {
					String str = String
							.format("  Frame=%05d time=%.5f pos=%s rot=%s scale=%s",
									frame, times[n], translations[n],
									rotations[n], scales[n]);
					log.debug(str);
				}

				// 记录当前帧
				last = current;

				n++;
			}

			BoneTrack track = new BoneTrack(ske.getBoneIndex(obj.NodeName));
			track.setKeyframes(times, translations, rotations, scales);
			anim.addTrack(track);
		}

		return anim;
	}

	/**
	 * 根据帧的编号来查询Keyframe数据，如果某个frame还没有对应的Keyframe数据，就创建一个新的。
	 * 
	 * @param keyframes
	 * @param frame
	 * @return
	 */
	private Keyframe getOrMakeKeyframe(
			SortedMap<Integer, Keyframe> keyframes, Integer frame) {
		Keyframe k = keyframes.get(frame);
		if (k == null) {
			k = new Keyframe();
			keyframes.put(frame, k);
		}
		return k;
	}

	public Node buildNode(SmdLoader loader) {
		Node rootNode = new Node("PAT3D:" + loader.key.getName());

		Skeleton ske = null;
		// 生成骨骼
		if (TmParent != null) {
			ske = TmParent.buildSkeleton();
		}

		for (int i = 0; i < nObj3d; i++) {
			OBJ3D obj = obj3d[i];
			if (obj.nFace > 0) {

				// 对所有顶点进行线性变换，否则顶点的坐标都在原点附近。
				obj.invertPoint();

				// 根据模型的材质不同，将创建多个网格，分别渲染。
				for (int mat_id = 0; mat_id < smMaterialGroup.materialCount; mat_id++) {
					// 生成网格
					Mesh mesh = obj.buildMesh(mat_id, ske);

					// 创建材质
					MATERIAL m = smMaterialGroup.materials[mat_id];
					Material mat;
					if (USE_LIGHT) {
						mat = loader.createLightMaterial(m);
					} else {
						mat = loader.createMiscMaterial(m);
					}

					// 创建几何体并应用材质。
					Geometry geom = new Geometry(obj3d[i].NodeName + "#" + mat_id, mesh);
					geom.setMaterial(mat);

					// 设置位置
					// FIXME 这个位置设置后并不准确，需要进一步研究。
					Vector3f translation = new Vector3f(-obj.py, obj.pz, -obj.px);
					Quaternion rotation = new Quaternion(-obj.qy, obj.qz, -obj.qx, -obj.qw);
					Vector3f scale = new Vector3f(obj.sy, obj.sz, obj.sx);
					geom.setLocalTranslation(translation);
					geom.setLocalRotation(rotation);
					geom.setLocalScale(scale);

					rootNode.attachChild(geom);
				}
			}
		}

		// 绑定动画控制器
		if (ske != null) {
			Animation anim = TmParent.buildAnimation(ske);
			AnimControl ac = new AnimControl(ske);
			ac.addAnim(anim);
			rootNode.addControl(ac);
			rootNode.addControl(new SkeletonControl(ske));
		}

		return rootNode;
	}

}