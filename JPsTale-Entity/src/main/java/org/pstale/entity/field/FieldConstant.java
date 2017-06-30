package org.pstale.entity.field;

/**
 * 地图的一些常量
 * 
 * @author yanmaoyuan
 *
 */
public interface FieldConstant {

    String FieldDirectory = "field/";
    String MapDirectory = "field/map/";
    String TitleDirectory = "field/title/";

    int FIELD_GATE_MAX = 12;// 每个地区中的门户数量最大值
    int FIELD_MAX = 256;// 地区数量最大值
    int FIELD_AMBENT_MAX = 80;// 每个地区中环境音效数量的最大值

    /***************************************
     * 地图的状态
     ***************************************/

    /**
     * 村庄
     */
    int FIELD_STATE_VILLAGE = 0x100;
    /**
     * 森林
     */
    int FIELD_STATE_FOREST = 0x200;
    /**
     * 沙漠
     */
    int FIELD_STATE_DESERT = 0x300;
    /**
     * 废墟
     */
    int FIELD_STATE_RUIN = 0x400;
    /**
     * 地下城
     */
    int FIELD_STATE_DUNGEON = 0x500;
    /**
     * 钢铁都市
     */
    int FIELD_STATE_IRON = 0x600;
    /**
     * 小房间
     */
    int FIELD_STATE_ROOM = 0x800;
    /**
     * 任务区域
     */
    int FIELD_STATE_QUEST_ARENA = FIELD_STATE_DUNGEON;
    /**
     * 雪原
     */
    int FIELD_STATE_ICE = 0x900;
    /**
     * 祝福城堡
     */
    int FIELD_STATE_CASTLE = 0xA00;
    /**
     * 活动区域
     */
    int FIELD_STATE_ACTION = 0xB00;
    /**
     * 所有区域
     */
    int FIELD_STATE_ALL = 0x1000;

    /*************************************
     * 背景图片
     *************************************/

    int FIELD_BACKIMAGE_RAIN = 0x00;
    int FIELD_BACKIMAGE_NIGHT = 0x01;
    int FIELD_BACKIMAGE_DAY = 0x02;
    int FIELD_BACKIMAGE_GLOWDAY = 0x03;
    int FIELD_BACKIMAGE_DESERT = 0x04;
    int FIELD_BACKIMAGE_GLOWDESERT = 0x05;
    int FIELD_BACKIMAGE_NIGHTDESERT = 0x06;
    int FIELD_BACKIMAGE_RUIN1 = 0x07;
    int FIELD_BACKIMAGE_RUIN2 = 0x08;
    int FIELD_BACKIMAGE_NIGHTRUIN1 = 0x09;
    int FIELD_BACKIMAGE_NIGHTRUIN2 = 0x0A;

    int FIELD_BACKIMAGE_GLOWRUIN1 = 0x0B;
    int FIELD_BACKIMAGE_GLOWRUIN2 = 0x0C;

    int FIELD_BACKIMAGE_NIGHTFALL = 0x11;
    int FIELD_BACKIMAGE_DAYFALL = 0x12;
    int FIELD_BACKIMAGE_GLOWFALL = 0x13;

    int FIELD_BACKIMAGE_NIGHTIRON1 = 0x14;
    int FIELD_BACKIMAGE_NIGHTIRON2 = 0x15;
    int FIELD_BACKIMAGE_DAYIRON = 0x16;
    int FIELD_BACKIMAGE_GLOWIRON = 0x17;

    int FIELD_BACKIMAGE_SODSKY = 0x18;
    int FIELD_BACKIMAGE_SODMOON = 0x19;
    int FIELD_BACKIMAGE_SODSUN = 0x20;
    int FIELD_BACKIMAGE_SODNONE = 0x21;
    int FIELD_BACKIMAGE_IRONBOSS = 0x22;

    int FIELD_BACKIMAGE_DAYSNOW = 0x23;
    int FIELD_BACKIMAGE_GLOWSNOW = 0x24;
    int FIELD_BACKIMAGE_NIGHTSNOW = 0x25;

    int FIELD_BACKIMAGE_DAYGREDDY = 0x26;
    int FIELD_BACKIMAGE_GLOWGREDDY = 0x27;
    int FIELD_BACKIMAGE_NIGHTGREDDY = 0x28;

    int FIELD_BACKIMAGE_DAYLOST = 0x29;
    int FIELD_BACKIMAGE_GLOWLOST = 0x2A;
    int FIELD_BACKIMAGE_NIGHTLOST = 0x2B;

    int FIELD_BACKIMAGE_DAYTEMPLE = 0x2C;
    int FIELD_BACKIMAGE_GLOWTEMPLE = 0x2D;
    int FIELD_BACKIMAGE_NIGHTTEMPLE = 0x2E;

    int FIELD_EVENT_NIGHTMARE = 0x01;

    int FIELD_START_POINT_MAX = 8;

    int FIELD_STAGE_OBJ_MAX = 50;

    int START_FIELD_NUM = 3;
    int START_FIELD_NEBISCO = 9;
    int START_FIELD_MORYON = 21;
    int START_FIELD_CASTLE = 33;
}
