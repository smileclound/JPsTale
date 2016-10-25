monster = [
	ID:'ha_death_knight',
	Name:'幽灵骑士',
	Clazz:0,// 1 BOSS
	Brood:0x90,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:100,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:30000,
		// Attack Status
		Atk:[400, 440],
		AtkSpeed:8,
		Range:100,
		Rating:2600,

		// Defence Status
		Flee:2500,
		Absorb:60,
		Block:12,
		DamageStunPers:5,

		// Resistance
		Earth:0,
		Fire:40,
		Ice:100,// Water
		Lighting:40,// Wind
		Poison:100,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:9,
		Real_Sight:400,
		Talks:[],

		// Move Behavier
		Move_Speed:1,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:15,
		SkillDamage:[750, 790],
		SkillDistance:2,
		SkillRange:280,
		SkillRating:20,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:0,
		PotionCount:0,
	],

	Looks:[
		ClassCode:0,
		ArrowPosi:[0, 0],
		ModelSize:1.2,
		UseEventModel:false,
		SizeLevel:2,
		Model:'char/monster/death_knight/death_knight.ini',
		Sound:0x00001680,
	],

	// Drops
	AllSeeItem:false,
	Exp:80000,
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
		[probability:0, code:"WA111"/* 天阙斧 */],
		[probability:0, code:"WC114"/* 天狼爪 */],
		[probability:0, code:"WM113"/* 魔蜓杖 */],
		[probability:0, code:"WP114"/* 龙翼枪 */],
		[probability:0, code:"WS115"/* 精灵之翼 */],
		[probability:0, code:"WS217"/* 鬼切 */],
		[probability:0, code:"WT114"/* 追月标 */],
		[probability:0, code:"DA122"/* 炫金圣铠 */],
		[probability:0, code:"DA222"/* 雅典娜圣衣 */],
		[probability:0, code:"DS118"/* 死神之盾 */],
		[probability:0, code:"OM111"/* 淬火乌晶 */],
		[probability:70, code:"EC103"/* 回城卷 */],
		[probability:7, code:"OA102"/* 红玉项链 */],
		[probability:7, code:"OR101"/* 情殇 */],
		[probability:7, code:"OA101"/* 爱之石链 */],
		[probability:7, code:"OR102"/* 铜戒指 */],
		[probability:7, code:"OA114"/* 神之庇护 */],
		[probability:7, code:"OS110"/* 钻晶石 */],
		[probability:7, code:"OS111"/* 龙睛石 */],
		[probability:7, code:"OS112"/* 圣晶石 */],
		[probability:7, code:"OS113"/* 恶魔石 */]
	],
	drops_more:[
	]
]
