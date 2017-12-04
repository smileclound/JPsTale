package com.jme3.scene.plugins.smd.geom;

import java.io.IOException;

import org.pstale.assets.Flyweight;

import com.jme3.script.plugins.character.ModelGroup;
import com.jme3.util.LittleEndien;

/**
 * Inx文件表示动画模型。
 * @author kocla
 *
 */
public class AnimateModel extends Flyweight {

    public String modelFile;
    public String motionFile;
    public String subModelFile;

    public ModelGroup HighModel;
    public ModelGroup DefaultModel;
    public ModelGroup LowModel;

    public MotionInfo[] subMotions = new MotionInfo[MOTION_INFO_MAX];
    public int subMotionCount;

    public int FileTypeKeyWord;
    public int LinkFileKeyWord;

    public String motionLinkFile;// 64

    // 弊 颇老 2俺啊 *.ini *.in 颇老疙捞 鞍篮 版快. 笛促 鞍篮 捞抚狼 *.inx 肺 唱坷扁东矫.
    // 颇老疙捞 崔扼具 钦聪促.
    public String talkLinkFile; // 64
    public String talkMotionFile;// 64
    public MotionInfo[] talkMotions = new MotionInfo[TALK_MOTION_INFO_MAX];
    public int talkMotionCount;

    public int[] npcMotionRate = new int[NPC_MOTION_INFO_MAX];
    public int[] npcMotionRateCnt = new int[100];

    public int[] talkMotionRate = new int[TALK_MOTION_INFO_MAX];
    public int[][] talkMotionRateCnt = new int[TALK_MOTION_FILE_MAX][100];

    @Override
    public void loadData(LittleEndien in) throws IOException {
        /******
         * 旧的模型，MOTIONINFO结构体只有120字节，而新模型的MOTIONINFO结构体有172字节。 文件体积上有差异。
         */
        if (in.available() > 67084) {
            MotionInfo.KPT = true;
        } else {
            MotionInfo.KPT = false;
        }

        modelFile = getString(in, 64);
        motionFile = getString(in, 64);
        subModelFile = getString(in, 64);

        HighModel = new ModelGroup();
        HighModel.loadData(in);

        DefaultModel = new ModelGroup();
        DefaultModel.loadData(in);

        LowModel = new ModelGroup();
        LowModel.loadData(in);

        for (int i = 0; i < MOTION_INFO_MAX; i++) {
            subMotions[i] = new MotionInfo();
            subMotions[i].loadData(in);
        }

        // FIXME ???????? drz是这么写得，但是这里明明是个Int呀？
        // MotionCount = in.readInt();
        subMotionCount = in.readShort();//
        in.readShort();

        FileTypeKeyWord = in.readInt();
        LinkFileKeyWord = in.readInt();

        motionLinkFile = getString(in, 64);

        talkLinkFile = getString(in, 64);
        talkMotionFile = getString(in, 64);
        for (int i = 0; i < TALK_MOTION_INFO_MAX; i++) {
            talkMotions[i] = new MotionInfo();
            talkMotions[i].loadData(in);
        }
        talkMotionCount = in.readInt();

        for (int i = 0; i < NPC_MOTION_INFO_MAX; i++) {
            npcMotionRate[i] = in.readInt();
        }
        for (int i = 0; i < 100; i++) {
            npcMotionRateCnt[i] = in.readInt();
        }

        for (int i = 0; i < TALK_MOTION_INFO_MAX; i++) {
            talkMotionRate[i] = in.readInt();
        }

        for (int i = 0; i < TALK_MOTION_FILE_MAX; i++) {
            for (int j = 0; j < 100; j++) {
                talkMotionRateCnt[i][j] = in.readInt();
            }
        }

        in.close();
    }

}
