package org.pstale.asset.struct;

import java.io.IOException;

import com.jme3.util.LittleEndien;

/**
 * SMD文件头 size = 556;
 */
public class FILE_HEADER extends Flyweight {
    String header;// 24字节
    int objCounter;
    int matCounter;
    int matFilePoint;
    int firstObjInfoPoint;
    int tmFrameCounter;
    FRAME_POS[] TmFrame = new FRAME_POS[OBJ_FRAME_SEARCH_MAX];// 512字节

    /**
     * 读取文件头
     */
    public void loadData(LittleEndien in) throws IOException {
        header = getString(in, 24);
        objCounter = in.readInt();
        matCounter = in.readInt();
        matFilePoint = in.readInt();
        firstObjInfoPoint = in.readInt();
        tmFrameCounter = in.readInt();
        for (int i = 0; i < OBJ_FRAME_SEARCH_MAX; i++) {
            TmFrame[i] = new FRAME_POS();
            TmFrame[i].loadData(in);
        }
    }

}