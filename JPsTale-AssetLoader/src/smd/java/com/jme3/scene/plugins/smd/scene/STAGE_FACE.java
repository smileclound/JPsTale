package com.jme3.scene.plugins.smd.scene;

import java.io.IOException;

import com.jme3.scene.plugins.smd.Flyweight;
import com.jme3.scene.plugins.smd.material.TEXLINK;
import com.jme3.util.LittleEndien;

/**
 * size = 28
 * 
 */
public class STAGE_FACE extends Flyweight {
    int sum;
    int CalcSum;
    public int v[] = new int[4];// a, b, c, mat_id;
    int lpTexLink;// 这是一个指针，指向TEXLINK结构体
    public TEXLINK TexLink;// 若lpTexLink != 0，则TexLink指向一个实际的对象象

    float nx, ny, nz, y;// Cross氦磐( Normal ) ( nx , ny , nz , [0,1,0]氦磐 Y );

    public void loadData(LittleEndien in) throws IOException {
        sum = in.readInt();
        CalcSum = in.readInt();

        for (int i = 0; i < 4; i++) {
            v[i] = in.readUnsignedShort();
        }

        lpTexLink = in.readInt();// 纹理坐标的指针。smTEX_LINK *lpTexLink

        nx = in.readShort() / 32767f;// nx
        ny = in.readShort() / 32767f;// ny
        nz = in.readShort() / 32767f;// nz
        y = in.readShort() / 32767f;// Y 除以32767后是 1/8PI，不知道有何用。
    }
}