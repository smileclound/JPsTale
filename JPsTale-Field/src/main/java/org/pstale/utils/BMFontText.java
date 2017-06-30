package org.pstale.utils;

import org.pstale.entity.field.Field;
import org.pstale.loader.FieldLoader;

/**
 * 生成BMFont所需的所有字符
 * 
 * @author yanmaoyuan
 * 
 */
public class BMFontText {

    static String[] texts = { "载入", "地图", "解析", "网格线", "显示隐藏", "所有", "列表", "区域", "音乐", "音量", "音效", "背景", "传送门", "门户",
            "刷怪点", "进度", "百分比", "坐标系", "打开关闭", "环境", "摄像机", "移动", "速度", "跳转", "正在", "完毕", "成功", "失败", "碰撞检测", };

    static String[] npcs = { "杂货店", "艾琳", "危险收集者", "杂货店", "杂货店", "杂货店", "杂货店", "艾德斯", "沃克", "祝福城守卫", "大桥守卫", "铜狼",
            "公会管理员", "水晶女孩", "水晶男孩", "沙漠法师", "沙漠难民", "沙漠武器店", "沙漠守卫", "吉萨神殿守卫", "沙漠杂货店", "二级爵士德克", "沙漠守卫", "捐献女孩",
            "地牢守卫", "杂货店", "力量大师", "狮面魔王", "兑换商店", "金狼", "活动女孩", "豪森安", "猎人", "装备发放员", "简", "新手向导", "湖泊守卫", "米特尔",
            "冰封矿洞杂货店", "祝福传送员", "洞穴守卫", "莉莉丝", "居民米诺", "内维斯克导游", "铁匠默菲", "杂货店", "魔法商店", "幸运的独角兽", "阿卡达斯", "莫利奶奶", "纽特",
            "居民", "菲尔拉导游", "菲尔拉导游", "铁匠巴特兹", "铁匠德弗里安", "菲尔拉守卫", "菲尔拉守卫", "菲尔拉守卫", "神秘的巴云", "魔法商店", "魔法导师", "任务商店",
            "石头商店", "杂货店", "仓库管理员", "卡片魔兽兵", "魔法师雷蒙", "公会管理员", "铁匠古登", "铁匠格斯", "里查登守卫", "里查登守卫", "里查登守卫", "合成大师",
            "技能导师", "任务商店", "石头商店", "杂货店", "精灵中国仓库管理员", "镶嵌布玛熊", "流浪者罗斯", "铁匠鲁加", "打孔魔兽兵", "魔法商店", "杂货店", "萨拉娜", "银狼",
            "珊蒂", "火之精灵卡莎", "水之精灵艾丽尔", "风之精灵赛尔菲", "地之精灵诺雅丝", "秘书卡丽纳", "SOD布玛熊", "SOD魔兽兵", "佩罗娜", "殿堂法师", "洞穴守卫", "卡迪",
            "传送点发明人", "力量大师", "幽拉大陆守卫", "幽拉大陆守卫", "铁路守卫", "杂货店", "幽拉仓库管理员", };

    static String[] monsters = { "圣诞精灵", "卡奥斯", "龙鹰灵兽", "冰风魔王", "邪恶火灵", "亚特兰斯巨人", "地狱猎犬", "铁拳", "鲁图怪", "命运女神", "神弓魔",
            "暗月法师", "破日魔王", "嗜血骑士", "红岭蛛王", "暗凰冤魂", "坦普守护者", "箭神", "女武神", "圣殿武士", "剑圣", "黑魔导师", "机甲武士", "皇家骑士", "圣灵法师",
            "绿豆糕", "圣诞精灵(小)", "死亡骑士", "狮面魔王", "无息魔王", "凯尔维苏", "无息兽王", "命运之主", "米诺陶斯", "鲁图王", "狮面魔王(改)", " 狮面魔王(极)",
            "怪物箱子", "火精灵", "狮面魔王(真)", "暗夜死灵", "尼肯", "吸血蝙蝠", "魔法魔兽兵", "寒冰树", "寒冰恶魔", "卡卡噢", "矿山机械", "矿山晶石", "冰甲毒蛛", "奥兹",
            "纳兹", "恶魔伯爵", "矿山管理者", "狂暴比利", "地狱犬", "海龙", "雷娜", "狂暴雷娜", "吊死鬼", "狂暴吊死鬼", "巨斧守卫", "火眼魔", "幽灵之主", "霸天龟王",
            "矿山开采者", "死亡巫师", "狂暴死亡巫师", "斯普林", "双足虫", "变异植物", "双子恶魔", "技师侵略者", "死神", "研究所技师", "死亡枫树", "研究所工程师", "渗透侵略者",
            "氙谭", "爆破侵略者", "比利", "研究所装甲兵", "精兵侵略者", "埃克斯", "魔兽兵", "巨魔兽", "变异兔妖", "树根怪", "红木精", "绿木精", "绿木精怪", "红木精怪",
            "青骷髅", "红骷髅", "僵尸", "独眼蜈蚣", "蜂巢怪", "青精灵", "蓝豆糕", "蝎兽", "恶梦树", "梦魇树", "魔斧怪", "魔锤兵", "入地龙", "圣诞精灵", "浮灵",
            "盗贼", "独眼魔人", "大头蜘蛛", "重甲蝎兽", "布玛熊", "铜狼", "骷髅射手", "地精", "银狼", "刀斧手", "金狼", "魔兽怪", "骷髅游骑兵", "独眼魔神", "小绿人",
            "骷髅战士", "独眼蝙蝠", "小独角兽", "独角兽", "兔妖", "红兔妖", "蜗牛怪", "巨蜥怪", "独角守护者", "大圣诞精灵", "蜜蜂精", "松饼怪", "泰坦巨人", "泥妖",
            "骷髅爵士", "沙妖", "魔剑人马", "泥潭守护者", "黑暗枫树", "魔剑士", "蹦蹦鸡", "红独角兽", "土妖", "红蘑菇精", "蘑菇精", "透明妖", "暗夜骑士", "黑暗魔树",
            "变种蜗牛", "火灵神", "雪狼", "巨塔石人", "魔枪人马", "蜂王", "荆棘爬行者", "木乃伊", "黑暗骑士", "魔剑圣", "魔战妖王", "梦魇", "魔弓手", "黑暗爵士",
            "火灵王", "夜行者", "穿山甲", "啪啪鸡", "魔锤人马", "巨大圣诞精灵", "终极机械", "女巫", "铁甲巨蜥", "重装魔兽兵", "三角魔神", "小无双石怪", "变异甲虫",
            "无双石怪", "无双石魔", "独眼甲虫", "达克", "绿巨人", "萨酷巴斯", "毒眼飞蛾", "双头独眼", "魔弓女王", "木乃伊旗主", "鬼影杀手", "幽灵", "路灯怪", "西瓜太郎",
            "无息独角兽", "守护圣徒", "嗜血屠狼", "魔法导师", "技能导师", "铁甲狂魔", "魔弓人马", "英酷巴斯", "双刀娜迦", "笨笨熊", "人马女皇", "链锤傀儡", "神秘十五",
            "狂暴战士", "杀戮机械", "暴跳怪", "机械魔蚊", "蜘蛛女皇", "幽灵之主", "台风", "嗜血蜂", "嗜血机械", "独角兽王", "鬼影魔神", "巨斧萨满", "远古守卫", "异形",
            "神秘尖晶石", "机械刑天", "霸天蛛", "巨锤魔", "魂魄", "草帽怪", "鬼魂", "火眼怪", "魔鬼", "冷眼魔", "远古祭司", "冰妖", "神秘十八", "冰甲魔兽兵", "寒霜魔",
            "冰石人", "冰甲独眼", "霸天龟", "冰风女妖", "冰甲无双", "火烈树", "眼怪魔", "超级黑暗爵士", "瓦尔哈拉之塔", "超级地精", "超级鲁图怪王", "巴别塔", "巴别塔(改)",
            "巴别塔(真)", "超级变异兔子", "超级青精灵", "超级蓝豆糕", "超级木精怪", "超级盗尸贼", "超级刀斧手", "超级独眼魔人", "超级魔兽怪", "超级骷髅游骑兵", "超级独眼魔神",
            "超级独角兽", "超级泰坦巨人", "超级骷髅骑士", "超级魔剑士", "超级魔斗士", "超级魔剑圣", "超级魔战妖王", "超级火灵王", "超级无双石怪", "超级嗜血屠狼", "超级独角兽王",
            "超级幽灵", "超级远古祭司", "城门", "守护水晶", "邪恶火灵", "烈火麒麟", "莫利狼奶奶", "精灵鼠小弟", "夜魔族骑士★☆", "夜魔族骑士★★", "夜魔族骑士★★☆",
            "夜魔族骑士★★★", "夜魔族骑士★★★☆", "夜魔族骑士★★★★", "卫兵", "警卫兵", "禁卫兵", "护卫兵", "冰水晶塔", "雷水晶塔", "水晶塔", "火水晶塔", "锤子巨魔兽",
            "锤子树", "锤子浮灵", };

    static String[] items = { "阿塔纳西娅", "必杀卷轴", "躲闪卷轴", "全愈卷轴", "复活卷轴", "水晶塔之印", "水晶塔之印", "水晶塔之印", "攻击纹章", "攻击纹章",
            "攻击纹章", "攻击纹章", "攻击纹章", "攻击纹章", "攻击纹章", "攻击纹章", "阿卡西亚斯", "攻击纹章", "攻击纹章", "生命上限药水(1小时)", "生命上限药水(3小时)",
            "生命上限药水(1天)", "魔法上限药水(1小时)", "魔法上限药水(3小时)", "魔法上限药水(1天)", "耐力上限药水(1小时)", "耐力上限药水(3小时)", "耐力上限药水(1天)",
            "紫色药水(3小时)", "魔法上限药水(1小时)", "耐力上限药水(1小时)", "哈瓦那之光", "卫队", "蓝水晶", "红水晶", "绿水晶", "复活卷轴", "永恒生命", "暴怒卷轴",
            "闪避卷轴", "传送卷轴", "大头药水", "锻造紫水晶", "锻造守护石", "夺宝卷轴(1天)", "经验药水(1天)", "夺宝卷轴(7天)", "经验药水(7天)", "A型染发剂", "B型染发剂",
            "C型染发剂", "吸血鬼之牙(1天)", "吸血鬼之牙(7天)", "魔法药剂(1天)", "魔法药剂(7天)", "烟花", "中级经验药水(1天)", "中级经验药水(7天)", "魔法圣水(1天)",
            "魔法圣水(7天)", "锻造熟练(武器)", "锻造熟练(盾)", "锻造熟练(衣服)", "技能之石(初级)", "技能之石(中级)", "技能之石(高级)", "高级经验药水(1天)",
            "高级经验药水(7天)", "力量水晶", "精神水晶", "才能水晶", "敏捷水晶", "体质水晶", "火龙(30天)", "冰龙(30天)", "雷龙(30天)", "愈龙(30天)", "锻造等级石",
            "锻造熟练石(臂环)", "锻造熟练石(护手)", "锻造熟练石(鞋子)", "火龙(7天)", "冰龙(7天)", "雷龙(7天)", "愈龙(7天)", "夺宝卷轴(1小时)", "经验药水(1小时)",
            "吸血之牙(1小时)", "魔法药剂(1小时)", "魔法圣水(1小时)", "负重提升药水", "锻造水晶", "耐力药剂(1天)", "耐力药剂(7天)", "合成回复石", "巴别塔之角",
            "耐力药剂(1天)", "耐力药剂(7天)", "快乐药水", "爱情药水", "悲伤药水", "兴奋药水", "转职之羽", "铜质宝箱(3小时)", "铜质宝箱(1天)", "铜质宝箱(7天)",
            "铜质宝箱(30天)", "银质宝箱(3小时)", "银质宝箱(1天)", "银质宝箱(7天)", "银质宝箱(30天)", "金质宝箱(3小时)", "金质宝箱(1天)", "金质宝箱(7天)",
            "金质宝箱(30天)", "D型染发剂", "E型染发剂", "F型染发剂", "G型染发剂", "H型染发剂", "I型染发剂", "J型染发剂", "K型染发剂", "L型染发剂", "M型染发剂",
            "精英药水(3小时)", "精英药水(1天)", "精英药水(7天)", "铜箱子(3小时)", "铜箱子(1天)", "铜箱子(7天)", "银箱子(3小时)", "银箱子(1天)", "银箱子(7天)",
            "金箱子(3小时)", "金箱子(1天)", "金箱子(7天)", "精英箱子(3小时)", "精英箱子(1天)", "精英箱子(7天)", "耐力药剂(1小时)", "巴别塔之翼1级(3小时)",
            "巴别塔之翼2级(3小时)", "巴别塔之翼3级(3小时)", "巴别塔之翼4级(3小时)", "绿色箱子(3小时)", "绿色箱子(1天)", "绿色箱子(7天)", "铜钥匙(7天)", "银钥匙(7天)",
            "金钥匙(7天)", "蓝钥匙(7天)", "铜钥匙(30天)", "银钥匙(30天)", "金钥匙(30天)", "蓝钥匙(30天)", "负重药水(30天)", "蛋糕", "魔法圣水(3小时)",
            "耐力药剂(3小时)", "南瓜饼", "高级蛋糕", "魔咒", "打孔石", "力量打孔石", "精神打孔石", "才能打孔石", "敏捷打孔石", "体质打孔石", "摘除工具", "修复工具", "蛋糕",
            "绿色锻造石", "蓝色锻造石", "紫色锻造石", "经验水70%(1天)", "经验水70%(7天)", "鬼之力", "白色骷髅", "经验胶囊", "经验胶囊", "经验胶囊", "专职胶囊",
            "天鹅铠男(7天)", "天鹅铠男(30天)", "天鹅铠女(7天)", "天鹅铠女(30天)", "飞鹰铠男(7天)", "飞鹰铠男(30天)", "飞鹰铠女(7天)", "飞鹰铠女(30天)",
            "格罗夫铠男(7天)", "格罗夫铠男(30天)", "格罗夫铠女(7天)", "格罗夫铠女(30天)", "格罗夫铠女A(30天)", "天鹅袍男(7天)", "天鹅袍男(30天)", "天鹅袍女(7天)",
            "天鹅袍女(30天)", "飞鹰袍男(7天)", "飞鹰袍男(30天)", "飞鹰袍女(7天)", "飞鹰袍女(30天)", "格罗夫袍男(7天)", "格罗夫袍男(30天)", "格罗夫袍女(7天)",
            "格罗夫袍女(30天)", "哈迪斯男铠", "哈迪斯女铠", "克洛诺斯男铠", "克洛诺斯男铠", "克洛诺斯女铠", "克洛诺斯女铠", "至尊男铠", "至尊女铠", "测试衣服A", "测试衣服A",
            "测试衣服A", "测试衣服A", "测试衣服A", "测试衣服A", "测试衣服A", "测试衣服A", "布衣", "战斗服", "皮甲", "铠甲", "鱼鳞甲", "索子甲", "全钢胸铠", "精制链铠",
            "黄铜战铠", "百裂铠", "重装机铠", "战神宝铠", "虎刹魔铠", "星晨宝铠", "泰坦战铠", "暗黑铠", "远古圣铠", "米诺陶斯铠", "死神绝命铠", "炎龙圣铠", "魔龙圣铠",
            "炫金圣铠", "凤凰圣铠", "奥丁圣铠", "汉兰达铠", "暗奥丁圣铠", "卡洛斯铠", "阿波罗铠", "瑶光星战铠", "骑士装男(7天)", "骑士装男(30天)", "优雅装女(7天)",
            "优雅装女(30天)", "禁卫铠甲(7天)", "禁卫铠甲(30天)", "禁卫铠甲(7天)", "禁卫铠甲(30天)", "古朴装男(7天)", "古朴装男(30天)", "古朴装女(7天)",
            "古朴装女(30天)", "火龙战铠男(7天)", "火龙战铠男(30天)", "火龙战铠女(7天)", "火龙战铠女(30天)", "暗黑铠甲A", "暗黑铠甲", "暗黑铠甲", "沙滩服男(30天)",
            "游泳服女(30天)", "礼服男(30天)", "礼服女(30天)", "奇异装男(30天)", "古代装女(30天)", "帕克装(30天)", "玛琪装(30天)", "圣诞装男", "圣诞装女",
            "男士韩服", "女士韩服", "露西汗铠", "诺克斯铠", "阿米尔汗铠", "汗巴铠", "比尔汗铠", "比尔汗铠c", "比尔汗铠0", "比尔汗铠A", "布袍", "袍", "长袍", "常青袍",
            "战斗袍", "精灵袍", "紫电袍", "圣女袍", "学徒披风", "信徒披风", "大法师袍", "红莲战袍", "幽绿之眼", "绯红之眼", "文章法袍", "祝福法袍", "天使法袍", "撒旦披风",
            "幻彩羽袍", "修罗圣衣", "涅磐圣衣", "雅典娜圣衣", "凤凰圣衣", "奥丁圣衣", "天使圣衣", "暗奥丁圣衣", "芙蕾雅圣衣", "阿波罗圣衣", "瑶光星战袍", "骑士装男(7天)",
            "骑士装男(30天)", "优雅装女(7天)", "优雅装女(30天)", "法神之袍(7天)", "法神之袍(30天)", "青霞长袍(7天)", "青霞长袍(30天)", "古朴装男(7天)",
            "古朴装男(30天)", "古朴装女(7天)", "古朴装女(30天)", "黑魔法袍男(7天)", "黑魔法袍男(30天)", "黑魔法袍女(7天)", "黑魔法袍女(30天)", "暗黑法袍男",
            "暗黑法袍女", "沙滩服男(30天)", "游泳服女(30天)", "礼服男(30天)", "礼服女(30天)", "奇异装男(30天)", "古代装女(30天)", "帕克装(30天)", "玛琪装(30天)",
            "圣诞装男", "圣诞装女", "男士韩服", "女士韩服", "露西汗袍", "诺克斯袍", "阿米尔汗袍", "汗巴袍", "比尔汗袍", "皮靴", "精灵靴", "精钢靴", "精铜靴", "百兽靴",
            "黄金靴", "冰火靴", "百战靴", "大地靴", "地火战靴", "圣靴", "破棘之靴", "遁地靴", "鹏翅之靴", "时空之靴", "赤龙战靴", "烈焰靴", "符文之靴", "死神战靴",
            "炫金战靴", "凤凰战靴", "奥丁战靴", "圣龙之靴", "芙蕾雅之靴", "阿波罗之靴", "科巴莫靴", "手套", "拳套", "铁拳套", "银贝护手", "钢指护手", "白金护手", "百裂护手",
            "大地护手", "神力护手", "火云护手", "黄铜护手", "巨灵护手", "鲲鹏护手", "金刚护手", "赤龙护手", "星辰护手", "炫钻护手", "泰坦护手", "炫彩护手", "炫金护手",
            "凤凰护手", "奥丁护手", "圣龙护手", "芙蕾雅护手", "阿波罗护手", "玄武卷轴A", "玄武卷轴B", "玄武卷轴C", "玄武卷轴D", "玄武卷轴E", "玄武卷轴F", "玄武卷轴G",
            "玄武卷轴H", "玄武卷轴I", "玄武卷轴J", "玄武卷轴K", "玄武卷轴(F)", "玄武卷轴(G)", "玄武卷轴玄武卷轴(H)", "木盾", "钉盾", "圆盾", "轻盾", "罗塔盾",
            "百炼盾", "金刚盾", "赤龙焰盾", "圣盾", "宙斯盾", "苍穹之盾", "暗黑盾", "龙纹盾", "泰坦之盾", "亢龙之盾", "远古之盾", "米诺陶斯盾", "死神之盾", "蛮狮之盾",
            "炫金之盾", "凤凰之盾", "奥丁之盾", "地狱之盾", "克罗诺斯之盾", "阿波罗之盾", "摇光星", "开阳星", "回城卷", "回城卷", "回城卷", "回城卷", "公会卷轴", "公会卷轴",
            "移动卷轴", "星遗之力", "流云之力", "海精之力", "天仪之力", "冰晶之力", "玄风之力", "水晶之力", "虎翼之力", "龙鳞之力", "钻晶之力", "龙睛之力", "圣晶之力",
            "恶魔之力", "荣誉之力", "蓝晶之力", "星遗之力(魔法)", "流云之力(魔法)", "海精之力(魔法)", "天仪之力(魔法)", "冰晶之力(魔法)", "玄风之力(魔法)", "水晶之力(魔法)",
            "虎翼之力(魔法)", "龙鳞之力(魔法)", "钻晶之力(魔法)", "龙睛之力(魔法)", "圣晶之力(魔法)", "恶魔之力(魔法)", "荣誉之力(魔法)", "塔队", "塔队", "塔队", "塔队",
            "塔队", "塔队", "星星礼物", "巴别塔之角", "9个护身符", "尾巴护身符", "许愿粉末", "祈福粉末", "独角兽水晶", "魔兽兵水晶", "浮灵水晶", "刀斧手水晶", "魔剑士水晶",
            "火灵王水晶", "独角兽王水晶", "绿巨人水晶", "神秘水晶", "守护圣徒水晶", "大头蜘蛛水晶", "鬼影魔神水晶", "铁甲狂魔水晶", "祝福卫兵水晶", "祝福骑士水晶", "绿色神秘水晶",
            "红色神秘水晶", "黄色神秘水晶", "橙色神秘水晶", "紫色神秘水晶", "守护水晶", "暗黑神秘水晶", "深蓝神秘水晶", "冰蓝神秘水晶", "水晶树", "瓶子", "蜂蜜", "神秘之油",
            "黄铜项链", "红玉项链", "多情环", "金项链", "梦之心链", "碧云石链", "水晶项链", "靛青石链", "海蓝石链", "镇魂铃", "圣者之链", "魔龙之心", "生命之链", "神之庇护",
            "暗印护符", "苍穹之链", "天眼护符", "圣光勋章", "逆天纹章", "九转护符", "圣龙之光", "天使之链", "王者之链", "记忆之链", "凯尔维苏项链", "凯拉尔项链", "芙蕾娜项链",
            "皮制臂环", "精铁臂环", "倒刃臂环", "大力臂环", "龙鳞臂环", "精灵臂环", "乌金臂环", "百炼臂环", "飞翼臂环", "百川流水臂环", "玄铁臂环", "紫焰臂环", "璇彩臂环",
            "金刚臂环", "赤龙臂环", "鎏金臂环", "炫钻臂环", "泰坦臂环", "兽神臂环", "炫金臂环", "凤凰臂环", "奥丁臂环", "圣龙臂环", "芙蕾雅臂环", "阿波罗臂环", "念珠",
            "水晶球", "淬角水晶", "龙骨念珠", "龙额念珠", "水星", "火星", "阳炎", "暗月", "蓝色星辰", "淬火乌晶", "菱晶石", "西法路", "堕天", "炫彩水晶", "龙之护身",
            "绿釉之眼", "黑魔", "赤魔之心", "炫钻水晶", "海洋之心", "恶魔法珠", "紫金之光", "圣龙之光", "芙蕾雅法珠", "阿波罗法珠", "铁戒指", "铜戒指", "金戒指", "玉戒指",
            "蓝宝石戒指", "红宝石戒指", "法师戒指", "翡翠戒指", "黑暗之戒", "伏魔戒指", "封印之戒", "王者戒指", "灵魂之戒", "帝王之戒", "守护之戒", "雅典娜之吻", "封魔之戒",
            "封神之戒", "轮回之戒", "涅磐之戒", "龙誉之戒", "诸神之戒", "芙蕾之戒", "阿波罗之戒", "贤者环", "君主环", "中心环", "火神指环", "水神指环", "破坏之环", "星遗石",
            "流云石", "海精石", "天仪石", "冰晶石", "玄风石", "水晶石", "虎翼石", "龙鳞石", "钻晶石", "龙睛石", "圣晶石", "恶魔石", "荣誉石", "蓝晶石", "炽天使之泪",
            "幽蓝水晶", "寒霜石", "星遗石(魔法)", "流云石(魔法)", "海精石(魔法)", "天仪石(魔法)", "冰晶石(魔法)", "玄风石(魔法)", "水晶石(魔法)", "虎翼石(魔法)",
            "龙鳞石(魔法)", "钻晶石(魔法)", "龙睛石(魔法)", "圣晶石(魔法)", "恶魔石(魔法)", "荣誉石(魔法)", "魔鬼石", "王者石", "低级复生命药水", "中级恢复生命药水",
            "高级恢复生命药水", "顶级恢复生命药水", "低级恢复魔法药水", "中级恢复魔法药水", "高级恢复魔法药水", "顶级恢复魔法药水", "紫色矿石", "银色矿石", "金色矿石", "天蓝矿石",
            "海蓝矿石", "橙色矿石", "红色矿石", "绿色矿石", "紫色水晶", "银色水晶", "金色水晶", "天蓝水晶", "海蓝水晶", "橙色水晶", "红色水晶", "绿色水晶", "雪银水晶",
            "雪花水晶", "滴泪水晶", "棕色水晶", "油绿水晶", "暗紫水晶", "紫色魔法石(A)", "银色魔法石(A)", "金色魔法石(A)", "天蓝魔法石(A)", "海蓝魔法石(A)",
            "橙色魔法石(A)", "红色魔法石(A)", "绿色魔法石(A)", "雪银魔法石(A)", "雪花魔法石(A)", "滴泪魔法石(A)", "棕色魔法石(A)", "油绿魔法石(A)", "暗紫魔法石(A)",
            "紫色魔法石(B)", "银色魔法石(B)", "金色魔法石(B)", "天蓝魔法石(B)", "海蓝魔法石(B)", "橙色魔法石(B)", "红色魔法石(B)", "绿色魔法石(B)", "雪银魔法石(B)",
            "雪花魔法石(B)", "滴泪魔法石(B)", "棕色魔法石(B)", "油绿魔法石(B)", "暗紫魔法石(B)", "力量之石(小)", "力量之石(中)", "力量之石(大)", "精神之石(小)",
            "精神之石(中)", "精神之石(大)", "才能之石(小)", "才能之石(中)", "才能之石(大)", "敏捷之石(小)", "敏捷之石(中)", "敏捷之石(大)", "体质之石(小)",
            "体质之石(中)", "体质之石(大)", "低级恢复耐力药水", "中级恢复耐力药水", "高级恢复耐力药水", "顶级恢复耐力药水", "拼图碎片 1", "拼图碎片 2", "拼图碎片 3",
            "拼图碎片 4", "拼图碎片 5", "拼图碎片 6", "拼图碎片 7", "拼图碎片 8", "拼图碎片 1", "拼图碎片 2", "拼图碎片 3", "拼图碎片 4", "拼图碎片 5",
            "拼图碎片 6", "拼图碎片 7", "拼图碎片 8", "狼毛", "狼尾", "狼角", "蜂王浆", "生发剂", "神豆", "生命之石", "恶魔的眼泪", "金色戒指", "银色戒指", "青铜戒指",
            "一封介绍信", "金刚项链", "糖块", "奶油蛋糕", "锤子", "水晶碎片", "守护水晶石", "恶魔凭信", "亚特兰斯巨人凭信", "铁拳凭信", "冷眼魔凭信", "霸天龟凭信",
            "地狱猎犬凭信", "银色徽章", "阿波罗徽章", "金属之翼", "银色之翼", "黄金之翼", "大地之翼", "混乱之翼", "迷失之翼", "恶魔猎手", "米诺陶斯斧", "古代复仇者", "寂灭龙爪",
            "巨型弯刀", "谜之剑", "陨星", "轰雷战锤", "鬼魂", "雅典娜之光", "塔坦卡法珠", "卡塞塔法珠", "魔鬼之镰", "灵犀之镰", "薄雾", "凤舞九天", "镀金剑", "创世之剑",
            "水蟒", "龙翔标枪", "炸弹封印", "战锤封印", "冰冻封印", "兔宝宝封印", "幽灵封印", "守护圣徒封印", "+5000 PT UP", "造化石", "天工石", "鬼斧石", "祈祷石",
            "小月饼", "中月饼", "大月饼", "宝箱", "宝箱", "宝箱", "宝箱", "宝箱", "宝箱", "宝箱", "宝箱", "宝箱", "宝箱", "宝箱", "宝箱", "铜条", "银条",
            "金条", "红宝箱", "红宝箱", "红宝箱", "支票(5000)", "支票(10000)", "支票(50000)", "支票(100000)", "支票(500000)", "支票(1000000)",
            "钥匙", "蓝箱子", "蓝箱子", "宝箱", "木质宝箱", "铁质宝箱", "皮质宝箱", "蓝质宝箱", "紫色宝箱", "石斧", "铁斧", "板斧", "劈地斧", "双刃战斧", "喧哗斧",
            "刺脊斧", "空牙", "破山斧", "定神斧", "天阙斧", "奥丁斧", "蝶花霹雳斧", "残月斧", "泰坦斧", "轮回战斧", "远古战斧", "雷神斧", "幽月斧", "米诺陶斯斧",
            "苍红斧", "龙炎斧", "龙之破坏者", "远古之怒", "传说之斧", "阿波罗之斧", "瑶光星战斧", "幽月斧", "苍红斧", "龙炎斧", "暗黑之斧", "暗黑之斧", "暗黑之斧", "爪",
            "虎爪", "鱼镰刺", "蛇牙刺", "玄电爪", "狼牙刃", "平手刃", "黄金双面刃", "兽之斧刃", "九头刺蛇爪", "利维坦", "飞龙爪", "魔星爪", "天狼爪", "逆天爪", "泰坦爪",
            "冰魄爪", "星刺爪", "修罗爪", "寂灭龙爪", "灭牙爪", "黑虹爪", "红蛛之牙", "传奇之爪", "阿波罗之爪", "瑶光星之爪", "修罗爪", "灭牙爪", "黑虹爪", "暗黑之爪",
            "暗黑之爪", "暗黑之爪", "普通匕首", "铁匕首", "猎刀", "弯刀", "双刃灵剑", "钢铁之剑", "混乱之刀", "圣剑", "巨型弯刀", "霜冻之剑", "深邃之剑", "决斗之剑",
            "渴望之剑", "焰型剑", "制式之剑", "拉巴之剑", "神秘之剑", "月长石之剑", "混沌之剑", "掠夺之剑", "谜之剑", "龙之剑", "毁灭狮子之剑", "华丽暗黑之剑", "水晶之剑",
            "阿波罗之刺", "瑶光星双刀", "掠夺之剑", "龙之剑", "毁灭狮子之剑", "掠夺之剑", "龙之剑", "毁灭狮子之剑", "狼牙棒", "星刺锤", "长锤", "巨灵锤", "破天锤",
            "浑元金锤", "十字锤", "圣光锤", "玄星战锤", "轩辕巨锤", "赤冥之锤", "碎星锤", "破日锤", "鬼眼锤", "雷公槌", "轰天锤", "兽神锤", "灭神锤", "弑神", "无畏",
            "轰雷战锤", "魁伐折罗", "大鎚伊武岐", "龙王之锤", "武瓮槌", "传说之锤", "阿波罗之锤", "瑶光星战锤", "无畏", "魁伐折罗", "大鎚伊武岐", "暗黑之锤", "暗黑之锤",
            "暗黑之锤", "翅膀", "沉默之杖", "执着之杖", "暗杀之杖", "涤荡之杖", "旋风之杖", "抗拒之杖", "天魔杖", "公正之杖", "贤者杖", "圣者杖", "王者杖", "审判之杖",
            "魔蜓杖", "混沌之杖", "五彩凤翼", "诸神的黄昏", "神圣之光", "日月同辉", "末日辉煌", "灭绝", "雅典娜之光", "流泉月花", "螺钿三日星", "双龙阿修罗", "芙蕾雅之仗",
            "传说之仗", "阿波罗之仗", "瑶光星法杖", "灭绝", "流泉月花", "螺钿三日星", "暗黑法杖", "暗黑法杖", "暗黑法杖", "暗黑魔杖", "暗黑魔杖", "暗黑魔杖", "护盾法珠",
            "克里克法珠", "图钉法珠", "反向法珠", "晚生法珠", "汪卡拉法珠", "薄荷法珠", "佩斯坎法珠", "塔坦卡法珠", "索肖法珠", "韵尼法珠", "希哈法珠", "卫法珠", "罗安法珠",
            "坎库法珠", "奥瓦法珠", "恩客得法珠", "菲祖拉法珠", "鲁塔法珠", "纳坦法珠", "卡塞塔法珠", "尼亚哈法珠", "阿奇拉法珠", "瓦沁阳法珠", "瓦坎法珠", "阿波罗法珠",
            "瑶光星徽章", "纳坦法珠", "尼亚哈法珠", "阿奇拉法珠", "纳坦法珠", "尼亚哈法珠", "阿奇拉法珠", "精制木杖", "点钢蛇矛", "鹰嘴矛", "方天战戟", "鸠牙战镰", "三叉戟",
            "战神之镰", "血烟长矛", "嗜血魔镰", "白银之枪", "屠龙枪", "傲天枪", "冥河战镰", "龙翼枪", "狂暴之枪", "末日审判", "银河战镰", "月影神矛", "棲羽亚陀",
            "炎枪素戋鸣", "灵犀之镰", "天之琼侔", "卡厄斯", "烈枪降阎魔", "凤凰之矛", "阿波罗之矛", "瑶光星战戟", "炎枪素戋鸣", "天之琼侔", "卡厄斯", "暗黑之枪", "暗黑之枪",
            "暗黑之枪", "风魔卷轴A", "风魔卷轴B", "风魔卷轴C", "风魔卷轴D", "风魔卷轴E", "风魔卷轴F", "风魔卷轴G", "风魔卷轴H", "风魔卷轴I", "风魔卷轴J", "风魔卷轴K",
            "风魔卷轴M", "风魔卷轴N", "风魔卷轴O", "风魔卷轴(F)", "风魔卷轴(G)", "风魔卷轴(H)", "短弓", "羊角弓", "手弩", "十字弩", "战弓", "长弓", "射日弓",
            "巨弩", "点金手弩", "连环弩", "龙骨战弓", "人马之辉", "猛犸巨弩", "爱神之翼", "精灵之翼", "圣灵弓", "破鹫", "风切", "丘比特之弓", "羽裂", "亚罗栖",
            "凤舞九天", "红羽", "天之麻迦古弓", "潘多拉之弓", "兰宝姬", "阿波罗之弓", "瑶光星战弓", "亚罗栖", "红羽", "天之麻迦古弓", "暗黑之弓", "暗黑之弓", "暗黑之弓",
            "神木剑", "蛇行匕首", "断剑", "短剑", "长剑", "阔剑", "长刀", "圣殿武士剑", "镇妖剑", "封魔剑", "斩马刀", "嗜血屠魔剑", "双截刃", "金刚伏魔剑", "诅咒之剑",
            "破军", "鬼切", "天裂", "龙焰", "龙牙", "烈风", "赤焰流星", "创世之剑", "暗牙黄泉津", "龙神梵天", "真龙神啸", "酪氨酸兵剑", "传说之剑", "阿波罗之剑",
            "瑶光星战剑", "赤焰流星", "暗牙黄泉津", "龙神梵天", "暗黑之剑", "暗黑之剑", "暗黑之剑", "标枪", "战标", "长牙标", "铁标", "双刺标", "精灵标", "天命标",
            "金标", "毒牙标", "飞云标", "神标", "鸩尾标", "魔龙标", "追月标", "惊鸿", "裂空", "夜叉", "噬月", "流星", "天妒", "龙翔标枪", "暗破", "天严云",
            "皓月谙晓", "卡洛斯标枪", "阿波罗标枪", "瑶光星战标", "天妒", "暗破", "天严云", "暗黑之标", "暗黑之标", "暗黑之标", };

    public static void main(String[] args) {
        StringBuffer buffer = new StringBuffer();

        // GUI字符
        for (String str : texts) {
            buffer.append(str);
        }
        // 所有怪物名字
        for (String str : monsters) {
            buffer.append(str);
        }
        // 所有NPC名字
        for (String str : npcs) {
            buffer.append(str);
        }
        // 所有装备名字
        for (String str : items) {
            buffer.append(str);
        }

        // 所有地图名字
        Field[] fields = new FieldLoader().load();
        for (Field f : fields) {
            buffer.append(f.getTitle());
        }

        System.out.println(buffer.toString());
    }

}
