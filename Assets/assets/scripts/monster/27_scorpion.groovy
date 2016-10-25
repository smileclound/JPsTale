monster = [
	ID:'27_scorpion',
	Name:'蝎兽',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:27,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:250,
		// Attack Status
		Atk:[15, 22],
		AtkSpeed:6,
		Range:55,
		Rating:450,

		// Defence Status
		Flee:100,
		Absorb:11,
		Block:0,
		DamageStunPers:100,

		// Resistance
		Earth:20,
		Fire:30,
		Ice:0,// Water
		Lighting:0,// Wind
		Poison:100,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:7,
		Real_Sight:350,
		Talks:[],

		// Move Behavier
		Move_Speed:5,
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
		ArrowPosi:[0, 16],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:-1,
		Model:'char/monster/scorpion/scorpion.ini',
		Sound:0x000010D0,
	],

	// Drops
	AllSeeItem:false,
	Exp:2260,
	Quantity:1,
	drops:[
		[probability:2850, code:"NULL"/* Drops nothing */],
		[probability:4600, code:"GG101", value:[25, 68]/* Gold */],
		[probability:250, code:"PL101"/* 低级复生命药水 */],
		[probability:250, code:"PL102"/* 中级恢复生命药水 */],
		[probability:250, code:"PS102"/* 中级恢复耐力药水 */],
		[probability:250, code:"PL102"/* 中级恢复生命药水 */],
		[probability:52, code:"WS104"/* 十字弩 */],
		[probability:52, code:"WS204"/* 短剑 */],
		[probability:52, code:"WA104"/* 劈地斧 */],
		[probability:52, code:"WC104"/* 蛇牙刺 */],
		[probability:52, code:"WH104"/* 巨灵锤 */],
		[probability:52, code:"WM104"/* 涤荡之杖 */],
		[probability:52, code:"WP104"/* 方天战戟 */],
		[probability:52, code:"WT104"/* 铁标 */],
		[probability:52, code:"DA104"/* 铠甲 */],
		[probability:52, code:"DA204"/* 常青袍 */],
		[probability:52, code:"DB104"/* 精铜靴 */],
		[probability:52, code:"DS104"/* 轻盾 */],
		[probability:52, code:"DG104"/* 银贝护手 */],
		[probability:52, code:"OA204"/* 大力臂环 */],
		[probability:52, code:"OM104"/* 龙骨念珠 */],
		[probability:52, code:"OR105"/* 蓝宝石戒指 */],
		[probability:52, code:"OA105"/* 梦之心链 */],
		[probability:23, code:"WS105"/* 战弓 */],
		[probability:23, code:"WS205"/* 长剑 */],
		[probability:23, code:"WA105"/* 双刃战斧 */],
		[probability:23, code:"WC105"/* 玄电爪 */],
		[probability:23, code:"WH105"/* 破天锤 */],
		[probability:23, code:"WM105"/* 旋风之杖 */],
		[probability:23, code:"WP105"/* 鸠牙战镰 */],
		[probability:23, code:"WT105"/* 双刺标 */],
		[probability:23, code:"DA105"/* 鱼鳞甲 */],
		[probability:23, code:"DA205"/* 战斗袍 */],
		[probability:23, code:"DB105"/* 百兽靴 */],
		[probability:23, code:"DS105"/* 罗塔盾 */],
		[probability:23, code:"DG105"/* 钢指护手 */],
		[probability:23, code:"OA205"/* 龙鳞臂环 */],
		[probability:23, code:"OM105"/* 龙额念珠 */],
		[probability:23, code:"WC105"/* 玄电爪 */],
		[probability:23, code:"EC101"/* 回城卷 */],
		[probability:23, code:"OR106"/* 红宝石戒指 */],
		[probability:23, code:"OA106"/* 碧云石链 */],
		[probability:10, code:"WS106"/* 长弓 */],
		[probability:10, code:"WS206"/* 阔剑 */],
		[probability:10, code:"wa106"/* 凯旋 */],
		[probability:10, code:"WC106"/* 狼牙刃 */],
		[probability:10, code:"WH106"/* 浑元金锤 */],
		[probability:10, code:"WM106"/* 抗拒之杖 */],
		[probability:10, code:"WP106"/* 三叉戟 */],
		[probability:10, code:"WT106"/* 精灵标 */],
		[probability:10, code:"DA106"/* 索子甲 */],
		[probability:10, code:"DA206"/* 精灵袍 */],
		[probability:10, code:"DB106"/* 黄金靴 */],
		[probability:10, code:"DS106"/* 百炼盾 */],
		[probability:10, code:"DG106"/* 白金护手 */],
		[probability:10, code:"OA206"/* 精灵臂环 */],
		[probability:10, code:"OM106"/* 水星 */],
		[probability:10, code:"WC106"/* 狼牙刃 */],
		[probability:10, code:"EC101"/* 回城卷 */],
		[probability:10, code:"OR106"/* 红宝石戒指 */],
		[probability:10, code:"OA106"/* 碧云石链 */],
		[probability:10, code:"OS104"/* 天仪石 */]
	],
	drops_more:[
	]
]
