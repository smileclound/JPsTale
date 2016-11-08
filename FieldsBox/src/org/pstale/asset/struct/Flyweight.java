package org.pstale.asset.struct;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.jme3.util.LittleEndien;

public abstract class Flyweight {
	/**
	 * 精灵的动画使用3DS MAX的默认速率，每秒30Tick，每Tick共160帧。 也就是每秒4800帧。
	 * 
	 * 但是smd文件中也另外存储了2个参数： (1) 每秒Tick数 (默认30) (2) 每Tick帧数 (默认160)
	 * 这两个变量的值控制了动画播放的速率。
	 */
	float framePerSecond = 4800f;
	
	boolean USE_LIGHT = false;

	// 是否使用OPENGL坐标系
	boolean OPEN_GL_AXIS = true;
	// 是否打印动画日志
	boolean LOG_ANIMATION = false;
	
	public final static int OBJ_FRAME_SEARCH_MAX = 32;
	
	public final static int sMATS_SCRIPT_WIND = 1;
	public final static int sMATS_SCRIPT_WINDZ1 = 0x0020;
	public final static int sMATS_SCRIPT_WINDZ2 = 0x0040;
	public final static int sMATS_SCRIPT_WINDX1 = 0x0080;
	public final static int sMATS_SCRIPT_WINDX2 = 0x0100;
	public final static int sMATS_SCRIPT_WATER = 0x0200;
	public final static int sMATS_SCRIPT_NOTVIEW = 0x0400;
	public final static int sMATS_SCRIPT_PASS = 0x0800;
	public final static int sMATS_SCRIPT_NOTPASS = 0x1000;
	public final static int sMATS_SCRIPT_RENDLATTER = 0x2000;
	public final static int sMATS_SCRIPT_BLINK_COLOR = 0x4000;
	public final static int sMATS_SCRIPT_CHECK_ICE = 0x8000;
	public final static int sMATS_SCRIPT_ORG_WATER = 0x10000;
	
	
	/**
	 * <pre>
	 * #define	smLIGHT_TYPE_NIGHT		0x00001
	 * #define	smLIGHT_TYPE_LENS		0x00002
	 * #define	smLIGHT_TYPE_PULSE2	0x00004
	 * #define	SMLIGHT_TYPE_OBJ		0x00008
	 * #define	smLIGHT_TYPE_DYNAMIC	0x80000
	 * </pre>
	 */
	
	Logger log = Logger.getLogger(Flyweight.class);
	
	abstract void loadData(LittleEndien in) throws IOException;
	
	
	/**
	 * This reads bytes until it gets 0x00 and returns the corresponding string.
	 */
	public String getString(LittleEndien in) throws IOException {
		StringBuffer stringBuffer = new StringBuffer();
		char charIn = (char) in.read();
		while (charIn != 0x00) {
			stringBuffer.append(charIn);
			charIn = (char) in.read();
		}
		return stringBuffer.toString();
	}
	
	/**
	 * This reads bytes until it gets 0x00 and returns the corresponding string.
	 */
	public String getString(LittleEndien in, int size) throws IOException {
		int count=0;
		StringBuffer stringBuffer = new StringBuffer();
		char charIn = (char) in.read();
		count++;
		while (charIn != 0x00 && count < size) {
			stringBuffer.append(charIn);
			charIn = (char) in.read();
			count++;
		}
		
		// skip useless byte
		if (count < size) {
			for(int i=count; i< size; i++) {
				in.read();
			}
		}
		return stringBuffer.toString();
	}
}
