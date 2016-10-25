monster = [
	ID:'t_crystal_n',
	Name:'水晶塔',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:120,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:30000,
		// Attack Status
		Atk:[0, 0],
		AtkSpeed:8,
		Range:400,
		Rating:0,

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
		SkillDamage:[0, 0],
		SkillDistance:0,
		SkillRange:0,
		SkillRating:0,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:0,
		PotionCount:2,
	],

	Looks:[
		ClassCode:0,
		ArrowPosi:[-10, 150],
		ModelSize:1.2,
		UseEventModel:false,
		SizeLevel:-1,
		Model:'char/monster/t_crystal_n/t_crystal_n.ini',
		Sound:0x00005023,
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
