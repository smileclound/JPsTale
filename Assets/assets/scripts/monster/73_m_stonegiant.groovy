monster = [
	ID:'73_m_stonegiant',
	Name:'小无双石怪',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:73,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:1350,
		// Attack Status
		Atk:[30, 40],
		AtkSpeed:7,
		Range:80,
		Rating:710,

		// Defence Status
		Flee:600,
		Absorb:30,
		Block:8,
		DamageStunPers:80,

		// Resistance
		Earth:70,
		Fire:80,
		Ice:35,// Water
		Lighting:0,// Wind
		Poison:100,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:8,
		Real_Sight:370,
		Talks:[],

		// Move Behavier
		Move_Speed:4,
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
		ArrowPosi:[1, 38],
		ModelSize:0.3,
		UseEventModel:false,
		SizeLevel:3,
		Model:'char/monster/Golem/Golem.ini',
		Sound:0x00001190,
	],

	// Drops
	AllSeeItem:false,
	Exp:18500,
	Quantity:1,
	drops:[
		[probability:0, code:"NULL"/* Drops nothing */],
		[probability:2700, code:"GG101", value:[1000, 1500]/* Gold */],
		[probability:5000, code:"PM104"/* 顶级恢复魔法药水 */],
		[probability:600, code:"OS106"/* 玄风石 */],
		[probability:600, code:"FO126"/* 玄风之力(魔法) */],
		[probability:233, code:"OS107"/* 水晶石 */],
		[probability:233, code:"FO106"/* 玄风之力 */],
		[probability:233, code:"FO127"/* 水晶之力(魔法) */],
		[probability:400, code:"FO107"/* 水晶之力 */]
	],
	drops_more:[
	]
]
