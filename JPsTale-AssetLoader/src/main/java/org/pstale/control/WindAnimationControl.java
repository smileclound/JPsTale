package org.pstale.control;

import com.jme3.export.Savable;
import com.jme3.math.FastMath;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.control.AbstractControl;

/**
 * 树叶、草会随风摆动
 * 
 * @author yanmaoyuan
 *
 */
public class WindAnimationControl extends AbstractControl implements Savable {

    final static int sMATS_SCRIPT_WINDZ1 = 0x0020;
    final static int sMATS_SCRIPT_WINDZ2 = 0x0040;
    final static int sMATS_SCRIPT_WINDX1 = 0x0080;
    final static int sMATS_SCRIPT_WINDX2 = 0x0100;

    int type;

    public WindAnimationControl(int type) {
        this.type = type;
    }

    float time = 0f;
    float x = 0;
    float z = 0;

    @Override
    protected void controlUpdate(float tpf) {
        time += tpf;
        if (time > 4) {
            time -= 4;
        }
        float radius = FastMath.TWO_PI * time / 4f;
        float x = 0;
        float z = 0;
        switch (type) {
        case (sMATS_SCRIPT_WINDX1):
            z = 2 * FastMath.sin(radius);
            break;
        case (sMATS_SCRIPT_WINDZ1):
            x = 2 * FastMath.sin(radius);
            break;
        case (sMATS_SCRIPT_WINDX2):
            x = 2 * FastMath.sin(radius);
            break;
        case (sMATS_SCRIPT_WINDZ2):
            z = 8 * FastMath.sin(radius);
            break;
        }
        spatial.setLocalTranslation(x, 0, z);
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
    }

}
