package org.pstale.entity.chars;

import com.jme3.scene.plugins.smd.animation.PAT3D;
import com.jme3.scene.plugins.smd.geom.GeomObject;

public class CHARTOOL {
    GeomObject ObjBip;
    PAT3D PatTool;
    int dwItemCode;

    int SizeMax, SizeMin;

    int ColorBlink;
    short[] sColors = new short[4];
    int DispEffect;
    int BlinkScale;
    int EffectKind;

    int TexMixCode, TexScroll;
}
