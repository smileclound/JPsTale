package org.pstale.components;

/**
 * 装备属性需求
 * @author yanmaoyuan
 *
 */
public class ItemRequire {
	
	int level; // 等级
	int strength; // 力量
	int spirit; // 精神
	int talent; // 才能
	int dexterity; // 敏捷
	int health; // 体质
	
	public ItemRequire() {
		level = 0;
		strength = 0;
		spirit = 0;
		talent = 0;
		dexterity = 0;
		health = 0;
	}
	
	public ItemRequire(int lv, int str, int spirit, int talent, int dex, int vit) {
		this.level = lv;
		this.strength = str;
		this.spirit = spirit;
		this.talent = talent;
		this.dexterity = dex;
		this.health = vit;
	}


	
}