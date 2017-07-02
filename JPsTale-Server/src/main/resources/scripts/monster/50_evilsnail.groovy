monster = [
	ID:'50_evilsnail',
	Name:'蜗牛怪',
	Clazz:0,// 1 BOSS
	Brood:0x92,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:50,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:620,
		// Attack Status
		Atk:[42, 47],
		AtkSpeed:6,
		Range:60,
		Rating:640,

		// Defence Status
		Flee:300,
		Absorb:10,
		Block:8,
		DamageStunPers:100,

		// Resistance
		Earth:30,
		Fire:0,
		Ice:50,// Water
		Lighting:50,// Wind
		Poison:50,
	],

	// AI
	AI:[
		Nature:0x80,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:7,
		Real_Sight:360,
		Talks:[],

		// Move Behavier
		Move_Speed:4,
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
		ArrowPosi:[-6, 130],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:-1,
		Model:'char/monster/esnail/esnail.INI',
		Sound:0x000010D0,
	],

	// Drops
	AllSeeItem:false,
	Exp:6600,
	Quantity:1,
	drops:[
		[probability:3700, code:"NULL"/* Drops nothing */],
		[probability:4000, code:"GG101", value:[160, 195]/* Gold */],
		[probability:300, code:"PL102"/* 中级恢复生命药水 */],
		[probability:300, code:"PL103"/* 高级恢复生命药水 */],
		[probability:300, code:"PM102"/* 中级恢复魔法药水 */],
		[probability:300, code:"PM103"/* 高级恢复魔法药水 */],
		[probability:50, code:"DA107"/* 全钢胸铠 */],
		[probability:50, code:"DA207"/* 紫电袍 */],
		[probability:50, code:"WA107"/* 刺脊斧 */],
		[probability:50, code:"WC107"/* 平手刃 */],
		[probability:50, code:"WH108"/* 圣光锤 */],
		[probability:50, code:"WM108"/* 公正之杖 */],
		[probability:50, code:"WP109"/* 单喉 荤捞靛 */],
		[probability:50, code:"WS109"/* 点金手弩 */],
		[probability:50, code:"WS209"/* 镇妖剑 */],
		[probability:50, code:"WT108"/* 金标 */],
		[probability:50, code:"DS106"/* 百炼盾 */],
		[probability:50, code:"OR108"/* 翡翠戒指 */],
		[probability:50, code:"OA108"/* 靛青石链 */],
		[probability:50, code:"EC105"/* 公会卷轴 */],
		[probability:20, code:"DA108"/* 精制链铠 */],
		[probability:20, code:"DA208"/* 圣女袍 */],
		[probability:20, code:"WA108"/* 叼刚 清磐 */],
		[probability:20, code:"WC108"/* 矩记飘 府亥瘤 */],
		[probability:20, code:"WH109"/* 固萍绢扼捞飘 */],
		[probability:20, code:"WM109"/* 绊胶飘 */],
		[probability:20, code:"WP110"/* 白银之枪 */],
		[probability:20, code:"WS110"/* 固胶飘 */],
		[probability:20, code:"WS210"/* 敲饭萍逞 家靛 */],
		[probability:20, code:"WT109"/* 酒唱能促 */],
		[probability:20, code:"DS107"/* 金刚盾 */],
		[probability:20, code:"OS106"/* 玄风石 */],
		[probability:20, code:"OR109"/* 黑暗之戒 */],
		[probability:20, code:"OA109"/* 海蓝石链 */],
		[probability:20, code:"EC105"/* 公会卷轴 */],
		[probability:7, code:"DA109"/* 黄铜战铠 */],
		[probability:7, code:"DA209"/* 学徒披风 */],
		[probability:7, code:"WA109"/* 破山斧 */],
		[probability:7, code:"WC109"/* 兽之斧刃 */],
		[probability:7, code:"WH110"/* 轩辕巨锤 */],
		[probability:7, code:"WM110"/* 圣者杖 */],
		[probability:7, code:"WP111"/* 屠龙枪 */],
		[probability:7, code:"WS111"/* 龙骨战弓 */],
		[probability:7, code:"WS211"/* 斩马刀 */],
		[probability:7, code:"WT110"/* 飞云标 */],
		[probability:7, code:"DS108"/* 赤龙焰盾 */],
		[probability:7, code:"EC102"/* 回城卷 */],
		[probability:7, code:"OR110"/* 伏魔戒指 */],
		[probability:7, code:"OA110"/* 镇魂铃 */]
	],
	drops_more:[
		[probability:70, code:"EC105"/* 公会卷轴 */]	]
]
