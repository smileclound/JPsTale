monster = [
	ID:'38_crypt',
	Name:'地精',
	Clazz:0,// 1 BOSS
	Brood:0x92,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:38,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:420,
		// Attack Status
		Atk:[26, 44],
		AtkSpeed:8,
		Range:50,
		Rating:500,

		// Defence Status
		Flee:200,
		Absorb:6,
		Block:0,
		DamageStunPers:100,

		// Resistance
		Earth:15,
		Fire:50,
		Ice:50,// Water
		Lighting:0,// Wind
		Poison:50,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:9,
		Real_Sight:360,
		Talks:[],

		// Move Behavier
		Move_Speed:6,
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
		ClassCode:56,
		ArrowPosi:[0, 14],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:1,
		Model:'char/monster/Monclip/monclip.ini',
		Sound:0x00001140,
	],

	// Drops
	AllSeeItem:false,
	Exp:4300,
	Quantity:1,
	drops:[
		[probability:4400, code:"NULL"/* Drops nothing */],
		[probability:3500, code:"GG101", value:[40, 100]/* Gold */],
		[probability:200, code:"PL102"/* 中级恢复生命药水 */],
		[probability:200, code:"PL103"/* 高级恢复生命药水 */],
		[probability:200, code:"PS103"/* 高级恢复耐力药水 */],
		[probability:200, code:"PL102"/* 中级恢复生命药水 */],
		[probability:200, code:"PM102"/* 中级恢复魔法药水 */],
		[probability:43, code:"DA107"/* 全钢胸铠 */],
		[probability:43, code:"DA207"/* 紫电袍 */],
		[probability:43, code:"WM106"/* 抗拒之杖 */],
		[probability:43, code:"WS106"/* 长弓 */],
		[probability:43, code:"WS206"/* 阔剑 */],
		[probability:43, code:"WT105"/* 双刺标 */],
		[probability:43, code:"PM102"/* 中级恢复魔法药水 */],
		[probability:43, code:"DS105"/* 罗塔盾 */],
		[probability:43, code:"OR107"/* 法师戒指 */],
		[probability:43, code:"OA107"/* 水晶项链 */],
		[probability:43, code:"EC102"/* 回城卷 */],
		[probability:43, code:"GP105"/* 魔剑士水晶 */],
		[probability:43, code:"GP106"/* 火灵王水晶 */],
		[probability:43, code:"GP107"/* 独角兽王水晶 */],
		[probability:43, code:"GP108"/* 绿巨人水晶 */],
		[probability:43, code:"GP111"/* 大头蜘蛛水晶 */],
		[probability:23, code:"DA108"/* 精制链铠 */],
		[probability:23, code:"DA208"/* 圣女袍 */],
		[probability:23, code:"WM107"/* 天魔杖 */],
		[probability:23, code:"WS107"/* 射日弓 */],
		[probability:23, code:"WS207"/* 长刀 */],
		[probability:23, code:"WT106"/* 精灵标 */],
		[probability:23, code:"DB106"/* 黄金靴 */],
		[probability:23, code:"DS106"/* 百炼盾 */],
		[probability:23, code:"DG106"/* 白金护手 */],
		[probability:23, code:"OA206"/* 精灵臂环 */],
		[probability:23, code:"EC102"/* 回城卷 */],
		[probability:23, code:"OR107"/* 法师戒指 */],
		[probability:23, code:"OA107"/* 水晶项链 */],
		[probability:7, code:"DA109"/* 黄铜战铠 */],
		[probability:7, code:"DA208"/* 圣女袍 */],
		[probability:7, code:"WM108"/* 公正之杖 */],
		[probability:7, code:"WA108"/* 叼刚 清磐 */],
		[probability:7, code:"WS208"/* 圣殿武士剑 */],
		[probability:7, code:"WT107"/* 天命标 */],
		[probability:7, code:"DB107"/* 冰火靴 */],
		[probability:7, code:"DS107"/* 金刚盾 */],
		[probability:7, code:"DG107"/* 百裂护手 */],
		[probability:7, code:"OA207"/* 乌金臂环 */],
		[probability:7, code:"EC102"/* 回城卷 */],
		[probability:7, code:"OS105"/* 冰晶石 */],
		[probability:7, code:"OR108"/* 翡翠戒指 */],
		[probability:7, code:"OA108"/* 靛青石链 */]
	],
	drops_more:[
	]
]
