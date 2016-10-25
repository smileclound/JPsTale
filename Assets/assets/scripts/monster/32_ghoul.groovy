monster = [
	ID:'32_ghoul',
	Name:'盗贼',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:32,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:410,
		// Attack Status
		Atk:[22, 26],
		AtkSpeed:7,
		Range:50,
		Rating:500,

		// Defence Status
		Flee:160,
		Absorb:8,
		Block:0,
		DamageStunPers:100,

		// Resistance
		Earth:10,
		Fire:0,
		Ice:40,// Water
		Lighting:-15,// Wind
		Poison:100,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:7,
		Real_Sight:340,
		Talks:[],

		// Move Behavier
		Move_Speed:5,
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
		ArrowPosi:[-3, 30],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:1,
		Model:'char/monster/Monzombi/Monzombi-B.INI',
		Sound:0x00001060,
	],

	// Drops
	AllSeeItem:false,
	Exp:3900,
	Quantity:1,
	drops:[
		[probability:2850, code:"NULL"/* Drops nothing */],
		[probability:3000, code:"GG101", value:[30, 74]/* Gold */],
		[probability:666, code:"PL102"/* 中级恢复生命药水 */],
		[probability:666, code:"PS102"/* 中级恢复耐力药水 */],
		[probability:666, code:"PM102"/* 中级恢复魔法药水 */],
		[probability:52, code:"DA104"/* 铠甲 */],
		[probability:52, code:"DA204"/* 常青袍 */],
		[probability:52, code:"WS106"/* 长弓 */],
		[probability:52, code:"WS206"/* 阔剑 */],
		[probability:52, code:"WA105"/* 双刃战斧 */],
		[probability:52, code:"WC105"/* 玄电爪 */],
		[probability:52, code:"WH106"/* 浑元金锤 */],
		[probability:52, code:"WM106"/* 抗拒之杖 */],
		[probability:52, code:"WP106"/* 三叉戟 */],
		[probability:52, code:"WT106"/* 精灵标 */],
		[probability:52, code:"PL104"/* 顶级恢复生命药水 */],
		[probability:52, code:"PS104"/* 顶级恢复耐力药水 */],
		[probability:52, code:"DA105"/* 鱼鳞甲 */],
		[probability:52, code:"DB105"/* 百兽靴 */],
		[probability:52, code:"DS105"/* 罗塔盾 */],
		[probability:52, code:"DG105"/* 钢指护手 */],
		[probability:52, code:"OA205"/* 龙鳞臂环 */],
		[probability:52, code:"OR105"/* 蓝宝石戒指 */],
		[probability:52, code:"OA105"/* 梦之心链 */],
		[probability:58, code:"DA105"/* 鱼鳞甲 */],
		[probability:58, code:"DA205"/* 战斗袍 */],
		[probability:58, code:"WS107"/* 射日弓 */],
		[probability:58, code:"WS207"/* 长刀 */],
		[probability:58, code:"wa106"/* 凯旋 */],
		[probability:58, code:"WC106"/* 狼牙刃 */],
		[probability:58, code:"WH107"/* 十字锤 */],
		[probability:58, code:"WM107"/* 天魔杖 */],
		[probability:58, code:"WP107"/* 战神之镰 */],
		[probability:58, code:"WT107"/* 天命标 */],
		[probability:58, code:"OR106"/* 红宝石戒指 */],
		[probability:58, code:"OA106"/* 碧云石链 */],
		[probability:27, code:"DA106"/* 索子甲 */],
		[probability:27, code:"DA206"/* 精灵袍 */],
		[probability:27, code:"DB106"/* 黄金靴 */],
		[probability:27, code:"DS106"/* 百炼盾 */],
		[probability:27, code:"DG106"/* 白金护手 */],
		[probability:27, code:"OM106"/* 水星 */],
		[probability:27, code:"OA206"/* 精灵臂环 */],
		[probability:27, code:"PL104"/* 顶级恢复生命药水 */],
		[probability:27, code:"OS104"/* 天仪石 */],
		[probability:27, code:"OR107"/* 法师戒指 */],
		[probability:27, code:"OA107"/* 水晶项链 */],
		[probability:9, code:"DA107"/* 全钢胸铠 */],
		[probability:9, code:"DA207"/* 紫电袍 */],
		[probability:9, code:"DB107"/* 冰火靴 */],
		[probability:9, code:"DS107"/* 金刚盾 */],
		[probability:9, code:"DG107"/* 百裂护手 */],
		[probability:9, code:"OM107"/* 火星 */],
		[probability:9, code:"PL104"/* 顶级恢复生命药水 */],
		[probability:9, code:"DA107"/* 全钢胸铠 */],
		[probability:9, code:"OS105"/* 冰晶石 */],
		[probability:9, code:"EC101"/* 回城卷 */],
		[probability:9, code:"DB107"/* 冰火靴 */],
		[probability:9, code:"DS107"/* 金刚盾 */],
		[probability:9, code:"DG107"/* 百裂护手 */],
		[probability:9, code:"OA207"/* 乌金臂环 */],
		[probability:9, code:"OR108"/* 翡翠戒指 */],
		[probability:9, code:"OA108"/* 靛青石链 */]
	],
	drops_more:[
	]
]
