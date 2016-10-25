monster = [
	ID:'82_avelin',
	Name:'魔弓人马',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:82,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:2300,
		// Attack Status
		Atk:[57, 67],
		AtkSpeed:8,
		Range:250,
		Rating:1100,

		// Defence Status
		Flee:400,
		Absorb:12,
		Block:0,
		DamageStunPers:100,

		// Resistance
		Earth:30,
		Fire:0,
		Ice:45,// Water
		Lighting:10,// Wind
		Poison:20,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:9,
		Real_Sight:360,
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
		ClassCode:25,
		ArrowPosi:[0, 30],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:-1,
		Model:'char/monster/Monavellriskbow/monavelriskbow.ini',
		Sound:0x000015E0,
	],

	// Drops
	AllSeeItem:false,
	Exp:30000,
	Quantity:1,
	drops:[
		[probability:2000, code:"NULL"/* Drops nothing */],
		[probability:5500, code:"GG101", value:[140, 170]/* Gold */],
		[probability:500, code:"PL103"/* 高级恢复生命药水 */],
		[probability:500, code:"PM103"/* 高级恢复魔法药水 */],
		[probability:42, code:"DA111"/* 重装机铠 */],
		[probability:42, code:"DA211"/* 大法师袍 */],
		[probability:42, code:"WA108"/* 叼刚 清磐 */],
		[probability:42, code:"WC108"/* 矩记飘 府亥瘤 */],
		[probability:42, code:"WH109"/* 固萍绢扼捞飘 */],
		[probability:42, code:"WM108"/* 公正之杖 */],
		[probability:42, code:"WP109"/* 单喉 荤捞靛 */],
		[probability:42, code:"WS110"/* 固胶飘 */],
		[probability:42, code:"WS210"/* 敲饭萍逞 家靛 */],
		[probability:42, code:"WT109"/* 酒唱能促 */],
		[probability:42, code:"OM109"/* 暗月 */],
		[probability:42, code:"DB108"/* 百战靴 */],
		[probability:42, code:"DS108"/* 赤龙焰盾 */],
		[probability:42, code:"DG108"/* 大地护手 */],
		[probability:42, code:"OA208"/* 百炼臂环 */],
		[probability:42, code:"PM103"/* 高级恢复魔法药水 */],
		[probability:42, code:"OR110"/* 伏魔戒指 */],
		[probability:42, code:"OA110"/* 镇魂铃 */],
		[probability:42, code:"GP106"/* 火灵王水晶 */],
		[probability:42, code:"EC105"/* 公会卷轴 */],
		[probability:18, code:"DA112"/* 战神宝铠 */],
		[probability:18, code:"DA212"/* 红莲战袍 */],
		[probability:18, code:"WA109"/* 破山斧 */],
		[probability:18, code:"WC109"/* 兽之斧刃 */],
		[probability:18, code:"WH110"/* 轩辕巨锤 */],
		[probability:18, code:"WM109"/* 绊胶飘 */],
		[probability:18, code:"WP110"/* 白银之枪 */],
		[probability:18, code:"WS111"/* 龙骨战弓 */],
		[probability:18, code:"WS211"/* 斩马刀 */],
		[probability:18, code:"WT110"/* 飞云标 */],
		[probability:18, code:"OM110"/* 蓝色星辰 */],
		[probability:18, code:"OS106"/* 玄风石 */],
		[probability:18, code:"OS107"/* 水晶石 */],
		[probability:18, code:"DB109"/* 大地靴 */],
		[probability:18, code:"DS109"/* 圣盾 */],
		[probability:18, code:"DG109"/* 神力护手 */],
		[probability:18, code:"OA209"/* 飞翼臂环 */],
		[probability:18, code:"PM104"/* 顶级恢复魔法药水 */],
		[probability:18, code:"OR111"/* 封印之戒 */],
		[probability:18, code:"OA111"/* 圣者之链 */],
		[probability:18, code:"GP106"/* 火灵王水晶 */],
		[probability:18, code:"EC105"/* 公会卷轴 */],
		[probability:9, code:"DA113"/* 虎刹魔铠 */],
		[probability:9, code:"DA213"/* 幽绿之眼 */],
		[probability:9, code:"WA110"/* 定神斧 */],
		[probability:9, code:"WC110"/* 九头刺蛇爪 */],
		[probability:9, code:"WH111"/* 赤冥之锤 */],
		[probability:9, code:"WM110"/* 圣者杖 */],
		[probability:9, code:"WP111"/* 屠龙枪 */],
		[probability:9, code:"WS112"/* 人马之辉 */],
		[probability:9, code:"WS212"/* 嗜血屠魔剑 */],
		[probability:9, code:"WT111"/* 神标 */],
		[probability:9, code:"OM111"/* 淬火乌晶 */],
		[probability:9, code:"OS106"/* 玄风石 */],
		[probability:9, code:"OS107"/* 水晶石 */],
		[probability:9, code:"DB110"/* 地火战靴 */],
		[probability:9, code:"DS110"/* 宙斯盾 */],
		[probability:9, code:"DG110"/* 火云护手 */],
		[probability:9, code:"OA210"/* 百川流水臂环 */],
		[probability:9, code:"PM104"/* 顶级恢复魔法药水 */],
		[probability:9, code:"OR111"/* 封印之戒 */],
		[probability:9, code:"OA111"/* 圣者之链 */],
		[probability:9, code:"GP107"/* 独角兽王水晶 */],
		[probability:2, code:"DA114"/* 星晨宝铠 */],
		[probability:2, code:"DA214"/* 绯红之眼 */],
		[probability:2, code:"WA111"/* 天阙斧 */],
		[probability:2, code:"WC111"/* 利维坦 */],
		[probability:2, code:"WH112"/* 碎星锤 */],
		[probability:2, code:"WM111"/* 王者杖 */],
		[probability:2, code:"WP112"/* 傲天枪 */],
		[probability:2, code:"WS113"/* 猛犸巨弩 */],
		[probability:2, code:"WS213"/* 双截刃 */],
		[probability:2, code:"WT112"/* 鸩尾标 */],
		[probability:2, code:"OM112"/* 菱晶石 */],
		[probability:2, code:"OS107"/* 水晶石 */],
		[probability:2, code:"OS107"/* 水晶石 */],
		[probability:2, code:"DB111"/* 圣靴 */],
		[probability:2, code:"DS111"/* 苍穹之盾 */],
		[probability:2, code:"DG111"/* 黄铜护手 */],
		[probability:2, code:"OA211"/* 玄铁臂环 */],
		[probability:2, code:"PM104"/* 顶级恢复魔法药水 */],
		[probability:2, code:"OR112"/* 王者戒指 */],
		[probability:2, code:"OA112"/* 魔龙之心 */],
		[probability:2, code:"GP107"/* 独角兽王水晶 */]
	],
	drops_more:[
		[probability:1000, code:"PM104"/* 顶级恢复魔法药水 */]	]
]
