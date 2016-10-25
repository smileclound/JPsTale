monster = [
	ID:'12_doral',
	Name:'双足虫',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:12,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:110,
		// Attack Status
		Atk:[7, 12],
		AtkSpeed:7,
		Range:60,
		Rating:300,

		// Defence Status
		Flee:46,
		Absorb:6,
		Block:0,
		DamageStunPers:100,

		// Resistance
		Earth:20,
		Fire:15,
		Ice:20,// Water
		Lighting:0,// Wind
		Poison:50,
	],

	// AI
	AI:[
		Nature:0x80,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:6,
		Real_Sight:300,
		Talks:[],

		// Move Behavier
		Move_Speed:3,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:20,
		SkillDamage:[0, 0],
		SkillDistance:0,
		SkillRange:0,
		SkillRating:0,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:0,
		PotionCount:0,
	],

	Looks:[
		ClassCode:50,
		ArrowPosi:[0, 60],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:1,
		Model:'char/monster/doral/doral.ini',
		Sound:0x00001170,
	],

	// Drops
	AllSeeItem:false,
	Exp:570,
	Quantity:1,
	drops:[
		[probability:3700, code:"NULL"/* Drops nothing */],
		[probability:4200, code:"GG101", value:[20, 50]/* Gold */],
		[probability:466, code:"PL101"/* 低级复生命药水 */],
		[probability:466, code:"PL102"/* 中级恢复生命药水 */],
		[probability:466, code:"PS102"/* 中级恢复耐力药水 */],
		[probability:33, code:"WA102"/* 铁斧 */],
		[probability:33, code:"WC102"/* 虎爪 */],
		[probability:33, code:"WH102"/* 星刺锤 */],
		[probability:33, code:"WS102"/* 羊角弓 */],
		[probability:33, code:"WS202"/* 蛇行匕首 */],
		[probability:33, code:"WP102"/* 点钢蛇矛 */],
		[probability:33, code:"WS102"/* 羊角弓 */],
		[probability:33, code:"WS202"/* 蛇行匕首 */],
		[probability:33, code:"WT102"/* 战标 */],
		[probability:33, code:"PM101"/* 低级恢复魔法药水 */],
		[probability:33, code:"OR103"/* 金戒指 */],
		[probability:33, code:"OA103"/* 多情环 */],
		[probability:14, code:"WA103"/* 板斧 */],
		[probability:14, code:"WC103"/* 鱼镰刺 */],
		[probability:14, code:"WH103"/* 长锤 */],
		[probability:14, code:"WS203"/* 断剑 */],
		[probability:14, code:"WS103"/* 手弩 */],
		[probability:14, code:"WP103"/* 鹰嘴矛 */],
		[probability:14, code:"WS103"/* 手弩 */],
		[probability:14, code:"WS203"/* 断剑 */],
		[probability:14, code:"WT103"/* 长牙标 */],
		[probability:14, code:"PL101"/* 低级复生命药水 */],
		[probability:14, code:"PM101"/* 低级恢复魔法药水 */],
		[probability:14, code:"PS101"/* 低级恢复耐力药水 */],
		[probability:14, code:"OR104"/* 玉戒指 */],
		[probability:14, code:"OA104"/* 金项链 */],
		[probability:7, code:"WA104"/* 劈地斧 */],
		[probability:7, code:"WC104"/* 蛇牙刺 */],
		[probability:7, code:"WS104"/* 十字弩 */],
		[probability:7, code:"WS204"/* 短剑 */],
		[probability:7, code:"WH104"/* 巨灵锤 */],
		[probability:7, code:"WP104"/* 方天战戟 */],
		[probability:7, code:"WS104"/* 十字弩 */],
		[probability:7, code:"WS204"/* 短剑 */],
		[probability:7, code:"WT104"/* 铁标 */],
		[probability:7, code:"PM102"/* 中级恢复魔法药水 */],
		[probability:7, code:"PS102"/* 中级恢复耐力药水 */],
		[probability:7, code:"PL102"/* 中级恢复生命药水 */],
		[probability:7, code:"OR105"/* 蓝宝石戒指 */],
		[probability:7, code:"OA105"/* 梦之心链 */]
	],
	drops_more:[
	]
]
