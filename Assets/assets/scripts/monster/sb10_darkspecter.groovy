monster = [
	ID:'sb10_darkspecter',
	Name:' 促农 埔乓 ',
	Clazz:1,// 1 BOSS
	Brood:0x92,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:87,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:6000,
		// Attack Status
		Atk:[125, 160],
		AtkSpeed:7,
		Range:170,
		Rating:1400,

		// Defence Status
		Flee:720,
		Absorb:20,
		Block:10,
		DamageStunPers:100,

		// Resistance
		Earth:0,
		Fire:40,
		Ice:40,// Water
		Lighting:20,// Wind
		Poison:40,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:8,
		Real_Sight:410,
		Talks:[],

		// Move Behavier
		Move_Speed:4,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:20,
		SkillDamage:[170, 190],
		SkillDistance:0,
		SkillRange:110,
		SkillRating:20,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:0,
		PotionCount:0,
	],

	Looks:[
		ClassCode:0,
		ArrowPosi:[0, 30],
		ModelSize:1.3,
		UseEventModel:false,
		SizeLevel:2,
		Model:'char/monster/Monshadow-1/Monshadow-1.ini',
		Sound:0x000012E0,
	],

	// Drops
	AllSeeItem:false,
	Exp:8000,
	Quantity:0,
	drops:[

	],
	drops_more:[
	]
]
