monster = [
	ID:'6_arma',
	Name:'穿山甲',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:6,
	ActiveHour:0,
	RespawnGroup:[2, 2],

	Attributes:[
		Life:22,
		// Attack Status
		Atk:[2, 4],
		AtkSpeed:8,
		Range:30,
		Rating:140,

		// Defence Status
		Flee:8,
		Absorb:0,
		Block:0,
		DamageStunPers:100,

		// Resistance
		Earth:10,
		Fire:15,
		Ice:-5,// Water
		Lighting:-5,// Wind
		Poison:5,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:4,
		Real_Sight:270,
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
		ArrowPosi:[0, 40],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:0,
		Model:'char/monster/Mouse/mouse.INI',
		Sound:0x000010C0,
	],

	// Drops
	AllSeeItem:false,
	Exp:200,
	Quantity:10,
	drops:[
		[probability:3500, code:"NULL"/* Drops nothing */],
		[probability:4000, code:"GG101", value:[608, 614]/* Gold */],
		[probability:333, code:"PL101"/* 低级复生命药水 */],
		[probability:333, code:"PS101"/* 低级恢复耐力药水 */],
		[probability:333, code:"PL101"/* 低级复生命药水 */],
		[probability:111, code:"DA102"/* 战斗服 */],
		[probability:111, code:"WA101"/* 石斧 */],
		[probability:111, code:"WC101"/* 爪 */],
		[probability:111, code:"WH101"/* 狼牙棒 */],
		[probability:111, code:"WP101"/* 精制木杖 */],
		[probability:111, code:"WS101"/* 短弓 */],
		[probability:111, code:"WS201"/* 神木剑 */],
		[probability:111, code:"WT101"/* 标枪 */],
		[probability:111, code:"OR101"/* 情殇 */],
		[probability:40, code:"DA103"/* 皮甲 */],
		[probability:40, code:"WA102"/* 铁斧 */],
		[probability:40, code:"WC102"/* 虎爪 */],
		[probability:40, code:"WH102"/* 星刺锤 */],
		[probability:40, code:"WP102"/* 点钢蛇矛 */],
		[probability:40, code:"WS102"/* 羊角弓 */],
		[probability:40, code:"WS202"/* 蛇行匕首 */],
		[probability:40, code:"WT102"/* 战标 */],
		[probability:40, code:"OR102"/* 铜戒指 */],
		[probability:40, code:"OA102"/* 红玉项链 */],
		[probability:14, code:"DA104"/* 铠甲 */],
		[probability:14, code:"DB102"/* 精灵靴 */],
		[probability:14, code:"DG102"/* 拳套 */],
		[probability:14, code:"OA202"/* 精铁臂环 */],
		[probability:14, code:"DS102"/* 钉盾 */],
		[probability:14, code:"OM102"/* 水晶球 */],
		[probability:14, code:"GP109"/* 神秘水晶 */]
	],
	drops_more:[
	]
]
