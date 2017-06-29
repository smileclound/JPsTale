package org.pstale.data.components;

/**
 * 角色音效
 * 
 * @author yanmaoyuan
 *
 */
public class CharSound {

    /**
     * 音乐文件路径 第一个参数对应怪物的文件夹，比如Cyclops
     * 第二个参数对应怪物的动作，如attack、dead、damage、netural、skill 第三个参数对应动作的编号，为数字1、2、3..
     */
    public final static String MONSTER_WAV = "wav/Effects/monster/%s/%s %d.wav";
    public final static String NPC_WAV = "wav/Effects/npc/%s/%s %d.wav";
    public final static String PLAYER_WAV = "wav/Effects/player/%s/%s %d.wav";

    public final static int CHAR_SOUND_FIGHTER = 0x0001;
    public final static int CHAR_SOUND_MECHANICAN = 0x0002;
    public final static int CHAR_SOUND_PIKEMAN = 0x0004;
    public final static int CHAR_SOUND_ARCHER = 0x0003;

    public final static int CHAR_SOUND_KNIGHT = 0x0006;
    public final static int CHAR_SOUND_ATALANTA = 0x0005;
    public final static int CHAR_SOUND_PRIESTESS = 0x0008;
    public final static int CHAR_SOUND_MAGICIAN = 0x0007;

    public final static int CHAR_SOUND_CYCLOPS = 0x1000;
    public final static int CHAR_SOUND_HOBGOBLIN = 0x1010;
    public final static int CHAR_SOUND_IMP = 0x1020;
    public final static int CHAR_SOUND_MINIG = 0x1030;
    public final static int CHAR_SOUND_PLANT = 0x1040;
    public final static int CHAR_SOUND_SKELETON = 0x1050;
    public final static int CHAR_SOUND_ZOMBI = 0x1060;
    public final static int CHAR_SOUND_OBIT = 0x1070;

    public final static int CHAR_SOUND_HOPT = 0x1080;
    public final static int CHAR_SOUND_BARGON = 0x1090;
    public final static int CHAR_SOUND_LEECH = 0x10A0;
    public final static int CHAR_SOUND_MUSHROOM = 0x10B0;

    public final static int CHAR_SOUND_ARMA = 0x10C0;
    public final static int CHAR_SOUND_SCORPION = 0x10D0;

    public final static int CHAR_SOUND_HEADCUTTER = 0x1100;
    public final static int CHAR_SOUND_SANDLEM = 0x1110;
    public final static int CHAR_SOUND_WEB = 0x1120;
    public final static int CHAR_SOUND_HOPYKING = 0x1130;
    public final static int CHAR_SOUND_CRIP = 0x1140;
    public final static int CHAR_SOUND_BUMA = 0x1150;
    public final static int CHAR_SOUND_DECOY = 0x1160;
    public final static int CHAR_SOUND_DORAL = 0x1170;
    public final static int CHAR_SOUND_FIGON = 0x1180;
    public final static int CHAR_SOUND_STONEGIANT = 0x1190;
    public final static int CHAR_SOUND_GREVEN = 0x11A0;
    public final static int CHAR_SOUND_ILLUSIONKNIGHT = 0x11B0;
    public final static int CHAR_SOUND_SKELETONRANGE = 0x11C0;
    public final static int CHAR_SOUND_SKELETONMELEE = 0x11D0;
    public final static int CHAR_SOUND_WOLVERLIN = 0x11E0;

    public final static int CHAR_SOUND_RABIE = 0x1200;
    public final static int CHAR_SOUND_MUDY = 0x1210;
    public final static int CHAR_SOUND_SEN = 0x1220;
    public final static int CHAR_SOUND_EGAN = 0x1230;
    public final static int CHAR_SOUND_BEEDOG = 0x1240;
    public final static int CHAR_SOUND_MUTANTPLANT = 0x1250;
    public final static int CHAR_SOUND_MUTANTRABIE = 0x1260;
    public final static int CHAR_SOUND_MUTANTTREE = 0x1270;
    public final static int CHAR_SOUND_AVELISK = 0x1280;
    public final static int CHAR_SOUND_NAZ = 0x1290;
    public final static int CHAR_SOUND_MUMMY = 0x12A0;
    public final static int CHAR_SOUND_HULK = 0x12B0;
    public final static int CHAR_SOUND_SUCCUBUS = 0x12C0;
    public final static int CHAR_SOUND_DAWLIN = 0x12D0;
    public final static int CHAR_SOUND_SHADOW = 0x12E0;
    public final static int CHAR_SOUND_BERSERKER = 0x12F0;
    public final static int CHAR_SOUND_IRONGUARD = 0x1300;
    public final static int CHAR_SOUND_FURY = 0x1310;
    public final static int CHAR_SOUND_SLIVER = 0x1320;
    public final static int CHAR_SOUND_HUNGKY = 0x1330;

    public final static int CHAR_SOUND_RATOO = 0x1340;
    public final static int CHAR_SOUND_STYGIANLORD = 0x1350;
    public final static int CHAR_SOUND_OMICRON = 0x1360;
    public final static int CHAR_SOUND_DMACHINE = 0x1370;
    public final static int CHAR_SOUND_METRON = 0x1380;

    public final static int CHAR_SOUND_MRGHOST = 0x1390;

    public final static int CHAR_SOUND_VAMPIRICBAT = 0x13A0;
    public final static int CHAR_SOUND_MIREKEEPER = 0x13B0;
    public final static int CHAR_SOUND_MUFFIN = 0x13C0;
    public final static int CHAR_SOUND_SOLIDSNAIL = 0x13D0;
    public final static int CHAR_SOUND_BEEVIL = 0x13E0;
    public final static int CHAR_SOUND_DIREBEE = 0x13F0;
    public final static int CHAR_SOUND_NIGHTMARE = 0x1400;
    public final static int CHAR_SOUND_STONEGOLEM = 0x1410;
    public final static int CHAR_SOUND_THORNCRAWLER = 0x1420;
    public final static int CHAR_SOUND_HEAVYGOBLIN = 0x1430;
    public final static int CHAR_SOUND_EVILPLANT = 0x1440;
    public final static int CHAR_SOUND_HAUNTINGPLANT = 0x1450;
    public final static int CHAR_SOUND_DARKKNIGHT = 0x1460;
    public final static int CHAR_SOUND_GUARDIAN_SAINT = 0x1470;

    public final static int CHAR_SOUND_CHAINGOLEM = 0x1500;
    public final static int CHAR_SOUND_DEADZONE = 0x1510;
    public final static int CHAR_SOUND_GROTESQUE = 0x1520;
    public final static int CHAR_SOUND_HYPERMACHINE = 0x1530;
    public final static int CHAR_SOUND_IRONFIST = 0x1540;
    public final static int CHAR_SOUND_MORGON = 0x1550;
    public final static int CHAR_SOUND_MOUNTAIN = 0x1560;
    public final static int CHAR_SOUND_RAMPAGE = 0x1570;
    public final static int CHAR_SOUND_RUNICGUARDIAN = 0x1580;
    public final static int CHAR_SOUND_SADNESS = 0x1590;
    public final static int CHAR_SOUND_TOWERGOLEM = 0x15A0;
    public final static int CHAR_SOUND_VAMPRICBEE = 0x15B0;
    public final static int CHAR_SOUND_VAMPRICMACHINE = 0x15C0;
    public final static int CHAR_SOUND_OMU = 0x15D0;

    public final static int CHAR_SOUND_AVELIN_ARCHER = 0x15E0;
    public final static int CHAR_SOUND_AVELIN_QUEEN = 0x15F0;

    public final static int CHAR_SOUND_BABEL = 0x1600;

    public final static int CHAR_SOUND_MYSTIC = 0x1610;
    public final static int CHAR_SOUND_ICEGOBLIN = 0x1620;
    public final static int CHAR_SOUND_COLDEYE = 0x1630;
    public final static int CHAR_SOUND_FROZEN = 0x1640;
    public final static int CHAR_SOUND_ICEGOLEM = 0x1650;
    public final static int CHAR_SOUND_FROST = 0x1660;
    public final static int CHAR_SOUND_CHAOSCARA = 0x1670;
    public final static int CHAR_SOUND_DEATHKNIGHT = 0x1680;

    // 弊府叼 龋荐 眠啊 阁胶磐
    public final static int CHAR_SOUND_GREATE_GREVEN = 0x1700;
    public final static int CHAR_SOUND_LIZARDFOLK = 0x1710;
    public final static int CHAR_SOUND_M_LORD = 0x1720;
    public final static int CHAR_SOUND_SPIDER = 0x1730;
    public final static int CHAR_SOUND_STINGRAY = 0x1740;
    public final static int CHAR_SOUND_STRIDER = 0x1750;

    // 酒捞胶2 眠啊 阁胶磐
    public final static int CHAR_SOUND_TURTLE_CANNON = 0x1800;
    public final static int CHAR_SOUND_DEVIL_BIRD = 0x1810;
    public final static int CHAR_SOUND_BLIZZARD_GIANT = 0x1820;
    public final static int CHAR_SOUND_KELVEZU = 0x1830;

    // 脚痹鞘靛眠啊
    public final static int CHAR_SOUND_DARKPHALANX = 0x1840;
    public final static int CHAR_SOUND_BLOODYKNIGHT = 0x1850;
    public final static int CHAR_SOUND_CHIMERA = 0x1860;
    public final static int CHAR_SOUND_FIREWORM = 0x1870;
    public final static int CHAR_SOUND_HELLHOUND = 0x1880;
    public final static int CHAR_SOUND_DARKGUARD = 0x1890;
    public final static int CHAR_SOUND_DARKMAGE = 0x18A0;

    public final static int CHAR_SOUND_MOKOVA = 0x18B0;
    public final static int CHAR_SOUND_TEMPLEGUARD = 0x18C0;
    public final static int CHAR_SOUND_SETO = 0x18D0;
    public final static int CHAR_SOUND_KINGSPIDER = 0x18E0;

    // pluto 脚痹鞘靛 阁胶磐
    public final static int CHAR_SOUND_REVIVED_KNIGHT = 0x18F0;
    public final static int CHAR_SOUND_REVIVED_MAGICIAN = 0x1900;
    public final static int CHAR_SOUND_REVIVED_ARCHER = 0x1910;
    public final static int CHAR_SOUND_REVIVED_ATALANTA = 0x1920; // 酒呕鄂鸥
    public final static int CHAR_SOUND_REVIVED_FIGTHER = 0x1930; // 颇捞磐
    public final static int CHAR_SOUND_REVIVED_MECANICIAN = 0x1940; // 皋墨聪记
    public final static int CHAR_SOUND_REVIVED_PIKEMAN = 0x1950; // 颇捞农盖
    public final static int CHAR_SOUND_REVIVED_PRIESTESS = 0x1960; // 橇府胶萍胶
    public final static int CHAR_SOUND_DEADHOPT = 0x1970; // 单靛龋乔
    public final static int CHAR_SOUND_DEADKINGHOPY = 0x1980; // 攫单靛 欧龋乔
    public final static int CHAR_SOUND_GORGON = 0x1990; // 绊福帮
    public final static int CHAR_SOUND_HOBOGOLEM = 0x19A0; // 龋焊绊方

    // 冠犁盔 - 历林罐篮 脚傈 3摸(脚痹鞘靛 阁胶磐)
    public final static int CHAR_SOUND_NIKEN = 0x19B0; // 聪乃
    public final static int CHAR_SOUND_MIMIC = 0x19C0; // 固雇
    public final static int CHAR_SOUND_KINGBAT = 0x19D0; // 欧诡
    public final static int CHAR_SOUND_GOBLINSHAMAN = 0x19E0; // 绊喉赴箕刚
    public final static int CHAR_SOUND_HEST = 0x19F0; // 庆胶飘

    // 冠犁盔 - 场绝绰 啪 3摸(脚痹鞘靛 阁胶磐)
    public final static int CHAR_SOUND_RUCA = 0x2100; // 风墨
    public final static int CHAR_SOUND_NAZSENIOR = 0x2110; // 唱令 矫聪绢
    public final static int CHAR_SOUND_IGOLATION = 0x2120; // 捞榜扼萍柯
    public final static int CHAR_SOUND_KAKOA = 0x2130; // 墨内酒
    public final static int CHAR_SOUND_SPRIN = 0x2140; // 胶橇赴
    public final static int CHAR_SOUND_UNDEADMAPLE = 0x2150; // 攫单靛 皋捞敲
    public final static int CHAR_SOUND_XETAN = 0x2160; // 力藕

    // 冠犁盔 - 汗朝 捞亥飘 阁胶磐 眠啊
    public final static int CHAR_SOUND_BEBECHICK = 0x2170; // 酒扁 部部
    public final static int CHAR_SOUND_PAPACHICK = 0x2180; // 酒狐 部部

    // 岿靛呐 阁胶磐
    public final static int CHAR_SOUND_WORLDCUP = 0x1A00;

    public final static int CHAR_SOUND_NPC_MORIF = 0x2010;
    public final static int CHAR_SOUND_NPC_MOLLYWOLF = 0x2012;
    public final static int CHAR_SOUND_NPC_SKILLMASTER = 0x2020;
    public final static int CHAR_SOUND_NPC_MAGICMASTER = 0x2030;

    public final static int CHAR_SOUND_S_WOLVERLIN = 0x3010;
    public final static int CHAR_SOUND_S_METALGOLEM = 0x3020;
    public final static int CHAR_SOUND_S_F_ELEMENTAL = 0x3030;

    public final static int CHAR_SOUND_CASTLE_DOOR = 0x5010;
    public final static int CHAR_SOUND_CASTLE_CRYSTAL_R = 0x5020;
    public final static int CHAR_SOUND_CASTLE_CRYSTAL_G = 0x5021;
    public final static int CHAR_SOUND_CASTLE_CRYSTAL_B = 0x5022;
    public final static int CHAR_SOUND_CASTLE_CRYSTAL_N = 0x5023;
    public final static int CHAR_SOUND_CASTLE_TOWER_B = 0x5030;

    // 侩捍甸
    public final static int CHAR_SOUND_CASTLE_SOLDER_A = 0x5100;
    public final static int CHAR_SOUND_CASTLE_SOLDER_B = 0x5110;
    public final static int CHAR_SOUND_CASTLE_SOLDER_C = 0x5120;

    // 厘喊 - 荐冠阁胶磐
    public final static int CHAR_SOUND_WATERMELON = 0x5130;

    // 厘喊 - 家匡胶沛
    public final static int CHAR_SOUND_S_AVELIN = 0x5140;
    public final static int CHAR_SOUND_S_BAGON = 0x5150;
    public final static int CHAR_SOUND_S_BEEDOG = 0x5160;
    public final static int CHAR_SOUND_S_BEEVIL = 0x5170;
    public final static int CHAR_SOUND_S_BERSERKER = 0x5180;
    public final static int CHAR_SOUND_S_BUMA = 0x5190;
    public final static int CHAR_SOUND_S_COKRIS = 0x51A0;
    public final static int CHAR_SOUND_S_COLDEYE = 0x51B0;
    public final static int CHAR_SOUND_S_CRYPT = 0x51C0;
    public final static int CHAR_SOUND_S_CYCLOPS = 0x51D0;
    public final static int CHAR_SOUND_S_DEADZONE = 0x51E0;
    public final static int CHAR_SOUND_S_DECOY = 0x51F0;
    public final static int CHAR_SOUND_S_DMACHINE = 0x5200;
    public final static int CHAR_SOUND_S_EVILSNAIL = 0x5210;
    public final static int CHAR_SOUND_S_GREVEN = 0x5220;
    public final static int CHAR_SOUND_S_GROTESQUE = 0x5230;
    public final static int CHAR_SOUND_S_ICEGOBLIN = 0x5240;
    public final static int CHAR_SOUND_S_ICEGOLEM = 0x5250;
    public final static int CHAR_SOUND_S_INCUBUS = 0x5260;
    public final static int CHAR_SOUND_S_KINGHOPY = 0x5270;
    public final static int CHAR_SOUND_S_LEECH = 0x5280;
    public final static int CHAR_SOUND_S_LIZARDFOLK = 0x5290;
    public final static int CHAR_SOUND_S_MEPHIT = 0x52A0;
    public final static int CHAR_SOUND_S_METRON = 0x52B0;
    public final static int CHAR_SOUND_S_MUFFIN = 0x52C0;
    public final static int CHAR_SOUND_S_MUMMY = 0x52D0;
    public final static int CHAR_SOUND_S_NAZ = 0x52E0;
    public final static int CHAR_SOUND_S_OMEGA = 0x52F0;
    public final static int CHAR_SOUND_S_RAMPAGE = 0x5300;
    public final static int CHAR_SOUND_S_SADNESS = 0x5310;
    public final static int CHAR_SOUND_S_SLAUGHTER = 0x5320;
    public final static int CHAR_SOUND_S_SLAYON = 0x5330;
    public final static int CHAR_SOUND_S_SLIVER = 0x5340;
    public final static int CHAR_SOUND_S_TITAN = 0x5350;
    public final static int CHAR_SOUND_S_TOWERGOLEM = 0x5360;
    public final static int CHAR_SOUND_S_TYPHOON = 0x5370;
    public final static int CHAR_SOUND_S_VAMPIRICBAT = 0x5380;
    public final static int CHAR_SOUND_S_WITCH = 0x5390;
    public final static int CHAR_SOUND_S_ZOMBIE = 0x53A0;

    public final static int esSOUND_FIGON_IMPACT = 10;

    public final static CharSound CHAR_SOUND_CODE[] = { new CharSound("CYCLOPS", CHAR_SOUND_CYCLOPS),
            new CharSound("HOBGOBLIN", CHAR_SOUND_HOBGOBLIN), new CharSound("IMP", CHAR_SOUND_IMP),
            new CharSound("MINIG", CHAR_SOUND_MINIG), new CharSound("PLANT", CHAR_SOUND_PLANT),
            new CharSound("SKELETON", CHAR_SOUND_SKELETON), new CharSound("ZOMBI", CHAR_SOUND_ZOMBI),
            new CharSound("OBIT", CHAR_SOUND_OBIT),

            new CharSound("HOPT", CHAR_SOUND_HOPT), new CharSound("BARGON", CHAR_SOUND_BARGON),
            new CharSound("LEECH", CHAR_SOUND_LEECH), new CharSound("MUSHROOM", CHAR_SOUND_MUSHROOM),

            new CharSound("ARMA", CHAR_SOUND_ARMA), new CharSound("ARMADIL", CHAR_SOUND_ARMA),
            new CharSound("SCORPION", CHAR_SOUND_SCORPION),

            new CharSound("HEADCUTTER", CHAR_SOUND_HEADCUTTER), new CharSound("SANDLEM", CHAR_SOUND_SANDLEM),
            new CharSound("WEB", CHAR_SOUND_WEB), new CharSound("HOPYKING", CHAR_SOUND_HOPYKING),
            new CharSound("CRIP", CHAR_SOUND_CRIP), new CharSound("BUMA", CHAR_SOUND_BUMA),
            new CharSound("DECOY", CHAR_SOUND_DECOY), new CharSound("DORAL", CHAR_SOUND_DORAL),
            new CharSound("FIGON", CHAR_SOUND_FIGON), new CharSound("GOLEM", CHAR_SOUND_STONEGIANT),
            new CharSound("GREVEN", CHAR_SOUND_GREVEN), new CharSound("ILLUSIONKNIGHT", CHAR_SOUND_ILLUSIONKNIGHT),
            new CharSound("SKELETONRANGE", CHAR_SOUND_SKELETONRANGE),
            new CharSound("SKELETONMELEE", CHAR_SOUND_SKELETONMELEE), new CharSound("WOLVERLIN", CHAR_SOUND_WOLVERLIN),

            // 葛扼捞柯 脚痹 眠啊 阁胶磐

            new CharSound("RABIE", CHAR_SOUND_RABIE), new CharSound("MUDY", CHAR_SOUND_MUDY),
            new CharSound("SEN", CHAR_SOUND_SEN), new CharSound("EGAN", CHAR_SOUND_EGAN),
            new CharSound("BEEDOG", CHAR_SOUND_BEEDOG), new CharSound("MUTANTPLANT", CHAR_SOUND_MUTANTPLANT),
            new CharSound("MUTANTRABIE", CHAR_SOUND_MUTANTRABIE), new CharSound("MUTANTTREE", CHAR_SOUND_MUTANTTREE),
            new CharSound("AVELISK", CHAR_SOUND_AVELISK), new CharSound("NAZ", CHAR_SOUND_NAZ),
            new CharSound("MUMMY", CHAR_SOUND_MUMMY), new CharSound("HULK", CHAR_SOUND_HULK),
            new CharSound("HUNGKY", CHAR_SOUND_HUNGKY), new CharSound("SUCCUBUS", CHAR_SOUND_SUCCUBUS),
            new CharSound("DAWLIN", CHAR_SOUND_DAWLIN), new CharSound("SHADOW", CHAR_SOUND_SHADOW),
            new CharSound("BERSERKER", CHAR_SOUND_BERSERKER), new CharSound("IRONGUARD", CHAR_SOUND_IRONGUARD),
            new CharSound("FURY", CHAR_SOUND_FURY), new CharSound("SLIVER", CHAR_SOUND_SLIVER),

            new CharSound("RATOO", CHAR_SOUND_RATOO), new CharSound("STYGIANLORD", CHAR_SOUND_STYGIANLORD),
            new CharSound("OMICRON", CHAR_SOUND_OMICRON), new CharSound("D-MACHINE", CHAR_SOUND_DMACHINE),
            new CharSound("METRON", CHAR_SOUND_METRON), new CharSound("MRGHOST", CHAR_SOUND_MRGHOST),

            new CharSound("VAMPIRICBAT", CHAR_SOUND_VAMPIRICBAT), new CharSound("MIREKEEPER", CHAR_SOUND_MIREKEEPER),
            new CharSound("MUFFIN", CHAR_SOUND_MUFFIN), new CharSound("SOLIDSNAIL", CHAR_SOUND_SOLIDSNAIL),
            new CharSound("BEEVIL", CHAR_SOUND_BEEVIL), new CharSound("NIGHTMARE", CHAR_SOUND_NIGHTMARE),
            new CharSound("STONEGOLEM", CHAR_SOUND_STONEGOLEM), new CharSound("THORNCRAWLER", CHAR_SOUND_THORNCRAWLER),
            new CharSound("HEAVYGOBLIN", CHAR_SOUND_HEAVYGOBLIN), new CharSound("EVILPLANT", CHAR_SOUND_EVILPLANT),
            new CharSound("HAUNTINGPLANT", CHAR_SOUND_HAUNTINGPLANT),
            new CharSound("DARKKNIGHT", CHAR_SOUND_DARKKNIGHT),
            new CharSound("GUARDIAN-SAINT", CHAR_SOUND_GUARDIAN_SAINT),

            // ; ///////////// 酒捞攫 阁胶磐 眠啊; //////////////////

            new CharSound("CHAINGOLEM", CHAR_SOUND_CHAINGOLEM), new CharSound("DEADZONE", CHAR_SOUND_DEADZONE),
            new CharSound("GROTESQUE", CHAR_SOUND_GROTESQUE), new CharSound("HYPERMACHINE", CHAR_SOUND_HYPERMACHINE),
            new CharSound("IRONFIST", CHAR_SOUND_IRONFIST), new CharSound("MORGON", CHAR_SOUND_MORGON),
            new CharSound("MOUNTAIN", CHAR_SOUND_MOUNTAIN), new CharSound("RAMPAGE", CHAR_SOUND_RAMPAGE),
            new CharSound("RUNICGUARDIAN", CHAR_SOUND_RUNICGUARDIAN), new CharSound("SADNESS", CHAR_SOUND_SADNESS),
            new CharSound("TOWERGOLEM", CHAR_SOUND_TOWERGOLEM), new CharSound("VAMPIRICBEE", CHAR_SOUND_VAMPRICBEE),
            new CharSound("VAMPIRICMACHINE", CHAR_SOUND_VAMPRICMACHINE),
            new CharSound("AVELINARCHER", CHAR_SOUND_AVELIN_ARCHER),
            new CharSound("AVELINQUEEN", CHAR_SOUND_AVELIN_QUEEN), new CharSound("BABEL", CHAR_SOUND_BABEL),

            // ; ///////////// 酒捞胶 阁胶磐 眠啊; //////////////////
            new CharSound("MYSTIC", CHAR_SOUND_MYSTIC), new CharSound("ICEGOBLIN", CHAR_SOUND_ICEGOBLIN),
            new CharSound("COLDEYE", CHAR_SOUND_COLDEYE), new CharSound("FROZEN", CHAR_SOUND_FROZEN),
            new CharSound("ICEGOLEM", CHAR_SOUND_ICEGOLEM), new CharSound("FROST", CHAR_SOUND_FROST),
            new CharSound("CHAOSCARA", CHAR_SOUND_CHAOSCARA), new CharSound("DEATHKNIGHT", CHAR_SOUND_DEATHKNIGHT),

            // ; /////////// 弊府叼龋荐 阁胶磐 眠啊; ////////////////
            new CharSound("GREATE_GREVEN", CHAR_SOUND_GREATE_GREVEN),
            new CharSound("LIZARDFOLK", CHAR_SOUND_LIZARDFOLK), new CharSound("M_LORD", CHAR_SOUND_M_LORD),
            new CharSound("SPIDER", CHAR_SOUND_SPIDER), new CharSound("STINGRAY", CHAR_SOUND_STINGRAY),
            new CharSound("STRIDER", CHAR_SOUND_STRIDER), new CharSound("OMU", CHAR_SOUND_OMU),

            // ; /////////// 酒捞胶2 阁胶磐 眠啊; ////////////////
            new CharSound("TURTLECANNON", CHAR_SOUND_TURTLE_CANNON), new CharSound("DEVILBIRD", CHAR_SOUND_DEVIL_BIRD),
            new CharSound("BLIZZARDGIANT", CHAR_SOUND_BLIZZARD_GIANT), new CharSound("KELVEZU", CHAR_SOUND_KELVEZU),

            // ; /////////// 脚痹鞘 阁胶磐 眠啊; ////////////////
            new CharSound("DARKPHALANX", CHAR_SOUND_DARKPHALANX),
            new CharSound("BLOODYKNIGHT", CHAR_SOUND_BLOODYKNIGHT), new CharSound("CHIMERA", CHAR_SOUND_CHIMERA),
            new CharSound("FIREWORM", CHAR_SOUND_FIREWORM), new CharSound("HELLHOUND", CHAR_SOUND_HELLHOUND),
            new CharSound("DARKGUARD", CHAR_SOUND_DARKGUARD), new CharSound("DARKMAGE", CHAR_SOUND_DARKMAGE),

            new CharSound("MONMOKOVA", CHAR_SOUND_MOKOVA), new CharSound("MONTEMPLEGUARD", CHAR_SOUND_TEMPLEGUARD),
            new CharSound("MONSETO", CHAR_SOUND_SETO), new CharSound("MONKINGSPIDER", CHAR_SOUND_KINGSPIDER),
            // ; //////////// pluto 脚痹鞘靛 阁胶磐; //////////////
            new CharSound("D_KN", CHAR_SOUND_REVIVED_KNIGHT), new CharSound("D_MAGI", CHAR_SOUND_REVIVED_MAGICIAN),
            new CharSound("D_ATAL", CHAR_SOUND_REVIVED_ATALANTA), new CharSound("D_FI", CHAR_SOUND_REVIVED_FIGTHER),
            new CharSound("D_AR", CHAR_SOUND_REVIVED_ARCHER), new CharSound("D_MECA", CHAR_SOUND_REVIVED_MECANICIAN),
            new CharSound("D_PA", CHAR_SOUND_REVIVED_PIKEMAN), new CharSound("D_PR", CHAR_SOUND_REVIVED_PRIESTESS),
            new CharSound("DEADHOPT", CHAR_SOUND_DEADHOPT), new CharSound("DEADKINGHOPY", CHAR_SOUND_DEADKINGHOPY),
            new CharSound("GORGON", CHAR_SOUND_GORGON), new CharSound("HOBOGOLEM", CHAR_SOUND_HOBOGOLEM),

            // 冠犁盔 - 历林罐篮 脚傈 3摸(脚痹鞘靛 阁胶磐)
            new CharSound("NIKEN", CHAR_SOUND_NIKEN), new CharSound("MIMIC", CHAR_SOUND_MIMIC),
            new CharSound("KINGBAT", CHAR_SOUND_KINGBAT), new CharSound("GOBLINSHAMAN", CHAR_SOUND_GOBLINSHAMAN),
            new CharSound("HEST", CHAR_SOUND_HEST),

            // 冠犁盔 - 场绝绰 啪 3摸(脚痹鞘靛 阁胶磐)
            new CharSound("RUCA", CHAR_SOUND_RUCA), new CharSound("NAZSENIOR", CHAR_SOUND_NAZSENIOR),
            new CharSound("IGOLATION", CHAR_SOUND_IGOLATION), new CharSound("KAKOA", CHAR_SOUND_KAKOA),
            new CharSound("SPRIN", CHAR_SOUND_SPRIN), new CharSound("UNDEADMAPLE", CHAR_SOUND_UNDEADMAPLE),
            new CharSound("XETAN", CHAR_SOUND_XETAN),

            // 冠犁盔 - 汗朝 捞亥飘 阁胶磐 眠啊
            new CharSound("BEBECHICK", CHAR_SOUND_BEBECHICK), // 酒扁 部部
            new CharSound("PAPACHICK", CHAR_SOUND_PAPACHICK), // 酒狐 部部

            // ; ///////////// 捞亥飘侩 NPC; //////////////////
            new CharSound("MORIF", CHAR_SOUND_NPC_MORIF), new CharSound("MOLLYWOLF", CHAR_SOUND_NPC_MOLLYWOLF),
            new CharSound("SKILLMASTER", CHAR_SOUND_NPC_SKILLMASTER), new CharSound("MAGE", CHAR_SOUND_NPC_MAGICMASTER),
            new CharSound("WORLDCUP", CHAR_SOUND_WORLDCUP), new CharSound("WATERMELON", CHAR_SOUND_WATERMELON), // 厘喊
                                                                                                                // -
                                                                                                                // 荐冠阁胶磐

            // ; ///////////// 胶懦 家券侩; //////////////////

            new CharSound("WOLVERIN", CHAR_SOUND_S_WOLVERLIN), new CharSound("METALGOLEM", CHAR_SOUND_S_METALGOLEM),
            new CharSound("FIREELEMENTAL", CHAR_SOUND_S_F_ELEMENTAL),

            // ; /////////////// 傍己 包访 拱眉; /////////////////////
            new CharSound("CASTLEDOOR", CHAR_SOUND_CASTLE_DOOR),
            new CharSound("T_CRYSTAL_R", CHAR_SOUND_CASTLE_CRYSTAL_R),
            new CharSound("T_CRYSTAL_G", CHAR_SOUND_CASTLE_CRYSTAL_G),
            new CharSound("T_CRYSTAL_B", CHAR_SOUND_CASTLE_CRYSTAL_B),
            new CharSound("T_CRYSTAL_N", CHAR_SOUND_CASTLE_CRYSTAL_N),
            new CharSound("TOWER-B", CHAR_SOUND_CASTLE_TOWER_B),

            // 侩捍 A,B,C
            new CharSound("SOLDIER_A", CHAR_SOUND_CASTLE_SOLDER_A),
            new CharSound("SOLDIER_B", CHAR_SOUND_CASTLE_SOLDER_B),
            new CharSound("SOLDIER_C", CHAR_SOUND_CASTLE_SOLDER_C),

            // 厘喊 - 家匡胶沛
            new CharSound("S_AVELIN", CHAR_SOUND_S_AVELIN), new CharSound("S_BAGON", CHAR_SOUND_S_BAGON),
            new CharSound("S_BEEDOG", CHAR_SOUND_S_BEEDOG), new CharSound("S_BEEVIL", CHAR_SOUND_S_BEEVIL),
            new CharSound("S_BERSERKER", CHAR_SOUND_S_BERSERKER), new CharSound("S_BUMA", CHAR_SOUND_S_BUMA),
            new CharSound("S_COKRIS", CHAR_SOUND_S_COKRIS), new CharSound("S_COLDEYE", CHAR_SOUND_S_COLDEYE),
            new CharSound("S_CRYPT", CHAR_SOUND_S_CRYPT), new CharSound("S_CYCLOPS", CHAR_SOUND_S_CYCLOPS),
            new CharSound("S_DEADZONE", CHAR_SOUND_S_DEADZONE), new CharSound("S_DECOY", CHAR_SOUND_S_DECOY),
            new CharSound("S_D-Machine", CHAR_SOUND_S_DMACHINE), new CharSound("S_EVILSNAIL", CHAR_SOUND_S_EVILSNAIL),
            new CharSound("S_GREVEN", CHAR_SOUND_S_GREVEN), new CharSound("S_GROTESQUE", CHAR_SOUND_S_GROTESQUE),
            new CharSound("S_ICEGOBLIN", CHAR_SOUND_S_ICEGOBLIN), new CharSound("S_ICEGOLEM", CHAR_SOUND_S_ICEGOLEM),
            new CharSound("S_INCUBUS", CHAR_SOUND_S_INCUBUS), new CharSound("S_KINGHOPY", CHAR_SOUND_S_KINGHOPY),
            new CharSound("S_LEECH", CHAR_SOUND_S_LEECH), new CharSound("S_LIZARDFOLK", CHAR_SOUND_S_LIZARDFOLK),
            new CharSound("S_MEPHIT", CHAR_SOUND_S_MEPHIT), new CharSound("S_METRON", CHAR_SOUND_S_METRON),
            new CharSound("S_MUFFIN", CHAR_SOUND_S_MUFFIN), new CharSound("S_MUMMY", CHAR_SOUND_S_MUMMY),
            new CharSound("S_NAZ", CHAR_SOUND_S_NAZ), new CharSound("S_OMEGA", CHAR_SOUND_S_OMEGA),
            new CharSound("S_RAMPAGE", CHAR_SOUND_S_RAMPAGE), new CharSound("S_SADNESS", CHAR_SOUND_S_SADNESS),
            new CharSound("S_SLAUGHTER", CHAR_SOUND_S_SLAUGHTER), new CharSound("S_SLAYON", CHAR_SOUND_S_SLAYON),
            new CharSound("S_SLIVER", CHAR_SOUND_S_SLIVER), new CharSound("S_TITAN", CHAR_SOUND_S_TITAN),
            new CharSound("S_TOWERGOLEM", CHAR_SOUND_S_TOWERGOLEM), new CharSound("S_TYPHOON", CHAR_SOUND_S_TYPHOON),
            new CharSound("S_VAMPIRICBAT", CHAR_SOUND_S_VAMPIRICBAT), new CharSound("S_WITCH", CHAR_SOUND_S_WITCH),
            new CharSound("S_ZOMBIE", CHAR_SOUND_S_ZOMBIE),

            new CharSound("", 0) };

    public String name;
    public int code; // 角色音效编码

    public CharSound(String name, int code) {
        this.name = name;
        this.code = code;
    }
}
