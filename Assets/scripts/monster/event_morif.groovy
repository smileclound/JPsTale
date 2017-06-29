monster = [
	ID:'event_morif',
	Name:'精灵鼠小弟',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:10,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:600,
		// Attack Status
		Atk:[6, 10],
		AtkSpeed:8,
		Range:150,
		Rating:500,

		// Defence Status
		Flee:100,
		Absorb:45,
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
		ModelSize:1.2,
		UseEventModel:false,
		SizeLevel:0,
		Model:'char/npc/evenmorif/evenmorif.ini',
		Sound:0x00002010,
	],

	// Drops
	AllSeeItem:false,
	Exp:100000,
	Quantity:1,
	drops:[
		[probability:1663, code:"FO110"/* 钻晶之力 */],
		[probability:1663, code:"FO109"/* 龙鳞之力 */],
		[probability:1663, code:"FO108"/* 虎翼之力 */],
		[probability:1, code:"SE104"/* 祈祷石 */],
		[probability:1, code:"PR101"/* 紫色矿石 */],
		[probability:1, code:"PR102"/* 银色矿石 */],
		[probability:1, code:"PR103"/* 金色矿石 */],
		[probability:1, code:"PR104"/* 天蓝矿石 */],
		[probability:1, code:"PR105"/* 海蓝矿石 */],
		[probability:1, code:"PR106"/* 橙色矿石 */],
		[probability:1, code:"PR107"/* 红色矿石 */],
		[probability:5000, code:"GG101", value:[9650, 14050]/* Gold */]
	],
	drops_more:[
	]
]
