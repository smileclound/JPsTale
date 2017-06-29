monster = [
	ID:'event_molly',
	Name:'莫利狼奶奶',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:10,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:700,
		// Attack Status
		Atk:[8, 12],
		AtkSpeed:8,
		Range:150,
		Rating:500,

		// Defence Status
		Flee:100,
		Absorb:50,
		Block:0,
		DamageStunPers:100,

		// Resistance
		Earth:50,
		Fire:50,
		Ice:50,// Water
		Lighting:50,// Wind
		Poison:0,
	],

	// AI
	AI:[
		Nature:0x80,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:10,
		Real_Sight:300,
		Talks:[],

		// Move Behavier
		Move_Speed:2,
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
		ArrowPosi:[0, 0],
		ModelSize:1.5,
		UseEventModel:false,
		SizeLevel:0,
		Model:'char/npc/eventmolly/eventmolly.ini',
		Sound:0x00002012,
	],

	// Drops
	AllSeeItem:false,
	Exp:100000,
	Quantity:3,
	drops:[
		[probability:0, code:"NULL"/* Drops nothing */],
		[probability:4600, code:"SE101"/* 造化石 */],
		[probability:3300, code:"FO104"/* 天仪之力 */],
		[probability:1000, code:"FO105"/* 冰晶之力 */],
		[probability:600, code:"FO106"/* 玄风之力 */],
		[probability:300, code:"FO107"/* 水晶之力 */],
		[probability:200, code:"FO108"/* 虎翼之力 */]
	],
	drops_more:[
	]
]
