monster = [
	ID:'22_beedog',
	Name:'蜂巢怪',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:22,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:250,
		// Attack Status
		Atk:[15, 20],
		AtkSpeed:8,
		Range:110,
		Rating:420,

		// Defence Status
		Flee:95,
		Absorb:5,
		Block:0,
		DamageStunPers:100,

		// Resistance
		Earth:10,
		Fire:-10,
		Ice:20,// Water
		Lighting:-10,// Wind
		Poison:50,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:7,
		Real_Sight:360,
		Talks:[],

		// Move Behavier
		Move_Speed:3,
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
		ClassCode:53,
		ArrowPosi:[-16, 50],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:1,
		Model:'char/monster/Monbeedog/Monbeedog.ini',
		Sound:0x00001240,
	],

	// Drops
	AllSeeItem:false,
	Exp:1410,
	Quantity:1,
	QuestItem:0x09020100,
	drops:[
		[probability:4000, code:"NULL"/* Drops nothing */],
		[probability:3500, code:"GG101", value:[34, 60]/* Gold */],
		[probability:125, code:"PL101"/* 低级复生命药水 */],
		[probability:125, code:"PL102"/* 中级恢复生命药水 */],
		[probability:125, code:"PM101"/* 低级恢复魔法药水 */],
		[probability:125, code:"MA201"/* 蜂蜜 */],
		[probability:125, code:"MA201"/* 蜂蜜 */],
		[probability:125, code:"MA201"/* 蜂蜜 */],
		[probability:125, code:"MA201"/* 蜂蜜 */],
		[probability:125, code:"MA201"/* 蜂蜜 */],
		[probability:44, code:"DA203"/* 长袍 */],
		[probability:44, code:"DA103"/* 皮甲 */],
		[probability:44, code:"DB103"/* 精钢靴 */],
		[probability:44, code:"DS103"/* 圆盾 */],
		[probability:44, code:"DG103"/* 铁拳套 */],
		[probability:44, code:"OM103"/* 淬角水晶 */],
		[probability:44, code:"WH103"/* 长锤 */],
		[probability:44, code:"WM103"/* 暗杀之杖 */],
		[probability:44, code:"WP103"/* 鹰嘴矛 */],
		[probability:44, code:"WS103"/* 手弩 */],
		[probability:44, code:"WS203"/* 断剑 */],
		[probability:44, code:"WT103"/* 长牙标 */],
		[probability:44, code:"PL102"/* 中级恢复生命药水 */],
		[probability:44, code:"PS103"/* 高级恢复耐力药水 */],
		[probability:44, code:"PM102"/* 中级恢复魔法药水 */],
		[probability:44, code:"OR103"/* 金戒指 */],
		[probability:44, code:"OA203"/* 倒刃臂环 */],
		[probability:44, code:"GP102"/* 魔兽兵水晶 */],
		[probability:26, code:"DA204"/* 常青袍 */],
		[probability:26, code:"DA104"/* 铠甲 */],
		[probability:26, code:"DB104"/* 精铜靴 */],
		[probability:26, code:"DS104"/* 轻盾 */],
		[probability:26, code:"DG104"/* 银贝护手 */],
		[probability:26, code:"OM104"/* 龙骨念珠 */],
		[probability:26, code:"WH104"/* 巨灵锤 */],
		[probability:26, code:"WM104"/* 涤荡之杖 */],
		[probability:26, code:"WP104"/* 方天战戟 */],
		[probability:26, code:"WS104"/* 十字弩 */],
		[probability:26, code:"WS204"/* 短剑 */],
		[probability:26, code:"WT104"/* 铁标 */],
		[probability:26, code:"PL102"/* 中级恢复生命药水 */],
		[probability:26, code:"PS103"/* 高级恢复耐力药水 */],
		[probability:26, code:"PM103"/* 高级恢复魔法药水 */],
		[probability:26, code:"OS103"/* 海精石 */],
		[probability:26, code:"OR104"/* 玉戒指 */],
		[probability:26, code:"OA203"/* 倒刃臂环 */],
		[probability:26, code:"GP103"/* 浮灵水晶 */],
		[probability:11, code:"DA205"/* 战斗袍 */],
		[probability:11, code:"DA105"/* 鱼鳞甲 */],
		[probability:11, code:"DB105"/* 百兽靴 */],
		[probability:11, code:"DG105"/* 钢指护手 */],
		[probability:11, code:"DG105"/* 钢指护手 */],
		[probability:11, code:"OM105"/* 龙额念珠 */],
		[probability:11, code:"WH105"/* 破天锤 */],
		[probability:11, code:"WM105"/* 旋风之杖 */],
		[probability:11, code:"WP105"/* 鸠牙战镰 */],
		[probability:11, code:"WS105"/* 战弓 */],
		[probability:11, code:"WS205"/* 长剑 */],
		[probability:11, code:"WT105"/* 双刺标 */],
		[probability:11, code:"PL103"/* 高级恢复生命药水 */],
		[probability:11, code:"PS103"/* 高级恢复耐力药水 */],
		[probability:11, code:"OS103"/* 海精石 */],
		[probability:11, code:"OS104"/* 天仪石 */],
		[probability:11, code:"OR104"/* 玉戒指 */],
		[probability:11, code:"OA203"/* 倒刃臂环 */]
	],
	drops_more:[
	]
]
