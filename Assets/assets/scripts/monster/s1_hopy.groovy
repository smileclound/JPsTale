monster = [
	ID:'s1_hopy',
	Name:' 龋乔 ',
	Clazz:1,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:100,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:18000,
		// Attack Status
		Atk:[1200, 1430],
		AtkSpeed:9,
		Range:700,
		Rating:200,

		// Defence Status
		Flee:200,
		Absorb:9,
		Block:0,
		DamageStunPers:0,

		// Resistance
		Earth:0,
		Fire:100,
		Ice:70,// Water
		Lighting:70,// Wind
		Poison:70,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:9,
		Real_Sight:700,
		Talks:[],

		// Move Behavier
		Move_Speed:1,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:30,
		SkillDamage:[1600, 1820],
		SkillDistance:0,
		SkillRange:200,
		SkillRating:15,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:0,
		PotionCount:3,
	],

	Looks:[
		ClassCode:0,
		ArrowPosi:[-10, 150],
		ModelSize:9.0,
		UseEventModel:false,
		SizeLevel:2,
		Model:'char/monster/t_crystal_g/t_crystal_g.ini',
		Sound:0x00005021,
	],

	// Drops
	AllSeeItem:false,
	Exp:100000,
	Quantity:0,
	drops:[

	],
	drops_more:[
	]
]
