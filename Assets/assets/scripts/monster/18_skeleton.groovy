monster = [
	ID:'18_skeleton',
	Name:'红骷髅',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:18,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:210,
		// Attack Status
		Atk:[12, 18],
		AtkSpeed:7,
		Range:45,
		Rating:380,

		// Defence Status
		Flee:80,
		Absorb:6,
		Block:0,
		DamageStunPers:100,

		// Resistance
		Earth:20,
		Fire:20,
		Ice:10,// Water
		Lighting:-10,// Wind
		Poison:100,
	],

	// AI
	AI:[
		Nature:0x80,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:6,
		Real_Sight:350,
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
		ClassCode:46,
		ArrowPosi:[-3, 30],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:1,
		Model:'char/monster/Monskeleton/MonSkeleton-a.INI',
		Sound:0x00001050,
	],

	// Drops
	AllSeeItem:false,
	Exp:1100,
	Quantity:1,
	drops:[
		[probability:3400, code:"NULL"/* Drops nothing */],
		[probability:3150, code:"GG101", value:[30, 60]/* Gold */],
		[probability:533, code:"PL101"/* 低级复生命药水 */],
		[probability:533, code:"PS101"/* 低级恢复耐力药水 */],
		[probability:533, code:"PL101"/* 低级复生命药水 */],
		[probability:58, code:"WH103"/* 长锤 */],
		[probability:58, code:"WM103"/* 暗杀之杖 */],
		[probability:58, code:"WP104"/* 方天战戟 */],
		[probability:58, code:"WS103"/* 手弩 */],
		[probability:58, code:"WS203"/* 断剑 */],
		[probability:58, code:"WT103"/* 长牙标 */],
		[probability:58, code:"DA203"/* 长袍 */],
		[probability:58, code:"DA103"/* 皮甲 */],
		[probability:58, code:"DS103"/* 圆盾 */],
		[probability:58, code:"DB103"/* 精钢靴 */],
		[probability:58, code:"DG103"/* 铁拳套 */],
		[probability:58, code:"OM103"/* 淬角水晶 */],
		[probability:58, code:"OA203"/* 倒刃臂环 */],
		[probability:58, code:"OR104"/* 玉戒指 */],
		[probability:58, code:"OA104"/* 金项链 */],
		[probability:58, code:"EC101"/* 回城卷 */],
		[probability:58, code:"PM102"/* 中级恢复魔法药水 */],
		[probability:34, code:"WH104"/* 巨灵锤 */],
		[probability:34, code:"WM104"/* 涤荡之杖 */],
		[probability:34, code:"WP105"/* 鸠牙战镰 */],
		[probability:34, code:"WS104"/* 十字弩 */],
		[probability:34, code:"WS204"/* 短剑 */],
		[probability:34, code:"WT104"/* 铁标 */],
		[probability:34, code:"DA204"/* 常青袍 */],
		[probability:34, code:"DA104"/* 铠甲 */],
		[probability:34, code:"DS104"/* 轻盾 */],
		[probability:34, code:"DB104"/* 精铜靴 */],
		[probability:34, code:"DG104"/* 银贝护手 */],
		[probability:34, code:"OM104"/* 龙骨念珠 */],
		[probability:34, code:"PM101"/* 低级恢复魔法药水 */],
		[probability:34, code:"PL101"/* 低级复生命药水 */],
		[probability:34, code:"PS101"/* 低级恢复耐力药水 */],
		[probability:34, code:"PM101"/* 低级恢复魔法药水 */],
		[probability:34, code:"EC101"/* 回城卷 */],
		[probability:34, code:"OR105"/* 蓝宝石戒指 */],
		[probability:34, code:"OA105"/* 梦之心链 */],
		[probability:11, code:"WH105"/* 破天锤 */],
		[probability:11, code:"WM105"/* 旋风之杖 */],
		[probability:11, code:"WP106"/* 三叉戟 */],
		[probability:11, code:"WS105"/* 战弓 */],
		[probability:11, code:"WS205"/* 长剑 */],
		[probability:11, code:"WT105"/* 双刺标 */],
		[probability:11, code:"DA205"/* 战斗袍 */],
		[probability:11, code:"DA105"/* 鱼鳞甲 */],
		[probability:11, code:"DS105"/* 罗塔盾 */],
		[probability:11, code:"DB103"/* 精钢靴 */],
		[probability:11, code:"DG105"/* 钢指护手 */],
		[probability:11, code:"OM105"/* 龙额念珠 */],
		[probability:11, code:"OA203"/* 倒刃臂环 */],
		[probability:11, code:"OR106"/* 红宝石戒指 */],
		[probability:11, code:"OA106"/* 碧云石链 */],
		[probability:11, code:"OS103"/* 海精石 */],
		[probability:11, code:"GP109"/* 神秘水晶 */]
	],
	drops_more:[
	]
]
