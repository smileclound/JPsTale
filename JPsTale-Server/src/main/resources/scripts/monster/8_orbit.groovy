monster = [
	ID:'8_orbit',
	Name:'火眼怪',
	Clazz:0,// 1 BOSS
	Brood:0x92,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:8,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:80,
		// Attack Status
		Atk:[5, 9],
		AtkSpeed:5,
		Range:45,
		Rating:180,

		// Defence Status
		Flee:16,
		Absorb:1,
		Block:0,
		DamageStunPers:100,

		// Resistance
		Earth:10,
		Fire:-10,
		Ice:0,// Water
		Lighting:0,// Wind
		Poison:-15,
	],

	// AI
	AI:[
		Nature:0x80,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:5,
		Real_Sight:300,
		Talks:[],

		// Move Behavier
		Move_Speed:1,
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
		ArrowPosi:[0, 40],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:0,
		Model:'char/monster/Moneye/eye.INI',
		Sound:0x00001070,
	],

	// Drops
	AllSeeItem:false,
	Exp:250,
	Quantity:1,
	drops:[
		[probability:3000, code:"NULL"/* Drops nothing */],
		[probability:4000, code:"GG101", value:[15, 30]/* Gold */],
		[probability:400, code:"PL101"/* 低级复生命药水 */],
		[probability:400, code:"PS101"/* 低级恢复耐力药水 */],
		[probability:400, code:"PM101"/* 低级恢复魔法药水 */],
		[probability:400, code:"PL101"/* 低级复生命药水 */],
		[probability:72, code:"WS101"/* 短弓 */],
		[probability:72, code:"WA101"/* 石斧 */],
		[probability:72, code:"WH101"/* 狼牙棒 */],
		[probability:72, code:"WP101"/* 精制木杖 */],
		[probability:72, code:"WT101"/* 标枪 */],
		[probability:72, code:"DA102"/* 战斗服 */],
		[probability:72, code:"DG101"/* 手套 */],
		[probability:72, code:"DS101"/* 木盾 */],
		[probability:72, code:"DB101"/* 皮靴 */],
		[probability:72, code:"OR101"/* 情殇 */],
		[probability:72, code:"OA201"/* 皮制臂环 */],
		[probability:40, code:"WS102"/* 羊角弓 */],
		[probability:40, code:"WS202"/* 蛇行匕首 */],
		[probability:40, code:"WA102"/* 铁斧 */],
		[probability:40, code:"WH102"/* 星刺锤 */],
		[probability:40, code:"WP102"/* 点钢蛇矛 */],
		[probability:40, code:"WT102"/* 战标 */],
		[probability:40, code:"WC102"/* 虎爪 */],
		[probability:40, code:"OR102"/* 铜戒指 */],
		[probability:40, code:"OA102"/* 红玉项链 */],
		[probability:40, code:"OS101"/* 星遗石 */],
		[probability:18, code:"WS103"/* 手弩 */],
		[probability:18, code:"WS203"/* 断剑 */],
		[probability:18, code:"WA103"/* 板斧 */],
		[probability:18, code:"WH103"/* 长锤 */],
		[probability:18, code:"WP103"/* 鹰嘴矛 */],
		[probability:18, code:"WT103"/* 长牙标 */],
		[probability:18, code:"WC103"/* 鱼镰刺 */],
		[probability:18, code:"OR103"/* 金戒指 */],
		[probability:18, code:"OA103"/* 多情环 */],
		[probability:18, code:"DA103"/* 皮甲 */],
		[probability:18, code:"OS102"/* 流云石 */]
	],
	drops_more:[
	]
]
