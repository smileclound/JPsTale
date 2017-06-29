monster = [
	ID:'5_mudy',
	Name:'土妖',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:4,
	ActiveHour:0,
	RespawnGroup:[2, 2],

	Attributes:[
		Life:18,
		// Attack Status
		Atk:[2, 4],
		AtkSpeed:7,
		Range:45,
		Rating:110,

		// Defence Status
		Flee:10,
		Absorb:1,
		Block:0,
		DamageStunPers:100,

		// Resistance
		Earth:100,
		Fire:-50,
		Ice:0,// Water
		Lighting:100,// Wind
		Poison:100,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:8,
		Real_Sight:250,
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
		Model:'char/monster/MonMudy/Monmudy.ini',
		Sound:0x00001210,
	],

	// Drops
	AllSeeItem:false,
	Exp:110,
	Quantity:1,
	drops:[
		[probability:9999, code:"NULL"/* Drops nothing */],
		[probability:0, code:"DA111"/* 重装机铠 */],
		[probability:0, code:"DA211"/* 大法师袍 */],
		[probability:0, code:"WA110"/* 定神斧 */],
		[probability:0, code:"WC110"/* 九头刺蛇爪 */],
		[probability:0, code:"WH111"/* 赤冥之锤 */],
		[probability:0, code:"WM111"/* 王者杖 */],
		[probability:0, code:"WP111"/* 屠龙枪 */],
		[probability:0, code:"WS112"/* 人马之辉 */],
		[probability:0, code:"WS212"/* 嗜血屠魔剑 */],
		[probability:0, code:"WT111"/* 神标 */],
		[probability:0, code:"OM111"/* 淬火乌晶 */],
		[probability:0, code:"DS110"/* 宙斯盾 */],
		[probability:0, code:"DB110"/* 地火战靴 */],
		[probability:0, code:"DG110"/* 火云护手 */],
		[probability:0, code:"OA210"/* 百川流水臂环 */],
		[probability:0, code:"OR115"/* 守护之戒 */],
		[probability:0, code:"OA115"/* 暗印护符 */],
		[probability:0, code:"OS106"/* 玄风石 */]
	],
	drops_more:[
	]
]
