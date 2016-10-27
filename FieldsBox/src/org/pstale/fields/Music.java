package org.pstale.fields;
public enum Music {

	LOGIN(99, "登录", "bgm/Intro(Login) - Neo Age.ogg"),
	CHARACTER_SELECT(100, "角色选择", "Sounds/bgm/Character Select.ogg"),

	CUSTOM(-1, "CUSTOM", "bgm/Field - Desert - Pilgrim.ogg"),
	TOWN1(1, "内维斯克", "bgm/Town - Tempskron_Stronghold.ogg"),
	TOWN2(2, "理查登", "bgm/Town 1 - Tempskron_Ricarten - When wind comes-o.ogg"),
	VILLAGE(3, "村庄", "bgm/wind loop.bgm"), // 这个文件并不存在
	FOREST(4, "森林", "bgm/Field - Forest - DarkWood.ogg"),
	DUNGEON(5, "地下城", "bgm/Dungeon - Gloomy Heart.ogg"),
	FILAI(6, "菲尔拉", "bgm/Town 2 - Morion_Philliy - Voyage above the Clouds.ogg"),
	SOD1(7, "SOD1", "bgm/SOD_Stage_Play1.ogg"),
	SOD2(8, "SOD2", "bgm/SOD_Stage_Play1.ogg"),
	SOD3(9, "SOD3", "bgm/SOD_Stage_Play3.ogg"),
	DESERT(10, "沙漠", "bgm/Field - Desert - Pilgrim.ogg"),
	ICE(11, "雪原", "bgm/Ice 1.ogg");

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