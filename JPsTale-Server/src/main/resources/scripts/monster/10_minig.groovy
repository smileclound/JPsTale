monster = [
	ID:'10_minig',
	Name:'绿豆糕',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:10,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:75,
		// Attack Status
		Atk:[5, 9],
		AtkSpeed:8,
		Range:25,
		Rating:250,

		// Defence Status
		Flee:30,
		Absorb:3,
		Block:0,
		DamageStunPers:100,

		// Resistance
		Earth:10,
		Fire:-10,
		Ice:20,// Water
		Lighting:-10,// Wind
		Poison:50,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:4,
		Real_Sight:320,
		Talks:[],

		// Move Behavier
		Move_Speed:3,
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
		ArrowPosi:[0, 0],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:0,
		Model:'char/monster/MonminiG/MonminiG-a1.ini',
		Sound:0x00001030,
	],

	// Drops
	AllSeeItem:false,
	Exp:320,
	Quantity:1,
	drops:[
		[probability:3600, code:"NULL"/* Drops nothing */],
		[probability:3500, code:"GG101", value:[10, 30]/* Gold */],
		[probability:500, code:"PL101"/* 低级复生命药水 */],
		[probability:500, code:"PS101"/* 低级恢复耐力药水 */],
		[probability:500, code:"PL101"/* 低级复生命药水 */],
		[probability:61, code:"DA103"/* 皮甲 */],
		[probability:61, code:"WA102"/* 铁斧 */],
		[probability:61, code:"WC102"/* 虎爪 */],
		[probability:61, code:"WH102"/* 星刺锤 */],
		[probability:61, code:"WP102"/* 点钢蛇矛 */],
		[probability:61, code:"WS102"/* 羊角弓 */],
		[probability:61, code:"WS202"/* 蛇行匕首 */],
		[probability:61, code:"WT102"/* 战标 */],
		[probability:61, code:"PL102"/* 中级恢复生命药水 */],
		[probability:61, code:"PS102"/* 中级恢复耐力药水 */],
		[probability:61, code:"OR103"/* 金戒指 */],
		[probability:61, code:"OA103"/* 多情环 */],
		[probability:61, code:"DA103"/* 皮甲 */],
		[probability:33, code:"DA104"/* 铠甲 */],
		[probability:33, code:"WA103"/* 板斧 */],
		[probability:33, code:"WC103"/* 鱼镰刺 */],
		[probability:33, code:"WH103"/* 长锤 */],
		[probability:33, code:"WP103"/* 鹰嘴矛 */],
		[probability:33, code:"WS103"/* 手弩 */],
		[probability:33, code:"WS203"/* 断剑 */],
		[probability:33, code:"WT103"/* 长牙标 */],
		[probability:33, code:"OR104"/* 玉戒指 */],
		[probability:33, code:"OA104"/* 金项链 */],
		[probability:33, code:"DA104"/* 铠甲 */],
		[probability:33, code:"OS102"/* 流云石 */],
		[probability:15, code:"DA105"/* 鱼鳞甲 */],
		[probability:15, code:"WA104"/* 劈地斧 */],
		[probability:15, code:"WC104"/* 蛇牙刺 */],
		[probability:15, code:"WH104"/* 巨灵锤 */],
		[probability:15, code:"WP104"/* 方天战戟 */],
		[probability:15, code:"WS104"/* 十字弩 */],
		[probability:15, code:"WS204"/* 短剑 */],
		[probability:15, code:"WT104"/* 铁标 */],
		[probability:15, code:"DS103"/* 圆盾 */],
		[probability:15, code:"DB103"/* 精钢靴 */],
		[probability:15, code:"DG103"/* 铁拳套 */],
		[probability:15, code:"GP102"/* 魔兽兵水晶 */],
		[probability:15, code:"GP109"/* 神秘水晶 */]
	],
	drops_more:[
	]
]
