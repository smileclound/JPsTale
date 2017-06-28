package net.jmecn.asset.chars;

public class CharInfo implements CharInfoConstant {
	public String szName; // 名称 [32]
	public String szModelName; // 模型 名称 [64]
	public String szModelName2; // 模型 名称2[60]
	public long ModelNameCode2; // 模型名称编码

	public long dwObjectSerial; // 按眉狼 绊蜡蔼

	// int AutoPlayer; //磊悼 加己
	public int ClassClan; // 家加努罚
	public int State; // 角色状态 ( smCHAR_STATE_ NPC / ENEMY / USER )
	public int SizeLevel; // 尺寸 ( 0-小型 ~ 3-大型 )
	public long dwCharSoundCode; // 角色音效编码

	/*-------------------------*
	 *			属性
	 *--------------------------*/
	public long JOB_CODE; // 职业编码
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

	public int Money; // 焊蜡茄 捣

	public CharMonsterInfo lpMonInfo; // 怪物数据 TODO 这个是否应该删掉？

	public long Brood; // 种族

	public int StatePoint; // 瓷仿摹 器牢飘
	public byte bUpdateInfo[] = { 0, 0, 0, 0 }; // 郴侩 函版 墨款磐
	public short ArrowPosi[] = { 0, 0 }; // 牢亥配府 拳混钎 困摹
	public int Potion_Space; // 药水容量

	public int LifeFunction; // 生命函数
	public int ManaFunction; // 魔力函数
	public int StaminaFunction; // 耐力函数
	public short DamageFunction[] = { 0, 0, 0 }; // 0 近战 1 远程 2 魔法

	public long RefomCode; // 府汽 内靛

	public long ChangeJob;
	public long JobBitMask;

	public long wPlayerKilling[] = { 0, 0 }; // 皑苛挨囚 乐澜
	public int wPlayClass[] = { 0, 0 }; // 拌鞭 ( 阁胶磐狼 焊胶汲沥 )

	public int Exp_High; // 版氰摹 惑困4厚飘
	public long dwEventTime_T; // 捞亥飘 力茄 矫埃 - ( 倔奴捞 矫埃 )
	public short sEventParam[] = { 0, 0 }; // 捞亥飘 颇扼皋磐侩 [0] 捞亥飘内靛 [1] 捞亥飘 颇扼皋磐

	public short sPresentItem[] = { 0, 0 }; // 快楷阑 啊厘茄 鞘楷利牢 酒捞袍 瘤鞭

	// 厘喊 - 弊扼厚萍 胶农费
	public short GravityScroolCheck[] = { 0, 0 };

	public long dwTemp[] = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }; // 抗厚 滚欺 // 厘喊 -
																// 弊扼厚萍
																// 胶农费(12->11)

	public long dwLoginServerIP; // 肺弊牢茄 辑滚狼 IP
	public long dwLoginServerSafeKey; // 肺弊牢茄 辑滚俊辑 焊辰 焊救虐

	public long wVersion[] = { 0, 0 }; // 捞 备炼眉狼 滚傈

	// 厘喊 - 抗厚滚欺
	// DWORD dwTemp2[2000];
}
