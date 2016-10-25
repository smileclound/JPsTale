monster = [
	ID:'97_turtlecannon',
	Name:'霸天龟',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:97,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:10000,
		// Attack Status
		Atk:[80, 100],
		AtkSpeed:8,
		Range:140,
		Rating:1500,

		// Defence Status
		Flee:800,
		Absorb:28,
		Block:11,
		DamageStunPers:20,

		// Resistance
		Earth:60,
		Fire:-100,
		Ice:100,// Water
		Lighting:10,// Wind
		Poison:50,
	],

	// AI
	AI:[
		Nature:0x80,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:8,
		Real_Sight:420,
		Talks:[],

		// Move Behavier
		Move_Speed:2,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:15,
		SkillDamage:[100, 140],
		SkillDistance:80,
		SkillRange:80,
		SkillRating:20,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:0,
		PotionCount:0,
	],

	Looks:[
		ClassCode:63,
		ArrowPosi:[-2, 120],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:1,
		Model:'char/monster/turtlecannon/turtlecannon.ini',
		Sound:0x00000000,
	],

	// Drops
	AllSeeItem:false,
	Exp:520000,
	Quantity:1,
	drops:[
		[probability:1750, code:"NULL"/* Drops nothing */],
		[probability:2236, code:"GG101", value:[555, 836]/* Gold */],
		[probability:192, code:"DA116"/* 暗黑铠 */],
		[probability:192, code:"DA216"/* 祝福法袍 */],
		[probability:192, code:"WA114"/* 战神之刃 */],
		[probability:192, code:"WC114"/* 天狼爪 */],
		[probability:192, code:"WH115"/* 雷公槌 */],
		[probability:192, code:"WM115"/* 亡灵刺 */],
		[probability:192, code:"WP115"/* 狂暴之枪 */],
		[probability:192, code:"WS116"/* 血精灵 */],
		[probability:192, code:"WS217"/* 鬼切 */],
		[probability:192, code:"WT115"/* 惊鸿 */],
		[probability:192, code:"DB114"/* 鹏翅之靴 */],
		[probability:192, code:"DS114"/* 泰坦之盾 */],
		[probability:192, code:"OM115"/* 炫彩水晶 */],
		[probability:192, code:"DG114"/* 金刚护手 */],
		[probability:192, code:"OA214"/* 金刚臂环 */],
		[probability:192, code:"OR116"/* 雅典娜之吻 */],
		[probability:192, code:"OA116"/* 苍穹之链 */],
		[probability:192, code:"OS107"/* 水晶石 */],
		[probability:118, code:"DA117"/* 远古圣铠 */],
		[probability:118, code:"DA217"/* 天使法袍 */],
		[probability:118, code:"WA115"/* 泰坦斧 */],
		[probability:118, code:"WC115"/* 魔玉爪 */],
		[probability:118, code:"WH116"/* 轰天锤 */],
		[probability:118, code:"WM116"/* 诸神的黄昏 */],
		[probability:118, code:"WP116"/* 虹月 */],
		[probability:118, code:"WS117"/* 破鹫 */],
		[probability:118, code:"WS218"/* 天裂 */],
		[probability:118, code:"WT116"/* 玛雅神标 */],
		[probability:118, code:"DB115"/* 时空之靴 */],
		[probability:118, code:"DS115"/* 亢龙之盾 */],
		[probability:118, code:"OM116"/* 龙之护身 */],
		[probability:118, code:"DG115"/* 赤龙护手 */],
		[probability:118, code:"OA215"/* 赤龙臂环 */],
		[probability:118, code:"OR117"/* 封魔之戒 */],
		[probability:118, code:"OA117"/* 天眼护符 */],
		[probability:118, code:"OS108"/* 虎翼石 */],
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
		[probability:4, code:"DA119"/* 死神绝命铠 */],
		[probability:4, code:"DA219"/* 幻彩羽袍 */],
		[probability:4, code:"WA117"/* 远古战斧 */],
		[probability:4, code:"WC117"/* 冰魄爪 */],
		[probability:4, code:"WH118"/* 灭神锤 */],
		[probability:4, code:"WM118"/* 日月同辉 */],
		[probability:4, code:"WP118"/* 月影神矛 */],
		[probability:4, code:"WS119"/* 丘比特之弓 */],
		[probability:4, code:"WS220"/* 龙牙 */],
		[probability:4, code:"WT118"/* 噬月 */],
		[probability:4, code:"DB117"/* 烈焰靴 */],
		[probability:4, code:"DS117"/* 米诺陶斯盾 */],
		[probability:4, code:"OM118"/* 黑魔 */],
		[probability:4, code:"DG117"/* 炫钻护手 */],
		[probability:4, code:"OA217"/* 炫钻臂环 */],
		[probability:4, code:"OR119"/* 轮回之戒 */],
		[probability:4, code:"OA119"/* 逆天纹章 */],
		[probability:4, code:"OS110"/* 钻晶石 */]
	],
	drops_more:[
	]
]
