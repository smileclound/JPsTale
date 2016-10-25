monster = [
	ID:'s60_stygianlord',
	Name:'胶萍瘤攫 肺靛',
	Clazz:0,// 1 BOSS
	Brood:0x90,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:85,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:3500,
		// Attack Status
		Atk:[90, 110],
		AtkSpeed:8,
		Range:220,
		Rating:1250,

		// Defence Status
		Flee:560,
		Absorb:20,
		Block:5,
		DamageStunPers:100,

		// Resistance
		Earth:0,
		Fire:90,
		Ice:20,// Water
		Lighting:25,// Wind
		Poison:30,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:9,
		Real_Sight:400,
		Talks:[],

		// Move Behavier
		Move_Speed:4,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:30,
		SkillDamage:[115, 130],
		SkillDistance:0,
		SkillRange:140,
		SkillRating:30,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:0,
		PotionCount:0,
	],

	Looks:[
		ClassCode:0,
		ArrowPosi:[-8, 50],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:1,
		Model:'char/monster/Monstygianlord/Monstygianlord.ini',
		Sound:0x00001350,
	],

	// Drops
	AllSeeItem:false,
	Exp:6600,
	Quantity:0,
	drops:[

	],
	drops_more:[
	]
]
