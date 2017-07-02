monster = [
	ID:'soldier_a',
	Name:'卫兵',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:75,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:12000,
		// Attack Status
		Atk:[360, 395],
		AtkSpeed:8,
		Range:70,
		Rating:1200,

		// Defence Status
		Flee:4620,
		Absorb:70,
		Block:12,
		DamageStunPers:65,

		// Resistance
		Earth:60,
		Fire:30,
		Ice:30,// Water
		Lighting:20,// Wind
		Poison:50,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:8,
		Real_Sight:380,
		Talks:[],

		// Move Behavier
		Move_Speed:4,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:20,
		SkillDamage:[380, 420],
		SkillDistance:0,
		SkillRange:130,
		SkillRating:20,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:0,
		PotionCount:1,
	],

	Looks:[
		ClassCode:0,
		ArrowPosi:[-2, 30],
		ModelSize:1.2,
		UseEventModel:false,
		SizeLevel:-1,
		Model:'char/monster/Soldier_A/Soldier_A.ini',
		Sound:0x00005100,
	],

	// Drops
	AllSeeItem:false,
	Exp:1860000,
	Quantity:2,
	drops:[
		[probability:2000, code:"NULL"/* Drops nothing */],
		[probability:2500, code:"GG101", value:[1250, 2250]/* Gold */],
		[probability:5000, code:"FO114"/* 荣誉之力 */]
	],
	drops_more:[
	]
]
