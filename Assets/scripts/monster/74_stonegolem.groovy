monster = [
	ID:'74_stonegolem',
	Name:'无双石魔',
	Clazz:0,// 1 BOSS
	Brood:0x92,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:74,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:1550,
		// Attack Status
		Atk:[37, 50],
		AtkSpeed:6,
		Range:70,
		Rating:1100,

		// Defence Status
		Flee:620,
		Absorb:18,
		Block:0,
		DamageStunPers:100,

		// Resistance
		Earth:90,
		Fire:80,
		Ice:30,// Water
		Lighting:30,// Wind
		Poison:100,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:7,
		Real_Sight:380,
		Talks:[],

		// Move Behavier
		Move_Speed:3,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:20,
		SkillDamage:[44, 54],
		SkillDistance:70,
		SkillRange:60,
		SkillRating:17,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:0,
		PotionCount:0,
	],

	Looks:[
		ClassCode:23,
		ArrowPosi:[2, 65],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:3,
		Model:'char/monster/Monstonegolem/Monstonegolem.ini',
		Sound:0x00001410,
	],

	// Drops
	AllSeeItem:false,
	Exp:37000,
	Quantity:1,
	drops:[
		[probability:2750, code:"NULL"/* Drops nothing */],
		[probability:3500, code:"GG101", value:[350, 480]/* Gold */],
		[probability:800, code:"PL104"/* 顶级恢复生命药水 */],
		[probability:800, code:"PS104"/* 顶级恢复耐力药水 */],
		[probability:800, code:"PM104"/* 顶级恢复魔法药水 */],
		[probability:38, code:"DA109"/* 黄铜战铠 */],
		[probability:38, code:"DA209"/* 学徒披风 */],
		[probability:38, code:"WA107"/* 刺脊斧 */],
		[probability:38, code:"WC107"/* 平手刃 */],
		[probability:38, code:"WH109"/* 固萍绢扼捞飘 */],
		[probability:38, code:"WM109"/* 绊胶飘 */],
		[probability:38, code:"WP109"/* 单喉 荤捞靛 */],
		[probability:38, code:"WS110"/* 固胶飘 */],
		[probability:38, code:"WS210"/* 敲饭萍逞 家靛 */],
		[probability:38, code:"WT109"/* 酒唱能促 */],
		[probability:38, code:"DB108"/* 百战靴 */],
		[probability:38, code:"DS108"/* 赤龙焰盾 */],
		[probability:38, code:"OM109"/* 暗月 */],
		[probability:38, code:"DG108"/* 大地护手 */],
		[probability:38, code:"OA208"/* 百炼臂环 */],
		[probability:38, code:"OR112"/* 王者戒指 */],
		[probability:38, code:"OA112"/* 魔龙之心 */],
		[probability:38, code:"EC105"/* 公会卷轴 */],
		[probability:21, code:"DA110"/* 百裂铠 */],
		[probability:21, code:"DA210"/* 信徒披风 */],
		[probability:21, code:"WA108"/* 叼刚 清磐 */],
		[probability:21, code:"WC108"/* 矩记飘 府亥瘤 */],
		[probability:21, code:"WH110"/* 轩辕巨锤 */],
		[probability:21, code:"WM110"/* 圣者杖 */],
		[probability:21, code:"WP110"/* 白银之枪 */],
		[probability:21, code:"WS111"/* 龙骨战弓 */],
		[probability:21, code:"WS211"/* 斩马刀 */],
		[probability:21, code:"WT110"/* 飞云标 */],
		[probability:21, code:"DB109"/* 大地靴 */],
		[probability:21, code:"DS109"/* 圣盾 */],
		[probability:21, code:"OM110"/* 蓝色星辰 */],
		[probability:21, code:"DG109"/* 神力护手 */],
		[probability:21, code:"OA209"/* 飞翼臂环 */],
		[probability:21, code:"EC102"/* 回城卷 */],
		[probability:21, code:"OR113"/* 灵魂之戒 */],
		[probability:21, code:"OA113"/* 生命之链 */],
		[probability:21, code:"EC102"/* 回城卷 */],
		[probability:5, code:"DA111"/* 重装机铠 */],
		[probability:5, code:"DA211"/* 大法师袍 */],
		[probability:5, code:"WA109"/* 破山斧 */],
		[probability:5, code:"WC109"/* 兽之斧刃 */],
		[probability:5, code:"WH111"/* 赤冥之锤 */],
		[probability:5, code:"WM111"/* 王者杖 */],
		[probability:5, code:"WP111"/* 屠龙枪 */],
		[probability:5, code:"WS212"/* 嗜血屠魔剑 */],
		[probability:5, code:"WS112"/* 人马之辉 */],
		[probability:5, code:"WT111"/* 神标 */],
		[probability:5, code:"DB110"/* 地火战靴 */],
		[probability:5, code:"DS110"/* 宙斯盾 */],
		[probability:5, code:"OM111"/* 淬火乌晶 */],
		[probability:5, code:"DG110"/* 火云护手 */],
		[probability:5, code:"OA210"/* 百川流水臂环 */],
		[probability:5, code:"EC102"/* 回城卷 */],
		[probability:5, code:"OR113"/* 灵魂之戒 */],
		[probability:5, code:"OA113"/* 生命之链 */],
		[probability:5, code:"EC102"/* 回城卷 */],
		[probability:5, code:"OS107"/* 水晶石 */],
		[probability:2, code:"DA112"/* 战神宝铠 */],
		[probability:2, code:"DA212"/* 红莲战袍 */],
		[probability:2, code:"WA110"/* 定神斧 */],
		[probability:2, code:"WC110"/* 九头刺蛇爪 */],
		[probability:2, code:"WH112"/* 碎星锤 */],
		[probability:2, code:"WM112"/* 审判之杖 */],
		[probability:2, code:"WP112"/* 傲天枪 */],
		[probability:2, code:"WS113"/* 猛犸巨弩 */],
		[probability:2, code:"WS213"/* 双截刃 */],
		[probability:2, code:"WT112"/* 鸩尾标 */],
		[probability:2, code:"DB111"/* 圣靴 */],
		[probability:2, code:"DS111"/* 苍穹之盾 */],
		[probability:2, code:"OM112"/* 菱晶石 */],
		[probability:2, code:"DG111"/* 黄铜护手 */],
		[probability:2, code:"OA211"/* 玄铁臂环 */],
		[probability:2, code:"EC102"/* 回城卷 */],
		[probability:2, code:"OR114"/* 帝王之戒 */],
		[probability:2, code:"OA114"/* 神之庇护 */],
		[probability:2, code:"EC102"/* 回城卷 */]
	],
	drops_more:[
	]
]
