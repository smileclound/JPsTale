package com.jme3.script.plugins.character;

import java.io.IOException;

import org.pstale.assets.Flyweight;

import com.jme3.scene.plugins.smd.animation.PAT3D;
import com.jme3.util.LittleEndien;

public class DPAT extends Flyweight {

    DPAT lpTalkLink;

    DPAT smDinaLink;
    PAT3D Pat;

    String patName;// [64]
    int UseCount;
    int dwSpeedFindSum;

    ModelInfo lpModelInfo;

    int LastUsedTime;

    @Override
    public void loadData(LittleEndien in) throws IOException {
        in.readInt();// lpTalkLink
        in.readInt();// smDinaLink
        in.readInt();// Pat

        patName = getString(in, 64);
        UseCount = in.readInt();
        dwSpeedFindSum = in.readInt();

        in.readInt();// lpModelInfo

        LastUsedTime = in.readInt();
    }
}
