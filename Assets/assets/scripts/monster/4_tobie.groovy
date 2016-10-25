monster = [
	ID:'4_tobie',
	Name:'红兔妖',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:4,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:25,
		// Attack Status
		Atk:[3, 5],
		AtkSpeed:9,
		Range:35,
		Rating:130,

		// Defence Status
		Flee:10,
		Absorb:20,
		Block:0,
		DamageStunPers:100,

		// Resistance
		Earth:0,
		Fire:0,
		Ice:0,// Water
		Lighting:0,// Wind
		Poison:50,
	],

	// AI
	AI:[
		Nature:0x80,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:10,
		Real_Sight:250,
		Talks:[],

		// Move Behavier
		Move_Speed:2,
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
		ArrowPosi:[-5, 44],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:0,
		Model:'char/monster/Monrebi/Monrebi-1.ini',
		Sound:0x00001200,
	],

	// Drops
	AllSeeItem:false,
	Exp:111,
	Quantity:10,
	drops:[
		[probability:2150, code:"NULL"/* Drops nothing */],
		[probability:5800, code:"GG101", value:[8, 15]/* Gold */],
		[probability:280, code:"PS101"/* 低级恢复耐力药水 */],
		[probability:280, code:"PS101"/* 低级恢复耐力药水 */],
		[probability:280, code:"PL101"/* 低级复生命药水 */],
		[probability:280, code:"PL101"/* 低级复生命药水 */],
		[probability:280, code:"PL101"/* 低级复生命药水 */],
		[probability:40, code:"DA102"/* 战斗服 */],
		[probability:40, code:"DA202"/* 袍 */],
		[probability:40, code:"WA101"/* 石斧 */],
		[probability:40, code:"WH101"/* 狼牙棒 */],
		[probability:40, code:"WM101"/* 沉默之杖 */],
		[probability:40, code:"WP101"/* 精制木杖 */],
		[probability:40, code:"WS101"/* 短弓 */],
		[probability:40, code:"WT101"/* 标枪 */],
		[probability:40, code:"OR101"/* 情殇 */],
		[probability:40, code:"GP101"/* 独角兽水晶 */],
		[probability:14, code:"DA103"/* 皮甲 */],
		[probability:14, code:"DA203"/* 长袍 */],
		[probability:14, code:"WA102"/* 铁斧 */],
		[probability:14, code:"WC102"/* 虎爪 */],
		[probability:14, code:"WH102"/* 星刺锤 */],
		[probability:14, code:"WM102"/* 执着之杖 */],
		[probability:14, code:"WP102"/* 点钢蛇矛 */],
		[probability:14, code:"WS102"/* 羊角弓 */],
		[probability:14, code:"WS202"/* 蛇行匕首 */],
		[probability:14, code:"WT102"/* 战标 */],
		[probability:14, code:"DS101"/* 木盾 */],
		[probability:14, code:"DB101"/* 皮靴 */],
		[probability:14, code:"DG101"/* 手套 */],
		[probability:14, code:"OA201"/* 皮制臂环 */],
		[probability:10, code:"OS103"/* 海精石 */],
		[probability:10, code:"OR103"/* 金戒指 */],
		[probability:10, code:"OA103"/* 多情环 */],
		[probability:10, code:"GP109"/* 神秘水晶 */],
		[probability:10, code:"PM101"/* 低级恢复魔法药水 */]
	],
	drops_more:[
	]
]
