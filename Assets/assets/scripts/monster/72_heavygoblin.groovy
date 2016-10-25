monster = [
	ID:'72_heavygoblin',
	Name:'重装魔兽兵',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:72,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:1400,
		// Attack Status
		Atk:[36, 44],
		AtkSpeed:8,
		Range:75,
		Rating:1100,

		// Defence Status
		Flee:700,
		Absorb:12,
		Block:0,
		DamageStunPers:100,

		// Resistance
		Earth:30,
		Fire:10,
		Ice:45,// Water
		Lighting:20,// Wind
		Poison:0,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:9,
		Real_Sight:350,
		Talks:[],

		// Move Behavier
		Move_Speed:5,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:0,
		SkillDamage:[45, 55],
		SkillDistance:80,
		SkillRange:55,
		SkillRating:18,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:0,
		PotionCount:0,
	],

	Looks:[
		ClassCode:31,
		ArrowPosi:[0, 40],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:-1,
		Model:'char/monster/Monheavygoblin/Monheavygoblin.ini',
		Sound:0x00001430,
	],

	// Drops
	AllSeeItem:false,
	Exp:18900,
	Quantity:1,
	drops:[
		[probability:2540, code:"NULL"/* Drops nothing */],
		[probability:3500, code:"GG101", value:[350, 490]/* Gold */],
		[probability:277, code:"PL104"/* 顶级恢复生命药水 */],
		[probability:277, code:"PS104"/* 顶级恢复耐力药水 */],
		[probability:277, code:"PM104"/* 顶级恢复魔法药水 */],
		[probability:277, code:"PM104"/* 顶级恢复魔法药水 */],
		[probability:277, code:"GP105"/* 魔剑士水晶 */],
		[probability:277, code:"GP106"/* 火灵王水晶 */],
		[probability:277, code:"GP107"/* 独角兽王水晶 */],
		[probability:277, code:"GP108"/* 绿巨人水晶 */],
		[probability:277, code:"GP111"/* 大头蜘蛛水晶 */],
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
		[probability:72, code:"EC105"/* 公会卷轴 */],
		[probability:25, code:"DA110"/* 百裂铠 */],
		[probability:25, code:"DA210"/* 信徒披风 */],
		[probability:25, code:"WC109"/* 兽之斧刃 */],
		[probability:25, code:"WH110"/* 轩辕巨锤 */],
		[probability:25, code:"WM110"/* 圣者杖 */],
		[probability:25, code:"WP110"/* 白银之枪 */],
		[probability:25, code:"WS211"/* 斩马刀 */],
		[probability:25, code:"DB109"/* 大地靴 */],
		[probability:25, code:"DS109"/* 圣盾 */],
		[probability:25, code:"DG109"/* 神力护手 */],
		[probability:25, code:"OA209"/* 飞翼臂环 */],
		[probability:25, code:"EC102"/* 回城卷 */],
		[probability:25, code:"OR113"/* 灵魂之戒 */],
		[probability:25, code:"OA113"/* 生命之链 */],
		[probability:25, code:"EC102"/* 回城卷 */],
		[probability:25, code:"OS106"/* 玄风石 */],
		[probability:13, code:"DA211"/* 大法师袍 */],
		[probability:13, code:"WH111"/* 赤冥之锤 */],
		[probability:13, code:"WM111"/* 王者杖 */],
		[probability:13, code:"WS112"/* 人马之辉 */],
		[probability:13, code:"WT110"/* 飞云标 */],
		[probability:13, code:"DB110"/* 地火战靴 */],
		[probability:13, code:"DS110"/* 宙斯盾 */],
		[probability:13, code:"DG110"/* 火云护手 */],
		[probability:13, code:"OA210"/* 百川流水臂环 */],
		[probability:13, code:"EC102"/* 回城卷 */],
		[probability:13, code:"OR113"/* 灵魂之戒 */],
		[probability:13, code:"OA113"/* 生命之链 */],
		[probability:13, code:"EC102"/* 回城卷 */],
		[probability:13, code:"OS106"/* 玄风石 */],
		[probability:13, code:"GP117"/* 绿色神秘水晶 */],
		[probability:5, code:"WH112"/* 碎星锤 */],
		[probability:5, code:"DB111"/* 圣靴 */],
		[probability:5, code:"DS111"/* 苍穹之盾 */],
		[probability:5, code:"DG111"/* 黄铜护手 */],
		[probability:5, code:"OA211"/* 玄铁臂环 */],
		[probability:5, code:"OR114"/* 帝王之戒 */],
		[probability:5, code:"OA114"/* 神之庇护 */],
		[probability:5, code:"OS106"/* 玄风石 */],
		[probability:5, code:"OS107"/* 水晶石 */],
		[probability:5, code:"GP110"/* 守护圣徒水晶 */],
		[probability:5, code:"WR101"/* 风魔卷轴A */]
	],
	drops_more:[
		[probability:60, code:"DA110"/* 百裂铠 */]	]
]
