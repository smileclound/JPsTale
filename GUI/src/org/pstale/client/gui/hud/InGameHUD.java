package org.pstale.client.gui.hud;

import org.pstale.client.gui.GroupWindow;
import org.pstale.client.gui.InfoPanel;
import org.pstale.client.gui.InventoryWindow;
import org.pstale.client.gui.MiniMap;
import org.pstale.client.gui.QuestWindow;
import org.pstale.client.gui.SkillWindow;
import org.pstale.client.gui.StatsWindow;
import org.pstale.client.gui.SystemPanel;

import tonegod.gui.controls.buttons.Button;
import tonegod.gui.controls.buttons.ButtonAdapter;
import tonegod.gui.controls.extras.ChatBox;
import tonegod.gui.controls.extras.Indicator;
import tonegod.gui.controls.windows.Panel;
import tonegod.gui.core.Element;
import tonegod.gui.core.Element.Orientation;
import tonegod.gui.core.Screen;
import tonegod.gui.effects.Effect;
import tonegod.gui.effects.Effect.EffectEvent;
import tonegod.gui.effects.Effect.EffectType;

import com.jme3.app.Application;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.event.MouseButtonEvent;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector4f;

public class InGameHUD extends HUD {

	private InventoryWindow inventoryWnd = null;// 道具窗口
	private StatsWindow statsWnd = null;// 属性窗口
	private GroupWindow groupWnd = null;// 组队窗口
	private SkillWindow skillWnd = null;// 技能窗口
	private MiniMap mapWnd = null;// 小地图
	private QuestWindow questWnd = null;// 任务窗口
	private SystemPanel sysPanel = null;// 系统菜单
	private ChatBox chatBox = null;// 聊天窗口

	private Indicator expBar = null;// 经验条

	// 按钮
	private Button invBtn = null;
	private Button groupBtn = null;
	private Button questBtn = null;
	private Button skillBtn = null;
	private Button statusBtn = null;
	private Button sysBtn = null;

	private InputManager inputManager;

	public InGameHUD(Screen screen) {
		super(screen);

		Application app = screen.getApplication();
		this.inputManager = app.getInputManager();
	}

	/**
	 * 窗口大小变化后，HUD需要根据窗口大小重新调整UI布局。
	 */
	@Override
	public void resize(float width, float height) {
		playinfo.setPosition(0, height - 55);
		targetinfo.setPosition(200, height - 55);
		
		contentPanel.setPosition(width - 216, 0);

		chatBox.setPosition(0, 0);
		
		sysPanel.resize(width, height);

		mapWnd.setPosition(width - 160 - 20, 48 + 20);
	}

	@Override
	public void initialize() {
		screen.addElement(getChatBox());
		screen.addElement(getContentPanel());

		screen.addElement(playerInfoPanel());
		screen.addElement(targetPanel());

		screen.addElement(getInvWnd());
		screen.addElement(getSkillWnd());
		screen.addElement(getQuestWnd());
		screen.addElement(getStatsWnd());
		screen.addElement(getGroupWnd());
		screen.addElement(getMiniMap());
		screen.addElement(getSysPanel());

		inventoryWnd.hide();
		skillWnd.hide();
		questWnd.hide();
		statsWnd.hide();
		groupWnd.hide();
		sysPanel.hide();

		initKeys();
	}

	@Override
	public void cleanup() {
		screen.removeElement(getChatBox());
		screen.removeElement(getContentPanel());

		screen.removeElement(playerInfoPanel());
		screen.removeElement(targetPanel());

		screen.removeElement(getInvWnd());
		screen.removeElement(getSkillWnd());
		screen.removeElement(getQuestWnd());
		screen.removeElement(getStatsWnd());
		screen.removeElement(getGroupWnd());
		screen.removeElement(getMiniMap());
		screen.removeElement(getSysPanel());

		removeKeys();
	}

	// 按键监听器
	private ActionListener inGameHudListener;

	private void initKeys() {
		if (inGameHudListener == null) {

			inGameHudListener = new ActionListener() {
				public void onAction(String name, boolean isPressed, float tpf) {
					if (isPressed) {
						if ("inven".equals(name)) {
							toggleInventoryWnd();
						}
						if ("skill".equals(name)) {
							toggleSkillWnd();
						}
						if ("quest".equals(name)) {
							toggleQuestWnd();
						}
						if ("stats".equals(name)) {
							toggleStatsWnd();
						}
						if ("group".equals(name)) {
							toggleGroupWnd();
						}
						if ("esc".equals(name)) {
							toggleEsc();
						}
						if ("cam".equals(name)) {
							toggleCameraMode();
						}
						if ("run".equals(name)) {
							toggleRunMode();
						}
						if ("minimap".equals(name)) {
							toggleMiniMap();
						}
					}
				}
			};

			inputManager.addMapping("inven", new KeyTrigger(KeyInput.KEY_V));
			inputManager.addMapping("skill", new KeyTrigger(KeyInput.KEY_S));
			inputManager.addMapping("quest", new KeyTrigger(KeyInput.KEY_Q));
			inputManager.addMapping("stats", new KeyTrigger(KeyInput.KEY_C));
			inputManager.addMapping("group", new KeyTrigger(KeyInput.KEY_G));
			inputManager.addMapping("esc", new KeyTrigger(KeyInput.KEY_ESCAPE));
			inputManager.addMapping("cam", new KeyTrigger(KeyInput.KEY_Z));
			inputManager.addMapping("run", new KeyTrigger(KeyInput.KEY_R));
			inputManager.addMapping("minimap", new KeyTrigger(KeyInput.KEY_M));

			inputManager.addListener(inGameHudListener, "inven", "skill",
					"quest", "stats", "group", "esc");
			inputManager
					.addListener(inGameHudListener, "cam", "run", "minimap");
		}
	}

	private void removeKeys() {
		inputManager.removeListener(inGameHudListener);
		inputManager.clearMappings();
	}

	/**
	 * 显示角色的头像、名称、血量、蓝量、耐力
	 * 
	 * @return
	 */
	InfoPanel playinfo = null;

	private Element playerInfoPanel() {
		if (playinfo == null) {
			playinfo = new InfoPanel(screen, "hero", Vector2f.ZERO);
		}
		return playinfo;
	}

	InfoPanel targetinfo = null;

	private Element targetPanel() {
		// TODO 显示目标的头像、名称、血量、蓝量、耐力
		if (targetinfo == null) {
			targetinfo = new InfoPanel(screen, "target", new Vector2f(190, 0));
		}
		return targetinfo;
	}

	private Element getSysPanel() {
		if (sysPanel == null) {
			sysPanel = new SystemPanel(screen, this);
		}
		return sysPanel;
	}

	private Element getGroupWnd() {
		if (groupWnd == null) {
			groupWnd = new GroupWindow(screen);
		}
		return groupWnd;
	}

	private Element getStatsWnd() {
		if (statsWnd == null) {
			statsWnd = new StatsWindow(screen);
		}
		return statsWnd;
	}

	private Element getSkillWnd() {
		if (skillWnd == null) {
			skillWnd = new SkillWindow(screen);
		}
		return skillWnd;
	}

	private Element getQuestWnd() {
		if (questWnd == null) {
			questWnd = new QuestWindow(screen);
		}
		return questWnd;
	}

	private Element getInvWnd() {
		if (inventoryWnd == null) {
			inventoryWnd = new InventoryWindow(screen);
		}
		return inventoryWnd;
	}

	/**
	 * 屏幕上方一排，显示角色的buff
	 * 
	 * @return
	 */
	protected Element getBuffPanel() {
		// TODO
		return null;
	}

	/**
	 * 屏幕右下角，显示小地图。
	 * 
	 * @return
	 */
	private Element getMiniMap() {
		if (mapWnd == null) {
			float posX = screenWidth - 160 - 20;
			float posY = screenHeight - 190 - 48 - 20;
			mapWnd = new MiniMap(screen, new Vector2f(posX, posY));
			mapWnd.setWindowTitle("理查登");
			mapWnd.setMap("Textures/Map/town_RicaX-88Y-77.bmp");
		}
		return mapWnd;
	}

	/**
	 * 屏幕左下角，显示聊天窗口
	 * 
	 * @return
	 */
	private Element getChatBox() {
		if (chatBox == null) {
			chatBox = new ChatBox(screen, new Vector2f(0, screenHeight - 150),
					new Vector2f(300, 150)) {
				@Override
				public void onSendMsg(String msg) {
					if (msg != null && msg.trim().length() > 0)
						receiveMsg("ME:" + msg);
				}
			};
			chatBox.setResizeBorders(2, 2, 2, 2);
		}
		return chatBox;
	}

	/**
	 * 主面板，沉底。分为上下2部分
	 * 
	 * @return
	 */
	private Panel contentPanel;

	private Element getContentPanel() {
		if (contentPanel == null) {
			float posX = screenWidth - 216;
			float posY = screenHeight - 30;
			contentPanel = new Panel(screen, "contentPanel", new Vector2f(posX,
					posY), new Vector2f(216, 30), Vector4f.ZERO, null);
			contentPanel.setIgnoreMouse(true);

			contentPanel.addChild(getTogglePanel());
			contentPanel.addChild(getMenuPanel());
			contentPanel.addChild(getExpBar());
		}
		return contentPanel;
	}

	/**
	 * 经验条
	 * 
	 * @return
	 */
	private Element getExpBar() {
		if (expBar == null) {
			expBar = new Indicator(screen, "hud_expBar", new Vector2f(0, 25),
					new Vector2f(216, 5), Vector4f.ZERO, null,
					Orientation.HORIZONTAL) {
				@Override
				public void onChange(float currentValue, float currentPercentage) {
				}
			};
			expBar.setIndicatorImage("Textures/InGameHUD/Bar_Exp.bmp");
			expBar.setMaxValue(100);
			expBar.setCurrentValue(100);
		}
		return expBar;
	}

	/**
	 * 功能面板，用于容纳菜单键、技能、装备、属性等功能按钮。
	 * 
	 * @return
	 */
	private Element getMenuPanel() {
		Panel functionPanel = new Panel(screen, "hud_menuPanel", new Vector2f(
				72, 0), new Vector2f(144, 24), Vector4f.ZERO, null);
		functionPanel.setIgnoreMouse(true);
		functionPanel.setGlobalAlpha(0.7f);

		functionPanel.addChild(getSkillBtn());
		functionPanel.addChild(getStatusBtn());
		functionPanel.addChild(getInventoryBtn());
		functionPanel.addChild(getQuestBtn());
		functionPanel.addChild(getGroupBtn());
		functionPanel.addChild(getSystemBtn());
		return functionPanel;
	}

	/**
	 * 技能按钮，点击之后将显示/隐藏技能窗口
	 * 
	 * @return
	 */
	private Button getSkillBtn() {
		if (skillBtn == null) {
			skillBtn = new ButtonAdapter(screen, "hud_skillbtn", new Vector2f(
					0, 0), new Vector2f(25, 24), Vector4f.ZERO,
					"Textures/InGameHUD/bSkill-1.bmp") {
				public void onButtonMouseLeftUp(MouseButtonEvent evt,
						boolean toggled) {
					toggleSkillWnd();
				}
			};
			skillBtn.setButtonHoverInfo("Textures/InGameHUD/bSkill.bmp",
					ColorRGBA.Orange);
			skillBtn.setButtonPressedInfo("Textures/InGameHUD/bSkill-1.bmp",
					ColorRGBA.White);
			skillBtn.removeEffect(Effect.EffectEvent.Hover);
			skillBtn.addEffect(new Effect(EffectType.ImageSwap,
					EffectEvent.Hover, 0));
		}
		return skillBtn;
	}

	protected void toggleSkillWnd() {
		if (skillWnd.getIsVisible()) {
			skillWnd.hide();
		} else {
			skillWnd.show();
		}
	}

	/**
	 * 属性栏按钮
	 * 
	 * @return
	 */
	private Element getStatusBtn() {
		if (statusBtn == null) {
			statusBtn = new ButtonAdapter(screen, "hud_statusbtn",
					new Vector2f(24, 0), new Vector2f(25, 24), Vector4f.ZERO,
					"Textures/InGameHUD/bStatus-1.bmp") {
				public void onButtonMouseLeftUp(MouseButtonEvent evt,
						boolean toggled) {
					toggleStatsWnd();
				}
			};
			statusBtn.setButtonHoverInfo("Textures/InGameHUD/bStatus.bmp",
					ColorRGBA.Orange);
			statusBtn.setButtonPressedInfo("Textures/InGameHUD/bStatus.bmp",
					ColorRGBA.White);
			statusBtn.removeEffect(Effect.EffectEvent.Hover);
			statusBtn.addEffect(new Effect(EffectType.ImageSwap,
					EffectEvent.Hover, 0));
		}
		return statusBtn;
	}

	protected void toggleStatsWnd() {
		if (statsWnd.getIsVisible()) {
			statsWnd.hide();
		} else {
			statsWnd.show();
		}
	}

	/**
	 * 装备栏
	 * 
	 * @return
	 */
	private Element getInventoryBtn() {
		if (invBtn == null) {
			invBtn = new ButtonAdapter(screen, "hud_invbtn",
					new Vector2f(48, 0), new Vector2f(25, 24), Vector4f.ZERO,
					"Textures/InGameHUD/bInvenTory-1.bmp") {
				public void onButtonMouseLeftUp(MouseButtonEvent evt,
						boolean toggled) {
					toggleInventoryWnd();
				}
			};
			invBtn.setButtonHoverInfo("Textures/InGameHUD/bInvenTory.bmp",
					ColorRGBA.Orange);
			invBtn.setButtonPressedInfo("Textures/InGameHUD/bInvenTory.bmp",
					ColorRGBA.White);
			invBtn.removeEffect(Effect.EffectEvent.Hover);
			invBtn.addEffect(new Effect(EffectType.ImageSwap,
					EffectEvent.Hover, 0));
		}
		return invBtn;
	}

	protected void toggleInventoryWnd() {
		if (inventoryWnd.getIsVisible()) {
			inventoryWnd.hide();
		} else {
			inventoryWnd.show();
		}
	}

	/**
	 * 任务按钮
	 * 
	 * @return
	 */
	private Element getQuestBtn() {
		if (questBtn == null) {
			questBtn = new ButtonAdapter(screen, "hud_questbtn", new Vector2f(
					72, 0), new Vector2f(25, 24), Vector4f.ZERO,
					"Textures/InGameHUD/bQuest-1.bmp") {
				public void onButtonMouseLeftUp(MouseButtonEvent evt,
						boolean toggled) {
					toggleQuestWnd();
				}
			};
			questBtn.setButtonHoverInfo("Textures/InGameHUD/bQuest.bmp",
					ColorRGBA.Orange);
			questBtn.setButtonPressedInfo("Textures/InGameHUD/bQuest.bmp",
					ColorRGBA.White);
			questBtn.removeEffect(Effect.EffectEvent.Hover);
			questBtn.addEffect(new Effect(EffectType.ImageSwap,
					EffectEvent.Hover, 0));
		}
		return questBtn;
	}

	protected void toggleQuestWnd() {
		if (questWnd.getIsVisible()) {
			questWnd.hide();
		} else {
			questWnd.show();
		}
	}

	/**
	 * 组队按钮
	 * 
	 * @return
	 */
	private Element getGroupBtn() {
		if (groupBtn == null) {
			groupBtn = new ButtonAdapter(screen, "hud_groupbtn", new Vector2f(
					96, 0), new Vector2f(25, 24), Vector4f.ZERO,
					"Textures/InGameHUD/bParty-1.bmp") {
				public void onButtonMouseLeftUp(MouseButtonEvent evt,
						boolean toggled) {
					toggleGroupWnd();
				}
			};
			groupBtn.setButtonHoverInfo("Textures/InGameHUD/bParty.bmp",
					ColorRGBA.Orange);
			groupBtn.setButtonPressedInfo("Textures/InGameHUD/bParty.bmp",
					ColorRGBA.White);
			groupBtn.removeEffect(Effect.EffectEvent.Hover);
			groupBtn.addEffect(new Effect(EffectType.ImageSwap,
					EffectEvent.Hover, 0));
		}
		return groupBtn;
	}

	protected void toggleGroupWnd() {
		if (groupWnd.getIsVisible()) {
			groupWnd.hide();
		} else {
			groupWnd.show();
		}
	}

	/**
	 * 菜单按钮
	 * 
	 * @return
	 */
	private Element getSystemBtn() {
		if (sysBtn == null) {
			sysBtn = new ButtonAdapter(screen, "hud_sysbtn", new Vector2f(120,
					0), new Vector2f(25, 24), Vector4f.ZERO,
					"Textures/InGameHUD/bSystem-1.bmp") {
				public void onButtonMouseLeftUp(MouseButtonEvent evt,
						boolean toggled) {
					toggleEsc();
				}
			};
			sysBtn.setButtonHoverInfo("Textures/InGameHUD/bSystem.bmp",
					ColorRGBA.Orange);
			sysBtn.setButtonPressedInfo("Textures/InGameHUD/bSystem.bmp",
					ColorRGBA.White);
			sysBtn.removeEffect(Effect.EffectEvent.Hover);
			sysBtn.addEffect(new Effect(EffectType.ImageSwap,
					EffectEvent.Hover, 0));
		}
		return sysBtn;
	}

	protected void toggleEsc() {
		if (sysPanel.getIsVisible()) {
			sysPanel.hide();
			sysPanel.onBackButtonClick();
		} else {
			sysPanel.show();
		}
	}

	Button camBtn;// 切换镜头模式
	Button mapBtn;// 打开关闭小地图
	Button runBtn;// 切换走/跑

	/**
	 * 开关面板
	 * 
	 * @return
	 */
	private Element getTogglePanel() {
		Panel togglePanel = new Panel(screen, "hud_menuPanel", new Vector2f(0,
				0), new Vector2f(72, 24), Vector4f.ZERO, null);
		togglePanel.setIgnoreMouse(true);

		togglePanel.addChild(getCameraBtn());
		togglePanel.addChild(getMapBtn());
		togglePanel.addChild(getRunBtn());
		return togglePanel;
	}

	/**
	 * 摄像头按钮 快捷键Z
	 * 
	 * @return
	 */
	private Button getCameraBtn() {
		if (camBtn == null) {
			camBtn = new ButtonAdapter(screen, "hud_cambtn",
					new Vector2f(0, 0), new Vector2f(24, 24), Vector4f.ZERO,
					"Textures/InGameHUD/AutoCameraImage.bmp") {
				public void onButtonMouseLeftUp(MouseButtonEvent evt,
						boolean toggled) {
					toggleCameraMode();
				}
			};
			camBtn.setButtonPressedInfo(
					"Textures/InGameHUD/PixCameraImage.bmp", ColorRGBA.White);
			camBtn.removeEffect(Effect.EffectEvent.Hover);
		}
		return camBtn;
	}

	protected void toggleCameraMode() {
		// TODO
	}

	/**
	 * 小地图开关
	 * 
	 * @return
	 */
	private Element getMapBtn() {
		if (mapBtn == null) {
			mapBtn = new ButtonAdapter(screen, "hud_mapbtn",
					new Vector2f(24, 0), new Vector2f(24, 24), Vector4f.ZERO,
					"Textures/InGameHUD/MapOnImage.bmp") {
				public void onButtonMouseLeftUp(MouseButtonEvent evt,
						boolean toggled) {
					toggleMiniMap();
				}
			};
			mapBtn.removeEffect(Effect.EffectEvent.Hover);
		}
		return mapBtn;
	}

	protected void toggleMiniMap() {
		if (mapWnd.getIsVisible()) {
			mapWnd.hide();
		} else {
			mapWnd.show();
		}
	}

	/**
	 * 走/跑开关
	 * 
	 * @return
	 */
	private Element getRunBtn() {
		if (runBtn == null) {
			runBtn = new ButtonAdapter(screen, "hud_runbtn",
					new Vector2f(48, 0), new Vector2f(24, 24), Vector4f.ZERO,
					"Textures/InGameHUD/Walk.bmp") {
				public void onButtonMouseLeftUp(MouseButtonEvent evt,
						boolean toggled) {
					toggleRunMode();
				}
			};
			runBtn.removeEffect(Effect.EffectEvent.Hover);
		}
		return runBtn;
	}

	protected void toggleRunMode() {
		// TODO
	}
}
