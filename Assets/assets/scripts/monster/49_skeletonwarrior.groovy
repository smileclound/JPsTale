monster = [
	ID:'49_skeletonwarrior',
	Name:'骷髅战士',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:49,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:670,
		// Attack Status
		Atk:[42, 46],
		AtkSpeed:8,
		Range:55,
		Rating:750,

		// Defence Status
		Flee:320,
		Absorb:13,
		Block:9,
		DamageStunPers:100,

		// Resistance
		Earth:30,
		Fire:20,
		Ice:0,// Water
		Lighting:-10,// Wind
		Poison:100,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:8,
		Real_Sight:350,
		Talks:[],

		// Move Behavier
		Move_Speed:4,
		MoveRange:64,

		// Skill Behavier
		SpAttackPercetage:15,
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
		ClassCode:2,
		ArrowPosi:[-2, 30],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:-1,
		Model:'char/monster/MonSWarrior/MonSWarrior.ini',
		Sound:0x000011D0,
	],

	// Drops
	AllSeeItem:false,
	Exp:5500,
	Quantity:1,
	drops:[
		[probability:2800, code:"NULL"/* Drops nothing */],
		[probability:3000, code:"GG101", value:[70, 100]/* Gold */],
		[probability:500, code:"PL102"/* 中级恢复生命药水 */],
		[probability:500, code:"PS102"/* 中级恢复耐力药水 */],
		[probability:500, code:"PL103"/* 高级恢复生命药水 */],
		[probability:500, code:"PS103"/* 高级恢复耐力药水 */],
		[probability:500, code:"PL103"/* 高级恢复生命药水 */],
		[probability:58, code:"DA106"/* 索子甲 */],
		[probability:58, code:"DA206"/* 精灵袍 */],
		[probability:58, code:"WA105"/* 双刃战斧 */],
		[probability:58, code:"WC105"/* 玄电爪 */],
		[probability:58, code:"WH105"/* 破天锤 */],
		[probability:58, code:"WM105"/* 旋风之杖 */],
		[probability:58, code:"WP105"/* 鸠牙战镰 */],
		[probability:58, code:"WS206"/* 阔剑 */],
		[probability:58, code:"WS105"/* 战弓 */],
		[probability:58, code:"DB105"/* 百兽靴 */],
		[probability:58, code:"DS105"/* 罗塔盾 */],
		[probability:58, code:"OM105"/* 龙额念珠 */],
		[probability:58, code:"DG105"/* 钢指护手 */],
		[probability:58, code:"OA205"/* 龙鳞臂环 */],
		[probability:58, code:"OS103"/* 海精石 */],
		[probability:58, code:"OR107"/* 法师戒指 */],
		[probability:58, code:"OA107"/* 水晶项链 */],
		[probability:25, code:"DA107"/* 全钢胸铠 */],
		[probability:25, code:"DA207"/* 紫电袍 */],
		[probability:25, code:"wa106"/* 凯旋 */],
		[probability:25, code:"WC106"/* 狼牙刃 */],
		[probability:25, code:"WH106"/* 浑元金锤 */],
		[probability:25, code:"WM106"/* 抗拒之杖 */],
		[probability:25, code:"WP106"/* 三叉戟 */],
		[probability:25, code:"WS106"/* 长弓 */],
		[probability:25, code:"WS107"/* 射日弓 */],
		[probability:25, code:"WS207"/* 长刀 */],
		[probability:25, code:"WS208"/* 圣殿武士剑 */],
		[probability:25, code:"WT106"/* 精灵标 */],
		[probability:25, code:"PS104"/* 顶级恢复耐力药水 */],
		[probability:25, code:"PL104"/* 顶级恢复生命药水 */],
		[probability:25, code:"OS104"/* 天仪石 */],
		[probability:25, code:"OR108"/* 翡翠戒指 */],
		[probability:25, code:"OA108"/* 靛青石链 */],
		[probability:25, code:"EC102"/* 回城卷 */],
		[probability:25, code:"OS104"/* 天仪石 */],
		[probability:25, code:"GP111"/* 大头蜘蛛水晶 */],
		[probability:7, code:"DA108"/* 精制链铠 */],
		[probability:7, code:"DA208"/* 圣女袍 */],
		[probability:7, code:"WA107"/* 刺脊斧 */],
		[probability:7, code:"WC107"/* 平手刃 */],
		[probability:7, code:"WH107"/* 十字锤 */],
		[probability:7, code:"WM107"/* 天魔杖 */],
		[probability:7, code:"WP107"/* 战神之镰 */],
		[probability:7, code:"WS107"/* 射日弓 */],
		[probability:7, code:"WS108"/* 巨弩 */],
		[probability:7, code:"WS208"/* 圣殿武士剑 */],
		[probability:7, code:"WT107"/* 天命标 */],
		[probability:7, code:"DB106"/* 黄金靴 */],
		[probability:7, code:"DS106"/* 百炼盾 */],
		[probability:7, code:"DG106"/* 白金护手 */],
		[probability:7, code:"OA206"/* 精灵臂环 */],
		[probability:7, code:"OR109"/* 黑暗之戒 */],
		[probability:7, code:"OA109"/* 海蓝石链 */],
		[probability:7, code:"EC102"/* 回城卷 */],
		[probability:7, code:"OS104"/* 天仪石 */],
		[probability:7, code:"OS105"/* 冰晶石 */],
		[probability:7, code:"GP111"/* 大头蜘蛛水晶 */],
		[probability:2, code:"DA109"/* 黄铜战铠 */],
		[probability:2, code:"DA209"/* 学徒披风 */],
		[probability:2, code:"WA108"/* 叼刚 清磐 */],
		[probability:2, code:"WC108"/* 矩记飘 府亥瘤 */],
		[probability:2, code:"WH108"/* 圣光锤 */],
		[probability:2, code:"WM108"/* 公正之杖 */],
		[probability:2, code:"WP108"/* 血烟长矛 */],
		[probability:2, code:"WS108"/* 巨弩 */],
		[probability:2, code:"WS109"/* 点金手弩 */],
		[probability:2, code:"WS209"/* 镇妖剑 */],
		[probability:2, code:"EC102"/* 回城卷 */],
		[probability:2, code:"DB107"/* 冰火靴 */],
		[probability:2, code:"DS107"/* 金刚盾 */],
		[probability:2, code:"DG107"/* 百裂护手 */],
		[probability:2, code:"OA207"/* 乌金臂环 */],
		[probability:2, code:"OS105"/* 冰晶石 */],
		[probability:2, code:"OS106"/* 玄风石 */]
	],
	drops_more:[
	]
]
