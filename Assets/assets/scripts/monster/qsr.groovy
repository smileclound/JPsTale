monster = [
	ID:'qsr',
	Name:'小魔怪',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:75,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:3000,
		// Attack Status
		Atk:[50, 80],
		AtkSpeed:8,
		Range:50,
		Rating:600,

		// Defence Status
		Flee:110,
		Absorb:30,
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
		Nature:0x80,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:10,
		Real_Sight:300,
		Talks:[],

		// Move Behavier
		Move_Speed:2,
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
		ArrowPosi:[0, 0],
		ModelSize:0.7,
		UseEventModel:false,
		SizeLevel:1,
		Model:'char/monster/Monhobsanta/Monhobsanta.INI',
		Sound:0x00001010,
	],

	// Drops
	AllSeeItem:false,
	Exp:120000,
	Quantity:1,
	drops:[
		[probability:0, code:"NULL"/* Drops nothing */],
		[probability:287, code:"WA122"/* 龙炎斧 */],
		[probability:287, code:"WC122"/* 黑虹爪 */],
		[probability:287, code:"WH123"/* 大鎚伊武岐 */],
		[probability:287, code:"WM123"/* 螺钿三日星 */],
		[probability:287, code:"WP123"/* 卡厄斯 */],
		[probability:287, code:"WS124"/* 天之麻迦古弓 */],
		[probability:287, code:"WS225"/* 32d22w */],
		[probability:287, code:"WT123"/* 天严云 */],
		[probability:287, code:"DA119"/* 死神绝命铠 */],
		[probability:287, code:"DA219"/* 幻彩羽袍 */],
		[probability:287, code:"OA120"/* 九转护符 */],
		[probability:287, code:"OS109"/* 龙鳞石 */],
		[probability:164, code:"wa106"/* 凯旋 */],
		[probability:164, code:"WC106"/* 狼牙刃 */],
		[probability:164, code:"WM107"/* 天魔杖 */],
		[probability:164, code:"WP107"/* 战神之镰 */],
		[probability:164, code:"WS114"/* 爱神之翼 */],
		[probability:164, code:"WS210"/* 敲饭萍逞 家靛 */],
		[probability:164, code:"WT107"/* 天命标 */],
		[probability:164, code:"DA120"/* 炎龙圣铠 */],
		[probability:164, code:"DA220"/* 修罗圣衣 */],
		[probability:164, code:"OR119"/* 轮回之戒 */],
		[probability:164, code:"OR121"/* 龙誉之戒 */],
		[probability:164, code:"OR120"/* 涅磐之戒 */],
		[probability:164, code:"OA121"/* 圣龙之光 */],
		[probability:164, code:"OS110"/* 钻晶石 */],
		[probability:92, code:"WA109"/* 破山斧 */],
		[probability:92, code:"WC109"/* 兽之斧刃 */],
		[probability:92, code:"WM111"/* 王者杖 */],
		[probability:92, code:"WP112"/* 傲天枪 */],
		[probability:92, code:"WS111"/* 龙骨战弓 */],
		[probability:92, code:"WS214"/* 金刚伏魔剑 */],
		[probability:92, code:"WT110"/* 飞云标 */],
		[probability:92, code:"DA121"/* 魔龙圣铠 */],
		[probability:92, code:"DA221"/* 涅磐圣衣 */],
		[probability:92, code:"OR101"/* 情殇 */],
		[probability:92, code:"OR102"/* 铜戒指 */],
		[probability:92, code:"OA102"/* 红玉项链 */],
		[probability:92, code:"OS111"/* 龙睛石 */],
		[probability:41, code:"WA111"/* 天阙斧 */],
		[probability:41, code:"WC114"/* 天狼爪 */],
		[probability:41, code:"WM113"/* 魔蜓杖 */],
		[probability:41, code:"WP114"/* 龙翼枪 */],
		[probability:41, code:"WS115"/* 精灵之翼 */],
		[probability:41, code:"WS217"/* 鬼切 */],
		[probability:41, code:"WT114"/* 追月标 */],
		[probability:41, code:"DA122"/* 炫金圣铠 */],
		[probability:41, code:"DA222"/* 雅典娜圣衣 */],
		[probability:41, code:"OR205"/* 火神指环 */],
		[probability:41, code:"OA101"/* 爱之石链 */],
		[probability:41, code:"OS112"/* 圣晶石 */],
		[probability:100, code:"OS113"/* 恶魔石 */],
		[probability:4, code:"WA114"/* 战神之刃 */],
		[probability:4, code:"WC115"/* 魔玉爪 */],
		[probability:4, code:"WM115"/* 亡灵刺 */],
		[probability:4, code:"WP116"/* 虹月 */],
		[probability:4, code:"WS116"/* 血精灵 */],
		[probability:4, code:"WS219"/* 凝雾 */],
		[probability:4, code:"WT116"/* 玛雅神标 */],
		[probability:4, code:"DA123"/* 凤凰圣铠 */],
		[probability:4, code:"DA223"/* 凤凰圣衣 */],
		[probability:4, code:"DS118"/* 死神之盾 */],
		[probability:4, code:"OM111"/* 淬火乌晶 */],
		[probability:1400, code:"EC103"/* 回城卷 */],
		[probability:66, code:"DB111"/* 圣靴 */],
		[probability:66, code:"DG112"/* 巨灵护手 */],
		[probability:66, code:"OA214"/* 金刚臂环 */],
		[probability:66, code:"DB112"/* 破棘之靴 */],
		[probability:66, code:"DG114"/* 金刚护手 */],
		[probability:66, code:"OA216"/* 鎏金臂环 */],
		[probability:66, code:"DB109"/* 大地靴 */],
		[probability:66, code:"DG110"/* 火云护手 */],
		[probability:66, code:"OA211"/* 玄铁臂环 */],
		[probability:66, code:"DS105"/* 罗塔盾 */],
		[probability:66, code:"OM109"/* 暗月 */],
		[probability:66, code:"DS113"/* 龙纹盾 */],
		[probability:66, code:"OM106"/* 水星 */],
		[probability:66, code:"DS116"/* 远古之盾 */],
		[probability:66, code:"OM114"/* 堕天 */]
	],
	drops_more:[
	]
]
