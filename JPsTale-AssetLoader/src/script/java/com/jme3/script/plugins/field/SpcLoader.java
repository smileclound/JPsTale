package com.jme3.script.plugins.field;

import java.io.IOException;
import java.util.ArrayList;

import com.jme3.asset.AssetInfo;
import com.jme3.asset.AssetLoader;
import com.jme3.util.LittleEndien;

/**
 * 读取场景中的NPC信息，这些信息存储在一个.spc文件中。
 * 一个场景中最多只能有个100个NPC，每个NPC有504字节，每个spc文件大小固定为50400字节。数据结构如下：
 * 
 * <pre>
struct smTRNAS_PLAYERINFO
{
	int	size;
	int code;

	smCHAR_INFO	smCharInfo;

	DWORD	dwObjectSerial;

	int	x,y,z;
	int ax,ay,az;
	int state;
};
 * </pre>
 * 
 * @author yanmaoyuan
 *
 */
public class SpcLoader implements AssetLoader {

    /**
     * NPC角色数量
     */
    private final static int FIX_CHAR_MAX = 100;
    private final static int STG_CHAR_INFO_SIZE = 504;

    @Override
    public ArrayList<CharacterTransform> load(AssetInfo assetInfo) throws IOException {
        /**
         * 分配内存
         */
        int length = FIX_CHAR_MAX * STG_CHAR_INFO_SIZE;
        int available = assetInfo.openStream().available();

        assert available == length;

        ArrayList<CharacterTransform> npcs = new ArrayList<CharacterTransform>();

        LittleEndien in = new LittleEndien(assetInfo.openStream());

        /**
         * 解析出生点数据
         */
        for (int i = 0; i < FIX_CHAR_MAX; i++) {
            CharacterTransform playerinfo = new CharacterTransform();
            playerinfo.loadData(in);

            if (playerinfo.code != 0) {
                npcs.add(playerinfo);
            }
        }

        return npcs;
    }
}
