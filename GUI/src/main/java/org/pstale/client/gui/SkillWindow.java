package org.pstale.client.gui;

import tonegod.gui.controls.windows.Window;
import tonegod.gui.core.Screen;

import com.jme3.math.Vector2f;

/**
 * 技能窗口
 * 
 * @author yanmaoyuan
 * 
 */
public class SkillWindow extends Window {

	static float WIDTH = 180;
	static float HEIGHT = 400;

	public SkillWindow(Screen screen) {
		super(screen, "hud_skillWnd", Vector2f.ZERO, new Vector2f(WIDTH, HEIGHT));
		this.setWindowTitle("Skill");
		this.setIsResizable(false);
	}
}
