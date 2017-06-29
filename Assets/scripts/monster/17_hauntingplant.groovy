monster = [
	ID:'17_hauntingplant',
	Name:'绿木精',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:17,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:180,
		// Attack Status
		Atk:[9, 15],
		AtkSpeed:8,
		Range:50,
		Rating:350,

		// Defence Status
		Flee:80,
		Absorb:4,
		Block:0,
		DamageStunPers:100,

		// Resistance
		Earth:30,
		Fire:-10,
		Ice:10,// Water
		Lighting:-15,// Wind
		Poison:100,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:8,
		Real_Sight:360,
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
		ArrowPosi:[-33, 120],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:-1,
		Model:'char/monster/Monhauntplant/Monhauntplant.ini',
		Sound:0x00001450,
	],

	// Drops
	AllSeeItem:false,
	Exp:770,
	Quantity:1,
	drops:[
		[probability:2900, code:"NULL"/* Drops nothing */],
		[probability:3800, code:"GG101", value:[30, 55]/* Gold */],
		[probability:333, code:"PL101"/* 低级复生命药水 */],
		[probability:333, code:"PL102"/* 中级恢复生命药水 */],
		[probability:333, code:"PS102"/* 中级恢复耐力药水 */],
		[probability:68, code:"DA103"/* 皮甲 */],
		[probability:68, code:"WA103"/* 板斧 */],
		[probability:68, code:"WC103"/* 鱼镰刺 */],
		[probability:68, code:"WS103"/* 手弩 */],
		[probability:68, code:"WS203"/* 断剑 */],
		[probability:68, code:"WH103"/* 长锤 */],
		[probability:68, code:"WP103"/* 鹰嘴矛 */],
		[probability:68, code:"WT103"/* 长牙标 */],
		[probability:68, code:"DB103"/* 精钢靴 */],
		[probability:68, code:"DS103"/* 圆盾 */],
		[probability:68, code:"DG103"/* 铁拳套 */],
		[probability:68, code:"OA203"/* 倒刃臂环 */],
		[probability:68, code:"OR104"/* 玉戒指 */],
		[probability:68, code:"OA104"/* 金项链 */],
		[probability:68, code:"PM101"/* 低级恢复魔法药水 */],
		[probability:68, code:"EC101"/* 回城卷 */],
		[probability:46, code:"DA104"/* 铠甲 */],
		[probability:46, code:"WA104"/* 劈地斧 */],
		[probability:46, code:"WC104"/* 蛇牙刺 */],
		[probability:46, code:"WS104"/* 十字弩 */],
		[probability:46, code:"WS204"/* 短剑 */],
		[probability:46, code:"WH104"/* 巨灵锤 */],
		[probability:46, code:"WP104"/* 方天战戟 */],
		[probability:46, code:"WT104"/* 铁标 */],
		[probability:46, code:"PM102"/* 中级恢复魔法药水 */],
		[probability:46, code:"PS102"/* 中级恢复耐力药水 */],
		[probability:46, code:"PM102"/* 中级恢复魔法药水 */],
		[probability:46, code:"PL102"/* 中级恢复生命药水 */],
		[probability:46, code:"EC101"/* 回城卷 */],
		[probability:46, code:"OR105"/* 蓝宝石戒指 */],
		[probability:46, code:"OA105"/* 梦之心链 */],
		[probability:27, code:"DA105"/* 鱼鳞甲 */],
		[probability:27, code:"WA105"/* 双刃战斧 */],
		[probability:27, code:"WC105"/* 玄电爪 */],
		[probability:27, code:"WH105"/* 破天锤 */],
		[probability:27, code:"WS205"/* 长剑 */],
		[probability:27, code:"WS105"/* 战弓 */],
		[probability:27, code:"WP105"/* 鸠牙战镰 */],
		[probability:27, code:"WT105"/* 双刺标 */],
		[probability:27, code:"OS103"/* 海精石 */],
		[probability:27, code:"OR105"/* 蓝宝石戒指 */],
		[probability:27, code:"OA105"/* 梦之心链 */],
		[probability:14, code:"DA106"/* 索子甲 */],
		[probability:14, code:"wa106"/* 凯旋 */],
		[probability:14, code:"WC106"/* 狼牙刃 */],
		[probability:14, code:"WH106"/* 浑元金锤 */],
		[probability:14, code:"WS206"/* 阔剑 */],
		[probability:14, code:"WS106"/* 长弓 */],
		[probability:14, code:"WP106"/* 三叉戟 */],
		[probability:14, code:"WT106"/* 精灵标 */],
		[probability:14, code:"OS104"/* 天仪石 */],
		[probability:14, code:"OR106"/* 红宝石戒指 */],
		[probability:14, code:"OA106"/* 碧云石链 */],
		[probability:14, code:"OA206"/* 精灵臂环 */],
		[probability:14, code:"DB106"/* 黄金靴 */],
		[probability:14, code:"DG106"/* 白金护手 */]
	],
	drops_more:[
	]
]
