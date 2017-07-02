monster = [
	ID:'a1-bargon',
	Name:'绿色刀斧',
	Clazz:0,// 1 BOSS
	Brood:0x92,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:40,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:480,
		// Attack Status
		Atk:[22, 28],
		AtkSpeed:6,
		Range:70,
		Rating:700,

		// Defence Status
		Flee:250,
		Absorb:8,
		Block:6,
		DamageStunPers:100,

		// Resistance
		Earth:0,
		Fire:-10,
		Ice:10,// Water
		Lighting:20,// Wind
		Poison:50,
	],

	// AI
	AI:[
		Nature:0x80,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:7,
		Real_Sight:380,
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
		ClassCode:1,
		ArrowPosi:[0, 10],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:3,
		Model:'char/monster/Monbagon/Monbagon-A.ini',
		Sound:0x00001090,
	],

	// Drops
	AllSeeItem:false,
	Exp:1400,
	Quantity:1,
	drops:[
		[probability:3890, code:"NULL"/* Drops nothing */],
		[probability:4800, code:"GG101", value:[40, 55]/* Gold */],
		[probability:50, code:"WA105"/* 双刃战斧 */],
		[probability:50, code:"WC105"/* 玄电爪 */],
		[probability:50, code:"WH106"/* 浑元金锤 */],
		[probability:50, code:"WM106"/* 抗拒之杖 */],
		[probability:50, code:"WP106"/* 三叉戟 */],
		[probability:50, code:"WS107"/* 射日弓 */],
		[probability:50, code:"WS208"/* 圣殿武士剑 */],
		[probability:50, code:"WT106"/* 精灵标 */],
		[probability:50, code:"DA107"/* 全钢胸铠 */],
		[probability:50, code:"DA207"/* 紫电袍 */],
		[probability:50, code:"DS106"/* 百炼盾 */],
		[probability:50, code:"OM107"/* 火星 */],
		[probability:21, code:"WA107"/* 刺脊斧 */],
		[probability:21, code:"WC107"/* 平手刃 */],
		[probability:21, code:"WH108"/* 圣光锤 */],
		[probability:21, code:"WM108"/* 公正之杖 */],
		[probability:21, code:"WP108"/* 血烟长矛 */],
		[probability:21, code:"WS109"/* 点金手弩 */],
		[probability:21, code:"WS209"/* 镇妖剑 */],
		[probability:21, code:"WT108"/* 金标 */],
		[probability:21, code:"DA108"/* 精制链铠 */],
		[probability:21, code:"DA208"/* 圣女袍 */],
		[probability:21, code:"DS107"/* 金刚盾 */],
		[probability:21, code:"OM108"/* 阳炎 */],
		[probability:10, code:"WA108"/* 叼刚 清磐 */],
		[probability:10, code:"WC108"/* 矩记飘 府亥瘤 */],
		[probability:10, code:"WH109"/* 固萍绢扼捞飘 */],
		[probability:10, code:"WM109"/* 绊胶飘 */],
		[probability:10, code:"WP109"/* 单喉 荤捞靛 */],
		[probability:10, code:"WS110"/* 固胶飘 */],
		[probability:10, code:"WS211"/* 斩马刀 */],
		[probability:10, code:"WT109"/* 酒唱能促 */],
		[probability:10, code:"DA109"/* 黄铜战铠 */],
		[probability:10, code:"DA209"/* 学徒披风 */],
		[probability:10, code:"DS108"/* 赤龙焰盾 */],
		[probability:10, code:"OM110"/* 蓝色星辰 */],
		[probability:41, code:"DB110"/* 地火战靴 */],
		[probability:41, code:"DG109"/* 神力护手 */],
		[probability:41, code:"OA208"/* 百炼臂环 */],
		[probability:41, code:"DB114"/* 鹏翅之靴 */],
		[probability:41, code:"DG111"/* 黄铜护手 */],
		[probability:41, code:"OA209"/* 飞翼臂环 */],
		[probability:7, code:"OA106"/* 碧云石链 */],
		[probability:7, code:"OR108"/* 翡翠戒指 */],
		[probability:7, code:"OA107"/* 水晶项链 */],
		[probability:7, code:"OR109"/* 黑暗之戒 */],
		[probability:7, code:"OA108"/* 靛青石链 */],
		[probability:7, code:"OS101"/* 星遗石 */],
		[probability:7, code:"OS102"/* 流云石 */],
		[probability:7, code:"OS103"/* 海精石 */],
		[probability:7, code:"OS104"/* 天仪石 */]
	],
	drops_more:[
	]
]
