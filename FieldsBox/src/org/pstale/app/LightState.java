package org.pstale.app;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.light.SpotLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;

public class LightState extends BaseAppState {

	AmbientLight ambient;
	DirectionalLight sun;
	SpotLight spot;
	@Override
	protected void initialize(Application app) {
		ambient = new AmbientLight();
		ambient.setColor(new ColorRGBA(0.75f, 0.75f, 0.75f, 1));
		
		sun = new DirectionalLight();
		sun.setColor(new ColorRGBA(0.25f, 0.25f, 0.25f, 1));
		sun.setDirection(new Vector3f(1, -1, -0.5f).normalizeLocal());
		
		spot = new SpotLight();
		spot.setDirection(new Vector3f(0, -1, 0));
		spot.setColor(new ColorRGBA(1, 1, 1, 1));
		spot.setSpotRange(1000f);
		spot.setSpotInnerAngle(FastMath.DEG_TO_RAD * 5);
		spot.setSpotOuterAngle(FastMath.DEG_TO_RAD * 30);
	}

	@Override
	protected void cleanup(Application app) {
	}

	public void update(float tpf) {
		spot.setPosition(getApplication().getCamera().getLocation());
	}
	
	@Override
	protected void onEnable() {
		SimpleApplication simpleApp = (SimpleApplication) getApplication();
		simpleApp.getRootNode().addLight(ambient);
		simpleApp.getRootNode().addLight(sun);
		simpleApp.getRootNode().addLight(spot);
	}

	@Override
	protected void onDisable() {
		SimpleApplication simpleApp = (SimpleApplication) getApplication();
		simpleApp.getRootNode().removeLight(ambient);
		simpleApp.getRootNode().removeLight(sun);
		simpleApp.getRootNode().removeLight(spot);
	}

}
