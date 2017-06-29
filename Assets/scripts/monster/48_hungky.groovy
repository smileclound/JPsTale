monster = [
	ID:'48_hungky',
	Name:'小绿人',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:48,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:750,
		// Attack Status
		Atk:[52, 57],
		AtkSpeed:7,
		Range:60,
		Rating:800,

		// Defence Status
		Flee:300,
		Absorb:10,
		Block:5,
		DamageStunPers:100,

		// Resistance
		Earth:50,
		Fire:50,
		Ice:50,// Water
		Lighting:20,// Wind
		Poison:50,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:7,
		Real_Sight:400,
		Talks:[],

		// Move Behavier
		Move_Speed:4,
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
		ArrowPosi:[-3, 40],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:1,
		Model:'char/monster/Monbabyhulk/Monbabyhulk.ini',
		Sound:0x00001330,
	],

	// Drops
	AllSeeItem:false,
	Exp:5000,
	Quantity:1,
	drops:[
		[probability:4000, code:"NULL"/* Drops nothing */],
		[probability:3400, code:"GG101", value:[140, 200]/* Gold */],
		[probability:500, code:"PL103"/* 高级恢复生命药水 */],
		[probability:500, code:"PS103"/* 高级恢复耐力药水 */],
		[probability:500, code:"PM103"/* 高级恢复魔法药水 */],
		[probability:38, code:"DA107"/* 全钢胸铠 */],
		[probability:38, code:"DA207"/* 紫电袍 */],
		[probability:38, code:"WA107"/* 刺脊斧 */],
		[probability:38, code:"WC107"/* 平手刃 */],
		[probability:38, code:"WH108"/* 圣光锤 */],
		[probability:38, code:"WM108"/* 公正之杖 */],
		[probability:38, code:"WP108"/* 血烟长矛 */],
		[probability:38, code:"WS109"/* 点金手弩 */],
		[probability:38, code:"WS209"/* 镇妖剑 */],
		[probability:38, code:"WT108"/* 金标 */],
		[probability:38, code:"OM107"/* 火星 */],
		[probability:38, code:"DS107"/* 金刚盾 */],
		[probability:38, code:"DB107"/* 冰火靴 */],
		[probability:38, code:"DG107"/* 百裂护手 */],
		[probability:38, code:"OA207"/* 乌金臂环 */],
		[probability:38, code:"OR107"/* 法师戒指 */],
		[probability:38, code:"OA107"/* 水晶项链 */],
		[probability:38, code:"EC105"/* 公会卷轴 */],
		[probability:15, code:"DA108"/* 精制链铠 */],
		[probability:15, code:"DA208"/* 圣女袍 */],
		[probability:15, code:"WA108"/* 叼刚 清磐 */],
		[probability:15, code:"WC108"/* 矩记飘 府亥瘤 */],
		[probability:15, code:"WH109"/* 固萍绢扼捞飘 */],
		[probability:15, code:"WM109"/* 绊胶飘 */],
		[probability:15, code:"WP109"/* 单喉 荤捞靛 */],
		[probability:15, code:"WS109"/* 点金手弩 */],
		[probability:15, code:"WS209"/* 镇妖剑 */],
		[probability:15, code:"WT109"/* 酒唱能促 */],
		[probability:15, code:"OM108"/* 阳炎 */],
		[probability:15, code:"DS108"/* 赤龙焰盾 */],
		[probability:15, code:"DB108"/* 百战靴 */],
		[probability:15, code:"DG108"/* 大地护手 */],
		[probability:15, code:"OA208"/* 百炼臂环 */],
		[probability:15, code:"OR108"/* 翡翠戒指 */],
		[probability:15, code:"OA108"/* 靛青石链 */],
		[probability:15, code:"OS105"/* 冰晶石 */],
		[probability:15, code:"OS105"/* 冰晶石 */],
		[probability:15, code:"EC105"/* 公会卷轴 */],
		[probability:5, code:"DA109"/* 黄铜战铠 */],
		[probability:5, code:"DA209"/* 学徒披风 */],
		[probability:5, code:"WA109"/* 破山斧 */],
		[probability:5, code:"WC109"/* 兽之斧刃 */],
		[probability:5, code:"WH110"/* 轩辕巨锤 */],
		[probability:5, code:"WM110"/* 圣者杖 */],
		[probability:5, code:"WP110"/* 白银之枪 */],
		[probability:5, code:"WS110"/* 固胶飘 */],
		[probability:5, code:"WS210"/* 敲饭萍逞 家靛 */],
		[probability:5, code:"WT110"/* 飞云标 */],
		[probability:5, code:"OM109"/* 暗月 */],
		[probability:5, code:"DS109"/* 圣盾 */],
		[probability:5, code:"DB109"/* 大地靴 */],
		[probability:5, code:"DG109"/* 神力护手 */],
		[probability:5, code:"OA209"/* 飞翼臂环 */],
		[probability:5, code:"OR109"/* 黑暗之戒 */],
		[probability:5, code:"OA109"/* 海蓝石链 */],
		[probability:5, code:"OS105"/* 冰晶石 */],
		[probability:5, code:"OS106"/* 玄风石 */],
		[probability:5, code:"EC105"/* 公会卷轴 */]
	],
	drops_more:[
	]
]
