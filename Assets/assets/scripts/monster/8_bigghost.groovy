monster = [
	ID:'8_bigghost',
	Name:'魂魄',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:8,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:600,
		// Attack Status
		Atk:[7, 9],
		AtkSpeed:8,
		Range:45,
		Rating:180,

		// Defence Status
		Flee:16,
		Absorb:45,
		Block:0,
		DamageStunPers:100,

		// Resistance
		Earth:50,
		Fire:0,
		Ice:30,// Water
		Lighting:0,// Wind
		Poison:100,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:7,
		Real_Sight:330,
		Talks:[],

		// Move Behavier
		Move_Speed:3,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:0,
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
		ArrowPosi:[-7, 60],
		ModelSize:1.8,
		UseEventModel:false,
		SizeLevel:1,
		Model:'char/monster/ghost/ghost.INI',
		Sound:0x00001390,
	],

	// Drops
	AllSeeItem:false,
	Exp:250,
	Quantity:1,
	drops:[
		[probability:0, code:"NULL"/* Drops nothing */],
		[probability:6000, code:"PM102"/* 中级恢复魔法药水 */],
		[probability:3000, code:"PM103"/* 高级恢复魔法药水 */],
		[probability:1000, code:"PM104"/* 顶级恢复魔法药水 */]
	],
	drops_more:[
	]
]
