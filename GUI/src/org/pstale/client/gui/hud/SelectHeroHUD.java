package org.pstale.client.gui.hud;

import tonegod.gui.controls.buttons.Button;
import tonegod.gui.controls.buttons.ButtonAdapter;
import tonegod.gui.core.Screen;

import com.jme3.font.BitmapFont;
import com.jme3.input.event.MouseButtonEvent;
import com.jme3.math.Vector2f;

public abstract class SelectHeroHUD extends HUD {

	// 控件定义
	private ButtonAdapter startbtn;// 开始游戏
	private ButtonAdapter deletebtn;// 删除角色
	private ButtonAdapter createbtn;// 创建角色
	private ButtonAdapter backbtn;// 返回

	/**
	 * 构造方法
	 * 
	 * @param screen
	 */
	public SelectHeroHUD(Screen screen) {
		super(screen);
	}

	@Override
	public void initialize() {
		screen.addElement(getStartBtn());
		screen.addElement(getCreateBtn());
		screen.addElement(getDeleteBtn());
		screen.addElement(getBackBtn());
	}

	@Override
	public void cleanup() {
		screen.removeElement(getStartBtn());
		screen.removeElement(getCreateBtn());
		screen.removeElement(getDeleteBtn());
		screen.removeElement(getBackBtn());
	}

	/**
	 * 重置窗口大小
	 */
	@Override
	public void resize(float width, float height) {
		float posX = width / 2 - 52;
		float posY = 10;
		startbtn.setPosition(posX, posY);

		posX = 15;
		posY = 8;
		deletebtn.setPosition(posX, posY);

		posX = 120;
		posY = 8;
		createbtn.setPosition(posX, posY);

		posX = width - 75;
		posY = 8;
		backbtn.setPosition(posX, posY);
	}

	// 2D gui
	private Button getStartBtn() {
		if (startbtn == null) {
			float posX = screenWidth / 2 - 52;
			float posY = screenHeight - 60;
			startbtn = new ButtonAdapter(screen, "StartGame", new Vector2f(
					posX, posY), new Vector2f(104, 40)) {
				@Override
				public void onButtonMouseLeftUp(MouseButtonEvent evt,
						boolean toggled) {
					onStartButtonClick();
				}
			};
			startbtn.setText("Start Game");
			startbtn.setTextAlign(BitmapFont.Align.Center);
		}
		return startbtn;
	}

	private Button getDeleteBtn() {
		if (deletebtn == null) {
			float posX = 15;
			float posY = screenHeight - 40;
			deletebtn = new ButtonAdapter(screen, "Delete", new Vector2f(posX,
					posY), new Vector2f(100, 32)) {
				@Override
				public void onButtonMouseLeftUp(MouseButtonEvent evt,
						boolean toggled) {
					onDeleteButtonClick();
				}
			};
			deletebtn.setText("Delete");
			deletebtn.setTextAlign(BitmapFont.Align.Center);
		}
		return deletebtn;
	}

	private Button getCreateBtn() {
		if (createbtn == null) {
			float posX = 120;
			float posY = screenHeight - 40;
			createbtn = new ButtonAdapter(screen, "Create", new Vector2f(posX,
					posY), new Vector2f(100, 32)) {
				@Override
				public void onButtonMouseLeftUp(MouseButtonEvent evt,
						boolean toggled) {
					onCreateButtonClick();
				}
			};
			createbtn.setText("Create");
			createbtn.setTextAlign(BitmapFont.Align.Center);
		}
		return createbtn;
	}

	private Button getBackBtn() {
		if (backbtn == null) {
			float posX = screenWidth - 75;
			float posY = screenHeight - 40;
			backbtn = new ButtonAdapter(screen, "Back",
					new Vector2f(posX, posY), new Vector2f(60, 30)) {
				@Override
				public void onButtonMouseLeftUp(MouseButtonEvent evt,
						boolean toggled) {
					onBackButtonClick();
				}
			};
			backbtn.setText("Back");
			backbtn.setTextAlign(BitmapFont.Align.Center);
		}
		return backbtn;
	}

	public abstract void onCreateButtonClick();

	public abstract void onDeleteButtonClick();

	public abstract void onStartButtonClick();

	public abstract void onBackButtonClick();
}
