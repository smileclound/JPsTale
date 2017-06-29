monster = [
	ID:'a7-deathmage',
	Name:'教皇',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:104,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:10000,
		// Attack Status
		Atk:[320, 340],
		AtkSpeed:9,
		Range:100,
		Rating:1700,

		// Defence Status
		Flee:1600,
		Absorb:20,
		Block:15,
		DamageStunPers:10,

		// Resistance
		Earth:0,
		Fire:100,
		Ice:100,// Water
		Lighting:100,// Wind
		Poison:100,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:9,
		Real_Sight:460,
		Talks:[],

		// Move Behavier
		Move_Speed:6,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:0,
		SkillDamage:[385, 425],
		SkillDistance:300,
		SkillRange:0,
		SkillRating:50,
		SkillCurse:0,

		// Heal Behavier
		PotionPercent:0,
		PotionCount:0,
	],

	Looks:[
		ClassCode:0,
		ArrowPosi:[0, 0],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:2,
		Model:'char/monster/MonMokova/mokova.ini',
		Sound:0x000018B0,
	],

	// Drops
	AllSeeItem:false,
	Exp:36500,
	Quantity:1,
	drops:[
		[probability:3410, code:"NULL"/* Drops nothing */],
		[probability:4660, code:"GG101", value:[8300, 12800]/* Gold */],
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
		[probability:16, code:"WA121"/* 苍红斧 */],
		[probability:16, code:"WC121"/* 灭牙爪 */],
		[probability:16, code:"WH122"/* 魁伐折罗 */],
		[probability:16, code:"WM122"/* 流泉月花 */],
		[probability:16, code:"WP122"/* 天之琼侔 */],
		[probability:16, code:"WS123"/* 红羽 */],
		[probability:16, code:"WS224"/* 暗牙黄泉津 */],
		[probability:16, code:"WT122"/* 暗破 */],
		[probability:16, code:"DA119"/* 死神绝命铠 */],
		[probability:16, code:"DA219"/* 幻彩羽袍 */],
		[probability:16, code:"DS121"/* 凤凰之盾 */],
		[probability:16, code:"OM122"/* 恶魔法珠 */],
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
		[probability:12, code:"DB111"/* 圣靴 */],
		[probability:12, code:"DG112"/* 巨灵护手 */],
		[probability:12, code:"OA214"/* 金刚臂环 */],
		[probability:12, code:"DB112"/* 破棘之靴 */],
		[probability:12, code:"DG114"/* 金刚护手 */],
		[probability:12, code:"OA216"/* 鎏金臂环 */],
		[probability:12, code:"EC105"/* 公会卷轴 */],
		[probability:118, code:"OA102"/* 红玉项链 */],
		[probability:118, code:"OR101"/* 情殇 */],
		[probability:118, code:"OA101"/* 爱之石链 */],
		[probability:118, code:"OR102"/* 铜戒指 */],
		[probability:118, code:"OA114"/* 神之庇护 */],
		[probability:118, code:"OS109"/* 龙鳞石 */],
		[probability:118, code:"OS110"/* 钻晶石 */],
		[probability:118, code:"OS111"/* 龙睛石 */],
		[probability:118, code:"OS112"/* 圣晶石 */]
	],
	drops_more:[
	]
]
