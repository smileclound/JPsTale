package com.jme3.scene.plugins.smd.animation;

import java.io.IOException;

import org.pstale.assets.Flyweight;

import com.jme3.util.LittleEndien;

/**
 * size = 16
 */
public class FRAME_POS extends Flyweight {
    int startFrame;
    int endFrame;
    int posNum;
    int posCnt;

    public void loadData(LittleEndien in) throws IOException {
        startFrame = in.readInt();
        endFrame = in.readInt();
        posNum = in.readInt();
        posCnt = in.readInt();
    }
}