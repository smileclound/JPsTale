package com.jme3.scene.plugins.smd.material;

import java.io.IOException;

import com.jme3.scene.plugins.smd.Flyweight;
import com.jme3.util.LittleEndien;

/**
 * size = 32
 * 
 */
public class TEXLINK extends Flyweight {
    public float[] u = new float[3];
    public float[] v = new float[3];
    int hTexture;
    public int lpNextTex;// 这是一个指针，指向TEXLINK结构体
    public TEXLINK NextTex;// 若lpNextTex != 0，则NextTex指向一个实际的对象

    public void loadData(LittleEndien in) throws IOException {
        u[0] = in.readFloat();
        u[1] = in.readFloat();
        u[2] = in.readFloat();

        v[0] = in.readFloat();
        v[1] = in.readFloat();
        v[2] = in.readFloat();

        hTexture = in.readInt();// *hTexture;
        lpNextTex = in.readInt();// *NextTex;
    }
}