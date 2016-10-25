monster = [
	ID:'33_cyclops',
	Name:'独眼魔人',
	Clazz:0,// 1 BOSS
	Brood:0x92,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:33,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:380,
		// Attack Status
		Atk:[20, 29],
		AtkSpeed:7,
		Range:65,
		Rating:560,

		// Defence Status
		Flee:180,
		Absorb:8,
		Block:17,
		DamageStunPers:100,

		// Resistance
		Earth:20,
		Fire:20,
		Ice:15,// Water
		Lighting:-15,// Wind
		Poison:25,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:6,
		Real_Sight:370,
		Talks:[],

		// Move Behavier
		Move_Speed:3,
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
		ClassCode:47,
		ArrowPosi:[0, 34],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:3,
		Model:'char/monster/Moncyclops/MonCyclops-a.INI',
		Sound:0x00001000,
	],

	// Drops
	AllSeeItem:false,
	Exp:3500,
	Quantity:1,
	drops:[
		[probability:4700, code:"NULL"/* Drops nothing */],
		[probability:2250, code:"GG101", value:[30, 82]/* Gold */],
		[probability:380, code:"PL102"/* 中级恢复生命药水 */],
		[probability:380, code:"PS102"/* 中级恢复耐力药水 */],
		[probability:380, code:"PL103"/* 高级恢复生命药水 */],
		[probability:380, code:"PS103"/* 高级恢复耐力药水 */],
		[probability:380, code:"PL102"/* 中级恢复生命药水 */],
		[probability:38, code:"WS104"/* 十字弩 */],
		[probability:38, code:"WS204"/* 短剑 */],
		[probability:38, code:"WA104"/* 劈地斧 */],
		[probability:38, code:"WC104"/* 蛇牙刺 */],
		[probability:38, code:"WH104"/* 巨灵锤 */],
		[probability:38, code:"WM104"/* 涤荡之杖 */],
		[probability:38, code:"WP104"/* 方天战戟 */],
		[probability:38, code:"WT104"/* 铁标 */],
		[probability:38, code:"OM104"/* 龙骨念珠 */],
		[probability:38, code:"PL103"/* 高级恢复生命药水 */],
		[probability:38, code:"PS103"/* 高级恢复耐力药水 */],
		[probability:38, code:"DA104"/* 铠甲 */],
		[probability:38, code:"DB104"/* 精铜靴 */],
		[probability:38, code:"DS104"/* 轻盾 */],
		[probability:38, code:"DG104"/* 银贝护手 */],
		[probability:38, code:"OA105"/* 梦之心链 */],
		[probability:38, code:"OA204"/* 大力臂环 */],
		[probability:38, code:"OR105"/* 蓝宝石戒指 */],
		[probability:21, code:"WS105"/* 战弓 */],
		[probability:21, code:"WS205"/* 长剑 */],
		[probability:21, code:"WA105"/* 双刃战斧 */],
		[probability:21, code:"WC105"/* 玄电爪 */],
		[probability:21, code:"WH105"/* 破天锤 */],
		[probability:21, code:"WM105"/* 旋风之杖 */],
		[probability:21, code:"WP105"/* 鸠牙战镰 */],
		[probability:21, code:"WT105"/* 双刺标 */],
		[probability:21, code:"OM105"/* 龙额念珠 */],
		[probability:21, code:"PM103"/* 高级恢复魔法药水 */],
		[probability:21, code:"OR106"/* 红宝石戒指 */],
		[probability:21, code:"OA106"/* 碧云石链 */],
		[probability:21, code:"EC101"/* 回城卷 */],
		[probability:21, code:"OS103"/* 海精石 */],
		[probability:11, code:"WS106"/* 长弓 */],
		[probability:11, code:"WS206"/* 阔剑 */],
		[probability:11, code:"wa106"/* 凯旋 */],
		[probability:11, code:"WC106"/* 狼牙刃 */],
		[probability:11, code:"WH106"/* 浑元金锤 */],
		[probability:11, code:"WM106"/* 抗拒之杖 */],
		[probability:11, code:"WP106"/* 三叉戟 */],
		[probability:11, code:"WT106"/* 精灵标 */],
		[probability:11, code:"OM106"/* 水星 */],
		[probability:11, code:"EC101"/* 回城卷 */],
		[probability:11, code:"OR107"/* 法师戒指 */],
		[probability:11, code:"OA107"/* 水晶项链 */],
		[probability:11, code:"OS104"/* 天仪石 */]
	],
	drops_more:[
	]
]
