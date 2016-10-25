monster = [
	ID:'4_hopykid',
	Name:'龋乔 虐靛',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:4,
	ActiveHour:0,
	RespawnGroup:[2, 2],

	Attributes:[
		Life:15,
		// Attack Status
		Atk:[1, 2],
		AtkSpeed:8,
		Range:30,
		Rating:170,

		// Defence Status
		Flee:10,
		Absorb:0,
		Block:0,
		DamageStunPers:100,

		// Resistance
		Earth:0,
		Fire:10,
		Ice:5,// Water
		Lighting:0,// Wind
		Poison:10,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:4,
		Real_Sight:300,
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
		ArrowPosi:[8, 25],
		ModelSize:0.6,
		UseEventModel:false,
		SizeLevel:0,
		Model:'char/monster/Hopy/hopy.INI',
		Sound:0x00001080,
	],

	// Drops
	AllSeeItem:false,
	Exp:20,
	Quantity:1,
	drops:[
		[probability:0, code:"NULL"/* Drops nothing */],
		[probability:8000, code:"GG101", value:[800, 1200]/* Gold */],
		[probability:83, code:"WA102"/* 铁斧 */],
		[probability:83, code:"WC102"/* 虎爪 */],
		[probability:83, code:"WH103"/* 长锤 */],
		[probability:83, code:"WM103"/* 暗杀之杖 */],
		[probability:83, code:"WP102"/* 点钢蛇矛 */],
		[probability:83, code:"WS103"/* 手弩 */],
		[probability:83, code:"WS205"/* 长剑 */],
		[probability:83, code:"WT103"/* 长牙标 */],
		[probability:83, code:"DA104"/* 铠甲 */],
		[probability:83, code:"DA204"/* 常青袍 */],
		[probability:83, code:"DS102"/* 钉盾 */],
		[probability:83, code:"OM101"/* 念珠 */],
		[probability:33, code:"WA103"/* 板斧 */],
		[probability:33, code:"WC103"/* 鱼镰刺 */],
		[probability:33, code:"WH104"/* 巨灵锤 */],
		[probability:33, code:"WM104"/* 涤荡之杖 */],
		[probability:33, code:"WP104"/* 方天战戟 */],
		[probability:33, code:"WS104"/* 十字弩 */],
		[probability:33, code:"WS206"/* 阔剑 */],
		[probability:33, code:"WT104"/* 铁标 */],
		[probability:33, code:"DA105"/* 鱼鳞甲 */],
		[probability:33, code:"DA205"/* 战斗袍 */],
		[probability:33, code:"DS103"/* 圆盾 */],
		[probability:33, code:"OM104"/* 龙骨念珠 */],
		[probability:16, code:"WA104"/* 劈地斧 */],
		[probability:16, code:"WC104"/* 蛇牙刺 */],
		[probability:16, code:"WH105"/* 破天锤 */],
		[probability:16, code:"WM105"/* 旋风之杖 */],
		[probability:16, code:"WP105"/* 鸠牙战镰 */],
		[probability:16, code:"WS105"/* 战弓 */],
		[probability:16, code:"WS207"/* 长刀 */],
		[probability:16, code:"WT105"/* 双刺标 */],
		[probability:16, code:"DA106"/* 索子甲 */],
		[probability:16, code:"DA206"/* 精灵袍 */],
		[probability:16, code:"DS104"/* 轻盾 */],
		[probability:16, code:"OM105"/* 龙额念珠 */],
		[probability:33, code:"DB102"/* 精灵靴 */],
		[probability:33, code:"DG102"/* 拳套 */],
		[probability:33, code:"OA202"/* 精铁臂环 */],
		[probability:33, code:"DB103"/* 精钢靴 */],
		[probability:33, code:"DG103"/* 铁拳套 */],
		[probability:33, code:"OA203"/* 倒刃臂环 */],
		[probability:33, code:"DB104"/* 精铜靴 */],
		[probability:33, code:"DG104"/* 银贝护手 */],
		[probability:33, code:"OA204"/* 大力臂环 */],
		[probability:20, code:"OR104"/* 玉戒指 */],
		[probability:20, code:"OA103"/* 多情环 */],
		[probability:20, code:"OR105"/* 蓝宝石戒指 */],
		[probability:20, code:"OS101"/* 星遗石 */],
		[probability:20, code:"OS102"/* 流云石 */]
	],
	drops_more:[
	]
]
