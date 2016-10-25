package net.jmecn.asset.item;

import java.io.*;

import org.junit.Before;
import org.junit.Test;

/**
 * 测试读取gameserver\openitem\文件夹下的所有txt文件。
 * 
 * @author yanmaoyuan
 * 
 */
public class ItemInfoReader {

	private String filepath;// 文件夹路径

	@Before
	public void init() {
		filepath = "gameserver\\openitem";
	}

	@Test
	public void read() {
		// item文件路径
		File openitem = new File(filepath);

		if (openitem.exists()) {
			// 读取文件列表
			File[] files = openitem.listFiles(new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) {
					if (name.endsWith(".txt"))
						return true;
					return false;
				}
			});

			// 遍历文件
			int count = 0;
			System.out.println("开始解析..");
			for (File file : files) {
				if (file.isFile()) {
					System.out.println(".......... Decode " + file.getName() + " ..........");
					decodeItemInfo(file);// 解析文件
					count++;
				}
			}
			System.out.println("共解析装备数量:" + count);
		} else {
			System.out.println("文件夹不存在");
		}
	}

	/**
	 * 解析装备数据
	 * 
	 * @param itemfile
	 * @return
	 */
	boolean decodeItemInfo(File itemfile) {
		if (itemfile == null)
			return false;

		try {
			String charset = "gbk";
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(itemfile), charset));
			String line = null;
			while ((line = br.readLine()) != null) {
				if (line.length() == 0 || line.startsWith("//")) {
					continue;
				}

				// ///////// NAME ///////////
				getString(line, "*捞抚", "NAME");
				getString(line, "Name", "NAME");
				getString(line, "*内靛", "CODE");

				getInt(line, "*蜡聪农", "UniqueItem");// atoi
				getString(line, "*蜡聪农祸惑", "EffectBlink");// RGBA + Blink 5
				getInt(line, "*捞棋飘汲沥", "DispEffect");// atoi

				// ////////// Common //////////
				getInt(line, "*郴备仿", "Durability");// atoi 2
				getInt(line, "*公霸", "Weight");// atoi
				getInt(line, "*啊拜", "Price");// atoi

				// ////////// Elements ///////////
				getInt(line, "*积眉", "Resistance_Bionic");// atoi
				getInt(line, "*措磊楷", "Resistance_Earth");// atoi
				getInt(line, "*阂", "Resistance_Fire");// atoi
				getInt(line, "*趁扁", "Resistance_Ice");// atoi
				getInt(line, "*锅俺", "Resistance_Lighting");// atoi
				getInt(line, "*刀", "Resistance_Poison");// atoi
				getInt(line, "*拱", "Resistance_Water");// atoi
				getInt(line, "*官恩", "Resistance_Wind");// atoi

				// ////////// Damage //
				getInt(line, "*傍拜仿", "Damage");// atoi 4
				getInt(line, "*荤沥芭府", "Shooting_Range");// atoi
				getInt(line, "*傍拜加档", "Attack_Speed");// atoi
				getInt(line, "*疙吝仿", "Attack_Rating");// atoi 2
				getInt(line, "*农府萍拿", "Critical_Hit");// atoi

				// ////////// Defence
				getFloat(line, "*软荐仿", "Absorb");// atof 2
				getInt(line, "*规绢仿", "Defence");// atoi
				getFloat(line, "*喉钒啦", "Block_Rating");// atof 2

				// ////////// Boots
				getFloat(line, "*捞悼加档", "Speed");// atof 2

				// ////////// Armlet
				getInt(line, "*焊蜡傍埃", "Potion_Space");// atoi

				// ////////// Recovery
				getFloat(line, "*付过槛访档", "Magic_Mastery");// atof 2
				getFloat(line, "*扁仿犁积", "Mana_Regen");// atof 2
				getFloat(line, "*付唱犁积", "Mana_Regen");// atof 2
				getFloat(line, "*积疙仿犁积", "Life_Regen");// atof 2
				getFloat(line, "*扼捞橇犁积", "Life_Regen");// atof 2
				getFloat(line, "*辟仿犁积", "Stamina_Regen");// atof 2
				getFloat(line, "*胶抛固唱犁积", "Stamina_Regen");// atof 2
				getInt(line, "*扁仿眠啊", "Increase_Mana");// atoi 2
				getInt(line, "*付唱眠啊", "Increase_Mana");// atoi 2
				getInt(line, "*积疙仿眠啊", "Increase_Life");// atof 2
				getInt(line, "*扼捞橇眠啊", "Increase_Life");// atof 2
				getInt(line, "*辟仿眠啊", "Increase_Stamina");// atof 2
				getInt(line, "*胶抛固唱眠啊", "Increase_Stamina");// atof 2

				// //////// Requirements
				getInt(line, "*饭骇", "Level");// atoi
				getInt(line, "*塞", "Strength");// atoi
				getInt(line, "*沥脚仿", "Spirit");// atoi
				getInt(line, "*犁瓷", "Talent");// atoi
				getInt(line, "*刮酶己", "Dexterity");// atoi
				getInt(line, "*扒碍", "Health");// atoi

				// /////////////////////
				getInt(line, "*辟仿惑铰", "Stamina");// atoi 2
				getInt(line, "*胶抛固呈惑铰", "Stamina");// atoi 2
				getInt(line, "*扁仿惑铰", "Mana");// atoi 2
				getInt(line, "*付唱惑铰", "Mana");// atoi 2
				getInt(line, "*积疙仿惑铰", "Life");// atoi 2
				getInt(line, "*扼捞橇惑铰", "Life");// atoi 2

				// //////////////////// Special Job
				getString(line, "**漂拳", "JobCodeMask");
				getString(line, "**漂拳罚待", "DefaultJobBitCode");

				// //////////// Special Defense
				getFloat(line, "**软荐仿", "Special_Absorb");// atof 2
				getInt(line, "**规绢仿", "Special_Defence");// atoi
				getFloat(line, "**喉钒啦", "Add_Block_Rating");// atoi
				getFloat(line, "**捞悼加档", "Special_fSpeed");// atof 2

				// ////////// Special Damage
				getInt(line, "**傍拜加档", "Add_Attack_Speed");// atoi
				getInt(line, "**农府萍拿", "Add_Critical_Hit");// atoi
				getInt(line, "**荤沥芭府", "Add_Shooting_Range");// atoi

				// ////////// Special Add By Level
				getInt(line, "**扁仿眠啊", "Lev_Mana");// atoi
				getInt(line, "**付唱眠啊", "Lev_Mana");// atoi
				getInt(line, "**积疙仿眠啊", "Lev_Life");// atof
				getInt(line, "**扼捞橇眠啊", "Lev_Life");// atof
				getInt(line, "**疙吝仿", "Lev_Attack_Rating");// atof 2
				getInt(line, "**傍拜仿", "Lev_Damage");// atof 2

				// ////////// Special Recovery
				getFloat(line, "**付过槛访档", "Special_Magic_Mastery");// atof 2
				getFloat(line, "**扁仿犁积", "Special_Mana_Regen");// atof 2
				getFloat(line, "**付唱犁积", "Special_Mana_Regen");// atof 2
				getFloat(line, "**积疙仿犁积", "Special_Life_Regen");// atof 2
				getFloat(line, "**扼捞橇犁积", "Special_Life_Regen");// atof 2
				getFloat(line, "**辟仿犁积", "Special_Stamina_Regen");// atof 2
				getFloat(line, "**胶抛固唱犁积", "Special_Stamina_Regen");// atof 2

				// ///////// ???
				getInt(line, "*惯积力茄", "GenDay");// atoi
				getString(line, "*楷搬颇老", "NextFile");

			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return true;
	}

	private void getString(String line, String key, String name) {
		if (line.startsWith(key)) {
			String value = line.substring(key.length()).trim();
			if (value.length() > 0)
				System.out.println("\"" + name + "\":" + value);
		}
	}

	private void getInt(String line, String key, String name) {
		if (line.startsWith(key)) {
			String value = line.substring(key.length()).trim();
			if (value.length() > 0)
				System.out.println("\"" + name + "\":" + value);
		}
	}

	private void getFloat(String line, String key, String name) {
		if (line.startsWith(key)) {
			String value = line.substring(key.length()).trim();
			if (value.length() > 0)
				System.out.println("\"" + name + "\":" + value);
		}
	}

}
