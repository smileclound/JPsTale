monster = [
	ID:'s51_guardian',
	Name:'啊叼救 技牢飘',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:80,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:25000,
		// Attack Status
		Atk:[700, 900],
		AtkSpeed:7,
		Range:80,
		Rating:1200,

		// Defence Status
		Flee:520,
		Absorb:90,
		Block:0,
		DamageStunPers:70,

		// Resistance
		Earth:0,
		Fire:30,
		Ice:30,// Water
		Lighting:20,// Wind
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
		SkillDamage:[1100, 1400],
		SkillDistance:0,
		SkillRange:100,
		SkillRating:17,
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
		SizeLevel:2,
		Model:'char/monster/guardian/guardian.ini',
		Sound:0x00001470,
	],

	// Drops
	AllSeeItem:false,
	Exp:18000,
	Quantity:0,
	drops:[

	],
	drops_more:[
	]
]
