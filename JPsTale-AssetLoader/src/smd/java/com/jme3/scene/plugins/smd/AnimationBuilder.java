package com.jme3.scene.plugins.smd;

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
import com.jme3.scene.plugins.smd.animation.PAT3D;
import com.jme3.scene.plugins.smd.animation.TransPosition;
import com.jme3.scene.plugins.smd.animation.TransRotation;
import com.jme3.scene.plugins.smd.animation.TransScale;
import com.jme3.scene.plugins.smd.scene.GeomObject;

public class AnimationBuilder {
    
    static Logger logger = LoggerFactory.getLogger(AnimationBuilder.class);
    
    /**
     * 精灵的动画使用3DS MAX的默认速率，每秒30Tick，每Tick共160帧。 也就是每秒4800帧。
     * 
     * 但是smd文件中也另外存储了2个参数： (1) 每秒Tick数 (默认30) (2) 每Tick帧数 (默认160)
     * 这两个变量的值控制了动画播放的速率。
     */
    private final static float framePerSecond = 4800f;
    

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
    public static Animation buildAnimation(PAT3D pat, Skeleton ske) {

        // 统计帧数
        int maxFrame = 0;
        for (int i = 0; i < pat.objCount; i++) {
            GeomObject obj = pat.objArray[i];
            if (obj.TmRotCnt > 0) {
                if (obj.rotArray[obj.TmRotCnt - 1].frame > maxFrame) {
                    maxFrame = obj.rotArray[obj.TmRotCnt - 1].frame;
                }
            }
            if (obj.TmPosCnt > 0) {
                if (obj.posArray[obj.TmPosCnt - 1].frame > maxFrame) {
                    maxFrame = obj.posArray[obj.TmPosCnt - 1].frame;
                }
            }
            if (obj.TmScaleCnt > 0) {
                if (obj.scaleArray[obj.TmScaleCnt - 1].frame > maxFrame) {
                    maxFrame = obj.scaleArray[obj.TmScaleCnt - 1].frame;
                }
            }

            if (logger.isDebugEnabled()) {
                //logger.debug(obj.NodeName + " 最大帧=" + maxFrame);
            }
        }

        // 计算动画时常
        float length = (maxFrame) / framePerSecond;

        if (logger.isDebugEnabled()) {
            logger.debug("动画总时长=" + length);
        }

        Animation anim = new Animation("Anim", length);

        /**
         * 统计每个骨骼的关键帧
         */
        for (int i = 0; i < pat.objCount; i++) {
            GeomObject obj = pat.objArray[i];

            if (logger.isDebugEnabled()) {
                //logger.debug("TmPos:" + obj.TmPosCnt + " TmRot:" + obj.TmRotCnt + " TmScl:" + obj.TmScaleCnt);
            }

            /**
             * 统计关键帧。
             */
            TreeMap<Integer, Keyframe> keyframes = new TreeMap<Integer, Keyframe>();
            for (int j = 0; j < obj.TmPosCnt; j++) {
                TransPosition pos = obj.posArray[j];
                Keyframe k = getOrMakeKeyframe(keyframes, pos.frame);
                //k.translation = new Vector3f(pos.x, pos.z, -pos.y);
                k.translation = new Vector3f(pos.x, pos.y, pos.z);
            }

            for (int j = 0; j < obj.TmRotCnt; j++) {
                TransRotation rot = obj.rotArray[j];
                Keyframe k = getOrMakeKeyframe(keyframes, rot.frame);

                //Quaternion rotation = new Quaternion(rot.x, rot.z, -rot.y, -rot.w);
                Quaternion rotation = new Quaternion(rot.x, rot.y, rot.z, -rot.w);
                
                k.rotation = rotation;
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

                //k.scale = new Vector3f(scale.x, scale.z, scale.y);
                k.scale = new Vector3f(scale.x, scale.y, scale.z);
            }

            if (logger.isDebugEnabled()) {
                logger.debug("Track[{}], keyframes={}",obj.NodeName ,keyframes.size());
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

                times[n] = frame / framePerSecond;
                translations[n] = current.translation;
                rotations[n] = current.rotation.normalizeLocal();
                scales[n] = current.scale;

                if (logger.isDebugEnabled()) {
                    //logger.debug("  Frame={} time={} pos={} rot={} scale={}", translations[n], rotations[n], scales[n]);
                }

                // 记录当前帧
                last = current;

                n++;
            }

            BoneTrack track = new BoneTrack(ske.getBoneIndex(obj.NodeName));
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
        public Vector3f translation;
        public Quaternion rotation;
        public Vector3f scale;
    }
}
