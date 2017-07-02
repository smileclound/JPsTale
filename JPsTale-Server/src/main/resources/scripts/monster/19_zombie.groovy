monster = [
	ID:'19_zombie',
	Name:'僵尸',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:19,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:200,
		// Attack Status
		Atk:[11, 17],
		AtkSpeed:7,
		Range:35,
		Rating:400,

		// Defence Status
		Flee:90,
		Absorb:10,
		Block:0,
		DamageStunPers:100,

		// Resistance
		Earth:30,
		Fire:-20,
		Ice:30,// Water
		Lighting:-15,// Wind
		Poison:100,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:7,
		Real_Sight:340,
		Talks:[],

		// Move Behavier
		Move_Speed:4,
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
		ArrowPosi:[-3, 30],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:1,
		Model:'char/monster/Monzombi/Monzombi-a.INI',
		Sound:0x00001060,
	],

	// Drops
	AllSeeItem:false,
	Exp:1000,
	Quantity:1,
	drops:[
		[probability:3100, code:"NULL"/* Drops nothing */],
		[probability:4000, code:"GG101", value:[34, 65]/* Gold */],
		[probability:500, code:"PL101"/* 低级复生命药水 */],
		[probability:500, code:"PS101"/* 低级恢复耐力药水 */],
		[probability:500, code:"PL101"/* 低级复生命药水 */],
		[probability:57, code:"DA103"/* 皮甲 */],
		[probability:57, code:"WA103"/* 板斧 */],
		[probability:57, code:"WS103"/* 手弩 */],
		[probability:57, code:"WC103"/* 鱼镰刺 */],
		[probability:57, code:"WS203"/* 断剑 */],
		[probability:57, code:"WH103"/* 长锤 */],
		[probability:57, code:"WP103"/* 鹰嘴矛 */],
		[probability:57, code:"DB103"/* 精钢靴 */],
		[probability:57, code:"DS103"/* 圆盾 */],
		[probability:57, code:"DG103"/* 铁拳套 */],
		[probability:57, code:"OA203"/* 倒刃臂环 */],
		[probability:57, code:"OR104"/* 玉戒指 */],
		[probability:57, code:"OA104"/* 金项链 */],
		[probability:57, code:"EC101"/* 回城卷 */],
		[probability:40, code:"DA104"/* 铠甲 */],
		[probability:40, code:"WA104"/* 劈地斧 */],
		[probability:40, code:"WS104"/* 十字弩 */],
		[probability:40, code:"WS204"/* 短剑 */],
		[probability:40, code:"WC104"/* 蛇牙刺 */],
		[probability:40, code:"WH104"/* 巨灵锤 */],
		[probability:40, code:"WP104"/* 方天战戟 */],
		[probability:40, code:"OR105"/* 蓝宝石戒指 */],
		[probability:40, code:"OA105"/* 梦之心链 */],
		[probability:40, code:"EC101"/* 回城卷 */],
		[probability:16, code:"DA105"/* 鱼鳞甲 */],
		[probability:16, code:"WS105"/* 战弓 */],
		[probability:16, code:"WS106"/* 长弓 */],
		[probability:16, code:"WS205"/* 长剑 */],
		[probability:16, code:"WS206"/* 阔剑 */],
		[probability:16, code:"WH105"/* 破天锤 */],
		[probability:16, code:"WP105"/* 鸠牙战镰 */],
		[probability:16, code:"WP106"/* 三叉戟 */],
		[probability:16, code:"PM102"/* 中级恢复魔法药水 */],
		[probability:16, code:"OR106"/* 红宝石戒指 */],
		[probability:16, code:"OA106"/* 碧云石链 */],
		[probability:16, code:"EC102"/* 回城卷 */]
	],
	drops_more:[
	]
]
