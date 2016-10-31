package org.pstale.app;

import java.util.Collection;

import org.pstale.asset.loader.InxLoader;
import org.pstale.asset.loader.SmbLoader;
import org.pstale.asset.loader.SmdLoader;
import org.pstale.desktop.Main;
import org.pstale.state.AxisAppState;
import org.pstale.state.GuiLoaderAppState;
import org.pstale.state.HeightMapAppState;
import org.pstale.state.LoaderAppState;
import org.pstale.state.PickingAppState;
import org.pstale.utils.FileLocator;

import com.jme3.app.DebugKeysAppState;
import com.jme3.app.FlyCamAppState;
import com.jme3.app.SimpleApplication;
import com.jme3.app.StatsAppState;
import com.jme3.app.state.ScreenshotAppState;
import com.jme3.asset.maxase.AseLoader;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.light.AmbientLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;

public class LoaderApp extends SimpleApplication {

	public static void main(String[] args) {
		LoaderApp app = new LoaderApp();
		app.setPauseOnLostFocus(false);
		app.start();
	}

	public Main main;

	public LoaderApp() {
		super(new AxisAppState(), new GuiLoaderAppState(), new LoaderAppState(), new HeightMapAppState(), new PickingAppState(), new StatsAppState(),
				new DebugKeysAppState(), new FlyCamAppState(), new ScreenshotAppState());
	}

	public LoaderApp(Main main) {
		super(new AxisAppState(), new GuiLoaderAppState(), new LoaderAppState(), new HeightMapAppState(), new PickingAppState(), new StatsAppState(),
				new DebugKeysAppState(), new FlyCamAppState(), new ScreenshotAppState());
		this.main = main;
	}

	@Override
	public void simpleInitApp() {
		cam.setLocation(new Vector3f(100, 50, 0));
		cam.lookAt(new Vector3f(0, 0, 0), Vector3f.UNIT_Y);

		flyCam.setMoveSpeed(50);
		flyCam.setDragToRotate(true);
		inputManager.addMapping("FLYCAM_RotateDrag", new MouseButtonTrigger(MouseInput.BUTTON_RIGHT));

		assetManager.registerLoader(AseLoader.class, "ase");
		assetManager.registerLoader(InxLoader.class, "inx");
		assetManager.registerLoader(SmdLoader.class, "smd");
		assetManager.registerLoader(SmbLoader.class, "smb");
		assetManager.registerLocator("/", FileLocator.class);

		AmbientLight light = new AmbientLight();
		light.setColor(ColorRGBA.White);
		rootNode.addLight(light);
		
	}
	
	public void updateUI(Collection<String> collection) {
		main.setAnimList(collection);
	}
}
