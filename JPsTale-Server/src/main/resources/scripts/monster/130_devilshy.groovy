monster = [
	ID:'130_devilshy',
	Name:'双子恶魔',
	Clazz:0,// 1 BOSS
	Brood:0x92,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:130,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:30000,
		// Attack Status
		Atk:[690, 760],
		AtkSpeed:8,
		Range:120,
		Rating:1850,

		// Defence Status
		Flee:1231,
		Absorb:99,
		Block:41,
		DamageStunPers:31,

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
		IQ:9,
		Real_Sight:440,
		Talks:[],

		// Move Behavier
		Move_Speed:2,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:30,
		SkillDamage:[730, 780],
		SkillDistance:200,
		SkillRange:200,
		SkillRating:20,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:30,
		PotionCount:2,
	],

	Looks:[
		ClassCode:0,
		ArrowPosi:[0, 0],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:-1,
		Model:'char/monster/DevilShy/DevilShy.ini',
		Sound:0x00000000,
	],

	// Drops
	AllSeeItem:false,
	Exp:1700000,
	Quantity:3,
	drops:[
		[probability:1, code:"NULL"/* Drops nothing */],
		[probability:9990, code:"BI189"/* 合成回复石 */]
	],
	drops_more:[
	]
]
