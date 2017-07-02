monster = [
	ID:'event_evilspirit',
	Name:'邪恶火灵',
	Clazz:0,// 1 BOSS
	Brood:0x92,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:100,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:5000,
		// Attack Status
		Atk:[52, 57],
		AtkSpeed:8,
		Range:230,
		Rating:1200,

		// Defence Status
		Flee:350,
		Absorb:30,
		Block:10,
		DamageStunPers:100,

		// Resistance
		Earth:100,
		Fire:100,
		Ice:0,// Water
		Lighting:30,// Wind
		Poison:100,
	],

	// AI
	AI:[
		Nature:0x80,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:8,
		Real_Sight:410,
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
		ArrowPosi:[5, 40],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:3,
		Model:'char/monster/Evilspirit/Evilspirit.ini',
		Sound:0x00003030,
	],

	// Drops
	AllSeeItem:false,
	Exp:12400,
	Quantity:0,
	drops:[

	],
	drops_more:[
	]
]
