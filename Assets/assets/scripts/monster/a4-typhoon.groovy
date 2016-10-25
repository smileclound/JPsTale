monster = [
	ID:'a4-typhoon',
	Name:'飓风战士',
	Clazz:0,// 1 BOSS
	Brood:0x91,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:85,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:3200,
		// Attack Status
		Atk:[79, 98],
		AtkSpeed:7,
		Range:80,
		Rating:1300,

		// Defence Status
		Flee:720,
		Absorb:16,
		Block:8,
		DamageStunPers:65,

		// Resistance
		Earth:0,
		Fire:40,
		Ice:40,// Water
		Lighting:20,// Wind
		Poison:70,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:8,
		Real_Sight:380,
		Talks:[],

		// Move Behavier
		Move_Speed:4,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:20,
		SkillDamage:[0, 0],
		SkillDistance:0,
		SkillRange:0,
		SkillRating:0,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:40,
		PotionCount:1,
	],

	Looks:[
		ClassCode:9,
		ArrowPosi:[-2, 30],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:2,
		Model:'char/monster/Monhulk-1/Monhulk-1.ini',
		Sound:0x000012B0,
	],

	// Drops
	AllSeeItem:false,
	Exp:11000,
	Quantity:1,
	drops:[
		[probability:4430, code:"NULL"/* Drops nothing */],
		[probability:4460, code:"GG101", value:[95, 120]/* Gold */],
		[probability:37, code:"WA112"/* 奥丁斧 */],
		[probability:37, code:"WC111"/* 利维坦 */],
		[probability:37, code:"WH111"/* 赤冥之锤 */],
		[probability:37, code:"WM112"/* 审判之杖 */],
		[probability:37, code:"WP111"/* 屠龙枪 */],
		[probability:37, code:"WS113"/* 猛犸巨弩 */],
		[probability:37, code:"WS213"/* 双截刃 */],
		[probability:37, code:"WT112"/* 鸩尾标 */],
		[probability:37, code:"DA111"/* 重装机铠 */],
		[probability:37, code:"DA211"/* 大法师袍 */],
		[probability:37, code:"DS110"/* 宙斯盾 */],
		[probability:37, code:"OM113"/* 西法路 */],
		[probability:200, code:"EC105"/* 公会卷轴 */],
		[probability:9, code:"WA115"/* 泰坦斧 */],
		[probability:9, code:"WC113"/* 魔星爪 */],
		[probability:9, code:"WH115"/* 雷公槌 */],
		[probability:9, code:"WM116"/* 诸神的黄昏 */],
		[probability:9, code:"WP115"/* 狂暴之枪 */],
		[probability:9, code:"WS119"/* 丘比特之弓 */],
		[probability:9, code:"WS218"/* 天裂 */],
		[probability:9, code:"WT115"/* 惊鸿 */],
		[probability:9, code:"DA113"/* 虎刹魔铠 */],
		[probability:9, code:"DA213"/* 幽绿之眼 */],
		[probability:9, code:"DS112"/* 暗黑盾 */],
		[probability:9, code:"OM116"/* 龙之护身 */],
		[probability:8, code:"WA116"/* 轮回战斧 */],
		[probability:8, code:"WC117"/* 冰魄爪 */],
		[probability:8, code:"WH116"/* 轰天锤 */],
		[probability:8, code:"WM118"/* 日月同辉 */],
		[probability:8, code:"WP117"/* 银河战镰 */],
		[probability:8, code:"WS120"/* 羽裂 */],
		[probability:8, code:"WS216"/* 破军 */],
		[probability:8, code:"WT117"/* 夜叉 */],
		[probability:8, code:"DA114"/* 星晨宝铠 */],
		[probability:8, code:"DA214"/* 绯红之眼 */],
		[probability:8, code:"DS114"/* 泰坦之盾 */],
		[probability:8, code:"OM117"/* 绿釉之眼 */],
		[probability:33, code:"DB117"/* 烈焰靴 */],
		[probability:33, code:"DG116"/* 星辰护手 */],
		[probability:33, code:"OA215"/* 赤龙臂环 */],
		[probability:33, code:"DB118"/* 符文之靴 */],
		[probability:33, code:"DG118"/* 泰坦护手 */],
		[probability:33, code:"OA218"/* 泰坦臂环 */],
		[probability:5, code:"OR115"/* 守护之戒 */],
		[probability:5, code:"OA115"/* 暗印护符 */],
		[probability:5, code:"OR116"/* 雅典娜之吻 */],
		[probability:5, code:"OA116"/* 苍穹之链 */],
		[probability:5, code:"OR117"/* 封魔之戒 */],
		[probability:5, code:"OS104"/* 天仪石 */],
		[probability:5, code:"OS105"/* 冰晶石 */],
		[probability:5, code:"OS106"/* 玄风石 */],
		[probability:5, code:"OS107"/* 水晶石 */]
	],
	drops_more:[
	]
]
