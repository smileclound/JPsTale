package org.pstale.asset.struct.chars;

import java.io.IOException;

import org.pstale.asset.struct.Flyweight;

import com.jme3.util.LittleEndien;

/**
<pre>
struct smTRNAS_PLAYERINFO
{
	int size;
	int code;

	smCHAR_INFO smCharInfo;

	DWORD dwObjectSerial;

	int x,y,z;
	int ax,ay,az;
	int state;
};</pre>
 * size = 504
 * @author yanmaoyuan
 *
 */
public class TRNAS_PLAYERINFO extends Flyweight {
	
	public int size;
	public int code;

	public CHAR_INFO charInfo;

	public int objectSerial;

	public float x, y, z;
	public float ax, ay, az;
	public int state;

	@Override
	public void loadData(LittleEndien in) throws IOException {
		size = in.readInt();
		code = in.readInt();
		
		charInfo = new CHAR_INFO();
		charInfo.loadData(in);
		
		objectSerial = in.readInt();
		
		z = -in.readInt() / 256f;
		y = in.readInt() / 256f;
		x = -in.readInt() / 256f;
		
		az = -in.readInt() / 256f;
		ay = in.readInt() / 256f;
		ax = -in.readInt() / 256f;
		
		state = in.readInt();

	}
}