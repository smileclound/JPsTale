monster = [
	ID:'5_mushroomghost',
	Name:'蘑菇精',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:5,
	ActiveHour:0,
	RespawnGroup:[2, 2],

	Attributes:[
		Life:20,
		// Attack Status
		Atk:[2, 3],
		AtkSpeed:7,
		Range:25,
		Rating:130,

		// Defence Status
		Flee:10,
		Absorb:1,
		Block:0,
		DamageStunPers:100,

		// Resistance
		Earth:0,
		Fire:20,
		Ice:10,// Water
		Lighting:0,// Wind
		Poison:0,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:4,
		Real_Sight:260,
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
		ArrowPosi:[-3, 50],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:0,
		Model:'char/monster/Monmushroom/Monmushroom.ini',
		Sound:0x000010B0,
	],

	// Drops
	AllSeeItem:false,
	Exp:106,
	Quantity:10,
	drops:[
		[probability:3100, code:"NULL"/* Drops nothing */],
		[probability:4800, code:"GG101", value:[608, 615]/* Gold */],
		[probability:700, code:"PL101"/* 低级复生命药水 */],
		[probability:700, code:"PS101"/* 低级恢复耐力药水 */],
		[probability:41, code:"WA101"/* 石斧 */],
		[probability:41, code:"WH101"/* 狼牙棒 */],
		[probability:41, code:"WP101"/* 精制木杖 */],
		[probability:41, code:"WS101"/* 短弓 */],
		[probability:41, code:"WS202"/* 蛇行匕首 */],
		[probability:41, code:"WT101"/* 标枪 */],
		[probability:41, code:"OR101"/* 情殇 */],
		[probability:41, code:"DS101"/* 木盾 */],
		[probability:41, code:"DB101"/* 皮靴 */],
		[probability:41, code:"DG101"/* 手套 */],
		[probability:41, code:"OA201"/* 皮制臂环 */],
		[probability:41, code:"GP101"/* 独角兽水晶 */],
		[probability:15, code:"WA102"/* 铁斧 */],
		[probability:15, code:"WC102"/* 虎爪 */],
		[probability:15, code:"WH102"/* 星刺锤 */],
		[probability:15, code:"WP102"/* 点钢蛇矛 */],
		[probability:15, code:"WS102"/* 羊角弓 */],
		[probability:15, code:"WS202"/* 蛇行匕首 */],
		[probability:15, code:"OR102"/* 铜戒指 */],
		[probability:15, code:"OA102"/* 红玉项链 */],
		[probability:15, code:"DS102"/* 钉盾 */],
		[probability:15, code:"DB102"/* 精灵靴 */],
		[probability:15, code:"DG102"/* 拳套 */],
		[probability:15, code:"OA202"/* 精铁臂环 */],
		[probability:15, code:"GP101"/* 独角兽水晶 */]
	],
	drops_more:[
	]
]
