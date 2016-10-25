package org.pstale.client.gui;

import java.awt.Toolkit;

import org.pstale.client.Game;
import org.pstale.client.gui.hud.HUD;

import tonegod.gui.controls.buttons.CheckBox;
import tonegod.gui.controls.lists.ComboBox;
import tonegod.gui.controls.windows.Panel;
import tonegod.gui.core.Element;
import tonegod.gui.core.Screen;

import com.jme3.app.Application;
import com.jme3.input.event.MouseButtonEvent;
import com.jme3.math.Vector2f;

public class UIPanel extends Panel {

	static float WIDTH = 300;
	static float HEIGHT = 120;

	HUD parentHUD;

	ComboBox size;
	CheckBox fullScreen;

	public UIPanel(Screen screen, HUD parent) {
		super(screen, "sys_uipanel", Vector2f.ZERO, new Vector2f(WIDTH, HEIGHT));
		this.setIsResizable(false);
		this.parentHUD = parent;
		
		addChild(getComboBox());
		addChild(getCheckBox());
	}

	private Element getComboBox() {
		if (size == null) {
			final int screenW = Toolkit.getDefaultToolkit().getScreenSize().width;
			final int screenH = Toolkit.getDefaultToolkit().getScreenSize().height;
			size = new ComboBox(screen, "cb2", new Vector2f(15, 15),
					new Vector2f(150, 25)) {
				@Override
				public void onChange(int selectedIndex, Object value) {
					switch (selectedIndex) {
					case 0:
						resizeScreen(1024, 768);
						break;
					case 1:
						resizeScreen(800, 600);
						break;
					case 2:
						resizeScreen(screenW, screenH);
						break;
					}
				}

				private void resizeScreen(int x, int y) {
					Application app = screen.getApplication();
					if (app instanceof Game) {
						Game game = (Game) app;
						int w = game.getSettings().getWidth();
						int h = game.getSettings().getHeight();
						if (w != x && h != y) {
							game.getSettings().setWidth(x);
							game.getSettings().setHeight(y);
							game.restart();
							parentHUD.resize(x, y);
						}
					}
				}
			};
			size.setSelectedIndex(0);
			size.addListItem("1024 * 768", 0);
			size.addListItem("800 * 600", 1);
			size.addListItem(screenW + " * " + screenH, 2);
		}
		return size;
	}

	private Element getCheckBox() {
		if (fullScreen == null) {
			fullScreen = new CheckBox(screen, "cb", new Vector2f(170, 15),
					new Vector2f(25, 25)) {
				@Override
				public void onButtonMouseLeftUp(MouseButtonEvent evt,
						boolean toggled) {
					// 从setting中读取FullScreen状态
					Application app = screen.getApplication();
					if (app instanceof Game) {
						Game game = (Game) app;
						boolean fullBefore = game.getSettings().isFullscreen();
						boolean fullAfter = fullScreen.getIsChecked();
						if (fullAfter != fullBefore) {
							game.getSettings().setFullscreen(fullAfter);
							game.restart();
						}
					}
				}
			};
			fullScreen.setLabelText("Full Screen");
			fullScreen.setIsChecked(false);

			// 从setting中读取FullScreen状态
			Application app = screen.getApplication();
			if (app instanceof Game) {
				Game game = (Game) app;
				boolean full = game.getSettings().isFullscreen();
				fullScreen.setIsChecked(full);
			}
		}
		return fullScreen;
	}
}
