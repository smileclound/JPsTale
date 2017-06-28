package net.jmecn.aging;

import java.util.Random;

public class Aging {

	// 锻造石
	private String[][] SheltomName = {
			{ "星遗石", "lucidy" }, 
			{ "流云石", "sereneo" },
			{ "海精石", "fadeo" },
			{ "天仪石", "sparky" },
			{ "冰晶石", "raident" },
			{ "玄风石", "transparo" },
			{ "水晶石", "murky" },
			{ "虎翼石", "devine" },
			{ "龙鳞石", "celesto" },
			{ "钻晶石", "mirage" },
			{ "龙睛石", "inferna" },
			{ "圣晶石", "enigma" },
			{ "恶魔石", "bellum" },
			{ "荣誉石", "oredo" },
			{ "蓝晶石", "sapphire" }};

	// 锻造升级配方
	private int[][] AgingLevelSheltom = { { 3, 3, 4, 4, 5, 0, 0, 0, 0, 0, 0, 0 },
			{ 3, 3, 4, 4, 5, 5, 0, 0, 0, 0, 0, 0 },
			{ 3, 3, 4, 4, 5, 5, 6, 0, 0, 0, 0, 0 },
			{ 3, 3, 4, 4, 5, 5, 6, 6, 0, 0, 0, 0 },
			{ 3, 3, 4, 4, 5, 5, 6, 6, 7, 0, 0, 0 },
			{ 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 0, 0 },
			{ 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 0 },
			{ 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8 },
			{ 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 0 },
			{ 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9 },
			{ 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10, 0 },
			{ 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10, 10 },
			{ 6, 6, 7, 7, 8, 8, 9, 9, 10, 10, 11, 0 },
			{ 6, 6, 7, 7, 8, 8, 9, 9, 10, 10, 11, 11 },
			{ 7, 7, 8, 8, 9, 9, 10, 10, 11, 11, 12, 0 },
			{ 7, 7, 8, 8, 9, 9, 10, 10, 11, 11, 12, 12 },
			{ 8, 8, 9, 9, 10, 10, 11, 11, 12, 12, 13, 0 },
			{ 8, 8, 9, 9, 10, 10, 11, 11, 12, 12, 13, 13 },
			{ 9, 9, 10, 10, 11, 11, 12, 12, 13, 13, 14, 0 },
			{ 9, 9, 10, 10, 11, 11, 12, 12, 13, 13, 14, 14 } };

	/********************** 锻造升级所需的条件 **********************/
	// 升级所需攻击次数
	private int[] AgingLevelAttack = { 100, 130, 169, 219, 284, 369, 479, 622, 808,
			1049, 1362, 1769, 2297, 2983, 3874, 5031, 6534, 8486, 11021, 14313 };

	// 升级所需必杀次数
	private int[] AgingLevelCritical = { 12, 16, 21, 27, 35, 45, 58, 75, 97, 126, 164,
			213, 277, 360, 468, 608, 790, 1026, 1332, 1730 };

	// 升级所需格挡次数
	private int[] AgingLevelBlock = { 15, 19, 25, 32, 42, 55, 71, 92, 119, 155, 201,
			261, 339, 440, 571, 742, 964, 1252, 1626, 2112 };

	// 升级所需命中次数
	private int[] AgingLevelHit = { 45, 58, 75, 97, 126, 164, 213, 277, 360, 468, 608,
			790, 1026, 1332, 1730, 2247, 2918, 3790, 4922, 6392 };

	/********************** 锻造几率 **********************/
	// 锻造失败率 %
	private int[] AgingOkPercent = { 0, 0, 0, 0, 0, 5, 10, 15, 20, 30, 35, 40, 45, 50, 55, 65, 75, 85, 95, 95 };
//	private int[] AgingOkPercent = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 20, 30, 40, 50, 60, 70, 80 };

	// 触发双倍锻造几率 %
	private int[] PlusAgingPercent = { 30, 25, 20, 10, 10, 10, 10, 5, 5, 5, 5, 4, 4, 3, 3, 2, 2, 2, 0 ,0}; //Aging twice
	
	private int[][] AgingFailPercent = {
			// FIXED, DOWN_LVL, DESTRY
			{100, 0, 0},// +1
			{100, 0, 0},// +2
			{100, 0, 0},// +3
			{100, 0, 0},// +4
			{100, 0, 0},// +5
			{10, 90, 0},// +6
			{20, 80, 0},// +7
			{15, 70, 15},// +8
			{20, 60, 20},// +9
			{20, 50, 30},// +10
			{20, 40, 40},// +11
			{20, 30, 50},// +12
			{10, 30, 60},// +13
			{10, 20, 70},// +14
			{10, 10, 80},// +15
			{10, 10, 80},// +16
			{10, 10, 80},// +17
			{10, 10, 80},// +18
			{10, 10, 80},// +19
			{10, 10, 80}};// +20

	public enum RESULT {
		DESTROY ,
		DOWN_2LVL,
		DOWN_LVL,
		FIXED,
		SUCCESS,
		DOUBLE_LVL
	}
	
	Random rand = new Random();
	public RESULT aging(int agingNum, int add) {
		
		int destroy = rand.nextInt(100);
		int plus = rand.nextInt(100);
		
		if (destroy >= getAgingPercent(agingNum, add)) {
			// 锻造失败
			if (destroy < AgingFailPercent[agingNum][0]) {
				return RESULT.FIXED;// 不变
			} else if (destroy < AgingFailPercent[agingNum][0] + AgingFailPercent[agingNum][1]) {
				return RESULT.DOWN_LVL;// 不变
			} else {
				if (add > 0) {
					if (rand.nextInt(100) < 80) {
						return RESULT.DOWN_LVL; 
					} else {
						return RESULT.DOWN_2LVL;
					}
				} else {
					return RESULT.DESTROY;// 摧毁
				}
			}
		} else {
			// 锻造成功
			if (plus <= PlusAgingPercent[agingNum]*2) {
				// 额外+1
				return RESULT.DOUBLE_LVL;
			} else {
				// +1
				return RESULT.SUCCESS;
			}
		}
	}
	
	/**
	 * 查询锻造成功率
	 * @param agingNum 0~19 锻造级别
	 * @param add 附加几率
	 * @return
	 */
	public int getAgingPercent(int agingNum, int add) {
		int per = 100 - AgingOkPercent[agingNum];
		per += per*add/100;
		if (per > 100) per = 100;
		if (per < 0) per = 0;
		return per;
	}
	
	public int getAgingMoney(int price, int agingLvl) {
		int money = price * (1 + agingLvl) / 2;
		return money;
	}
	
	public void simulator() {
		int n = 0;
		int s = 0;
		int money;
		int add = 0;// 额外锻造几率
		int price = 350000;
		int agingLvl = 0;
		System.out.println("锻造\t| 等级\t| 成功率\t| 结果");
		System.out.println("--------|-------|-------|-------");
		while(agingLvl<20) {
			s++;
			System.out.printf("第%d次\t| +%d\t| %d%%\t| ",s, (agingLvl+1), getAgingPercent(agingLvl, add));
			switch (aging(agingLvl, add)) {
				case DESTROY: {
					System.out.println("摧毁");
					agingLvl = 0;
					n++;
					break;
				}
				case DOWN_2LVL: {
					System.out.println("-2");
					agingLvl--;
					agingLvl--;
					break;
				}
				case DOWN_LVL: {
					System.out.println("-1");
					agingLvl--;
					break;
				}
				case FIXED: {
					System.out.println("+0");
					break;
				}
				case SUCCESS: {
					System.out.println("+1");
					agingLvl++;
					break;
				}
				case DOUBLE_LVL: {
					System.out.println("+2");
					agingLvl++;
					agingLvl++;
					break;
				}
			}
		}
		
		System.out.println("使用+" + add + "%几率锻造石，共消耗" + (n+1) +"件装备, 锻造" + s + "次。");
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Aging aging = new Aging();
		aging.simulator();
	}

}
