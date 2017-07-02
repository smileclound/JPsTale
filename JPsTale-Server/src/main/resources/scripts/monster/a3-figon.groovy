monster = [
	ID:'a3-figon',
	Name:'火灵魔王',
	Clazz:0,// 1 BOSS
	Brood:0x92,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:69,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:900,
		// Attack Status
		Atk:[55, 65],
		AtkSpeed:8,
		Range:230,
		Rating:1200,

		// Defence Status
		Flee:350,
		Absorb:9,
		Block:0,
		DamageStunPers:100,

		// Resistance
		Earth:0,
		Fire:100,
		Ice:0,// Water
		Lighting:30,// Wind
		Poison:100,
	],

	// AI
	AI:[
		Nature:0x80,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:8,
		Real_Sight:410,
		Talks:[],

		// Move Behavier
		Move_Speed:3,
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
		ClassCode:21,
		ArrowPosi:[5, 40],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:3,
		Model:'char/monster/figon/figon.ini',
		Sound:0x00001180,
	],

	// Drops
	AllSeeItem:false,
	Exp:6000,
	Quantity:1,
	drops:[
		[probability:4430, code:"NULL"/* Drops nothing */],
		[probability:4530, code:"GG101", value:[65, 80]/* Gold */],
		[probability:37, code:"WA108"/* 叼刚 清磐 */],
		[probability:37, code:"WC108"/* 矩记飘 府亥瘤 */],
		[probability:37, code:"WH109"/* 固萍绢扼捞飘 */],
		[probability:37, code:"WM109"/* 绊胶飘 */],
		[probability:37, code:"WP109"/* 单喉 荤捞靛 */],
		[probability:37, code:"WS110"/* 固胶飘 */],
		[probability:37, code:"WS211"/* 斩马刀 */],
		[probability:37, code:"WT109"/* 酒唱能促 */],
		[probability:37, code:"DA109"/* 黄铜战铠 */],
		[probability:37, code:"DA209"/* 学徒披风 */],
		[probability:37, code:"DS108"/* 赤龙焰盾 */],
		[probability:37, code:"OM110"/* 蓝色星辰 */],
		[probability:17, code:"WA110"/* 定神斧 */],
		[probability:17, code:"WC110"/* 九头刺蛇爪 */],
		[probability:17, code:"WH110"/* 轩辕巨锤 */],
		[probability:17, code:"WM110"/* 圣者杖 */],
		[probability:17, code:"WP110"/* 白银之枪 */],
		[probability:17, code:"WS112"/* 人马之辉 */],
		[probability:17, code:"WS212"/* 嗜血屠魔剑 */],
		[probability:17, code:"WT111"/* 神标 */],
		[probability:17, code:"DA110"/* 百裂铠 */],
		[probability:17, code:"DA210"/* 信徒披风 */],
		[probability:17, code:"DS109"/* 圣盾 */],
		[probability:17, code:"OM112"/* 菱晶石 */],
		[probability:10, code:"WA110"/* 定神斧 */],
		[probability:10, code:"WC110"/* 九头刺蛇爪 */],
		[probability:10, code:"WH110"/* 轩辕巨锤 */],
		[probability:10, code:"WM110"/* 圣者杖 */],
		[probability:10, code:"WP110"/* 白银之枪 */],
		[probability:10, code:"WS112"/* 人马之辉 */],
		[probability:10, code:"WS212"/* 嗜血屠魔剑 */],
		[probability:10, code:"WT111"/* 神标 */],
		[probability:10, code:"DA110"/* 百裂铠 */],
		[probability:10, code:"DA210"/* 信徒披风 */],
		[probability:10, code:"DS109"/* 圣盾 */],
		[probability:10, code:"OM112"/* 菱晶石 */],
		[probability:35, code:"DB115"/* 时空之靴 */],
		[probability:35, code:"DG113"/* 鲲鹏护手 */],
		[probability:35, code:"OA212"/* 紫焰臂环 */],
		[probability:35, code:"DB116"/* 赤龙战靴 */],
		[probability:35, code:"DG115"/* 赤龙护手 */],
		[probability:35, code:"OA213"/* 璇彩臂环 */],
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
