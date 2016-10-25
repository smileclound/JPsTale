monster = [
	ID:'56_avelisk-s',
	Name:'魔剑人马',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:56,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:1000,
		// Attack Status
		Atk:[44, 56],
		AtkSpeed:8,
		Range:85,
		Rating:850,

		// Defence Status
		Flee:440,
		Absorb:8,
		Block:5,
		DamageStunPers:100,

		// Resistance
		Earth:0,
		Fire:20,
		Ice:20,// Water
		Lighting:0,// Wind
		Poison:40,
	],

	// AI
	AI:[
		Nature:0x80,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:9,
		Real_Sight:380,
		Talks:[],

		// Move Behavier
		Move_Speed:6,
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
		ArrowPosi:[0, 40],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:-1,
		Model:'char/monster/Monavelrisk/Monavelrisk-sword.ini',
		Sound:0x00001280,
	],

	// Drops
	AllSeeItem:false,
	Exp:10600,
	Quantity:1,
	drops:[
		[probability:2000, code:"NULL"/* Drops nothing */],
		[probability:4350, code:"GG101", value:[150, 180]/* Gold */],
		[probability:400, code:"PL103"/* 高级恢复生命药水 */],
		[probability:400, code:"PS103"/* 高级恢复耐力药水 */],
		[probability:400, code:"PL103"/* 高级恢复生命药水 */],
		[probability:400, code:"PL103"/* 高级恢复生命药水 */],
		[probability:400, code:"PM102"/* 中级恢复魔法药水 */],
		[probability:52, code:"DA108"/* 精制链铠 */],
		[probability:52, code:"DA208"/* 圣女袍 */],
		[probability:52, code:"PL102"/* 中级恢复生命药水 */],
		[probability:52, code:"WA107"/* 刺脊斧 */],
		[probability:52, code:"WC107"/* 平手刃 */],
		[probability:52, code:"WH108"/* 圣光锤 */],
		[probability:52, code:"WM108"/* 公正之杖 */],
		[probability:52, code:"WP108"/* 血烟长矛 */],
		[probability:52, code:"WS109"/* 点金手弩 */],
		[probability:52, code:"WS209"/* 镇妖剑 */],
		[probability:52, code:"WT108"/* 金标 */],
		[probability:52, code:"OM106"/* 水星 */],
		[probability:52, code:"DB106"/* 黄金靴 */],
		[probability:52, code:"DS106"/* 百炼盾 */],
		[probability:52, code:"DG106"/* 白金护手 */],
		[probability:52, code:"OA206"/* 精灵臂环 */],
		[probability:52, code:"OR110"/* 伏魔戒指 */],
		[probability:52, code:"OA110"/* 镇魂铃 */],
		[probability:52, code:"EC105"/* 公会卷轴 */],
		[probability:23, code:"DA109"/* 黄铜战铠 */],
		[probability:23, code:"DA209"/* 学徒披风 */],
		[probability:23, code:"PL103"/* 高级恢复生命药水 */],
		[probability:23, code:"WA108"/* 叼刚 清磐 */],
		[probability:23, code:"WC108"/* 矩记飘 府亥瘤 */],
		[probability:23, code:"WH109"/* 固萍绢扼捞飘 */],
		[probability:23, code:"WM109"/* 绊胶飘 */],
		[probability:23, code:"WP109"/* 单喉 荤捞靛 */],
		[probability:23, code:"WS210"/* 敲饭萍逞 家靛 */],
		[probability:23, code:"WS110"/* 固胶飘 */],
		[probability:23, code:"WT109"/* 酒唱能促 */],
		[probability:23, code:"OM107"/* 火星 */],
		[probability:23, code:"OS105"/* 冰晶石 */],
		[probability:23, code:"OS105"/* 冰晶石 */],
		[probability:23, code:"OS106"/* 玄风石 */],
		[probability:23, code:"DB107"/* 冰火靴 */],
		[probability:23, code:"DS107"/* 金刚盾 */],
		[probability:23, code:"OR111"/* 封印之戒 */],
		[probability:23, code:"OA111"/* 圣者之链 */],
		[probability:23, code:"EC105"/* 公会卷轴 */],
		[probability:23, code:"OS107"/* 水晶石 */],
		[probability:7, code:"DA110"/* 百裂铠 */],
		[probability:7, code:"DA210"/* 信徒披风 */],
		[probability:7, code:"PL104"/* 顶级恢复生命药水 */],
		[probability:7, code:"WA109"/* 破山斧 */],
		[probability:7, code:"WC109"/* 兽之斧刃 */],
		[probability:7, code:"WH110"/* 轩辕巨锤 */],
		[probability:7, code:"WM110"/* 圣者杖 */],
		[probability:7, code:"WP110"/* 白银之枪 */],
		[probability:7, code:"WS211"/* 斩马刀 */],
		[probability:7, code:"WS111"/* 龙骨战弓 */],
		[probability:7, code:"WT110"/* 飞云标 */],
		[probability:7, code:"OM108"/* 阳炎 */],
		[probability:7, code:"OS106"/* 玄风石 */],
		[probability:7, code:"OS106"/* 玄风石 */],
		[probability:7, code:"DB108"/* 百战靴 */],
		[probability:7, code:"DS108"/* 赤龙焰盾 */],
		[probability:7, code:"PM103"/* 高级恢复魔法药水 */],
		[probability:7, code:"OR112"/* 王者戒指 */],
		[probability:7, code:"OA112"/* 魔龙之心 */],
		[probability:7, code:"EC105"/* 公会卷轴 */],
		[probability:7, code:"GP110"/* 守护圣徒水晶 */]
	],
	drops_more:[
	]
]
