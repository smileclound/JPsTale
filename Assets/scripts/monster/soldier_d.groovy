monster = [
	ID:'soldier_d',
	Name:'护卫兵',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:88,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:16000,
		// Attack Status
		Atk:[280, 315],
		AtkSpeed:9,
		Range:200,
		Rating:1450,

		// Defence Status
		Flee:1720,
		Absorb:50,
		Block:12,
		DamageStunPers:60,

		// Resistance
		Earth:50,
		Fire:50,
		Ice:50,// Water
		Lighting:50,// Wind
		Poison:50,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:8,
		Real_Sight:400,
		Talks:[],

		// Move Behavier
		Move_Speed:4,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:20,
		SkillDamage:[320, 340],
		SkillDistance:0,
		SkillRange:130,
		SkillRating:20,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:0,
		PotionCount:4,
	],

	Looks:[
		ClassCode:0,
		ArrowPosi:[0, 25],
		ModelSize:1.2,
		UseEventModel:false,
		SizeLevel:-1,
		Model:'char/monster/Soldier_D/Soldier_D.ini',
		Sound:0x00000000,
	],

	// Drops
	AllSeeItem:false,
	Exp:1000,
	Quantity:0,
	drops:[

	],
	drops_more:[
	]
]
