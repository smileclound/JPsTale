monster = [
	ID:'yspider',
	Name:'小幻影蛛王',
	Clazz:0,// 1 BOSS
	Brood:0x92,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:185,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:30000,
		// Attack Status
		Atk:[250, 290],
		AtkSpeed:9,
		Range:80,
		Rating:2400,

		// Defence Status
		Flee:2290,
		Absorb:20,
		Block:15,
		DamageStunPers:40,

		// Resistance
		Earth:0,
		Fire:40,
		Ice:40,// Water
		Lighting:40,// Wind
		Poison:60,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:9,
		Real_Sight:480,
		Talks:[],

		// Move Behavier
		Move_Speed:4,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:0,
		SkillDamage:[295, 315],
		SkillDistance:0,
		SkillRange:130,
		SkillRating:17,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:0,
		PotionCount:0,
	],

	Looks:[
		ClassCode:0,
		ArrowPosi:[0, 25],
		ModelSize:1.4,
		UseEventModel:false,
		SizeLevel:2,
		Model:'char/monster/spider/spider.ini',
		Sound:0x00001730,
	],

	// Drops
	AllSeeItem:false,
	Exp:590000,
	Quantity:1,
	drops:[
		[probability:8000, code:"NULL"/* Drops nothing */],
		[probability:1980, code:"EC103"/* 回城卷 */],
		[probability:2, code:"WA117"/* 远古战斧 */],
		[probability:2, code:"WC116"/* 泰坦爪 */],
		[probability:2, code:"WM117"/* 神圣之光 */],
		[probability:2, code:"WP119"/* 棲羽亚陀 */],
		[probability:2, code:"WS118"/* 风切 */],
		[probability:2, code:"WS220"/* 龙牙 */],
		[probability:2, code:"WT118"/* 噬月 */],
		[probability:2, code:"DA102"/* 战斗服 */],
		[probability:2, code:"DA202"/* 袍 */]
	],
	drops_more:[
	]
]
