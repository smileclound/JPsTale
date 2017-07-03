package org.pstale.loader;

import java.util.ArrayList;

import org.pstale.assets.AssetFactory;
import org.pstale.entity.field.StartPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jme3.asset.AssetManager;
import com.jme3.asset.DesktopAssetManager;

public class TestSppLoader {

    static Logger logger = LoggerFactory.getLogger(TestSppLoader.class);

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        // 初始化资源管理器
        AssetManager assetManager = new DesktopAssetManager();
        AssetFactory.setAssetManager(assetManager);

        // 读取地图的spc文件
        ArrayList<StartPoint> points = (ArrayList<StartPoint>) assetManager.loadAsset("GameServer/Field/fore-3.ase.spp");

        for (StartPoint p : points) {
            logger.info("{}, {}", p.x, p.z);
        }

        logger.info("刷怪点数量:{}", points.size());
    }

}
