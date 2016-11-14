package org.pstale.asset.struct.chars;

public class CharMonsterInfo {
	public final static int FALLITEM_MAX = 200;
	public final static int FALLITEM2_MAX = 3;
	public final static int NPC_MESSAGE_MAX = 20;
	
	class RECT {
		long left;
		long top;
		long right;
		long bottom;
	}

	public String szName; // 名字char[32]
	public String enName;

	public short SkillDamage[] = { 0, 0 }; // 胶懦 傍拜仿
	public int SkillRange; // 胶懦 利侩 裹困
	public int SkillRating; // 胶懦 傍拜伏
	public int SkillDistance; // 利侩 胶懦狼 芭府
	public RECT SkillRangeRect; // 裹困 胶懦利侩矫 困摹
	public int SkillCurse; // 历林 吧扁

	public int AttackPattern; // 傍拜 菩畔
	public int ActiveHour; // 劝悼矫埃
	public int IQ; // 瘤瓷瘤荐
	public int Nature; // 前己
	public int Undead; // 攫单靛 蜡公
	public int GenerateGroup[] = { 0, 0 }; // 悼矫俊 免泅荐 ( 弥家 / 弥措 )
	public int GetExp; // 磷菌阑锭 版氰摹 何咯樊
	public int SpAttackPercetage; // 漂荐 傍拜 荤侩 犬伏
	public int MoveRange; // 捞悼裹困

	public int DamageStunPers; // 傍拜罐阑矫 胶畔犬伏

	public int PotionCount; // 拱距 焊蜡荐
	public int PotionPercent; // 拱距 焊蜡伏

	public int AllSeeItem; // 冻绢龙嫐 促焊牢促
	public int FallItemCount; // 冻绢哆副 酒捞袍甸狼 墨款磐
	public int FallItemPerMax; // 酒捞袍 积己 犬伏 弥措摹
	public int FallItemMax; // 悼矫俊 冻绢哆副 酒捞袍 弥措摹
	public int FallItemPlusCount; // 冻绢哆副 酒捞袍甸狼 墨款磐 (眠啊)

	public int ClassCode; // 阁胶磐 备喊 内靛

	public int UseEventModel; // 捞亥飘 葛胆 历厘蜡公

	public int Real_Sight; // 角力 矫具 芭府 ( 力蚌辟 酒丛 )

	public FallItem FallItems[] = new FallItem[FALLITEM_MAX];
	public FallItem FallItems_Plus[] = new FallItem[FALLITEM2_MAX]; // 眠啊酒捞袍

	// NPC 惑痢 魄概 沥焊
	public int SellAttackItemCount;
	public long SellAttackItem[] = new long[32]; // 傍拜 酒捞袍
	public int SellDefenceItemCount;
	public long SellDefenceItem[] = new long[32]; // 规绢 酒捞袍
	public int SellEtcItemCount;
	public long SellEtcItem[] = new long[32]; // 扁鸥 酒捞袍

	public int SkillMaster; // 胶懦 荐访 NPC
	public int SkillChangeJob; // 傈流 啊瓷 NPC
	public int WareHouseMaster; // 酒捞袍 焊包 NPC
	public int ItemMix; // 酒捞袍 炼钦 NPC
	public int ItemAging; // 酒捞袍 俊捞隆
	public int CollectMoney; // 捣葛陛 NPC
	public int EventGift; // 版前 捞亥飘 NPC
	public int Smelting; // pluto 力访 NPC
	public int Manufacture; // pluto 力累 NPC
	public int MixtureReset; // 籍瘤侩 - 雇胶媚 府悸 NPC

	public int EventCode; // 捞亥飘 内靛
	public int EventInfo; // 捞亥飘 沥焊
	public long dwEvnetItem; // 捞亥飘 酒捞袍
	public int EventNPC; // 捞亥飘 NPC 沥焊
	public int ClanNPC; // 努坊 NPC
	public int GiftExpress; // 版前 硅崔
	public int ForceOrbNPC;
	public int WingQuestNpc; // 喇 涅胶飘 NPC
	public int StarPointNpc; // 喊 器牢飘 NPC
	public int GiveMoneyNpc; // 捣 葛陛 NPC
	public int TelePortNpc; // 炮饭器飘 NPC
	public int BlessCastleNPC; // 喉饭胶 某浇 NPC
	public int PollingNpc; // 汲巩炼荤 NPC
	public int SoketNPC;// 打孔镶嵌

	public String szMediaPlayNPC_Title; // 悼康惑 NPC
	public String szMediaPlayNPC_Path; // 悼康惑 NPC

	public int QuestCode; // 涅胶飘 贸府内靛
	public int QuestParam; // 涅胶飘 颇扼皋磐

	public short OpenCount[] = { 0, 0 }; // 免泅 埃拜 锅龋

	public long dwAutoCharCode; // 磊悼某腐磐 备盒 内靛 (SpeedSum 蔼)

	public String NpcMessage[] = new String[NPC_MESSAGE_MAX]; // NPC 措拳 皋技瘤
	public int NpcMsgCount; // NPC 措拳 墨款磐

	public String NextFile;
	public String File;
}
