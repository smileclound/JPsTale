monster = [
	ID:'sb4_bargon',
	Name:' 官快沛 ',
	Clazz:1,// 1 BOSS
	Brood:0x92,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:40,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:960,
		// Attack Status
		Atk:[36, 42],
		AtkSpeed:6,
		Range:70,
		Rating:1200,

		// Defence Status
		Flee:250,
		Absorb:8,
		Block:6,
		DamageStunPers:100,

		// Resistance
		Earth:0,
		Fire:-10,
		Ice:10,// Water
		Lighting:10,// Wind
		Poison:50,
	],

	// AI
	AI:[
		Nature:0x80,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:7,
		Real_Sight:320,
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
		ArrowPosi:[0, 10],
		ModelSize:1.3,
		UseEventModel:false,
		SizeLevel:3,
		Model:'char/monster/Monbagon/Monbagon-B.ini',
		Sound:0x00001090,
	],

	// Drops
	AllSeeItem:false,
	Exp:700,
	Quantity:0,
	drops:[

	],
	drops_more:[
	]
]
