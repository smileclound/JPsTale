monster = [
	ID:'52_direbee',
	Name:'蜜蜂精',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:52,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:620,
		// Attack Status
		Atk:[38, 48],
		AtkSpeed:9,
		Range:60,
		Rating:700,

		// Defence Status
		Flee:290,
		Absorb:10,
		Block:0,
		DamageStunPers:100,

		// Resistance
		Earth:20,
		Fire:20,
		Ice:20,// Water
		Lighting:30,// Wind
		Poison:50,
	],

	// AI
	AI:[
		Nature:0x80,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:7,
		Real_Sight:380,
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
		ClassCode:8,
		ArrowPosi:[-17, 70],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:1,
		Model:'char/monster/mondirebee/mondirebee.ini',
		Sound:0x000013E0,
	],

	// Drops
	AllSeeItem:false,
	Exp:7200,
	Quantity:1,
	drops:[
		[probability:3900, code:"NULL"/* Drops nothing */],
		[probability:3500, code:"GG101", value:[100, 190]/* Gold */],
		[probability:375, code:"PL103"/* 高级恢复生命药水 */],
		[probability:375, code:"PS103"/* 高级恢复耐力药水 */],
		[probability:375, code:"PL103"/* 高级恢复生命药水 */],
		[probability:375, code:"PM103"/* 高级恢复魔法药水 */],
		[probability:33, code:"DA108"/* 精制链铠 */],
		[probability:33, code:"DA208"/* 圣女袍 */],
		[probability:33, code:"WA107"/* 刺脊斧 */],
		[probability:33, code:"WC107"/* 平手刃 */],
		[probability:33, code:"WH107"/* 十字锤 */],
		[probability:33, code:"WM107"/* 天魔杖 */],
		[probability:33, code:"WP108"/* 血烟长矛 */],
		[probability:33, code:"WS109"/* 点金手弩 */],
		[probability:33, code:"WS209"/* 镇妖剑 */],
		[probability:33, code:"WT108"/* 金标 */],
		[probability:33, code:"DB107"/* 冰火靴 */],
		[probability:33, code:"DS107"/* 金刚盾 */],
		[probability:33, code:"OM107"/* 火星 */],
		[probability:33, code:"DG107"/* 百裂护手 */],
		[probability:33, code:"OA207"/* 乌金臂环 */],
		[probability:33, code:"PM102"/* 中级恢复魔法药水 */],
		[probability:33, code:"OR108"/* 翡翠戒指 */],
		[probability:33, code:"OA108"/* 靛青石链 */],
		[probability:33, code:"PL104"/* 顶级恢复生命药水 */],
		[probability:33, code:"GP103"/* 浮灵水晶 */],
		[probability:33, code:"EC105"/* 公会卷轴 */],
		[probability:17, code:"DA109"/* 黄铜战铠 */],
		[probability:17, code:"DA209"/* 学徒披风 */],
		[probability:17, code:"WA108"/* 叼刚 清磐 */],
		[probability:17, code:"WC108"/* 矩记飘 府亥瘤 */],
		[probability:17, code:"WH108"/* 圣光锤 */],
		[probability:17, code:"WM108"/* 公正之杖 */],
		[probability:17, code:"WP109"/* 单喉 荤捞靛 */],
		[probability:17, code:"WS110"/* 固胶飘 */],
		[probability:17, code:"WS209"/* 镇妖剑 */],
		[probability:17, code:"WT109"/* 酒唱能促 */],
		[probability:17, code:"PM103"/* 高级恢复魔法药水 */],
		[probability:17, code:"OR109"/* 黑暗之戒 */],
		[probability:17, code:"OA109"/* 海蓝石链 */],
		[probability:17, code:"PL104"/* 顶级恢复生命药水 */],
		[probability:17, code:"GP104"/* 刀斧手水晶 */],
		[probability:17, code:"OS105"/* 冰晶石 */],
		[probability:17, code:"EC105"/* 公会卷轴 */],
		[probability:5, code:"DA110"/* 百裂铠 */],
		[probability:5, code:"DA210"/* 信徒披风 */],
		[probability:5, code:"WA109"/* 破山斧 */],
		[probability:5, code:"WC109"/* 兽之斧刃 */],
		[probability:5, code:"WH109"/* 固萍绢扼捞飘 */],
		[probability:5, code:"WM109"/* 绊胶飘 */],
		[probability:5, code:"WP110"/* 白银之枪 */],
		[probability:5, code:"WS111"/* 龙骨战弓 */],
		[probability:5, code:"WS211"/* 斩马刀 */],
		[probability:5, code:"WT110"/* 飞云标 */],
		[probability:5, code:"EC102"/* 回城卷 */],
		[probability:5, code:"DB109"/* 大地靴 */],
		[probability:5, code:"DS109"/* 圣盾 */],
		[probability:5, code:"DG109"/* 神力护手 */],
		[probability:5, code:"OA209"/* 飞翼臂环 */],
		[probability:5, code:"PM104"/* 顶级恢复魔法药水 */],
		[probability:5, code:"OR110"/* 伏魔戒指 */],
		[probability:5, code:"OA110"/* 镇魂铃 */],
		[probability:5, code:"GP105"/* 魔剑士水晶 */],
		[probability:5, code:"EC105"/* 公会卷轴 */]
	],
	drops_more:[
	]
]
