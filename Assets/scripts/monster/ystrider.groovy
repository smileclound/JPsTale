monster = [
	ID:'ystrider',
	Name:'卡塔尔海妖',
	Clazz:0,// 1 BOSS
	Brood:0x92,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:175,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:13000,
		// Attack Status
		Atk:[180, 230],
		AtkSpeed:9,
		Range:80,
		Rating:1900,

		// Defence Status
		Flee:1720,
		Absorb:20,
		Block:8,
		DamageStunPers:50,

		// Resistance
		Earth:0,
		Fire:40,
		Ice:40,// Water
		Lighting:30,// Wind
		Poison:60,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:9,
		Real_Sight:480,
		Talks:[],

		// Move Behavier
		Move_Speed:5,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:0,
		SkillDamage:[255, 285],
		SkillDistance:0,
		SkillRange:0,
		SkillRating:17,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:0,
		PotionCount:0,
	],

	Looks:[
		ClassCode:0,
		ArrowPosi:[0, 25],
		ModelSize:1.8,
		UseEventModel:false,
		SizeLevel:2,
		Model:'char/monster/strider/strider.ini',
		Sound:0x00001750,
	],

	// Drops
	AllSeeItem:false,
	Exp:480000,
	Quantity:1,
	drops:[
		[probability:8000, code:"NULL"/* Drops nothing */],
		[probability:2000, code:"EC105"/* 公会卷轴 */]
	],
	drops_more:[
	]
]
