package org.pstale.loader.inx;

import org.junit.Test;
import org.pstale.assets.AssetFactory;
import org.pstale.assets.utils.AnimationBuilder;
import org.pstale.assets.utils.AssetNameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jme3.asset.DesktopAssetManager;
import com.jme3.scene.plugins.smd.geom.AnimateModel;
import com.jme3.scene.plugins.smd.geom.MotionInfo;
import com.jme3.scene.plugins.smd.geom.PAT3D;

public class TestInxLoader {

    static Logger logger = LoggerFactory.getLogger(TestInxLoader.class);

    static {
        AssetFactory.setAssetManager(new DesktopAssetManager());
    }

    @Test
    public void testDeathKnight() {
        printAnimation("char/monster/death_knight/death_knight.inx");
    }

    @Test
    public void testArad() {
        printAnimation("char/npc/arad/arad.inx");
    }

    @Test
    public void testBcn01() {
        printAnimation("char/npc/bcn01/Bcn01.ini");
    }

    @Test
    public void testCmc010() {
        printAnimation("char/tmABCD/Cmc010.ini");
    }

    private void printAnimation(String path) {
        String folder = AssetNameUtils.getFolder(path);
        AnimateModel modelInfo = AssetFactory.loadInx(path);

        logger.debug("=============");

        logger.debug("ModelFile: {}", modelInfo.modelFile);

        logger.debug("FileTypeKeyWord: {}", modelInfo.FileTypeKeyWord);
        logger.debug("LinkFileKeyWord: {}", modelInfo.LinkFileKeyWord);

        // 共享动作数据
        if (modelInfo.motionLinkFile.length() > 0) {
            logger.debug("Use link file: {}", modelInfo.motionLinkFile);

            String inx = AssetNameUtils.changeExt(modelInfo.motionLinkFile, "inx");
            String name = AssetNameUtils.getName(inx);

            AnimateModel mi = AssetFactory.loadInx(folder + name);

            modelInfo.motionFile = mi.motionFile;

            modelInfo.subMotions = mi.subMotions;
            modelInfo.subMotionCount = mi.subMotionCount;
        }

        logger.debug("MotionFile: {}", modelInfo.motionFile);
        logger.debug("MotionLinkFile: {}", modelInfo.motionLinkFile);
        logger.debug("MotionCount: {}", modelInfo.subMotionCount - 10);
        printAnimation(modelInfo.subMotions, modelInfo.subMotionCount - 10);

        // 共享交谈动作数据
        if (modelInfo.talkLinkFile.length() > 0) {
            logger.debug("Use talk link file: {}", modelInfo.talkLinkFile);

            String inx = AssetNameUtils.changeExt(modelInfo.talkLinkFile, "inx");
            String name = AssetNameUtils.getName(inx);

            AnimateModel mi = AssetFactory.loadInx(folder + name);

            modelInfo.talkMotionFile = mi.talkMotionFile;
            modelInfo.talkLinkFile = mi.talkLinkFile;
            modelInfo.talkMotionCount = mi.talkMotionCount;
            modelInfo.talkMotions = mi.talkMotions;
        }
        logger.debug("TalkMotionFile:{}", modelInfo.talkMotionFile);
        logger.debug("TalkLinkFile:{}", modelInfo.talkLinkFile);
        logger.debug("TalkMotionCount:{}", modelInfo.talkMotionCount - 10);
        printAnimation(modelInfo.talkMotions, modelInfo.talkMotionCount - 10);

        PAT3D skeleton = null;
        // 读取动画
        if (modelInfo.motionFile.length() > 0) {
            // 后缀名改为smb
            String smbFile = AssetNameUtils.changeExt(modelInfo.motionFile, "smb");
            String name = AssetNameUtils.getName(smbFile);
            skeleton = AssetFactory.loadSmb(folder + name);
            
            int count = modelInfo.subMotionCount - 10;
            for (int i = 0; i < count; i++) {
                MotionInfo motion = modelInfo.subMotions[i + 10];
                AnimationBuilder.buildAnimation(skeleton, motion);
            }
        }

        // 读取网格
        String smdFile = AssetNameUtils.changeExt(modelInfo.modelFile, "smd");
        smdFile = AssetNameUtils.getName(smdFile);
        PAT3D model = AssetFactory.loadSmd(folder + smdFile);
        model.setSkeleton(skeleton);
    }

    private void printAnimation(MotionInfo[] motions, int count) {
        for (int i = 0; i < count; i++) {
            MotionInfo motion = motions[i + 10];

            if (motion.State == 0) {
                logger.debug(i + ":unkownn");
            } else {
                logger.debug("{} : {} Repeat:{} KeyCode:[{}] Frames:{}", i,
                        AnimationBuilder.getAnimationNameById(motion.State), motion.Repeat, motion.KeyCode,
                        motion.MotionFrame);
                if (motion.motionStartFrame > 0) {
                    logger.debug("MotionKeyFrames:{} {} {}", motion.motionStartFrame / 256, motion.endFrame / 256,
                            motion.MotionKeyWord_2 / 256);
                }
                if (motion.talkStartFrame > 0) {
                    logger.debug("TalkKeyFrames: {} {}", motion.talkStartFrame / 256, motion.endFrame / 256);
                }
            }
        }
    }

}
