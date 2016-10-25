monster = [
	ID:'75_hulk',
	Name:'绿巨人',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:75,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:2000,
		// Attack Status
		Atk:[52, 60],
		AtkSpeed:7,
		Range:70,
		Rating:1200,

		// Defence Status
		Flee:520,
		Absorb:20,
		Block:8,
		DamageStunPers:70,

		// Resistance
		Earth:60,
		Fire:30,
		Ice:30,// Water
		Lighting:20,// Wind
		Poison:50,
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
		PotionPercent:50,
		PotionCount:1,
	],

	Looks:[
		ClassCode:0,
		ArrowPosi:[-2, 30],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:-1,
		Model:'char/monster/Monhulk/Monhulk.ini',
		Sound:0x000012B0,
	],

	// Drops
	AllSeeItem:false,
	Exp:24000,
	Quantity:1,
	drops:[
		[probability:2050, code:"NULL"/* Drops nothing */],
		[probability:4500, code:"GG101", value:[350, 480]/* Gold */],
		[probability:500, code:"PL104"/* 顶级恢复生命药水 */],
		[probability:500, code:"PS104"/* 顶级恢复耐力药水 */],
		[probability:500, code:"PL104"/* 顶级恢复生命药水 */],
		[probability:500, code:"PM104"/* 顶级恢复魔法药水 */],
		[probability:72, code:"DA109"/* 黄铜战铠 */],
		[probability:72, code:"WA108"/* 叼刚 清磐 */],
		[probability:72, code:"WH109"/* 固萍绢扼捞飘 */],
		[probability:72, code:"DB108"/* 百战靴 */],
		[probability:72, code:"DS108"/* 赤龙焰盾 */],
		[probability:72, code:"OM109"/* 暗月 */],
		[probability:72, code:"DG108"/* 大地护手 */],
		[probability:72, code:"OA208"/* 百炼臂环 */],
		[probability:72, code:"OR112"/* 王者戒指 */],
		[probability:72, code:"OA112"/* 魔龙之心 */],
		[probability:72, code:"GP108"/* 绿巨人水晶 */],
		[probability:23, code:"DA110"/* 百裂铠 */],
		[probability:23, code:"DA210"/* 信徒披风 */],
		[probability:23, code:"WC109"/* 兽之斧刃 */],
		[probability:23, code:"WH110"/* 轩辕巨锤 */],
		[probability:23, code:"WM110"/* 圣者杖 */],
		[probability:23, code:"WS211"/* 斩马刀 */],
		[probability:23, code:"WT110"/* 飞云标 */],
		[probability:23, code:"DB109"/* 大地靴 */],
		[probability:23, code:"DS109"/* 圣盾 */],
		[probability:23, code:"DG109"/* 神力护手 */],
		[probability:23, code:"OA209"/* 飞翼臂环 */],
		[probability:23, code:"EC102"/* 回城卷 */],
		[probability:23, code:"OR113"/* 灵魂之戒 */],
		[probability:23, code:"OA113"/* 生命之链 */],
		[probability:23, code:"EC102"/* 回城卷 */],
		[probability:23, code:"OS106"/* 玄风石 */],
		[probability:23, code:"OS106"/* 玄风石 */],
		[probability:11, code:"DA211"/* 大法师袍 */],
		[probability:11, code:"WH111"/* 赤冥之锤 */],
		[probability:11, code:"WM111"/* 王者杖 */],
		[probability:11, code:"WS112"/* 人马之辉 */],
		[probability:11, code:"DB110"/* 地火战靴 */],
		[probability:11, code:"DS110"/* 宙斯盾 */],
		[probability:11, code:"DG110"/* 火云护手 */],
		[probability:11, code:"OA210"/* 百川流水臂环 */],
		[probability:11, code:"EC102"/* 回城卷 */],
		[probability:11, code:"OR113"/* 灵魂之戒 */],
		[probability:11, code:"OA113"/* 生命之链 */],
		[probability:11, code:"EC102"/* 回城卷 */],
		[probability:11, code:"OS106"/* 玄风石 */],
		[probability:11, code:"OS106"/* 玄风石 */],
		[probability:11, code:"OS107"/* 水晶石 */],
		[probability:11, code:"GP108"/* 绿巨人水晶 */],
		[probability:11, code:"GP110"/* 守护圣徒水晶 */],
		[probability:3, code:"WH112"/* 碎星锤 */],
		[probability:3, code:"DB111"/* 圣靴 */],
		[probability:3, code:"DS111"/* 苍穹之盾 */],
		[probability:3, code:"DG111"/* 黄铜护手 */],
		[probability:3, code:"OA211"/* 玄铁臂环 */],
		[probability:3, code:"EC102"/* 回城卷 */],
		[probability:3, code:"OR114"/* 帝王之戒 */],
		[probability:3, code:"OA114"/* 神之庇护 */],
		[probability:3, code:"EC102"/* 回城卷 */],
		[probability:3, code:"OS106"/* 玄风石 */],
		[probability:3, code:"OS107"/* 水晶石 */],
		[probability:3, code:"GP108"/* 绿巨人水晶 */],
		[probability:3, code:"GP110"/* 守护圣徒水晶 */]
	],
	drops_more:[
	]
]
