package org.pstale.client;

import com.jme3.system.AppSettings;

public class Main {

	public static void main(String[] args) {
		AppSettings settings = new AppSettings(true);
		settings.setFrameRate(60);
		settings.setBitsPerPixel(24);
		settings.setResolution(1024, 768);
		settings.setFullscreen(false);
		settings.setTitle("精灵III");
		
		Game app = new Game();
		app.setSettings(settings);
		app.start();
	}

}