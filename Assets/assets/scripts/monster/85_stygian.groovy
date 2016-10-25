monster = [
	ID:'85_stygian',
	Name:'幽灵之主',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:85,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:2200,
		// Attack Status
		Atk:[90, 110],
		AtkSpeed:8,
		Range:200,
		Rating:1250,

		// Defence Status
		Flee:560,
		Absorb:18,
		Block:5,
		DamageStunPers:100,

		// Resistance
		Earth:40,
		Fire:90,
		Ice:20,// Water
		Lighting:25,// Wind
		Poison:30,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:9,
		Real_Sight:400,
		Talks:[],

		// Move Behavier
		Move_Speed:4,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:30,
		SkillDamage:[110, 130],
		SkillDistance:0,
		SkillRange:140,
		SkillRating:20,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:0,
		PotionCount:0,
	],

	Looks:[
		ClassCode:33,
		ArrowPosi:[-8, 50],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:1,
		Model:'char/monster/Monstygianlord/Monstygianlord.ini',
		Sound:0x00001350,
	],

	// Drops
	AllSeeItem:false,
	Exp:33000,
	Quantity:1,
	drops:[
		[probability:3600, code:"NULL"/* Drops nothing */],
		[probability:4150, code:"GG101", value:[240, 360]/* Gold */],
		[probability:250, code:"PL104"/* 顶级恢复生命药水 */],
		[probability:250, code:"PS104"/* 顶级恢复耐力药水 */],
		[probability:250, code:"PL104"/* 顶级恢复生命药水 */],
		[probability:250, code:"PM104"/* 顶级恢复魔法药水 */],
		[probability:38, code:"DA209"/* 学徒披风 */],
		[probability:38, code:"WA108"/* 叼刚 清磐 */],
		[probability:38, code:"WC108"/* 矩记飘 府亥瘤 */],
		[probability:38, code:"WH109"/* 固萍绢扼捞飘 */],
		[probability:38, code:"WM109"/* 绊胶飘 */],
		[probability:38, code:"WP109"/* 单喉 荤捞靛 */],
		[probability:38, code:"WS210"/* 敲饭萍逞 家靛 */],
		[probability:38, code:"WS110"/* 固胶飘 */],
		[probability:38, code:"WT109"/* 酒唱能促 */],
		[probability:38, code:"DB108"/* 百战靴 */],
		[probability:38, code:"OM109"/* 暗月 */],
		[probability:38, code:"DS108"/* 赤龙焰盾 */],
		[probability:38, code:"DG108"/* 大地护手 */],
		[probability:38, code:"OA208"/* 百炼臂环 */],
		[probability:38, code:"OR111"/* 封印之戒 */],
		[probability:38, code:"OA111"/* 圣者之链 */],
		[probability:38, code:"EC102"/* 回城卷 */],
		[probability:38, code:"EC105"/* 公会卷轴 */],
		[probability:19, code:"DA210"/* 信徒披风 */],
		[probability:19, code:"WA109"/* 破山斧 */],
		[probability:19, code:"WC109"/* 兽之斧刃 */],
		[probability:19, code:"WH110"/* 轩辕巨锤 */],
		[probability:19, code:"WM110"/* 圣者杖 */],
		[probability:19, code:"WP110"/* 白银之枪 */],
		[probability:19, code:"WS211"/* 斩马刀 */],
		[probability:19, code:"WS111"/* 龙骨战弓 */],
		[probability:19, code:"WT110"/* 飞云标 */],
		[probability:19, code:"DB109"/* 大地靴 */],
		[probability:19, code:"OM110"/* 蓝色星辰 */],
		[probability:19, code:"DS109"/* 圣盾 */],
		[probability:19, code:"DG109"/* 神力护手 */],
		[probability:19, code:"OA209"/* 飞翼臂环 */],
		[probability:19, code:"OS105"/* 冰晶石 */],
		[probability:19, code:"OR112"/* 王者戒指 */],
		[probability:19, code:"OA112"/* 魔龙之心 */],
		[probability:19, code:"EC105"/* 公会卷轴 */],
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
		[probability:8, code:"OM111"/* 淬火乌晶 */],
		[probability:8, code:"DS110"/* 宙斯盾 */],
		[probability:8, code:"DG110"/* 火云护手 */],
		[probability:8, code:"OA210"/* 百川流水臂环 */],
		[probability:8, code:"OS106"/* 玄风石 */],
		[probability:8, code:"OR113"/* 灵魂之戒 */],
		[probability:8, code:"OA113"/* 生命之链 */],
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
		[probability:2, code:"OM112"/* 菱晶石 */],
		[probability:2, code:"DS111"/* 苍穹之盾 */],
		[probability:2, code:"DG111"/* 黄铜护手 */],
		[probability:2, code:"OA211"/* 玄铁臂环 */],
		[probability:2, code:"OS106"/* 玄风石 */],
		[probability:2, code:"OS107"/* 水晶石 */],
		[probability:2, code:"OR114"/* 帝王之戒 */],
		[probability:2, code:"OA114"/* 神之庇护 */],
		[probability:2, code:"GP110"/* 守护圣徒水晶 */]
	],
	drops_more:[
	]
]
