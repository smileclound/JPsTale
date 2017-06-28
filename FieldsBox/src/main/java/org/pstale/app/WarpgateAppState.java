package org.pstale.app;

import org.pstale.fields.WarpGate;

import com.jme3.app.Application;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Cylinder;

/**
 * 传送门
 * 
 * @author yanmaoyuan
 * 
 */
public class WarpgateAppState extends SubAppState {

	@Override
	protected void initialize(Application app) {

	}

	/**
	 * 载入传送门
	 * @param warpGate
	 */
	public void load(WarpGate... warpGates) {
		//rootNode.detachAllChildren();
		
		for(int i=0; i<warpGates.length; i++) {
			WarpGate warp = warpGates[i];
			
			Vector3f pos = new Vector3f(warp.getPosition());
			pos.multLocal(scale);
			pos.y += 1;
			
			float height = warp.getHeight() * scale;
			float size = warp.getSize() * scale;
			
			/**
			 * 创建一个传送门标记
			 */
			Cylinder cylinder = new Cylinder(5, 6, height, size, true, false);
			Geometry geom = new Geometry("WarpFlag", cylinder);
			geom.rotate(FastMath.HALF_PI, 0, 0);
			geom.setLocalTranslation(pos);
			geom.setMaterial(getMaterial(ColorRGBA.Orange));
			rootNode.attachChild(geom);
		}

	}

}
