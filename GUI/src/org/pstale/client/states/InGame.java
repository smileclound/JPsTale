package org.pstale.client.states;

import org.pstale.client.Game;
import org.pstale.client.gui.hud.InGameHUD;

import tonegod.gui.core.Screen;

import com.jme3.app.Application;
import com.jme3.app.state.AppStateManager;
import com.jme3.math.ColorRGBA;

/**
 * 游戏内主界面
 * ->Quit
 * <-SelectHero
 * @author yanmaoyuan
 *
 */
public class InGame extends BaseAppState {

	private InGameHUD hud;
	public InGame(Game app, Screen screen) {
		super(app, screen);
	}

	@Override
	public void initialize(AppStateManager stateManager, Application app) {
		super.initialize(stateManager, app);
		
		// 初始化HUD
		if (hud == null) {
			hud = new InGameHUD(screen);
		}
		hud.initialize();
		hud.resize(screen.getWidth(), screen.getHeight());
		
		game.getViewPort().setBackgroundColor(ColorRGBA.LightGray);
	}

	@Override
	public void cleanup() {
		super.cleanup();
		
		// 移除HUD
		hud.cleanup();
		
		// 恢复镜头颜色
		game.getViewPort().setBackgroundColor(ColorRGBA.Black);
	}
}
