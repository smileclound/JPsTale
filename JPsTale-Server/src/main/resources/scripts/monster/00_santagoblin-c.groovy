monster = [
	ID:'00_santagoblin-c',
	Name:'圣诞精灵',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:60,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:4000,
		// Attack Status
		Atk:[12, 15],
		AtkSpeed:8,
		Range:70,
		Rating:600,

		// Defence Status
		Flee:100,
		Absorb:60,
		Block:0,
		DamageStunPers:100,

		// Resistance
		Earth:30,
		Fire:30,
		Ice:30,// Water
		Lighting:30,// Wind
		Poison:0,
	],

	// AI
	AI:[
		Nature:0x80,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:10,
		Real_Sight:300,
		Talks:[],

		// Move Behavier
		Move_Speed:2,
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
		ArrowPosi:[0, 0],
		ModelSize:0.7,
		UseEventModel:false,
		SizeLevel:1,
		Model:'char/monster/Monhobsanta/Monhobsanta.INI',
		Sound:0x00001010,
	],

	// Drops
	AllSeeItem:false,
	Exp:500,
	Quantity:4,
	drops:[
		[probability:0, code:"NULL"/* Drops nothing */],
		[probability:4000, code:"GG101", value:[2000, 3000]/* Gold */],
		[probability:3000, code:"PM104"/* 顶级恢复魔法药水 */],
		[probability:2000, code:"FO105"/* 冰晶之力 */],
		[probability:700, code:"FO106"/* 玄风之力 */],
		[probability:300, code:"FO107"/* 水晶之力 */]
	],
	drops_more:[
	]
]
