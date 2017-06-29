monster = [
	ID:'b_25_minigsilver',
	Name:'超级蓝豆糕',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:25,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:810,
		// Attack Status
		Atk:[33, 46],
		AtkSpeed:8,
		Range:25,
		Rating:450,

		// Defence Status
		Flee:140,
		Absorb:11,
		Block:6,
		DamageStunPers:100,

		// Resistance
		Earth:20,
		Fire:25,
		Ice:25,// Water
		Lighting:10,// Wind
		Poison:50,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:8,
		Real_Sight:550,
		Talks:[],

		// Move Behavier
		Move_Speed:4,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:0,
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
		ModelSize:1.4,
		UseEventModel:false,
		SizeLevel:0,
		Model:'char/monster/MonminiG/MonminiG-B1.ini',
		Sound:0x00001030,
	],

	// Drops
	AllSeeItem:false,
	Exp:6400,
	Quantity:1,
	QuestItem:0x07010B00,
	drops:[
		[probability:5000, code:"NULL"/* Drops nothing */],
		[probability:2000, code:"GG101", value:[2000, 3000]/* Gold */],
		[probability:375, code:"GP101"/* 独角兽水晶 */],
		[probability:375, code:"GP102"/* 魔兽兵水晶 */],
		[probability:375, code:"GP103"/* 浮灵水晶 */],
		[probability:375, code:"GP104"/* 刀斧手水晶 */],
		[probability:375, code:"GP105"/* 魔剑士水晶 */],
		[probability:375, code:"GP106"/* 火灵王水晶 */],
		[probability:375, code:"GP107"/* 独角兽王水晶 */],
		[probability:375, code:"GP108"/* 绿巨人水晶 */]
	],
	drops_more:[
	]
]
