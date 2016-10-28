package org.pstale.loader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.pstale.fields.RespawnList;
import org.pstale.fields.StgBoss;
import org.pstale.fields.StgMonster;

/**
 * 
 * @author yanmaoyuan
 *
 */
public class MonsterLoader {

	private boolean handleToken = false;
	
	protected File file = null;
	protected BufferedReader reader = null;
	protected String line;
	protected String[] token;
	
	private String charset = "gbk";
	
	public RespawnList load(String name) throws IOException {
		RespawnList list = new RespawnList();

		// 加载文件
		file = new File(name);
		if (!file.exists()) {
			System.out.println(name + " not found");
			// 文件不存在
			return null;
		}
		
		reader = new BufferedReader(
				new InputStreamReader(
						new FileInputStream(file), charset));

		// 解析文件
		list.File = file.getName();
		
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
	
	/**
	 * 读取下一行文本
	 * 
	 * @throws IOException
	 */
	protected boolean nextLine() throws IOException {
		handleToken = false;
		boolean flag = false;

		if (reader != null && (line = reader.readLine()) != null) {
			line = line.trim();
			token = line.split("\\s+");// 拆解成token
			flag = true;
		}

		return flag;
	}
	
	/**
	 * 比较命令行，默认检查1个参数
	 * @param token
	 * @return
	 */
	protected boolean startWith(final String token) {
		return startWith(token, 1);
	}
	
	/**
	 * 比较命令行
	 * @param token
	 * @param argCnt 参数数量
	 * @return
	 */
	protected boolean startWith(final String token, final int argCnt) {
		boolean startToken = false;
		for(String start : token.split("\\|")) {
			if (start.equals(this.token[0])) {
				startToken = true;
				break;
			}
		}
		boolean flag = !handleToken && startToken && this.token.length > argCnt;
		if (flag) {
			handleToken = true;
		}
		return flag;
	}
	/**
	 * 读取文本数据
	 * @return
	 */
	protected String getString() {
		String value = line.substring(token[0].length()).trim();
		return value;
	}
	
	/**
	 * 读取整数
	 * @param index
	 * @return
	 */
	protected int getInt() {
		return getInt(0);
	}
	
	/**
	 * 读取整数
	 * @param index
	 * @return
	 */
	protected int getInt(int index) {
		int value = 0;
		// B_32_Ghoul.inf中，怪物格挡率为6%。。。多了一个%号。
		try {
			value = Integer.parseInt(token[1 + index]);
		} catch (NumberFormatException e) {
			System.out.println(file.getName()); // TODO for test
			e.printStackTrace();
		}
		return value;
	}
}
