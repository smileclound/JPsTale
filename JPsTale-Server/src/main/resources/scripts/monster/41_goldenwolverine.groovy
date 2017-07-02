monster = [
	ID:'41_goldenwolverine',
	Name:'金狼',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:41,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:1000,
		// Attack Status
		Atk:[30, 44],
		AtkSpeed:8,
		Range:80,
		Rating:800,

		// Defence Status
		Flee:300,
		Absorb:10,
		Block:0,
		DamageStunPers:100,

		// Resistance
		Earth:50,
		Fire:0,
		Ice:30,// Water
		Lighting:30,// Wind
		Poison:50,
	],

	// AI
	AI:[
		Nature:0x80,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:8,
		Real_Sight:420,
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
		Model:'char/monster/MonWolverin/MonWolverin-02.ini',
		Sound:0x000011E0,
	],

	// Drops
	AllSeeItem:false,
	Exp:5000,
	Quantity:1,
	QuestItem:0x07010300,
	drops:[
		[probability:4000, code:"NULL"/* Drops nothing */],
		[probability:3300, code:"GG101", value:[20, 75]/* Gold */],
		[probability:322, code:"PL102"/* 中级恢复生命药水 */],
		[probability:322, code:"PS102"/* 中级恢复耐力药水 */],
		[probability:322, code:"PL103"/* 高级恢复生命药水 */],
		[probability:322, code:"PS103"/* 高级恢复耐力药水 */],
		[probability:322, code:"PL102"/* 中级恢复生命药水 */],
		[probability:28, code:"DA105"/* 鱼鳞甲 */],
		[probability:28, code:"DA205"/* 战斗袍 */],
		[probability:28, code:"WA105"/* 双刃战斧 */],
		[probability:28, code:"WC105"/* 玄电爪 */],
		[probability:28, code:"WH105"/* 破天锤 */],
		[probability:28, code:"WM105"/* 旋风之杖 */],
		[probability:28, code:"WP105"/* 鸠牙战镰 */],
		[probability:28, code:"WS106"/* 长弓 */],
		[probability:28, code:"WS206"/* 阔剑 */],
		[probability:28, code:"WT105"/* 双刺标 */],
		[probability:28, code:"DB104"/* 精铜靴 */],
		[probability:28, code:"DS104"/* 轻盾 */],
		[probability:28, code:"OM104"/* 龙骨念珠 */],
		[probability:28, code:"DG104"/* 银贝护手 */],
		[probability:28, code:"OA204"/* 大力臂环 */],
		[probability:28, code:"OS103"/* 海精石 */],
		[probability:28, code:"OR105"/* 蓝宝石戒指 */],
		[probability:28, code:"OA105"/* 梦之心链 */],
		[probability:28, code:"PL103"/* 高级恢复生命药水 */],
		[probability:28, code:"PS103"/* 高级恢复耐力药水 */],
		[probability:28, code:"EC102"/* 回城卷 */],
		[probability:19, code:"DA106"/* 索子甲 */],
		[probability:19, code:"DA206"/* 精灵袍 */],
		[probability:19, code:"wa106"/* 凯旋 */],
		[probability:19, code:"WC106"/* 狼牙刃 */],
		[probability:19, code:"WH106"/* 浑元金锤 */],
		[probability:19, code:"WM106"/* 抗拒之杖 */],
		[probability:19, code:"WP106"/* 三叉戟 */],
		[probability:19, code:"WS107"/* 射日弓 */],
		[probability:19, code:"WS207"/* 长刀 */],
		[probability:19, code:"WT106"/* 精灵标 */],
		[probability:19, code:"DB105"/* 百兽靴 */],
		[probability:19, code:"DS105"/* 罗塔盾 */],
		[probability:19, code:"OM105"/* 龙额念珠 */],
		[probability:19, code:"DG105"/* 钢指护手 */],
		[probability:19, code:"OA205"/* 龙鳞臂环 */],
		[probability:19, code:"EC102"/* 回城卷 */],
		[probability:19, code:"OR106"/* 红宝石戒指 */],
		[probability:19, code:"OA106"/* 碧云石链 */],
		[probability:5, code:"DA107"/* 全钢胸铠 */],
		[probability:5, code:"DA207"/* 紫电袍 */],
		[probability:5, code:"WA107"/* 刺脊斧 */],
		[probability:5, code:"WC107"/* 平手刃 */],
		[probability:5, code:"WH107"/* 十字锤 */],
		[probability:5, code:"WM107"/* 天魔杖 */],
		[probability:5, code:"WP107"/* 战神之镰 */],
		[probability:5, code:"WS108"/* 巨弩 */],
		[probability:5, code:"WS208"/* 圣殿武士剑 */],
		[probability:5, code:"WT107"/* 天命标 */],
		[probability:5, code:"DB106"/* 黄金靴 */],
		[probability:5, code:"DS106"/* 百炼盾 */],
		[probability:5, code:"OM106"/* 水星 */],
		[probability:5, code:"DG106"/* 白金护手 */],
		[probability:5, code:"OA206"/* 精灵臂环 */],
		[probability:5, code:"EC102"/* 回城卷 */],
		[probability:5, code:"OR107"/* 法师戒指 */],
		[probability:5, code:"OA107"/* 水晶项链 */],
		[probability:2, code:"DA108"/* 精制链铠 */],
		[probability:2, code:"WA108"/* 叼刚 清磐 */],
		[probability:2, code:"WC108"/* 矩记飘 府亥瘤 */],
		[probability:2, code:"WH108"/* 圣光锤 */],
		[probability:2, code:"WM108"/* 公正之杖 */],
		[probability:2, code:"WP108"/* 血烟长矛 */],
		[probability:2, code:"WS109"/* 点金手弩 */],
		[probability:2, code:"WS209"/* 镇妖剑 */],
		[probability:2, code:"WT108"/* 金标 */],
		[probability:2, code:"DB107"/* 冰火靴 */],
		[probability:2, code:"DS107"/* 金刚盾 */],
		[probability:2, code:"OM107"/* 火星 */],
		[probability:2, code:"DG107"/* 百裂护手 */],
		[probability:2, code:"OA207"/* 乌金臂环 */],
		[probability:2, code:"EC102"/* 回城卷 */],
		[probability:2, code:"OR107"/* 法师戒指 */],
		[probability:2, code:"OA107"/* 水晶项链 */]
	],
	drops_more:[
	]
]
