package org.pstale.asset.struct;

import java.io.IOException;

import com.jme3.math.Vector3f;
import com.jme3.scene.Mesh;
import com.jme3.scene.VertexBuffer.Type;
import com.jme3.util.BufferUtils;
import com.jme3.util.LittleEndien;
import com.jme3.util.TempVars;

/**
 * Stage3D对象的属性 文件数据的第二段，存储了一个完整的smSTAGE3D对象。 size = 262260
 * 其中的关键数据是nVertex/nFace/nTexLink/nLight这些。
 */
public class STAGE3D extends Flyweight {
	// DWORD Head; 无用的头文件指针，4字节
	int[][] StageArea;// WORD *StageArea[MAP_SIZE][MAP_SIZE];256 * 256个指针，共262144字节
	Vector3f[] AreaList;// POINT *AreaList; 一个指针，等于是一个数组
	int AreaListCnt;

	int MemMode;

	int SumCount;
	int CalcSumCount;

	public STAGE_VERTEX[] Vertex;
	public STAGE_FACE[] Face;
	public TEXLINK[] TexLink;
	public LIGHT3D[] Light;
	public MATERIAL_GROUP materialGroup;// sizeof(smMaterialGroup) = 88
	// smSTAGE_OBJECT *StageObject;
	public MATERIAL[] materials;

	public int nVertex = 0;// offset = 88 + = 262752
	public int nFace = 0;
	public int nTexLink = 0;// UvVertexNum
	public int nLight = 0;
	int nVertColor = 0;

	int Contrast = 0;
	int Bright = 0;

	Vector3f vectLight;

	// WORD *lpwAreaBuff;
	int wAreaSize;
	// RECT StageMapRect;// top bottom right left 4个整数

	// ////////////////
	// 这个整数用来记录TexLink在文件中的地址
	int lpOldTexLink;

	// ////////////////

	/**
	 * 初始化Stage3D对象
	 */
	public void loadData(LittleEndien in) throws IOException {

		// Head = FALSE;
		in.readInt();// Head
		in.readFully(new byte[262144]);// *StageArea[MAP_SIZE][MAP_SIZE]; 4 * 256 * 256 = 262144
		in.readInt();// *AreaList;
		AreaListCnt = in.readInt();
		MemMode = in.readInt();
		SumCount = in.readInt();
		CalcSumCount = in.readInt();

		in.readInt();// *Vertex
		in.readInt();// *Face
		lpOldTexLink = in.readInt();// *TexLink
		in.readInt();// *smLight
		in.readInt();// *smMaterialGroup
		in.readInt();// *StageObject
		in.readInt();// *smMaterial

		nVertex = in.readInt();
		nFace = in.readInt();
		nTexLink = in.readInt();
		nLight = in.readInt();

		nVertColor = in.readInt();
		Contrast = in.readInt();
		Bright = in.readInt();

		// 灯光的方向
		vectLight = new Vector3f();
		vectLight.x = in.readInt();
		vectLight.y = in.readInt();
		vectLight.z = in.readInt();

		in.readInt();// *lpwAreaBuff
		wAreaSize = in.readInt();

		// 下列数值是地图的边缘，x,z平面的矩形。矩形的边长被放大了256倍
		// sizeof(RECT) == 16
		in.readInt();// minX
		in.readInt();// minY
		in.readInt();// maxX
		in.readInt();// maxY
	}

	/**
	 * 加载舞台数据
	 * 
	 * @return
	 */
	public void loadFile(LittleEndien in) throws IOException {
		FILE_HEADER header = new FILE_HEADER();
		header.loadData(in);
		
		this.loadData(in);
		
		// 读取MaterialGroup
		if (header.matCounter > 0) {
			// 读取MaterialGroup对象
			materialGroup = new MATERIAL_GROUP();
			materialGroup.loadData(in);
			materials = materialGroup.materials;
		}

		// 读取Vertex
		Vertex = new STAGE_VERTEX[nVertex];
		for (int i = 0; i < nVertex; i++) {
			Vertex[i] = new STAGE_VERTEX();
			Vertex[i].loadData(in);
		}

		// 读取Face
		Face = new STAGE_FACE[nFace];
		for (int i = 0; i < nFace; i++) {
			Face[i] = new STAGE_FACE();
			Face[i].loadData(in);
		}

		// 读取TEX_LINK(其实就是uv坐标)
		TexLink = new TEXLINK[nTexLink];
		for (int i = 0; i < nTexLink; i++) {
			TexLink[i] = new TEXLINK();
			TexLink[i].loadData(in);
		}

		// 读取灯光
		if (nLight > 0) {
			Light = new LIGHT3D[nLight];
			for (int i = 0; i < nLight; i++) {
				Light[i] = new LIGHT3D();
				Light[i].loadData(in);
			}
		}

		// 重新建立Face与TexLink之间的关联
		relinkFaceAndTex();
	}

	/**
	 * 重新建立TexLink之间、Face与TexLink之间的关联。
	 * 
	 * TexLink是一个smTEXLINK数组，顺序存储，lpOldTexLink记录了其首地址。 由于
	 * {@code sizeof(smTEXLINK) = 32}，所以：{@code 索引号=(原地址-lpOldTexLink)/32}
	 */
	void relinkFaceAndTex() {
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
	 * 生成碰撞网格 将透明的、不参与碰撞检测的面统统裁剪掉，只保留参于碰撞检测的面。
	 * 
	 * @return
	 */
	public Mesh buildSolidMesh() {
		Mesh mesh = new Mesh();

		int materialCount = materialGroup.materialCount;
		/**
		 * 根据材质的特诊来筛选参加碰撞检测的物体， 将被忽略的材质设置成null，作为一种标记。
		 */
		MATERIAL m;// 临时变量
		for (int mat_id = 0; mat_id < materialCount; mat_id++) {
			m = materials[mat_id];

			if (m.MeshState == 1 && m.Transparency < 0.2f && m.BlendType != 4) {
				continue;
			}

			if ((m.UseState & sMATS_SCRIPT_NOTPASS) != 0) {
				// 这些面要参加碰撞检测
				continue;
			}

			if ((m.UseState & 0x07FF) != 0) {
				// 这些面被设置为可以直接穿透
				materials[mat_id] = null;
				continue;
			}
			
			if ( m.BlendType == 1 || m.BlendType == 4) {// ALPHA混色
				materials[mat_id] = null;
				continue;
			}

			if (m.MapOpacity != 0 || m.Transparency != 0f) {
				// 透明的面不参加碰撞检测
				materials[mat_id] = null;
				continue;
			}

			if (m.TextureType == 1) {
				// 帧动画也不纳入碰撞检测。比如火焰、飞舞的光点。
				materials[mat_id] = null;
				continue;
			}

		}

		/**
		 * 统计有多少个要参加碰撞检测的面。
		 */
		int loc[] = new int[nVertex];
		for (int i = 0; i < nVertex; i++) {
			loc[i] = -1;
		}

		int fSize = 0;
		for (int i = 0; i < nFace; i++) {
			STAGE_FACE face = Face[i];
			if (materials[face.v[3]] != null) {
				loc[face.v[0]] = face.v[0];
				loc[face.v[1]] = face.v[1];
				loc[face.v[2]] = face.v[2];

				fSize++;
			}
		}

		int vSize = 0;
		for (int i = 0; i < nVertex; i++) {
			if (loc[i] > -1) {
				vSize++;
			}
		}

		// 记录新的顶点编号
		Vector3f[] v = new Vector3f[vSize];
		vSize = 0;
		for (int i = 0; i < nVertex; i++) {
			if (loc[i] > -1) {
				v[vSize] = Vertex[i].v;
				loc[i] = vSize;
				vSize++;
			}
		}

		// 记录新的顶点索引号
		int[] f = new int[fSize * 3];
		fSize = 0;
		for (int i = 0; i < nFace; i++) {
			STAGE_FACE face = Face[i];
			if (materials[face.v[3]] != null) {
				f[fSize * 3] = loc[face.v[0]];
				f[fSize * 3 + 1] = loc[face.v[1]];
				f[fSize * 3 + 2] = loc[face.v[2]];
				fSize++;
			}
		}

		mesh.setBuffer(Type.Position, 3, BufferUtils.createFloatBuffer(v));
		mesh.setBuffer(Type.Index, 3, BufferUtils.createIntBuffer(f));

		mesh.updateBound();
		mesh.setStatic();

		return mesh;
	}

	/**
	 * 根据原有的面，计算每个顶点的法向量。
	 * 
	 * @return
	 */
	public Vector3f[] computeOrginNormals() {
		TempVars tmp = TempVars.get();

		Vector3f A;// 三角形的第1个点
		Vector3f B;// 三角形的第2个点
		Vector3f C;// 三角形的第3个点

		Vector3f vAB = tmp.vect1;
		Vector3f vAC = tmp.vect2;
		Vector3f n = tmp.vect4;

		// Here we allocate all the memory we need to calculate the normals
		Vector3f[] tempNormals = new Vector3f[nFace];
		Vector3f[] normals = new Vector3f[nVertex];

		for (int i = 0; i < nFace; i++) {
			A = Vertex[Face[i].v[0]].v;
			B = Vertex[Face[i].v[1]].v;
			C = Vertex[Face[i].v[2]].v;

			vAB = B.subtract(A, vAB);
			vAC = C.subtract(A, vAC);
			n = vAB.cross(vAC, n);

			tempNormals[i] = n.normalize();
		}

		Vector3f sum = tmp.vect4;
		int shared = 0;

		for (int i = 0; i < nVertex; i++) {
			// 统计每个点被那些面共用。
			for (int j = 0; j < nFace; j++) {
				if (Face[j].v[0] == i || Face[j].v[1] == i
						|| Face[j].v[2] == i) {
					sum.addLocal(tempNormals[j]);
					shared++;
				}
			}

			// 求均值
			normals[i] = sum.divideLocal((shared)).normalize();

			sum.zero(); // Reset the sum
			shared = 0; // Reset the shared
		}

		tmp.release();
		return normals;
	}

}