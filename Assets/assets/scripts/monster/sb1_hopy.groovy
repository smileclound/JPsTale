monster = [
	ID:'sb1_hopy',
	Name:' 炉乔 ',
	Clazz:1,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:4,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:150,
		// Attack Status
		Atk:[6, 9],
		AtkSpeed:8,
		Range:40,
		Rating:190,

		// Defence Status
		Flee:50,
		Absorb:5,
		Block:0,
		DamageStunPers:100,

		// Resistance
		Earth:0,
		Fire:10,
		Ice:5,// Water
		Lighting:0,// Wind
		Poison:10,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:5,
		Real_Sight:200,
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
		PotionCount:0,
	],

	Looks:[
		ClassCode:0,
		ArrowPosi:[8, 25],
		ModelSize:1.3,
		UseEventModel:false,
		SizeLevel:0,
		Model:'char/monster/Hopy/hopy.INI',
		Sound:0x00001080,
	],

	// Drops
	AllSeeItem:false,
	Exp:20,
	Quantity:0,
	drops:[

	],
	drops_more:[
	]
]
