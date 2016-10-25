monster = [
	ID:'110_death_knight',
	Name:'死亡骑士',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:110,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:30000,
		// Attack Status
		Atk:[830, 860],
		AtkSpeed:8,
		Range:150,
		Rating:2800,

		// Defence Status
		Flee:3500,
		Absorb:98,
		Block:65,
		DamageStunPers:25,

		// Resistance
		Earth:200,
		Fire:189,
		Ice:200,// Water
		Lighting:188,// Wind
		Poison:200,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:8,
		Real_Sight:400,
		Talks:[],

		// Move Behavier
		Move_Speed:8,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:25,
		SkillDamage:[920, 970],
		SkillDistance:80,
		SkillRange:280,
		SkillRating:50,
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
		SizeLevel:-1,
		Model:'char/monster/death_knight/death_knight.ini',
		Sound:0x00001680,
	],

	// Drops
	AllSeeItem:false,
	Exp:8000000,
	Quantity:5,
	drops:[
		[probability:4000, code:"NULL"/* Drops nothing */],
		[probability:1097, code:"BI119"/* 吸血鬼之牙(1天) */],
		[probability:1097, code:"BI191"/* 耐力药剂(1天) */],
		[probability:1097, code:"BI112"/* 夺宝卷轴(1天) */],
		[probability:1097, code:"BC123"/* 生命上限药水(1天) */],
		[probability:38, code:"DA123"/* 凤凰圣铠 */],
		[probability:38, code:"DA223"/* 凤凰圣衣 */],
		[probability:38, code:"WA121"/* 苍红斧 */],
		[probability:38, code:"WC121"/* 灭牙爪 */],
		[probability:38, code:"WH122"/* 魁伐折罗 */],
		[probability:38, code:"WM122"/* 流泉月花 */],
		[probability:38, code:"WP122"/* 天之琼侔 */],
		[probability:38, code:"WS123"/* 红羽 */],
		[probability:38, code:"WS224"/* 暗牙黄泉津 */],
		[probability:38, code:"WT122"/* 暗破 */],
		[probability:38, code:"DB121"/* 凤凰战靴 */],
		[probability:38, code:"DS121"/* 凤凰之盾 */],
		[probability:38, code:"OM121"/* 海洋之心 */],
		[probability:38, code:"DG121"/* 凤凰护手 */],
		[probability:38, code:"OA221"/* 凤凰臂环 */],
		[probability:38, code:"OR121"/* 龙誉之戒 */],
		[probability:38, code:"OA121"/* 圣龙之光 */],
		[probability:38, code:"OS112"/* 圣晶石 */],
		[probability:33, code:"DA124"/* 奥丁圣铠 */],
		[probability:33, code:"DA224"/* 奥丁圣衣 */],
		[probability:33, code:"WA122"/* 龙炎斧 */],
		[probability:33, code:"WC122"/* 黑虹爪 */],
		[probability:33, code:"WH123"/* 大鎚伊武岐 */],
		[probability:33, code:"WM123"/* 螺钿三日星 */],
		[probability:33, code:"WP123"/* 卡厄斯 */],
		[probability:33, code:"WS124"/* 天之麻迦古弓 */],
		[probability:33, code:"WS225"/* 32d22w */],
		[probability:33, code:"WT123"/* 天严云 */],
		[probability:33, code:"DB122"/* 奥丁战靴 */],
		[probability:33, code:"DS122"/* 奥丁之盾 */],
		[probability:33, code:"OM122"/* 恶魔法珠 */],
		[probability:33, code:"DG122"/* 奥丁护手 */],
		[probability:33, code:"OA222"/* 奥丁臂环 */],
		[probability:33, code:"OR122"/* 彩晶环 */],
		[probability:33, code:"OA122"/* 彩晶链 */],
		[probability:33, code:"OS113"/* 恶魔石 */],
		[probability:18, code:"DA125"/* 汉兰达铠 */],
		[probability:18, code:"DA225"/* 天使圣衣 */],
		[probability:18, code:"WA123"/* 龙之破坏者 */],
		[probability:18, code:"WC123"/* 红蛛之牙 */],
		[probability:18, code:"WH124"/* 龙王之锤 */],
		[probability:18, code:"WM124"/* 双龙阿修罗 */],
		[probability:18, code:"WP124"/* 烈枪降阎魔 */],
		[probability:18, code:"WS125"/* 潘多拉之弓 */],
		[probability:18, code:"WS226"/* 真龙神啸 */],
		[probability:18, code:"WT124"/* 皓月谙晓 */],
		[probability:18, code:"DB123"/* 圣龙之靴 */],
		[probability:18, code:"DS123"/* 地狱之盾 */],
		[probability:18, code:"OM123"/* 紫金之光 */],
		[probability:18, code:"DG123"/* 圣龙护手 */],
		[probability:18, code:"OA223"/* 圣龙臂环 */],
		[probability:18, code:"OS114"/* 彩晶石 */],
		[probability:5, code:"OR205"/* 火神指环 */],
		[probability:5, code:"SE104"/* 祈祷石 */]
	],
	drops_more:[
	]
]
