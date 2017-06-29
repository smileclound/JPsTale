monster = [
	ID:'25_minigsilver',
	Name:'蓝豆糕',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:25,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:270,
		// Attack Status
		Atk:[22, 31],
		AtkSpeed:8,
		Range:25,
		Rating:450,

		// Defence Status
		Flee:110,
		Absorb:8,
		Block:0,
		DamageStunPers:100,

		// Resistance
		Earth:20,
		Fire:25,
		Ice:15,// Water
		Lighting:10,// Wind
		Poison:50,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:8,
		Real_Sight:400,
		Talks:[],

		// Move Behavier
		Move_Speed:4,
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
		Model:'char/monster/MonminiG/MonminiG-C1.ini',
		Sound:0x00001030,
	],

	// Drops
	AllSeeItem:false,
	Exp:3200,
	Quantity:2,
	QuestItem:0x07010600,
	drops:[
		[probability:3200, code:"NULL"/* Drops nothing */],
		[probability:3300, code:"GG101", value:[100, 200]/* Gold */],
		[probability:333, code:"PL102"/* 中级恢复生命药水 */],
		[probability:333, code:"PS102"/* 中级恢复耐力药水 */],
		[probability:333, code:"PM102"/* 中级恢复魔法药水 */],
		[probability:333, code:"QT106"/* 神豆 */],
		[probability:333, code:"QT106"/* 神豆 */],
		[probability:333, code:"QT106"/* 神豆 */],
		[probability:52, code:"WS104"/* 十字弩 */],
		[probability:52, code:"WS204"/* 短剑 */],
		[probability:52, code:"WA104"/* 劈地斧 */],
		[probability:52, code:"WC104"/* 蛇牙刺 */],
		[probability:52, code:"WH104"/* 巨灵锤 */],
		[probability:52, code:"WP104"/* 方天战戟 */],
		[probability:52, code:"WT104"/* 铁标 */],
		[probability:52, code:"PL103"/* 高级恢复生命药水 */],
		[probability:52, code:"PS103"/* 高级恢复耐力药水 */],
		[probability:52, code:"DA104"/* 铠甲 */],
		[probability:52, code:"DB104"/* 精铜靴 */],
		[probability:52, code:"DS104"/* 轻盾 */],
		[probability:52, code:"DG104"/* 银贝护手 */],
		[probability:52, code:"OA204"/* 大力臂环 */],
		[probability:52, code:"OS102"/* 流云石 */],
		[probability:52, code:"OR105"/* 蓝宝石戒指 */],
		[probability:52, code:"OA105"/* 梦之心链 */],
		[probability:23, code:"WS105"/* 战弓 */],
		[probability:23, code:"WS205"/* 长剑 */],
		[probability:23, code:"WA105"/* 双刃战斧 */],
		[probability:23, code:"WC105"/* 玄电爪 */],
		[probability:23, code:"WH105"/* 破天锤 */],
		[probability:23, code:"WP105"/* 鸠牙战镰 */],
		[probability:23, code:"WT105"/* 双刺标 */],
		[probability:23, code:"PL104"/* 顶级恢复生命药水 */],
		[probability:23, code:"PS104"/* 顶级恢复耐力药水 */],
		[probability:23, code:"DA105"/* 鱼鳞甲 */],
		[probability:23, code:"DB105"/* 百兽靴 */],
		[probability:23, code:"DS105"/* 罗塔盾 */],
		[probability:23, code:"DG105"/* 钢指护手 */],
		[probability:23, code:"OA205"/* 龙鳞臂环 */],
		[probability:23, code:"OS103"/* 海精石 */],
		[probability:23, code:"OR106"/* 红宝石戒指 */],
		[probability:23, code:"OA106"/* 碧云石链 */],
		[probability:11, code:"WS106"/* 长弓 */],
		[probability:11, code:"WS206"/* 阔剑 */],
		[probability:11, code:"wa106"/* 凯旋 */],
		[probability:11, code:"WC106"/* 狼牙刃 */],
		[probability:11, code:"WH106"/* 浑元金锤 */],
		[probability:11, code:"WP106"/* 三叉戟 */],
		[probability:11, code:"WT106"/* 精灵标 */],
		[probability:11, code:"PL104"/* 顶级恢复生命药水 */],
		[probability:11, code:"PS104"/* 顶级恢复耐力药水 */],
		[probability:11, code:"DA106"/* 索子甲 */],
		[probability:11, code:"DB106"/* 黄金靴 */],
		[probability:11, code:"DS106"/* 百炼盾 */],
		[probability:11, code:"DG106"/* 白金护手 */],
		[probability:11, code:"OA206"/* 精灵臂环 */],
		[probability:11, code:"OS104"/* 天仪石 */],
		[probability:11, code:"OR106"/* 红宝石戒指 */],
		[probability:11, code:"OA106"/* 碧云石链 */]
	],
	drops_more:[
	]
]
