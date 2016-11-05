package org.pstale.app;

import org.pstale.asset.loader.SmdKey;
import static org.pstale.asset.loader.SmdKey.SMDTYPE.*;

import com.jme3.asset.AssetManager;
import com.jme3.asset.maxase.AseKey;
import com.jme3.scene.Mesh;
import com.jme3.scene.Node;

public class ModelFactory {

	static AssetManager assetManager;
	
	public static void setAssetManager(final AssetManager manager) {
		assetManager = manager;
	}
	
	/**
	 * 加载地图
	 * @param name
	 * @return
	 * @throws Exception 
	 */
	public static Node loadStage3D(final String name) {
		Node model = null;
		
		// 文件路径
		String path = name;
		if (path != null) {
			path = path.replaceAll("\\\\", "/");
		}
		
		int idx = path.lastIndexOf(".");
		String smd = path.substring(0, idx) + ".smd";
		try {
			model = (Node)assetManager.loadAsset(new SmdKey(smd, STAGE3D));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("加载"+smd+"失败，尝试加载原模型");
			try {
				// 如果加载失败，则尝试加载ase文件。
				AseKey key = new AseKey(path);
				model = (Node) assetManager.loadAsset(key);
			} catch (Exception e2) {
				e2.printStackTrace();
				System.out.println("加载" + path + "失败");
			}
		}
		
		return model;
	}
	
	public static Mesh loadStage3DMesh(final String name) {
		// 文件路径
		String path = name;
		if (path != null) {
			path = path.replaceAll("\\\\", "/");
		}
		
		int idx = path.lastIndexOf(".");
		String smd = path.substring(0, idx) + ".smd";
				
		return (Mesh)assetManager.loadAsset(new SmdKey(smd, STAGE3D_SOLID));
	}
	
	public static Node loadStageObj(final String name, final boolean bip) {
		String path = name;
		if (path != null) {
			path = path.replaceAll("\\\\", "/");
		}
		
		int idx = path.lastIndexOf(".");
		String smd = path.substring(0, idx) + ".smd";
		
		return (Node)assetManager.loadAsset(new SmdKey(smd, bip?PAT3D_BIP:PAT3D));
	}
	public static Node loadNPC(final String name) {
		String path = name;
		if (path != null) {
			path = path.replaceAll("\\\\", "/");
		}
		
		int idx = path.lastIndexOf(".");
		String smd = path.substring(0, idx) + ".inx";
		
		return (Node)assetManager.loadAsset(new SmdKey(smd, INX));
	}
}
