package org.pstale.utils;

/**
 * wav音频文件解码。
 * 
 * @author yanmaoyuan
 *
 */
public class WavDecoder {
    /**
     * 转换WAV文件。精灵把角色、玩家、NPC的音频文件头从RIFF改成了JODO。
     * 
     * @param buffer
     *            从文件头开始的数据，4字节
     * @param readable
     *            是否使文件可读?
     */
    public static void convertWAV(byte[] buffer, boolean readable) {
        if (readable) {
            // 解密WAV
            buffer[0] = 0x52;
            buffer[1] = 0x49;
            buffer[2] = 0x46;
            buffer[3] = 0x46;
        } else {
            // 加密WAV
            buffer[0] = 0x4A;
            buffer[1] = 0x4F;
            buffer[2] = 0x44;
            buffer[3] = 0x4F;
        }
    }
}
