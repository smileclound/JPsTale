package org.pstale.client.gui.hud;

import java.util.Random;

import tonegod.gui.controls.extras.Indicator;
import tonegod.gui.controls.text.Label;
import tonegod.gui.controls.windows.Panel;
import tonegod.gui.core.ElementManager;

import com.jme3.font.BitmapFont;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector4f;

public class LoadingHUD extends Panel {

	private Panel panel;
	private Indicator progressBar;
	private Label lableMsg;

	float max = 400;
	float maxH = 8;

	float WIDTH = 800;
	float HEIGHT = 600;
	
	public LoadingHUD(ElementManager screen, String UID, Vector2f position,
			Vector2f dimensions) {
		super(screen, UID, position, dimensions, Vector4f.ZERO, null);
		this.setIgnoreMouse(true);

		this.setDimensions(WIDTH, HEIGHT);

		panel = new Panel(screen, UID, position, dimensions, Vector4f.ZERO, null);
		float width = dimensions.x;
		float height = dimensions.y;
		this.setPosition((width-WIDTH)/2, (height-HEIGHT)/2);

		float posX = (WIDTH - max) / 2;
		float posY = (HEIGHT - 40);

		progressBar = new Indicator(screen, 
				UID + "progressBar",
				new Vector2f(posX, posY),
				new Vector2f(max, 12), Orientation.HORIZONTAL) {
			@Override
			public void onChange(float currentValue, float currentPercentage) {
			}
		};
		progressBar.setIndicatorColor(new ColorRGBA(0.0f, 1f, 0f, 1));
		progressBar.setAlphaMap(screen.getStyle("Indicator").getString("alphaImg"));
		progressBar.setMaxValue(100);
		progressBar.setCurrentValue(100);
		progressBar.setIgnoreMouse(true);
		progressBar.setDisplayPercentage();
		this.addChild(progressBar);

		lableMsg = new Label(screen, "lableMsg", new Vector2f(posX, posY + maxH),
				new Vector2f(max, 16));
		lableMsg.setTextAlign(BitmapFont.Align.Center);
		lableMsg.setFontColor(ColorRGBA.White);
		lableMsg.setIgnoreMouse(true);
		this.addChild(lableMsg);
	}

	public void setBackground() {
		String[] imgs = {
				"Textures/Loading/map-all-all.bmp",
				"Textures/Loading/map-all-mrin.bmp",
				"Textures/Loading/map-all-tscr.bmp",
				"Textures/Loading/map-ch-ac.bmp",
				"Textures/Loading/map-ch-at.bmp",
				"Textures/Loading/map-ch-ft.bmp",
				"Textures/Loading/map-ch-knight.bmp",
				"Textures/Loading/map-ch-meca.bmp",
				"Textures/Loading/map-ch-mg.bmp",
				"Textures/Loading/map-ch-pk.bmp",
				"Textures/Loading/map-ch-pt.bmp",
				"Textures/Loading/map-logo-RNS.bmp" };
		Random random = new Random();
		int n = random.nextInt(imgs.length);
		setColorMap(imgs[n]);
	}
	/**
	 * 更新载入界面的进度条
	 * 
	 * @param progress
	 * @param msg
	 */
	public void setProgress(float progress, String msg) {
		progressBar.setCurrentValue(progress * 100); 
		lableMsg.setText(msg);
	}
}
