package org.pstale.asset.struct;

import java.io.IOException;

import com.jme3.util.LittleEndien;

// size = 8
public class FTPOINT extends Flyweight {
	float u, v;

	public void loadData(LittleEndien in) throws IOException {
		u = in.readFloat();
		v = in.readFloat();
	}
}