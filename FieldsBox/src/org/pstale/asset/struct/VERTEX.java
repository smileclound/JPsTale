package org.pstale.asset.struct;

import java.io.IOException;

import com.jme3.math.Vector3f;
import com.jme3.util.LittleEndien;

/**
 * size = 24
 * 
 */
public class VERTEX extends Flyweight {
	long x, y, z;
	Vector3f v;// 坐标
	Vector3f n;// normals 法向量

	public void loadData(LittleEndien in) throws IOException {
		x = in.readInt();
		y = in.readInt();
		z = in.readInt();

		v = new Vector3f(x / 256f, y / 256f, z / 256f);
		
		float nx = in.readInt() / 256f;
		float ny = in.readInt() / 256f;
		float nz = in.readInt() / 256f;

		n = new Vector3f(nx, ny, nz);
	}
}