monster = [
	ID:'a4-witch',
	Name:'生化女巫',
	Clazz:0,// 1 BOSS
	Brood:0x92,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:71,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:1100,
		// Attack Status
		Atk:[60, 80],
		AtkSpeed:9,
		Range:180,
		Rating:1200,

		// Defence Status
		Flee:520,
		Absorb:12,
		Block:0,
		DamageStunPers:100,

		// Resistance
		Earth:0,
		Fire:30,
		Ice:0,// Water
		Lighting:20,// Wind
		Poison:100,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:9,
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
		SkillRating:10,
		SkillCurse:101,

		// Heal Behavier
		PotionPercent:0,
		PotionCount:0,
	],

	Looks:[
		ClassCode:30,
		ArrowPosi:[2, 30],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:1,
		Model:'char/monster/MonWitch/MonWitch2.ini',
		Sound:0x000012C0,
	],

	// Drops
	AllSeeItem:false,
	Exp:11000,
	Quantity:1,
	drops:[
		[probability:4430, code:"NULL"/* Drops nothing */],
		[probability:4460, code:"GG101", value:[75, 98]/* Gold */],
		[probability:37, code:"WA110"/* 定神斧 */],
		[probability:37, code:"WC110"/* 九头刺蛇爪 */],
		[probability:37, code:"WH110"/* 轩辕巨锤 */],
		[probability:37, code:"WM110"/* 圣者杖 */],
		[probability:37, code:"WP110"/* 白银之枪 */],
		[probability:37, code:"WS112"/* 人马之辉 */],
		[probability:37, code:"WS212"/* 嗜血屠魔剑 */],
		[probability:37, code:"WT111"/* 神标 */],
		[probability:37, code:"DA110"/* 百裂铠 */],
		[probability:37, code:"DA210"/* 信徒披风 */],
		[probability:37, code:"DS109"/* 圣盾 */],
		[probability:37, code:"OM112"/* 菱晶石 */],
		[probability:16, code:"WA110"/* 定神斧 */],
		[probability:16, code:"WC110"/* 九头刺蛇爪 */],
		[probability:16, code:"WH110"/* 轩辕巨锤 */],
		[probability:16, code:"WM110"/* 圣者杖 */],
		[probability:16, code:"WP110"/* 白银之枪 */],
		[probability:16, code:"WS112"/* 人马之辉 */],
		[probability:16, code:"WS212"/* 嗜血屠魔剑 */],
		[probability:16, code:"WT111"/* 神标 */],
		[probability:16, code:"DA110"/* 百裂铠 */],
		[probability:16, code:"DA210"/* 信徒披风 */],
		[probability:16, code:"DS109"/* 圣盾 */],
		[probability:16, code:"OM112"/* 菱晶石 */],
		[probability:9, code:"WA113"/* 蝶花霹雳斧 */],
		[probability:9, code:"WC112"/* 飞龙爪 */],
		[probability:9, code:"WH113"/* 破日锤 */],
		[probability:9, code:"WM114"/* 混沌之杖 */],
		[probability:9, code:"WP113"/* 冥河战镰 */],
		[probability:9, code:"WS117"/* 破鹫 */],
		[probability:9, code:"WS215"/* 诅咒之剑 */],
		[probability:9, code:"WT113"/* 魔龙标 */],
		[probability:9, code:"DA112"/* 战神宝铠 */],
		[probability:9, code:"DA212"/* 红莲战袍 */],
		[probability:9, code:"DS111"/* 苍穹之盾 */],
		[probability:9, code:"OM115"/* 炫彩水晶 */],
		[probability:8, code:"WA115"/* 泰坦斧 */],
		[probability:8, code:"WC113"/* 魔星爪 */],
		[probability:8, code:"WH115"/* 雷公槌 */],
		[probability:8, code:"WM116"/* 诸神的黄昏 */],
		[probability:8, code:"WP115"/* 狂暴之枪 */],
		[probability:8, code:"WS119"/* 丘比特之弓 */],
		[probability:8, code:"WS218"/* 天裂 */],
		[probability:8, code:"WT115"/* 惊鸿 */],
		[probability:8, code:"DA113"/* 虎刹魔铠 */],
		[probability:8, code:"DA213"/* 幽绿之眼 */],
		[probability:8, code:"DS112"/* 暗黑盾 */],
		[probability:8, code:"OM116"/* 龙之护身 */],
		[probability:33, code:"DB116"/* 赤龙战靴 */],
		[probability:33, code:"DG115"/* 赤龙护手 */],
		[probability:33, code:"OA213"/* 璇彩臂环 */],
		[probability:33, code:"DB117"/* 烈焰靴 */],
		[probability:33, code:"DG116"/* 星辰护手 */],
		[probability:33, code:"OA215"/* 赤龙臂环 */],
		[probability:5, code:"OR112"/* 王者戒指 */],
		[probability:5, code:"OA111"/* 圣者之链 */],
		[probability:5, code:"OR113"/* 灵魂之戒 */],
		[probability:5, code:"OA112"/* 魔龙之心 */],
		[probability:5, code:"OR114"/* 帝王之戒 */],
		[probability:5, code:"OS103"/* 海精石 */],
		[probability:5, code:"OS104"/* 天仪石 */],
		[probability:5, code:"OS105"/* 冰晶石 */],
		[probability:5, code:"OS106"/* 玄风石 */]
	],
	drops_more:[
	]
]
