monster = [
	ID:'50_guardhopy',
	Name:'独角守护者',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:50,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:800,
		// Attack Status
		Atk:[34, 46],
		AtkSpeed:8,
		Range:55,
		Rating:800,

		// Defence Status
		Flee:320,
		Absorb:0,
		Block:0,
		DamageStunPers:100,

		// Resistance
		Earth:20,
		Fire:15,
		Ice:25,// Water
		Lighting:40,// Wind
		Poison:100,
	],

	// AI
	AI:[
		Nature:0x80,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:9,
		Real_Sight:400,
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
		PotionPercent:0,
		PotionCount:0,
	],

	Looks:[
		ClassCode:0,
		ArrowPosi:[8, 25],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:1,
		Model:'char/monster/hopyking/hopyking.ini',
		Sound:0x00001080,
	],

	// Drops
	AllSeeItem:false,
	Exp:6600,
	Quantity:1,
	drops:[
		[probability:5000, code:"NULL"/* Drops nothing */],
		[probability:3000, code:"GG101", value:[100, 200]/* Gold */],
		[probability:200, code:"PL102"/* 中级恢复生命药水 */],
		[probability:200, code:"PS102"/* 中级恢复耐力药水 */],
		[probability:200, code:"PL103"/* 高级恢复生命药水 */],
		[probability:200, code:"PS103"/* 高级恢复耐力药水 */],
		[probability:200, code:"PL102"/* 中级恢复生命药水 */],
		[probability:37, code:"DA106"/* 索子甲 */],
		[probability:37, code:"WS104"/* 十字弩 */],
		[probability:37, code:"WS106"/* 长弓 */],
		[probability:37, code:"WA104"/* 劈地斧 */],
		[probability:37, code:"WC104"/* 蛇牙刺 */],
		[probability:37, code:"WH104"/* 巨灵锤 */],
		[probability:37, code:"WP104"/* 方天战戟 */],
		[probability:37, code:"PL103"/* 高级恢复生命药水 */],
		[probability:37, code:"PS103"/* 高级恢复耐力药水 */],
		[probability:37, code:"DA104"/* 铠甲 */],
		[probability:37, code:"DB104"/* 精铜靴 */],
		[probability:37, code:"DS104"/* 轻盾 */],
		[probability:37, code:"DG104"/* 银贝护手 */],
		[probability:37, code:"OA204"/* 大力臂环 */],
		[probability:37, code:"OR104"/* 玉戒指 */],
		[probability:37, code:"OA104"/* 金项链 */],
		[probability:17, code:"DA107"/* 全钢胸铠 */],
		[probability:17, code:"WS105"/* 战弓 */],
		[probability:17, code:"WS107"/* 射日弓 */],
		[probability:17, code:"WA105"/* 双刃战斧 */],
		[probability:17, code:"WC105"/* 玄电爪 */],
		[probability:17, code:"WH106"/* 浑元金锤 */],
		[probability:17, code:"WP106"/* 三叉戟 */],
		[probability:17, code:"PL104"/* 顶级恢复生命药水 */],
		[probability:17, code:"PS104"/* 顶级恢复耐力药水 */],
		[probability:17, code:"DA105"/* 鱼鳞甲 */],
		[probability:17, code:"DB105"/* 百兽靴 */],
		[probability:17, code:"DS105"/* 罗塔盾 */],
		[probability:17, code:"DG105"/* 钢指护手 */],
		[probability:17, code:"OA205"/* 龙鳞臂环 */],
		[probability:17, code:"OS103"/* 海精石 */],
		[probability:17, code:"OR105"/* 蓝宝石戒指 */],
		[probability:17, code:"OA105"/* 梦之心链 */],
		[probability:25, code:"DA108"/* 精制链铠 */],
		[probability:25, code:"EC102"/* 回城卷 */],
		[probability:25, code:"OR106"/* 红宝石戒指 */],
		[probability:25, code:"OA106"/* 碧云石链 */]
	],
	drops_more:[
	]
]
