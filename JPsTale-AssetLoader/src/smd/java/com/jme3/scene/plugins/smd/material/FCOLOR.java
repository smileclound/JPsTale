package com.jme3.scene.plugins.smd.material;

import java.io.IOException;

import com.jme3.scene.plugins.smd.Flyweight;
import com.jme3.util.LittleEndien;

/**
 * MaterialGroup中使用这个类来记录Diffuse size = 12。
 */
public class FCOLOR extends Flyweight {
    public float r, g, b;

    public void loadData(LittleEndien in) throws IOException {
        r = in.readFloat();
        g = in.readFloat();
        b = in.readFloat();
    }
}