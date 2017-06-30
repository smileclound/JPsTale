package com.jme3.scene.plugins.smd.animation;

import java.io.IOException;

import com.jme3.scene.plugins.smd.Flyweight;
import com.jme3.util.LittleEndien;

/**
 * size = 16
 */
public class TM_SCALE extends Flyweight {
    public int frame;
    public float x, y, z;

    public void loadData(LittleEndien in) throws IOException {
        frame = in.readInt();
        x = in.readInt() / 256f;
        y = in.readInt() / 256f;
        z = in.readInt() / 256f;
    }

}