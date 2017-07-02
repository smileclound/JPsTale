package org.pstale.server;

import java.io.File;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.pstale.assets.AssetFactory;
import org.pstale.entity.item.ItemInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jme3.asset.DesktopAssetManager;
import com.jme3.script.plugins.character.Monster;

public class TestServerAsset {
    static Logger logger = LoggerFactory.getLogger(TestServerAsset.class);
    
    /**
     * 服务端的路径
     */
    static boolean CHECK_SERVER = false;
    static String SERVER_ROOT = "I:/game/PTCN-RPT1.0";
    static String FIELD_DIR = "GameServer/Field";
    static String MONSTER_DIR = "GameServer/Monster";
    static String OPENITEM_DIR = "GameServer/OpenItem";
    static String NPC_DIR = "GameServer/NPC";
    
    
    @Before
    public void init() {
        AssetFactory.setAssetManager(new DesktopAssetManager());
    }
    
    @Test
    public void testLoadMonster() {
        // 所有怪物数据
        File folder = new File(SERVER_ROOT + "/" + MONSTER_DIR);
        String[] files = folder.list();
        int len = files.length;
        ArrayList<Monster> allMonster = new ArrayList<Monster>(len);
        for (int i = 0; i < len; i++) {
            String name = files[i];
            Monster m = AssetFactory.loadMonsterScript(name);
            if (m != null) {
                allMonster.add(m);
            }
        }

        logger.info("Monster: {}", allMonster.size());
    }
    
    @Test
    public void testLoadNPC() {
        
    }
    
    @Test
    public void testLoadItem() {
     // 所有装备数据
        File folder = new File(SERVER_ROOT + "/" + OPENITEM_DIR);
        String[] files = folder.list();
        int len = files.length;
        ArrayList<ItemInfo> allItem = new ArrayList<ItemInfo>(len);
        for (int i = 0; i < len; i++) {
            String name = files[i];
            ItemInfo item = AssetFactory.loadItemScript(name);
            if (item != null) {
                allItem.add(item);
            }
        }

        logger.info("Item: {}", allItem.size());
    }
}
