package org.pstale.test;

import java.io.File;

import org.pstale.app.ModelFactory;

import com.jme3.app.SimpleApplication;
import com.jme3.audio.plugins.WAVLoader;
import com.jme3.scene.plugins.smd.FileLocator;
import com.jme3.scene.plugins.smd.SmdLoader;

/**
 * 测试基类，主要是注册SmdLoader之类的东西。
 * 
 * @author yanmaoyuan
 *
 */
public abstract class TestBase extends SimpleApplication {

    @Override
    public void simpleInitApp() {
        // 注册Loader
        assetManager.registerLoader(SmdLoader.class, "smd", "smb", "inx");
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
