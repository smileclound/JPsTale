monster = [
	ID:'event_tobie',
	Name:'咯空配厚',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:10,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:300,
		// Attack Status
		Atk:[7, 15],
		AtkSpeed:9,
		Range:35,
		Rating:130,

		// Defence Status
		Flee:10,
		Absorb:20,
		Block:0,
		DamageStunPers:100,

		// Resistance
		Earth:0,
		Fire:0,
		Ice:0,// Water
		Lighting:0,// Wind
		Poison:50,
	],

	// AI
	AI:[
		Nature:0x80,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:7,
		Real_Sight:250,
		Talks:[],

		// Move Behavier
		Move_Speed:2,
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
		ArrowPosi:[-5, 44],
		ModelSize:2.2,
		UseEventModel:false,
		SizeLevel:0,
		Model:'char/monster/Monrebi/Monrebi-1.ini',
		Sound:0x00001200,
	],

	// Drops
	AllSeeItem:false,
	Exp:6000,
	Quantity:2,
	drops:[
		[probability:4000, code:"GG101", value:[12000, 19000]/* Gold */],
		[probability:2000, code:"SP103"/* 大月饼 */],
		[probability:2000, code:"FO103"/* 海精之力 */],
		[probability:1200, code:"FO104"/* 天仪之力 */],
		[probability:500, code:"FO105"/* 冰晶之力 */],
		[probability:300, code:"FO106"/* 玄风之力 */]
	],
	drops_more:[
	]
]
