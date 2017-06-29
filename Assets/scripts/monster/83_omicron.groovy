monster = [
	ID:'83_omicron',
	Name:'神秘十五',
	Clazz:0,// 1 BOSS
	Brood:0x92,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:83,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:2600,
		// Attack Status
		Atk:[48, 60],
		AtkSpeed:9,
		Range:80,
		Rating:1200,

		// Defence Status
		Flee:620,
		Absorb:18,
		Block:10,
		DamageStunPers:70,

		// Resistance
		Earth:40,
		Fire:10,
		Ice:40,// Water
		Lighting:20,// Wind
		Poison:40,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:9,
		Real_Sight:360,
		Talks:[],

		// Move Behavier
		Move_Speed:4,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:20,
		SkillDamage:[55, 65],
		SkillDistance:0,
		SkillRange:130,
		SkillRating:17,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:0,
		PotionCount:0,
	],

	Looks:[
		ClassCode:0,
		ArrowPosi:[0, 25],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:-1,
		Model:'char/monster/Monomicron/Monomicron.ini',
		Sound:0x00001360,
	],

	// Drops
	AllSeeItem:false,
	Exp:40875,
	Quantity:1,
	drops:[
		[probability:1040, code:"NULL"/* Drops nothing */],
		[probability:4400, code:"GG101", value:[265, 320]/* Gold */],
		[probability:600, code:"PL104"/* 顶级恢复生命药水 */],
		[probability:600, code:"PS104"/* 顶级恢复耐力药水 */],
		[probability:600, code:"PM104"/* 顶级恢复魔法药水 */],
		[probability:600, code:"PL104"/* 顶级恢复生命药水 */],
		[probability:600, code:"PM104"/* 顶级恢复魔法药水 */],
		[probability:52, code:"DA110"/* 百裂铠 */],
		[probability:52, code:"DA210"/* 信徒披风 */],
		[probability:52, code:"WA109"/* 破山斧 */],
		[probability:52, code:"WC109"/* 兽之斧刃 */],
		[probability:52, code:"WH110"/* 轩辕巨锤 */],
		[probability:52, code:"WM110"/* 圣者杖 */],
		[probability:52, code:"WP110"/* 白银之枪 */],
		[probability:52, code:"WS111"/* 龙骨战弓 */],
		[probability:52, code:"WS211"/* 斩马刀 */],
		[probability:52, code:"WT110"/* 飞云标 */],
		[probability:52, code:"DB109"/* 大地靴 */],
		[probability:52, code:"DS109"/* 圣盾 */],
		[probability:52, code:"OM110"/* 蓝色星辰 */],
		[probability:52, code:"DG109"/* 神力护手 */],
		[probability:52, code:"OA209"/* 飞翼臂环 */],
		[probability:52, code:"OR111"/* 封印之戒 */],
		[probability:52, code:"OA111"/* 圣者之链 */],
		[probability:20, code:"DA111"/* 重装机铠 */],
		[probability:20, code:"DA211"/* 大法师袍 */],
		[probability:20, code:"WA110"/* 定神斧 */],
		[probability:20, code:"WC110"/* 九头刺蛇爪 */],
		[probability:20, code:"WH111"/* 赤冥之锤 */],
		[probability:20, code:"WM111"/* 王者杖 */],
		[probability:20, code:"WP111"/* 屠龙枪 */],
		[probability:20, code:"WS112"/* 人马之辉 */],
		[probability:20, code:"WS212"/* 嗜血屠魔剑 */],
		[probability:20, code:"WT111"/* 神标 */],
		[probability:20, code:"DB110"/* 地火战靴 */],
		[probability:20, code:"DS110"/* 宙斯盾 */],
		[probability:20, code:"OM111"/* 淬火乌晶 */],
		[probability:20, code:"DG110"/* 火云护手 */],
		[probability:20, code:"OA210"/* 百川流水臂环 */],
		[probability:20, code:"OR112"/* 王者戒指 */],
		[probability:20, code:"OA112"/* 魔龙之心 */],
		[probability:20, code:"OS107"/* 水晶石 */],
		[probability:20, code:"OS107"/* 水晶石 */],
		[probability:20, code:"EC102"/* 回城卷 */],
		[probability:9, code:"DA112"/* 战神宝铠 */],
		[probability:9, code:"DA212"/* 红莲战袍 */],
		[probability:9, code:"WA111"/* 天阙斧 */],
		[probability:9, code:"WC111"/* 利维坦 */],
		[probability:9, code:"WH112"/* 碎星锤 */],
		[probability:9, code:"WM112"/* 审判之杖 */],
		[probability:9, code:"WP112"/* 傲天枪 */],
		[probability:9, code:"WS113"/* 猛犸巨弩 */],
		[probability:9, code:"WS213"/* 双截刃 */],
		[probability:9, code:"WT112"/* 鸩尾标 */],
		[probability:9, code:"DB111"/* 圣靴 */],
		[probability:9, code:"DS111"/* 苍穹之盾 */],
		[probability:9, code:"OM112"/* 菱晶石 */],
		[probability:9, code:"DG111"/* 黄铜护手 */],
		[probability:9, code:"OA211"/* 玄铁臂环 */],
		[probability:9, code:"OR113"/* 灵魂之戒 */],
		[probability:9, code:"OA113"/* 生命之链 */],
		[probability:9, code:"OS107"/* 水晶石 */],
		[probability:9, code:"OS108"/* 虎翼石 */],
		[probability:9, code:"EC102"/* 回城卷 */],
		[probability:9, code:"GP110"/* 守护圣徒水晶 */],
		[probability:3, code:"DA113"/* 虎刹魔铠 */],
		[probability:3, code:"DA213"/* 幽绿之眼 */],
		[probability:3, code:"WA112"/* 奥丁斧 */],
		[probability:3, code:"WC112"/* 飞龙爪 */],
		[probability:3, code:"WH113"/* 破日锤 */],
		[probability:3, code:"WM113"/* 魔蜓杖 */],
		[probability:3, code:"WP113"/* 冥河战镰 */],
		[probability:3, code:"WS114"/* 爱神之翼 */],
		[probability:3, code:"WS214"/* 金刚伏魔剑 */],
		[probability:3, code:"WT113"/* 魔龙标 */],
		[probability:3, code:"DB112"/* 破棘之靴 */],
		[probability:3, code:"DS112"/* 暗黑盾 */],
		[probability:3, code:"OM113"/* 西法路 */],
		[probability:3, code:"DG112"/* 巨灵护手 */],
		[probability:3, code:"OA212"/* 紫焰臂环 */],
		[probability:3, code:"OR113"/* 灵魂之戒 */],
		[probability:3, code:"OA113"/* 生命之链 */],
		[probability:3, code:"EC102"/* 回城卷 */],
		[probability:3, code:"GP113"/* 铁甲狂魔水晶 */]
	],
	drops_more:[
		[probability:50, code:"DA113"/* 虎刹魔铠 */]	]
]
