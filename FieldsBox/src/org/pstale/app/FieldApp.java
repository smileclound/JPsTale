package org.pstale.app;

import org.pstale.asset.loader.SmdLoader;

import com.jme3.app.DebugKeysAppState;
import com.jme3.app.FlyCamAppState;
import com.jme3.app.SimpleApplication;
import com.jme3.app.StatsAppState;
import com.jme3.app.state.ScreenshotAppState;
import com.jme3.asset.maxase.AseLoader;
import com.jme3.asset.maxase.FileLocator;
import com.jme3.audio.plugins.WAVLoader;
import com.jme3.font.BitmapFont;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.MouseButtonTrigger;
import com.simsilica.lemur.GuiGlobals;
import com.simsilica.lemur.style.BaseStyles;

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
		assetManager.registerLoader(AseLoader.class, "ase");
		assetManager.registerLoader(SmdLoader.class, "inx", "smd", "smb");
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
		
		/**
		 * 是否使用灯光、法线
		 */
		boolean useLight = settings.getBoolean("UseLight");
		SmdLoader.USE_LIGHT = useLight;
		LightState.USE_LIGHT = useLight;
		
		// 设置模型工厂
		ModelFactory.setAssetManager(assetManager);
		
		// Initialize the Lemur helper instance
		GuiGlobals.initialize(this);

		// Load the 'glass' style
		BaseStyles.loadGlassStyle();

		// Set 'glass' as the default style when not specified
		GuiGlobals.getInstance().getStyles().setDefaultStyle("glass");
		
		// 设置字体
		BitmapFont font = assetManager.loadFont("Font/field.fnt");
		GuiGlobals.getInstance().getStyles().setDefault(font);

		flyCam.setMoveSpeed(50);
		flyCam.setDragToRotate(true);
		inputManager.addMapping("FLYCAM_RotateDrag", new MouseButtonTrigger(MouseInput.BUTTON_RIGHT));
	}

}
