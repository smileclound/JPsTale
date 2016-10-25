monster = [
	ID:'12_mutantplant',
	Name:'变异植物',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:12,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:105,
		// Attack Status
		Atk:[6, 13],
		AtkSpeed:7,
		Range:45,
		Rating:300,

		// Defence Status
		Flee:44,
		Absorb:7,
		Block:0,
		DamageStunPers:100,

		// Resistance
		Earth:10,
		Fire:0,
		Ice:0,// Water
		Lighting:0,// Wind
		Poison:50,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:6,
		Real_Sight:330,
		Talks:[],

		// Move Behavier
		Move_Speed:3,
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
		ArrowPosi:[2, 44],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:1,
		Model:'char/monster/Monrafra/Monrafra.ini',
		Sound:0x000010D0,
	],

	// Drops
	AllSeeItem:false,
	Exp:550,
	Quantity:1,
	drops:[
		[probability:2950, code:"NULL"/* Drops nothing */],
		[probability:3500, code:"GG101", value:[20, 50]/* Gold */],
		[probability:500, code:"PL101"/* 低级复生命药水 */],
		[probability:500, code:"PL102"/* 中级恢复生命药水 */],
		[probability:500, code:"PM101"/* 低级恢复魔法药水 */],
		[probability:66, code:"DA103"/* 皮甲 */],
		[probability:66, code:"DA203"/* 长袍 */],
		[probability:66, code:"WA103"/* 板斧 */],
		[probability:66, code:"WC103"/* 鱼镰刺 */],
		[probability:66, code:"WH103"/* 长锤 */],
		[probability:66, code:"WM103"/* 暗杀之杖 */],
		[probability:66, code:"WP103"/* 鹰嘴矛 */],
		[probability:66, code:"WS103"/* 手弩 */],
		[probability:66, code:"WS203"/* 断剑 */],
		[probability:66, code:"WT103"/* 长牙标 */],
		[probability:66, code:"DS103"/* 圆盾 */],
		[probability:66, code:"PL101"/* 低级复生命药水 */],
		[probability:66, code:"PM101"/* 低级恢复魔法药水 */],
		[probability:66, code:"OR104"/* 玉戒指 */],
		[probability:66, code:"OA104"/* 金项链 */],
		[probability:33, code:"DA104"/* 铠甲 */],
		[probability:33, code:"DA204"/* 常青袍 */],
		[probability:33, code:"WA104"/* 劈地斧 */],
		[probability:33, code:"WC104"/* 蛇牙刺 */],
		[probability:33, code:"WH104"/* 巨灵锤 */],
		[probability:33, code:"WM104"/* 涤荡之杖 */],
		[probability:33, code:"WP104"/* 方天战戟 */],
		[probability:33, code:"WS104"/* 十字弩 */],
		[probability:33, code:"WS204"/* 短剑 */],
		[probability:33, code:"WT104"/* 铁标 */],
		[probability:33, code:"DS104"/* 轻盾 */],
		[probability:33, code:"PM102"/* 中级恢复魔法药水 */],
		[probability:33, code:"PS102"/* 中级恢复耐力药水 */],
		[probability:33, code:"PM102"/* 中级恢复魔法药水 */],
		[probability:33, code:"PL102"/* 中级恢复生命药水 */],
		[probability:33, code:"OR105"/* 蓝宝石戒指 */],
		[probability:33, code:"OA105"/* 梦之心链 */],
		[probability:33, code:"OS103"/* 海精石 */],
		[probability:16, code:"DA105"/* 鱼鳞甲 */],
		[probability:16, code:"DA205"/* 战斗袍 */],
		[probability:16, code:"WA105"/* 双刃战斧 */],
		[probability:16, code:"WC105"/* 玄电爪 */],
		[probability:16, code:"WH105"/* 破天锤 */],
		[probability:16, code:"WM105"/* 旋风之杖 */],
		[probability:16, code:"WP105"/* 鸠牙战镰 */],
		[probability:16, code:"WS105"/* 战弓 */],
		[probability:16, code:"WS205"/* 长剑 */],
		[probability:16, code:"WT105"/* 双刺标 */],
		[probability:16, code:"DS105"/* 罗塔盾 */],
		[probability:16, code:"PM102"/* 中级恢复魔法药水 */],
		[probability:16, code:"PS102"/* 中级恢复耐力药水 */],
		[probability:16, code:"PM102"/* 中级恢复魔法药水 */],
		[probability:16, code:"PL102"/* 中级恢复生命药水 */],
		[probability:16, code:"OR106"/* 红宝石戒指 */],
		[probability:16, code:"OA106"/* 碧云石链 */],
		[probability:16, code:"OS103"/* 海精石 */],
		[probability:25, code:"DA106"/* 索子甲 */],
		[probability:25, code:"DA206"/* 精灵袍 */],
		[probability:25, code:"DB105"/* 百兽靴 */],
		[probability:25, code:"DG105"/* 钢指护手 */],
		[probability:25, code:"OA205"/* 龙鳞臂环 */],
		[probability:25, code:"GP109"/* 神秘水晶 */]
	],
	drops_more:[
	]
]
