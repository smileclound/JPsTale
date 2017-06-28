package org.pstale.test;

import jme3utilities.sky.SkyAppState;

import com.jme3.app.SimpleApplication;

/**
 * 测试SkyDome
 * @author yanmaoyuan
 *
 */
public class TestSkyAppState extends SimpleApplication {

	@Override
	public void simpleInitApp() {
		stateManager.attach(new SkyAppState());
	}

	public static void main(String[] args) {
		TestSkyAppState app = new TestSkyAppState();
		app.start();
	}

}
