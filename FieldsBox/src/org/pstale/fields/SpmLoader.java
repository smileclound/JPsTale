package org.pstale.fields;

import java.io.File;
import java.io.IOException;

public class SpmLoader extends ResourceLoader<RespawnList> {

	@Override
	protected boolean accept(File dir, String name) {
		return name.endsWith(".spm");
	}

	String folder = "assets/server/Field";

	/**
	 * 文件夹路径
	 */
	public void setFolder(String f) {
		folder = f;
	}

	@Override
	protected String getFolder() {
		return folder;
	}

	@Override
	protected RespawnList decode() throws IOException {
		RespawnList list = new RespawnList();

		while (nextLine()) {
			if (line.length() == 0 || line.startsWith("//")
					|| token[0].length() == 0) {
				continue;
			}

			// 名字
			if (startWith("*弥措悼矫免泅荐|*怪物总数")) {
				list.LimitMax = getInt();
			}
			if (startWith("*免泅埃拜|*出现间隔", 2)) {
				list.OpenInterval = (1 << getInt()) - 1;
				list.IntervalTime = getInt(1) * 1000;
			}
			if (startWith("*免泅荐|*数量")) {
				list.OpenLimit = getInt();
			}
			if (startWith("*免楷磊|*怪物种类", 2)) {
				int start = line.indexOf('"');
				int end = line.lastIndexOf('"');

				StgMonster monster = new StgMonster();
				monster.name = line.substring(start + 1, end);
				monster.percentage = getInt(token.length - 2);
				list.PercentageCnt += monster.percentage;
				list.monsterList.add(monster);
			}
			if (startWith("*免楷磊滴格|*BOSS种类", 2)) {
				int _0 = line.indexOf('"', 0);
				int _1 = line.indexOf('"', _0 + 1);
				int _2 = line.indexOf('"', _1 + 1);
				int _3 = line.indexOf('"', _2 + 1);
				String[] nums = line.substring(_3+1).trim().split("\\s+");
				
				StgBoss boss = new StgBoss();
				boss.name = line.substring(_0+1, _1);
				boss.slave = line.substring(_2+1, _3);
				
				boss.slaveCnt = Integer.parseInt(nums[0]);
				
				boss.openTimeCnt = nums.length - 1;
				boss.openTime = new byte[nums.length - 1];
				for(int i=1; i<nums.length; i++) {
					boss.openTime[i-1] = (byte) Integer.parseInt(nums[i]);
				}
				list.bossList.add(boss);
				list.BossMonsterCount++;
			}
		}
		return list;
	}

	@Override
	protected boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}

}
