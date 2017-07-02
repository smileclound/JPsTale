monster = [
	ID:'z21_fireelemental',
	Name:'幻化之焰',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:120,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:30000,
		// Attack Status
		Atk:[930, 950],
		AtkSpeed:8,
		Range:330,
		Rating:1400,

		// Defence Status
		Flee:1200,
		Absorb:90,
		Block:20,
		DamageStunPers:60,

		// Resistance
		Earth:0,
		Fire:30,
		Ice:40,// Water
		Lighting:30,// Wind
		Poison:60,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:9,
		Real_Sight:620,
		Talks:[],

		// Move Behavier
		Move_Speed:4,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:0,
		SkillDamage:[1525, 1580],
		SkillDistance:0,
		SkillRange:130,
		SkillRating:17,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:0,
		PotionCount:0,
	],

	Looks:[
		ClassCode:0,
		ArrowPosi:[-8, 200],
		ModelSize:1.4,
		UseEventModel:false,
		SizeLevel:2,
		Model:'char/monster/Firegolem/Firegolem.INI',
		Sound:0x000018D0,
	],

	// Drops
	AllSeeItem:false,
	Exp:500000,
	Quantity:2,
	drops:[
		[probability:4000, code:"GG101", value:[12000, 16800]/* Gold */],
		[probability:181, code:"WA114"/* 战神之刃 */],
		[probability:181, code:"WC115"/* 魔玉爪 */],
		[probability:181, code:"WM115"/* 亡灵刺 */],
		[probability:181, code:"WP116"/* 虹月 */],
		[probability:181, code:"WS116"/* 血精灵 */],
		[probability:181, code:"WS219"/* 凝雾 */],
		[probability:181, code:"WT116"/* 玛雅神标 */],
		[probability:181, code:"DA123"/* 凤凰圣铠 */],
		[probability:181, code:"DA223"/* 凤凰圣衣 */],
		[probability:181, code:"DS118"/* 死神之盾 */],
		[probability:181, code:"OM111"/* 淬火乌晶 */],
		[probability:333, code:"OR205"/* 火神指环 */],
		[probability:333, code:"OR103"/* 金戒指 */],
		[probability:333, code:"OA130"/* 凯尔维苏项链 */],
		[probability:333, code:"OA114"/* 神之庇护 */],
		[probability:333, code:"OS113"/* 恶魔石 */],
		[probability:333, code:"OS112"/* 圣晶石 */],
		[probability:333, code:"OS111"/* 龙睛石 */],
		[probability:333, code:"OS110"/* 钻晶石 */],
		[probability:333, code:"OS109"/* 龙鳞石 */],
		[probability:250, code:"EC103"/* 回城卷 */],
		[probability:250, code:"DB113"/* 遁地靴 */],
		[probability:250, code:"DG117"/* 炫钻护手 */],
		[probability:250, code:"OA217"/* 炫钻臂环 */]
	],
	drops_more:[
	]
]
