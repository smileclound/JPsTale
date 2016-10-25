monster = [
	ID:'38_silverwolverine',
	Name:'银狼',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:38,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:820,
		// Attack Status
		Atk:[29, 40],
		AtkSpeed:8,
		Range:75,
		Rating:600,

		// Defence Status
		Flee:270,
		Absorb:10,
		Block:0,
		DamageStunPers:100,

		// Resistance
		Earth:50,
		Fire:30,
		Ice:0,// Water
		Lighting:30,// Wind
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
		Model:'char/monster/MonWolverin/MonWolverin-03.ini',
		Sound:0x000011E0,
	],

	// Drops
	AllSeeItem:false,
	Exp:4800,
	Quantity:1,
	QuestItem:0x07010100,
	drops:[
		[probability:4300, code:"NULL"/* Drops nothing */],
		[probability:3000, code:"GG101", value:[36, 100]/* Gold */],
		[probability:333, code:"PL102"/* 中级恢复生命药水 */],
		[probability:333, code:"PS102"/* 中级恢复耐力药水 */],
		[probability:333, code:"PL103"/* 高级恢复生命药水 */],
		[probability:333, code:"PS103"/* 高级恢复耐力药水 */],
		[probability:333, code:"PL102"/* 中级恢复生命药水 */],
		[probability:333, code:"PM102"/* 中级恢复魔法药水 */],
		[probability:28, code:"WS105"/* 战弓 */],
		[probability:28, code:"WS205"/* 长剑 */],
		[probability:28, code:"WA105"/* 双刃战斧 */],
		[probability:28, code:"WC105"/* 玄电爪 */],
		[probability:28, code:"WH105"/* 破天锤 */],
		[probability:28, code:"WP105"/* 鸠牙战镰 */],
		[probability:28, code:"PL104"/* 顶级恢复生命药水 */],
		[probability:28, code:"PS104"/* 顶级恢复耐力药水 */],
		[probability:28, code:"PM104"/* 顶级恢复魔法药水 */],
		[probability:28, code:"DA105"/* 鱼鳞甲 */],
		[probability:28, code:"DB105"/* 百兽靴 */],
		[probability:28, code:"DS105"/* 罗塔盾 */],
		[probability:28, code:"DG105"/* 钢指护手 */],
		[probability:28, code:"OA205"/* 龙鳞臂环 */],
		[probability:28, code:"OS103"/* 海精石 */],
		[probability:28, code:"OR105"/* 蓝宝石戒指 */],
		[probability:28, code:"OA105"/* 梦之心链 */],
		[probability:30, code:"DA106"/* 索子甲 */],
		[probability:30, code:"WT104"/* 铁标 */],
		[probability:30, code:"PM102"/* 中级恢复魔法药水 */],
		[probability:30, code:"OR106"/* 红宝石戒指 */],
		[probability:30, code:"OA106"/* 碧云石链 */],
		[probability:5, code:"DA107"/* 全钢胸铠 */],
		[probability:5, code:"WT105"/* 双刺标 */],
		[probability:5, code:"WS106"/* 长弓 */],
		[probability:5, code:"WS206"/* 阔剑 */],
		[probability:5, code:"PM102"/* 中级恢复魔法药水 */],
		[probability:5, code:"OS103"/* 海精石 */],
		[probability:5, code:"OR107"/* 法师戒指 */],
		[probability:5, code:"OA107"/* 水晶项链 */],
		[probability:5, code:"EC102"/* 回城卷 */],
		[probability:1, code:"DA108"/* 精制链铠 */],
		[probability:1, code:"WS107"/* 射日弓 */],
		[probability:1, code:"WS207"/* 长刀 */],
		[probability:1, code:"WT106"/* 精灵标 */],
		[probability:1, code:"DB106"/* 黄金靴 */],
		[probability:1, code:"DS106"/* 百炼盾 */],
		[probability:1, code:"DG106"/* 白金护手 */],
		[probability:1, code:"OA206"/* 精灵臂环 */],
		[probability:1, code:"PM103"/* 高级恢复魔法药水 */],
		[probability:1, code:"EC102"/* 回城卷 */],
		[probability:1, code:"OS104"/* 天仪石 */],
		[probability:1, code:"OR107"/* 法师戒指 */],
		[probability:1, code:"OA108"/* 靛青石链 */]
	],
	drops_more:[
	]
]
