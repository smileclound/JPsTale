monster = [
	ID:'99_orbit',
	Name:'眼怪魔',
	Clazz:0,// 1 BOSS
	Brood:0x92,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:99,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:8400,
		// Attack Status
		Atk:[77, 86],
		AtkSpeed:8,
		Range:80,
		Rating:362,

		// Defence Status
		Flee:1200,
		Absorb:11,
		Block:12,
		DamageStunPers:100,

		// Resistance
		Earth:30,
		Fire:100,
		Ice:0,// Water
		Lighting:20,// Wind
		Poison:10,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:5,
		Real_Sight:380,
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
		ArrowPosi:[0, 40],
		ModelSize:1.4,
		UseEventModel:false,
		SizeLevel:1,
		Model:'char/monster/Moneye/eye.INI',
		Sound:0x00001070,
	],

	// Drops
	AllSeeItem:false,
	Exp:415000,
	Quantity:1,
	drops:[
		[probability:3000, code:"NULL"/* Drops nothing */],
		[probability:7000, code:"GG101", value:[3400, 5600]/* Gold */]
	],
	drops_more:[
	]
]
