package net.jmecn.asset.item;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

/**
 * 装备名称国际化
 * 
 * @author yanmaoyuan
 * 
 */
public class ItemNameTranslation {


	private String folder = "assert\\language\\";
	public void translateItemName(List<ItemInfo> list) {
		Locale locale = Locale.getDefault();
		File file = new File(folder + "item_" + locale + ".language");
		try {
			Properties prop = new Properties();
			InputStream in = new FileInputStream(file);
			prop.load(in);

			for (ItemInfo i : list) {
				String code = i.code.substring(1, 6).toUpperCase();
				String localeName = prop.getProperty("item." + code);
				try {
					if (localeName != null)
						localeName = new String(
								localeName.getBytes("iso8859-1"), "gbk");
					else
						localeName = "";
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				i.localeName = localeName;
			}

		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

	}
}
