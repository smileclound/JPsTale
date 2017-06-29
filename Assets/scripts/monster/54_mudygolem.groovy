monster = [
	ID:'54_mudygolem',
	Name:'泥妖',
	Clazz:0,// 1 BOSS
	Brood:0x92,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:54,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:900,
		// Attack Status
		Atk:[40, 46],
		AtkSpeed:6,
		Range:60,
		Rating:800,

		// Defence Status
		Flee:330,
		Absorb:14,
		Block:0,
		DamageStunPers:100,

		// Resistance
		Earth:100,
		Fire:50,
		Ice:50,// Water
		Lighting:50,// Wind
		Poison:100,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:7,
		Real_Sight:340,
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
		ClassCode:0,
		ArrowPosi:[2, 80],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:-1,
		Model:'char/monster/Monplant/MudyGolem.INI',
		Sound:0x00001040,
	],

	// Drops
	AllSeeItem:false,
	Exp:9500,
	Quantity:1,
	drops:[
		[probability:3200, code:"NULL"/* Drops nothing */],
		[probability:4000, code:"GG101", value:[100, 160]/* Gold */],
		[probability:216, code:"PL102"/* 中级恢复生命药水 */],
		[probability:216, code:"PL103"/* 高级恢复生命药水 */],
		[probability:216, code:"PS103"/* 高级恢复耐力药水 */],
		[probability:216, code:"PM103"/* 高级恢复魔法药水 */],
		[probability:216, code:"PL103"/* 高级恢复生命药水 */],
		[probability:216, code:"PM103"/* 高级恢复魔法药水 */],
		[probability:55, code:"DA108"/* 精制链铠 */],
		[probability:55, code:"DA208"/* 圣女袍 */],
		[probability:55, code:"WA107"/* 刺脊斧 */],
		[probability:55, code:"WC107"/* 平手刃 */],
		[probability:55, code:"WH108"/* 圣光锤 */],
		[probability:55, code:"WM108"/* 公正之杖 */],
		[probability:55, code:"WP108"/* 血烟长矛 */],
		[probability:55, code:"WS108"/* 巨弩 */],
		[probability:55, code:"WS209"/* 镇妖剑 */],
		[probability:55, code:"WT107"/* 天命标 */],
		[probability:55, code:"DB107"/* 冰火靴 */],
		[probability:55, code:"DS107"/* 金刚盾 */],
		[probability:55, code:"OM107"/* 火星 */],
		[probability:55, code:"DG107"/* 百裂护手 */],
		[probability:55, code:"OA207"/* 乌金臂环 */],
		[probability:55, code:"OR109"/* 黑暗之戒 */],
		[probability:55, code:"OA109"/* 海蓝石链 */],
		[probability:55, code:"EC105"/* 公会卷轴 */],
		[probability:20, code:"DA109"/* 黄铜战铠 */],
		[probability:20, code:"DA209"/* 学徒披风 */],
		[probability:20, code:"WA108"/* 叼刚 清磐 */],
		[probability:20, code:"WC108"/* 矩记飘 府亥瘤 */],
		[probability:20, code:"WH109"/* 固萍绢扼捞飘 */],
		[probability:20, code:"WM109"/* 绊胶飘 */],
		[probability:20, code:"WP109"/* 单喉 荤捞靛 */],
		[probability:20, code:"WS110"/* 固胶飘 */],
		[probability:20, code:"WS210"/* 敲饭萍逞 家靛 */],
		[probability:20, code:"WT108"/* 金标 */],
		[probability:20, code:"OS105"/* 冰晶石 */],
		[probability:20, code:"OS106"/* 玄风石 */],
		[probability:20, code:"DB108"/* 百战靴 */],
		[probability:20, code:"DS108"/* 赤龙焰盾 */],
		[probability:20, code:"OM108"/* 阳炎 */],
		[probability:20, code:"DG108"/* 大地护手 */],
		[probability:20, code:"OA208"/* 百炼臂环 */],
		[probability:20, code:"PM102"/* 中级恢复魔法药水 */],
		[probability:20, code:"OR110"/* 伏魔戒指 */],
		[probability:20, code:"OA110"/* 镇魂铃 */],
		[probability:5, code:"DA110"/* 百裂铠 */],
		[probability:5, code:"DA210"/* 信徒披风 */],
		[probability:5, code:"WA109"/* 破山斧 */],
		[probability:5, code:"WC109"/* 兽之斧刃 */],
		[probability:5, code:"WH110"/* 轩辕巨锤 */],
		[probability:5, code:"WM110"/* 圣者杖 */],
		[probability:5, code:"WP110"/* 白银之枪 */],
		[probability:5, code:"WS211"/* 斩马刀 */],
		[probability:5, code:"WT109"/* 酒唱能促 */],
		[probability:5, code:"OS106"/* 玄风石 */],
		[probability:5, code:"DB109"/* 大地靴 */],
		[probability:5, code:"DS109"/* 圣盾 */],
		[probability:5, code:"OM109"/* 暗月 */],
		[probability:5, code:"DG109"/* 神力护手 */],
		[probability:5, code:"OA209"/* 飞翼臂环 */],
		[probability:5, code:"PM103"/* 高级恢复魔法药水 */],
		[probability:5, code:"EC102"/* 回城卷 */]
	],
	drops_more:[
	]
]
