monster = [
	ID:'36_buma',
	Name:'布玛熊',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:36,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:530,
		// Attack Status
		Atk:[26, 36],
		AtkSpeed:8,
		Range:75,
		Rating:600,

		// Defence Status
		Flee:240,
		Absorb:9,
		Block:0,
		DamageStunPers:100,

		// Resistance
		Earth:40,
		Fire:35,
		Ice:30,// Water
		Lighting:-15,// Wind
		Poison:50,
	],

	// AI
	AI:[
		Nature:0x80,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:7,
		Real_Sight:350,
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
		PotionPercent:15,
		PotionCount:1,
	],

	Looks:[
		ClassCode:0,
		ArrowPosi:[0, 44],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:-1,
		Model:'char/monster/buma/buma.ini',
		Sound:0x00001150,
	],

	// Drops
	AllSeeItem:false,
	Exp:4500,
	Quantity:1,
	drops:[
		[probability:4300, code:"NULL"/* Drops nothing */],
		[probability:3500, code:"GG101", value:[50, 100]/* Gold */],
		[probability:220, code:"PL102"/* 中级恢复生命药水 */],
		[probability:220, code:"PL103"/* 高级恢复生命药水 */],
		[probability:220, code:"PS103"/* 高级恢复耐力药水 */],
		[probability:220, code:"PL102"/* 中级恢复生命药水 */],
		[probability:220, code:"PM102"/* 中级恢复魔法药水 */],
		[probability:41, code:"DA106"/* 索子甲 */],
		[probability:41, code:"DA206"/* 精灵袍 */],
		[probability:41, code:"WC106"/* 狼牙刃 */],
		[probability:41, code:"WH106"/* 浑元金锤 */],
		[probability:41, code:"WM106"/* 抗拒之杖 */],
		[probability:41, code:"WP106"/* 三叉戟 */],
		[probability:41, code:"WS106"/* 长弓 */],
		[probability:41, code:"WS206"/* 阔剑 */],
		[probability:41, code:"WT105"/* 双刺标 */],
		[probability:41, code:"DB106"/* 黄金靴 */],
		[probability:41, code:"DS106"/* 百炼盾 */],
		[probability:41, code:"DG106"/* 白金护手 */],
		[probability:41, code:"DB105"/* 百兽靴 */],
		[probability:41, code:"OA206"/* 精灵臂环 */],
		[probability:41, code:"OR107"/* 法师戒指 */],
		[probability:41, code:"OA107"/* 水晶项链 */],
		[probability:41, code:"EC102"/* 回城卷 */],
		[probability:17, code:"DA107"/* 全钢胸铠 */],
		[probability:17, code:"DA207"/* 紫电袍 */],
		[probability:17, code:"WH107"/* 十字锤 */],
		[probability:17, code:"WM107"/* 天魔杖 */],
		[probability:17, code:"WP107"/* 战神之镰 */],
		[probability:17, code:"WS107"/* 射日弓 */],
		[probability:17, code:"WS207"/* 长刀 */],
		[probability:17, code:"WT106"/* 精灵标 */],
		[probability:17, code:"EC101"/* 回城卷 */],
		[probability:17, code:"DS106"/* 百炼盾 */],
		[probability:17, code:"DG106"/* 白金护手 */],
		[probability:17, code:"DB106"/* 黄金靴 */],
		[probability:17, code:"OA206"/* 精灵臂环 */],
		[probability:17, code:"OS105"/* 冰晶石 */],
		[probability:17, code:"OR108"/* 翡翠戒指 */],
		[probability:17, code:"OA108"/* 靛青石链 */],
		[probability:17, code:"EC104"/* 回城卷 */],
		[probability:7, code:"DA108"/* 精制链铠 */],
		[probability:7, code:"WA108"/* 叼刚 清磐 */],
		[probability:7, code:"WH108"/* 圣光锤 */],
		[probability:7, code:"WM108"/* 公正之杖 */],
		[probability:7, code:"WS208"/* 圣殿武士剑 */],
		[probability:7, code:"WT107"/* 天命标 */],
		[probability:7, code:"EC102"/* 回城卷 */],
		[probability:7, code:"DB107"/* 冰火靴 */],
		[probability:7, code:"DS107"/* 金刚盾 */],
		[probability:7, code:"DG107"/* 百裂护手 */],
		[probability:7, code:"OA207"/* 乌金臂环 */],
		[probability:7, code:"OS106"/* 玄风石 */],
		[probability:7, code:"OR109"/* 黑暗之戒 */],
		[probability:7, code:"OA109"/* 海蓝石链 */]
	],
	drops_more:[
	]
]
