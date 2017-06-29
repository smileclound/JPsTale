monster = [
	ID:'85_vampiricmachine',
	Name:'嗜血机械',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:85,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:4900,
		// Attack Status
		Atk:[0, 0],
		AtkSpeed:8,
		Range:380,
		Rating:0,

		// Defence Status
		Flee:640,
		Absorb:13,
		Block:0,
		DamageStunPers:100,

		// Resistance
		Earth:100,
		Fire:0,
		Ice:0,// Water
		Lighting:0,// Wind
		Poison:100,
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
		ClassCode:61,
		ArrowPosi:[-10, 150],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:3,
		Model:'char/monster/vampiric/vemp.ini',
		Sound:0x000015C0,
	],

	// Drops
	AllSeeItem:false,
	Exp:43000,
	Quantity:1,
	drops:[
		[probability:1050, code:"NULL"/* Drops nothing */],
		[probability:3500, code:"GG101", value:[350, 480]/* Gold */],
		[probability:1333, code:"PL104"/* 顶级恢复生命药水 */],
		[probability:1333, code:"PM104"/* 顶级恢复魔法药水 */],
		[probability:1333, code:"PM104"/* 顶级恢复魔法药水 */],
		[probability:44, code:"DA109"/* 黄铜战铠 */],
		[probability:44, code:"DA209"/* 学徒披风 */],
		[probability:44, code:"WA108"/* 叼刚 清磐 */],
		[probability:44, code:"WC108"/* 矩记飘 府亥瘤 */],
		[probability:44, code:"WH109"/* 固萍绢扼捞飘 */],
		[probability:44, code:"WM109"/* 绊胶飘 */],
		[probability:44, code:"WP108"/* 血烟长矛 */],
		[probability:44, code:"WS110"/* 固胶飘 */],
		[probability:44, code:"WS210"/* 敲饭萍逞 家靛 */],
		[probability:44, code:"WT109"/* 酒唱能促 */],
		[probability:44, code:"DB108"/* 百战靴 */],
		[probability:44, code:"DS108"/* 赤龙焰盾 */],
		[probability:44, code:"OM109"/* 暗月 */],
		[probability:44, code:"DG108"/* 大地护手 */],
		[probability:44, code:"OA208"/* 百炼臂环 */],
		[probability:44, code:"OR112"/* 王者戒指 */],
		[probability:44, code:"OA112"/* 魔龙之心 */],
		[probability:44, code:"EC105"/* 公会卷轴 */],
		[probability:20, code:"DA110"/* 百裂铠 */],
		[probability:20, code:"DA210"/* 信徒披风 */],
		[probability:20, code:"WA109"/* 破山斧 */],
		[probability:20, code:"WC109"/* 兽之斧刃 */],
		[probability:20, code:"WH110"/* 轩辕巨锤 */],
		[probability:20, code:"WM110"/* 圣者杖 */],
		[probability:20, code:"WP109"/* 单喉 荤捞靛 */],
		[probability:20, code:"WS111"/* 龙骨战弓 */],
		[probability:20, code:"WS211"/* 斩马刀 */],
		[probability:20, code:"WT110"/* 飞云标 */],
		[probability:20, code:"DB109"/* 大地靴 */],
		[probability:20, code:"DS109"/* 圣盾 */],
		[probability:20, code:"OM110"/* 蓝色星辰 */],
		[probability:20, code:"DG109"/* 神力护手 */],
		[probability:20, code:"OA209"/* 飞翼臂环 */],
		[probability:20, code:"OR113"/* 灵魂之戒 */],
		[probability:20, code:"OA113"/* 生命之链 */],
		[probability:20, code:"EC102"/* 回城卷 */],
		[probability:20, code:"OS106"/* 玄风石 */],
		[probability:20, code:"OS107"/* 水晶石 */],
		[probability:8, code:"DA111"/* 重装机铠 */],
		[probability:8, code:"DA211"/* 大法师袍 */],
		[probability:8, code:"WA110"/* 定神斧 */],
		[probability:8, code:"WC110"/* 九头刺蛇爪 */],
		[probability:8, code:"WH111"/* 赤冥之锤 */],
		[probability:8, code:"WM111"/* 王者杖 */],
		[probability:8, code:"WP111"/* 屠龙枪 */],
		[probability:8, code:"WS212"/* 嗜血屠魔剑 */],
		[probability:8, code:"WS112"/* 人马之辉 */],
		[probability:8, code:"WT111"/* 神标 */],
		[probability:8, code:"DB110"/* 地火战靴 */],
		[probability:8, code:"DS110"/* 宙斯盾 */],
		[probability:8, code:"OM111"/* 淬火乌晶 */],
		[probability:8, code:"DG110"/* 火云护手 */],
		[probability:8, code:"OA210"/* 百川流水臂环 */],
		[probability:8, code:"OR113"/* 灵魂之戒 */],
		[probability:8, code:"OA113"/* 生命之链 */],
		[probability:8, code:"EC102"/* 回城卷 */],
		[probability:8, code:"OS106"/* 玄风石 */],
		[probability:8, code:"OS107"/* 水晶石 */],
		[probability:8, code:"OS107"/* 水晶石 */],
		[probability:8, code:"GP108"/* 绿巨人水晶 */],
		[probability:8, code:"GP110"/* 守护圣徒水晶 */],
		[probability:2, code:"DA112"/* 战神宝铠 */],
		[probability:2, code:"DA212"/* 红莲战袍 */],
		[probability:2, code:"WA111"/* 天阙斧 */],
		[probability:2, code:"WC111"/* 利维坦 */],
		[probability:2, code:"WH112"/* 碎星锤 */],
		[probability:2, code:"WM112"/* 审判之杖 */],
		[probability:2, code:"WP112"/* 傲天枪 */],
		[probability:2, code:"WS213"/* 双截刃 */],
		[probability:2, code:"WS113"/* 猛犸巨弩 */],
		[probability:2, code:"WT112"/* 鸩尾标 */],
		[probability:2, code:"DB111"/* 圣靴 */],
		[probability:2, code:"DS111"/* 苍穹之盾 */],
		[probability:2, code:"OM112"/* 菱晶石 */],
		[probability:2, code:"DG111"/* 黄铜护手 */],
		[probability:2, code:"OA211"/* 玄铁臂环 */],
		[probability:2, code:"OR114"/* 帝王之戒 */],
		[probability:2, code:"OA114"/* 神之庇护 */],
		[probability:2, code:"EC102"/* 回城卷 */],
		[probability:2, code:"OS106"/* 玄风石 */],
		[probability:2, code:"OS107"/* 水晶石 */],
		[probability:2, code:"OS108"/* 虎翼石 */],
		[probability:2, code:"GP108"/* 绿巨人水晶 */],
		[probability:2, code:"GP113"/* 铁甲狂魔水晶 */]
	],
	drops_more:[
		[probability:200, code:"PM104"/* 顶级恢复魔法药水 */]	]
]
