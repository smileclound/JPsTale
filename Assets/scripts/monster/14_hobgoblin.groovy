monster = [
	ID:'14_hobgoblin',
	Name:'魔兽兵',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:14,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:130,
		// Attack Status
		Atk:[9, 16],
		AtkSpeed:8,
		Range:45,
		Rating:350,

		// Defence Status
		Flee:70,
		Absorb:4,
		Block:17,
		DamageStunPers:100,

		// Resistance
		Earth:10,
		Fire:20,
		Ice:10,// Water
		Lighting:0,// Wind
		Poison:0,
	],

	// AI
	AI:[
		Nature:0x80,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:6,
		Real_Sight:320,
		Talks:[],

		// Move Behavier
		Move_Speed:2,
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
		ClassCode:44,
		ArrowPosi:[0, 30],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:1,
		Model:'char/monster/Monhobgoblin/Monhobgoblin-a.INI',
		Sound:0x00001010,
	],

	// Drops
	AllSeeItem:false,
	Exp:670,
	Quantity:2,
	drops:[
		[probability:2900, code:"NULL"/* Drops nothing */],
		[probability:3500, code:"GG101", value:[20, 45]/* Gold */],
		[probability:400, code:"PL101"/* 低级复生命药水 */],
		[probability:400, code:"PS101"/* 低级恢复耐力药水 */],
		[probability:400, code:"PL101"/* 低级复生命药水 */],
		[probability:76, code:"DA102"/* 战斗服 */],
		[probability:76, code:"DS102"/* 钉盾 */],
		[probability:76, code:"WA102"/* 铁斧 */],
		[probability:76, code:"WC102"/* 虎爪 */],
		[probability:76, code:"WH102"/* 星刺锤 */],
		[probability:76, code:"WS102"/* 羊角弓 */],
		[probability:76, code:"WS203"/* 断剑 */],
		[probability:76, code:"WP102"/* 点钢蛇矛 */],
		[probability:76, code:"WT102"/* 战标 */],
		[probability:76, code:"DB102"/* 精灵靴 */],
		[probability:76, code:"DS102"/* 钉盾 */],
		[probability:76, code:"DG102"/* 拳套 */],
		[probability:76, code:"OA102"/* 红玉项链 */],
		[probability:57, code:"DA103"/* 皮甲 */],
		[probability:57, code:"DS103"/* 圆盾 */],
		[probability:57, code:"WA103"/* 板斧 */],
		[probability:57, code:"WC103"/* 鱼镰刺 */],
		[probability:57, code:"WH103"/* 长锤 */],
		[probability:57, code:"WS103"/* 手弩 */],
		[probability:57, code:"WS203"/* 断剑 */],
		[probability:57, code:"WP103"/* 鹰嘴矛 */],
		[probability:57, code:"WT103"/* 长牙标 */],
		[probability:57, code:"OR103"/* 金戒指 */],
		[probability:57, code:"OA103"/* 多情环 */],
		[probability:57, code:"OS102"/* 流云石 */],
		[probability:57, code:"GP102"/* 魔兽兵水晶 */],
		[probability:57, code:"GP102"/* 魔兽兵水晶 */],
		[probability:26, code:"DA104"/* 铠甲 */],
		[probability:26, code:"DS104"/* 轻盾 */],
		[probability:26, code:"WA104"/* 劈地斧 */],
		[probability:26, code:"WC104"/* 蛇牙刺 */],
		[probability:26, code:"WH104"/* 巨灵锤 */],
		[probability:26, code:"WS104"/* 十字弩 */],
		[probability:26, code:"WS204"/* 短剑 */],
		[probability:26, code:"WP104"/* 方天战戟 */],
		[probability:26, code:"WT104"/* 铁标 */],
		[probability:26, code:"EC101"/* 回城卷 */],
		[probability:26, code:"PM101"/* 低级恢复魔法药水 */],
		[probability:26, code:"OR104"/* 玉戒指 */],
		[probability:26, code:"OA104"/* 金项链 */],
		[probability:26, code:"GP102"/* 魔兽兵水晶 */],
		[probability:26, code:"GP102"/* 魔兽兵水晶 */],
		[probability:13, code:"DA105"/* 鱼鳞甲 */],
		[probability:13, code:"DS105"/* 罗塔盾 */],
		[probability:13, code:"WA105"/* 双刃战斧 */],
		[probability:13, code:"WC105"/* 玄电爪 */],
		[probability:13, code:"WH105"/* 破天锤 */],
		[probability:13, code:"WS105"/* 战弓 */],
		[probability:13, code:"WS205"/* 长剑 */],
		[probability:13, code:"WP105"/* 鸠牙战镰 */],
		[probability:13, code:"WT105"/* 双刺标 */],
		[probability:13, code:"OS103"/* 海精石 */],
		[probability:13, code:"OR105"/* 蓝宝石戒指 */],
		[probability:13, code:"OA105"/* 梦之心链 */],
		[probability:13, code:"GP103"/* 浮灵水晶 */],
		[probability:13, code:"GP103"/* 浮灵水晶 */],
		[probability:13, code:"GP109"/* 神秘水晶 */]
	],
	drops_more:[
	]
]
