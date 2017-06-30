package org.pstale.entity.item;

/**
 * 药剂效果
 * 
 * @author yanmaoyuan
 *
 */
public class AttrPotionEffect {

    /* 回复能力 */
    public short[] Mana = new short[2]; // 法力(最小)(最大)
    public short[] Life = new short[2]; // 生命恢复(最小)(最大)
    public short[] Stamina = new short[2]; // 耐力恢复(最小)(最大)
}
