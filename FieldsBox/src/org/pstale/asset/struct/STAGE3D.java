package org.pstale.asset.struct;

import java.io.IOException;

import org.pstale.asset.control.WindAnimationControl;
import org.pstale.asset.loader.SmdLoader;

import com.jme3.material.Material;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue.Bucket;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.Node;
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

	STAGE_VERTEX[] Vertex;
	STAGE_FACE[] Face;
	TEXLINK[] TexLink;
	LIGHT3D[] Light;
	MATERIAL_GROUP materialGroup;// sizeof(smMaterialGroup) = 88
	// smSTAGE_OBJECT *StageObject;
	MATERIAL[] materials;

	int nVertex = 0;// offset = 88 + = 262752
	int nFace = 0;
	int nTexLink = 0;// UvVertexNum
	int nLight = 0;
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
	 * 生成STAGE3D对象
	 * 
	 * @return
	 */
	public Node buildNode(SmdLoader loader) {
		Node rootNode = new Node("STAGE3D:" + loader.key.getName());

		Vector3f[] orginNormal = null;
		if (USE_LIGHT) {
			// 为了让表面平滑光照，先基于原来的面和顶点计算一次法向量。
			orginNormal = computeOrginNormals();
		}

		int materialCount = materialGroup.materialCount;

		// 创建材质
		for (int mat_id = 0; mat_id < materialCount; mat_id++) {
			MATERIAL m = materials[mat_id];

			// 该材质没有使用，不需要显示。
			if (m.InUse == 0) {
				continue;
			}
			// 没有纹理，不需要显示。
			if (m.TextureCounter == 0 && m.AnimTexCounter == 0) {
				continue;
			}
			// 不可见的材质，不需要显示。
			if ((m.UseState & sMATS_SCRIPT_NOTVIEW) != 0) {
				continue;
			}

			/**
			 * 统计材质为mat_id的面一共有多少个面，用于计算需要生成多少个子网格。
			 */
			int size = 0;
			for (int i = 0; i < nFace; i++) {
				if (Face[i].v[3] != mat_id)
					continue;
				size++;
			}
			// 没有面使用这个材质，跳过。
			if (size < 1) {
				continue;
			}

			// 计算网格
			Mesh mesh = buildMesh(size, mat_id, orginNormal);
			Geometry geom = new Geometry(loader.key.getName() + "#" + mat_id, mesh);

			// 创建材质
			Material mat;
			
			// 有多个动画
			if (m.TextureType == 0) {
				// SMTEX_TYPE_MULTIMIX
				int n = m.TextureFormState[0];
				if(n >= 4) {// 4 SCROLL 滚轴 5 REFLEX 反光 6 SCROLL2 2倍速滚轴
					mat = loader.createScrollMaterial(materials[mat_id]);
				} else {
					if (USE_LIGHT) {
						mat = loader.createLightMaterial(materials[mat_id]);
					} else {
						mat = loader.createMiscMaterial(materials[mat_id]);
					}
				}
			} else {// SMTEX_TYPE_ANIMATION
				if (m.AnimTexCounter > 0) {
					// AminTexCounter大于0说明有轮播动画，创建一个Control，定时更新画面。
					mat = loader.createShiftMaterial(materials[mat_id]);
				} else {
					mat = loader.createMiscMaterial(materials[mat_id]);
				}
			}
			
			// 应用动画
			if (m.WindMeshBottom != 0 && (m.UseState & sMATS_SCRIPT_BLINK_COLOR) == 0) {
				switch (m.WindMeshBottom & 0x07FF) {
				case sMATS_SCRIPT_WINDX1:
				case sMATS_SCRIPT_WINDX2:
				case sMATS_SCRIPT_WINDZ1:
				case sMATS_SCRIPT_WINDZ2:{
					geom.addControl(new WindAnimationControl(m.WindMeshBottom & 0x07FF));
					break;
				}
				case sMATS_SCRIPT_WATER:{
					mat = loader.createRoundMaterial(materials[mat_id]);
					break;
				}
				}
			}
			
			loader.setRenderState(m, mat);

			// 应用材质
			geom.setMaterial(mat);

			rootNode.attachChild(geom);

			// 透明度
			// 只有不透明物体才需要检测碰撞网格。
			if (m.MapOpacity != 0 || m.Transparency != 0 || m.BlendType == 1 || m.BlendType == 4) {
				geom.setQueueBucket(Bucket.Translucent);
			}

			if (m.ReformTexture > 0) {
				log.debug("ReformTexture=" + m.ReformTexture);// 需要被加密的图片数目
			}
			if (m.SelfIllum > 0.0f) {
				log.debug("SelfIllum=" + m.SelfIllum);// 自发光
			}

			if (m.UseState != 0) {// ScriptState
				if ((m.UseState & sMATS_SCRIPT_RENDLATTER) != 0) {
					// MeshState |= sMATS_SCRIPT_RENDLATTER;
				}
				if ((m.UseState & sMATS_SCRIPT_CHECK_ICE) != 0) {
					// MeshState |= sMATS_SCRIPT_CHECK_ICE;
				}
				if ((m.UseState & sMATS_SCRIPT_ORG_WATER) != 0) {
					// MeshState = sMATS_SCRIPT_ORG_WATER;
				}
				if ((m.UseState & sMATS_SCRIPT_BLINK_COLOR) != 0) {
					// m.WindMeshBottom == dwBlinkCode[]{ 9, 10, 11, 12, 13, 14, 15, 16,} 8个数值的其中之一
				}
			}

		}

		if (nLight > 0) {
			// TODO 处理灯光
		}

		return rootNode;
	}

	/**
	 * 根据原有的面，计算每个顶点的法向量。
	 * 
	 * @return
	 */
	Vector3f[] computeOrginNormals() {
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

	Mesh buildMesh(int size, int mat_id, Vector3f[] orginNormal) {

		Vector3f[] position = new Vector3f[size * 3];
		int[] f = new int[size * 3];
		Vector3f[] normal = new Vector3f[size * 3];
		Vector2f[] uv1 = new Vector2f[size * 3];
		Vector2f[] uv2 = new Vector2f[size * 3];

		int index = 0;
		// Prepare MeshData
		for (int i = 0; i < nFace; i++) {
			// Check the MaterialIndex
			if (Face[i].v[3] != mat_id)
				continue;

			// 顺序处理3个顶点
			for (int vIndex = 0; vIndex < 3; vIndex++) {
				// 顶点 VERTEX
				position[index * 3 + vIndex] = Vertex[Face[i].v[vIndex]].v;
				
				if (USE_LIGHT) {
					// 法向量 Normal
					normal[index * 3 + vIndex] = orginNormal[Face[i].v[vIndex]];
				}

				// 面 FACE
				f[index * 3 + vIndex] = index * 3 + vIndex;

				// 纹理映射
				TEXLINK tl = Face[i].TexLink;
				if (tl != null) {
					// 第1组uv坐标
					uv1[index * 3 + vIndex] = new Vector2f(tl.u[vIndex],
							1f - tl.v[vIndex]);
				} else {
					uv1[index * 3 + vIndex] = new Vector2f();
				}

				// 第2组uv坐标
				if (tl != null && tl.NextTex != null) {
					tl = tl.NextTex;
					uv2[index * 3 + vIndex] = new Vector2f(tl.u[vIndex],
							1f - tl.v[vIndex]);
				} else {
					uv2[index * 3 + vIndex] = new Vector2f();
				}
			}

			index++;
		}

		Mesh mesh = new Mesh();
		mesh.setBuffer(Type.Position, 3, BufferUtils.createFloatBuffer(position));
		mesh.setBuffer(Type.Index, 3, f);
		// DiffuseMap UV
		mesh.setBuffer(Type.TexCoord, 2, BufferUtils.createFloatBuffer(uv1));
		// LightMap UV
		mesh.setBuffer(Type.TexCoord2, 2, BufferUtils.createFloatBuffer(uv2));
		
		if (USE_LIGHT) {
			// 法向量
			mesh.setBuffer(Type.Normal, 3, BufferUtils.createFloatBuffer(normal));
		}

		mesh.setStatic();
		mesh.updateBound();
		mesh.updateCounts();

		return mesh;
	}
}