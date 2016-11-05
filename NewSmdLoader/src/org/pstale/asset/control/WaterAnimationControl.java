package org.pstale.asset.control;

import com.jme3.export.Savable;
import com.jme3.math.FastMath;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.control.AbstractControl;

/**
 * 水面其实是在做小范围的转圈圈
 * @author yanmaoyuan
 *
 */
public class WaterAnimationControl extends AbstractControl implements Savable {

	float time = 0f;
	@Override
	protected void controlUpdate(float tpf) {
		time += tpf;
		if (time > 8) {
			time -= 8;
		}
		
		float radius = FastMath.TWO_PI * time / 8f;
		float x = 8 * FastMath.sin(radius);
		float z = 8 * FastMath.cos(radius);
		spatial.setLocalTranslation(x, 0, z);
	}

	@Override
	protected void controlRender(RenderManager rm, ViewPort vp) {}

}
