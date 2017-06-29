package org.pstale.loader;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.pstale.asset.struct.chars.TRNAS_PLAYERINFO;

import com.jme3.asset.AssetManager;
import com.jme3.asset.DesktopAssetManager;
import com.jme3.asset.plugins.ClasspathLocator;
import com.jme3.scene.plugins.smd.SpcLoader;

public class TestSpcLoader {

    static Logger log = Logger.getLogger(TestSpcLoader.class);

    public static void main(String[] args) {
        // 初始化资源管理器
        AssetManager assetManager = new DesktopAssetManager();
        assetManager.registerLoader(SpcLoader.class, "spc");
        assetManager.registerLocator("/", ClasspathLocator.class);

        // 读取地图的spc文件
        ArrayList<TRNAS_PLAYERINFO> npcs = (ArrayList<TRNAS_PLAYERINFO>) assetManager.loadAsset("server/Field/fore-3.ase.spc");
        for (TRNAS_PLAYERINFO npc : npcs) {
            log.info("" + npc.charInfo.szModelName);
            log.info("" + npc.charInfo.szModelName2);
            log.info("Lv:" + npc.charInfo.Level);
            log.info("HP:" + npc.charInfo.Life[0] + "/" + npc.charInfo.Life[1]);
            log.info("MP:" + npc.charInfo.Mana[0] + "/" + npc.charInfo.Mana[1]);
            log.info("SP:" + npc.charInfo.Stamina[0] + "/" + npc.charInfo.Stamina[1]);
            log.info("Position(" + npc.x + ", " + npc.y + ", " + npc.z + ")");
            log.info("Direction(" + npc.ax + ", " + npc.ay + ", " + npc.az + ")");
        }
    }

}
