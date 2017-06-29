monster = [
	ID:'4_hopy kid',
	Name:'小独角兽',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:4,
	ActiveHour:0,
	RespawnGroup:[2, 2],

	Attributes:[
		Life:15,
		// Attack Status
		Atk:[2, 3],
		AtkSpeed:8,
		Range:30,
		Rating:90,

		// Defence Status
		Flee:10,
		Absorb:0,
		Block:0,
		DamageStunPers:100,

		// Resistance
		Earth:10,
		Fire:10,
		Ice:5,// Water
		Lighting:0,// Wind
		Poison:10,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:4,
		Real_Sight:300,
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
		ModelSize:0.6,
		UseEventModel:false,
		SizeLevel:0,
		Model:'char/monster/Hopy/hopy.INI',
		Sound:0x00001080,
	],

	// Drops
	AllSeeItem:false,
	Exp:70,
	Quantity:1,
	drops:[
		[probability:4390, code:"NULL"/* Drops nothing */],
		[probability:4000, code:"GG101", value:[600, 620]/* Gold */],
		[probability:375, code:"PL101"/* 低级复生命药水 */],
		[probability:375, code:"PL101"/* 低级复生命药水 */],
		[probability:375, code:"PS101"/* 低级恢复耐力药水 */],
		[probability:375, code:"PS101"/* 低级恢复耐力药水 */],
		[probability:14, code:"WA101"/* 石斧 */],
		[probability:14, code:"WH101"/* 狼牙棒 */],
		[probability:14, code:"WP101"/* 精制木杖 */],
		[probability:14, code:"WS101"/* 短弓 */],
		[probability:14, code:"WT101"/* 标枪 */],
		[probability:14, code:"OR101"/* 情殇 */],
		[probability:14, code:"OA101"/* 爱之石链 */],
		[probability:2, code:"OS103"/* 海精石 */],
		[probability:2, code:"OR102"/* 铜戒指 */],
		[probability:2, code:"OR103"/* 金戒指 */],
		[probability:2, code:"OA102"/* 红玉项链 */],
		[probability:2, code:"OA103"/* 多情环 */]
	],
	drops_more:[
	]
]
