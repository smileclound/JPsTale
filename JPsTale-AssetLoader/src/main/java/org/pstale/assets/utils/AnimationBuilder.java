package org.pstale.assets.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jme3.animation.Animation;
import com.jme3.animation.Bone;
import com.jme3.animation.BoneTrack;
import com.jme3.animation.Skeleton;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.plugins.smd.geom.GeomObject;
import com.jme3.scene.plugins.smd.geom.MotionInfo;
import com.jme3.scene.plugins.smd.geom.PAT3D;
import com.jme3.scene.plugins.smd.geom.TransPosition;
import com.jme3.scene.plugins.smd.geom.TransRotation;
import com.jme3.scene.plugins.smd.geom.TransScale;

public class AnimationBuilder {
    
    static Logger logger = LoggerFactory.getLogger(AnimationBuilder.class);
    
    /**
     * 精灵的动画使用3DS MAX的默认速率，每秒30Tick，每Tick共160帧。 也就是每秒4800帧。
     * 
     * 但是smd文件中也另外存储了2个参数： (1) 每秒Tick数 (默认30) (2) 每Tick帧数 (默认160)
     * 这两个变量的值控制了动画播放的速率。
     */
    public final static int FramePerSecond = 4800;
    
    public final static int TickPerSecond = 30;
    public final static int FramePerTick = 160;

    /**
     * 生成骨骼
     * @param skeleton
     */
    public static Skeleton buildSkeleton(PAT3D skeleton) {

        HashMap<String, Bone> boneMap = new HashMap<String, Bone>();
        Bone[] bones = new Bone[skeleton.objCount];
        
        for (int i = 0; i < skeleton.objCount; i++) {
            GeomObject obj = skeleton.objArray[i];

            // 创建一个骨头
            Bone bone = new Bone(obj.NodeName);
            bones[i] = bone;

            // 设置初始POSE

            //Vector3f translation = new Vector3f(obj.px,obj.pz, -obj.py);
            //Quaternion rotation = new Quaternion(obj.qx, obj.qz, -obj.qy, -obj.qw);
            //Vector3f scale = new Vector3f(obj.sx, obj.sz, obj.sy);
            
            Vector3f translation = new Vector3f(obj.px,obj.py, obj.pz);
            Quaternion rotation = new Quaternion(obj.qx, obj.qy, obj.qz, -obj.qw);
            Vector3f scale = new Vector3f(obj.sx, obj.sy, obj.sz);
            
            bone.setBindTransforms(translation, rotation, scale);

            // 建立父子关系
            boneMap.put(obj.NodeName, bone);
            if (obj.NodeParent != null) {
                Bone parent = boneMap.get(obj.NodeParent);
                if (parent != null)
                    parent.addChild(bone);
            }

        }

        // 生成骨架
        Skeleton ske = new Skeleton(bones);
        return ske;
    }

    /**
     * 生成骨骼动画 FIXME
     * 
     * @param ske
     */
    public static Animation buildAnimation(PAT3D pat, MotionInfo motionInfo) {

        // 起始帧
        int startTick = 0;
        // 结束帧数
        int endTick = 0;
        
        // TODO 也许应该把普通的动画和交谈的动画分别处理。不过暂时就这样吧。
        if (motionInfo.motionStartFrame > 0) {
            startTick = motionInfo.motionStartFrame/256;
        } else if (motionInfo.talkStartFrame > 0){
            startTick = motionInfo.talkStartFrame/256;
        } else {
            logger.warn("No animation included!");
            return null;
        }
        endTick = motionInfo.endFrame/256;
        
        // 这种情况是需要倒放的动画。
        boolean backForward = false;
        if (endTick < startTick) {
            int tmp = endTick;
            endTick = startTick;
            startTick = tmp;
            backForward = true;
            
            logger.debug("需要倒放的动画!");
        }
        
        // 计算开始和结束的帧，用于截取动画数据
        int startFrame = startTick * FramePerTick;
        int endFrame = endTick * FramePerTick;
        
        // 计算动画时常
        float length = (float)(endTick - startTick) / TickPerSecond;
        
        String name = getAnimationNameById(motionInfo.State);
        if (logger.isDebugEnabled()) {
            logger.debug("{} startKey:{} endKey:{} length:{}", name, startTick, endTick, length);
        }

        Animation anim = new Animation(name, length);

        /**
         * 统计每个骨骼的关键帧
         */
        for (int i = 0; i < pat.objCount; i++) {
            GeomObject obj = pat.objArray[i];

            /**
             * 根据骨骼姿态，修正动画数据。
             */
            // 骨骼的初始姿态。
            Vector3f bindPosition = new Vector3f(obj.px,obj.py, obj.pz);
            Quaternion bindRotation = new Quaternion(obj.qx, obj.qy, obj.qz, -obj.qw);
            Quaternion bindRotationI = bindRotation.inverse();// 逆旋转
            Vector3f bindScale = new Vector3f(obj.sx, obj.sy, obj.sz);
            
            Quaternion tmpQ = new Quaternion();
            
            /**
             * 统计关键帧。
             */
            TreeMap<Integer, Keyframe> keyframes = new TreeMap<Integer, Keyframe>();
            
            // 用于统计实际的帧数
            int posCnt = 0;
            int rotCnt = 0;
            int sclCnt = 0;
            
            for (int j = 0; j < obj.TmPosCnt; j++) {
                TransPosition pos = obj.posArray[j];
            
                Keyframe k = getOrMakeKeyframe(keyframes, pos.frame);
                k.translation.set(pos.x, pos.y, pos.z);
                
                // 还原位移
                k.translation.subtractLocal(bindPosition);
                
                posCnt++;
            }

            for (int j = 0; j < obj.TmRotCnt; j++) {
                TransRotation rot = obj.rotArray[j];
                Keyframe k = getOrMakeKeyframe(keyframes, rot.frame);
                k.rotation.set(rot.x, rot.y, rot.z, -rot.w);
                
                if (j==0) {
                    // 还原旋转，只处理第一帧动画
                    bindRotationI.mult(k.rotation, tmpQ);
                    k.rotation.set(tmpQ);
                }
                rotCnt++;
            }

            Quaternion ori = new Quaternion(0, 0, 0, 1);
            for (Keyframe k : keyframes.values()) {
                if (k.rotation != null) {
                    // ori.multLocal(k.rotation);
                    ori = k.rotation.mult(ori);
                    k.rotation.set(ori);
                }
            }

            for (int j = 0; j < obj.TmScaleCnt; j++) {
                TransScale scale = obj.scaleArray[j];
                Keyframe k = getOrMakeKeyframe(keyframes, scale.frame);
                k.scale.set(scale.x, scale.y, scale.z);
                
                // 还原缩放
                k.scale.divideLocal(bindScale);
                
                sclCnt++;
            }

            logger.debug("total:{} position:{} rotation:{} scacle:{}", keyframes.size(), posCnt, rotCnt, sclCnt);
            
            /**
             * 计算动画数据。 为BoneTrack准备数据。
             */
            int size = keyframes.size();
            if (size == 0) {
                continue;// 继续检查下一个骨骼
            }

            float[] times = new float[size];
            Vector3f[] translations = new Vector3f[size];
            Quaternion[] rotations = new Quaternion[size];
            Vector3f[] scales = new Vector3f[size];

            /**
             * 这个变量用来记录已经解析到了第几个Keyframe。 当n=0时，初始化last变量的值。
             * 在循环的末尾，总是将last的引用指向当前Keyframe对象。
             */
            int n = 0;
            for (Integer frame : keyframes.keySet()) {
                
                if (frame >= startFrame && frame <= endFrame) {
                    // 获取当前帧
                    Keyframe current = keyframes.get(frame);
    
                    times[n] = (float)frame / FramePerSecond;
                    translations[n] = current.translation;
                    rotations[n] = current.rotation.normalizeLocal();
                    scales[n] = current.scale;
    
                    n++;
                }
            }
            
            if (n == 0) {
                continue;
            }
            
            times = Arrays.copyOfRange(times, 0, n);
            translations = Arrays.copyOfRange(translations, 0, n);
            rotations = Arrays.copyOfRange(rotations, 0, n);
            scales = Arrays.copyOfRange(scales, 0, n);

            BoneTrack track = new BoneTrack(pat.getObjIndex(obj.NodeName));
            track.setKeyframes(times, translations, rotations, scales);
            anim.addTrack(track);
        }

        return anim;
    }
    
    /**
     * 生成骨骼动画 FIXME
     * 
     * @param ske
     */
    public static Animation buildAnimation(PAT3D pat) {

        // 统计帧数
        int maxFrame = 0;
        for (int i = 0; i < pat.objCount; i++) {
            GeomObject obj = pat.objArray[i];
            if (obj.maxFrame > maxFrame) {
                maxFrame = obj.maxFrame;
            }
        }

        // 计算动画时常
        float length = (float) maxFrame / FramePerSecond;

        if (logger.isDebugEnabled()) {
            logger.debug("MaxFrame:{}, Tick:{}, Time:{}", maxFrame, maxFrame/FramePerTick, length);
        }

        Animation anim = new Animation("Anim", length);

        /**
         * 统计每个骨骼的关键帧
         */
        for (int i = 0; i < pat.objCount; i++) {
            GeomObject obj = pat.objArray[i];

            /**
             * 根据骨骼姿态，修正动画数据。
             */
            // 骨骼的初始姿态。
            Vector3f bindPosition = new Vector3f(obj.px,obj.py, obj.pz);
            Quaternion bindRotation = new Quaternion(obj.qx, obj.qy, obj.qz, -obj.qw);
            Quaternion bindRotationI = bindRotation.inverse();// 逆旋转
            Vector3f bindScale = new Vector3f(obj.sx, obj.sy, obj.sz);
            
            Quaternion tmpQ = new Quaternion();
            
            /**
             * 统计关键帧。
             */
            TreeMap<Integer, Keyframe> keyframes = new TreeMap<Integer, Keyframe>();
            for (int j = 0; j < obj.TmPosCnt; j++) {
                TransPosition pos = obj.posArray[j];
                Keyframe k = getOrMakeKeyframe(keyframes, pos.frame);
                k.translation.set(pos.x, pos.y, pos.z);
                
                // 还原位移
                k.translation.subtractLocal(bindPosition);
            }

            for (int j = 0; j < obj.TmRotCnt; j++) {
                TransRotation rot = obj.rotArray[j];
                Keyframe k = getOrMakeKeyframe(keyframes, rot.frame);
                k.rotation.set(rot.x, rot.y, rot.z, -rot.w);
                
                if (j==0) {
                    // 还原旋转，只处理第一帧动画
                    bindRotationI.mult(k.rotation, tmpQ);
                    k.rotation.set(tmpQ);
                }
            }

            Quaternion ori = new Quaternion(0, 0, 0, 1);
            for (Keyframe k : keyframes.values()) {
                if (k.rotation != null) {
                    // ori.multLocal(k.rotation);
                    ori = k.rotation.mult(ori);
                    k.rotation.set(ori);
                }
            }

            for (int j = 0; j < obj.TmScaleCnt; j++) {
                TransScale scale = obj.scaleArray[j];
                Keyframe k = getOrMakeKeyframe(keyframes, scale.frame);
                k.scale.set(scale.x, scale.y, scale.z);
                
                // 还原缩放
                k.scale.divideLocal(bindScale);
            }

            /**
             * 计算动画数据。 为BoneTrack准备数据。
             */
            int size = keyframes.size();
            if (size == 0) {
                continue;// 继续检查下一个骨骼
            }

            float[] times = new float[size];
            Vector3f[] translations = new Vector3f[size];
            Quaternion[] rotations = new Quaternion[size];
            Vector3f[] scales = new Vector3f[size];

            /**
             * 由于精灵中的pose动画、rotate动画、scale动画的数量不一定相同， 因此keyframe中有些属性的值可能是null。
             * 如果某一帧缺少其他的数据，那么复用上一帧的数据。
             */
            Keyframe last = null;
            
            /**
             * 这个变量用来记录已经解析到了第几个Keyframe。 当n=0时，初始化last变量的值。
             * 在循环的末尾，总是将last的引用指向当前Keyframe对象。
             */
            int n = 0;
            for (Integer frame : keyframes.keySet()) {
                // 获取当前帧
                Keyframe current = keyframes.get(frame);

                // 检查pose动画
                if (current.translation == null) {
                    if (n == 0) {
                        current.translation = new Vector3f(0, 0, 0);
                    } else {// 复用上一帧的数据
                        current.translation = new Vector3f(last.translation);
                    }
                }

                // 检查rotate动画
                if (current.rotation == null) {
                    if (n == 0) {
                        current.rotation = new Quaternion(0, 0, 0, 1);
                    } else {
                        current.rotation = new Quaternion(last.rotation);
                    }
                }

                // 检查scale动画
                if (current.scale == null) {
                    if (n == 0) {
                        current.scale = new Vector3f(1, 1, 1);
                    } else {
                        current.scale = new Vector3f(last.scale);
                    }
                }

                times[n] = (float) frame / FramePerSecond;
                translations[n] = current.translation;
                rotations[n] = current.rotation.normalizeLocal();
                scales[n] = current.scale;

                // 记录当前帧
                last = current;

                n++;
            }

            BoneTrack track = new BoneTrack(pat.getObjIndex(obj.NodeName));
            track.setKeyframes(times, translations, rotations, scales);
            anim.addTrack(track);
        }

        return anim;
    }

    /**
     * 根据帧的编号来查询Keyframe数据，如果某个frame还没有对应的Keyframe数据，就创建一个新的。
     * 
     * @param keyframes
     * @param frame
     * @return
     */
    private static Keyframe getOrMakeKeyframe(SortedMap<Integer, Keyframe> keyframes, Integer frame) {
        Keyframe k = keyframes.get(frame);
        if (k == null) {
            k = new Keyframe();
            keyframes.put(frame, k);
        }
        return k;
    }
    
    /**
     * 用于统计动画的关键帧。
     * @author yanmaoyuan
     *
     */
    private static class Keyframe {
        public Vector3f translation = new Vector3f(0, 0, 0);
        public Quaternion rotation = new Quaternion(0, 0, 0, 1);
        public Vector3f scale = new Vector3f(1, 1, 1);
    }
    

    /**
     * 获取动画名称
     * @param id
     * @return
     */
    public static String getAnimationNameById(int id) {
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
        // TODO 0x400开始的动画为 TalkMotion，这里是否应该有不同的表情之分？
        case 1024: //0x400
            ret = "Talk (I)";
            break;
        case 1040: //0x410
            ret = "Talk (I)";
            break;
        case 1056:// 0x420
            ret = "Talk (II)";
            break;
        case 1072:// 0x430
            ret = "Talk (III)";
            break;
        case 1088:// 0x440
            ret = "Talk (IV)";
            break;
        case 1104:// 0x450
            ret = "Talk (V)";
            break;
        default:
            logger.debug("Unknow animation id: {}", id);
        }

        return ret;
    }
}
