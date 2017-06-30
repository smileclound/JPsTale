package com.jme3.script.plugins.item;

import static org.pstale.entity.chars.JobConstant.JobDataBase;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.pstale.entity.item.ItemInfo;

import com.jme3.script.plugins.PTScriptLoader;

/**
 * 服务器的装备信息加载
 * 
 * @author yanmaoyuan
 * 
 */
public class ItemLoader extends PTScriptLoader<ItemInfo> {

    static Logger log = Logger.getLogger(ItemLoader.class);

    /**
     * 有两件装备不是通过文件加载的，而是直接写死的。
     * <pre>
     * if (this instanceof ItemInitilize) {
     * 
     *     ItemInfo gold = new ItemInfo();
     *     gold.code = "\"GG101\"";
     *     gold.CODE = 0x05010100;
     *     gold.localeName = "\"Gold\"";
     *     gold.Weight = 0;
     *     list.add((T) gold);
     * 
     *     ItemInfo exp = new ItemInfo();
     *     exp.code = "\"GG102\"";
     *     exp.CODE = 0x05010200;
     *     exp.localeName = "\"Exp\"";
     *     exp.Weight = 0;
     *     list.add((T) exp);
     * 
     * }
     * 
     * </pre>
     */
    // ///////// NAME ///////////
    public final static String LOCALE_NAME = "*捞抚|*名字";
    public final static String EN_NAME = "*Name|*NAME";
    public final static String CODE = "*内靛|*代码";

    public final static String UniqueItem = "*蜡聪农|*特殊";
    public final static String EffectBlink = "*蜡聪农祸惑|*特殊颜色";// RGBA + Blink 5
    public final static String DispEffect = "*捞棋飘汲沥|*效果设置";

    // ////////// Common //////////
    public final static String Durability = "*郴备仿|*耐久";
    public final static String Weight = "*公霸|*重量";
    public final static String Price = "*啊拜|*价格";

    // ////////// Elements ///////////
    public final static String Resistance_Bionic = "*积眉";
    public final static String Resistance_Earth = "*措磊楷|*魔属性";
    public final static String Resistance_Fire = "*阂|*火属性";
    public final static String Resistance_Ice = "*趁扁|*冰属性";
    public final static String Resistance_Lighting = "*锅俺|*雷属性";
    public final static String Resistance_Poison = "*刀|*毒属性";
    public final static String Resistance_Water = "*拱";
    public final static String Resistance_Wind = "*官恩";

    // ////////// Damage //
    public final static String Damage = "*傍拜仿|*攻击力";
    public final static String Shooting_Range = "*荤沥芭府|*攻击范围";
    public final static String Attack_Speed = "*傍拜加档|*攻击速度";
    public final static String Attack_Rating = "*疙吝仿|*命中";
    public final static String Critical_Hit = "*农府萍拿|*攻击必杀";
    public final static String Magic_Mastery = "*付过槛访档|*魔法熟练";

    // ////////// Defence
    public final static String Absorb = "*软荐仿|*吸收";
    public final static String Defence = "*规绢仿|*防御";
    public final static String Block_Rating = "*喉钒啦|*格档";

    // ////////// Boots
    public final static String Speed = "*捞悼加档|*移动速度";

    // ////////// Armlet
    public final static String Potion_Space = "*焊蜡傍埃|*药水存量";

    // ////////// Recovery
    public final static String Mana_Regen = "*扁仿犁积|*付唱犁积|*魔法再生";
    public final static String Life_Regen = "*积疙仿犁积|*扼捞橇犁积|*生命再生";
    public final static String Stamina_Regen = "*辟仿犁积|*胶抛固唱犁积|*耐力再生";
    public final static String Increase_Mana = "*扁仿眠啊|*付唱眠啊|*魔法增加";
    public final static String Increase_Life = "*积疙仿眠啊|*扼捞橇眠啊|*生命增加";
    public final static String Increase_Stamina = "*辟仿眠啊|*胶抛固唱眠啊|*耐力增加";

    // //////// Requirements
    public final static String Require_Level = "*饭骇|*等级";
    public final static String Require_Strength = "*塞|*力量";
    public final static String Require_Spirit = "*沥脚仿|*精神";
    public final static String Require_Talent = "*犁瓷|*才能";
    public final static String Require_Dexterity = "*刮酶己|*敏捷";
    public final static String Require_Health = "*扒碍|*体质";

    // ///////// 药水效果 ////////////
    public final static String Stamina = "*辟仿惑铰|*胶抛固呈惑铰|*耐力提高";
    public final static String Mana = "*扁仿惑铰|*付唱惑铰|*魔法提高";
    public final static String Life = "*积疙仿惑铰|*扼捞橇惑铰|*生命提高";

    // Special Job
    public final static String JobCodeMask = "**漂拳|**特效";
    public final static String RandomJobName = "**漂拳罚待|**随机特效";

    // Special Defense
    public final static String Add_Absorb = "**软荐仿|**吸收";
    public final static String Add_Defence = "**规绢仿|**防御";
    public final static String Add_Block_Rating = "**喉钒啦|**格档";

    // Special Speed
    public final static String Add_Speed = "**捞悼加档|**移动速度";
    // Special Damage
    public final static String Add_Attack_Speed = "**傍拜加档|**攻击速度";
    public final static String Add_Critical_Hit = "**农府萍拿|**攻击必杀";
    public final static String Add_Shooting_Range = "**荤沥芭府|**攻击范围";
    public final static String Add_Magic_Mastery = "**付过槛访档|**魔法熟练";

    // Special Add By Level
    public final static String Lev_Mana = "**扁仿眠啊|**付唱眠啊|**魔法增加";
    public final static String Lev_Life = "**积疙仿眠啊|**扼捞橇眠啊|**生命增加";
    public final static String Lev_Attack_Rating = "**疙吝仿|**命中";
    public final static String Lev_Damage = "**傍拜仿|**攻击力";

    // Special Recovery
    public final static String Add_Per_Mana_Regen = "**扁仿犁积|**付唱犁积|**魔法再生";
    public final static String Add_Per_Life_Regen = "**积疙仿犁积|**扼捞橇犁积|**生命再生";
    public final static String Add_Per_Stamina_Regen = "**辟仿犁积|**胶抛固唱犁积|**耐力再生";

    // ///////// ???
    public final static String GenDay = "*惯积力茄";
    public final static String NextFile = "*楷搬颇老|*连接文件";

    @Override
    protected ItemInfo decode() throws IOException {
        // 逐行解析文件数据
        ItemInfo iteminfo = new ItemInfo();
        iteminfo.UniqueItem = 1;
        while (nextLine()) {
            if (line.length() == 0 || line.startsWith("//") || token[0].length() == 0) {
                continue;
            }

            // ///////// NAME ///////////
            if (startWith(LOCALE_NAME)) {
                iteminfo.localeName = getString();
            }

            if (startWith(EN_NAME)) {
                iteminfo.enName = getString();
            }

            if (startWith(CODE)) {
                iteminfo.code = getString();
            }

            if (startWith(UniqueItem)) {
                iteminfo.UniqueItem = getInt();
            }

            if (startWith(EffectBlink, 0)) {
                if (token.length > 5) {
                    iteminfo.EffectColor[0] = getShort(0);// R
                    iteminfo.EffectColor[1] = getShort(1);// G
                    iteminfo.EffectColor[2] = getShort(2);// B
                    iteminfo.EffectColor[3] = getShort(3);// A
                    iteminfo.EffectBlink[0] = getShort(4);// Blink
                    if (token.length > 6) {
                        iteminfo.ScaleBlink[0] = (short) (getFloat(5) * 256);
                    }
                }
            }

            if (startWith(DispEffect)) {
                iteminfo.DispEffect = getInt();
            }

            // ////////// Common //////////
            if (startWith(Durability, 2)) {
                iteminfo.durability.durabilityCur = getInt(0);
                iteminfo.durability.durabilityMax = getInt(1);
            }
            if (startWith(Weight)) {
                iteminfo.Weight = getInt();
            }
            if (startWith(Price)) {
                iteminfo.Price = getInt();
            }

            // ////////// Resistance 元素抗性
            if (startWith(Resistance_Bionic, 2)) {
                iteminfo.resistance.Bionic[0] = getInt(0);
                iteminfo.resistance.Bionic[1] = getInt(1);
            }
            if (startWith(Resistance_Earth, 2)) {
                iteminfo.resistance.Earth[0] = getInt(0);
                iteminfo.resistance.Earth[1] = getInt(1);
            }
            if (startWith(Resistance_Fire, 2)) {
                iteminfo.resistance.Fire[0] = getInt(0);
                iteminfo.resistance.Fire[1] = getInt(1);
            }
            if (startWith(Resistance_Ice, 2)) {
                iteminfo.resistance.Ice[0] = getInt(0);
                iteminfo.resistance.Ice[1] = getInt(1);
            }
            if (startWith(Resistance_Lighting, 2)) {
                iteminfo.resistance.Lighting[0] = getInt(0);
                iteminfo.resistance.Lighting[1] = getInt(1);
            }
            if (startWith(Resistance_Poison, 2)) {
                iteminfo.resistance.Poison[0] = getInt(0);
                iteminfo.resistance.Poison[1] = getInt(1);
            }
            if (startWith(Resistance_Water, 2)) {
                iteminfo.resistance.Water[0] = getInt(0);
                iteminfo.resistance.Water[1] = getInt(1);
            }
            if (startWith(Resistance_Wind, 2)) {
                iteminfo.resistance.Wind[0] = getInt(0);
                iteminfo.resistance.Wind[1] = getInt(1);
            }

            // ////////// Damage 伤害能力
            if (startWith(Damage, 4)) {
                iteminfo.attack.Damage[0] = getInt(0);
                iteminfo.attack.Damage[1] = getInt(1);
                iteminfo.attack.Damage[2] = getInt(2);
                iteminfo.attack.Damage[3] = getInt(3);
            }
            if (startWith(Shooting_Range)) {
                iteminfo.attack.Shooting_Range = getInt();
            }
            if (startWith(Attack_Speed)) {
                iteminfo.attack.Attack_Speed = getInt();
            }
            if (startWith(Attack_Rating, 2)) {
                iteminfo.attack.Attack_Rating[0] = getInt(0);
                iteminfo.attack.Attack_Rating[1] = getInt(1);
            }
            if (startWith(Critical_Hit)) {
                iteminfo.attack.Critical_Hit = getInt();
            }
            if (startWith(Magic_Mastery, 2)) {
                iteminfo.attack.Magic_Mastery[0] = getFloat(0);
                iteminfo.attack.Magic_Mastery[1] = getFloat(1);
            }

            // ////////// Defence
            if (startWith(Absorb, 2)) {
                iteminfo.defence.Absorb[0] = getFloat(0);
                iteminfo.defence.Absorb[1] = getFloat(1);
            }
            if (startWith(Defence, 2)) {
                iteminfo.defence.Defence[0] = getInt(0);
                iteminfo.defence.Defence[1] = getInt(1);
            }
            if (startWith(Block_Rating, 2)) {
                iteminfo.defence.Block_Rating[0] = getFloat(0);
                iteminfo.defence.Block_Rating[1] = getFloat(1);
            }

            // ////////// Boots 移动能力
            if (startWith(Speed, 2)) {
                iteminfo.speed.Speed[0] = getFloat(0);
                iteminfo.speed.Speed[1] = getFloat(1);
            }

            // ////////// Armlet
            if (startWith(Potion_Space)) {
                iteminfo.Potion_Space = getInt();
            }

            // ////////// Recovery
            if (startWith(Mana_Regen, 2)) {
                iteminfo.reovery.Mana_Regen[0] = getFloat(0);
                iteminfo.reovery.Mana_Regen[1] = getFloat(1);
            }
            if (startWith(Life_Regen, 2)) {
                iteminfo.reovery.Life_Regen[0] = getFloat(0);
                iteminfo.reovery.Life_Regen[1] = getFloat(1);
            }
            if (startWith(Stamina_Regen, 2)) {
                iteminfo.reovery.Stamina_Regen[0] = getFloat(0);
                iteminfo.reovery.Stamina_Regen[1] = getFloat(1);
            }
            if (startWith(Increase_Mana, 2)) {
                iteminfo.reovery.Increase_Mana[0] = getFloat(0);
                iteminfo.reovery.Increase_Mana[1] = getFloat(1);
            }
            if (startWith(Increase_Life, 2)) {
                iteminfo.reovery.Increase_Life[0] = getFloat(0);
                iteminfo.reovery.Increase_Life[1] = getFloat(1);
            }
            if (startWith(Increase_Stamina, 2)) {
                iteminfo.reovery.Increase_Stamina[0] = getFloat(0);
                iteminfo.reovery.Increase_Stamina[1] = getFloat(1);
            }

            // //////// Requirements 装备需求
            if (startWith(Require_Level)) {
                iteminfo.require.level = getInt();
            }
            if (startWith(Require_Strength)) {
                iteminfo.require.strength = getInt();
            }
            if (startWith(Require_Spirit)) {
                iteminfo.require.spirit = getInt();
            }
            if (startWith(Require_Talent)) {
                iteminfo.require.talent = getInt();
            }
            if (startWith(Require_Dexterity)) {
                iteminfo.require.dexterity = getInt();
            }
            if (startWith(Require_Health)) {
                iteminfo.require.health = getInt();
            }

            // ///////////////////// 药剂效果
            if (startWith(Stamina, 2)) {
                iteminfo.potionEffect.Stamina[0] = getShort(0);
                iteminfo.potionEffect.Stamina[1] = getShort(1);
            }
            if (startWith(Mana, 2)) {
                iteminfo.potionEffect.Mana[0] = getShort(0);
                iteminfo.potionEffect.Mana[1] = getShort(1);
            }
            if (startWith(Life, 2)) {
                iteminfo.potionEffect.Life[0] = getShort(0);
                iteminfo.potionEffect.Life[1] = getShort(1);
            }

            // //////////////////// Special Job
            if (startWith(JobCodeMask)) {// 特效
                iteminfo.JobName = getString();

                boolean find = false;
                int cnt = 0;
                while (!find && cnt < JobDataBase.length) {
                    if (token[1].equalsIgnoreCase(JobDataBase[cnt].szName)) {
                        iteminfo.JobCodeMask = JobDataBase[cnt].JobBitCode;
                        find = true;
                    }
                    cnt++;
                }
            }

            if (startWith(RandomJobName)) {// 随机特效
                iteminfo.RandomJobName = getString();

                for (int i = 1; i < token.length; i++) {
                    boolean find = false;
                    int cnt = 0;
                    while (!find && cnt < JobDataBase.length) {
                        if (token[i].equalsIgnoreCase(JobDataBase[cnt].szName)) {
                            if (iteminfo.JobBitCodeRandomCount < ItemInfo.SPECIAL_JOB_RANDOM_MAX) {
                                iteminfo.dwJobBitCode_Random[iteminfo.JobBitCodeRandomCount++] = JobDataBase[cnt].JobBitCode;
                                find = true;
                            }
                        }
                        cnt++;
                    }
                }
            }

            // //////////// Special Defense
            if (startWith(Add_Absorb, 2)) {
                iteminfo.jobItem.Add_Absorb[0] = getFloat(0);
                iteminfo.jobItem.Add_Absorb[1] = getFloat(1);
            }
            if (startWith(Add_Defence, 2)) {
                iteminfo.jobItem.Add_Defence[0] = getInt(0);
                iteminfo.jobItem.Add_Defence[1] = getInt(1);
            }
            if (startWith(Add_Block_Rating)) {
                iteminfo.jobItem.Add_Block_Rating = getInt();
            }
            if (startWith(Add_Speed, 2)) {
                iteminfo.jobItem.Add_Speed[0] = getFloat();
                iteminfo.jobItem.Add_Speed[1] = getFloat(1);
            }

            // Specail Damage
            if (startWith(Add_Attack_Speed)) {
                iteminfo.jobItem.Add_Attack_Speed = getInt();
            }
            if (startWith(Add_Critical_Hit)) {
                iteminfo.jobItem.Add_Critical_Hit = getInt();
            }
            if (startWith(Add_Shooting_Range)) {
                iteminfo.jobItem.Add_Shooting_Range = getInt();
            }

            // ////////// Special Add By Level
            if (startWith(Lev_Mana)) {
                iteminfo.jobItem.Lev_Mana = getInt();
            }
            if (startWith(Lev_Life)) {
                iteminfo.jobItem.Lev_Life = getInt();
            }
            if (startWith(Lev_Attack_Rating, 2)) {
                iteminfo.jobItem.Lev_Attack_Rating[0] = getInt(0);
                iteminfo.jobItem.Lev_Attack_Rating[1] = getInt(1);
            }
            if (startWith(Lev_Damage, 2)) {
                iteminfo.jobItem.Lev_Damage[0] = getInt(0);
                iteminfo.jobItem.Lev_Damage[1] = getInt(1);
            }

            // ////////// Special Recovery
            if (startWith(Add_Magic_Mastery, 2)) {
                iteminfo.jobItem.Add_Magic_Mastery[0] = getFloat(0);
                iteminfo.jobItem.Add_Magic_Mastery[1] = getFloat(1);
            }
            if (startWith(Add_Per_Mana_Regen, 2)) {
                iteminfo.jobItem.Add_Per_Mana_Regen[0] = getFloat(0);
                iteminfo.jobItem.Add_Per_Mana_Regen[1] = getFloat(1);
            }
            if (startWith(Add_Per_Life_Regen, 2)) {
                iteminfo.jobItem.Add_Per_Life_Regen[0] = getFloat(0);
                iteminfo.jobItem.Add_Per_Life_Regen[1] = getFloat(1);
            }
            if (startWith(Add_Per_Stamina_Regen, 2)) {
                iteminfo.jobItem.Add_Per_Stamina_Regen[0] = getFloat(0);
                iteminfo.jobItem.Add_Per_Stamina_Regen[1] = getFloat(1);
            }

            // ///////// ???
            if (startWith(GenDay)) {
                // TODO lpDefItem->sGenDay[0] = atoi(strBuff);
                iteminfo.GenDay = getInt();
            }
            if (startWith(NextFile)) {
                // TODO check if file exists
                iteminfo.NextFile = getString();
            }
        }

        return iteminfo;
    }

}
