package org.pstale.asset.struct;

import java.io.IOException;

import com.jme3.util.LittleEndien;

/**
 * size = 16
 */
public class TM_POS extends Flyweight {
    public int frame;
    public float x, y, z;

    public void loadData(LittleEndien in) throws IOException {
        frame = in.readInt();
        x = in.readFloat();
        y = in.readFloat();
        z = in.readFloat();
    }
}