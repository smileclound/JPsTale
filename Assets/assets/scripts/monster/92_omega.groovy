monster = [
	ID:'92_omega',
	Name:'神秘十八',
	Clazz:0,// 1 BOSS
	Brood:0x92,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:88,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:3000,
		// Attack Status
		Atk:[50, 63],
		AtkSpeed:9,
		Range:80,
		Rating:1450,

		// Defence Status
		Flee:720,
		Absorb:20,
		Block:12,
		DamageStunPers:60,

		// Resistance
		Earth:90,
		Fire:10,
		Ice:40,// Water
		Lighting:20,// Wind
		Poison:40,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:9,
		Real_Sight:400,
		Talks:[],

		// Move Behavier
		Move_Speed:4,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:20,
		SkillDamage:[65, 75],
		SkillDistance:0,
		SkillRange:130,
		SkillRating:17,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:0,
		PotionCount:0,
	],

	Looks:[
		ClassCode:13,
		ArrowPosi:[0, 25],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:-1,
		Model:'char/monster/Monomicron-1/Monomicron-1.ini',
		Sound:0x00001360,
	],

	// Drops
	AllSeeItem:false,
	Exp:60000,
	Quantity:1,
	drops:[
		[probability:1100, code:"NULL"/* Drops nothing */],
		[probability:4060, code:"GG101", value:[350, 550]/* Gold */],
		[probability:666, code:"PL104"/* 顶级恢复生命药水 */],
		[probability:666, code:"PM104"/* 顶级恢复魔法药水 */],
		[probability:666, code:"PM104"/* 顶级恢复魔法药水 */],
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
		[probability:33, code:"DA113"/* 虎刹魔铠 */],
		[probability:33, code:"DA213"/* 幽绿之眼 */],
		[probability:33, code:"WA111"/* 天阙斧 */],
		[probability:33, code:"WC111"/* 利维坦 */],
		[probability:33, code:"WH112"/* 碎星锤 */],
		[probability:33, code:"WM112"/* 审判之杖 */],
		[probability:33, code:"WP112"/* 傲天枪 */],
		[probability:33, code:"WS113"/* 猛犸巨弩 */],
		[probability:33, code:"WS214"/* 金刚伏魔剑 */],
		[probability:33, code:"WT112"/* 鸩尾标 */],
		[probability:33, code:"DB111"/* 圣靴 */],
		[probability:33, code:"DS111"/* 苍穹之盾 */],
		[probability:33, code:"OM112"/* 菱晶石 */],
		[probability:33, code:"DG111"/* 黄铜护手 */],
		[probability:33, code:"OA211"/* 玄铁臂环 */],
		[probability:33, code:"OR113"/* 灵魂之戒 */],
		[probability:33, code:"OA113"/* 生命之链 */],
		[probability:33, code:"OS107"/* 水晶石 */],
		[probability:10, code:"DA114"/* 星晨宝铠 */],
		[probability:10, code:"DA214"/* 绯红之眼 */],
		[probability:10, code:"WA112"/* 奥丁斧 */],
		[probability:10, code:"WC112"/* 飞龙爪 */],
		[probability:10, code:"WH113"/* 破日锤 */],
		[probability:10, code:"WM113"/* 魔蜓杖 */],
		[probability:10, code:"WP113"/* 冥河战镰 */],
		[probability:10, code:"WS114"/* 爱神之翼 */],
		[probability:10, code:"WS215"/* 诅咒之剑 */],
		[probability:10, code:"WT113"/* 魔龙标 */],
		[probability:10, code:"DB112"/* 破棘之靴 */],
		[probability:10, code:"DS112"/* 暗黑盾 */],
		[probability:10, code:"OM113"/* 西法路 */],
		[probability:10, code:"DG112"/* 巨灵护手 */],
		[probability:10, code:"OA212"/* 紫焰臂环 */],
		[probability:10, code:"OR114"/* 帝王之戒 */],
		[probability:10, code:"OA114"/* 神之庇护 */],
		[probability:10, code:"OS107"/* 水晶石 */],
		[probability:10, code:"OS108"/* 虎翼石 */],
		[probability:10, code:"GP109"/* 神秘水晶 */],
		[probability:2, code:"DA115"/* 泰坦战铠 */],
		[probability:2, code:"DA215"/* 文章法袍 */],
		[probability:2, code:"WA113"/* 蝶花霹雳斧 */],
		[probability:2, code:"WC113"/* 魔星爪 */],
		[probability:2, code:"WH114"/* 鬼眼锤 */],
		[probability:2, code:"WM114"/* 混沌之杖 */],
		[probability:2, code:"WP114"/* 龙翼枪 */],
		[probability:2, code:"WS115"/* 精灵之翼 */],
		[probability:2, code:"WS216"/* 破军 */],
		[probability:2, code:"WT114"/* 追月标 */],
		[probability:2, code:"DB113"/* 遁地靴 */],
		[probability:2, code:"DS113"/* 龙纹盾 */],
		[probability:2, code:"OM114"/* 堕天 */],
		[probability:2, code:"DG113"/* 鲲鹏护手 */],
		[probability:2, code:"OA213"/* 璇彩臂环 */],
		[probability:2, code:"OR115"/* 守护之戒 */],
		[probability:2, code:"OA115"/* 暗印护符 */],
		[probability:2, code:"OS108"/* 虎翼石 */],
		[probability:2, code:"GP109"/* 神秘水晶 */],
		[probability:2, code:"GP110"/* 守护圣徒水晶 */]
	],
	drops_more:[
		[probability:1500, code:"WA110"/* 定神斧 */]	]
]
