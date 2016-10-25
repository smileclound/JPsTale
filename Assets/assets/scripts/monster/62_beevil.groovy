monster = [
	ID:'62_beevil',
	Name:'蜂王',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:62,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:1300,
		// Attack Status
		Atk:[43, 58],
		AtkSpeed:9,
		Range:70,
		Rating:980,

		// Defence Status
		Flee:480,
		Absorb:8,
		Block:0,
		DamageStunPers:100,

		// Resistance
		Earth:10,
		Fire:10,
		Ice:10,// Water
		Lighting:10,// Wind
		Poison:50,
	],

	// AI
	AI:[
		Nature:0x80,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:9,
		Real_Sight:400,
		Talks:[],

		// Move Behavier
		Move_Speed:3,
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
		ClassCode:27,
		ArrowPosi:[-28, 60],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:1,
		Model:'char/monster/monbeechief/monbeechief.ini',
		Sound:0x000013E0,
	],

	// Drops
	AllSeeItem:false,
	Exp:15000,
	Quantity:1,
	drops:[
		[probability:2400, code:"NULL"/* Drops nothing */],
		[probability:5150, code:"GG101", value:[200, 250]/* Gold */],
		[probability:300, code:"PL104"/* 顶级恢复生命药水 */],
		[probability:300, code:"PS104"/* 顶级恢复耐力药水 */],
		[probability:300, code:"PL104"/* 顶级恢复生命药水 */],
		[probability:300, code:"PM103"/* 高级恢复魔法药水 */],
		[probability:42, code:"DA110"/* 百裂铠 */],
		[probability:42, code:"DA210"/* 信徒披风 */],
		[probability:42, code:"WA107"/* 刺脊斧 */],
		[probability:42, code:"WC108"/* 矩记飘 府亥瘤 */],
		[probability:42, code:"WH107"/* 十字锤 */],
		[probability:42, code:"WM108"/* 公正之杖 */],
		[probability:42, code:"WP107"/* 战神之镰 */],
		[probability:42, code:"WS108"/* 巨弩 */],
		[probability:42, code:"WS209"/* 镇妖剑 */],
		[probability:42, code:"WT107"/* 天命标 */],
		[probability:42, code:"OM108"/* 阳炎 */],
		[probability:42, code:"OR108"/* 翡翠戒指 */],
		[probability:42, code:"OA108"/* 靛青石链 */],
		[probability:42, code:"EC105"/* 公会卷轴 */],
		[probability:42, code:"GP105"/* 魔剑士水晶 */],
		[probability:42, code:"GP106"/* 火灵王水晶 */],
		[probability:42, code:"GP107"/* 独角兽王水晶 */],
		[probability:42, code:"GP108"/* 绿巨人水晶 */],
		[probability:42, code:"GP111"/* 大头蜘蛛水晶 */],
		[probability:42, code:"OS105"/* 冰晶石 */],
		[probability:15, code:"DA111"/* 重装机铠 */],
		[probability:15, code:"DA211"/* 大法师袍 */],
		[probability:15, code:"WA108"/* 叼刚 清磐 */],
		[probability:15, code:"WC108"/* 矩记飘 府亥瘤 */],
		[probability:15, code:"WH109"/* 固萍绢扼捞飘 */],
		[probability:15, code:"WM109"/* 绊胶飘 */],
		[probability:15, code:"WP108"/* 血烟长矛 */],
		[probability:15, code:"WS109"/* 点金手弩 */],
		[probability:15, code:"WS210"/* 敲饭萍逞 家靛 */],
		[probability:15, code:"WT108"/* 金标 */],
		[probability:15, code:"OM109"/* 暗月 */],
		[probability:15, code:"DB108"/* 百战靴 */],
		[probability:15, code:"DS108"/* 赤龙焰盾 */],
		[probability:15, code:"OM108"/* 阳炎 */],
		[probability:15, code:"DG108"/* 大地护手 */],
		[probability:15, code:"OA208"/* 百炼臂环 */],
		[probability:15, code:"PM102"/* 中级恢复魔法药水 */],
		[probability:15, code:"EC102"/* 回城卷 */],
		[probability:15, code:"OS107"/* 水晶石 */],
		[probability:15, code:"OR109"/* 黑暗之戒 */],
		[probability:15, code:"OA109"/* 海蓝石链 */],
		[probability:15, code:"OS106"/* 玄风石 */],
		[probability:2, code:"DA112"/* 战神宝铠 */],
		[probability:2, code:"DA212"/* 红莲战袍 */],
		[probability:2, code:"WA109"/* 破山斧 */],
		[probability:2, code:"WC109"/* 兽之斧刃 */],
		[probability:2, code:"WH110"/* 轩辕巨锤 */],
		[probability:2, code:"WM110"/* 圣者杖 */],
		[probability:2, code:"WP110"/* 白银之枪 */],
		[probability:2, code:"WS110"/* 固胶飘 */],
		[probability:2, code:"WS211"/* 斩马刀 */],
		[probability:2, code:"WT109"/* 酒唱能促 */],
		[probability:2, code:"OM110"/* 蓝色星辰 */],
		[probability:2, code:"DB109"/* 大地靴 */],
		[probability:2, code:"DS109"/* 圣盾 */],
		[probability:2, code:"OM109"/* 暗月 */],
		[probability:2, code:"DG109"/* 神力护手 */],
		[probability:2, code:"OA209"/* 飞翼臂环 */],
		[probability:2, code:"PM103"/* 高级恢复魔法药水 */],
		[probability:2, code:"EC102"/* 回城卷 */],
		[probability:2, code:"OR110"/* 伏魔戒指 */],
		[probability:2, code:"OA110"/* 镇魂铃 */]
	],
	drops_more:[
		[probability:70, code:"EC105"/* 公会卷轴 */]	]
]
