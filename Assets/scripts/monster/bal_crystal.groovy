monster = [
	ID:'bal_crystal',
	Name:'瓦尔哈拉之塔',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:120,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:30000,
		// Attack Status
		Atk:[0, 0],
		AtkSpeed:8,
		Range:300,
		Rating:1400,

		// Defence Status
		Flee:4000,
		Absorb:98,
		Block:20,
		DamageStunPers:0,

		// Resistance
		Earth:80,
		Fire:80,
		Ice:80,// Water
		Lighting:80,// Wind
		Poison:80,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:9,
		Real_Sight:900,
		Talks:[],

		// Move Behavier
		Move_Speed:1,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:20,
		SkillDamage:[0, 0],
		SkillDistance:0,
		SkillRange:0,
		SkillRating:0,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:100,
		PotionCount:30,
	],

	Looks:[
		ClassCode:0,
		ArrowPosi:[-10, 150],
		ModelSize:1.2,
		UseEventModel:false,
		SizeLevel:-1,
		Model:'char/monster/bal_crystal/bal_crystal.ini',
		Sound:0x00005030,
	],

	// Drops
	AllSeeItem:false,
	Exp:200000,
	Quantity:0,
	drops:[

	],
	drops_more:[
	]
]
