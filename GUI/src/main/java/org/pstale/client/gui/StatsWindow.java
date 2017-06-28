package org.pstale.client.gui;

import tonegod.gui.controls.windows.Window;
import tonegod.gui.core.Screen;

import com.jme3.math.Vector2f;

/**
 * 属性窗口，用于显示角色的各种数据。
 * 
 * @author yanmaoyuan
 * 
 */
public class StatsWindow extends Window {
	static float WIDTH = 200;
	static float HEIGHT = 300;

	public StatsWindow(Screen screen) {
		super(screen, "hud_statsWnd", new Vector2f(15, 15), new Vector2f(WIDTH, HEIGHT));
		this.setWindowTitle("Status");
		this.setIsResizable(false);
	}
}
