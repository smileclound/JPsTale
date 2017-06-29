monster = [
	ID:'60_solidsnail',
	Name:'变种蜗牛',
	Clazz:0,// 1 BOSS
	Brood:0x92,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:60,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:1160,
		// Attack Status
		Atk:[30, 43],
		AtkSpeed:7,
		Range:60,
		Rating:640,

		// Defence Status
		Flee:480,
		Absorb:16,
		Block:8,
		DamageStunPers:100,

		// Resistance
		Earth:50,
		Fire:20,
		Ice:30,// Water
		Lighting:30,// Wind
		Poison:50,
	],

	// AI
	AI:[
		Nature:0x80,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:7,
		Real_Sight:380,
		Talks:[],

		// Move Behavier
		Move_Speed:3,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:0,
		SkillDamage:[45, 55],
		SkillDistance:80,
		SkillRange:0,
		SkillRating:15,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:0,
		PotionCount:0,
	],

	Looks:[
		ClassCode:17,
		ArrowPosi:[-8, 200],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:-1,
		Model:'char/monster/ssnail/ssnail.INI',
		Sound:0x000013D0,
	],

	// Drops
	AllSeeItem:false,
	Exp:13500,
	Quantity:1,
	QuestItem:0x07010D00,
	drops:[
		[probability:2900, code:"NULL"/* Drops nothing */],
		[probability:5250, code:"GG101", value:[200, 250]/* Gold */],
		[probability:300, code:"PL104"/* 顶级恢复生命药水 */],
		[probability:300, code:"PS104"/* 顶级恢复耐力药水 */],
		[probability:300, code:"PL104"/* 顶级恢复生命药水 */],
		[probability:300, code:"PM103"/* 高级恢复魔法药水 */],
		[probability:41, code:"DA110"/* 百裂铠 */],
		[probability:41, code:"DA210"/* 信徒披风 */],
		[probability:41, code:"WA107"/* 刺脊斧 */],
		[probability:41, code:"WC108"/* 矩记飘 府亥瘤 */],
		[probability:41, code:"WH107"/* 十字锤 */],
		[probability:41, code:"WM108"/* 公正之杖 */],
		[probability:41, code:"WP108"/* 血烟长矛 */],
		[probability:41, code:"WS108"/* 巨弩 */],
		[probability:41, code:"WS209"/* 镇妖剑 */],
		[probability:41, code:"WT108"/* 金标 */],
		[probability:41, code:"OM108"/* 阳炎 */],
		[probability:41, code:"DG107"/* 百裂护手 */],
		[probability:41, code:"DS107"/* 金刚盾 */],
		[probability:41, code:"OR108"/* 翡翠戒指 */],
		[probability:41, code:"OA108"/* 靛青石链 */],
		[probability:41, code:"PL102"/* 中级恢复生命药水 */],
		[probability:41, code:"EC105"/* 公会卷轴 */],
		[probability:13, code:"DA111"/* 重装机铠 */],
		[probability:13, code:"DA211"/* 大法师袍 */],
		[probability:13, code:"WA108"/* 叼刚 清磐 */],
		[probability:13, code:"WC108"/* 矩记飘 府亥瘤 */],
		[probability:13, code:"WH109"/* 固萍绢扼捞飘 */],
		[probability:13, code:"WM109"/* 绊胶飘 */],
		[probability:13, code:"WP109"/* 单喉 荤捞靛 */],
		[probability:13, code:"WS109"/* 点金手弩 */],
		[probability:13, code:"WS210"/* 敲饭萍逞 家靛 */],
		[probability:13, code:"WT109"/* 酒唱能促 */],
		[probability:13, code:"OM109"/* 暗月 */],
		[probability:13, code:"DB108"/* 百战靴 */],
		[probability:13, code:"DS108"/* 赤龙焰盾 */],
		[probability:13, code:"DG108"/* 大地护手 */],
		[probability:13, code:"OA208"/* 百炼臂环 */],
		[probability:13, code:"PM102"/* 中级恢复魔法药水 */],
		[probability:13, code:"EC102"/* 回城卷 */],
		[probability:13, code:"OS105"/* 冰晶石 */],
		[probability:13, code:"OR109"/* 黑暗之戒 */],
		[probability:13, code:"OA109"/* 海蓝石链 */],
		[probability:13, code:"OS106"/* 玄风石 */],
		[probability:13, code:"OS107"/* 水晶石 */],
		[probability:5, code:"DA112"/* 战神宝铠 */],
		[probability:5, code:"DA212"/* 红莲战袍 */],
		[probability:5, code:"WA109"/* 破山斧 */],
		[probability:5, code:"WC109"/* 兽之斧刃 */],
		[probability:5, code:"WH110"/* 轩辕巨锤 */],
		[probability:5, code:"WM110"/* 圣者杖 */],
		[probability:5, code:"WP110"/* 白银之枪 */],
		[probability:5, code:"WS110"/* 固胶飘 */],
		[probability:5, code:"WS211"/* 斩马刀 */],
		[probability:5, code:"WT110"/* 飞云标 */],
		[probability:5, code:"OM110"/* 蓝色星辰 */],
		[probability:5, code:"DB109"/* 大地靴 */],
		[probability:5, code:"DS109"/* 圣盾 */],
		[probability:5, code:"OM109"/* 暗月 */],
		[probability:5, code:"DG109"/* 神力护手 */],
		[probability:5, code:"OA209"/* 飞翼臂环 */],
		[probability:5, code:"PM103"/* 高级恢复魔法药水 */],
		[probability:5, code:"EC102"/* 回城卷 */],
		[probability:5, code:"OR110"/* 伏魔戒指 */],
		[probability:5, code:"OA110"/* 镇魂铃 */]
	],
	drops_more:[
	]
]
