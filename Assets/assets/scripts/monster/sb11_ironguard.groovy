monster = [
	ID:'sb11_ironguard',
	Name:' 捞固萍福 啊靛 ',
	Clazz:1,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:81,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:4200,
		// Attack Status
		Atk:[140, 165],
		AtkSpeed:8,
		Range:80,
		Rating:1200,

		// Defence Status
		Flee:740,
		Absorb:21,
		Block:0,
		DamageStunPers:70,

		// Resistance
		Earth:0,
		Fire:100,
		Ice:100,// Water
		Lighting:0,// Wind
		Poison:20,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:8,
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
		ArrowPosi:[-9, 45],
		ModelSize:1.3,
		UseEventModel:false,
		SizeLevel:2,
		Model:'char/monster/MonIgad/MonIgad.ini',
		Sound:0x00001300,
	],

	// Drops
	AllSeeItem:false,
	Exp:7200,
	Quantity:0,
	drops:[

	],
	drops_more:[
	]
]
