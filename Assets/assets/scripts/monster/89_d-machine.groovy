monster = [
	ID:'89_d-machine',
	Name:'霸天蛛',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:89,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:3000,
		// Attack Status
		Atk:[50, 60],
		AtkSpeed:8,
		Range:180,
		Rating:1300,

		// Defence Status
		Flee:860,
		Absorb:20,
		Block:0,
		DamageStunPers:60,

		// Resistance
		Earth:100,
		Fire:80,
		Ice:70,// Water
		Lighting:0,// Wind
		Poison:70,
	],

	// AI
	AI:[
		Nature:0x80,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:8,
		Real_Sight:420,
		Talks:[],

		// Move Behavier
		Move_Speed:4,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:30,
		SkillDamage:[65, 75],
		SkillDistance:0,
		SkillRange:150,
		SkillRating:20,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:0,
		PotionCount:0,
	],

	Looks:[
		ClassCode:14,
		ArrowPosi:[-18, 70],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:-1,
		Model:'char/monster/MonDMachine/MonDMachine.ini',
		Sound:0x00001370,
	],

	// Drops
	AllSeeItem:false,
	Exp:50500,
	Quantity:1,
	drops:[
		[probability:750, code:"NULL"/* Drops nothing */],
		[probability:4100, code:"GG101", value:[430, 500]/* Gold */],
		[probability:760, code:"PL104"/* 顶级恢复生命药水 */],
		[probability:760, code:"PS104"/* 顶级恢复耐力药水 */],
		[probability:760, code:"PM104"/* 顶级恢复魔法药水 */],
		[probability:760, code:"PL104"/* 顶级恢复生命药水 */],
		[probability:760, code:"PM104"/* 顶级恢复魔法药水 */],
		[probability:38, code:"DA112"/* 战神宝铠 */],
		[probability:38, code:"DA212"/* 红莲战袍 */],
		[probability:38, code:"WA110"/* 定神斧 */],
		[probability:38, code:"WC110"/* 九头刺蛇爪 */],
		[probability:38, code:"WH111"/* 赤冥之锤 */],
		[probability:38, code:"WM111"/* 王者杖 */],
		[probability:38, code:"WP111"/* 屠龙枪 */],
		[probability:38, code:"WS112"/* 人马之辉 */],
		[probability:38, code:"WS212"/* 嗜血屠魔剑 */],
		[probability:38, code:"WT111"/* 神标 */],
		[probability:38, code:"DB110"/* 地火战靴 */],
		[probability:38, code:"DS110"/* 宙斯盾 */],
		[probability:38, code:"OM111"/* 淬火乌晶 */],
		[probability:38, code:"DG110"/* 火云护手 */],
		[probability:38, code:"OA210"/* 百川流水臂环 */],
		[probability:38, code:"OR113"/* 灵魂之戒 */],
		[probability:38, code:"OA113"/* 生命之链 */],
		[probability:38, code:"EC105"/* 公会卷轴 */],
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
		[probability:11, code:"DA114"/* 星晨宝铠 */],
		[probability:11, code:"DA214"/* 绯红之眼 */],
		[probability:11, code:"WA112"/* 奥丁斧 */],
		[probability:11, code:"WC112"/* 飞龙爪 */],
		[probability:11, code:"WH113"/* 破日锤 */],
		[probability:11, code:"WM113"/* 魔蜓杖 */],
		[probability:11, code:"WP113"/* 冥河战镰 */],
		[probability:11, code:"WS214"/* 金刚伏魔剑 */],
		[probability:11, code:"WS114"/* 爱神之翼 */],
		[probability:11, code:"WT113"/* 魔龙标 */],
		[probability:11, code:"DB112"/* 破棘之靴 */],
		[probability:11, code:"DS112"/* 暗黑盾 */],
		[probability:11, code:"OM113"/* 西法路 */],
		[probability:11, code:"DG112"/* 巨灵护手 */],
		[probability:11, code:"OA212"/* 紫焰臂环 */],
		[probability:11, code:"OR114"/* 帝王之戒 */],
		[probability:11, code:"OA114"/* 神之庇护 */],
		[probability:11, code:"GP110"/* 守护圣徒水晶 */],
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
	]
]
