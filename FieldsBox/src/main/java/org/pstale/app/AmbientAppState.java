package org.pstale.app;

import org.pstale.fields.AmbientPos;

import com.jme3.app.Application;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Sphere;

/**
 * 环境音效
 * @author yanmaoyuan
 *
 */
public class AmbientAppState extends SubAppState {

	@Override
	protected void initialize(Application app) {
		getMaterial(new ColorRGBA(0f, 1f, 1f, 1f));
		defaultMat.getAdditionalRenderState().setWireframe(true);
	}

	/**
	 * 载入环境音效
	 * @param ambients
	 */
	public void load(AmbientPos ... ambients) {
		//rootNode.detachAllChildren();
		
		for(int i=0; i<ambients.length; i++) {
			AmbientPos ambient = ambients[i];
			
			Vector3f pos = new Vector3f(ambient.getPosition());
			pos.multLocal(scale);
			
			float radius = ambient.getRound() * scale;
			
			/**
			 * 创建一个环境音效球，可视化查看其位置和影响。
			 */
			Sphere sphere = new Sphere(12, 12, radius);
			Geometry geom = new Geometry("AmbientFlag", sphere);
			geom.setLocalTranslation(pos);
			geom.setMaterial(defaultMat);
			rootNode.attachChild(geom);
		}
	}
}
