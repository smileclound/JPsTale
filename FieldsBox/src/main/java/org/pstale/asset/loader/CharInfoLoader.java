package org.pstale.asset.loader;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.pstale.asset.struct.chars.CharMonsterInfo;
import org.pstale.asset.struct.chars.CharSoundCode;
import org.pstale.asset.struct.chars.FallItem;

import static org.pstale.asset.struct.item.ItemConstant.*;

/**
 * 服务器的角色信息加载
 * @author yanmaoyuan
 *
 */
public class CharInfoLoader extends PTScriptLoader<CharMonsterInfo> {

	static Logger log = Logger.getLogger(CharInfoLoader.class);
	
	public final static int fONE = 256;

	public final static int CHAR_STATE_NPC = 0;
	public final static int CHAR_STATE_ENEMY = 1;
	public final static int CHAR_STATE_USER = 0x80;

	public final static int CHAR_MONSTER_NATURAL = 0x80;
	public final static int CHAR_MONSTER_GOOD = 0x81;
	public final static int CHAR_MONSTER_EVIL = 0x82;

	public final static int CHAR_MONSTER_NORMAL = 0x00;
	public final static int CHAR_MONSTER_UNDEAD = 0x90;
	public final static int CHAR_MONSTER_MUTANT = 0x91;
	public final static int CHAR_MONSTER_DEMON = 0x92;
	public final static int CHAR_MONSTER_MECHANIC = 0x93;

	public final static int CHAR_MONSTER_USER = 0xA0;

	public final static int COMMNAD_USER_WARP = 0x100;
	public final static int COMMNAD_USER_AGINGUP = 0x110;
	public final static int COMMNAD_USER_HOLY_INCANTATION = 0x120;
	public final static int COMMNAD_USER_RESURRECTION = 0x130;
	public final static int COMMNAD_USER_FORCEORB = 0x140;
	public final static int COMMNAD_USER_VALENTO = 0x150;
	public final static int COMMNAD_USER_FIREFLOWER = 0x160;

	public final static int COMMAND_PLAY_BABEL = 0x200;
	public final static int COMMAND_PLAY_BELSS_CASTLE = 0x210;
	public final static int COMMAND_PLAY_PK_FIELD = 0x220;
	public final static int COMMAND_PLAY_KELVEZU = 0x230;

	public final static int FALLITEM_MAX = 200;
	public final static int FALLITEM2_MAX = 3;
	public final static int NPC_MESSAGE_MAX = 20;
	
	public final static int MONSTER_CLASS_NORMAL	=	0;
	public final static int MONSTER_CLASS_BOSS		=	1;
	public final static int MONSTER_CLASS_HAMMER	=	200;
	public final static int MONSTER_CLASS_GHOST		=	300;
	

	public final static String NAME = "*捞抚|*名字";
	public final static String EN_NAME = "*Name|*NAME";
	
	// 模型
	public final static String MODEL_SIZE = "*葛胆农扁|*模型尺寸";
	public final static String MODEL_NAME = "*葛剧颇老|*外型文件";
	public final static String ARROW_POSI = "*拳搁焊沥|*画面修正";
	
	// 对话
	public final static String DIALOG_MSG = "*措拳|*对话";

	// 怪物类型
	public final static String STATE = "*加己|*属性";
	public final static String STATE_ENEMY = "利|怪物";
	public final static String STATE_NPC = "NPC";
	
	// 尺寸
	public final static String SIZE_LEVEL = "*农扁|*尺寸";
	public final static String SIZE_SMALL = "家屈|小型";
	public final static String SIZE_MIDDEL = "吝屈|中型";
	public final static String SIZE_LARGE = "吝措屈|中大型";
	public final static String SIZE_HUGE = "措屈|大型";
	public final static String SIZE_NONE = "绝澜|无";
	
	// 音效
	public final static String CHAR_SOUND_CODE = "*家府|*瓤苞澜|*音效";

	// 等级
	public final static String LEVEL = "*饭骇|*等级";
	
	// 类型
	public final static String PLAY_CLASS_BOSS = "*滴格";
	public final static String PLAY_CLASS_0 = "*拌鞭|*排名";
	
	// 移动能力
	public final static String MOVE_SPEED = "*捞悼仿|*捞悼加档|*移动速度|*运动";

	// 攻击能力
	public final static String ATTACK_DAMAGE = "*傍拜仿|*攻击力";
	public final static String ATTACK_SPEED = "*傍拜加档|*攻击速度";
	public final static String SHOOTING_RANGE = "*傍拜裹困|*攻击范围";
	public final static String ATTACK_RATING = "*疙吝仿|*命中";
	
	// 防御能力
	public final static String DEFENCE = "*规绢仿|*防御";
	public final static String ABSORPTION = "*软荐啦|*吸收率";
	public final static String CHANCE_BLOCK = "*喉钒啦|*格档率";
	
	// 生命值
	public final static String LIFE = "*积疙仿|*扼捞橇|*生命力";
	
	// 元素抗性
	public final static String Resistance_Bionic = "*积眉";
	public final static String Resistance_Earth = "*瘤悼仿|*措磊楷|*魔属性";
	public final static String Resistance_Fire = "*阂|*火属性";
	public final static String Resistance_Ice = "*趁扁|*倔澜|*冰属性";
	public final static String Resistance_Lighting = "*锅俺|*雷属性";
	public final static String Resistance_Poison = "*刀|*毒属性";
	public final static String Resistance_Water = "*拱";
	public final static String Resistance_Wind = "*官恩";
	
	public final static String SIGHT = "*矫具|*视野";
	
	////////////
	///////////////
	
	/* 怪物特有 START */
	
	// 怪物使用活动模型
	public final static String USE_EVENT_MODEL = "*抗厚葛胆";
	
	// 怪物技能
	public final static String SKILL_DAMAGE = "*扁贱傍拜仿|*技能攻击力";
	public final static String SKILL_DISTANCE = "*扁贱傍拜芭府|*技能攻击距离";
	public final static String SKILL_RANGE = "*扁贱傍拜裹困|*技能攻击范围";
	public final static String SKILL_RATING = "*扁贱傍拜伏|*技能攻击率";
	public final static String SKILL_CURSE = "*历林扁贱";
	
	// 移动类型
	public final static String MOVE_TYPE = "*捞悼鸥涝|*移动类型"; // TODO 源代码中没有赋值???
	public final static String MOVE_RANGE = "*捞悼裹困|*移动范围";
	
	// Active Hour
	public final static String ACTIVE_HOUR = "*劝悼矫埃|*活动时间";
	public final static String ACTIVE_HOUR_NO_LIMIT = "力茄绝澜|无限制";
	public final static String ACTIVE_HOUR_DAY = "撤";
	public final static String ACTIVE_HOUR_NIGHT = "广";
	
	public final static String GENERATE_GROUP = "*炼流|*组织";
	
	public final static String IQ = "*瘤瓷|*智力";
	
	public final static String CLASS_CODE = "*备喊内靛|*代码区分";

	public final static String DAMAGE_STUN_PERS = "*胶畔啦|*胶畔伏|*眩晕率";
	
	// 品质
	public final static String MONSTER_NATURE = "*前己|*品性";
	public final static String MONSTER_NATURE_EVIL = "evil|Evil|坏";
	public final static String MONSTER_NATURE_NATURAL = "neutral|Neutral";
	public final static String MONSTER_NATURE_GOOD = "good|Good";
	
	// 活动
	public final static String EVENT_CODE = "*捞亥飘内靛|*事件代码";
	public final static String EVENT_INFO = "*捞亥飘沥焊|*事件信息";
	public final static String EVENT_ITEM = "*捞亥飘酒捞袍|*事件物品";

	// 特殊攻击
	public final static String ATTACK_PERCETAGE = "*漂荐傍拜伏|*特殊攻击率";
	
	// 属性
	public final static String UNDEAD = "*攫单靛";
	public final static String UNDEAD_YES = "蜡|乐澜";
	
	public final static String BROOD = "*阁胶磐辆练|*怪物种族";
	public final static String BROOD_UNDEAD = "攫单靛";
	public final static String BROOD_MUTANT = "孤畔飘";
	public final static String BROOD_DEMON = "叼刚|恶魔系";
	public final static String BROOD_MECHANIC = "皋墨葱";

	// 经验值
	public final static String GET_EXP = "*版氰摹|*经验值";
	
	// 药水
	public final static String POTION_COUNT = "*拱距焊蜡荐|*药水储备";
	public final static String POTION_PERCENT = "*拱距焊蜡伏|*药水保留率";
	
	// 物品掉落
	public final static String ALL_SEE_ITEM = "*酒捞袍葛滴";
	public final static String FALLITEM_MAX_ = "*酒捞袍墨款磐|*物品数量";
	public final static String FALLITEM_PLUS = "*眠啊酒捞袍|*增加物品";
	public final static String FALLITEM = "*酒捞袍|*物品";
	public final static String FALLITEM_NONE = "绝澜|无";
	public final static String FALLITEM_MONEY = "捣|金";
	
	
	// NPC 特有属性 //////////////////////////////////////////////
	// 商店
	public final static String NPC_SELL_WEAPON = "*公扁魄概|*武器出售";
	public final static String NPC_SELL_ARMOR = "*规绢备魄概|*防具出售";
	public final static String NPC_SELL_ETC = "*棱拳魄概|*物品出售";
	public final static String NOTHING = "无";
	
	public final static String NPC_SKILL_MASTER = "*胶懦荐访|*技能修炼";
	public final static String NPC_SKILL_CHANGE_JOB = "*流诀傈券|*职业转换";

	public final static String NPC_EVENT_NPC = "*捞亥飘概钎家|*事件接受";

	public final static String NPC_WAREHOUSE = "*酒捞袍焊包|*物品保管";
	public final static String NPC_ITEM_MIX = "*酒捞袍炼钦|*物品合成";// 合成大师
	public final static String NPC_ITEM_MIX_200 = "*酒捞袍楷陛";
	public final static String NPC_SMELTING = "*酒捞袍力访";// 装备制炼 3个矿石 + 1神石
	public final static String NPC_MANUFACTURE = "*酒捞袍力累|*物品冶炼";// 萨拉娜  TODO 道具合成???
	public final static String NPC_ITEM_AGING = "*酒捞袍俊捞隆|*物品锻造";// 神秘的巴云
	public final static String NPC_MIXTURE_RESET = "*雇胶贸府悸|*合成恢复";// 珊蒂
	public final static String NPC_COLLECT_MONEY = "*葛陛窃"; // TODO 不认识的NPC
	public final static String NPC_EVENT_GIFT = "*版前眠梅";
	public final static String NPC_CLAN_NPC = "*努罚扁瓷|*公会管理";
	public final static String NPC_GIFT_EXPRESS = "*版前硅崔|*物品发放";
	public final static String NPC_WING_QUEST_1 = "*喇涅胶飘|*前线任务";
	public final static String NPC_WING_QUEST_2 = "*涅胶飘捞亥飘|*任务事件";
	public final static String NPC_STAR_POINT = "*喊器牢飘利赋|*星星积分";
	public final static String NPC_GIVE_MONEY = "*扁何窃"; // TODO 不认识的NPC
	public final static String NPC_TELEPORT = "*炮饭器飘|*移动";
	public final static String NPC_BLESS_CASTLE = "*喉饭胶某浇|*祝福城堡";
	public final static String NPC_POLLING = "*汲巩炼荤"; // TODO 不认识的NPC

	public final static String NPC_MEDIA_PLAY_TITLE = "*悼康惑力格"; // TODO 不认识的NPC
	public final static String NPC_MEDIA_PLAY_PATH = "*悼康惑版肺"; // TODO 不认识的NPC
	
	public final static String NPC_OPEN_COUNT = "*免泅埃拜|*出现间隔"; // TODO 不认识的NPC
	public final static String NPC_QUEST_CODE = "*涅胶飘内靛|*任务代码";

	// TODO 下面的代码，在C++源码中不存在
	public final static String NPC_SOKET = "*打孔镶嵌";
	public final static String NPC_FORCE_ORB = "*力量石制造";// 力量大师
	
	public final static String NPC_QUEST_EVENT = "*任务事件";
	
	public final static String NPC_ACTION_CODE = "*事件代码";
	public final static String NPC_ACTION_INFO = "*事件信息";
	public final static String NPC_ACTION_HOUR = "*出现间隔";
	
	/* 怪物特有 END*/
	
	// 链接文件
	public final static String Next_File= "*楷搬颇老";

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
		monster.MoveRange = 64 * fONE;
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
				if (STATE_ENEMY.contains(value)) {
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
				for (int cnt = 0; cnt < CharSoundCode.dwCharSoundCode.length; cnt++) {
					if (CharSoundCode.dwCharSoundCode[cnt].szCodeName.equalsIgnoreCase(value)) {
						monster.dwCharSoundCode = CharSoundCode.dwCharSoundCode[cnt].dwCode;
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
				monster.wPlayClass[0] = (short) getInt(0);
			}

			// 模型尺寸
			if (startWith(MODEL_SIZE)) {
				monster.wPlayClass[1] = (short) (getFloat() * fONE);
				if (monster.wPlayClass[1] == fONE)
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
				monster.Attack_Speed = (int) getFloat() * fONE;
				// TODO 源码这里计算了2次一模一样的攻速速度，不明白为什么
			}
			if (startWith(SHOOTING_RANGE)) {
				monster.Shooting_Range = getInt() * fONE;
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
				monster.Resistance[ITEMINFO_BIONIC] = getShort();
			}
			if (startWith(Resistance_Earth)) {
				monster.Resistance[ITEMINFO_EARTH] = getShort();
			}
			if (startWith(Resistance_Fire)) {
				monster.Resistance[ITEMINFO_FIRE] = getShort();
			}
			if (startWith(Resistance_Ice)) {
				monster.Resistance[ITEMINFO_ICE] = getShort();
			}
			if (startWith(Resistance_Lighting)) {
				monster.Resistance[ITEMINFO_LIGHTING] = getShort();
			}
			if (startWith(Resistance_Poison)) {
				monster.Resistance[ITEMINFO_POISON] = getShort();
			}
			if (startWith(Resistance_Water)) {
				monster.Resistance[ITEMINFO_WATER] = getShort();
			}
			if (startWith(Resistance_Wind)) {
				monster.Resistance[ITEMINFO_WIND] = getShort();
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
				monster.MoveRange = (int) (getFloat() * fONE);
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
					if (itemDataBase[cnt].category.equalsIgnoreCase(token[1])) {
						monster.dwEvnetItem = itemDataBase[cnt].code;
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
						if (itemDataBase[cnt].category.equalsIgnoreCase(token[2])) {
							monster.FallItems_Plus[count].dwItemCode = itemDataBase[cnt].code;
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
								if (itemDataBase[cnt].category.equalsIgnoreCase(token[i])) {
									dwItemCode[ItemCodeCnt++] = itemDataBase[cnt].code;
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
						if (itemDataBase[cnt].category.equalsIgnoreCase(token[i])) {
							npc.SellAttackItem[npc.SellAttackItemCount++] = itemDataBase[cnt].code;
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
						if (itemDataBase[cnt].category.equalsIgnoreCase(token[i])) {
							npc.SellDefenceItem[npc.SellDefenceItemCount++] = itemDataBase[cnt].code;
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
						if (itemDataBase[cnt].category.equalsIgnoreCase(token[i])) {
							npc.SellEtcItem[npc.SellEtcItemCount++] = itemDataBase[cnt].code;
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
		sp = (int) ((fSpeed - 9) * 16) + fONE;
		return sp;
	}
	
}
