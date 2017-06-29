monster = [
	ID:'60_evilplant',
	Name:'黑暗魔树',
	Clazz:0,// 1 BOSS
	Brood:0x92,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:60,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:1050,
		// Attack Status
		Atk:[28, 34],
		AtkSpeed:8,
		Range:60,
		Rating:700,

		// Defence Status
		Flee:450,
		Absorb:13,
		Block:0,
		DamageStunPers:100,

		// Resistance
		Earth:50,
		Fire:0,
		Ice:10,// Water
		Lighting:0,// Wind
		Poison:50,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:9,
		Real_Sight:360,
		Talks:[],

		// Move Behavier
		Move_Speed:1,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:15,
		SkillDamage:[35, 44],
		SkillDistance:80,
		SkillRange:55,
		SkillRating:18,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:0,
		PotionCount:0,
	],

	Looks:[
		ClassCode:0,
		ArrowPosi:[-10, 150],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:-1,
		Model:'char/monster/EvilPlant/EvilPlant.INI',
		Sound:0x00001440,
	],

	// Drops
	AllSeeItem:false,
	Exp:11500,
	Quantity:1,
	drops:[
		[probability:3000, code:"NULL"/* Drops nothing */],
		[probability:3700, code:"GG101", value:[100, 180]/* Gold */],
		[probability:500, code:"PL104"/* 顶级恢复生命药水 */],
		[probability:500, code:"PS104"/* 顶级恢复耐力药水 */],
		[probability:500, code:"PL104"/* 顶级恢复生命药水 */],
		[probability:47, code:"DA108"/* 精制链铠 */],
		[probability:47, code:"DA208"/* 圣女袍 */],
		[probability:47, code:"wa106"/* 凯旋 */],
		[probability:47, code:"WC106"/* 狼牙刃 */],
		[probability:47, code:"WH106"/* 浑元金锤 */],
		[probability:47, code:"WM107"/* 天魔杖 */],
		[probability:47, code:"WP107"/* 战神之镰 */],
		[probability:47, code:"WS107"/* 射日弓 */],
		[probability:47, code:"WS208"/* 圣殿武士剑 */],
		[probability:47, code:"WT107"/* 天命标 */],
		[probability:47, code:"DA106"/* 索子甲 */],
		[probability:47, code:"DB106"/* 黄金靴 */],
		[probability:47, code:"DS106"/* 百炼盾 */],
		[probability:47, code:"OM106"/* 水星 */],
		[probability:47, code:"DG106"/* 白金护手 */],
		[probability:47, code:"OA206"/* 精灵臂环 */],
		[probability:47, code:"OR107"/* 法师戒指 */],
		[probability:47, code:"OA107"/* 水晶项链 */],
		[probability:47, code:"GP105"/* 魔剑士水晶 */],
		[probability:37, code:"DA109"/* 黄铜战铠 */],
		[probability:37, code:"DA209"/* 学徒披风 */],
		[probability:37, code:"WA107"/* 刺脊斧 */],
		[probability:37, code:"WC107"/* 平手刃 */],
		[probability:37, code:"WH107"/* 十字锤 */],
		[probability:37, code:"WM108"/* 公正之杖 */],
		[probability:37, code:"WP108"/* 血烟长矛 */],
		[probability:37, code:"WS108"/* 巨弩 */],
		[probability:37, code:"WS209"/* 镇妖剑 */],
		[probability:37, code:"WT108"/* 金标 */],
		[probability:37, code:"OS104"/* 天仪石 */],
		[probability:37, code:"OR108"/* 翡翠戒指 */],
		[probability:37, code:"OA108"/* 靛青石链 */],
		[probability:37, code:"OM107"/* 火星 */],
		[probability:37, code:"GP105"/* 魔剑士水晶 */],
		[probability:37, code:"EC105"/* 公会卷轴 */],
		[probability:10, code:"DA110"/* 百裂铠 */],
		[probability:10, code:"DA210"/* 信徒披风 */],
		[probability:10, code:"WA108"/* 叼刚 清磐 */],
		[probability:10, code:"WC108"/* 矩记飘 府亥瘤 */],
		[probability:10, code:"WH109"/* 固萍绢扼捞飘 */],
		[probability:10, code:"WM109"/* 绊胶飘 */],
		[probability:10, code:"WP109"/* 单喉 荤捞靛 */],
		[probability:10, code:"WS109"/* 点金手弩 */],
		[probability:10, code:"WS210"/* 敲饭萍逞 家靛 */],
		[probability:10, code:"WT109"/* 酒唱能促 */],
		[probability:10, code:"DB108"/* 百战靴 */],
		[probability:10, code:"DS108"/* 赤龙焰盾 */],
		[probability:10, code:"OM107"/* 火星 */],
		[probability:10, code:"DG108"/* 大地护手 */],
		[probability:10, code:"OA208"/* 百炼臂环 */],
		[probability:10, code:"EC102"/* 回城卷 */],
		[probability:10, code:"OS105"/* 冰晶石 */],
		[probability:10, code:"OR109"/* 黑暗之戒 */],
		[probability:10, code:"GP105"/* 魔剑士水晶 */],
		[probability:10, code:"OS106"/* 玄风石 */],
		[probability:5, code:"DA111"/* 重装机铠 */],
		[probability:5, code:"DA211"/* 大法师袍 */],
		[probability:5, code:"WA109"/* 破山斧 */],
		[probability:5, code:"WC109"/* 兽之斧刃 */],
		[probability:5, code:"WH110"/* 轩辕巨锤 */],
		[probability:5, code:"WM110"/* 圣者杖 */],
		[probability:5, code:"WP110"/* 白银之枪 */],
		[probability:5, code:"WS110"/* 固胶飘 */],
		[probability:5, code:"WS211"/* 斩马刀 */],
		[probability:5, code:"WT110"/* 飞云标 */],
		[probability:5, code:"DB109"/* 大地靴 */],
		[probability:5, code:"DS109"/* 圣盾 */],
		[probability:5, code:"OM109"/* 暗月 */],
		[probability:5, code:"DG109"/* 神力护手 */],
		[probability:5, code:"OA209"/* 飞翼臂环 */],
		[probability:5, code:"EC102"/* 回城卷 */],
		[probability:5, code:"OR110"/* 伏魔戒指 */],
		[probability:5, code:"OA110"/* 镇魂铃 */],
		[probability:5, code:"GP106"/* 火灵王水晶 */],
		[probability:5, code:"GP110"/* 守护圣徒水晶 */]
	],
	drops_more:[
	]
]
