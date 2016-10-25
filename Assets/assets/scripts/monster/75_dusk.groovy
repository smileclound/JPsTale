monster = [
	ID:'75_dusk',
	Name:'达克',
	Clazz:0,// 1 BOSS
	Brood:0x92,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:75,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:1500,
		// Attack Status
		Atk:[40, 50],
		AtkSpeed:9,
		Range:150,
		Rating:1300,

		// Defence Status
		Flee:530,
		Absorb:12,
		Block:10,
		DamageStunPers:100,

		// Resistance
		Earth:30,
		Fire:15,
		Ice:40,// Water
		Lighting:0,// Wind
		Poison:40,
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
		SpAttackPercetage:15,
		SkillDamage:[50, 60],
		SkillDistance:0,
		SkillRange:110,
		SkillRating:20,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:0,
		PotionCount:0,
	],

	Looks:[
		ClassCode:39,
		ArrowPosi:[0, 0],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:1,
		Model:'char/monster/Monnazz-1/Monnazz-1.ini',
		Sound:0x00001290,
	],

	// Drops
	AllSeeItem:false,
	Exp:22000,
	Quantity:1,
	QuestItem:0x07010D00,
	drops:[
		[probability:2100, code:"NULL"/* Drops nothing */],
		[probability:4000, code:"GG101", value:[160, 190]/* Gold */],
		[probability:360, code:"PL103"/* 高级恢复生命药水 */],
		[probability:360, code:"PS103"/* 高级恢复耐力药水 */],
		[probability:360, code:"PL103"/* 高级恢复生命药水 */],
		[probability:360, code:"PM103"/* 高级恢复魔法药水 */],
		[probability:360, code:"PM102"/* 中级恢复魔法药水 */],
		[probability:70, code:"DA110"/* 百裂铠 */],
		[probability:70, code:"DA210"/* 信徒披风 */],
		[probability:70, code:"WA108"/* 叼刚 清磐 */],
		[probability:70, code:"WC108"/* 矩记飘 府亥瘤 */],
		[probability:70, code:"WH109"/* 固萍绢扼捞飘 */],
		[probability:70, code:"WM109"/* 绊胶飘 */],
		[probability:70, code:"WP109"/* 单喉 荤捞靛 */],
		[probability:70, code:"WS110"/* 固胶飘 */],
		[probability:70, code:"WS210"/* 敲饭萍逞 家靛 */],
		[probability:70, code:"WT109"/* 酒唱能促 */],
		[probability:70, code:"DB108"/* 百战靴 */],
		[probability:70, code:"DS108"/* 赤龙焰盾 */],
		[probability:70, code:"OM109"/* 暗月 */],
		[probability:70, code:"DG108"/* 大地护手 */],
		[probability:70, code:"OA208"/* 百炼臂环 */],
		[probability:70, code:"OR110"/* 伏魔戒指 */],
		[probability:70, code:"OA110"/* 镇魂铃 */],
		[probability:31, code:"DA111"/* 重装机铠 */],
		[probability:31, code:"DA211"/* 大法师袍 */],
		[probability:31, code:"WA109"/* 破山斧 */],
		[probability:31, code:"WC109"/* 兽之斧刃 */],
		[probability:31, code:"WH110"/* 轩辕巨锤 */],
		[probability:31, code:"WM110"/* 圣者杖 */],
		[probability:31, code:"WP110"/* 白银之枪 */],
		[probability:31, code:"WS111"/* 龙骨战弓 */],
		[probability:31, code:"WS211"/* 斩马刀 */],
		[probability:31, code:"WT110"/* 飞云标 */],
		[probability:31, code:"DB109"/* 大地靴 */],
		[probability:31, code:"DS109"/* 圣盾 */],
		[probability:31, code:"OM110"/* 蓝色星辰 */],
		[probability:31, code:"DG109"/* 神力护手 */],
		[probability:31, code:"OA209"/* 飞翼臂环 */],
		[probability:31, code:"EC102"/* 回城卷 */],
		[probability:31, code:"OR111"/* 封印之戒 */],
		[probability:31, code:"OA111"/* 圣者之链 */],
		[probability:31, code:"EC105"/* 公会卷轴 */],
		[probability:12, code:"DA112"/* 战神宝铠 */],
		[probability:12, code:"DA212"/* 红莲战袍 */],
		[probability:12, code:"WA110"/* 定神斧 */],
		[probability:12, code:"WC110"/* 九头刺蛇爪 */],
		[probability:12, code:"WH111"/* 赤冥之锤 */],
		[probability:12, code:"WM111"/* 王者杖 */],
		[probability:12, code:"WP111"/* 屠龙枪 */],
		[probability:12, code:"WS112"/* 人马之辉 */],
		[probability:12, code:"WS212"/* 嗜血屠魔剑 */],
		[probability:12, code:"WT111"/* 神标 */],
		[probability:12, code:"DB110"/* 地火战靴 */],
		[probability:12, code:"DS110"/* 宙斯盾 */],
		[probability:12, code:"OM111"/* 淬火乌晶 */],
		[probability:12, code:"DG110"/* 火云护手 */],
		[probability:12, code:"OA210"/* 百川流水臂环 */],
		[probability:12, code:"EC102"/* 回城卷 */],
		[probability:12, code:"OR112"/* 王者戒指 */],
		[probability:12, code:"OA112"/* 魔龙之心 */],
		[probability:12, code:"OS106"/* 玄风石 */],
		[probability:12, code:"OS107"/* 水晶石 */],
		[probability:2, code:"DA113"/* 虎刹魔铠 */],
		[probability:2, code:"DA213"/* 幽绿之眼 */],
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
		[probability:2, code:"GP110"/* 守护圣徒水晶 */]
	],
	drops_more:[
		[probability:50, code:"DA110"/* 百裂铠 */]	]
]
