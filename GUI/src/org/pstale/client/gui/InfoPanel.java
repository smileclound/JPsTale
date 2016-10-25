package org.pstale.client.gui;

import com.jme3.math.Vector2f;
import com.jme3.math.Vector4f;

import tonegod.gui.controls.extras.Indicator;
import tonegod.gui.controls.windows.Panel;
import tonegod.gui.core.Screen;

/**
 * 玩家信息面板
 * 
 * @author yanmaoyuan
 *
 */
public class InfoPanel extends Panel {
	public InfoPanel(Screen screen, String UID, Vector2f position) {
		super(screen, UID, position, new Vector2f(176, 55), Vector4f.ZERO, null);

		Panel avatarPanel = new Panel(screen, UID + "avatarPanel",
				new Vector2f(2, 2), new Vector2f(48, 48), Vector4f.ZERO, "Textures/Avatar/avatar.bmp");
		avatarPanel.setIsResizable(false);
		avatarPanel.setIsMovable(false);

		Indicator hpBar = new Indicator(screen, UID + "hpBar", new Vector2f(52,
				4), new Vector2f(120, 14), Orientation.HORIZONTAL) {
			@Override
			public void onChange(float currentValue, float currentPercentage) {
			}
		};
		hpBar.setIndicatorImage("Textures/InGameHUD/Bar_Life.png");
//		hpBar.setAlphaMap(screen.getStyle("Indicator").getString("alphaImg"));
		hpBar.setMaxValue(100);
		hpBar.setCurrentValue(100);

		Indicator mpBar = new Indicator(screen, UID + "mpBar", new Vector2f(52,
				20), new Vector2f(100, 14), Orientation.HORIZONTAL) {
			@Override
			public void onChange(float currentValue, float currentPercentage) {
			}
		};
		mpBar.setIndicatorImage("Textures/InGameHUD/Bar_Mana.png");
//		mpBar.setAlphaMap(screen.getStyle("Indicator").getString("alphaImg"));
		mpBar.setMaxValue(100);
		mpBar.setCurrentValue(100);

		Indicator stmBar = new Indicator(screen, UID + "stmBar", new Vector2f(
				52, 36), new Vector2f(80, 12), Orientation.HORIZONTAL) {
			@Override
			public void onChange(float currentValue, float currentPercentage) {
			}
		};
		stmBar.setIndicatorImage("Textures/InGameHUD/Bar_Stamina.png");
//		stmBar.setAlphaMap(screen.getStyle("Indicator").getString("alphaImg"));
		stmBar.setMaxValue(100);
		stmBar.setCurrentValue(100);

		setIsResizable(false);
		addChild(avatarPanel);
		addChild(hpBar);
		addChild(mpBar);
		addChild(stmBar);
	}

}