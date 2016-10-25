package org.pstale.client.gui;

import tonegod.gui.controls.windows.Window;
import tonegod.gui.core.Screen;

import com.jme3.math.Vector2f;

/**
 * 队伍窗口
 * 
 * @author yanmaoyuan
 * 
 */
public class GroupWindow extends Window {
	static float WIDTH = 120;
	static float HEIGHT = 300;

	public GroupWindow(Screen screen) {
		super(screen, "hud_groupWnd", Vector2f.ZERO, new Vector2f(WIDTH, HEIGHT));
		this.setWindowTitle("Group");
		this.setIsResizable(false);
	}

}
