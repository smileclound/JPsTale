monster = [
	ID:'80_deadhopy',
	Name:'无息独角兽',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:80,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:5000,
		// Attack Status
		Atk:[135, 145],
		AtkSpeed:8,
		Range:80,
		Rating:1200,

		// Defence Status
		Flee:620,
		Absorb:20,
		Block:10,
		DamageStunPers:70,

		// Resistance
		Earth:36,
		Fire:12,
		Ice:80,// Water
		Lighting:47,// Wind
		Poison:90,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:8,
		Real_Sight:270,
		Talks:[],

		// Move Behavier
		Move_Speed:1,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:30,
		SkillDamage:[140, 150],
		SkillDistance:0,
		SkillRange:0,
		SkillRating:30,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:0,
		PotionCount:0,
	],

	Looks:[
		ClassCode:0,
		ArrowPosi:[0, 0],
		ModelSize:1.2,
		UseEventModel:false,
		SizeLevel:0,
		Model:'char/monster/Deadhopy/Deadhopy.ini',
		Sound:0x00001970,
	],

	// Drops
	AllSeeItem:false,
	Exp:160000,
	Quantity:0,
	drops:[

	],
	drops_more:[
	]
]
