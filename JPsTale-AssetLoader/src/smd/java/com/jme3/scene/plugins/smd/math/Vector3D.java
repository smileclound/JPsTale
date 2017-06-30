package com.jme3.scene.plugins.smd.math;

import java.io.IOException;

import org.pstale.assets.Flyweight;

import com.jme3.util.LittleEndien;

// size = 12
public class Vector3D extends Flyweight {
    public int x, y, z;

    public Vector3D() {
        x = y = z = 0;
    }

    public void loadData(LittleEndien in) throws IOException {
        x = in.readInt();
        y = in.readInt();
        z = in.readInt();
    }
}