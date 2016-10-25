package org.pstale.client.gui;

import org.pstale.client.Game;
import org.pstale.client.gui.hud.HUD;

import com.jme3.app.Application;
import com.jme3.font.BitmapFont;
import com.jme3.input.event.MouseButtonEvent;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector2f;

import tonegod.gui.controls.buttons.Button;
import tonegod.gui.controls.buttons.ButtonAdapter;
import tonegod.gui.controls.windows.Panel;
import tonegod.gui.core.Screen;

public class SystemPanel extends Panel {
	// 父屏幕
	private Screen screen;
	private float width;
	private float height;
	private HUD parentHUD;

	// 自身的高宽
	private static float WIDTH = 160;
	private static float HEIGHT = 320;

	private Vector2f btnSize = new Vector2f(120, 24);
	
	// 控件定义
	private ButtonAdapter helpbtn;// 帮助
	private ButtonAdapter marketbtn;// 商城

	private ButtonAdapter optionbtn;// 系统选项
	private ButtonAdapter uibtn;// 界面设置
	private ButtonAdapter keybtn;// 按键设置

	private ButtonAdapter selectherobtn;// 返回角色选择
	private ButtonAdapter quitbtn;// 退出游戏

	private ButtonAdapter backbtn;// 返回游戏

	/**
	 * 构造方法
	 * @param screen
	 */
	public SystemPanel(Screen screen, HUD parent) {
		super(screen, "hud_syspanel", Vector2f.ZERO,
				new Vector2f(WIDTH, HEIGHT));
		this.screen = screen;
		this.parentHUD = parent;
		
		width = screen.getWidth();
		height = screen.getHeight();

		this.setIsModal(true);
		this.setIsResizable(false);
		this.setIsMovable(false);
		this.getMaterial().setColor("Color", ColorRGBA.Pink);

		initialize();
		resize();
	}

	private void initialize() {
		addChild(getHelpBtn());
		addChild(getMarketBtn());

		addChild(getOptionBtn());
		addChild(getUIBtn());
		addChild(getKeyBtn());

		addChild(getSelectHeroBtn());
		addChild(getQuitBtn());

		addChild(getBackBtn());
		
		
		helpbtn.getMaterial().setColor("Color", ColorRGBA.Red);
		marketbtn.getMaterial().setColor("Color", ColorRGBA.Red);
		optionbtn.getMaterial().setColor("Color", ColorRGBA.Red);
		uibtn.getMaterial().setColor("Color", ColorRGBA.Red);
		keybtn.getMaterial().setColor("Color", ColorRGBA.Red);
		selectherobtn.getMaterial().setColor("Color", ColorRGBA.Red);
		quitbtn.getMaterial().setColor("Color", ColorRGBA.Red);
		backbtn.getMaterial().setColor("Color", ColorRGBA.Red);
	}

	private void resize() {
		float posX;
		float posY;

		posX = (width - WIDTH) / 2;
		posY = (height - HEIGHT) / 2;
		this.setPosition(posX, posY);

	}

	public void resize(float width, float height) {
		this.width = width;
		this.height = height;
		resize();
	}

	/**
	 * 帮助按钮
	 * 
	 * @return
	 */
	private Button getHelpBtn() {
		if (helpbtn == null) {
			float posX = (WIDTH - btnSize.x)/ 2;
			float posY = 30;
			helpbtn = new ButtonAdapter(screen, "sys_helpbtn", new Vector2f(
					posX, posY), btnSize) {
				@Override
				public void onButtonMouseLeftUp(MouseButtonEvent evt,
						boolean toggled) {
					onHelpButtonClick();
				}
			};
			helpbtn.setText("Help");
			helpbtn.setTextAlign(BitmapFont.Align.Center);
		}
		return helpbtn;
	}
	// 帮助
	protected void onHelpButtonClick() {
		// TODO
	}

	/**
	 * 商城按钮
	 * 
	 * @return
	 */
	private Button getMarketBtn() {
		if (marketbtn == null) {
			float posX = (WIDTH - btnSize.x)/ 2;
			float posY = 54;
			marketbtn = new ButtonAdapter(screen, "sys_marketbtn",
					new Vector2f(posX, posY), btnSize) {
				@Override
				public void onButtonMouseLeftUp(MouseButtonEvent evt,
						boolean toggled) {
					onMarketButtonClick();
				}
			};
			marketbtn.setText("Market");
			marketbtn.setTextAlign(BitmapFont.Align.Center);
		}
		return marketbtn;
	}

	// 商城
	protected void onMarketButtonClick() {
		// TODO
	}

	/**
	 * 系统选项
	 * 
	 * @return
	 */
	private Button getOptionBtn() {
		if (optionbtn == null) {
			float posX = (WIDTH - btnSize.x)/ 2;
			float posY = 102;
			optionbtn = new ButtonAdapter(screen, "sys_optionbtn",
					new Vector2f(posX, posY), btnSize) {
				@Override
				public void onButtonMouseLeftUp(MouseButtonEvent evt,
						boolean toggled) {
					onOptionButtonClick();
				}
			};
			optionbtn.setText("Option");
			optionbtn.setTextAlign(BitmapFont.Align.Center);
		}
		return optionbtn;
	}

	// 系统选项
	protected void onOptionButtonClick() {
		// TODO
	}

	/**
	 * 按键设置
	 * 
	 * @return
	 */
	private Button getKeyBtn() {
		if (keybtn == null) {
			float posX = (WIDTH - btnSize.x)/ 2;
			float posY = 126;
			keybtn = new ButtonAdapter(screen, "sys_keybtn", new Vector2f(posX,
					posY), btnSize) {
				@Override
				public void onButtonMouseLeftUp(MouseButtonEvent evt,
						boolean toggled) {
					onKeyButtonClick();
				}
			};
			keybtn.setText("Keys");
			keybtn.setTextAlign(BitmapFont.Align.Center);
		}
		return keybtn;
	}

	// 按键设置
	protected void onKeyButtonClick() {
		// TODO
	}

	/**
	 * 界面设置
	 * 
	 * @return
	 */
	private Button getUIBtn() {
		if (uibtn == null) {
			float posX = (WIDTH - btnSize.x)/ 2;
			float posY = 150;
			uibtn = new ButtonAdapter(screen, "sys_uibtn", new Vector2f(posX,
					posY), btnSize) {
				@Override
				public void onButtonMouseLeftUp(MouseButtonEvent evt,
						boolean toggled) {
					onUIButtonClick();
				}
			};
			uibtn.setText("UI");
			uibtn.setTextAlign(BitmapFont.Align.Center);
		}
		return uibtn;
	}

	private UIPanel uiPanel;
	// 界面设置
	protected void onUIButtonClick() {
		if (uiPanel == null) {
			uiPanel = new UIPanel(screen, parentHUD);
			uiPanel.hide();
		}
		
		if (uiPanel.getIsVisible()) {
			uiPanel.hide();
		} else {
			uiPanel.show();
		}
	}

	/**
	 * 返回角色选择
	 * 
	 * @return
	 */
	private Button getSelectHeroBtn() {
		if (selectherobtn == null) {
			float posX = (WIDTH - btnSize.x)/ 2;
			float posY = 198;
			selectherobtn = new ButtonAdapter(screen, "sys_selectherobtn",
					new Vector2f(posX, posY), btnSize) {
				@Override
				public void onButtonMouseLeftUp(MouseButtonEvent evt,
						boolean toggled) {
					onSelectHeroButtonClick();
				}
			};
			selectherobtn.setText("Select Hero");
			selectherobtn.setTextAlign(BitmapFont.Align.Center);
		}
		return selectherobtn;
	}

	// 返回角色选择
	protected void onSelectHeroButtonClick() {
		Application app = screen.getApplication();
		if (app instanceof Game) {
			Game game = (Game)app;
			game.selectHero();
		}
	}

	/**
	 * 退出游戏
	 * 
	 * @return
	 */
	private Button getQuitBtn() {
		if (quitbtn == null) {
			float posX = (WIDTH - btnSize.x)/ 2;
			float posY = 222;
			quitbtn = new ButtonAdapter(screen, "sys_quitbtn", new Vector2f(
					posX, posY), btnSize) {
				@Override
				public void onButtonMouseLeftUp(MouseButtonEvent evt,
						boolean toggled) {
					onQuitButtonClick();
				}
			};
			quitbtn.setText("Quit");
			quitbtn.setTextAlign(BitmapFont.Align.Center);
		}
		return quitbtn;
	}

	// 退出游戏
	protected void onQuitButtonClick() {
		screen.getApplication().stop();
	}

	/**
	 * 返回游戏
	 * 
	 * @return
	 */
	private Button getBackBtn() {
		if (backbtn == null) {
			float posX = (WIDTH - btnSize.x)/ 2;
			float posY = 270;
			backbtn = new ButtonAdapter(screen, "sys_backbtn", new Vector2f(
					posX, posY), btnSize) {
				@Override
				public void onButtonMouseLeftUp(MouseButtonEvent evt,
						boolean toggled) {
					onBackButtonClick();
				}
			};
			backbtn.setText("Back to game");
			backbtn.setTextAlign(BitmapFont.Align.Center);
		}
		return backbtn;
	}

	// 返回游戏
	public void onBackButtonClick() {
		if (uiPanel.getIsVisible())
			uiPanel.hide();
		if (this.getIsVisible()) {
			this.hide();
		}
	}

}
