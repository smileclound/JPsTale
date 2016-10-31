package org.pstale.app;

import org.pstale.fields.NPC;

import com.jme3.app.Application;
import com.jme3.asset.AssetManager;
import com.jme3.asset.maxase.AseKey;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;

/**
 * NPC
 * @author yanmaoyuan
 *
 */
public class NpcAppState extends SubAppState {

	@Override
	protected void initialize(Application app) {

	}
	
	/**
	 * 加载NPC
	 * @param npcs
	 */
	public void load(NPC ... npcs) {
		//rootNode.detachAllChildren();
		
		for(int i=0; i<npcs.length; i++) {
			NPC npc = npcs[i];
			Vector3f pos = new Vector3f(npc.getPosition());
			pos.multLocal(scale);
			pos.y += 1;

			/**
			 * 创建一个NPC模型
			 * @param pos
			 */
			try {
				// 首先尝试直接读取NPC模型
				Spatial model = this.loadModel(npc.getModel());
				model.scale(scale);
				model.setLocalTranslation(pos);
			} catch (Exception e) {
				// 加载失败，改为加载一个绿色方块代替NPC。
				Box box = new Box(1, 1, 1);
				Geometry geom = new Geometry("NPCFlag", box);
				geom.setLocalTranslation(pos);
				geom.setMaterial(getMaterial(ColorRGBA.Green));
				geom.setUserData("script", npc.getScript());
				rootNode.attachChild(geom);
			}
		}
	}
	
	/**
	 * 加载NPC模型
	 * @param name
	 * @return
	 */
	protected Spatial loadModel(final String name) throws Exception {
		Spatial model = null;
		
		AssetManager assetManager = getApplication().getAssetManager();
		
		String path = name;
		if (path != null) {
			path = path.replaceAll("\\\\", "/");
		}
		
		// NPC的模型文件名为ini，将其改为inx
		int index = path.lastIndexOf(".");
		path = path.substring(0, index) + ".inx";
		
		AseKey key = new AseKey(path);
		model = (Spatial) assetManager.loadAsset(key);
		
		return model;
	}
}
