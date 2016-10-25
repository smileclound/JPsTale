monster = [
	ID:'sb8_kinghopy',
	Name:' 龋矾乔柯 ',
	Clazz:1,// 1 BOSS
	Brood:0x91,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:86,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:4200,
		// Attack Status
		Atk:[99, 132],
		AtkSpeed:8,
		Range:70,
		Rating:1800,

		// Defence Status
		Flee:500,
		Absorb:10,
		Block:5,
		DamageStunPers:100,

		// Resistance
		Earth:0,
		Fire:20,
		Ice:50,// Water
		Lighting:0,// Wind
		Poison:20,
	],

	// AI
	AI:[
		Nature:0x80,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:9,
		Real_Sight:360,
		Talks:[],

		// Move Behavier
		Move_Speed:4,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:30,
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
		ArrowPosi:[-32, 110],
		ModelSize:1.3,
		UseEventModel:false,
		SizeLevel:3,
		Model:'char/monster/hopyking/hopyking.ini',
		Sound:0x00001130,
	],

	// Drops
	AllSeeItem:false,
	Exp:4000,
	Quantity:0,
	drops:[

	],
	drops_more:[
	]
]
