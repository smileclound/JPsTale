monster = [
	ID:'65_darkknight',
	Name:'黑暗骑士',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:55,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:1000,
		// Attack Status
		Atk:[36, 44],
		AtkSpeed:8,
		Range:70,
		Rating:840,

		// Defence Status
		Flee:560,
		Absorb:15,
		Block:0,
		DamageStunPers:100,

		// Resistance
		Earth:75,
		Fire:10,
		Ice:10,// Water
		Lighting:0,// Wind
		Poison:90,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:8,
		Real_Sight:360,
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
		ArrowPosi:[0, 20],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:-1,
		Model:'char/monster/MonSGuard/Monhauntguard.INI',
		Sound:0x00001460,
	],

	// Drops
	AllSeeItem:false,
	Exp:16000,
	Quantity:1,
	drops:[
		[probability:3050, code:"NULL"/* Drops nothing */],
		[probability:3600, code:"GG101", value:[120, 180]/* Gold */],
		[probability:666, code:"PL104"/* 顶级恢复生命药水 */],
		[probability:666, code:"PS104"/* 顶级恢复耐力药水 */],
		[probability:666, code:"PL103"/* 高级恢复生命药水 */],
		[probability:33, code:"DA109"/* 黄铜战铠 */],
		[probability:33, code:"DA209"/* 学徒披风 */],
		[probability:33, code:"WA107"/* 刺脊斧 */],
		[probability:33, code:"WC107"/* 平手刃 */],
		[probability:33, code:"WH108"/* 圣光锤 */],
		[probability:33, code:"WM108"/* 公正之杖 */],
		[probability:33, code:"WP107"/* 战神之镰 */],
		[probability:33, code:"WS109"/* 点金手弩 */],
		[probability:33, code:"WS209"/* 镇妖剑 */],
		[probability:33, code:"WT107"/* 天命标 */],
		[probability:33, code:"OM106"/* 水星 */],
		[probability:33, code:"DB106"/* 黄金靴 */],
		[probability:33, code:"DS106"/* 百炼盾 */],
		[probability:33, code:"DG106"/* 白金护手 */],
		[probability:33, code:"OA206"/* 精灵臂环 */],
		[probability:33, code:"OR109"/* 黑暗之戒 */],
		[probability:33, code:"OA109"/* 海蓝石链 */],
		[probability:33, code:"EC102"/* 回城卷 */],
		[probability:26, code:"DA110"/* 百裂铠 */],
		[probability:26, code:"DA210"/* 信徒披风 */],
		[probability:26, code:"WA108"/* 叼刚 清磐 */],
		[probability:26, code:"WC108"/* 矩记飘 府亥瘤 */],
		[probability:26, code:"WH109"/* 固萍绢扼捞飘 */],
		[probability:26, code:"WM109"/* 绊胶飘 */],
		[probability:26, code:"WP108"/* 血烟长矛 */],
		[probability:26, code:"WS110"/* 固胶飘 */],
		[probability:26, code:"WS210"/* 敲饭萍逞 家靛 */],
		[probability:26, code:"WT108"/* 金标 */],
		[probability:26, code:"OM107"/* 火星 */],
		[probability:26, code:"DB107"/* 冰火靴 */],
		[probability:26, code:"DS107"/* 金刚盾 */],
		[probability:26, code:"DG107"/* 百裂护手 */],
		[probability:26, code:"OA207"/* 乌金臂环 */],
		[probability:26, code:"OR110"/* 伏魔戒指 */],
		[probability:26, code:"OA110"/* 镇魂铃 */],
		[probability:26, code:"EC102"/* 回城卷 */],
		[probability:26, code:"EC105"/* 公会卷轴 */],
		[probability:10, code:"DA111"/* 重装机铠 */],
		[probability:10, code:"DA211"/* 大法师袍 */],
		[probability:10, code:"WA109"/* 破山斧 */],
		[probability:10, code:"WC109"/* 兽之斧刃 */],
		[probability:10, code:"WH110"/* 轩辕巨锤 */],
		[probability:10, code:"WM110"/* 圣者杖 */],
		[probability:10, code:"WP109"/* 单喉 荤捞靛 */],
		[probability:10, code:"WS111"/* 龙骨战弓 */],
		[probability:10, code:"WS211"/* 斩马刀 */],
		[probability:10, code:"WT109"/* 酒唱能促 */],
		[probability:10, code:"OM108"/* 阳炎 */],
		[probability:10, code:"DB108"/* 百战靴 */],
		[probability:10, code:"DS108"/* 赤龙焰盾 */],
		[probability:10, code:"DG108"/* 大地护手 */],
		[probability:10, code:"OA208"/* 百炼臂环 */],
		[probability:10, code:"OR111"/* 封印之戒 */],
		[probability:10, code:"OA111"/* 圣者之链 */],
		[probability:10, code:"OS105"/* 冰晶石 */],
		[probability:10, code:"EC105"/* 公会卷轴 */],
		[probability:10, code:"GP111"/* 大头蜘蛛水晶 */],
		[probability:2, code:"DA112"/* 战神宝铠 */],
		[probability:2, code:"DA212"/* 红莲战袍 */],
		[probability:2, code:"WA110"/* 定神斧 */],
		[probability:2, code:"WC110"/* 九头刺蛇爪 */],
		[probability:2, code:"WH111"/* 赤冥之锤 */],
		[probability:2, code:"WM111"/* 王者杖 */],
		[probability:2, code:"WP110"/* 白银之枪 */],
		[probability:2, code:"WS212"/* 嗜血屠魔剑 */],
		[probability:2, code:"WS112"/* 人马之辉 */],
		[probability:2, code:"WT110"/* 飞云标 */],
		[probability:2, code:"OM109"/* 暗月 */],
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
