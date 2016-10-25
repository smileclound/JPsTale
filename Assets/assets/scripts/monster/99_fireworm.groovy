monster = [
	ID:'99_fireworm',
	Name:'火烈树',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:99,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:11500,
		// Attack Status
		Atk:[100, 110],
		AtkSpeed:8,
		Range:350,
		Rating:1600,

		// Defence Status
		Flee:640,
		Absorb:30,
		Block:20,
		DamageStunPers:10,

		// Resistance
		Earth:32,
		Fire:68,
		Ice:-10,// Water
		Lighting:32,// Wind
		Poison:38,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:8,
		Real_Sight:390,
		Talks:[],

		// Move Behavier
		Move_Speed:0,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:30,
		SkillDamage:[120, 140],
		SkillDistance:100,
		SkillRange:100,
		SkillRating:20,
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
		Model:'char/monster/Fireworm/fireworm.ini',
		Sound:0x00001870,
	],

	// Drops
	AllSeeItem:false,
	Exp:550000,
	Quantity:1,
	drops:[
		[probability:1330, code:"NULL"/* Drops nothing */],
		[probability:2341, code:"GG101", value:[480, 580]/* Gold */],
		[probability:192, code:"DA117"/* 远古圣铠 */],
		[probability:192, code:"DA217"/* 天使法袍 */],
		[probability:192, code:"WA115"/* 泰坦斧 */],
		[probability:192, code:"WC115"/* 魔玉爪 */],
		[probability:192, code:"WH116"/* 轰天锤 */],
		[probability:192, code:"WM116"/* 诸神的黄昏 */],
		[probability:192, code:"WP116"/* 虹月 */],
		[probability:192, code:"WS117"/* 破鹫 */],
		[probability:192, code:"WS217"/* 鬼切 */],
		[probability:192, code:"WT116"/* 玛雅神标 */],
		[probability:192, code:"DB115"/* 时空之靴 */],
		[probability:192, code:"DS115"/* 亢龙之盾 */],
		[probability:192, code:"OM115"/* 炫彩水晶 */],
		[probability:192, code:"DG115"/* 赤龙护手 */],
		[probability:192, code:"OA215"/* 赤龙臂环 */],
		[probability:192, code:"OR115"/* 守护之戒 */],
		[probability:192, code:"OA115"/* 暗印护符 */],
		[probability:192, code:"OS108"/* 虎翼石 */],
		[probability:129, code:"DA118"/* 米诺陶斯铠 */],
		[probability:129, code:"DA218"/* 撒旦披风 */],
		[probability:129, code:"WA116"/* 轮回战斧 */],
		[probability:129, code:"WC116"/* 泰坦爪 */],
		[probability:129, code:"WH117"/* 兽神锤 */],
		[probability:129, code:"WM117"/* 神圣之光 */],
		[probability:129, code:"WP117"/* 银河战镰 */],
		[probability:129, code:"WS118"/* 风切 */],
		[probability:129, code:"WS218"/* 天裂 */],
		[probability:129, code:"WT117"/* 夜叉 */],
		[probability:129, code:"DB118"/* 符文之靴 */],
		[probability:129, code:"DS116"/* 远古之盾 */],
		[probability:129, code:"OM116"/* 龙之护身 */],
		[probability:129, code:"DG116"/* 星辰护手 */],
		[probability:129, code:"OA216"/* 鎏金臂环 */],
		[probability:129, code:"OR116"/* 雅典娜之吻 */],
		[probability:129, code:"OA116"/* 苍穹之链 */],
		[probability:129, code:"OS109"/* 龙鳞石 */],
		[probability:29, code:"DA119"/* 死神绝命铠 */],
		[probability:29, code:"DA219"/* 幻彩羽袍 */],
		[probability:29, code:"WA117"/* 远古战斧 */],
		[probability:29, code:"WC117"/* 冰魄爪 */],
		[probability:29, code:"WH118"/* 灭神锤 */],
		[probability:29, code:"WM118"/* 日月同辉 */],
		[probability:29, code:"WP118"/* 月影神矛 */],
		[probability:29, code:"WS119"/* 丘比特之弓 */],
		[probability:29, code:"WS219"/* 凝雾 */],
		[probability:29, code:"WT118"/* 噬月 */],
		[probability:29, code:"DB117"/* 烈焰靴 */],
		[probability:29, code:"DS117"/* 米诺陶斯盾 */],
		[probability:29, code:"OM117"/* 绿釉之眼 */],
		[probability:29, code:"DG117"/* 炫钻护手 */],
		[probability:29, code:"OA217"/* 炫钻臂环 */],
		[probability:29, code:"OR117"/* 封魔之戒 */],
		[probability:29, code:"OA117"/* 天眼护符 */],
		[probability:29, code:"OS110"/* 钻晶石 */],
		[probability:1, code:"DA120"/* 炎龙圣铠 */],
		[probability:1, code:"DA220"/* 修罗圣衣 */],
		[probability:1, code:"WA118"/* 雷神斧 */],
		[probability:1, code:"WC118"/* 星刺爪 */],
		[probability:1, code:"WH119"/* 弑神 */],
		[probability:1, code:"WM119"/* 末日辉煌 */],
		[probability:1, code:"WP119"/* 棲羽亚陀 */],
		[probability:1, code:"WS120"/* 羽裂 */],
		[probability:1, code:"WS220"/* 龙牙 */],
		[probability:1, code:"WT119"/* 流星 */],
		[probability:1, code:"DB118"/* 符文之靴 */],
		[probability:1, code:"DS118"/* 死神之盾 */],
		[probability:1, code:"OM118"/* 黑魔 */],
		[probability:1, code:"DG118"/* 泰坦护手 */],
		[probability:1, code:"OA218"/* 泰坦臂环 */],
		[probability:1, code:"OR118"/* 封神之戒 */],
		[probability:1, code:"OA118"/* 圣光勋章 */],
		[probability:1, code:"OS111"/* 龙睛石 */]
	],
	drops_more:[
	]
]
