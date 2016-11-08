package org.pstale.asset.loader;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.pstale.asset.struct.MATERIAL;
import org.pstale.asset.struct.PAT3D;
import org.pstale.asset.struct.STAGE3D;

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
import com.jme3.texture.Texture;
import com.jme3.texture.Texture.WrapMode;
import com.jme3.util.LittleEndien;

/**
 * 精灵场景加载器
 * 
 * @author yanmaoyuan
 * 
 */
public class SmdLoader implements AssetLoader {

	static Logger log = Logger.getLogger(SmdLoader.class);

	public static boolean USE_LIGHT = false;

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
		
		log.debug("模型文件:" + key.getName());
		
		switch (type) {
		case STAGE3D: {// 主地图
			LittleEndien in = new LittleEndien(assetInfo.openStream());
			STAGE3D stage3D = new STAGE3D();
			stage3D.loadFile(in);
			return stage3D.buildNode(this);
		}
		case STAGE3D_SOLID: {// 地图网格
			LittleEndien in = new LittleEndien(assetInfo.openStream());
			STAGE3D stage3D = new STAGE3D();
			stage3D.loadFile(in);
			return stage3D.buildSolidMesh();
		}
		case BONE: {
			LittleEndien in = new LittleEndien(assetInfo.openStream());
			PAT3D bone = new PAT3D();
			bone.loadFile(in, null, null);
			return bone;
		}
		case PAT3D_BIP: {// 有动画的舞台物体
			// 后缀名改为smb
			String smbFile = key.getName();
			int n = smbFile.lastIndexOf(".");
			String str = smbFile.substring(0, n);
			smbFile = str + ".smb";
			PAT3D bone = (PAT3D) manager.loadAsset(new SmdKey(smbFile, SMDTYPE.BONE));

			// 再加载smd文件
			key = assetInfo.getKey();
			LittleEndien in = new LittleEndien(assetInfo.openStream());
			PAT3D pat = new PAT3D();
			pat.loadFile(in, null, bone);
			return pat.buildNode(this);
		}
		case PAT3D: {// 舞台物体，无动画
			LittleEndien in = new LittleEndien(assetInfo.openStream());
			PAT3D pat = new PAT3D();
			pat.loadFile(in, null, smdkey.getBone());
			return pat.buildNode(this);
		}
		case INX: {
			log.info("not supported yet");
			return null;
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
	 * 创建纹理
	 * 
	 * @param name
	 */
	private Texture createTexture(String name) {
		name = changeName(name);

		// TODO 将texture.Name存入缓存中，避免多次加载。

		Texture texture = null;
		try {
			texture = manager.loadTexture(new TextureKey(key.getFolder() + name));
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
	public Material createLightMaterial(MATERIAL m) {
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
	public Material createMiscMaterial(MATERIAL m) {
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
	 * 创建一个匀速切换帧的材质。 动画专用
	 * 
	 * @param m
	 * @return
	 */
	public Material createShiftMaterial(MATERIAL m) {
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
	 * 创建一个卷轴动画材质。 动画专用
	 * 
	 * @param m
	 * @return
	 */
	public Material createScrollMaterial(MATERIAL m) {
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
	public Material createRoundMaterial(MATERIAL m) {
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
	public void setRenderState(MATERIAL m, Material mat) {
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

}
