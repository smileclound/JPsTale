monster = [
	ID:'17_planty',
	Name:'红木精怪',
	Clazz:0,// 1 BOSS
	Brood:0x92,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:17,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:180,
		// Attack Status
		Atk:[9, 15],
		AtkSpeed:7,
		Range:45,
		Rating:350,

		// Defence Status
		Flee:70,
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
		Nature:0x80,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:5,
		Real_Sight:360,
		Talks:[],

		// Move Behavier
		Move_Speed:1,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:17,
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
		ArrowPosi:[2, 80],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:3,
		Model:'char/monster/Monplant/MonPlant-c.INI',
		Sound:0x00001040,
	],

	// Drops
	AllSeeItem:false,
	Exp:770,
	Quantity:1,
	drops:[
		[probability:3000, code:"NULL"/* Drops nothing */],
		[probability:3100, code:"GG101", value:[30, 55]/* Gold */],
		[probability:500, code:"PL101"/* 低级复生命药水 */],
		[probability:500, code:"PL102"/* 中级恢复生命药水 */],
		[probability:500, code:"PS102"/* 中级恢复耐力药水 */],
		[probability:75, code:"DA103"/* 皮甲 */],
		[probability:75, code:"WA103"/* 板斧 */],
		[probability:75, code:"WC103"/* 鱼镰刺 */],
		[probability:75, code:"WS103"/* 手弩 */],
		[probability:75, code:"WS203"/* 断剑 */],
		[probability:75, code:"WH103"/* 长锤 */],
		[probability:75, code:"WP103"/* 鹰嘴矛 */],
		[probability:75, code:"WT103"/* 长牙标 */],
		[probability:75, code:"DB103"/* 精钢靴 */],
		[probability:75, code:"DS103"/* 圆盾 */],
		[probability:75, code:"DG103"/* 铁拳套 */],
		[probability:75, code:"OA203"/* 倒刃臂环 */],
		[probability:75, code:"OR104"/* 玉戒指 */],
		[probability:75, code:"OA104"/* 金项链 */],
		[probability:75, code:"PM101"/* 低级恢复魔法药水 */],
		[probability:75, code:"EC101"/* 回城卷 */],
		[probability:50, code:"DA104"/* 铠甲 */],
		[probability:50, code:"WA104"/* 劈地斧 */],
		[probability:50, code:"WC104"/* 蛇牙刺 */],
		[probability:50, code:"WS104"/* 十字弩 */],
		[probability:50, code:"WS204"/* 短剑 */],
		[probability:50, code:"WH104"/* 巨灵锤 */],
		[probability:50, code:"WP104"/* 方天战戟 */],
		[probability:50, code:"WT104"/* 铁标 */],
		[probability:50, code:"PM102"/* 中级恢复魔法药水 */],
		[probability:50, code:"PS102"/* 中级恢复耐力药水 */],
		[probability:50, code:"PL102"/* 中级恢复生命药水 */],
		[probability:50, code:"EC101"/* 回城卷 */],
		[probability:50, code:"OR105"/* 蓝宝石戒指 */],
		[probability:50, code:"OA105"/* 梦之心链 */],
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
		[probability:13, code:"DA106"/* 索子甲 */],
		[probability:13, code:"wa106"/* 凯旋 */],
		[probability:13, code:"WC106"/* 狼牙刃 */],
		[probability:13, code:"WH106"/* 浑元金锤 */],
		[probability:13, code:"WS206"/* 阔剑 */],
		[probability:13, code:"WS106"/* 长弓 */],
		[probability:13, code:"WP106"/* 三叉戟 */],
		[probability:13, code:"WT106"/* 精灵标 */],
		[probability:13, code:"OS104"/* 天仪石 */],
		[probability:13, code:"OR106"/* 红宝石戒指 */],
		[probability:13, code:"OA106"/* 碧云石链 */],
		[probability:13, code:"OA206"/* 精灵臂环 */],
		[probability:13, code:"DB106"/* 黄金靴 */],
		[probability:13, code:"DG106"/* 白金护手 */],
		[probability:13, code:"GP109"/* 神秘水晶 */]
	],
	drops_more:[
	]
]
