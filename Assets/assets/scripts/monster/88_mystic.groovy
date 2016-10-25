monster = [
	ID:'88_mystic',
	Name:'神秘尖晶石',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:88,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:5000,
		// Attack Status
		Atk:[0, 0],
		AtkSpeed:8,
		Range:90,
		Rating:1400,

		// Defence Status
		Flee:830,
		Absorb:21,
		Block:0,
		DamageStunPers:100,

		// Resistance
		Earth:30,
		Fire:-100,
		Ice:100,// Water
		Lighting:30,// Wind
		Poison:40,
	],

	// AI
	AI:[
		Nature:0x80,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:9,
		Real_Sight:400,
		Talks:[],

		// Move Behavier
		Move_Speed:6,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:30,
		SkillDamage:[100, 130],
		SkillDistance:0,
		SkillRange:140,
		SkillRating:10,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:0,
		PotionCount:0,
	],

	Looks:[
		ClassCode:0,
		ArrowPosi:[-3, 20],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:1,
		Model:'char/monster/Mystic/Mystic.ini',
		Sound:0x00001610,
	],

	// Drops
	AllSeeItem:false,
	Exp:70000,
	Quantity:1,
	drops:[
		[probability:1775, code:"NULL"/* Drops nothing */],
		[probability:5715, code:"GG101", value:[350, 500]/* Gold */],
		[probability:600, code:"PL104"/* 顶级恢复生命药水 */],
		[probability:600, code:"PM104"/* 顶级恢复魔法药水 */],
		[probability:600, code:"PM104"/* 顶级恢复魔法药水 */],
		[probability:31, code:"DA115"/* 泰坦战铠 */],
		[probability:31, code:"DA215"/* 文章法袍 */],
		[probability:31, code:"WA113"/* 蝶花霹雳斧 */],
		[probability:31, code:"WC113"/* 魔星爪 */],
		[probability:31, code:"WH114"/* 鬼眼锤 */],
		[probability:31, code:"WM114"/* 混沌之杖 */],
		[probability:31, code:"WP114"/* 龙翼枪 */],
		[probability:31, code:"WS115"/* 精灵之翼 */],
		[probability:31, code:"WS216"/* 破军 */],
		[probability:31, code:"WT114"/* 追月标 */],
		[probability:31, code:"DB113"/* 遁地靴 */],
		[probability:31, code:"DS113"/* 龙纹盾 */],
		[probability:31, code:"OM114"/* 堕天 */],
		[probability:31, code:"DG113"/* 鲲鹏护手 */],
		[probability:31, code:"OA213"/* 璇彩臂环 */],
		[probability:31, code:"OR114"/* 帝王之戒 */],
		[probability:31, code:"OA114"/* 神之庇护 */],
		[probability:31, code:"GP109"/* 神秘水晶 */],
		[probability:4, code:"DA116"/* 暗黑铠 */],
		[probability:4, code:"DA216"/* 祝福法袍 */],
		[probability:4, code:"WA114"/* 战神之刃 */],
		[probability:4, code:"WC114"/* 天狼爪 */],
		[probability:4, code:"WH115"/* 雷公槌 */],
		[probability:4, code:"WM115"/* 亡灵刺 */],
		[probability:4, code:"WP115"/* 狂暴之枪 */],
		[probability:4, code:"WS116"/* 血精灵 */],
		[probability:4, code:"WS217"/* 鬼切 */],
		[probability:4, code:"WT115"/* 惊鸿 */],
		[probability:4, code:"DB114"/* 鹏翅之靴 */],
		[probability:4, code:"DS114"/* 泰坦之盾 */],
		[probability:4, code:"OM115"/* 炫彩水晶 */],
		[probability:4, code:"DG114"/* 金刚护手 */],
		[probability:4, code:"OA214"/* 金刚臂环 */],
		[probability:4, code:"OR115"/* 守护之戒 */],
		[probability:4, code:"OA115"/* 暗印护符 */],
		[probability:4, code:"OS107"/* 水晶石 */],
		[probability:4, code:"GP109"/* 神秘水晶 */],
		[probability:4, code:"GP110"/* 守护圣徒水晶 */],
		[probability:2, code:"DA117"/* 远古圣铠 */],
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
		[probability:2, code:"OS107"/* 水晶石 */],
		[probability:2, code:"GP109"/* 神秘水晶 */],
		[probability:2, code:"GP110"/* 守护圣徒水晶 */],
		[probability:1, code:"DA118"/* 米诺陶斯铠 */],
		[probability:1, code:"DA218"/* 撒旦披风 */],
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
		[probability:1, code:"OS108"/* 虎翼石 */],
		[probability:1, code:"GP110"/* 守护圣徒水晶 */],
		[probability:1, code:"GP113"/* 铁甲狂魔水晶 */]
	],
	drops_more:[
		[probability:1000, code:"OS109"/* 龙鳞石 */]	]
]
