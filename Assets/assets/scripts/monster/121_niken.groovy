monster = [
	ID:'121_niken',
	Name:'尼肯',
	Clazz:0,// 1 BOSS
	Brood:0x92,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:121,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:20000,
		// Attack Status
		Atk:[123, 156],
		AtkSpeed:9,
		Range:80,
		Rating:1500,

		// Defence Status
		Flee:1120,
		Absorb:32,
		Block:25,
		DamageStunPers:50,

		// Resistance
		Earth:46,
		Fire:76,
		Ice:63,// Water
		Lighting:63,// Wind
		Poison:60,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:8,
		Real_Sight:380,
		Talks:[],

		// Move Behavier
		Move_Speed:5,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:0,
		SkillDamage:[134, 146],
		SkillDistance:0,
		SkillRange:0,
		SkillRating:17,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:30,
		PotionCount:1,
	],

	Looks:[
		ClassCode:0,
		ArrowPosi:[0, 25],
		ModelSize:1.2,
		UseEventModel:false,
		SizeLevel:-1,
		Model:'char/monster/Niken/niken.ini',
		Sound:0x000019B0,
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
