monster = [
	ID:'soldier_c',
	Name:'禁卫兵',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:100,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:24000,
		// Attack Status
		Atk:[710, 735],
		AtkSpeed:8,
		Range:80,
		Rating:6300,

		// Defence Status
		Flee:5920,
		Absorb:75,
		Block:75,
		DamageStunPers:50,

		// Resistance
		Earth:50,
		Fire:50,
		Ice:50,// Water
		Lighting:50,// Wind
		Poison:50,
	],

	// AI
	AI:[
		Nature:0x80,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:8,
		Real_Sight:600,
		Talks:[],

		// Move Behavier
		Move_Speed:4,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:0,
		SkillDamage:[730, 750],
		SkillDistance:110,
		SkillRange:70,
		SkillRating:25,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:0,
		PotionCount:3,
	],

	Looks:[
		ClassCode:0,
		ArrowPosi:[0, 0],
		ModelSize:1.2,
		UseEventModel:false,
		SizeLevel:-1,
		Model:'char/monster/Soldier_C/Soldier_C.ini',
		Sound:0x00005120,
	],

	// Drops
	AllSeeItem:false,
	Exp:2160000,
	Quantity:2,
	drops:[
		[probability:2000, code:"NULL"/* Drops nothing */],
		[probability:2500, code:"GG101", value:[1250, 2250]/* Gold */],
		[probability:5000, code:"FO114"/* 荣誉之力 */]
	],
	drops_more:[
	]
]
