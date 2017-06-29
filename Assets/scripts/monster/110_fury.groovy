monster = [
	ID:'110_fury',
	Name:'狮面魔王',
	Clazz:0,// 1 BOSS
	Brood:0x92,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:110,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:30000,
		// Attack Status
		Atk:[400, 460],
		AtkSpeed:8,
		Range:90,
		Rating:2200,

		// Defence Status
		Flee:1500,
		Absorb:90,
		Block:25,
		DamageStunPers:60,

		// Resistance
		Earth:100,
		Fire:80,
		Ice:70,// Water
		Lighting:80,// Wind
		Poison:80,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:8,
		Real_Sight:440,
		Talks:[],

		// Move Behavier
		Move_Speed:7,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:20,
		SkillDamage:[460, 540],
		SkillDistance:0,
		SkillRange:110,
		SkillRating:20,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:30,
		PotionCount:1,
	],

	Looks:[
		ClassCode:0,
		ArrowPosi:[0, 30],
		ModelSize:1.2,
		UseEventModel:false,
		SizeLevel:3,
		Model:'char/monster/Monfury/Monfury.ini',
		Sound:0x00001310,
	],

	// Drops
	AllSeeItem:false,
	Exp:10300000,
	Quantity:2,
	drops:[
		[probability:4320, code:"NULL"/* Drops nothing */],
		[probability:199, code:"DA123"/* 凤凰圣铠 */],
		[probability:199, code:"DA223"/* 凤凰圣衣 */],
		[probability:199, code:"WA121"/* 苍红斧 */],
		[probability:199, code:"WC121"/* 灭牙爪 */],
		[probability:199, code:"WH122"/* 魁伐折罗 */],
		[probability:199, code:"WM122"/* 流泉月花 */],
		[probability:199, code:"WP122"/* 天之琼侔 */],
		[probability:199, code:"WS123"/* 红羽 */],
		[probability:199, code:"WS224"/* 暗牙黄泉津 */],
		[probability:199, code:"WT122"/* 暗破 */],
		[probability:199, code:"DB121"/* 凤凰战靴 */],
		[probability:199, code:"DS121"/* 凤凰之盾 */],
		[probability:199, code:"OM121"/* 海洋之心 */],
		[probability:199, code:"DG121"/* 凤凰护手 */],
		[probability:199, code:"OA221"/* 凤凰臂环 */],
		[probability:199, code:"OR122"/* 彩晶环 */],
		[probability:199, code:"OA122"/* 彩晶链 */],
		[probability:199, code:"OS112"/* 圣晶石 */],
		[probability:125, code:"DA124"/* 奥丁圣铠 */],
		[probability:125, code:"DA224"/* 奥丁圣衣 */],
		[probability:125, code:"WA122"/* 龙炎斧 */],
		[probability:125, code:"WC122"/* 黑虹爪 */],
		[probability:125, code:"WH123"/* 大鎚伊武岐 */],
		[probability:125, code:"WM123"/* 螺钿三日星 */],
		[probability:125, code:"WP123"/* 卡厄斯 */],
		[probability:125, code:"WS124"/* 天之麻迦古弓 */],
		[probability:125, code:"WS225"/* 32d22w */],
		[probability:125, code:"WT123"/* 天严云 */],
		[probability:125, code:"DB122"/* 奥丁战靴 */],
		[probability:125, code:"DS122"/* 奥丁之盾 */],
		[probability:125, code:"OM122"/* 恶魔法珠 */],
		[probability:125, code:"DG122"/* 奥丁护手 */],
		[probability:125, code:"OA222"/* 奥丁臂环 */],
		[probability:125, code:"OS113"/* 恶魔石 */],
		[probability:5, code:"DA125"/* 汉兰达铠 */],
		[probability:5, code:"DA225"/* 天使圣衣 */],
		[probability:5, code:"WA123"/* 龙之破坏者 */],
		[probability:5, code:"WC123"/* 红蛛之牙 */],
		[probability:5, code:"WH124"/* 龙王之锤 */],
		[probability:5, code:"WM124"/* 双龙阿修罗 */],
		[probability:5, code:"WP124"/* 烈枪降阎魔 */],
		[probability:5, code:"WS125"/* 潘多拉之弓 */],
		[probability:5, code:"WS226"/* 真龙神啸 */],
		[probability:5, code:"WT124"/* 皓月谙晓 */],
		[probability:5, code:"DB123"/* 圣龙之靴 */],
		[probability:5, code:"DS123"/* 地狱之盾 */],
		[probability:5, code:"OM123"/* 紫金之光 */],
		[probability:5, code:"DG123"/* 圣龙护手 */],
		[probability:5, code:"OA223"/* 圣龙臂环 */],
		[probability:5, code:"OS114"/* 彩晶石 */]
	],
	drops_more:[
	]
]
