package org.pstale.asset.loader;

import java.io.IOException;
import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.pstale.asset.control.WindAnimationControl;
import org.pstale.asset.struct.*;
import org.pstale.asset.struct.chars.MODELINFO;

import com.jme3.animation.AnimControl;
import com.jme3.animation.Animation;
import com.jme3.animation.Bone;
import com.jme3.animation.BoneTrack;
import com.jme3.animation.Skeleton;
import com.jme3.animation.SkeletonControl;
import com.jme3.asset.AssetInfo;
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
import com.jme3.util.LittleEndien;
import com.jme3.util.TempVars;

/**
 * 精灵场景加载器
 * 
 * @author yanmaoyuan
 * 
 */
public class SmdLoader implements AssetLoader {

	static Logger log = Logger.getLogger(SmdLoader.class);
	/**
	 * 精灵的动画使用3DS MAX的默认速率，每秒30Tick，每Tick共160帧。 也就是每秒4800帧。
	 * 
	 * 但是smd文件中也另外存储了2个参数： (1) 每秒Tick数 (默认30) (2) 每Tick帧数 (默认160)
	 * 这两个变量的值控制了动画播放的速率。
	 */
	float framePerSecond = 4800f;
	
	public static boolean USE_LIGHT = false;

	// 是否使用OPENGL坐标系
	public static boolean OPEN_GL_AXIS = true;
	// 是否打印动画日志
	public static boolean LOG_ANIMATION = false;

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

	private AssetManager manager = null;
	private SmdKey key = null;

	@Override
	public Object load(AssetInfo assetInfo) throws IOException {
		// 确认用户使用了SmdKey
		if (!(assetInfo.getKey() instanceof SmdKey)) {
			log.warn("用户未使用SmdKey来加载模型:" + key.getName());
			throw new RuntimeException("请使用SmdKey来加载精灵的smd模型。");
		}
		
		this.key = (SmdKey)assetInfo.getKey();
		manager = assetInfo.getManager();

		/**
		 * 若用户使用了SmdKey，就根据type来决定采用哪种方式来加载模型。
		 */
		switch (key.type) {
		case STAGE3D: {// 直接返回STAGE3D对象
			STAGE3D stage3D = new STAGE3D();
			stage3D.loadFile(new LittleEndien(assetInfo.openStream()));
			return stage3D;
		}
		case STAGE3D_VISUAL: {// 返回STAGE3D的可视部分
			STAGE3D stage3D = new STAGE3D();
			stage3D.loadFile(new LittleEndien(assetInfo.openStream()));
			return buildNode(stage3D);
		}
		case STAGE3D_COLLISION: {// 返回STAGE3D中参加碰撞检测的网格
			STAGE3D stage3D = new STAGE3D();
			stage3D.loadFile(new LittleEndien(assetInfo.openStream()));
			return buildCollisionMesh(stage3D);
		}
		case PAT3D: {// 直接返回PAT3D对象
			PAT3D bone = new PAT3D();
			bone.loadFile(new LittleEndien(assetInfo.openStream()), null, null);
			return bone;
		}
		case PAT3D_BIP: {// 有动画的舞台物体
			// 后缀名改为smb
			String smbFile = changeExt(key.getName(), "smb");
			PAT3D bone = (PAT3D) manager.loadAsset(new SmdKey(smbFile, SMDTYPE.PAT3D));

			// 再加载smd文件
			key = (SmdKey)assetInfo.getKey();
			LittleEndien in = new LittleEndien(assetInfo.openStream());
			PAT3D pat = new PAT3D();
			pat.loadFile(in, null, bone);
			return buildNode(pat);
		}
		case PAT3D_VISUAL: {// 舞台物体，无动画
			LittleEndien in = new LittleEndien(assetInfo.openStream());
			PAT3D pat = new PAT3D();
			pat.loadFile(in, null, key.getBone());
			return buildNode(pat);
		}
		case MODELINFO: {
			LittleEndien in = new LittleEndien(assetInfo.openStream());
			MODELINFO modelInfo = new MODELINFO();
			modelInfo.loadData(in);
			return modelInfo;
		}
		case MODELINFO_ANIMATION: {
			LittleEndien in = new LittleEndien(assetInfo.openStream());
			MODELINFO modelInfo = new MODELINFO();
			modelInfo.loadData(in);
			
			// 有共享数据?
			String linkFile = modelInfo.linkFile;
			if (linkFile.length() > 0) {
				SmdKey linkFileKey = new SmdKey(linkFile, SMDTYPE.MODELINFO);
				MODELINFO mi = (MODELINFO)manager.loadAsset(linkFileKey);
				modelInfo.animationFile = mi.animationFile;
			}
			
			PAT3D BipPattern = null;
			// 读取动画
			if (modelInfo.animationFile.length() > 0) {
				// 后缀名改为smb
				String smbFile = changeExt(modelInfo.animationFile, "smb");
				smbFile = changeName(smbFile);
				BipPattern = (PAT3D) manager.loadAsset(new SmdKey(key.getFolder() + smbFile, SMDTYPE.PAT3D));
				
				// 生成动画
				Skeleton ske = buildSkeleton(BipPattern);
				Animation anim = buildAnimation(BipPattern, ske);
				AnimControl ac = new AnimControl(ske);
				ac.addAnim(anim);
				return ac;
			} else {
				return null;
			}
		}
		case MODELINFO_MODEL: {
			LittleEndien in = new LittleEndien(assetInfo.openStream());
			MODELINFO modelInfo = new MODELINFO();
			modelInfo.loadData(in);
			
			// 有共享数据?
			String linkFile = modelInfo.linkFile;
			if (linkFile.length() > 0) {
				SmdKey linkFileKey = new SmdKey(linkFile, SMDTYPE.MODELINFO);
				MODELINFO mi = (MODELINFO)manager.loadAsset(linkFileKey);
				modelInfo.animationFile = mi.animationFile;
			}
			
			PAT3D BipPattern = null;
			// 读取动画
			if (modelInfo.animationFile.length() > 0) {
				// 后缀名改为smb
				String smbFile = changeExt(modelInfo.animationFile, "smb");
				smbFile = changeName(smbFile);
				BipPattern = (PAT3D) manager.loadAsset(new SmdKey(key.getFolder() + smbFile, SMDTYPE.PAT3D));
			}

			// 读取网格
			String smdFile = changeExt(modelInfo.modelFile, "smd");
			smdFile = changeName(smdFile);

			SmdKey smdKey = new SmdKey(key.getFolder() + smdFile, SMDTYPE.PAT3D_VISUAL);
			smdKey.setBone(BipPattern);
			return manager.loadAsset(smdKey);
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
	 * 改变文件名后缀。
	 * @param orgin
	 * @param ext
	 * @return
	 */
	public static String changeExt(final String orgin, final String ext) {
		String path = orgin;
		path = path.replaceAll("\\\\", "/");
		
		int idx = path.lastIndexOf(".") + 1;
		String dest = path.substring(0, idx) + ext;
		
		return dest;
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
			TextureKey texKey = new TextureKey(key.getFolder() + name);
			texture = manager.loadTexture(texKey);
			texture.setWrap(WrapMode.Repeat);
		} catch (Exception ex) {
			texture = manager.loadTexture("Common/Textures/MissingTexture.png");
			texture.setWrap(WrapMode.EdgeClamp);
		}
		return texture;
	}

	/**
	 * 创建感光材质
	 * 
	 * @param m
	 * @return
	 */
	private Material createLightMaterial(MATERIAL m) {
		Material mat = new Material(manager, "Common/MatDefs/Light/Lighting.j3md");
		mat.setColor("Diffuse", new ColorRGBA(m.Diffuse.r, m.Diffuse.g, m.Diffuse.b, 1));
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
	 * 创建一个忽略光源的材质。 
	 * 
	 * @param m
	 * @return
	 */
	private Material createMiscMaterial(MATERIAL m) {
		Material mat = new Material(manager, "Common/MatDefs/Misc/Unshaded.j3md");
		// mat.setColor("Color", new ColorRGBA(m.Diffuse.r, m.Diffuse.g, m.Diffuse.b, 1));
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
	 * 创建一个匀速切换帧的材质。
	 * 
	 * @param m
	 * @return
	 */
	private Material createShiftMaterial(MATERIAL m) {
		Material mat = new Material(manager, "Shader/Misc/Shift.j3md");
		
		// 画面的切换时间间隔
		float ShiftSpeed = (1 << m.Shift_FrameSpeed) / 1000f;
		mat.setFloat("ShiftSpeed", ShiftSpeed);
		
		// 设置贴图
		Texture tex;
		for (int i = 0; i < m.AnimTexCounter; i++) {
			tex = createTexture(m.smAnimTexture[i].Name);
			mat.setTexture("Tex"+(i+1), tex);
		}
		
		return mat;
	}
	
	/**
	 * 创建一个卷轴动画材质。
	 * 
	 * @param m
	 * @return
	 */
	private Material createScrollMaterial(MATERIAL m) {
		Material mat = new Material(manager, "Shader/Misc/Scroll.j3md");
		
		// 画面的卷动速度
		float speed = 1f;
		
		int n = m.TextureFormState[0];
		if (n >= 6 && n <= 14) {
			speed = 15 - n;
		}
		
		if (n >= 15 && n <= 18) {
			int factor = 18 - n + 4;
			speed = (128 >> factor) / 256f;
		}
		
		mat.setFloat("Speed", speed);
		
		// 设置贴图
		Texture tex = createTexture(m.smTexture[0].Name);
		mat.setTexture("ColorMap", tex);
		
		if (m.TextureCounter > 1) {
			mat.setBoolean("SeparateTexCoord", true);
			mat.setTexture("LightMap", createTexture(m.smTexture[1].Name));
		}
		
		return mat;
	}
	
	/**
	 * 创建一个原地转圈的动画材质。 Water动画专用
	 * 
	 * @param m
	 * @return
	 */
	private Material createRoundMaterial(MATERIAL m) {
		Material mat = new Material(manager, "Shader/Misc/Round.j3md");
		
		// 设置贴图
		Texture tex = createTexture(m.smTexture[0].Name);
		mat.setTexture("ColorMap", tex);
		
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
			rs.setBlendMode(BlendMode.Additive);
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

	/****************************************
	 * OBJ3D
	 ****************************************/
	
	/**
	 * 生成网格数据。
	 * 
	 * @param ske
	 * @return
	 */
	private Mesh buildMesh(OBJ3D obj, int mat_id, Skeleton ske) {
		Mesh mesh = new Mesh();

		// 统计使用这个材质的面数
		int count = 0;
		for (int i = 0; i < obj.nFace; i++) {
			if (obj.Face[i].v[3] == mat_id) {
				count++;
			}
		}

		// 计算网格
		Vector3f[] position = new Vector3f[count * 3];
		int[] f = new int[count * 3];
		Vector2f[] uv = new Vector2f[count * 3];
		int index = 0;

		// Prepare MeshData
		for (int i = 0; i < obj.nFace; i++) {
			// 忽略掉这个面
			if (obj.Face[i].v[3] != mat_id) {
				continue;
			}

			// 顶点 VERTEX
			position[index * 3 + 0] = obj.Vertex[obj.Face[i].v[0]].v;
			position[index * 3 + 1] = obj.Vertex[obj.Face[i].v[1]].v;
			position[index * 3 + 2] = obj.Vertex[obj.Face[i].v[2]].v;

			// 面 FACE
			if (i < obj.nFace) {
				f[index * 3 + 0] = index * 3 + 0;
				f[index * 3 + 1] = index * 3 + 1;
				f[index * 3 + 2] = index * 3 + 2;
			}

			// 纹理映射
			TEXLINK tl = obj.Face[i].TexLink;
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
		if (obj.Physique != null && ske != null) {
			float[] boneIndex = new float[count * 12];
			float[] boneWeight = new float[count * 12];

			index = 0;
			for (int i = 0; i < obj.nFace; i++) {
				// 忽略这个面
				if (obj.Face[i].v[3] != mat_id) {
					continue;
				}

				for (int j = 0; j < 3; j++) {
					int v = obj.Face[i].v[j];// 顶点序号
					int bi = index * 3 + j;// 对应骨骼的序号

					OBJ3D obj3d = obj.Physique[v];
					byte targetBoneIndex = (byte) ske.getBoneIndex(obj3d.NodeName);

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
	private Vector3f mult(long res1, long res2, long res3, MATRIX tm) {
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

	private void invertPoint(OBJ3D obj) {

		for (int i = 0; i < obj.nVertex; i++) {
			if (obj.Physique != null) {
				obj.Vertex[i].v = mult(obj.Vertex[i].x, obj.Vertex[i].y, obj.Vertex[i].z,
						obj.Physique[i].TmInvert);
			} else {
				obj.Vertex[i].v = mult(obj.Vertex[i].x, obj.Vertex[i].y, obj.Vertex[i].z,
						obj.TmInvert);
			}
		}
	}
	
	
	/************************************************
	 * PAT3D
	 */
	
	/**
	 * 生成骨骼
	 */
	private Skeleton buildSkeleton(PAT3D pat) {

		HashMap<String, Bone> boneMap = new HashMap<String, Bone>();
		Bone[] bones = new Bone[pat.nObj3d];
		for (int i = 0; i < pat.nObj3d; i++) {
			OBJ3D obj = pat.obj3d[i];

			// 创建一个骨头
			Bone bone = new Bone(obj.NodeName);
			bones[i] = bone;

			// 设置初始POSE
			if (OPEN_GL_AXIS) {
				Vector3f translation = new Vector3f(-obj.py, obj.pz, -obj.px);
				Quaternion rotation = new Quaternion(-obj.qy, obj.qz, -obj.qx, -obj.qw);
				Vector3f scale = new Vector3f(obj.sy, obj.sz, obj.sx);

				bone.setBindTransforms(translation, rotation, scale);
			} else {
				Vector3f translation = new Vector3f(obj.px, obj.py, obj.pz);
				Quaternion rotation = new Quaternion(-obj.qx, -obj.qy, -obj.qz, obj.qw);
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
	 * 生成骨骼动画
	 * FIXME
	 * 
	 * @param ske
	 */
	private Animation buildAnimation(PAT3D pat, Skeleton ske) {

		// 统计帧数
		int maxFrame = 0;
		for (int i = 0; i < pat.nObj3d; i++) {
			OBJ3D obj = pat.obj3d[i];
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
				log.debug("TmPos:" + obj.TmPosCnt + " TmRot:" + obj.TmRotCnt + " TmScl:" + obj.TmScaleCnt);
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
		for (int i = 0; i < pat.nObj3d; i++) {
			OBJ3D obj = pat.obj3d[i];

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
					k.rotation = new Quaternion(-rot.y, rot.z, -rot.x, -rot.w);
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

	private Node buildNode(PAT3D pat) {
		Node rootNode = new Node("PAT3D:" + key.getName());

		Skeleton ske = null;
		// 生成骨骼
		if (pat.TmParent != null) {
			ske = buildSkeleton(pat.TmParent);
		}

		for (int i = 0; i < pat.nObj3d; i++) {
			OBJ3D obj = pat.obj3d[i];
			if (obj.nFace > 0) {

				// 对所有顶点进行线性变换，否则顶点的坐标都在原点附近。
				invertPoint(obj);

				// 根据模型的材质不同，将创建多个网格，分别渲染。
				for (int mat_id = 0; mat_id < pat.smMaterialGroup.materialCount; mat_id++) {
					// 生成网格
					Mesh mesh = buildMesh(obj, mat_id, ske);

					// 创建材质
					MATERIAL m = pat.smMaterialGroup.materials[mat_id];
					Material mat;
					if (USE_LIGHT) {
						mat = createLightMaterial(m);
					} else {
						mat = createMiscMaterial(m);
					}

					// 创建几何体并应用材质。
					Geometry geom = new Geometry(pat.obj3d[i].NodeName + "#" + mat_id, mesh);
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
			Animation anim = buildAnimation(pat.TmParent, ske);
			AnimControl ac = new AnimControl(ske);
			ac.addAnim(anim);
			rootNode.addControl(ac);
			rootNode.addControl(new SkeletonControl(ske));
		}

		return rootNode;
	}
	
	/**********************************
	 * STAGE3D
	 */
	
	/**
	 * 生成STAGE3D对象
	 * 
	 * @return
	 */
	private Node buildNode(STAGE3D stage) {
		Node rootNode = new Node("STAGE3D:" + key.getName());

		Vector3f[] orginNormal = null;
		if (USE_LIGHT) {
			// 为了让表面平滑光照，先基于原来的面和顶点计算一次法向量。
			orginNormal = computeOrginNormals(stage);
		}

		int materialCount = stage.materialGroup.materialCount;

		// 创建材质
		for (int mat_id = 0; mat_id < materialCount; mat_id++) {
			MATERIAL m = stage.materials[mat_id];

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
			for (int i = 0; i < stage.nFace; i++) {
				if (stage.Face[i].v[3] != mat_id)
					continue;
				size++;
			}
			// 没有面使用这个材质，跳过。
			if (size < 1) {
				continue;
			}

			// 计算网格
			Mesh mesh = buildMesh(stage, size, mat_id, orginNormal);
			Geometry geom = new Geometry(key.getName() + "#" + mat_id, mesh);

			// 创建材质
			Material mat;
			
			// 有多个动画
			if (m.TextureType == 0) {
				// SMTEX_TYPE_MULTIMIX
				int n = m.TextureFormState[0];
				if(n >= 4) {// 4 SCROLL 滚轴 5 REFLEX 反光 6 SCROLL2 2倍速滚轴
					mat = createScrollMaterial(m);
				} else {
					if (USE_LIGHT) {
						mat = createLightMaterial(m);
					} else {
						mat = createMiscMaterial(m);
					}
				}
			} else {// SMTEX_TYPE_ANIMATION
				if (m.AnimTexCounter > 0) {
					// AminTexCounter大于0说明有轮播动画，创建一个Control，定时更新画面。
					mat = createShiftMaterial(m);
				} else {
					mat = createMiscMaterial(m);
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
					mat = createRoundMaterial(m);
					break;
				}
				}
			}
			
			setRenderState(m, mat);

			// 应用材质
			geom.setMaterial(mat);
			
			geom.setUserData("MeshState", m.MeshState);
			geom.setUserData("UseState", m.UseState);
			geom.setUserData("BlendType", m.BlendType);
			geom.setUserData("MapOpacity", m.MapOpacity);
			geom.setUserData("Transparency", m.Transparency);

			rootNode.attachChild(geom);

			// 透明度
			// 只有不透明物体才需要检测碰撞网格。
			if (m.MapOpacity != 0 || m.Transparency != 0 || m.BlendType == 1 || m.BlendType == 4) {
				geom.setQueueBucket(Bucket.Translucent);
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

		if (stage.nLight > 0) {
			// TODO 处理灯光
		}

		return rootNode;
	}
	
	/**
	 * 根据原有的面，计算每个顶点的法向量。
	 * 
	 * @return
	 */
	private Vector3f[] computeOrginNormals(STAGE3D stage) {
		TempVars tmp = TempVars.get();

		Vector3f A;// 三角形的第1个点
		Vector3f B;// 三角形的第2个点
		Vector3f C;// 三角形的第3个点

		Vector3f vAB = tmp.vect1;
		Vector3f vAC = tmp.vect2;
		Vector3f n = tmp.vect4;

		// Here we allocate all the memory we need to calculate the normals
		Vector3f[] tempNormals = new Vector3f[stage.nFace];
		Vector3f[] normals = new Vector3f[stage.nVertex];

		for (int i = 0; i < stage.nFace; i++) {
			A = stage.Vertex[stage.Face[i].v[0]].v;
			B = stage.Vertex[stage.Face[i].v[1]].v;
			C = stage.Vertex[stage.Face[i].v[2]].v;

			vAB = B.subtract(A, vAB);
			vAC = C.subtract(A, vAC);
			n = vAB.cross(vAC, n);

			tempNormals[i] = n.normalize();
		}

		Vector3f sum = tmp.vect4;
		int shared = 0;

		for (int i = 0; i < stage.nVertex; i++) {
			// 统计每个点被那些面共用。
			for (int j = 0; j < stage.nFace; j++) {
				if (stage.Face[j].v[0] == i || stage.Face[j].v[1] == i
						|| stage.Face[j].v[2] == i) {
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
	
	/**
	 * 由于网格中不同的面所应用的材质不同，需要根据材质来对网格进行分组，将相同材质的面单独取出来，做成一个独立的网格。
	 * @param stage STAGE3D对象
	 * @param size 面数
	 * @param mat_id 材质编号
	 * @param orginNormal 法线
	 * @return
	 */
	private Mesh buildMesh(STAGE3D stage, int size, int mat_id, Vector3f[] orginNormal) {

		Vector3f[] position = new Vector3f[size * 3];
		int[] f = new int[size * 3];
		Vector3f[] normal = new Vector3f[size * 3];
		Vector2f[] uv1 = new Vector2f[size * 3];
		Vector2f[] uv2 = new Vector2f[size * 3];

		int index = 0;
		// Prepare MeshData
		for (int i = 0; i < stage.nFace; i++) {
			// Check the MaterialIndex
			if (stage.Face[i].v[3] != mat_id)
				continue;

			// 顺序处理3个顶点
			for (int vIndex = 0; vIndex < 3; vIndex++) {
				// 顶点 VERTEX
				position[index * 3 + vIndex] = stage.Vertex[stage.Face[i].v[vIndex]].v;
				
				if (USE_LIGHT) {
					// 法向量 Normal
					normal[index * 3 + vIndex] = orginNormal[stage.Face[i].v[vIndex]];
				}

				// 面 FACE
				f[index * 3 + vIndex] = index * 3 + vIndex;

				// 纹理映射
				TEXLINK tl = stage.Face[i].TexLink;
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
	
	/**
	 * 生成碰撞网格：将透明的、不参与碰撞检测的面统统裁剪掉，只保留参于碰撞检测的面。
	 * 
	 * @return
	 */
	private Mesh buildCollisionMesh(STAGE3D stage) {
		Mesh mesh = new Mesh();

		int materialCount = stage.materialGroup.materialCount;
		/**
		 * 根据材质的特诊来筛选参加碰撞检测的物体， 将被忽略的材质设置成null，作为一种标记。
		 */
		MATERIAL m;// 临时变量
		for (int mat_id = 0; mat_id < materialCount; mat_id++) {
			m = stage.materials[mat_id];

			if ((m.MeshState & 0x0001) == 0) {
				stage.materials[mat_id] = null;
			}
		}

		/**
		 * 统计有多少个要参加碰撞检测的面。
		 */
		int loc[] = new int[stage.nVertex];
		for (int i = 0; i < stage.nVertex; i++) {
			loc[i] = -1;
		}

		int fSize = 0;
		for (int i = 0; i < stage.nFace; i++) {
			STAGE_FACE face = stage.Face[i];
			if (stage.materials[face.v[3]] != null) {
				loc[face.v[0]] = face.v[0];
				loc[face.v[1]] = face.v[1];
				loc[face.v[2]] = face.v[2];

				fSize++;
			}
		}

		int vSize = 0;
		for (int i = 0; i < stage.nVertex; i++) {
			if (loc[i] > -1) {
				vSize++;
			}
		}

		// 记录新的顶点编号
		Vector3f[] v = new Vector3f[vSize];
		vSize = 0;
		for (int i = 0; i < stage.nVertex; i++) {
			if (loc[i] > -1) {
				v[vSize] = stage.Vertex[i].v;
				loc[i] = vSize;
				vSize++;
			}
		}

		// 记录新的顶点索引号
		int[] f = new int[fSize * 3];
		fSize = 0;
		for (int i = 0; i < stage.nFace; i++) {
			STAGE_FACE face = stage.Face[i];
			if (stage.materials[face.v[3]] != null) {
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
}
