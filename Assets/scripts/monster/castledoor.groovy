monster = [
	ID:'castledoor',
	Name:'城门',
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
		Range:0,
		Rating:0,

		// Defence Status
		Flee:0,
		Absorb:98,
		Block:0,
		DamageStunPers:0,

		// Resistance
		Earth:100,
		Fire:40,
		Ice:80,// Water
		Lighting:100,// Wind
		Poison:80,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:9,
		Real_Sight:900,
		Talks:[],

		// Move Behavier
		Move_Speed:1,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:0,
		SkillDamage:[0, 0],
		SkillDistance:0,
		SkillRange:0,
		SkillRating:0,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:0,
		PotionCount:10,
	],

	Looks:[
		ClassCode:0,
		ArrowPosi:[-10, 150],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:-1,
		Model:'char/monster/castledoor/castledoor.ini',
		Sound:0x00005010,
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
