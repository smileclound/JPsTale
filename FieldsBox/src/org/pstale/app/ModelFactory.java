package org.pstale.app;

import static org.pstale.asset.loader.SMDTYPE.MODELINFO_MODEL;
import static org.pstale.asset.loader.SMDTYPE.PAT3D_BIP;
import static org.pstale.asset.loader.SMDTYPE.PAT3D_VISUAL;
import static org.pstale.asset.loader.SMDTYPE.STAGE3D_COLLISION;
import static org.pstale.asset.loader.SMDTYPE.STAGE3D_VISUAL;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.pstale.asset.loader.CharInfoLoader;
import org.pstale.asset.loader.FileLocator;
import org.pstale.asset.loader.ItemLoader;
import org.pstale.asset.loader.SmdKey;
import org.pstale.asset.loader.SmdLoader;
import org.pstale.asset.loader.SpcLoader;
import org.pstale.asset.loader.SpmLoader;
import org.pstale.asset.loader.SppLoader;
import org.pstale.asset.struct.chars.CharMonsterInfo;
import org.pstale.asset.struct.chars.TRNAS_PLAYERINFO;
import org.pstale.asset.struct.item.ItemInfo;
import org.pstale.fields.RespawnList;
import org.pstale.fields.StartPoint;

import com.jme3.asset.AssetManager;
import com.jme3.audio.plugins.WAVLoader;
import com.jme3.material.Material;
import com.jme3.material.RenderState.FaceCullMode;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.Node;
import com.jme3.scene.SceneGraphVisitor;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Quad;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture.WrapMode;

/**
 * 模型工厂
 * @author yanmaoyuan
 *
 */
public class ModelFactory {

	static Logger log = Logger.getLogger(ModelFactory.class);
	
	static AssetManager assetManager;
	
	public static void setAssetManager(final AssetManager manager) {
		assetManager = manager;
		assetManager.registerLoader(SmdLoader.class, "smd", "smb", "inx");
		assetManager.registerLoader(WAVLoader.class, "bgm");
		assetManager.registerLoader(SpcLoader.class, "spc");
		assetManager.registerLoader(SpmLoader.class, "spm");
		assetManager.registerLoader(SppLoader.class, "spp");
		assetManager.registerLoader(CharInfoLoader.class, "inf", "npc");
		assetManager.registerLoader(ItemLoader.class, "txt");
		assetManager.registerLocator("/", FileLocator.class);
		assetManager.registerLocator("assets", FileLocator.class);
	}
	
	/**
	 * 获得一个刷怪点标记
	 * @return
	 */
	public static Spatial loadFlag() {
		Spatial flag;
		try {
			Node wow = ModelFactory.loadStageObj("char/flag/wow.smd", false);
			wow.depthFirstTraversal(new SceneGraphVisitor() {
				@Override
				public void visit(Spatial spatial) {
					if (spatial instanceof Geometry) {
						Geometry geom = (Geometry)spatial;
						geom.getMaterial().getAdditionalRenderState().setFaceCullMode(FaceCullMode.Off);
					}
				}
			});
			
			flag = wow;
		} catch (Exception e) {
			log.debug("无法加载旗帜", e);
			flag = new Geometry("flag", new Box(1/SubAppState.scale, 1/SubAppState.scale, 1/SubAppState.scale));	
			Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
			mat.setColor("Color", ColorRGBA.Red);
			flag.setMaterial(mat);
		}
		
		return flag;
	}
	
	/**
	 * 创建一个Loading标记
	 * @return
	 */
	public static Spatial getLoadingFlag() {
		Quad quad = new Quad(80, 20);
		Geometry geom = new Geometry("loading", quad);
		Material mat = new Material(assetManager, "Shader/Misc/Scroll.j3md");
		Texture tex = assetManager.loadTexture("Interface/loading.png");
		tex.setWrap(WrapMode.Repeat);
		mat.setTexture("ColorMap", tex);
		mat.setFloat("Speed", 2);
		mat.setColor("Color", ColorRGBA.Magenta);
		geom.setMaterial(mat);

		return geom;
	}
	/**
	 * 加载地图
	 * @param name
	 * @return
	 * @throws Exception 
	 */
	public static Node loadStage3D(final String name) {
		String smd = changeName(name, "smd");
		return (Node)assetManager.loadAsset(new SmdKey(smd, STAGE3D_VISUAL));
	}
	
	public static Mesh loadStage3DMesh(final String name) {
		String smd = changeName(name, "smd");
		return (Mesh)assetManager.loadAsset(new SmdKey(smd, STAGE3D_COLLISION));
	}
	
	public static Node loadStageObj(final String name, final boolean bip) {
		String smd = changeName(name, "smd");
		return (Node)assetManager.loadAsset(new SmdKey(smd, bip?PAT3D_BIP:PAT3D_VISUAL));
	}
	
	public static Node loadNPC(final String name) {
		String inx = changeName(name, "inx");
		return (Node)assetManager.loadAsset(new SmdKey(inx, MODELINFO_MODEL));
	}
	
	public static ArrayList<StartPoint> loadSpp(final String name) {
		String path = String.format("GameServer/Field/%s.ase.spp", getSimpleName(name));
		try {
			ArrayList<StartPoint> spp = (ArrayList<StartPoint>)assetManager.loadAsset(path);
			return spp;
		} catch (Exception e) {
			return null;
		}
	}
	
	public static RespawnList loadSpm(final String name) {
		String path = String.format("GameServer/Field/%s.ase.spm", getSimpleName(name));
		try {
			RespawnList creatures = (RespawnList)assetManager.loadAsset(path);
			return creatures;
		} catch (Exception e) {
			return null;
		}
	}
	
	public static ArrayList<TRNAS_PLAYERINFO> loadSpc(final String name) {
		String path = String.format("GameServer/Field/%s.ase.spc", getSimpleName(name));
		try {
			ArrayList<TRNAS_PLAYERINFO> npcs = (ArrayList<TRNAS_PLAYERINFO>)assetManager.loadAsset(path);
			return npcs;
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 导入NPC的脚本文件
	 * @param name
	 * @return
	 */
	public static CharMonsterInfo loadNpcScript(final String name) {
		String path = String.format("GameServer/NPC/%s.npc", getSimpleName(name));
		
		try {
			CharMonsterInfo info = (CharMonsterInfo)assetManager.loadAsset(path);
			return info;
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 导入怪物的脚本文件
	 * @param name
	 * @return
	 */
	public static CharMonsterInfo loadMonsterScript(final String name) {
		String path = String.format("GameServer/Monster/%s.inf", getSimpleName(name));
		
		try {
			CharMonsterInfo info = (CharMonsterInfo)assetManager.loadAsset(path);
			return info;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 导入装备的脚本
	 * @param name
	 * @return
	 */
	public static ItemInfo loadItemScript(String name) {
		String path = String.format("GameServer/OpenItem/%s.txt", getSimpleName(name));
		
		try {
			ItemInfo info = (ItemInfo)assetManager.loadAsset(path);
			return info;
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 将文件名掐头去尾，只留下名字。
	 * “Field/forest/fore-3.ASE”会变成"fore-3"
	 * @param name
	 * @return
	 */
	public static String getSimpleName(final String orgin) {
		if (orgin == null)
			return null;
		
		// 替换可能存在的window文件夹符号
		String path = orgin.replaceAll("\\\\", "/");
		
		// 掐头
		int idx = path.lastIndexOf("/");
		if (idx != -1) {
			path = path.substring(idx+1);
		}

		// 去尾
		idx = path.indexOf(".");
		if (idx != -1) {
			path = path.substring(0, idx);
		}
		
		return path;
	}
	/**
	 * 改变文件名后缀。
	 * @param orgin
	 * @param ext
	 * @return
	 */
	public static String changeName(final String orgin, final String ext) {
		String path = orgin;
		if (path != null) {
			path = path.replaceAll("\\\\", "/");
		}
		int idx = path.lastIndexOf(".") + 1;
		String dest = path.substring(0, idx) + ext;
		
		return dest;
	}

}
