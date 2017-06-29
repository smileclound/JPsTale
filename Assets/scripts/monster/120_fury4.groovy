monster = [
	ID:'120_fury4',
	Name:'狮面魔王(真)',
	Clazz:0,// 1 BOSS
	Brood:0x92,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:120,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:15000,
		// Attack Status
		Atk:[210, 230],
		AtkSpeed:8,
		Range:90,
		Rating:1800,

		// Defence Status
		Flee:550,
		Absorb:25,
		Block:20,
		DamageStunPers:60,

		// Resistance
		Earth:100,
		Fire:20,
		Ice:70,// Water
		Lighting:20,// Wind
		Poison:50,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:8,
		Real_Sight:440,
		Talks:[],

		// Move Behavier
		Move_Speed:4,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:20,
		SkillDamage:[250, 280],
		SkillDistance:0,
		SkillRange:110,
		SkillRating:20,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:0,
		PotionCount:0,
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
	Exp:200000,
	Quantity:0,
	drops:[

	],
	drops_more:[
	]
]
