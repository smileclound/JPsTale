monster = [
	ID:'85_typhoon',
	Name:'台风',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:85,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:4000,
		// Attack Status
		Atk:[60, 70],
		AtkSpeed:7,
		Range:80,
		Rating:1300,

		// Defence Status
		Flee:720,
		Absorb:23,
		Block:8,
		DamageStunPers:65,

		// Resistance
		Earth:60,
		Fire:40,
		Ice:40,// Water
		Lighting:20,// Wind
		Poison:70,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:8,
		Real_Sight:380,
		Talks:[],

		// Move Behavier
		Move_Speed:4,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:20,
		SkillDamage:[0, 0],
		SkillDistance:0,
		SkillRange:0,
		SkillRating:0,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:40,
		PotionCount:1,
	],

	Looks:[
		ClassCode:9,
		ArrowPosi:[-2, 30],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:-1,
		Model:'char/monster/Monhulk-1/Monhulk-1.ini',
		Sound:0x000012B0,
	],

	// Drops
	AllSeeItem:false,
	Exp:37500,
	Quantity:1,
	drops:[
		[probability:2490, code:"NULL"/* Drops nothing */],
		[probability:3400, code:"GG101", value:[500, 600]/* Gold */],
		[probability:300, code:"PL104"/* 顶级恢复生命药水 */],
		[probability:300, code:"PS104"/* 顶级恢复耐力药水 */],
		[probability:300, code:"PM104"/* 顶级恢复魔法药水 */],
		[probability:300, code:"PM104"/* 顶级恢复魔法药水 */],
		[probability:300, code:"PL104"/* 顶级恢复生命药水 */],
		[probability:300, code:"PM104"/* 顶级恢复魔法药水 */],
		[probability:109, code:"DA109"/* 黄铜战铠 */],
		[probability:109, code:"WA108"/* 叼刚 清磐 */],
		[probability:109, code:"WH109"/* 固萍绢扼捞飘 */],
		[probability:109, code:"DB108"/* 百战靴 */],
		[probability:109, code:"DS108"/* 赤龙焰盾 */],
		[probability:109, code:"OM109"/* 暗月 */],
		[probability:109, code:"DG108"/* 大地护手 */],
		[probability:109, code:"OA208"/* 百炼臂环 */],
		[probability:109, code:"OR112"/* 王者戒指 */],
		[probability:109, code:"OA112"/* 魔龙之心 */],
		[probability:109, code:"GP108"/* 绿巨人水晶 */],
		[probability:37, code:"DA110"/* 百裂铠 */],
		[probability:37, code:"DA210"/* 信徒披风 */],
		[probability:37, code:"WC109"/* 兽之斧刃 */],
		[probability:37, code:"WH110"/* 轩辕巨锤 */],
		[probability:37, code:"WM110"/* 圣者杖 */],
		[probability:37, code:"WP110"/* 白银之枪 */],
		[probability:37, code:"WS211"/* 斩马刀 */],
		[probability:37, code:"WT110"/* 飞云标 */],
		[probability:37, code:"DB109"/* 大地靴 */],
		[probability:37, code:"DS109"/* 圣盾 */],
		[probability:37, code:"DG109"/* 神力护手 */],
		[probability:37, code:"OA209"/* 飞翼臂环 */],
		[probability:37, code:"OR113"/* 灵魂之戒 */],
		[probability:37, code:"OA113"/* 生命之链 */],
		[probability:37, code:"OS106"/* 玄风石 */],
		[probability:37, code:"EC105"/* 公会卷轴 */],
		[probability:23, code:"DA211"/* 大法师袍 */],
		[probability:23, code:"WH111"/* 赤冥之锤 */],
		[probability:23, code:"WM111"/* 王者杖 */],
		[probability:23, code:"WS112"/* 人马之辉 */],
		[probability:23, code:"DB110"/* 地火战靴 */],
		[probability:23, code:"DS110"/* 宙斯盾 */],
		[probability:23, code:"DG110"/* 火云护手 */],
		[probability:23, code:"OA210"/* 百川流水臂环 */],
		[probability:23, code:"OR113"/* 灵魂之戒 */],
		[probability:23, code:"OA113"/* 生命之链 */],
		[probability:23, code:"OS106"/* 玄风石 */],
		[probability:23, code:"OS106"/* 玄风石 */],
		[probability:23, code:"OS107"/* 水晶石 */],
		[probability:12, code:"WH112"/* 碎星锤 */],
		[probability:12, code:"DB111"/* 圣靴 */],
		[probability:12, code:"DS111"/* 苍穹之盾 */],
		[probability:12, code:"DG111"/* 黄铜护手 */],
		[probability:12, code:"OA211"/* 玄铁臂环 */],
		[probability:12, code:"OR114"/* 帝王之戒 */],
		[probability:12, code:"OA114"/* 神之庇护 */],
		[probability:12, code:"WR102"/* 风魔卷轴B */],
		[probability:12, code:"OS107"/* 水晶石 */],
		[probability:12, code:"GP109"/* 神秘水晶 */],
		[probability:12, code:"GP110"/* 守护圣徒水晶 */],
		[probability:12, code:"GP112"/* 鬼影魔神水晶 */],
		[probability:4, code:"WA112"/* 奥丁斧 */],
		[probability:4, code:"WH113"/* 破日锤 */],
		[probability:4, code:"WM113"/* 魔蜓杖 */],
		[probability:4, code:"WS214"/* 金刚伏魔剑 */],
		[probability:4, code:"DB112"/* 破棘之靴 */],
		[probability:4, code:"DS112"/* 暗黑盾 */],
		[probability:4, code:"DG112"/* 巨灵护手 */],
		[probability:4, code:"OA212"/* 紫焰臂环 */],
		[probability:4, code:"OR115"/* 守护之戒 */],
		[probability:4, code:"OA115"/* 暗印护符 */],
		[probability:4, code:"OS107"/* 水晶石 */],
		[probability:4, code:"OS108"/* 虎翼石 */],
		[probability:4, code:"GP110"/* 守护圣徒水晶 */]
	],
	drops_more:[
		[probability:50, code:"EC105"/* 公会卷轴 */]	]
]
