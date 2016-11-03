package org.pstale.asset.loader;

import java.io.IOException;
import java.nio.FloatBuffer;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.pstale.asset.loader.SmdKey.SMDTYPE;

import com.jme3.animation.AnimControl;
import com.jme3.animation.Bone;
import com.jme3.animation.Skeleton;
import com.jme3.animation.SkeletonControl;
import com.jme3.asset.AssetInfo;
import com.jme3.asset.AssetKey;
import com.jme3.asset.AssetLoader;
import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.material.RenderState;
import com.jme3.material.RenderState.BlendMode;
import com.jme3.material.RenderState.FaceCullMode;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.renderer.queue.RenderQueue.Bucket;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.Node;
import com.jme3.scene.VertexBuffer.Type;
import com.jme3.scene.control.AbstractControl;
import com.jme3.scene.shape.Sphere;
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
	/**
	 * 骨骼
	 */
	private PAT3D BipPattern = null;
	
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
		
		/**
		 * 这个矩阵的特征值是256，所有元素除以256后的行列式是1。
		 * @param init
		 */
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
		Quaternion quad;
		TM_ROT() {
			frame = getInt();
			
			float x, y, z, w;
			x = getFloat();
			y = getFloat();
			z = getFloat();
			w = getFloat();
			quad = new Quaternion(-x, -y, -z, w);
		}
	}

	/**
	 * size = 16
	 */
	class TM_POS {
		int frame;
		Vector3f vec3;
		TM_POS() {
			frame = getInt();
			float x, y, z;
			x = getFloat();
			y = getFloat();
			z = getFloat();
			
			vec3 = new Vector3f(x, y, z);
		}
	}
	
	/**
	 * size = 16
	 */
	class TM_SCALE {
		int frame;
		Vector3f scale;
		TM_SCALE() {
			frame = getInt();
			float x, y, z;
			x = getPTDouble();
			y = getPTDouble();
			z = getPTDouble();
			scale = new Vector3f(x, y, z);
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
		/**
		 * 物体的名称
		 */
		String NodeName;// 32字节
		/**
		 * 这个Obj3D区块在文件中所占的字节数。
		 */
		int Length;
		/**
		 * 这个Obj3D区块在文件中的其实位置。
		 */
		int ObjFilePoint;
		
		FILE_OBJINFO() {
			NodeName = getString(32);
			Length = getInt();
			ObjFilePoint = getInt();
		}
	}
	
	
	/**
	 * 若文件头中的mat>0，说明有材质。
	 * 接下来第三部分应该是一个完整的smMATERIAL_GROUP对象。
	 * size = 88。
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
			int start = buffer.position();
			
			getInt();// Head
			getInt();// *smMaterial
			materialCount = getInt();
			reformTexture = getInt();
			maxMaterial = getInt();
			lastSearchMaterial = getInt();
			lastSearchName = getString(64);
			
			size += 88;
			
			assert buffer.position() - start == 88;
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
					
					materials[i].smTexture = new TEXTURE[materials[i].TextureCounter];
					for(int j=0; j<materials[i].TextureCounter; j++) {
						TEXTURE texture = new TEXTURE();
						materials[i].smTexture[j] = texture;
						texture.Name = getString();
						texture.NameA = getString();
						
						if (texture.NameA.length() > 0) {
							log.debug("TEX MIPMAP:" + texture.NameA);
						}
					}
					
					materials[i].smAnimTexture = new TEXTURE[materials[i].AnimTexCounter];
					for(int j=0; j<materials[i].AnimTexCounter; j++) {
						TEXTURE texture = new TEXTURE();
						materials[i].smAnimTexture[j] = texture;
						texture.Name = getString();
						texture.NameA = getString();
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
		/**
		 * 判断这个面是否被使用。
		 * 实际上smd文件中存储的材质都是被用到的材质，否则是不会存储的。
		 * 因此判断这个变量并没有实际意义。
		 */
		int InUse;
		/**
		 * 纹理的数量。
		 */
		int TextureCounter;
		/**
		 * 纹理图片的名称。
		 * 对于STAGE3D来说，第1个纹理是DiffuseMap，第2个纹理应该是LightMap。
		 * 多余的纹理并不知道有什么用。
		 */
		TEXTURE[] smTexture = new TEXTURE[8];
		int[] TextureStageState = new int[8];
		int[] TextureFormState = new int[8];
		int ReformTexture;

		/**
		 * 是否透明 ( TRUE , FALSE )
		 */
		int MapOpacity;

		/**
		 * 纹理类型：混色或动画<pre>
		 * #define SMTEX_TYPE_MULTIMIX		0x0000
		 * #define SMTEX_TYPE_ANIMATION		0x0001</pre>
		 */
		int TextureType;
		
		/**
		 * 混色方式<pre>
		 * #define SMMAT_BLEND_NONE		0x00
		 * #define SMMAT_BLEND_ALPHA		0x01
		 * #define SMMAT_BLEND_COLOR		0x02
		 * #define SMMAT_BLEND_SHADOW		0x03
		 * #define SMMAT_BLEND_LAMP		0x04
		 * #define SMMAT_BLEND_ADDCOLOR	0x05
		 * #define SMMAT_BLEND_INVSHADOW	0x06</pre>
		 */
		int BlendType;// SMMAT_BLEND_XXXX

		/**
		 * TODO 未知属性
		 * TRUE or FALSE
		 */
		int Shade;
		/**
		 * 是否两面都显示 TRUE or FALSE
		 */
		int TwoSide; // 剧搁 荤侩 咯何
		int SerialNum; // 皋飘府倔俊 措茄 绊蜡 锅龋

		/**
		 * 材质的颜色
		 */
		FCOLOR Diffuse;
		/**
		 * 透明度，取值范围(0~1f)。若材质的透明度大于0.2，则这个模型不需要进行碰撞检测。
		 */
		float Transparency;
		/**
		 * 发光程度
		 */
		float SelfIllum;

		int TextureSwap; // 咆胶媚 胶客俏
		int MatFrame; // 荤侩橇饭烙 ( 荤侩矫 悼扁甫 嘎眠扁 困窃 )
		int TextureClip; // 胶客俏侩 咆胶媚 努赋蜡公 ( TRUE 搁 咆胶媚 努府俏 倾啊 )

		/**
		 * 等于ASE模型中的ScriptState
		 */
		int UseState;
		/**
		 * 是否进行碰撞检测<pre>
		 * #define SMMAT_STAT_CHECK_FACE	0x00000001</pre>
		 */
		int MeshState;

		// Mesh 函屈 包访 汲沥
		int WindMeshBottom; // 官恩阂扁 皋浆 函屈 矫累 蔼

		// 俊聪皋捞记 咆胶媚 加己
		TEXTURE[] smAnimTexture = new TEXTURE[32]; // 局聪皋捞记 咆胶媚 勤甸 府胶飘
		/**
		 * 动画有几张图NumTex
		 */
		int AnimTexCounter;
		int FrameMask; // 局聪皋捞记侩 橇饭烙 付胶农
		/**
		 * 动画切换速度。
		 */
		int Shift_FrameSpeed;
		/**
		 * SMTEX_AUTOANIMATION
		 */
		int AnimationFrame;
		
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

			
			MapOpacity = getInt();

			TextureType = getInt();

			BlendType = getInt();

			Shade = getInt();
			TwoSide = getInt();
			SerialNum = getInt();

			Diffuse = new FCOLOR();
			Transparency = getFloat();
			SelfIllum = getFloat(); //

			TextureSwap = getInt(); //
			MatFrame = getInt(); //
			TextureClip = getInt(); //

			UseState = getInt(); // ScriptState
			MeshState = getInt();

			// Mesh 函屈 包访 汲沥
			WindMeshBottom = getInt(); // TODO @see smTexture.cpp 脚本的编号

			// 俊聪皋捞记 咆胶媚 加己
			for(int i=0; i<32; i++) {
				getInt();// *smAnimTexture[32]
			}
			AnimTexCounter = getInt(); 
			FrameMask = getInt(); // NumTex-1
			Shift_FrameSpeed = getInt();
			
			/**
			 * 是否自动播放动画
			 * #define SMTEX_AUTOANIMATION		0x100
			 * 为0时不自动播放
			 */
			AnimationFrame = getInt();
			
			assert (buffer.position() - start) == 320;
		}
	}
	
	class TEXTURE {
		String Name;// [64];
		String NameA;// [64];
		int Width, Height;
		int UsedTime;
		int UseCounter;// 这个变量是给缓存器的标志位，记录这个Texture是否已经使用。
		int MapOpacity; // 是否透明( TRUE , FALSE )
		int TexSwapMode; // ( TRUE / FALSE )
		TEXTURE TexChild;
	}
	
	/**
	 * MaterialGroup中使用这个类来记录Diffuse
	 * size = 12。
	 */
	class FCOLOR {
		float r, g, b;
		FCOLOR() {
			r = getFloat();
			g = getFloat();
			b = getFloat();
		}
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
		Vector3f v;// 坐标
		Vector3f n;// normals 法向量
		
		VERTEX() {
			v = getPTPoint3f();
			n = getPTPoint3f();
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
	    Vector3f v;
	    ColorRGBA vectorColor;
	    
	    STAGE_VERTEX() {
	    	sum = getInt();
			getInt();// *lpRendVertex

			// Vectex // 除以256才是实际的值
			v = getPTPoint3f();
			
			// VectorColor
			// 除以256才能用作ColorRGBA
			float r = getShort() / 256f;
			float g = getShort() / 256f;
			float b = getShort() / 256f;
			float a = getShort() / 256f;
			vectorColor = new ColorRGBA(r, g, b, a);
	    }
	}
	
	/**
	 * size = 28
	 *
	 */
	class STAGE_FACE {
	    int sum;
	    int CalcSum;
	    int v[] = new int[4];//a, b, c, mat_id;
	    int lpTexLink;// 这是一个指针，指向TEXLINK结构体
	    TEXLINK TexLink;// 若lpTexLink != 0，则TexLink指向一个实际的对象象

	    float nx, ny, nz, y;// Cross氦磐( Normal )  ( nx , ny , nz , [0,1,0]氦磐 Y ); 
	    
	    STAGE_FACE() {
	    	sum = getInt();
			CalcSum = getInt();
			
			for(int i=0; i<4; i++) {
				v[i] = getUnsignedShort();
			}
			
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
	    
	    Vector3f location;
	    float range;
	    ColorRGBA color;
	    
	    LIGHT3D() {
	    	type = getInt();
	    	
	    	location = getPTPoint3f();
	    	
			range = getInt() / 256f / 256f;
			
			float r = getUnsignedShort() / 255f;
			float g = getUnsignedShort() / 255f;
			float b = getUnsignedShort() / 255f;
			color = new ColorRGBA(r, g, b, 1f);
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
			int start = buffer.position();
			
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
			log.debug("下列数值是地图的边缘，x,z平面的矩形。矩形的边长被放大了256倍");
			log.debug(String.format("min(%d, %d) max(%d, %d)", minX, minY, maxX, maxY));
			
			assert buffer.position() - start== 262260;
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
			Node rootNode = new Node("STAGE3D:" + key.getName());
			
			Node solidNode = new Node("SMMAT_STAT_CHECK_FACE");// 用来存放需要进行碰撞检测的部分
			Node otherNode = new Node("SMMAT_STAT_NOT_CHECK_FACE");// 用来存放不需要进行碰撞检测的部分
			rootNode.attachChild(solidNode);
			rootNode.attachChild(otherNode);
			
			int materialCount = materialGroup.materialCount;
			
			// 创建材质
			for(int mat_id=0; mat_id<materialCount; mat_id++) {
				MATERIAL m = materials[mat_id];
				
				if (m.InUse == 0) {
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
			
			if (nLight > 0) {
				rootNode.attachChild(lightNode());
			}
			
			return rootNode;
		}
		
		Node lightNode() {
			
			Node lightNode = new Node("LIGHT3D");// 灯光节点，存放所有的模拟灯光位置，仅用于调试。
			
			for(int i=0; i<nLight; i++) {
				Sphere mesh = new Sphere(12, 12, Light[i].range);
				FloatBuffer fb = (FloatBuffer)mesh.getBuffer(Type.Position).getData();
				// 顶点数目
				int cnt = fb.capacity() / 3;
				for(int n=0; n<cnt; n++) {
					float x = fb.get(n*3);
					float y = fb.get(n*3+1);
					float z = fb.get(n*3+2);
					
					x+= Light[i].location.x;
					y+= Light[i].location.y;
					z+= Light[i].location.z;
					
					fb.put(n*3, x);
					fb.put(n*3+1, y);
					fb.put(n*3+2, z);
				}
				//mesh.setBuffer(Type.Position, 3, fb);
				mesh.updateBound();
				
				Geometry geom = new Geometry("Light"+i, mesh);
				
				// 材质
				Material mat = new Material(manager, "Common/MatDefs/Misc/Unshaded.j3md");
				mat.setColor("Color", Light[i].color);
				mat.getAdditionalRenderState().setWireframe(true);
				geom.setMaterial(mat);
				
				lightNode.attachChild(geom);
			}
			
			return lightNode;
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
				if (Face[i].v[3] != mat_id)
					continue;

				// 顺序处理3个顶点
				for(int vIndex=0; vIndex<3; vIndex++) {
					// 顶点 VERTEX
					position[index * 3 + vIndex] = Vertex[Face[i].v[vIndex]].v;
	
					// 面 FACE
					f[index * 3 + vIndex] = index * 3 + vIndex;
	
					// 纹理映射
					TEXLINK tl = Face[i].TexLink;
					if(tl != null) {
						// 第1组uv坐标
						uv1[index * 3 + vIndex] = new Vector2f(tl.u[vIndex], 1f - tl.v[vIndex]);
					} else {
						uv1[index * 3 + vIndex] = new Vector2f();
					}
					
					// 第2组uv坐标
					if (tl != null && tl.NextTex != null) {
						tl = tl.NextTex;
						uv2[index * 3 + vIndex] = new Vector2f(tl.u[vIndex], 1f - tl.v[vIndex]);
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

			mesh.setStatic();
			mesh.updateBound();
			mesh.updateCounts();
			
			return mesh;
		}
	}

	class SMotionStEndInfo {
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
		MATRIX	TmInvert;		// 逆矩阵
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
			
			getInt();// Head `DCB\0`
			getInt();// smVERTEX	*Vertex;
			getInt();// smFACE		*Face;
			lpOldTexLink = getInt();// smTEXLINK	*TexLink;
			lpPhysuque = getInt();// smOBJ3D		**Physique;
			
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
			log.debug("模型文件:" + key.getName());
			
			OBJ3D obj;
			PAT3D BipPat;
			FILE_HEADER	FileHeader = smd_file_header;

			init();
			
			BipPat = BipPattern;
			
			// 读取Obj3D物体信息
			FILE_OBJINFO[] FileObjInfo = new FILE_OBJINFO [ FileHeader.objCounter ];
			for(int i=0; i<FileHeader.objCounter; i++) {
				FileObjInfo[i] = new FILE_OBJINFO();
			}
			
			// 记录文件头中的动画的帧数，拷贝每帧的数据。
			TmFrameCnt = FileHeader.tmFrameCounter;
			for(int i=0; i<32; i++) {
				TmFrame[i] = FileHeader.TmFrame[i];
			}
			
			// 读取材质
			// 骨骼文件(.smb)中不包含材质，因此可能没有这一段数据。
			if ( FileHeader.matCounter > 0) {
				smMaterialGroup = new MATERIAL_GROUP();
				smMaterialGroup.loadFile();
			}
			
			if ( NodeName != null ) {
				log.debug("NodeName != null && NodeName == " + NodeName);
				// 加载指定名称的3D物体
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
			} else {
				// 读取全部3D对象
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
			// 限制物体的数量，最多128个
			if ( nObj3d < 128 ) {
				obj3d[ nObj3d ] = obj;
				nObj3d ++;

				// 统计动画帧数
				int frame = 0;
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
		
		/**
		 * 计算物体之间的父子关系。
		 */
		void linkObject() {
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

			// 清零
			for(int i=0;i<128;i++) {
				TmSort[i]=0;
			}

			// 首先记录根节点
			for(int i=0;i<nObj3d; i++ ) {
				if ( obj3d[i].pParent == null ) 
					TmSort[NodeCnt++] = (byte)i;
			}

			// 何葛俊 崔妨乐绰 磊侥阑 茫酒 鉴辑措肺 历厘
			for(int j=0;j<nObj3d; j++ ) {
				for(int i=0; i<nObj3d; i++ ) {
					if ( obj3d[i].pParent !=null && obj3d[TmSort[j]]==obj3d[i].pParent ) {
						TmSort[NodeCnt++] = (byte)i;
					}
				}
			}
		}
		
		/**
		 * 根据结点名称，查询Obj3D对象。
		 * @param name
		 * @return
		 */
		OBJ3D getObjectFromName(String name) {
			for(int i=0; i<nObj3d; i++) {
				if(obj3d[i].NodeName.equals(name)) {
					return obj3d[i];
				}
			}
			return null;
		}
		
		/**
		 * 生成骨骼
		 */
		void buildSkeleton() {
			/*
			for(int i=0; i<nObj3d; i++) {
				MATRIX TmInvert = obj3d[i].TmInvert;
				log.debug(obj3d[i].NodeName + " Invert:");
				log.debug(TmInvert._11 + ", " + TmInvert._12 + ", " + TmInvert._13 + ", " + TmInvert._14);
				log.debug(TmInvert._21 + ", " + TmInvert._22 + ", " + TmInvert._23 + ", " + TmInvert._24);
				log.debug(TmInvert._31 + ", " + TmInvert._32 + ", " + TmInvert._33 + ", " + TmInvert._34);
				log.debug(TmInvert._41 + ", " + TmInvert._42 + ", " + TmInvert._43 + ", " + TmInvert._44);
		
			}
			*/
			
			HashMap<String, Bone> boneMap = new HashMap<String, Bone>();
			Bone[] bones = new Bone[nObj3d];
			for (int i = 0; i < nObj3d; i++) {
				byte n = TmSort[i];
				OBJ3D obj = obj3d[n];

				Bone bone = new Bone(obj.NodeName);
				boneMap.put(obj.NodeName, bone);
				bone.setBindTransforms(obj.TmPos[0].vec3, obj.TmRot[0].quad, obj.TmScale[0].scale);
				bones[i] = bone;

				// I AM YOUR FATHER!!!
				if (obj.NodeParent != null) {
					Bone parent = boneMap.get(obj.NodeParent);
					if (parent != null)
						parent.addChild(bone);
				}

			}

			Skeleton ske = new Skeleton(bones);

			AnimControl ac = new AnimControl(ske);

			SkeletonControl sc = new SkeletonControl(ske);
		}
		
		/**
		 * 将顺序读取的3个int，用TmInvert进行转置，获得一个GL坐标系的顶点。
		 * @param res1
		 * @param res2
		 * @param res3
		 * @param tm
		 */
		Vector3f mult(long res1, long res2, long res3, MATRIX tm ) {
			// reverting..
			long v1 = -(
					(res2 * tm._33 * tm._21 - res2 * tm._23 * tm._31 - res1 * tm._33 * tm._22
					+ res1 * tm._23 * tm._32 - res3 * tm._21 * tm._32 + res3 * tm._31 * tm._22
					+ tm._43 * tm._21 * tm._32 - tm._43 * tm._31 * tm._22 - tm._33 * tm._21 * tm._42
					+ tm._33 * tm._41 * tm._22 + tm._23 * tm._31 * tm._42 - tm._23 * tm._41 * tm._32) << 8)
					/ (tm._11 * tm._33 * tm._22 + tm._23 * tm._31 * tm._12 + tm._21 * tm._32 * tm._13
					- tm._33 * tm._21 * tm._12 - tm._11 * tm._23 * tm._32 - tm._31 * tm._22 * tm._13);
			long v2 = (
					(res2 * tm._11 * tm._33 - res1 * tm._33 * tm._12 - res3 * tm._11 * tm._32
					+ res3 * tm._31 * tm._12 - res2 * tm._31 * tm._13 + res1 * tm._32 * tm._13
					+ tm._11 * tm._43 * tm._32 - tm._43 * tm._31 * tm._12 - tm._11 * tm._33 * tm._42
					+ tm._33 * tm._41 * tm._12 + tm._31 * tm._42 * tm._13 - tm._41 * tm._32 * tm._13) << 8)
					/ (tm._11 * tm._33 * tm._22 + tm._23 * tm._31 * tm._12 + tm._21 * tm._32 * tm._13
					- tm._33 * tm._21 * tm._12 - tm._11 * tm._23 * tm._32 - tm._31 * tm._22 * tm._13);
			long v3 = -(
					(res2 * tm._11 * tm._23 - res1 * tm._23 * tm._12 - res3 * tm._11 * tm._22
					+ res3 * tm._21 * tm._12 - res2 * tm._21 * tm._13 + res1 * tm._22 * tm._13
					+ tm._11 * tm._43 * tm._22 - tm._43 * tm._21 * tm._12 - tm._11 * tm._23 * tm._42
					+ tm._23 * tm._41 * tm._12 + tm._21 * tm._42 * tm._13 - tm._41 * tm._22 * tm._13) << 8)
					/ (tm._11 * tm._33 * tm._22 + tm._23 * tm._31 * tm._12 + tm._21 * tm._32 * tm._13
					- tm._33 * tm._21 * tm._12 - tm._11 * tm._23 * tm._32 - tm._31 * tm._22 * tm._13);

			float x = (float) v1 / 256.0f;
			float y = (float) v2 / 256.0f;
			float z = (float) v3 / 256.0f;
			return new Vector3f(x, y, z);
		}
		Node buildPAT3D() {
			Node rootNode = new Node("STAGEOBJ:" + key.getName());
			
			for(int i=0; i<nObj3d; i++) {
				if (obj3d[i].nFace > 0) {
					Geometry geom = obj3d[i].buildOBJ3D();
					// 创建材质
					int mat_id = obj3d[i].Face[0].v[3];
					MATERIAL m = smMaterialGroup.materials[mat_id];
					Material mat = createLightMaterial(m);
					geom.setMaterial(mat);
					rootNode.attachChild(geom);
				}
			}
			
			
			// 生成骨骼
			if (BipPattern != null) {
				BipPattern.buildSkeleton();
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
		
		// 确认用户使用了SmdKey
		if (!(key instanceof SmdKey)) {
			log.error("用户未使用SmdKey来加载模型:" + key.getName());
			throw new RuntimeException("请使用SmdKey来加载精灵的smd模型。");
		}
		
		/**
		 * 将物理对象初始化为null
		 */
		BipPattern = null;
		
		/**
		 * 若用户使用了SmdKey，就根据type来决定采用哪种方式来加载模型。
		 */
		SmdKey smdkey = (SmdKey) key;
		SMDTYPE type = smdkey.type;
		switch (type) {
		case STAGE3D:{// 主地图
			getByteBuffer(assetInfo.openStream());
			smd_file_header = new FILE_HEADER();
			STAGE3D stage3D = new STAGE3D();
			stage3D.loadFile();
			return stage3D.buildStage3D();
		}
		case STAGE_OBJ_BIP: {// 舞台物体，有动画
			// 将文件名后缀改为smb
			String smb = key.getName();
			smb = smb.replaceAll("smd", "smb");
			BipPattern = (PAT3D)manager.loadAsset(new SmdKey(smb, SMDTYPE.BONE));
		}
		case STAGE_OBJ:{// 舞台物体，无动画
			getByteBuffer(assetInfo.openStream());
			smd_file_header = new FILE_HEADER();
			PAT3D pat = new PAT3D();
			pat.loadFile(null);
			return pat.buildPAT3D();
		}
		case PAT3D:
			return null;
		case MODEL:
			return null;
		case BONE: {
			getByteBuffer(assetInfo.openStream());
			smd_file_header = new FILE_HEADER();
			PAT3D bone = new PAT3D();
			bone.loadFile(null);
			return bone;
		}
		default:
			return null;
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
		
		// TODO 将texture.Name存入缓存中，避免多次加载。
		
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
		mat.setColor("Diffuse", new ColorRGBA(m.Diffuse.r, m.Diffuse.g, m.Diffuse.b, 1));
		//mat.setBoolean("UseMaterialColors", true);
		
		RenderState rs = mat.getAdditionalRenderState();
		
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
		FrameAnimControl control = new FrameAnimControl(m.AnimTexCounter, m.Shift_FrameSpeed);
		for(int i=0; i<m.AnimTexCounter; i++) {
			Texture tex = createTexture(m.smAnimTexture[i].Name);
			control.textures[i] = tex;
		}
		return control;
	}
	
	/**
	 * 帧动画控制器
	 * 精灵的部分动画是通过图片轮播来实现的。
	 * @author yanmaoyuan
	 *
	 */
	class FrameAnimControl extends AbstractControl {
		/**
		 * 纹理数据数据
		 */
		private Texture[] textures;
		/**
		 * 动画帧数
		 */
		private final int numTex;
		/**
		 * 帧切换的时间间隔，单位为秒。
		 */
		private final float internal;
		
		/**
		 * 
		 * @param numTex
		 * @param shiftFrameSpeed 帧的切换速度，为2的n次方，单位是毫秒。
		 */
		public FrameAnimControl(int numTex, int shiftFrameSpeed) {
			this.numTex = numTex;
			this.textures = new Texture[numTex];
			this.internal = (1 << shiftFrameSpeed) / 1000f;
		}
		
		private float time = 0;
		private int index = 0;
		
		@Override
		protected void controlUpdate(float tpf) {
			time += tpf;
			if (time > internal) {
				time -= internal;

				// 切换图片
				index++;
				if (index == numTex) {
					index = 0;
				}
				
				Material mat = ((Geometry) spatial).getMaterial();
				mat.setTexture("DiffuseMap", textures[index]);
			}
		}
		
		@Override
		protected void controlRender(RenderManager rm, ViewPort vp) {}

	}

}
