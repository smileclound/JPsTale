package org.pstale.data.entity;

import org.pstale.data.components.*;

public class Monster {
    Name name;
    Model model;
    CharSound sound;

    Level lv;// 等级和经验
    Health hp;// 生命值
    State state;// 属性点

    Attack atk;// 攻击能力
    Defense def;// 防御能力

    Regen regen;// 再生能力

    SkillBehavior skill;// 使用技能的行为
    HealBehavior heal;// 治疗自己的行为
}
