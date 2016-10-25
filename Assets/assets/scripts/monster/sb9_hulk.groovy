monster = [
	ID:'sb9_hulk',
	Name:' 鸥捞乾 ',
	Clazz:1,// 1 BOSS
	Brood:0x91,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:76,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:5500,
		// Attack Status
		Atk:[101, 132],
		AtkSpeed:7,
		Range:70,
		Rating:1200,

		// Defence Status
		Flee:520,
		Absorb:20,
		Block:8,
		DamageStunPers:70,

		// Resistance
		Earth:0,
		Fire:30,
		Ice:30,// Water
		Lighting:20,// Wind
		Poison:50,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:8,
		Real_Sight:400,
		Talks:[],

		// Move Behavier
		Move_Speed:4,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:20,
		SkillDamage:[0, 0],
		SkillDistance:0,
		SkillRange:0,
		SkillRating:0,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:50,
		PotionCount:1,
	],

	Looks:[
		ClassCode:0,
		ArrowPosi:[-2, 30],
		ModelSize:1.3,
		UseEventModel:false,
		SizeLevel:2,
		Model:'char/monster/Monhulk-1/Monhulk-1.ini',
		Sound:0x000012B0,
	],

	// Drops
	AllSeeItem:false,
	Exp:3600,
	Quantity:0,
	drops:[

	],
	drops_more:[
	]
]
