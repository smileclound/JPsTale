monster = [
	ID:'58_evilmaple',
	Name:'黑暗枫树',
	Clazz:0,// 1 BOSS
	Brood:0x92,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:58,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:900,
		// Attack Status
		Atk:[42, 50],
		AtkSpeed:8,
		Range:60,
		Rating:700,

		// Defence Status
		Flee:370,
		Absorb:13,
		Block:0,
		DamageStunPers:100,

		// Resistance
		Earth:50,
		Fire:0,
		Ice:10,// Water
		Lighting:0,// Wind
		Poison:50,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:9,
		Real_Sight:360,
		Talks:[],

		// Move Behavier
		Move_Speed:1,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:15,
		SkillDamage:[46, 60],
		SkillDistance:80,
		SkillRange:55,
		SkillRating:15,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:0,
		PotionCount:0,
	],

	Looks:[
		ClassCode:0,
		ArrowPosi:[-10, 90],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:-1,
		Model:'char/monster/EvilMaple/EvilMaple.INI',
		Sound:0x00001440,
	],

	// Drops
	AllSeeItem:false,
	Exp:11500,
	Quantity:1,
	drops:[
		[probability:3400, code:"NULL"/* Drops nothing */],
		[probability:3300, code:"GG101", value:[110, 200]/* Gold */],
		[probability:325, code:"PL104"/* 顶级恢复生命药水 */],
		[probability:325, code:"PS104"/* 顶级恢复耐力药水 */],
		[probability:325, code:"PL104"/* 顶级恢复生命药水 */],
		[probability:325, code:"PM103"/* 高级恢复魔法药水 */],
		[probability:52, code:"DA108"/* 精制链铠 */],
		[probability:52, code:"DA208"/* 圣女袍 */],
		[probability:52, code:"wa106"/* 凯旋 */],
		[probability:52, code:"WC106"/* 狼牙刃 */],
		[probability:52, code:"WH106"/* 浑元金锤 */],
		[probability:52, code:"WM106"/* 抗拒之杖 */],
		[probability:52, code:"WP107"/* 战神之镰 */],
		[probability:52, code:"WS107"/* 射日弓 */],
		[probability:52, code:"WS208"/* 圣殿武士剑 */],
		[probability:52, code:"WT107"/* 天命标 */],
		[probability:52, code:"DA106"/* 索子甲 */],
		[probability:52, code:"DB106"/* 黄金靴 */],
		[probability:52, code:"DS106"/* 百炼盾 */],
		[probability:52, code:"OM106"/* 水星 */],
		[probability:52, code:"DG106"/* 白金护手 */],
		[probability:52, code:"OA206"/* 精灵臂环 */],
		[probability:52, code:"OR107"/* 法师戒指 */],
		[probability:52, code:"OA107"/* 水晶项链 */],
		[probability:52, code:"GP105"/* 魔剑士水晶 */],
		[probability:28, code:"DA109"/* 黄铜战铠 */],
		[probability:28, code:"DA209"/* 学徒披风 */],
		[probability:28, code:"WA107"/* 刺脊斧 */],
		[probability:28, code:"WC107"/* 平手刃 */],
		[probability:28, code:"WH107"/* 十字锤 */],
		[probability:28, code:"WM107"/* 天魔杖 */],
		[probability:28, code:"WP108"/* 血烟长矛 */],
		[probability:28, code:"WS108"/* 巨弩 */],
		[probability:28, code:"WS209"/* 镇妖剑 */],
		[probability:28, code:"WT108"/* 金标 */],
		[probability:28, code:"OR108"/* 翡翠戒指 */],
		[probability:28, code:"OA108"/* 靛青石链 */],
		[probability:28, code:"OM107"/* 火星 */],
		[probability:28, code:"GP105"/* 魔剑士水晶 */],
		[probability:28, code:"EC105"/* 公会卷轴 */],
		[probability:28, code:"OS105"/* 冰晶石 */],
		[probability:28, code:"GP105"/* 魔剑士水晶 */],
		[probability:28, code:"GP106"/* 火灵王水晶 */],
		[probability:28, code:"GP107"/* 独角兽王水晶 */],
		[probability:28, code:"GP108"/* 绿巨人水晶 */],
		[probability:28, code:"GP111"/* 大头蜘蛛水晶 */],
		[probability:15, code:"DA110"/* 百裂铠 */],
		[probability:15, code:"DA210"/* 信徒披风 */],
		[probability:15, code:"WA108"/* 叼刚 清磐 */],
		[probability:15, code:"WC108"/* 矩记飘 府亥瘤 */],
		[probability:15, code:"WH109"/* 固萍绢扼捞飘 */],
		[probability:15, code:"WM108"/* 公正之杖 */],
		[probability:15, code:"WP109"/* 单喉 荤捞靛 */],
		[probability:15, code:"WS109"/* 点金手弩 */],
		[probability:15, code:"WS210"/* 敲饭萍逞 家靛 */],
		[probability:15, code:"WT109"/* 酒唱能促 */],
		[probability:15, code:"DB108"/* 百战靴 */],
		[probability:15, code:"DS108"/* 赤龙焰盾 */],
		[probability:15, code:"OM108"/* 阳炎 */],
		[probability:15, code:"DG108"/* 大地护手 */],
		[probability:15, code:"OA208"/* 百炼臂环 */],
		[probability:15, code:"EC102"/* 回城卷 */],
		[probability:15, code:"OS105"/* 冰晶石 */],
		[probability:15, code:"OR109"/* 黑暗之戒 */],
		[probability:15, code:"OA109"/* 海蓝石链 */],
		[probability:15, code:"GP105"/* 魔剑士水晶 */],
		[probability:4, code:"DA111"/* 重装机铠 */],
		[probability:4, code:"DA211"/* 大法师袍 */],
		[probability:4, code:"WA109"/* 破山斧 */],
		[probability:4, code:"WC109"/* 兽之斧刃 */],
		[probability:4, code:"WH110"/* 轩辕巨锤 */],
		[probability:4, code:"WM109"/* 绊胶飘 */],
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
		[probability:4, code:"OS106"/* 玄风石 */],
		[probability:4, code:"OR110"/* 伏魔戒指 */],
		[probability:4, code:"OA110"/* 镇魂铃 */],
		[probability:4, code:"GP106"/* 火灵王水晶 */],
		[probability:4, code:"GP110"/* 守护圣徒水晶 */]
	],
	drops_more:[
	]
]
