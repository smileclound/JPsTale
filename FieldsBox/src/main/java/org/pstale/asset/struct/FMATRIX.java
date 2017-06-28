package org.pstale.asset.struct;

import java.io.IOException;

import com.jme3.util.LittleEndien;

/**
 * size = 64
 */
public class FMATRIX extends Flyweight{
	float _11, _12, _13, _14;
	float _21, _22, _23, _24;
	float _31, _32, _33, _34;
	float _41, _42, _43, _44;

	public FMATRIX() {
		_11 = 1;
		_12 = 0;
		_13 = 0;
		_14 = 0;
		_21 = 0;
		_22 = 1;
		_23 = 0;
		_24 = 0;
		_31 = 0;
		_32 = 0;
		_33 = 1;
		_34 = 0;
		_41 = 0;
		_42 = 0;
		_43 = 0;
		_44 = 1;
	}

	public void loadData(LittleEndien in) throws IOException{
		_11 = in.readFloat();
		_12 = in.readFloat();
		_13 = in.readFloat();
		_14 = in.readFloat();
		_21 = in.readFloat();
		_22 = in.readFloat();
		_23 = in.readFloat();
		_24 = in.readFloat();
		_31 = in.readFloat();
		_32 = in.readFloat();
		_33 = in.readFloat();
		_34 = in.readFloat();
		_41 = in.readFloat();
		_42 = in.readFloat();
		_43 = in.readFloat();
		_44 = in.readFloat();
	}
}