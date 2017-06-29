monster = [
	ID:'b_89_stygianlord',
	Name:'超级幽灵',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:89,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:6600,
		// Attack Status
		Atk:[135, 175],
		AtkSpeed:8,
		Range:200,
		Rating:1300,

		// Defence Status
		Flee:680,
		Absorb:18,
		Block:5,
		DamageStunPers:100,

		// Resistance
		Earth:80,
		Fire:90,
		Ice:20,// Water
		Lighting:30,// Wind
		Poison:50,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:8,
		Real_Sight:420,
		Talks:[],

		// Move Behavier
		Move_Speed:4,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:18,
		SkillDamage:[145, 190],
		SkillDistance:0,
		SkillRange:150,
		SkillRating:18,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:0,
		PotionCount:0,
	],

	Looks:[
		ClassCode:0,
		ArrowPosi:[-8, 30],
		ModelSize:1.5,
		UseEventModel:false,
		SizeLevel:1,
		Model:'char/monster/Monstygianlord-1/Monstygianlord-1.ini',
		Sound:0x00001350,
	],

	// Drops
	AllSeeItem:false,
	Exp:75000,
	Quantity:1,
	QuestItem:0x07010900,
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
