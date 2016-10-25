monster = [
	ID:'66_slaughter',
	Name:'魔剑圣',
	Clazz:0,// 1 BOSS
	Brood:0x92,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:66,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:1000,
		// Attack Status
		Atk:[48, 56],
		AtkSpeed:7,
		Range:100,
		Rating:900,

		// Defence Status
		Flee:520,
		Absorb:15,
		Block:12,
		DamageStunPers:100,

		// Resistance
		Earth:30,
		Fire:20,
		Ice:20,// Water
		Lighting:0,// Wind
		Poison:100,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:8,
		Real_Sight:400,
		Talks:[],

		// Move Behavier
		Move_Speed:3,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:20,
		SkillDamage:[50, 60],
		SkillDistance:80,
		SkillRange:60,
		SkillRating:20,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:15,
		PotionCount:1,
	],

	Looks:[
		ClassCode:58,
		ArrowPosi:[-2, 30],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:-1,
		Model:'char/monster/headcut/headcutaxe.ini',
		Sound:0x00001100,
	],

	// Drops
	AllSeeItem:false,
	Exp:17500,
	Quantity:1,
	drops:[
		[probability:2750, code:"NULL"/* Drops nothing */],
		[probability:4000, code:"GG101", value:[120, 180]/* Gold */],
		[probability:500, code:"PL104"/* 顶级恢复生命药水 */],
		[probability:500, code:"PS104"/* 顶级恢复耐力药水 */],
		[probability:500, code:"PL103"/* 高级恢复生命药水 */],
		[probability:500, code:"PS103"/* 高级恢复耐力药水 */],
		[probability:35, code:"DA109"/* 黄铜战铠 */],
		[probability:35, code:"DA209"/* 学徒披风 */],
		[probability:35, code:"WA107"/* 刺脊斧 */],
		[probability:35, code:"WC107"/* 平手刃 */],
		[probability:35, code:"WH108"/* 圣光锤 */],
		[probability:35, code:"WM108"/* 公正之杖 */],
		[probability:35, code:"WP108"/* 血烟长矛 */],
		[probability:35, code:"WS109"/* 点金手弩 */],
		[probability:35, code:"WS209"/* 镇妖剑 */],
		[probability:35, code:"WT107"/* 天命标 */],
		[probability:35, code:"OM107"/* 火星 */],
		[probability:35, code:"DB106"/* 黄金靴 */],
		[probability:35, code:"DS106"/* 百炼盾 */],
		[probability:35, code:"DG106"/* 白金护手 */],
		[probability:35, code:"OA206"/* 精灵臂环 */],
		[probability:35, code:"OR109"/* 黑暗之戒 */],
		[probability:35, code:"OA109"/* 海蓝石链 */],
		[probability:25, code:"DA110"/* 百裂铠 */],
		[probability:25, code:"DA210"/* 信徒披风 */],
		[probability:25, code:"WA108"/* 叼刚 清磐 */],
		[probability:25, code:"WC108"/* 矩记飘 府亥瘤 */],
		[probability:25, code:"WH109"/* 固萍绢扼捞飘 */],
		[probability:25, code:"WM109"/* 绊胶飘 */],
		[probability:25, code:"WP109"/* 单喉 荤捞靛 */],
		[probability:25, code:"WS110"/* 固胶飘 */],
		[probability:25, code:"WS210"/* 敲饭萍逞 家靛 */],
		[probability:25, code:"WT108"/* 金标 */],
		[probability:25, code:"OM108"/* 阳炎 */],
		[probability:25, code:"DB107"/* 冰火靴 */],
		[probability:25, code:"DS107"/* 金刚盾 */],
		[probability:25, code:"DG107"/* 百裂护手 */],
		[probability:25, code:"OA207"/* 乌金臂环 */],
		[probability:25, code:"OR110"/* 伏魔戒指 */],
		[probability:25, code:"OA110"/* 镇魂铃 */],
		[probability:25, code:"OS105"/* 冰晶石 */],
		[probability:25, code:"EC102"/* 回城卷 */],
		[probability:25, code:"EC105"/* 公会卷轴 */],
		[probability:10, code:"DA111"/* 重装机铠 */],
		[probability:10, code:"DA211"/* 大法师袍 */],
		[probability:10, code:"WA109"/* 破山斧 */],
		[probability:10, code:"WC109"/* 兽之斧刃 */],
		[probability:10, code:"WH110"/* 轩辕巨锤 */],
		[probability:10, code:"WM110"/* 圣者杖 */],
		[probability:10, code:"WP110"/* 白银之枪 */],
		[probability:10, code:"WS111"/* 龙骨战弓 */],
		[probability:10, code:"WS211"/* 斩马刀 */],
		[probability:10, code:"WT109"/* 酒唱能促 */],
		[probability:10, code:"OM109"/* 暗月 */],
		[probability:10, code:"DB108"/* 百战靴 */],
		[probability:10, code:"DS108"/* 赤龙焰盾 */],
		[probability:10, code:"DG108"/* 大地护手 */],
		[probability:10, code:"OA208"/* 百炼臂环 */],
		[probability:10, code:"OR111"/* 封印之戒 */],
		[probability:10, code:"OA111"/* 圣者之链 */],
		[probability:10, code:"OS105"/* 冰晶石 */],
		[probability:10, code:"EC105"/* 公会卷轴 */],
		[probability:2, code:"DA112"/* 战神宝铠 */],
		[probability:2, code:"DA212"/* 红莲战袍 */],
		[probability:2, code:"WA110"/* 定神斧 */],
		[probability:2, code:"WC110"/* 九头刺蛇爪 */],
		[probability:2, code:"WH111"/* 赤冥之锤 */],
		[probability:2, code:"WM111"/* 王者杖 */],
		[probability:2, code:"WP111"/* 屠龙枪 */],
		[probability:2, code:"WS212"/* 嗜血屠魔剑 */],
		[probability:2, code:"WS112"/* 人马之辉 */],
		[probability:2, code:"WT110"/* 飞云标 */],
		[probability:2, code:"OM110"/* 蓝色星辰 */],
		[probability:2, code:"DB109"/* 大地靴 */],
		[probability:2, code:"DS109"/* 圣盾 */],
		[probability:2, code:"DG109"/* 神力护手 */],
		[probability:2, code:"OA209"/* 飞翼臂环 */],
		[probability:2, code:"OR112"/* 王者戒指 */],
		[probability:2, code:"OA112"/* 魔龙之心 */],
		[probability:2, code:"EC102"/* 回城卷 */],
		[probability:2, code:"OS106"/* 玄风石 */],
		[probability:2, code:"GP110"/* 守护圣徒水晶 */]
	],
	drops_more:[
	]
]
