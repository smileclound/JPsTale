monster = [
	ID:'119_mimic',
	Name:'怪物箱子',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:119,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:18500,
		// Attack Status
		Atk:[123, 132],
		AtkSpeed:9,
		Range:80,
		Rating:2000,

		// Defence Status
		Flee:1300,
		Absorb:26,
		Block:22,
		DamageStunPers:70,

		// Resistance
		Earth:36,
		Fire:96,
		Ice:63,// Water
		Lighting:46,// Wind
		Poison:36,
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
		SpAttackPercetage:20,
		SkillDamage:[143, 165],
		SkillDistance:0,
		SkillRange:130,
		SkillRating:35,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:0,
		PotionCount:0,
	],

	Looks:[
		ClassCode:0,
		ArrowPosi:[0, 25],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:-1,
		Model:'char/monster/Mimic/mimic.ini',
		Sound:0x000019C0,
	],

	// Drops
	AllSeeItem:false,
	Exp:595000,
	Quantity:1,
	drops:[
		[probability:3000, code:"NULL"/* Drops nothing */],
		[probability:7000, code:"GG101", value:[3000, 6000]/* Gold */]
	],
	drops_more:[
	]
]
