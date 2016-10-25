monster = [
	ID:'88_sadness',
	Name:'机械刑天',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:88,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:4100,
		// Attack Status
		Atk:[58, 68],
		AtkSpeed:8,
		Range:110,
		Rating:1300,

		// Defence Status
		Flee:780,
		Absorb:20,
		Block:8,
		DamageStunPers:100,

		// Resistance
		Earth:100,
		Fire:100,
		Ice:20,// Water
		Lighting:0,// Wind
		Poison:100,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:9,
		Real_Sight:390,
		Talks:[],

		// Move Behavier
		Move_Speed:1,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:15,
		SkillDamage:[70, 80],
		SkillDistance:80,
		SkillRange:55,
		SkillRating:18,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:0,
		PotionCount:0,
	],

	Looks:[
		ClassCode:40,
		ArrowPosi:[-10, 150],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:-1,
		Model:'char/monster/Sadness/sadness.ini',
		Sound:0x00001590,
	],

	// Drops
	AllSeeItem:false,
	Exp:49500,
	Quantity:1,
	QuestItem:0x07010D00,
	drops:[
		[probability:800, code:"NULL"/* Drops nothing */],
		[probability:4000, code:"GG101", value:[430, 500]/* Gold */],
		[probability:760, code:"PL104"/* 顶级恢复生命药水 */],
		[probability:760, code:"PS104"/* 顶级恢复耐力药水 */],
		[probability:760, code:"PM104"/* 顶级恢复魔法药水 */],
		[probability:760, code:"PL104"/* 顶级恢复生命药水 */],
		[probability:760, code:"PM104"/* 顶级恢复魔法药水 */],
		[probability:41, code:"DA112"/* 战神宝铠 */],
		[probability:41, code:"DA212"/* 红莲战袍 */],
		[probability:41, code:"WA110"/* 定神斧 */],
		[probability:41, code:"WC110"/* 九头刺蛇爪 */],
		[probability:41, code:"WH111"/* 赤冥之锤 */],
		[probability:41, code:"WM111"/* 王者杖 */],
		[probability:41, code:"WP111"/* 屠龙枪 */],
		[probability:41, code:"WS112"/* 人马之辉 */],
		[probability:41, code:"WS212"/* 嗜血屠魔剑 */],
		[probability:41, code:"WT111"/* 神标 */],
		[probability:41, code:"DB110"/* 地火战靴 */],
		[probability:41, code:"DS110"/* 宙斯盾 */],
		[probability:41, code:"OM111"/* 淬火乌晶 */],
		[probability:41, code:"DG110"/* 火云护手 */],
		[probability:41, code:"OA210"/* 百川流水臂环 */],
		[probability:41, code:"OR113"/* 灵魂之戒 */],
		[probability:41, code:"OA113"/* 生命之链 */],
		[probability:41, code:"EC105"/* 公会卷轴 */],
		[probability:21, code:"DA113"/* 虎刹魔铠 */],
		[probability:21, code:"DA213"/* 幽绿之眼 */],
		[probability:21, code:"WA111"/* 天阙斧 */],
		[probability:21, code:"WC111"/* 利维坦 */],
		[probability:21, code:"WH112"/* 碎星锤 */],
		[probability:21, code:"WM112"/* 审判之杖 */],
		[probability:21, code:"WP112"/* 傲天枪 */],
		[probability:21, code:"WS213"/* 双截刃 */],
		[probability:21, code:"WS113"/* 猛犸巨弩 */],
		[probability:21, code:"WT112"/* 鸩尾标 */],
		[probability:21, code:"DB111"/* 圣靴 */],
		[probability:21, code:"DS111"/* 苍穹之盾 */],
		[probability:21, code:"OM112"/* 菱晶石 */],
		[probability:21, code:"DG111"/* 黄铜护手 */],
		[probability:21, code:"OA211"/* 玄铁臂环 */],
		[probability:21, code:"OR114"/* 帝王之戒 */],
		[probability:21, code:"OA114"/* 神之庇护 */],
		[probability:21, code:"OS107"/* 水晶石 */],
		[probability:21, code:"GP110"/* 守护圣徒水晶 */],
		[probability:10, code:"DA114"/* 星晨宝铠 */],
		[probability:10, code:"DA214"/* 绯红之眼 */],
		[probability:10, code:"WA112"/* 奥丁斧 */],
		[probability:10, code:"WC112"/* 飞龙爪 */],
		[probability:10, code:"WH113"/* 破日锤 */],
		[probability:10, code:"WM113"/* 魔蜓杖 */],
		[probability:10, code:"WP113"/* 冥河战镰 */],
		[probability:10, code:"WS214"/* 金刚伏魔剑 */],
		[probability:10, code:"WS114"/* 爱神之翼 */],
		[probability:10, code:"WT113"/* 魔龙标 */],
		[probability:10, code:"DB112"/* 破棘之靴 */],
		[probability:10, code:"DS112"/* 暗黑盾 */],
		[probability:10, code:"OM113"/* 西法路 */],
		[probability:10, code:"DG112"/* 巨灵护手 */],
		[probability:10, code:"OA212"/* 紫焰臂环 */],
		[probability:10, code:"OR114"/* 帝王之戒 */],
		[probability:10, code:"OA114"/* 神之庇护 */],
		[probability:10, code:"OS108"/* 虎翼石 */],
		[probability:10, code:"GP110"/* 守护圣徒水晶 */],
		[probability:2, code:"DA115"/* 泰坦战铠 */],
		[probability:2, code:"DA215"/* 文章法袍 */],
		[probability:2, code:"WA113"/* 蝶花霹雳斧 */],
		[probability:2, code:"WC113"/* 魔星爪 */],
		[probability:2, code:"WH114"/* 鬼眼锤 */],
		[probability:2, code:"WM114"/* 混沌之杖 */],
		[probability:2, code:"WP114"/* 龙翼枪 */],
		[probability:2, code:"WS215"/* 诅咒之剑 */],
		[probability:2, code:"WS115"/* 精灵之翼 */],
		[probability:2, code:"WT114"/* 追月标 */],
		[probability:2, code:"DB113"/* 遁地靴 */],
		[probability:2, code:"DS113"/* 龙纹盾 */],
		[probability:2, code:"OM114"/* 堕天 */],
		[probability:2, code:"DG113"/* 鲲鹏护手 */],
		[probability:2, code:"OA213"/* 璇彩臂环 */],
		[probability:2, code:"OR114"/* 帝王之戒 */],
		[probability:2, code:"OA114"/* 神之庇护 */],
		[probability:2, code:"OS108"/* 虎翼石 */],
		[probability:2, code:"GP113"/* 铁甲狂魔水晶 */]
	],
	drops_more:[
		[probability:400, code:"DA112"/* 战神宝铠 */]	]
]
