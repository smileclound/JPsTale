monster = [
	ID:'120_mokova',
	Name:'暗夜死灵',
	Clazz:0,// 1 BOSS
	Brood:0x92,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:120,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:30000,
		// Attack Status
		Atk:[350, 380],
		AtkSpeed:8,
		Range:120,
		Rating:2000,

		// Defence Status
		Flee:1300,
		Absorb:78,
		Block:28,
		DamageStunPers:5,

		// Resistance
		Earth:100,
		Fire:100,
		Ice:100,// Water
		Lighting:100,// Wind
		Poison:100,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:8,
		Real_Sight:400,
		Talks:[],

		// Move Behavier
		Move_Speed:4,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:35,
		SkillDamage:[370, 430],
		SkillDistance:70,
		SkillRange:70,
		SkillRating:35,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:60,
		PotionCount:2,
	],

	Looks:[
		ClassCode:0,
		ArrowPosi:[0, 0],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:-1,
		Model:'char/monster/MonMokova/mokova.ini',
		Sound:0x000018B0,
	],

	// Drops
	AllSeeItem:false,
	Exp:4500000,
	Quantity:3,
	drops:[
		[probability:4000, code:"NULL"/* Drops nothing */],
		[probability:83, code:"DA121"/* 魔龙圣铠 */],
		[probability:83, code:"DA221"/* 涅磐圣衣 */],
		[probability:83, code:"WA119"/* 幽月斧 */],
		[probability:83, code:"WC119"/* 修罗爪 */],
		[probability:83, code:"WH120"/* 无畏 */],
		[probability:83, code:"WM120"/* 灭绝 */],
		[probability:83, code:"WP120"/* 炎枪素戋鸣 */],
		[probability:83, code:"WS121"/* 亚罗栖 */],
		[probability:83, code:"WS222"/* 赤焰流星 */],
		[probability:83, code:"WT120"/* 天妒 */],
		[probability:83, code:"DB119"/* 死神战靴 */],
		[probability:83, code:"DS119"/* 蛮狮之盾 */],
		[probability:83, code:"OM119"/* 赤魔之心 */],
		[probability:83, code:"DG119"/* 炫彩护手 */],
		[probability:83, code:"OA219"/* 兽神臂环 */],
		[probability:83, code:"OR120"/* 涅磐之戒 */],
		[probability:83, code:"OA120"/* 九转护符 */],
		[probability:83, code:"OS110"/* 钻晶石 */],
		[probability:83, code:"DA122"/* 炫金圣铠 */],
		[probability:83, code:"DA222"/* 雅典娜圣衣 */],
		[probability:83, code:"WA120"/* 米诺陶斯斧 */],
		[probability:83, code:"WC120"/* 寂灭龙爪 */],
		[probability:83, code:"WH121"/* 轰雷战锤 */],
		[probability:83, code:"WM121"/* 雅典娜之光 */],
		[probability:83, code:"WP121"/* 灵犀之镰 */],
		[probability:83, code:"WS122"/* 凤舞九天 */],
		[probability:83, code:"WS223"/* 创世之剑 */],
		[probability:83, code:"WT121"/* 龙翔标枪 */],
		[probability:83, code:"DB120"/* 炫金战靴 */],
		[probability:83, code:"DS120"/* 炫金之盾 */],
		[probability:83, code:"OM120"/* 炫钻水晶 */],
		[probability:83, code:"DG120"/* 炫金护手 */],
		[probability:83, code:"OA220"/* 炫金臂环 */],
		[probability:83, code:"OR121"/* 龙誉之戒 */],
		[probability:83, code:"OA121"/* 圣龙之光 */],
		[probability:83, code:"OS111"/* 龙睛石 */],
		[probability:55, code:"DA123"/* 凤凰圣铠 */],
		[probability:55, code:"DA223"/* 凤凰圣衣 */],
		[probability:55, code:"WA121"/* 苍红斧 */],
		[probability:55, code:"WC121"/* 灭牙爪 */],
		[probability:55, code:"WH122"/* 魁伐折罗 */],
		[probability:55, code:"WM122"/* 流泉月花 */],
		[probability:55, code:"WP122"/* 天之琼侔 */],
		[probability:55, code:"WS123"/* 红羽 */],
		[probability:55, code:"WS224"/* 暗牙黄泉津 */],
		[probability:55, code:"WT122"/* 暗破 */],
		[probability:55, code:"DB121"/* 凤凰战靴 */],
		[probability:55, code:"DS121"/* 凤凰之盾 */],
		[probability:55, code:"OM121"/* 海洋之心 */],
		[probability:55, code:"DG121"/* 凤凰护手 */],
		[probability:55, code:"OA221"/* 凤凰臂环 */],
		[probability:55, code:"OR122"/* 彩晶环 */],
		[probability:55, code:"OA122"/* 彩晶链 */],
		[probability:55, code:"OS112"/* 圣晶石 */],
		[probability:1250, code:"BI121"/* 魔法药剂(1天) */],
		[probability:1250, code:"BI128"/* 魔法圣水(1天) */],
		[probability:31, code:"DA124"/* 奥丁圣铠 */],
		[probability:31, code:"DA224"/* 奥丁圣衣 */],
		[probability:31, code:"WA122"/* 龙炎斧 */],
		[probability:31, code:"WC122"/* 黑虹爪 */],
		[probability:31, code:"WH123"/* 大鎚伊武岐 */],
		[probability:31, code:"WM123"/* 螺钿三日星 */],
		[probability:31, code:"WP123"/* 卡厄斯 */],
		[probability:31, code:"WS124"/* 天之麻迦古弓 */],
		[probability:31, code:"WS225"/* 32d22w */],
		[probability:31, code:"WT123"/* 天严云 */],
		[probability:31, code:"DB122"/* 奥丁战靴 */],
		[probability:31, code:"DS122"/* 奥丁之盾 */],
		[probability:31, code:"OM122"/* 恶魔法珠 */],
		[probability:31, code:"DG122"/* 奥丁护手 */],
		[probability:31, code:"OA222"/* 奥丁臂环 */],
		[probability:31, code:"OS113"/* 恶魔石 */]
	],
	drops_more:[
	]
]
