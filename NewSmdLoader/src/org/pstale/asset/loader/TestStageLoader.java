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
		
		Node model = (Node)assetManager.loadAsset(new SmdKey("field/dungeon/dun-1.smd", SmdKey.SMDTYPE.STAGE3D));
		rootNode.attachChild(model);
		
		model.scale(0.01f);
		model.center().move(0, 0, 0);
		
		viewPort.setBackgroundColor(new ColorRGBA(0.6f, 0.7f, 0.8f, 1));
		
		rootNode.addLight(new AmbientLight());
		//rootNode.addLight(new DirectionalLight(new Vector3f(0.0f, -372.0f, -93.0f)));
	}

}
