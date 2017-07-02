monster = [
	ID:'73_omu',
	Name:'变异甲虫',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:73,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:2000,
		// Attack Status
		Atk:[44, 55],
		AtkSpeed:8,
		Range:80,
		Rating:1180,

		// Defence Status
		Flee:580,
		Absorb:12,
		Block:8,
		DamageStunPers:70,

		// Resistance
		Earth:40,
		Fire:40,
		Ice:40,// Water
		Lighting:40,// Wind
		Poison:60,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:9,
		Real_Sight:350,
		Talks:[],

		// Move Behavier
		Move_Speed:5,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:0,
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
		ArrowPosi:[0, 25],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:-1,
		Model:'char/monster/Omu/Omu.ini',
		Sound:0x000015D0,
	],

	// Drops
	AllSeeItem:false,
	Exp:23000,
	Quantity:1,
	drops:[
		[probability:2750, code:"NULL"/* Drops nothing */],
		[probability:3500, code:"GG101", value:[350, 480]/* Gold */],
		[probability:1240, code:"PL104"/* 顶级恢复生命药水 */],
		[probability:1240, code:"PM104"/* 顶级恢复魔法药水 */],
		[probability:38, code:"DA109"/* 黄铜战铠 */],
		[probability:38, code:"DA209"/* 学徒披风 */],
		[probability:38, code:"WA108"/* 叼刚 清磐 */],
		[probability:38, code:"WC108"/* 矩记飘 府亥瘤 */],
		[probability:38, code:"WH109"/* 固萍绢扼捞飘 */],
		[probability:38, code:"WM109"/* 绊胶飘 */],
		[probability:38, code:"WP109"/* 单喉 荤捞靛 */],
		[probability:38, code:"WS210"/* 敲饭萍逞 家靛 */],
		[probability:38, code:"WS110"/* 固胶飘 */],
		[probability:38, code:"WT109"/* 酒唱能促 */],
		[probability:38, code:"DB108"/* 百战靴 */],
		[probability:38, code:"DS108"/* 赤龙焰盾 */],
		[probability:38, code:"OM109"/* 暗月 */],
		[probability:38, code:"DG108"/* 大地护手 */],
		[probability:38, code:"OA208"/* 百炼臂环 */],
		[probability:38, code:"OR112"/* 王者戒指 */],
		[probability:38, code:"OA112"/* 魔龙之心 */],
		[probability:38, code:"EC105"/* 公会卷轴 */],
		[probability:20, code:"DA110"/* 百裂铠 */],
		[probability:20, code:"DA210"/* 信徒披风 */],
		[probability:20, code:"WA109"/* 破山斧 */],
		[probability:20, code:"WC109"/* 兽之斧刃 */],
		[probability:20, code:"WH110"/* 轩辕巨锤 */],
		[probability:20, code:"WM110"/* 圣者杖 */],
		[probability:20, code:"WP110"/* 白银之枪 */],
		[probability:20, code:"WS111"/* 龙骨战弓 */],
		[probability:20, code:"WS211"/* 斩马刀 */],
		[probability:20, code:"WT110"/* 飞云标 */],
		[probability:20, code:"DB109"/* 大地靴 */],
		[probability:20, code:"DS109"/* 圣盾 */],
		[probability:20, code:"OM110"/* 蓝色星辰 */],
		[probability:20, code:"DG109"/* 神力护手 */],
		[probability:20, code:"OA209"/* 飞翼臂环 */],
		[probability:20, code:"EC102"/* 回城卷 */],
		[probability:20, code:"OR113"/* 灵魂之戒 */],
		[probability:20, code:"OA113"/* 生命之链 */],
		[probability:20, code:"EC102"/* 回城卷 */],
		[probability:20, code:"OS106"/* 玄风石 */],
		[probability:4, code:"DA111"/* 重装机铠 */],
		[probability:4, code:"DA211"/* 大法师袍 */],
		[probability:4, code:"WA110"/* 定神斧 */],
		[probability:4, code:"WC110"/* 九头刺蛇爪 */],
		[probability:4, code:"WH111"/* 赤冥之锤 */],
		[probability:4, code:"WM111"/* 王者杖 */],
		[probability:4, code:"WP111"/* 屠龙枪 */],
		[probability:4, code:"WS212"/* 嗜血屠魔剑 */],
		[probability:4, code:"WS112"/* 人马之辉 */],
		[probability:4, code:"WT111"/* 神标 */],
		[probability:4, code:"DB110"/* 地火战靴 */],
		[probability:4, code:"DS110"/* 宙斯盾 */],
		[probability:4, code:"OM111"/* 淬火乌晶 */],
		[probability:4, code:"DG110"/* 火云护手 */],
		[probability:4, code:"OA210"/* 百川流水臂环 */],
		[probability:4, code:"EC102"/* 回城卷 */],
		[probability:4, code:"OR113"/* 灵魂之戒 */],
		[probability:4, code:"OA113"/* 生命之链 */],
		[probability:4, code:"EC102"/* 回城卷 */],
		[probability:4, code:"OS106"/* 玄风石 */],
		[probability:4, code:"OS107"/* 水晶石 */],
		[probability:3, code:"DA112"/* 战神宝铠 */],
		[probability:3, code:"DA212"/* 红莲战袍 */],
		[probability:3, code:"WA111"/* 天阙斧 */],
		[probability:3, code:"WC111"/* 利维坦 */],
		[probability:3, code:"WH112"/* 碎星锤 */],
		[probability:3, code:"WM112"/* 审判之杖 */],
		[probability:3, code:"WP112"/* 傲天枪 */],
		[probability:3, code:"WS113"/* 猛犸巨弩 */],
		[probability:3, code:"WS213"/* 双截刃 */],
		[probability:3, code:"WT112"/* 鸩尾标 */],
		[probability:3, code:"DB111"/* 圣靴 */],
		[probability:3, code:"DS111"/* 苍穹之盾 */],
		[probability:3, code:"OM112"/* 菱晶石 */],
		[probability:3, code:"DG111"/* 黄铜护手 */],
		[probability:3, code:"OA211"/* 玄铁臂环 */],
		[probability:3, code:"EC102"/* 回城卷 */],
		[probability:3, code:"OR114"/* 帝王之戒 */],
		[probability:3, code:"OA114"/* 神之庇护 */],
		[probability:3, code:"EC102"/* 回城卷 */]
	],
	drops_more:[
		[probability:200, code:"DA110"/* 百裂铠 */]	]
]
