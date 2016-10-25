monster = [
	ID:'s2usk',
	Name:'达克魔王',
	Clazz:0,// 1 BOSS
	Brood:0x92,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:103,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:15000,
		// Attack Status
		Atk:[250, 280],
		AtkSpeed:9,
		Range:220,
		Rating:1500,

		// Defence Status
		Flee:1500,
		Absorb:30,
		Block:10,
		DamageStunPers:100,

		// Resistance
		Earth:0,
		Fire:15,
		Ice:40,// Water
		Lighting:0,// Wind
		Poison:40,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:9,
		Real_Sight:460,
		Talks:[],

		// Move Behavier
		Move_Speed:4,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:15,
		SkillDamage:[315, 345],
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
		ArrowPosi:[0, 0],
		ModelSize:1.3,
		UseEventModel:false,
		SizeLevel:1,
		Model:'char/monster/RPTshurikar/RPTshurikar.ini',
		Sound:0x00001290,
	],

	// Drops
	AllSeeItem:false,
	Exp:80000,
	Quantity:1,
	drops:[
		[probability:4410, code:"NULL"/* Drops nothing */],
		[probability:4560, code:"GG101", value:[6300, 8800]/* Gold */],
		[probability:37, code:"WA121"/* 苍红斧 */],
		[probability:37, code:"WC121"/* 灭牙爪 */],
		[probability:37, code:"WH122"/* 魁伐折罗 */],
		[probability:37, code:"WM122"/* 流泉月花 */],
		[probability:37, code:"WP122"/* 天之琼侔 */],
		[probability:37, code:"WS123"/* 红羽 */],
		[probability:37, code:"WS224"/* 暗牙黄泉津 */],
		[probability:37, code:"WT122"/* 暗破 */],
		[probability:37, code:"DA118"/* 米诺陶斯铠 */],
		[probability:37, code:"DA218"/* 撒旦披风 */],
		[probability:37, code:"DS121"/* 凤凰之盾 */],
		[probability:37, code:"OM122"/* 恶魔法珠 */],
		[probability:200, code:"EC105"/* 公会卷轴 */],
		[probability:8, code:"WA122"/* 龙炎斧 */],
		[probability:8, code:"WC122"/* 黑虹爪 */],
		[probability:8, code:"WH123"/* 大鎚伊武岐 */],
		[probability:8, code:"WM123"/* 螺钿三日星 */],
		[probability:8, code:"WP123"/* 卡厄斯 */],
		[probability:8, code:"WS124"/* 天之麻迦古弓 */],
		[probability:8, code:"WS225"/* 32d22w */],
		[probability:8, code:"WT123"/* 天严云 */],
		[probability:8, code:"DA119"/* 死神绝命铠 */],
		[probability:8, code:"DA219"/* 幻彩羽袍 */],
		[probability:8, code:"DS122"/* 奥丁之盾 */],
		[probability:8, code:"OM123"/* 紫金之光 */],
		[probability:20, code:"EC103"/* 回城卷 */],
		[probability:190, code:"BI104"/* 复活卷轴 */],
		[probability:8, code:"OA102"/* 红玉项链 */],
		[probability:8, code:"OR101"/* 情殇 */],
		[probability:8, code:"OA101"/* 爱之石链 */],
		[probability:8, code:"OR102"/* 铜戒指 */],
		[probability:8, code:"OA114"/* 神之庇护 */],
		[probability:8, code:"OS108"/* 虎翼石 */],
		[probability:8, code:"OS109"/* 龙鳞石 */],
		[probability:8, code:"OS110"/* 钻晶石 */]
	],
	drops_more:[
	]
]
