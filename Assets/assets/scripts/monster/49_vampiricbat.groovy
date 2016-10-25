monster = [
	ID:'49_vampiricbat',
	Name:'独眼蝙蝠',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:49,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:600,
		// Attack Status
		Atk:[42, 48],
		AtkSpeed:6,
		Range:60,
		Rating:800,

		// Defence Status
		Flee:300,
		Absorb:12,
		Block:0,
		DamageStunPers:100,

		// Resistance
		Earth:20,
		Fire:0,
		Ice:30,// Water
		Lighting:0,// Wind
		Poison:50,
	],

	// AI
	AI:[
		Nature:0x80,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:7,
		Real_Sight:360,
		Talks:[],

		// Move Behavier
		Move_Speed:3,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:0,
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
		ClassCode:0,
		ArrowPosi:[-1, 50],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:1,
		Model:'char/monster/VampiricBat/VampiricBat.INI',
		Sound:0x000013A0,
	],

	// Drops
	AllSeeItem:false,
	Exp:5900,
	Quantity:1,
	drops:[
		[probability:3500, code:"NULL"/* Drops nothing */],
		[probability:3300, code:"GG101", value:[70, 100]/* Gold */],
		[probability:500, code:"PL102"/* 中级恢复生命药水 */],
		[probability:500, code:"PL103"/* 高级恢复生命药水 */],
		[probability:500, code:"PS103"/* 高级恢复耐力药水 */],
		[probability:500, code:"PL103"/* 高级恢复生命药水 */],
		[probability:53, code:"DA107"/* 全钢胸铠 */],
		[probability:53, code:"DA207"/* 紫电袍 */],
		[probability:53, code:"wa106"/* 凯旋 */],
		[probability:53, code:"WC106"/* 狼牙刃 */],
		[probability:53, code:"WH107"/* 十字锤 */],
		[probability:53, code:"WM107"/* 天魔杖 */],
		[probability:53, code:"WP107"/* 战神之镰 */],
		[probability:53, code:"WS106"/* 长弓 */],
		[probability:53, code:"WS107"/* 射日弓 */],
		[probability:53, code:"WS207"/* 长刀 */],
		[probability:53, code:"WS208"/* 圣殿武士剑 */],
		[probability:53, code:"WT106"/* 精灵标 */],
		[probability:53, code:"OR108"/* 翡翠戒指 */],
		[probability:53, code:"OA108"/* 靛青石链 */],
		[probability:53, code:"EC102"/* 回城卷 */],
		[probability:15, code:"DA108"/* 精制链铠 */],
		[probability:15, code:"DA208"/* 圣女袍 */],
		[probability:15, code:"WA107"/* 刺脊斧 */],
		[probability:15, code:"WC107"/* 平手刃 */],
		[probability:15, code:"WH108"/* 圣光锤 */],
		[probability:15, code:"WM108"/* 公正之杖 */],
		[probability:15, code:"WP108"/* 血烟长矛 */],
		[probability:15, code:"WS107"/* 射日弓 */],
		[probability:15, code:"WS108"/* 巨弩 */],
		[probability:15, code:"WS209"/* 镇妖剑 */],
		[probability:15, code:"WT107"/* 天命标 */],
		[probability:15, code:"DB106"/* 黄金靴 */],
		[probability:15, code:"DS106"/* 百炼盾 */],
		[probability:15, code:"DG106"/* 白金护手 */],
		[probability:15, code:"OA206"/* 精灵臂环 */],
		[probability:15, code:"OR109"/* 黑暗之戒 */],
		[probability:15, code:"OA109"/* 海蓝石链 */],
		[probability:15, code:"EC102"/* 回城卷 */],
		[probability:15, code:"OS105"/* 冰晶石 */],
		[probability:5, code:"DA109"/* 黄铜战铠 */],
		[probability:5, code:"DA209"/* 学徒披风 */],
		[probability:5, code:"WA108"/* 叼刚 清磐 */],
		[probability:5, code:"WC108"/* 矩记飘 府亥瘤 */],
		[probability:5, code:"WH109"/* 固萍绢扼捞飘 */],
		[probability:5, code:"WM109"/* 绊胶飘 */],
		[probability:5, code:"WP109"/* 单喉 荤捞靛 */],
		[probability:5, code:"WS110"/* 固胶飘 */],
		[probability:5, code:"WS210"/* 敲饭萍逞 家靛 */],
		[probability:5, code:"WT109"/* 酒唱能促 */],
		[probability:5, code:"EC102"/* 回城卷 */],
		[probability:5, code:"DB107"/* 冰火靴 */],
		[probability:5, code:"DS107"/* 金刚盾 */],
		[probability:5, code:"DG107"/* 百裂护手 */],
		[probability:5, code:"OA207"/* 乌金臂环 */],
		[probability:5, code:"OS106"/* 玄风石 */],
		[probability:5, code:"OS105"/* 冰晶石 */]
	],
	drops_more:[
	]
]
