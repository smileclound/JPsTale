package org.pstale.loader;

import org.apache.log4j.Logger;
import org.pstale.asset.struct.chars.CharMonsterInfo;
import org.pstale.utils.FileLocator;

import com.jme3.asset.AssetManager;
import com.jme3.asset.DesktopAssetManager;
import com.jme3.scene.plugins.ptscript.CharInfoLoader;

public class TestNpcLoader {

    static Logger log = Logger.getLogger(TestNpcLoader.class);

    public static void main(String[] args) {
        // 初始化资源管理器
        AssetManager assetManager = new DesktopAssetManager();
        assetManager.registerLoader(CharInfoLoader.class, "inf", "npc");
        assetManager.registerLocator("D:/Priston Tale/0_素材/Server/精灵中国全服务端3060/3060", FileLocator.class);

        CharMonsterInfo charMon = (CharMonsterInfo) assetManager.loadAsset("gameserver/Monster/_14_H_Hobgoblin.inf");
        log.info(charMon.szName);
        log.info("模型:" + charMon.szModelName);
        log.info("等级:" + charMon.Level);
        log.info("HP:" + charMon.Life[0] + "/" + charMon.Life[1]);
        log.info("EXP:" + charMon.Exp);
        log.info("攻击力:" + charMon.Attack_Damage[0] + " - " + charMon.Attack_Damage[1]);
        log.info("攻击速度:" + charMon.Attack_Speed);
        log.info("攻击范围:" + charMon.Shooting_Range);
        log.info("必杀:" + charMon.Critical_Hit);
        log.info("防御:" + charMon.Defence);
        log.info("格挡率:" + charMon.Chance_Block + "%");
        log.info("吸收率:" + charMon.Absorption);
        log.info("移动速度:" + charMon.Move_Speed);
        log.info("视野:" + charMon.Sight);

        assetManager.loadAsset("gameserver/NPC/Bcn01.npc");
    }

}
