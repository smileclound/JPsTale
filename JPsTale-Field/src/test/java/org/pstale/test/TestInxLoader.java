package org.pstale.test;

import java.io.File;

import org.apache.log4j.Logger;
import org.pstale.asset.struct.chars.MODELINFO;
import org.pstale.asset.struct.chars.MOTIONINFO;

import com.jme3.asset.AssetManager;
import com.jme3.asset.DesktopAssetManager;
import com.jme3.scene.plugins.smd.FileLocator;
import com.jme3.scene.plugins.smd.SMDTYPE;
import com.jme3.scene.plugins.smd.SmdKey;
import com.jme3.scene.plugins.smd.SmdLoader;

public class TestInxLoader {

    static Logger log = Logger.getLogger(TestInxLoader.class);

    public static void main(String[] args) {
        // 初始化资源管理器
        AssetManager assetManager = new DesktopAssetManager();
        assetManager.registerLoader(SmdLoader.class, "inx");
        if (new File("I:/game/PTCN-RPT1.0").exists()) {
            assetManager.registerLocator("I:/game/PTCN-RPT1.0", FileLocator.class);
        } else {
            assetManager.registerLocator("D:/Priston Tale/PTCN3550/PTCN3550", FileLocator.class);
        }

        // 读取地图的smd文件
        // MODELINFO model = (MODELINFO) assetManager.loadAsset(new
        // SmdKey("char/npc/arad/arad.inx", SMDTYPE.INX));
        MODELINFO model = (MODELINFO) assetManager
                .loadAsset(new SmdKey("char/monster/death_knight/death_knight.inx", SMDTYPE.MODELINFO));

        log.debug(model.modelFile);
        log.debug(model.animationFile);
        log.debug(model.linkFile);

        log.debug("FileTypeKeyWord:" + model.FileTypeKeyWord);
        log.debug("LinkFileKeyWord:" + model.LinkFileKeyWord);

        log.debug("MotionCount:" + model.MotionCount);

        // 有10帧数据没有用
        for (int i = 10; i < model.MotionCount; i++) {
            MOTIONINFO motion = model.MotionInfo[i];
            if (motion.State == 0) {
                log.debug(i + ":unkownn");
            } else {
                String name = getAnimationNameById(motion.State);
                log.debug(i + ":" + name + " Key1:" + motion.MotionKeyWord_1 + " Key2:" + motion.MotionKeyWord_2
                        + " Start:" + motion.StartFrame + " End:" + motion.EndFrame + " Repeat:" + motion.Repeat
                        + " KeyCode:" + motion.KeyCode + " Frames:" + motion.MotionFrame);
            }
        }

        log.debug("TalkMotionCount:" + model.TalkMotionCount);
        for (int i = 10; i < model.TalkMotionCount; i++) {
            MOTIONINFO motion = model.TalkMotionInfo[i];
            if (motion.State == 0) {
                log.debug(i + ":unkownn");
            } else {
                String name = getAnimationNameById(motion.State);
                log.debug(i + ":" + name + " Key1:" + motion.MotionKeyWord_1 + " Key2:" + motion.MotionKeyWord_2
                        + " Start:" + motion.StartFrame + " End:" + motion.EndFrame + " Repeat:" + motion.Repeat
                        + " KeyCode:" + motion.KeyCode + " Frames:" + motion.MotionFrame);
            }
        }
    }

    private static String getAnimationNameById(int id) {
        String ret = "unknown";

        switch (id) {
        case 64:// 0x40
            ret = "Idle";
            break;
        case 80:// 0x50
            ret = "Walk";
            break;
        case 96:// 0x60
            ret = "Run";
            break;
        case 128:// 0x80
            ret = "Fall";
            break;
        case 256:// 0x100
            ret = "Attack";
            break;
        case 272:// 0x110
            ret = "Damage";
            break;
        case 288:// 0x120
            ret = "Die";
            break;
        case 304:// 0x130
            ret = "Sometimes";
            break;
        case 320:// 0x140
            ret = "Potion";
            break;
        case 336:// 0x150
            ret = "Technique";
            break;
        case 368:// 0x170
            ret = "Landing (small)";
            break;
        case 384:// 0x180
            ret = "Landing (large)";
            break;
        case 512:// 0x200
            ret = "Standup";
            break;
        case 528:// 0x210
            ret = "Cry";
            break;
        case 544:// 0x220
            ret = "Hurray";
            break;
        case 576:// 0x240
            ret = "Jump";
            break;
        }

        return ret;
    }

}
