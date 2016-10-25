monster = [
	ID:'54_skeletonknight',
	Name:'骷髅爵士',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:54,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:840,
		// Attack Status
		Atk:[36, 44],
		AtkSpeed:8,
		Range:65,
		Rating:850,

		// Defence Status
		Flee:340,
		Absorb:14,
		Block:11,
		DamageStunPers:100,

		// Resistance
		Earth:20,
		Fire:0,
		Ice:25,// Water
		Lighting:0,// Wind
		Poison:100,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:7,
		Real_Sight:370,
		Talks:[],

		// Move Behavier
		Move_Speed:4,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:15,
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
		ClassCode:16,
		ArrowPosi:[0, 38],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:1,
		Model:'char/monster/MonSKnight/MonSKnight.ini',
		Sound:0x000011D0,
	],

	// Drops
	AllSeeItem:false,
	Exp:8500,
	Quantity:1,
	drops:[
		[probability:3400, code:"NULL"/* Drops nothing */],
		[probability:3400, code:"GG101", value:[100, 160]/* Gold */],
		[probability:280, code:"PL102"/* 中级恢复生命药水 */],
		[probability:280, code:"PL103"/* 高级恢复生命药水 */],
		[probability:280, code:"PS102"/* 中级恢复耐力药水 */],
		[probability:280, code:"PS103"/* 高级恢复耐力药水 */],
		[probability:280, code:"PL103"/* 高级恢复生命药水 */],
		[probability:52, code:"DA107"/* 全钢胸铠 */],
		[probability:52, code:"DA207"/* 紫电袍 */],
		[probability:52, code:"wa106"/* 凯旋 */],
		[probability:52, code:"WC106"/* 狼牙刃 */],
		[probability:52, code:"WH107"/* 十字锤 */],
		[probability:52, code:"WM107"/* 天魔杖 */],
		[probability:52, code:"WP107"/* 战神之镰 */],
		[probability:52, code:"WS108"/* 巨弩 */],
		[probability:52, code:"WS208"/* 圣殿武士剑 */],
		[probability:52, code:"WT106"/* 精灵标 */],
		[probability:52, code:"DB106"/* 黄金靴 */],
		[probability:52, code:"DS106"/* 百炼盾 */],
		[probability:52, code:"OM106"/* 水星 */],
		[probability:52, code:"DG106"/* 白金护手 */],
		[probability:52, code:"OA206"/* 精灵臂环 */],
		[probability:52, code:"OR108"/* 翡翠戒指 */],
		[probability:52, code:"OA108"/* 靛青石链 */],
		[probability:31, code:"DA108"/* 精制链铠 */],
		[probability:31, code:"DA208"/* 圣女袍 */],
		[probability:31, code:"WA107"/* 刺脊斧 */],
		[probability:31, code:"WA107"/* 刺脊斧 */],
		[probability:31, code:"WH108"/* 圣光锤 */],
		[probability:31, code:"WM108"/* 公正之杖 */],
		[probability:31, code:"WP108"/* 血烟长矛 */],
		[probability:31, code:"WS209"/* 镇妖剑 */],
		[probability:31, code:"WS109"/* 点金手弩 */],
		[probability:31, code:"WT107"/* 天命标 */],
		[probability:31, code:"OS105"/* 冰晶石 */],
		[probability:31, code:"DB107"/* 冰火靴 */],
		[probability:31, code:"DS107"/* 金刚盾 */],
		[probability:31, code:"OM107"/* 火星 */],
		[probability:31, code:"DG107"/* 百裂护手 */],
		[probability:31, code:"OA207"/* 乌金臂环 */],
		[probability:31, code:"OR109"/* 黑暗之戒 */],
		[probability:31, code:"OA109"/* 海蓝石链 */],
		[probability:31, code:"EC105"/* 公会卷轴 */],
		[probability:10, code:"DA109"/* 黄铜战铠 */],
		[probability:10, code:"DA209"/* 学徒披风 */],
		[probability:10, code:"WA108"/* 叼刚 清磐 */],
		[probability:10, code:"WC108"/* 矩记飘 府亥瘤 */],
		[probability:10, code:"WH109"/* 固萍绢扼捞飘 */],
		[probability:10, code:"WM109"/* 绊胶飘 */],
		[probability:10, code:"WP109"/* 单喉 荤捞靛 */],
		[probability:10, code:"WS210"/* 敲饭萍逞 家靛 */],
		[probability:10, code:"WS110"/* 固胶飘 */],
		[probability:10, code:"WT108"/* 金标 */],
		[probability:10, code:"OS105"/* 冰晶石 */],
		[probability:10, code:"DB108"/* 百战靴 */],
		[probability:10, code:"DS108"/* 赤龙焰盾 */],
		[probability:10, code:"OM108"/* 阳炎 */],
		[probability:10, code:"DG108"/* 大地护手 */],
		[probability:10, code:"OA208"/* 百炼臂环 */],
		[probability:10, code:"PM102"/* 中级恢复魔法药水 */],
		[probability:10, code:"OR110"/* 伏魔戒指 */],
		[probability:10, code:"OA110"/* 镇魂铃 */],
		[probability:10, code:"GP111"/* 大头蜘蛛水晶 */],
		[probability:5, code:"DA110"/* 百裂铠 */],
		[probability:5, code:"DA210"/* 信徒披风 */],
		[probability:5, code:"WA109"/* 破山斧 */],
		[probability:5, code:"WC109"/* 兽之斧刃 */],
		[probability:5, code:"WH110"/* 轩辕巨锤 */],
		[probability:5, code:"WM110"/* 圣者杖 */],
		[probability:5, code:"WP110"/* 白银之枪 */],
		[probability:5, code:"WS211"/* 斩马刀 */],
		[probability:5, code:"WS111"/* 龙骨战弓 */],
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
