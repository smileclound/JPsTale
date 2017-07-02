monster = [
	ID:'6_papachick',
	Name:'啪啪鸡',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:6,
	ActiveHour:0,
	RespawnGroup:[2, 2],

	Attributes:[
		Life:15,
		// Attack Status
		Atk:[2, 3],
		AtkSpeed:8,
		Range:30,
		Rating:100,

		// Defence Status
		Flee:10,
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
		IQ:5,
		Real_Sight:240,
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
		Model:'char/monster/PaPachick/PaPachick.ini',
		Sound:0x00002180,
	],

	// Drops
	AllSeeItem:false,
	Exp:205,
	Quantity:1,
	drops:[
		[probability:2350, code:"NULL"/* Drops nothing */],
		[probability:5800, code:"GG101", value:[1500, 3000]/* Gold */],
		[probability:400, code:"PS101"/* 低级恢复耐力药水 */],
		[probability:400, code:"PM101"/* 低级恢复魔法药水 */],
		[probability:400, code:"PL101"/* 低级复生命药水 */],
		[probability:36, code:"DA102"/* 战斗服 */],
		[probability:36, code:"WA101"/* 石斧 */],
		[probability:36, code:"WC101"/* 爪 */],
		[probability:36, code:"WH101"/* 狼牙棒 */],
		[probability:36, code:"WP101"/* 精制木杖 */],
		[probability:36, code:"WS101"/* 短弓 */],
		[probability:36, code:"WS201"/* 神木剑 */],
		[probability:36, code:"WT101"/* 标枪 */],
		[probability:36, code:"OR101"/* 情殇 */],
		[probability:36, code:"OA101"/* 爱之石链 */],
		[probability:36, code:"GP101"/* 独角兽水晶 */],
		[probability:28, code:"GP101"/* 独角兽水晶 */],
		[probability:28, code:"GP101"/* 独角兽水晶 */],
		[probability:28, code:"DS101"/* 木盾 */],
		[probability:28, code:"DB101"/* 皮靴 */],
		[probability:28, code:"DG101"/* 手套 */],
		[probability:28, code:"OA201"/* 皮制臂环 */],
		[probability:28, code:"OS102"/* 流云石 */],
		[probability:8, code:"OS103"/* 海精石 */],
		[probability:8, code:"OR102"/* 铜戒指 */],
		[probability:8, code:"OR103"/* 金戒指 */],
		[probability:8, code:"OA102"/* 红玉项链 */],
		[probability:8, code:"OA103"/* 多情环 */],
		[probability:8, code:"GP109"/* 神秘水晶 */]
	],
	drops_more:[
	]
]
