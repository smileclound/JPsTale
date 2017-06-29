monster = [
	ID:'81_ironguard',
	Name:'铁甲狂魔',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:81,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:3200,
		// Attack Status
		Atk:[50, 65],
		AtkSpeed:8,
		Range:80,
		Rating:1200,

		// Defence Status
		Flee:740,
		Absorb:21,
		Block:0,
		DamageStunPers:70,

		// Resistance
		Earth:100,
		Fire:50,
		Ice:100,// Water
		Lighting:0,// Wind
		Poison:20,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:8,
		Real_Sight:360,
		Talks:[],

		// Move Behavier
		Move_Speed:4,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:30,
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
		ClassCode:24,
		ArrowPosi:[-9, 45],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:-1,
		Model:'char/monster/MonIgad/MonIgad.ini',
		Sound:0x00001300,
	],

	// Drops
	AllSeeItem:false,
	Exp:38500,
	Quantity:1,
	drops:[
		[probability:2145, code:"NULL"/* Drops nothing */],
		[probability:3000, code:"GG101", value:[190, 240]/* Gold */],
		[probability:680, code:"PL104"/* 顶级恢复生命药水 */],
		[probability:680, code:"PL104"/* 顶级恢复生命药水 */],
		[probability:680, code:"PS104"/* 顶级恢复耐力药水 */],
		[probability:680, code:"PM104"/* 顶级恢复魔法药水 */],
		[probability:680, code:"PM104"/* 顶级恢复魔法药水 */],
		[probability:44, code:"DA111"/* 重装机铠 */],
		[probability:44, code:"DA211"/* 大法师袍 */],
		[probability:44, code:"WA109"/* 破山斧 */],
		[probability:44, code:"WC109"/* 兽之斧刃 */],
		[probability:44, code:"WH110"/* 轩辕巨锤 */],
		[probability:44, code:"WM110"/* 圣者杖 */],
		[probability:44, code:"WP110"/* 白银之枪 */],
		[probability:44, code:"WS111"/* 龙骨战弓 */],
		[probability:44, code:"WS211"/* 斩马刀 */],
		[probability:44, code:"WT110"/* 飞云标 */],
		[probability:44, code:"DB108"/* 百战靴 */],
		[probability:44, code:"DS108"/* 赤龙焰盾 */],
		[probability:44, code:"OM110"/* 蓝色星辰 */],
		[probability:44, code:"DG108"/* 大地护手 */],
		[probability:44, code:"OA208"/* 百炼臂环 */],
		[probability:44, code:"OR111"/* 封印之戒 */],
		[probability:44, code:"OA111"/* 圣者之链 */],
		[probability:44, code:"EC105"/* 公会卷轴 */],
		[probability:21, code:"DA112"/* 战神宝铠 */],
		[probability:21, code:"DA212"/* 红莲战袍 */],
		[probability:21, code:"WA110"/* 定神斧 */],
		[probability:21, code:"WC110"/* 九头刺蛇爪 */],
		[probability:21, code:"WH111"/* 赤冥之锤 */],
		[probability:21, code:"WM111"/* 王者杖 */],
		[probability:21, code:"WP111"/* 屠龙枪 */],
		[probability:21, code:"WS112"/* 人马之辉 */],
		[probability:21, code:"WS212"/* 嗜血屠魔剑 */],
		[probability:21, code:"WT111"/* 神标 */],
		[probability:21, code:"DB109"/* 大地靴 */],
		[probability:21, code:"DS109"/* 圣盾 */],
		[probability:21, code:"OM111"/* 淬火乌晶 */],
		[probability:21, code:"DG109"/* 神力护手 */],
		[probability:21, code:"OA209"/* 飞翼臂环 */],
		[probability:21, code:"OR112"/* 王者戒指 */],
		[probability:21, code:"OA112"/* 魔龙之心 */],
		[probability:21, code:"OS106"/* 玄风石 */],
		[probability:21, code:"EC105"/* 公会卷轴 */],
		[probability:9, code:"DA113"/* 虎刹魔铠 */],
		[probability:9, code:"DA213"/* 幽绿之眼 */],
		[probability:9, code:"WA111"/* 天阙斧 */],
		[probability:9, code:"WC111"/* 利维坦 */],
		[probability:9, code:"WH112"/* 碎星锤 */],
		[probability:9, code:"WM112"/* 审判之杖 */],
		[probability:9, code:"WP112"/* 傲天枪 */],
		[probability:9, code:"WS113"/* 猛犸巨弩 */],
		[probability:9, code:"WS213"/* 双截刃 */],
		[probability:9, code:"WT112"/* 鸩尾标 */],
		[probability:9, code:"DB110"/* 地火战靴 */],
		[probability:9, code:"DS110"/* 宙斯盾 */],
		[probability:9, code:"OM112"/* 菱晶石 */],
		[probability:9, code:"DG110"/* 火云护手 */],
		[probability:9, code:"OA210"/* 百川流水臂环 */],
		[probability:9, code:"EC102"/* 回城卷 */],
		[probability:9, code:"OR113"/* 灵魂之戒 */],
		[probability:9, code:"OA113"/* 生命之链 */],
		[probability:9, code:"EC102"/* 回城卷 */],
		[probability:9, code:"OS107"/* 水晶石 */],
		[probability:9, code:"OS108"/* 虎翼石 */],
		[probability:9, code:"GP113"/* 铁甲狂魔水晶 */],
		[probability:2, code:"DA114"/* 星晨宝铠 */],
		[probability:2, code:"DA214"/* 绯红之眼 */],
		[probability:2, code:"WA112"/* 奥丁斧 */],
		[probability:2, code:"WC112"/* 飞龙爪 */],
		[probability:2, code:"WH113"/* 破日锤 */],
		[probability:2, code:"WM113"/* 魔蜓杖 */],
		[probability:2, code:"WP113"/* 冥河战镰 */],
		[probability:2, code:"WS114"/* 爱神之翼 */],
		[probability:2, code:"WS214"/* 金刚伏魔剑 */],
		[probability:2, code:"WT113"/* 魔龙标 */],
		[probability:2, code:"DB111"/* 圣靴 */],
		[probability:2, code:"DS111"/* 苍穹之盾 */],
		[probability:2, code:"OM113"/* 西法路 */],
		[probability:2, code:"DG111"/* 黄铜护手 */],
		[probability:2, code:"OA211"/* 玄铁臂环 */],
		[probability:2, code:"EC102"/* 回城卷 */],
		[probability:2, code:"OR114"/* 帝王之戒 */],
		[probability:2, code:"OA114"/* 神之庇护 */],
		[probability:2, code:"EC102"/* 回城卷 */],
		[probability:2, code:"GP110"/* 守护圣徒水晶 */]
	],
	drops_more:[
		[probability:1000, code:"PM104"/* 顶级恢复魔法药水 */]	]
]
