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
import com.jme3.math.Transform;
import com.jme3.math.Vector3f;
import com.jme3.scene.plugins.smd.geom.GeomObject;
import com.jme3.scene.plugins.smd.geom.MotionInfo;
import com.jme3.scene.plugins.smd.geom.PAT3D;
import com.jme3.scene.plugins.smd.geom.TransPosition;
import com.jme3.scene.plugins.smd.geom.TransRotation;
import com.jme3.scene.plugins.smd.geom.TransScale;

/**
 * 动画生成器
 * @author yanmaoyuan
 */
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
            Quaternion bindRotation = new Quaternion(-obj.qx, -obj.qy, -obj.qz, obj.qw);
            Quaternion bindRotationI = bindRotation.inverse();// 逆旋转
            Vector3f bindScale = new Vector3f(obj.sx, obj.sy, obj.sz);
            
            Quaternion tmpQ = new Quaternion();
            
            /**
             * 统计关键帧。
             */
            TreeMap<Integer, Transform> keyframes = new TreeMap<Integer, Transform>();
            
            // 用于统计实际的帧数
            int posCnt = 0;
            int rotCnt = 0;
            int sclCnt = 0;
            
            for (int j = 0; j < obj.TmPosCnt; j++) {
                TransPosition pos = obj.posArray[j];
            
                Transform keyframe = getOrMakeKeyframe(keyframes, pos.frame);
                keyframe.getTranslation().set(pos.x, pos.y, pos.z);
                
                // 还原位移
                keyframe.getTranslation().subtractLocal(bindPosition);
                
                posCnt++;
            }

            for (int j = 0; j < obj.TmRotCnt; j++) {
                TransRotation rot = obj.rotArray[j];
                Transform k = getOrMakeKeyframe(keyframes, rot.frame);
                k.getRotation().set(-rot.x, -rot.y, -rot.z, rot.w);
                
                if (j==0) {
                    // 根据初始帧与这一帧的相似度决定是否需要除法。
                    float dx = obj.qx - rot.x;
                    float dy = obj.qy - rot.y;
                    float dz = obj.qz - rot.z;
                    float dw = obj.qw - rot.w;
                    float lengthSquared = dx * dx + dy * dy + dz * dz + dw * dw;
                    if (lengthSquared == 0f) {
                        k.getRotation().loadIdentity();
                    } else {
                        // 还原旋转，只处理第一帧动画
                        bindRotationI.mult(k.getRotation(), tmpQ);
                        k.getRotation().set(tmpQ);
                    }
                }
            }

            Quaternion ori = new Quaternion(0, 0, 0, 1);
            for (Transform keyframe : keyframes.values()) {
                // 右乘前一帧
                keyframe.getRotation().multLocal(ori);
                // 记录前一帧
                ori.set(keyframe.getRotation());
            }

            for (int j = 0; j < obj.TmScaleCnt; j++) {
                TransScale scale = obj.scaleArray[j];
                Transform k = getOrMakeKeyframe(keyframes, scale.frame);
                k.getScale().set(scale.x, scale.y, scale.z);
                
                // 还原缩放
                k.getScale().divideLocal(bindScale);
                
                sclCnt++;
            }

            logger.debug("total:{} position:{} rotation:{} scale:{}", keyframes.size(), posCnt, rotCnt, sclCnt);
            
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
                    Transform current = keyframes.get(frame);
    
                    times[n] = (float)frame / FramePerSecond;
                    translations[n] = current.getTranslation();
                    rotations[n] = current.getRotation().normalizeLocal();
                    scales[n] = current.getScale();
    
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
            Quaternion bindRotation = new Quaternion(-obj.qx, -obj.qy, -obj.qz, obj.qw);
            Quaternion bindRotationI = bindRotation.inverse();// 逆旋转
            Vector3f bindScale = new Vector3f(obj.sx, obj.sy, obj.sz);
            
            Quaternion tmpQ = new Quaternion();
            
            /**
             * 统计关键帧。
             */
            TreeMap<Integer, Transform> keyframes = new TreeMap<Integer, Transform>();
            for (int j = 0; j < obj.TmPosCnt; j++) {
                TransPosition pos = obj.posArray[j];
                Transform k = getOrMakeKeyframe(keyframes, pos.frame);
                k.getTranslation().set(pos.x, pos.y, pos.z);
                
                // 还原位移
                k.getTranslation().subtractLocal(bindPosition);
            }

            for (int j = 0; j < obj.TmRotCnt; j++) {
                TransRotation rot = obj.rotArray[j];
                Transform k = getOrMakeKeyframe(keyframes, rot.frame);
                k.getRotation().set(-rot.x, -rot.y, -rot.z, rot.w);
                
                if (j==0) {
                    // 根据初始帧与这一帧的相似度决定是否需要除法。
                    float dx = obj.qx - rot.x;
                    float dy = obj.qy - rot.y;
                    float dz = obj.qz - rot.z;
                    float dw = obj.qw - rot.w;
                    float lengthSquared = dx * dx + dy * dy + dz * dz + dw * dw;
                    if (lengthSquared == 0f) {
                        k.getRotation().loadIdentity();
                    } else {
                        // 还原旋转，只处理第一帧动画
                        bindRotationI.mult(k.getRotation(), tmpQ);
                        k.getRotation().set(tmpQ);
                    }
                }
            }

            Quaternion ori = new Quaternion(0, 0, 0, 1);
            for (Transform keyframe : keyframes.values()) {
                // 右乘前一帧
                keyframe.getRotation().multLocal(ori);
                // 记录前一帧
                ori.set(keyframe.getRotation());
            }

            for (int j = 0; j < obj.TmScaleCnt; j++) {
                TransScale scale = obj.scaleArray[j];
                Transform k = getOrMakeKeyframe(keyframes, scale.frame);
                k.setScale(scale.x, scale.y, scale.z);
                
                // 还原缩放
                k.getScale().divideLocal(bindScale);
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

            int n = 0;
            for (Integer frame : keyframes.keySet()) {
                // 获取当前帧
                Transform current = keyframes.get(frame);

                times[n] = (float) frame / FramePerSecond;
                translations[n] = current.getTranslation();
                rotations[n] = current.getRotation().normalizeLocal();
                scales[n] = current.getScale();

                n++;
            }

            BoneTrack track = new BoneTrack(pat.getObjIndex(obj.NodeName));
            track.setKeyframes(times, translations, rotations, scales);
            anim.addTrack(track);
        }

        return anim;
    }

    /**
     * 根据帧的编号来查询关键帧数据，如果某个frame还没有对应的关键帧数据，就创建一个新的。
     * 
     * @param keyframes
     * @param frame
     * @return
     */
    private static Transform getOrMakeKeyframe(SortedMap<Integer, Transform> keyframes, Integer frame) {
        Transform k = keyframes.get(frame);
        if (k == null) {
            k = new Transform();
            keyframes.put(frame, k);
        }
        return k;
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
