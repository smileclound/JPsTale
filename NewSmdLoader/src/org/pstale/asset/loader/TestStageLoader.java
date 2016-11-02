package org.pstale.asset.loader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.jme3.app.SimpleApplication;
import com.jme3.light.AmbientLight;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Node;

public class TestStageLoader extends SimpleApplication {

	
	public static void main(String[] args) {
		new TestStageLoader().start();
	}

	@Override
	public void simpleInitApp() {
		
		// 属性文件的路径
		Properties props = new Properties();
		try {
			props.load(new FileInputStream("config.properties"));
			String client = props.getProperty("CLIENT_ROOT");
			assetManager.registerLocator(client, FileLocator.class);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		assetManager.registerLoader(StageLoader.class, "smd");
		
		Node model = (Node)assetManager.loadAsset(new SmdKey("Field/dungeon/dun-1.smd", SmdKey.SMDTYPE.STAGE3D));
		Node solid = (Node)model.getChild("SMMAT_STAT_CHECK_FACE");
		Node other = (Node)model.getChild("SMMAT_STAT_NOT_CHECK_FACE");
		rootNode.attachChild(solid);
		rootNode.attachChild(other);
		
		solid.scale(0.01f);
		other.scale(0.01f);
		
		// 动画模型
		Node ani = (Node)assetManager.loadAsset(new SmdKey("Field/dungeon/dun-1-wheel.smd", SmdKey.SMDTYPE.STAGE_OBJ));
		rootNode.attachChild(ani);
		ani.scale(0.01f);
		
		cam.setLocation(ani.getWorldBound().getCenter());
		flyCam.setMoveSpeed(100f);
		
		viewPort.setBackgroundColor(new ColorRGBA(0.7f, 0.8f, 0.9f, 1));
		
		AmbientLight light = new AmbientLight();
		//light.setColor(new ColorRGBA(0.625f, 0.625f, 0.625f, 1f));
		rootNode.addLight(light);
	}

}
