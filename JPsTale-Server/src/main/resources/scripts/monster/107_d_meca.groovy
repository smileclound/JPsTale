monster = [
	ID:'107_d_meca',
	Name:'机甲武士',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:125,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:15500,
		// Attack Status
		Atk:[190, 210],
		AtkSpeed:8,
		Range:60,
		Rating:1800,

		// Defence Status
		Flee:1150,
		Absorb:55,
		Block:28,
		DamageStunPers:20,

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
		Real_Sight:380,
		Talks:[],

		// Move Behavier
		Move_Speed:1,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:30,
		SkillDamage:[220, 260],
		SkillDistance:0,
		SkillRange:0,
		SkillRating:60,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:30,
		PotionCount:1,
	],

	Looks:[
		ClassCode:70,
		ArrowPosi:[0, 0],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:1,
		Model:'char/monster/d_meca/dmeca.ini',
		Sound:0x00001940,
	],

	// Drops
	AllSeeItem:false,
	Exp:1525000,
	Quantity:1,
	drops:[
		[probability:2105, code:"NULL"/* Drops nothing */],
		[probability:3020, code:"GG101", value:[3650, 4050]/* Gold */],
		[probability:666, code:"PL104"/* 顶级恢复生命药水 */],
		[probability:666, code:"PS104"/* 顶级恢复耐力药水 */],
		[probability:666, code:"PM104"/* 顶级恢复魔法药水 */],
		[probability:66, code:"DA121"/* 魔龙圣铠 */],
		[probability:66, code:"DA221"/* 涅磐圣衣 */],
		[probability:66, code:"WA119"/* 幽月斧 */],
		[probability:66, code:"WC119"/* 修罗爪 */],
		[probability:66, code:"WH120"/* 无畏 */],
		[probability:66, code:"WM120"/* 灭绝 */],
		[probability:66, code:"WP120"/* 炎枪素戋鸣 */],
		[probability:66, code:"WS121"/* 亚罗栖 */],
		[probability:66, code:"WS222"/* 赤焰流星 */],
		[probability:66, code:"WT120"/* 天妒 */],
		[probability:66, code:"DB119"/* 死神战靴 */],
		[probability:66, code:"DS119"/* 蛮狮之盾 */],
		[probability:66, code:"OM120"/* 炫钻水晶 */],
		[probability:66, code:"DG119"/* 炫彩护手 */],
		[probability:66, code:"OA219"/* 兽神臂环 */],
		[probability:66, code:"OR118"/* 封神之戒 */],
		[probability:66, code:"OA118"/* 圣光勋章 */],
		[probability:66, code:"OS110"/* 钻晶石 */],
		[probability:58, code:"DA122"/* 炫金圣铠 */],
		[probability:58, code:"DA222"/* 雅典娜圣衣 */],
		[probability:58, code:"WA120"/* 米诺陶斯斧 */],
		[probability:58, code:"WC120"/* 寂灭龙爪 */],
		[probability:58, code:"WH121"/* 轰雷战锤 */],
		[probability:58, code:"WM121"/* 雅典娜之光 */],
		[probability:58, code:"WP121"/* 灵犀之镰 */],
		[probability:58, code:"WS122"/* 凤舞九天 */],
		[probability:58, code:"WS223"/* 创世之剑 */],
		[probability:58, code:"WT121"/* 龙翔标枪 */],
		[probability:58, code:"DB120"/* 炫金战靴 */],
		[probability:58, code:"DS120"/* 炫金之盾 */],
		[probability:58, code:"OM121"/* 海洋之心 */],
		[probability:58, code:"DG120"/* 炫金护手 */],
		[probability:58, code:"OA220"/* 炫金臂环 */],
		[probability:58, code:"OR119"/* 轮回之戒 */],
		[probability:58, code:"OA119"/* 逆天纹章 */],
		[probability:58, code:"OS111"/* 龙睛石 */],
		[probability:31, code:"DA123"/* 凤凰圣铠 */],
		[probability:31, code:"DA223"/* 凤凰圣衣 */],
		[probability:31, code:"WA121"/* 苍红斧 */],
		[probability:31, code:"WC121"/* 灭牙爪 */],
		[probability:31, code:"WH122"/* 魁伐折罗 */],
		[probability:31, code:"WM122"/* 流泉月花 */],
		[probability:31, code:"WP122"/* 天之琼侔 */],
		[probability:31, code:"WS123"/* 红羽 */],
		[probability:31, code:"WS224"/* 暗牙黄泉津 */],
		[probability:31, code:"WT122"/* 暗破 */],
		[probability:31, code:"DB121"/* 凤凰战靴 */],
		[probability:31, code:"DS121"/* 凤凰之盾 */],
		[probability:31, code:"OM122"/* 恶魔法珠 */],
		[probability:31, code:"DG121"/* 凤凰护手 */],
		[probability:31, code:"OA221"/* 凤凰臂环 */],
		[probability:31, code:"OR120"/* 涅磐之戒 */],
		[probability:31, code:"OA120"/* 九转护符 */],
		[probability:31, code:"OS112"/* 圣晶石 */],
		[probability:3, code:"DA124"/* 奥丁圣铠 */],
		[probability:3, code:"DA224"/* 奥丁圣衣 */],
		[probability:3, code:"WA122"/* 龙炎斧 */],
		[probability:3, code:"WC122"/* 黑虹爪 */],
		[probability:3, code:"WH123"/* 大鎚伊武岐 */],
		[probability:3, code:"WM123"/* 螺钿三日星 */],
		[probability:3, code:"WP123"/* 卡厄斯 */],
		[probability:3, code:"WS124"/* 天之麻迦古弓 */],
		[probability:3, code:"WS225"/* 32d22w */],
		[probability:3, code:"WT123"/* 天严云 */],
		[probability:3, code:"DB122"/* 奥丁战靴 */],
		[probability:3, code:"DS122"/* 奥丁之盾 */],
		[probability:3, code:"OM123"/* 紫金之光 */],
		[probability:3, code:"DG122"/* 奥丁护手 */],
		[probability:3, code:"OA222"/* 奥丁臂环 */],
		[probability:3, code:"OR121"/* 龙誉之戒 */],
		[probability:3, code:"OA121"/* 圣龙之光 */],
		[probability:3, code:"OS113"/* 恶魔石 */]
	],
	drops_more:[
	]
]
