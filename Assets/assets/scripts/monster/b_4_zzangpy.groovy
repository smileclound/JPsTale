monster = [
	ID:'b_4_zzangpy',
	Name:'超级独角兽',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:4,
	ActiveHour:0,
	RespawnGroup:[2, 2],

	Attributes:[
		Life:200,
		// Attack Status
		Atk:[10, 20],
		AtkSpeed:8,
		Range:30,
		Rating:130,

		// Defence Status
		Flee:10,
		Absorb:0,
		Block:0,
		DamageStunPers:100,

		// Resistance
		Earth:10,
		Fire:10,
		Ice:70,// Water
		Lighting:0,// Wind
		Poison:10,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:4,
		Real_Sight:340,
		Talks:[],

		// Move Behavier
		Move_Speed:1,
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
		ArrowPosi:[8, 25],
		ModelSize:2.0,
		UseEventModel:false,
		SizeLevel:0,
		Model:'char/monster/Hopy/hopy.INI',
		Sound:0x00001080,
	],

	// Drops
	AllSeeItem:false,
	Exp:1300,
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
