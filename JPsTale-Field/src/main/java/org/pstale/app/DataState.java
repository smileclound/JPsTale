package org.pstale.app;

import java.util.List;

import org.apache.log4j.Logger;
import org.pstale.entity.field.Field;
import org.pstale.entity.item.ItemInfo;

import com.jme3.app.Application;
import com.jme3.app.state.BaseAppState;
import com.jme3.script.plugins.character.Monster;
import com.jme3.script.plugins.field.FieldLoader;

/**
 * 这个状态机仅用于维持地区数据
 * 
 * @author yanmaoyuan
 * 
 */
public class DataState extends BaseAppState {

    static Logger log = Logger.getLogger(DataState.class);

    private String serverRoot;
    private List<Monster> allMonster;
    private List<Monster> allNpc;
    private List<ItemInfo> allItem;

    private Field[] fields;

    /**
     * 此构造方法被LoadingAppState调用。
     * 
     * @param serverRoot
     * @param allMonster
     * @param allNpc
     * @param allItem
     * @param clientRoot
     * @param fields
     */
    public DataState(String serverRoot, List<Monster> allMonster, List<Monster> allNpc,
            List<ItemInfo> allItem) {
        this.serverRoot = serverRoot;
        this.allMonster = allMonster;
        this.allNpc = allNpc;
        this.allItem = allItem;
    }

    @Override
    protected void initialize(Application app) {
        Field[] fields = new FieldLoader().load();
        this.fields = fields;
    }

    @Override
    protected void cleanup(Application app) {
    }

    @Override
    protected void onEnable() {
    }

    @Override
    protected void onDisable() {
    }

    /**
     * 查询服务端根目录
     * 
     * @return
     */
    public String getServerRoot() {
        return serverRoot;
    }

    /**
     * 获得地区数据
     * 
     * @return
     */
    public Field[] getFields() {
        return fields;
    }

    /**
     * 查询NPC信息
     * 
     * @param file
     * @return
     */
    public Monster findNPC(String file) {
        if (file == null || file.trim().length() == 0) {
            return null;
        }

        if (allNpc == null || allNpc.size() == 0) {
            return null;
        }

        // spc中记录的NPC文件名为 "GameServer\npc\Kamiyu.npc"这种形式，我们只关心最后一部分。
        int index = file.lastIndexOf("\\");
        if (index != -1) {
            file = file.substring(index + 1);
        }
        log.info("查找NPC:" + file);

        int len = allNpc.size();
        for (int i = 0; i < len; i++) {
            Monster npc = allNpc.get(i);
            if (npc.File.equalsIgnoreCase(file)) {
                log.info("找到了:" + npc.szName);
                return npc;
            }
        }

        log.info("没有找到该NPC");
        return null;
    }

    /**
     * 根据怪物的名字查询怪物信息
     * 
     * @param file
     * @return
     */
    public Monster findMonsterByName(String name) {
        if (allMonster == null || allMonster.size() == 0) {
            return null;
        }

        log.info("查找怪物:" + name);

        int len = allMonster.size();
        for (int i = 0; i < len; i++) {
            Monster monster = allMonster.get(i);
            if (monster.szName.equalsIgnoreCase(name)) {
                log.info("找到了:" + monster.szModelName);
                return monster;
            }
        }

        log.info("没有找到这个怪物");
        return null;
    }

    /**
     * 根据装备的编号查询装备信息，装备编号的格式为WA103
     * 
     * @return
     */
    public ItemInfo findItem(String code) {
        if (allItem == null || allItem.size() == 0) {
            return null;
        }

        log.info("查找装备:" + code);
        for (int i = 0; i < allItem.size(); i++) {
            ItemInfo item = allItem.get(i);
            if (item.code.equalsIgnoreCase(code)) {
                log.info("找到了:" + item.localeName);
                return item;
            }
        }

        log.info("没有找到这件装备");
        return null;
    }
}
