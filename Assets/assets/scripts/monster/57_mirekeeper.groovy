monster = [
	ID:'57_mirekeeper',
	Name:'泥潭守护者',
	Clazz:0,// 1 BOSS
	Brood:0x92,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:57,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:1040,
		// Attack Status
		Atk:[37, 46],
		AtkSpeed:6,
		Range:70,
		Rating:750,

		// Defence Status
		Flee:440,
		Absorb:12,
		Block:15,
		DamageStunPers:100,

		// Resistance
		Earth:50,
		Fire:25,
		Ice:50,// Water
		Lighting:20,// Wind
		Poison:50,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:8,
		Real_Sight:360,
		Talks:[],

		// Move Behavier
		Move_Speed:3,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:0,
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
		ArrowPosi:[0, 50],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:1,
		Model:'char/monster/MireKeeper/MireKeeper.INI',
		Sound:0x000013B0,
	],

	// Drops
	AllSeeItem:false,
	Exp:12000,
	Quantity:1,
	drops:[
		[probability:3000, code:"NULL"/* Drops nothing */],
		[probability:3750, code:"GG101", value:[150, 180]/* Gold */],
		[probability:400, code:"PL103"/* 高级恢复生命药水 */],
		[probability:400, code:"PS103"/* 高级恢复耐力药水 */],
		[probability:400, code:"PL103"/* 高级恢复生命药水 */],
		[probability:400, code:"PL103"/* 高级恢复生命药水 */],
		[probability:400, code:"PM102"/* 中级恢复魔法药水 */],
		[probability:50, code:"DA108"/* 精制链铠 */],
		[probability:50, code:"DA208"/* 圣女袍 */],
		[probability:50, code:"WA107"/* 刺脊斧 */],
		[probability:50, code:"WC107"/* 平手刃 */],
		[probability:50, code:"WH108"/* 圣光锤 */],
		[probability:50, code:"WM108"/* 公正之杖 */],
		[probability:50, code:"WP108"/* 血烟长矛 */],
		[probability:50, code:"WS209"/* 镇妖剑 */],
		[probability:50, code:"WS108"/* 巨弩 */],
		[probability:50, code:"WT108"/* 金标 */],
		[probability:50, code:"OM106"/* 水星 */],
		[probability:50, code:"DB106"/* 黄金靴 */],
		[probability:50, code:"DS106"/* 百炼盾 */],
		[probability:50, code:"DG106"/* 白金护手 */],
		[probability:50, code:"OA206"/* 精灵臂环 */],
		[probability:50, code:"OR110"/* 伏魔戒指 */],
		[probability:50, code:"OA110"/* 镇魂铃 */],
		[probability:50, code:"EC105"/* 公会卷轴 */],
		[probability:20, code:"DA109"/* 黄铜战铠 */],
		[probability:20, code:"DA209"/* 学徒披风 */],
		[probability:20, code:"WA108"/* 叼刚 清磐 */],
		[probability:20, code:"WC108"/* 矩记飘 府亥瘤 */],
		[probability:20, code:"WH109"/* 固萍绢扼捞飘 */],
		[probability:20, code:"WM109"/* 绊胶飘 */],
		[probability:20, code:"WP109"/* 单喉 荤捞靛 */],
		[probability:20, code:"WS210"/* 敲饭萍逞 家靛 */],
		[probability:20, code:"WS109"/* 点金手弩 */],
		[probability:20, code:"WT109"/* 酒唱能促 */],
		[probability:20, code:"OM107"/* 火星 */],
		[probability:20, code:"OS105"/* 冰晶石 */],
		[probability:20, code:"DB107"/* 冰火靴 */],
		[probability:20, code:"DS107"/* 金刚盾 */],
		[probability:20, code:"OR111"/* 封印之戒 */],
		[probability:20, code:"OA111"/* 圣者之链 */],
		[probability:20, code:"EC105"/* 公会卷轴 */],
		[probability:5, code:"DA110"/* 百裂铠 */],
		[probability:5, code:"DA210"/* 信徒披风 */],
		[probability:5, code:"WA109"/* 破山斧 */],
		[probability:5, code:"WC109"/* 兽之斧刃 */],
		[probability:5, code:"WH110"/* 轩辕巨锤 */],
		[probability:5, code:"WM110"/* 圣者杖 */],
		[probability:5, code:"WP110"/* 白银之枪 */],
		[probability:5, code:"WS211"/* 斩马刀 */],
		[probability:5, code:"WS110"/* 固胶飘 */],
		[probability:5, code:"WT110"/* 飞云标 */],
		[probability:5, code:"OM108"/* 阳炎 */],
		[probability:5, code:"OS106"/* 玄风石 */],
		[probability:5, code:"DB108"/* 百战靴 */],
		[probability:5, code:"DS108"/* 赤龙焰盾 */],
		[probability:5, code:"OR112"/* 王者戒指 */],
		[probability:5, code:"OA112"/* 魔龙之心 */],
		[probability:5, code:"EC105"/* 公会卷轴 */]
	],
	drops_more:[
		[probability:70, code:"EC105"/* 公会卷轴 */]	]
]
