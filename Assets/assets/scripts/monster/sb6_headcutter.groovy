monster = [
	ID:'sb6_headcutter',
	Name:' 海啊 靛固福 ',
	Clazz:1,// 1 BOSS
	Brood:0x92,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:59,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:1640,
		// Attack Status
		Atk:[55, 65],
		AtkSpeed:7,
		Range:100,
		Rating:800,

		// Defence Status
		Flee:450,
		Absorb:12,
		Block:9,
		DamageStunPers:100,

		// Resistance
		Earth:0,
		Fire:0,
		Ice:40,// Water
		Lighting:10,// Wind
		Poison:10,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:7,
		Real_Sight:370,
		Talks:[],

		// Move Behavier
		Move_Speed:3,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:20,
		SkillDamage:[68, 80],
		SkillDistance:80,
		SkillRange:70,
		SkillRating:20,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:15,
		PotionCount:2,
	],

	Looks:[
		ClassCode:0,
		ArrowPosi:[-2, 30],
		ModelSize:1.3,
		UseEventModel:false,
		SizeLevel:2,
		Model:'char/monster/headcut/headcut.ini',
		Sound:0x00001100,
	],

	// Drops
	AllSeeItem:false,
	Exp:1800,
	Quantity:0,
	drops:[

	],
	drops_more:[
	]
]
