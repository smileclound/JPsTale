monster = [
	ID:'sb7_figon',
	Name:' 秋教 ',
	Clazz:1,// 1 BOSS
	Brood:0x92,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:69,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:2200,
		// Attack Status
		Atk:[72, 81],
		AtkSpeed:8,
		Range:230,
		Rating:2100,

		// Defence Status
		Flee:350,
		Absorb:9,
		Block:0,
		DamageStunPers:100,

		// Resistance
		Earth:0,
		Fire:100,
		Ice:0,// Water
		Lighting:30,// Wind
		Poison:100,
	],

	// AI
	AI:[
		Nature:0x80,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:8,
		Real_Sight:440,
		Talks:[],

		// Move Behavier
		Move_Speed:3,
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
		ArrowPosi:[5, 40],
		ModelSize:1.3,
		UseEventModel:false,
		SizeLevel:3,
		Model:'char/monster/figon/Monfigon-boss.ini',
		Sound:0x00001180,
	],

	// Drops
	AllSeeItem:false,
	Exp:2400,
	Quantity:0,
	drops:[

	],
	drops_more:[
	]
]
