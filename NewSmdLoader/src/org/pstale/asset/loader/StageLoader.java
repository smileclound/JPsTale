package org.pstale.asset.loader;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.pstale.asset.loader.SmdKey.SMDTYPE;

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

	private FILE_HEADER smd_file_header;
	private PAT3D smPhysique = null;
	
	/**
	 * size = 64
	 */
	class FMATRIX {
		float _11, _12, _13, _14;
		float _21, _22, _23, _24;
		float _31, _32, _33, _34;
		float _41, _42, _43, _44;
		
		FMATRIX() {
			_11 = 1; _12 = 0; _13 = 0; _14 = 0;
			_21 = 0; _22 = 1; _23 = 0; _24 = 0;
			_31 = 0; _32 = 0; _33 = 1; _34 = 0;
			_41 = 0; _42 = 0; _43 = 0; _44 = 1;
		}
		FMATRIX(boolean init) {
			_11 = getFloat(); _12 = getFloat(); _13 = getFloat(); _14 = getFloat();
			_21 = getFloat(); _22 = getFloat(); _23 = getFloat(); _24 = getFloat();
			_31 = getFloat(); _32 = getFloat(); _33 = getFloat(); _34 = getFloat();
			_41 = getFloat(); _42 = getFloat(); _43 = getFloat(); _44 = getFloat();
		}
	}
	
	/**
	 * size = 64
	 */
	class MATRIX {
		int _11, _12, _13, _14;
		int _21, _22, _23, _24;
		int _31, _32, _33, _34;
		int _41, _42, _43, _44;
		
		MATRIX() {
			_11 = 1; _12 = 0; _13 = 0; _14 = 0;
			_21 = 0; _22 = 1; _23 = 0; _24 = 0;
			_31 = 0; _32 = 0; _33 = 1; _34 = 0;
			_41 = 0; _42 = 0; _43 = 0; _44 = 1;
		}
		MATRIX(boolean init) {
			_11 = getInt(); _12 = getInt(); _13 = getInt(); _14 = getInt();
			_21 = getInt(); _22 = getInt(); _23 = getInt(); _24 = getInt();
			_31 = getInt(); _32 = getInt(); _33 = getInt(); _34 = getInt();
			_41 = getInt(); _42 = getInt(); _43 = getInt(); _44 = getInt();
		}
	}
	
	/**
	 * size = 20
	 *
	 */
	class TM_ROT {
		int frame;
		float x, y, z, w;
		
		TM_ROT() {
			frame = getInt();
			x = getFloat();
			y = getFloat();
			z = getFloat();
			w = getFloat();
		}
	}

	/**
	 * size = 16
	 */
	class TM_POS {
		int frame;
		float x, y, z;
		TM_POS() {
			frame = getInt();
			x = getFloat();
			y = getFloat();
			z = getFloat();
		}
	}
	
	/**
	 * size = 16
	 */
	class TM_SCALE {
		int frame;
		int x, y, z;
		TM_SCALE() {
			frame = getInt();
			x = getInt();
			y = getInt();
			z = getInt();
		}
	}
	
	/**
	 * size = 16
	 */
	class FRAME_POS {
		int startFrame;
		int endFrame;
		int posNum;
		int posCnt;
		
		FRAME_POS() {
			startFrame = getInt();
			endFrame = getInt();
			posNum = getInt();
			posCnt = getInt();
		}
	}
	
	/**
	 * SMD文件头
	 * size = 556;
	 */
	class FILE_HEADER{
		String header;// 24字节
		int objCounter;
		int matCounter;
		int matFilePoint;
		int firstObjInfoPoint;
		int tmFrameCounter;
		FRAME_POS[] TmFrame = new FRAME_POS[OBJ_FRAME_SEARCH_MAX];// 512字节
		
		/**
		 * 读取文件头
		 */
		FILE_HEADER() {
			header = getString(24);
			objCounter = getInt();
			matCounter = getInt();
			matFilePoint = getInt();
			firstObjInfoPoint = getInt();
			tmFrameCounter = getInt();
			for (int i = 0; i < OBJ_FRAME_SEARCH_MAX; i++) {
				TmFrame[i] = new FRAME_POS();
			}
			
			assert buffer.position() == 556;
			
			log.debug(header);
		}
	}
	
	/**
	 * size = 40
	 */
	class FILE_OBJINFO {
		String NodeName;// 32字节
		int Length;
		int ObjFilePoint;
		
		FILE_OBJINFO() {
			NodeName = getString(32);
			Length = getInt();
			ObjFilePoint = getInt();
		}
	}
	
	
	/**
	 * 若文件头中的mat>0，说明有材质。
	 * 接下来第三部分应该是一个完整的smMATERIAL_GROUP对象。size = 88。
	 */
	class MATERIAL_GROUP {
		// DWORD Head
		MATERIAL[] materials;
		int materialCount;
		int reformTexture;
		int maxMaterial;
		int lastSearchMaterial;
		String lastSearchName;
		
		////////////////
		// 计算读取结束后整个MaterialGroup占用了多少内存，没有实际意义。
		int size = 0;
		////////////////
		/**
		 * 读取smMATERIAL_GROUP数据
		 */
		MATERIAL_GROUP() {
			getInt();// Head
			getInt();// *smMaterial
			materialCount = getInt();
			reformTexture = getInt();
			maxMaterial = getInt();
			lastSearchMaterial = getInt();
			lastSearchName = getString(64);
			
			size += 88;
			
			assert buffer.position() == 262904;
		}
		
		/**
		 * 载入所有材质
		 */
		void loadFile() {
			materials = new MATERIAL[materialCount];
			
			for(int i=0; i<materialCount; i++) {
				materials[i] = new MATERIAL();
				size += 320;
				
				if (materials[i].InUse != 0) {
					int strLen = getInt();
					size += 4;
					size += strLen;
					
					materials[i].smTexture = new TEXTUREHANDLE[materials[i].TextureCounter];
					for(int j=0; j<materials[i].TextureCounter; j++) {
						TEXTUREHANDLE texture = new TEXTUREHANDLE();
						materials[i].smTexture[j] = texture;
						texture.Name = getString();
						texture.NameA = getString();
						
						// TODO 将texture.Name存入缓存中，避免多次加载。
						
						if (texture.NameA.length() > 0) {
							log.info("TEX MIPMAP:" + texture.NameA);
						}
					}
					
					materials[i].smAnimTexture = new TEXTUREHANDLE[materials[i].AnimTexCounter];
					for(int j=0; j<materials[i].AnimTexCounter; j++) {
						TEXTUREHANDLE texture = new TEXTUREHANDLE();
						materials[i].smAnimTexture[j] = texture;
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
	}
	
	/**
	 * 材质
	 * size = 320
	 * @author yanmaoyuan
	 *
	 */
	class MATERIAL {
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
		
		/**
		 * 读取MATERIAL数据结构
		 */
		MATERIAL() {
			int start = buffer.position();
			
			InUse = getInt(); // > 0 表示在使用
			TextureCounter = getInt();// 纹理的数量。动画纹理必然只有1张。
			for(int i=0; i<8; i++) {
				getInt();// *smTexture[8];
			}
			for(int i=0; i<8; i++) {
				TextureStageState[i] = getInt();
			}
			for(int i=0; i<8; i++) {
				TextureFormState[i] = getInt();
			}
			ReformTexture = getInt();

			/**
			 * 透明
			 */
			MapOpacity = getInt(); // TRUE or FALSE

			/**
			 * 纹理类型
			 * #define SMTEX_TYPE_MULTIMIX		0x0000
			 * #define SMTEX_TYPE_ANIMATION		0x0001
			 */
			TextureType = getInt();
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
			BlendType = getInt(); // SMMAT_BLEND_XXXX

			Shade = getInt(); // TRUE or FALSE
			TwoSide = getInt(); // TRUE or FALSE
			SerialNum = getInt(); // 皋飘府倔俊 措茄 绊蜡 锅龋

			Diffuse = getVector3f(); // Diffuse 祸惑
			Transparency = getFloat(); //
			SelfIllum = getFloat(); //

			TextureSwap = getInt(); //
			MatFrame = getInt(); //
			TextureClip = getInt(); //

			// 皋浆 包访 加己
			UseState = getInt(); // ScriptState
			/**
			 * 是否进行碰撞检测
			 * #define SMMAT_STAT_CHECK_FACE	0x00000001
			 */
			MeshState = getInt();

			// Mesh 函屈 包访 汲沥
			WindMeshBottom = getInt(); // TODO @see smTexture.cpp 脚本的编号

			// 俊聪皋捞记 咆胶媚 加己
			for(int i=0; i<32; i++) {
				getInt();// *smAnimTexture[32]
			}
			AnimTexCounter = getInt(); // 动画有几张图NumTex
			FrameMask = getInt(); // NumTex-1
			Shift_FrameSpeed = getInt(); // 动画切换速度，默认是6
			
			/**
			 * 是否自动播放动画
			 * #define SMTEX_AUTOANIMATION		0x100
			 * 为0时不自动播放
			 */
			AnimationFrame = getInt();
			
			assert (buffer.position() - start) == 320;
		}
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
	
	// size = 8
	class FTPOINT {
	    float u,v;
	    FTPOINT() {
	    	u = getFloat();
	    	v = getFloat();
	    }
	}
	
	// size = 12
	class POINT3D {
		int x, y ,z;
		
		POINT3D() {
			x = y = z = 0;
		}
		POINT3D(boolean init) {
			x = getInt();
			y = getInt();
			z = getInt();
		}
	}
	
	/**
	 * size = 24
	 *
	 */
	class VERTEX {
		float x,y,z;// 坐标
		float nx,ny,nz;// normals 法向量
		
		VERTEX() {
			x = getInt() / 256f;
			y = getInt() / 256f;
			z = getInt() / 256f;
			nx = getInt() / 256f;
			ny = getInt() / 256f;
			nz = getInt() / 256f;
		}
	}
	
	/**
	 * size = 36
	 */
	class FACE {
		int[] v= new int[4];// a,b,c,Matrial
	    FTPOINT[] t = new FTPOINT[3];
	    int lpTexLink;
	    TEXLINK TexLink;
	    
	    FACE() {
	    	for(int i=0; i<4; i++) {
	    		v[i] = getUnsignedShort();
	    	}
	    	
	    	for(int i=0; i<3; i++) {
	    		t[i] = new FTPOINT();
	    	}
	    	
	    	lpTexLink = getInt();
	    }
	}
	/**
	 * size = 28
	 */
	class STAGE_VERTEX {
	    int sum;
	    //smRENDVERTEX *lpRendVertex;
	    float x,y,z;
	    float r, g, b, a;// 除以256才能用作ColorRGBA
	    
	    STAGE_VERTEX() {
	    	sum = getInt();
			getInt();// *lpRendVertex

			// Vectex // 除以256才是实际的值
			x = getInt() / 256f;
			y = getInt() / 256f;
			z = getInt() / 256f;
			
			// VectorColor
			r = getShort() / 256f;
			g = getShort() / 256f;
			b = getShort() / 256f;
			a = getShort() / 256f;
	    }
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
	    
	    STAGE_FACE() {
	    	sum = getInt();
			CalcSum = getInt();
			
			a = getUnsignedShort();
			b = getUnsignedShort();
			c = getUnsignedShort();
			mat_id = getUnsignedShort();// 材质的索引号
			
			lpTexLink = getInt();// 纹理坐标的指针。smTEX_LINK *lpTexLink
			
			nx = getShort()/32767f;// nx
			ny = getShort()/32767f;// ny
			nz = getShort()/32767f;// nz
			y = getShort()/32767f;// Y 除以32767后是 1/8PI，不知道有何用。
	    }
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
		
		TEXLINK() {
			u[0] = getFloat();
			u[1] = getFloat();
			u[2] = getFloat();
			
			v[0] = getFloat();
			v[1] = getFloat();
			v[2] = getFloat();
			
			hTexture = getInt();// *hTexture;
			lpNextTex = getInt();// *NextTex;
		}
	}
	
	/**
	 * size = 22
	 */
	class LIGHT3D {
	    int type;
	    float x,y,z;
	    float Range;
	    float r,g,b;
	    
	    LIGHT3D() {
	    	type = getInt();
			x = getInt() / 256f;
			y = getInt() / 256f;
			z = getInt() / 256f;
			Range = getInt() / 64f / 256f;
			
			r = getUnsignedShort() / 255f;
			g = getUnsignedShort() / 255f;
			b = getUnsignedShort() / 255f;
	    }
	}
	
	/**
	 * Stage3D对象的属性
	 * 文件数据的第二段，存储了一个完整的smSTAGE3D对象。 size = 262260
	 * 其中的关键数据是nVertex/nFace/nTexLink/nLight这些。
	 */
	class STAGE3D {
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
		MATERIAL_GROUP    materialGroup;// sizeof(smMaterialGroup) = 88
		// smSTAGE_OBJECT      *StageObject;
		MATERIAL[]          materials;
		
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
		 * 初始化Stage3D对象
		 */
		protected STAGE3D() {
		    // Head = FALSE;
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
			
			nVertex = getInt();
			nFace = getInt();
			nTexLink = getInt();
			nLight = getInt();
			
			nVertColor = getInt();
			Contrast = getInt();
			Bright = getInt();
			
			// 灯光的方向
			vectLight = new Vector3f();
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
		 * 加载舞台数据
		 * @return
		 */
		void loadFile() {
			// 读取MaterialGroup
			if (smd_file_header.matCounter > 0) {
				// 读取MaterialGroup对象
				materialGroup = new MATERIAL_GROUP();
				materialGroup.loadFile();
				materials = materialGroup.materials;
			}
			
			// 读取Vertex
			Vertex = new STAGE_VERTEX[nVertex];
			for(int i=0; i<nVertex; i++) {
				Vertex[i] = new STAGE_VERTEX();
			}
			
			// 读取Face
			Face = new STAGE_FACE[nFace];
			for(int i=0; i<nFace; i++) {
				Face[i] = new STAGE_FACE();
			}
			
			// 读取TEX_LINK(其实就是uv坐标)
			TexLink = new TEXLINK[nTexLink];
			for(int i=0; i<nTexLink; i++) {
				TexLink[i] = new TEXLINK();
			}
			
			// 读取灯光
			if ( nLight > 0 ) {
				Light = new LIGHT3D[nLight];
				for(int i=0; i<nLight; i++) {
					Light[i] = new LIGHT3D();
				}
			}
			
			// 重新建立Face与TexLink之间的关联
			relinkFaceAndTex();
		}
		
		/**
		 * 重新建立TexLink之间、Face与TexLink之间的关联。
		 * 
		 * TexLink是一个smTEXLINK数组，顺序存储，lpOldTexLink记录了其首地址。
		 * 由于{@code sizeof(smTEXLINK) = 32}，所以：{@code 索引号=(原地址-lpOldTexLink)/32}
		 */
		void relinkFaceAndTex() {
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
		
		/**
		 * 生成STAGE3D对象
		 * @return
		 */
		Node buildStage3D() {
			Node solidNode = new Node("SMMAT_STAT_CHECK_FACE");// 用来存放需要进行碰撞检测的部分
			Node otherNode = new Node("SMMAT_STAT_NOT_CHECK_FACE");// 用来存放不需要进行碰撞检测的部分
			
			Node rootNode = new Node("STAGE3D:" + key.getName());
			rootNode.attachChild(solidNode);
			rootNode.attachChild(otherNode);
			
			int materialCount = materialGroup.materialCount;
			
			// 创建材质
			for(int mat_id=0; mat_id<materialCount; mat_id++) {
				MATERIAL m = materials[mat_id];
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
				Material mat = createLightMaterial(materials[mat_id]);
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
						FrameAnimControl control = createFrameAnimControl(materials[mat_id]);
						geom.addControl(control);
					}
				}

			}
			
			return rootNode;
		}

		Mesh buildStage3DMesh(int size, int mat_id) {
			
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
				for(int k=0; k<materials[mat_id].TextureCounter; k++) {
					
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
	}

	class SMotionStEndInfo
	{
		int	StartFrame;
		int	EndFrame;
	}
	/**
	 * size = 2236
	 */
	class OBJ3D {
		//DWORD		Head;
		VERTEX[] Vertex;				// 滚咆胶
		FACE[] Face;					// 其捞胶
		TEXLINK[] TexLink;				//咆胶媚 谅钎 府胶飘

		OBJ3D[] Physique; // 各顶点的骨骼

		VERTEX	ZeroVertex;				// 坷宏璃飘 吝居 滚咆胶 蔼

		int maxZ,minZ;
		int maxY,minY;
		int maxX,minX;

		int dBound;							// 官款爹 胶其绢 蔼 ^2
		int Bound;							// 官款爹 胶其绢 蔼

		int MaxVertex;
		int MaxFace;

		int nVertex;
		int nFace;

		int nTexLink;

		int ColorEffect;					// 祸惑瓤苞 荤侩 蜡公
		int ClipStates;					// 努府俏 付胶农 ( 阿 努府俏喊 荤侩 蜡公 ) 

		POINT3D Posi;
		POINT3D CameraPosi;
		POINT3D Angle;
		int[]	Trig = new int[8];

		// 局聪皋捞记 包访
		String NodeName;//[32];		// 坷宏璃飘狼 畴靛 捞抚
		String NodeParent;//[32];		// 何葛 坷宏璃飘狼 捞抚
		OBJ3D pParent;			// 何葛 坷宏璃飘 器牢磐

		MATRIX	Tm;				// 扁夯 TM 青纺
		MATRIX	TmInvert;		// Tm 狼 开青纺 
		FMATRIX	TmResult;		// 局聪皋捞记 青纺
		MATRIX	TmRotate;		// 扁夯利 雀傈 青纺 

		MATRIX	mWorld;			// 岿靛谅钎 函券 青纺
		MATRIX	mLocal;			// 肺漠谅钎 函券 青纺

		int		lFrame;				// 弥饶 橇饭烙

		float	qx,qy,qz,qw;		// 雀傈 孽磐聪攫
		int		sx,sy,sz;			// 胶纳老 谅钎
		int		px,py,pz;			// 器瘤记 谅钎

		TM_ROT[] TmRot;			// 橇饭烙喊 雀傈 局聪皋捞记
		TM_POS[] TmPos;			// 橇饭烙喊 器瘤记 局聪皋捞记
		TM_SCALE[] TmScale;		// 橇饭烙喊 胶纳老 局聪皋捞记

		FMATRIX[] TmPrevRot; // 帧的动画矩阵

		int TmRotCnt;
		int TmPosCnt;
		int TmScaleCnt;

		//TM 橇饭烙 辑摹 ( 橇饭烙捞 腹栏搁 茫扁啊 塞惦 )
		FRAME_POS[] TmRotFrame = new FRAME_POS[OBJ_FRAME_SEARCH_MAX];
		FRAME_POS[] TmPosFrame = new FRAME_POS[OBJ_FRAME_SEARCH_MAX];
		FRAME_POS[] TmScaleFrame = new FRAME_POS[OBJ_FRAME_SEARCH_MAX];
		int TmFrameCnt;									//TM橇饭烙 墨款磐 (傈眉肮荐)

		////////////////////
		int lpPhysuque;
		int lpOldTexLink;
		////////////////////
		
		OBJ3D() {
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
			Face=null;
			Vertex=null;
			TexLink=null;
			Physique = null;
		}
		
		void readOBJ3D() {
			int start = buffer.position();
			
			getInt();// Head
			getInt();// smVERTEX	*Vertex;				// 滚咆胶
			getInt();// smFACE		*Face;					// 其捞胶
			lpOldTexLink = getInt();// smTEXLINK	*TexLink;				//咆胶媚 谅钎 府胶飘
			lpPhysuque = getInt();// smOBJ3D		**Physique;				// 阿 滚咆胶狼 乔瘤农 坷宏璃飘
			
			ZeroVertex = new VERTEX();
			
			maxZ = getInt();minZ = getInt();
			maxY = getInt();minY = getInt();
			maxX = getInt();minX = getInt();

			dBound = getInt();
			Bound = getInt();

			MaxVertex = getInt();
			MaxFace = getInt();

			nVertex = getInt();
			nFace = getInt();

			nTexLink = getInt();

			ColorEffect = getInt();
			ClipStates = getInt();

			Posi = new POINT3D(true);
			CameraPosi = new POINT3D(true);
			Angle = new POINT3D(true);
			Trig = new int[8];
			for(int i=0; i<8; i++) {
				Trig[i] = getInt();
			}

			// 局聪皋捞记 包访
			NodeName = getString(32);
			NodeParent = getString(32);
			getInt();// OBJ3D *pParent;

			Tm = new MATRIX(true);
			TmInvert = new MATRIX(true);
			TmResult = new FMATRIX(true);
			TmRotate = new MATRIX(true);

			mWorld = new MATRIX(true);
			mLocal = new MATRIX(true);

			lFrame = getInt();

			qx = getFloat();qy = getFloat();qz = getFloat();qw = getFloat();
			sx = getInt();sy = getInt();sz = getInt();
			px = getInt();py = getInt();pz = getInt();
			
			getInt();// smTM_ROT	*TmRot;
			getInt();// smTM_POS	*TmPos;
			getInt();// smTM_SCALE	*TmScale;
			getInt();// smFMATRIX	*TmPrevRot;
			
			TmRotCnt = getInt();
			TmPosCnt = getInt();
			TmScaleCnt = getInt();
			
			for(int i=0; i<OBJ_FRAME_SEARCH_MAX; i++) {
				TmRotFrame[i] = new FRAME_POS();
			}
			for(int i=0; i<OBJ_FRAME_SEARCH_MAX; i++) {
				TmPosFrame[i] = new FRAME_POS();
			}
			for(int i=0; i<OBJ_FRAME_SEARCH_MAX; i++) {
				TmScaleFrame[i] = new FRAME_POS();
			}
			TmFrameCnt = getInt();
			
			assert buffer.position() - start == 2236;
		}
		
		void loadFile(PAT3D PatPhysique) {
			readOBJ3D();
			
			Vertex = new VERTEX[ nVertex ];
			for(int i=0; i<nVertex; i++) {
				Vertex[i] = new VERTEX();
			}

			Face = new FACE[ nFace ];
			for(int i=0; i<nFace; i++) {
				Face[i] = new FACE();
			}

			TexLink = new TEXLINK[ nTexLink ];
			for(int i=0; i<nTexLink; i++) {
				TexLink[i] = new TEXLINK();
			}

			TmRot = new TM_ROT[ TmRotCnt ];
			for(int i=0; i<TmRotCnt; i++) {
				TmRot[i] = new TM_ROT();
			}

			TmPos = new TM_POS[ TmPosCnt ];
			for(int i=0; i<TmPosCnt; i++) {
				TmPos[i] = new TM_POS();
			}

			TmScale = new TM_SCALE[TmScaleCnt];
			for(int i=0; i<TmScaleCnt; i++) {
				TmScale[i] = new TM_SCALE();
			}

			TmPrevRot	= new FMATRIX[ TmRotCnt ];	
			for(int i=0; i<TmRotCnt; i++) {
				TmPrevRot[i] = new FMATRIX(true);
			}
			
			relinkFaceAndTex();
			
			if ( lpPhysuque != 0 && PatPhysique != null ) {
				Physique = new OBJ3D [ nVertex ];
				
				String[] names = new String[nVertex];
				for(int i=0; i<nVertex; i++) {
					names[i] = getString(32);
				}

				for(int i=0; i<nVertex ; i++ ) {
					Physique[i] = PatPhysique.getObjectFromName( names[i] );
				}

			}
			
		}
		
		void relinkFaceAndTex() {
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

		Geometry buildOBJ3D() {
				
			// 计算网格
			Mesh mesh = buildOBJ3DMesh();
			Geometry geom = new Geometry(NodeName, mesh);
			
			log.debug("NodeName:" + NodeName);
			return geom;
		}

		Mesh buildOBJ3DMesh() {
			
			Vector3f[] position = new Vector3f[nFace * 3];
			int[] f = new int[nFace * 3];
			Vector2f[] uv1 = new Vector2f[nFace * 3];

			int index = 0;
			// Prepare MeshData
			for (int i = 0; i < nFace; i++) {

				// 顶点 VERTEX
				position[index * 3 + 0] = new Vector3f(Vertex[Face[i].v[0]].x, Vertex[Face[i].v[0]].y, Vertex[Face[i].v[0]].z);
				position[index * 3 + 1] = new Vector3f(Vertex[Face[i].v[1]].x, Vertex[Face[i].v[1]].y, Vertex[Face[i].v[1]].z);
				position[index * 3 + 2] = new Vector3f(Vertex[Face[i].v[2]].x, Vertex[Face[i].v[2]].y, Vertex[Face[i].v[2]].z);

				// 面 FACE
				if (i < nFace) {
					f[index * 3 + 0] = index * 3 + 0;
					f[index * 3 + 1] = index * 3 + 1;
					f[index * 3 + 2] = index * 3 + 2;
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
				
				index++;
			}

			Mesh mesh = new Mesh();
			mesh.setBuffer(Type.Position, 3, BufferUtils.createFloatBuffer(position));
			mesh.setBuffer(Type.Index, 3, f);
			// DiffuseMap UV
			mesh.setBuffer(Type.TexCoord, 2, BufferUtils.createFloatBuffer(uv1));

			mesh.setStatic();
			mesh.updateBound();
			mesh.updateCounts();
			
			return mesh;
		}
	}
	
	/**
	 * size = 1228
	 */
	class PAT3D {
		//DWORD	Head;
		OBJ3D[] obj3d = new OBJ3D[128];
		byte[] TmSort = new byte[128];

		PAT3D TmParent;

		MATERIAL_GROUP smMaterialGroup;		//皋飘府倔 弊缝

		int MaxFrame;
		int Frame;

		int SizeWidth , SizeHeight;					// 臭捞 承捞 狼 弥措摹 

		int nObj3d;
		//LPDIRECT3DTEXTURE2 *hD3DTexture;

		POINT3D Posi;
		POINT3D Angle;
		POINT3D CameraPosi;

		int dBound;
		int Bound;

		FRAME_POS[] TmFrame = new FRAME_POS[OBJ_FRAME_SEARCH_MAX];
		int TmFrameCnt;

		int TmLastFrame;
		POINT3D TmLastAngle;
	
		PAT3D() {
			
		}

		PAT3D(boolean init) {
			int start = buffer.position();
			
			getInt();// Head
			for(int i=0; i<128; i++) {
				getInt();
			}
			buffer.get(TmSort);
			
			getInt();//smPAT3D		*TmParent;

			getInt();//smMATERIAL_GROUP	*smMaterialGroup;		//皋飘府倔 弊缝

			MaxFrame = getInt();
			Frame = getInt();

			SizeWidth = getInt(); SizeHeight = getInt();

			nObj3d = getInt();
			getInt();//LPDIRECT3DTEXTURE2 *hD3DTexture;

			Posi = new POINT3D(true);
			Angle = new POINT3D(true);
			CameraPosi = new POINT3D(true);

			dBound = getInt();
			Bound = getInt();

			for(int i=0; i<OBJ_FRAME_SEARCH_MAX; i++) {
				TmFrame[i] = new FRAME_POS();
			}
			TmFrameCnt = getInt();

			TmLastFrame = getInt();
			TmLastAngle = new POINT3D(true);
		
			assert buffer.position() - start == 1228;
		}
		
		void init() {
			nObj3d = 0;
			//hD3DTexture = 0;
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

			for( int i=0;i<128;i++) {
				TmSort[i]=(byte)i;
			}

			smMaterialGroup = null;
		}
		void loadFile(String NodeName) {
			OBJ3D obj;
			PAT3D BipPat;
			FILE_HEADER	FileHeader = smd_file_header;

			init();
			
			BipPat = smPhysique;
			
			FILE_OBJINFO[] FileObjInfo = new FILE_OBJINFO [ FileHeader.objCounter ];
			for(int i=0; i<FileHeader.objCounter; i++) {
				FileObjInfo[i] = new FILE_OBJINFO();
			}
			
			TmFrameCnt = FileHeader.tmFrameCounter;
			for(int i=0; i<32; i++) {
				TmFrame[i] = FileHeader.TmFrame[i];
			}
			
			if ( FileHeader.matCounter > 0) {
				smMaterialGroup = new MATERIAL_GROUP();
				smMaterialGroup.loadFile();
			}
			
			if ( NodeName != null ) {
				for(int i=0;i<FileHeader.objCounter;i++) {
					if ( NodeName.equals( FileObjInfo[i].NodeName ) ) {
						obj = new OBJ3D();
						if ( obj != null) {
							buffer.position(FileObjInfo[i].ObjFilePoint);
							obj.loadFile( BipPat );
							addObject( obj );
						}
						break;
					}
				}
			}
			else {
				for(int i=0;i<FileHeader.objCounter;i++) {
					obj = new OBJ3D();
					if ( obj != null ) {
						obj.loadFile( BipPat );
						addObject( obj );
					}
				}
				linkObject();
			}

			TmParent = BipPat;
		}
		
		boolean addObject(OBJ3D obj ) {
			int frame;

			if ( nObj3d< 128 ) {
				obj3d[ nObj3d ] = obj;
				nObj3d ++;

				frame = 0;

				if ( obj.TmRotCnt>0 && obj.TmRot != null) 
					frame = obj.TmRot[ obj.TmRotCnt-1 ].frame;
				if ( obj.TmPosCnt>0 && obj.TmPos != null ) 
					frame = obj.TmPos[ obj.TmPosCnt-1 ].frame;
				if ( MaxFrame<frame ) 
					MaxFrame = frame;

				//农扁 承捞 汲沥
				if ( SizeWidth < obj.maxX ) SizeWidth = obj.maxX;
				if ( SizeWidth < obj.maxZ ) SizeWidth = obj.maxZ;
				if ( SizeHeight < obj.maxY ) SizeHeight = obj.maxY;

				//官款爹 胶其绢 蔼
				if ( Bound<obj.Bound ) {
					Bound = obj.Bound;
					dBound = obj.dBound;
				}


				return true;
			}

			return false;
		}
		
		void linkObject() {
			// 畴靛 匙烙栏肺 何葛 磊侥 楷搬
			for(int i=0; i<nObj3d ; i++ ) {
				if ( obj3d[i].NodeParent != null) {
					for(int k=0; k<nObj3d; k++ ) {
						if (  obj3d[i].NodeParent.equals( obj3d[k].NodeName )) {
							obj3d[i].pParent = obj3d[k];
							break;
						}
					}
				} else {
					log.debug("j = 0");
				}
			}

			int NodeCnt =0;

			for(int i=0;i<128;i++) {
				TmSort[i]=0;
			}

			// ROOT(何葛啊 绝绰) 坷宏璃飘  
			for(int i=0;i<nObj3d; i++ ) {
				if ( obj3d[i].pParent==null ) 
					TmSort[NodeCnt++] = (byte)i;
			}

			// 何葛俊 崔妨乐绰 磊侥阑 茫酒 鉴辑措肺 历厘
			for(int j=0;j<nObj3d; j++ ) {
				for(int i=0; i<nObj3d; i++ ) {
					if ( obj3d[i].pParent!=null && obj3d[TmSort[j]]==obj3d[i].pParent ) {
						TmSort[NodeCnt++] = (byte)i;
					}
				}
			}  
		}
		
		OBJ3D getObjectFromName(String name) {
			for(int i=0; i<nObj3d; i++) {
				if(obj3d[i].NodeName.equals(name)) {
					return obj3d[i];
				}
			}
			return null;
		}
		
		
		Node buildPAT3D() {
			Node rootNode = new Node("STAGEOBJ:" + key.getName());
			
			for(int i=0; i<nObj3d; i++) {
				if (obj3d[i].nFace > 0) {
					Geometry geom = obj3d[i].buildOBJ3D();
					// 创建材质
					int mat_id = obj3d[i].Face[i].v[3];
					MATERIAL m = smMaterialGroup.materials[mat_id];
					Material mat = createLightMaterial(m);
					geom.setMaterial(mat);
					rootNode.attachChild(geom);
				}
			}
			
			return rootNode;
		}
		
		
	}
	
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
		smd_file_header = new FILE_HEADER();
		
		/**
		 * 若用户使用了SmdKey，就根据type来决定采用哪种方式来加载模型。
		 */
		if (key instanceof SmdKey) {
			SmdKey smdkey = (SmdKey) key;
			SMDTYPE type = smdkey.type;
			switch (type) {
			case STAGE3D:
				STAGE3D stage3D = new STAGE3D();
				stage3D.loadFile();
				return stage3D.buildStage3D();
			case STAGE_OBJ:
				PAT3D pat = new PAT3D();
				pat.loadFile(null);
				return pat.buildPAT3D();
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
			if ("SMD Stage data Ver 0.72".equals(smd_file_header.header)) {// 地图
				STAGE3D stage3D = new STAGE3D();
				stage3D.loadFile();
				return stage3D.buildStage3D();
			} else if ("SMD Model data Ver 0.62".equals(smd_file_header.header)){// 模型
				
				return null;
			} else {
				
				return null;
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
