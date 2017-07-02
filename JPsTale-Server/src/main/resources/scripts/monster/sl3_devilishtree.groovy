monster = [
	ID:'sl3_devilishtree',
	Name:'树魔人',
	Clazz:0,// 1 BOSS
	Brood:0x92,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:173,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:30000,
		// Attack Status
		Atk:[100, 3800],
		AtkSpeed:7,
		Range:65,
		Rating:500,

		// Defence Status
		Flee:4500,
		Absorb:30,
		Block:4500,
		DamageStunPers:100,

		// Resistance
		Earth:0,
		Fire:-10,
		Ice:25,// Water
		Lighting:-10,// Wind
		Poison:100,
	],

	// AI
	AI:[
		Nature:0x80,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:5,
		Real_Sight:340,
		Talks:[],

		// Move Behavier
		Move_Speed:1,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:17,
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
		ArrowPosi:[2, 80],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:3,
		Model:'char/monster/Monplant/MonPlant-d.INI',
		Sound:0x00001040,
	],

	// Drops
	AllSeeItem:false,
	Exp:530000,
	Quantity:0,
	drops:[

	],
	drops_more:[
	]
]
