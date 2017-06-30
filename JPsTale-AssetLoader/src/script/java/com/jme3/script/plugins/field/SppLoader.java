package com.jme3.script.plugins.field;

import java.io.IOException;
import java.util.ArrayList;

import org.pstale.fields.StartPoint;

import com.jme3.asset.AssetInfo;
import com.jme3.asset.AssetLoader;
import com.jme3.util.LittleEndien;

/**
 * spp文件记录了地区中的刷怪点。 每个地图最多只有200个刷怪点，每个刷怪点的数据由4个整数(共12字节)来存储，数据结构如下：
 * 
 * <pre>
 * struct STG_START_POINT {
 *  int state;
 *  int x,z;
 *};
 *state标记了这个点是否正在使用，0表示未使用，1表示使用。
 *x,z分别记录了刷怪点在大地图上的坐标。
 * </pre>
 * 
 * 由于刷怪点的数量和刷怪点数据结构是固定的，因此每个spp文件的大小也是固定的，共2400字节。
 * 
 * @author yanmaoyuan
 *
 */
public class SppLoader implements AssetLoader {

    /**
     * 地区刷怪点最大数量。
     */
    public final static int STG_START_POINT_MAX = 200;

    @Override
    public Object load(AssetInfo asset) throws IOException {

        LittleEndien in = new LittleEndien(asset.openStream());

        /**
         * 解析出生点数据
         */
        ArrayList<StartPoint> spp = new ArrayList<StartPoint>(STG_START_POINT_MAX);
        for (int i = 0; i < STG_START_POINT_MAX; i++) {
            int state = in.readInt();
            int z = -in.readInt();
            int x = -in.readInt();

            if (state != 0) {
                spp.add(new StartPoint(state, x, z));
            }
        }

        in.close();

        return spp;
    }

}
