package org.pstale.asset.struct.item;

import java.util.Random;

/**
 * 耐久度
 * @author yanmaoyuan
 *
 */
public class AttrDurability {


	private Random rand = new Random();
	
	public AttrDurability(int cur, int max) {
		durabilityCur = cur;
		durabilityMax = max;
	}

	public AttrDurability() {
		durabilityCur = 18;
		durabilityMax = 18;
	}
	
	int useCount = 0;// 使用次数

	public int durabilityCur;// 当前耐久
	public int durabilityMax;// 耐久上限
	
	/*
	 * 是否受到祝福
	 * 被祝福过的装备，掉耐速度减半，修理成功率+1%。
	 */
	boolean isBlessed = true;// 是否已祝福

	/**
	 * 修理装备。
	 * 如果修理成功，恢复1点耐久(不超过耐久上限)。
	 * 如果修理失败，耐久上限-1(变成0这装备就毁了)。
	 * @param pct 修理成功率(90%, 95%, 98%)
	 * @return 
	 */
	boolean fix(int pct) {
		if (isBlessed) pct += 1;// 受到祝福，修理成功率+1%
		
		if (rand.nextInt(100) < pct) {
			if (durabilityCur < durabilityMax) {
				durabilityCur++;// 修复一点耐久
			}
			return true;
		} else {
			if(durabilityCur > 0)durabilityMax--;// 耐久上限-1
			return false;
		}
	}
	
	/**
	 * 是否损坏
	 * @return
	 */
	boolean isBroken() {
		return durabilityCur < durabilityMax;
	}
	
	/**
	 * 是否可用
	 * @return
	 */
	boolean isAvaliable() {
		return durabilityCur > 0;
	}
	
	void onUse() {
		useCount++;
		log("使用" + useCount);
		
		// 受到祝福的装备，比一般装备的耐久强一倍
		if (isBlessed && rand.nextInt(100) > 50) {
				return;
		} else {
			// 降低耐久
			durabilityCur--;
		}
		
		log("耐久:" + durabilityCur + "/" + durabilityMax);
	}
	
	void log(String msg) {
		System.out.println(msg);
	}
	
	public static void main(String[] args) {
		AttrDurability dur = new AttrDurability();
		while(dur.isAvaliable()) {
			dur.onUse();// 使用装备
		}
		
		while(dur.isBroken()) {
			dur.fix(90);
		}
	}
}