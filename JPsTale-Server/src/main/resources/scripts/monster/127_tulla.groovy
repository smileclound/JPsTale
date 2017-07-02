monster = [
	ID:'127_tulla',
	Name:'恶魔伯爵',
	Clazz:0,// 1 BOSS
	Brood:0x92,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:135,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:30000,
		// Attack Status
		Atk:[720, 760],
		AtkSpeed:8,
		Range:100,
		Rating:3200,

		// Defence Status
		Flee:2100,
		Absorb:98,
		Block:62,
		DamageStunPers:100,

		// Resistance
		Earth:100,
		Fire:100,
		Ice:100,// Water
		Lighting:100,// Wind
		Poison:90,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:8,
		Real_Sight:430,
		Talks:[],

		// Move Behavier
		Move_Speed:3,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:30,
		SkillDamage:[790, 830],
		SkillDistance:0,
		SkillRange:110,
		SkillRating:30,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:30,
		PotionCount:3,
	],

	Looks:[
		ClassCode:0,
		ArrowPosi:[0, 0],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:-1,
		Model:'char/monster/Tulla/Tulla.ini',
		Sound:0x00000000,
	],

	// Drops
	AllSeeItem:false,
	Exp:3625000,
	Quantity:3,
	drops:[
		[probability:1, code:"NULL"/* Drops nothing */],
		[probability:4997, code:"BI190"/* 巴别塔之角 */],
		[probability:4997, code:"BI189"/* 合成回复石 */],
		[probability:5, code:"BI185"/* 锻造水晶 */]
	],
	drops_more:[
	]
]
