monster = [
	ID:'b_15_mutantrabie',
	Name:'超级变异兔子',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:15,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:3000,
		// Attack Status
		Atk:[55, 65],
		AtkSpeed:7,
		Range:70,
		Rating:1000,

		// Defence Status
		Flee:600,
		Absorb:5,
		Block:8,
		DamageStunPers:100,

		// Resistance
		Earth:55,
		Fire:55,
		Ice:55,// Water
		Lighting:55,// Wind
		Poison:100,
	],

	// AI
	AI:[
		Nature:0x80,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:7,
		Real_Sight:380,
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
		ArrowPosi:[-1, 50],
		ModelSize:2.0,
		UseEventModel:false,
		SizeLevel:3,
		Model:'char/monster/Monrebion/Monrebion.ini',
		Sound:0x00001260,
	],

	// Drops
	AllSeeItem:false,
	Exp:3000,
	Quantity:1,
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
