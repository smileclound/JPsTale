monster = [
	ID:'a1-greven',
	Name:'绿蜥怪',
	Clazz:0,// 1 BOSS
	Brood:0x91,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:50,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:550,
		// Attack Status
		Atk:[35, 40],
		AtkSpeed:8,
		Range:70,
		Rating:700,

		// Defence Status
		Flee:240,
		Absorb:7,
		Block:0,
		DamageStunPers:100,

		// Resistance
		Earth:0,
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
	Exp:1400,
	Quantity:1,
	drops:[
		[probability:4220, code:"NULL"/* Drops nothing */],
		[probability:4600, code:"GG101", value:[55, 70]/* Gold */],
		[probability:45, code:"WA107"/* 刺脊斧 */],
		[probability:45, code:"WC107"/* 平手刃 */],
		[probability:45, code:"WH108"/* 圣光锤 */],
		[probability:45, code:"WM108"/* 公正之杖 */],
		[probability:45, code:"WP108"/* 血烟长矛 */],
		[probability:45, code:"WS109"/* 点金手弩 */],
		[probability:45, code:"WS209"/* 镇妖剑 */],
		[probability:45, code:"WT108"/* 金标 */],
		[probability:45, code:"DA108"/* 精制链铠 */],
		[probability:45, code:"DA208"/* 圣女袍 */],
		[probability:45, code:"DS107"/* 金刚盾 */],
		[probability:45, code:"OM108"/* 阳炎 */],
		[probability:20, code:"WA108"/* 叼刚 清磐 */],
		[probability:20, code:"WC108"/* 矩记飘 府亥瘤 */],
		[probability:20, code:"WH109"/* 固萍绢扼捞飘 */],
		[probability:20, code:"WM109"/* 绊胶飘 */],
		[probability:20, code:"WP109"/* 单喉 荤捞靛 */],
		[probability:20, code:"WS110"/* 固胶飘 */],
		[probability:20, code:"WS211"/* 斩马刀 */],
		[probability:20, code:"WT109"/* 酒唱能促 */],
		[probability:20, code:"DA109"/* 黄铜战铠 */],
		[probability:20, code:"DA209"/* 学徒披风 */],
		[probability:20, code:"DS108"/* 赤龙焰盾 */],
		[probability:20, code:"OM110"/* 蓝色星辰 */],
		[probability:10, code:"WA110"/* 定神斧 */],
		[probability:10, code:"WC110"/* 九头刺蛇爪 */],
		[probability:10, code:"WH110"/* 轩辕巨锤 */],
		[probability:10, code:"WM110"/* 圣者杖 */],
		[probability:10, code:"WP110"/* 白银之枪 */],
		[probability:10, code:"WS112"/* 人马之辉 */],
		[probability:10, code:"WS212"/* 嗜血屠魔剑 */],
		[probability:10, code:"WT111"/* 神标 */],
		[probability:10, code:"DA110"/* 百裂铠 */],
		[probability:10, code:"DA210"/* 信徒披风 */],
		[probability:10, code:"DS109"/* 圣盾 */],
		[probability:10, code:"OM112"/* 菱晶石 */],
		[probability:38, code:"DB114"/* 鹏翅之靴 */],
		[probability:38, code:"DG111"/* 黄铜护手 */],
		[probability:38, code:"OA209"/* 飞翼臂环 */],
		[probability:38, code:"DB115"/* 时空之靴 */],
		[probability:38, code:"DG113"/* 鲲鹏护手 */],
		[probability:38, code:"OA212"/* 紫焰臂环 */],
		[probability:5, code:"OA106"/* 碧云石链 */],
		[probability:5, code:"OR108"/* 翡翠戒指 */],
		[probability:5, code:"OA107"/* 水晶项链 */],
		[probability:5, code:"OR109"/* 黑暗之戒 */],
		[probability:5, code:"OA108"/* 靛青石链 */],
		[probability:5, code:"OS101"/* 星遗石 */],
		[probability:5, code:"OS102"/* 流云石 */],
		[probability:5, code:"OS103"/* 海精石 */],
		[probability:5, code:"OS104"/* 天仪石 */]
	],
	drops_more:[
	]
]
