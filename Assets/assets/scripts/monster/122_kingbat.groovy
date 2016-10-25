monster = [
	ID:'122_kingbat',
	Name:'吸血蝙蝠',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:122,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:18250,
		// Attack Status
		Atk:[122, 133],
		AtkSpeed:8,
		Range:75,
		Rating:1800,

		// Defence Status
		Flee:1150,
		Absorb:31,
		Block:24,
		DamageStunPers:100,

		// Resistance
		Earth:36,
		Fire:-10,
		Ice:63,// Water
		Lighting:21,// Wind
		Poison:100,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:8,
		Real_Sight:360,
		Talks:[],

		// Move Behavier
		Move_Speed:3,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:15,
		SkillDamage:[141, 151],
		SkillDistance:80,
		SkillRange:55,
		SkillRating:35,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:0,
		PotionCount:0,
	],

	Looks:[
		ClassCode:87,
		ArrowPosi:[-10, 150],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:-1,
		Model:'char/monster/KingBat/KingBat.ini',
		Sound:0x000019D0,
	],

	// Drops
	AllSeeItem:false,
	Exp:590250,
	Quantity:1,
	drops:[
		[probability:3000, code:"NULL"/* Drops nothing */],
		[probability:7000, code:"GG101", value:[3210, 4530]/* Gold */]
	],
	drops_more:[
	]
]
