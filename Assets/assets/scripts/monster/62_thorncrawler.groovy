monster = [
	ID:'62_thorncrawler',
	Name:'荆棘爬行者',
	Clazz:0,// 1 BOSS
	Brood:0x92,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:62,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:1000,
		// Attack Status
		Atk:[36, 46],
		AtkSpeed:6,
		Range:70,
		Rating:920,

		// Defence Status
		Flee:540,
		Absorb:10,
		Block:6,
		DamageStunPers:100,

		// Resistance
		Earth:40,
		Fire:30,
		Ice:30,// Water
		Lighting:0,// Wind
		Poison:20,
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
		SpAttackPercetage:15,
		SkillDamage:[45, 54],
		SkillDistance:80,
		SkillRange:0,
		SkillRating:18,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:0,
		PotionCount:0,
	],

	Looks:[
		ClassCode:18,
		ArrowPosi:[-16, 140],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:3,
		Model:'char/monster/Monthorncrowler/Monthorncrowler.ini',
		Sound:0x00001420,
	],

	// Drops
	AllSeeItem:false,
	Exp:16500,
	Quantity:1,
	drops:[
		[probability:2900, code:"NULL"/* Drops nothing */],
		[probability:3500, code:"GG101", value:[140, 240]/* Gold */],
		[probability:666, code:"PL104"/* 顶级恢复生命药水 */],
		[probability:666, code:"PS104"/* 顶级恢复耐力药水 */],
		[probability:666, code:"PL103"/* 高级恢复生命药水 */],
		[probability:45, code:"DA109"/* 黄铜战铠 */],
		[probability:45, code:"DA209"/* 学徒披风 */],
		[probability:45, code:"WA107"/* 刺脊斧 */],
		[probability:45, code:"WC107"/* 平手刃 */],
		[probability:45, code:"WH108"/* 圣光锤 */],
		[probability:45, code:"WM108"/* 公正之杖 */],
		[probability:45, code:"WP107"/* 战神之镰 */],
		[probability:45, code:"WS109"/* 点金手弩 */],
		[probability:45, code:"WS209"/* 镇妖剑 */],
		[probability:45, code:"WT107"/* 天命标 */],
		[probability:45, code:"OM105"/* 龙额念珠 */],
		[probability:45, code:"DB105"/* 百兽靴 */],
		[probability:45, code:"DS105"/* 罗塔盾 */],
		[probability:45, code:"DG105"/* 钢指护手 */],
		[probability:45, code:"OA205"/* 龙鳞臂环 */],
		[probability:45, code:"PM101"/* 低级恢复魔法药水 */],
		[probability:45, code:"OR110"/* 伏魔戒指 */],
		[probability:45, code:"OA110"/* 镇魂铃 */],
		[probability:45, code:"PL102"/* 中级恢复生命药水 */],
		[probability:45, code:"EC105"/* 公会卷轴 */],
		[probability:20, code:"DA110"/* 百裂铠 */],
		[probability:20, code:"DA210"/* 信徒披风 */],
		[probability:20, code:"WA108"/* 叼刚 清磐 */],
		[probability:20, code:"WC108"/* 矩记飘 府亥瘤 */],
		[probability:20, code:"WH109"/* 固萍绢扼捞飘 */],
		[probability:20, code:"WM109"/* 绊胶飘 */],
		[probability:20, code:"WP108"/* 血烟长矛 */],
		[probability:20, code:"WS110"/* 固胶飘 */],
		[probability:20, code:"WS210"/* 敲饭萍逞 家靛 */],
		[probability:20, code:"WT108"/* 金标 */],
		[probability:20, code:"OM106"/* 水星 */],
		[probability:20, code:"OS105"/* 冰晶石 */],
		[probability:20, code:"DB106"/* 黄金靴 */],
		[probability:20, code:"DS106"/* 百炼盾 */],
		[probability:20, code:"DG106"/* 白金护手 */],
		[probability:20, code:"OA206"/* 精灵臂环 */],
		[probability:20, code:"PM101"/* 低级恢复魔法药水 */],
		[probability:20, code:"OR110"/* 伏魔戒指 */],
		[probability:20, code:"OA110"/* 镇魂铃 */],
		[probability:20, code:"PL103"/* 高级恢复生命药水 */],
		[probability:20, code:"GP105"/* 魔剑士水晶 */],
		[probability:20, code:"EC105"/* 公会卷轴 */],
		[probability:9, code:"DA111"/* 重装机铠 */],
		[probability:9, code:"DA211"/* 大法师袍 */],
		[probability:9, code:"WA109"/* 破山斧 */],
		[probability:9, code:"WC109"/* 兽之斧刃 */],
		[probability:9, code:"WH110"/* 轩辕巨锤 */],
		[probability:9, code:"WM110"/* 圣者杖 */],
		[probability:9, code:"WP109"/* 单喉 荤捞靛 */],
		[probability:9, code:"WS111"/* 龙骨战弓 */],
		[probability:9, code:"WS211"/* 斩马刀 */],
		[probability:9, code:"WT109"/* 酒唱能促 */],
		[probability:9, code:"OM107"/* 火星 */],
		[probability:9, code:"DB107"/* 冰火靴 */],
		[probability:9, code:"DS107"/* 金刚盾 */],
		[probability:9, code:"DG107"/* 百裂护手 */],
		[probability:9, code:"OA207"/* 乌金臂环 */],
		[probability:9, code:"PM102"/* 中级恢复魔法药水 */],
		[probability:9, code:"OR111"/* 封印之戒 */],
		[probability:9, code:"OA111"/* 圣者之链 */],
		[probability:9, code:"PL104"/* 顶级恢复生命药水 */],
		[probability:9, code:"GP105"/* 魔剑士水晶 */],
		[probability:9, code:"OS106"/* 玄风石 */],
		[probability:9, code:"EC105"/* 公会卷轴 */],
		[probability:2, code:"DA112"/* 战神宝铠 */],
		[probability:2, code:"DA212"/* 红莲战袍 */],
		[probability:2, code:"WA110"/* 定神斧 */],
		[probability:2, code:"WC110"/* 九头刺蛇爪 */],
		[probability:2, code:"WH111"/* 赤冥之锤 */],
		[probability:2, code:"WM111"/* 王者杖 */],
		[probability:2, code:"WP110"/* 白银之枪 */],
		[probability:2, code:"WS112"/* 人马之辉 */],
		[probability:2, code:"WT110"/* 飞云标 */],
		[probability:2, code:"WT110"/* 飞云标 */],
		[probability:2, code:"EC102"/* 回城卷 */],
		[probability:2, code:"DB108"/* 百战靴 */],
		[probability:2, code:"DS108"/* 赤龙焰盾 */],
		[probability:2, code:"DG108"/* 大地护手 */],
		[probability:2, code:"OA208"/* 百炼臂环 */],
		[probability:2, code:"OA112"/* 魔龙之心 */],
		[probability:2, code:"GP106"/* 火灵王水晶 */],
		[probability:2, code:"OS106"/* 玄风石 */]
	],
	drops_more:[
	]
]
