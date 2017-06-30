package com.jme3.scene.plugins.smd;

import java.io.IOException;

import org.pstale.assets.Flyweight;

import com.jme3.util.LittleEndien;

/**
 * size = 40
 */
public class SmdFileObjInfo extends Flyweight {
    /**
     * 物体的名称
     */
    public String NodeName;// 32字节
    /**
     * 这个Obj3D区块在文件中所占的字节数。
     */
    public int Length;
    /**
     * 这个Obj3D区块在文件中的其实位置。
     */
    public int ObjFilePoint;

    public void loadData(LittleEndien in) throws IOException {
        NodeName = getString(in, 32);
        Length = in.readInt();
        ObjFilePoint = in.readInt();
    }
}