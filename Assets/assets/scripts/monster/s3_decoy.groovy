monster = [
	ID:'s3_decoy',
	Name:' 叼内捞 ',
	Clazz:0,// 1 BOSS
	Brood:0x92,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:31,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:560,
		// Attack Status
		Atk:[25, 28],
		AtkSpeed:8,
		Range:60,
		Rating:540,

		// Defence Status
		Flee:120,
		Absorb:7,
		Block:0,
		DamageStunPers:100,

		// Resistance
		Earth:0,
		Fire:-15,
		Ice:15,// Water
		Lighting:-15,// Wind
		Poison:100,
	],

	// AI
	AI:[
		Nature:0x80,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:7,
		Real_Sight:260,
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
		PotionPercent:0,
		PotionCount:0,
	],

	Looks:[
		ClassCode:0,
		ArrowPosi:[-5, 10],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:1,
		Model:'char/monster/decoy/decoy.ini',
		Sound:0x00001160,
	],

	// Drops
	AllSeeItem:false,
	Exp:520,
	Quantity:0,
	drops:[

	],
	drops_more:[
	]
]
