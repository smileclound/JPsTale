monster = [
	ID:'52_muffin',
	Name:'松饼怪',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:52,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:660,
		// Attack Status
		Atk:[42, 50],
		AtkSpeed:7,
		Range:60,
		Rating:750,

		// Defence Status
		Flee:280,
		Absorb:9,
		Block:0,
		DamageStunPers:100,

		// Resistance
		Earth:30,
		Fire:10,
		Ice:50,// Water
		Lighting:10,// Wind
		Poison:50,
	],

	// AI
	AI:[
		Nature:0x80,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:7,
		Real_Sight:360,
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
		ClassCode:7,
		ArrowPosi:[-6, 150],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:-1,
		Model:'char/monster/CaveMush/Cmush.ini',
		Sound:0x000013C0,
	],

	// Drops
	AllSeeItem:false,
	Exp:7100,
	Quantity:1,
	drops:[
		[probability:3900, code:"NULL"/* Drops nothing */],
		[probability:3500, code:"GG101", value:[100, 160]/* Gold */],
		[probability:375, code:"PL103"/* 高级恢复生命药水 */],
		[probability:375, code:"PS103"/* 高级恢复耐力药水 */],
		[probability:375, code:"PL103"/* 高级恢复生命药水 */],
		[probability:375, code:"PM103"/* 高级恢复魔法药水 */],
		[probability:36, code:"DA108"/* 精制链铠 */],
		[probability:36, code:"DA208"/* 圣女袍 */],
		[probability:36, code:"WA107"/* 刺脊斧 */],
		[probability:36, code:"WC107"/* 平手刃 */],
		[probability:36, code:"WH108"/* 圣光锤 */],
		[probability:36, code:"WM108"/* 公正之杖 */],
		[probability:36, code:"WP108"/* 血烟长矛 */],
		[probability:36, code:"WS109"/* 点金手弩 */],
		[probability:36, code:"WS209"/* 镇妖剑 */],
		[probability:36, code:"WT108"/* 金标 */],
		[probability:36, code:"DB107"/* 冰火靴 */],
		[probability:36, code:"DS107"/* 金刚盾 */],
		[probability:36, code:"OM107"/* 火星 */],
		[probability:36, code:"DG107"/* 百裂护手 */],
		[probability:36, code:"OA207"/* 乌金臂环 */],
		[probability:36, code:"OR108"/* 翡翠戒指 */],
		[probability:36, code:"OA108"/* 靛青石链 */],
		[probability:36, code:"GP103"/* 浮灵水晶 */],
		[probability:36, code:"EC105"/* 公会卷轴 */],
		[probability:18, code:"DA109"/* 黄铜战铠 */],
		[probability:18, code:"DA209"/* 学徒披风 */],
		[probability:18, code:"WA108"/* 叼刚 清磐 */],
		[probability:18, code:"WC108"/* 矩记飘 府亥瘤 */],
		[probability:18, code:"WH109"/* 固萍绢扼捞飘 */],
		[probability:18, code:"WM109"/* 绊胶飘 */],
		[probability:18, code:"WP109"/* 单喉 荤捞靛 */],
		[probability:18, code:"WS110"/* 固胶飘 */],
		[probability:18, code:"WS210"/* 敲饭萍逞 家靛 */],
		[probability:18, code:"WT109"/* 酒唱能促 */],
		[probability:18, code:"OR109"/* 黑暗之戒 */],
		[probability:18, code:"OA109"/* 海蓝石链 */],
		[probability:18, code:"OM108"/* 阳炎 */],
		[probability:18, code:"GP104"/* 刀斧手水晶 */],
		[probability:18, code:"OS105"/* 冰晶石 */],
		[probability:18, code:"EC105"/* 公会卷轴 */],
		[probability:5, code:"DA110"/* 百裂铠 */],
		[probability:5, code:"DA210"/* 信徒披风 */],
		[probability:5, code:"WA109"/* 破山斧 */],
		[probability:5, code:"WC109"/* 兽之斧刃 */],
		[probability:5, code:"WH110"/* 轩辕巨锤 */],
		[probability:5, code:"WM110"/* 圣者杖 */],
		[probability:5, code:"WP110"/* 白银之枪 */],
		[probability:5, code:"WS111"/* 龙骨战弓 */],
		[probability:5, code:"WS211"/* 斩马刀 */],
		[probability:5, code:"WT110"/* 飞云标 */],
		[probability:5, code:"EC102"/* 回城卷 */],
		[probability:5, code:"DB109"/* 大地靴 */],
		[probability:5, code:"DS109"/* 圣盾 */],
		[probability:5, code:"DG109"/* 神力护手 */],
		[probability:5, code:"OA209"/* 飞翼臂环 */],
		[probability:5, code:"OR110"/* 伏魔戒指 */],
		[probability:5, code:"OA110"/* 镇魂铃 */],
		[probability:5, code:"GP105"/* 魔剑士水晶 */],
		[probability:5, code:"EC105"/* 公会卷轴 */]
	],
	drops_more:[
	]
]
