monster = [
	ID:'14_northgoblin',
	Name:'巨魔兽',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:14,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:130,
		// Attack Status
		Atk:[9, 15],
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
		ClassCode:43,
		ArrowPosi:[0, 30],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:1,
		Model:'char/monster/Monhobgoblin/Monhobgoblin-b.INI',
		Sound:0x00001010,
	],

	// Drops
	AllSeeItem:false,
	Exp:670,
	Quantity:2,
	drops:[
		[probability:2800, code:"NULL"/* Drops nothing */],
		[probability:3500, code:"GG101", value:[20, 45]/* Gold */],
		[probability:260, code:"PL101"/* 低级复生命药水 */],
		[probability:260, code:"PS101"/* 低级恢复耐力药水 */],
		[probability:260, code:"PM101"/* 低级恢复魔法药水 */],
		[probability:260, code:"PL101"/* 低级复生命药水 */],
		[probability:260, code:"PM101"/* 低级恢复魔法药水 */],
		[probability:58, code:"DA102"/* 战斗服 */],
		[probability:58, code:"DA202"/* 袍 */],
		[probability:58, code:"DS102"/* 钉盾 */],
		[probability:58, code:"WA102"/* 铁斧 */],
		[probability:58, code:"WH102"/* 星刺锤 */],
		[probability:58, code:"WM102"/* 执着之杖 */],
		[probability:58, code:"WP102"/* 点钢蛇矛 */],
		[probability:58, code:"WS102"/* 羊角弓 */],
		[probability:58, code:"WS203"/* 断剑 */],
		[probability:58, code:"WT102"/* 战标 */],
		[probability:58, code:"DB102"/* 精灵靴 */],
		[probability:58, code:"DS102"/* 钉盾 */],
		[probability:58, code:"DG102"/* 拳套 */],
		[probability:58, code:"WT101"/* 标枪 */],
		[probability:58, code:"PL102"/* 中级恢复生命药水 */],
		[probability:58, code:"PS102"/* 中级恢复耐力药水 */],
		[probability:58, code:"OA102"/* 红玉项链 */],
		[probability:50, code:"DA103"/* 皮甲 */],
		[probability:50, code:"DA203"/* 长袍 */],
		[probability:50, code:"DS103"/* 圆盾 */],
		[probability:50, code:"WA103"/* 板斧 */],
		[probability:50, code:"WH103"/* 长锤 */],
		[probability:50, code:"WM103"/* 暗杀之杖 */],
		[probability:50, code:"WP103"/* 鹰嘴矛 */],
		[probability:50, code:"WS103"/* 手弩 */],
		[probability:50, code:"WS204"/* 短剑 */],
		[probability:50, code:"WT103"/* 长牙标 */],
		[probability:50, code:"OR103"/* 金戒指 */],
		[probability:50, code:"OA103"/* 多情环 */],
		[probability:50, code:"OS102"/* 流云石 */],
		[probability:50, code:"PM102"/* 中级恢复魔法药水 */],
		[probability:50, code:"GP102"/* 魔兽兵水晶 */],
		[probability:50, code:"GP102"/* 魔兽兵水晶 */],
		[probability:25, code:"DA104"/* 铠甲 */],
		[probability:25, code:"DA204"/* 常青袍 */],
		[probability:25, code:"DS104"/* 轻盾 */],
		[probability:25, code:"WA104"/* 劈地斧 */],
		[probability:25, code:"WH104"/* 巨灵锤 */],
		[probability:25, code:"WM104"/* 涤荡之杖 */],
		[probability:25, code:"WP104"/* 方天战戟 */],
		[probability:25, code:"WS104"/* 十字弩 */],
		[probability:25, code:"WS204"/* 短剑 */],
		[probability:25, code:"WT104"/* 铁标 */],
		[probability:25, code:"EC104"/* 回城卷 */],
		[probability:25, code:"PM101"/* 低级恢复魔法药水 */],
		[probability:25, code:"OR104"/* 玉戒指 */],
		[probability:25, code:"OA104"/* 金项链 */],
		[probability:25, code:"GP102"/* 魔兽兵水晶 */],
		[probability:25, code:"GP102"/* 魔兽兵水晶 */],
		[probability:12, code:"DA105"/* 鱼鳞甲 */],
		[probability:12, code:"DA205"/* 战斗袍 */],
		[probability:12, code:"DS105"/* 罗塔盾 */],
		[probability:12, code:"WA105"/* 双刃战斧 */],
		[probability:12, code:"WH105"/* 破天锤 */],
		[probability:12, code:"WM105"/* 旋风之杖 */],
		[probability:12, code:"WP105"/* 鸠牙战镰 */],
		[probability:12, code:"WS105"/* 战弓 */],
		[probability:12, code:"WS205"/* 长剑 */],
		[probability:12, code:"WT105"/* 双刺标 */],
		[probability:12, code:"OS103"/* 海精石 */],
		[probability:12, code:"OR105"/* 蓝宝石戒指 */],
		[probability:12, code:"OA105"/* 梦之心链 */],
		[probability:12, code:"GP103"/* 浮灵水晶 */],
		[probability:12, code:"GP103"/* 浮灵水晶 */],
		[probability:12, code:"GP109"/* 神秘水晶 */]
	],
	drops_more:[
	]
]
