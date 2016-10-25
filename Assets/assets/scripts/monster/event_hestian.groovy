monster = [
	ID:'event_hestian',
	Name:'烈火麒麟',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:100,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:15000,
		// Attack Status
		Atk:[100, 120],
		AtkSpeed:8,
		Range:80,
		Rating:670,

		// Defence Status
		Flee:920,
		Absorb:40,
		Block:20,
		DamageStunPers:10,

		// Resistance
		Earth:80,
		Fire:100,
		Ice:50,// Water
		Lighting:20,// Wind
		Poison:50,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:9,
		Real_Sight:440,
		Talks:[],

		// Move Behavier
		Move_Speed:5,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:16,
		SkillDamage:[140, 170],
		SkillDistance:80,
		SkillRange:70,
		SkillRating:20,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:15,
		PotionCount:3,
	],

	Looks:[
		ClassCode:0,
		ArrowPosi:[-6, 50],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:3,
		Model:'char/monster/Hestian/Hestian.ini',
		Sound:0x00000000,
	],

	// Drops
	AllSeeItem:false,
	Exp:42000,
	Quantity:0,
	drops:[

	],
	drops_more:[
	]
]
