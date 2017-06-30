package com.jme3.script.plugins.character;

import java.io.IOException;

import org.pstale.assets.Flyweight;

import com.jme3.util.LittleEndien;

/**
 * size = 4+64 = 68
 * 
 * @author yanmaoyuan
 *
 */
public class ModelGroup extends Flyweight {
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
