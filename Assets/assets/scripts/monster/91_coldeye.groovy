monster = [
	ID:'91_coldeye',
	Name:'冷眼魔',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:91,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:6500,
		// Attack Status
		Atk:[65, 76],
		AtkSpeed:8,
		Range:220,
		Rating:1450,

		// Defence Status
		Flee:820,
		Absorb:20,
		Block:8,
		DamageStunPers:30,

		// Resistance
		Earth:20,
		Fire:-100,
		Ice:100,// Water
		Lighting:-100,// Wind
		Poison:50,
	],

	// AI
	AI:[
		Nature:0x80,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:8,
		Real_Sight:420,
		Talks:[],

		// Move Behavier
		Move_Speed:4,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:30,
		SkillDamage:[79, 87],
		SkillDistance:0,
		SkillRange:150,
		SkillRating:16,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:0,
		PotionCount:0,
	],

	Looks:[
		ClassCode:0,
		ArrowPosi:[4, 0],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:1,
		Model:'char/monster/coldeye/coldeye.ini',
		Sound:0x00001630,
	],

	// Drops
	AllSeeItem:false,
	Exp:98000,
	Quantity:1,
	drops:[
		[probability:1775, code:"NULL"/* Drops nothing */],
		[probability:5895, code:"GG101", value:[420, 620]/* Gold */],
		[probability:600, code:"PL104"/* 顶级恢复生命药水 */],
		[probability:600, code:"PM104"/* 顶级恢复魔法药水 */],
		[probability:600, code:"PM104"/* 顶级恢复魔法药水 */],
		[probability:14, code:"DA116"/* 暗黑铠 */],
		[probability:14, code:"DA215"/* 文章法袍 */],
		[probability:14, code:"WA113"/* 蝶花霹雳斧 */],
		[probability:14, code:"WC113"/* 魔星爪 */],
		[probability:14, code:"WH114"/* 鬼眼锤 */],
		[probability:14, code:"WM114"/* 混沌之杖 */],
		[probability:14, code:"WP114"/* 龙翼枪 */],
		[probability:14, code:"WS115"/* 精灵之翼 */],
		[probability:14, code:"WS216"/* 破军 */],
		[probability:14, code:"WT114"/* 追月标 */],
		[probability:14, code:"DB113"/* 遁地靴 */],
		[probability:14, code:"DS113"/* 龙纹盾 */],
		[probability:14, code:"OM114"/* 堕天 */],
		[probability:14, code:"DG113"/* 鲲鹏护手 */],
		[probability:14, code:"OA213"/* 璇彩臂环 */],
		[probability:14, code:"OR115"/* 守护之戒 */],
		[probability:14, code:"OA115"/* 暗印护符 */],
		[probability:14, code:"OS107"/* 水晶石 */],
		[probability:14, code:"OS108"/* 虎翼石 */],
		[probability:14, code:"GP109"/* 神秘水晶 */],
		[probability:14, code:"GP110"/* 守护圣徒水晶 */],
		[probability:6, code:"DA117"/* 远古圣铠 */],
		[probability:6, code:"DA216"/* 祝福法袍 */],
		[probability:6, code:"WA114"/* 战神之刃 */],
		[probability:6, code:"WC114"/* 天狼爪 */],
		[probability:6, code:"WH115"/* 雷公槌 */],
		[probability:6, code:"WM115"/* 亡灵刺 */],
		[probability:6, code:"WP115"/* 狂暴之枪 */],
		[probability:6, code:"WS116"/* 血精灵 */],
		[probability:6, code:"WS217"/* 鬼切 */],
		[probability:6, code:"WT115"/* 惊鸿 */],
		[probability:6, code:"DB114"/* 鹏翅之靴 */],
		[probability:6, code:"DS114"/* 泰坦之盾 */],
		[probability:6, code:"OM115"/* 炫彩水晶 */],
		[probability:6, code:"DG114"/* 金刚护手 */],
		[probability:6, code:"OA214"/* 金刚臂环 */],
		[probability:6, code:"OR116"/* 雅典娜之吻 */],
		[probability:6, code:"OA116"/* 苍穹之链 */],
		[probability:6, code:"OS108"/* 虎翼石 */],
		[probability:6, code:"OS128"/* 虎翼石(魔法) */],
		[probability:6, code:"GP109"/* 神秘水晶 */],
		[probability:6, code:"GP110"/* 守护圣徒水晶 */],
		[probability:2, code:"DA118"/* 米诺陶斯铠 */],
		[probability:2, code:"DA217"/* 天使法袍 */],
		[probability:2, code:"WA115"/* 泰坦斧 */],
		[probability:2, code:"WC115"/* 魔玉爪 */],
		[probability:2, code:"WH116"/* 轰天锤 */],
		[probability:2, code:"WM116"/* 诸神的黄昏 */],
		[probability:2, code:"WP116"/* 虹月 */],
		[probability:2, code:"WS117"/* 破鹫 */],
		[probability:2, code:"WS218"/* 天裂 */],
		[probability:2, code:"WT116"/* 玛雅神标 */],
		[probability:2, code:"DB115"/* 时空之靴 */],
		[probability:2, code:"DS115"/* 亢龙之盾 */],
		[probability:2, code:"OM116"/* 龙之护身 */],
		[probability:2, code:"DG115"/* 赤龙护手 */],
		[probability:2, code:"OA215"/* 赤龙臂环 */],
		[probability:2, code:"OR116"/* 雅典娜之吻 */],
		[probability:2, code:"OA116"/* 苍穹之链 */],
		[probability:2, code:"OS108"/* 虎翼石 */],
		[probability:2, code:"GP110"/* 守护圣徒水晶 */],
		[probability:2, code:"GP113"/* 铁甲狂魔水晶 */],
		[probability:1, code:"DA119"/* 死神绝命铠 */],
		[probability:1, code:"DA219"/* 幻彩羽袍 */],
		[probability:1, code:"WA116"/* 轮回战斧 */],
		[probability:1, code:"WC116"/* 泰坦爪 */],
		[probability:1, code:"WH117"/* 兽神锤 */],
		[probability:1, code:"WM117"/* 神圣之光 */],
		[probability:1, code:"WP117"/* 银河战镰 */],
		[probability:1, code:"WS118"/* 风切 */],
		[probability:1, code:"WS219"/* 凝雾 */],
		[probability:1, code:"WT117"/* 夜叉 */],
		[probability:1, code:"DB116"/* 赤龙战靴 */],
		[probability:1, code:"DS116"/* 远古之盾 */],
		[probability:1, code:"OM117"/* 绿釉之眼 */],
		[probability:1, code:"DG116"/* 星辰护手 */],
		[probability:1, code:"OA216"/* 鎏金臂环 */],
		[probability:1, code:"OR117"/* 封魔之戒 */],
		[probability:1, code:"OA117"/* 天眼护符 */],
		[probability:2, code:"DA120"/* 炎龙圣铠 */],
		[probability:2, code:"DA220"/* 修罗圣衣 */],
		[probability:2, code:"OS109"/* 龙鳞石 */],
		[probability:2, code:"OS129"/* 龙鳞石(魔法) */]
	],
	drops_more:[
		[probability:1000, code:"WH113"/* 破日锤 */]	]
]
