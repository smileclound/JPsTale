monster = [
	ID:'140_draxos',
	Name:'埃克斯',
	Clazz:0,// 1 BOSS
	Brood:0x92,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:140,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:30000,
		// Attack Status
		Atk:[1860, 1890],
		AtkSpeed:9,
		Range:200,
		Rating:1600,

		// Defence Status
		Flee:4800,
		Absorb:99,
		Block:88,
		DamageStunPers:30,

		// Resistance
		Earth:300,
		Fire:300,
		Ice:300,// Water
		Lighting:300,// Wind
		Poison:300,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:8,
		Real_Sight:380,
		Talks:[],

		// Move Behavier
		Move_Speed:5,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:35,
		SkillDamage:[1880, 1960],
		SkillDistance:0,
		SkillRange:0,
		SkillRating:60,
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
		Model:'char/monster/Draxos/Draxos.ini',
		Sound:0x00000000,
	],

	// Drops
	AllSeeItem:false,
	Exp:10000000,
	Quantity:2,
	drops:[
		[probability:500, code:"OA130"/* 凯尔维苏项链 */]
	],
	drops_more:[
	]
]
