monster = [
	ID:'69_naz',
	Name:'夜行者',
	Clazz:0,// 1 BOSS
	Brood:0x92,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:69,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:1200,
		// Attack Status
		Atk:[45, 55],
		AtkSpeed:9,
		Range:140,
		Rating:950,

		// Defence Status
		Flee:530,
		Absorb:10,
		Block:10,
		DamageStunPers:100,

		// Resistance
		Earth:30,
		Fire:20,
		Ice:40,// Water
		Lighting:0,// Wind
		Poison:40,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:9,
		Real_Sight:390,
		Talks:[],

		// Move Behavier
		Move_Speed:4,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:15,
		SkillDamage:[55, 60],
		SkillDistance:0,
		SkillRange:110,
		SkillRating:20,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:0,
		PotionCount:0,
	],

	Looks:[
		ClassCode:0,
		ArrowPosi:[0, 0],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:1,
		Model:'char/monster/Monnazz/Monnazz.ini',
		Sound:0x00001290,
	],

	// Drops
	AllSeeItem:false,
	Exp:17000,
	Quantity:1,
	drops:[
		[probability:2390, code:"NULL"/* Drops nothing */],
		[probability:3500, code:"GG101", value:[170, 200]/* Gold */],
		[probability:500, code:"PL103"/* 高级恢复生命药水 */],
		[probability:500, code:"PL103"/* 高级恢复生命药水 */],
		[probability:500, code:"PM103"/* 高级恢复魔法药水 */],
		[probability:500, code:"PM102"/* 中级恢复魔法药水 */],
		[probability:57, code:"DA108"/* 精制链铠 */],
		[probability:57, code:"DA208"/* 圣女袍 */],
		[probability:57, code:"WA107"/* 刺脊斧 */],
		[probability:57, code:"WC107"/* 平手刃 */],
		[probability:57, code:"WH107"/* 十字锤 */],
		[probability:57, code:"WM108"/* 公正之杖 */],
		[probability:57, code:"WP108"/* 血烟长矛 */],
		[probability:57, code:"WS109"/* 点金手弩 */],
		[probability:57, code:"WS209"/* 镇妖剑 */],
		[probability:57, code:"WT108"/* 金标 */],
		[probability:57, code:"DB107"/* 冰火靴 */],
		[probability:57, code:"DS107"/* 金刚盾 */],
		[probability:57, code:"OM108"/* 阳炎 */],
		[probability:57, code:"DG107"/* 百裂护手 */],
		[probability:57, code:"OA207"/* 乌金臂环 */],
		[probability:57, code:"PM102"/* 中级恢复魔法药水 */],
		[probability:57, code:"EC102"/* 回城卷 */],
		[probability:57, code:"OR109"/* 黑暗之戒 */],
		[probability:57, code:"OA109"/* 海蓝石链 */],
		[probability:33, code:"DA109"/* 黄铜战铠 */],
		[probability:33, code:"DA209"/* 学徒披风 */],
		[probability:33, code:"WA108"/* 叼刚 清磐 */],
		[probability:33, code:"WC108"/* 矩记飘 府亥瘤 */],
		[probability:33, code:"WH109"/* 固萍绢扼捞飘 */],
		[probability:33, code:"WM109"/* 绊胶飘 */],
		[probability:33, code:"WP109"/* 单喉 荤捞靛 */],
		[probability:33, code:"WS110"/* 固胶飘 */],
		[probability:33, code:"WS210"/* 敲饭萍逞 家靛 */],
		[probability:33, code:"WT109"/* 酒唱能促 */],
		[probability:33, code:"DB108"/* 百战靴 */],
		[probability:33, code:"DS108"/* 赤龙焰盾 */],
		[probability:33, code:"OM109"/* 暗月 */],
		[probability:33, code:"DG108"/* 大地护手 */],
		[probability:33, code:"OA208"/* 百炼臂环 */],
		[probability:33, code:"PM103"/* 高级恢复魔法药水 */],
		[probability:33, code:"EC102"/* 回城卷 */],
		[probability:33, code:"OR110"/* 伏魔戒指 */],
		[probability:33, code:"OA110"/* 镇魂铃 */],
		[probability:33, code:"OS105"/* 冰晶石 */],
		[probability:33, code:"EC105"/* 公会卷轴 */],
		[probability:11, code:"DA110"/* 百裂铠 */],
		[probability:11, code:"DA210"/* 信徒披风 */],
		[probability:11, code:"WA109"/* 破山斧 */],
		[probability:11, code:"WC109"/* 兽之斧刃 */],
		[probability:11, code:"WH110"/* 轩辕巨锤 */],
		[probability:11, code:"WM110"/* 圣者杖 */],
		[probability:11, code:"WP110"/* 白银之枪 */],
		[probability:11, code:"WS111"/* 龙骨战弓 */],
		[probability:11, code:"WS211"/* 斩马刀 */],
		[probability:11, code:"WT109"/* 酒唱能促 */],
		[probability:11, code:"DB109"/* 大地靴 */],
		[probability:11, code:"DS109"/* 圣盾 */],
		[probability:11, code:"OM110"/* 蓝色星辰 */],
		[probability:11, code:"DG109"/* 神力护手 */],
		[probability:11, code:"OA209"/* 飞翼臂环 */],
		[probability:11, code:"PM104"/* 顶级恢复魔法药水 */],
		[probability:11, code:"EC102"/* 回城卷 */],
		[probability:11, code:"OR111"/* 封印之戒 */],
		[probability:11, code:"OA111"/* 圣者之链 */],
		[probability:11, code:"OS106"/* 玄风石 */],
		[probability:11, code:"EC102"/* 回城卷 */],
		[probability:2, code:"DA111"/* 重装机铠 */],
		[probability:2, code:"DA211"/* 大法师袍 */],
		[probability:2, code:"WA110"/* 定神斧 */],
		[probability:2, code:"WC110"/* 九头刺蛇爪 */],
		[probability:2, code:"WH111"/* 赤冥之锤 */],
		[probability:2, code:"WM111"/* 王者杖 */],
		[probability:2, code:"WP111"/* 屠龙枪 */],
		[probability:2, code:"WS112"/* 人马之辉 */],
		[probability:2, code:"WS212"/* 嗜血屠魔剑 */],
		[probability:2, code:"WT110"/* 飞云标 */],
		[probability:2, code:"DB110"/* 地火战靴 */],
		[probability:2, code:"DS110"/* 宙斯盾 */],
		[probability:2, code:"OM111"/* 淬火乌晶 */],
		[probability:2, code:"DG110"/* 火云护手 */],
		[probability:2, code:"OA210"/* 百川流水臂环 */],
		[probability:2, code:"PM104"/* 顶级恢复魔法药水 */],
		[probability:2, code:"EC102"/* 回城卷 */],
		[probability:2, code:"OR112"/* 王者戒指 */],
		[probability:2, code:"OA112"/* 魔龙之心 */],
		[probability:2, code:"OS107"/* 水晶石 */],
		[probability:2, code:"GP110"/* 守护圣徒水晶 */]
	],
	drops_more:[
		[probability:50, code:"GP111"/* 大头蜘蛛水晶 */]	]
]
