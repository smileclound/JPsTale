monster = [
	ID:'53_titan',
	Name:'泰坦巨人',
	Clazz:0,// 1 BOSS
	Brood:0x92,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:53,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:750,
		// Attack Status
		Atk:[40, 50],
		AtkSpeed:6,
		Range:70,
		Rating:800,

		// Defence Status
		Flee:340,
		Absorb:10,
		Block:8,
		DamageStunPers:100,

		// Resistance
		Earth:40,
		Fire:40,
		Ice:40,// Water
		Lighting:30,// Wind
		Poison:50,
	],

	// AI
	AI:[
		Nature:0x80,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:7,
		Real_Sight:410,
		Talks:[],

		// Move Behavier
		Move_Speed:3,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:20,
		SkillDamage:[0, 0],
		SkillDistance:0,
		SkillRange:0,
		SkillRating:0,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:15,
		PotionCount:1,
	],

	Looks:[
		ClassCode:6,
		ArrowPosi:[0, 10],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:3,
		Model:'char/monster/Monbagon/Monbagon-boss.ini',
		Sound:0x00001090,
	],

	// Drops
	AllSeeItem:false,
	Exp:8100,
	Quantity:1,
	drops:[
		[probability:4450, code:"NULL"/* Drops nothing */],
		[probability:3000, code:"GG101", value:[100, 190]/* Gold */],
		[probability:300, code:"PL103"/* 高级恢复生命药水 */],
		[probability:300, code:"PS103"/* 高级恢复耐力药水 */],
		[probability:300, code:"PL103"/* 高级恢复生命药水 */],
		[probability:300, code:"PS103"/* 高级恢复耐力药水 */],
		[probability:300, code:"PM103"/* 高级恢复魔法药水 */],
		[probability:34, code:"DA108"/* 精制链铠 */],
		[probability:34, code:"DA208"/* 圣女袍 */],
		[probability:34, code:"WA107"/* 刺脊斧 */],
		[probability:34, code:"WC107"/* 平手刃 */],
		[probability:34, code:"WH108"/* 圣光锤 */],
		[probability:34, code:"WM108"/* 公正之杖 */],
		[probability:34, code:"WP108"/* 血烟长矛 */],
		[probability:34, code:"WS109"/* 点金手弩 */],
		[probability:34, code:"WS209"/* 镇妖剑 */],
		[probability:34, code:"WT108"/* 金标 */],
		[probability:34, code:"OS105"/* 冰晶石 */],
		[probability:34, code:"DB107"/* 冰火靴 */],
		[probability:34, code:"DS107"/* 金刚盾 */],
		[probability:34, code:"OM107"/* 火星 */],
		[probability:34, code:"DG107"/* 百裂护手 */],
		[probability:34, code:"OA207"/* 乌金臂环 */],
		[probability:34, code:"PM102"/* 中级恢复魔法药水 */],
		[probability:34, code:"OR108"/* 翡翠戒指 */],
		[probability:34, code:"OA108"/* 靛青石链 */],
		[probability:34, code:"PL104"/* 顶级恢复生命药水 */],
		[probability:34, code:"GP103"/* 浮灵水晶 */],
		[probability:34, code:"EC105"/* 公会卷轴 */],
		[probability:11, code:"DA109"/* 黄铜战铠 */],
		[probability:11, code:"DA209"/* 学徒披风 */],
		[probability:11, code:"WA108"/* 叼刚 清磐 */],
		[probability:11, code:"WC108"/* 矩记飘 府亥瘤 */],
		[probability:11, code:"WH109"/* 固萍绢扼捞飘 */],
		[probability:11, code:"WM109"/* 绊胶飘 */],
		[probability:11, code:"WP109"/* 单喉 荤捞靛 */],
		[probability:11, code:"WS109"/* 点金手弩 */],
		[probability:11, code:"WS210"/* 敲饭萍逞 家靛 */],
		[probability:11, code:"WT109"/* 酒唱能促 */],
		[probability:11, code:"PM103"/* 高级恢复魔法药水 */],
		[probability:11, code:"OR109"/* 黑暗之戒 */],
		[probability:11, code:"OA109"/* 海蓝石链 */],
		[probability:11, code:"PL104"/* 顶级恢复生命药水 */],
		[probability:11, code:"OM108"/* 阳炎 */],
		[probability:11, code:"GP104"/* 刀斧手水晶 */],
		[probability:11, code:"OS104"/* 天仪石 */],
		[probability:11, code:"EC105"/* 公会卷轴 */],
		[probability:5, code:"DA110"/* 百裂铠 */],
		[probability:5, code:"DA210"/* 信徒披风 */],
		[probability:5, code:"WA109"/* 破山斧 */],
		[probability:5, code:"WC109"/* 兽之斧刃 */],
		[probability:5, code:"WH110"/* 轩辕巨锤 */],
		[probability:5, code:"WM110"/* 圣者杖 */],
		[probability:5, code:"WP110"/* 白银之枪 */],
		[probability:5, code:"WS110"/* 固胶飘 */],
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
		[probability:5, code:"EC105"/* 公会卷轴 */],
		[probability:5, code:"GP109"/* 神秘水晶 */]
	],
	drops_more:[
	]
]
