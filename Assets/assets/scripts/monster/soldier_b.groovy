monster = [
	ID:'soldier_b',
	Name:'警卫兵',
	Clazz:0,// 1 BOSS
	Brood:0x92,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:88,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:16000,
		// Attack Status
		Atk:[480, 515],
		AtkSpeed:9,
		Range:80,
		Rating:1450,

		// Defence Status
		Flee:4720,
		Absorb:73,
		Block:12,
		DamageStunPers:60,

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
		Real_Sight:400,
		Talks:[],

		// Move Behavier
		Move_Speed:4,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:20,
		SkillDamage:[520, 540],
		SkillDistance:0,
		SkillRange:130,
		SkillRating:20,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:0,
		PotionCount:2,
	],

	Looks:[
		ClassCode:0,
		ArrowPosi:[0, 25],
		ModelSize:1.2,
		UseEventModel:false,
		SizeLevel:-1,
		Model:'char/monster/Soldier_B/Soldier_B.ini',
		Sound:0x00005110,
	],

	// Drops
	AllSeeItem:false,
	Exp:1960000,
	Quantity:2,
	drops:[
		[probability:2000, code:"NULL"/* Drops nothing */],
		[probability:2500, code:"GG101", value:[1250, 2250]/* Gold */],
		[probability:5000, code:"FO114"/* 荣誉之力 */]
	],
	drops_more:[
	]
]
