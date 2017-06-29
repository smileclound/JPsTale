monster = [
	ID:'128_hound',
	Name:'地狱犬',
	Clazz:0,// 1 BOSS
	Brood:0x92,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:128,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:23000,
		// Attack Status
		Atk:[690, 640],
		AtkSpeed:7,
		Range:80,
		Rating:2400,

		// Defence Status
		Flee:2100,
		Absorb:81,
		Block:40,
		DamageStunPers:100,

		// Resistance
		Earth:70,
		Fire:-10,
		Ice:80,// Water
		Lighting:30,// Wind
		Poison:90,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:8,
		Real_Sight:410,
		Talks:[],

		// Move Behavier
		Move_Speed:2,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:30,
		SkillDamage:[710, 790],
		SkillDistance:0,
		SkillRange:110,
		SkillRating:30,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:0,
		PotionCount:0,
	],

	Looks:[
		ClassCode:0,
		ArrowPosi:[0, 0],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:1,
		Model:'char/monster/Hound/Hound.ini',
		Sound:0x00000000,
	],

	// Drops
	AllSeeItem:false,
	Exp:1860000,
	Quantity:2,
	drops:[
		[probability:3500, code:"NULL"/* Drops nothing */],
		[probability:2400, code:"GG101", value:[1250, 2250]/* Gold */],
		[probability:512, code:"PL104"/* 顶级恢复生命药水 */],
		[probability:512, code:"PM104"/* 顶级恢复魔法药水 */],
		[probability:512, code:"PS104"/* 顶级恢复耐力药水 */],
		[probability:512, code:"PM104"/* 顶级恢复魔法药水 */]
	],
	drops_more:[
	]
]
