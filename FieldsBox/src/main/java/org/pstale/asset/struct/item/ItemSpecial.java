package org.pstale.asset.struct.item;

/**
 * 装备特效
 * 
 */
public class ItemSpecial {
	public float Add_Absorb[] = new float[2]; // 吸收
	public int Add_Defence[] = new int[2]; // 防御
	public float Add_Speed[] = new float[2]; // 移动速度
	public float Add_Block_Rating; // (盾)格挡率
	public int Add_Attack_Speed; // 攻击速度
	public int Add_Critical_Hit; // 1.5倍 致命几率
	public int Add_Shooting_Range; // 射程
	public float Add_Magic_Mastery[] = new float[2]; // 魔法熟练度
	public short[] Add_Resistance = new short[8]; // 元素抗性

	// //////////////////////////////////////////////

	public short[] Lev_Attack_Resistance = new short[8]; // 元素抗性
	public int Lev_Mana; // 法力(最小)(最大)
	public int Lev_Life; // 生命(最小)(最大)
	public int[] Lev_Attack_Rating = new int[2]; // 命中
	public int[] Lev_Damage = new int[2]; // 攻击

	public float Add_Per_Mana_Regen[] = new float[2]; // 每秒法力回复
	public float Add_Per_Life_Regen[] = new float[2]; // 每秒生命回复
	public float Add_Per_Stamina_Regen[] = new float[2]; // 每秒耐力回复
}