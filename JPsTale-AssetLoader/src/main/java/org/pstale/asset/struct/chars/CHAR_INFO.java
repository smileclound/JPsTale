package org.pstale.asset.struct.chars;

import java.io.IOException;

import org.pstale.asset.struct.Flyweight;

import com.jme3.util.LittleEndien;

/**
 * STG_CHAR_INFO_SIZE = 504
 * 
 * size = 464
 * 
 * @author yanmaoyuan
 * 
 */
public class CHAR_INFO extends Flyweight {
    public String szName; // 名称 [32]
    public String szModelName; // 模型名称 [64]
    public String szModelName2; // 模型 名称2[60]
    public int ModelNameCode2; // 模型名称编码

    public int dwObjectSerial;

    public int ClassClan;
    public int State; // 角色状态 (NPC / ENEMY / USER )
    public int SizeLevel; // 尺寸 ( 0-小型 ~ 3-大型 )
    public int dwCharSoundCode; // 角色音效编码

    /*-------------------------*
     *			属性
     *--------------------------*/
    public int JOB_CODE; // 职业编码
    public int Level; // 等级
    public int Strength; // 力量
    public int Spirit; // 精神
    public int Talent; // 才能
    public int Dexterity; // 敏捷
    public int Health; // 体质

    /*-------------------------*
     *			战斗属性
     *--------------------------*/
    public int Accuracy; // 精确
    public int Attack_Rating; // 命中
    public int Attack_Damage[] = { 0, 0 }; // 攻击力 ( 最小 / 最大 )
    public int Attack_Speed; // 攻击速度
    public int Shooting_Range; // 攻击范围 ( 惯荤屈公扁 )
    public int Critical_Hit; // 攻击必杀 ( 1.5硅 单固瘤 犬伏 )

    public int Defence; // 防御
    public int Chance_Block; // 格挡率
    public int Absorption; // 吸收率

    public int Move_Speed; // 移动速度
    public int Sight; // 视野
    public short Weight[] = { 0, 0 }; // 负重

    /*-------------------------*
     *			元素
     *--------------------------*/
    public short Resistance[] = { 0, 0, 0, 0, 0, 0, 0, 0 }; // 元素抗性
    public short Attack_Resistance[] = { 0, 0, 0, 0, 0, 0, 0, 0 }; // 元素攻击

    /*-------------------------*
     *			数值
     *--------------------------*/
    public short Life[] = { 0, 0 }; // 生命力 0 当前 1 最大值
    public short Mana[] = { 0, 0 }; // 魔法力 0 当前 1 最大值
    public short Stamina[] = { 0, 0 }; // 耐久力 0 当前 1 最大值

    public float Life_Regen; // 生命再生
    public float Mana_Regen; // 魔法再生
    public float Stamina_Regen; // 耐力再生

    public int Exp; // 经验值
    public int Next_Exp; // 下一级所需经验值

    public int Money;

    // CharMonsterInfo *lpMonInfo; // 怪物数据

    public int Brood; // 种族

    public int StatePoint;
    public byte bUpdateInfo[] = { 0, 0, 0, 0 };
    public short ArrowPosi[] = { 0, 0 };
    public int Potion_Space; // 药水容量

    public int LifeFunction; // 生命函数
    public int ManaFunction; // 魔力函数
    public int StaminaFunction; // 耐力函数
    public short DamageFunction[] = { 0, 0 }; // 0 近战 1 远程 2 魔法

    public int RefomCode;

    public int ChangeJob;
    public int JobBitMask;

    public short wPlayerKilling[] = { 0, 0 };
    public short wPlayClass[] = { 0, 0 };

    public int Exp_High;
    public int dwEventTime_T;
    public short sEventParam[] = { 0, 0 };
    public short sPresentItem[] = { 0, 0 };

    // 厘喊 - 弊扼厚萍 胶农费
    public short GravityScroolCheck[] = { 0, 0 };

    public int dwTemp[] = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

    public int dwLoginServerIP; // 肺弊牢茄 辑滚狼 IP
    public int dwLoginServerSafeKey; // 肺弊牢茄 辑滚俊辑 焊辰 焊救虐

    public short wVersion[] = { 0, 0 }; // 捞 备炼眉狼 滚傈

    @Override
    public void loadData(LittleEndien in) throws IOException {
        szName = getString(in, 32);
        szModelName = getString(in, 64);
        szModelName2 = getString(in, 60);
        ModelNameCode2 = in.readInt();

        dwObjectSerial = in.readInt();

        ClassClan = in.readInt();
        State = in.readInt();
        SizeLevel = in.readInt();
        dwCharSoundCode = in.readInt();

        JOB_CODE = in.readInt();
        Level = in.readInt();
        Strength = in.readInt();
        Spirit = in.readInt();
        Talent = in.readInt();
        Dexterity = in.readInt();
        Health = in.readInt();

        Accuracy = in.readInt();
        Attack_Rating = in.readInt();
        Attack_Damage[0] = in.readInt();
        Attack_Damage[1] = in.readInt();
        Attack_Speed = in.readInt();
        Shooting_Range = in.readInt();
        Critical_Hit = in.readInt();

        Defence = in.readInt();
        Chance_Block = in.readInt();
        Absorption = in.readInt();

        Move_Speed = in.readInt();

        Sight = in.readInt();

        Weight[0] = in.readShort();
        Weight[1] = in.readShort();

        Resistance = new short[8];
        for (int i = 0; i < 8; i++) {
            Resistance[i] = in.readShort();
        }
        Attack_Resistance = new short[8];
        for (int i = 0; i < 8; i++) {
            Attack_Resistance[i] = in.readShort();
        }

        Life[0] = in.readShort();
        Life[1] = in.readShort();
        Mana[0] = in.readShort();
        Mana[1] = in.readShort();
        Stamina[0] = in.readShort();
        Stamina[1] = in.readShort();

        Life_Regen = in.readFloat();
        Mana_Regen = in.readFloat();
        Stamina_Regen = in.readFloat();

        Exp = in.readInt();
        Next_Exp = in.readInt();

        Money = in.readInt();

        in.readInt();// lpMonInfo

        Brood = in.readInt();
        StatePoint = in.readInt();
        bUpdateInfo = new byte[] { in.readByte(), in.readByte(), in.readByte(), in.readByte() };

        ArrowPosi[0] = in.readShort();
        ArrowPosi[1] = in.readShort();

        Potion_Space = in.readInt();

        LifeFunction = in.readInt();
        ManaFunction = in.readInt();
        StaminaFunction = in.readInt();
        DamageFunction[0] = in.readShort();
        DamageFunction[1] = in.readShort();

        RefomCode = in.readInt();
        ChangeJob = in.readInt();
        JobBitMask = in.readInt();

        wPlayerKilling[0] = in.readShort();
        wPlayerKilling[1] = in.readShort();
        wPlayClass[0] = in.readShort();
        wPlayClass[1] = in.readShort();

        Exp_High = in.readInt();
        dwEventTime_T = in.readInt();

        sEventParam[0] = in.readShort();
        sEventParam[1] = in.readShort();
        sPresentItem[0] = in.readShort();
        sPresentItem[1] = in.readShort();
        GravityScroolCheck[0] = in.readShort();
        GravityScroolCheck[1] = in.readShort();

        dwTemp = new int[11];
        for (int i = 0; i < 11; i++) {
            dwTemp[i] = in.readInt();
        }
        this.dwLoginServerIP = in.readInt();
        this.dwLoginServerSafeKey = in.readInt();
        this.wVersion[0] = in.readShort();
        this.wVersion[1] = in.readShort();
    }
}
