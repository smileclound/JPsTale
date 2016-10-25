monster = [
	ID:'60_towergolem',
	Name:'巨塔石人',
	Clazz:0,// 1 BOSS
	Brood:0x92,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:60,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:1050,
		// Attack Status
		Atk:[30, 40],
		AtkSpeed:8,
		Range:75,
		Rating:700,

		// Defence Status
		Flee:450,
		Absorb:30,
		Block:0,
		DamageStunPers:100,

		// Resistance
		Earth:100,
		Fire:70,
		Ice:10,// Water
		Lighting:0,// Wind
		Poison:100,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:9,
		Real_Sight:340,
		Talks:[],

		// Move Behavier
		Move_Speed:1,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:15,
		SkillDamage:[42, 52],
		SkillDistance:80,
		SkillRange:55,
		SkillRating:18,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:0,
		PotionCount:0,
	],

	Looks:[
		ClassCode:0,
		ArrowPosi:[-10, 150],
		ModelSize:0.7,
		UseEventModel:false,
		SizeLevel:3,
		Model:'char/monster/TowerGolem/TowerGolem.ini',
		Sound:0x000015A0,
	],

	// Drops
	AllSeeItem:false,
	Exp:15500,
	Quantity:1,
	drops:[
		[probability:0, code:"NULL"/* Drops nothing */],
		[probability:2700, code:"GG101", value:[200, 280]/* Gold */],
		[probability:5000, code:"PM104"/* 顶级恢复魔法药水 */],
		[probability:200, code:"OS106"/* 玄风石 */],
		[probability:200, code:"GP105"/* 魔剑士水晶 */],
		[probability:200, code:"GP106"/* 火灵王水晶 */],
		[probability:200, code:"GP107"/* 独角兽王水晶 */],
		[probability:200, code:"GP108"/* 绿巨人水晶 */],
		[probability:200, code:"GP111"/* 大头蜘蛛水晶 */],
		[probability:350, code:"OS107"/* 水晶石 */],
		[probability:350, code:"FO106"/* 玄风之力 */],
		[probability:400, code:"FO107"/* 水晶之力 */]
	],
	drops_more:[
	]
]
