monster = [
	ID:'100_evilspirit',
	Name:'邪恶火灵',
	Clazz:0,// 1 BOSS
	Brood:0x92,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:100,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:9620,
		// Attack Status
		Atk:[85, 96],
		AtkSpeed:8,
		Range:230,
		Rating:1200,

		// Defence Status
		Flee:323,
		Absorb:13,
		Block:10,
		DamageStunPers:100,

		// Resistance
		Earth:83,
		Fire:100,
		Ice:0,// Water
		Lighting:30,// Wind
		Poison:53,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
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
		SizeLevel:1,
		Model:'char/monster/Evilspirit/Evilspirit.ini',
		Sound:0x00003030,
	],

	// Drops
	AllSeeItem:false,
	Exp:432000,
	Quantity:1,
	drops:[
		[probability:3000, code:"NULL"/* Drops nothing */],
		[probability:7000, code:"GG101", value:[3400, 5600]/* Gold */]
	],
	drops_more:[
	]
]
