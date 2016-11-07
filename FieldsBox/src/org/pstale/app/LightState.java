package org.pstale.app;

import org.pstale.utils.GameDate;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.light.SpotLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.renderer.ViewPort;

/**
 * 游戏时间及亮度系统控制。
 * @author yanmaoyuan
 *
 */
public class LightState extends SubAppState {

	public static boolean USE_LIGHT = true;

	Vector3f orgin;
	
	AmbientLight ambientLight;// 环境光
	DirectionalLight sunLight;// 太阳光
	SpotLight spotLight;// 玩家头顶的点光源
	
	ColorRGBA sunColor = new ColorRGBA(0.45f, 0.45f, 0.45f, 1);
	ColorRGBA skyColor = new ColorRGBA(0.7f, 0.8f, 0.9f, 1f);
	
	GameDate gameDate;// 游戏时间  
	Camera cam;// 摄像机
	ViewPort viewPort;
	BitmapText gui;// 用来显示游戏时间  
	
	// 点光源悬停与玩家头顶一定的高度。
	float height = 800 * scale;
	Vector3f spotLoc = new Vector3f();
	
	@Override
	protected void initialize(Application app) {
		// 初始化游戏时间  
        gameDate = new GameDate(21600);// 6:00 AM  
        cam = app.getCamera();
        viewPort = app.getViewPort();
        
        /** 
         * 创建gui，用来显示时间 
         */  
        BitmapFont guiFont = app.getAssetManager().loadFont("Interface/Fonts/Default.fnt");  
        gui = new BitmapText(guiFont, false);  
        gui.setText("00:00");  
        guiNode.attachChild(gui);  

        // 把gui放在屏幕顶部居中  
        float width = (cam.getWidth() - gui.getLineWidth())/2;  
        float height = cam.getHeight();  
        gui.setLocalTranslation(width, height, 0); 
        
        if (USE_LIGHT) {
			ambientLight = new AmbientLight();
			ambientLight.setColor(new ColorRGBA(0.4f, 0.4f, 0.4f, 1));
			
			sunLight = new DirectionalLight();
			sunLight.setColor(sunColor);
			sunLight.setDirection(new Vector3f(1, -1, -0.5f).normalizeLocal());
			
			spotLight = new SpotLight();
			spotLight.setDirection(new Vector3f(0, -1, 0));
			spotLight.setColor(new ColorRGBA(0.6f, 0.6f, 0.6f, 1));
			spotLight.setSpotRange(1000f/scale);
			spotLight.setSpotInnerAngle(FastMath.DEG_TO_RAD * 15);
			spotLight.setSpotOuterAngle(FastMath.DEG_TO_RAD * 30);
			
			// 在这里获得CollisionState中玩家的坐标点引用。
			CollisionState collisionState = getStateManager().getState(CollisionState.class);
			if (collisionState != null) {
				orgin = collisionState.getPlayerLocation();
			}
        }
        
	}

	@Override
	protected void cleanup(Application app) {
	}

	public void update(float tpf) {
		// 更新游戏时间  
        gameDate.update(tpf);  
        // 更新gui，显示当前时间  
        gui.setText(String.format("%02d:%02d", gameDate.getHour(), gameDate.getMinute()));  
        
        // 更新阳光亮度  
        float power = gameDate.getLightPower();
        
        // 更新天空背景色
        skyColor.r = 0.7f * power;
        skyColor.g = 0.8f * power;
        skyColor.b = 0.9f * power;
        skyColor.a = power;
        viewPort.setBackgroundColor(skyColor);
        
        if (!USE_LIGHT) {
        	return;
        }
        
        // 更新阳光亮度  
        sunColor.r = power;
        sunColor.g = power;
        sunColor.b = power;
        sunColor.a = power;
        sunLight.setColor(sunColor);
        
        // 更新光照角度  
        sunLight.setDirection(gameDate.getSunDirection());  
        
        /**
         * 如果亮度足够大，就不要点光源了。
         */
        if (power > 0.8f) {
        	spotLight.setColor(ColorRGBA.Black);
        } else {
        	spotLight.setColor(ColorRGBA.White);
        }
        
        /**
         * 计算点光源的坐标。
         */
		if (orgin != null) {
			spotLoc.set(orgin);
			spotLoc.y += height;
		} else {
			spotLoc.set(getApplication().getCamera().getLocation());
			spotLoc.y += height;
		}
		spotLight.setPosition(spotLoc);
	}
	
	@Override
	protected void onEnable() {
		super.onEnable();
		
		if (USE_LIGHT) {
			SimpleApplication simpleApp = (SimpleApplication) getApplication();
			simpleApp.getRootNode().addLight(ambientLight);
			simpleApp.getRootNode().addLight(sunLight);
			simpleApp.getRootNode().addLight(spotLight);
		}
	}

	@Override
	protected void onDisable() {
		super.onDisable();
		
		if (USE_LIGHT) {
			SimpleApplication simpleApp = (SimpleApplication) getApplication();
			simpleApp.getRootNode().removeLight(ambientLight);
			simpleApp.getRootNode().removeLight(sunLight);
			simpleApp.getRootNode().removeLight(spotLight);
		}
	}

}
