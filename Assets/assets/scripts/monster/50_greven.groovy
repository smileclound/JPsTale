monster = [
	ID:'50_greven',
	Name:'巨蜥怪',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:50,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:550,
		// Attack Status
		Atk:[40, 46],
		AtkSpeed:8,
		Range:70,
		Rating:700,

		// Defence Status
		Flee:240,
		Absorb:7,
		Block:0,
		DamageStunPers:100,

		// Resistance
		Earth:35,
		Fire:10,
		Ice:40,// Water
		Lighting:-10,// Wind
		Poison:50,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:9,
		Real_Sight:420,
		Talks:[],

		// Move Behavier
		Move_Speed:5,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:20,
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
		ArrowPosi:[-4, 20],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:3,
		Model:'char/monster/MONgreven/MONgreven.INI',
		Sound:0x000011A0,
	],

	// Drops
	AllSeeItem:false,
	Exp:6200,
	Quantity:1,
	drops:[
		[probability:4400, code:"NULL"/* Drops nothing */],
		[probability:3500, code:"GG101", value:[160, 195]/* Gold */],
		[probability:200, code:"PL102"/* 中级恢复生命药水 */],
		[probability:200, code:"PL103"/* 高级恢复生命药水 */],
		[probability:200, code:"PS103"/* 高级恢复耐力药水 */],
		[probability:200, code:"PM102"/* 中级恢复魔法药水 */],
		[probability:200, code:"PM103"/* 高级恢复魔法药水 */],
		[probability:43, code:"DA107"/* 全钢胸铠 */],
		[probability:43, code:"DA207"/* 紫电袍 */],
		[probability:43, code:"WA107"/* 刺脊斧 */],
		[probability:43, code:"WC107"/* 平手刃 */],
		[probability:43, code:"WH108"/* 圣光锤 */],
		[probability:43, code:"WM108"/* 公正之杖 */],
		[probability:43, code:"WP108"/* 血烟长矛 */],
		[probability:43, code:"WS109"/* 点金手弩 */],
		[probability:43, code:"WS209"/* 镇妖剑 */],
		[probability:43, code:"WT108"/* 金标 */],
		[probability:43, code:"DS107"/* 金刚盾 */],
		[probability:43, code:"OR108"/* 翡翠戒指 */],
		[probability:43, code:"OA108"/* 靛青石链 */],
		[probability:43, code:"EC105"/* 公会卷轴 */],
		[probability:43, code:"EC105"/* 公会卷轴 */],
		[probability:43, code:"EC102"/* 回城卷 */],
		[probability:17, code:"DA108"/* 精制链铠 */],
		[probability:17, code:"DA208"/* 圣女袍 */],
		[probability:17, code:"WA108"/* 叼刚 清磐 */],
		[probability:17, code:"WC109"/* 兽之斧刃 */],
		[probability:17, code:"WH109"/* 固萍绢扼捞飘 */],
		[probability:17, code:"WM109"/* 绊胶飘 */],
		[probability:17, code:"WP109"/* 单喉 荤捞靛 */],
		[probability:17, code:"WS110"/* 固胶飘 */],
		[probability:17, code:"WS210"/* 敲饭萍逞 家靛 */],
		[probability:17, code:"WT109"/* 酒唱能促 */],
		[probability:17, code:"DS108"/* 赤龙焰盾 */],
		[probability:17, code:"OS105"/* 冰晶石 */],
		[probability:17, code:"OS106"/* 玄风石 */],
		[probability:17, code:"OR109"/* 黑暗之戒 */],
		[probability:17, code:"OA109"/* 海蓝石链 */],
		[probability:17, code:"EC105"/* 公会卷轴 */],
		[probability:17, code:"EC105"/* 公会卷轴 */],
		[probability:6, code:"DA109"/* 黄铜战铠 */],
		[probability:6, code:"DA209"/* 学徒披风 */],
		[probability:6, code:"WA109"/* 破山斧 */],
		[probability:6, code:"WC109"/* 兽之斧刃 */],
		[probability:6, code:"WH110"/* 轩辕巨锤 */],
		[probability:6, code:"WM110"/* 圣者杖 */],
		[probability:6, code:"WP110"/* 白银之枪 */],
		[probability:6, code:"WS111"/* 龙骨战弓 */],
		[probability:6, code:"WS211"/* 斩马刀 */],
		[probability:6, code:"WT110"/* 飞云标 */],
		[probability:6, code:"DS109"/* 圣盾 */],
		[probability:6, code:"EC102"/* 回城卷 */],
		[probability:6, code:"PM102"/* 中级恢复魔法药水 */],
		[probability:6, code:"OR110"/* 伏魔戒指 */],
		[probability:6, code:"OA110"/* 镇魂铃 */]
	],
	drops_more:[
		[probability:80, code:"EC105"/* 公会卷轴 */]	]
]
