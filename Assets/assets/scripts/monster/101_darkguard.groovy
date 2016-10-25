monster = [
	ID:'101_darkguard',
	Name:'神弓魔',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:101,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:10200,
		// Attack Status
		Atk:[100, 120],
		AtkSpeed:8,
		Range:130,
		Rating:1300,

		// Defence Status
		Flee:850,
		Absorb:30,
		Block:38,
		DamageStunPers:15,

		// Resistance
		Earth:32,
		Fire:-10,
		Ice:43,// Water
		Lighting:-5,// Wind
		Poison:63,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:8,
		Real_Sight:380,
		Talks:[],

		// Move Behavier
		Move_Speed:2,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:15,
		SkillDamage:[120, 150],
		SkillDistance:130,
		SkillRange:0,
		SkillRating:15,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:0,
		PotionCount:0,
	],

	Looks:[
		ClassCode:67,
		ArrowPosi:[0, 0],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:1,
		Model:'char/monster/Darkguard/skullac.ini',
		Sound:0x00001890,
	],

	// Drops
	AllSeeItem:false,
	Exp:600000,
	Quantity:1,
	drops:[
		[probability:2039, code:"NULL"/* Drops nothing */],
		[probability:2230, code:"GG101", value:[550, 950]/* Gold */],
		[probability:180, code:"DA118"/* 米诺陶斯铠 */],
		[probability:180, code:"DA218"/* 撒旦披风 */],
		[probability:180, code:"WA116"/* 轮回战斧 */],
		[probability:180, code:"WC116"/* 泰坦爪 */],
		[probability:180, code:"WH117"/* 兽神锤 */],
		[probability:180, code:"WM117"/* 神圣之光 */],
		[probability:180, code:"WP117"/* 银河战镰 */],
		[probability:180, code:"WS118"/* 风切 */],
		[probability:180, code:"WS218"/* 天裂 */],
		[probability:180, code:"WT117"/* 夜叉 */],
		[probability:180, code:"DB116"/* 赤龙战靴 */],
		[probability:180, code:"DS116"/* 远古之盾 */],
		[probability:180, code:"OM116"/* 龙之护身 */],
		[probability:180, code:"DG116"/* 星辰护手 */],
		[probability:180, code:"OA216"/* 鎏金臂环 */],
		[probability:180, code:"OR116"/* 雅典娜之吻 */],
		[probability:180, code:"OA116"/* 苍穹之链 */],
		[probability:180, code:"OS107"/* 水晶石 */],
		[probability:116, code:"DA119"/* 死神绝命铠 */],
		[probability:116, code:"DA219"/* 幻彩羽袍 */],
		[probability:116, code:"WA117"/* 远古战斧 */],
		[probability:116, code:"WC117"/* 冰魄爪 */],
		[probability:116, code:"WH118"/* 灭神锤 */],
		[probability:116, code:"WM118"/* 日月同辉 */],
		[probability:116, code:"WP118"/* 月影神矛 */],
		[probability:116, code:"WS119"/* 丘比特之弓 */],
		[probability:116, code:"WS219"/* 凝雾 */],
		[probability:116, code:"WT118"/* 噬月 */],
		[probability:116, code:"DB117"/* 烈焰靴 */],
		[probability:116, code:"DS117"/* 米诺陶斯盾 */],
		[probability:116, code:"OM117"/* 绿釉之眼 */],
		[probability:116, code:"DG117"/* 炫钻护手 */],
		[probability:116, code:"OA217"/* 炫钻臂环 */],
		[probability:116, code:"OR117"/* 封魔之戒 */],
		[probability:116, code:"OA117"/* 天眼护符 */],
		[probability:116, code:"OS108"/* 虎翼石 */],
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
		[probability:2, code:"DA120"/* 炎龙圣铠 */],
		[probability:2, code:"DA220"/* 修罗圣衣 */],
		[probability:2, code:"WA119"/* 幽月斧 */],
		[probability:2, code:"WC119"/* 修罗爪 */],
		[probability:2, code:"WH110"/* 轩辕巨锤 */],
		[probability:2, code:"WM120"/* 灭绝 */],
		[probability:2, code:"WP120"/* 炎枪素戋鸣 */],
		[probability:2, code:"WS121"/* 亚罗栖 */],
		[probability:2, code:"ws221"/* 32dew2 */],
		[probability:2, code:"WT120"/* 天妒 */],
		[probability:2, code:"DB119"/* 死神战靴 */],
		[probability:2, code:"DS119"/* 蛮狮之盾 */],
		[probability:2, code:"OM119"/* 赤魔之心 */],
		[probability:2, code:"DG119"/* 炫彩护手 */],
		[probability:2, code:"OA219"/* 兽神臂环 */],
		[probability:2, code:"OR119"/* 轮回之戒 */],
		[probability:2, code:"OA119"/* 逆天纹章 */],
		[probability:2, code:"OS110"/* 钻晶石 */]
	],
	drops_more:[
	]
]
