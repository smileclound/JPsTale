monster = [
	ID:'s5_web',
	Name:' 昆 ',
	Clazz:0,// 1 BOSS
	Brood:0x91,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:34,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:800,
		// Attack Status
		Atk:[23, 33],
		AtkSpeed:5,
		Range:180,
		Rating:700,

		// Defence Status
		Flee:150,
		Absorb:12,
		Block:0,
		DamageStunPers:100,

		// Resistance
		Earth:0,
		Fire:-15,
		Ice:10,// Water
		Lighting:-15,// Wind
		Poison:100,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:6,
		Real_Sight:340,
		Talks:[],

		// Move Behavier
		Move_Speed:3,
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
		ArrowPosi:[-5, 150],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:-1,
		Model:'char/monster/web/web.ini',
		Sound:0x00001120,
	],

	// Drops
	AllSeeItem:false,
	Exp:700,
	Quantity:0,
	drops:[

	],
	drops_more:[
	]
]
