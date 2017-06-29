package org.pstale.fields;

public enum Music {

    LOGIN(99, "登录", "wav/BGM/Intro(Login) - Neo Age.bgm"), CHARACTER_SELECT(100, "角色选择",
            "Sounds/wav/BGM/Character Select.bgm"),

    CUSTOM(-1, "CUSTOM", "wav/BGM/Field - Desert - Pilgrim.bgm"), TOWN1(1, "内维斯克",
            "wav/BGM/Town - Tempskron_Stronghold.bgm"), TOWN2(2, "理查登",
                    "wav/BGM/Town 1 - Tempskron_Ricarten - When wind comes-o.bgm"), VILLAGE(3, "村庄",
                            "wav/BGM/wind loop.bgm"), // 这个文件并不存在
    FOREST(4, "森林", "wav/BGM/Field - Forest - DarkWood.bgm"), DUNGEON(5, "地下城",
            "wav/BGM/Dungeon - Gloomy Heart.bgm"), FILAI(6, "菲尔拉",
                    "wav/BGM/Town 2 - Morion_Philliy - Voyage above the Clouds.bgm"), SOD1(7, "SOD1",
                            "wav/Sod/BGM/Stage_Play1.wav"), SOD2(8, "SOD2", "wav/Sod/BGM/Stage_Play1.wav"), SOD3(9,
                                    "SOD3", "wav/Sod/BGM/Stage_Play3.wav"), DESERT(10, "沙漠",
                                            "wav/BGM/Field - Desert - Pilgrim.bgm"), ICE(11, "雪原", "wav/BGM/Ice 1.bgm");

    private int id;// id
    private String name;// 名称
    private String filename;// 文件名

    private Music(int id, String name, String filename) {
        this.id = id;
        this.name = name;
        this.filename = filename;
    }

    public static Music get(int id) {
        switch (id) {
        case 1:
            return TOWN1;
        case 2:
            return TOWN2;
        case 3:
            return VILLAGE;
        case 4:
            return FOREST;
        case 5:
            return DUNGEON;
        case 6:
            return FILAI;
        case 7:
            return SOD1;
        case 8:
            return SOD2;
        case 9:
            return SOD3;
        case 10:
            return DESERT;
        case 11:
            return ICE;
        default:
            return CUSTOM;
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFilename() {
        return filename;
    }
}