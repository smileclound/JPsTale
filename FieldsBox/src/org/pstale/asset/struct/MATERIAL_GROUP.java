package org.pstale.asset.struct;

import java.io.IOException;

import com.jme3.util.LittleEndien;

/**
 * 若文件头中的mat>0，说明有材质。 接下来第三部分应该是一个完整的smMATERIAL_GROUP对象。 size = 88。
 */
public class MATERIAL_GROUP extends Flyweight {
	// DWORD Head
	public MATERIAL[] materials;
	public int materialCount;
	int reformTexture;
	int maxMaterial;
	int lastSearchMaterial;
	String lastSearchName;

	// //////////////
	// 计算读取结束后整个MaterialGroup占用了多少内存，没有实际意义。
	// int size = 0;
	// //////////////
	/**
	 * 读取smMATERIAL_GROUP数据
	 */

	/**
	 * 载入所有材质
	 */
	public void loadData(LittleEndien in) throws IOException {
		in.readInt();// Head
		in.readInt();// *smMaterial
		materialCount = in.readInt();
		reformTexture = in.readInt();
		maxMaterial = in.readInt();
		lastSearchMaterial = in.readInt();
		lastSearchName = getString(in, 64);

		// size += 88;
		
		materials = new MATERIAL[materialCount];

		for (int i = 0; i < materialCount; i++) {
			materials[i] = new MATERIAL();
			materials[i].loadData(in);
			// size += 320;

			if (materials[i].InUse != 0) {
				in.readInt();// int strLen; 这个整数记录了后续所有材质名称所占的字节数。
				// size += 4;
				// size += strLen;

				materials[i].smTexture = new TEXTURE[materials[i].TextureCounter];
				for (int j = 0; j < materials[i].TextureCounter; j++) {
					TEXTURE texture = new TEXTURE();
					materials[i].smTexture[j] = texture;
					texture.Name = getString(in);
					texture.NameA = getString(in);

					if (texture.NameA.length() > 1) {
						// TODO 还不知道NameA所代表的Tex有何用
					}
				}

				materials[i].smAnimTexture = new TEXTURE[materials[i].AnimTexCounter];
				for (int j = 0; j < materials[i].AnimTexCounter; j++) {
					TEXTURE texture = new TEXTURE();
					materials[i].smAnimTexture[j] = texture;
					texture.Name = getString(in);
					texture.NameA = getString(in);
				}
			}
		}
	}
}