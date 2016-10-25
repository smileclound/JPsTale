monster = [
	ID:'75_beetle',
	Name:'独眼甲虫',
	Clazz:0,// 1 BOSS
	Brood:0x92,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:75,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:1000,
		// Attack Status
		Atk:[30, 40],
		AtkSpeed:7,
		Range:110,
		Rating:1800,

		// Defence Status
		Flee:1100,
		Absorb:30,
		Block:10,
		DamageStunPers:80,

		// Resistance
		Earth:60,
		Fire:50,
		Ice:50,// Water
		Lighting:20,// Wind
		Poison:50,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:9,
		Real_Sight:420,
		Talks:[],

		// Move Behavier
		Move_Speed:4,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:20,
		SkillDamage:[42, 50],
		SkillDistance:0,
		SkillRange:100,
		SkillRating:30,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:0,
		PotionCount:0,
	],

	Looks:[
		ClassCode:0,
		ArrowPosi:[-30, 90],
		ModelSize:0.7,
		UseEventModel:false,
		SizeLevel:3,
		Model:'char/monster/beetle/beetle.ini',
		Sound:0x000010D0,
	],

	// Drops
	AllSeeItem:false,
	Exp:20000,
	Quantity:1,
	drops:[
		[probability:0, code:"NULL"/* Drops nothing */],
		[probability:2700, code:"GG101", value:[1200, 1800]/* Gold */],
		[probability:5000, code:"PM104"/* 顶级恢复魔法药水 */],
		[probability:600, code:"OS106"/* 玄风石 */],
		[probability:600, code:"FO126"/* 玄风之力(魔法) */],
		[probability:233, code:"OS107"/* 水晶石 */],
		[probability:233, code:"FO106"/* 玄风之力 */],
		[probability:233, code:"FO127"/* 水晶之力(魔法) */],
		[probability:200, code:"FO107"/* 水晶之力 */],
		[probability:200, code:"FO127"/* 水晶之力(魔法) */]
	],
	drops_more:[
	]
]
