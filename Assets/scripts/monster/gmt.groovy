monster = [
	ID:'gmt',
	Name:'寒冰鬼眼',
	Clazz:1,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:190,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:30000,
		// Attack Status
		Atk:[13400, 13430],
		AtkSpeed:9,
		Range:1000,
		Rating:5400,

		// Defence Status
		Flee:5000,
		Absorb:99,
		Block:0,
		DamageStunPers:0,

		// Resistance
		Earth:0,
		Fire:100,
		Ice:70,// Water
		Lighting:70,// Wind
		Poison:70,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:9,
		Real_Sight:1000,
		Talks:[],

		// Move Behavier
		Move_Speed:1,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:30,
		SkillDamage:[15200, 15220],
		SkillDistance:0,
		SkillRange:200,
		SkillRating:15,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:0,
		PotionCount:3,
	],

	Looks:[
		ClassCode:0,
		ArrowPosi:[-10, 150],
		ModelSize:9.9,
		UseEventModel:false,
		SizeLevel:2,
		Model:'char/monster/t_crystal_b/t_crystal_b.ini',
		Sound:0x00005022,
	],

	// Drops
	AllSeeItem:false,
	Exp:100000,
	Quantity:5,
	drops:[
		[probability:1000, code:"NULL"/* Drops nothing */],
		[probability:9000, code:"EC103"/* 回城卷 */]
	],
	drops_more:[
	]
]
