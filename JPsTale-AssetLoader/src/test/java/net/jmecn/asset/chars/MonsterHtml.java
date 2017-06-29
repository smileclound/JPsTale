package net.jmecn.asset.chars;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Collections;
import java.util.List;

import net.jmecn.asset.Convertable;
import net.jmecn.asset.ItemInitilize;
import net.jmecn.asset.MonsterInitilize;
import net.jmecn.asset.item.ItemConstant;
import net.jmecn.asset.item.ItemInfo;

public class MonsterHtml implements Convertable {
	public static void main(String[] args) {
		new MonsterHtml().convert();
	}

	List<CharMonsterInfo> list = null;
	List<ItemInfo> items = null;
	String folder = null;

	public MonsterHtml() {
		MonsterInitilize monsterInit = new MonsterInitilize();
		monsterInit.setFolder("D:/Priston Tale/0_素材/Server/精灵中国全服务端3060/3060/GameServer/Monster");
		monsterInit.init();

		ItemInitilize itemInit = new ItemInitilize();
		itemInit.setFolder("D:/Priston Tale/0_素材/Server/精灵中国全服务端3060/3060/GameServer/OpenItem");
		itemInit.init();
		items = itemInit.getList();

		list = monsterInit.getList();

		folder = "assert\\HTML\\";
	}

	@Override
	public boolean convert() {
		Collections.sort(list);

		html();
		return false;
	}

	public void html() {
		int count = 0;
		File dir = new File(folder);
		if (!dir.exists())
			dir.mkdirs();
		File IndexPage = new File(folder + "menu.htm");
		try {
			PrintStream out = new PrintStream(new FileOutputStream(IndexPage));
			out.println("<!DOCTYPE html>");
			out.println("<HTML>");
			out.println("	<HEAD>");
			out.println("		<meta http-equiv=\"content-type\" content=\"text/html;charset=utf8\">");
			out.println("		<title>精灵中国 - 怪物列表</title>");
			out.println("		<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\"/>");
			out.println("	</HEAD>");
			out.println("	<BODY>");
			out.println("		<TABLE class=\"gridtable\">");
			out.println("		<TR>");
			out.println("			<TH align='center'>序号</TH>");
			out.println("			<TH align='center'>名字</TH>");
			out.println("			<TH align='center'>等级</TH>");
			out.println("			<TH align='center'>生命值</TH>");
			out.println("			<TH align='center'>攻击</TH>");
			out.println("			<TH align='center'>防御</TH>");
			out.println("			<TH align='center'>吸收</TH>");
			out.println("			<TH align='center'>格挡</TH>");
			out.println("			<TH align='center'>经验值</TH>");
			out.println("		</TR>");
			for (CharMonsterInfo monster : list) {
				count++;
				out.println("		<TR>");
				out.printf("			<TD align='center'>%d</TD>", count);out.println();
				out.printf(
						"			<TD><A href=\"mon%d.htm\" target=\"content\">%s</A></TD>",
						count, monster.szName);out.println();
				out.printf("			<TD align='center'>%d</TD>", monster.Level);out.println();
				out.printf("			<TD>%d</TD>", monster.Life[1]);out.println();
				out.printf("			<TD>%d~%d</TD>", monster.Attack_Damage[0], monster.Attack_Damage[1]);out.println();
				out.printf("			<TD>%d</TD>", monster.Defence);out.println();
				out.printf("			<TD>%d</TD>", monster.Absorption);out.println();
				out.printf("			<TD>%d</TD>", monster.Chance_Block);out.println();
				out.printf("			<TD>%d</TD>", monster.GetExp);out.println();
				out.println("		</TR>");

				html(monster, count);
			}
			out.println("		</TABLE>");
			out.println("	</BODY>");
			out.println("</HTML>");
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public boolean html(CharMonsterInfo monster, int count) {
		String filename = "mon" + count + ".htm";

		File IndexPage = new File(folder + filename);
		try {
			PrintStream out = new PrintStream(new FileOutputStream(IndexPage));
			out.println("<!DOCTYPE html>");
			out.println("<HTML>");
			out.println("	<HEAD>");
			out.println("		<meta http-equiv=\"content-type\" content=\"text/html;charset=utf8\">");
			out.println("		<title>" + monster.szName + "的掉落列表</title>");
			out.println("		<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\"/>");
			out.println("	</HEAD>");
			out.println("	<BODY>");
			out.println("		<TABLE class=\"gridtable\">");
			if (monster.FallItemPlusCount > 0) {
				out.printf("		<TR><TD colspan=\"3\">%s的额外掉落清单</TD></TR>", monster.szName);
				out.println("		<TR>");
				out.println("			<TH align='center'>几率</TH>");
				out.println("			<TH align='center'>物品编号</TH>");
				out.println("			<TH align='center'>物品名称</TH>");
				out.println("		</TR>");
				for (int i = 0; i < monster.FallItemPlusCount; i++) {
					out.println("		<TR>");
					out.printf("			<TD align='center'>%2.2f%%</TD>", (float) monster.FallItems_Plus[i].Percentage / 100);out.println();
					out.printf("			<TD align='center'>0x%08X</TD>", monster.FallItems_Plus[i].dwItemCode);	out.println();
					String ItemName = null;
					ItemInfo ii = searchName(monster.FallItems_Plus[i].dwItemCode);
					if (ii == null) {
						ItemName = "未知装备";
					} else {
						ItemName = ii.localeName;
					}
					if (monster.FallItems_Plus[i].dwItemCode == 0) {
						ItemName = "无掉落";
					}
					if (monster.FallItems_Plus[i].dwItemCode == 0x05010100) {
						ItemName = "金钱";
					}
					out.printf("			<TD align='left'>%s</TD>", ItemName);
					
					out.println("		</TR>");
				}
			}
			
			if (monster.FallItemCount > 0) {
				out.printf("		<TR><TD colspan=\"3\">%s的掉落清单</TD></TR>", monster.szName);
				out.println();
				out.println("		<TR>");
				out.println("			<TH align='center'>几率</TH>");
				out.println("			<TH align='center'>物品编号</TH>");
				out.println("			<TH align='center'>物品名称</TH>");
				out.println("		</TR>");
				for (int i = 0; i < monster.FallItemCount; i++) {
					out.println("		<TR>");
					out.printf("			<TD align='center'>%2.2f%%</TD>",
							(float) monster.FallItems[i].Percentage / 100);
					out.println();
					out.printf("			<TD align='center'>0x%08X</TD>",
							monster.FallItems[i].dwItemCode);
					out.println();

					String ItemName = null;
					ItemInfo ii = searchName(monster.FallItems[i].dwItemCode);
					if (ii == null) {
						ItemName = "未知装备";
					} else {
						ItemName = ii.localeName;
					}

					if (monster.FallItems[i].dwItemCode == 0) {
						ItemName = "无掉落";
					}
					if (monster.FallItems[i].dwItemCode == 0x05010100) {
						ItemName = "金钱";
					}
					out.printf("			<TD align='left'>%s</TD>", ItemName);
					out.println();
					out.println("		</TR>");
				}
			}
			out.println("		</TABLE>");
			out.println("	</BODY>");
			out.println("</HTML>");
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return false;
	}

	private ItemInfo searchName(long code) {
		int len = ItemConstant.itemDataBase.length;
		for (int i = 0; i < len; i++) {
			if (code == ItemConstant.itemDataBase[i].CODE) {
				String category = ItemConstant.itemDataBase[i].LastCategory;
				for (ItemInfo item : items) {
					String cate = item.code.substring(1, 6).toUpperCase();
					if (category.equalsIgnoreCase(cate)) {
						return item;
					}
				}
			}
			;
		}

		return null;
	}
}
