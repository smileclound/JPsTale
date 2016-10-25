monster = [
	ID:'b_104_babel',
	Name:'巴别塔',
	Clazz:0,// 1 BOSS
	Brood:0x92,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:104,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:30000,
		// Attack Status
		Atk:[300, 330],
		AtkSpeed:8,
		Range:300,
		Rating:1600,

		// Defence Status
		Flee:2200,
		Absorb:75,
		Block:50,
		DamageStunPers:10,

		// Resistance
		Earth:100,
		Fire:100,
		Ice:100,// Water
		Lighting:100,// Wind
		Poison:100,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:8,
		Real_Sight:420,
		Talks:[],

		// Move Behavier
		Move_Speed:1,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:15,
		SkillDamage:[440, 450],
		SkillDistance:120,
		SkillRange:200,
		SkillRating:18,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:0,
		PotionCount:0,
	],

	Looks:[
		ClassCode:0,
		ArrowPosi:[-10, 150],
		ModelSize:1.2,
		UseEventModel:false,
		SizeLevel:-1,
		Model:'char/monster/BGuardian/BGuardian.ini',
		Sound:0x00001600,
	],

	// Drops
	AllSeeItem:false,
	Exp:1000000,
	Quantity:3,
	drops:[
		[probability:4000, code:"NULL"/* Drops nothing */],
		[probability:1333, code:"BI189"/* 合成回复石 */],
		[probability:1333, code:"BI190"/* 巴别塔之角 */],
		[probability:1333, code:"SE101"/* 造化石 */]
	],
	drops_more:[
	]
]
