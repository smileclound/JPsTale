monster = [
	ID:'100_hellhound',
	Name:'地狱猎犬',
	Clazz:0,// 1 BOSS
	Brood:0x92,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:100,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:11000,
		// Attack Status
		Atk:[145, 165],
		AtkSpeed:8,
		Range:120,
		Rating:1310,

		// Defence Status
		Flee:860,
		Absorb:35,
		Block:25,
		DamageStunPers:10,

		// Resistance
		Earth:40,
		Fire:43,
		Ice:33,// Water
		Lighting:23,// Wind
		Poison:33,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:9,
		Real_Sight:380,
		Talks:[],

		// Move Behavier
		Move_Speed:1,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:25,
		SkillDamage:[165, 175],
		SkillDistance:0,
		SkillRange:0,
		SkillRating:25,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:0,
		PotionCount:0,
	],

	Looks:[
		ClassCode:66,
		ArrowPosi:[0, 0],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:-1,
		Model:'char/monster/hellhound/hellhound.ini',
		Sound:0x00001880,
	],

	// Drops
	AllSeeItem:false,
	Exp:640000,
	Quantity:1,
	drops:[
		[probability:2675, code:"NULL"/* Drops nothing */],
		[probability:2136, code:"GG101", value:[550, 950]/* Gold */],
		[probability:174, code:"DA117"/* 远古圣铠 */],
		[probability:174, code:"DA217"/* 天使法袍 */],
		[probability:174, code:"WA116"/* 轮回战斧 */],
		[probability:174, code:"WC116"/* 泰坦爪 */],
		[probability:174, code:"WH117"/* 兽神锤 */],
		[probability:174, code:"WM117"/* 神圣之光 */],
		[probability:174, code:"WP117"/* 银河战镰 */],
		[probability:174, code:"WS118"/* 风切 */],
		[probability:174, code:"WS218"/* 天裂 */],
		[probability:174, code:"WT117"/* 夜叉 */],
		[probability:174, code:"DB116"/* 赤龙战靴 */],
		[probability:174, code:"DS116"/* 远古之盾 */],
		[probability:174, code:"OM116"/* 龙之护身 */],
		[probability:174, code:"DG116"/* 星辰护手 */],
		[probability:174, code:"OA216"/* 鎏金臂环 */],
		[probability:174, code:"OR116"/* 雅典娜之吻 */],
		[probability:174, code:"OA116"/* 苍穹之链 */],
		[probability:174, code:"OS107"/* 水晶石 */],
		[probability:93, code:"DA118"/* 米诺陶斯铠 */],
		[probability:93, code:"DA218"/* 撒旦披风 */],
		[probability:93, code:"WA117"/* 远古战斧 */],
		[probability:93, code:"WC117"/* 冰魄爪 */],
		[probability:93, code:"WH118"/* 灭神锤 */],
		[probability:93, code:"WM118"/* 日月同辉 */],
		[probability:93, code:"WP118"/* 月影神矛 */],
		[probability:93, code:"WS119"/* 丘比特之弓 */],
		[probability:93, code:"WS219"/* 凝雾 */],
		[probability:93, code:"WT118"/* 噬月 */],
		[probability:93, code:"DB117"/* 烈焰靴 */],
		[probability:93, code:"DS117"/* 米诺陶斯盾 */],
		[probability:93, code:"OM117"/* 绿釉之眼 */],
		[probability:93, code:"DG117"/* 炫钻护手 */],
		[probability:93, code:"OA217"/* 炫钻臂环 */],
		[probability:93, code:"OR117"/* 封魔之戒 */],
		[probability:93, code:"OA117"/* 天眼护符 */],
		[probability:93, code:"OS108"/* 虎翼石 */],
		[probability:19, code:"DA119"/* 死神绝命铠 */],
		[probability:19, code:"DA219"/* 幻彩羽袍 */],
		[probability:19, code:"WA118"/* 雷神斧 */],
		[probability:19, code:"WC118"/* 星刺爪 */],
		[probability:19, code:"WH119"/* 弑神 */],
		[probability:19, code:"WM119"/* 末日辉煌 */],
		[probability:19, code:"WP119"/* 棲羽亚陀 */],
		[probability:19, code:"WS120"/* 羽裂 */],
		[probability:19, code:"WS220"/* 龙牙 */],
		[probability:19, code:"WT119"/* 流星 */],
		[probability:19, code:"DB118"/* 符文之靴 */],
		[probability:19, code:"DS118"/* 死神之盾 */],
		[probability:19, code:"OM118"/* 黑魔 */],
		[probability:19, code:"DG118"/* 泰坦护手 */],
		[probability:19, code:"OA218"/* 泰坦臂环 */],
		[probability:19, code:"OR118"/* 封神之戒 */],
		[probability:19, code:"OA118"/* 圣光勋章 */],
		[probability:19, code:"OS109"/* 龙鳞石 */],
		[probability:1, code:"DA120"/* 炎龙圣铠 */],
		[probability:1, code:"DA220"/* 修罗圣衣 */],
		[probability:1, code:"WA119"/* 幽月斧 */],
		[probability:1, code:"WC119"/* 修罗爪 */],
		[probability:1, code:"WH110"/* 轩辕巨锤 */],
		[probability:1, code:"WM120"/* 灭绝 */],
		[probability:1, code:"WP120"/* 炎枪素戋鸣 */],
		[probability:1, code:"WS121"/* 亚罗栖 */],
		[probability:1, code:"ws221"/* 32dew2 */],
		[probability:1, code:"WT120"/* 天妒 */],
		[probability:1, code:"DB119"/* 死神战靴 */],
		[probability:1, code:"DS119"/* 蛮狮之盾 */],
		[probability:1, code:"OM119"/* 赤魔之心 */],
		[probability:1, code:"DG119"/* 炫彩护手 */],
		[probability:1, code:"OA219"/* 兽神臂环 */],
		[probability:1, code:"OR119"/* 轮回之戒 */],
		[probability:1, code:"OA119"/* 逆天纹章 */],
		[probability:1, code:"OS110"/* 钻晶石 */]
	],
	drops_more:[
	]
]
