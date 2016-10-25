package net.jmecn.asset.chars;

import java.io.File;
import java.util.Collections;
import java.util.List;

import net.jmecn.asset.Convertable;
import net.jmecn.asset.ItemInitilize;
import net.jmecn.asset.MonsterInitilize;
import net.jmecn.asset.item.ItemInfo;

public class MonsterJSON implements Convertable {
	public static void main(String[] args) {
		new MonsterJSON().convert();
	}
	List<CharMonsterInfo> list = null;
	List<ItemInfo> items = null;
	String folder = null;
	public MonsterJSON() {
		MonsterInitilize monsterInit = new MonsterInitilize();
		monsterInit.setFolder("D:\\Priston Tale\\0_素材\\Server\\精灵中国全服务端3060\\3060\\GameServer\\Monster");
		monsterInit.init();

		ItemInitilize itemInit = new ItemInitilize();
		itemInit.setFolder("D:\\Priston Tale\\0_素材\\Server\\精灵中国全服务端3060\\3060\\GameServer\\OpenItem");
		itemInit.init();
		items = itemInit.getList();

		list = monsterInit.getList();

		folder = "assert\\JSON\\";
	}
	@Override
	public boolean convert() {
		Collections.sort(list);

		json();
		return false;
	}
	
	private void json() {
		File dir = new File(folder);
		if (!dir.exists())
			dir.mkdirs();
		for (CharMonsterInfo monster : list) {
			System.out.println(monster.szName);
		}
	}
}
