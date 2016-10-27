package org.pstale.util;

public class ImageDecoder {

	/**
	 * 转换TGA文件
	 * @param buffer 从文件头开始的数据，至少18字节
	 * @param readable 是否使文件可读?
	 */
	public static void convertTGA(byte[] buffer, boolean readable) {
		if (readable) {
			// 解密TGA
            buffer[0] = 0x0;
            buffer[1] = 0x0;
        	for(byte i=2; i<18; i++) {
        		buffer[i] -= (byte)(i*i);
        	}
		} else {
			// 加密TGA
			buffer[0] = 0x47;
            buffer[1] = 0x38;
        	for(byte i=2; i<18; i++) {
        		buffer[i] += (byte)(i*i);
        	}
		}
	}
	
	/**
	 * 转换BMP文件
	 * @param buffer 从文件头开始的数据，至少16字节
	 * @param readable 是否使文件可读?
	 */
	public static void convertBMP(byte[] buffer, boolean readable) {
		if (readable) {
			// 解密BMP
			buffer[0] = 0x42;
			buffer[1] = 0x4D;
			for(byte i=2; i<14; i++) {
				buffer[i] -= (byte)(i*i);
			}
		} else {
			// 加密BMP
        	buffer[0] = 0x41;
        	buffer[1] = 0x38;
        	for(byte i=2; i<14; i++) {
        		buffer[i] += (byte)(i*i);
        	}
		}
		
	}
}
