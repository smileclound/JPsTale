monster = [
	ID:'100_chimera',
	Name:'龙鹰灵兽',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:100,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:11500,
		// Attack Status
		Atk:[145, 160],
		AtkSpeed:8,
		Range:100,
		Rating:1300,

		// Defence Status
		Flee:660,
		Absorb:35,
		Block:22,
		DamageStunPers:20,

		// Resistance
		Earth:32,
		Fire:63,
		Ice:33,// Water
		Lighting:23,// Wind
		Poison:23,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:8,
		Real_Sight:380,
		Talks:[],

		// Move Behavier
		Move_Speed:3,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:30,
		SkillDamage:[165, 175],
		SkillDistance:0,
		SkillRange:20,
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
		SizeLevel:-1,
		Model:'char/monster/Chimera/kimera_A.ini',
		Sound:0x00001860,
	],

	// Drops
	AllSeeItem:false,
	Exp:560000,
	Quantity:1,
	drops:[
		[probability:2687, code:"NULL"/* Drops nothing */],
		[probability:2260, code:"GG101", value:[550, 950]/* Gold */],
		[probability:190, code:"DA118"/* 米诺陶斯铠 */],
		[probability:190, code:"DA218"/* 撒旦披风 */],
		[probability:190, code:"WA116"/* 轮回战斧 */],
		[probability:190, code:"WC116"/* 泰坦爪 */],
		[probability:190, code:"WH117"/* 兽神锤 */],
		[probability:190, code:"WM117"/* 神圣之光 */],
		[probability:190, code:"WP117"/* 银河战镰 */],
		[probability:190, code:"WS118"/* 风切 */],
		[probability:190, code:"WS218"/* 天裂 */],
		[probability:190, code:"WT117"/* 夜叉 */],
		[probability:190, code:"DB116"/* 赤龙战靴 */],
		[probability:190, code:"DS116"/* 远古之盾 */],
		[probability:190, code:"OM116"/* 龙之护身 */],
		[probability:190, code:"DG116"/* 星辰护手 */],
		[probability:190, code:"OA216"/* 鎏金臂环 */],
		[probability:190, code:"OR116"/* 雅典娜之吻 */],
		[probability:190, code:"OA116"/* 苍穹之链 */],
		[probability:84, code:"DA119"/* 死神绝命铠 */],
		[probability:84, code:"DA219"/* 幻彩羽袍 */],
		[probability:84, code:"WA117"/* 远古战斧 */],
		[probability:84, code:"WC117"/* 冰魄爪 */],
		[probability:84, code:"WH118"/* 灭神锤 */],
		[probability:84, code:"WM118"/* 日月同辉 */],
		[probability:84, code:"WP118"/* 月影神矛 */],
		[probability:84, code:"WS119"/* 丘比特之弓 */],
		[probability:84, code:"WS219"/* 凝雾 */],
		[probability:84, code:"WT118"/* 噬月 */],
		[probability:84, code:"DB117"/* 烈焰靴 */],
		[probability:84, code:"DS117"/* 米诺陶斯盾 */],
		[probability:84, code:"OM117"/* 绿釉之眼 */],
		[probability:84, code:"DG117"/* 炫钻护手 */],
		[probability:84, code:"OA217"/* 炫钻臂环 */],
		[probability:84, code:"OR117"/* 封魔之戒 */],
		[probability:84, code:"OA117"/* 天眼护符 */],
		[probability:21, code:"DA119"/* 死神绝命铠 */],
		[probability:21, code:"DA219"/* 幻彩羽袍 */],
		[probability:21, code:"WA118"/* 雷神斧 */],
		[probability:21, code:"WC118"/* 星刺爪 */],
		[probability:21, code:"WH119"/* 弑神 */],
		[probability:21, code:"WM119"/* 末日辉煌 */],
		[probability:21, code:"WP119"/* 棲羽亚陀 */],
		[probability:21, code:"WS120"/* 羽裂 */],
		[probability:21, code:"WS220"/* 龙牙 */],
		[probability:21, code:"WT119"/* 流星 */],
		[probability:21, code:"DB118"/* 符文之靴 */],
		[probability:21, code:"DS118"/* 死神之盾 */],
		[probability:21, code:"OM118"/* 黑魔 */],
		[probability:21, code:"DG118"/* 泰坦护手 */],
		[probability:21, code:"OA218"/* 泰坦臂环 */],
		[probability:21, code:"OR118"/* 封神之戒 */],
		[probability:21, code:"OA118"/* 圣光勋章 */],
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
		[probability:1, code:"OA119"/* 逆天纹章 */]
	],
	drops_more:[
	]
]
