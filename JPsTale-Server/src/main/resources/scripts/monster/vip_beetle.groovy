monster = [
	ID:'vip_beetle',
	Name:'厚撇',
	Clazz:1,// 1 BOSS
	Brood:0x92,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:100,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:2000,
		// Attack Status
		Atk:[30, 40],
		AtkSpeed:7,
		Range:80,
		Rating:1000,

		// Defence Status
		Flee:1000,
		Absorb:30,
		Block:10,
		DamageStunPers:80,

		// Resistance
		Earth:0,
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
		SkillDamage:[145, 155],
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
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:3,
		Model:'char/monster/beetle/beetle.ini',
		Sound:0x000010D0,
	],

	// Drops
	AllSeeItem:false,
	Exp:2200,
	Quantity:1,
	drops:[
		[probability:1000, code:"GG101", value:[1, 2000]/* Gold */],
		[probability:4500, code:"PM102"/* 中级恢复魔法药水 */],
		[probability:4500, code:"PM104"/* 顶级恢复魔法药水 */]
	],
	drops_more:[
	]
]
