package net.jmecn.asset.job;

public interface JobConstant {

	public final static long JOB_CODE_MECHANICIAN = 0x00000001;
	public final static long JOB_CODE_FIGHTER = 0x00000002;
	public final static long JOB_CODE_PIKEMAN = 0x00000004;
	public final static long JOB_CODE_ARCHER = 0x00000008;

	public final static long JOB_CODE_MECHANICMASTER = 0x00000010;
	public final static long JOB_CODE_WARRIOR = 0x00000020;
	public final static long JOB_CODE_COMBATANT = 0x00000040;
	public final static long JOB_CODE_HUNTERMASTER = 0x00000080;

	public final static long JOB_CODE_METALLEADER = 0x00000100;
	public final static long JOB_CODE_CHAMPION = 0x00000200;
	public final static long JOB_CODE_LANCER = 0x00000400;
	public final static long JOB_CODE_DIONS_DISCIPLE = 0x00000800;

	public final static long JOB_CODE_HEAVYMETAL = 0x00001000;
	public final static long JOB_CODE_IMMORTALWARRIOR = 0x00002000;
	public final static long JOB_CODE_LANCELOT = 0x00004000;
	public final static long JOB_CODE_SAGITTARION = 0x00008000;

	public final static long JOB_CODE_KNIGHT = 0x00010000;
	public final static long JOB_CODE_ATALANTA = 0x00020000;
	public final static long JOB_CODE_PRIEST = 0x00040000;
	public final static long JOB_CODE_MAGICIAN = 0x00080000;

	public final static long JOB_CODE_PALADIN = 0x00100000;
	public final static long JOB_CODE_VALKYRIE = 0x00200000;
	public final static long JOB_CODE_SAINTESS = 0x00400000;
	public final static long JOB_CODE_WIZARD = 0x00800000;

	public final static long JOB_CODE_HOLYKNIGHT = 0x01000000;
	public final static long JOB_CODE_BRUNHILD = 0x02000000;
	public final static long JOB_CODE_BISHOP = 0x04000000;
	public final static long JOB_CODE_ROYALKNIGHT = 0x08000000;

	public final static long JOB_CODE_SAINTKNIGHT = 0x10000000;
	public final static long JOB_CODE_VALHALLA = 0x20000000;
	public final static long JOB_CODE_CELESTIAL = 0x40000000;
	public final static long JOB_CODE_ARCHMAGE = 0x80000000;

	public final static long BROOD_CODE_TEMPSKRON = 1;
	public final static long BROOD_CODE_MORAYION = 2;
	public final static long BROOD_CODE_SOPPHETIO = 4;
	
	public static JobDataCode JobDataBase[] = {
		// 坦普
		new JobDataCode("Mechanician", "机械兵", JOB_CODE_MECHANICIAN, 2, BROOD_CODE_TEMPSKRON, 65, 2, 2, 2, 2, 2, 0),
		new JobDataCode("Fighter", "武士", JOB_CODE_FIGHTER, 1, BROOD_CODE_TEMPSKRON, 70, 1, 3, 1, 1, 2, 0),
		new JobDataCode("Pikeman", "枪兵", JOB_CODE_PIKEMAN, 4, BROOD_CODE_TEMPSKRON, 75, 1, 3, 1, 1, 2, 0),
		new JobDataCode("Archer", "弓箭手", JOB_CODE_ARCHER, 3, BROOD_CODE_TEMPSKRON, 80, 3, 3, 2, 3, 1, 0),
	
		new JobDataCode("MechanicMaster", "机甲兵", JOB_CODE_MECHANICMASTER, 2, BROOD_CODE_TEMPSKRON),
		new JobDataCode("Warrior", "勇战士", JOB_CODE_WARRIOR, 1, BROOD_CODE_TEMPSKRON),
		new JobDataCode("Combatant", "长枪客", JOB_CODE_COMBATANT, 4, BROOD_CODE_TEMPSKRON),
		new JobDataCode("HunterMaster", "名箭手", JOB_CODE_HUNTERMASTER, 3, BROOD_CODE_TEMPSKRON),
	
		new JobDataCode("MetalLeader", "重甲勇士", JOB_CODE_METALLEADER, 2, BROOD_CODE_TEMPSKRON),
		new JobDataCode("Champion", "狂战士", JOB_CODE_CHAMPION, 1, BROOD_CODE_TEMPSKRON),
		new JobDataCode("Lancer", "枪骑士", JOB_CODE_LANCER, 4, BROOD_CODE_TEMPSKRON),
		new JobDataCode("Dion's Disciple", "神射手", JOB_CODE_DIONS_DISCIPLE, 3, BROOD_CODE_TEMPSKRON),
	
		new JobDataCode("Metalion", "机甲队长", JOB_CODE_HEAVYMETAL, 2, BROOD_CODE_TEMPSKRON),
		new JobDataCode("ImmortalWarrior", "圣殿武士", JOB_CODE_IMMORTALWARRIOR, 1, BROOD_CODE_TEMPSKRON),
		new JobDataCode("Lancelot", "皇家骑士", JOB_CODE_LANCELOT, 4, BROOD_CODE_TEMPSKRON),
		new JobDataCode("Sagittarion", "箭神", JOB_CODE_SAGITTARION, 3, BROOD_CODE_TEMPSKRON),
	
		// 魔灵
		new JobDataCode("Knight", "游侠", JOB_CODE_KNIGHT, 6, BROOD_CODE_MORAYION, 190, 2, 2, 2, 1, 2, 0),
		new JobDataCode("Atalanta", "魔枪手", JOB_CODE_ATALANTA, 5, BROOD_CODE_MORAYION, 200, 2, 2, 2, 2, 1, 0),
		new JobDataCode("Priestess", "祭司", JOB_CODE_PRIEST, 8, BROOD_CODE_MORAYION, 200, 4, 1, 3, 3, 2, 2),
		new JobDataCode("Magician", "魔法师", JOB_CODE_MAGICIAN, 7, BROOD_CODE_MORAYION, 190, 5, 1, 3, 3, 2, 1),
	
		new JobDataCode("Paladin", "爵士", JOB_CODE_PALADIN, 6, BROOD_CODE_MORAYION),
		new JobDataCode("Valkyrie", "暗杀者", JOB_CODE_VALKYRIE, 5, BROOD_CODE_MORAYION),
		new JobDataCode("Saintess", "圣徒", JOB_CODE_SAINTESS, 8, BROOD_CODE_MORAYION),
		new JobDataCode("Wizard", "术士", JOB_CODE_WIZARD, 7, BROOD_CODE_MORAYION),
	
		new JobDataCode("HolyKnight", "突击剑士", JOB_CODE_HOLYKNIGHT, 6, BROOD_CODE_MORAYION),
		new JobDataCode("BrunHild", "狙杀者", JOB_CODE_BRUNHILD, 5, BROOD_CODE_MORAYION),
		new JobDataCode("Bishop", "贤者", JOB_CODE_BISHOP, 8, BROOD_CODE_MORAYION),
		new JobDataCode("RoyalKnight", "魔导师", JOB_CODE_ROYALKNIGHT, 7, BROOD_CODE_MORAYION),
	
		new JobDataCode("SaintKnight", "剑圣", JOB_CODE_SAINTKNIGHT, 6, BROOD_CODE_MORAYION),
		new JobDataCode("Valhalla", "女武神", JOB_CODE_VALHALLA, 5, BROOD_CODE_MORAYION),
		new JobDataCode("Celestial", "圣灵法师", JOB_CODE_CELESTIAL, 8, BROOD_CODE_MORAYION),
		new JobDataCode("ArchMage", "死灵法师", JOB_CODE_ARCHMAGE, 7, BROOD_CODE_MORAYION),
	
		new JobDataCode("", "", 0, 0, 0)
	};
}
