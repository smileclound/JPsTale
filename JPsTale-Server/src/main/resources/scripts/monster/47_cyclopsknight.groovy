monster = [
	ID:'47_cyclopsknight',
	Name:'独眼魔神',
	Clazz:0,// 1 BOSS
	Brood:0x92,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:47,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:620,
		// Attack Status
		Atk:[38, 46],
		AtkSpeed:7,
		Range:70,
		Rating:660,

		// Defence Status
		Flee:300,
		Absorb:10,
		Block:26,
		DamageStunPers:100,

		// Resistance
		Earth:20,
		Fire:25,
		Ice:25,// Water
		Lighting:15,// Wind
		Poison:40,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:7,
		Real_Sight:420,
		Talks:[],

		// Move Behavier
		Move_Speed:4,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:15,
		SkillDamage:[0, 0],
		SkillDistance:0,
		SkillRange:0,
		SkillRating:0,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:15,
		PotionCount:1,
	],

	Looks:[
		ClassCode:0,
		ArrowPosi:[0, 34],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:3,
		Model:'char/monster/Moncyclops/MonCyclops-d.INI',
		Sound:0x00001000,
	],

	// Drops
	AllSeeItem:false,
	Exp:5400,
	Quantity:1,
	drops:[
		[probability:4000, code:"NULL"/* Drops nothing */],
		[probability:3900, code:"GG101", value:[100, 155]/* Gold */],
		[probability:200, code:"PL102"/* 中级恢复生命药水 */],
		[probability:200, code:"PM102"/* 中级恢复魔法药水 */],
		[probability:200, code:"PL103"/* 高级恢复生命药水 */],
		[probability:200, code:"PS103"/* 高级恢复耐力药水 */],
		[probability:200, code:"PM103"/* 高级恢复魔法药水 */],
		[probability:33, code:"DA107"/* 全钢胸铠 */],
		[probability:33, code:"DA207"/* 紫电袍 */],
		[probability:33, code:"wa106"/* 凯旋 */],
		[probability:33, code:"WC106"/* 狼牙刃 */],
		[probability:33, code:"WH107"/* 十字锤 */],
		[probability:33, code:"WM107"/* 天魔杖 */],
		[probability:33, code:"WP107"/* 战神之镰 */],
		[probability:33, code:"WS106"/* 长弓 */],
		[probability:33, code:"WS107"/* 射日弓 */],
		[probability:33, code:"WS207"/* 长刀 */],
		[probability:33, code:"WS208"/* 圣殿武士剑 */],
		[probability:33, code:"WT108"/* 金标 */],
		[probability:33, code:"DB106"/* 黄金靴 */],
		[probability:33, code:"DS106"/* 百炼盾 */],
		[probability:33, code:"DG106"/* 白金护手 */],
		[probability:33, code:"OA206"/* 精灵臂环 */],
		[probability:33, code:"OR107"/* 法师戒指 */],
		[probability:33, code:"OA107"/* 水晶项链 */],
		[probability:33, code:"DS107"/* 金刚盾 */],
		[probability:33, code:"OM107"/* 火星 */],
		[probability:33, code:"OS104"/* 天仪石 */],
		[probability:13, code:"DA108"/* 精制链铠 */],
		[probability:13, code:"DA208"/* 圣女袍 */],
		[probability:13, code:"WA107"/* 刺脊斧 */],
		[probability:13, code:"WC107"/* 平手刃 */],
		[probability:13, code:"WH108"/* 圣光锤 */],
		[probability:13, code:"WM108"/* 公正之杖 */],
		[probability:13, code:"WP108"/* 血烟长矛 */],
		[probability:13, code:"WS107"/* 射日弓 */],
		[probability:13, code:"WS108"/* 巨弩 */],
		[probability:13, code:"WS208"/* 圣殿武士剑 */],
		[probability:13, code:"WT109"/* 酒唱能促 */],
		[probability:13, code:"OM107"/* 火星 */],
		[probability:13, code:"OS105"/* 冰晶石 */],
		[probability:13, code:"DB107"/* 冰火靴 */],
		[probability:13, code:"DS107"/* 金刚盾 */],
		[probability:13, code:"DG107"/* 百裂护手 */],
		[probability:13, code:"OA207"/* 乌金臂环 */],
		[probability:13, code:"OR108"/* 翡翠戒指 */],
		[probability:13, code:"OA108"/* 靛青石链 */],
		[probability:13, code:"DS108"/* 赤龙焰盾 */],
		[probability:13, code:"OM108"/* 阳炎 */],
		[probability:13, code:"OS105"/* 冰晶石 */],
		[probability:6, code:"DA109"/* 黄铜战铠 */],
		[probability:6, code:"DA209"/* 学徒披风 */],
		[probability:6, code:"WA108"/* 叼刚 清磐 */],
		[probability:6, code:"WC108"/* 矩记飘 府亥瘤 */],
		[probability:6, code:"WH109"/* 固萍绢扼捞飘 */],
		[probability:6, code:"WM109"/* 绊胶飘 */],
		[probability:6, code:"WP109"/* 单喉 荤捞靛 */],
		[probability:6, code:"WS108"/* 巨弩 */],
		[probability:6, code:"WS109"/* 点金手弩 */],
		[probability:6, code:"WT110"/* 飞云标 */],
		[probability:6, code:"DB108"/* 百战靴 */],
		[probability:6, code:"DS108"/* 赤龙焰盾 */],
		[probability:6, code:"DG108"/* 大地护手 */],
		[probability:6, code:"OA208"/* 百炼臂环 */],
		[probability:6, code:"OS105"/* 冰晶石 */],
		[probability:6, code:"GP109"/* 神秘水晶 */]
	],
	drops_more:[
	]
]
