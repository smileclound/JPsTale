monster = [
	ID:'z4boby',
	Name:'　金焰凤凰　',
	Clazz:1,// 1 BOSS
	Brood:0x92,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:110,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:30000,
		// Attack Status
		Atk:[500, 600],
		AtkSpeed:8,
		Range:400,
		Rating:2200,

		// Defence Status
		Flee:2200,
		Absorb:85,
		Block:12,
		DamageStunPers:5,

		// Resistance
		Earth:0,
		Fire:50,
		Ice:100,// Water
		Lighting:50,// Wind
		Poison:100,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:9,
		Real_Sight:420,
		Talks:[],

		// Move Behavier
		Move_Speed:1,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:15,
		SkillDamage:[650, 750],
		SkillDistance:1,
		SkillRange:200,
		SkillRating:18,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:0,
		PotionCount:0,
	],

	Looks:[
		ClassCode:0,
		ArrowPosi:[-10, 150],
		ModelSize:1.3,
		UseEventModel:false,
		SizeLevel:2,
		Model:'char/monster/BGuardian/BGuardian.ini',
		Sound:0x00001600,
	],

	// Drops
	AllSeeItem:false,
	Exp:100000,
	Quantity:5,
	drops:[
		[probability:0, code:"NULL"/* Drops nothing */],
		[probability:10000, code:"EC105"/* 公会卷轴 */]
	],
	drops_more:[
	]
]
