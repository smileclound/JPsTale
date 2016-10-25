monster = [
	ID:'5_zeldy',
	Name:'透明妖',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:5,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:18,
		// Attack Status
		Atk:[2, 4],
		AtkSpeed:7,
		Range:35,
		Rating:110,

		// Defence Status
		Flee:10,
		Absorb:1,
		Block:0,
		DamageStunPers:100,

		// Resistance
		Earth:100,
		Fire:100,
		Ice:100,// Water
		Lighting:100,// Wind
		Poison:100,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:8,
		Real_Sight:270,
		Talks:[],

		// Move Behavier
		Move_Speed:1,
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
		ArrowPosi:[-6, 30],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:-1,
		Model:'char/monster/MonMudy/monmudyjel.ini',
		Sound:0x00001210,
	],

	// Drops
	AllSeeItem:false,
	Exp:110,
	Quantity:10,
	drops:[
		[probability:2600, code:"NULL"/* Drops nothing */],
		[probability:5000, code:"GG101", value:[8, 15]/* Gold */],
		[probability:700, code:"PL101"/* 低级复生命药水 */],
		[probability:700, code:"PS101"/* 低级恢复耐力药水 */],
		[probability:54, code:"DA102"/* 战斗服 */],
		[probability:54, code:"DA202"/* 袍 */],
		[probability:54, code:"WA101"/* 石斧 */],
		[probability:54, code:"WH101"/* 狼牙棒 */],
		[probability:54, code:"WM101"/* 沉默之杖 */],
		[probability:54, code:"WP101"/* 精制木杖 */],
		[probability:54, code:"WS101"/* 短弓 */],
		[probability:54, code:"WT101"/* 标枪 */],
		[probability:54, code:"OM101"/* 念珠 */],
		[probability:54, code:"OR101"/* 情殇 */],
		[probability:54, code:"PM101"/* 低级恢复魔法药水 */],
		[probability:21, code:"DA103"/* 皮甲 */],
		[probability:21, code:"DA203"/* 长袍 */],
		[probability:21, code:"WA102"/* 铁斧 */],
		[probability:21, code:"WC102"/* 虎爪 */],
		[probability:21, code:"WH102"/* 星刺锤 */],
		[probability:21, code:"WM102"/* 执着之杖 */],
		[probability:21, code:"WP102"/* 点钢蛇矛 */],
		[probability:21, code:"WS202"/* 蛇行匕首 */],
		[probability:21, code:"WS102"/* 羊角弓 */],
		[probability:21, code:"WT102"/* 战标 */],
		[probability:21, code:"OM102"/* 水晶球 */],
		[probability:21, code:"OR102"/* 铜戒指 */],
		[probability:21, code:"OA102"/* 红玉项链 */],
		[probability:21, code:"PM102"/* 中级恢复魔法药水 */],
		[probability:7, code:"DA104"/* 铠甲 */],
		[probability:7, code:"DA204"/* 常青袍 */],
		[probability:7, code:"WA103"/* 板斧 */],
		[probability:7, code:"WC103"/* 鱼镰刺 */],
		[probability:7, code:"WH103"/* 长锤 */],
		[probability:7, code:"WM103"/* 暗杀之杖 */],
		[probability:7, code:"WP103"/* 鹰嘴矛 */],
		[probability:7, code:"WS203"/* 断剑 */],
		[probability:7, code:"WS103"/* 手弩 */],
		[probability:7, code:"WT103"/* 长牙标 */],
		[probability:7, code:"OM103"/* 淬角水晶 */],
		[probability:7, code:"OR103"/* 金戒指 */],
		[probability:7, code:"OA103"/* 多情环 */],
		[probability:7, code:"PM102"/* 中级恢复魔法药水 */]
	],
	drops_more:[
	]
]
