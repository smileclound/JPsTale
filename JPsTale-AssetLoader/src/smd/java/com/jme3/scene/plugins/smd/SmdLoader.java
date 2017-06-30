package com.jme3.scene.plugins.smd;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.pstale.constants.SceneConstants;
import org.pstale.control.WindAnimationControl;

import com.jme3.animation.AnimControl;
import com.jme3.animation.Animation;
import com.jme3.animation.Skeleton;
import com.jme3.animation.SkeletonControl;
import com.jme3.asset.AssetInfo;
import com.jme3.asset.AssetLoader;
import com.jme3.asset.AssetManager;
import com.jme3.light.AmbientLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue.Bucket;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.Node;
import com.jme3.scene.plugins.smd.animation.PAT3D;
import com.jme3.scene.plugins.smd.material._Material;
import com.jme3.scene.plugins.smd.math.Matrix4D;
import com.jme3.scene.plugins.smd.scene.OBJ3D;
import com.jme3.scene.plugins.smd.scene.Stage;
import com.jme3.scene.plugins.smd.utils.AnimUtils;
import com.jme3.scene.plugins.smd.utils.GeometryUtils;
import com.jme3.scene.plugins.smd.utils.MaterialUtils;
import com.jme3.script.plugins.character.ModelInfo;
import com.jme3.util.LittleEndien;

/**
 * 精灵场景加载器
 * 
 * @author yanmaoyuan
 * 
 */
public class SmdLoader extends SceneConstants implements AssetLoader {

    static Logger log = Logger.getLogger(SmdLoader.class);
    
    private MaterialUtils materialFactory = null;
    private AnimUtils animUtils = new AnimUtils();
    
    /**
     * 动画的颜色要比别的模型亮一点点。
     */
    AmbientLight ambientLightForAnimation;

    public SmdLoader() {
        ambientLightForAnimation = new AmbientLight();
        ambientLightForAnimation.setColor(new ColorRGBA(0.8f, 0.8f, 0.8f, 1f));
    }

    /**
     * 精灵的动画使用3DS MAX的默认速率，每秒30Tick，每Tick共160帧。 也就是每秒4800帧。
     * 
     * 但是smd文件中也另外存储了2个参数： (1) 每秒Tick数 (默认30) (2) 每Tick帧数 (默认160)
     * 这两个变量的值控制了动画播放的速率。
     */

    final static int sMATS_SCRIPT_WIND = 1;
    final static int sMATS_SCRIPT_WINDZ1 = 0x0020;
    final static int sMATS_SCRIPT_WINDZ2 = 0x0040;
    final static int sMATS_SCRIPT_WINDX1 = 0x0080;
    final static int sMATS_SCRIPT_WINDX2 = 0x0100;
    final static int sMATS_SCRIPT_WATER = 0x0200;
    final static int sMATS_SCRIPT_NOTVIEW = 0x0400;
    final static int sMATS_SCRIPT_PASS = 0x0800;
    final static int sMATS_SCRIPT_NOTPASS = 0x1000;
    final static int sMATS_SCRIPT_RENDLATTER = 0x2000;
    final static int sMATS_SCRIPT_BLINK_COLOR = 0x4000;
    final static int sMATS_SCRIPT_CHECK_ICE = 0x8000;
    final static int sMATS_SCRIPT_ORG_WATER = 0x10000;

    private AssetManager manager = null;
    private SmdKey key = null;

    @Override
    public Object load(AssetInfo assetInfo) throws IOException {
        // 确认用户使用了SmdKey
        if (!(assetInfo.getKey() instanceof SmdKey)) {
            log.warn("用户未使用SmdKey来加载模型:" + key.getName());
            throw new RuntimeException("请使用SmdKey来加载精灵的smd模型。");
        }

        this.key = (SmdKey) assetInfo.getKey();
        this.manager = assetInfo.getManager();
        this.materialFactory = new MaterialUtils(manager);
        this.materialFactory.setFolder(key.getFolder());

        /**
         * 若用户使用了SmdKey，就根据type来决定采用哪种方式来加载模型。
         */
        switch (key.type) {
        case STAGE3D: {// 直接返回STAGE3D对象
            Stage stage3D = new Stage();
            stage3D.loadFile(new LittleEndien(assetInfo.openStream()));
            return stage3D;
        }
        case STAGE3D_VISUAL: {// 返回STAGE3D的可视部分
            Stage stage3D = new Stage();
            stage3D.loadFile(new LittleEndien(assetInfo.openStream()));
            return buildNode(stage3D);
        }
        case STAGE3D_COLLISION: {// 返回STAGE3D中参加碰撞检测的网格
            Stage stage3D = new Stage();
            stage3D.loadFile(new LittleEndien(assetInfo.openStream()));
            return GeometryUtils.buildCollisionMesh(stage3D);
        }
        case PAT3D: {// 直接返回PAT3D对象
            PAT3D bone = new PAT3D();
            bone.loadFile(new LittleEndien(assetInfo.openStream()), null, null);
            return bone;
        }
        case PAT3D_BIP: {// 有动画的舞台物体
            // 后缀名改为smb
            String smbFile = changeExt(key.getName(), "smb");
            PAT3D bone = (PAT3D) manager.loadAsset(new SmdKey(smbFile, SMDTYPE.PAT3D));

            // 再加载smd文件
            key = (SmdKey) assetInfo.getKey();
            LittleEndien in = new LittleEndien(assetInfo.openStream());
            PAT3D pat = new PAT3D();
            pat.loadFile(in, null, bone);
            return buildNode(pat);
        }
        case PAT3D_VISUAL: {// 舞台物体，无动画
            LittleEndien in = new LittleEndien(assetInfo.openStream());
            PAT3D pat = new PAT3D();
            pat.loadFile(in, null, key.getBone());
            return buildNode(pat);
        }
        case MODELINFO: {// inx 文件
            LittleEndien in = new LittleEndien(assetInfo.openStream());
            ModelInfo modelInfo = new ModelInfo();
            modelInfo.loadData(in);
            return modelInfo;
        }
        case MODELINFO_ANIMATION: {
            LittleEndien in = new LittleEndien(assetInfo.openStream());
            ModelInfo modelInfo = new ModelInfo();
            modelInfo.loadData(in);

            // 有共享数据?
            String linkFile = modelInfo.linkFile;
            if (linkFile.length() > 0) {
                SmdKey linkFileKey = new SmdKey(linkFile, SMDTYPE.MODELINFO);
                ModelInfo mi = (ModelInfo) manager.loadAsset(linkFileKey);
                modelInfo.animationFile = mi.animationFile;
            }

            PAT3D BipPattern = null;
            // 读取动画
            if (modelInfo.animationFile.length() > 0) {
                // 后缀名改为smb
                String smbFile = changeExt(modelInfo.animationFile, "smb");
                smbFile = changeName(smbFile);
                BipPattern = (PAT3D) manager.loadAsset(new SmdKey(key.getFolder() + smbFile, SMDTYPE.PAT3D));

                // 生成动画
                Skeleton ske = animUtils.buildSkeleton(BipPattern);
                Animation anim = animUtils.buildAnimation(BipPattern, ske);
                AnimControl ac = new AnimControl(ske);
                ac.addAnim(anim);
                return ac;
            } else {
                return null;
            }
        }
        case MODELINFO_MODEL: {
            LittleEndien in = new LittleEndien(assetInfo.openStream());
            ModelInfo modelInfo = new ModelInfo();
            modelInfo.loadData(in);

            // 有共享数据?
            String linkFile = modelInfo.linkFile;
            if (linkFile.length() > 0) {
                SmdKey linkFileKey = new SmdKey(linkFile, SMDTYPE.MODELINFO);
                ModelInfo mi = (ModelInfo) manager.loadAsset(linkFileKey);
                modelInfo.animationFile = mi.animationFile;
            }

            PAT3D BipPattern = null;
            // 读取动画
            if (modelInfo.animationFile.length() > 0) {
                // 后缀名改为smb
                String smbFile = changeExt(modelInfo.animationFile, "smb");
                smbFile = changeName(smbFile);
                BipPattern = (PAT3D) manager.loadAsset(new SmdKey(key.getFolder() + smbFile, SMDTYPE.PAT3D));
            }

            // 读取网格
            String smdFile = changeExt(modelInfo.modelFile, "smd");
            smdFile = changeName(smdFile);

            SmdKey smdKey = new SmdKey(key.getFolder() + smdFile, SMDTYPE.PAT3D_VISUAL);
            smdKey.setBone(BipPattern);
            return manager.loadAsset(smdKey);
        }
        default:
            return null;
        }
    }

    /**
     * 改变文件的后缀名
     * 
     * @param line
     * @return
     */
    private String changeName(String line) {
        line = line.replaceAll("\\\\", "/");
        int index = line.lastIndexOf("/");
        if (index != -1) {
            line = line.substring(index + 1);
        }
        return line;
    }

    /**
     * 改变文件名后缀。
     * 
     * @param orgin
     * @param ext
     * @return
     */
    public static String changeExt(final String orgin, final String ext) {
        String path = orgin;
        path = path.replaceAll("\\\\", "/");

        int idx = path.lastIndexOf(".") + 1;
        String dest = path.substring(0, idx) + ext;

        return dest;
    }

    /****************************************
     * OBJ3D
     ****************************************/

    /**
     * 将顺序读取的3个int，用TmInvert进行转置，获得一个GL坐标系的顶点。
     * 
     * <pre>
     * 若有向量x=(v1, v2, v3, 1)与矩阵TmInvert (_11, _12, _13, _14)
     *                                      (_21, _22, _23, _24)
     *                                      (_31, _32, _33, _34)
     *                                      (_41, _42, _43, _44)。
     * 使用TmInvert对向量x进行线性变换后，得到的向量为a(res1, res2, res3, 1)。
     *       即：TmInvert * x = a(res1, res2, res3, 1)
     * 其中TmInvert与a已知，求x。
     *       x = (1/TmInvert) * a
     * </pre>
     * 
     * @param res1
     * @param res2
     * @param res3
     * @param tm
     */
    private Vector3f mult(long res1, long res2, long res3, Matrix4D tm) {
        long v1 = -((res2 * tm._33 * tm._21 - res2 * tm._23 * tm._31 - res1 * tm._33 * tm._22 + res1 * tm._23 * tm._32
                - res3 * tm._21 * tm._32 + res3 * tm._31 * tm._22 + tm._43 * tm._21 * tm._32 - tm._43 * tm._31 * tm._22
                - tm._33 * tm._21 * tm._42 + tm._33 * tm._41 * tm._22 + tm._23 * tm._31 * tm._42
                - tm._23 * tm._41 * tm._32) << 8)
                / (tm._11 * tm._33 * tm._22 + tm._23 * tm._31 * tm._12 + tm._21 * tm._32 * tm._13
                        - tm._33 * tm._21 * tm._12 - tm._11 * tm._23 * tm._32 - tm._31 * tm._22 * tm._13);
        long v2 = ((res2 * tm._11 * tm._33 - res1 * tm._33 * tm._12 - res3 * tm._11 * tm._32 + res3 * tm._31 * tm._12
                - res2 * tm._31 * tm._13 + res1 * tm._32 * tm._13 + tm._11 * tm._43 * tm._32 - tm._43 * tm._31 * tm._12
                - tm._11 * tm._33 * tm._42 + tm._33 * tm._41 * tm._12 + tm._31 * tm._42 * tm._13
                - tm._41 * tm._32 * tm._13) << 8)
                / (tm._11 * tm._33 * tm._22 + tm._23 * tm._31 * tm._12 + tm._21 * tm._32 * tm._13
                        - tm._33 * tm._21 * tm._12 - tm._11 * tm._23 * tm._32 - tm._31 * tm._22 * tm._13);
        long v3 = -((res2 * tm._11 * tm._23 - res1 * tm._23 * tm._12 - res3 * tm._11 * tm._22 + res3 * tm._21 * tm._12
                - res2 * tm._21 * tm._13 + res1 * tm._22 * tm._13 + tm._11 * tm._43 * tm._22 - tm._43 * tm._21 * tm._12
                - tm._11 * tm._23 * tm._42 + tm._23 * tm._41 * tm._12 + tm._21 * tm._42 * tm._13
                - tm._41 * tm._22 * tm._13) << 8)
                / (tm._11 * tm._33 * tm._22 + tm._23 * tm._31 * tm._12 + tm._21 * tm._32 * tm._13
                        - tm._33 * tm._21 * tm._12 - tm._11 * tm._23 * tm._32 - tm._31 * tm._22 * tm._13);

        float x = (float) v1 / 256.0f;
        float y = (float) v2 / 256.0f;
        float z = (float) v3 / 256.0f;

        // FIXME 
        //return new Vector3f(-y, z, -x);
        return new Vector3f(x, y, z);
    }

    private void invertPoint(OBJ3D obj) {

        for (int i = 0; i < obj.nVertex; i++) {
            if (obj.Physique != null) {
                obj.Vertex[i].v = mult(obj.Vertex[i].x, obj.Vertex[i].y, obj.Vertex[i].z, obj.Physique[i].TmInvert);
            } else {
                obj.Vertex[i].v = mult(obj.Vertex[i].x, obj.Vertex[i].y, obj.Vertex[i].z, obj.TmInvert);
            }
        }
    }

    private Node buildNode(PAT3D pat) {
        Node rootNode = new Node("PAT3D:" + key.getName());

        Skeleton ske = null;
        // 生成骨骼
        if (pat.TmParent != null) {
            ske = animUtils.buildSkeleton(pat.TmParent);
        }

        for (int i = 0; i < pat.nObj3d; i++) {
            OBJ3D obj = pat.obj3d[i];
            if (obj.nFace > 0) {

                // 对所有顶点进行线性变换，否则顶点的坐标都在原点附近。
                invertPoint(obj);

                // 根据模型的材质不同，将创建多个网格，分别渲染。
                for (int mat_id = 0; mat_id < pat.materialGroup.materialCount; mat_id++) {
                    // 生成网格
                    Mesh mesh = GeometryUtils.buildMesh(obj, mat_id, ske);

                    // 创建材质
                    _Material m = pat.materialGroup.materials[mat_id];
                    Material mat;
                    if (USE_LIGHT) {
                        mat = materialFactory.createLightMaterial(m);
                    } else {
                        mat = materialFactory.createMiscMaterial(m);
                    }

                    // 创建几何体并应用材质。
                    Geometry geom = new Geometry(pat.obj3d[i].NodeName + "#" + mat_id, mesh);
                    geom.setMaterial(mat);

                    // 设置位置
                    // FIXME 这个位置设置后并不准确，需要进一步研究。
                    Vector3f translation = new Vector3f(-obj.py, obj.pz, -obj.px);
                    Quaternion rotation = new Quaternion(-obj.qy, obj.qz, -obj.qx, -obj.qw);
                    Vector3f scale = new Vector3f(obj.sy, obj.sz, obj.sx);
                    geom.setLocalTranslation(translation);
                    geom.setLocalRotation(rotation);
                    geom.setLocalScale(scale);

                    rootNode.attachChild(geom);
                }
            }
        }

        // 绑定动画控制器
        if (ske != null) {
            Animation anim = animUtils.buildAnimation(pat.TmParent, ske);
            AnimControl ac = new AnimControl(ske);
            ac.addAnim(anim);
            rootNode.addControl(ac);
            rootNode.addControl(new SkeletonControl(ske));
        }

        return rootNode;
    }


    /**
     * 生成STAGE3D对象
     * 
     * @return
     */
    private Node buildNode(Stage stage) {
        Node rootNode = new Node("STAGE3D:" + key.getName());

        Vector3f[] orginNormal = null;
        if (USE_LIGHT) {
            // 为了让表面平滑光照，先基于原来的面和顶点计算一次法向量。
            orginNormal = GeometryUtils.computeOrginNormals(stage);
        }

        int materialCount = stage.materialGroup.materialCount;

        // 创建材质
        for (int mat_id = 0; mat_id < materialCount; mat_id++) {
            _Material m = stage.materials[mat_id];

            // 该材质没有使用，不需要显示。
            if (m.InUse == 0) {
                continue;
            }
            // 没有纹理，不需要显示。
            if (m.TextureCounter == 0 && m.AnimTexCounter == 0) {
                continue;
            }
            // 不可见的材质，不需要显示。
            if ((m.UseState & sMATS_SCRIPT_NOTVIEW) != 0) {
                continue;
            }

            /**
             * 统计材质为mat_id的面一共有多少个面，用于计算需要生成多少个子网格。
             */
            int size = 0;
            for (int i = 0; i < stage.nFace; i++) {
                if (stage.Face[i].v[3] != mat_id)
                    continue;
                size++;
            }
            // 没有面使用这个材质，跳过。
            if (size < 1) {
                continue;
            }

            // 计算网格
            Mesh mesh = GeometryUtils.buildMesh(stage, size, mat_id, orginNormal);
            Geometry geom = new Geometry(key.getName() + "#" + mat_id, mesh);

            // 创建材质
            Material mat;

            // 有多个动画
            if (m.TextureType == 0) {
                // SMTEX_TYPE_MULTIMIX
                int n = m.TextureFormState[0];
                if (n >= 4) {// 4 SCROLL 滚轴 5 REFLEX 反光 6 SCROLL2 2倍速滚轴
                    mat = materialFactory.createScrollMaterial(m);
                } else {
                    if (USE_LIGHT) {
                        mat = materialFactory.createLightMaterial(m);
                    } else {
                        mat = materialFactory.createMiscMaterial(m);
                    }
                }
            } else {// SMTEX_TYPE_ANIMATION
                if (m.AnimTexCounter > 0) {
                    // AminTexCounter大于0说明有轮播动画，创建一个Control，定时更新画面。
                    mat = materialFactory.createShiftMaterial(m);
                } else {
                    mat = materialFactory.createMiscMaterial(m);
                }
            }

            // 应用动画
            if (m.WindMeshBottom != 0 && (m.UseState & sMATS_SCRIPT_BLINK_COLOR) == 0) {
                switch (m.WindMeshBottom & 0x07FF) {
                case sMATS_SCRIPT_WINDX1:
                case sMATS_SCRIPT_WINDX2:
                case sMATS_SCRIPT_WINDZ1:
                case sMATS_SCRIPT_WINDZ2: {
                    geom.addControl(new WindAnimationControl(m.WindMeshBottom & 0x07FF));
                    break;
                }
                case sMATS_SCRIPT_WATER: {
                    mat = materialFactory.createRoundMaterial(m);
                    break;
                }
                }
            }

            materialFactory.setRenderState(m, mat);

            // 应用材质
            geom.setMaterial(mat);

            geom.setUserData("MeshState", m.MeshState);
            geom.setUserData("UseState", m.UseState);
            geom.setUserData("BlendType", m.BlendType);
            geom.setUserData("MapOpacity", m.MapOpacity);
            geom.setUserData("Transparency", m.Transparency);

            rootNode.attachChild(geom);

            // 透明度
            // 只有不透明物体才需要检测碰撞网格。
            if (m.MapOpacity != 0 || m.Transparency != 0 || m.BlendType == 1 || m.BlendType == 4) {
                geom.setQueueBucket(Bucket.Translucent);
            }

            if (m.UseState != 0) {// ScriptState
                if ((m.UseState & sMATS_SCRIPT_RENDLATTER) != 0) {
                    // MeshState |= sMATS_SCRIPT_RENDLATTER;
                }
                if ((m.UseState & sMATS_SCRIPT_CHECK_ICE) != 0) {
                    // MeshState |= sMATS_SCRIPT_CHECK_ICE;
                }
                if ((m.UseState & sMATS_SCRIPT_ORG_WATER) != 0) {
                    // MeshState = sMATS_SCRIPT_ORG_WATER;
                }
                if ((m.UseState & sMATS_SCRIPT_BLINK_COLOR) != 0) {
                    // m.WindMeshBottom == dwBlinkCode[]{ 9, 10, 11, 12, 13, 14,
                    // 15, 16,} 8个数值的其中之一
                }
            }

        }

        if (stage.nLight > 0) {
            // TODO 处理灯光
        }

        return rootNode;
    }
}
