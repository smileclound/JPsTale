monster = [
	ID:'ycoldeye',
	Name:'影精灵',
	Clazz:0,// 1 BOSS
	Brood:0x93,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:175,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:12000,
		// Attack Status
		Atk:[210, 247],
		AtkSpeed:8,
		Range:320,
		Rating:2250,

		// Defence Status
		Flee:2200,
		Absorb:20,
		Block:8,
		DamageStunPers:30,

		// Resistance
		Earth:0,
		Fire:60,
		Ice:100,// Water
		Lighting:80,// Wind
		Poison:80,
	],

	// AI
	AI:[
		Nature:0x80,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:8,
		Real_Sight:480,
		Talks:[],

		// Move Behavier
		Move_Speed:4,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:30,
		SkillDamage:[265, 285],
		SkillDistance:0,
		SkillRange:150,
		SkillRating:16,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:0,
		PotionCount:0,
	],

	Looks:[
		ClassCode:0,
		ArrowPosi:[4, 0],
		ModelSize:1.2,
		UseEventModel:false,
		SizeLevel:1,
		Model:'char/monster/coldeye/coldeye.ini',
		Sound:0x00001630,
	],

	// Drops
	AllSeeItem:false,
	Exp:480000,
	Quantity:1,
	drops:[
		[probability:8000, code:"NULL"/* Drops nothing */],
		[probability:2000, code:"EC105"/* 公会卷轴 */]
	],
	drops_more:[
	]
]
