package com.jme3.scene.plugins.smd.geom;

import java.io.IOException;

import org.pstale.assets.Flyweight;

import com.jme3.util.LittleEndien;

/**
 * size = 118 ->120 KPT的字节长度为172，不知道哪里多了52字节
 * 
 * @author yanmaoyuan
 * 
 */
public class MotionInfo extends Flyweight {

    // KPT的字节长度为172，不知道哪里多了52字节
    public static boolean KPT = false;

    public int State; // 惑怕 TRUE搁 蜡瓤

    public int MotionKeyWord_1;
    public int StartFrame; // 矫累 橇饭烙
    public int MotionKeyWord_2;
    public int EndFrame; // 辆丰 橇饭烙

    public int[] EventFrame = new int[4]; // 捞亥飘 积己 橇饭烙

    public int ItemCodeCount; // 秦寸 酒捞袍 府胶飘 墨款磐 ( 0 绝澜 -1 傈眉 秦寸 )
    public byte[] ItemCodeList = new byte[MOTION_TOOL_MAX]; // 秦寸 酒捞袍 内靛 府胶飘
    public int dwJobCodeBit; // 秦寸 流诀喊 厚飘 付胶农
    public byte[] SkillCodeList = new byte[MOTION_SKIL_MAX]; // 秦寸 胶懦 锅龋

    public int MapPosition; // 秦寸 甘 利侩 ( 0-包拌绝澜 付阑 1 - 鞘靛 2 )

    public int Repeat; // 馆汗 咯何
    public char KeyCode;// CHAR //悼累 矫累 虐
    public int MotionFrame; // 葛记 楷搬 颇老 锅龋

    @Override
    public void loadData(LittleEndien in) throws IOException {
        State = in.readInt();
        
        // TODO 下面几个帧，似乎都应该除以256
        MotionKeyWord_1 = readKey(in);
        StartFrame = readKey(in);
        MotionKeyWord_2 = readKey(in);
        EndFrame = readKey(in);

        for (int i = 0; i < 4; i++) {
            EventFrame[i] = readKey(in);
        }

        ItemCodeCount = in.readInt();

        in.readFully(ItemCodeList);

        dwJobCodeBit = in.readInt();

        in.readFully(SkillCodeList);

        if (KPT) {
            byte[] tmp = new byte[52];
            in.readFully(tmp);
        }

        MapPosition = in.readInt();
        Repeat = in.readInt();
        KeyCode = in.readChar();
        in.readChar();
        MotionFrame = in.readInt();
    }

    int[] val = new int[2];

    int readKey(LittleEndien in) throws IOException {
        val[0] = in.read();
        in.read();
        val[1] = in.read();
        in.read();

        return val[0] + val[1] << 8;
    }
}
