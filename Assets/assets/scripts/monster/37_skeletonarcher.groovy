monster = [
	ID:'37_skeletonarcher',
	Name:'骷髅射手',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:37,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:350,
		// Attack Status
		Atk:[20, 28],
		AtkSpeed:8,
		Range:200,
		Rating:550,

		// Defence Status
		Flee:160,
		Absorb:7,
		Block:0,
		DamageStunPers:100,

		// Resistance
		Earth:20,
		Fire:0,
		Ice:20,// Water
		Lighting:-15,// Wind
		Poison:100,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:7,
		Real_Sight:380,
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
		PotionPercent:0,
		PotionCount:0,
	],

	Looks:[
		ClassCode:55,
		ArrowPosi:[-3, 10],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:1,
		Model:'char/monster/MONSArcher/MONSArcher.ini',
		Sound:0x000011C0,
	],

	// Drops
	AllSeeItem:false,
	Exp:4400,
	Quantity:1,
	drops:[
		[probability:4100, code:"NULL"/* Drops nothing */],
		[probability:3500, code:"GG101", value:[40, 95]/* Gold */],
		[probability:233, code:"PL102"/* 中级恢复生命药水 */],
		[probability:233, code:"PS102"/* 中级恢复耐力药水 */],
		[probability:233, code:"PL103"/* 高级恢复生命药水 */],
		[probability:233, code:"PS103"/* 高级恢复耐力药水 */],
		[probability:233, code:"PL102"/* 中级恢复生命药水 */],
		[probability:233, code:"PM102"/* 中级恢复魔法药水 */],
		[probability:46, code:"DA106"/* 索子甲 */],
		[probability:46, code:"DA206"/* 精灵袍 */],
		[probability:46, code:"PM102"/* 中级恢复魔法药水 */],
		[probability:46, code:"OR106"/* 红宝石戒指 */],
		[probability:46, code:"OA106"/* 碧云石链 */],
		[probability:46, code:"EC101"/* 回城卷 */],
		[probability:46, code:"wa106"/* 凯旋 */],
		[probability:46, code:"WC106"/* 狼牙刃 */],
		[probability:46, code:"WH106"/* 浑元金锤 */],
		[probability:46, code:"WM106"/* 抗拒之杖 */],
		[probability:46, code:"WP106"/* 三叉戟 */],
		[probability:46, code:"WS107"/* 射日弓 */],
		[probability:46, code:"WS207"/* 长刀 */],
		[probability:37, code:"DA107"/* 全钢胸铠 */],
		[probability:37, code:"DA207"/* 紫电袍 */],
		[probability:37, code:"OM106"/* 水星 */],
		[probability:37, code:"DS106"/* 百炼盾 */],
		[probability:37, code:"PM103"/* 高级恢复魔法药水 */],
		[probability:37, code:"OR107"/* 法师戒指 */],
		[probability:37, code:"OA107"/* 水晶项链 */],
		[probability:37, code:"OS104"/* 天仪石 */],
		[probability:6, code:"DA108"/* 精制链铠 */],
		[probability:6, code:"DA208"/* 圣女袍 */],
		[probability:6, code:"OM107"/* 火星 */],
		[probability:6, code:"WC107"/* 平手刃 */],
		[probability:6, code:"WH107"/* 十字锤 */],
		[probability:6, code:"WM107"/* 天魔杖 */],
		[probability:6, code:"WP107"/* 战神之镰 */],
		[probability:6, code:"WS108"/* 巨弩 */],
		[probability:6, code:"WS208"/* 圣殿武士剑 */],
		[probability:6, code:"WT108"/* 金标 */],
		[probability:6, code:"EC101"/* 回城卷 */],
		[probability:6, code:"EC104"/* 回城卷 */],
		[probability:6, code:"PM104"/* 顶级恢复魔法药水 */],
		[probability:6, code:"OR108"/* 翡翠戒指 */],
		[probability:6, code:"OA108"/* 靛青石链 */],
		[probability:6, code:"OS105"/* 冰晶石 */]
	],
	drops_more:[
	]
]
