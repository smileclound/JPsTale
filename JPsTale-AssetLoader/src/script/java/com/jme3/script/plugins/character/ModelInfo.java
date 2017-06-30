package com.jme3.script.plugins.character;

import java.io.IOException;

import org.pstale.assets.Flyweight;

import com.jme3.util.LittleEndien;

public class ModelInfo extends Flyweight {

    public String modelFile;
    public String animationFile;
    public String subModelFile;

    public ModelGroup HighModel;
    public ModelGroup DefaultModel;
    public ModelGroup LowModel;

    public MOTIONINFO[] motionInfo = new MOTIONINFO[MOTION_INFO_MAX];
    public int MotionCount;

    public int FileTypeKeyWord;
    public int LinkFileKeyWord;

    public String linkFile;// 64

    // 弊 颇老 2俺啊 *.ini *.in 颇老疙捞 鞍篮 版快. 笛促 鞍篮 捞抚狼 *.inx 肺 唱坷扁东矫.
    // 颇老疙捞 崔扼具 钦聪促.
    public String szTalkLinkFile; // 64
    public String szTalkMotionFile;// 64
    public MOTIONINFO[] TalkMotionInfo = new MOTIONINFO[TALK_MOTION_INFO_MAX];
    public int TalkMotionCount;

    public int[] NpcMotionRate = new int[NPC_MOTION_INFO_MAX];
    public int[] NpcMotionRateCnt = new int[100];

    public int[] TalkMotionRate = new int[TALK_MOTION_INFO_MAX];
    public int[][] TalkMotionRateCnt = new int[TALK_MOTION_FILE_MAX][100];

    @Override
    public void loadData(LittleEndien in) throws IOException {
        /******
         * 旧的模型，MOTIONINFO结构体只有120字节，而新模型的MOTIONINFO结构体有172字节。 文件体积上有差异。
         */
        if (in.available() > 67084) {
            MOTIONINFO.KPT = true;
        } else {
            MOTIONINFO.KPT = false;
        }

        modelFile = getString(in, 64);
        animationFile = getString(in, 64);
        subModelFile = getString(in, 64);

        HighModel = new ModelGroup();
        HighModel.loadData(in);

        DefaultModel = new ModelGroup();
        DefaultModel.loadData(in);

        LowModel = new ModelGroup();
        LowModel.loadData(in);

        for (int i = 0; i < MOTION_INFO_MAX; i++) {
            motionInfo[i] = new MOTIONINFO();
            motionInfo[i].loadData(in);
        }

        // FIXME ???????? drz是这么写得，但是这里明明是个Int呀？
        // MotionCount = in.readInt();
        MotionCount = in.readShort();//
        in.readShort();

        FileTypeKeyWord = in.readInt();
        LinkFileKeyWord = in.readInt();

        linkFile = getString(in, 64);

        szTalkLinkFile = getString(in, 64);
        szTalkMotionFile = getString(in, 64);
        for (int i = 0; i < TALK_MOTION_INFO_MAX; i++) {
            TalkMotionInfo[i] = new MOTIONINFO();
            TalkMotionInfo[i].loadData(in);
        }
        TalkMotionCount = in.readInt();

        for (int i = 0; i < NPC_MOTION_INFO_MAX; i++) {
            NpcMotionRate[i] = in.readInt();
        }
        for (int i = 0; i < 100; i++) {
            NpcMotionRateCnt[i] = in.readInt();
        }

        for (int i = 0; i < TALK_MOTION_INFO_MAX; i++) {
            TalkMotionRate[i] = in.readInt();
        }

        for (int i = 0; i < TALK_MOTION_FILE_MAX; i++) {
            for (int j = 0; j < 100; j++) {
                TalkMotionRateCnt[i][j] = in.readInt();
            }
        }

        in.close();
    }

}
