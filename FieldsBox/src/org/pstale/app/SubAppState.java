package org.pstale.app;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.asset.AssetManager;
import com.jme3.asset.maxase.AseKey;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

/**
 * 子场景状态机。这个AppState向游戏根节点添加了另外2个子节点，其子类可以直接使用。
 * @author yanmaoyuan
 *
 */
public abstract class SubAppState extends BaseAppState {

	protected float scale = 0.05f;
	protected Node rootNode;
	protected Node guiNode;
	
	public SubAppState() {
		rootNode = new Node("subRootNode" + System.currentTimeMillis());
		guiNode = new Node("subGuiNode" + System.currentTimeMillis());
	}
	

	@Override
	protected void cleanup(Application app) {}
	
	@Override
	protected void onEnable() {
		SimpleApplication simpleApp = (SimpleApplication)getApplication();
		simpleApp.getRootNode().attachChild(rootNode);
		simpleApp.getGuiNode().attachChild(guiNode);
	}

	@Override
	protected void onDisable() {
		rootNode.removeFromParent();
		guiNode.removeFromParent();
	}

	/**
	 * 加载模型
	 * @param name
	 * @return
	 * @throws Exception 
	 */
	protected Spatial loadModel(final String name) throws Exception {
		Spatial model = null;
		
		AssetManager assetManager = getApplication().getAssetManager();
		
		// 文件路径
		String path = name;
		if (path != null) {
			path = path.replaceAll("\\\\", "/");
		}
		
		int idx = path.lastIndexOf(".");
		String smd = path.substring(0, idx) + ".smd";
		try {
			// 首先尝试加载smd模型，如果存在smd文件，就不再查找ase文件。
			AseKey key = new AseKey(smd);
			model = (Spatial) assetManager.loadAsset(key);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("加载"+smd+"失败，尝试加载原模型");
			try {
				// 如果加载失败，则尝试加载ase文件。
				AseKey key = new AseKey(path);
				model = (Spatial) assetManager.loadAsset(key);
			} catch (Exception e2) {
				e2.printStackTrace();
				System.out.println("加载" + path + "失败");
			}
		}
		
		return model;
	}
	
	protected Material defaultMat;
	/**
	 * 创建一个简单的材质
	 * @param color
	 * @return
	 */
	protected Material getMaterial(ColorRGBA color) {
		if (defaultMat == null) {
			AssetManager assetManager = getApplication().getAssetManager();
			Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
			mat.setColor("Color", color);
			defaultMat = mat;
		}
		return defaultMat;
	}
}
