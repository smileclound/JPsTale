package org.pstale.loader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.pstale.fields.StartPoint;

/**
 * spp文件记录了地区中的刷怪点。
 * 每个地图最多只有200个刷怪点，每个刷怪点的数据由4个整数(共12字节)来存储，数据结构如下：
 *<pre>struct STG_START_POINT {
 *  int state;
 *  int x,z;
 *};
 *state标记了这个点是否正在使用，0表示未使用，1表示使用。
 *x,z分别记录了刷怪点在大地图上的坐标。
 * </pre>
 * 由于刷怪点的数量和刷怪点数据结构是固定的，因此每个spp文件的大小也是固定的，共2400字节。
 * 
 * @author yanmaoyuan
 *
 */
public class SppLoader {
	
	/**
	 * 地区刷怪点最大数量。
	 */
	public final static int STG_START_POINT_MAX = 200;
	
	public Object load(String fileName) throws IOException {
		/**
		 * 判断文件是否存在
		 */
		File file = new File(fileName);
		if (!file.exists()) {
			return null;
		}

		/**
		 * 分配内存
		 */
		int length = STG_START_POINT_MAX * 12;
		byte[] buffer = new byte[length];

		/**
		 * 读取文件
		 */
		FileInputStream fis = new FileInputStream(file);
		fis.read(buffer);
		fis.close();
		
		/**
		 * 解析出生点数据
		 */
		StartPoint[] points = new StartPoint[STG_START_POINT_MAX];
		for(int i=0; i<STG_START_POINT_MAX; i++) {
			int index = i*12;
			int state = makeInt(buffer, index);
			int x = makeInt(buffer, index+4);
			int z = makeInt(buffer, index+8);
			
			if (state != 0) {
				points[i] = new StartPoint(state, x, z);
				System.out.println("x=" + x + " z=" + z);
			}
		}
		
		return points;
	}
	
	/**
	 * 按照LITTLE_ENDIAN的顺序，从字节数组中读取4个字节，并组成一个整数。
	 * @param b0
	 * @param b1
	 * @param b2
	 * @param b3
	 * @return
	 */
	static private int makeInt(byte[] buffer, int index) {
		byte b0 = buffer[index], b1 = buffer[index+1], b2 = buffer[index+2], b3 = buffer[index+3];
        return (((b3       ) << 24) |
                ((b2 & 0xff) << 16) |
                ((b1 & 0xff) <<  8) |
                ((b0 & 0xff)      ));
    }
	
	public static void main(String[] args) throws Exception {
		SppLoader loader = new SppLoader();
		loader.load("assets/server/Field/fore-3.ase.spp");
	}
}
