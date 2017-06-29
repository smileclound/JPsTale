monster = [
	ID:'vip_mummy',
	Name:'沙漠木乃伊',
	Clazz:1,// 1 BOSS
	Brood:0x90,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:110,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:30000,
		// Attack Status
		Atk:[420, 1535],
		AtkSpeed:7,
		Range:70,
		Rating:2800,

		// Defence Status
		Flee:2200,
		Absorb:90,
		Block:2,
		DamageStunPers:100,

		// Resistance
		Earth:0,
		Fire:15,
		Ice:25,// Water
		Lighting:0,// Wind
		Poison:100,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:8,
		Real_Sight:360,
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
		ArrowPosi:[0, 45],
		ModelSize:1.5,
		UseEventModel:false,
		SizeLevel:1,
		Model:'char/monster/MonMummy/MonMummy.ini',
		Sound:0x000012A0,
	],

	// Drops
	AllSeeItem:false,
	Exp:500000,
	Quantity:1,
	drops:[
		[probability:4140, code:"NULL"/* Drops nothing */],
		[probability:3530, code:"EC103"/* 回城卷 */],
		[probability:136, code:"WA111"/* 天阙斧 */],
		[probability:136, code:"WC114"/* 天狼爪 */],
		[probability:136, code:"WM113"/* 魔蜓杖 */],
		[probability:136, code:"WP114"/* 龙翼枪 */],
		[probability:136, code:"WS115"/* 精灵之翼 */],
		[probability:136, code:"WS217"/* 鬼切 */],
		[probability:136, code:"WT114"/* 追月标 */],
		[probability:136, code:"DA122"/* 炫金圣铠 */],
		[probability:136, code:"DA222"/* 雅典娜圣衣 */],
		[probability:136, code:"DS116"/* 远古之盾 */],
		[probability:136, code:"OM114"/* 堕天 */],
		[probability:63, code:"WA114"/* 战神之刃 */],
		[probability:63, code:"WC115"/* 魔玉爪 */],
		[probability:63, code:"WM115"/* 亡灵刺 */],
		[probability:63, code:"WP116"/* 虹月 */],
		[probability:63, code:"WS116"/* 血精灵 */],
		[probability:63, code:"WS219"/* 凝雾 */],
		[probability:63, code:"WT116"/* 玛雅神标 */],
		[probability:63, code:"DA123"/* 凤凰圣铠 */],
		[probability:63, code:"DA223"/* 凤凰圣衣 */],
		[probability:63, code:"DS118"/* 死神之盾 */],
		[probability:63, code:"OM111"/* 淬火乌晶 */],
		[probability:2, code:"WA117"/* 远古战斧 */],
		[probability:2, code:"WC116"/* 泰坦爪 */],
		[probability:2, code:"WM117"/* 神圣之光 */],
		[probability:2, code:"WP119"/* 棲羽亚陀 */],
		[probability:2, code:"WS118"/* 风切 */],
		[probability:2, code:"WS220"/* 龙牙 */],
		[probability:2, code:"WT118"/* 噬月 */],
		[probability:2, code:"DA102"/* 战斗服 */],
		[probability:2, code:"DA202"/* 袍 */],
		[probability:5, code:"OR205"/* 火神指环 */],
		[probability:5, code:"OR103"/* 金戒指 */],
		[probability:5, code:"OA130"/* 凯尔维苏项链 */],
		[probability:5, code:"OA114"/* 神之庇护 */],
		[probability:5, code:"OS113"/* 恶魔石 */],
		[probability:5, code:"OS112"/* 圣晶石 */],
		[probability:5, code:"OS111"/* 龙睛石 */],
		[probability:5, code:"OS110"/* 钻晶石 */],
		[probability:5, code:"OS109"/* 龙鳞石 */],
		[probability:26, code:"DB113"/* 遁地靴 */],
		[probability:26, code:"DG117"/* 炫钻护手 */],
		[probability:26, code:"OA217"/* 炫钻臂环 */]
	],
	drops_more:[
	]
]
