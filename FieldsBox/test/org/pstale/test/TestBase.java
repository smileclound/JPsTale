package org.pstale.test;

import java.io.File;

import org.pstale.app.ModelFactory;
import org.pstale.asset.loader.FileLocator;
import org.pstale.asset.loader.InxLoader;
import org.pstale.asset.loader.SmdLoader;

import com.jme3.app.SimpleApplication;
import com.jme3.audio.plugins.WAVLoader;

/**
 * 测试基类，主要是注册SmdLoader、InxLoader之类的东西。
 * @author yanmaoyuan
 *
 */
public abstract class TestBase extends SimpleApplication {

	@Override
	public void simpleInitApp() {
		// 注册Loader
		assetManager.registerLoader(SmdLoader.class, "smd", "smb");
		assetManager.registerLoader(InxLoader.class, "inx");
		assetManager.registerLoader(WAVLoader.class, "bgm");
		
		// 注册模型文件的路径
		assetManager.registerLocator("/", FileLocator.class);
		if (new File("I:/game/PTCN-RPT1.0").exists()) {
			assetManager.registerLocator("I:/game/PTCN-RPT1.0", FileLocator.class);
		} else {
			assetManager.registerLocator("D:/Priston Tale/PTCN3550/PTCN3550", FileLocator.class);
		}

		// 注册模型工厂
		ModelFactory.setAssetManager(assetManager);
		
		init();
	}

	public abstract void init();
}
