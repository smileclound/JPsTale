package org.pstale.asset.struct;

import java.io.IOException;

import com.jme3.util.LittleEndien;

// size = 12
public class POINT3D extends Flyweight {
	int x, y, z;

	POINT3D() {
		x = y = z = 0;
	}

	public void loadData(LittleEndien in) throws IOException {
		x = in.readInt();
		y = in.readInt();
		z = in.readInt();
	}
}