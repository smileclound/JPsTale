package org.pstale.app;

import java.io.File;

import org.pstale.asset.loader.InxLoader;
import org.pstale.asset.loader.SmbLoader;
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
import com.jme3.system.AppSettings;
import com.simsilica.lemur.GuiGlobals;
import com.simsilica.lemur.style.BaseStyles;

public class FieldApp extends SimpleApplication {

	/**
	 * 客户端资源的根目录
	 */
	private String clientRoot;
	
	public FieldApp() {
		super(new LoadingAppState(),
				new DebugKeysAppState(),
				new StatsAppState(),
				new FlyCamAppState(),
				new ScreenshotAppState());
	}
	
	public FieldApp(String clientRoot) {
		this();
		this.clientRoot = clientRoot;
	}

	@Override
	public void simpleInitApp() {
		// Initialize the Lemur helper instance
		GuiGlobals.initialize(this);

		// Load the 'glass' style
		BaseStyles.loadGlassStyle();

		// Set 'glass' as the default style when not specified
		GuiGlobals.getInstance().getStyles().setDefaultStyle("glass");
		
		// 设置字体
		BitmapFont font = assetManager.loadFont("Interface/Font/field.fnt");
		GuiGlobals.getInstance().getStyles().setDefault(font);

		flyCam.setMoveSpeed(50);
		flyCam.setDragToRotate(true);
		inputManager.addMapping("FLYCAM_RotateDrag", new MouseButtonTrigger(MouseInput.BUTTON_RIGHT));

		assetManager.registerLoader(AseLoader.class, "ase");
		assetManager.registerLoader(InxLoader.class, "inx");
		assetManager.registerLoader(SmdLoader.class, "smd");
		assetManager.registerLoader(SmbLoader.class, "smb");
		assetManager.registerLoader(WAVLoader.class, "bgm");
		assetManager.registerLocator("/", FileLocator.class);
		assetManager.registerLocator("assets", FileLocator.class);
		
		if (clientRoot != null && new File(clientRoot).isDirectory()) {
			assetManager.registerLocator(clientRoot, FileLocator.class);
		}

	}

	public static void main(String[] args) {
		AppSettings settings = new AppSettings(true);
		settings.setTitle("FieldBox");
		settings.setWidth(1024);
		settings.setHeight(768);

		FieldApp app = new FieldApp();
		app.setSettings(settings);
		app.setPauseOnLostFocus(false);
		app.start();
	}
}
