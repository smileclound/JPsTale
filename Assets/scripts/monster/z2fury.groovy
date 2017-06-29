monster = [
	ID:'z2fury',
	Name:'幽灵狮魔',
	Clazz:1,// 1 BOSS
	Brood:0x92,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:150,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:30000,
		// Attack Status
		Atk:[4500, 4700],
		AtkSpeed:8,
		Range:280,
		Rating:5200,

		// Defence Status
		Flee:5200,
		Absorb:99,
		Block:20,
		DamageStunPers:60,

		// Resistance
		Earth:0,
		Fire:20,
		Ice:70,// Water
		Lighting:20,// Wind
		Poison:50,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:8,
		Real_Sight:600,
		Talks:[],

		// Move Behavier
		Move_Speed:4,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:20,
		SkillDamage:[6000, 6500],
		SkillDistance:0,
		SkillRange:110,
		SkillRating:20,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:0,
		PotionCount:0,
	],

	Looks:[
		ClassCode:0,
		ArrowPosi:[0, 30],
		ModelSize:1.5,
		UseEventModel:false,
		SizeLevel:3,
		Model:'char/monster/Monfury/Monfury.ini',
		Sound:0x00001310,
	],

	// Drops
	AllSeeItem:false,
	Exp:1000000,
	Quantity:1,
	drops:[
		[probability:0, code:"NULL"/* Drops nothing */],
		[probability:2700, code:"GG101", value:[12000, 16800]/* Gold */],
		[probability:545, code:"WA114"/* 战神之刃 */],
		[probability:545, code:"WC115"/* 魔玉爪 */],
		[probability:545, code:"WM115"/* 亡灵刺 */],
		[probability:545, code:"WP116"/* 虹月 */],
		[probability:545, code:"WS116"/* 血精灵 */],
		[probability:545, code:"WS219"/* 凝雾 */],
		[probability:545, code:"WT116"/* 玛雅神标 */],
		[probability:545, code:"DA123"/* 凤凰圣铠 */],
		[probability:545, code:"DA223"/* 凤凰圣衣 */],
		[probability:545, code:"DS118"/* 死神之盾 */],
		[probability:545, code:"OM111"/* 淬火乌晶 */],
		[probability:42, code:"WA117"/* 远古战斧 */],
		[probability:42, code:"WC116"/* 泰坦爪 */],
		[probability:42, code:"WM117"/* 神圣之光 */],
		[probability:42, code:"WP119"/* 棲羽亚陀 */],
		[probability:42, code:"WS118"/* 风切 */],
		[probability:42, code:"WS220"/* 龙牙 */],
		[probability:42, code:"WT118"/* 噬月 */],
		[probability:111, code:"OR205"/* 火神指环 */],
		[probability:111, code:"OR103"/* 金戒指 */],
		[probability:111, code:"OA130"/* 凯尔维苏项链 */],
		[probability:111, code:"OA114"/* 神之庇护 */],
		[probability:111, code:"OS113"/* 恶魔石 */],
		[probability:111, code:"OS112"/* 圣晶石 */],
		[probability:111, code:"OS111"/* 龙睛石 */],
		[probability:111, code:"OS110"/* 钻晶石 */],
		[probability:111, code:"OS109"/* 龙鳞石 */]
	],
	drops_more:[
	]
]
