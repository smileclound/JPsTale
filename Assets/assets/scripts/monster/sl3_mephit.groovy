monster = [
	ID:'sl3_mephit',
	Name:'水精灵',
	Clazz:0,// 1 BOSS
	Brood:0x92,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:173,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:30000,
		// Attack Status
		Atk:[782, 1287],
		AtkSpeed:7,
		Range:170,
		Rating:450,

		// Defence Status
		Flee:4500,
		Absorb:30,
		Block:4500,
		DamageStunPers:100,

		// Resistance
		Earth:0,
		Fire:0,
		Ice:100,// Water
		Lighting:50,// Wind
		Poison:50,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:7,
		Real_Sight:380,
		Talks:[],

		// Move Behavier
		Move_Speed:4,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:100,
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
		ArrowPosi:[0, 0],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:0,
		Model:'char/monster/Monimp/Monimp-b.INI',
		Sound:0x00001020,
	],

	// Drops
	AllSeeItem:false,
	Exp:420000,
	Quantity:0,
	drops:[

	],
	drops_more:[
	]
]
