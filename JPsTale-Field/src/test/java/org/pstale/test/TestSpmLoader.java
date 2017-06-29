package org.pstale.test;

import org.apache.log4j.Logger;
import org.pstale.fields.RespawnList;

import com.jme3.asset.AssetManager;
import com.jme3.asset.DesktopAssetManager;
import com.jme3.scene.plugins.smd.FileLocator;
import com.jme3.scene.plugins.smd.SpmLoader;

public class TestSpmLoader {

    static Logger log = Logger.getLogger(TestSpmLoader.class);

    public static void main(String[] args) {
        // 初始化资源管理器
        AssetManager assetManager = new DesktopAssetManager();
        assetManager.registerLoader(SpmLoader.class, "spm");
        assetManager.registerLocator("assets/server", FileLocator.class);

        // 读取地图的spc文件
        RespawnList respawn = (RespawnList) assetManager.loadAsset("Field/fore-3.ase.spm");

        log.info("" + respawn.LimitMax);
        log.info("" + respawn.IntervalTime);
        log.info("" + respawn.OpenLimit);
        log.info("" + respawn.PercentageCnt);

    }

}
