monster = [
	ID:'124_goblinshaman',
	Name:'魔法魔兽兵',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:124,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:18650,
		// Attack Status
		Atk:[122, 136],
		AtkSpeed:8,
		Range:160,
		Rating:2000,

		// Defence Status
		Flee:1200,
		Absorb:38,
		Block:24,
		DamageStunPers:100,

		// Resistance
		Earth:36,
		Fire:23,
		Ice:38,// Water
		Lighting:96,// Wind
		Poison:68,
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
		SkillDamage:[133, 145],
		SkillDistance:80,
		SkillRange:100,
		SkillRating:35,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:0,
		PotionCount:0,
	],

	Looks:[
		ClassCode:85,
		ArrowPosi:[-10, 150],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:-1,
		Model:'char/monster/GoblinShaman/GoblinShaman.ini',
		Sound:0x000019E0,
	],

	// Drops
	AllSeeItem:false,
	Exp:595000,
	Quantity:1,
	drops:[
		[probability:3000, code:"NULL"/* Drops nothing */],
		[probability:7000, code:"GG101", value:[7200, 8600]/* Gold */]
	],
	drops_more:[
	]
]
