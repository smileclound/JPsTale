package net.jmecn.asset;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

import net.jmecn.asset.item.ItemInfo;
import net.jmecn.asset.item.ItemKeys;
import net.jmecn.asset.job.JobConstant;

/**
 * 装备数据初始化
 * 
 * @author yanmaoyuan
 * 
 */
public class ItemInitilize extends ResourceInitilize<ItemInfo> implements
		ItemKeys, JobConstant {

	String folder = "assert\\server\\openitem";
	/**
	 * 文件夹路径
	 */
	@Override
	protected String getFolder() {
		return folder;
	}
	
	public void setFolder(String f) {
		folder = f;
	}

	/**
	 * 装备文件名
	 */
	@Override
	protected boolean accept(File dir, String name) {
		return (name.endsWith(".txt"));
	}

	/**
	 * 验证装备数据有效性
	 */
	@Override
	public boolean validate() {
		Set<String> map = new HashSet<String>();

		int count = 0;
		for (ItemInfo i : list) {
			String key = i.code.substring(1, 6);
			if (map.contains(key)) {
				count++;
				System.out.println(key + "\t" + i.File);
			} else {
				map.add(key);
			}
		}
		
		if (count > 0) {
			System.out.printf("检查完毕，共%d件雷同装备。\n", count);
		}

		return count > 0;
	}

	/**
	 * 对装备名字进行本地化
	 * 
	 * @param list
	 */
	public void localeItemName() {
		// 加载properties文件
		Locale locale = Locale.getDefault();
		ResourceBundle rb = ResourceBundle.getBundle("MessageBundle", locale);

		// 修改装备名字
		for (ItemInfo i : list) {
			String code = i.code.substring(1, 6).toUpperCase();
			i.localeName = rb.getString("item." + code);
		}
	}

	/**
	 * 解析装备数据
	 */
	@Override
	protected ItemInfo decode() throws IOException {
		// 逐行解析文件数据
		ItemInfo iteminfo = new ItemInfo();
		iteminfo.UniqueItem = 1;
		iteminfo.File = file.getName();
		while (nextLine()) {
			if (line.length() == 0 || line.startsWith("//")
					|| token[0].length() == 0) {
				continue;
			}

			// ///////// NAME ///////////
			if (startWith(LOCALE_NAME)) {
				iteminfo.localeName = getString();
			}

			if (startWith(EN_NAME)) {
				iteminfo.enName = getString();
			}

			if (startWith(CODE)) {
				iteminfo.code = getString();
			}

			if (startWith(UniqueItem)) {
				iteminfo.UniqueItem = getInt();
			}

			if (startWith(EffectBlink, 0)) {
				if (token.length > 5) {
					iteminfo.EffectColor[0] = getShort(0);// R
					iteminfo.EffectColor[1] = getShort(1);// G
					iteminfo.EffectColor[2] = getShort(2);// B
					iteminfo.EffectColor[3] = getShort(3);// A
					iteminfo.EffectBlink[0] = getShort(4);// Blink
					if (token.length > 6) {
						iteminfo.ScaleBlink[0] = (short) (getFloat(5) * 256);
					}
				}
			}

			if (startWith(DispEffect)) {
				iteminfo.DispEffect = getInt();
			}

			// ////////// Common //////////
			if (startWith(Durability, 2)) {
				iteminfo.durability.durabilityCur = getInt(0);
				iteminfo.durability.durabilityMax = getInt(1);
			}
			if (startWith(Weight)) {
				iteminfo.Weight = getInt();
			}
			if (startWith(Price)) {
				iteminfo.Price = getInt();
			}

			// ////////// Resistance 元素抗性
			if (startWith(Resistance_Bionic, 2)) {
				iteminfo.resistance.Bionic[0] = getInt(0);
				iteminfo.resistance.Bionic[1] = getInt(1);
			}
			if (startWith(Resistance_Earth, 2)) {
				iteminfo.resistance.Earth[0] = getInt(0);
				iteminfo.resistance.Earth[1] = getInt(1);
			}
			if (startWith(Resistance_Fire, 2)) {
				iteminfo.resistance.Fire[0] = getInt(0);
				iteminfo.resistance.Fire[1] = getInt(1);
			}
			if (startWith(Resistance_Ice, 2)) {
				iteminfo.resistance.Ice[0] = getInt(0);
				iteminfo.resistance.Ice[1] = getInt(1);
			}
			if (startWith(Resistance_Lighting, 2)) {
				iteminfo.resistance.Lighting[0] = getInt(0);
				iteminfo.resistance.Lighting[1] = getInt(1);
			}
			if (startWith(Resistance_Poison, 2)) {
				iteminfo.resistance.Poison[0] = getInt(0);
				iteminfo.resistance.Poison[1] = getInt(1);
			}
			if (startWith(Resistance_Water, 2)) {
				iteminfo.resistance.Water[0] = getInt(0);
				iteminfo.resistance.Water[1] = getInt(1);
			}
			if (startWith(Resistance_Wind, 2)) {
				iteminfo.resistance.Wind[0] = getInt(0);
				iteminfo.resistance.Wind[1] = getInt(1);
			}

			// ////////// Damage 伤害能力
			if (startWith(Damage, 4)) {
				iteminfo.attack.Damage[0] = getInt(0);
				iteminfo.attack.Damage[1] = getInt(1);
				iteminfo.attack.Damage[2] = getInt(2);
				iteminfo.attack.Damage[3] = getInt(3);
			}
			if (startWith(Shooting_Range)) {
				iteminfo.attack.Shooting_Range = getInt();
			}
			if (startWith(Attack_Speed)) {
				iteminfo.attack.Attack_Speed = getInt();
			}
			if (startWith(Attack_Rating, 2)) {
				iteminfo.attack.Attack_Rating[0] = getInt(0);
				iteminfo.attack.Attack_Rating[1] = getInt(1);
			}
			if (startWith(Critical_Hit)) {
				iteminfo.attack.Critical_Hit = getInt();
			}
			if (startWith(Magic_Mastery, 2)) {
				iteminfo.attack.Magic_Mastery[0] = getFloat(0);
				iteminfo.attack.Magic_Mastery[1] = getFloat(1);
			}

			// ////////// Defence
			if (startWith(Absorb, 2)) {
				iteminfo.defence.Absorb[0] = getFloat(0);
				iteminfo.defence.Absorb[1] = getFloat(1);
			}
			if (startWith(Defence, 2)) {
				iteminfo.defence.Defence[0] = getInt(0);
				iteminfo.defence.Defence[1] = getInt(1);
			}
			if (startWith(Block_Rating, 2)) {
				iteminfo.defence.Block_Rating[0] = getFloat(0);
				iteminfo.defence.Block_Rating[1] = getFloat(1);
			}

			// ////////// Boots 移动能力
			if (startWith(Speed, 2)) {
				iteminfo.speed.Speed[0] = getFloat(0);
				iteminfo.speed.Speed[1] = getFloat(1);
			}

			// ////////// Armlet
			if (startWith(Potion_Space)) {
				iteminfo.Potion_Space = getInt();
			}

			// ////////// Recovery
			if (startWith(Mana_Regen, 2)) {
				iteminfo.reovery.Mana_Regen[0] = getFloat(0);
				iteminfo.reovery.Mana_Regen[1] = getFloat(1);
			}
			if (startWith(Life_Regen, 2)) {
				iteminfo.reovery.Life_Regen[0] = getFloat(0);
				iteminfo.reovery.Life_Regen[1] = getFloat(1);
			}
			if (startWith(Stamina_Regen, 2)) {
				iteminfo.reovery.Stamina_Regen[0] = getFloat(0);
				iteminfo.reovery.Stamina_Regen[1] = getFloat(1);
			}
			if (startWith(Increase_Mana, 2)) {
				iteminfo.reovery.Increase_Mana[0] = getFloat(0);
				iteminfo.reovery.Increase_Mana[1] = getFloat(1);
			}
			if (startWith(Increase_Life, 2)) {
				iteminfo.reovery.Increase_Life[0] = getFloat(0);
				iteminfo.reovery.Increase_Life[1] = getFloat(1);
			}
			if (startWith(Increase_Stamina, 2)) {
				iteminfo.reovery.Increase_Stamina[0] = getFloat(0);
				iteminfo.reovery.Increase_Stamina[1] = getFloat(1);
			}

			// //////// Requirements 装备需求
			if (startWith(Require_Level)) {
				iteminfo.require.level = getInt();
			}
			if (startWith(Require_Strength)) {
				iteminfo.require.strength = getInt();
			}
			if (startWith(Require_Spirit)) {
				iteminfo.require.spirit = getInt();
			}
			if (startWith(Require_Talent)) {
				iteminfo.require.talent = getInt();
			}
			if (startWith(Require_Dexterity)) {
				iteminfo.require.dexterity = getInt();
			}
			if (startWith(Require_Health)) {
				iteminfo.require.health = getInt();
			}

			// ///////////////////// 药剂效果
			if (startWith(Stamina, 2)) {
				iteminfo.potionEffect.Stamina[0] = getShort(0);
				iteminfo.potionEffect.Stamina[1] = getShort(1);
			}
			if (startWith(Mana, 2)) {
				iteminfo.potionEffect.Mana[0] = getShort(0);
				iteminfo.potionEffect.Mana[1] = getShort(1);
			}
			if (startWith(Life, 2)) {
				iteminfo.potionEffect.Life[0] = getShort(0);
				iteminfo.potionEffect.Life[1] = getShort(1);
			}

			// //////////////////// Special Job
			if (startWith(JobCodeMask)) {// 特效
				iteminfo.JobName = getString();
				
				boolean find = false;
				int cnt = 0;
				while(!find && cnt<JobDataBase.length) {
					if (token[1].equalsIgnoreCase(JobDataBase[cnt].szName)) {
						iteminfo.JobCodeMask = JobDataBase[cnt].JobBitCode;
						find = true;
					}
					cnt++;
				}
			}

			if (startWith(RandomJobName)) {// 随机特效
				iteminfo.RandomJobName = getString();
				
				for(int i=1; i<token.length; i++) {
					boolean find = false;
					int cnt = 0;
					while(!find && cnt<JobDataBase.length) {
						if (token[i].equalsIgnoreCase(JobDataBase[cnt].szName)) {
							if (iteminfo.JobBitCodeRandomCount < ItemInfo.SPECIAL_JOB_RANDOM_MAX) {
								iteminfo.dwJobBitCode_Random[iteminfo.JobBitCodeRandomCount++] = JobDataBase[cnt].JobBitCode;
								find = true;
							}
						}
						cnt++;
					}
				}
			}

			// //////////// Special Defense
			if (startWith(Add_Absorb, 2)) {
				iteminfo.jobItem.Add_Absorb[0] = getFloat(0);
				iteminfo.jobItem.Add_Absorb[1] = getFloat(1);
			}
			if (startWith(Add_Defence, 2)) {
				iteminfo.jobItem.Add_Defence[0] = getInt(0);
				iteminfo.jobItem.Add_Defence[1] = getInt(1);
			}
			if (startWith(Add_Block_Rating)) {
				iteminfo.jobItem.Add_Block_Rating = getInt();
			}
			if (startWith(Add_Speed, 2)) {
				iteminfo.jobItem.Add_Speed[0] = getFloat();
				iteminfo.jobItem.Add_Speed[1] = getFloat(1);
			}

			// Specail Damage
			if (startWith(Add_Attack_Speed)) {
				iteminfo.jobItem.Add_Attack_Speed = getInt();
			}
			if (startWith(Add_Critical_Hit)) {
				iteminfo.jobItem.Add_Critical_Hit = getInt();
			}
			if (startWith(Add_Shooting_Range)) {
				iteminfo.jobItem.Add_Shooting_Range = getInt();
			}

			// ////////// Special Add By Level
			if (startWith(Lev_Mana)) {
				iteminfo.jobItem.Lev_Mana = getInt();
			}
			if (startWith(Lev_Life)) {
				iteminfo.jobItem.Lev_Life = getInt();
			}
			if (startWith(Lev_Attack_Rating, 2)) {
				iteminfo.jobItem.Lev_Attack_Rating[0] = getInt(0);
				iteminfo.jobItem.Lev_Attack_Rating[1] = getInt(1);
			}
			if (startWith(Lev_Damage, 2)) {
				iteminfo.jobItem.Lev_Damage[0] = getInt(0);
				iteminfo.jobItem.Lev_Damage[1] = getInt(1);
			}

			// ////////// Special Recovery
			if (startWith(Add_Magic_Mastery, 2)) {
				iteminfo.jobItem.Add_Magic_Mastery[0] = getFloat(0);
				iteminfo.jobItem.Add_Magic_Mastery[1] = getFloat(1);
			}
			if (startWith(Add_Per_Mana_Regen, 2)) {
				iteminfo.jobItem.Add_Per_Mana_Regen[0] = getFloat(0);
				iteminfo.jobItem.Add_Per_Mana_Regen[1] = getFloat(1);
			}
			if (startWith(Add_Per_Life_Regen, 2)) {
				iteminfo.jobItem.Add_Per_Life_Regen[0] = getFloat(0);
				iteminfo.jobItem.Add_Per_Life_Regen[1] = getFloat(1);
			}
			if (startWith(Add_Per_Stamina_Regen, 2)) {
				iteminfo.jobItem.Add_Per_Stamina_Regen[0] = getFloat(0);
				iteminfo.jobItem.Add_Per_Stamina_Regen[1] = getFloat(1);
			}

			// ///////// ???
			if (startWith(GenDay)) {
				// TODO lpDefItem->sGenDay[0] = atoi(strBuff);
				iteminfo.GenDay = getInt();
			}
			if (startWith(NextFile)) {
				// TODO check if file exists
				iteminfo.NextFile = getString();
			}
		}

		return iteminfo;
	}
}
