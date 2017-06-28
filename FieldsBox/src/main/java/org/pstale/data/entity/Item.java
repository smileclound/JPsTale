package org.pstale.data.entity;

import org.pstale.data.components.Attack;
import org.pstale.data.components.Increase;
import org.pstale.data.components.MoveSpeed;
import org.pstale.data.components.Regen;
import org.pstale.data.components.Require;
import org.pstale.data.components.Resistance;

public class Item {
	/**
	 * 武器的攻击方式
	 * @author yanmaoyuan
	 *
	 */
	enum AttackType {
		NOT_SHOOTING,// 近战 Melee
		SHOOTING,// 远程
		CASTING;// 施法
	}
	
	/**
	 * 武器
	 * @author yanmaoyuan
	 *
	 */
	enum Weapon {
		Axe,// 斧
		Claws,// 爪
		Dagger,// 匕首
		Hammer,// 锤
		MagicStuff,// 魔杖
		Shooter,// 射击类(弓,弩)
		Spear,// 矛，长枪，战镰刀
		Sword,// 剑
		Throwing// 投掷类(标枪)
	}
	
	/**
	 * 防具
	 * @author yanmaoyuan
	 *
	 */
	enum Defense {
		Armor,// 铠甲
		Boots,// 靴
		Gloves,// 护手
		Shield// 盾
	}
	
	/**
	 * 其他道具
	 * @author yanmaoyuan
	 *
	 */
	enum Others {
		Amulet,//护身符
		Armlet,//臂环
		EtherCore,// 以太核心：回城卷轴
		ForceOrb,// 力量石
		Potion,// 药剂
		Ring,// 戒指
		Sheltom,// 宝石?
	}
	
	Attack attack = null;// 攻击能力
	Resistance resist = null;// 防御能力
	Defense defense = null;// 防御能力
	MoveSpeed speed = null;// 移动能力
	Regen regon = null;// 再生能力
	Increase increase = null;// 数值增强
	
	Require require = null;// 装备需求

	public Attack getAttack() {
		return attack;
	}

	public Resistance getResist() {
		return resist;
	}

	public Defense getDefense() {
		return defense;
	}

	public MoveSpeed getSpeed() {
		return speed;
	}

	public Regen getRegon() {
		return regon;
	}

	public Increase getIncrease() {
		return increase;
	}

	public Require getRequire() {
		return require;
	}
	
}
