monster = [
	ID:'b100_doomlord',
	Name:'超级黑暗爵士',
	Clazz:0,// 1 BOSS
	Brood:0x92,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:100,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:20000,
		// Attack Status
		Atk:[180, 200],
		AtkSpeed:8,
		Range:100,
		Rating:1200,

		// Defence Status
		Flee:900,
		Absorb:18,
		Block:0,
		DamageStunPers:100,

		// Resistance
		Earth:100,
		Fire:50,
		Ice:50,// Water
		Lighting:50,// Wind
		Poison:100,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:8,
		Real_Sight:400,
		Talks:[],

		// Move Behavier
		Move_Speed:1,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:0,
		SkillDamage:[200, 220],
		SkillDistance:100,
		SkillRange:100,
		SkillRating:15,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:0,
		PotionCount:0,
	],

	Looks:[
		ClassCode:0,
		ArrowPosi:[0, 50],
		ModelSize:1.6,
		UseEventModel:false,
		SizeLevel:-1,
		Model:'char/monster/MonSGuard/Mondoomguard.INI',
		Sound:0x00001460,
	],

	// Drops
	AllSeeItem:false,
	Exp:121000,
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
