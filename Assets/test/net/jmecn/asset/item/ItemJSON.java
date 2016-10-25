package net.jmecn.asset.item;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

/**
 * 将ItemInfo转换成JSON文件
 * @author yanmaoyuan
 *
 */
public class ItemJSON {

	// 输出文件路径
	private String folder = "assert\\server\\openitem\\json\\";

	public String getFolder() {
		return folder;
	}
	public void jsonItemInfo(List<ItemInfo> list) {
		for (ItemInfo info : list) {
			try {
				// 文件名
				String filename = info.code.substring(1, 6).toUpperCase()+".json";
				
				// 生成文件内容
				String content = jsonItemInfo(info);
				
				// 保存文件
				PrintStream ps = new PrintStream(
						new FileOutputStream(
								new File(folder + filename)));
				ps.print(content);
				ps.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 生成 ITEMINFO表的JSON文本
	 * 
	 * @param list
	 */
	public String jsonItemInfo(ItemInfo item) {

		StringBuffer sb = new StringBuffer();
		sb.append(String.format("{\r\n"));
		sb.append(String.format("    \"ItemInfo\": {\r\n"));
		sb.append(String.format("        \"Locale_Name\": \"%s\",\r\n",
				item.localeName));
		sb.append(String
				.format("        \"En_Name\": %s,\r\n", item.enName));
		sb.append(String.format("        \"UniqueItem\": %d,\r\n",
				item.UniqueItem));
		sb.append(String.format("        \"EffectColor\": [%d,%d,%d,%d],\r\n",
				item.EffectColor[0], item.EffectColor[1], item.EffectColor[2],
				item.EffectColor[3]));
		sb.append(String.format("        \"EffectBlink\": %d,\r\n",
				item.EffectBlink[0]));
		sb.append(String.format("        \"ScaleBlink\": %d,\r\n",
				item.ScaleBlink[0]));
		sb.append(String.format("        \"DispEffect\": %d,\r\n",
				item.DispEffect));

		// Common
		sb.append(String.format("        \"Weight\": %d,\r\n", item.Weight));
		sb.append(String.format("        \"Price\": %d,\r\n", item.Price));

		// Resistance
		sb.append(String.format("        \"AttrResistance\": {\r\n"));
		sb.append(String.format("            \"Bionic\": [%d,%d],\r\n",
				item.resistance.Bionic[0], item.resistance.Bionic[1]));
		sb.append(String.format("            \"Earth\": [%d,%d],\r\n",
				item.resistance.Earth[0], item.resistance.Earth[1]));
		sb.append(String.format("            \"Fire\": [%d,%d],\r\n",
				item.resistance.Fire[0], item.resistance.Fire[1]));
		sb.append(String.format("            \"Ice\": [%d,%d],\r\n",
				item.resistance.Ice[0], item.resistance.Ice[1]));
		sb.append(String.format("            \"Lighting\": [%d,%d],\r\n",
				item.resistance.Lighting[0], item.resistance.Lighting[1]));
		sb.append(String.format("            \"Poison\": [%d,%d],\r\n",
				item.resistance.Poison[0], item.resistance.Poison[1]));
		sb.append(String.format("            \"Water\": [%d,%d],\r\n",
				item.resistance.Water[0], item.resistance.Water[1]));
		sb.append(String.format("            \"Wind\": [%d,%d],\r\n",
				item.resistance.Wind[0], item.resistance.Wind[1]));
		sb.append(String.format("        },\r\n"));

		// Attack
		sb.append(String.format("        \"AttrAttack\": {\r\n"));
		sb.append(String.format("            \"Damage\": [%d,%d,%d,%d],\r\n",
				item.attack.Damage[0], item.attack.Damage[1],
				item.attack.Damage[2], item.attack.Damage[3]));
		sb.append(String.format("            \"Shooting_Range\": %d,\r\n",
				item.attack.Shooting_Range));
		sb.append(String.format("            \"Attack_Speed\": %d,\r\n",
				item.attack.Attack_Speed));
		sb.append(String.format("            \"Attack_Rating\": [%d,%d],\r\n",
				item.attack.Attack_Rating[0], item.attack.Attack_Rating[1]));
		sb.append(String.format("            \"Critical_Hit\": %d,\r\n",
				item.attack.Critical_Hit));
		sb.append(String.format(
				"            \"Magic_Mastery\": [%.1f,%.1f]\r\n",
				item.attack.Magic_Mastery[0], item.attack.Magic_Mastery[1]));
		sb.append(String.format("        },\r\n"));

		// Defense
		sb.append(String.format("        \"AttrDefense\": {\r\n"));
		sb.append(String.format("            \"Absorb\": [%.1f,%.1f],\r\n",
				item.defence.Absorb[0], item.defence.Absorb[1]));
		sb.append(String.format("            \"Defense\": [%d,%d],\r\n",
				item.defence.Defence[0], item.defence.Defence[1]));
		sb.append(String.format("            \"Block_Rating\": [%.1f,%.1f]\r\n",
				item.defence.Block_Rating[0], item.defence.Block_Rating[1]));
		sb.append(String.format("        },\r\n"));

		// Speed
		sb.append(String.format("        \"AttrSpeed\": [%.1f,%.1f],\r\n",
				item.speed.Speed[0], item.speed.Speed[1]));

		// Recovery
		sb.append(String.format("        \"AttrRecovery\": {\r\n"));
		sb.append(String.format("            \"Mana_Regen\": [%.1f,%.1f],\r\n",
				item.reovery.Mana_Regen[0], item.reovery.Mana_Regen[1]));
		sb.append(String.format("            \"Life_Regen\": [%.1f,%.1f],\r\n",
				item.reovery.Life_Regen[0], item.reovery.Life_Regen[1]));
		sb.append(String.format(
				"            \"Stamina_Regen\": [%.1f,%.1f],\r\n",
				item.reovery.Stamina_Regen[0], item.reovery.Stamina_Regen[1]));
		sb.append(String.format("            \"Increase_Life\": [%.1f,%.1f],\r\n",
				item.reovery.Increase_Life[0], item.reovery.Increase_Life[1]));
		sb.append(String.format("            \"Increase_Mana\": [%.1f,%.1f],\r\n",
				item.reovery.Increase_Mana[0], item.reovery.Increase_Mana[1]));
		sb.append(String.format(
				"            \"Increase_Stamina\": [%.1f,%.1f]\r\n",
				item.reovery.Increase_Stamina[0],
				item.reovery.Increase_Stamina[1]));
		sb.append(String.format("        },\r\n"));

		// PotionSpace
		sb.append(String.format("        \"AttrPotionSpace\": %d,\r\n",
				item.Potion_Space));

		// PotionEffect
		sb.append(String.format("        \"AttrPotionEffect\": {\r\n"));
		sb.append(String.format("            \"Mana\":[%d,%d],\r\n",
				item.potionEffect.Mana[0], item.potionEffect.Mana[1]));
		sb.append(String.format("            \"Life\": [%d,%d],\r\n",
				item.potionEffect.Life[0], item.potionEffect.Life[1]));
		sb.append(String.format("            \"Stamina\": [%d,%d]\r\n",
				item.potionEffect.Stamina[0], item.potionEffect.Stamina[1]));
		sb.append(String.format("        },\r\n"));

		// Special
		sb.append(String.format("        \"ItemSpecial\": {\r\n"));
		sb.append(String.format(
				"            \"Default_Job_Name\": \"%s\",\r\n", item.JobName));
		sb.append(String.format("            \"Random_Job_Name\": \"%s\",\r\n",
				item.RandomJobName));
		sb.append(String.format("            \"Add_Absorb\": [%.1f,%.1f],\r\n",
				item.jobItem.Add_Absorb[0], item.jobItem.Add_Absorb[1]));
		sb.append(String.format("            \"Add_Defence\": [%d,%d],\r\n",
				item.jobItem.Add_Defence[0], item.jobItem.Add_Defence[1]));
		sb.append(String.format("            \"Add_Speed\": [%.1f,%.1f],\r\n",
				item.jobItem.Add_Speed[0], item.jobItem.Add_Speed[1]));
		sb.append(String.format("            \"Add_Block_Rating\": %.1f,\r\n",
				item.jobItem.Add_Block_Rating));
		sb.append(String.format("            \"Add_Attack_Speed\": %d,\r\n",
				item.jobItem.Add_Attack_Speed));
		sb.append(String.format("            \"Add_Critical_Hit\": %d,\r\n",
				item.jobItem.Add_Critical_Hit));
		sb.append(String.format("            \"Add_Shooting_Range\": %d,\r\n",
				item.jobItem.Add_Shooting_Range));
		sb.append(String.format(
				"            \"Add_Magic_Mastery\": [%.1f,%.1f],\r\n",
				item.jobItem.Add_Magic_Mastery[0],
				item.jobItem.Add_Magic_Mastery[1]));
		sb.append(String
				.format("            \"Add_Resistance\": [%d,%d,%d,%d,%d,%d,%d,%d],\r\n",
						item.jobItem.Add_Resistance[0],
						item.jobItem.Add_Resistance[1],
						item.jobItem.Add_Resistance[2],
						item.jobItem.Add_Resistance[3],
						item.jobItem.Add_Resistance[4],
						item.jobItem.Add_Resistance[5],
						item.jobItem.Add_Resistance[6],
						item.jobItem.Add_Resistance[7]));
		sb.append(String
				.format("            \"Lev_Attack_Resistance\": [%d,%d,%d,%d,%d,%d,%d,%d],\r\n",
						item.jobItem.Lev_Attack_Resistance[0],
						item.jobItem.Lev_Attack_Resistance[1],
						item.jobItem.Lev_Attack_Resistance[2],
						item.jobItem.Lev_Attack_Resistance[3],
						item.jobItem.Lev_Attack_Resistance[4],
						item.jobItem.Lev_Attack_Resistance[5],
						item.jobItem.Lev_Attack_Resistance[6],
						item.jobItem.Lev_Attack_Resistance[7]));
		sb.append(String.format("            \"Lev_Mana\": %d,\r\n",
				item.jobItem.Lev_Mana));
		sb.append(String.format("            \"Lev_Life\": %d,\r\n",
				item.jobItem.Lev_Life));
		sb.append(String.format(
				"            \"Lev_Attack_Rating\": [%d,%d],\r\n",
				item.jobItem.Lev_Attack_Rating[0],
				item.jobItem.Lev_Attack_Rating[1]));
		sb.append(String.format("            \"Lev_Damage\": [%d,%d],\r\n",
				item.jobItem.Lev_Damage[0], item.jobItem.Lev_Damage[1]));
		sb.append(String.format(
				"            \"Add_Mana_Regen\": [%.1f,%.1f],\r\n",
				item.jobItem.Add_Per_Mana_Regen[0],
				item.jobItem.Add_Per_Mana_Regen[1]));
		sb.append(String.format(
				"            \"Add_Life_Regen\":  [%.1f,%.1f],\r\n",
				item.jobItem.Add_Per_Life_Regen[0],
				item.jobItem.Add_Per_Life_Regen[1]));
		sb.append(String.format(
				"            \"Add_Stamina_Regen\":  [%.1f,%.1f]\r\n",
				item.jobItem.Add_Per_Stamina_Regen[0],
				item.jobItem.Add_Per_Stamina_Regen[1]));
		sb.append(String.format("        },\r\n"));

		// Require
		sb.append(String.format("        \"ItemRequire\": {\r\n"));
		sb.append(String.format("            \"Level\": %d,\r\n",
				item.require.level));
		sb.append(String.format("            \"Strength\": %d,\r\n",
				item.require.strength));
		sb.append(String.format("            \"Spirit\": %d,\r\n",
				item.require.spirit));
		sb.append(String.format("            \"Talent\": %d,\r\n",
				item.require.talent));
		sb.append(String.format("            \"Dexterity\": %d,\r\n",
				item.require.dexterity));
		sb.append(String.format("            \"Health\": %d\r\n",
				item.require.health));
		sb.append(String.format("        },\r\n"));

		// Other
		sb.append(String.format("        \"GenDay\": %d,\r\n", item.GenDay));
		sb.append(String.format("        \"NextFile\": %s\r\n",
				item.NextFile));
		sb.append(String.format("    }\r\n"));
		sb.append(String.format("}"));

		return sb.toString();
	}
}
