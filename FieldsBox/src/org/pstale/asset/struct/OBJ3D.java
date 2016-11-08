package org.pstale.asset.struct;

import java.io.IOException;

import com.jme3.animation.Skeleton;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Mesh;
import com.jme3.scene.VertexBuffer.Type;
import com.jme3.util.BufferUtils;
import com.jme3.util.LittleEndien;

/**
 * size = 2236
 */
public class OBJ3D extends Flyweight {
	// DWORD Head;
	VERTEX[] Vertex;// 顶点
	FACE[] Face;// 面
	TEXLINK[] TexLink;// 纹理坐标

	OBJ3D[] Physique; // 各顶点的骨骼

	VERTEX ZeroVertex; // 坷宏璃飘 吝居 滚咆胶 蔼

	int maxZ, minZ;
	int maxY, minY;
	int maxX, minX;

	int dBound; // 官款爹 胶其绢 蔼 ^2
	int Bound; // 官款爹 胶其绢 蔼

	int MaxVertex;
	int MaxFace;

	int nVertex;
	int nFace;

	int nTexLink;

	int ColorEffect; // 祸惑瓤苞 荤侩 蜡公
	int ClipStates; // 努府俏 付胶农 ( 阿 努府俏喊 荤侩 蜡公 )

	POINT3D Posi;
	POINT3D CameraPosi;
	POINT3D Angle;
	int[] Trig = new int[8];

	// 局聪皋捞记 包访
	String NodeName;// [32]; // 坷宏璃飘狼 畴靛 捞抚
	String NodeParent;// [32]; // 何葛 坷宏璃飘狼 捞抚
	OBJ3D pParent; // 何葛 坷宏璃飘 器牢磐

	MATRIX Tm; // 扁夯 TM 青纺
	MATRIX TmInvert; // 逆矩阵
	FMATRIX TmResult; // 局聪皋捞记 青纺
	MATRIX TmRotate; // 扁夯利 雀傈 青纺

	MATRIX mWorld; // 岿靛谅钎 函券 青纺
	MATRIX mLocal; // 肺漠谅钎 函券 青纺

	int lFrame;// 没有实际作用

	float qx, qy, qz, qw; // 雀傈 孽磐聪攫
	float sx, sy, sz; // 胶纳老 谅钎
	float px, py, pz; // 器瘤记 谅钎

	TM_ROT[] TmRot; // 橇饭烙喊 雀傈 局聪皋捞记
	TM_POS[] TmPos; // 橇饭烙喊 器瘤记 局聪皋捞记
	TM_SCALE[] TmScale; // 橇饭烙喊 胶纳老 局聪皋捞记

	FMATRIX[] TmPrevRot; // 帧的动画矩阵

	int TmRotCnt;
	int TmPosCnt;
	int TmScaleCnt;

	// TM 橇饭烙 辑摹 ( 橇饭烙捞 腹栏搁 茫扁啊 塞惦 )
	FRAME_POS[] TmRotFrame = new FRAME_POS[OBJ_FRAME_SEARCH_MAX];
	FRAME_POS[] TmPosFrame = new FRAME_POS[OBJ_FRAME_SEARCH_MAX];
	FRAME_POS[] TmScaleFrame = new FRAME_POS[OBJ_FRAME_SEARCH_MAX];
	int TmFrameCnt;// 是否有动画 TRUE or FALSE

	// //////////////////
	int lpPhysuque;
	int lpOldTexLink;

	// //////////////////

	public OBJ3D() {
		NodeName = null;
		NodeParent = null;
		Tm = new MATRIX();
		pParent = null;
		TmRot = null;
		TmPos = null;
		TmScale = null;
		TmRotCnt = 0;
		TmPosCnt = 0;
		TmScaleCnt = 0;
		TmPrevRot = null;
		Face = null;
		Vertex = null;
		TexLink = null;
		Physique = null;
	}

	public void loadData(LittleEndien in) throws IOException {
		in.readInt();// Head `DCB\0`
		in.readInt();// smVERTEX *Vertex;
		in.readInt();// smFACE *Face;
		lpOldTexLink = in.readInt();// smTEXLINK *TexLink;
		lpPhysuque = in.readInt();// smOBJ3D **Physique;

		ZeroVertex = new VERTEX();
		ZeroVertex.loadData(in);

		maxZ = in.readInt();
		minZ = in.readInt();
		maxY = in.readInt();
		minY = in.readInt();
		maxX = in.readInt();
		minX = in.readInt();

		dBound = in.readInt();
		Bound = in.readInt();

		MaxVertex = in.readInt();
		MaxFace = in.readInt();

		nVertex = in.readInt();
		nFace = in.readInt();

		nTexLink = in.readInt();

		ColorEffect = in.readInt();
		ClipStates = in.readInt();

		Posi = new POINT3D();
		Posi.loadData(in);
		
		CameraPosi = new POINT3D();
		CameraPosi.loadData(in);
		
		Angle = new POINT3D();
		Angle.loadData(in);
		
		Trig = new int[8];
		for (int i = 0; i < 8; i++) {
			Trig[i] = in.readInt();
		}

		// 局聪皋捞记 包访
		NodeName = getString(in, 32);
		NodeParent = getString(in, 32);
		in.readInt();// OBJ3D *pParent;

		Tm = new MATRIX();
		Tm.loadData(in);
		
		TmInvert = new MATRIX();
		TmInvert.loadData(in);
		
		TmResult = new FMATRIX();
		TmResult.loadData(in);
		
		TmRotate = new MATRIX();
		TmRotate.loadData(in);

		mWorld = new MATRIX();
		mWorld.loadData(in);
		
		mLocal = new MATRIX();
		mLocal.loadData(in);

		lFrame = in.readInt();

		qx = in.readFloat();
		qy = in.readFloat();
		qz = in.readFloat();
		qw = in.readFloat();
		sx = in.readInt() / 256f;
		sy = in.readInt() / 256f;
		sz = in.readInt() / 256f;
		px = in.readInt() / 256f;
		py = in.readInt() / 256f;
		pz = in.readInt() / 256f;

		in.readInt();// smTM_ROT *TmRot;
		in.readInt();// smTM_POS *TmPos;
		in.readInt();// smTM_SCALE *TmScale;
		in.readInt();// smFMATRIX *TmPrevRot;

		TmRotCnt = in.readInt();
		TmPosCnt = in.readInt();
		TmScaleCnt = in.readInt();

		for (int i = 0; i < OBJ_FRAME_SEARCH_MAX; i++) {
			TmRotFrame[i] = new FRAME_POS();
			TmRotFrame[i].loadData(in);
		}
		for (int i = 0; i < OBJ_FRAME_SEARCH_MAX; i++) {
			TmPosFrame[i] = new FRAME_POS();
			TmPosFrame[i].loadData(in);
		}
		for (int i = 0; i < OBJ_FRAME_SEARCH_MAX; i++) {
			TmScaleFrame[i] = new FRAME_POS();
			TmScaleFrame[i].loadData(in);
		}
		TmFrameCnt = in.readInt();

	}

	/**
	 * 读取OBJ3D文件数据
	 * 
	 * @param PatPhysique
	 */
	public void loadFile(LittleEndien in, PAT3D PatPhysique) throws IOException {

		Vertex = new VERTEX[nVertex];
		for (int i = 0; i < nVertex; i++) {
			Vertex[i] = new VERTEX();
			Vertex[i].loadData(in);
		}

		Face = new FACE[nFace];
		for (int i = 0; i < nFace; i++) {
			Face[i] = new FACE();
			Face[i].loadData(in);
		}

		TexLink = new TEXLINK[nTexLink];
		for (int i = 0; i < nTexLink; i++) {
			TexLink[i] = new TEXLINK();
			TexLink[i].loadData(in);
		}

		TmRot = new TM_ROT[TmRotCnt];
		for (int i = 0; i < TmRotCnt; i++) {
			TmRot[i] = new TM_ROT();
			TmRot[i].loadData(in);
		}

		TmPos = new TM_POS[TmPosCnt];
		for (int i = 0; i < TmPosCnt; i++) {
			TmPos[i] = new TM_POS();
			TmPos[i].loadData(in);
		}

		TmScale = new TM_SCALE[TmScaleCnt];
		for (int i = 0; i < TmScaleCnt; i++) {
			TmScale[i] = new TM_SCALE();
			TmScale[i].loadData(in);
		}

		TmPrevRot = new FMATRIX[TmRotCnt];
		for (int i = 0; i < TmRotCnt; i++) {
			TmPrevRot[i] = new FMATRIX();
			TmPrevRot[i].loadData(in);
		}

		relinkFaceAndTex();

		// 绑定动画骨骼
		if (lpPhysuque != 0 && PatPhysique != null) {

			Physique = new OBJ3D[nVertex];

			String[] names = new String[nVertex];
			for (int i = 0; i < nVertex; i++) {
				names[i] = getString(in, 32);
			}

			for (int i = 0; i < nVertex; i++) {
				Physique[i] = PatPhysique.getObjectFromName(names[i]);
			}

		}
	}

	private void relinkFaceAndTex() {
		// 重新建立TexLink链表中的关联
		for (int i = 0; i < nTexLink; i++) {
			if (TexLink[i].lpNextTex != 0) {
				int index = (TexLink[i].lpNextTex - lpOldTexLink) / 32;
				TexLink[i].NextTex = TexLink[index];
			}
		}

		// 重新建立Face与TexLink之间的关联
		for (int i = 0; i < nFace; i++) {
			if (Face[i].lpTexLink != 0) {
				int index = (Face[i].lpTexLink - lpOldTexLink) / 32;
				Face[i].TexLink = TexLink[index];
			}
		}
	}

	/**
	 * 生成网格数据。
	 * 
	 * @param ske
	 * @return
	 */
	public Mesh buildMesh(int mat_id, Skeleton ske) {
		Mesh mesh = new Mesh();

		// 统计使用这个材质的面数
		int count = 0;
		for (int i = 0; i < nFace; i++) {
			if (Face[i].v[3] == mat_id) {
				count++;
			}
		}

		// 计算网格
		Vector3f[] position = new Vector3f[count * 3];
		int[] f = new int[count * 3];
		Vector2f[] uv = new Vector2f[count * 3];
		int index = 0;

		// Prepare MeshData
		for (int i = 0; i < nFace; i++) {
			// 忽略掉这个面
			if (Face[i].v[3] != mat_id) {
				continue;
			}

			// 顶点 VERTEX
			position[index * 3 + 0] = Vertex[Face[i].v[0]].v;
			position[index * 3 + 1] = Vertex[Face[i].v[1]].v;
			position[index * 3 + 2] = Vertex[Face[i].v[2]].v;

			// 面 FACE
			if (i < nFace) {
				f[index * 3 + 0] = index * 3 + 0;
				f[index * 3 + 1] = index * 3 + 1;
				f[index * 3 + 2] = index * 3 + 2;
			}

			// 纹理映射
			TEXLINK tl = Face[i].TexLink;
			if (tl != null) {
				// 第1组uv坐标
				uv[index * 3 + 0] = new Vector2f(tl.u[0], 1f - tl.v[0]);
				uv[index * 3 + 1] = new Vector2f(tl.u[1], 1f - tl.v[1]);
				uv[index * 3 + 2] = new Vector2f(tl.u[2], 1f - tl.v[2]);
			} else {
				uv[index * 3 + 0] = new Vector2f();
				uv[index * 3 + 1] = new Vector2f();
				uv[index * 3 + 2] = new Vector2f();
			}

			index++;
		}

		mesh.setBuffer(Type.Position, 3, BufferUtils.createFloatBuffer(position));
		mesh.setBuffer(Type.Index, 3, f);
		mesh.setBuffer(Type.TexCoord, 2, BufferUtils.createFloatBuffer(uv));

		// 骨骼蒙皮
		if (Physique != null && ske != null) {
			float[] boneIndex = new float[count * 12];
			float[] boneWeight = new float[count * 12];

			index = 0;
			for (int i = 0; i < nFace; i++) {
				// 忽略这个面
				if (Face[i].v[3] != mat_id) {
					continue;
				}

				for (int j = 0; j < 3; j++) {
					int v = Face[i].v[j];// 顶点序号
					int bi = index * 3 + j;// 对应骨骼的序号

					OBJ3D obj3d = Physique[v];
					byte targetBoneIndex = (byte) ske
							.getBoneIndex(obj3d.NodeName);

					boneIndex[bi * 4] = targetBoneIndex;
					boneIndex[bi * 4 + 1] = 0;
					boneIndex[bi * 4 + 2] = 0;
					boneIndex[bi * 4 + 3] = 0;

					boneWeight[bi * 4] = 1;
					boneWeight[bi * 4 + 1] = 0;
					boneWeight[bi * 4 + 2] = 0;
					boneWeight[bi * 4 + 3] = 0;
				}

				index++;
			}

			mesh.setMaxNumWeights(1);
			// apply software skinning
			mesh.setBuffer(Type.BoneIndex, 4, boneIndex);
			mesh.setBuffer(Type.BoneWeight, 4, boneWeight);
			// apply hardware skinning
			mesh.setBuffer(Type.HWBoneIndex, 4, boneIndex);
			mesh.setBuffer(Type.HWBoneWeight, 4, boneWeight);

			mesh.generateBindPose(true);
		}

		mesh.setStatic();
		mesh.updateBound();
		mesh.updateCounts();

		return mesh;
	}

	/**
	 * 将顺序读取的3个int，用TmInvert进行转置，获得一个GL坐标系的顶点。
	 * 
	 * <pre>
	 * 若有向量x=(v1, v2, v3, 1)与矩阵TmInvert (_11, _12, _13, _14)
	 *                                      (_21, _22, _23, _24)
	 *                                      (_31, _32, _33, _34)
	 *                                      (_41, _42, _43, _44)。
	 * 使用TmInvert对向量x进行线性变换后，得到的向量为a(res1, res2, res3, 1)。
	 *       即：TmInvert * x = a(res1, res2, res3, 1)
	 * 其中TmInvert与a已知，求x。
	 *       x = (1/TmInvert) * a
	 * </pre>
	 * 
	 * @param res1
	 * @param res2
	 * @param res3
	 * @param tm
	 */
	public Vector3f mult(long res1, long res2, long res3, MATRIX tm) {
		long v1 = -((res2 * tm._33 * tm._21 - res2 * tm._23 * tm._31 - res1
				* tm._33 * tm._22 + res1 * tm._23 * tm._32 - res3 * tm._21
				* tm._32 + res3 * tm._31 * tm._22 + tm._43 * tm._21
				* tm._32 - tm._43 * tm._31 * tm._22 - tm._33 * tm._21
				* tm._42 + tm._33 * tm._41 * tm._22 + tm._23 * tm._31
				* tm._42 - tm._23 * tm._41 * tm._32) << 8)
				/ (tm._11 * tm._33 * tm._22 + tm._23 * tm._31 * tm._12
						+ tm._21 * tm._32 * tm._13 - tm._33 * tm._21
						* tm._12 - tm._11 * tm._23 * tm._32 - tm._31
						* tm._22 * tm._13);
		long v2 = ((res2 * tm._11 * tm._33 - res1 * tm._33 * tm._12 - res3
				* tm._11 * tm._32 + res3 * tm._31 * tm._12 - res2 * tm._31
				* tm._13 + res1 * tm._32 * tm._13 + tm._11 * tm._43
				* tm._32 - tm._43 * tm._31 * tm._12 - tm._11 * tm._33
				* tm._42 + tm._33 * tm._41 * tm._12 + tm._31 * tm._42
				* tm._13 - tm._41 * tm._32 * tm._13) << 8)
				/ (tm._11 * tm._33 * tm._22 + tm._23 * tm._31 * tm._12
						+ tm._21 * tm._32 * tm._13 - tm._33 * tm._21
						* tm._12 - tm._11 * tm._23 * tm._32 - tm._31
						* tm._22 * tm._13);
		long v3 = -((res2 * tm._11 * tm._23 - res1 * tm._23 * tm._12 - res3
				* tm._11 * tm._22 + res3 * tm._21 * tm._12 - res2 * tm._21
				* tm._13 + res1 * tm._22 * tm._13 + tm._11 * tm._43
				* tm._22 - tm._43 * tm._21 * tm._12 - tm._11 * tm._23
				* tm._42 + tm._23 * tm._41 * tm._12 + tm._21 * tm._42
				* tm._13 - tm._41 * tm._22 * tm._13) << 8)
				/ (tm._11 * tm._33 * tm._22 + tm._23 * tm._31 * tm._12
						+ tm._21 * tm._32 * tm._13 - tm._33 * tm._21
						* tm._12 - tm._11 * tm._23 * tm._32 - tm._31
						* tm._22 * tm._13);

		float x = (float) v1 / 256.0f;
		float y = (float) v2 / 256.0f;
		float z = (float) v3 / 256.0f;

		if (OPEN_GL_AXIS) {
			return new Vector3f(-y, z, -x);
		} else {
			return new Vector3f(x, y, z);
		}
	}

	public void invertPoint() {

		for (int i = 0; i < nVertex; i++) {
			if (Physique != null) {
				Vertex[i].v = mult(Vertex[i].x, Vertex[i].y, Vertex[i].z,
						Physique[i].TmInvert);
			} else {
				Vertex[i].v = mult(Vertex[i].x, Vertex[i].y, Vertex[i].z,
						TmInvert);
			}
		}
	}
}