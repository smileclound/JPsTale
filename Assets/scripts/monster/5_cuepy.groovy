monster = [
	ID:'5_cuepy',
	Name:'红独角兽',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:5,
	ActiveHour:0,
	RespawnGroup:[2, 2],

	Attributes:[
		Life:25,
		// Attack Status
		Atk:[2, 5],
		AtkSpeed:8,
		Range:35,
		Rating:125,

		// Defence Status
		Flee:14,
		Absorb:0,
		Block:0,
		DamageStunPers:100,

		// Resistance
		Earth:10,
		Fire:10,
		Ice:5,// Water
		Lighting:0,// Wind
		Poison:10,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:7,
		Real_Sight:250,
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
		ArrowPosi:[8, 25],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:0,
		Model:'char/monster/Hopy/mopy.INI',
		Sound:0x00001080,
	],

	// Drops
	AllSeeItem:false,
	Exp:111,
	Quantity:10,
	drops:[
		[probability:3200, code:"NULL"/* Drops nothing */],
		[probability:5000, code:"GG101", value:[8, 15]/* Gold */],
		[probability:220, code:"PS101"/* 低级恢复耐力药水 */],
		[probability:220, code:"PL101"/* 低级复生命药水 */],
		[probability:220, code:"PL101"/* 低级复生命药水 */],
		[probability:220, code:"PS101"/* 低级恢复耐力药水 */],
		[probability:220, code:"PL101"/* 低级复生命药水 */],
		[probability:50, code:"DA102"/* 战斗服 */],
		[probability:50, code:"WA101"/* 石斧 */],
		[probability:50, code:"WC101"/* 爪 */],
		[probability:50, code:"WH101"/* 狼牙棒 */],
		[probability:50, code:"WP101"/* 精制木杖 */],
		[probability:50, code:"WS101"/* 短弓 */],
		[probability:50, code:"WT101"/* 标枪 */],
		[probability:50, code:"OR101"/* 情殇 */],
		[probability:16, code:"DA103"/* 皮甲 */],
		[probability:16, code:"WA102"/* 铁斧 */],
		[probability:16, code:"WC102"/* 虎爪 */],
		[probability:16, code:"WH102"/* 星刺锤 */],
		[probability:16, code:"WP102"/* 点钢蛇矛 */],
		[probability:16, code:"WS102"/* 羊角弓 */],
		[probability:16, code:"WT102"/* 战标 */],
		[probability:16, code:"GP101"/* 独角兽水晶 */],
		[probability:16, code:"DS101"/* 木盾 */],
		[probability:16, code:"DB101"/* 皮靴 */],
		[probability:16, code:"DG101"/* 手套 */],
		[probability:16, code:"OA201"/* 皮制臂环 */],
		[probability:16, code:"OS103"/* 海精石 */],
		[probability:16, code:"OR102"/* 铜戒指 */],
		[probability:16, code:"OR103"/* 金戒指 */],
		[probability:16, code:"OA102"/* 红玉项链 */],
		[probability:16, code:"OA103"/* 多情环 */],
		[probability:16, code:"GP109"/* 神秘水晶 */]
	],
	drops_more:[
	]
]
