monster = [
	ID:'60_death_night',
	Name:'暗夜骑士',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:60,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:1500,
		// Attack Status
		Atk:[30, 40],
		AtkSpeed:7,
		Range:75,
		Rating:900,

		// Defence Status
		Flee:0,
		Absorb:5,
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
		SkillDamage:[45, 55],
		SkillDistance:80,
		SkillRange:0,
		SkillRating:20,
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
		Model:'char/monster/death_knight/death_knight.ini',
		Sound:0x00001680,
	],

	// Drops
	AllSeeItem:false,
	Exp:25500,
	Quantity:1,
	drops:[
		[probability:10000, code:"NULL"/* Drops nothing */]
	],
	drops_more:[
	]
]
