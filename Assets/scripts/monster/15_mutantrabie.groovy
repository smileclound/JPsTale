monster = [
	ID:'15_mutantrabie',
	Name:'变异兔妖',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:15,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:140,
		// Attack Status
		Atk:[10, 15],
		AtkSpeed:7,
		Range:45,
		Rating:400,

		// Defence Status
		Flee:54,
		Absorb:5,
		Block:8,
		DamageStunPers:100,

		// Resistance
		Earth:25,
		Fire:-10,
		Ice:10,// Water
		Lighting:-15,// Wind
		Poison:100,
	],

	// AI
	AI:[
		Nature:0x80,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:7,
		Real_Sight:350,
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
		ArrowPosi:[-1, 50],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:3,
		Model:'char/monster/Monrebion/Monrebion.ini',
		Sound:0x00001260,
	],

	// Drops
	AllSeeItem:false,
	Exp:690,
	Quantity:1,
	drops:[
		[probability:2750, code:"NULL"/* Drops nothing */],
		[probability:3600, code:"GG101", value:[25, 55]/* Gold */],
		[probability:280, code:"PL101"/* 低级复生命药水 */],
		[probability:280, code:"PS101"/* 低级恢复耐力药水 */],
		[probability:280, code:"PM101"/* 低级恢复魔法药水 */],
		[probability:280, code:"PL101"/* 低级复生命药水 */],
		[probability:280, code:"PM101"/* 低级恢复魔法药水 */],
		[probability:71, code:"DA102"/* 战斗服 */],
		[probability:71, code:"DA202"/* 袍 */],
		[probability:71, code:"WC102"/* 虎爪 */],
		[probability:71, code:"WH102"/* 星刺锤 */],
		[probability:71, code:"WS102"/* 羊角弓 */],
		[probability:71, code:"WS202"/* 蛇行匕首 */],
		[probability:71, code:"WP102"/* 点钢蛇矛 */],
		[probability:71, code:"WT102"/* 战标 */],
		[probability:71, code:"DB102"/* 精灵靴 */],
		[probability:71, code:"DG102"/* 拳套 */],
		[probability:71, code:"OA103"/* 多情环 */],
		[probability:71, code:"OR103"/* 金戒指 */],
		[probability:71, code:"OA202"/* 精铁臂环 */],
		[probability:71, code:"GP101"/* 独角兽水晶 */],
		[probability:53, code:"DA103"/* 皮甲 */],
		[probability:53, code:"DA203"/* 长袍 */],
		[probability:53, code:"WC103"/* 鱼镰刺 */],
		[probability:53, code:"WH103"/* 长锤 */],
		[probability:53, code:"WS103"/* 手弩 */],
		[probability:53, code:"WS203"/* 断剑 */],
		[probability:53, code:"WP103"/* 鹰嘴矛 */],
		[probability:53, code:"WT103"/* 长牙标 */],
		[probability:53, code:"DB103"/* 精钢靴 */],
		[probability:53, code:"DG103"/* 铁拳套 */],
		[probability:53, code:"OA203"/* 倒刃臂环 */],
		[probability:53, code:"OR104"/* 玉戒指 */],
		[probability:53, code:"OA104"/* 金项链 */],
		[probability:53, code:"PM101"/* 低级恢复魔法药水 */],
		[probability:53, code:"EC104"/* 回城卷 */],
		[probability:20, code:"DA104"/* 铠甲 */],
		[probability:20, code:"DA204"/* 常青袍 */],
		[probability:20, code:"WC104"/* 蛇牙刺 */],
		[probability:20, code:"WH104"/* 巨灵锤 */],
		[probability:20, code:"WS104"/* 十字弩 */],
		[probability:20, code:"WS204"/* 短剑 */],
		[probability:20, code:"WP104"/* 方天战戟 */],
		[probability:20, code:"WT104"/* 铁标 */],
		[probability:20, code:"DB104"/* 精铜靴 */],
		[probability:20, code:"DG104"/* 银贝护手 */],
		[probability:20, code:"OS102"/* 流云石 */],
		[probability:20, code:"OR105"/* 蓝宝石戒指 */],
		[probability:20, code:"OA105"/* 梦之心链 */],
		[probability:20, code:"PM102"/* 中级恢复魔法药水 */],
		[probability:20, code:"EC104"/* 回城卷 */],
		[probability:11, code:"DA105"/* 鱼鳞甲 */],
		[probability:11, code:"DA205"/* 战斗袍 */],
		[probability:11, code:"WC105"/* 玄电爪 */],
		[probability:11, code:"WH105"/* 破天锤 */],
		[probability:11, code:"WS205"/* 长剑 */],
		[probability:11, code:"WS105"/* 战弓 */],
		[probability:11, code:"WP105"/* 鸠牙战镰 */],
		[probability:11, code:"WT105"/* 双刺标 */],
		[probability:11, code:"DB104"/* 精铜靴 */],
		[probability:11, code:"DG105"/* 钢指护手 */],
		[probability:11, code:"OS103"/* 海精石 */],
		[probability:11, code:"OR105"/* 蓝宝石戒指 */],
		[probability:11, code:"OA105"/* 梦之心链 */]
	],
	drops_more:[
	]
]
