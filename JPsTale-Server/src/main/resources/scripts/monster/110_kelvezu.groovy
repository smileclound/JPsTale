monster = [
	ID:'110_kelvezu',
	Name:'凯尔维苏',
	Clazz:0,// 1 BOSS
	Brood:0x92,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:110,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:30000,
		// Attack Status
		Atk:[2020, 2760],
		AtkSpeed:9,
		Range:480,
		Rating:1800,

		// Defence Status
		Flee:5000,
		Absorb:99,
		Block:84,
		DamageStunPers:5,

		// Resistance
		Earth:500,
		Fire:500,
		Ice:500,// Water
		Lighting:150,// Wind
		Poison:500,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:8,
		Real_Sight:500,
		Talks:[],

		// Move Behavier
		Move_Speed:1,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:40,
		SkillDamage:[1740, 1810],
		SkillDistance:200,
		SkillRange:480,
		SkillRating:40,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:60,
		PotionCount:4,
	],

	Looks:[
		ClassCode:0,
		ArrowPosi:[0, 0],
		ModelSize:0.5,
		UseEventModel:false,
		SizeLevel:3,
		Model:'char/monster/Kelvezu/Kelvezu.ini',
		Sound:0x00001830,
	],

	// Drops
	AllSeeItem:false,
	Exp:10000000,
	Quantity:3,
	drops:[
		[probability:2333, code:"OS110"/* 钻晶石 */],
		[probability:2333, code:"OS111"/* 龙睛石 */],
		[probability:2333, code:"OS112"/* 圣晶石 */],
		[probability:333, code:"OS112"/* 圣晶石 */],
		[probability:333, code:"OS113"/* 恶魔石 */],
		[probability:333, code:"OS114"/* 彩晶石 */],
		[probability:500, code:"OA130"/* 凯尔维苏项链 */],
		[probability:250, code:"OA130"/* 凯尔维苏项链 */]
	],
	drops_more:[
	]
]
