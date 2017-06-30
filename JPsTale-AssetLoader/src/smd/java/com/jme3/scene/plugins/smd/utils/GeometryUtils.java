package com.jme3.scene.plugins.smd.utils;

import org.pstale.constants.SceneConstants;

import com.jme3.animation.Skeleton;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Mesh;
import com.jme3.scene.VertexBuffer.Type;
import com.jme3.scene.plugins.smd.material.TEXLINK;
import com.jme3.scene.plugins.smd.material._Material;
import com.jme3.scene.plugins.smd.scene.OBJ3D;
import com.jme3.scene.plugins.smd.scene.Stage;
import com.jme3.scene.plugins.smd.scene.StageFace;
import com.jme3.util.BufferUtils;
import com.jme3.util.TempVars;

public class GeometryUtils {
    
    /**
     * 生成网格数据。
     * 
     * @param ske
     * @return
     */
    public static Mesh buildMesh(OBJ3D obj, int mat_id, Skeleton ske) {
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
        if (obj.Physique != null && ske != null) {
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

                    OBJ3D obj3d = obj.Physique[v];
                    byte targetBoneIndex = (byte) ske.getBoneIndex(obj3d.NodeName);

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
    
    /**********************************
     * STAGE3D
     */

    /**
     * 根据原有的面，计算每个顶点的法向量。
     * 
     * @return
     */
    public static Vector3f[] computeOrginNormals(Stage stage) {
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
