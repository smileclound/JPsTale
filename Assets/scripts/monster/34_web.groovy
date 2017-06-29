monster = [
	ID:'34_web',
	Name:'大头蜘蛛',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:34,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:400,
		// Attack Status
		Atk:[23, 33],
		AtkSpeed:5,
		Range:180,
		Rating:700,

		// Defence Status
		Flee:150,
		Absorb:12,
		Block:0,
		DamageStunPers:100,

		// Resistance
		Earth:10,
		Fire:-15,
		Ice:10,// Water
		Lighting:-15,// Wind
		Poison:100,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:6,
		Real_Sight:340,
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
		ClassCode:0,
		ArrowPosi:[-5, 150],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:-1,
		Model:'char/monster/web/web.ini',
		Sound:0x00001120,
	],

	// Drops
	AllSeeItem:false,
	Exp:3800,
	Quantity:1,
	drops:[
		[probability:4900, code:"NULL"/* Drops nothing */],
		[probability:3000, code:"GG101", value:[40, 70]/* Gold */],
		[probability:200, code:"PL102"/* 中级恢复生命药水 */],
		[probability:200, code:"PL103"/* 高级恢复生命药水 */],
		[probability:200, code:"PS103"/* 高级恢复耐力药水 */],
		[probability:200, code:"PL102"/* 中级恢复生命药水 */],
		[probability:200, code:"PM102"/* 中级恢复魔法药水 */],
		[probability:53, code:"DA106"/* 索子甲 */],
		[probability:53, code:"DA206"/* 精灵袍 */],
		[probability:53, code:"wa106"/* 凯旋 */],
		[probability:53, code:"WC106"/* 狼牙刃 */],
		[probability:53, code:"WH106"/* 浑元金锤 */],
		[probability:53, code:"WM106"/* 抗拒之杖 */],
		[probability:53, code:"WP106"/* 三叉戟 */],
		[probability:53, code:"WS107"/* 射日弓 */],
		[probability:53, code:"WS207"/* 长刀 */],
		[probability:53, code:"WT106"/* 精灵标 */],
		[probability:53, code:"PM102"/* 中级恢复魔法药水 */],
		[probability:53, code:"OR106"/* 红宝石戒指 */],
		[probability:53, code:"OA106"/* 碧云石链 */],
		[probability:23, code:"DA107"/* 全钢胸铠 */],
		[probability:23, code:"DA207"/* 紫电袍 */],
		[probability:23, code:"WT105"/* 双刺标 */],
		[probability:23, code:"WS106"/* 长弓 */],
		[probability:23, code:"WS206"/* 阔剑 */],
		[probability:23, code:"WM106"/* 抗拒之杖 */],
		[probability:23, code:"WP106"/* 三叉戟 */],
		[probability:23, code:"WH106"/* 浑元金锤 */],
		[probability:23, code:"PM103"/* 高级恢复魔法药水 */],
		[probability:23, code:"OR107"/* 法师戒指 */],
		[probability:23, code:"OA107"/* 水晶项链 */],
		[probability:23, code:"OS104"/* 天仪石 */],
		[probability:23, code:"GP111"/* 大头蜘蛛水晶 */],
		[probability:7, code:"DA108"/* 精制链铠 */],
		[probability:7, code:"DA208"/* 圣女袍 */],
		[probability:7, code:"WT106"/* 精灵标 */],
		[probability:7, code:"WS107"/* 射日弓 */],
		[probability:7, code:"WS207"/* 长刀 */],
		[probability:7, code:"WM107"/* 天魔杖 */],
		[probability:7, code:"WP107"/* 战神之镰 */],
		[probability:7, code:"WH107"/* 十字锤 */],
		[probability:7, code:"PM103"/* 高级恢复魔法药水 */],
		[probability:7, code:"EC102"/* 回城卷 */],
		[probability:7, code:"OR108"/* 翡翠戒指 */],
		[probability:7, code:"OS105"/* 冰晶石 */],
		[probability:7, code:"OA108"/* 靛青石链 */]
	],
	drops_more:[
	]
]
