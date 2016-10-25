monster = [
	ID:'100_devilbird',
	Name:'冰风魔王',
	Clazz:0,// 1 BOSS
	Brood:0x92,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:100,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:11000,
		// Attack Status
		Atk:[105, 125],
		AtkSpeed:8,
		Range:80,
		Rating:1500,

		// Defence Status
		Flee:820,
		Absorb:30,
		Block:18,
		DamageStunPers:10,

		// Resistance
		Earth:60,
		Fire:-100,
		Ice:100,// Water
		Lighting:30,// Wind
		Poison:30,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:9,
		Real_Sight:440,
		Talks:[],

		// Move Behavier
		Move_Speed:2,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:16,
		SkillDamage:[120, 155],
		SkillDistance:0,
		SkillRange:0,
		SkillRating:18,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:0,
		PotionCount:0,
	],

	Looks:[
		ClassCode:0,
		ArrowPosi:[0, 0],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:1,
		Model:'char/monster/devilbird/devilbird.ini',
		Sound:0x00001810,
	],

	// Drops
	AllSeeItem:false,
	Exp:570000,
	Quantity:3,
	drops:[
		[probability:1000, code:"NULL"/* Drops nothing */],
		[probability:2000, code:"GG101", value:[520, 820]/* Gold */],
		[probability:205, code:"DA117"/* 远古圣铠 */],
		[probability:205, code:"DA217"/* 天使法袍 */],
		[probability:205, code:"WA115"/* 泰坦斧 */],
		[probability:205, code:"WC115"/* 魔玉爪 */],
		[probability:205, code:"WH116"/* 轰天锤 */],
		[probability:205, code:"WM116"/* 诸神的黄昏 */],
		[probability:205, code:"WP116"/* 虹月 */],
		[probability:205, code:"WS117"/* 破鹫 */],
		[probability:205, code:"WS218"/* 天裂 */],
		[probability:205, code:"WT116"/* 玛雅神标 */],
		[probability:205, code:"DB115"/* 时空之靴 */],
		[probability:205, code:"DS115"/* 亢龙之盾 */],
		[probability:205, code:"OM116"/* 龙之护身 */],
		[probability:205, code:"DG115"/* 赤龙护手 */],
		[probability:205, code:"OA215"/* 赤龙臂环 */],
		[probability:205, code:"OR117"/* 封魔之戒 */],
		[probability:205, code:"OA117"/* 天眼护符 */],
		[probability:147, code:"DA118"/* 米诺陶斯铠 */],
		[probability:147, code:"DA218"/* 撒旦披风 */],
		[probability:147, code:"WA116"/* 轮回战斧 */],
		[probability:147, code:"WC116"/* 泰坦爪 */],
		[probability:147, code:"WH117"/* 兽神锤 */],
		[probability:147, code:"WM117"/* 神圣之光 */],
		[probability:147, code:"WP117"/* 银河战镰 */],
		[probability:147, code:"WS118"/* 风切 */],
		[probability:147, code:"WS219"/* 凝雾 */],
		[probability:147, code:"WT117"/* 夜叉 */],
		[probability:147, code:"DB116"/* 赤龙战靴 */],
		[probability:147, code:"DS116"/* 远古之盾 */],
		[probability:147, code:"OM117"/* 绿釉之眼 */],
		[probability:147, code:"DG116"/* 星辰护手 */],
		[probability:147, code:"OA216"/* 鎏金臂环 */],
		[probability:147, code:"OR118"/* 封神之戒 */],
		[probability:147, code:"OA118"/* 圣光勋章 */],
		[probability:41, code:"DA119"/* 死神绝命铠 */],
		[probability:41, code:"DA219"/* 幻彩羽袍 */],
		[probability:41, code:"WA117"/* 远古战斧 */],
		[probability:41, code:"WC117"/* 冰魄爪 */],
		[probability:41, code:"WH118"/* 灭神锤 */],
		[probability:41, code:"WM118"/* 日月同辉 */],
		[probability:41, code:"WP118"/* 月影神矛 */],
		[probability:41, code:"WS119"/* 丘比特之弓 */],
		[probability:41, code:"WS220"/* 龙牙 */],
		[probability:41, code:"WT118"/* 噬月 */],
		[probability:41, code:"DB117"/* 烈焰靴 */],
		[probability:41, code:"DS117"/* 米诺陶斯盾 */],
		[probability:41, code:"OM118"/* 黑魔 */],
		[probability:41, code:"DG117"/* 炫钻护手 */],
		[probability:41, code:"OA217"/* 炫钻臂环 */],
		[probability:41, code:"OR119"/* 轮回之戒 */],
		[probability:41, code:"OA119"/* 逆天纹章 */],
		[probability:17, code:"DA120"/* 炎龙圣铠 */],
		[probability:17, code:"DA220"/* 修罗圣衣 */],
		[probability:17, code:"WA118"/* 雷神斧 */],
		[probability:17, code:"WC118"/* 星刺爪 */],
		[probability:17, code:"WH119"/* 弑神 */],
		[probability:17, code:"WM119"/* 末日辉煌 */],
		[probability:17, code:"WP119"/* 棲羽亚陀 */],
		[probability:17, code:"WS120"/* 羽裂 */],
		[probability:17, code:"ws221"/* 32dew2 */],
		[probability:17, code:"WT119"/* 流星 */],
		[probability:17, code:"DB118"/* 符文之靴 */],
		[probability:17, code:"DS118"/* 死神之盾 */],
		[probability:17, code:"OM119"/* 赤魔之心 */],
		[probability:17, code:"DG118"/* 泰坦护手 */],
		[probability:17, code:"OA218"/* 泰坦臂环 */],
		[probability:17, code:"OR120"/* 涅磐之戒 */],
		[probability:17, code:"OA120"/* 九转护符 */]
	],
	drops_more:[
	]
]
