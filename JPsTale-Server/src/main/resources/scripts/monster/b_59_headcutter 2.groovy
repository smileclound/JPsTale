monster = [
	ID:'b_59_headcutter 2',
	Name:'超级魔斗士',
	Clazz:0,// 1 BOSS
	Brood:0x92,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:59,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:2610,
		// Attack Status
		Atk:[84, 105],
		AtkSpeed:7,
		Range:75,
		Rating:700,

		// Defence Status
		Flee:450,
		Absorb:18,
		Block:10,
		DamageStunPers:100,

		// Resistance
		Earth:50,
		Fire:0,
		Ice:20,// Water
		Lighting:10,// Wind
		Poison:10,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:7,
		Real_Sight:480,
		Talks:[],

		// Move Behavier
		Move_Speed:3,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:20,
		SkillDamage:[94, 115],
		SkillDistance:80,
		SkillRange:50,
		SkillRating:18,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:15,
		PotionCount:1,
	],

	Looks:[
		ClassCode:0,
		ArrowPosi:[0, 0],
		ModelSize:1.3,
		UseEventModel:false,
		SizeLevel:-1,
		Model:'char/monster/headcut/headcut.ini',
		Sound:0x00001100,
	],

	// Drops
	AllSeeItem:false,
	Exp:24000,
	Quantity:1,
	QuestItem:0x07010A00,
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
