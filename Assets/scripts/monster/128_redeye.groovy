monster = [
	ID:'128_redeye',
	Name:'火眼魔',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:138,
	ActiveHour:0,
	RespawnGroup:[2, 2],

	Attributes:[
		Life:26000,
		// Attack Status
		Atk:[290, 310],
		AtkSpeed:6,
		Range:220,
		Rating:1600,

		// Defence Status
		Flee:3200,
		Absorb:88,
		Block:30,
		DamageStunPers:100,

		// Resistance
		Earth:70,
		Fire:100,
		Ice:-100,// Water
		Lighting:30,// Wind
		Poison:90,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:8,
		Real_Sight:400,
		Talks:[],

		// Move Behavier
		Move_Speed:2,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:30,
		SkillDamage:[310, 330],
		SkillDistance:0,
		SkillRange:110,
		SkillRating:30,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:0,
		PotionCount:0,
	],

	Looks:[
		ClassCode:0,
		ArrowPosi:[0, 0],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:1,
		Model:'char/monster/coldeye/Redeye.ini',
		Sound:0x00000000,
	],

	// Drops
	AllSeeItem:false,
	Exp:2800000,
	Quantity:1,
	drops:[
		[probability:4000, code:"NULL"/* Drops nothing */],
		[probability:6000, code:"GG101", value:[6000, 7000]/* Gold */]
	],
	drops_more:[
	]
]
