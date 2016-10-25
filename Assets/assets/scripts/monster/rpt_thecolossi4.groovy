monster = [
	ID:'rpt_thecolossi4',
	Name:'夜魔族骑士★★★',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:145,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:30000,
		// Attack Status
		Atk:[860, 900],
		AtkSpeed:7,
		Range:80,
		Rating:3400,

		// Defence Status
		Flee:5500,
		Absorb:90,
		Block:30,
		DamageStunPers:100,

		// Resistance
		Earth:70,
		Fire:20,
		Ice:80,// Water
		Lighting:30,// Wind
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
		SkillDamage:[940, 960],
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
		ModelSize:13.2,
		UseEventModel:false,
		SizeLevel:1,
		Model:'char/monster/RPT_TheColossi/Colossus4.ini',
		Sound:0x00001840,
	],

	// Drops
	AllSeeItem:false,
	Exp:8000000,
	Quantity:1,
	drops:[
		[probability:4960, code:"NULL"/* Drops nothing */],
		[probability:5, code:"SE104"/* 祈祷石 */],
		[probability:5, code:"PR101"/* 紫色矿石 */],
		[probability:5, code:"PR102"/* 银色矿石 */],
		[probability:5, code:"PR103"/* 金色矿石 */],
		[probability:5, code:"PR104"/* 天蓝矿石 */],
		[probability:5, code:"PR105"/* 海蓝矿石 */],
		[probability:5, code:"PR106"/* 橙色矿石 */],
		[probability:5, code:"PR107"/* 红色矿石 */],
		[probability:5000, code:"GG101", value:[3650, 4050]/* Gold */]
	],
	drops_more:[
	]
]
