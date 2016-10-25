monster = [
	ID:'v2_kingspider',
	Name:'KingSpider',
	Clazz:0,// 1 BOSS
	Brood:0x92,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:87,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:8800,
		// Attack Status
		Atk:[110, 125],
		AtkSpeed:9,
		Range:80,
		Rating:1500,

		// Defence Status
		Flee:1200,
		Absorb:20,
		Block:15,
		DamageStunPers:10,

		// Resistance
		Earth:0,
		Fire:40,
		Ice:40,// Water
		Lighting:30,// Wind
		Poison:60,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:8,
		Real_Sight:350,
		Talks:[],

		// Move Behavier
		Move_Speed:6,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:0,
		SkillDamage:[135, 159],
		SkillDistance:0,
		SkillRange:0,
		SkillRating:17,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:0,
		PotionCount:0,
	],

	Looks:[
		ClassCode:0,
		ArrowPosi:[0, 25],
		ModelSize:1.2,
		UseEventModel:false,
		SizeLevel:2,
		Model:'char/monster/MonKingspider/ks.ini',
		Sound:0x000018E0,
	],

	// Drops
	AllSeeItem:false,
	Exp:26000,
	Quantity:1,
	drops:[
		[probability:4400, code:"NULL"/* Drops nothing */],
		[probability:4590, code:"GG101", value:[480, 800]/* Gold */],
		[probability:37, code:"WA119"/* 幽月斧 */],
		[probability:37, code:"WC119"/* 修罗爪 */],
		[probability:37, code:"WH120"/* 无畏 */],
		[probability:37, code:"WM120"/* 灭绝 */],
		[probability:37, code:"WP120"/* 炎枪素戋鸣 */],
		[probability:37, code:"WS121"/* 亚罗栖 */],
		[probability:37, code:"WS222"/* 赤焰流星 */],
		[probability:37, code:"WT120"/* 天妒 */],
		[probability:37, code:"DA116"/* 暗黑铠 */],
		[probability:37, code:"DA216"/* 祝福法袍 */],
		[probability:37, code:"DS119"/* 蛮狮之盾 */],
		[probability:37, code:"OM120"/* 炫钻水晶 */],
		[probability:16, code:"WA120"/* 米诺陶斯斧 */],
		[probability:16, code:"WC120"/* 寂灭龙爪 */],
		[probability:16, code:"WH121"/* 轰雷战锤 */],
		[probability:16, code:"WM121"/* 雅典娜之光 */],
		[probability:16, code:"WP121"/* 灵犀之镰 */],
		[probability:16, code:"WS122"/* 凤舞九天 */],
		[probability:16, code:"WS223"/* 创世之剑 */],
		[probability:16, code:"WT121"/* 龙翔标枪 */],
		[probability:16, code:"DA117"/* 远古圣铠 */],
		[probability:16, code:"DA217"/* 天使法袍 */],
		[probability:16, code:"DS120"/* 炫金之盾 */],
		[probability:16, code:"OM121"/* 海洋之心 */],
		[probability:8, code:"WA121"/* 苍红斧 */],
		[probability:8, code:"WC121"/* 灭牙爪 */],
		[probability:8, code:"WH122"/* 魁伐折罗 */],
		[probability:8, code:"WM122"/* 流泉月花 */],
		[probability:8, code:"WP122"/* 天之琼侔 */],
		[probability:8, code:"WS123"/* 红羽 */],
		[probability:8, code:"WS224"/* 暗牙黄泉津 */],
		[probability:8, code:"WT122"/* 暗破 */],
		[probability:8, code:"DA118"/* 米诺陶斯铠 */],
		[probability:8, code:"DA218"/* 撒旦披风 */],
		[probability:8, code:"DS121"/* 凤凰之盾 */],
		[probability:8, code:"OM122"/* 恶魔法珠 */],
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
		[probability:31, code:"DB121"/* 凤凰战靴 */],
		[probability:31, code:"DG121"/* 凤凰护手 */],
		[probability:31, code:"OA221"/* 凤凰臂环 */],
		[probability:31, code:"DB122"/* 奥丁战靴 */],
		[probability:31, code:"DG122"/* 奥丁护手 */],
		[probability:31, code:"OA222"/* 奥丁臂环 */],
		[probability:5, code:"OR121"/* 龙誉之戒 */],
		[probability:5, code:"OA121"/* 圣龙之光 */],
		[probability:5, code:"OR201"/* 瘤粮 馆瘤 */],
		[probability:5, code:"OA102"/* 红玉项链 */],
		[probability:5, code:"OR101"/* 情殇 */],
		[probability:5, code:"OS106"/* 玄风石 */],
		[probability:5, code:"OS107"/* 水晶石 */],
		[probability:5, code:"OS108"/* 虎翼石 */],
		[probability:5, code:"OS109"/* 龙鳞石 */]
	],
	drops_more:[
	]
]
