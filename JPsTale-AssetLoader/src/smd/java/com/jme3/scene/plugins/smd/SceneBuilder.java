package com.jme3.scene.plugins.smd;

import org.pstale.assets.AssetNameUtils;
import org.pstale.constants.SceneConstants;
import org.pstale.control.WindAnimationControl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jme3.asset.AssetManager;
import com.jme3.asset.TextureKey;
import com.jme3.material.Material;
import com.jme3.material.RenderState;
import com.jme3.material.RenderState.BlendMode;
import com.jme3.material.RenderState.FaceCullMode;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue.Bucket;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.Node;
import com.jme3.scene.VertexBuffer.Type;
import com.jme3.scene.plugins.smd.material.TEXLINK;
import com.jme3.scene.plugins.smd.material._Material;
import com.jme3.scene.plugins.smd.scene.Stage;
import com.jme3.scene.plugins.smd.scene.StageFace;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture.WrapMode;
import com.jme3.util.BufferUtils;
import com.jme3.util.TempVars;

/**
 * 根据解析出来的数据，生成jME3的材质。
 * 
 * @author yanmaoyuan
 *
 */
public class SceneBuilder {

    static Logger logger = LoggerFactory.getLogger(SceneBuilder.class);
    
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
    
    private static AssetManager assetManager;
    private static String folder = "/";
    
    public static void setAssetManager(AssetManager manager) {
        assetManager = manager;
    }
    
    /**
     * 设置资源目录
     * @param folder
     */
    public static void setFolder(String f) {
        folder = f;
    }
    
    /**
     * 创建纹理
     * 
     * @param name
     */
    public static Texture createTexture(String name) {
        name = AssetNameUtils.getName(name);

        Texture texture = null;
        try {
            TextureKey texKey = new TextureKey(folder + name);
            texKey.setGenerateMips(true);
            texture = assetManager.loadTexture(texKey);
            texture.setWrap(WrapMode.Repeat);
            texture.setAnisotropicFilter(4);
        } catch (Exception ex) {
            texture = assetManager.loadTexture("Common/Textures/MissingTexture.png");
            texture.setWrap(WrapMode.EdgeClamp);
        }
        return texture;
    }

    /**
     * 创建感光材质
     * 
     * @param m
     * @return
     */
    public static Material createLightMaterial(_Material m) {
        Material mat = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
        mat.setColor("Diffuse", new ColorRGBA(m.Diffuse.r, m.Diffuse.g, m.Diffuse.b, 1));
        mat.setColor("Ambient", new ColorRGBA(1f, 1f, 1f, 1f));
        mat.setColor("Specular", new ColorRGBA(0, 0, 0, 1));
        // mat.setBoolean("UseMaterialColors", true);

        // 设置贴图
        if (m.TextureCounter > 0) {
            mat.setTexture("DiffuseMap", createTexture(m.smTexture[0].Name));
        }
        if (m.TextureCounter > 1) {
            mat.setBoolean("SeparateTexCoord", true);
            mat.setTexture("LightMap", createTexture(m.smTexture[1].Name));
        }

        return mat;
    }

    /**
     * 创建一个忽略光源的材质。
     * 
     * @param m
     * @return
     */
    public static Material createMiscMaterial(_Material m) {
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        // mat.setColor("Color", new ColorRGBA(m.Diffuse.r, m.Diffuse.g,
        // m.Diffuse.b, 1));
        mat.setColor("Color", ColorRGBA.White);

        // 设置贴图
        if (m.TextureCounter > 0) {
            mat.setTexture("ColorMap", createTexture(m.smTexture[0].Name));
        }
        if (m.TextureCounter > 1) {
            mat.setBoolean("SeparateTexCoord", true);
            mat.setTexture("LightMap", createTexture(m.smTexture[1].Name));
        }

        return mat;
    }

    /**
     * 创建一个匀速切换帧的材质。
     * 
     * @param m
     * @return
     */
    public static Material createShiftMaterial(_Material m) {
        Material mat = new Material(assetManager, "Shader/Misc/Shift.j3md");

        // 画面的切换时间间隔
        float ShiftSpeed = (1 << m.Shift_FrameSpeed) / 1000f;
        mat.setFloat("ShiftSpeed", ShiftSpeed);

        // 设置贴图
        Texture tex;
        for (int i = 0; i < m.AnimTexCounter; i++) {
            tex = createTexture(m.smAnimTexture[i].Name);
            mat.setTexture("Tex" + (i + 1), tex);
        }

        return mat;
    }

    /**
     * 创建一个卷轴动画材质。
     * 
     * @param m
     * @return
     */
    public static Material createScrollMaterial(_Material m) {
        Material mat = new Material(assetManager, "Shader/Misc/Scroll.j3md");

        // 画面的卷动速度
        float speed = 1f;

        int n = m.TextureFormState[0];
        if (n >= 6 && n <= 14) {
            speed = 15 - n;
        }

        if (n >= 15 && n <= 18) {
            int factor = 18 - n + 4;
            speed = (128 >> factor) / 256f;
        }

        mat.setFloat("Speed", speed);

        // 设置贴图
        Texture tex = createTexture(m.smTexture[0].Name);
        mat.setTexture("ColorMap", tex);

        if (m.TextureCounter > 1) {
            mat.setBoolean("SeparateTexCoord", true);
            mat.setTexture("LightMap", createTexture(m.smTexture[1].Name));
        }

        return mat;
    }

    /**
     * 创建一个原地转圈的动画材质。 Water动画专用
     * 
     * @param m
     * @return
     */
    public static Material createRoundMaterial(_Material m) {
        Material mat = new Material(assetManager, "Shader/Misc/Round.j3md");

        // 设置贴图
        Texture tex = createTexture(m.smTexture[0].Name);
        mat.setTexture("ColorMap", tex);

        if (m.TextureCounter > 1) {
            mat.setBoolean("SeparateTexCoord", true);
            mat.setTexture("LightMap", createTexture(m.smTexture[1].Name));
        }

        return mat;
    }

    /**
     * 设置材质的RenderState
     * 
     * @param m
     * @param mat
     */
    private static void setRenderState(_Material m, Material mat) {
        RenderState rs = mat.getAdditionalRenderState();

        switch (m.BlendType) {
        case 0:// SMMAT_BLEND_NONE
            rs.setBlendMode(BlendMode.Off);
            break;
        case 1:// SMMAT_BLEND_ALPHA
            rs.setBlendMode(BlendMode.Alpha);
            break;
        case 2:// SMMAT_BLEND_COLOR
            rs.setBlendMode(BlendMode.Color);
            break;
        case 3:// SMMAT_BLEND_SHADOW
            break;
        case 4:// SMMAT_BLEND_LAMP
            rs.setBlendMode(BlendMode.Additive);
            break;
        case 5:// SMMAT_BLEND_ADDCOLOR
            rs.setBlendMode(BlendMode.Additive);
            break;
        case 6:
            break;
        default:
            logger.info("Unknown BlendType=" + m.BlendType);
        }
        ;

        if (m.TwoSide == 1) {
            rs.setFaceCullMode(FaceCullMode.Off);
        }

        if (m.TextureType == 0x0001) {
            // 动画默认显示2面
            rs.setFaceCullMode(FaceCullMode.Off);
        }

        // 透明物体
        if (m.MapOpacity != 0 || m.Transparency != 0) {
            // 这个值设置得稍微大一些，这样草、花等图片的边缘就会因为透明度不够而过滤掉像素。
            mat.setFloat("AlphaDiscardThreshold", 0.75f);
            // 虽然已经过时，但是还是写上以防不测。
            // rs.setAlphaTest(true);
            // rs.setAlphaFallOff(0.6f);
            rs.setDepthWrite(true);
            rs.setDepthTest(true);
            rs.setColorWrite(true);

            // 透明物体不裁剪面
            rs.setFaceCullMode(FaceCullMode.Off);
        }
    }



    /**
     * 生成STAGE3D对象
     * @param name 
     * 
     * @return
     */
    public static Node buildScene3D(Stage stage, String name) {
        Node rootNode = new Node("STAGE3D:" + name);

        Vector3f[] orginNormal = null;
        if (SceneConstants.USE_LIGHT) {
            // 为了让表面平滑光照，先基于原来的面和顶点计算一次法向量。
            orginNormal = computeOrginNormals(stage);
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
            Mesh mesh = buildMesh(stage, size, mat_id, orginNormal);
            Geometry geom = new Geometry(name + "#" + mat_id, mesh);

            // 创建材质
            Material mat;

            // 有多个动画
            if (m.TextureType == 0) {
                // SMTEX_TYPE_MULTIMIX
                int n = m.TextureFormState[0];
                if (n >= 4) {// 4 SCROLL 滚轴 5 REFLEX 反光 6 SCROLL2 2倍速滚轴
                    mat = SceneBuilder.createScrollMaterial(m);
                } else {
                    if (SceneConstants.USE_LIGHT) {
                        mat = SceneBuilder.createLightMaterial(m);
                    } else {
                        mat = SceneBuilder.createMiscMaterial(m);
                    }
                }
            } else {// SMTEX_TYPE_ANIMATION
                if (m.AnimTexCounter > 0) {
                    // AminTexCounter大于0说明有轮播动画，创建一个Control，定时更新画面。
                    mat = SceneBuilder.createShiftMaterial(m);
                } else {
                    mat = SceneBuilder.createMiscMaterial(m);
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
                    mat = SceneBuilder.createRoundMaterial(m);
                    break;
                }
                }
            }

            SceneBuilder.setRenderState(m, mat);

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
    
    /**********************************
     * STAGE3D
     */

    /**
     * 根据原有的面，计算每个顶点的法向量。
     * 
     * @return
     */
    private static Vector3f[] computeOrginNormals(Stage stage) {
        TempVars tmp = TempVars.get();

        Vector3f A;// 三角形的第1个点
        Vector3f B;// 三角形的第2个点
        Vector3f C;// 三角形的第3个点

        Vector3f vAB = tmp.vect1;
        Vector3f vAC = tmp.vect2;
        Vector3f n = tmp.vect4;

        // Here we allocate all the memory we need to calculate the normals
        Vector3f[] tempNormals = new Vector3f[stage.nFace];
        Vector3f[] normals = new Vector3f[stage.nVertex];

        for (int i = 0; i < stage.nFace; i++) {
            A = stage.Vertex[stage.Face[i].v[0]].v;
            B = stage.Vertex[stage.Face[i].v[1]].v;
            C = stage.Vertex[stage.Face[i].v[2]].v;

            vAB = B.subtract(A, vAB);
            vAC = C.subtract(A, vAC);
            n = vAB.cross(vAC, n);

            tempNormals[i] = n.normalize();
        }

        Vector3f sum = tmp.vect4;
        int shared = 0;

        for (int i = 0; i < stage.nVertex; i++) {
            // 统计每个点被那些面共用。
            for (int j = 0; j < stage.nFace; j++) {
                if (stage.Face[j].v[0] == i || stage.Face[j].v[1] == i || stage.Face[j].v[2] == i) {
                    sum.addLocal(tempNormals[j]);
                    shared++;
                }
            }

            // 求均值
            normals[i] = sum.divideLocal((shared)).normalize();

            sum.zero(); // Reset the sum
            shared = 0; // Reset the shared
        }

        tmp.release();
        return normals;
    }

    /**
     * 由于网格中不同的面所应用的材质不同，需要根据材质来对网格进行分组，将相同材质的面单独取出来，做成一个独立的网格。
     * 
     * @param stage
     *            STAGE3D对象
     * @param size
     *            面数
     * @param mat_id
     *            材质编号
     * @param orginNormal
     *            法线
     * @return
     */
    public static Mesh buildMesh(Stage stage, int size, int mat_id, Vector3f[] orginNormal) {

        Vector3f[] position = new Vector3f[size * 3];
        int[] f = new int[size * 3];
        Vector3f[] normal = new Vector3f[size * 3];
        Vector2f[] uv1 = new Vector2f[size * 3];
        Vector2f[] uv2 = new Vector2f[size * 3];

        int index = 0;
        // Prepare MeshData
        for (int i = 0; i < stage.nFace; i++) {
            // Check the MaterialIndex
            if (stage.Face[i].v[3] != mat_id)
                continue;

            // 顺序处理3个顶点
            for (int vIndex = 0; vIndex < 3; vIndex++) {
                // 顶点 VERTEX
                position[index * 3 + vIndex] = stage.Vertex[stage.Face[i].v[vIndex]].v;

                if (SceneConstants.USE_LIGHT) {
                    // 法向量 Normal
                    normal[index * 3 + vIndex] = orginNormal[stage.Face[i].v[vIndex]];
                }

                // 面 FACE
                f[index * 3 + vIndex] = index * 3 + vIndex;

                // 纹理映射
                TEXLINK tl = stage.Face[i].TexLink;
                if (tl != null) {
                    // 第1组uv坐标
                    uv1[index * 3 + vIndex] = new Vector2f(tl.u[vIndex], 1f - tl.v[vIndex]);
                } else {
                    uv1[index * 3 + vIndex] = new Vector2f();
                }

                // 第2组uv坐标
                if (tl != null && tl.NextTex != null) {
                    tl = tl.NextTex;
                    uv2[index * 3 + vIndex] = new Vector2f(tl.u[vIndex], 1f - tl.v[vIndex]);
                } else {
                    uv2[index * 3 + vIndex] = new Vector2f();
                }
            }

            index++;
        }

        Mesh mesh = new Mesh();
        mesh.setBuffer(Type.Position, 3, BufferUtils.createFloatBuffer(position));
        mesh.setBuffer(Type.Index, 3, f);
        // DiffuseMap UV
        mesh.setBuffer(Type.TexCoord, 2, BufferUtils.createFloatBuffer(uv1));
        // LightMap UV
        mesh.setBuffer(Type.TexCoord2, 2, BufferUtils.createFloatBuffer(uv2));

        if (SceneConstants.USE_LIGHT) {
            // 法向量
            mesh.setBuffer(Type.Normal, 3, BufferUtils.createFloatBuffer(normal));
        }

        mesh.setStatic();
        mesh.updateBound();
        mesh.updateCounts();

        return mesh;
    }

    /**
     * 生成碰撞网格：将透明的、不参与碰撞检测的面统统裁剪掉，只保留参于碰撞检测的面。
     * 
     * @return
     */
    public static Mesh buildCollisionMesh(Stage stage) {
        Mesh mesh = new Mesh();

        int materialCount = stage.materialGroup.materialCount;
        /**
         * 根据材质的特诊来筛选参加碰撞检测的物体， 将被忽略的材质设置成null，作为一种标记。
         */
        _Material m;// 临时变量
        for (int mat_id = 0; mat_id < materialCount; mat_id++) {
            m = stage.materials[mat_id];

            if ((m.MeshState & 0x0001) == 0) {
                stage.materials[mat_id] = null;
            }
        }

        /**
         * 统计有多少个要参加碰撞检测的面。
         */
        int loc[] = new int[stage.nVertex];
        for (int i = 0; i < stage.nVertex; i++) {
            loc[i] = -1;
        }

        int fSize = 0;
        for (int i = 0; i < stage.nFace; i++) {
            StageFace face = stage.Face[i];
            if (stage.materials[face.v[3]] != null) {
                loc[face.v[0]] = face.v[0];
                loc[face.v[1]] = face.v[1];
                loc[face.v[2]] = face.v[2];

                fSize++;
            }
        }

        int vSize = 0;
        for (int i = 0; i < stage.nVertex; i++) {
            if (loc[i] > -1) {
                vSize++;
            }
        }

        // 记录新的顶点编号
        Vector3f[] v = new Vector3f[vSize];
        vSize = 0;
        for (int i = 0; i < stage.nVertex; i++) {
            if (loc[i] > -1) {
                v[vSize] = stage.Vertex[i].v;
                loc[i] = vSize;
                vSize++;
            }
        }

        // 记录新的顶点索引号
        int[] f = new int[fSize * 3];
        fSize = 0;
        for (int i = 0; i < stage.nFace; i++) {
            StageFace face = stage.Face[i];
            if (stage.materials[face.v[3]] != null) {
                f[fSize * 3] = loc[face.v[0]];
                f[fSize * 3 + 1] = loc[face.v[1]];
                f[fSize * 3 + 2] = loc[face.v[2]];
                fSize++;
            }
        }

        mesh.setBuffer(Type.Position, 3, BufferUtils.createFloatBuffer(v));
        mesh.setBuffer(Type.Index, 3, BufferUtils.createIntBuffer(f));

        mesh.updateBound();
        mesh.setStatic();

        return mesh;
    }
}
