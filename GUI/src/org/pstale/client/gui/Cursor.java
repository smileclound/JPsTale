package org.pstale.client.gui;

import java.util.ArrayList;

import com.jme3.asset.AssetManager;
import com.jme3.cursors.plugins.JmeCursor;
import com.jme3.input.InputManager;
import com.jme3.input.RawInputListener;
import com.jme3.input.event.JoyAxisEvent;
import com.jme3.input.event.JoyButtonEvent;
import com.jme3.input.event.KeyInputEvent;
import com.jme3.input.event.MouseButtonEvent;
import com.jme3.input.event.MouseMotionEvent;
import com.jme3.input.event.TouchEvent;
import com.jme3.math.FastMath;
import com.jme3.math.Vector2f;
import com.jme3.system.AppSettings;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture2D;
import com.jme3.ui.Picture;

/**
 * 光标
 * 
 * @author yanmaoyuan
 * 
 */
public class Cursor extends Picture {
	private Vector2f position;
	private AppSettings settings;
	
	private ArrayList<Texture> cursorsTex = new ArrayList<Texture>();

	private AssetManager assetManager;

	public Cursor(InputManager inputManager, AssetManager assetManager, AppSettings settings) {
		super("Cursor");
		this.settings = settings;
		this.assetManager = assetManager;
		
		position = new Vector2f();

		// 初始化鼠标
		// 把鼠标的图片搞成透平的，这样玩家就只能看见我们的图片了！
		JmeCursor jmeCursor = (JmeCursor)assetManager.loadAsset("Textures/Cursors/invisiable.cur");
		inputManager.setMouseCursor(jmeCursor);
		
		cursorsTex.add(assetManager.loadTexture("Textures/Cursors/DefaultCursor.tga"));
		cursorsTex.add(assetManager.loadTexture("Textures/Cursors/Attack_Cursor.tga"));
		cursorsTex.add(assetManager.loadTexture("Textures/Cursors/GetItem_Cursor1.tga"));
		cursorsTex.add(assetManager.loadTexture("Textures/Cursors/GetItem_Cursor2.tga"));
		cursorsTex.add(assetManager.loadTexture("Textures/Cursors/Talk_Cursor.tga"));
		cursorsTex.add(assetManager.loadTexture("Textures/Cursors/BuyCursor.tga"));
		cursorsTex.add(assetManager.loadTexture("Textures/Cursors/SellCursor.tga"));
		cursorsTex.add(assetManager.loadTexture("Textures/Cursors/RepairCursor.tga"));

		setLocalTranslation(settings.getWidth()/2, settings.getHeight()/2-32, Float.MAX_VALUE);
		setTexture(assetManager, (Texture2D) cursorsTex.get(0), true);
		setWidth(32);
		setHeight(32);
		
		inputManager.addRawInputListener(inputListener);
	}
	
	public final static int DEFAULT_CURSOR = 0;
	public final static int ATTACK_CURSOR = 1;
	public final static int GETITEM_CURSOR1 = 2;
	public final static int GETITEM_CURSOR2 = 3;
	public final static int TALK_CURSOR = 4;
	public final static int BUY_CURSOR = 5;
	public final static int SELL_CURSOR = 6;
	public final static int REPAIR_CURSOR = 7;
	
	protected int current;

	public void chageCursor(int count) {
		current = count;
		setTexture(assetManager, (Texture2D) cursorsTex.get(count), true);
	}

	/**
	 * 光标移动监听器
	 */
	private RawInputListener inputListener = new RawInputListener() {
		private float x;
		private float y;
		public void onMouseMotionEvent(MouseMotionEvent evt) {
			// 获得当前鼠标的坐标
			x = evt.getX();
			y = evt.getY();

			// 处理屏幕边缘
			x = FastMath.clamp(x, 0, settings.getWidth());
			y = FastMath.clamp(y, 0, settings.getHeight());

			position.x = x;
			position.y = y;
			
			// 设置光标位置
			setPosition(x, y - 32);
		}
		public void beginInput() {}
		public void endInput() {}
		public void onJoyAxisEvent(JoyAxisEvent evt) {}
		public void onJoyButtonEvent(JoyButtonEvent evt) {}
		public void onMouseButtonEvent(MouseButtonEvent evt) {}
		public void onKeyEvent(KeyInputEvent evt) {}
		public void onTouchEvent(TouchEvent evt) {}
	};
}
