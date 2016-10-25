monster = [
	ID:'8_ghost',
	Name:'鬼魂',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:8,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:80,
		// Attack Status
		Atk:[5, 9],
		AtkSpeed:8,
		Range:45,
		Rating:180,

		// Defence Status
		Flee:16,
		Absorb:0,
		Block:0,
		DamageStunPers:100,

		// Resistance
		Earth:50,
		Fire:0,
		Ice:30,// Water
		Lighting:0,// Wind
		Poison:100,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:7,
		Real_Sight:330,
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
		ArrowPosi:[-7, 60],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:1,
		Model:'char/monster/ghost/ghost.INI',
		Sound:0x00001390,
	],

	// Drops
	AllSeeItem:false,
	Exp:250,
	Quantity:1,
	drops:[
		[probability:3000, code:"NULL"/* Drops nothing */],
		[probability:4000, code:"GG101", value:[15, 30]/* Gold */],
		[probability:433, code:"PL101"/* 低级复生命药水 */],
		[probability:433, code:"PS101"/* 低级恢复耐力药水 */],
		[probability:433, code:"PL101"/* 低级复生命药水 */],
		[probability:61, code:"WS101"/* 短弓 */],
		[probability:61, code:"WA101"/* 石斧 */],
		[probability:61, code:"WH101"/* 狼牙棒 */],
		[probability:61, code:"WP101"/* 精制木杖 */],
		[probability:61, code:"WT101"/* 标枪 */],
		[probability:61, code:"PL102"/* 中级恢复生命药水 */],
		[probability:61, code:"PS102"/* 中级恢复耐力药水 */],
		[probability:61, code:"DA102"/* 战斗服 */],
		[probability:61, code:"DG101"/* 手套 */],
		[probability:61, code:"DS101"/* 木盾 */],
		[probability:61, code:"DB101"/* 皮靴 */],
		[probability:61, code:"OR101"/* 情殇 */],
		[probability:61, code:"OA201"/* 皮制臂环 */],
		[probability:40, code:"WS102"/* 羊角弓 */],
		[probability:40, code:"WS202"/* 蛇行匕首 */],
		[probability:40, code:"WA102"/* 铁斧 */],
		[probability:40, code:"WH102"/* 星刺锤 */],
		[probability:40, code:"WP102"/* 点钢蛇矛 */],
		[probability:40, code:"WT102"/* 战标 */],
		[probability:40, code:"WC102"/* 虎爪 */],
		[probability:40, code:"OR102"/* 铜戒指 */],
		[probability:40, code:"OA102"/* 红玉项链 */],
		[probability:40, code:"OS101"/* 星遗石 */],
		[probability:18, code:"WS103"/* 手弩 */],
		[probability:18, code:"WS203"/* 断剑 */],
		[probability:18, code:"WA103"/* 板斧 */],
		[probability:18, code:"WH103"/* 长锤 */],
		[probability:18, code:"WP103"/* 鹰嘴矛 */],
		[probability:18, code:"WT103"/* 长牙标 */],
		[probability:18, code:"WC103"/* 鱼镰刺 */],
		[probability:18, code:"OR103"/* 金戒指 */],
		[probability:18, code:"OA103"/* 多情环 */],
		[probability:18, code:"DA103"/* 皮甲 */],
		[probability:18, code:"OS102"/* 流云石 */],
		[probability:9, code:"WS104"/* 十字弩 */],
		[probability:9, code:"WS204"/* 短剑 */],
		[probability:9, code:"WA104"/* 劈地斧 */],
		[probability:9, code:"WH104"/* 巨灵锤 */],
		[probability:9, code:"WP104"/* 方天战戟 */],
		[probability:9, code:"WT104"/* 铁标 */],
		[probability:9, code:"WC104"/* 蛇牙刺 */],
		[probability:9, code:"OR104"/* 玉戒指 */],
		[probability:9, code:"OA104"/* 金项链 */],
		[probability:9, code:"DA104"/* 铠甲 */],
		[probability:9, code:"OS103"/* 海精石 */]
	],
	drops_more:[
	]
]
