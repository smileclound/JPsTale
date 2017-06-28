package org.pstale.client.gui;

import java.nio.ByteBuffer;
import java.util.List;

import tonegod.gui.controls.windows.Panel;
import tonegod.gui.core.Screen;

import com.jme3.material.Material;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector4f;
import com.jme3.texture.Image;
import com.jme3.texture.Image.Format;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture.WrapMode;
import com.jme3.texture.Texture2D;
import com.jme3.util.BufferUtils;

/**
 * 装备窗口
 * 
 * @author yanmaoyuan
 * 
 */
public class InventoryWindow extends Panel {

	static float WIDTH = 291;
	static float HEIGHT = 333;

	public InventoryWindow(Screen screen) {
		super(screen, "hud_inventoryWnd", new Vector2f(200, 250), new Vector2f(WIDTH, HEIGHT), Vector4f.ZERO, "Textures/InGameHUD/Inventory/InvenBox.bmp");
		Material mat = this.getMaterial();
		Texture tex = mat.getTextureParam("ColorMap").getTextureValue();
		mkAlphaMap(tex, mat);
		this.setIsResizable(false);
	}
	private void mkAlphaMap(Texture texture, Material mat) {
		Image img = texture.getImage();
		List<ByteBuffer> list = img.getData();
		if (img.getFormat() == Format.BGR8 || img.getFormat() == Format.RGB8) {
			if (list != null && list.size() > 0) {
				ByteBuffer buffer = list.get(0);
				int size = buffer.limit();
				byte[] imageData = new byte[size];
				for (int i = 0; i < size / 3; i++) {
					byte b = (byte)buffer.get(i * 3);
					byte g = (byte)buffer.get(i * 3 + 1);
					byte r = (byte)buffer.get(i * 3 + 2);
					byte a = (byte) 0xFF;
					
					if (b==0 && g == 0 && r == 0) {
						a = 0;
					}
					imageData[i * 3] = (byte)a;
					imageData[i * 3 + 1] = (byte)a;
					imageData[i * 3 + 2] = (byte)a;
				}
				ByteBuffer tempData = BufferUtils.createByteBuffer(size);
				tempData.put(imageData).flip();
				
				int w = img.getWidth();
				int h = img.getHeight();
				Image newImg = new Image(img.getFormat(), w, h, tempData);
				Texture alpha =  new Texture2D(newImg);
				alpha.setWrap(WrapMode.Clamp);
				
				mat.setTexture("AlphaMap", alpha);
			}
		} else {
			System.out.println(img.getFormat());
		}
	}
}
