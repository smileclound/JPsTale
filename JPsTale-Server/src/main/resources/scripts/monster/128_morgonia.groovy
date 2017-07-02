monster = [
	ID:'128_morgonia',
	Name:'巨斧守卫',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:125,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:23000,
		// Attack Status
		Atk:[290, 310],
		AtkSpeed:6,
		Range:75,
		Rating:1600,

		// Defence Status
		Flee:1500,
		Absorb:70,
		Block:30,
		DamageStunPers:100,

		// Resistance
		Earth:70,
		Fire:20,
		Ice:80,// Water
		Lighting:-100,// Wind
		Poison:90,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:9,
		Real_Sight:400,
		Talks:[],

		// Move Behavier
		Move_Speed:2,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:30,
		SkillDamage:[310, 330],
		SkillDistance:0,
		SkillRange:110,
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
		Model:'char/monster/Morgon/Morgonia.ini',
		Sound:0x00000000,
	],

	// Drops
	AllSeeItem:false,
	Exp:315000,
	Quantity:1,
	drops:[
		[probability:5000, code:"NULL"/* Drops nothing */],
		[probability:2000, code:"GG101", value:[1250, 2250]/* Gold */],
		[probability:400, code:"PL104"/* 顶级恢复生命药水 */],
		[probability:400, code:"PM104"/* 顶级恢复魔法药水 */],
		[probability:400, code:"PS104"/* 顶级恢复耐力药水 */],
		[probability:400, code:"PM104"/* 顶级恢复魔法药水 */],
		[probability:50, code:"DA123"/* 凤凰圣铠 */],
		[probability:50, code:"DA223"/* 凤凰圣衣 */],
		[probability:50, code:"OA221"/* 凤凰臂环 */],
		[probability:50, code:"DS121"/* 凤凰之盾 */],
		[probability:50, code:"DB121"/* 凤凰战靴 */],
		[probability:50, code:"DG121"/* 凤凰护手 */],
		[probability:50, code:"WA121"/* 苍红斧 */],
		[probability:50, code:"WP122"/* 天之琼侔 */],
		[probability:50, code:"WC121"/* 灭牙爪 */],
		[probability:50, code:"WH122"/* 魁伐折罗 */],
		[probability:50, code:"WM122"/* 流泉月花 */],
		[probability:50, code:"WS123"/* 红羽 */],
		[probability:50, code:"WS224"/* 暗牙黄泉津 */],
		[probability:50, code:"WT122"/* 暗破 */],
		[probability:50, code:"OA118"/* 圣光勋章 */],
		[probability:50, code:"OR118"/* 封神之戒 */],
		[probability:50, code:"OS112"/* 圣晶石 */],
		[probability:50, code:"GP122"/* 守护水晶 */],
		[probability:50, code:"SE101"/* 造化石 */],
		[probability:20, code:"DA124"/* 奥丁圣铠 */],
		[probability:20, code:"DA224"/* 奥丁圣衣 */],
		[probability:20, code:"OA222"/* 奥丁臂环 */],
		[probability:20, code:"DS122"/* 奥丁之盾 */],
		[probability:20, code:"DB122"/* 奥丁战靴 */],
		[probability:20, code:"DG122"/* 奥丁护手 */],
		[probability:20, code:"WA122"/* 龙炎斧 */],
		[probability:20, code:"WP123"/* 卡厄斯 */],
		[probability:20, code:"WC122"/* 黑虹爪 */],
		[probability:20, code:"WH123"/* 大鎚伊武岐 */],
		[probability:20, code:"WM123"/* 螺钿三日星 */],
		[probability:20, code:"WS124"/* 天之麻迦古弓 */],
		[probability:20, code:"WS225"/* 32d22w */],
		[probability:20, code:"WT123"/* 天严云 */],
		[probability:20, code:"OA119"/* 逆天纹章 */],
		[probability:20, code:"OR119"/* 轮回之戒 */],
		[probability:20, code:"OS113"/* 恶魔石 */],
		[probability:2, code:"DA125"/* 汉兰达铠 */],
		[probability:2, code:"DA225"/* 天使圣衣 */],
		[probability:2, code:"OA223"/* 圣龙臂环 */],
		[probability:2, code:"DS123"/* 地狱之盾 */],
		[probability:2, code:"DB123"/* 圣龙之靴 */],
		[probability:2, code:"DG123"/* 圣龙护手 */],
		[probability:2, code:"WA123"/* 龙之破坏者 */],
		[probability:2, code:"WP124"/* 烈枪降阎魔 */],
		[probability:2, code:"WC123"/* 红蛛之牙 */],
		[probability:2, code:"WH124"/* 龙王之锤 */],
		[probability:2, code:"WM124"/* 双龙阿修罗 */],
		[probability:2, code:"WS125"/* 潘多拉之弓 */],
		[probability:2, code:"WS226"/* 真龙神啸 */],
		[probability:2, code:"WT124"/* 皓月谙晓 */],
		[probability:2, code:"OA120"/* 九转护符 */],
		[probability:2, code:"OR120"/* 涅磐之戒 */],
		[probability:2, code:"OS114"/* 彩晶石 */],
		[probability:10, code:"OA121"/* 圣龙之光 */],
		[probability:10, code:"OR121"/* 龙誉之戒 */],
		[probability:10, code:"OS114"/* 彩晶石 */]
	],
	drops_more:[
	]
]
