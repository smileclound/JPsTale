package org.pstale.app;
import static org.pstale.constants.SceneConstants.scale;

import org.pstale.entity.field.FieldGate;

import com.jme3.app.Application;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

/**
 * 地区门户
 * 
 * @author yanmaoyuan
 *
 */
public class FieldgateAppState extends SubAppState {

    @Override
    protected void initialize(Application app) {
        // TODO Auto-generated method stub

    }

    /**
     * 载入门户
     * 
     * @param fieldGates
     */
    public void load(FieldGate... fieldGates) {
        // rootNode.detachAllChildren();

        for (int i = 0; i < fieldGates.length; i++) {
            FieldGate gate = fieldGates[i];

            Vector3f pos = new Vector3f(gate.getPosition());
            pos.multLocal(scale);
            pos.y += 1000 * scale;

            gate.getToField();

            /**
             * 创建一个门户标记
             */
            Box box = new Box(1, 2000 * scale, 1);
            Geometry geom = new Geometry("GateFlag", box);
            geom.setLocalTranslation(pos);
            geom.setMaterial(getMaterial(ColorRGBA.White));
            rootNode.attachChild(geom);
        }

    }

}
