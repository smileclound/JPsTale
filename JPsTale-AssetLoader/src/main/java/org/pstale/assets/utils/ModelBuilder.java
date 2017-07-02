package org.pstale.assets.utils;

import org.pstale.constants.SceneConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jme3.animation.AnimControl;
import com.jme3.animation.Animation;
import com.jme3.animation.Skeleton;
import com.jme3.animation.SkeletonControl;
import com.jme3.asset.AssetManager;
import com.jme3.asset.TextureKey;
import com.jme3.material.Material;
import com.jme3.material.RenderState;
import com.jme3.material.RenderState.BlendMode;
import com.jme3.material.RenderState.FaceCullMode;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.Node;
import com.jme3.scene.VertexBuffer.Type;
import com.jme3.scene.plugins.smd.geom.GeomObject;
import com.jme3.scene.plugins.smd.geom.PAT3D;
import com.jme3.scene.plugins.smd.material.TEXLINK;
import com.jme3.scene.plugins.smd.material._Material;
import com.jme3.scene.plugins.smd.math.Matrix4D;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture.WrapMode;
import com.jme3.util.BufferUtils;

/**
 * 根据解析出来的数据，生成jME3的材质。
 * 
 * @author yanmaoyuan
 *
 */
public class ModelBuilder {

    static Logger logger = LoggerFactory.getLogger(ModelBuilder.class);

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
     * 生成模型
     * @param pat
     * @param name
     * @return
     */
    public static Node buildModel(PAT3D pat, String name) {
        Node rootNode = new Node("PAT3D:" + name);

        Skeleton ske = null;
        // 生成骨骼
        if (pat.skeleton != null) {
            ske = AnimationBuilder.buildSkeleton(pat.skeleton);
        }
        
        logger.debug("Material Count: {}", pat.materialGroup.materialCount);

        for (int i = 0; i < pat.objCount; i++) {
            GeomObject obj = pat.objArray[i];
            if (obj.nFace > 0) {

                // 对所有顶点进行线性变换，否则顶点的坐标都在原点附近。
                invertPoint(obj);
                

                // 根据模型的材质不同，将创建多个网格，分别渲染。
                for (int mat_id = 0; mat_id < pat.materialGroup.materialCount; mat_id++) {
                    // 生成网格
                    Mesh mesh = buildMesh(obj, mat_id, ske);

                    // 创建材质
                    _Material m = pat.materialGroup.materials[mat_id];
                    Material mat;
                    if (SceneConstants.USE_LIGHT) {
                        mat = createLightMaterial(m);
                    } else {
                        mat = createMiscMaterial(m);
                    }
                    
                    setRenderState(m, mat);

                    // 创建几何体并应用材质。
                    Geometry geom = new Geometry(pat.objArray[i].NodeName + "#" + mat_id, mesh);
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
            Animation anim = AnimationBuilder.buildAnimation(pat.skeleton, ske);
            AnimControl ac = new AnimControl(ske);
            ac.addAnim(anim);
            rootNode.addControl(ac);
            rootNode.addControl(new SkeletonControl(ske));
        }

        return rootNode;
    }
    
    private static void invertPoint(GeomObject obj) {

        for (int i = 0; i < obj.nVertex; i++) {
            if (obj.boneArray != null) {
                obj.Vertex[i].v = mult(obj.Vertex[i].x, obj.Vertex[i].y, obj.Vertex[i].z, obj.boneArray[i].transformInvert);
            } else {
                obj.Vertex[i].v = mult(obj.Vertex[i].x, obj.Vertex[i].y, obj.Vertex[i].z, obj.transformInvert);
            }
        }
    }

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
     * @param m
     */
    private static Vector3f mult(long res1, long res2, long res3, Matrix4D m) {
        long v1 = -((res2 * m._33 * m._21 - res2 * m._23 * m._31 - res1 * m._33 * m._22 + res1 * m._23 * m._32
                - res3 * m._21 * m._32 + res3 * m._31 * m._22 + m._43 * m._21 * m._32 - m._43 * m._31 * m._22
                - m._33 * m._21 * m._42 + m._33 * m._41 * m._22 + m._23 * m._31 * m._42
                - m._23 * m._41 * m._32) << 8)
                / (m._11 * m._33 * m._22 + m._23 * m._31 * m._12 + m._21 * m._32 * m._13
                        - m._33 * m._21 * m._12 - m._11 * m._23 * m._32 - m._31 * m._22 * m._13);
        long v2 = ((res2 * m._11 * m._33 - res1 * m._33 * m._12 - res3 * m._11 * m._32 + res3 * m._31 * m._12
                - res2 * m._31 * m._13 + res1 * m._32 * m._13 + m._11 * m._43 * m._32 - m._43 * m._31 * m._12
                - m._11 * m._33 * m._42 + m._33 * m._41 * m._12 + m._31 * m._42 * m._13
                - m._41 * m._32 * m._13) << 8)
                / (m._11 * m._33 * m._22 + m._23 * m._31 * m._12 + m._21 * m._32 * m._13
                        - m._33 * m._21 * m._12 - m._11 * m._23 * m._32 - m._31 * m._22 * m._13);
        long v3 = -((res2 * m._11 * m._23 - res1 * m._23 * m._12 - res3 * m._11 * m._22 + res3 * m._21 * m._12
                - res2 * m._21 * m._13 + res1 * m._22 * m._13 + m._11 * m._43 * m._22 - m._43 * m._21 * m._12
                - m._11 * m._23 * m._42 + m._23 * m._41 * m._12 + m._21 * m._42 * m._13
                - m._41 * m._22 * m._13) << 8)
                / (m._11 * m._33 * m._22 + m._23 * m._31 * m._12 + m._21 * m._32 * m._13
                        - m._33 * m._21 * m._12 - m._11 * m._23 * m._32 - m._31 * m._22 * m._13);

        float x = (float) v1 / 256.0f;
        float y = (float) v2 / 256.0f;
        float z = (float) v3 / 256.0f;

        return new Vector3f(x, y, z);
    }
    
    
    /**
     * 生成网格数据。
     * 
     * @param ske
     * @return
     */
    public static Mesh buildMesh(GeomObject obj, int mat_id, Skeleton ske) {
        Mesh mesh = new Mesh();

        // 统计使用这个材质的面数
        int count = 0;
        for (int i = 0; i < obj.nFace; i++) {
            if (obj.Face[i].v[3] == mat_id) {
                count++;
            }
        }

        // 计算网格
        Vector3f[] position = new Vector3f[count * 3];
        int[] f = new int[count * 3];
        Vector2f[] uv = new Vector2f[count * 3];
        int index = 0;

        // Prepare MeshData
        for (int i = 0; i < obj.nFace; i++) {
            // 忽略掉这个面
            if (obj.Face[i].v[3] != mat_id) {
                continue;
            }

            // 顶点 VERTEX
            position[index * 3 + 0] = obj.Vertex[obj.Face[i].v[0]].v;
            position[index * 3 + 1] = obj.Vertex[obj.Face[i].v[1]].v;
            position[index * 3 + 2] = obj.Vertex[obj.Face[i].v[2]].v;

            // 面 FACE
            if (i < obj.nFace) {
                f[index * 3 + 0] = index * 3 + 0;
                f[index * 3 + 1] = index * 3 + 1;
                f[index * 3 + 2] = index * 3 + 2;
            }

            // 纹理映射
            TEXLINK tl = obj.Face[i].TexLink;
            if (tl != null) {
                // 第1组uv坐标
                uv[index * 3 + 0] = new Vector2f(tl.u[0], 1f - tl.v[0]);
                uv[index * 3 + 1] = new Vector2f(tl.u[1], 1f - tl.v[1]);
                uv[index * 3 + 2] = new Vector2f(tl.u[2], 1f - tl.v[2]);
            } else {
                uv[index * 3 + 0] = new Vector2f();
                uv[index * 3 + 1] = new Vector2f();
                uv[index * 3 + 2] = new Vector2f();
            }

            index++;
        }

        mesh.setBuffer(Type.Position, 3, BufferUtils.createFloatBuffer(position));
        mesh.setBuffer(Type.Index, 3, f);
        mesh.setBuffer(Type.TexCoord, 2, BufferUtils.createFloatBuffer(uv));

        // 骨骼蒙皮
        if (obj.boneArray != null && ske != null) {
            float[] boneIndex = new float[count * 12];
            float[] boneWeight = new float[count * 12];

            index = 0;
            for (int i = 0; i < obj.nFace; i++) {
                // 忽略这个面
                if (obj.Face[i].v[3] != mat_id) {
                    continue;
                }

                for (int j = 0; j < 3; j++) {
                    int v = obj.Face[i].v[j];// 顶点序号
                    int bi = index * 3 + j;// 对应骨骼的序号

                    GeomObject bone = obj.boneArray[v];
                    byte targetBoneIndex = (byte) ske.getBoneIndex(bone.NodeName);

                    boneIndex[bi * 4] = targetBoneIndex;
                    boneIndex[bi * 4 + 1] = 0;
                    boneIndex[bi * 4 + 2] = 0;
                    boneIndex[bi * 4 + 3] = 0;

                    boneWeight[bi * 4] = 1;
                    boneWeight[bi * 4 + 1] = 0;
                    boneWeight[bi * 4 + 2] = 0;
                    boneWeight[bi * 4 + 3] = 0;
                }

                index++;
            }

            mesh.setMaxNumWeights(1);
            // apply software skinning
            mesh.setBuffer(Type.BoneIndex, 4, boneIndex);
            mesh.setBuffer(Type.BoneWeight, 4, boneWeight);
            // apply hardware skinning
            mesh.setBuffer(Type.HWBoneIndex, 4, boneIndex);
            mesh.setBuffer(Type.HWBoneWeight, 4, boneWeight);

            mesh.generateBindPose(true);
        }

        mesh.setStatic();
        mesh.updateBound();
        mesh.updateCounts();

        return mesh;
    }

}
