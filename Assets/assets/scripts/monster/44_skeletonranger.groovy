monster = [
	ID:'44_skeletonranger',
	Name:'骷髅游骑兵',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:44,
	ActiveHour:0,
	RespawnGroup:[1, 1],

	Attributes:[
		Life:370,
		// Attack Status
		Atk:[29, 34],
		AtkSpeed:8,
		Range:220,
		Rating:800,

		// Defence Status
		Flee:180,
		Absorb:5,
		Block:0,
		DamageStunPers:100,

		// Resistance
		Earth:40,
		Fire:0,
		Ice:10,// Water
		Lighting:-10,// Wind
		Poison:100,
	],

	// AI
	AI:[
		Nature:0x82,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:7,
		Real_Sight:380,
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
		ClassCode:5,
		ArrowPosi:[-3, 30],
		ModelSize:0.0,
		UseEventModel:false,
		SizeLevel:1,
		Model:'char/monster/MonSRazorArcher/MonSRazorArcher.ini',
		Sound:0x000011C0,
	],

	// Drops
	AllSeeItem:false,
	Exp:4900,
	Quantity:1,
	drops:[
		[probability:3200, code:"NULL"/* Drops nothing */],
		[probability:2100, code:"GG101", value:[30, 85]/* Gold */],
		[probability:580, code:"PL102"/* 中级恢复生命药水 */],
		[probability:580, code:"PS102"/* 中级恢复耐力药水 */],
		[probability:580, code:"PL103"/* 高级恢复生命药水 */],
		[probability:580, code:"PS103"/* 高级恢复耐力药水 */],
		[probability:580, code:"PL102"/* 中级恢复生命药水 */],
		[probability:45, code:"DA106"/* 索子甲 */],
		[probability:45, code:"DA206"/* 精灵袍 */],
		[probability:45, code:"WS105"/* 战弓 */],
		[probability:45, code:"WS205"/* 长剑 */],
		[probability:45, code:"WA105"/* 双刃战斧 */],
		[probability:45, code:"WC105"/* 玄电爪 */],
		[probability:45, code:"WH105"/* 破天锤 */],
		[probability:45, code:"WM105"/* 旋风之杖 */],
		[probability:45, code:"WP105"/* 鸠牙战镰 */],
		[probability:45, code:"WT105"/* 双刺标 */],
		[probability:45, code:"DB105"/* 百兽靴 */],
		[probability:45, code:"DS105"/* 罗塔盾 */],
		[probability:45, code:"OM105"/* 龙额念珠 */],
		[probability:45, code:"DG105"/* 钢指护手 */],
		[probability:45, code:"OA205"/* 龙鳞臂环 */],
		[probability:45, code:"PS103"/* 高级恢复耐力药水 */],
		[probability:45, code:"PL103"/* 高级恢复生命药水 */],
		[probability:45, code:"PM101"/* 低级恢复魔法药水 */],
		[probability:45, code:"OR107"/* 法师戒指 */],
		[probability:45, code:"OA107"/* 水晶项链 */],
		[probability:22, code:"DA107"/* 全钢胸铠 */],
		[probability:22, code:"DA207"/* 紫电袍 */],
		[probability:22, code:"wa106"/* 凯旋 */],
		[probability:22, code:"WC106"/* 狼牙刃 */],
		[probability:22, code:"WH107"/* 十字锤 */],
		[probability:22, code:"WM107"/* 天魔杖 */],
		[probability:22, code:"WP107"/* 战神之镰 */],
		[probability:22, code:"WS107"/* 射日弓 */],
		[probability:22, code:"WS208"/* 圣殿武士剑 */],
		[probability:22, code:"WT107"/* 天命标 */],
		[probability:22, code:"DB106"/* 黄金靴 */],
		[probability:22, code:"DS106"/* 百炼盾 */],
		[probability:22, code:"OM106"/* 水星 */],
		[probability:22, code:"DG106"/* 白金护手 */],
		[probability:22, code:"OA206"/* 精灵臂环 */],
		[probability:22, code:"OS104"/* 天仪石 */],
		[probability:22, code:"OR108"/* 翡翠戒指 */],
		[probability:22, code:"OA108"/* 靛青石链 */],
		[probability:21, code:"DA108"/* 精制链铠 */],
		[probability:21, code:"DA208"/* 圣女袍 */],
		[probability:21, code:"WA107"/* 刺脊斧 */],
		[probability:21, code:"WC107"/* 平手刃 */],
		[probability:21, code:"WH108"/* 圣光锤 */],
		[probability:21, code:"WM108"/* 公正之杖 */],
		[probability:21, code:"WP108"/* 血烟长矛 */],
		[probability:21, code:"WS108"/* 巨弩 */],
		[probability:21, code:"WS209"/* 镇妖剑 */],
		[probability:21, code:"WT108"/* 金标 */],
		[probability:21, code:"DB107"/* 冰火靴 */],
		[probability:21, code:"DS107"/* 金刚盾 */],
		[probability:21, code:"OM107"/* 火星 */],
		[probability:21, code:"OR109"/* 黑暗之戒 */],
		[probability:21, code:"OA109"/* 海蓝石链 */],
		[probability:21, code:"GP111"/* 大头蜘蛛水晶 */],
		[probability:10, code:"DA109"/* 黄铜战铠 */],
		[probability:10, code:"DA209"/* 学徒披风 */],
		[probability:10, code:"WA108"/* 叼刚 清磐 */],
		[probability:10, code:"WC108"/* 矩记飘 府亥瘤 */],
		[probability:10, code:"WH109"/* 固萍绢扼捞飘 */],
		[probability:10, code:"WM109"/* 绊胶飘 */],
		[probability:10, code:"WP108"/* 血烟长矛 */],
		[probability:10, code:"WS109"/* 点金手弩 */],
		[probability:10, code:"WS210"/* 敲饭萍逞 家靛 */],
		[probability:10, code:"WT109"/* 酒唱能促 */],
		[probability:10, code:"EC102"/* 回城卷 */],
		[probability:10, code:"PM102"/* 中级恢复魔法药水 */],
		[probability:10, code:"OR110"/* 伏魔戒指 */],
		[probability:10, code:"OA110"/* 镇魂铃 */],
		[probability:10, code:"OS105"/* 冰晶石 */]
	],
	drops_more:[
	]
]
