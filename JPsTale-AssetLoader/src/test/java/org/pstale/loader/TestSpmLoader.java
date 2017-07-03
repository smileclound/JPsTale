package org.pstale.loader;

import org.apache.log4j.Logger;
import org.pstale.assets.AssetFactory;
import org.pstale.entity.field.RespawnList;

import com.jme3.asset.AssetManager;
import com.jme3.asset.DesktopAssetManager;

public class TestSpmLoader {

    static Logger log = Logger.getLogger(TestSpmLoader.class);

    public static void main(String[] args) {
        // 初始化资源管理器
        AssetManager assetManager = new DesktopAssetManager();
        AssetFactory.setAssetManager(assetManager);

        // 读取地图的spc文件
        RespawnList respawn = (RespawnList) assetManager.loadAsset("GameServer/Field/Fore-3.ase.spm");

        log.info("" + respawn.LimitMax);
        log.info("" + respawn.IntervalTime);
        log.info("" + respawn.OpenLimit);
        log.info("" + respawn.PercentageCnt);

    }

}
