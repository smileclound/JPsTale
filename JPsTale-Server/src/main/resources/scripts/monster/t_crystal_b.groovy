monster = [
	ID:'t_crystal_b',
	Name:'冰水晶塔',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:120,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:30000,
		// Attack Status
		Atk:[150, 200],
		AtkSpeed:8,
		Range:400,
		Rating:1400,

		// Defence Status
		Flee:2800,
		Absorb:96,
		Block:0,
		DamageStunPers:100,

		// Resistance
		Earth:70,
		Fire:70,
		Ice:100,// Water
		Lighting:70,// Wind
		Poison:70,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:9,
		Real_Sight:600,
		Talks:[],

		// Move Behavier
		Move_Speed:1,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:20,
		SkillDamage:[200, 250],
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
		ModelSize:1.2,
		UseEventModel:false,
		SizeLevel:-1,
		Model:'char/monster/t_crystal_b/t_crystal_b.ini',
		Sound:0x00005022,
	],

	// Drops
	AllSeeItem:false,
	Exp:1000,
	Quantity:0,
	drops:[

	],
	drops_more:[
	]
]
