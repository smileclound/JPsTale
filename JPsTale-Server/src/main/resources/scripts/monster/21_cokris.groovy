monster = [
	ID:'21_cokris',
	Name:'独眼蜈蚣',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:21,
	ActiveHour:0,
	RespawnGroup:[2, 2],

	Attributes:[
		Life:230,
		// Attack Status
		Atk:[13, 19],
		AtkSpeed:5,
		Range:100,
		Rating:420,

		// Defence Status
		Flee:90,
		Absorb:6,
		Block:0,
		DamageStunPers:100,

		// Resistance
		Earth:15,
		Fire:-10,
		Ice:50,// Water
		Lighting:10,// Wind
		Poison:100,
	],

	// AI
	AI:[
		Nature:0x80,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:5,
		Real_Sight:360,
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
		ClassCode:51,
		ArrowPosi:[1, 70],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:-1,
		Model:'char/monster/cokris/cokris.INI',
		Sound:0x000010D0,
	],

	// Drops
	AllSeeItem:false,
	Exp:1300,
	Quantity:1,
	drops:[
		[probability:3350, code:"NULL"/* Drops nothing */],
		[probability:3500, code:"GG101", value:[34, 60]/* Gold */],
		[probability:666, code:"PL101"/* 低级复生命药水 */],
		[probability:666, code:"PS101"/* 低级恢复耐力药水 */],
		[probability:666, code:"PL101"/* 低级复生命药水 */],
		[probability:46, code:"WS103"/* 手弩 */],
		[probability:46, code:"WS203"/* 断剑 */],
		[probability:46, code:"WA103"/* 板斧 */],
		[probability:46, code:"WC103"/* 鱼镰刺 */],
		[probability:46, code:"WH103"/* 长锤 */],
		[probability:46, code:"WP103"/* 鹰嘴矛 */],
		[probability:46, code:"PL103"/* 高级恢复生命药水 */],
		[probability:46, code:"PS103"/* 高级恢复耐力药水 */],
		[probability:46, code:"DA103"/* 皮甲 */],
		[probability:46, code:"DB103"/* 精钢靴 */],
		[probability:46, code:"DS103"/* 圆盾 */],
		[probability:46, code:"DG103"/* 铁拳套 */],
		[probability:46, code:"OR103"/* 金戒指 */],
		[probability:46, code:"OA203"/* 倒刃臂环 */],
		[probability:46, code:"GP102"/* 魔兽兵水晶 */],
		[probability:25, code:"DA104"/* 铠甲 */],
		[probability:25, code:"DA204"/* 常青袍 */],
		[probability:25, code:"DB103"/* 精钢靴 */],
		[probability:25, code:"WS104"/* 十字弩 */],
		[probability:25, code:"WS204"/* 短剑 */],
		[probability:25, code:"WH104"/* 巨灵锤 */],
		[probability:25, code:"WP104"/* 方天战戟 */],
		[probability:25, code:"PM102"/* 中级恢复魔法药水 */],
		[probability:25, code:"OS103"/* 海精石 */],
		[probability:25, code:"OR104"/* 玉戒指 */],
		[probability:25, code:"OA104"/* 金项链 */],
		[probability:25, code:"GP103"/* 浮灵水晶 */],
		[probability:9, code:"DA105"/* 鱼鳞甲 */],
		[probability:9, code:"DA205"/* 战斗袍 */],
		[probability:9, code:"DB104"/* 精铜靴 */],
		[probability:9, code:"DG104"/* 银贝护手 */],
		[probability:9, code:"DS104"/* 轻盾 */],
		[probability:9, code:"OA204"/* 大力臂环 */],
		[probability:9, code:"WA105"/* 双刃战斧 */],
		[probability:9, code:"WC105"/* 玄电爪 */],
		[probability:9, code:"WH105"/* 破天锤 */],
		[probability:9, code:"WP105"/* 鸠牙战镰 */],
		[probability:9, code:"WS105"/* 战弓 */],
		[probability:9, code:"WS205"/* 长剑 */],
		[probability:9, code:"WT105"/* 双刺标 */],
		[probability:9, code:"OS104"/* 天仪石 */],
		[probability:9, code:"OR105"/* 蓝宝石戒指 */],
		[probability:9, code:"OA105"/* 梦之心链 */]
	],
	drops_more:[
	]
]
