package org.pstale.client.gui;

import tonegod.gui.controls.windows.Window;
import tonegod.gui.core.Screen;

import com.jme3.math.Vector2f;

/**
 * 任务窗口
 * 
 * @author yanmaoyuan
 * 
 */
public class QuestWindow extends Window {

	static float WIDTH = 200;
	static float HEIGHT = 250;

	public QuestWindow(Screen screen) {
		super(screen, "hud_questWnd", Vector2f.ZERO, new Vector2f(WIDTH, HEIGHT));
		this.setWindowTitle("Quest");
		this.setIsResizable(false);
	}
}
