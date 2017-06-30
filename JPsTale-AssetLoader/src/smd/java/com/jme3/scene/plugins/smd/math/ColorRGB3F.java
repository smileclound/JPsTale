package com.jme3.scene.plugins.smd.math;

import java.io.IOException;

import org.pstale.assets.Flyweight;

import com.jme3.util.LittleEndien;

/**
 * MaterialGroup中使用这个类来记录Diffuse size = 12。
 */
public class ColorRGB3F extends Flyweight {
    public float r, g, b;

    public void loadData(LittleEndien in) throws IOException {
        r = in.readFloat();
        g = in.readFloat();
        b = in.readFloat();
    }
}