monster = [
	ID:'qsr2',
	Name:'　小魔怪　',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:75,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:10000,
		// Attack Status
		Atk:[130, 160],
		AtkSpeed:8,
		Range:50,
		Rating:600,

		// Defence Status
		Flee:110,
		Absorb:30,
		Block:0,
		DamageStunPers:100,

		// Resistance
		Earth:0,
		Fire:20,
		Ice:10,// Water
		Lighting:0,// Wind
		Poison:0,
	],

	// AI
	AI:[
		Nature:0x80,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:10,
		Real_Sight:300,
		Talks:[],

		// Move Behavier
		Move_Speed:2,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:20,
		SkillDamage:[0, 0],
		SkillDistance:0,
		SkillRange:0,
		SkillRating:0,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:0,
		PotionCount:0,
	],

	Looks:[
		ClassCode:0,
		ArrowPosi:[0, 0],
		ModelSize:0.8,
		UseEventModel:false,
		SizeLevel:1,
		Model:'char/monster/Monhobsanta/Monhobsanta.INI',
		Sound:0x00001010,
	],

	// Drops
	AllSeeItem:false,
	Exp:120000,
	Quantity:1,
	drops:[
		[probability:0, code:"NULL"/* Drops nothing */],
		[probability:10000, code:"EC105"/* 公会卷轴 */]
	],
	drops_more:[
	]
]
