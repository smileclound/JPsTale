monster = [
	ID:'80_magicmaster',
	Name:'魔法导师',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:80,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:10000,
		// Attack Status
		Atk:[80, 100],
		AtkSpeed:7,
		Range:300,
		Rating:1000,

		// Defence Status
		Flee:700,
		Absorb:20,
		Block:15,
		DamageStunPers:10,

		// Resistance
		Earth:50,
		Fire:50,
		Ice:50,// Water
		Lighting:50,// Wind
		Poison:50,
	],

	// AI
	AI:[
		Nature:0x80,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:8,
		Real_Sight:600,
		Talks:[],

		// Move Behavier
		Move_Speed:3,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:0,
		SkillDamage:[110, 140],
		SkillDistance:80,
		SkillRange:0,
		SkillRating:30,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:100,
		PotionCount:3,
	],

	Looks:[
		ClassCode:0,
		ArrowPosi:[0, 0],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:-1,
		Model:'char/npc/Mage/Mage.ini',
		Sound:0x00002030,
	],

	// Drops
	AllSeeItem:false,
	Exp:50000,
	Quantity:0,
	drops:[

	],
	drops_more:[
	]
]
