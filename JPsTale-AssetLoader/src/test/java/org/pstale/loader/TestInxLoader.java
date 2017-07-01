package org.pstale.loader;

import org.junit.Test;
import org.pstale.assets.AssetFactory;
import org.pstale.assets.AssetNameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jme3.animation.AnimControl;
import com.jme3.animation.Animation;
import com.jme3.animation.Skeleton;
import com.jme3.asset.DesktopAssetManager;
import com.jme3.scene.plugins.smd.AnimationBuilder;
import com.jme3.scene.plugins.smd.animation.PAT3D;
import com.jme3.script.plugins.character.MOTIONINFO;
import com.jme3.script.plugins.character.ModelInfo;

public class TestInxLoader {

    static Logger logger = LoggerFactory.getLogger(TestInxLoader.class);
    
    String MONSTER_DK = "char/monster/death_knight/death_knight.inx";
    String NPC_ARAD = "char/npc/arad/arad.inx";

    static {
        AssetFactory.setAssetManager(new DesktopAssetManager());
    }
    
    @Test
    public void testDeathKnight() {
        logger.debug("============");
        
        ModelInfo modelInfo = AssetFactory.loadInx(MONSTER_DK);
        
        String folder = AssetNameUtils.getFolder(MONSTER_DK);

        // 有共享数据?
        String linkFile = modelInfo.linkFile;
        if (linkFile.length() > 0) {
            ModelInfo mi = AssetFactory.loadInx(linkFile);
            modelInfo.animationFile = mi.animationFile;
        }

        PAT3D skeleton = null;
        // 读取动画
        if (modelInfo.animationFile.length() > 0) {
            // 后缀名改为smb
            String smbFile = AssetNameUtils.changeExt(modelInfo.animationFile, "smb");
            
            String name = AssetNameUtils.getName(smbFile);
            
            skeleton = AssetFactory.loadSmb(folder + name);

            // 生成动画
            Skeleton ske = AnimationBuilder.buildSkeleton(skeleton);
            Animation anim = AnimationBuilder.buildAnimation(skeleton, ske);
            AnimControl ac = new AnimControl(ske);
            ac.addAnim(anim);
        }
        printAnimation(modelInfo);
    }
    @Test
    public void testArad() {
        logger.debug("============");
        ModelInfo model = AssetFactory.loadInx(NPC_ARAD);
        
        printAnimation(model);
    }
    
    private void printAnimation(ModelInfo model) {
        logger.debug("Model: {}", model.modelFile);
        logger.debug("Animation: {}", model.animationFile);
        logger.debug("LinkFile: {}", model.linkFile);

        logger.debug("FileTypeKeyWord: {}", model.FileTypeKeyWord);
        logger.debug("LinkFileKeyWord: {}", model.LinkFileKeyWord);

        logger.debug("MotionCount: {}", model.MotionCount - 10);
        printAnimation(model.motionInfo, model.MotionCount - 10);

        logger.debug("TalkLinkFile:{}", model.szTalkLinkFile);
        logger.debug("TalkMotionFile:{}", model.szTalkMotionFile);
        logger.debug("TalkMotionCount:{}", model.TalkMotionCount - 10);
        printAnimation(model.TalkMotionInfo, model.TalkMotionCount - 10);
    }

    private void printAnimation(MOTIONINFO[] motions, int count) {
        for (int i = 0; i < count; i++) {
            MOTIONINFO motion = motions[i + 10];
            if (motion.State == 0) {
                logger.debug(i + ":unkownn");
            } else {
                String name = getAnimationNameById(motion.State);
                logger.debug(i + ":" + name + " Key1:" + motion.MotionKeyWord_1 + " Key2:" + motion.MotionKeyWord_2
                        + " Start:" + motion.StartFrame + " End:" + motion.EndFrame + " Repeat:" + motion.Repeat
                        + " KeyCode:" + motion.KeyCode + " Frames:" + motion.MotionFrame);
            }
        }
    }

    private String getAnimationNameById(int id) {
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
