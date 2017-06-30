package org.pstale.entity;

import com.jme3.util.IntMap;

public class Player {
	
	public final static int LHAND = 1;// 左手
	public final static int RHAND = 2;// 右手
	public final static int ARMOR = 3;// 铠甲
	
	public final static int BOOTS = 4;// 鞋
	public final static int GLOVES = 5;// 护手
	public final static int ARMLET = 6;// 臂环
	
	public final static int LRING = 7;// 左戒
	public final static int RRING = 8;// 右戒
	public final static int AMULET = 9;//护身符
	public final static int SHELTOM = 10;// 符文石
	
	// 装备栏
	IntMap<Item> slots = new IntMap<Item>();
	
	Level lv;// 等级和经验
	State state;// 属性点
	Health hp;
	Mana mp;
	Attack atk;// 攻击能力
	Defense def;// 防御能力
	Increase inc;// 增强
	Regen regen;// 再生能力
	
	public State getState() {
		if (state != null) {
			state = new State();
		}
		
		return state;
	}
	
	public int getHp() {
		int hp = 0;
		// 计算装备上的生命值
		for(int i=0; i<10; i++) {
			Item item = slots.get(i);
			if (item != null && item.getIncrease() != null) {
				hp += item.getIncrease().getLife();
			}
		}
		
		// 1点体质2点生命值
		hp += getState().getHealth();
		
		return hp;
	}
	
	public int getMp() {
		int mp = 0;
		// 计算装备上的生命值
		for(int i=0; i<10; i++) {
			Item item = slots.get(i);
			if (item != null && item.getIncrease() != null) {
				mp += item.getIncrease().getMana();
			}
		}
		
		// 1点精神2点魔法值
		mp += getState().getSpirit() * 2;
		
		return mp;
	}
}
