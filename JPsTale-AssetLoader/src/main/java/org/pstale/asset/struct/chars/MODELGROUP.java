package org.pstale.asset.struct.chars;

import java.io.IOException;

import org.pstale.asset.struct.Flyweight;

import com.jme3.util.LittleEndien;

/**
 * size = 4+64 = 68
 * 
 * @author yanmaoyuan
 *
 */
public class MODELGROUP extends Flyweight {
    public int ModelNameCnt;
    public String[] szModelName = new String[4];// 每个String长度为16

    @Override
    public void loadData(LittleEndien in) throws IOException {
        ModelNameCnt = in.readInt();

        for (int i = 0; i < 4; i++) {
            szModelName[i] = getString(in, 16);
        }
    }
}
