package org.pstale.fields;

import java.util.ArrayList;
import java.util.List;

/**
 * 刷新列表
 * @author yanmaoyuan
 *
 */
public class RespawnList {
	public String File;// 文件名
	
	public int LimitMax;// 地图中允许存在的怪物总数
	public int OpenLimit = 3;// 每次刷新的最大数量

	public int Counter;
	public int OpenInterval;// 刷新间隔(单位:秒)
	public int IntervalTime;

	// 怪物列表
	public List<StgMonster> monsterList = new ArrayList<StgMonster>();// 最多50种
	public int PercentageCnt;

	// BOSS列表
	public List<StgBoss> bossList = new ArrayList<StgBoss>();// 最多16种
	public int	BossMonsterCount;
	public boolean hasMonster() {return monsterList.size() > 0 || bossList.size() > 0;}
}
