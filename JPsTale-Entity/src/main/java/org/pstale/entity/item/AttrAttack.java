package org.pstale.entity.item;

/**
 * 攻击能力
 * 
 * @author yanmaoyuan
 *
 */
public class AttrAttack {
    public int[] Damage = new int[4]; // 伤害值
    public int Shooting_Range; // 射程
    public int Attack_Speed; // 攻击速度
    public int Attack_Rating[] = new int[2]; // 攻击命中率
    public int Critical_Hit; // 1.5倍 必杀率
    public float Magic_Mastery[] = new float[2]; // 魔法熟练度

}
