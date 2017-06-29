package org.pstale.asset.struct;

import java.io.IOException;

import com.jme3.util.LittleEndien;

/**
 * size = 40
 */
public class FILE_OBJINFO extends Flyweight {
    /**
     * 物体的名称
     */
    String NodeName;// 32字节
    /**
     * 这个Obj3D区块在文件中所占的字节数。
     */
    int Length;
    /**
     * 这个Obj3D区块在文件中的其实位置。
     */
    int ObjFilePoint;

    public void loadData(LittleEndien in) throws IOException {
        NodeName = getString(in, 32);
        Length = in.readInt();
        ObjFilePoint = in.readInt();
    }
}