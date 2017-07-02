monster = [
	ID:'z5kelvezuy',
	Name:'小魔龙',
	Clazz:0,// 1 BOSS
	Brood:0x90,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:110,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:30000,
		// Attack Status
		Atk:[300, 400],
		AtkSpeed:8,
		Range:100,
		Rating:1200,

		// Defence Status
		Flee:1200,
		Absorb:97,
		Block:12,
		DamageStunPers:5,

		// Resistance
		Earth:0,
		Fire:40,
		Ice:100,// Water
		Lighting:40,// Wind
		Poison:100,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:9,
		Real_Sight:400,
		Talks:[],

		// Move Behavier
		Move_Speed:1,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:15,
		SkillDamage:[1300, 1500],
		SkillDistance:2,
		SkillRange:280,
		SkillRating:20,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:100,
		PotionCount:10,
	],

	Looks:[
		ClassCode:0,
		ArrowPosi:[0, 0],
		ModelSize:0.8,
		UseEventModel:false,
		SizeLevel:2,
		Model:'char/monster/Kelvezu/Kelvezu.ini',
		Sound:0x00001830,
	],

	// Drops
	AllSeeItem:false,
	Exp:100000,
	Quantity:5,
	drops:[
		[probability:0, code:"NULL"/* Drops nothing */],
		[probability:10000, code:"EC103"/* 回城卷 */]
	],
	drops_more:[
	]
]
