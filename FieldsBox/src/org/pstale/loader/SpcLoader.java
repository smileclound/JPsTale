package org.pstale.loader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * 读取场景中的NPC信息，这些信息存储在一个.spc文件中。
 * 一个场景中最多只能有个100个NPC，每个NPC有504字节，每个spc文件大小固定为50400字节。数据结构如下：
<pre>
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
</pre>
 * @author yanmaoyuan
 *
 */
public class SpcLoader {
	
	/**
	 * NPC角色数量
	 */
	private final static int FIX_CHAR_MAX	= 100;
	private final static int STG_CHAR_INFO_SIZE = 504;
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
		int length = FIX_CHAR_MAX * STG_CHAR_INFO_SIZE;
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
		for(int i=0; i<FIX_CHAR_MAX; i++) {
			int index = i*STG_CHAR_INFO_SIZE;
			
			int size = makeInt(buffer, index);// 结构体的大小，实际上应该是504
			int code = makeInt(buffer, index+4);// 这是一个标记，如果存在NPC的信息，那么code应该不为0，没有实际意义。
			
			String ini = makeStr(buffer, index+40);// 这个字符串指向了NPC的模型、动画
			String npc = makeStr(buffer, index+104);// 这个字符串指向了NPC的脚本，诸如对话、售卖物品列表、职业等。
			
			// 这是NPC在大地图上的坐标
			int x = makeInt(buffer, index+476);
			int y = makeInt(buffer, index+480);
			int z = makeInt(buffer, index+484);
			
			// 这是NPC面向的角度，3个值分别是NPC绕x,y,z轴旋转的角度。
			// 目前还不太确定这个角度是弧度值还是欧拉角，但是可以肯定这个值被放大了。也许要除以256或65536。
			int ax = makeInt(buffer, index+488);
			int ay = makeInt(buffer, index+492);
			int az = makeInt(buffer, index+496);
			
			int state = makeInt(buffer, index+500);
			
			if (code != 0) {
				System.out.println("size=" + size);
				System.out.println("code=" + code);
				System.out.println("model=" + ini);
				System.out.println("npc=" + npc);
				
				System.out.println("posi=" + x + ", " + y + ", " + z);
				System.out.println("face=" + ax + ", " + ay + ", " + az);
				
				System.out.println("state=" + state);
				
				System.out.println();
			}
		}
		
		return null;
	}
	
	/**
	 * 读取字符串
	 * @param buffer
	 * @param index
	 * @return
	 */
	static private String makeStr(byte[] buffer, int index) {
		int length = 0;
		while(buffer[index+length] != 0) {
			length++;
		}
		String str = new String(buffer, index, length, Charset.defaultCharset());
		
		return str;
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
		SpcLoader loader = new SpcLoader();
		loader.load("assets/server/Field/fore-3.ase.spc");
	}
}
