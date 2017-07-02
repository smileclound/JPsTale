monster = [
	ID:'61_avelisk-l',
	Name:'魔枪人马',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:61,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:1250,
		// Attack Status
		Atk:[50, 65],
		AtkSpeed:7,
		Range:85,
		Rating:950,

		// Defence Status
		Flee:510,
		Absorb:11,
		Block:7,
		DamageStunPers:100,

		// Resistance
		Earth:40,
		Fire:0,
		Ice:35,// Water
		Lighting:10,// Wind
		Poison:10,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:9,
		Real_Sight:400,
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
		ClassCode:26,
		ArrowPosi:[0, 40],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:-1,
		Model:'char/monster/Monavelrisk/Monavelrisk-spear.ini',
		Sound:0x00001280,
	],

	// Drops
	AllSeeItem:false,
	Exp:13000,
	Quantity:1,
	QuestItem:0x07010D00,
	drops:[
		[probability:2700, code:"NULL"/* Drops nothing */],
		[probability:3900, code:"GG101", value:[200, 250]/* Gold */],
		[probability:433, code:"PL104"/* 顶级恢复生命药水 */],
		[probability:433, code:"PS104"/* 顶级恢复耐力药水 */],
		[probability:433, code:"PL104"/* 顶级恢复生命药水 */],
		[probability:52, code:"DA109"/* 黄铜战铠 */],
		[probability:52, code:"DA209"/* 学徒披风 */],
		[probability:52, code:"wa106"/* 凯旋 */],
		[probability:52, code:"WC106"/* 狼牙刃 */],
		[probability:52, code:"WH106"/* 浑元金锤 */],
		[probability:52, code:"WM107"/* 天魔杖 */],
		[probability:52, code:"WP107"/* 战神之镰 */],
		[probability:52, code:"WS107"/* 射日弓 */],
		[probability:52, code:"WS208"/* 圣殿武士剑 */],
		[probability:52, code:"WT106"/* 精灵标 */],
		[probability:52, code:"OM107"/* 火星 */],
		[probability:52, code:"DA106"/* 索子甲 */],
		[probability:52, code:"DB106"/* 黄金靴 */],
		[probability:52, code:"DS106"/* 百炼盾 */],
		[probability:52, code:"OM106"/* 水星 */],
		[probability:52, code:"DG106"/* 白金护手 */],
		[probability:52, code:"OA206"/* 精灵臂环 */],
		[probability:52, code:"OR107"/* 法师戒指 */],
		[probability:52, code:"OA107"/* 水晶项链 */],
		[probability:38, code:"DA110"/* 百裂铠 */],
		[probability:38, code:"DA210"/* 信徒披风 */],
		[probability:38, code:"WA107"/* 刺脊斧 */],
		[probability:38, code:"WC107"/* 平手刃 */],
		[probability:38, code:"WH107"/* 十字锤 */],
		[probability:38, code:"WM108"/* 公正之杖 */],
		[probability:38, code:"WP107"/* 战神之镰 */],
		[probability:38, code:"WS108"/* 巨弩 */],
		[probability:38, code:"WS219"/* 凝雾 */],
		[probability:38, code:"WT107"/* 天命标 */],
		[probability:38, code:"OM108"/* 阳炎 */],
		[probability:38, code:"OS104"/* 天仪石 */],
		[probability:38, code:"OR108"/* 翡翠戒指 */],
		[probability:38, code:"OA108"/* 靛青石链 */],
		[probability:38, code:"PL102"/* 中级恢复生命药水 */],
		[probability:38, code:"EC105"/* 公会卷轴 */],
		[probability:38, code:"OS106"/* 玄风石 */],
		[probability:38, code:"OS107"/* 水晶石 */],
		[probability:13, code:"DA111"/* 重装机铠 */],
		[probability:13, code:"DA211"/* 大法师袍 */],
		[probability:13, code:"WA108"/* 叼刚 清磐 */],
		[probability:13, code:"WC108"/* 矩记飘 府亥瘤 */],
		[probability:13, code:"WH109"/* 固萍绢扼捞飘 */],
		[probability:13, code:"WM109"/* 绊胶飘 */],
		[probability:13, code:"WP109"/* 单喉 荤捞靛 */],
		[probability:13, code:"WS109"/* 点金手弩 */],
		[probability:13, code:"WS210"/* 敲饭萍逞 家靛 */],
		[probability:13, code:"WT110"/* 飞云标 */],
		[probability:13, code:"OM109"/* 暗月 */],
		[probability:13, code:"OS105"/* 冰晶石 */],
		[probability:13, code:"DB108"/* 百战靴 */],
		[probability:13, code:"DS108"/* 赤龙焰盾 */],
		[probability:13, code:"OM108"/* 阳炎 */],
		[probability:13, code:"DG108"/* 大地护手 */],
		[probability:13, code:"OA208"/* 百炼臂环 */],
		[probability:13, code:"PM102"/* 中级恢复魔法药水 */],
		[probability:13, code:"EC102"/* 回城卷 */],
		[probability:13, code:"OS106"/* 玄风石 */],
		[probability:13, code:"OR109"/* 黑暗之戒 */],
		[probability:13, code:"OA109"/* 海蓝石链 */],
		[probability:5, code:"DA112"/* 战神宝铠 */],
		[probability:5, code:"DA212"/* 红莲战袍 */],
		[probability:5, code:"WA109"/* 破山斧 */],
		[probability:5, code:"WC109"/* 兽之斧刃 */],
		[probability:5, code:"WH110"/* 轩辕巨锤 */],
		[probability:5, code:"WM110"/* 圣者杖 */],
		[probability:5, code:"WP110"/* 白银之枪 */],
		[probability:5, code:"WS110"/* 固胶飘 */],
		[probability:5, code:"WS211"/* 斩马刀 */],
		[probability:5, code:"WT109"/* 酒唱能促 */],
		[probability:5, code:"OM110"/* 蓝色星辰 */],
		[probability:5, code:"DB109"/* 大地靴 */],
		[probability:5, code:"DS109"/* 圣盾 */],
		[probability:5, code:"OM109"/* 暗月 */],
		[probability:5, code:"DG109"/* 神力护手 */],
		[probability:5, code:"OA209"/* 飞翼臂环 */],
		[probability:5, code:"PM103"/* 高级恢复魔法药水 */],
		[probability:5, code:"EC102"/* 回城卷 */],
		[probability:5, code:"OR110"/* 伏魔戒指 */],
		[probability:5, code:"OA110"/* 镇魂铃 */]
	],
	drops_more:[
	]
]
