monster = [
	ID:'37_bronzewolverine',
	Name:'铜狼',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:37,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:700,
		// Attack Status
		Atk:[28, 38],
		AtkSpeed:8,
		Range:75,
		Rating:550,

		// Defence Status
		Flee:250,
		Absorb:10,
		Block:0,
		DamageStunPers:100,

		// Resistance
		Earth:35,
		Fire:30,
		Ice:30,// Water
		Lighting:0,// Wind
		Poison:50,
	],

	// AI
	AI:[
		Nature:0x80,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:7,
		Real_Sight:410,
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
		ArrowPosi:[0, 44],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:-1,
		Model:'char/monster/MonWolverin/MonWolverin-01.ini',
		Sound:0x000011E0,
	],

	// Drops
	AllSeeItem:false,
	Exp:4700,
	Quantity:1,
	QuestItem:0x07010200,
	drops:[
		[probability:4440, code:"NULL"/* Drops nothing */],
		[probability:3200, code:"GG101", value:[40, 95]/* Gold */],
		[probability:266, code:"PL102"/* 中级恢复生命药水 */],
		[probability:266, code:"PS102"/* 中级恢复耐力药水 */],
		[probability:266, code:"PL103"/* 高级恢复生命药水 */],
		[probability:266, code:"PS103"/* 高级恢复耐力药水 */],
		[probability:266, code:"PL102"/* 中级恢复生命药水 */],
		[probability:266, code:"PM102"/* 中级恢复魔法药水 */],
		[probability:32, code:"WA105"/* 双刃战斧 */],
		[probability:32, code:"WC105"/* 玄电爪 */],
		[probability:32, code:"WH105"/* 破天锤 */],
		[probability:32, code:"WM105"/* 旋风之杖 */],
		[probability:32, code:"WP105"/* 鸠牙战镰 */],
		[probability:32, code:"WS105"/* 战弓 */],
		[probability:32, code:"WS205"/* 长剑 */],
		[probability:32, code:"PL104"/* 顶级恢复生命药水 */],
		[probability:32, code:"PS104"/* 顶级恢复耐力药水 */],
		[probability:32, code:"DA105"/* 鱼鳞甲 */],
		[probability:32, code:"DB105"/* 百兽靴 */],
		[probability:32, code:"DS105"/* 罗塔盾 */],
		[probability:32, code:"DG105"/* 钢指护手 */],
		[probability:32, code:"OA205"/* 龙鳞臂环 */],
		[probability:32, code:"OS103"/* 海精石 */],
		[probability:32, code:"OR105"/* 蓝宝石戒指 */],
		[probability:32, code:"OA105"/* 梦之心链 */],
		[probability:15, code:"DA106"/* 索子甲 */],
		[probability:15, code:"DA206"/* 精灵袍 */],
		[probability:15, code:"WT104"/* 铁标 */],
		[probability:15, code:"DS105"/* 罗塔盾 */],
		[probability:15, code:"OM105"/* 龙额念珠 */],
		[probability:15, code:"PM102"/* 中级恢复魔法药水 */],
		[probability:15, code:"OR106"/* 红宝石戒指 */],
		[probability:15, code:"OA106"/* 碧云石链 */],
		[probability:15, code:"EC101"/* 回城卷 */],
		[probability:4, code:"DA107"/* 全钢胸铠 */],
		[probability:4, code:"DA207"/* 紫电袍 */],
		[probability:4, code:"WT106"/* 精灵标 */],
		[probability:4, code:"DS106"/* 百炼盾 */],
		[probability:4, code:"OM106"/* 水星 */],
		[probability:4, code:"WS106"/* 长弓 */],
		[probability:4, code:"WS207"/* 长刀 */],
		[probability:4, code:"PM103"/* 高级恢复魔法药水 */],
		[probability:4, code:"OR107"/* 法师戒指 */],
		[probability:4, code:"OA107"/* 水晶项链 */],
		[probability:1, code:"DA108"/* 精制链铠 */],
		[probability:1, code:"DA208"/* 圣女袍 */],
		[probability:1, code:"WS107"/* 射日弓 */],
		[probability:1, code:"DS107"/* 金刚盾 */],
		[probability:1, code:"OM107"/* 火星 */],
		[probability:1, code:"WS207"/* 长刀 */],
		[probability:1, code:"WT106"/* 精灵标 */],
		[probability:1, code:"EC101"/* 回城卷 */],
		[probability:1, code:"PM104"/* 顶级恢复魔法药水 */],
		[probability:1, code:"OR108"/* 翡翠戒指 */],
		[probability:1, code:"OA108"/* 靛青石链 */]
	],
	drops_more:[
	]
]
