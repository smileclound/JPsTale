package org.pstale.client.gui.hud;

import tonegod.gui.core.Screen;

/**
 * Head Up Display
 * @author yanmaoyuan
 *
 */
public abstract class HUD {

	protected Screen screen;// 窗口
	protected float screenWidth;
	protected float screenHeight;

	public HUD(Screen screen) {
		this.screen = screen;
		screenWidth = screen.getWidth();
		screenHeight = screen.getHeight();
	}
	
	// 初始化窗口
	public abstract void initialize();
	
	// 清除窗口
	public abstract void cleanup();
	
	// 重新布局窗口
	public abstract void resize(final float width, final float height);
}
