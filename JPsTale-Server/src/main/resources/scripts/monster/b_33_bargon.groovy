monster = [
	ID:'b_33_bargon',
	Name:'超级刀斧手',
	Clazz:0,// 1 BOSS
	Brood:0x92,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:40,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:1440,
		// Attack Status
		Atk:[48, 57],
		AtkSpeed:6,
		Range:75,
		Rating:700,

		// Defence Status
		Flee:250,
		Absorb:14,
		Block:8,
		DamageStunPers:100,

		// Resistance
		Earth:50,
		Fire:0,
		Ice:50,// Water
		Lighting:50,// Wind
		Poison:50,
	],

	// AI
	AI:[
		Nature:0x80,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:7,
		Real_Sight:432,
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
		ModelSize:1.3,
		UseEventModel:false,
		SizeLevel:3,
		Model:'char/monster/Monbagon/Monbagon-B.ini',
		Sound:0x00001090,
	],

	// Drops
	AllSeeItem:false,
	Exp:8600,
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
