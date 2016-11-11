package org.pstale.app;

import org.pstale.asset.loader.FileLocator;
import org.pstale.asset.loader.SmdLoader;
import org.pstale.gui.Style;

import com.jme3.app.DebugKeysAppState;
import com.jme3.app.FlyCamAppState;
import com.jme3.app.SimpleApplication;
import com.jme3.app.StatsAppState;
import com.jme3.app.state.ScreenshotAppState;
import com.jme3.audio.plugins.WAVLoader;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.MouseButtonTrigger;

public class FieldApp extends SimpleApplication {

	public FieldApp() {
		super(new LoadingAppState(),
				new CursorState(),
				new DebugKeysAppState(),
				new StatsAppState(),
				new FlyCamAppState(),
				new ScreenshotAppState());
	}
	
	@Override
	public void simpleInitApp() {
		assetManager.registerLoader(SmdLoader.class, "smd", "smb", "inx");
		//assetManager.registerLoader(InxLoader.class, "inx");
		assetManager.registerLoader(WAVLoader.class, "bgm");
		assetManager.registerLocator("/", FileLocator.class);
		assetManager.registerLocator("assets", FileLocator.class);
		
		/**
		 * 客户端资源的根目录
		 */
		if (settings.getString("ClientRoot") != null) {
			String clientRoot = settings.getString("ClientRoot");
			assetManager.registerLocator(clientRoot, FileLocator.class);
		}
		
		LoadingAppState.SERVER_ROOT = settings.getString("ServerRoot");
		LoadingAppState.CHECK_SERVER = settings.getBoolean("CheckServer");
		
		/**
		 * 是否使用灯光、法线
		 */
		boolean useLight = settings.getBoolean("UseLight");
		SmdLoader.USE_LIGHT = useLight;
		LightState.USE_LIGHT = useLight;
		
		/**
		 * 设置模型工厂
		 */
		ModelFactory.setAssetManager(assetManager);
		
		/**
		 * 初始化Lemur样式
		 */
		Style.initStyle(this);

		flyCam.setMoveSpeed(50);
		flyCam.setDragToRotate(true);
		inputManager.addMapping("FLYCAM_RotateDrag", new MouseButtonTrigger(MouseInput.BUTTON_RIGHT));
	}

}
