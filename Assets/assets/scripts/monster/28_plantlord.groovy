monster = [
	ID:'28_plantlord',
	Name:'梦魇树',
	Clazz:0,// 1 BOSS
	Brood:0x92,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:28,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:320,
		// Attack Status
		Atk:[15, 23],
		AtkSpeed:7,
		Range:45,
		Rating:500,

		// Defence Status
		Flee:130,
		Absorb:6,
		Block:0,
		DamageStunPers:100,

		// Resistance
		Earth:40,
		Fire:-10,
		Ice:25,// Water
		Lighting:-10,// Wind
		Poison:100,
	],

	// AI
	AI:[
		Nature:0x80,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:5,
		Real_Sight:360,
		Talks:[],

		// Move Behavier
		Move_Speed:1,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:17,
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
		ClassCode:52,
		ArrowPosi:[2, 80],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:3,
		Model:'char/monster/Monplant/MonPlant-b.INI',
		Sound:0x00001040,
	],

	// Drops
	AllSeeItem:false,
	Exp:2600,
	Quantity:1,
	drops:[
		[probability:4000, code:"NULL"/* Drops nothing */],
		[probability:2500, code:"GG101", value:[50, 70]/* Gold */],
		[probability:533, code:"PL102"/* 中级恢复生命药水 */],
		[probability:533, code:"PL102"/* 中级恢复生命药水 */],
		[probability:533, code:"PS102"/* 中级恢复耐力药水 */],
		[probability:66, code:"DA104"/* 铠甲 */],
		[probability:66, code:"DA204"/* 常青袍 */],
		[probability:66, code:"WS105"/* 战弓 */],
		[probability:66, code:"WS205"/* 长剑 */],
		[probability:66, code:"WH104"/* 巨灵锤 */],
		[probability:66, code:"WM104"/* 涤荡之杖 */],
		[probability:66, code:"WP104"/* 方天战戟 */],
		[probability:66, code:"WT104"/* 铁标 */],
		[probability:66, code:"DB104"/* 精铜靴 */],
		[probability:66, code:"DS104"/* 轻盾 */],
		[probability:66, code:"DG104"/* 银贝护手 */],
		[probability:66, code:"OR105"/* 蓝宝石戒指 */],
		[probability:66, code:"OA105"/* 梦之心链 */],
		[probability:66, code:"OA203"/* 倒刃臂环 */],
		[probability:66, code:"EC104"/* 回城卷 */],
		[probability:33, code:"DA105"/* 鱼鳞甲 */],
		[probability:33, code:"DA205"/* 战斗袍 */],
		[probability:33, code:"WS106"/* 长弓 */],
		[probability:33, code:"WS206"/* 阔剑 */],
		[probability:33, code:"WH105"/* 破天锤 */],
		[probability:33, code:"WM105"/* 旋风之杖 */],
		[probability:33, code:"WP105"/* 鸠牙战镰 */],
		[probability:33, code:"WT105"/* 双刺标 */],
		[probability:33, code:"DB105"/* 百兽靴 */],
		[probability:33, code:"DS105"/* 罗塔盾 */],
		[probability:33, code:"DG105"/* 钢指护手 */],
		[probability:33, code:"OS104"/* 天仪石 */],
		[probability:33, code:"OR106"/* 红宝石戒指 */],
		[probability:33, code:"OA106"/* 碧云石链 */],
		[probability:33, code:"GP102"/* 魔兽兵水晶 */],
		[probability:8, code:"DA106"/* 索子甲 */],
		[probability:8, code:"DA206"/* 精灵袍 */],
		[probability:8, code:"WS107"/* 射日弓 */],
		[probability:8, code:"WS207"/* 长刀 */],
		[probability:8, code:"WH106"/* 浑元金锤 */],
		[probability:8, code:"WM106"/* 抗拒之杖 */],
		[probability:8, code:"WP106"/* 三叉戟 */],
		[probability:8, code:"WT106"/* 精灵标 */],
		[probability:8, code:"DB106"/* 黄金靴 */],
		[probability:8, code:"DS106"/* 百炼盾 */],
		[probability:8, code:"DG106"/* 白金护手 */],
		[probability:8, code:"OS105"/* 冰晶石 */],
		[probability:8, code:"PM102"/* 中级恢复魔法药水 */],
		[probability:8, code:"EC102"/* 回城卷 */],
		[probability:8, code:"OR107"/* 法师戒指 */],
		[probability:8, code:"OA107"/* 水晶项链 */],
		[probability:8, code:"GP103"/* 浮灵水晶 */],
		[probability:50, code:"GP109"/* 神秘水晶 */]
	],
	drops_more:[
	]
]
