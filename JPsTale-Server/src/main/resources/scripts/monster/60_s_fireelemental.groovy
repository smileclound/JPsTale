monster = [
	ID:'60_s_fireelemental',
	Name:'火灵神',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:60,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:2000,
		// Attack Status
		Atk:[40, 55],
		AtkSpeed:7,
		Range:230,
		Rating:400,

		// Defence Status
		Flee:220,
		Absorb:0,
		Block:0,
		DamageStunPers:100,

		// Resistance
		Earth:100,
		Fire:100,
		Ice:100,// Water
		Lighting:100,// Wind
		Poison:100,
	],

	// AI
	AI:[
		Nature:0x80,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:7,
		Real_Sight:380,
		Talks:[],

		// Move Behavier
		Move_Speed:3,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:0,
		SkillDamage:[56, 65],
		SkillDistance:70,
		SkillRange:100,
		SkillRating:17,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:0,
		PotionCount:0,
	],

	Looks:[
		ClassCode:0,
		ArrowPosi:[-8, 200],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:-1,
		Model:'char/monster/Firegolem/Firegolem.INI',
		Sound:0x00003030,
	],

	// Drops
	AllSeeItem:false,
	Exp:13500,
	Quantity:1,
	drops:[
		[probability:10000, code:"NULL"/* Drops nothing */]
	],
	drops_more:[
	]
]
