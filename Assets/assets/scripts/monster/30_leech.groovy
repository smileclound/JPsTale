monster = [
	ID:'30_leech',
	Name:'入地龙',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:30,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:310,
		// Attack Status
		Atk:[17, 25],
		AtkSpeed:6,
		Range:45,
		Rating:450,

		// Defence Status
		Flee:160,
		Absorb:7,
		Block:0,
		DamageStunPers:100,

		// Resistance
		Earth:40,
		Fire:10,
		Ice:10,// Water
		Lighting:20,// Wind
		Poison:50,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:7,
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
		ArrowPosi:[0, 10],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:-1,
		Model:'char/monster/leech/leech.INI',
		Sound:0x000010A0,
	],

	// Drops
	AllSeeItem:false,
	Exp:3000,
	Quantity:1,
	drops:[
		[probability:3400, code:"NULL"/* Drops nothing */],
		[probability:3000, code:"GG101", value:[25, 60]/* Gold */],
		[probability:566, code:"PL102"/* 中级恢复生命药水 */],
		[probability:566, code:"PL103"/* 高级恢复生命药水 */],
		[probability:566, code:"PS103"/* 高级恢复耐力药水 */],
		[probability:64, code:"WA104"/* 劈地斧 */],
		[probability:64, code:"WC104"/* 蛇牙刺 */],
		[probability:64, code:"WH105"/* 破天锤 */],
		[probability:64, code:"WM105"/* 旋风之杖 */],
		[probability:64, code:"WP105"/* 鸠牙战镰 */],
		[probability:64, code:"WS104"/* 十字弩 */],
		[probability:64, code:"WS204"/* 短剑 */],
		[probability:64, code:"WT104"/* 铁标 */],
		[probability:64, code:"DA104"/* 铠甲 */],
		[probability:64, code:"DA204"/* 常青袍 */],
		[probability:64, code:"DB104"/* 精铜靴 */],
		[probability:64, code:"DS104"/* 轻盾 */],
		[probability:64, code:"DG104"/* 银贝护手 */],
		[probability:64, code:"OM104"/* 龙骨念珠 */],
		[probability:64, code:"OA204"/* 大力臂环 */],
		[probability:64, code:"OR105"/* 蓝宝石戒指 */],
		[probability:64, code:"OA105"/* 梦之心链 */],
		[probability:31, code:"WA105"/* 双刃战斧 */],
		[probability:31, code:"WC105"/* 玄电爪 */],
		[probability:31, code:"WH106"/* 浑元金锤 */],
		[probability:31, code:"WM106"/* 抗拒之杖 */],
		[probability:31, code:"WP106"/* 三叉戟 */],
		[probability:31, code:"WS105"/* 战弓 */],
		[probability:31, code:"WS205"/* 长剑 */],
		[probability:31, code:"WT105"/* 双刺标 */],
		[probability:31, code:"DA105"/* 鱼鳞甲 */],
		[probability:31, code:"DA205"/* 战斗袍 */],
		[probability:31, code:"DB105"/* 百兽靴 */],
		[probability:31, code:"DS105"/* 罗塔盾 */],
		[probability:31, code:"DG105"/* 钢指护手 */],
		[probability:31, code:"OM105"/* 龙额念珠 */],
		[probability:31, code:"OA205"/* 龙鳞臂环 */],
		[probability:31, code:"OR106"/* 红宝石戒指 */],
		[probability:31, code:"OA106"/* 碧云石链 */],
		[probability:31, code:"OR106"/* 红宝石戒指 */],
		[probability:31, code:"OA106"/* 碧云石链 */],
		[probability:11, code:"wa106"/* 凯旋 */],
		[probability:11, code:"WC106"/* 狼牙刃 */],
		[probability:11, code:"WH107"/* 十字锤 */],
		[probability:11, code:"WM107"/* 天魔杖 */],
		[probability:11, code:"WP107"/* 战神之镰 */],
		[probability:11, code:"WS106"/* 长弓 */],
		[probability:11, code:"WS206"/* 阔剑 */],
		[probability:11, code:"WT106"/* 精灵标 */],
		[probability:11, code:"DA106"/* 索子甲 */],
		[probability:11, code:"DA206"/* 精灵袍 */],
		[probability:11, code:"DB106"/* 黄金靴 */],
		[probability:11, code:"DS106"/* 百炼盾 */],
		[probability:11, code:"DG106"/* 白金护手 */],
		[probability:11, code:"OM106"/* 水星 */],
		[probability:11, code:"OA206"/* 精灵臂环 */],
		[probability:11, code:"OR107"/* 法师戒指 */],
		[probability:11, code:"OA107"/* 水晶项链 */],
		[probability:11, code:"EC102"/* 回城卷 */]
	],
	drops_more:[
	]
]
