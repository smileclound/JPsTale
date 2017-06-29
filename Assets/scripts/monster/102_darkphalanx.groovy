monster = [
	ID:'102_darkphalanx',
	Name:'破日魔王',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:102,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:12500,
		// Attack Status
		Atk:[160, 176],
		AtkSpeed:8,
		Range:80,
		Rating:1480,

		// Defence Status
		Flee:800,
		Absorb:41,
		Block:22,
		DamageStunPers:10,

		// Resistance
		Earth:50,
		Fire:50,
		Ice:50,// Water
		Lighting:50,// Wind
		Poison:50,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:8,
		Real_Sight:390,
		Talks:[],

		// Move Behavier
		Move_Speed:1,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:30,
		SkillDamage:[180, 200],
		SkillDistance:0,
		SkillRange:0,
		SkillRating:30,
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
		Model:'char/monster/Darkphalanx/darkphalanx.ini',
		Sound:0x00001840,
	],

	// Drops
	AllSeeItem:false,
	Exp:600000,
	Quantity:1,
	drops:[
		[probability:1685, code:"NULL"/* Drops nothing */],
		[probability:2326, code:"GG101", value:[550, 950]/* Gold */],
		[probability:203, code:"DA117"/* 远古圣铠 */],
		[probability:203, code:"DA217"/* 天使法袍 */],
		[probability:203, code:"WA116"/* 轮回战斧 */],
		[probability:203, code:"WC116"/* 泰坦爪 */],
		[probability:203, code:"WH117"/* 兽神锤 */],
		[probability:203, code:"WM117"/* 神圣之光 */],
		[probability:203, code:"WP117"/* 银河战镰 */],
		[probability:203, code:"WS118"/* 风切 */],
		[probability:203, code:"WS218"/* 天裂 */],
		[probability:203, code:"WT117"/* 夜叉 */],
		[probability:203, code:"DB116"/* 赤龙战靴 */],
		[probability:203, code:"DS116"/* 远古之盾 */],
		[probability:203, code:"OM116"/* 龙之护身 */],
		[probability:203, code:"DG116"/* 星辰护手 */],
		[probability:203, code:"OA216"/* 鎏金臂环 */],
		[probability:203, code:"OR116"/* 雅典娜之吻 */],
		[probability:203, code:"OA116"/* 苍穹之链 */],
		[probability:125, code:"DA118"/* 米诺陶斯铠 */],
		[probability:125, code:"DA218"/* 撒旦披风 */],
		[probability:125, code:"WA117"/* 远古战斧 */],
		[probability:125, code:"WC117"/* 冰魄爪 */],
		[probability:125, code:"WH118"/* 灭神锤 */],
		[probability:125, code:"WM118"/* 日月同辉 */],
		[probability:125, code:"WP118"/* 月影神矛 */],
		[probability:125, code:"WS119"/* 丘比特之弓 */],
		[probability:125, code:"WS219"/* 凝雾 */],
		[probability:125, code:"WT118"/* 噬月 */],
		[probability:125, code:"DB117"/* 烈焰靴 */],
		[probability:125, code:"DS117"/* 米诺陶斯盾 */],
		[probability:125, code:"OM117"/* 绿釉之眼 */],
		[probability:125, code:"DG117"/* 炫钻护手 */],
		[probability:125, code:"OA217"/* 炫钻臂环 */],
		[probability:125, code:"OR117"/* 封魔之戒 */],
		[probability:125, code:"OA117"/* 天眼护符 */],
		[probability:20, code:"DA119"/* 死神绝命铠 */],
		[probability:20, code:"DA219"/* 幻彩羽袍 */],
		[probability:20, code:"WA118"/* 雷神斧 */],
		[probability:20, code:"WC118"/* 星刺爪 */],
		[probability:20, code:"WH119"/* 弑神 */],
		[probability:20, code:"WM119"/* 末日辉煌 */],
		[probability:20, code:"WP119"/* 棲羽亚陀 */],
		[probability:20, code:"WS120"/* 羽裂 */],
		[probability:20, code:"WS220"/* 龙牙 */],
		[probability:20, code:"WT119"/* 流星 */],
		[probability:20, code:"DB118"/* 符文之靴 */],
		[probability:20, code:"DS118"/* 死神之盾 */],
		[probability:20, code:"OM118"/* 黑魔 */],
		[probability:20, code:"DG118"/* 泰坦护手 */],
		[probability:20, code:"OA218"/* 泰坦臂环 */],
		[probability:20, code:"OR118"/* 封神之戒 */],
		[probability:20, code:"OA118"/* 圣光勋章 */],
		[probability:3, code:"DA120"/* 炎龙圣铠 */],
		[probability:3, code:"DA220"/* 修罗圣衣 */],
		[probability:3, code:"WA119"/* 幽月斧 */],
		[probability:3, code:"WC119"/* 修罗爪 */],
		[probability:3, code:"WH110"/* 轩辕巨锤 */],
		[probability:3, code:"WM120"/* 灭绝 */],
		[probability:3, code:"WP120"/* 炎枪素戋鸣 */],
		[probability:3, code:"WS121"/* 亚罗栖 */],
		[probability:3, code:"ws221"/* 32dew2 */],
		[probability:3, code:"WT120"/* 天妒 */],
		[probability:3, code:"DB119"/* 死神战靴 */],
		[probability:3, code:"DS119"/* 蛮狮之盾 */],
		[probability:3, code:"OM119"/* 赤魔之心 */],
		[probability:3, code:"DG119"/* 炫彩护手 */],
		[probability:3, code:"OA219"/* 兽神臂环 */],
		[probability:3, code:"OR119"/* 轮回之戒 */],
		[probability:3, code:"OA119"/* 逆天纹章 */]
	],
	drops_more:[
	]
]
