monster = [
	ID:'8_egan',
	Name:'草帽怪',
	Clazz:0,// 1 BOSS
	Brood:0x92,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:8,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:65,
		// Attack Status
		Atk:[4, 10],
		AtkSpeed:5,
		Range:145,
		Rating:180,

		// Defence Status
		Flee:16,
		Absorb:2,
		Block:0,
		DamageStunPers:100,

		// Resistance
		Earth:10,
		Fire:-10,
		Ice:0,// Water
		Lighting:0,// Wind
		Poison:-15,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:8,
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
		ArrowPosi:[2, 40],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:0,
		Model:'char/monster/MonEgan/Monegan.ini',
		Sound:0x00001230,
	],

	// Drops
	AllSeeItem:false,
	Exp:260,
	Quantity:1,
	drops:[
		[probability:2750, code:"NULL"/* Drops nothing */],
		[probability:4000, code:"GG101", value:[15, 30]/* Gold */],
		[probability:333, code:"PL101"/* 低级复生命药水 */],
		[probability:333, code:"PS101"/* 低级恢复耐力药水 */],
		[probability:333, code:"PM101"/* 低级恢复魔法药水 */],
		[probability:85, code:"WA101"/* 石斧 */],
		[probability:85, code:"WH101"/* 狼牙棒 */],
		[probability:85, code:"WP101"/* 精制木杖 */],
		[probability:85, code:"WM101"/* 沉默之杖 */],
		[probability:85, code:"WS101"/* 短弓 */],
		[probability:85, code:"WT101"/* 标枪 */],
		[probability:85, code:"PL102"/* 中级恢复生命药水 */],
		[probability:85, code:"PS102"/* 中级恢复耐力药水 */],
		[probability:85, code:"DA102"/* 战斗服 */],
		[probability:85, code:"DG101"/* 手套 */],
		[probability:85, code:"DS101"/* 木盾 */],
		[probability:85, code:"DB101"/* 皮靴 */],
		[probability:85, code:"OR101"/* 情殇 */],
		[probability:85, code:"OA201"/* 皮制臂环 */],
		[probability:54, code:"WA102"/* 铁斧 */],
		[probability:54, code:"WC102"/* 虎爪 */],
		[probability:54, code:"WH102"/* 星刺锤 */],
		[probability:54, code:"WP102"/* 点钢蛇矛 */],
		[probability:54, code:"WM102"/* 执着之杖 */],
		[probability:54, code:"WS102"/* 羊角弓 */],
		[probability:54, code:"WS202"/* 蛇行匕首 */],
		[probability:54, code:"WT102"/* 战标 */],
		[probability:54, code:"OR102"/* 铜戒指 */],
		[probability:54, code:"OA102"/* 红玉项链 */],
		[probability:54, code:"OS101"/* 星遗石 */],
		[probability:27, code:"WA103"/* 板斧 */],
		[probability:27, code:"WC103"/* 鱼镰刺 */],
		[probability:27, code:"WH103"/* 长锤 */],
		[probability:27, code:"WP103"/* 鹰嘴矛 */],
		[probability:27, code:"WM103"/* 暗杀之杖 */],
		[probability:27, code:"WS103"/* 手弩 */],
		[probability:27, code:"WS203"/* 断剑 */],
		[probability:27, code:"WT103"/* 长牙标 */],
		[probability:27, code:"OR103"/* 金戒指 */],
		[probability:27, code:"OA103"/* 多情环 */],
		[probability:27, code:"OS102"/* 流云石 */],
		[probability:12, code:"WA104"/* 劈地斧 */],
		[probability:12, code:"WC104"/* 蛇牙刺 */],
		[probability:12, code:"WH104"/* 巨灵锤 */],
		[probability:12, code:"WM104"/* 涤荡之杖 */],
		[probability:12, code:"WS104"/* 十字弩 */],
		[probability:12, code:"WS204"/* 短剑 */],
		[probability:12, code:"WP104"/* 方天战戟 */],
		[probability:12, code:"WT104"/* 铁标 */],
		[probability:12, code:"OM104"/* 龙骨念珠 */],
		[probability:12, code:"OR104"/* 玉戒指 */],
		[probability:12, code:"OA104"/* 金项链 */],
		[probability:12, code:"OS103"/* 海精石 */]
	],
	drops_more:[
	]
]
