package org.pstale.app;

import java.io.File;

import org.pstale.asset.loader.InxLoader;
import org.pstale.asset.loader.SmdLoader;

import com.jme3.app.FlyCamAppState;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.ScreenshotAppState;
import com.jme3.asset.maxase.AseLoader;
import com.jme3.asset.maxase.FileLocator;
import com.jme3.audio.plugins.WAVLoader;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.light.AmbientLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;

public class FieldApp extends SimpleApplication {

	/**
	 * 客户端资源的根目录
	 */
	private String clientRoot;
	
	public FieldApp() {
		super(new AxisAppState(),
				new LoaderAppState(),
				new MusicAppState(),
				new FlyCamAppState(),
				new ScreenshotAppState());
	}
	
	public FieldApp(String clientRoot) {
		super(new AxisAppState(),
				new LoaderAppState(),
				new MusicAppState(),
				new FlyCamAppState(),
				new ScreenshotAppState());
		
		this.clientRoot = clientRoot;
	}

	@Override
	public void simpleInitApp() {
		cam.setLocation(new Vector3f(100, 50, 0));
		cam.lookAt(new Vector3f(0, 0, 0), Vector3f.UNIT_Y);

		flyCam.setMoveSpeed(50);
		flyCam.setDragToRotate(true);
		inputManager.addMapping("FLYCAM_RotateDrag", new MouseButtonTrigger(
				MouseInput.BUTTON_RIGHT));

		assetManager.registerLoader(AseLoader.class, "ase");
		assetManager.registerLoader(InxLoader.class, "inx");
		assetManager.registerLoader(SmdLoader.class, "smd");
		assetManager.registerLoader(WAVLoader.class, "bgm");
		assetManager.registerLocator("/", FileLocator.class);
		assetManager.registerLocator("assets", FileLocator.class);
		
		if (clientRoot != null && new File(clientRoot).isDirectory()) {
			assetManager.registerLocator(clientRoot, FileLocator.class);
		}

		AmbientLight light = new AmbientLight();
		light.setColor(ColorRGBA.White);
		rootNode.addLight(light);
	}

}
