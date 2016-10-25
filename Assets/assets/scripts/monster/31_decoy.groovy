monster = [
	ID:'31_decoy',
	Name:'浮灵',
	Clazz:0,// 1 BOSS
	Brood:0x92,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:31,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:280,
		// Attack Status
		Atk:[23, 30],
		AtkSpeed:8,
		Range:60,
		Rating:450,

		// Defence Status
		Flee:120,
		Absorb:7,
		Block:0,
		DamageStunPers:100,

		// Resistance
		Earth:20,
		Fire:-15,
		Ice:15,// Water
		Lighting:-15,// Wind
		Poison:100,
	],

	// AI
	AI:[
		Nature:0x80,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:7,
		Real_Sight:360,
		Talks:[],

		// Move Behavier
		Move_Speed:4,
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
		ArrowPosi:[-5, 10],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:1,
		Model:'char/monster/decoy/decoy.ini',
		Sound:0x00001160,
	],

	// Drops
	AllSeeItem:false,
	Exp:3200,
	Quantity:1,
	drops:[
		[probability:4600, code:"NULL"/* Drops nothing */],
		[probability:3500, code:"GG101", value:[30, 74]/* Gold */],
		[probability:50, code:"WA105"/* 双刃战斧 */],
		[probability:50, code:"WC105"/* 玄电爪 */],
		[probability:50, code:"WH106"/* 浑元金锤 */],
		[probability:50, code:"WS107"/* 射日弓 */],
		[probability:50, code:"WS207"/* 长刀 */],
		[probability:50, code:"WM106"/* 抗拒之杖 */],
		[probability:50, code:"WP106"/* 三叉戟 */],
		[probability:50, code:"WT106"/* 精灵标 */],
		[probability:50, code:"OM106"/* 水星 */],
		[probability:50, code:"DA105"/* 鱼鳞甲 */],
		[probability:50, code:"DA205"/* 战斗袍 */],
		[probability:50, code:"DB105"/* 百兽靴 */],
		[probability:50, code:"DS105"/* 罗塔盾 */],
		[probability:50, code:"DG105"/* 钢指护手 */],
		[probability:50, code:"OA205"/* 龙鳞臂环 */],
		[probability:50, code:"OR106"/* 红宝石戒指 */],
		[probability:50, code:"OA106"/* 碧云石链 */],
		[probability:50, code:"GP103"/* 浮灵水晶 */],
		[probability:28, code:"wa106"/* 凯旋 */],
		[probability:28, code:"WC106"/* 狼牙刃 */],
		[probability:28, code:"WH107"/* 十字锤 */],
		[probability:28, code:"WS108"/* 巨弩 */],
		[probability:28, code:"WS208"/* 圣殿武士剑 */],
		[probability:28, code:"WM107"/* 天魔杖 */],
		[probability:28, code:"WP107"/* 战神之镰 */],
		[probability:28, code:"WT107"/* 天命标 */],
		[probability:28, code:"OM107"/* 火星 */],
		[probability:28, code:"DA106"/* 索子甲 */],
		[probability:28, code:"DA206"/* 精灵袍 */],
		[probability:28, code:"DB106"/* 黄金靴 */],
		[probability:28, code:"DS106"/* 百炼盾 */],
		[probability:28, code:"DG106"/* 白金护手 */],
		[probability:28, code:"OA206"/* 精灵臂环 */],
		[probability:28, code:"PM101"/* 低级恢复魔法药水 */],
		[probability:28, code:"OS104"/* 天仪石 */],
		[probability:28, code:"OR107"/* 法师戒指 */],
		[probability:28, code:"OA107"/* 水晶项链 */],
		[probability:28, code:"EC102"/* 回城卷 */],
		[probability:28, code:"GP103"/* 浮灵水晶 */],
		[probability:14, code:"WA107"/* 刺脊斧 */],
		[probability:14, code:"WC107"/* 平手刃 */],
		[probability:14, code:"WH108"/* 圣光锤 */],
		[probability:14, code:"WS109"/* 点金手弩 */],
		[probability:14, code:"WS209"/* 镇妖剑 */],
		[probability:14, code:"WM108"/* 公正之杖 */],
		[probability:14, code:"WP108"/* 血烟长矛 */],
		[probability:14, code:"WT108"/* 金标 */],
		[probability:14, code:"OM108"/* 阳炎 */],
		[probability:14, code:"DA107"/* 全钢胸铠 */],
		[probability:14, code:"DA207"/* 紫电袍 */],
		[probability:14, code:"DB107"/* 冰火靴 */],
		[probability:14, code:"DS107"/* 金刚盾 */],
		[probability:14, code:"DG107"/* 百裂护手 */],
		[probability:14, code:"OA207"/* 乌金臂环 */],
		[probability:14, code:"PM102"/* 中级恢复魔法药水 */],
		[probability:14, code:"OS105"/* 冰晶石 */],
		[probability:14, code:"OR108"/* 翡翠戒指 */],
		[probability:14, code:"OA108"/* 靛青石链 */],
		[probability:14, code:"EC102"/* 回城卷 */],
		[probability:14, code:"GP104"/* 刀斧手水晶 */],
		[probability:50, code:"GP109"/* 神秘水晶 */],
		[probability:50, code:"OS105"/* 冰晶石 */]
	],
	drops_more:[
	]
]
