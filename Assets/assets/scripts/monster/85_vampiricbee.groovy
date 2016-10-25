monster = [
	ID:'85_vampiricbee',
	Name:'嗜血蜂',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:85,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:1200,
		// Attack Status
		Atk:[40, 50],
		AtkSpeed:8,
		Range:70,
		Rating:1200,

		// Defence Status
		Flee:600,
		Absorb:16,
		Block:0,
		DamageStunPers:100,

		// Resistance
		Earth:10,
		Fire:0,
		Ice:30,// Water
		Lighting:0,// Wind
		Poison:50,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:9,
		Real_Sight:380,
		Talks:[],

		// Move Behavier
		Move_Speed:1,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:15,
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
		ClassCode:0,
		ArrowPosi:[-10, 150],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:1,
		Model:'char/monster/V_bee/V_bee.ini',
		Sound:0x000015B0,
	],

	// Drops
	AllSeeItem:false,
	Exp:4000,
	Quantity:1,
	drops:[
		[probability:1100, code:"NULL"/* Drops nothing */],
		[probability:4600, code:"GG101", value:[120, 170]/* Gold */],
		[probability:1520, code:"PL104"/* 顶级恢复生命药水 */],
		[probability:1520, code:"PM104"/* 顶级恢复魔法药水 */],
		[probability:44, code:"DA110"/* 百裂铠 */],
		[probability:44, code:"DA210"/* 信徒披风 */],
		[probability:44, code:"WA109"/* 破山斧 */],
		[probability:44, code:"WC109"/* 兽之斧刃 */],
		[probability:44, code:"WH110"/* 轩辕巨锤 */],
		[probability:44, code:"WM110"/* 圣者杖 */],
		[probability:44, code:"WP110"/* 白银之枪 */],
		[probability:44, code:"WS111"/* 龙骨战弓 */],
		[probability:44, code:"WS211"/* 斩马刀 */],
		[probability:44, code:"WT110"/* 飞云标 */],
		[probability:44, code:"OM110"/* 蓝色星辰 */],
		[probability:44, code:"DB108"/* 百战靴 */],
		[probability:44, code:"DS108"/* 赤龙焰盾 */],
		[probability:44, code:"DG108"/* 大地护手 */],
		[probability:44, code:"OA208"/* 百炼臂环 */],
		[probability:44, code:"OR110"/* 伏魔戒指 */],
		[probability:44, code:"OA110"/* 镇魂铃 */],
		[probability:44, code:"EC105"/* 公会卷轴 */],
		[probability:21, code:"DA111"/* 重装机铠 */],
		[probability:21, code:"DA211"/* 大法师袍 */],
		[probability:21, code:"WA110"/* 定神斧 */],
		[probability:21, code:"WC110"/* 九头刺蛇爪 */],
		[probability:21, code:"WH111"/* 赤冥之锤 */],
		[probability:21, code:"WM111"/* 王者杖 */],
		[probability:21, code:"WP111"/* 屠龙枪 */],
		[probability:21, code:"WS212"/* 嗜血屠魔剑 */],
		[probability:21, code:"WS112"/* 人马之辉 */],
		[probability:21, code:"WT111"/* 神标 */],
		[probability:21, code:"OM111"/* 淬火乌晶 */],
		[probability:21, code:"DB109"/* 大地靴 */],
		[probability:21, code:"DS109"/* 圣盾 */],
		[probability:21, code:"DG109"/* 神力护手 */],
		[probability:21, code:"OA209"/* 飞翼臂环 */],
		[probability:21, code:"OR111"/* 封印之戒 */],
		[probability:21, code:"OA111"/* 圣者之链 */],
		[probability:21, code:"OS105"/* 冰晶石 */],
		[probability:21, code:"OS106"/* 玄风石 */],
		[probability:3, code:"DA112"/* 战神宝铠 */],
		[probability:3, code:"DA212"/* 红莲战袍 */],
		[probability:3, code:"WA111"/* 天阙斧 */],
		[probability:3, code:"WC111"/* 利维坦 */],
		[probability:3, code:"WH112"/* 碎星锤 */],
		[probability:3, code:"WM112"/* 审判之杖 */],
		[probability:3, code:"WP112"/* 傲天枪 */],
		[probability:3, code:"WS213"/* 双截刃 */],
		[probability:3, code:"WS113"/* 猛犸巨弩 */],
		[probability:3, code:"WT112"/* 鸩尾标 */],
		[probability:3, code:"OM112"/* 菱晶石 */],
		[probability:3, code:"DB110"/* 地火战靴 */],
		[probability:3, code:"DS110"/* 宙斯盾 */],
		[probability:3, code:"DG110"/* 火云护手 */],
		[probability:3, code:"OA210"/* 百川流水臂环 */],
		[probability:3, code:"OR111"/* 封印之戒 */],
		[probability:3, code:"OA111"/* 圣者之链 */],
		[probability:3, code:"OS106"/* 玄风石 */],
		[probability:3, code:"OS106"/* 玄风石 */]
	],
	drops_more:[
	]
]
