package com.jme3.scene.plugins.smd.math;

import java.io.IOException;

import org.pstale.assets.Flyweight;

import com.jme3.util.LittleEndien;

// size = 8
public class Vector2F extends Flyweight {
    public float u, v;

    public void loadData(LittleEndien in) throws IOException {
        u = in.readFloat();
        v = in.readFloat();
    }
}