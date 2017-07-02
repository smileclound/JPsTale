monster = [
	ID:'127_hest',
	Name:'奥兹',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:127,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:21500,
		// Attack Status
		Atk:[145, 163],
		AtkSpeed:7,
		Range:110,
		Rating:2000,

		// Defence Status
		Flee:1400,
		Absorb:36,
		Block:25,
		DamageStunPers:70,

		// Resistance
		Earth:46,
		Fire:16,
		Ice:76,// Water
		Lighting:13,// Wind
		Poison:63,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:8,
		Real_Sight:380,
		Talks:[],

		// Move Behavier
		Move_Speed:2,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:16,
		SkillDamage:[174, 213],
		SkillDistance:80,
		SkillRange:120,
		SkillRating:50,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:0,
		PotionCount:0,
	],

	Looks:[
		ClassCode:0,
		ArrowPosi:[-6, 50],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:3,
		Model:'char/monster/Hest/Hest.ini',
		Sound:0x000019F0,
	],

	// Drops
	AllSeeItem:false,
	Exp:595000,
	Quantity:1,
	drops:[
		[probability:3000, code:"NULL"/* Drops nothing */],
		[probability:4988, code:"GG101", value:[3236, 6368]/* Gold */],
		[probability:666, code:"PL104"/* 顶级恢复生命药水 */],
		[probability:666, code:"PS104"/* 顶级恢复耐力药水 */],
		[probability:666, code:"PM104"/* 顶级恢复魔法药水 */],
		[probability:2, code:"SE104"/* 祈祷石 */]
	],
	drops_more:[
	]
]
