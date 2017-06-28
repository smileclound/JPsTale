package net.jmecn.asset;

import java.io.File;
import java.io.IOException;

import net.jmecn.asset.chars.CharInfo;
import net.jmecn.asset.chars.CharInfoConstant;
import net.jmecn.asset.chars.CharKeys;
import net.jmecn.asset.chars.CharMonsterInfo;
import net.jmecn.asset.chars.CharSoundCode;
import net.jmecn.asset.item.ItemConstant;

/**
 * 怪物数据初始化
 * 
 * @author yanmaoyuan
 * 
 */
public class MonsterInitilize extends ResourceInitilize<CharMonsterInfo>
		implements ItemConstant, CharInfoConstant, CharKeys, CharSoundCode {

	String folder= "assert\\server\\monster";
	/**
	 * 文件夹路径
	 */
	@Override
	public String getFolder() {
		return folder;
	}
	
	public void setFolder(String f) {
		folder = f;
	}

	@Override
	protected boolean accept(File dir, String name) {
		return (name.endsWith(".inf") || name.endsWith(".npc") || name.endsWith(".NPC"));
	}

	@Override
	protected CharMonsterInfo decode() throws IOException {
		int dialogFlag = 0;
		CharMonsterInfo monster = new CharMonsterInfo();
		CharMonsterInfo npc = monster;

		// 初始化CharInfo
		monster.szModelName2 = "";
		monster.wPlayClass[1] = 0;
		monster.ArrowPosi[0] = 0;
		monster.ArrowPosi[1] = 0;

		// 初始化CharMonsterInfo
		monster.MoveRange = 64 * CharMonsterInfo.fONE;
		monster.FallItemCount = 0;
		monster.FallItemMax = 0;
		monster.FallItemPerMax = 0;

		monster.DamageStunPers = 100;// 眩晕率 100%
		monster.UseEventModel = 0;

		while (nextLine()) {
			if (line.length() == 0 || line.startsWith("//")
					|| token[0].length() == 0) {
				continue;
			}

			// 名字
			if (startWith(NAME)) {
				monster.szName = getString();
			}

			if (startWith(EN_NAME)) {
				monster.enName = getString();
			}
			
			// 模型文件
			if (startWith(MODEL_NAME)) {
				monster.szModelName = getString();
			}

			// 对话
			dialogFlag = 0;
			String dialogMsg = null;
			if (startWith(DIALOG_MSG)) {
				dialogMsg = getString();
				dialogFlag++;
			}

			if (dialogFlag > 0) {
				if (monster.NpcMsgCount < NPC_MESSAGE_MAX) {
					monster.NpcMessage[monster.NpcMsgCount++] = setNPCMsg(dialogMsg);
				}
			}

			// 角色状态
			if (startWith(STATE)) {
				String value = getString();
				if (CharKeys.STATE_ENEMY.contains(value)) {
					monster.State = CHAR_STATE_ENEMY;
				} else {
					monster.State = CHAR_STATE_NPC;
				}
			}

			// 尺寸
			if (startWith(SIZE_LEVEL)) {
				String value = getString();
				monster.SizeLevel = -1;
				for (int cnt = 0; cnt < szCharSizeCodeName.length; cnt++) {
					if (szCharSizeCodeName[cnt].contains(value)) {
						monster.SizeLevel = cnt;
					}
				}
			}

			// 音效
			if (startWith(CHAR_SOUND_CODE)) {
				String value = getString();
				for (int cnt = 0; cnt < dwCharSoundCode.length; cnt++) {
					if (dwCharSoundCode[cnt].szCodeName.equalsIgnoreCase(value)) {
						monster.dwCharSoundCode = dwCharSoundCode[cnt].dwCode;
					}
				}
			}

			// 等级
			if (startWith(LEVEL)) {
				monster.Level = getInt();
			}

			// PlayClass[0]
			if (startWith(PLAY_CLASS_BOSS, 0)) {
				monster.wPlayClass[0] = MONSTER_CLASS_BOSS;
			}
			if (startWith(PLAY_CLASS_0)) {
				monster.wPlayClass[0] = getInt(0);
			}

			// 模型尺寸
			if (startWith(MODEL_SIZE)) {
				monster.wPlayClass[1] = (int) (getFloat() * CharInfo.fONE);
				if (monster.wPlayClass[1] == CharInfo.fONE)
					monster.wPlayClass[1] = 0;
			}

			// 移动速度
			if (startWith(MOVE_SPEED)) {
				monster.Move_Speed = ConvMoveSpeed(getFloat());
			}

			if (startWith(ATTACK_DAMAGE, 2)) {
				monster.Attack_Damage[0] = getInt(0);
				monster.Attack_Damage[1] = getInt(1);
			}
			if (startWith(ATTACK_SPEED)) {
				monster.Attack_Speed = (int) getFloat() * CharInfo.fONE;
				// TODO 源码这里计算了2次一模一样的攻速速度，不明白为什么
			}
			if (startWith(SHOOTING_RANGE)) {
				monster.Shooting_Range = getInt() * CharInfo.fONE;
			}
			if (startWith(ATTACK_RATING)) {
				monster.Attack_Rating = getInt();
			}
			if (startWith(DEFENCE)) {
				monster.Defence = getInt();
			}
			if (startWith(ABSORPTION)) {
				monster.Absorption = getInt();
			}
			if (startWith(CHANCE_BLOCK)) {
				monster.Chance_Block = getInt();
			}
			if (startWith(LIFE)) {
				monster.Life[1] = getShort();
			}

			// 元素抗性
			if (startWith(Resistance_Bionic)) {
				monster.Resistance[ItemConstant.ITEMINFO_BIONIC] = getShort();
			}
			if (startWith(Resistance_Earth)) {
				monster.Resistance[ItemConstant.ITEMINFO_EARTH] = getShort();
			}
			if (startWith(Resistance_Fire)) {
				monster.Resistance[ItemConstant.ITEMINFO_FIRE] = getShort();
			}
			if (startWith(Resistance_Ice)) {
				monster.Resistance[ItemConstant.ITEMINFO_ICE] = getShort();
			}
			if (startWith(Resistance_Lighting)) {
				monster.Resistance[ItemConstant.ITEMINFO_LIGHTING] = getShort();
			}
			if (startWith(Resistance_Poison)) {
				monster.Resistance[ItemConstant.ITEMINFO_POISON] = getShort();
			}
			if (startWith(Resistance_Water)) {
				monster.Resistance[ItemConstant.ITEMINFO_WATER] = getShort();
			}
			if (startWith(Resistance_Wind)) {
				monster.Resistance[ItemConstant.ITEMINFO_WIND] = getShort();
			}

			// 视野
			if (startWith(SIGHT)) {
				int sight = getInt();
				monster.Real_Sight = sight;
				monster.Sight = sight * sight;
			}

			// 画面修正
			if (startWith(ARROW_POSI, 0)) {
				if (token.length > 1)
					monster.ArrowPosi[0] = getShort(0);
				if (token.length > 2)
					monster.ArrowPosi[1] = getShort(1);
			}

			/* ///////////以下为怪物特有属性 START//////////// */
			if (startWith(USE_EVENT_MODEL)) {
				monster.szModelName2 = getString();
				monster.UseEventModel = 1;
			}

			if (startWith(SKILL_DAMAGE, 2)) {
				monster.SkillDamage[0] = getShort(0);
				monster.SkillDamage[1] = getShort(1);
			}

			if (startWith(SKILL_DISTANCE)) {
				monster.SkillDistance = getInt();
			}
			if (startWith(SKILL_RANGE)) {
				monster.SkillRange = getInt();
			}
			if (startWith(SKILL_RATING)) {
				monster.SkillRating = getInt();
			}
			if (startWith(SKILL_CURSE)) {
				monster.SkillCurse = getInt();
			}
			if (startWith(MOVE_TYPE)) {
				// TODO 源码这里什么都没做
			}
			if (startWith(MOVE_RANGE)) {
				monster.MoveRange = (int) (getFloat() * CharInfo.fONE);
			}
			if (startWith(ACTIVE_HOUR)) {
				monster.ActiveHour = 0;
				if (ACTIVE_HOUR_DAY.contains(token[1])) {
					monster.ActiveHour = 1;
				}
				if (ACTIVE_HOUR_NIGHT.contains(token[1])) {
					monster.ActiveHour = -1;
				}
			}
			if (startWith(GENERATE_GROUP, 2)) {
				monster.GenerateGroup[0] = getInt(0);
				monster.GenerateGroup[1] = getInt(1);
			}
			if (startWith(IQ)) {
				monster.IQ = getInt();
			}
			if (startWith(CLASS_CODE)) {
				monster.ClassCode = getInt();
			}
			if (startWith(DAMAGE_STUN_PERS)) {
				monster.DamageStunPers = getInt();
			}
			if (startWith(MONSTER_NATURE)) {
				monster.Nature = CHAR_MONSTER_NATURAL;
				if (MONSTER_NATURE_GOOD.contains(token[1])) {
					monster.Nature = CHAR_MONSTER_GOOD;
				}
				if (MONSTER_NATURE_EVIL.contains(token[1])) {
					monster.Nature = CHAR_MONSTER_EVIL;
				}
			}
			if (startWith(EVENT_CODE)) {
				monster.EventCode = getInt();
			}
			if (startWith(EVENT_INFO)) {
				monster.EventInfo = getInt();
			}
			if (startWith(EVENT_ITEM)) {
				for (int cnt = 0; cnt < itemDataBase.length; cnt++) {
					if (itemDataBase[cnt].LastCategory
							.equalsIgnoreCase(token[1])) {
						monster.dwEvnetItem = itemDataBase[cnt].CODE;
						break;
					}
				}
			}
			if (startWith(ATTACK_PERCETAGE)) {
				monster.SpAttackPercetage = ConvPercent8(getInt());
			}
			if (startWith(UNDEAD)) {
				if (UNDEAD_YES.contains(token[1])) {
					monster.Undead = 1;// TODO boolean
					monster.Brood = CHAR_MONSTER_UNDEAD;
				} else {
					monster.Undead = 0;
					monster.Brood = CHAR_MONSTER_NORMAL;
				}
			}
			if (startWith(BROOD)) {
				if (BROOD_UNDEAD.contains(token[1])) {
					monster.Brood = CHAR_MONSTER_UNDEAD;
				} else if (BROOD_MUTANT.contains(token[1])) {
					monster.Brood = CHAR_MONSTER_MUTANT;
				} else if (BROOD_DEMON.contains(token[1])) {
					monster.Brood = CHAR_MONSTER_DEMON;
				} else if (BROOD_MECHANIC.contains(token[1])) {
					monster.Brood = CHAR_MONSTER_MECHANIC;
				} else {
					monster.Brood = CHAR_MONSTER_NORMAL;
				}
			}
			if (startWith(GET_EXP)) {
				monster.GetExp = getInt();
				monster.Exp = monster.GetExp;
			}
			if (startWith(POTION_COUNT)) {
				monster.PotionCount = getInt();
			}
			if (startWith(POTION_PERCENT)) {
				monster.PotionPercent = getInt();
			}

			if (startWith(FALLITEM_MAX_)) {
				monster.FallItemMax = getInt();
			}
			if (startWith(FALLITEM_PLUS, 2)) {
				int count = monster.FallItemPlusCount;
				if (count < FALLITEM2_MAX) {
					monster.FallItems_Plus[count] = new FallItem();
					// 掉落百分比
					monster.FallItems_Plus[count].Percentage = getInt();
					// 掉落物
					for (int cnt = 0; cnt < itemDataBase.length; cnt++) {
						if (itemDataBase[cnt].LastCategory.equalsIgnoreCase(token[2])) {
							monster.FallItems_Plus[count].dwItemCode = itemDataBase[cnt].CODE;
							monster.FallItemPlusCount++;
							break;
						}
					}
				}
			}
			if (startWith(FALLITEM)) {
				int percent = getInt();
				monster.FallItemPerMax += percent;
				
				int count = monster.FallItemCount;
				
				if (FALLITEM_NONE.contains(token[2])) {// 什么都不掉
					monster.FallItems[count] = new FallItem();
					monster.FallItems[count].dwItemCode = 0;
					monster.FallItems[count].Percentage = percent;
					monster.FallItemCount++;
				} else {
					if (FALLITEM_MONEY.contains(token[2])) {// 掉钱
						monster.FallItems[count] = new FallItem();
						monster.FallItems[count].dwItemCode = sinGG1|sin01;
						monster.FallItems[count].sPrice[0] = getShort(2);
						if (token.length > 4) {
							monster.FallItems[count].sPrice[1] = getShort(3);
						} else {
							monster.FallItems[count].sPrice[1] = getShort(2);
						}
						monster.FallItems[count].Percentage = percent;
						monster.FallItemCount++;
						
					} else {// 掉装备
						int ItemCodeCnt = 0;
						long dwItemCode[] = new long[32];
						
						// 查找装备编号
						for(int i=2; i<token.length; i++) {
							// 掉落物
							for (int cnt = 0; cnt < itemDataBase.length; cnt++) {
								if (itemDataBase[cnt].LastCategory.equalsIgnoreCase(token[i])) {
									dwItemCode[ItemCodeCnt++] = itemDataBase[cnt].CODE;
									break;
								}
							}
						}
						
						// 将装备添加到掉落列表中
						if (ItemCodeCnt > 0) {
							percent = percent/ItemCodeCnt;
							for(int i=0; i<ItemCodeCnt; i++) {
								count = monster.FallItemCount;
								monster.FallItems[count] = new FallItem();
								monster.FallItems[count].dwItemCode = dwItemCode[i];
								monster.FallItems[count].Percentage = percent;
								monster.FallItemCount++;
							}
						}
					}
				}
			}
			// 装备掉落列表
			if (startWith(ALL_SEE_ITEM, 0)) {
				monster.AllSeeItem = 1;// TODO boolean
			}
			/* ///////////以下为怪物特有属性 END//////////// */

			/* NPC */
			
			if (startWith(NPC_SELL_WEAPON, 2)) {
				
				// 查找装备编号
				for(int i=1; i<token.length; i++) {
					if (npc.SellAttackItemCount >= 32) break;
					
					if (token[i] .equals(NOTHING)) {
						break;
					}
					
					// 出售的商品列表
					for (int cnt = 0; cnt < itemDataBase.length; cnt++) {
						if (itemDataBase[cnt].LastCategory.equalsIgnoreCase(token[i])) {
							npc.SellAttackItem[npc.SellAttackItemCount++] = itemDataBase[cnt].CODE;
							break;
						}
					}
				}
			}
			if (startWith(NPC_SELL_ARMOR, 2)) {
				
				// 查找装备编号
				for(int i=1; i<token.length; i++) {
					if (npc.SellDefenceItemCount >= 32) break;
					
					if (token[i] .equals(NOTHING)) {
						break;
					}
					
					// 出售的商品列表
					for (int cnt = 0; cnt < itemDataBase.length; cnt++) {
						if (itemDataBase[cnt].LastCategory.equalsIgnoreCase(token[i])) {
							npc.SellDefenceItem[npc.SellDefenceItemCount++] = itemDataBase[cnt].CODE;
							break;
						}
					}
				}
			}
			if (startWith(NPC_SELL_ETC, 2)) {
				
				// 查找装备编号
				for(int i=1; i<token.length; i++) {
					if (npc.SellEtcItemCount >= 32) break;
					
					if (token[i] .equals(NOTHING)) {
						break;
					}
					// 出售的商品列表
					for (int cnt = 0; cnt < itemDataBase.length; cnt++) {
						if (itemDataBase[cnt].LastCategory.equalsIgnoreCase(token[i])) {
							npc.SellEtcItem[npc.SellEtcItemCount++] = itemDataBase[cnt].CODE;
							break;
						}
					}
				}
				
			}
			if (startWith(NPC_SKILL_MASTER, 0)) {
				npc.SkillMaster = 1; // TODO boolean
			}
			if (startWith(NPC_SKILL_CHANGE_JOB, 0)) {
				if (token.length > 1) {
					npc.SkillChangeJob = getInt();
				} else {
					npc.SkillChangeJob = 1;
				}
			}
			if (startWith(NPC_EVENT_NPC, 0)) {
				if (token.length > 1) {
					npc.EventNPC = getInt();
				} else {
					npc.EventNPC = 1;
				}
			}
			if (startWith(NPC_WAREHOUSE, 0)) {
				npc.WareHouseMaster = 1;
			}
			if (startWith(NPC_ITEM_MIX, 0)) {
				npc.ItemMix = 1;
			}
			if (startWith(NPC_ITEM_MIX_200, 0)) {
				npc.ItemMix = 200;
			}
			if (startWith(NPC_SMELTING, 0)) {
				npc.Smelting = 1;
			}
			if (startWith(NPC_MANUFACTURE, 0)) {
				npc.Manufacture = 1;
			}
			if (startWith(NPC_ITEM_AGING, 0)) {
				npc.ItemAging = 1;
			}
			if (startWith(NPC_MIXTURE_RESET, 0)) {
				npc.MixtureReset = 1;
			}
			if (startWith(NPC_COLLECT_MONEY, 0)) {
				npc.CollectMoney = 1;
			}
			if (startWith(NPC_EVENT_GIFT, 0)) {
				npc.EventGift = 1;
			}
			if (startWith(NPC_CLAN_NPC, 0)) {
				npc.ClanNPC = 1;
			}
			if (startWith(NPC_GIFT_EXPRESS, 0)) {
				npc.GiftExpress = 1;
			}
			if (startWith(NPC_FORCE_ORB, 0)) {
				npc.ForceOrbNPC = 1;
			}
			if (startWith(NPC_SOKET, 0)) {
				if (token.length > 1) {
					npc.SoketNPC = getInt();
				} else {
					npc.SoketNPC = 1;
				}
			}
			if (startWith(NPC_WING_QUEST_1, 0)) {
				if (token.length > 1) {
					npc.WingQuestNpc = getInt();
				} else {
					npc.WingQuestNpc = 1;
				}
			}
			if (startWith(NPC_WING_QUEST_2, 0)) {
				if (token.length > 1) {
					npc.WingQuestNpc = getInt();
				} else {
					npc.WingQuestNpc = 2;
				}
			}
			if (startWith(NPC_STAR_POINT, 0)) {
				if (token.length > 1) {
					npc.StarPointNpc = getInt();
				} else {
					npc.StarPointNpc = 20;
				}
			}
			if (startWith(NPC_GIVE_MONEY, 0)) {
				npc.GiveMoneyNpc = 1;
			}
			if (startWith(NPC_TELEPORT, 0)) {
				if (token.length > 1) {
					npc.TelePortNpc = getInt();
				} else {
					npc.TelePortNpc = 1;
				}
			}
			if (startWith(NPC_BLESS_CASTLE, 0)) {
				if (token.length > 1) {
					npc.BlessCastleNPC = getInt();
				} else {
					npc.BlessCastleNPC = 1;
				}
			}
			if (startWith(NPC_POLLING, 0)) {
				if (token.length > 1) {
					npc.PollingNpc = getInt();
				} else {
					npc.PollingNpc = 1;
				}
			}
			if (startWith(NPC_MEDIA_PLAY_TITLE)) {
				npc.szMediaPlayNPC_Title = setNPCMsg(getString());
			}
			if (startWith(NPC_MEDIA_PLAY_PATH)) {
				npc.szMediaPlayNPC_Path = setNPCMsg(getString());
			}
			if (startWith(NPC_OPEN_COUNT, 2)) {
				npc.OpenCount[0] = getShort(0);
				npc.OpenCount[1] = getShort(1);
			}
			if (startWith(NPC_QUEST_CODE, 2)) {
				npc.QuestCode = getInt(0);
				npc.QuestParam = getInt(1);
			}
			/* NPC END */
			if (startWith(Next_File)) {
				monster.NextFile = getString();
			}
		}
		return monster;
	}

	String szCharSizeCodeName[] = { "家屈|小型", "吝屈|中型", "吝措屈|中大型", "措屈|大型" };

	// TODO 这一段代码应该移到服务器常量中。
	int NpcMsgCount = 0;
	final static int SVR_NPC_MSG_MAX = 1024;
	String szSvr_NpcMsgs[] = new String[SVR_NPC_MSG_MAX];
	long dwSvr_NpcMsgCode[] = new long[SVR_NPC_MSG_MAX];

	/**
	 * 保存msg
	 * 
	 * @param msg
	 * @return
	 */
	private String setNPCMsg(String msg) {
		long code = 0l;
		int cnt = 0;

		if (NpcMsgCount >= SVR_NPC_MSG_MAX)
			return null;

		code = getSpeedSum(msg);

		for (cnt = 0; cnt < NpcMsgCount; cnt++) {
			if (code == dwSvr_NpcMsgCode[cnt] && msg.equals(szSvr_NpcMsgs[cnt])) {
				break;
			}
		}

		if (cnt < NpcMsgCount) {
			return szSvr_NpcMsgs[cnt];
		}

		dwSvr_NpcMsgCode[NpcMsgCount] = code;
		szSvr_NpcMsgs[NpcMsgCount++] = msg;

		return msg;
	}

	/**
	 * 计算字符串的Sum值
	 * 
	 * @param szName
	 * @return
	 */
	private long getSpeedSum(String szName) {
		int cnt;
		long Sum1, Sum2;
		byte ch;
		long dwSum;

		Sum2 = 0;
		dwSum = 0;

		cnt = 0;

		byte[] data = szName.getBytes();

		while (cnt < data.length) {
			ch = data[cnt];
			if (ch == 0)
				break;
			if (ch >= 'a' && ch <= 'z') {// 措巩磊 家巩磊肺
				Sum2 += (ch - 0x20) * (cnt + 1);
				dwSum += (ch - 0x20) * (cnt * cnt);
			} else {
				Sum2 += (ch * (cnt + 1));
				dwSum += ch * (cnt * cnt);
			}
			cnt++;
		}

		Sum1 = cnt;

		return (dwSum << 24) | (Sum1 << 16) | Sum2;
	}

	private int ConvPercent8(int percent100) {
		return (percent100 * 256) / 100;
	}

	private int ConvMoveSpeed(float fSpeed) {
		int sp;
		sp = (int) ((fSpeed - 9) * 16) + CharMonsterInfo.fONE;
		return sp;
	}

	@Override
	protected boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
}
