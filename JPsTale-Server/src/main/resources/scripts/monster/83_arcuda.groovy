monster = [
	ID:'83_arcuda',
	Name:'笨笨熊',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:83,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:21000,
		// Attack Status
		Atk:[235, 275],
		AtkSpeed:9,
		Range:80,
		Rating:1600,

		// Defence Status
		Flee:1500,
		Absorb:60,
		Block:30,
		DamageStunPers:100,

		// Resistance
		Earth:20,
		Fire:80,
		Ice:20,// Water
		Lighting:70,// Wind
		Poison:90,
	],

	// AI
	AI:[
		Nature:0x80,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:9,
		Real_Sight:380,
		Talks:[],

		// Move Behavier
		Move_Speed:1,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:30,
		SkillDamage:[245, 290],
		SkillDistance:0,
		SkillRange:0,
		SkillRating:30,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:0,
		PotionCount:2,
	],

	Looks:[
		ClassCode:0,
		ArrowPosi:[0, 0],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:1,
		Model:'char/monster/arcuda/arcuda.ini',
		Sound:0x00001150,
	],

	// Drops
	AllSeeItem:false,
	Exp:295000,
	Quantity:0,
	drops:[

	],
	drops_more:[
	]
]
