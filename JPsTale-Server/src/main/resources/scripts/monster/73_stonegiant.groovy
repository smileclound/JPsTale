monster = [
	ID:'73_stonegiant',
	Name:'无双石怪',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:73,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:1350,
		// Attack Status
		Atk:[44, 56],
		AtkSpeed:7,
		Range:80,
		Rating:710,

		// Defence Status
		Flee:600,
		Absorb:16,
		Block:8,
		DamageStunPers:80,

		// Resistance
		Earth:70,
		Fire:80,
		Ice:35,// Water
		Lighting:0,// Wind
		Poison:100,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:8,
		Real_Sight:370,
		Talks:[],

		// Move Behavier
		Move_Speed:4,
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
		ClassCode:22,
		ArrowPosi:[1, 38],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:3,
		Model:'char/monster/Golem/Golem.ini',
		Sound:0x00001190,
	],

	// Drops
	AllSeeItem:false,
	Exp:18500,
	Quantity:1,
	drops:[
		[probability:2040, code:"NULL"/* Drops nothing */],
		[probability:4300, code:"GG101", value:[120, 190]/* Gold */],
		[probability:380, code:"PL104"/* 顶级恢复生命药水 */],
		[probability:380, code:"PS104"/* 顶级恢复耐力药水 */],
		[probability:380, code:"PM104"/* 顶级恢复魔法药水 */],
		[probability:380, code:"PL104"/* 顶级恢复生命药水 */],
		[probability:380, code:"PM104"/* 顶级恢复魔法药水 */],
		[probability:55, code:"DA109"/* 黄铜战铠 */],
		[probability:55, code:"DA209"/* 学徒披风 */],
		[probability:55, code:"WA108"/* 叼刚 清磐 */],
		[probability:55, code:"WC108"/* 矩记飘 府亥瘤 */],
		[probability:55, code:"WH109"/* 固萍绢扼捞飘 */],
		[probability:55, code:"WM108"/* 公正之杖 */],
		[probability:55, code:"WP109"/* 单喉 荤捞靛 */],
		[probability:55, code:"WS110"/* 固胶飘 */],
		[probability:55, code:"WS210"/* 敲饭萍逞 家靛 */],
		[probability:55, code:"WT108"/* 金标 */],
		[probability:55, code:"OM107"/* 火星 */],
		[probability:55, code:"DB108"/* 百战靴 */],
		[probability:55, code:"DS108"/* 赤龙焰盾 */],
		[probability:55, code:"OM109"/* 暗月 */],
		[probability:55, code:"DG108"/* 大地护手 */],
		[probability:55, code:"OA208"/* 百炼臂环 */],
		[probability:55, code:"OR111"/* 封印之戒 */],
		[probability:55, code:"OA111"/* 圣者之链 */],
		[probability:25, code:"DA110"/* 百裂铠 */],
		[probability:25, code:"DA210"/* 信徒披风 */],
		[probability:25, code:"WA109"/* 破山斧 */],
		[probability:25, code:"WC109"/* 兽之斧刃 */],
		[probability:25, code:"WH110"/* 轩辕巨锤 */],
		[probability:25, code:"WM109"/* 绊胶飘 */],
		[probability:25, code:"WP110"/* 白银之枪 */],
		[probability:25, code:"WS111"/* 龙骨战弓 */],
		[probability:25, code:"WS211"/* 斩马刀 */],
		[probability:25, code:"WT109"/* 酒唱能促 */],
		[probability:25, code:"OM108"/* 阳炎 */],
		[probability:25, code:"DB109"/* 大地靴 */],
		[probability:25, code:"DS109"/* 圣盾 */],
		[probability:25, code:"DG109"/* 神力护手 */],
		[probability:25, code:"OA209"/* 飞翼臂环 */],
		[probability:25, code:"OR112"/* 王者戒指 */],
		[probability:25, code:"OA112"/* 魔龙之心 */],
		[probability:25, code:"OS105"/* 冰晶石 */],
		[probability:25, code:"OS105"/* 冰晶石 */],
		[probability:25, code:"GP106"/* 火灵王水晶 */],
		[probability:9, code:"DA111"/* 重装机铠 */],
		[probability:9, code:"DA211"/* 大法师袍 */],
		[probability:9, code:"WA110"/* 定神斧 */],
		[probability:9, code:"WC110"/* 九头刺蛇爪 */],
		[probability:9, code:"WH111"/* 赤冥之锤 */],
		[probability:9, code:"WM110"/* 圣者杖 */],
		[probability:9, code:"WP111"/* 屠龙枪 */],
		[probability:9, code:"WS212"/* 嗜血屠魔剑 */],
		[probability:9, code:"WS112"/* 人马之辉 */],
		[probability:9, code:"WT110"/* 飞云标 */],
		[probability:9, code:"OM109"/* 暗月 */],
		[probability:9, code:"DB110"/* 地火战靴 */],
		[probability:9, code:"DS110"/* 宙斯盾 */],
		[probability:9, code:"DG110"/* 火云护手 */],
		[probability:9, code:"OA210"/* 百川流水臂环 */],
		[probability:9, code:"EC102"/* 回城卷 */],
		[probability:9, code:"OR113"/* 灵魂之戒 */],
		[probability:9, code:"OA113"/* 生命之链 */],
		[probability:9, code:"OS106"/* 玄风石 */],
		[probability:9, code:"OS107"/* 水晶石 */],
		[probability:9, code:"GP106"/* 火灵王水晶 */],
		[probability:2, code:"DA112"/* 战神宝铠 */],
		[probability:2, code:"DA212"/* 红莲战袍 */],
		[probability:2, code:"WA111"/* 天阙斧 */],
		[probability:2, code:"WC111"/* 利维坦 */],
		[probability:2, code:"WH112"/* 碎星锤 */],
		[probability:2, code:"WM111"/* 王者杖 */],
		[probability:2, code:"WP112"/* 傲天枪 */],
		[probability:2, code:"WS113"/* 猛犸巨弩 */],
		[probability:2, code:"WS213"/* 双截刃 */],
		[probability:2, code:"WT111"/* 神标 */],
		[probability:2, code:"OM110"/* 蓝色星辰 */],
		[probability:2, code:"DB111"/* 圣靴 */],
		[probability:2, code:"DS111"/* 苍穹之盾 */],
		[probability:2, code:"DG111"/* 黄铜护手 */],
		[probability:2, code:"OA211"/* 玄铁臂环 */],
		[probability:2, code:"EC102"/* 回城卷 */],
		[probability:2, code:"OR113"/* 灵魂之戒 */],
		[probability:2, code:"OA113"/* 生命之链 */],
		[probability:2, code:"OS106"/* 玄风石 */],
		[probability:2, code:"OS107"/* 水晶石 */],
		[probability:2, code:"GP106"/* 火灵王水晶 */],
		[probability:2, code:"GP110"/* 守护圣徒水晶 */]
	],
	drops_more:[
	]
]
