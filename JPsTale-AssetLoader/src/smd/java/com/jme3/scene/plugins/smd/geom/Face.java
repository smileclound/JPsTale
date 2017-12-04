package com.jme3.scene.plugins.smd.geom;

import java.io.IOException;

import org.pstale.assets.Flyweight;

import com.jme3.math.Vector2f;
import com.jme3.scene.plugins.smd.material.TEXLINK;
import com.jme3.util.LittleEndien;

/**
 * Face记录了Mesh中的“面”
 * 
 * size = 36
 */
public class Face extends Flyweight {
    /**
     * 使用a、b、c三个索引来组成三角形，并使用mtlid来记录这个三角形对应哪个材质。
     */
    public int[] v = new int[4];// a,b,c,Matrial
    
    /**
     * 记录这个三角形中每个顶点所对应的纹理坐标。
     */
    public Vector2f[] t = new Vector2f[3];
    
    /**
     * lpTexLink是一个指针，当它的值不为NULL时，说明有一个纹理贴图关联到了这个面。
     */
    public int lpTexLink;
    
    /**
     * @see GeomObject#relinkFaceAndTex()
     */
    public TEXLINK TexLink;

    @Override
    public void loadData(LittleEndien in) throws IOException {
        for (int i = 0; i < 4; i++) {
            v[i] = in.readUnsignedShort();
        }

        for (int i = 0; i < 3; i++) {
            float u = in.readFloat();
            float v = in.readFloat();
            t[i] = new Vector2f(u, v);
        }

        lpTexLink = in.readInt();

    }
}