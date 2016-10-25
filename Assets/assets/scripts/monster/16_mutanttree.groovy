monster = [
	ID:'16_mutanttree',
	Name:'树根怪',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:16,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:150,
		// Attack Status
		Atk:[10, 15],
		AtkSpeed:8,
		Range:120,
		Rating:400,

		// Defence Status
		Flee:68,
		Absorb:6,
		Block:0,
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
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:7,
		Real_Sight:350,
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
		ClassCode:0,
		ArrowPosi:[-8, 60],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:1,
		Model:'char/monster/Monburntree/Monburntree.ini',
		Sound:0x00001270,
	],

	// Drops
	AllSeeItem:false,
	Exp:710,
	Quantity:2,
	drops:[
		[probability:2700, code:"NULL"/* Drops nothing */],
		[probability:3650, code:"GG101", value:[20, 45]/* Gold */],
		[probability:466, code:"PL101"/* 低级复生命药水 */],
		[probability:466, code:"PS101"/* 低级恢复耐力药水 */],
		[probability:466, code:"PM101"/* 低级恢复魔法药水 */],
		[probability:62, code:"DA102"/* 战斗服 */],
		[probability:62, code:"DA202"/* 袍 */],
		[probability:62, code:"WA102"/* 铁斧 */],
		[probability:62, code:"WH102"/* 星刺锤 */],
		[probability:62, code:"WM102"/* 执着之杖 */],
		[probability:62, code:"WP102"/* 点钢蛇矛 */],
		[probability:62, code:"WT102"/* 战标 */],
		[probability:62, code:"DB102"/* 精灵靴 */],
		[probability:62, code:"DS102"/* 钉盾 */],
		[probability:62, code:"DG102"/* 拳套 */],
		[probability:62, code:"WT101"/* 标枪 */],
		[probability:62, code:"OM102"/* 水晶球 */],
		[probability:62, code:"PL102"/* 中级恢复生命药水 */],
		[probability:62, code:"PS102"/* 中级恢复耐力药水 */],
		[probability:62, code:"PM102"/* 中级恢复魔法药水 */],
		[probability:62, code:"OA102"/* 红玉项链 */],
		[probability:53, code:"DA103"/* 皮甲 */],
		[probability:53, code:"DA203"/* 长袍 */],
		[probability:53, code:"WA103"/* 板斧 */],
		[probability:53, code:"WH103"/* 长锤 */],
		[probability:53, code:"WM103"/* 暗杀之杖 */],
		[probability:53, code:"WP103"/* 鹰嘴矛 */],
		[probability:53, code:"WS103"/* 手弩 */],
		[probability:53, code:"WS204"/* 短剑 */],
		[probability:53, code:"WT103"/* 长牙标 */],
		[probability:53, code:"DB103"/* 精钢靴 */],
		[probability:53, code:"DS103"/* 圆盾 */],
		[probability:53, code:"OM103"/* 淬角水晶 */],
		[probability:53, code:"OR103"/* 金戒指 */],
		[probability:53, code:"OA103"/* 多情环 */],
		[probability:53, code:"OS102"/* 流云石 */],
		[probability:17, code:"DA104"/* 铠甲 */],
		[probability:17, code:"DA204"/* 常青袍 */],
		[probability:17, code:"WA104"/* 劈地斧 */],
		[probability:17, code:"WH104"/* 巨灵锤 */],
		[probability:17, code:"WM104"/* 涤荡之杖 */],
		[probability:17, code:"WP104"/* 方天战戟 */],
		[probability:17, code:"WS204"/* 短剑 */],
		[probability:17, code:"WS104"/* 十字弩 */],
		[probability:17, code:"WT104"/* 铁标 */],
		[probability:17, code:"DB104"/* 精铜靴 */],
		[probability:17, code:"DS104"/* 轻盾 */],
		[probability:17, code:"OM104"/* 龙骨念珠 */],
		[probability:17, code:"EC104"/* 回城卷 */],
		[probability:17, code:"PM101"/* 低级恢复魔法药水 */],
		[probability:17, code:"OS103"/* 海精石 */],
		[probability:17, code:"OR104"/* 玉戒指 */],
		[probability:17, code:"OA104"/* 金项链 */],
		[probability:9, code:"DA105"/* 鱼鳞甲 */],
		[probability:9, code:"DA205"/* 战斗袍 */],
		[probability:9, code:"WA105"/* 双刃战斧 */],
		[probability:9, code:"WH105"/* 破天锤 */],
		[probability:9, code:"WM105"/* 旋风之杖 */],
		[probability:9, code:"WP105"/* 鸠牙战镰 */],
		[probability:9, code:"WS205"/* 长剑 */],
		[probability:9, code:"WS105"/* 战弓 */],
		[probability:9, code:"WT105"/* 双刺标 */],
		[probability:9, code:"DB105"/* 百兽靴 */],
		[probability:9, code:"DS105"/* 罗塔盾 */],
		[probability:9, code:"OM105"/* 龙额念珠 */],
		[probability:9, code:"OS103"/* 海精石 */],
		[probability:9, code:"OR105"/* 蓝宝石戒指 */],
		[probability:9, code:"OS104"/* 天仪石 */],
		[probability:9, code:"OA105"/* 梦之心链 */]
	],
	drops_more:[
	]
]
