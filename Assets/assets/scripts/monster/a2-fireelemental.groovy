monster = [
	ID:'a2-fireelemental',
	Name:'火焰元素',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:60,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:2000,
		// Attack Status
		Atk:[80, 95],
		AtkSpeed:7,
		Range:230,
		Rating:400,

		// Defence Status
		Flee:220,
		Absorb:50,
		Block:0,
		DamageStunPers:100,

		// Resistance
		Earth:0,
		Fire:100,
		Ice:100,// Water
		Lighting:100,// Wind
		Poison:100,
	],

	// AI
	AI:[
		Nature:0x80,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:7,
		Real_Sight:380,
		Talks:[],

		// Move Behavier
		Move_Speed:3,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:0,
		SkillDamage:[95, 125],
		SkillDistance:70,
		SkillRange:100,
		SkillRating:17,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:0,
		PotionCount:0,
	],

	Looks:[
		ClassCode:0,
		ArrowPosi:[-8, 200],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:2,
		Model:'char/monster/Firegolem/Firegolem.INI',
		Sound:0x00003030,
	],

	// Drops
	AllSeeItem:false,
	Exp:3400,
	Quantity:1,
	drops:[
		[probability:3430, code:"NULL"/* Drops nothing */],
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
		[probability:1000, code:"EC105"/* 公会卷轴 */]
	],
	drops_more:[
	]
]
