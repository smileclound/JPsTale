package org.pstale.app;

import static org.pstale.asset.loader.SMDTYPE.*;

import org.apache.log4j.Logger;
import org.pstale.asset.loader.SmdKey;

import com.jme3.asset.AssetManager;
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
		return (Node)assetManager.loadAsset(inx);
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
