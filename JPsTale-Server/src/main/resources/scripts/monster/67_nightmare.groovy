monster = [
	ID:'67_nightmare',
	Name:'梦魇',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:67,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:1000,
		// Attack Status
		Atk:[34, 42],
		AtkSpeed:8,
		Range:75,
		Rating:1000,

		// Defence Status
		Flee:550,
		Absorb:16,
		Block:0,
		DamageStunPers:100,

		// Resistance
		Earth:50,
		Fire:10,
		Ice:10,// Water
		Lighting:10,// Wind
		Poison:100,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:7,
		Real_Sight:340,
		Talks:[],

		// Move Behavier
		Move_Speed:3,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:0,
		SkillDamage:[40, 50],
		SkillDistance:75,
		SkillRange:50,
		SkillRating:20,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:0,
		PotionCount:0,
	],

	Looks:[
		ClassCode:29,
		ArrowPosi:[-1, 50],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:-1,
		Model:'char/monster/moncavemummy/moncavemummy.ini',
		Sound:0x00001400,
	],

	// Drops
	AllSeeItem:false,
	Exp:16000,
	Quantity:1,
	drops:[
		[probability:3400, code:"NULL"/* Drops nothing */],
		[probability:3100, code:"GG101", value:[120, 170]/* Gold */],
		[probability:680, code:"PL104"/* 顶级恢复生命药水 */],
		[probability:680, code:"PS104"/* 顶级恢复耐力药水 */],
		[probability:680, code:"PL104"/* 顶级恢复生命药水 */],
		[probability:44, code:"DA109"/* 黄铜战铠 */],
		[probability:44, code:"DA209"/* 学徒披风 */],
		[probability:44, code:"WA108"/* 叼刚 清磐 */],
		[probability:44, code:"WC108"/* 矩记飘 府亥瘤 */],
		[probability:44, code:"WH109"/* 固萍绢扼捞飘 */],
		[probability:44, code:"WM109"/* 绊胶飘 */],
		[probability:44, code:"WP109"/* 单喉 荤捞靛 */],
		[probability:44, code:"WS110"/* 固胶飘 */],
		[probability:44, code:"WS210"/* 敲饭萍逞 家靛 */],
		[probability:44, code:"WT108"/* 金标 */],
		[probability:44, code:"OM109"/* 暗月 */],
		[probability:44, code:"DB107"/* 冰火靴 */],
		[probability:44, code:"DS107"/* 金刚盾 */],
		[probability:44, code:"DG107"/* 百裂护手 */],
		[probability:44, code:"OA207"/* 乌金臂环 */],
		[probability:44, code:"OR109"/* 黑暗之戒 */],
		[probability:44, code:"OA109"/* 海蓝石链 */],
		[probability:44, code:"EC105"/* 公会卷轴 */],
		[probability:21, code:"DA110"/* 百裂铠 */],
		[probability:21, code:"DA210"/* 信徒披风 */],
		[probability:21, code:"WA109"/* 破山斧 */],
		[probability:21, code:"WC109"/* 兽之斧刃 */],
		[probability:21, code:"WH110"/* 轩辕巨锤 */],
		[probability:21, code:"WM110"/* 圣者杖 */],
		[probability:21, code:"WP110"/* 白银之枪 */],
		[probability:21, code:"WS111"/* 龙骨战弓 */],
		[probability:21, code:"WS211"/* 斩马刀 */],
		[probability:21, code:"WT109"/* 酒唱能促 */],
		[probability:21, code:"OM110"/* 蓝色星辰 */],
		[probability:21, code:"DB108"/* 百战靴 */],
		[probability:21, code:"DS108"/* 赤龙焰盾 */],
		[probability:21, code:"DG108"/* 大地护手 */],
		[probability:21, code:"OA208"/* 百炼臂环 */],
		[probability:21, code:"EC102"/* 回城卷 */],
		[probability:21, code:"OR110"/* 伏魔戒指 */],
		[probability:21, code:"OA110"/* 镇魂铃 */],
		[probability:21, code:"EC105"/* 公会卷轴 */],
		[probability:10, code:"DA111"/* 重装机铠 */],
		[probability:10, code:"DA211"/* 大法师袍 */],
		[probability:10, code:"WA110"/* 定神斧 */],
		[probability:10, code:"WC110"/* 九头刺蛇爪 */],
		[probability:10, code:"WH111"/* 赤冥之锤 */],
		[probability:10, code:"WM111"/* 王者杖 */],
		[probability:10, code:"WP111"/* 屠龙枪 */],
		[probability:10, code:"WS212"/* 嗜血屠魔剑 */],
		[probability:10, code:"WS112"/* 人马之辉 */],
		[probability:10, code:"WT110"/* 飞云标 */],
		[probability:10, code:"OM111"/* 淬火乌晶 */],
		[probability:10, code:"DB109"/* 大地靴 */],
		[probability:10, code:"DS109"/* 圣盾 */],
		[probability:10, code:"DG109"/* 神力护手 */],
		[probability:10, code:"OA209"/* 飞翼臂环 */],
		[probability:10, code:"EC102"/* 回城卷 */],
		[probability:10, code:"OR111"/* 封印之戒 */],
		[probability:10, code:"OA111"/* 圣者之链 */],
		[probability:10, code:"OS105"/* 冰晶石 */],
		[probability:3, code:"DA112"/* 战神宝铠 */],
		[probability:3, code:"DA212"/* 红莲战袍 */],
		[probability:3, code:"WA111"/* 天阙斧 */],
		[probability:3, code:"WC111"/* 利维坦 */],
		[probability:3, code:"WH112"/* 碎星锤 */],
		[probability:3, code:"WM112"/* 审判之杖 */],
		[probability:3, code:"WP112"/* 傲天枪 */],
		[probability:3, code:"WS113"/* 猛犸巨弩 */],
		[probability:3, code:"WS213"/* 双截刃 */],
		[probability:3, code:"WT111"/* 神标 */],
		[probability:3, code:"OM112"/* 菱晶石 */],
		[probability:3, code:"DB110"/* 地火战靴 */],
		[probability:3, code:"DS110"/* 宙斯盾 */],
		[probability:3, code:"DG110"/* 火云护手 */],
		[probability:3, code:"OA210"/* 百川流水臂环 */],
		[probability:3, code:"EC102"/* 回城卷 */],
		[probability:3, code:"OR111"/* 封印之戒 */],
		[probability:3, code:"WR101"/* 风魔卷轴A */],
		[probability:3, code:"OS106"/* 玄风石 */],
		[probability:3, code:"GP111"/* 大头蜘蛛水晶 */]
	],
	drops_more:[
		[probability:60, code:"GP111"/* 大头蜘蛛水晶 */]	]
]
