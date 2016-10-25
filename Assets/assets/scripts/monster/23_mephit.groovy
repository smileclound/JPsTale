monster = [
	ID:'23_mephit',
	Name:'青精灵',
	Clazz:0,// 1 BOSS
	Brood:0x92,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:23,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:180,
		// Attack Status
		Atk:[8, 16],
		AtkSpeed:7,
		Range:170,
		Rating:450,

		// Defence Status
		Flee:60,
		Absorb:7,
		Block:0,
		DamageStunPers:100,

		// Resistance
		Earth:20,
		Fire:0,
		Ice:100,// Water
		Lighting:50,// Wind
		Poison:50,
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
		SpAttackPercetage:100,
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
		ArrowPosi:[0, 0],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:0,
		Model:'char/monster/Monimp/Monimp-b.INI',
		Sound:0x00001020,
	],

	// Drops
	AllSeeItem:false,
	Exp:1490,
	Quantity:1,
	drops:[
		[probability:3600, code:"NULL"/* Drops nothing */],
		[probability:4000, code:"GG101", value:[20, 35]/* Gold */],
		[probability:333, code:"PL101"/* 低级复生命药水 */],
		[probability:333, code:"PL102"/* 中级恢复生命药水 */],
		[probability:333, code:"PS102"/* 中级恢复耐力药水 */],
		[probability:50, code:"DA104"/* 铠甲 */],
		[probability:50, code:"DA204"/* 常青袍 */],
		[probability:50, code:"WS104"/* 十字弩 */],
		[probability:50, code:"WS204"/* 短剑 */],
		[probability:50, code:"WA104"/* 劈地斧 */],
		[probability:50, code:"WC104"/* 蛇牙刺 */],
		[probability:50, code:"WH104"/* 巨灵锤 */],
		[probability:50, code:"WP104"/* 方天战戟 */],
		[probability:50, code:"WT104"/* 铁标 */],
		[probability:50, code:"DA104"/* 铠甲 */],
		[probability:50, code:"DB104"/* 精铜靴 */],
		[probability:50, code:"DS104"/* 轻盾 */],
		[probability:50, code:"DG104"/* 银贝护手 */],
		[probability:50, code:"OA204"/* 大力臂环 */],
		[probability:50, code:"OR106"/* 红宝石戒指 */],
		[probability:50, code:"OA106"/* 碧云石链 */],
		[probability:33, code:"DA105"/* 鱼鳞甲 */],
		[probability:33, code:"DA205"/* 战斗袍 */],
		[probability:33, code:"WS105"/* 战弓 */],
		[probability:33, code:"WS205"/* 长剑 */],
		[probability:33, code:"WA105"/* 双刃战斧 */],
		[probability:33, code:"WC105"/* 玄电爪 */],
		[probability:33, code:"WH105"/* 破天锤 */],
		[probability:33, code:"WP105"/* 鸠牙战镰 */],
		[probability:33, code:"WT105"/* 双刺标 */],
		[probability:33, code:"EC101"/* 回城卷 */],
		[probability:33, code:"OR107"/* 法师戒指 */],
		[probability:33, code:"OA107"/* 水晶项链 */],
		[probability:15, code:"DA106"/* 索子甲 */],
		[probability:15, code:"DA206"/* 精灵袍 */],
		[probability:15, code:"WS106"/* 长弓 */],
		[probability:15, code:"WS206"/* 阔剑 */],
		[probability:15, code:"WC106"/* 狼牙刃 */],
		[probability:15, code:"WH106"/* 浑元金锤 */],
		[probability:15, code:"WP106"/* 三叉戟 */],
		[probability:15, code:"WT106"/* 精灵标 */],
		[probability:15, code:"DS106"/* 百炼盾 */],
		[probability:15, code:"OM106"/* 水星 */],
		[probability:15, code:"DB106"/* 黄金靴 */],
		[probability:15, code:"DG106"/* 白金护手 */],
		[probability:15, code:"OA206"/* 精灵臂环 */]
	],
	drops_more:[
	]
]
