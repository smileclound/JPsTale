monster = [
	ID:'83_avelinqueen',
	Name:'人马女皇',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:83,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:4200,
		// Attack Status
		Atk:[58, 67],
		AtkSpeed:7,
		Range:250,
		Rating:1200,

		// Defence Status
		Flee:540,
		Absorb:13,
		Block:0,
		DamageStunPers:100,

		// Resistance
		Earth:30,
		Fire:0,
		Ice:65,// Water
		Lighting:10,// Wind
		Poison:30,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:9,
		Real_Sight:380,
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
		ArrowPosi:[0, 30],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:-1,
		Model:'char/monster/Monavellriskstf/monavelriskstf.ini',
		Sound:0x000015F0,
	],

	// Drops
	AllSeeItem:false,
	Exp:36000,
	Quantity:1,
	drops:[
		[probability:2000, code:"NULL"/* Drops nothing */],
		[probability:5600, code:"GG101", value:[260, 320]/* Gold */],
		[probability:250, code:"PL104"/* 顶级恢复生命药水 */],
		[probability:250, code:"PS104"/* 顶级恢复耐力药水 */],
		[probability:250, code:"PL104"/* 顶级恢复生命药水 */],
		[probability:250, code:"PM104"/* 顶级恢复魔法药水 */],
		[probability:44, code:"DA110"/* 百裂铠 */],
		[probability:44, code:"DA210"/* 信徒披风 */],
		[probability:44, code:"WA109"/* 破山斧 */],
		[probability:44, code:"WC109"/* 兽之斧刃 */],
		[probability:44, code:"WH110"/* 轩辕巨锤 */],
		[probability:44, code:"WM110"/* 圣者杖 */],
		[probability:44, code:"WP110"/* 白银之枪 */],
		[probability:44, code:"WS111"/* 龙骨战弓 */],
		[probability:44, code:"WS211"/* 斩马刀 */],
		[probability:44, code:"WT110"/* 飞云标 */],
		[probability:44, code:"DB109"/* 大地靴 */],
		[probability:44, code:"DS109"/* 圣盾 */],
		[probability:44, code:"OM110"/* 蓝色星辰 */],
		[probability:44, code:"DG109"/* 神力护手 */],
		[probability:44, code:"OA209"/* 飞翼臂环 */],
		[probability:44, code:"EC102"/* 回城卷 */],
		[probability:44, code:"OR111"/* 封印之戒 */],
		[probability:44, code:"OA111"/* 圣者之链 */],
		[probability:19, code:"DA111"/* 重装机铠 */],
		[probability:19, code:"DA211"/* 大法师袍 */],
		[probability:19, code:"WA110"/* 定神斧 */],
		[probability:19, code:"WC110"/* 九头刺蛇爪 */],
		[probability:19, code:"WH111"/* 赤冥之锤 */],
		[probability:19, code:"WM111"/* 王者杖 */],
		[probability:19, code:"WP111"/* 屠龙枪 */],
		[probability:19, code:"WS112"/* 人马之辉 */],
		[probability:19, code:"WS212"/* 嗜血屠魔剑 */],
		[probability:19, code:"WT111"/* 神标 */],
		[probability:19, code:"DB110"/* 地火战靴 */],
		[probability:19, code:"DS110"/* 宙斯盾 */],
		[probability:19, code:"OM111"/* 淬火乌晶 */],
		[probability:19, code:"DG110"/* 火云护手 */],
		[probability:19, code:"OA210"/* 百川流水臂环 */],
		[probability:19, code:"OR112"/* 王者戒指 */],
		[probability:19, code:"OA112"/* 魔龙之心 */],
		[probability:19, code:"OS107"/* 水晶石 */],
		[probability:19, code:"OS107"/* 水晶石 */],
		[probability:19, code:"EC102"/* 回城卷 */],
		[probability:19, code:"GP107"/* 独角兽王水晶 */],
		[probability:7, code:"DA112"/* 战神宝铠 */],
		[probability:7, code:"DA212"/* 红莲战袍 */],
		[probability:7, code:"WA111"/* 天阙斧 */],
		[probability:7, code:"WC111"/* 利维坦 */],
		[probability:7, code:"WH112"/* 碎星锤 */],
		[probability:7, code:"WM112"/* 审判之杖 */],
		[probability:7, code:"WP112"/* 傲天枪 */],
		[probability:7, code:"WS113"/* 猛犸巨弩 */],
		[probability:7, code:"WS213"/* 双截刃 */],
		[probability:7, code:"WT112"/* 鸩尾标 */],
		[probability:7, code:"DB111"/* 圣靴 */],
		[probability:7, code:"DS111"/* 苍穹之盾 */],
		[probability:7, code:"OM112"/* 菱晶石 */],
		[probability:7, code:"DG111"/* 黄铜护手 */],
		[probability:7, code:"OA211"/* 玄铁臂环 */],
		[probability:7, code:"OR113"/* 灵魂之戒 */],
		[probability:7, code:"OA113"/* 生命之链 */],
		[probability:7, code:"EC102"/* 回城卷 */],
		[probability:7, code:"GP107"/* 独角兽王水晶 */],
		[probability:7, code:"GP110"/* 守护圣徒水晶 */],
		[probability:2, code:"DA113"/* 虎刹魔铠 */],
		[probability:2, code:"DA213"/* 幽绿之眼 */],
		[probability:2, code:"WA112"/* 奥丁斧 */],
		[probability:2, code:"WC112"/* 飞龙爪 */],
		[probability:2, code:"WH113"/* 破日锤 */],
		[probability:2, code:"WM113"/* 魔蜓杖 */],
		[probability:2, code:"WP113"/* 冥河战镰 */],
		[probability:2, code:"WS114"/* 爱神之翼 */],
		[probability:2, code:"WS214"/* 金刚伏魔剑 */],
		[probability:2, code:"WT113"/* 魔龙标 */],
		[probability:2, code:"DB112"/* 破棘之靴 */],
		[probability:2, code:"DS112"/* 暗黑盾 */],
		[probability:2, code:"OM113"/* 西法路 */],
		[probability:2, code:"DG112"/* 巨灵护手 */],
		[probability:2, code:"OA212"/* 紫焰臂环 */],
		[probability:2, code:"OR113"/* 灵魂之戒 */],
		[probability:2, code:"OA113"/* 生命之链 */],
		[probability:2, code:"OS107"/* 水晶石 */],
		[probability:2, code:"OS108"/* 虎翼石 */],
		[probability:2, code:"EC102"/* 回城卷 */],
		[probability:2, code:"GP107"/* 独角兽王水晶 */],
		[probability:2, code:"GP110"/* 守护圣徒水晶 */]
	],
	drops_more:[
	]
]
