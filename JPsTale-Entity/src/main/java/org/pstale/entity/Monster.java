package org.pstale.entity;

public class Monster {
	Level lv;// 等级和经验

	Health hp;// 生命值

	Attack atk;// 攻击能力
	Defense def;// 防御能力

	Regen regen;// 再生能力
	
	SkillBehavior sb;// 使用技能的行为
	HealBehavior hb;// 治疗自己的行为
}
