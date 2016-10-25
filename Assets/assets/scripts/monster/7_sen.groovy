monster = [
	ID:'7_sen',
	Name:'路灯怪',
	Clazz:0,// 1 BOSS
	Brood:0x92,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:7,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:22,
		// Attack Status
		Atk:[3, 6],
		AtkSpeed:8,
		Range:35,
		Rating:150,

		// Defence Status
		Flee:10,
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
		Nature:0x80,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:6,
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
		ArrowPosi:[-14, 50],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:0,
		Model:'char/monster/Monsen/Monsen.ini',
		Sound:0x00001220,
	],

	// Drops
	AllSeeItem:false,
	Exp:170,
	Quantity:1,
	drops:[
		[probability:2100, code:"NULL"/* Drops nothing */],
		[probability:4000, code:"GG101", value:[8, 14]/* Gold */],
		[probability:333, code:"PL101"/* 低级复生命药水 */],
		[probability:333, code:"PS101"/* 低级恢复耐力药水 */],
		[probability:333, code:"PL101"/* 低级复生命药水 */],
		[probability:136, code:"DA102"/* 战斗服 */],
		[probability:136, code:"DA202"/* 袍 */],
		[probability:136, code:"WA101"/* 石斧 */],
		[probability:136, code:"WH101"/* 狼牙棒 */],
		[probability:136, code:"WM101"/* 沉默之杖 */],
		[probability:136, code:"WS101"/* 短弓 */],
		[probability:136, code:"WP101"/* 精制木杖 */],
		[probability:136, code:"OM101"/* 念珠 */],
		[probability:136, code:"PL101"/* 低级复生命药水 */],
		[probability:136, code:"PS101"/* 低级恢复耐力药水 */],
		[probability:136, code:"OR101"/* 情殇 */],
		[probability:61, code:"DA103"/* 皮甲 */],
		[probability:61, code:"DA203"/* 长袍 */],
		[probability:61, code:"WA102"/* 铁斧 */],
		[probability:61, code:"WC102"/* 虎爪 */],
		[probability:61, code:"WH102"/* 星刺锤 */],
		[probability:61, code:"WM102"/* 执着之杖 */],
		[probability:61, code:"WS102"/* 羊角弓 */],
		[probability:61, code:"WS202"/* 蛇行匕首 */],
		[probability:61, code:"WP102"/* 点钢蛇矛 */],
		[probability:61, code:"OM102"/* 水晶球 */],
		[probability:61, code:"OR102"/* 铜戒指 */],
		[probability:61, code:"OA102"/* 红玉项链 */],
		[probability:61, code:"OS102"/* 流云石 */],
		[probability:30, code:"DA104"/* 铠甲 */],
		[probability:30, code:"DA204"/* 常青袍 */],
		[probability:30, code:"WA103"/* 板斧 */],
		[probability:30, code:"WC103"/* 鱼镰刺 */],
		[probability:30, code:"WH103"/* 长锤 */],
		[probability:30, code:"WM103"/* 暗杀之杖 */],
		[probability:30, code:"WS103"/* 手弩 */],
		[probability:30, code:"WS203"/* 断剑 */],
		[probability:30, code:"WP103"/* 鹰嘴矛 */],
		[probability:30, code:"OM103"/* 淬角水晶 */],
		[probability:30, code:"OR103"/* 金戒指 */],
		[probability:30, code:"OA103"/* 多情环 */],
		[probability:30, code:"OS103"/* 海精石 */],
		[probability:16, code:"WA104"/* 劈地斧 */],
		[probability:16, code:"WC104"/* 蛇牙刺 */],
		[probability:16, code:"WH104"/* 巨灵锤 */],
		[probability:16, code:"WM104"/* 涤荡之杖 */],
		[probability:16, code:"WS104"/* 十字弩 */],
		[probability:16, code:"WS204"/* 短剑 */],
		[probability:16, code:"WP104"/* 方天战戟 */],
		[probability:16, code:"OM104"/* 龙骨念珠 */],
		[probability:16, code:"OR104"/* 玉戒指 */],
		[probability:16, code:"OA104"/* 金项链 */],
		[probability:16, code:"OS103"/* 海精石 */],
		[probability:16, code:"GP109"/* 神秘水晶 */]
	],
	drops_more:[
	]
]
