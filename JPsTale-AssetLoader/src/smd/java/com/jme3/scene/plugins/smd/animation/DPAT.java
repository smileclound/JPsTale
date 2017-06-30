package com.jme3.scene.plugins.smd.animation;

import java.io.IOException;

import com.jme3.scene.plugins.smd.Flyweight;
import com.jme3.script.plugins.character.MODELINFO;
import com.jme3.util.LittleEndien;

public class DPAT extends Flyweight {

    DPAT lpTalkLink;

    DPAT smDinaLink;
    PAT3D Pat;

    String patName;// [64]
    int UseCount;
    int dwSpeedFindSum;

    MODELINFO lpModelInfo;

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
