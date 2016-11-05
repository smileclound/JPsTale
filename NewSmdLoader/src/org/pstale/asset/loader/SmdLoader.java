package org.pstale.asset.loader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.pstale.asset.control.FrameAnimControl;
import org.pstale.asset.control.WaterAnimationControl;
import org.pstale.asset.control.WindAnimationControl;
import org.pstale.asset.loader.SmdKey.SMDTYPE;

import com.jme3.animation.AnimControl;
import com.jme3.animation.Animation;
import com.jme3.animation.Bone;
import com.jme3.animation.BoneTrack;
import com.jme3.animation.Skeleton;
import com.jme3.animation.SkeletonControl;
import com.jme3.asset.AssetInfo;
import com.jme3.asset.AssetKey;
import com.jme3.asset.AssetLoader;
import com.jme3.asset.AssetManager;
import com.jme3.asset.TextureKey;
import com.jme3.light.AmbientLight;
import com.jme3.material.Material;
import com.jme3.material.RenderState;
import com.jme3.material.RenderState.BlendMode;
import com.jme3.material.RenderState.FaceCullMode;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Quaternion;
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
import com.jme3.util.TempVars;

/**
 * 精灵场景加载器
 * 
 * @author yanmaoyuan
 * 
 */
public class SmdLoader extends ByteReader implements AssetLoader {

	static Logger log = Logger.getLogger(SmdLoader.class);

	// 是否使用OPENGL坐标系
	boolean OPEN_GL_AXIS = true;
	// 是否打印动画日志
	boolean LOG_ANIMATION = false;

	/**
	 * 动画的颜色要比别的模型亮一点点。
	 */
	AmbientLight ambientLightForAnimation;

	public SmdLoader() {
		ambientLightForAnimation = new AmbientLight();
		ambientLightForAnimation.setColor(new ColorRGBA(0.8f, 0.8f, 0.8f, 1f));
	}

	/**
	 * 精灵的动画使用3DS MAX的默认速率，每秒30Tick，每Tick共160帧。 也就是每秒4800帧。
	 * 
	 * 但是smd文件中也另外存储了2个参数： (1) 每秒Tick数 (默认30) (2) 每Tick帧数 (默认160)
	 * 这两个变量的值控制了动画播放的速率。
	 */
	private float framePerSecond = 4800f;

	private final static int OBJ_FRAME_SEARCH_MAX = 32;

	private FILE_HEADER smd_file_header;

	final static int sMATS_SCRIPT_WIND = 1;
	final static int sMATS_SCRIPT_WINDZ1 = 0x0020;
	final static int sMATS_SCRIPT_WINDZ2 = 0x0040;
	final static int sMATS_SCRIPT_WINDX1 = 0x0080;
	final static int sMATS_SCRIPT_WINDX2 = 0x0100;
	final static int sMATS_SCRIPT_WATER = 0x0200;
	final static int sMATS_SCRIPT_NOTVIEW = 0x0400;
	final static int sMATS_SCRIPT_PASS = 0x0800;
	final static int sMATS_SCRIPT_NOTPASS = 0x1000;
	final static int sMATS_SCRIPT_RENDLATTER = 0x2000;
	final static int sMATS_SCRIPT_BLINK_COLOR = 0x4000;
	final static int sMATS_SCRIPT_CHECK_ICE = 0x8000;
	final static int sMATS_SCRIPT_ORG_WATER = 0x10000;

	/**
	 * size = 64
	 */
	class FMATRIX {
		float _11, _12, _13, _14;
		float _21, _22, _23, _24;
		float _31, _32, _33, _34;
		float _41, _42, _43, _44;

		FMATRIX() {
			_11 = 1;
			_12 = 0;
			_13 = 0;
			_14 = 0;
			_21 = 0;
			_22 = 1;
			_23 = 0;
			_24 = 0;
			_31 = 0;
			_32 = 0;
			_33 = 1;
			_34 = 0;
			_41 = 0;
			_42 = 0;
			_43 = 0;
			_44 = 1;
		}

		FMATRIX(boolean init) {
			_11 = getFloat();
			_12 = getFloat();
			_13 = getFloat();
			_14 = getFloat();
			_21 = getFloat();
			_22 = getFloat();
			_23 = getFloat();
			_24 = getFloat();
			_31 = getFloat();
			_32 = getFloat();
			_33 = getFloat();
			_34 = getFloat();
			_41 = getFloat();
			_42 = getFloat();
			_43 = getFloat();
			_44 = getFloat();
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
			_11 = 1;
			_12 = 0;
			_13 = 0;
			_14 = 0;
			_21 = 0;
			_22 = 1;
			_23 = 0;
			_24 = 0;
			_31 = 0;
			_32 = 0;
			_33 = 1;
			_34 = 0;
			_41 = 0;
			_42 = 0;
			_43 = 0;
			_44 = 1;
		}

		/**
		 * 这个矩阵的特征值是256，所有元素除以256后的行列式是1。
		 * 
		 * @param init
		 */
		MATRIX(boolean init) {
			_11 = getInt();
			_12 = getInt();
			_13 = getInt();
			_14 = getInt();
			_21 = getInt();
			_22 = getInt();
			_23 = getInt();
			_24 = getInt();
			_31 = getInt();
			_32 = getInt();
			_33 = getInt();
			_34 = getInt();
			_41 = getInt();
			_42 = getInt();
			_43 = getInt();
			_44 = getInt();
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
		float x, y, z;

		TM_SCALE() {
			frame = getInt();
			x = getPTDouble();
			y = getPTDouble();
			z = getPTDouble();
		}
	}

	class Keyframe {
		Vector3f translation;
		Quaternion rotation;
		Vector3f scale;
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
	 * SMD文件头 size = 556;
	 */
	class FILE_HEADER {
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
	 * 若文件头中的mat>0，说明有材质。 接下来第三部分应该是一个完整的smMATERIAL_GROUP对象。 size = 88。
	 */
	class MATERIAL_GROUP {
		// DWORD Head
		MATERIAL[] materials;
		int materialCount;
		int reformTexture;
		int maxMaterial;
		int lastSearchMaterial;
		String lastSearchName;

		// //////////////
		// 计算读取结束后整个MaterialGroup占用了多少内存，没有实际意义。
		// int size = 0;
		// //////////////
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

			// size += 88;

			assert buffer.position() - start == 88;
		}

		/**
		 * 载入所有材质
		 */
		void loadFile() {
			materials = new MATERIAL[materialCount];

			for (int i = 0; i < materialCount; i++) {
				materials[i] = new MATERIAL();
				// size += 320;

				if (materials[i].InUse != 0) {
					getInt();// int strLen; 这个整数记录了后续所有材质名称所占的字节数。
					// size += 4;
					// size += strLen;

					materials[i].smTexture = new TEXTURE[materials[i].TextureCounter];
					for (int j = 0; j < materials[i].TextureCounter; j++) {
						TEXTURE texture = new TEXTURE();
						materials[i].smTexture[j] = texture;
						texture.Name = getString();
						texture.NameA = getString();

						if (texture.NameA.length() > 1) {
							// TODO 还不知道NameA所代表的Tex有何用
						}
					}

					materials[i].smAnimTexture = new TEXTURE[materials[i].AnimTexCounter];
					for (int j = 0; j < materials[i].AnimTexCounter; j++) {
						TEXTURE texture = new TEXTURE();
						materials[i].smAnimTexture[j] = texture;
						texture.Name = getString();
						texture.NameA = getString();
					}
				}
			}
		}
	}

	/**
	 * 材质 size = 320
	 * 
	 * @author yanmaoyuan
	 * 
	 */
	class MATERIAL {
		/**
		 * 判断这个面是否被使用。 实际上smd文件中存储的材质都是被用到的材质，否则是不会存储的。 因此判断这个变量并没有实际意义。
		 */
		int InUse;
		/**
		 * 纹理的数量。
		 */
		int TextureCounter;
		/**
		 * 纹理图片的名称。 对于STAGE3D来说，第1个纹理是DiffuseMap，第2个纹理应该是LightMap。
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
		 * 纹理类型：混色或动画
		 * 
		 * <pre>
		 * #define SMTEX_TYPE_MULTIMIX		0x0000
		 * #define SMTEX_TYPE_ANIMATION		0x0001
		 * </pre>
		 */
		int TextureType;

		/**
		 * 混色方式
		 * 
		 * <pre>
		 * #define SMMAT_BLEND_NONE		0x00
		 * #define SMMAT_BLEND_ALPHA		0x01
		 * #define SMMAT_BLEND_COLOR		0x02
		 * #define SMMAT_BLEND_SHADOW		0x03
		 * #define SMMAT_BLEND_LAMP		0x04
		 * #define SMMAT_BLEND_ADDCOLOR	0x05
		 * #define SMMAT_BLEND_INVSHADOW	0x06
		 * </pre>
		 */
		int BlendType;// SMMAT_BLEND_XXXX

		/**
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
		 * 等于ASE模型中的ScriptState sMATS_SCRIPT_WIND sMATS_SCRIPT_WINDX1
		 * sMATS_SCRIPT_WINDX2 sMATS_SCRIPT_WINDZ1 sMATS_SCRIPT_WINDZ2
		 * sMATS_SCRIPT_WATER sMATS_SCRIPT_NOTPASS // 碰撞，但是不可见 sMATS_SCRIPT_PASS
		 * // 可以穿过
		 * 
		 * sMATS_SCRIPT_RENDLATTER -> MeshState |= sMATS_SCRIPT_RENDLATTER;
		 * sMATS_SCRIPT_CHECK_ICE -> MeshState |= sMATS_SCRIPT_CHECK_ICE;
		 * sMATS_SCRIPT_ORG_WATER -> MeshState = sMATS_SCRIPT_ORG_WATER;
		 */
		int UseState;
		/**
		 * 是否进行碰撞检测
		 * 
		 * <pre>
		 * #define SMMAT_STAT_CHECK_FACE	0x00000001
		 * </pre>
		 */
		int MeshState;

		int WindMeshBottom;

		/**
		 * 逐帧动画的纹理文件名
		 */
		TEXTURE[] smAnimTexture = new TEXTURE[32];
		/**
		 * 动画有几张图NumTex
		 */
		int AnimTexCounter;
		int FrameMask;// == AnimTexCounter - 1
		/**
		 * 动画切换速度。
		 */
		int Shift_FrameSpeed;
		/**
		 * SMTEX_AUTOANIMATION = 0x0100
		 */
		int AnimationFrame;

		/**
		 * 读取MATERIAL数据结构
		 */
		MATERIAL() {
			int start = buffer.position();

			InUse = getInt(); // > 0 表示在使用
			TextureCounter = getInt();// 纹理的数量。动画纹理必然只有1张。
			for (int i = 0; i < 8; i++) {
				getInt();// *smTexture[8];
			}
			for (int i = 0; i < 8; i++) {
				TextureStageState[i] = getInt();
			}
			for (int i = 0; i < 8; i++) {
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

			WindMeshBottom = getInt();

			// 俊聪皋捞记 咆胶媚 加己
			for (int i = 0; i < 32; i++) {
				getInt();// *smAnimTexture[32]
			}
			AnimTexCounter = getInt();
			FrameMask = getInt(); // NumTex-1
			Shift_FrameSpeed = getInt();

			/**
			 * 是否自动播放动画 #define SMTEX_AUTOANIMATION 0x100 为0时不自动播放
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
	 * MaterialGroup中使用这个类来记录Diffuse size = 12。
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
		float u, v;

		FTPOINT() {
			u = getFloat();
			v = getFloat();
		}
	}

	// size = 12
	class POINT3D {
		int x, y, z;

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
		long x, y, z;
		Vector3f v;// 坐标
		Vector3f n;// normals 法向量

		VERTEX() {
			x = getInt();
			y = getInt();
			z = getInt();

			v = new Vector3f(x / 256f, y / 256f, z / 256f);
			n = getPTPoint3f();
		}
	}

	/**
	 * size = 36
	 */
	class FACE {
		int[] v = new int[4];// a,b,c,Matrial
		FTPOINT[] t = new FTPOINT[3];
		int lpTexLink;
		TEXLINK TexLink;

		FACE() {
			for (int i = 0; i < 4; i++) {
				v[i] = getUnsignedShort();
			}

			for (int i = 0; i < 3; i++) {
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
		// smRENDVERTEX *lpRendVertex;
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
		int v[] = new int[4];// a, b, c, mat_id;
		int lpTexLink;// 这是一个指针，指向TEXLINK结构体
		TEXLINK TexLink;// 若lpTexLink != 0，则TexLink指向一个实际的对象象

		float nx, ny, nz, y;// Cross氦磐( Normal ) ( nx , ny , nz , [0,1,0]氦磐 Y );

		STAGE_FACE() {
			sum = getInt();
			CalcSum = getInt();

			for (int i = 0; i < 4; i++) {
				v[i] = getUnsignedShort();
			}

			lpTexLink = getInt();// 纹理坐标的指针。smTEX_LINK *lpTexLink

			nx = getShort() / 32767f;// nx
			ny = getShort() / 32767f;// ny
			nz = getShort() / 32767f;// nz
			y = getShort() / 32767f;// Y 除以32767后是 1/8PI，不知道有何用。
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
		/**
		 * <pre>
		 * #define	smLIGHT_TYPE_NIGHT		0x00001
		 * #define	smLIGHT_TYPE_LENS		0x00002
		 * #define	smLIGHT_TYPE_PULSE2	0x00004
		 * #define	SMLIGHT_TYPE_OBJ		0x00008
		 * #define	smLIGHT_TYPE_DYNAMIC	0x80000
		 * </pre>
		 */
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
	 * Stage3D对象的属性 文件数据的第二段，存储了一个完整的smSTAGE3D对象。 size = 262260
	 * 其中的关键数据是nVertex/nFace/nTexLink/nLight这些。
	 */
	class STAGE3D {
		// DWORD Head; 无用的头文件指针，4字节
		int[][] StageArea;// WORD *StageArea[MAP_SIZE][MAP_SIZE];256 *
							// 256个指针，共262144字节
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
		protected STAGE3D() {
			int start = buffer.position();

			// Head = FALSE;
			getInt();// Head
			buffer.get(new byte[262144]);// *StageArea[MAP_SIZE][MAP_SIZE]; 4 *
											// 256 * 256 = 262144
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
			log.debug(String.format("min(%d, %d) max(%d, %d)", minX, minY,
					maxX, maxY));

			assert buffer.position() - start == 262260;
		}

		/**
		 * 加载舞台数据
		 * 
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
			for (int i = 0; i < nVertex; i++) {
				Vertex[i] = new STAGE_VERTEX();
			}

			// 读取Face
			Face = new STAGE_FACE[nFace];
			for (int i = 0; i < nFace; i++) {
				Face[i] = new STAGE_FACE();
			}

			// 读取TEX_LINK(其实就是uv坐标)
			TexLink = new TEXLINK[nTexLink];
			for (int i = 0; i < nTexLink; i++) {
				TexLink[i] = new TEXLINK();
			}

			// 读取灯光
			if (nLight > 0) {
				Light = new LIGHT3D[nLight];
				for (int i = 0; i < nLight; i++) {
					Light[i] = new LIGHT3D();
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
		Mesh buildSolidMesh() {
			Mesh mesh = new Mesh();

			int materialCount = materialGroup.materialCount;
			/**
			 * 根据材质的特诊来筛选参加碰撞检测的物体， 将被忽略的材质设置成null，作为一种标记。
			 */
			MATERIAL m;// 临时变量
			for (int mat_id = 0; mat_id < materialCount; mat_id++) {
				m = materials[mat_id];

				if (m.MeshState == 1 && m.Transparency < 0.2f) {
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
				
				if ( m.BlendType == 1) {// ALPHA混色
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

			log.debug("总面数:" + nFace + " 碰撞面数:" + fSize);
			log.debug("总点数:" + nVertex + " 碰撞点数:" + vSize);
			return mesh;
		}

		/**
		 * 生成STAGE3D对象
		 * 
		 * @return
		 */
		Node buildNode() {
			Node rootNode = new Node("STAGE3D:" + key.getName());

			// 为了让表面平滑，先基于原来的面和定点计算一次法向量。
			Vector3f[] orginNormal = computeOrginNormals();

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
				Geometry geom = new Geometry(key.getName() + "#" + mat_id, mesh);

				// 创建材质
				Material mat;
				if (m.TextureType == 0) {
					// SMTEX_TYPE_MULTIMIX
					mat = createLightMaterial(materials[mat_id]);
				} else {
					// SMTEX_TYPE_ANIMATION
					mat = createMiscMaterial(materials[mat_id]);
				}
				setRenderState(m, mat);

				// 应用材质
				geom.setMaterial(mat);

				// 有多个动画
				if (m.AnimTexCounter > 0) {
					FrameAnimControl control = createFrameAnimControl(materials[mat_id]);
					geom.addControl(control);
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
						// 水面不能动，一动地图就裂了。。
						//geom.addControl(new WaterAnimationControl());
						break;
					}
					}
				}

				rootNode.attachChild(geom);

				// 透明度
				// 只有不透明物体才需要检测碰撞网格。
				if (m.MapOpacity != 0 || m.Transparency != 0 || m.BlendType == 1) {
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
						// m.WindMeshBottom == dwBlinkCode[]{ 9, 10, 11, 12, 13,
						// 14, 15, 16,} 8个数值的其中之一
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
					// 法向量 Normal
					normal[index * 3 + vIndex] = orginNormal[Face[i].v[vIndex]];

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
			mesh.setBuffer(Type.Position, 3,
					BufferUtils.createFloatBuffer(position));
			mesh.setBuffer(Type.Index, 3, f);
			// DiffuseMap UV
			mesh.setBuffer(Type.TexCoord, 2, BufferUtils.createFloatBuffer(uv1));
			// LightMap UV
			mesh.setBuffer(Type.TexCoord2, 2,
					BufferUtils.createFloatBuffer(uv2));
			// 法向量
			mesh.setBuffer(Type.Normal, 3,
					BufferUtils.createFloatBuffer(normal));

			mesh.setStatic();
			mesh.updateBound();
			mesh.updateCounts();

			return mesh;
		}
	}

	class SMotionStEndInfo {
		int StartFrame;
		int EndFrame;
	}

	/**
	 * size = 2236
	 */
	class OBJ3D {
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
			Face = null;
			Vertex = null;
			TexLink = null;
			Physique = null;
		}

		void readOBJ3D() {
			int start = buffer.position();

			getInt();// Head `DCB\0`
			getInt();// smVERTEX *Vertex;
			getInt();// smFACE *Face;
			lpOldTexLink = getInt();// smTEXLINK *TexLink;
			lpPhysuque = getInt();// smOBJ3D **Physique;

			ZeroVertex = new VERTEX();

			maxZ = getInt();
			minZ = getInt();
			maxY = getInt();
			minY = getInt();
			maxX = getInt();
			minX = getInt();

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
			for (int i = 0; i < 8; i++) {
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

			qx = getFloat();
			qy = getFloat();
			qz = getFloat();
			qw = getFloat();
			sx = getPTDouble();
			sy = getPTDouble();
			sz = getPTDouble();
			px = getPTDouble();
			py = getPTDouble();
			pz = getPTDouble();

			getInt();// smTM_ROT *TmRot;
			getInt();// smTM_POS *TmPos;
			getInt();// smTM_SCALE *TmScale;
			getInt();// smFMATRIX *TmPrevRot;

			TmRotCnt = getInt();
			TmPosCnt = getInt();
			TmScaleCnt = getInt();

			for (int i = 0; i < OBJ_FRAME_SEARCH_MAX; i++) {
				TmRotFrame[i] = new FRAME_POS();
			}
			for (int i = 0; i < OBJ_FRAME_SEARCH_MAX; i++) {
				TmPosFrame[i] = new FRAME_POS();
			}
			for (int i = 0; i < OBJ_FRAME_SEARCH_MAX; i++) {
				TmScaleFrame[i] = new FRAME_POS();
			}
			TmFrameCnt = getInt();

			assert buffer.position() - start == 2236;
		}

		/**
		 * 读取OBJ3D文件数据
		 * 
		 * @param PatPhysique
		 */
		void loadFile(PAT3D PatPhysique) {
			// 读取OBJ3D对象，共2236字节
			readOBJ3D();

			Vertex = new VERTEX[nVertex];
			for (int i = 0; i < nVertex; i++) {
				Vertex[i] = new VERTEX();
			}

			Face = new FACE[nFace];
			for (int i = 0; i < nFace; i++) {
				Face[i] = new FACE();
			}

			TexLink = new TEXLINK[nTexLink];
			for (int i = 0; i < nTexLink; i++) {
				TexLink[i] = new TEXLINK();
			}

			TmRot = new TM_ROT[TmRotCnt];
			for (int i = 0; i < TmRotCnt; i++) {
				TmRot[i] = new TM_ROT();
			}

			TmPos = new TM_POS[TmPosCnt];
			for (int i = 0; i < TmPosCnt; i++) {
				TmPos[i] = new TM_POS();
			}

			TmScale = new TM_SCALE[TmScaleCnt];
			for (int i = 0; i < TmScaleCnt; i++) {
				TmScale[i] = new TM_SCALE();
			}

			TmPrevRot = new FMATRIX[TmRotCnt];
			for (int i = 0; i < TmRotCnt; i++) {
				TmPrevRot[i] = new FMATRIX(true);
			}

			relinkFaceAndTex();

			// 绑定动画骨骼
			if (lpPhysuque != 0 && PatPhysique != null) {

				Physique = new OBJ3D[nVertex];

				String[] names = new String[nVertex];
				for (int i = 0; i < nVertex; i++) {
					names[i] = getString(32);
				}

				for (int i = 0; i < nVertex; i++) {
					Physique[i] = PatPhysique.getObjectFromName(names[i]);
				}

			}
		}

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
		 * 生成网格数据。
		 * 
		 * @param ske
		 * @return
		 */
		Mesh buildMesh(int mat_id, Skeleton ske) {
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

			mesh.setBuffer(Type.Position, 3,
					BufferUtils.createFloatBuffer(position));
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
		Vector3f mult(long res1, long res2, long res3, MATRIX tm) {
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

		void invertPoint() {

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

	/**
	 * size = 1228
	 */
	class PAT3D {
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

		PAT3D() {

		}

		PAT3D(boolean init) {
			int start = buffer.position();

			getInt();// Head
			for (int i = 0; i < 128; i++) {
				getInt();
			}
			buffer.get(TmSort);

			getInt();// smPAT3D *TmParent;

			getInt();// smMATERIAL_GROUP *smMaterialGroup; //皋飘府倔 弊缝

			MaxFrame = getInt();
			Frame = getInt();

			SizeWidth = getInt();
			SizeHeight = getInt();

			nObj3d = getInt();
			getInt();// LPDIRECT3DTEXTURE2 *hD3DTexture;

			Posi = new POINT3D(true);
			Angle = new POINT3D(true);
			CameraPosi = new POINT3D(true);

			dBound = getInt();
			Bound = getInt();

			for (int i = 0; i < OBJ_FRAME_SEARCH_MAX; i++) {
				TmFrame[i] = new FRAME_POS();
			}
			TmFrameCnt = getInt();

			TmLastFrame = getInt();
			TmLastAngle = new POINT3D(true);

			assert buffer.position() - start == 1228;
		}

		void init() {
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

		void loadFile(String NodeName, PAT3D BipPat) {
			log.debug("模型文件:" + key.getName());

			OBJ3D obj;
			FILE_HEADER FileHeader = smd_file_header;

			init();

			// 读取Obj3D物体信息
			FILE_OBJINFO[] FileObjInfo = new FILE_OBJINFO[FileHeader.objCounter];
			for (int i = 0; i < FileHeader.objCounter; i++) {
				FileObjInfo[i] = new FILE_OBJINFO();
			}

			// 记录文件头中的动画的帧数，拷贝每帧的数据。
			TmFrameCnt = FileHeader.tmFrameCounter;
			for (int i = 0; i < 32; i++) {
				TmFrame[i] = FileHeader.TmFrame[i];
			}

			// 读取材质
			// 骨骼文件(.smb)中不包含材质，因此可能没有这一段数据。
			if (FileHeader.matCounter > 0) {
				smMaterialGroup = new MATERIAL_GROUP();
				smMaterialGroup.loadFile();
			}

			if (NodeName != null) {
				log.debug("NodeName != null && NodeName == " + NodeName);
				// 加载指定名称的3D物体
				for (int i = 0; i < FileHeader.objCounter; i++) {
					if (NodeName.equals(FileObjInfo[i].NodeName)) {
						obj = new OBJ3D();
						if (obj != null) {
							buffer.position(FileObjInfo[i].ObjFilePoint);
							obj.loadFile(BipPat);
							addObject(obj);
						}
						break;
					}
				}
			} else {
				// 读取全部3D对象
				for (int i = 0; i < FileHeader.objCounter; i++) {
					obj = new OBJ3D();
					if (obj != null) {
						obj.loadFile(BipPat);
						addObject(obj);
					}
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

		Node buildNode() {
			Node rootNode = new Node("PAT3D:" + key.getName());

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
						Material mat = createLightMaterial(m);

						// 创建几何体并应用材质。
						Geometry geom = new Geometry(obj3d[i].NodeName + "#"
								+ mat_id, mesh);
						geom.setMaterial(mat);

						// 设置位置
						// TODO 这个位置设置后并不准确，需要进一步研究。
						Vector3f translation = new Vector3f(-obj.py, obj.pz,
								-obj.px);
						Quaternion rotation = new Quaternion(-obj.qy, obj.qz,
								-obj.qx, -obj.qw);
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
		 * 若用户使用了SmdKey，就根据type来决定采用哪种方式来加载模型。
		 */
		SmdKey smdkey = (SmdKey) key;
		SMDTYPE type = smdkey.type;
		switch (type) {
		case STAGE3D: {// 主地图
			getByteBuffer(assetInfo.openStream());
			smd_file_header = new FILE_HEADER();
			STAGE3D stage3D = new STAGE3D();
			stage3D.loadFile();
			return stage3D.buildNode();
		}
		case STAGE3D_SOLID: {// 地图网格
			getByteBuffer(assetInfo.openStream());
			smd_file_header = new FILE_HEADER();
			STAGE3D stage3D = new STAGE3D();
			stage3D.loadFile();
			return stage3D.buildSolidMesh();
		}
		case BONE: {
			getByteBuffer(assetInfo.openStream());
			smd_file_header = new FILE_HEADER();
			PAT3D bone = new PAT3D();
			bone.loadFile(null, null);
			return bone;
		}
		case PAT3D_BIP: {// 有动画的舞台物体
			// 后缀名改为smb
			String smbFile = key.getName();
			int n = smbFile.lastIndexOf(".");
			String str = smbFile.substring(0, n);
			smbFile = str + ".smb";
			PAT3D bone = (PAT3D) manager.loadAsset(new SmdKey(smbFile,
					SMDTYPE.BONE));

			// 再加载smd文件
			key = assetInfo.getKey();
			getByteBuffer(assetInfo.openStream());
			smd_file_header = new FILE_HEADER();
			PAT3D pat = new PAT3D();
			pat.loadFile(null, bone);
			return pat.buildNode();
		}
		case PAT3D: {// 舞台物体，无动画
			getByteBuffer(assetInfo.openStream());
			smd_file_header = new FILE_HEADER();
			PAT3D pat = new PAT3D();
			pat.loadFile(null, smdkey.getBone());
			return pat.buildNode();
		}
		case INX: {
			String inx = key.getName().toLowerCase();
			// inx文件
			if (inx.endsWith("inx")) {
				// 文件长度不对
				if (assetInfo.openStream().available() <= 67083) {
					log.warn("Error: can't read inx-file (invalid file content)");
					return null;
				}

				getByteBuffer(assetInfo.openStream());

				return parseInx();
			}

			return null;
		}

		default:
			return null;
		}
	}

	/**************************************************
	 * 解析INX文件
	 * 
	 * @return
	 */
	private Object parseInx() {

		String smdFile = getString(64);
		String smbFile = getString(64);

		if (smdFile.length() > 0) {
			smdFile = changeName(smdFile);
		}

		if (smbFile.length() > 0) {
			smbFile = changeName(smbFile);
		}

		DrzAnimation anim;
		String sharedInxFile;
		if (buffer.limit() <= 67084) { // old inx file
			buffer.position(61848);
			sharedInxFile = getString();
			handleShared(sharedInxFile);
			anim = readAnimFromOld();
		} else { // new inx file (KPT)
			buffer.position(88472);
			sharedInxFile = getString();
			handleShared(sharedInxFile);
			anim = readAnimFromNew();
		}

		PAT3D BipPattern = null;
		// Read Animation from smb
		if (smbFile.length() > 0) {
			// 后缀名改为smb
			int n = smbFile.lastIndexOf(".");
			String str = smbFile.substring(0, n);
			smbFile = str + ".smb";

			BipPattern = (PAT3D) manager.loadAsset(new SmdKey(key.getFolder()
					+ smbFile, SMDTYPE.BONE));
		}

		// Read Mesh from smd
		// 后缀名改为smd
		int n = smdFile.lastIndexOf(".");
		String str = smdFile.substring(0, n);
		smdFile = str + ".smd";

		SmdKey smdKey = new SmdKey(key.getFolder() + smdFile, SMDTYPE.PAT3D);
		smdKey.setBone(BipPattern);

		return manager.loadAsset(smdKey);
	}

	/**
	 * 共享动画数据
	 */
	private void handleShared(String sharedInxFile) {
		if (sharedInxFile == null || sharedInxFile.length() == 0)
			return;

		// 后缀名改为inx
		int n = sharedInxFile.lastIndexOf(".");
		String str = sharedInxFile.substring(0, n);
		sharedInxFile = str + ".inx";

		sharedInxFile = changeName(sharedInxFile);

		// 读取共享的动画
		File file = new File(sharedInxFile);
		if (file.exists()) {
			try {
				InputStream inputStream = new FileInputStream(file);
				int length = inputStream.available();

				if (length <= 67083) {
					System.err
							.println("Error: can't read inx-file (invalid file content):"
									+ length);
				} else {
					getByteBuffer(inputStream);

					buffer.position(64);
					String smbFile = getString();
					if (smbFile.length() > 0) {
						smbFile = changeName(smbFile);
					}

					// TODO 没有正确使用
					log.debug("使用了共享的骨骼动画:" + smbFile);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			log.warn("Error: " + sharedInxFile + " not exists.");
		}
	}

	class DrzAnimationSet {
		public int AnimationIndex;

		public int AnimationTypeId;

		public double SetStartTime;// 开始时间 * 160
		public double SetEndTime1;// 结束时间 * 160
		public double AnimationDurationTime;// 总时长 * 160

		public int AnimationStartKey;
		public int AnimationEndKey1;
		public int AnimationEndKey2;
		public int AnimationDurationKeys;

		public boolean Repeat;// 是否重复
		public char UnkChar;
		public int SubAnimationIndex;// 对应动画的索引

		public DrzAnimationSet() {

		}

		public DrzAnimationSet(int _ani_type_id, int _start_key, int _end_key,
				boolean _repeat, char _unk_letter, int _sub_ani_index) {
			AnimationStartKey = _start_key;
			AnimationEndKey1 = _end_key;
			Repeat = _repeat;
			UnkChar = _unk_letter;
			SubAnimationIndex = _sub_ani_index;
			AnimationTypeId = _ani_type_id;
		}

		public String toString() {
			String name = getAnimationSetNameById(AnimationTypeId);
			float length = (float) AnimationDurationTime * 160;
			return String.format(
					"[%d %s]SubAnimInx=%d Type=%d 开始帧=%d 结束帧=%d 重复=%b 时间=%.2f",
					AnimationIndex, name, SubAnimationIndex, AnimationTypeId,
					AnimationStartKey, AnimationEndKey1, Repeat, length);
		}

		public String getName() {
			return AnimationIndex + " "
					+ getAnimationSetNameById(AnimationTypeId);
		}

		public float getLength() {
			return (float) AnimationDurationTime * 160;
		}

		public Animation newJmeAnimation() {
			return new Animation(getName(), getLength());
		}
	}

	class DrzAnimation {
		String mAnimationName;

		HashMap<Integer, DrzAnimationSet> mAnimationSetMap = new HashMap<Integer, DrzAnimationSet>();

		List<DrzInxMeshInfo> meshDefInfo = new ArrayList<DrzInxMeshInfo>();

		int mSubAnimationNum;
	}

	/**
	 * 解析动画索引
	 * 
	 * @return
	 */
	private DrzAnimation readAnimFromOld() {
		DrzAnimation animation = new DrzAnimation();

		// Read The Mesh Def
		readMeshDef();

		buffer.position(61836);
		int AnimationCount = getShort() - 10;

		int AnimationOffset = 1596;

		int SubAnimationNum = 0;
		for (int i = 0; i < AnimationCount; i++) {
			buffer.position(AnimationOffset + (i * 120) + 116);
			int temp_max_sub_ani = getUnsignedInt();
			if (temp_max_sub_ani > SubAnimationNum) {
				SubAnimationNum = temp_max_sub_ani;
			}
		}

		// 解析动画索引
		if (SubAnimationNum > 0) {

			animation.mSubAnimationNum = SubAnimationNum;

			// 临时变量
			int[] tmpInt = new int[2];
			for (int id = 0; id < AnimationCount; id++) {

				/**
				 * 动画的类型，看getAminationSetNameById就知道什么意思了
				 */
				buffer.position(AnimationOffset + (id * 120));

				int AnimationId = getInt();

				if (AnimationId < 1) // no more Animations
					break;

				DrzAnimationSet animSet = new DrzAnimationSet();

				// Set AnimationSetID
				animSet.AnimationTypeId = AnimationId;

				// 开始帧
				buffer.position(AnimationOffset + (id * 120) + 4);// current
																	// animation
																	// starts at
																	// this
																	// frame
				tmpInt[0] = buffer.get() & 0xFF;
				buffer.position(AnimationOffset + (id * 120) + 6);
				tmpInt[1] = buffer.get() & 0xFF;

				animSet.AnimationStartKey = (tmpInt[1] << 8) + tmpInt[0];

				/**
				 * 结束帧
				 */
				buffer.position(AnimationOffset + (id * 120) + 16);// current
																	// animation
																	// end at
																	// this
																	// frame
				tmpInt[0] = buffer.get() & 0xFF;
				buffer.position(AnimationOffset + (id * 120) + 18);
				tmpInt[1] = buffer.get() & 0xFF;

				animSet.AnimationEndKey1 = (tmpInt[1] << 8) + tmpInt[0];

				/**
				 * 动画是否重复播放
				 */
				buffer.position(AnimationOffset + (id * 120) + 108);

				animSet.Repeat = (getInt() == 1);

				// TODO 未知字符
				buffer.position(AnimationOffset + (id * 120) + 112);

				animSet.UnkChar = buffer.getChar();

				/**
				 * 对应动画的索引号
				 */
				buffer.position(AnimationOffset + (id * 120) + 116);

				int animIndex = getInt();
				if (animIndex > 0) {
					animIndex--;
				}
				animSet.SubAnimationIndex = animIndex;

				animation.mAnimationSetMap.put(id, animSet);
			}
		}

		return animation;
	}

	/**
	 * 解析动画索引
	 * 
	 * @return
	 */
	private DrzAnimation readAnimFromNew() {
		DrzAnimation animation = new DrzAnimation();

		// Read The Mesh Def
		readMeshDef();

		buffer.position(88460);
		int AnimationCount = getShort() - 10;

		int AnimationOffset = 2116;

		int SubAnimationNum = 0;
		for (int i = 0; i < AnimationCount; i++) {
			buffer.position(AnimationOffset + (i * 172) + 168);
			int temp_max_sub_ani = getUnsignedInt();
			if (temp_max_sub_ani > SubAnimationNum) {
				SubAnimationNum = temp_max_sub_ani;
			}
		}

		// 解析动画索引
		if (SubAnimationNum > 0) {
			animation.mSubAnimationNum = SubAnimationNum;

			for (int i = 0; i < AnimationCount; i++) {
				buffer.position(AnimationOffset + (i * 172));
				int AnimationId = getInt();

				if (AnimationId < 1) // no more Animations
					break;

				DrzAnimationSet CurrentAnimationSet = new DrzAnimationSet();

				// Set AnimationSetID
				CurrentAnimationSet.AnimationTypeId = AnimationId;

				int[] val = new int[2];

				buffer.position(AnimationOffset + (i * 172) + 4);// current
																	// animation
																	// starts at
																	// this
																	// frame
				val[0] = buffer.get() & 0xFF;
				buffer.position(AnimationOffset + (i * 172) + 6);
				val[1] = buffer.get() & 0xFF;

				CurrentAnimationSet.AnimationStartKey = 160 * ((val[1] << 8) + val[0]);

				buffer.position(AnimationOffset + (i * 172) + 16);// current
																	// animation
																	// end at
																	// this
																	// frame
				val[0] = buffer.get() & 0xFF;
				buffer.position(AnimationOffset + (i * 172) + 18);
				val[1] = buffer.get() & 0xFF;
				CurrentAnimationSet.AnimationEndKey1 = 160 * ((val[1] << 8) + val[0]);

				buffer.position(AnimationOffset + (i * 172) + 24);// secound end
																	// key,
																	// downt
																	// know why
				val[0] = buffer.get() & 0xFF;
				buffer.position(AnimationOffset + (i * 172) + 26);
				val[1] = buffer.get() & 0xFF;
				CurrentAnimationSet.AnimationEndKey2 = 160 * ((val[1] << 8) + val[0]);

				CurrentAnimationSet.Repeat = false;
				buffer.position(AnimationOffset + (i * 172) + 160);
				int iRepeat = getInt();
				if (iRepeat == 1) {
					CurrentAnimationSet.Repeat = true;
				}

				buffer.position(AnimationOffset + (i * 172) + 164);
				CurrentAnimationSet.UnkChar = buffer.getChar();

				buffer.position(AnimationOffset + (i * 172) + 168);
				CurrentAnimationSet.SubAnimationIndex = getInt();
				if (CurrentAnimationSet.SubAnimationIndex > 0) {
					CurrentAnimationSet.SubAnimationIndex--;
				}

				// Add AnimationSet
				CurrentAnimationSet.AnimationIndex = i;
				animation.mAnimationSetMap.put(i, CurrentAnimationSet);
			}
		}

		return animation;
	}

	class DrzInxMeshInfo {
		int type = -1;
		String meshName1;
		String meshName2;
		String meshName3;
		String meshName4;
	}

	/**
	 * 读取网格定义
	 */
	private void readMeshDef() {
		int MeshDefOffset = 192;

		for (int i = 0; i < 28; i++) {
			buffer.position(MeshDefOffset + i * 68);
			int MeshDefNum = getInt();

			if (MeshDefNum > 0) {
				DrzInxMeshInfo subMesh = new DrzInxMeshInfo();
				subMesh.type = 1;
				buffer.position(MeshDefOffset + i * 68 + 4);
				subMesh.meshName1 = getString();
				buffer.position(MeshDefOffset + i * 68 + 20);
				subMesh.meshName2 = getString();
				buffer.position(MeshDefOffset + i * 68 + 36);
				subMesh.meshName3 = getString();
				buffer.position(MeshDefOffset + i * 68 + 52);
				subMesh.meshName4 = getString();

			}
		}
	}

	private String getAnimationSetNameById(int id) {
		String ret = "unknown";

		switch (id) {
		case 64:
			ret = "Idle";
			break;
		case 80:
			ret = "Walk";
			break;
		case 96:
			ret = "Run";
			break;
		case 128:
			ret = "Fall";
			break;
		case 256:
			ret = "Attack";
			break;
		case 272:
			ret = "Damage";
			break;
		case 288:
			ret = "Die";
			break;
		case 304:
			ret = "Sometimes";
			break;
		case 320:
			ret = "Potion";
			break;
		case 336:
			ret = "Technique";
			break;
		case 368:
			ret = "Landing (small)";
			break;
		case 384:
			ret = "Landing (large)";
			break;
		case 512:
			ret = "Standup";
			break;
		case 528:
			ret = "Cry";
			break;
		case 544:
			ret = "Hurray";
			break;
		case 576:
			ret = "Jump";
			break;
		}

		return ret;
	}

	/*******************************************************
	 * 下面的代码用于根据精灵的数据结构创建JME3的纹理、材质、网格等对象
	 *******************************************************/

	/**
	 * 改变文件的后缀名
	 * 
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
			texture = manager
					.loadTexture(new TextureKey(key.getFolder() + name));
			texture.setWrap(WrapMode.Repeat);
		} catch (Exception ex) {
			// log.warn("材质加载失败:" + ex.getMessage());
			texture = manager.loadTexture("Common/Textures/MissingTexture.png");
			texture.setWrap(WrapMode.EdgeClamp);
		}
		return texture;
	}

	/**
	 * 创建材质
	 * 
	 * @param m
	 * @return
	 */
	private Material createLightMaterial(MATERIAL m) {
		Material mat = new Material(manager,
				"Common/MatDefs/Light/Lighting.j3md");
		mat.setColor("Diffuse", new ColorRGBA(m.Diffuse.r, m.Diffuse.g,
				m.Diffuse.b, 1));
		mat.setColor("Ambient", new ColorRGBA(1f, 1f, 1f, 1f));
		mat.setColor("Specular", new ColorRGBA(0, 0, 0, 1));
		// mat.setBoolean("UseMaterialColors", true);

		// 设置贴图
		if (m.TextureCounter > 0) {
			mat.setTexture("DiffuseMap", createTexture(m.smTexture[0].Name));
		}
		if (m.TextureCounter > 1) {
			mat.setBoolean("SeparateTexCoord", true);
			mat.setTexture("LightMap", createTexture(m.smTexture[1].Name));
		}

		return mat;
	}

	/**
	 * 创建一个忽略光源的材质。 动画专用
	 * 
	 * @param m
	 * @return
	 */
	private Material createMiscMaterial(MATERIAL m) {
		Material mat = new Material(manager,
				"Common/MatDefs/Misc/Unshaded.j3md");
		// mat.setColor("Color", new ColorRGBA(m.Diffuse.r, m.Diffuse.g,
		// m.Diffuse.b, 1));
		mat.setColor("Color", ColorRGBA.White);

		// 设置贴图
		if (m.TextureCounter > 0) {
			mat.setTexture("ColorMap", createTexture(m.smTexture[0].Name));
		}
		if (m.TextureCounter > 1) {
			mat.setBoolean("SeparateTexCoord", true);
			mat.setTexture("LightMap", createTexture(m.smTexture[1].Name));
		}

		return mat;
	}

	/**
	 * 设置材质的RenderState
	 * 
	 * @param m
	 * @param mat
	 */
	private void setRenderState(MATERIAL m, Material mat) {
		RenderState rs = mat.getAdditionalRenderState();

		/**
		 * #define SMMAT_BLEND_NONE 0x00 #define SMMAT_BLEND_ALPHA 0x01 #define
		 * SMMAT_BLEND_COLOR 0x02 #define SMMAT_BLEND_SHADOW 0x03 #define
		 * SMMAT_BLEND_LAMP 0x04 #define SMMAT_BLEND_ADDCOLOR 0x05 #define
		 * SMMAT_BLEND_INVSHADOW 0x06
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
		}
		;

		if (m.TwoSide == 1) {
			rs.setFaceCullMode(FaceCullMode.Off);
		}

		if (m.TextureType == 0x0001) {
			// 动画默认显示2面
			rs.setFaceCullMode(FaceCullMode.Off);
		}

		// 透明物体
		if (m.MapOpacity != 0 || m.Transparency != 0) {
			// 这个值设置得稍微大一些，这样草、花等图片的边缘就会因为透明度不够而过滤掉像素。
			mat.setFloat("AlphaDiscardThreshold", 0.75f);
			// 虽然已经过时，但是还是写上以防不测。
			// rs.setAlphaTest(true);
			// rs.setAlphaFallOff(0.6f);
			rs.setDepthWrite(true);
			rs.setDepthTest(true);
			rs.setColorWrite(true);

			// 透明物体不裁剪面
			rs.setFaceCullMode(FaceCullMode.Off);
		}
	}

	/**
	 * AminTexCounter大于0说明有轮播动画，创建一个Control，定时更新画面。
	 * 
	 * @param m
	 * @return
	 */
	private FrameAnimControl createFrameAnimControl(MATERIAL m) {
		Texture[] tex = new Texture[m.AnimTexCounter];
		for (int i = 0; i < m.AnimTexCounter; i++) {
			tex[i] = createTexture(m.smAnimTexture[i].Name);
		}
		FrameAnimControl control = new FrameAnimControl(tex, m.AnimTexCounter, m.Shift_FrameSpeed);
		return control;
	}


}
