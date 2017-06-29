package org.pstale.loader;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.pstale.fields.StartPoint;

import com.jme3.asset.AssetManager;
import com.jme3.asset.DesktopAssetManager;
import com.jme3.asset.plugins.ClasspathLocator;
import com.jme3.scene.plugins.smd.SppLoader;

public class TestSppLoader {

    static Logger log = Logger.getLogger(TestSppLoader.class);

    public static void main(String[] args) {
        // 初始化资源管理器
        AssetManager assetManager = new DesktopAssetManager();
        assetManager.registerLoader(SppLoader.class, "spp");
        assetManager.registerLocator("/", ClasspathLocator.class);

        // 读取地图的spc文件
        ArrayList<StartPoint> points = (ArrayList<StartPoint>) assetManager.loadAsset("server/Field/fore-3.ase.spp");

        for (StartPoint p : points) {
            log.info("" + p.x + ", " + p.z);
        }

        log.info("刷怪点数量:" + points.size());
    }

}
