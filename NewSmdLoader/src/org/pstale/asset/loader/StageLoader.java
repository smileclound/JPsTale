package org.pstale.asset.loader;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.jme3.asset.AssetInfo;
import com.jme3.asset.AssetKey;
import com.jme3.asset.AssetLoader;
import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.material.RenderState;
import com.jme3.material.RenderState.BlendMode;
import com.jme3.material.RenderState.FaceCullMode;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue.Bucket;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.Node;
import com.jme3.scene.VertexBuffer.Type;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture.WrapMode;
import com.jme3.util.BufferUtils;

/**
 * 精灵场景加载器
 * @author yanmaoyuan
 *
 */
public class StageLoader extends ByteReader implements AssetLoader {

	static Logger log = Logger.getLogger(StageLoader.class);
	
	private final static int OBJ_FRAME_SEARCH_MAX = 32;

	class SmdFramePos {
		int startFrame;
		int endFrame;
		int posNum;
		int posCnt;
	}
	
	/**
	 * 材质
	 * size = 320
	 * @author yanmaoyuan
	 *
	 */
	private class MATERIAL {
		int InUse;
		int TextureCounter;
		TEXTUREHANDLE[] smTexture = new TEXTUREHANDLE[8];
		int[] TextureStageState = new int[8];
		int[] TextureFormState = new int[8];
		int ReformTexture;

		int MapOpacity; // 甘 坷乔矫萍 咯何 ( TRUE , FALSE )

		// 老馆利牢 加己
		int TextureType; // 咆胶媚 鸥涝 ( 钢萍雇胶 / 局聪皋捞记 )
		int BlendType; // 宏罚靛 屈侥 ( SMMAT_BLEND_XXXX )

		int Shade; // 溅捞爹 规侥 ( 畴溅捞靛 / 弊肺溅捞靛 )
		int TwoSide; // 剧搁 荤侩 咯何
		int SerialNum; // 皋飘府倔俊 措茄 绊蜡 锅龋

		Vector3f Diffuse; // Diffuse 祸惑
		float Transparency; // 捧疙档
		float SelfIllum; // 磊眉 惯堡

		int TextureSwap; // 咆胶媚 胶客俏
		int MatFrame; // 荤侩橇饭烙 ( 荤侩矫 悼扁甫 嘎眠扁 困窃 )
		int TextureClip; // 胶客俏侩 咆胶媚 努赋蜡公 ( TRUE 搁 咆胶媚 努府俏 倾啊 )

		// 皋浆 包访 加己
		int UseState; // 侩档 ( 荤侩 加己 )
		int MeshState; // MESH狼 龙皑 加己蔼

		// Mesh 函屈 包访 汲沥
		int WindMeshBottom; // 官恩阂扁 皋浆 函屈 矫累 蔼

		// 俊聪皋捞记 咆胶媚 加己
		TEXTUREHANDLE[] smAnimTexture = new TEXTUREHANDLE[32]; // 局聪皋捞记 咆胶媚 勤甸 府胶飘
		int AnimTexCounter; // 局固匙捞记 咆胶媚 墨款磐
		int FrameMask; // 局聪皋捞记侩 橇饭烙 付胶农
		int Shift_FrameSpeed; // 橇饭烙 刘啊 加档 ( 鸥捞赣甫 Shift 窍咯 拌魂 )
		int AnimationFrame; // 橇饭烙 锅龋 ( 绊沥老 版快 橇饭烙蔼 / SMTEX_AUTOANIMATION 篮 磊悼 )
	}
	
	class TEXTUREHANDLE {
		String Name;// [64];
		String NameA;// [64];
		int Width, Height;
		int UsedTime;
		int UseCounter;// 这个变量是给缓存器的标志位，记录这个Texture是否已经使用。
		int MapOpacity; // 是否透明( TRUE , FALSE )
		int TexSwapMode; // ( TRUE / FALSE )
		TEXTUREHANDLE TexChild;
	}
	
	class FTPOINT {
	    float u,v;              
	}
	
	/**
	 * size = 24
	 *
	 */
	class VERTEX {
		int x,y,z;// 坐标
		int nx,ny,nz;// normals 法向量
	}
	
	/**
	 * size = 36
	 */
	class FACE{
		short[] v= new short[4];// a,b,c,Matrial
	    FTPOINT[] t = new FTPOINT[3];
	    TEXLINK[] lpTexLink;
	}
	/**
	 * size = 28
	 */
	class STAGE_VERTEX {
	    int sum;
	    //smRENDVERTEX *lpRendVertex;
	    float x,y,z;
	    float r, g, b, a;// 除以256才能用作ColorRGBA
	}
	
	/**
	 * size = 28
	 *
	 */
	class STAGE_FACE {
	    int sum;
	    int CalcSum;
	    int a, b, c, mat_id;
	    int lpTexLink;// 这是一个指针，指向TEXLINK结构体
	    TEXLINK TexLink;// 若lpTexLink != 0，则TexLink指向一个实际的对象象

	    float nx, ny, nz, y;// Cross氦磐( Normal )  ( nx , ny , nz , [0,1,0]氦磐 Y ); 
	}
	
	/**
	 * size = 32
	 *
	 */
	class TEXLINK {
		float[] u = new float[3];
		float[] v = new float[3];
		int hTexture;
		int lpNextTex;// 这是一个指针，指向TEXLINK结构体
		TEXLINK NextTex;// 若lpNextTex != 0，则NextTex指向一个实际的对象
	}
	
	/**
	 * size = 22
	 */
	class LIGHT3D {
	    int type;
	    float x,y,z;
	    float Range;
	    float r,g,b;
	}
	
	/**
	 * SMD文件头
	 * 占文件前 size = 556;
	 */
	String header;// 24字节
	int objCounter;
	int matCounter;
	int matFilePoint;
	int firstObjInfoPoint;
	int tmFrameCounter;
	SmdFramePos[] data = new SmdFramePos[32];// 512字节
	
	/**
	 * Stage3D对象的属性
	 * 文件数据的第二段，存储了一个完整的smSTAGE3D对象。 size = 262260
	 * 其中的关键数据是nVertex/nFace/nTexLink/nLight这些。
	 */
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
	// smMATERIAL_GROUP    *smMaterialGroup;// sizeof(smMaterialGroup) = 88
	// smSTAGE_OBJECT      *StageObject;
	MATERIAL[]          smMaterial;
	
	int nVertex = 0;// offset = 88 +  = 262752
	int nFace = 0;
	int nTexLink = 0;//UvVertexNum
	int nLight = 0;
	int nVertColor = 0;
	
	int Contrast = 0;
	int Bright = 0;
	
	Vector3f vectLight;
	
	// WORD    *lpwAreaBuff;
	int     wAreaSize;
	// RECT    StageMapRect;// top bottom right left 4个整数
	
	//////////////////
	// 这个整数用来记录TexLink在文件中的地址
	int lpOldTexLink;
	//////////////////
	
	/**
	 * 若文件头中的mat>0，说明有材质。
	 * 接下来第三部分应该是一个完整的smMATERIAL_GROUP对象。size = 88。
	 */
	// DWORD Head
	// smMaterial* materials
	int materialCount;
	int reformTexture;
	int maxMaterial;
	int lastSearchMaterial;
	String lastSearchName;
	
	public AssetManager manager = null;
	public AssetKey<?> key = null;
	
	public Material defaultMaterial;
	
	@Override
	public Object load(AssetInfo assetInfo) throws IOException {
		key = assetInfo.getKey();
		manager = assetInfo.getManager();
		
		/**
		 * 缓存文件
		 */
		getByteBuffer(assetInfo.openStream());
		
		/**
		 * 读取文件头
		 */
		readHead();
		
		/**
		 * 若用户使用了SmdKey，就根据type来决定采用哪种方式来加载模型。
		 */
		if (key instanceof SmdKey) {
			switch (((SmdKey) key).type) {
			case STAGE3D:
				return loadStage();
			case STAGE_OBJ:
			case PAT3D:
			case MODEL:
			case BONE:
			}
			
			return null;
		} else {
			/**
			 * 若用户没有使用SmdKey，就根据文件头来判断。
			 * 尝试加载Stage或Model
			 */
			if ("SMD Stage data Ver 0.72".equals(header)) {// 地图
				return loadStage();
			} else if ("SMD Model data Ver 0.62".equals(header)){// 模型
				
				return null;
			} else {
				
				return null;
			}
		}
	}
	
	/**
	 * 初始化Stage3D对象
	 */
	private void initStage() {
		// 灯光的方向
		vectLight = new Vector3f(1f, -1f, 0.5f).normalizeLocal();
		
		Bright      = 160;//DEFAULT_BRIGHT (smType.h)
	    Contrast    = 300;//DEFAULT_CONTRAST (smType.h)
	    
	    // Head = FALSE;
	    MemMode = 0;
	    SumCount = 0;
	    CalcSumCount = 0;
	    
	    nLight   = 0;
	    nTexLink = 0;
	    nFace    = 0;
	    nVertex  = 0;
	    
	    nVertColor  = 0;

	    // ::ZeroMemory( StageArea, sizeof(StageArea) );

	    AreaList        = null;
	    Vertex          = null;
	    Face            = null;
	    TexLink         = null;
	    Light           = null;
	    //smMaterialGroup = null;
	    //StageObject     = null;
	    smMaterial      = null;
	    //lpwAreaBuff     = null;
	    
	    //////////////////////
	    lpOldTexLink    = 0;
	    //////////////////////
	}
	
	/**
	 * 加载舞台数据
	 * @return
	 */
	private Node loadStage() {
		/***********
		 * 读取SMD文件
		 */
		// 初始化smSTAGE3D对象
		initStage();
		
		// 读取smSTAGE3D对象
		readStage3D();
		
		// 读取MaterialGroup
		if (matCounter > 0) {
			loadMaterial();
		}
		
		// 读取Vertex
		readVertex();
		
		// 读取Face
		readFace();
		
		// 读取TEX_LINK(其实就是uv坐标)
		readTexLink();
		
		// 读取灯光
		if ( nLight > 0 ) {
			readLight();
		}
		
		// 重新建立Face与TexLink之间的关联
		relinkFaceAndTex();

		/*************
		 * 生成jme3对象
		 */
		
		return buildStage3D();
	}
	
	/**
	 * 读取文件头
	 */
	private void readHead() {
		header = getString(24);
		objCounter = getInt();
		matCounter = getInt();
		matFilePoint = getInt();
		firstObjInfoPoint = getInt();
		tmFrameCounter = getInt();
		for (int i = 0; i < OBJ_FRAME_SEARCH_MAX; i++) {
			data[i] = new SmdFramePos();
			data[i].startFrame = getInt();
			data[i].endFrame = getInt();
			data[i].posNum = getInt();
			data[i].posCnt = getInt();
		}
		
		assert buffer.position() == 556;
	}
	
	/**
	 * 读取smSTAGE3D对象
	 */
	private void readStage3D() {
		getInt();// Head
		buffer.get(new byte[262144]);//*StageArea[MAP_SIZE][MAP_SIZE]; 4 * 256 * 256 = 262144
		getInt();// *AreaList;
		AreaListCnt = getInt();
		MemMode = getInt();
		SumCount = getInt();
		CalcSumCount = getInt();
		
		getInt();// *Vertex
		getInt();// *Face
		lpOldTexLink = getInt();// *TexLink
		getInt();// *smLight
		getInt();// *smMaterialGroup
		getInt();// *StageObject
		getInt();// *smMaterial
		
		assert buffer.position() == 262752;
		
		nVertex = getInt();
		nFace = getInt();
		nTexLink = getInt();
		nLight = getInt();
		
		log.debug(String.format("V=%d F=%d T=%d L=%d", nVertex, nFace, nTexLink, nLight));
		
		nVertColor = getInt();
		Contrast = getInt();
		Bright = getInt();
		
		vectLight.x = getInt();
		vectLight.y = getInt();
		vectLight.z = getInt();
		
		getInt();// *lpwAreaBuff
		wAreaSize = getInt();
		
		// sizeof(RECT) == 16
		int minX = getInt();
		int minY = getInt();
		int maxX = getInt();
		int maxY = getInt();
		log.info("下列数值是地图的边缘，x,z平面的矩形。矩形的边长被放大了256倍");
		log.info(String.format("min(%d, %d) max(%d, %d)", minX, minY, maxX, maxY));
		
		assert buffer.position() == 262816;
	}
	
	/**
	 * 加载材质数据
	 */
	private void loadMaterial() {
		int size = 0;
		// 读取MaterialGroup对象
		readMaterialGroup();
		size += 88;
		
		smMaterial = new MATERIAL[materialCount];
		
		for(int i=0; i<materialCount; i++) {
			smMaterial[i] = readMaterial();
			size += 320;
			
			if (smMaterial[i].InUse != 0) {
				int strLen = getInt();
				size += 4;
				size += strLen;
				
				smMaterial[i].smTexture = new TEXTUREHANDLE[smMaterial[i].TextureCounter];
				for(int j=0; j<smMaterial[i].TextureCounter; j++) {
					TEXTUREHANDLE texture = new TEXTUREHANDLE();
					smMaterial[i].smTexture[j] = texture;
					texture.Name = getString();
					texture.NameA = getString();
					
					// TODO 将texture.Name存入缓存中，避免多次加载。
					
					if (texture.NameA.length() > 0) {
						log.info("TEX MIPMAP:" + texture.NameA);
					}
				}
				
				smMaterial[i].smAnimTexture = new TEXTUREHANDLE[smMaterial[i].AnimTexCounter];
				for(int j=0; j<smMaterial[i].AnimTexCounter; j++) {
					TEXTUREHANDLE texture = new TEXTUREHANDLE();
					smMaterial[i].smAnimTexture[j] = texture;
					texture.Name = getString();
					texture.NameA = getString();
					
					// TODO 将texture.Name存入缓存中，避免多次加载。

					if (texture.NameA.length() > 0) {
						log.info("Anim MIPMAP:" + texture.NameA);
					}
				}
			}
		}
		
		log.debug("Material Size=" + size);
	}
	
	/**
	 * 读取smMATERIAL_GROUP数据
	 */
	private void readMaterialGroup() {
		getInt();// Head
		getInt();// *smMaterial
		materialCount = getInt();
		reformTexture = getInt();
		maxMaterial = getInt();
		lastSearchMaterial = getInt();
		lastSearchName = getString(64);
		
		assert buffer.position() == 262904;
	}
	
	/**
	 * 读取MATERIAL数据结构
	 */
	private MATERIAL readMaterial() {
		int start = buffer.position();
		
		MATERIAL mat = new MATERIAL();
		
		mat.InUse = getInt(); // > 0 表示在使用
		mat.TextureCounter = getInt();// 纹理的数量。动画纹理必然只有1张。
		for(int i=0; i<8; i++) {
			getInt();// *smTexture[8];
		}
		for(int i=0; i<8; i++) {
			mat.TextureStageState[i] = getInt();
		}
		for(int i=0; i<8; i++) {
			mat.TextureFormState[i] = getInt();
		}
		mat.ReformTexture = getInt();

		/**
		 * 透明
		 */
		mat.MapOpacity = getInt(); // TRUE or FALSE

		/**
		 * 纹理类型
		 * #define SMTEX_TYPE_MULTIMIX		0x0000
		 * #define SMTEX_TYPE_ANIMATION		0x0001
		 */
		mat.TextureType = getInt();
		/**
		 * 混色方式
		 * #define SMMAT_BLEND_NONE			0x00
		 * #define SMMAT_BLEND_ALPHA		0x01
		 * #define SMMAT_BLEND_COLOR		0x02
		 * #define SMMAT_BLEND_SHADOW		0x03
		 * #define SMMAT_BLEND_LAMP			0x04
		 * #define SMMAT_BLEND_ADDCOLOR		0x05
		 * #define SMMAT_BLEND_INVSHADOW	0x06
		 */
		mat.BlendType = getInt(); // SMMAT_BLEND_XXXX

		mat.Shade = getInt(); // TRUE or FALSE
		mat.TwoSide = getInt(); // TRUE or FALSE
		mat.SerialNum = getInt(); // 皋飘府倔俊 措茄 绊蜡 锅龋

		mat.Diffuse = getVector3f(); // Diffuse 祸惑
		mat.Transparency = getFloat(); //
		mat.SelfIllum = getFloat(); //

		mat.TextureSwap = getInt(); //
		mat.MatFrame = getInt(); //
		mat.TextureClip = getInt(); //

		// 皋浆 包访 加己
		mat.UseState = getInt(); // ScriptState
		/**
		 * 是否进行碰撞检测
		 * #define SMMAT_STAT_CHECK_FACE	0x00000001
		 */
		mat.MeshState = getInt();

		// Mesh 函屈 包访 汲沥
		mat.WindMeshBottom = getInt(); // TODO @see smTexture.cpp 脚本的编号

		// 俊聪皋捞记 咆胶媚 加己
		for(int i=0; i<32; i++) {
			getInt();// *smAnimTexture[32]
		}
		mat.AnimTexCounter = getInt(); // 动画有几张图NumTex
		mat.FrameMask = getInt(); // NumTex-1
		mat.Shift_FrameSpeed = getInt(); // 动画切换速度，默认是6
		
		/**
		 * 是否自动播放动画
		 * #define SMTEX_AUTOANIMATION		0x100
		 * 为0时不自动播放
		 */
		mat.AnimationFrame = getInt();
		
		assert (buffer.position() - start) == 320;
		
		return mat;
	}

	/**
	 * STAGE_VERTEX
	 * size = 28
	 */
	private void readVertex() {
		Vertex = new STAGE_VERTEX[nVertex];
		for(int i=0; i<nVertex; i++) {
			STAGE_VERTEX vert = new STAGE_VERTEX();
			Vertex[i] = vert;
			
			vert.sum = getInt();
			getInt();// *lpRendVertex

			// Vectex // 除以256才是实际的值
			vert.x = getInt() / 256f;
			vert.y = getInt() / 256f;
			vert.z = getInt() / 256f;
			
			// VectorColor
			vert.r = getShort() / 256f;
			vert.g = getShort() / 256f;
			vert.b = getShort() / 256f;
			vert.a = getShort() / 256f;
		}
	}

	/**
	 * 读取STAGE_FACE
	 * size = 28
	 */
	private void readFace() {
		Face = new STAGE_FACE[nFace];
		for(int i=0; i<nFace; i++) {
			STAGE_FACE face = new STAGE_FACE();
			Face[i] = face;
			
			face.sum = getInt();
			face.CalcSum = getInt();
			
			face.a = getUnsignedShort();
			face.b = getUnsignedShort();
			face.c = getUnsignedShort();
			face.mat_id = getUnsignedShort();// 材质的索引号
			
			face.lpTexLink = getInt();// 纹理坐标的指针。smTEX_LINK *lpTexLink
			
			face.nx = getShort()/32767f;// nx
			face.ny = getShort()/32767f;// ny
			face.nz = getShort()/32767f;// nz
			face.y = getShort()/32767f;// Y 除以32767后是 1/8PI，不知道有何用。
		}
	}

	/**
	 * 读取TEXLINK
	 * size=32
	 */
	private void readTexLink() {
		TexLink = new TEXLINK[nTexLink];
		for(int i=0; i<nTexLink; i++) {
			TEXLINK tex = new TEXLINK();
			TexLink[i] = tex;
			
			tex.u[0] = getFloat();
			tex.u[1] = getFloat();
			tex.u[2] = getFloat();
			
			tex.v[0] = getFloat();
			tex.v[1] = getFloat();
			tex.v[2] = getFloat();
			
			tex.hTexture = getInt();// *hTexture;
			tex.lpNextTex = getInt();// *NextTex;
		}
	}
	
	/**
	 * 读取smLIGHT3D
	 * size = 28
	 */
	private void readLight() {
		Light = new LIGHT3D[nLight];
		for(int i=0; i<nLight; i++) {
			LIGHT3D light = new LIGHT3D();
			Light[i] = light;
			
			light.type = getInt();
			light.x = getInt() / 256f;
			light.y = getInt() / 256f;
			light.z = getInt() / 256f;
			light.Range = getInt() / 64f / 256f;
			
			light.r = getUnsignedShort() / 255f;
			light.g = getUnsignedShort() / 255f;
			light.b = getUnsignedShort() / 255f;
		}
	}
	
	/**
	 * 重新建立TexLink之间、Face与TexLink之间的关联。
	 * 
	 * TexLink是一个smTEXLINK数组，顺序存储，lpOldTexLink记录了其首地址。
	 * 由于{@code sizeof(smTEXLINK) = 32}，所以：{@code 索引号=(原地址-lpOldTexLink)/32}
	 */
	private void relinkFaceAndTex() {
		// 重新建立TexLink链表中的关联
		for(int i=0; i<nTexLink; i++) {
			if ( TexLink[i].lpNextTex != 0) {
	            int index = (TexLink[i].lpNextTex - lpOldTexLink) / 32;
	            TexLink[i].NextTex = TexLink[index];
	        }
		}
		
		// 重新建立Face与TexLink之间的关联
		for(int i=0; i<nFace; i++) {
	        if ( Face[i].lpTexLink != 0) {
	            int index = (Face[i].lpTexLink - lpOldTexLink) / 32;
	            Face[i].TexLink = TexLink[index];
	        }
	    }
	}
	
	
	
	/*******************************************************
	 * 下面的代码用于根据精灵的数据结构创建JME3的纹理、材质、网格等对象
	 *******************************************************/
	
	/**
	 * 改变文件的后缀名
	 * @param line
	 * @return
	 */
	private String changeName(String line) {
		line = line.replaceAll("\\\\", "/");
		int index = line.lastIndexOf("/");
		if (index != -1) {
			line = line.substring(index + 1);
		}
		
		return line;
	}
	/**
	 * 创建纹理
	 * 
	 * @param name
	 */
	private Texture createTexture(String name) {
		name = changeName(name);
		Texture texture = null;
		try {
			texture = manager.loadTexture(key.getFolder() + name);
			texture.setWrap(WrapMode.Repeat);
		} catch (Exception ex) {
			log.warn("Cannot load texture image " + name, ex);
			texture = manager.loadTexture("Common/Textures/MissingTexture.png");
			texture.setWrap(WrapMode.EdgeClamp);
		}
		return texture;
	}
	
	/**
	 * 创建材质
	 * @param m
	 * @return
	 */
	private Material createLightMaterial(MATERIAL m) {
		Material mat = new Material(manager, "Common/MatDefs/Light/Lighting.j3md");
		mat.setColor("Diffuse", new ColorRGBA(m.Diffuse.x, m.Diffuse.y, m.Diffuse.z, 1));
		//mat.setBoolean("UseMaterialColors", true);
		
		RenderState rs = mat.getAdditionalRenderState();
		
		// TODO 由于有些面被拆件了，这里强行使它们可见。应该根据MATERIAL中的参数来决定。
		rs.setFaceCullMode(FaceCullMode.Off);
		
		if(m.TextureCounter == 0) {
			rs.setFaceCullMode(FaceCullMode.FrontAndBack);
		}
		
		if (m.TwoSide == 1) {
			rs.setFaceCullMode(FaceCullMode.Off);
		}
		
		if (m.MapOpacity != 0) {
			mat.setFloat("AlphaDiscardThreshold", 0.01f);
		}

		// 设置贴图
		if (m.TextureCounter > 0) {
			mat.setTexture("DiffuseMap", createTexture(m.smTexture[0].Name));
		}
		if (m.TextureCounter > 1) {
			mat.setBoolean("SeparateTexCoord", true);
			mat.setTexture("LightMap", createTexture(m.smTexture[1].Name));
		}

		/**
			#define SMMAT_BLEND_NONE		0x00
			#define SMMAT_BLEND_ALPHA		0x01
			#define SMMAT_BLEND_COLOR		0x02
			#define SMMAT_BLEND_SHADOW		0x03
			#define SMMAT_BLEND_LAMP		0x04
			#define SMMAT_BLEND_ADDCOLOR	0x05
			#define SMMAT_BLEND_INVSHADOW	0x06
		 */
		switch (m.BlendType) {
		case 0:// SMMAT_BLEND_NONE
			rs.setBlendMode(BlendMode.Off);
			break;
		case 1:// SMMAT_BLEND_ALPHA
			rs.setBlendMode(BlendMode.Alpha);
			break;
		case 2:// SMMAT_BLEND_COLOR
			rs.setBlendMode(BlendMode.Color);
			break;
		case 3:// SMMAT_BLEND_SHADOW
			break;
		case 4:// SMMAT_BLEND_LAMP
			break;
		case 5:// SMMAT_BLEND_ADDCOLOR
			rs.setBlendMode(BlendMode.Additive);
			break;
		case 6:
			break;
		default:
			log.info("Unknown BlendType=" + m.BlendType);
		};
		
		// TODO 继续读smRender3d.cpp
		if (m.Transparency <= 0.2f) {
			rs.setDepthWrite(true);
		}
		return mat;
	}
	
	/**
	 * 生成STAGE3D对象
	 * @return
	 */
	private Node buildStage3D() {
		Node solidNode = new Node("SMMAT_STAT_CHECK_FACE");// 用来存放需要进行碰撞检测的部分
		Node otherNode = new Node("SMMAT_STAT_NOT_CHECK_FACE");// 用来存放不需要进行碰撞检测的部分
		
		Node rootNode = new Node("STAGE3D:" + key.getName());
		rootNode.attachChild(solidNode);
		rootNode.attachChild(otherNode);
		
		// 创建材质
		for(int mat_id=0; mat_id<materialCount; mat_id++) {
			MATERIAL m = smMaterial[mat_id];
			/**
			 * 判断这个面是否被使用。
			 * 实际上smd文件中存储的材质都是被用到的材质，否则是不会存储的。
			 * 因此这个判断并没有实际意义。
			 */
			if (m.InUse == 0) {
				continue;
			}
			
			/**
			 * 统计材质为mat_id的面一共有多少个面，用于计算需要生成多少个子网格。
			 */
			int size = 0;
			for (int i = 0; i < nFace; i++) {
				if (Face[i].mat_id != mat_id)
					continue;
				size++;
			}
			if (size < 1)
				continue;
			
			// 计算网格
			Mesh mesh = buildStage3DMesh(size, mat_id);
			Geometry geom = new Geometry(key.getName() + "#" + mat_id, mesh);
			
			// 创建材质
			Material mat = createLightMaterial(smMaterial[mat_id]);
			geom.setMaterial(mat);
			
			// 透明度
			if (m.MapOpacity != 0) {
				geom.setQueueBucket(Bucket.Translucent);
			}
			
			if (m.MeshState == 0) {
				otherNode.attachChild(geom);
				log.debug("ID:" + mat_id + " MeshState=" + m.MeshState);// 透明度
			} else {
				solidNode.attachChild(geom);
			}
			
			// 从smTexture.cpp中可知，只有Transparency==0的物体才需要检测碰撞网格。
			if (m.Transparency != 0) {
				otherNode.attachChild(geom);
				log.debug("Transparency=" + m.Transparency);// 透明度
			}
			
			if (m.ReformTexture > 0) {
				log.debug("ReformTexture=" + m.ReformTexture);// 需要被加密的图片数目
			}
			if (m.SelfIllum > 0.0f) {
				log.debug("SelfIllum=" + m.SelfIllum);// 自发光
			}
			if (m.UseState != 0) {//ScriptState
				log.debug("UseState=" + m.UseState);// 有脚本？？
			}
			
			if (m.TextureType == 0) {
				// SMTEX_TYPE_MULTIMIX		0x0000
			} else {
				// SMTEX_TYPE_ANIMATION		0x0001
				
				// 动画也是默认显示2面
				mat.getAdditionalRenderState().setFaceCullMode(FaceCullMode.Off);
				
				// 有多个动画
				if (m.AnimTexCounter > 0) {
					FrameAnimControl control = createFrameAnimControl(smMaterial[mat_id]);
					geom.addControl(control);
				}
			}

		}
		
		return rootNode;
	}

	private Mesh buildStage3DMesh(int size, int mat_id) {
		
		Vector3f[] position = new Vector3f[size * 3];
		int[] f = new int[size * 3];
		Vector2f[] uv1 = new Vector2f[size * 3];
		Vector2f[] uv2 = new Vector2f[size * 3];

		int index = 0;
		// Prepare MeshData
		for (int i = 0; i < nFace; i++) {
			// Check the MaterialIndex
			if (Face[i].mat_id != mat_id)
				continue;

			// 顶点 VERTEX
			position[index * 3 + 0] = new Vector3f(Vertex[Face[i].a].x, Vertex[Face[i].a].y, Vertex[Face[i].a].z);
			position[index * 3 + 1] = new Vector3f(Vertex[Face[i].b].x, Vertex[Face[i].b].y, Vertex[Face[i].b].z);
			position[index * 3 + 2] = new Vector3f(Vertex[Face[i].c].x, Vertex[Face[i].c].y, Vertex[Face[i].c].z);

			// 面 FACE
			if (i < nFace) {
				f[index * 3 + 0] = index * 3 + 0;
				f[index * 3 + 1] = index * 3 + 1;
				f[index * 3 + 2] = index * 3 + 2;
			}

			// 原地图可能有多个贴图，因此使用多个UV坐标
			for(int k=0; k<smMaterial[mat_id].TextureCounter; k++) {
				
			}
			// 纹理映射
			TEXLINK tl = Face[i].TexLink;
			if(tl != null) {
				// 第1组uv坐标
				uv1[index * 3 + 0] = new Vector2f(tl.u[0], 1f - tl.v[0]);
				uv1[index * 3 + 1] = new Vector2f(tl.u[1], 1f - tl.v[1]);
				uv1[index * 3 + 2] = new Vector2f(tl.u[2], 1f - tl.v[2]);
			} else {
				uv1[index * 3 + 0] = new Vector2f();
				uv1[index * 3 + 1] = new Vector2f();
				uv1[index * 3 + 2] = new Vector2f();
			}
			
			// 第2组uv坐标
			if (tl != null && tl.NextTex != null) {
				tl = tl.NextTex;
				
				uv2[index * 3 + 0] = new Vector2f(tl.u[0], 1f - tl.v[0]);
				uv2[index * 3 + 1] = new Vector2f(tl.u[1], 1f - tl.v[1]);
				uv2[index * 3 + 2] = new Vector2f(tl.u[2], 1f - tl.v[2]);
			} else {
				uv2[index * 3 + 0] = new Vector2f();
				uv2[index * 3 + 1] = new Vector2f();
				uv2[index * 3 + 2] = new Vector2f();
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

		mesh.setStatic();
		mesh.updateBound();
		mesh.updateCounts();
		
		return mesh;
	}
	
	/**
	 * AminTexCounter大于0说明有轮播动画，创建一个Control，定时更新画面。
	 * @param m
	 * @return
	 */
	private FrameAnimControl createFrameAnimControl(MATERIAL m) {
		FrameAnimControl control = new FrameAnimControl(m.AnimTexCounter);
		
		for(int i=0; i<m.AnimTexCounter; i++) {
			Texture tex = createTexture(m.smAnimTexture[i].Name);
			control.animTexture.add(tex);
		}
		return control;
	}
}
