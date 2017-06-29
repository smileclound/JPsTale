monster = [
	ID:'99_blizzardgiant',
	Name:'冰甲无双',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:99,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:10500,
		// Attack Status
		Atk:[100, 110],
		AtkSpeed:7,
		Range:80,
		Rating:1510,

		// Defence Status
		Flee:880,
		Absorb:28,
		Block:13,
		DamageStunPers:20,

		// Resistance
		Earth:60,
		Fire:-70,
		Ice:100,// Water
		Lighting:30,// Wind
		Poison:60,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:8,
		Real_Sight:380,
		Talks:[],

		// Move Behavier
		Move_Speed:4,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:20,
		SkillDamage:[120, 140],
		SkillDistance:100,
		SkillRange:40,
		SkillRating:18,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:50,
		PotionCount:1,
	],

	Looks:[
		ClassCode:0,
		ArrowPosi:[0, 0],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:3,
		Model:'char/monster/blizzardgiant/blizzardgiant.ini',
		Sound:0x00001820,
	],

	// Drops
	AllSeeItem:false,
	Exp:540000,
	Quantity:1,
	drops:[
		[probability:1961, code:"NULL"/* Drops nothing */],
		[probability:2260, code:"GG101", value:[520, 820]/* Gold */],
		[probability:175, code:"DA116"/* 暗黑铠 */],
		[probability:175, code:"DA216"/* 祝福法袍 */],
		[probability:175, code:"WA114"/* 战神之刃 */],
		[probability:175, code:"WC114"/* 天狼爪 */],
		[probability:175, code:"WH115"/* 雷公槌 */],
		[probability:175, code:"WM115"/* 亡灵刺 */],
		[probability:175, code:"WP115"/* 狂暴之枪 */],
		[probability:175, code:"WS116"/* 血精灵 */],
		[probability:175, code:"WS217"/* 鬼切 */],
		[probability:175, code:"WT115"/* 惊鸿 */],
		[probability:175, code:"DB114"/* 鹏翅之靴 */],
		[probability:175, code:"DS114"/* 泰坦之盾 */],
		[probability:175, code:"OM115"/* 炫彩水晶 */],
		[probability:175, code:"DG114"/* 金刚护手 */],
		[probability:175, code:"OA214"/* 金刚臂环 */],
		[probability:175, code:"OR116"/* 雅典娜之吻 */],
		[probability:175, code:"OA116"/* 苍穹之链 */],
		[probability:175, code:"OS107"/* 水晶石 */],
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
		[probability:20, code:"DA118"/* 米诺陶斯铠 */],
		[probability:20, code:"DA218"/* 撒旦披风 */],
		[probability:20, code:"WA116"/* 轮回战斧 */],
		[probability:20, code:"WC116"/* 泰坦爪 */],
		[probability:20, code:"WH117"/* 兽神锤 */],
		[probability:20, code:"WM117"/* 神圣之光 */],
		[probability:20, code:"WP117"/* 银河战镰 */],
		[probability:20, code:"WS118"/* 风切 */],
		[probability:20, code:"WS219"/* 凝雾 */],
		[probability:20, code:"WT117"/* 夜叉 */],
		[probability:20, code:"DB116"/* 赤龙战靴 */],
		[probability:20, code:"DS116"/* 远古之盾 */],
		[probability:20, code:"OM117"/* 绿釉之眼 */],
		[probability:20, code:"DG116"/* 星辰护手 */],
		[probability:20, code:"OA216"/* 鎏金臂环 */],
		[probability:20, code:"OR118"/* 封神之戒 */],
		[probability:20, code:"OA118"/* 圣光勋章 */],
		[probability:20, code:"OS109"/* 龙鳞石 */],
		[probability:6, code:"DA119"/* 死神绝命铠 */],
		[probability:6, code:"DA219"/* 幻彩羽袍 */],
		[probability:6, code:"WA117"/* 远古战斧 */],
		[probability:6, code:"WC117"/* 冰魄爪 */],
		[probability:6, code:"WH118"/* 灭神锤 */],
		[probability:6, code:"WM118"/* 日月同辉 */],
		[probability:6, code:"WP118"/* 月影神矛 */],
		[probability:6, code:"WS119"/* 丘比特之弓 */],
		[probability:6, code:"WS220"/* 龙牙 */],
		[probability:6, code:"WT118"/* 噬月 */],
		[probability:6, code:"DB117"/* 烈焰靴 */],
		[probability:6, code:"DS117"/* 米诺陶斯盾 */],
		[probability:6, code:"OM118"/* 黑魔 */],
		[probability:6, code:"DG117"/* 炫钻护手 */],
		[probability:6, code:"OA217"/* 炫钻臂环 */],
		[probability:6, code:"OR119"/* 轮回之戒 */],
		[probability:6, code:"OA119"/* 逆天纹章 */],
		[probability:6, code:"OS110"/* 钻晶石 */]
	],
	drops_more:[
	]
]
