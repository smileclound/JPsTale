package com.jme3.scene.plugins.smd.scene;

import java.io.IOException;

import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.plugins.smd.Flyweight;
import com.jme3.util.LittleEndien;

/**
 * size = 22
 */
public class LIGHT3D extends Flyweight {
    /**
     * <pre>
     * #define	smLIGHT_TYPE_NIGHT		0x00001
     * #define	smLIGHT_TYPE_LENS		0x00002
     * #define	smLIGHT_TYPE_PULSE2	0x00004
     * #define	SMLIGHT_TYPE_OBJ		0x00008
     * #define	smLIGHT_TYPE_DYNAMIC	0x80000
     * </pre>
     */
    int type;

    Vector3f location;
    float range;
    ColorRGBA color;

    public void loadData(LittleEndien in) throws IOException {
        type = in.readInt();

        float z = -in.readInt() / 256f;
        float y = in.readInt() / 256f;
        float x = -in.readInt() / 256f;

        location = new Vector3f(x, y, z);

        range = in.readInt() / 256f / 256f;

        float r = in.readUnsignedShort() / 255f;
        float g = in.readUnsignedShort() / 255f;
        float b = in.readUnsignedShort() / 255f;
        color = new ColorRGBA(r, g, b, 1f);
    }
}