monster = [
	ID:'10_santagoblin-d',
	Name:'圣诞精灵(小)',
	Clazz:0,// 1 BOSS
	Brood:0x00,// 0 Normal; 0x90(144) UNDEAD; 0x91 MUTANT; 0x92 DEMON; 0x93 MECHANIC;
	// Common Status
	Level:10,
	ActiveHour:0,
	RespawnGroup:[0, 0],

	Attributes:[
		Life:300,
		// Attack Status
		Atk:[6, 10],
		AtkSpeed:8,
		Range:50,
		Rating:400,

		// Defence Status
		Flee:70,
		Absorb:45,
		Block:0,
		DamageStunPers:100,

		// Resistance
		Earth:30,
		Fire:20,
		Ice:10,// Water
		Lighting:0,// Wind
		Poison:0,
	],

	// AI
	AI:[
		Nature:0x80,// 0x80 NATURAL; 0x81 GOOD; 0x82 EVIL
		IQ:10,
		Real_Sight:300,
		Talks:[],

		// Move Behavier
		Move_Speed:2,
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
		ArrowPosi:[0, 0],
		ModelSize:0.7,
		UseEventModel:false,
		SizeLevel:0,
		Model:'char/monster/Monhobsanta/Monhobsanta.INI',
		Sound:0x00001010,
	],

	// Drops
	AllSeeItem:false,
	Exp:600,
	Quantity:8,
	drops:[
		[probability:1000, code:"NULL"/* Drops nothing */],
		[probability:4000, code:"GG101", value:[400, 700]/* Gold */],
		[probability:2000, code:"PM101"/* 低级恢复魔法药水 */],
		[probability:107, code:"DA103"/* 皮甲 */],
		[probability:107, code:"DA203"/* 长袍 */],
		[probability:107, code:"DS103"/* 圆盾 */],
		[probability:107, code:"WA103"/* 板斧 */],
		[probability:107, code:"WC103"/* 鱼镰刺 */],
		[probability:107, code:"WM103"/* 暗杀之杖 */],
		[probability:107, code:"WH103"/* 长锤 */],
		[probability:107, code:"WS203"/* 断剑 */],
		[probability:107, code:"WS104"/* 十字弩 */],
		[probability:107, code:"WP103"/* 鹰嘴矛 */],
		[probability:107, code:"WT103"/* 长牙标 */],
		[probability:107, code:"OA203"/* 倒刃臂环 */],
		[probability:107, code:"OM103"/* 淬角水晶 */],
		[probability:107, code:"DB103"/* 精钢靴 */],
		[probability:107, code:"DS103"/* 圆盾 */],
		[probability:107, code:"DG103"/* 铁拳套 */],
		[probability:107, code:"EC101"/* 回城卷 */],
		[probability:107, code:"EC104"/* 回城卷 */],
		[probability:107, code:"OR103"/* 金戒指 */],
		[probability:107, code:"OA103"/* 多情环 */],
		[probability:23, code:"DA104"/* 铠甲 */],
		[probability:23, code:"DA204"/* 常青袍 */],
		[probability:23, code:"DS104"/* 轻盾 */],
		[probability:23, code:"WA104"/* 劈地斧 */],
		[probability:23, code:"WC104"/* 蛇牙刺 */],
		[probability:23, code:"WM104"/* 涤荡之杖 */],
		[probability:23, code:"WH104"/* 巨灵锤 */],
		[probability:23, code:"WS204"/* 短剑 */],
		[probability:23, code:"WS104"/* 十字弩 */],
		[probability:23, code:"WP104"/* 方天战戟 */],
		[probability:23, code:"WT104"/* 铁标 */],
		[probability:23, code:"OA204"/* 大力臂环 */],
		[probability:23, code:"OM104"/* 龙骨念珠 */],
		[probability:23, code:"DB104"/* 精铜靴 */],
		[probability:23, code:"DS104"/* 轻盾 */],
		[probability:23, code:"DG104"/* 银贝护手 */],
		[probability:23, code:"EC101"/* 回城卷 */],
		[probability:23, code:"EC104"/* 回城卷 */],
		[probability:23, code:"OR104"/* 玉戒指 */],
		[probability:23, code:"OA104"/* 金项链 */],
		[probability:23, code:"OS102"/* 流云石 */],
		[probability:14, code:"DA105"/* 鱼鳞甲 */],
		[probability:14, code:"DA205"/* 战斗袍 */],
		[probability:14, code:"DS105"/* 罗塔盾 */],
		[probability:14, code:"WA105"/* 双刃战斧 */],
		[probability:14, code:"WC105"/* 玄电爪 */],
		[probability:14, code:"WM105"/* 旋风之杖 */],
		[probability:14, code:"WH105"/* 破天锤 */],
		[probability:14, code:"WS205"/* 长剑 */],
		[probability:14, code:"WS105"/* 战弓 */],
		[probability:14, code:"WP105"/* 鸠牙战镰 */],
		[probability:14, code:"WT105"/* 双刺标 */],
		[probability:14, code:"OA205"/* 龙鳞臂环 */],
		[probability:14, code:"OM105"/* 龙额念珠 */],
		[probability:14, code:"DB105"/* 百兽靴 */],
		[probability:14, code:"DS105"/* 罗塔盾 */],
		[probability:14, code:"DG105"/* 钢指护手 */],
		[probability:14, code:"EC101"/* 回城卷 */],
		[probability:14, code:"EC104"/* 回城卷 */],
		[probability:14, code:"OR105"/* 蓝宝石戒指 */],
		[probability:14, code:"OA105"/* 梦之心链 */],
		[probability:14, code:"OS103"/* 海精石 */],
		[probability:2, code:"DA106"/* 索子甲 */],
		[probability:2, code:"DA206"/* 精灵袍 */],
		[probability:2, code:"DS106"/* 百炼盾 */],
		[probability:2, code:"wa106"/* 凯旋 */],
		[probability:2, code:"WC106"/* 狼牙刃 */],
		[probability:2, code:"WM106"/* 抗拒之杖 */],
		[probability:2, code:"WH106"/* 浑元金锤 */],
		[probability:2, code:"WS206"/* 阔剑 */],
		[probability:2, code:"WS106"/* 长弓 */],
		[probability:2, code:"WP106"/* 三叉戟 */],
		[probability:2, code:"WT106"/* 精灵标 */],
		[probability:2, code:"OA206"/* 精灵臂环 */],
		[probability:2, code:"OM106"/* 水星 */],
		[probability:2, code:"DB106"/* 黄金靴 */],
		[probability:2, code:"DS106"/* 百炼盾 */],
		[probability:2, code:"DG106"/* 白金护手 */],
		[probability:2, code:"EC101"/* 回城卷 */],
		[probability:2, code:"EC104"/* 回城卷 */],
		[probability:2, code:"OR106"/* 红宝石戒指 */],
		[probability:2, code:"OA106"/* 碧云石链 */],
		[probability:2, code:"OS104"/* 天仪石 */]
	],
	drops_more:[
	]
]
