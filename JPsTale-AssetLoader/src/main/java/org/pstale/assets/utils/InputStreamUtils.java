package org.pstale.assets.utils;

import java.io.IOException;

import com.jme3.util.LittleEndien;

public class InputStreamUtils {
    /**
     * This reads bytes until it gets 0x00 and returns the corresponding string.
     */
    public static String getString(LittleEndien in) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        char charIn = (char) in.read();
        while (charIn != 0x00) {
            stringBuffer.append(charIn);
            charIn = (char) in.read();
        }
        return stringBuffer.toString().trim();
    }

    /**
     * This reads bytes until it gets 0x00 and returns the corresponding string.
     */
    public static String getString(LittleEndien in, int size) throws IOException {
        int count = 0;
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
            for (int i = count; i < size; i++) {
                in.read();
            }
        }
        return stringBuffer.toString().trim();
    }
}
