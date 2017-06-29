monster = [
	ID:'59_headcutter',
	Name:'魔剑士',
	Clazz:0,// 1 BOSS
	Brood:0x92,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:59,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:870,
		// Attack Status
		Atk:[36, 48],
		AtkSpeed:7,
		Range:100,
		Rating:700,

		// Defence Status
		Flee:450,
		Absorb:12,
		Block:9,
		DamageStunPers:100,

		// Resistance
		Earth:30,
		Fire:10,
		Ice:35,// Water
		Lighting:100,// Wind
		Poison:10,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:7,
		Real_Sight:400,
		Talks:[],

		// Move Behavier
		Move_Speed:3,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:20,
		SkillDamage:[50, 60],
		SkillDistance:80,
		SkillRange:50,
		SkillRating:18,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:15,
		PotionCount:1,
	],

	Looks:[
		ClassCode:3,
		ArrowPosi:[-2, 30],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:-1,
		Model:'char/monster/headcut/headcut.ini',
		Sound:0x00001100,
	],

	// Drops
	AllSeeItem:false,
	Exp:12000,
	Quantity:1,
	drops:[
		[probability:4000, code:"NULL"/* Drops nothing */],
		[probability:3000, code:"GG101", value:[110, 200]/* Gold */],
		[probability:400, code:"PL104"/* 顶级恢复生命药水 */],
		[probability:400, code:"PS104"/* 顶级恢复耐力药水 */],
		[probability:400, code:"PL104"/* 顶级恢复生命药水 */],
		[probability:39, code:"DA108"/* 精制链铠 */],
		[probability:39, code:"DA208"/* 圣女袍 */],
		[probability:39, code:"wa106"/* 凯旋 */],
		[probability:39, code:"WC106"/* 狼牙刃 */],
		[probability:39, code:"WH106"/* 浑元金锤 */],
		[probability:39, code:"WM107"/* 天魔杖 */],
		[probability:39, code:"WP107"/* 战神之镰 */],
		[probability:39, code:"WS107"/* 射日弓 */],
		[probability:39, code:"WS208"/* 圣殿武士剑 */],
		[probability:39, code:"WT107"/* 天命标 */],
		[probability:39, code:"PL104"/* 顶级恢复生命药水 */],
		[probability:39, code:"PS104"/* 顶级恢复耐力药水 */],
		[probability:39, code:"PM104"/* 顶级恢复魔法药水 */],
		[probability:39, code:"DA106"/* 索子甲 */],
		[probability:39, code:"DB106"/* 黄金靴 */],
		[probability:39, code:"DS106"/* 百炼盾 */],
		[probability:39, code:"OM106"/* 水星 */],
		[probability:39, code:"DG106"/* 白金护手 */],
		[probability:39, code:"OA206"/* 精灵臂环 */],
		[probability:39, code:"OS104"/* 天仪石 */],
		[probability:39, code:"OR107"/* 法师戒指 */],
		[probability:39, code:"OA107"/* 水晶项链 */],
		[probability:39, code:"GP105"/* 魔剑士水晶 */],
		[probability:35, code:"DA109"/* 黄铜战铠 */],
		[probability:35, code:"DA209"/* 学徒披风 */],
		[probability:35, code:"WA107"/* 刺脊斧 */],
		[probability:35, code:"WC107"/* 平手刃 */],
		[probability:35, code:"WH107"/* 十字锤 */],
		[probability:35, code:"WM108"/* 公正之杖 */],
		[probability:35, code:"WP108"/* 血烟长矛 */],
		[probability:35, code:"WS108"/* 巨弩 */],
		[probability:35, code:"WS209"/* 镇妖剑 */],
		[probability:35, code:"WT108"/* 金标 */],
		[probability:35, code:"OS104"/* 天仪石 */],
		[probability:35, code:"OR108"/* 翡翠戒指 */],
		[probability:35, code:"OA108"/* 靛青石链 */],
		[probability:35, code:"PL102"/* 中级恢复生命药水 */],
		[probability:35, code:"OM107"/* 火星 */],
		[probability:35, code:"GP105"/* 魔剑士水晶 */],
		[probability:35, code:"EC105"/* 公会卷轴 */],
		[probability:9, code:"DA110"/* 百裂铠 */],
		[probability:9, code:"DA210"/* 信徒披风 */],
		[probability:9, code:"WA108"/* 叼刚 清磐 */],
		[probability:9, code:"WC108"/* 矩记飘 府亥瘤 */],
		[probability:9, code:"WH109"/* 固萍绢扼捞飘 */],
		[probability:9, code:"WM109"/* 绊胶飘 */],
		[probability:9, code:"WP109"/* 单喉 荤捞靛 */],
		[probability:9, code:"WS109"/* 点金手弩 */],
		[probability:9, code:"WS210"/* 敲饭萍逞 家靛 */],
		[probability:9, code:"WT109"/* 酒唱能促 */],
		[probability:9, code:"DB108"/* 百战靴 */],
		[probability:9, code:"DS108"/* 赤龙焰盾 */],
		[probability:9, code:"OM108"/* 阳炎 */],
		[probability:9, code:"DG108"/* 大地护手 */],
		[probability:9, code:"OA208"/* 百炼臂环 */],
		[probability:9, code:"EC102"/* 回城卷 */],
		[probability:9, code:"OS105"/* 冰晶石 */],
		[probability:9, code:"OR109"/* 黑暗之戒 */],
		[probability:9, code:"OA109"/* 海蓝石链 */],
		[probability:9, code:"PL103"/* 高级恢复生命药水 */],
		[probability:9, code:"GP106"/* 火灵王水晶 */],
		[probability:4, code:"DA111"/* 重装机铠 */],
		[probability:4, code:"DA211"/* 大法师袍 */],
		[probability:4, code:"WA109"/* 破山斧 */],
		[probability:4, code:"WC109"/* 兽之斧刃 */],
		[probability:4, code:"WH110"/* 轩辕巨锤 */],
		[probability:4, code:"WM110"/* 圣者杖 */],
		[probability:4, code:"WP110"/* 白银之枪 */],
		[probability:4, code:"WS110"/* 固胶飘 */],
		[probability:4, code:"WS211"/* 斩马刀 */],
		[probability:4, code:"WT110"/* 飞云标 */],
		[probability:4, code:"DB109"/* 大地靴 */],
		[probability:4, code:"DS109"/* 圣盾 */],
		[probability:4, code:"OM109"/* 暗月 */],
		[probability:4, code:"DG109"/* 神力护手 */],
		[probability:4, code:"OA209"/* 飞翼臂环 */],
		[probability:4, code:"EC102"/* 回城卷 */],
		[probability:4, code:"OS105"/* 冰晶石 */],
		[probability:4, code:"OS106"/* 玄风石 */],
		[probability:4, code:"OS107"/* 水晶石 */],
		[probability:4, code:"OR110"/* 伏魔戒指 */],
		[probability:4, code:"OA110"/* 镇魂铃 */],
		[probability:4, code:"GP110"/* 守护圣徒水晶 */]
	],
	drops_more:[
	]
]
