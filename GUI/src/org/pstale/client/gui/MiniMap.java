package org.pstale.client.gui;

import tonegod.gui.controls.windows.Window;
import tonegod.gui.core.Element;
import tonegod.gui.core.Screen;

import com.jme3.math.Vector2f;
import com.jme3.math.Vector4f;

/**
 * 小地图
 * @author yanmaoyuan
 *
 */
public class MiniMap extends Window {

	static float WIDTH = 160;
	static float HEIGHT = 160 + 24;
	
	private Element map;
	public MiniMap(Screen screen, Vector2f position) {
		super(screen, "hud_minimap", position, new Vector2f(WIDTH, HEIGHT));
		setIsResizable(false);

		map = new Element(screen, "minimap_map", new Vector2f(4, 28),
				new Vector2f(152, 152), Vector4f.ZERO, null);
		addChild(map);
		dragBar.setFont("Interface/Fonts/msyh.fnt");
	}
	
	public void setMap(String mapTexture) {
		map.setColorMap(mapTexture);
	}
	
}
