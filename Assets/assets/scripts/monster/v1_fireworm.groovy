monster = [
	ID:'v1_fireworm',
	Name:'Fireworm',
	Clazz:0,// 1 BOSS
	Brood:0x93,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:83,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:8000,
		// Attack Status
		Atk:[104, 125],
		AtkSpeed:8,
		Range:220,
		Rating:1450,

		// Defence Status
		Flee:820,
		Absorb:18,
		Block:8,
		DamageStunPers:30,

		// Resistance
		Earth:0,
		Fire:60,
		Ice:100,// Water
		Lighting:80,// Wind
		Poison:80,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:8,
		Real_Sight:420,
		Talks:[],

		// Move Behavier
		Move_Speed:4,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:30,
		SkillDamage:[133, 145],
		SkillDistance:0,
		SkillRange:170,
		SkillRating:17,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:0,
		PotionCount:0,
	],

	Looks:[
		ClassCode:0,
		ArrowPosi:[4, 0],
		ModelSize:0.9,
		UseEventModel:false,
		SizeLevel:1,
		Model:'char/monster/Fireworm/fireworm.ini',
		Sound:0x00001870,
	],

	// Drops
	AllSeeItem:false,
	Exp:22000,
	Quantity:1,
	drops:[
		[probability:4420, code:"NULL"/* Drops nothing */],
		[probability:4570, code:"GG101", value:[380, 500]/* Gold */],
		[probability:37, code:"WA118"/* 雷神斧 */],
		[probability:37, code:"WC118"/* 星刺爪 */],
		[probability:37, code:"WH118"/* 灭神锤 */],
		[probability:37, code:"WM119"/* 末日辉煌 */],
		[probability:37, code:"WP118"/* 月影神矛 */],
		[probability:37, code:"WS108"/* 巨弩 */],
		[probability:37, code:"ws221"/* 32dew2 */],
		[probability:37, code:"WT119"/* 流星 */],
		[probability:37, code:"DA115"/* 泰坦战铠 */],
		[probability:37, code:"DA215"/* 文章法袍 */],
		[probability:37, code:"DS117"/* 米诺陶斯盾 */],
		[probability:37, code:"OM119"/* 赤魔之心 */],
		[probability:16, code:"WA119"/* 幽月斧 */],
		[probability:16, code:"WC119"/* 修罗爪 */],
		[probability:16, code:"WH120"/* 无畏 */],
		[probability:16, code:"WM120"/* 灭绝 */],
		[probability:16, code:"WP120"/* 炎枪素戋鸣 */],
		[probability:16, code:"WS121"/* 亚罗栖 */],
		[probability:16, code:"WS222"/* 赤焰流星 */],
		[probability:16, code:"WT120"/* 天妒 */],
		[probability:16, code:"DA116"/* 暗黑铠 */],
		[probability:16, code:"DA216"/* 祝福法袍 */],
		[probability:16, code:"DS119"/* 蛮狮之盾 */],
		[probability:16, code:"OM120"/* 炫钻水晶 */],
		[probability:8, code:"WA120"/* 米诺陶斯斧 */],
		[probability:8, code:"WC120"/* 寂灭龙爪 */],
		[probability:8, code:"WH121"/* 轰雷战锤 */],
		[probability:8, code:"WM121"/* 雅典娜之光 */],
		[probability:8, code:"WP121"/* 灵犀之镰 */],
		[probability:8, code:"WS122"/* 凤舞九天 */],
		[probability:8, code:"WS223"/* 创世之剑 */],
		[probability:8, code:"WT121"/* 龙翔标枪 */],
		[probability:8, code:"DA117"/* 远古圣铠 */],
		[probability:8, code:"DA217"/* 天使法袍 */],
		[probability:8, code:"DS120"/* 炫金之盾 */],
		[probability:8, code:"OM121"/* 海洋之心 */],
		[probability:1, code:"WA122"/* 龙炎斧 */],
		[probability:1, code:"WC122"/* 黑虹爪 */],
		[probability:1, code:"WH123"/* 大鎚伊武岐 */],
		[probability:1, code:"WM123"/* 螺钿三日星 */],
		[probability:1, code:"WP123"/* 卡厄斯 */],
		[probability:1, code:"WS124"/* 天之麻迦古弓 */],
		[probability:1, code:"WS225"/* 32d22w */],
		[probability:1, code:"WT123"/* 天严云 */],
		[probability:1, code:"DA119"/* 死神绝命铠 */],
		[probability:1, code:"DA219"/* 幻彩羽袍 */],
		[probability:1, code:"DS122"/* 奥丁之盾 */],
		[probability:1, code:"OM123"/* 紫金之光 */],
		[probability:31, code:"DB120"/* 炫金战靴 */],
		[probability:31, code:"DG120"/* 炫金护手 */],
		[probability:31, code:"OA220"/* 炫金臂环 */],
		[probability:31, code:"DB121"/* 凤凰战靴 */],
		[probability:31, code:"DG121"/* 凤凰护手 */],
		[probability:31, code:"OA221"/* 凤凰臂环 */],
		[probability:5, code:"OA116"/* 苍穹之链 */],
		[probability:5, code:"OR117"/* 封魔之戒 */],
		[probability:5, code:"OA117"/* 天眼护符 */],
		[probability:5, code:"OR118"/* 封神之戒 */],
		[probability:5, code:"OA118"/* 圣光勋章 */],
		[probability:5, code:"OS104"/* 天仪石 */],
		[probability:5, code:"OS105"/* 冰晶石 */],
		[probability:5, code:"OS106"/* 玄风石 */],
		[probability:5, code:"OS107"/* 水晶石 */],
		[probability:5, code:"OS108"/* 虎翼石 */]
	],
	drops_more:[
	]
]
