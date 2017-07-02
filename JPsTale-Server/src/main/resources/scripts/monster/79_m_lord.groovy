monster = [
	ID:'79_m_lord',
	Name:'木乃伊旗主',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:79,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:3000,
		// Attack Status
		Atk:[50, 66],
		AtkSpeed:9,
		Range:180,
		Rating:1100,

		// Defence Status
		Flee:700,
		Absorb:13,
		Block:10,
		DamageStunPers:30,

		// Resistance
		Earth:50,
		Fire:20,
		Ice:40,// Water
		Lighting:20,// Wind
		Poison:60,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:9,
		Real_Sight:390,
		Talks:[],

		// Move Behavier
		Move_Speed:4,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:0,
		SkillDamage:[60, 70],
		SkillDistance:0,
		SkillRange:130,
		SkillRating:16,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:0,
		PotionCount:0,
	],

	Looks:[
		ClassCode:0,
		ArrowPosi:[0, 25],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:-1,
		Model:'char/monster/m_lord/m_load.ini',
		Sound:0x00001720,
	],

	// Drops
	AllSeeItem:false,
	Exp:29000,
	Quantity:1,
	drops:[
		[probability:2240, code:"NULL"/* Drops nothing */],
		[probability:4000, code:"GG101", value:[165, 200]/* Gold */],
		[probability:660, code:"PL104"/* 顶级恢复生命药水 */],
		[probability:660, code:"PS104"/* 顶级恢复耐力药水 */],
		[probability:660, code:"PM104"/* 顶级恢复魔法药水 */],
		[probability:55, code:"DA111"/* 重装机铠 */],
		[probability:55, code:"DA211"/* 大法师袍 */],
		[probability:55, code:"WA109"/* 破山斧 */],
		[probability:55, code:"WC109"/* 兽之斧刃 */],
		[probability:55, code:"WH110"/* 轩辕巨锤 */],
		[probability:55, code:"WM110"/* 圣者杖 */],
		[probability:55, code:"WP110"/* 白银之枪 */],
		[probability:55, code:"WS111"/* 龙骨战弓 */],
		[probability:55, code:"WS211"/* 斩马刀 */],
		[probability:55, code:"WT110"/* 飞云标 */],
		[probability:55, code:"DB109"/* 大地靴 */],
		[probability:55, code:"DS109"/* 圣盾 */],
		[probability:55, code:"OM110"/* 蓝色星辰 */],
		[probability:55, code:"DG109"/* 神力护手 */],
		[probability:55, code:"OA209"/* 飞翼臂环 */],
		[probability:55, code:"OR112"/* 王者戒指 */],
		[probability:55, code:"OA112"/* 魔龙之心 */],
		[probability:55, code:"EC105"/* 公会卷轴 */],
		[probability:25, code:"DA112"/* 战神宝铠 */],
		[probability:25, code:"DA212"/* 红莲战袍 */],
		[probability:25, code:"WA110"/* 定神斧 */],
		[probability:25, code:"WC110"/* 九头刺蛇爪 */],
		[probability:25, code:"WH111"/* 赤冥之锤 */],
		[probability:25, code:"WM111"/* 王者杖 */],
		[probability:25, code:"WP111"/* 屠龙枪 */],
		[probability:25, code:"WS112"/* 人马之辉 */],
		[probability:25, code:"WS212"/* 嗜血屠魔剑 */],
		[probability:25, code:"WT111"/* 神标 */],
		[probability:25, code:"DB110"/* 地火战靴 */],
		[probability:25, code:"DS110"/* 宙斯盾 */],
		[probability:25, code:"OM111"/* 淬火乌晶 */],
		[probability:25, code:"DG110"/* 火云护手 */],
		[probability:25, code:"OA210"/* 百川流水臂环 */],
		[probability:25, code:"EC102"/* 回城卷 */],
		[probability:25, code:"OR113"/* 灵魂之戒 */],
		[probability:25, code:"OA113"/* 生命之链 */],
		[probability:25, code:"OS106"/* 玄风石 */],
		[probability:25, code:"OS107"/* 水晶石 */],
		[probability:9, code:"DA113"/* 虎刹魔铠 */],
		[probability:9, code:"DA213"/* 幽绿之眼 */],
		[probability:9, code:"WA111"/* 天阙斧 */],
		[probability:9, code:"WC111"/* 利维坦 */],
		[probability:9, code:"WH112"/* 碎星锤 */],
		[probability:9, code:"WM112"/* 审判之杖 */],
		[probability:9, code:"WP112"/* 傲天枪 */],
		[probability:9, code:"WS113"/* 猛犸巨弩 */],
		[probability:9, code:"WS213"/* 双截刃 */],
		[probability:9, code:"WT112"/* 鸩尾标 */],
		[probability:9, code:"DB110"/* 地火战靴 */],
		[probability:9, code:"DS111"/* 苍穹之盾 */],
		[probability:9, code:"OM112"/* 菱晶石 */],
		[probability:9, code:"DG111"/* 黄铜护手 */],
		[probability:9, code:"OA211"/* 玄铁臂环 */],
		[probability:9, code:"EC102"/* 回城卷 */],
		[probability:9, code:"OR114"/* 帝王之戒 */],
		[probability:9, code:"OA114"/* 神之庇护 */],
		[probability:9, code:"EC102"/* 回城卷 */],
		[probability:9, code:"OS107"/* 水晶石 */],
		[probability:9, code:"GP110"/* 守护圣徒水晶 */],
		[probability:3, code:"DA114"/* 星晨宝铠 */],
		[probability:3, code:"DA214"/* 绯红之眼 */],
		[probability:3, code:"WA112"/* 奥丁斧 */],
		[probability:3, code:"WC112"/* 飞龙爪 */],
		[probability:3, code:"WH113"/* 破日锤 */],
		[probability:3, code:"WM113"/* 魔蜓杖 */],
		[probability:3, code:"WP113"/* 冥河战镰 */],
		[probability:3, code:"WS214"/* 金刚伏魔剑 */],
		[probability:3, code:"WS214"/* 金刚伏魔剑 */],
		[probability:3, code:"WT113"/* 魔龙标 */],
		[probability:3, code:"DB111"/* 圣靴 */],
		[probability:3, code:"DS112"/* 暗黑盾 */],
		[probability:3, code:"OM113"/* 西法路 */],
		[probability:3, code:"DG112"/* 巨灵护手 */],
		[probability:3, code:"OA212"/* 紫焰臂环 */],
		[probability:3, code:"EC102"/* 回城卷 */],
		[probability:3, code:"OR114"/* 帝王之戒 */],
		[probability:3, code:"OA114"/* 神之庇护 */],
		[probability:3, code:"EC102"/* 回城卷 */],
		[probability:3, code:"OS108"/* 虎翼石 */],
		[probability:3, code:"GP112"/* 鬼影魔神水晶 */]
	],
	drops_more:[
		[probability:200, code:"DA111"/* 重装机铠 */]	]
]
