monster = [
	ID:'ha_leech',
	Name:'梦魇毒蛇',
	Clazz:0,// 1 BOSS
	Brood:0x91,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:100,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:20000,
		// Attack Status
		Atk:[330, 450],
		AtkSpeed:6,
		Range:45,
		Rating:1450,

		// Defence Status
		Flee:1600,
		Absorb:40,
		Block:0,
		DamageStunPers:100,

		// Resistance
		Earth:0,
		Fire:10,
		Ice:10,// Water
		Lighting:20,// Wind
		Poison:50,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:7,
		Real_Sight:340,
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
		ArrowPosi:[0, 10],
		ModelSize:0.8,
		UseEventModel:false,
		SizeLevel:-1,
		Model:'char/monster/leech/leech.INI',
		Sound:0x000010A0,
	],

	// Drops
	AllSeeItem:false,
	Exp:60000,
	Quantity:1,
	drops:[
		[probability:4410, code:"NULL"/* Drops nothing */],
		[probability:4660, code:"GG101", value:[6300, 12800]/* Gold */],
		[probability:40, code:"wa106"/* 凯旋 */],
		[probability:40, code:"WC106"/* 狼牙刃 */],
		[probability:40, code:"WM107"/* 天魔杖 */],
		[probability:40, code:"WP107"/* 战神之镰 */],
		[probability:40, code:"WS114"/* 爱神之翼 */],
		[probability:40, code:"WS210"/* 敲饭萍逞 家靛 */],
		[probability:40, code:"WT107"/* 天命标 */],
		[probability:40, code:"DA118"/* 米诺陶斯铠 */],
		[probability:40, code:"DA218"/* 撒旦披风 */],
		[probability:40, code:"DS120"/* 炫金之盾 */],
		[probability:40, code:"OM121"/* 海洋之心 */],
		[probability:18, code:"WA121"/* 苍红斧 */],
		[probability:18, code:"WC121"/* 灭牙爪 */],
		[probability:18, code:"WH122"/* 魁伐折罗 */],
		[probability:18, code:"WM122"/* 流泉月花 */],
		[probability:18, code:"WP122"/* 天之琼侔 */],
		[probability:18, code:"WS123"/* 红羽 */],
		[probability:18, code:"WS224"/* 暗牙黄泉津 */],
		[probability:18, code:"WT122"/* 暗破 */],
		[probability:18, code:"DA119"/* 死神绝命铠 */],
		[probability:18, code:"DA219"/* 幻彩羽袍 */],
		[probability:18, code:"DS121"/* 凤凰之盾 */],
		[probability:18, code:"OM122"/* 恶魔法珠 */],
		[probability:9, code:"WA109"/* 破山斧 */],
		[probability:9, code:"WC109"/* 兽之斧刃 */],
		[probability:9, code:"WM111"/* 王者杖 */],
		[probability:9, code:"WP112"/* 傲天枪 */],
		[probability:9, code:"WS111"/* 龙骨战弓 */],
		[probability:9, code:"WS214"/* 金刚伏魔剑 */],
		[probability:9, code:"WT110"/* 飞云标 */],
		[probability:9, code:"DA120"/* 炎龙圣铠 */],
		[probability:9, code:"DA220"/* 修罗圣衣 */],
		[probability:9, code:"DS122"/* 奥丁之盾 */],
		[probability:9, code:"OM123"/* 紫金之光 */],
		[probability:20, code:"EC103"/* 回城卷 */],
		[probability:70, code:"EC105"/* 公会卷轴 */],
		[probability:14, code:"OA102"/* 红玉项链 */],
		[probability:14, code:"OR101"/* 情殇 */],
		[probability:14, code:"OA101"/* 爱之石链 */],
		[probability:14, code:"OR102"/* 铜戒指 */],
		[probability:14, code:"OA114"/* 神之庇护 */]
	],
	drops_more:[
	]
]