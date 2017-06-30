package org.pstale.app;

import java.util.ArrayList;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
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
import com.jme3.material.Material;
import com.jme3.material.RenderState.BlendMode;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue.Bucket;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial.CullHint;
import com.jme3.scene.shape.Quad;
import com.jme3.texture.Texture;

/**
 * 这个模块用来去掉默认的鼠标，改为精灵的鼠标。
 * 
 * @author yanmaoyuan
 *
 */
public class CursorState extends BaseAppState {

    private Geometry cursor;
    private Material material;
    private ArrayList<Texture> cursorsTex = new ArrayList<Texture>();

    private Vector3f position;
    private float width;// 屏幕宽度
    private float height;// 屏幕高度

    @Override
    protected void initialize(Application app) {

        width = app.getCamera().getWidth();
        height = app.getCamera().getHeight();
        position = new Vector3f(width / 2, height / 2 - 32, Float.MAX_VALUE);

        InputManager inputManager = app.getInputManager();
        AssetManager assetManager = app.getAssetManager();
        // 初始化鼠标
        // 把鼠标的图片搞成透平的，这样玩家就只能看见我们的图片了！
        JmeCursor jmeCursor = (JmeCursor) assetManager.loadAsset("Textures/Cursors/invisiable.cur");
        inputManager.setMouseCursor(jmeCursor);

        // 从精灵客户端文件夹中读取光标文件
        cursorsTex.add(assetManager.loadTexture("Textures/Cursors/DefaultCursor.tga"));
        cursorsTex.add(assetManager.loadTexture("Textures/Cursors/Attack_Cursor.tga"));
        cursorsTex.add(assetManager.loadTexture("Textures/Cursors/GetItem_Cursor1.tga"));
        cursorsTex.add(assetManager.loadTexture("Textures/Cursors/GetItem_Cursor2.tga"));
        cursorsTex.add(assetManager.loadTexture("Textures/Cursors/Talk_Cursor.tga"));
        cursorsTex.add(assetManager.loadTexture("Textures/Cursors/BuyCursor.tga"));
        cursorsTex.add(assetManager.loadTexture("Textures/Cursors/SellCursor.tga"));
        cursorsTex.add(assetManager.loadTexture("Textures/Cursors/RepairCursor.tga"));

        cursor = new Geometry("cursor", new Quad(32, 32));
        cursor.setQueueBucket(Bucket.Gui);
        cursor.setCullHint(CullHint.Never);

        material = new Material(assetManager, "Common/MatDefs/Gui/Gui.j3md");
        material.getAdditionalRenderState().setBlendMode(BlendMode.Alpha);
        material.setColor("Color", ColorRGBA.White);
        material.setTexture("Texture", cursorsTex.get(0));

        cursor.setLocalTranslation(position);
        cursor.setMaterial(material);

    }

    @Override
    protected void cleanup(Application app) {
    }

    @Override
    protected void onEnable() {
        InputManager inputManager = getApplication().getInputManager();
        inputManager.addRawInputListener(inputListener);

        ((SimpleApplication) getApplication()).getGuiNode().attachChild(cursor);
    }

    @Override
    protected void onDisable() {
        InputManager inputManager = getApplication().getInputManager();
        inputManager.removeRawInputListener(inputListener);

        cursor.removeFromParent();
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
        material.setTexture("Texture", cursorsTex.get(count));
    }

    /**
     * 光标移动监听器
     */
    boolean isPressed = false;
    private RawInputListener inputListener = new RawInputListener() {
        private float x;
        private float y;

        public void onMouseMotionEvent(MouseMotionEvent evt) {
            if (isPressed)
                return;
            // 获得当前鼠标的坐标
            x = evt.getX();
            y = evt.getY();

            // 处理屏幕边缘
            x = FastMath.clamp(x, 0, width);
            y = FastMath.clamp(y, 0, height);

            position.x = x;
            position.y = y - 32;

            // 设置光标位置
            cursor.setLocalTranslation(position);
        }

        public void beginInput() {
        }

        public void endInput() {
        }

        public void onJoyAxisEvent(JoyAxisEvent evt) {
        }

        public void onJoyButtonEvent(JoyButtonEvent evt) {
        }

        public void onMouseButtonEvent(MouseButtonEvent evt) {
            isPressed = evt.isPressed();
        }

        public void onKeyEvent(KeyInputEvent evt) {
        }

        public void onTouchEvent(TouchEvent evt) {
        }
    };

    public Vector3f getPosition() {
        return position;
    }

}
