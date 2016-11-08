package org.pstale.asset.struct;

import java.io.IOException;

import com.jme3.util.LittleEndien;

/**
 * size = 36
 */
public class FACE extends Flyweight{
	public int[] v = new int[4];// a,b,c,Matrial
	public FTPOINT[] t = new FTPOINT[3];
	public int lpTexLink;
	public TEXLINK TexLink;

	@Override
	public void loadData(LittleEndien in) throws IOException {
		for (int i = 0; i < 4; i++) {
			v[i] = in.readUnsignedShort();
		}

		for (int i = 0; i < 3; i++) {
			t[i] = new FTPOINT();
			t[i].loadData(in);
		}

		lpTexLink = in.readInt();
		
	}
}