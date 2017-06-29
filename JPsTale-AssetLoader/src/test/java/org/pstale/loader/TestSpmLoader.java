package org.pstale.loader;

import org.apache.log4j.Logger;
import org.pstale.fields.RespawnList;

import com.jme3.asset.AssetManager;
import com.jme3.asset.DesktopAssetManager;
import com.jme3.asset.plugins.ClasspathLocator;
import com.jme3.scene.plugins.ptscript.SpmLoader;

public class TestSpmLoader {

    static Logger log = Logger.getLogger(TestSpmLoader.class);

    public static void main(String[] args) {
        // 初始化资源管理器
        AssetManager assetManager = new DesktopAssetManager();
        assetManager.registerLoader(SpmLoader.class, "spm");
        assetManager.registerLocator("/", ClasspathLocator.class);

        // 读取地图的spc文件
        RespawnList respawn = (RespawnList) assetManager.loadAsset("server/Field/Fore-3.ase.spm");

        log.info("" + respawn.LimitMax);
        log.info("" + respawn.IntervalTime);
        log.info("" + respawn.OpenLimit);
        log.info("" + respawn.PercentageCnt);

    }

}
