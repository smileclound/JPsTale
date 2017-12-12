monster = [
	ID:'79_aveliskqueen',
	Name:'魔弓女王',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:79,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:2000,
		// Attack Status
		Atk:[44, 54],
		AtkSpeed:7,
		Range:250,
		Rating:1200,

		// Defence Status
		Flee:540,
		Absorb:16,
		Block:0,
		DamageStunPers:100,

		// Resistance
		Earth:50,
		Fire:0,
		Ice:65,// Water
		Lighting:10,// Wind
		Poison:30,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:9,
		Real_Sight:380,
		Talks:[],

		// Move Behavier
		Move_Speed:3,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:20,
		SkillDamage:[0, 0],
		SkillDistance:0,
		SkillRange:0,
		SkillRating:0,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:0,
		PotionCount:0,
	],

	Looks:[
		ClassCode:0,
		ArrowPosi:[0, 30],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:-1,
		Model:'char/monster/Monavellriskstf/monavelriskstf.ini',
		Sound:0x00001280,
	],

	// Drops
	AllSeeItem:false,
	Exp:27400,
	Quantity:1,
	drops:[
		[probability:2100, code:"NULL"/* Drops nothing */],
		[probability:3000, code:"GG101", value:[230, 270]/* Gold */],
		[probability:1250, code:"PL104"/* 顶级恢复生命药水 */],
		[probability:1250, code:"PM104"/* 顶级恢复魔法药水 */],
		[probability:52, code:"DA108"/* 精制链铠 */],
		[probability:52, code:"DA208"/* 圣女袍 */],
		[probability:52, code:"WA107"/* 刺脊斧 */],
		[probability:52, code:"WC107"/* 平手刃 */],
		[probability:52, code:"WH108"/* 圣光锤 */],
		[probability:52, code:"WM108"/* 公正之杖 */],
		[probability:52, code:"WP108"/* 血烟长矛 */],
		[probability:52, code:"WS109"/* 点金手弩 */],
		[probability:52, code:"WS209"/* 镇妖剑 */],
		[probability:52, code:"WT108"/* 金标 */],
		[probability:52, code:"DB107"/* 冰火靴 */],
		[probability:52, code:"DS107"/* 金刚盾 */],
		[probability:52, code:"OM107"/* 火星 */],
		[probability:52, code:"DG107"/* 百裂护手 */],
		[probability:52, code:"OA207"/* 乌金臂环 */],
		[probability:52, code:"EC102"/* 回城卷 */],
		[probability:52, code:"OR109"/* 黑暗之戒 */],
		[probability:52, code:"OA109"/* 海蓝石链 */],
		[probability:52, code:"OS106"/* 玄风石 */],
		[probability:40, code:"DA109"/* 黄铜战铠 */],
		[probability:40, code:"DA209"/* 学徒披风 */],
		[probability:40, code:"WA108"/* 叼刚 清磐 */],
		[probability:40, code:"WC108"/* 矩记飘 府亥瘤 */],
		[probability:40, code:"WH109"/* 固萍绢扼捞飘 */],
		[probability:40, code:"WM109"/* 绊胶飘 */],
		[probability:40, code:"WP109"/* 单喉 荤捞靛 */],
		[probability:40, code:"WS110"/* 固胶飘 */],
		[probability:40, code:"WS210"/* 敲饭萍逞 家靛 */],
		[probability:40, code:"WT109"/* 酒唱能促 */],
		[probability:40, code:"DB108"/* 百战靴 */],
		[probability:40, code:"DS108"/* 赤龙焰盾 */],
		[probability:40, code:"OM108"/* 阳炎 */],
		[probability:40, code:"DG108"/* 大地护手 */],
		[probability:40, code:"OA208"/* 百炼臂环 */],
		[probability:40, code:"EC102"/* 回城卷 */],
		[probability:40, code:"OR110"/* 伏魔戒指 */],
		[probability:40, code:"OA110"/* 镇魂铃 */],
		[probability:40, code:"OS106"/* 玄风石 */],
		[probability:40, code:"EC105"/* 公会卷轴 */],
		[probability:19, code:"DA110"/* 百裂铠 */],
		[probability:19, code:"DA210"/* 信徒披风 */],
		[probability:19, code:"WA109"/* 破山斧 */],
		[probability:19, code:"WC109"/* 兽之斧刃 */],
		[probability:19, code:"WH110"/* 轩辕巨锤 */],
		[probability:19, code:"WM110"/* 圣者杖 */],
		[probability:19, code:"WP110"/* 白银之枪 */],
		[probability:19, code:"WS111"/* 龙骨战弓 */],
		[probability:19, code:"WS211"/* 斩马刀 */],
		[probability:19, code:"WT110"/* 飞云标 */],
		[probability:19, code:"DB109"/* 大地靴 */],
		[probability:19, code:"DS109"/* 圣盾 */],
		[probability:19, code:"OM109"/* 暗月 */],
		[probability:19, code:"DG109"/* 神力护手 */],
		[probability:19, code:"OA209"/* 飞翼臂环 */],
		[probability:19, code:"EC102"/* 回城卷 */],
		[probability:19, code:"OR111"/* 封印之戒 */],
		[probability:19, code:"OA111"/* 圣者之链 */],
		[probability:19, code:"OS106"/* 玄风石 */],
		[probability:19, code:"GP106"/* 火灵王水晶 */],
		[probability:19, code:"EC105"/* 公会卷轴 */],
		[probability:7, code:"DA111"/* 重装机铠 */],
		[probability:7, code:"DA211"/* 大法师袍 */],
		[probability:7, code:"WA110"/* 定神斧 */],
		[probability:7, code:"WC110"/* 九头刺蛇爪 */],
		[probability:7, code:"WH111"/* 赤冥之锤 */],
		[probability:7, code:"WM111"/* 王者杖 */],
		[probability:7, code:"WP111"/* 屠龙枪 */],
		[probability:7, code:"WS112"/* 人马之辉 */],
		[probability:7, code:"WS212"/* 嗜血屠魔剑 */],
		[probability:7, code:"WT111"/* 神标 */],
		[probability:7, code:"DB110"/* 地火战靴 */],
		[probability:7, code:"DS110"/* 宙斯盾 */],
		[probability:7, code:"OM110"/* 蓝色星辰 */],
		[probability:7, code:"DG110"/* 火云护手 */],
		[probability:7, code:"OA210"/* 百川流水臂环 */],
		[probability:7, code:"EC102"/* 回城卷 */],
		[probability:7, code:"OR112"/* 王者戒指 */],
		[probability:7, code:"OA112"/* 魔龙之心 */],
		[probability:7, code:"OS107"/* 水晶石 */],
		[probability:7, code:"EC102"/* 回城卷 */],
		[probability:2, code:"DA112"/* 战神宝铠 */],
		[probability:2, code:"DA212"/* 红莲战袍 */],
		[probability:2, code:"WA111"/* 天阙斧 */],
		[probability:2, code:"WC111"/* 利维坦 */],
		[probability:2, code:"WH112"/* 碎星锤 */],
		[probability:2, code:"WM112"/* 审判之杖 */],
		[probability:2, code:"WP112"/* 傲天枪 */],
		[probability:2, code:"WS113"/* 猛犸巨弩 */],
		[probability:2, code:"WS213"/* 双截刃 */],
		[probability:2, code:"WT112"/* 鸩尾标 */],
		[probability:2, code:"DB111"/* 圣靴 */],
		[probability:2, code:"DS111"/* 苍穹之盾 */],
		[probability:2, code:"OM111"/* 淬火乌晶 */],
		[probability:2, code:"DG111"/* 黄铜护手 */],
		[probability:2, code:"OA211"/* 玄铁臂环 */],
		[probability:2, code:"EC102"/* 回城卷 */],
		[probability:2, code:"OR113"/* 灵魂之戒 */],
		[probability:2, code:"OA113"/* 生命之链 */],
		[probability:2, code:"OS107"/* 水晶石 */],
		[probability:2, code:"OS108"/* 虎翼石 */],
		[probability:2, code:"GP110"/* 守护圣徒水晶 */]
	],
	drops_more:[
	]
]