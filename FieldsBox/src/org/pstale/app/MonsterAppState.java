package org.pstale.app;

import com.jme3.app.Application;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

/**
 * 刷怪点
 * 
 * @author yanmaoyuan
 * 
 */
public class MonsterAppState extends SubAppState {

	@Override
	protected void initialize(Application app) {
		// TODO Auto-generated method stub

	}

	public void load(Vector3f... positions) {
		//rootNode.detachAllChildren();

		for (int i = 0; i < positions.length; i++) {
			Vector3f pos = positions[i];
			
			Box box = new Box(1, 1, 1);
			Geometry geom = new Geometry("MonsterFlag", box);
			geom.setLocalTranslation(pos);
			geom.setMaterial(getMaterial(ColorRGBA.Red));
			rootNode.attachChild(geom);
		}
	}
}
