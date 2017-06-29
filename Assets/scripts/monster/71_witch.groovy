monster = [
	ID:'71_witch',
	Name:'女巫',
	Clazz:0,// 1 BOSS
	Brood:0x92,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:71,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:1100,
		// Attack Status
		Atk:[40, 50],
		AtkSpeed:9,
		Range:180,
		Rating:1200,

		// Defence Status
		Flee:520,
		Absorb:12,
		Block:0,
		DamageStunPers:100,

		// Resistance
		Earth:50,
		Fire:30,
		Ice:0,// Water
		Lighting:20,// Wind
		Poison:100,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:9,
		Real_Sight:390,
		Talks:[],

		// Move Behavier
		Move_Speed:3,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:20,
		SkillDamage:[0, 0],
		SkillDistance:0,
		SkillRange:0,
		SkillRating:10,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:0,
		PotionCount:0,
	],

	Looks:[
		ClassCode:30,
		ArrowPosi:[2, 30],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:1,
		Model:'char/monster/MonWitch/MonWitch2.ini',
		Sound:0x000012C0,
	],

	// Drops
	AllSeeItem:false,
	Exp:20000,
	Quantity:1,
	drops:[
		[probability:2740, code:"NULL"/* Drops nothing */],
		[probability:1833, code:"PM104"/* 顶级恢复魔法药水 */],
		[probability:1833, code:"PM103"/* 高级恢复魔法药水 */],
		[probability:1833, code:"PM103"/* 高级恢复魔法药水 */],
		[probability:58, code:"DA208"/* 圣女袍 */],
		[probability:58, code:"WA107"/* 刺脊斧 */],
		[probability:58, code:"WC108"/* 矩记飘 府亥瘤 */],
		[probability:58, code:"WH108"/* 圣光锤 */],
		[probability:58, code:"WM108"/* 公正之杖 */],
		[probability:58, code:"WP108"/* 血烟长矛 */],
		[probability:58, code:"WS110"/* 固胶飘 */],
		[probability:58, code:"WS210"/* 敲饭萍逞 家靛 */],
		[probability:58, code:"WT109"/* 酒唱能促 */],
		[probability:58, code:"OM108"/* 阳炎 */],
		[probability:58, code:"DB107"/* 冰火靴 */],
		[probability:58, code:"DS107"/* 金刚盾 */],
		[probability:58, code:"DG107"/* 百裂护手 */],
		[probability:58, code:"OA207"/* 乌金臂环 */],
		[probability:58, code:"EC102"/* 回城卷 */],
		[probability:58, code:"OR109"/* 黑暗之戒 */],
		[probability:58, code:"OA109"/* 海蓝石链 */],
		[probability:27, code:"DA209"/* 学徒披风 */],
		[probability:27, code:"WA108"/* 叼刚 清磐 */],
		[probability:27, code:"WC108"/* 矩记飘 府亥瘤 */],
		[probability:27, code:"WH109"/* 固萍绢扼捞飘 */],
		[probability:27, code:"WM109"/* 绊胶飘 */],
		[probability:27, code:"WP109"/* 单喉 荤捞靛 */],
		[probability:27, code:"WS111"/* 龙骨战弓 */],
		[probability:27, code:"WS211"/* 斩马刀 */],
		[probability:27, code:"WT110"/* 飞云标 */],
		[probability:27, code:"OM109"/* 暗月 */],
		[probability:27, code:"DB108"/* 百战靴 */],
		[probability:27, code:"DS108"/* 赤龙焰盾 */],
		[probability:27, code:"DG108"/* 大地护手 */],
		[probability:27, code:"OA208"/* 百炼臂环 */],
		[probability:27, code:"EC102"/* 回城卷 */],
		[probability:27, code:"OR110"/* 伏魔戒指 */],
		[probability:27, code:"OA110"/* 镇魂铃 */],
		[probability:27, code:"EC105"/* 公会卷轴 */],
		[probability:10, code:"DA210"/* 信徒披风 */],
		[probability:10, code:"WA109"/* 破山斧 */],
		[probability:10, code:"WC109"/* 兽之斧刃 */],
		[probability:10, code:"WH110"/* 轩辕巨锤 */],
		[probability:10, code:"WM110"/* 圣者杖 */],
		[probability:10, code:"WP110"/* 白银之枪 */],
		[probability:10, code:"WS112"/* 人马之辉 */],
		[probability:10, code:"WS212"/* 嗜血屠魔剑 */],
		[probability:10, code:"WT110"/* 飞云标 */],
		[probability:10, code:"OM110"/* 蓝色星辰 */],
		[probability:10, code:"DB109"/* 大地靴 */],
		[probability:10, code:"DS109"/* 圣盾 */],
		[probability:10, code:"OM109"/* 暗月 */],
		[probability:10, code:"DG109"/* 神力护手 */],
		[probability:10, code:"OA209"/* 飞翼臂环 */],
		[probability:10, code:"EC102"/* 回城卷 */],
		[probability:10, code:"OS106"/* 玄风石 */],
		[probability:10, code:"OR111"/* 封印之戒 */],
		[probability:10, code:"OA111"/* 圣者之链 */],
		[probability:10, code:"EC105"/* 公会卷轴 */],
		[probability:3, code:"DA211"/* 大法师袍 */],
		[probability:3, code:"WA110"/* 定神斧 */],
		[probability:3, code:"WC110"/* 九头刺蛇爪 */],
		[probability:3, code:"WH111"/* 赤冥之锤 */],
		[probability:3, code:"WM111"/* 王者杖 */],
		[probability:3, code:"WP111"/* 屠龙枪 */],
		[probability:3, code:"WS113"/* 猛犸巨弩 */],
		[probability:3, code:"WS213"/* 双截刃 */],
		[probability:3, code:"WT111"/* 神标 */],
		[probability:3, code:"OM111"/* 淬火乌晶 */],
		[probability:3, code:"DB110"/* 地火战靴 */],
		[probability:3, code:"DS110"/* 宙斯盾 */],
		[probability:3, code:"DG110"/* 火云护手 */],
		[probability:3, code:"OA210"/* 百川流水臂环 */],
		[probability:3, code:"OS107"/* 水晶石 */],
		[probability:3, code:"OR112"/* 王者戒指 */],
		[probability:3, code:"OA112"/* 魔龙之心 */],
		[probability:3, code:"GP109"/* 神秘水晶 */],
		[probability:3, code:"GP110"/* 守护圣徒水晶 */],
		[probability:3, code:"WR101"/* 风魔卷轴A */]
	],
	drops_more:[
		[probability:60, code:"DA209"/* 学徒披风 */]	]
]
