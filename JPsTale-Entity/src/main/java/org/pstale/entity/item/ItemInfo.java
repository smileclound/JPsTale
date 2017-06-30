package org.pstale.entity.item;

public class ItemInfo {
    public final static int SPECIAL_JOB_RANDOM_MAX = 12;

    public ItemInfo() {
        itemHeader = new ItemCreate();
        require = new ItemRequire();

        durability = new AttrDurability();// 耐久度

        resistance = new AttrResistance();// 元素抗性

        attack = new AttrAttack();// 攻击能力

        defence = new AttrDefence();// 防守能力

        speed = new AttrSpeed();// 移动能力

        reovery = new AttrRecovery();// 特殊能力

        potionEffect = new AttrPotionEffect();// 药剂效果

        jobItem = new ItemSpecial();// 特效
    }

    long dwSize; // 物品结构尺寸

    ItemCreate itemHeader; // 物品创建的信息

    public AttrDurability durability; // 耐久度

    // ////// 实际装备数据信息 START /////////

    /*-------------------------*
     *		 装备信息
     *--------------------------*/
    public long CODE; // Item CODE
    public String localeName; // Item Name
    public String enName; // Item Name
    public String code;

    /*-------------------------*
     *		共同事项
     *--------------------------*/
    public int Weight; // 重量
    public int Price; // 价格

    // ///////////////////////////////////////
    int Index; // Index
    int PotionCount;
    // Potion counter
    // //////////////////////////////////////

    public AttrResistance resistance; // 元素抗性

    int Sight; // 视野

    public AttrAttack attack;// 攻击能力
    public AttrDefence defence;// 防守能力
    public AttrSpeed speed;// 移动能力

    /*-------------------------*
     *		 手镯性能
     *--------------------------*/
    public int Potion_Space; // 存放药水数量

    public AttrRecovery reovery;// 回复能力

    /*-------------------------*
     *		角色装备需求
     *--------------------------*/
    public ItemRequire require;

    public AttrPotionEffect potionEffect;// 药剂效果

    /*-------------------------*
     *		 .....
     *--------------------------*/
    int Money; // 拥有钱
    int NotUseFlag; // 禁用标志

    // long BackUpKey; // 备份头
    // long BackUpChkSum; // 备份装备校验

    public short[] ScaleBlink = new short[2]; // 规模的变化
    public long UniqueItem; // 独特
    public short[] EffectBlink = new short[2]; // 色彩显示循环 (0) 效果检验(1)
    public short[] EffectColor = new short[4]; // 色彩显示模式

    public long DispEffect; // 装备显示效果 (时间表)

    /*-------------------------*
     *		角色装备特效    (目前没有设置)
     *--------------------------*/

    public String JobName;// TODO for test
    public String RandomJobName;// TODO for test

    public long dwJobBitCode_Random[] = new long[SPECIAL_JOB_RANDOM_MAX]; // 罚待漂拳
                                                                          // 流诀
                                                                          // 格废
    public int JobBitCodeRandomCount;

    public long JobCodeMask; // 职业特效 (职业位掩码)
    public ItemSpecial jobItem; // 特效

    long ItemKindCode; // 装备类型 0代表普通装备
    long ItemKindMask;

    short[] ItemAgingNum = new short[2]; // 0 锻造+次数 1 锻造or not?
    short[] ItemAgingCount = new short[2]; // 0-Items skilled count 1-Skill
                                           // maximum
    short[] ItemAgingProtect = new short[2];// Aging protection

    short[] SpecialItemFlag = new short[2]; // 0-被诅咒的物品 flag 1-同任务特效 flag
                                            // Special items that are used

    long dwCreateTime; // 创建时间

    public int GenDay;// TODO for test
    public String NextFile;// TODO for test
    public String File;// TODO for test
}
