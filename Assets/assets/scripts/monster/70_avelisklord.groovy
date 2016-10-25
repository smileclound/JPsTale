monster = [
	ID:'70_avelisklord',
	Name:'魔锤人马',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:70,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:1300,
		// Attack Status
		Atk:[45, 54],
		AtkSpeed:8,
		Range:85,
		Rating:1080,

		// Defence Status
		Flee:600,
		Absorb:17,
		Block:15,
		DamageStunPers:80,

		// Resistance
		Earth:40,
		Fire:60,
		Ice:0,// Water
		Lighting:30,// Wind
		Poison:100,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:9,
		Real_Sight:380,
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
		ClassCode:38,
		ArrowPosi:[0, 40],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:3,
		Model:'char/monster/Monavelrisk/Monavelrisk-Mass.ini',
		Sound:0x00001280,
	],

	// Drops
	AllSeeItem:false,
	Exp:22000,
	Quantity:1,
	QuestItem:0x07010D00,
	drops:[
		[probability:2400, code:"NULL"/* Drops nothing */],
		[probability:4800, code:"GG101", value:[160, 240]/* Gold */],
		[probability:400, code:"PL103"/* 高级恢复生命药水 */],
		[probability:400, code:"PL103"/* 高级恢复生命药水 */],
		[probability:400, code:"PS103"/* 高级恢复耐力药水 */],
		[probability:400, code:"PM103"/* 高级恢复魔法药水 */],
		[probability:400, code:"PM102"/* 中级恢复魔法药水 */],
		[probability:28, code:"DA110"/* 百裂铠 */],
		[probability:28, code:"DA210"/* 信徒披风 */],
		[probability:28, code:"WA109"/* 破山斧 */],
		[probability:28, code:"WC109"/* 兽之斧刃 */],
		[probability:28, code:"WH110"/* 轩辕巨锤 */],
		[probability:28, code:"WM110"/* 圣者杖 */],
		[probability:28, code:"WP110"/* 白银之枪 */],
		[probability:28, code:"WS111"/* 龙骨战弓 */],
		[probability:28, code:"WS211"/* 斩马刀 */],
		[probability:28, code:"WT110"/* 飞云标 */],
		[probability:28, code:"DB109"/* 大地靴 */],
		[probability:28, code:"DS109"/* 圣盾 */],
		[probability:28, code:"OM110"/* 蓝色星辰 */],
		[probability:28, code:"DG109"/* 神力护手 */],
		[probability:28, code:"OA209"/* 飞翼臂环 */],
		[probability:28, code:"EC102"/* 回城卷 */],
		[probability:28, code:"OR111"/* 封印之戒 */],
		[probability:28, code:"OA111"/* 圣者之链 */],
		[probability:28, code:"EC105"/* 公会卷轴 */],
		[probability:10, code:"DA111"/* 重装机铠 */],
		[probability:10, code:"DA211"/* 大法师袍 */],
		[probability:10, code:"WA110"/* 定神斧 */],
		[probability:10, code:"WC110"/* 九头刺蛇爪 */],
		[probability:10, code:"WH111"/* 赤冥之锤 */],
		[probability:10, code:"WM111"/* 王者杖 */],
		[probability:10, code:"WP111"/* 屠龙枪 */],
		[probability:10, code:"WS112"/* 人马之辉 */],
		[probability:10, code:"WS212"/* 嗜血屠魔剑 */],
		[probability:10, code:"WT111"/* 神标 */],
		[probability:10, code:"DB110"/* 地火战靴 */],
		[probability:10, code:"DS110"/* 宙斯盾 */],
		[probability:10, code:"OM111"/* 淬火乌晶 */],
		[probability:10, code:"DG110"/* 火云护手 */],
		[probability:10, code:"OA210"/* 百川流水臂环 */],
		[probability:10, code:"EC102"/* 回城卷 */],
		[probability:10, code:"OR112"/* 王者戒指 */],
		[probability:10, code:"OA112"/* 魔龙之心 */],
		[probability:10, code:"OS106"/* 玄风石 */],
		[probability:10, code:"EC105"/* 公会卷轴 */],
		[probability:2, code:"DA112"/* 战神宝铠 */],
		[probability:2, code:"DA212"/* 红莲战袍 */],
		[probability:2, code:"WA111"/* 天阙斧 */],
		[probability:2, code:"WC111"/* 利维坦 */],
		[probability:2, code:"WH112"/* 碎星锤 */],
		[probability:2, code:"WM112"/* 审判之杖 */],
		[probability:2, code:"WP112"/* 傲天枪 */],
		[probability:2, code:"WS113"/* 猛犸巨弩 */],
		[probability:2, code:"WS213"/* 双截刃 */],
		[probability:2, code:"WT112"/* 鸩尾标 */],
		[probability:2, code:"DB111"/* 圣靴 */],
		[probability:2, code:"DS111"/* 苍穹之盾 */],
		[probability:2, code:"OM112"/* 菱晶石 */],
		[probability:2, code:"DG111"/* 黄铜护手 */],
		[probability:2, code:"OA211"/* 玄铁臂环 */],
		[probability:2, code:"EC102"/* 回城卷 */],
		[probability:2, code:"OR113"/* 灵魂之戒 */],
		[probability:2, code:"OA113"/* 生命之链 */],
		[probability:2, code:"OS107"/* 水晶石 */],
		[probability:2, code:"EC105"/* 公会卷轴 */],
		[probability:2, code:"GP109"/* 神秘水晶 */],
		[probability:2, code:"GP110"/* 守护圣徒水晶 */]
	],
	drops_more:[
		[probability:50, code:"GP111"/* 大头蜘蛛水晶 */]	]
]
