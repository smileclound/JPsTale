package org.pstale.app;

import org.pstale.asset.loader.FileLocator;
import org.pstale.asset.loader.SmdLoader;
import org.pstale.gui.Style;

import com.jme3.app.DebugKeysAppState;
import com.jme3.app.FlyCamAppState;
import com.jme3.app.SimpleApplication;
import com.jme3.app.StatsAppState;
import com.jme3.app.state.ScreenshotAppState;
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
		/**
		 * 客户端资源的根目录
		 */
		String clientRoot = settings.getString("ClientRoot");
		if (clientRoot != null) {
			assetManager.registerLocator(clientRoot, FileLocator.class);
		}
		
		/**
		 * 服务端资源的根目录
		 */
		String serverRoot = settings.getString("ServerRoot");
		boolean checkServer = settings.getBoolean("CheckServer");
		if (checkServer && serverRoot != null) {
			assetManager.registerLocator(serverRoot, FileLocator.class);
			
			LoadingAppState.CHECK_SERVER = true;
			LoadingAppState.SERVER_ROOT = serverRoot;
		}
		
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
