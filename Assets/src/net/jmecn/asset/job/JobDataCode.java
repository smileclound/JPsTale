package net.jmecn.asset.job;

public class JobDataCode {
	public String szName; // 英文名称
	public String szName2; // 中文名称
	public long JobBitCode; // 职业位代码
	public long JobCode; // 职业一般代码
	public long Brood; // 有关种族

	public int Sight; // 视野 **** 转职视野+5点 固定 ****
	public int LifeFunction; // 生命函数
	public int ManaFunction; // 魔力函数
	public int StaminaFunction; // 耐力函数
	public int DamageFunction[] = new int[] { 0, 0, 0 }; // 0 近战 1 远程 2魔法

	public JobDataCode(String szName, String szName2, long jobBitCode,
			long jobCode, long brood, int sight, int lifeFunction,
			int manaFunction, int staminaFunction, int damageFunction0,
			int damageFunction1, int damageFunction2) {
		super();
		this.szName = szName;
		this.szName2 = szName2;
		JobBitCode = jobBitCode;
		JobCode = jobCode;
		Brood = brood;
		Sight = sight;
		LifeFunction = lifeFunction;
		ManaFunction = manaFunction;
		StaminaFunction = staminaFunction;
		DamageFunction[0] = damageFunction0;
		DamageFunction[1] = damageFunction1;
		DamageFunction[2] = damageFunction2;
	}

	public JobDataCode(String szName, String szName2, long jobBitCode,
			long jobCode, long brood) {
		this(szName, szName2, jobBitCode, jobCode, brood, 0,0,0,0,0,0,0);
	}
}
