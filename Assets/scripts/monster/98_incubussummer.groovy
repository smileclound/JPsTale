monster = [
	ID:'98_incubussummer',
	Name:'冰风女妖',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:98,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:8000,
		// Attack Status
		Atk:[100, 120],
		AtkSpeed:9,
		Range:180,
		Rating:1500,

		// Defence Status
		Flee:720,
		Absorb:27,
		Block:20,
		DamageStunPers:100,

		// Resistance
		Earth:33,
		Fire:-100,
		Ice:88,// Water
		Lighting:63,// Wind
		Poison:60,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:8,
		Real_Sight:390,
		Talks:[],

		// Move Behavier
		Move_Speed:3,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:20,
		SkillDamage:[0, 0],
		SkillDistance:0,
		SkillRange:0,
		SkillRating:18,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:0,
		PotionCount:0,
	],

	Looks:[
		ClassCode:64,
		ArrowPosi:[2, 30],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:1,
		Model:'char/monster/Incubussummer/Incubussummer.ini',
		Sound:0x000012C0,
	],

	// Drops
	AllSeeItem:false,
	Exp:530000,
	Quantity:1,
	drops:[
		[probability:1626, code:"NULL"/* Drops nothing */],
		[probability:2260, code:"GG101", value:[520, 820]/* Gold */],
		[probability:184, code:"DA116"/* 暗黑铠 */],
		[probability:184, code:"DA216"/* 祝福法袍 */],
		[probability:184, code:"WA114"/* 战神之刃 */],
		[probability:184, code:"WC114"/* 天狼爪 */],
		[probability:184, code:"WH115"/* 雷公槌 */],
		[probability:184, code:"WM115"/* 亡灵刺 */],
		[probability:184, code:"WP115"/* 狂暴之枪 */],
		[probability:184, code:"WS116"/* 血精灵 */],
		[probability:184, code:"WS217"/* 鬼切 */],
		[probability:184, code:"WT115"/* 惊鸿 */],
		[probability:184, code:"DB114"/* 鹏翅之靴 */],
		[probability:184, code:"DS114"/* 泰坦之盾 */],
		[probability:184, code:"OM115"/* 炫彩水晶 */],
		[probability:184, code:"DG114"/* 金刚护手 */],
		[probability:184, code:"OA214"/* 金刚臂环 */],
		[probability:184, code:"OR116"/* 雅典娜之吻 */],
		[probability:184, code:"OA116"/* 苍穹之链 */],
		[probability:184, code:"OS107"/* 水晶石 */],
		[probability:131, code:"DA117"/* 远古圣铠 */],
		[probability:131, code:"DA217"/* 天使法袍 */],
		[probability:131, code:"WA115"/* 泰坦斧 */],
		[probability:131, code:"WC115"/* 魔玉爪 */],
		[probability:131, code:"WH116"/* 轰天锤 */],
		[probability:131, code:"WM116"/* 诸神的黄昏 */],
		[probability:131, code:"WP116"/* 虹月 */],
		[probability:131, code:"WS117"/* 破鹫 */],
		[probability:131, code:"WS218"/* 天裂 */],
		[probability:131, code:"WT116"/* 玛雅神标 */],
		[probability:131, code:"DB115"/* 时空之靴 */],
		[probability:131, code:"DS115"/* 亢龙之盾 */],
		[probability:131, code:"OM116"/* 龙之护身 */],
		[probability:131, code:"DG115"/* 赤龙护手 */],
		[probability:131, code:"OA215"/* 赤龙臂环 */],
		[probability:131, code:"OR117"/* 封魔之戒 */],
		[probability:131, code:"OA117"/* 天眼护符 */],
		[probability:131, code:"OS108"/* 虎翼石 */],
		[probability:18, code:"DA118"/* 米诺陶斯铠 */],
		[probability:18, code:"DA218"/* 撒旦披风 */],
		[probability:18, code:"WA116"/* 轮回战斧 */],
		[probability:18, code:"WC116"/* 泰坦爪 */],
		[probability:18, code:"WH117"/* 兽神锤 */],
		[probability:18, code:"WM117"/* 神圣之光 */],
		[probability:18, code:"WP117"/* 银河战镰 */],
		[probability:18, code:"WS118"/* 风切 */],
		[probability:18, code:"WS219"/* 凝雾 */],
		[probability:18, code:"WT117"/* 夜叉 */],
		[probability:18, code:"DB116"/* 赤龙战靴 */],
		[probability:18, code:"DS116"/* 远古之盾 */],
		[probability:18, code:"OM117"/* 绿釉之眼 */],
		[probability:18, code:"DG116"/* 星辰护手 */],
		[probability:18, code:"OA216"/* 鎏金臂环 */],
		[probability:18, code:"OR118"/* 封神之戒 */],
		[probability:18, code:"OA118"/* 圣光勋章 */],
		[probability:18, code:"OS109"/* 龙鳞石 */],
		[probability:5, code:"DA119"/* 死神绝命铠 */],
		[probability:5, code:"DA219"/* 幻彩羽袍 */],
		[probability:5, code:"WA117"/* 远古战斧 */],
		[probability:5, code:"WC117"/* 冰魄爪 */],
		[probability:5, code:"WH118"/* 灭神锤 */],
		[probability:5, code:"WM118"/* 日月同辉 */],
		[probability:5, code:"WP118"/* 月影神矛 */],
		[probability:5, code:"WS119"/* 丘比特之弓 */],
		[probability:5, code:"WS220"/* 龙牙 */],
		[probability:5, code:"WT118"/* 噬月 */],
		[probability:5, code:"DB117"/* 烈焰靴 */],
		[probability:5, code:"DS117"/* 米诺陶斯盾 */],
		[probability:5, code:"OM118"/* 黑魔 */],
		[probability:5, code:"DG117"/* 炫钻护手 */],
		[probability:5, code:"OA217"/* 炫钻臂环 */],
		[probability:5, code:"OR119"/* 轮回之戒 */],
		[probability:5, code:"OA119"/* 逆天纹章 */],
		[probability:5, code:"OS110"/* 钻晶石 */]
	],
	drops_more:[
	]
]
