monster = [
	ID:'69_doomguard',
	Name:'黑暗爵士',
	Clazz:0,// 1 BOSS
	Brood:0x92,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:69,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:1530,
		// Attack Status
		Atk:[44, 56],
		AtkSpeed:8,
		Range:70,
		Rating:1000,

		// Defence Status
		Flee:550,
		Absorb:17,
		Block:0,
		DamageStunPers:100,

		// Resistance
		Earth:50,
		Fire:10,
		Ice:35,// Water
		Lighting:0,// Wind
		Poison:100,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:8,
		Real_Sight:380,
		Talks:[],

		// Move Behavier
		Move_Speed:1,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:0,
		SkillDamage:[60, 70],
		SkillDistance:80,
		SkillRange:60,
		SkillRating:15,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:0,
		PotionCount:0,
	],

	Looks:[
		ClassCode:20,
		ArrowPosi:[0, 50],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:-1,
		Model:'char/monster/MonSGuard/Mondoomguard.INI',
		Sound:0x00001460,
	],

	// Drops
	AllSeeItem:false,
	Exp:17500,
	Quantity:1,
	drops:[
		[probability:3500, code:"NULL"/* Drops nothing */],
		[probability:3100, code:"GG101", value:[120, 180]/* Gold */],
		[probability:360, code:"PL103"/* 高级恢复生命药水 */],
		[probability:360, code:"PS103"/* 高级恢复耐力药水 */],
		[probability:360, code:"PL103"/* 高级恢复生命药水 */],
		[probability:360, code:"PS103"/* 高级恢复耐力药水 */],
		[probability:360, code:"PM103"/* 高级恢复魔法药水 */],
		[probability:47, code:"DA110"/* 百裂铠 */],
		[probability:47, code:"DA210"/* 信徒披风 */],
		[probability:47, code:"WA107"/* 刺脊斧 */],
		[probability:47, code:"WC107"/* 平手刃 */],
		[probability:47, code:"WH107"/* 十字锤 */],
		[probability:47, code:"WM107"/* 天魔杖 */],
		[probability:47, code:"WP107"/* 战神之镰 */],
		[probability:47, code:"WS109"/* 点金手弩 */],
		[probability:47, code:"WS209"/* 镇妖剑 */],
		[probability:47, code:"WT108"/* 金标 */],
		[probability:47, code:"OM108"/* 阳炎 */],
		[probability:47, code:"DB107"/* 冰火靴 */],
		[probability:47, code:"DS107"/* 金刚盾 */],
		[probability:47, code:"DG107"/* 百裂护手 */],
		[probability:47, code:"OA207"/* 乌金臂环 */],
		[probability:47, code:"PM102"/* 中级恢复魔法药水 */],
		[probability:47, code:"OR109"/* 黑暗之戒 */],
		[probability:47, code:"OA109"/* 海蓝石链 */],
		[probability:47, code:"GP106"/* 火灵王水晶 */],
		[probability:47, code:"EC105"/* 公会卷轴 */],
		[probability:20, code:"DA111"/* 重装机铠 */],
		[probability:20, code:"DA211"/* 大法师袍 */],
		[probability:20, code:"WA108"/* 叼刚 清磐 */],
		[probability:20, code:"WC108"/* 矩记飘 府亥瘤 */],
		[probability:20, code:"WH109"/* 固萍绢扼捞飘 */],
		[probability:20, code:"WM108"/* 公正之杖 */],
		[probability:20, code:"WP109"/* 单喉 荤捞靛 */],
		[probability:20, code:"WS110"/* 固胶飘 */],
		[probability:20, code:"WS210"/* 敲饭萍逞 家靛 */],
		[probability:20, code:"OM109"/* 暗月 */],
		[probability:20, code:"OS105"/* 冰晶石 */],
		[probability:20, code:"DB108"/* 百战靴 */],
		[probability:20, code:"DS108"/* 赤龙焰盾 */],
		[probability:20, code:"DG108"/* 大地护手 */],
		[probability:20, code:"OA208"/* 百炼臂环 */],
		[probability:20, code:"PM103"/* 高级恢复魔法药水 */],
		[probability:20, code:"OR110"/* 伏魔戒指 */],
		[probability:20, code:"OA110"/* 镇魂铃 */],
		[probability:20, code:"GP106"/* 火灵王水晶 */],
		[probability:20, code:"EC105"/* 公会卷轴 */],
		[probability:10, code:"DA112"/* 战神宝铠 */],
		[probability:10, code:"DA212"/* 红莲战袍 */],
		[probability:10, code:"WA109"/* 破山斧 */],
		[probability:10, code:"WC109"/* 兽之斧刃 */],
		[probability:10, code:"WH110"/* 轩辕巨锤 */],
		[probability:10, code:"WM109"/* 绊胶飘 */],
		[probability:10, code:"WP110"/* 白银之枪 */],
		[probability:10, code:"WS111"/* 龙骨战弓 */],
		[probability:10, code:"WS211"/* 斩马刀 */],
		[probability:10, code:"WT110"/* 飞云标 */],
		[probability:10, code:"OM110"/* 蓝色星辰 */],
		[probability:10, code:"DB109"/* 大地靴 */],
		[probability:10, code:"DS109"/* 圣盾 */],
		[probability:10, code:"DG109"/* 神力护手 */],
		[probability:10, code:"OA209"/* 飞翼臂环 */],
		[probability:10, code:"PM104"/* 顶级恢复魔法药水 */],
		[probability:10, code:"OR111"/* 封印之戒 */],
		[probability:10, code:"OA111"/* 圣者之链 */],
		[probability:10, code:"GP106"/* 火灵王水晶 */],
		[probability:2, code:"DA113"/* 虎刹魔铠 */],
		[probability:2, code:"DA213"/* 幽绿之眼 */],
		[probability:2, code:"WA110"/* 定神斧 */],
		[probability:2, code:"WC110"/* 九头刺蛇爪 */],
		[probability:2, code:"WH111"/* 赤冥之锤 */],
		[probability:2, code:"WM110"/* 圣者杖 */],
		[probability:2, code:"WP111"/* 屠龙枪 */],
		[probability:2, code:"WS112"/* 人马之辉 */],
		[probability:2, code:"WS212"/* 嗜血屠魔剑 */],
		[probability:2, code:"WT111"/* 神标 */],
		[probability:2, code:"OM111"/* 淬火乌晶 */],
		[probability:2, code:"DB110"/* 地火战靴 */],
		[probability:2, code:"DS110"/* 宙斯盾 */],
		[probability:2, code:"DG110"/* 火云护手 */],
		[probability:2, code:"OA210"/* 百川流水臂环 */],
		[probability:2, code:"PM104"/* 顶级恢复魔法药水 */],
		[probability:2, code:"OR111"/* 封印之戒 */],
		[probability:2, code:"OA111"/* 圣者之链 */],
		[probability:2, code:"GP107"/* 独角兽王水晶 */]
	],
	drops_more:[
	]
]
