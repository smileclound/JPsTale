monster = [
	ID:'100_ratoo',
	Name:'鲁图怪',
	Clazz:0,// 1 BOSS
	Brood:0x92,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:100,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:3000,
		// Attack Status
		Atk:[60, 75],
		AtkSpeed:7,
		Range:110,
		Rating:1300,

		// Defence Status
		Flee:920,
		Absorb:20,
		Block:10,
		DamageStunPers:70,

		// Resistance
		Earth:80,
		Fire:50,
		Ice:50,// Water
		Lighting:20,// Wind
		Poison:50,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:8,
		Real_Sight:440,
		Talks:[],

		// Move Behavier
		Move_Speed:4,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:16,
		SkillDamage:[80, 90],
		SkillDistance:110,
		SkillRange:70,
		SkillRating:16,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:0,
		PotionCount:0,
	],

	Looks:[
		ClassCode:10,
		ArrowPosi:[-6, 50],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:3,
		Model:'char/monster/Monratoo/Monaratoo.ini',
		Sound:0x00001340,
	],

	// Drops
	AllSeeItem:false,
	Exp:61000,
	Quantity:1,
	drops:[
		[probability:3000, code:"NULL"/* Drops nothing */],
		[probability:4050, code:"GG101", value:[350, 550]/* Gold */],
		[probability:117, code:"DA112"/* 战神宝铠 */],
		[probability:117, code:"DA212"/* 红莲战袍 */],
		[probability:117, code:"WA110"/* 定神斧 */],
		[probability:117, code:"WC110"/* 九头刺蛇爪 */],
		[probability:117, code:"WH111"/* 赤冥之锤 */],
		[probability:117, code:"WM111"/* 王者杖 */],
		[probability:117, code:"WP111"/* 屠龙枪 */],
		[probability:117, code:"WS112"/* 人马之辉 */],
		[probability:117, code:"WS213"/* 双截刃 */],
		[probability:117, code:"WT111"/* 神标 */],
		[probability:117, code:"DB110"/* 地火战靴 */],
		[probability:117, code:"DS110"/* 宙斯盾 */],
		[probability:117, code:"OM111"/* 淬火乌晶 */],
		[probability:117, code:"DG110"/* 火云护手 */],
		[probability:117, code:"OA210"/* 百川流水臂环 */],
		[probability:117, code:"OR112"/* 王者戒指 */],
		[probability:117, code:"OA112"/* 魔龙之心 */],
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
		[probability:35, code:"OS107"/* 水晶石 */],
		[probability:12, code:"DA114"/* 星晨宝铠 */],
		[probability:12, code:"DA214"/* 绯红之眼 */],
		[probability:12, code:"WA112"/* 奥丁斧 */],
		[probability:12, code:"WC112"/* 飞龙爪 */],
		[probability:12, code:"WH113"/* 破日锤 */],
		[probability:12, code:"WM113"/* 魔蜓杖 */],
		[probability:12, code:"WP113"/* 冥河战镰 */],
		[probability:12, code:"WS114"/* 爱神之翼 */],
		[probability:12, code:"WS215"/* 诅咒之剑 */],
		[probability:12, code:"WT113"/* 魔龙标 */],
		[probability:12, code:"DB112"/* 破棘之靴 */],
		[probability:12, code:"DS112"/* 暗黑盾 */],
		[probability:12, code:"OM113"/* 西法路 */],
		[probability:12, code:"DG112"/* 巨灵护手 */],
		[probability:12, code:"OA212"/* 紫焰臂环 */],
		[probability:12, code:"OR114"/* 帝王之戒 */],
		[probability:12, code:"OA114"/* 神之庇护 */],
		[probability:12, code:"OS107"/* 水晶石 */],
		[probability:12, code:"OS108"/* 虎翼石 */],
		[probability:12, code:"GP112"/* 鬼影魔神水晶 */],
		[probability:3, code:"DA115"/* 泰坦战铠 */],
		[probability:3, code:"DA215"/* 文章法袍 */],
		[probability:3, code:"WA113"/* 蝶花霹雳斧 */],
		[probability:3, code:"WC113"/* 魔星爪 */],
		[probability:3, code:"WH114"/* 鬼眼锤 */],
		[probability:3, code:"WM114"/* 混沌之杖 */],
		[probability:3, code:"WP114"/* 龙翼枪 */],
		[probability:3, code:"WS115"/* 精灵之翼 */],
		[probability:3, code:"WS216"/* 破军 */],
		[probability:3, code:"WT114"/* 追月标 */],
		[probability:3, code:"DB113"/* 遁地靴 */],
		[probability:3, code:"DS113"/* 龙纹盾 */],
		[probability:3, code:"OM114"/* 堕天 */],
		[probability:3, code:"DG113"/* 鲲鹏护手 */],
		[probability:3, code:"OA213"/* 璇彩臂环 */],
		[probability:3, code:"OR115"/* 守护之戒 */],
		[probability:3, code:"OA115"/* 暗印护符 */],
		[probability:3, code:"OS108"/* 虎翼石 */],
		[probability:3, code:"GP112"/* 鬼影魔神水晶 */],
		[probability:3, code:"GP110"/* 守护圣徒水晶 */]
	],
	drops_more:[
	]
]
