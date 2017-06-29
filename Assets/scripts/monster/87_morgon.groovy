monster = [
	ID:'87_morgon',
	Name:'巨斧萨满',
	Clazz:0,// 1 BOSS
	Brood:0x92,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:87,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:6000,
		// Attack Status
		Atk:[50, 60],
		AtkSpeed:8,
		Range:75,
		Rating:1330,

		// Defence Status
		Flee:860,
		Absorb:21,
		Block:6,
		DamageStunPers:100,

		// Resistance
		Earth:50,
		Fire:20,
		Ice:20,// Water
		Lighting:-100,// Wind
		Poison:50,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:9,
		Real_Sight:380,
		Talks:[],

		// Move Behavier
		Move_Speed:1,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:15,
		SkillDamage:[65, 75],
		SkillDistance:80,
		SkillRange:0,
		SkillRating:18,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:0,
		PotionCount:0,
	],

	Looks:[
		ClassCode:0,
		ArrowPosi:[-10, 150],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:-1,
		Model:'char/monster/Morgon/Morgon.ini',
		Sound:0x00001550,
	],

	// Drops
	AllSeeItem:false,
	Exp:43500,
	Quantity:1,
	drops:[
		[probability:1810, code:"NULL"/* Drops nothing */],
		[probability:4300, code:"GG101", value:[350, 500]/* Gold */],
		[probability:933, code:"PL104"/* 顶级恢复生命药水 */],
		[probability:933, code:"PM104"/* 顶级恢复魔法药水 */],
		[probability:933, code:"PM104"/* 顶级恢复魔法药水 */],
		[probability:35, code:"DA113"/* 虎刹魔铠 */],
		[probability:35, code:"DA213"/* 幽绿之眼 */],
		[probability:35, code:"WA111"/* 天阙斧 */],
		[probability:35, code:"WC111"/* 利维坦 */],
		[probability:35, code:"WH112"/* 碎星锤 */],
		[probability:35, code:"WM112"/* 审判之杖 */],
		[probability:35, code:"WP112"/* 傲天枪 */],
		[probability:35, code:"WS113"/* 猛犸巨弩 */],
		[probability:35, code:"WS214"/* 金刚伏魔剑 */],
		[probability:35, code:"WT112"/* 鸩尾标 */],
		[probability:35, code:"DB111"/* 圣靴 */],
		[probability:35, code:"DS111"/* 苍穹之盾 */],
		[probability:35, code:"OM112"/* 菱晶石 */],
		[probability:35, code:"DG111"/* 黄铜护手 */],
		[probability:35, code:"OA211"/* 玄铁臂环 */],
		[probability:35, code:"OR113"/* 灵魂之戒 */],
		[probability:35, code:"OA113"/* 生命之链 */],
		[probability:15, code:"DA114"/* 星晨宝铠 */],
		[probability:15, code:"DA214"/* 绯红之眼 */],
		[probability:15, code:"WA112"/* 奥丁斧 */],
		[probability:15, code:"WC112"/* 飞龙爪 */],
		[probability:15, code:"WH113"/* 破日锤 */],
		[probability:15, code:"WM113"/* 魔蜓杖 */],
		[probability:15, code:"WP113"/* 冥河战镰 */],
		[probability:15, code:"WS114"/* 爱神之翼 */],
		[probability:15, code:"WS215"/* 诅咒之剑 */],
		[probability:15, code:"WT113"/* 魔龙标 */],
		[probability:15, code:"DB112"/* 破棘之靴 */],
		[probability:15, code:"DS112"/* 暗黑盾 */],
		[probability:15, code:"OM113"/* 西法路 */],
		[probability:15, code:"DG112"/* 巨灵护手 */],
		[probability:15, code:"OA212"/* 紫焰臂环 */],
		[probability:15, code:"OR114"/* 帝王之戒 */],
		[probability:15, code:"OA114"/* 神之庇护 */],
		[probability:15, code:"OS107"/* 水晶石 */],
		[probability:15, code:"GP109"/* 神秘水晶 */],
		[probability:7, code:"DA115"/* 泰坦战铠 */],
		[probability:7, code:"DA215"/* 文章法袍 */],
		[probability:7, code:"WA113"/* 蝶花霹雳斧 */],
		[probability:7, code:"WC113"/* 魔星爪 */],
		[probability:7, code:"WH114"/* 鬼眼锤 */],
		[probability:7, code:"WM114"/* 混沌之杖 */],
		[probability:7, code:"WP114"/* 龙翼枪 */],
		[probability:7, code:"WS115"/* 精灵之翼 */],
		[probability:7, code:"WS216"/* 破军 */],
		[probability:7, code:"WT114"/* 追月标 */],
		[probability:7, code:"DB113"/* 遁地靴 */],
		[probability:7, code:"DS113"/* 龙纹盾 */],
		[probability:7, code:"OM114"/* 堕天 */],
		[probability:7, code:"DG113"/* 鲲鹏护手 */],
		[probability:7, code:"OA213"/* 璇彩臂环 */],
		[probability:7, code:"OR115"/* 守护之戒 */],
		[probability:7, code:"OA115"/* 暗印护符 */],
		[probability:7, code:"OS107"/* 水晶石 */],
		[probability:7, code:"OS108"/* 虎翼石 */],
		[probability:7, code:"GP109"/* 神秘水晶 */],
		[probability:7, code:"GP110"/* 守护圣徒水晶 */],
		[probability:2, code:"DA116"/* 暗黑铠 */],
		[probability:2, code:"DA216"/* 祝福法袍 */],
		[probability:2, code:"WA114"/* 战神之刃 */],
		[probability:2, code:"WC114"/* 天狼爪 */],
		[probability:2, code:"WH115"/* 雷公槌 */],
		[probability:2, code:"WM115"/* 亡灵刺 */],
		[probability:2, code:"WP115"/* 狂暴之枪 */],
		[probability:2, code:"WS116"/* 血精灵 */],
		[probability:2, code:"WS217"/* 鬼切 */],
		[probability:2, code:"WT115"/* 惊鸿 */],
		[probability:2, code:"DB114"/* 鹏翅之靴 */],
		[probability:2, code:"DS114"/* 泰坦之盾 */],
		[probability:2, code:"OM115"/* 炫彩水晶 */],
		[probability:2, code:"DG114"/* 金刚护手 */],
		[probability:2, code:"OA214"/* 金刚臂环 */],
		[probability:2, code:"OR115"/* 守护之戒 */],
		[probability:2, code:"OA115"/* 暗印护符 */],
		[probability:2, code:"OS108"/* 虎翼石 */],
		[probability:2, code:"GP109"/* 神秘水晶 */],
		[probability:2, code:"GP110"/* 守护圣徒水晶 */]
	],
	drops_more:[
		[probability:800, code:"DA113"/* 虎刹魔铠 */]	]
]
