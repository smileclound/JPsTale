package org.pstale.app;


import org.pstale.assets.AssetFactory;

import com.jme3.app.SimpleApplication;

/**
 * 测试基类，主要是注册SmdLoader之类的东西。
 * 
 * @author yanmaoyuan
 *
 */
public abstract class TestBase extends SimpleApplication {

    @Override
    public void simpleInitApp() {
        AssetFactory.setAssetManager(assetManager);

        stateManager.attach(new AxisAppState());

        init();
    }

    public abstract void init();
}
