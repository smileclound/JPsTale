monster = [
	ID:'b_100_ratoolord',
	Name:'超级鲁图怪王',
	Clazz:0,// 1 BOSS
	Brood:0x92,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:100,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:21000,
		// Attack Status
		Atk:[200, 240],
		AtkSpeed:7,
		Range:110,
		Rating:1500,

		// Defence Status
		Flee:1100,
		Absorb:20,
		Block:10,
		DamageStunPers:80,

		// Resistance
		Earth:90,
		Fire:50,
		Ice:50,// Water
		Lighting:20,// Wind
		Poison:50,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:8,
		Real_Sight:440,
		Talks:[],

		// Move Behavier
		Move_Speed:4,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:16,
		SkillDamage:[220, 260],
		SkillDistance:110,
		SkillRange:90,
		SkillRating:18,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:0,
		PotionCount:0,
	],

	Looks:[
		ClassCode:0,
		ArrowPosi:[-8, 40],
		ModelSize:1.3,
		UseEventModel:false,
		SizeLevel:3,
		Model:'char/monster/Monratoo-1/Monratoo-1.ini',
		Sound:0x00001340,
	],

	// Drops
	AllSeeItem:false,
	Exp:121000,
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
