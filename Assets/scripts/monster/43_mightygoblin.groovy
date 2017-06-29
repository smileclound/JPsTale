monster = [
	ID:'43_mightygoblin',
	Name:'魔兽怪',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:43,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:580,
		// Attack Status
		Atk:[32, 36],
		AtkSpeed:8,
		Range:45,
		Rating:600,

		// Defence Status
		Flee:280,
		Absorb:6,
		Block:17,
		DamageStunPers:100,

		// Resistance
		Earth:10,
		Fire:35,
		Ice:20,// Water
		Lighting:0,// Wind
		Poison:10,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
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
		PotionPercent:15,
		PotionCount:1,
	],

	Looks:[
		ClassCode:57,
		ArrowPosi:[-2, 20],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:1,
		Model:'char/monster/Monmightygoblin/Monmightygoblin.ini',
		Sound:0x00001010,
	],

	// Drops
	AllSeeItem:false,
	Exp:4600,
	Quantity:1,
	drops:[
		[probability:4200, code:"NULL"/* Drops nothing */],
		[probability:3000, code:"GG101", value:[40, 75]/* Gold */],
		[probability:500, code:"PL102"/* 中级恢复生命药水 */],
		[probability:500, code:"PS102"/* 中级恢复耐力药水 */],
		[probability:500, code:"PM102"/* 中级恢复魔法药水 */],
		[probability:42, code:"DA106"/* 索子甲 */],
		[probability:42, code:"DA206"/* 精灵袍 */],
		[probability:42, code:"wa106"/* 凯旋 */],
		[probability:42, code:"WC106"/* 狼牙刃 */],
		[probability:42, code:"WH106"/* 浑元金锤 */],
		[probability:42, code:"WM106"/* 抗拒之杖 */],
		[probability:42, code:"WP106"/* 三叉戟 */],
		[probability:42, code:"WS107"/* 射日弓 */],
		[probability:42, code:"WS207"/* 长刀 */],
		[probability:42, code:"WT106"/* 精灵标 */],
		[probability:42, code:"DB105"/* 百兽靴 */],
		[probability:42, code:"DS105"/* 罗塔盾 */],
		[probability:42, code:"OM105"/* 龙额念珠 */],
		[probability:42, code:"DG105"/* 钢指护手 */],
		[probability:42, code:"OA205"/* 龙鳞臂环 */],
		[probability:42, code:"EC102"/* 回城卷 */],
		[probability:42, code:"OR106"/* 红宝石戒指 */],
		[probability:42, code:"OA106"/* 碧云石链 */],
		[probability:42, code:"EC102"/* 回城卷 */],
		[probability:42, code:"GP103"/* 浮灵水晶 */],
		[probability:19, code:"DA107"/* 全钢胸铠 */],
		[probability:19, code:"DA207"/* 紫电袍 */],
		[probability:19, code:"WA107"/* 刺脊斧 */],
		[probability:19, code:"WC107"/* 平手刃 */],
		[probability:19, code:"WH107"/* 十字锤 */],
		[probability:19, code:"WM107"/* 天魔杖 */],
		[probability:19, code:"WP107"/* 战神之镰 */],
		[probability:19, code:"WS108"/* 巨弩 */],
		[probability:19, code:"WS208"/* 圣殿武士剑 */],
		[probability:19, code:"WT107"/* 天命标 */],
		[probability:19, code:"DB106"/* 黄金靴 */],
		[probability:19, code:"DS106"/* 百炼盾 */],
		[probability:19, code:"OM106"/* 水星 */],
		[probability:19, code:"DG106"/* 白金护手 */],
		[probability:19, code:"OA206"/* 精灵臂环 */],
		[probability:19, code:"EC102"/* 回城卷 */],
		[probability:19, code:"OS105"/* 冰晶石 */],
		[probability:19, code:"GP104"/* 刀斧手水晶 */],
		[probability:4, code:"DA108"/* 精制链铠 */],
		[probability:4, code:"DA208"/* 圣女袍 */],
		[probability:4, code:"WA108"/* 叼刚 清磐 */],
		[probability:4, code:"WC108"/* 矩记飘 府亥瘤 */],
		[probability:4, code:"WH108"/* 圣光锤 */],
		[probability:4, code:"WM108"/* 公正之杖 */],
		[probability:4, code:"WP108"/* 血烟长矛 */],
		[probability:4, code:"WS109"/* 点金手弩 */],
		[probability:4, code:"WS209"/* 镇妖剑 */],
		[probability:4, code:"WT108"/* 金标 */],
		[probability:4, code:"DB107"/* 冰火靴 */],
		[probability:4, code:"DS107"/* 金刚盾 */],
		[probability:4, code:"OM107"/* 火星 */],
		[probability:4, code:"DG107"/* 百裂护手 */],
		[probability:4, code:"OA207"/* 乌金臂环 */],
		[probability:4, code:"EC102"/* 回城卷 */],
		[probability:4, code:"OS105"/* 冰晶石 */],
		[probability:4, code:"OR107"/* 法师戒指 */],
		[probability:4, code:"OA107"/* 水晶项链 */],
		[probability:4, code:"GP104"/* 刀斧手水晶 */],
		[probability:4, code:"GP110"/* 守护圣徒水晶 */]
	],
	drops_more:[
	]
]
