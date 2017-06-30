package com.jme3.scene.plugins.smd.scene;

import java.io.IOException;

import org.pstale.assets.Flyweight;

import com.jme3.scene.plugins.smd.material.TEXLINK;
import com.jme3.scene.plugins.smd.math.Vector2F;
import com.jme3.util.LittleEndien;

/**
 * size = 36
 */
public class Face extends Flyweight {
    public int[] v = new int[4];// a,b,c,Matrial
    public Vector2F[] t = new Vector2F[3];
    public int lpTexLink;
    public TEXLINK TexLink;

    @Override
    public void loadData(LittleEndien in) throws IOException {
        for (int i = 0; i < 4; i++) {
            v[i] = in.readUnsignedShort();
        }

        for (int i = 0; i < 3; i++) {
            t[i] = new Vector2F();
            t[i].loadData(in);
        }

        lpTexLink = in.readInt();

    }
}