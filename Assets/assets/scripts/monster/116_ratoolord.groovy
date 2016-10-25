monster = [
	ID:'116_ratoolord',
	Name:'鲁图王',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:116,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:10000,
		// Attack Status
		Atk:[200, 240],
		AtkSpeed:7,
		Range:110,
		Rating:2100,

		// Defence Status
		Flee:1100,
		Absorb:20,
		Block:10,
		DamageStunPers:80,

		// Resistance
		Earth:60,
		Fire:50,
		Ice:50,// Water
		Lighting:20,// Wind
		Poison:50,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:9,
		Real_Sight:440,
		Talks:[],

		// Move Behavier
		Move_Speed:4,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:20,
		SkillDamage:[250, 300],
		SkillDistance:0,
		SkillRange:150,
		SkillRating:30,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:0,
		PotionCount:0,
	],

	Looks:[
		ClassCode:0,
		ArrowPosi:[0, 0],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:3,
		Model:'char/monster/Monratoo-1/Monratoo-1.ini',
		Sound:0x00001340,
	],

	// Drops
	AllSeeItem:false,
	Exp:61000,
	Quantity:3,
	drops:[
		[probability:2145, code:"NULL"/* Drops nothing */],
		[probability:3000, code:"GG101", value:[1200, 2000]/* Gold */],
		[probability:1133, code:"PL104"/* 顶级恢复生命药水 */],
		[probability:1133, code:"PS104"/* 顶级恢复耐力药水 */],
		[probability:1133, code:"PM104"/* 顶级恢复魔法药水 */],
		[probability:42, code:"DA114"/* 星晨宝铠 */],
		[probability:42, code:"DA214"/* 绯红之眼 */],
		[probability:42, code:"OA212"/* 紫焰臂环 */],
		[probability:42, code:"DS112"/* 暗黑盾 */],
		[probability:42, code:"DB112"/* 破棘之靴 */],
		[probability:42, code:"DG112"/* 巨灵护手 */],
		[probability:42, code:"WA112"/* 奥丁斧 */],
		[probability:42, code:"WP113"/* 冥河战镰 */],
		[probability:42, code:"WC112"/* 飞龙爪 */],
		[probability:42, code:"WH113"/* 破日锤 */],
		[probability:42, code:"WM113"/* 魔蜓杖 */],
		[probability:42, code:"WS114"/* 爱神之翼 */],
		[probability:42, code:"WS215"/* 诅咒之剑 */],
		[probability:42, code:"WT113"/* 魔龙标 */],
		[probability:42, code:"OA110"/* 镇魂铃 */],
		[probability:42, code:"OR110"/* 伏魔戒指 */],
		[probability:42, code:"OS109"/* 龙鳞石 */],
		[probability:42, code:"GP112"/* 鬼影魔神水晶 */],
		[probability:42, code:"SE101"/* 造化石 */],
		[probability:23, code:"DA115"/* 泰坦战铠 */],
		[probability:23, code:"DA215"/* 文章法袍 */],
		[probability:23, code:"OA213"/* 璇彩臂环 */],
		[probability:23, code:"DS113"/* 龙纹盾 */],
		[probability:23, code:"DB113"/* 遁地靴 */],
		[probability:23, code:"DG113"/* 鲲鹏护手 */],
		[probability:23, code:"WA113"/* 蝶花霹雳斧 */],
		[probability:23, code:"WP114"/* 龙翼枪 */],
		[probability:23, code:"WC113"/* 魔星爪 */],
		[probability:23, code:"WH114"/* 鬼眼锤 */],
		[probability:23, code:"WM114"/* 混沌之杖 */],
		[probability:23, code:"WS115"/* 精灵之翼 */],
		[probability:23, code:"WS216"/* 破军 */],
		[probability:23, code:"WT114"/* 追月标 */],
		[probability:23, code:"OA111"/* 圣者之链 */],
		[probability:23, code:"OR111"/* 封印之戒 */],
		[probability:23, code:"OS107"/* 水晶石 */],
		[probability:11, code:"DA116"/* 暗黑铠 */],
		[probability:11, code:"DA216"/* 祝福法袍 */],
		[probability:11, code:"OA214"/* 金刚臂环 */],
		[probability:11, code:"DS114"/* 泰坦之盾 */],
		[probability:11, code:"DB114"/* 鹏翅之靴 */],
		[probability:11, code:"DG114"/* 金刚护手 */],
		[probability:11, code:"WA114"/* 战神之刃 */],
		[probability:11, code:"WP115"/* 狂暴之枪 */],
		[probability:11, code:"WC114"/* 天狼爪 */],
		[probability:11, code:"WH115"/* 雷公槌 */],
		[probability:11, code:"WM115"/* 亡灵刺 */],
		[probability:11, code:"WS116"/* 血精灵 */],
		[probability:11, code:"WS217"/* 鬼切 */],
		[probability:11, code:"WT115"/* 惊鸿 */],
		[probability:11, code:"OA112"/* 魔龙之心 */],
		[probability:11, code:"OR112"/* 王者戒指 */],
		[probability:11, code:"OS107"/* 水晶石 */],
		[probability:11, code:"GP113"/* 铁甲狂魔水晶 */],
		[probability:3, code:"DA117"/* 远古圣铠 */],
		[probability:3, code:"DA217"/* 天使法袍 */],
		[probability:3, code:"OA215"/* 赤龙臂环 */],
		[probability:3, code:"DS115"/* 亢龙之盾 */],
		[probability:3, code:"DB115"/* 时空之靴 */],
		[probability:3, code:"DG115"/* 赤龙护手 */],
		[probability:3, code:"WA115"/* 泰坦斧 */],
		[probability:3, code:"WP116"/* 虹月 */],
		[probability:3, code:"WC115"/* 魔玉爪 */],
		[probability:3, code:"WH116"/* 轰天锤 */],
		[probability:3, code:"WM116"/* 诸神的黄昏 */],
		[probability:3, code:"WS117"/* 破鹫 */],
		[probability:3, code:"WS218"/* 天裂 */],
		[probability:3, code:"WT116"/* 玛雅神标 */],
		[probability:3, code:"OA113"/* 生命之链 */],
		[probability:3, code:"OR113"/* 灵魂之戒 */],
		[probability:3, code:"OS108"/* 虎翼石 */],
		[probability:3, code:"GP117"/* 绿色神秘水晶 */]
	],
	drops_more:[
	]
]
