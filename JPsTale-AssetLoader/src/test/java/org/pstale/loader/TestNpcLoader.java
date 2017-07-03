package org.pstale.loader;

import org.apache.log4j.Logger;
import org.pstale.assets.AssetFactory;

import com.jme3.asset.AssetManager;
import com.jme3.asset.DesktopAssetManager;
import com.jme3.script.plugins.character.Monster;

public class TestNpcLoader {

    static Logger log = Logger.getLogger(TestNpcLoader.class);

    public static void main(String[] args) {
        // 初始化资源管理器
        AssetManager assetManager = new DesktopAssetManager();
        AssetFactory.setAssetManager(assetManager);

        Monster charMon = (Monster) assetManager.loadAsset("GameServer/Monster/_14_H_Hobgoblin.inf");
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

        assetManager.loadAsset("GameServer/NPC/Bcn01.npc");
    }

}
