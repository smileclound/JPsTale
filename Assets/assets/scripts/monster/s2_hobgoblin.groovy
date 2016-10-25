monster = [
	ID:'s2_hobgoblin',
	Name:' 醛绊喉赴 ',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:14,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:260,
		// Attack Status
		Atk:[10, 16],
		AtkSpeed:8,
		Range:45,
		Rating:450,

		// Defence Status
		Flee:90,
		Absorb:4,
		Block:15,
		DamageStunPers:100,

		// Resistance
		Earth:0,
		Fire:20,
		Ice:10,// Water
		Lighting:0,// Wind
		Poison:0,
	],

	// AI
	AI:[
		Nature:0x80,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:6,
		Real_Sight:230,
		Talks:[],

		// Move Behavier
		Move_Speed:2,
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
		PotionCount:0,
	],

	Looks:[
		ClassCode:0,
		ArrowPosi:[0, 30],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:1,
		Model:'char/monster/Monhobgoblin/Monhobgoblin-a.INI',
		Sound:0x00001010,
	],

	// Drops
	AllSeeItem:false,
	Exp:110,
	Quantity:0,
	drops:[

	],
	drops_more:[
	]
]
