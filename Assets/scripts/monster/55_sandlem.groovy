monster = [
	ID:'55_sandlem',
	Name:'沙妖',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:55,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:720,
		// Attack Status
		Atk:[38, 52],
		AtkSpeed:8,
		Range:55,
		Rating:950,

		// Defence Status
		Flee:380,
		Absorb:14,
		Block:5,
		DamageStunPers:100,

		// Resistance
		Earth:50,
		Fire:40,
		Ice:20,// Water
		Lighting:0,// Wind
		Poison:100,
	],

	// AI
	AI:[
		Nature:0x80,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:8,
		Real_Sight:360,
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
		ClassCode:59,
		ArrowPosi:[1, 40],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:1,
		Model:'char/monster/sandlem/sandlem.ini',
		Sound:0x00001110,
	],

	// Drops
	AllSeeItem:false,
	Exp:9800,
	Quantity:1,
	drops:[
		[probability:3000, code:"NULL"/* Drops nothing */],
		[probability:3700, code:"GG101", value:[110, 140]/* Gold */],
		[probability:350, code:"PL102"/* 中级恢复生命药水 */],
		[probability:350, code:"PS102"/* 中级恢复耐力药水 */],
		[probability:350, code:"PL103"/* 高级恢复生命药水 */],
		[probability:350, code:"PM102"/* 中级恢复魔法药水 */],
		[probability:60, code:"DA108"/* 精制链铠 */],
		[probability:60, code:"DA208"/* 圣女袍 */],
		[probability:60, code:"wa106"/* 凯旋 */],
		[probability:60, code:"WC106"/* 狼牙刃 */],
		[probability:60, code:"WH107"/* 十字锤 */],
		[probability:60, code:"WM107"/* 天魔杖 */],
		[probability:60, code:"WP107"/* 战神之镰 */],
		[probability:60, code:"WS107"/* 射日弓 */],
		[probability:60, code:"WS208"/* 圣殿武士剑 */],
		[probability:60, code:"WT107"/* 天命标 */],
		[probability:60, code:"OM106"/* 水星 */],
		[probability:60, code:"EC102"/* 回城卷 */],
		[probability:60, code:"OR109"/* 黑暗之戒 */],
		[probability:60, code:"OA109"/* 海蓝石链 */],
		[probability:60, code:"PL102"/* 中级恢复生命药水 */],
		[probability:30, code:"DA109"/* 黄铜战铠 */],
		[probability:30, code:"DA209"/* 学徒披风 */],
		[probability:30, code:"WA107"/* 刺脊斧 */],
		[probability:30, code:"WC107"/* 平手刃 */],
		[probability:30, code:"WH108"/* 圣光锤 */],
		[probability:30, code:"WM108"/* 公正之杖 */],
		[probability:30, code:"WP108"/* 血烟长矛 */],
		[probability:30, code:"WS209"/* 镇妖剑 */],
		[probability:30, code:"WS108"/* 巨弩 */],
		[probability:30, code:"WT108"/* 金标 */],
		[probability:30, code:"OM107"/* 火星 */],
		[probability:30, code:"OS105"/* 冰晶石 */],
		[probability:30, code:"DB106"/* 黄金靴 */],
		[probability:30, code:"DS106"/* 百炼盾 */],
		[probability:30, code:"DG106"/* 白金护手 */],
		[probability:30, code:"OA206"/* 精灵臂环 */],
		[probability:30, code:"OR110"/* 伏魔戒指 */],
		[probability:30, code:"OA110"/* 镇魂铃 */],
		[probability:30, code:"OS105"/* 冰晶石 */],
		[probability:30, code:"EC105"/* 公会卷轴 */],
		[probability:16, code:"DA110"/* 百裂铠 */],
		[probability:16, code:"DA210"/* 信徒披风 */],
		[probability:16, code:"WA108"/* 叼刚 清磐 */],
		[probability:16, code:"WC108"/* 矩记飘 府亥瘤 */],
		[probability:16, code:"WH109"/* 固萍绢扼捞飘 */],
		[probability:16, code:"WM109"/* 绊胶飘 */],
		[probability:16, code:"WP109"/* 单喉 荤捞靛 */],
		[probability:16, code:"WS210"/* 敲饭萍逞 家靛 */],
		[probability:16, code:"WS109"/* 点金手弩 */],
		[probability:16, code:"WT109"/* 酒唱能促 */],
		[probability:16, code:"OM108"/* 阳炎 */],
		[probability:16, code:"DB107"/* 冰火靴 */],
		[probability:16, code:"OR111"/* 封印之戒 */],
		[probability:16, code:"OA111"/* 圣者之链 */],
		[probability:16, code:"PL104"/* 顶级恢复生命药水 */],
		[probability:16, code:"OS105"/* 冰晶石 */],
		[probability:16, code:"OS106"/* 玄风石 */],
		[probability:16, code:"EC105"/* 公会卷轴 */],
		[probability:5, code:"DA111"/* 重装机铠 */],
		[probability:5, code:"DA211"/* 大法师袍 */],
		[probability:5, code:"WA109"/* 破山斧 */],
		[probability:5, code:"WC109"/* 兽之斧刃 */],
		[probability:5, code:"WH110"/* 轩辕巨锤 */],
		[probability:5, code:"WM110"/* 圣者杖 */],
		[probability:5, code:"WP110"/* 白银之枪 */],
		[probability:5, code:"WS211"/* 斩马刀 */],
		[probability:5, code:"WS110"/* 固胶飘 */],
		[probability:5, code:"WT110"/* 飞云标 */],
		[probability:5, code:"OM109"/* 暗月 */],
		[probability:5, code:"EC101"/* 回城卷 */],
		[probability:5, code:"DS107"/* 金刚盾 */],
		[probability:5, code:"PM103"/* 高级恢复魔法药水 */],
		[probability:5, code:"OR112"/* 王者戒指 */],
		[probability:5, code:"OA112"/* 魔龙之心 */],
		[probability:5, code:"PL104"/* 顶级恢复生命药水 */],
		[probability:5, code:"OS105"/* 冰晶石 */],
		[probability:5, code:"OS106"/* 玄风石 */],
		[probability:5, code:"GP109"/* 神秘水晶 */]
	],
	drops_more:[
	]
]
