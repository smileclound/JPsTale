package com.jme3.scene.plugins.smd.geom;

import java.io.IOException;

import org.pstale.assets.Flyweight;

import com.jme3.scene.plugins.smd.material.TEXLINK;
import com.jme3.scene.plugins.smd.math.Matrix4F;
import com.jme3.scene.plugins.smd.math.Matrix4D;
import com.jme3.scene.plugins.smd.math.Vector3D;
import com.jme3.util.LittleEndien;

/**
 * size = 2236
 */
public class GeomObject extends Flyweight {
    // DWORD Head;
    public Vertex[] Vertex;// 顶点
    public Face[] Face;// 面
    public TEXLINK[] TexLink;// 纹理坐标

    public GeomObject[] boneArray; // 各顶点的骨骼

    Vertex zeroVertex; // 坷宏璃飘 吝居 滚咆胶 蔼

    public int maxZ, minZ;
    public int maxY, minY;
    public int maxX, minX;

    public int dBound; // 官款爹 胶其绢 蔼 ^2
    public int Bound; // 官款爹 胶其绢 蔼

    public int MaxVertex;
    public int MaxFace;

    public int nVertex;
    public int nFace;

    public int nTexLink;

    int ColorEffect; // 祸惑瓤苞 荤侩 蜡公
    int ClipStates; // 努府俏 付胶农 ( 阿 努府俏喊 荤侩 蜡公 )

    Vector3D Posi;
    Vector3D CameraPosi;
    Vector3D Angle;
    int[] Trig = new int[8];

    // 局聪皋捞记 包访
    public String NodeName;// [32]; // 坷宏璃飘狼 畴靛 捞抚
    public String NodeParent;// [32]; // 何葛 坷宏璃飘狼 捞抚
    public GeomObject pParent; // 何葛 坷宏璃飘 器牢磐

    Matrix4D transform; // 扁夯 TM 青纺
    public Matrix4D transformInvert; // 逆矩阵
    Matrix4F transformResult; // 局聪皋捞记 青纺
    Matrix4D transformRotate; // 扁夯利 雀傈 青纺

    Matrix4D worldMatrix; // 岿靛谅钎 函券 青纺
    Matrix4D localMatrix; // 肺漠谅钎 函券 青纺

    int lFrame;// 没有实际作用

    public float qx, qy, qz, qw; // 雀傈 孽磐聪攫
    public float sx, sy, sz; // 胶纳老 谅钎
    public float px, py, pz; // 器瘤记 谅钎

    public TransRotation[] rotArray;
    public TransPosition[] posArray;
    public TransScale[] scaleArray;

    Matrix4F[] TmPrevRot; // 帧的动画矩阵

    public int TmRotCnt;
    public int TmPosCnt;
    public int TmScaleCnt;

    // TM 橇饭烙 辑摹 ( 橇饭烙捞 腹栏搁 茫扁啊 塞惦 )
    FRAME_POS[] TmRotFrame = new FRAME_POS[OBJ_FRAME_SEARCH_MAX];
    FRAME_POS[] TmPosFrame = new FRAME_POS[OBJ_FRAME_SEARCH_MAX];
    FRAME_POS[] TmScaleFrame = new FRAME_POS[OBJ_FRAME_SEARCH_MAX];
    int TmFrameCnt;// 是否有动画 TRUE or FALSE

    // //////////////////
    int lpPhysuque;
    int lpOldTexLink;
    // //////////////////
    
    /**
     * 各顶点对应的骨骼名称，用于骨骼蒙皮动画。
     */
    public String[] boneNames;

    /**
     * 最大帧号，用于计算动画时间。
     */
    public int maxFrame = 0;
    
    public GeomObject() {
        NodeName = null;
        NodeParent = null;
        transform = new Matrix4D();
        pParent = null;
        rotArray = null;
        posArray = null;
        scaleArray = null;
        TmRotCnt = 0;
        TmPosCnt = 0;
        TmScaleCnt = 0;
        TmPrevRot = null;
        Face = null;
        Vertex = null;
        TexLink = null;
        boneArray = null;
    }

    public void loadData(LittleEndien in) throws IOException {
        in.readInt();// Head `DCB\0`
        in.readInt();// smVERTEX *Vertex;
        in.readInt();// smFACE *Face;
        lpOldTexLink = in.readInt();// smTEXLINK *TexLink;
        lpPhysuque = in.readInt();// smOBJ3D **Physique;

        zeroVertex = new Vertex();
        zeroVertex.loadData(in);

        maxZ = in.readInt();
        minZ = in.readInt();
        maxY = in.readInt();
        minY = in.readInt();
        maxX = in.readInt();
        minX = in.readInt();

        dBound = in.readInt();
        Bound = in.readInt();

        MaxVertex = in.readInt();
        MaxFace = in.readInt();

        nVertex = in.readInt();
        nFace = in.readInt();

        nTexLink = in.readInt();

        ColorEffect = in.readInt();
        ClipStates = in.readInt();

        Posi = new Vector3D();
        Posi.loadData(in);

        CameraPosi = new Vector3D();
        CameraPosi.loadData(in);

        Angle = new Vector3D();
        Angle.loadData(in);

        Trig = new int[8];
        for (int i = 0; i < 8; i++) {
            Trig[i] = in.readInt();
        }

        // 局聪皋捞记 包访
        NodeName = getString(in, 32);
        NodeParent = getString(in, 32);
        in.readInt();// OBJ3D *pParent;

        transform = new Matrix4D();
        transform.loadData(in);

        transformInvert = new Matrix4D();
        transformInvert.loadData(in);

        transformResult = new Matrix4F();
        transformResult.loadData(in);

        transformRotate = new Matrix4D();
        transformRotate.loadData(in);

        worldMatrix = new Matrix4D();
        worldMatrix.loadData(in);

        localMatrix = new Matrix4D();
        localMatrix.loadData(in);

        lFrame = in.readInt();

        qx = in.readFloat();
        qy = in.readFloat();
        qz = in.readFloat();
        qw = in.readFloat();
        sx = in.readInt() / 256f;
        sy = in.readInt() / 256f;
        sz = in.readInt() / 256f;
        px = in.readInt() / 256f;
        py = in.readInt() / 256f;
        pz = in.readInt() / 256f;

        // 4个指针
        in.readInt();// smTM_ROT *TmRot;
        in.readInt();// smTM_POS *TmPos;
        in.readInt();// smTM_SCALE *TmScale;
        in.readInt();// smFMATRIX *TmPrevRot;

        TmRotCnt = in.readInt();
        TmPosCnt = in.readInt();
        TmScaleCnt = in.readInt();

        for (int i = 0; i < OBJ_FRAME_SEARCH_MAX; i++) {
            TmRotFrame[i] = new FRAME_POS();
            TmRotFrame[i].loadData(in);
        }
        for (int i = 0; i < OBJ_FRAME_SEARCH_MAX; i++) {
            TmPosFrame[i] = new FRAME_POS();
            TmPosFrame[i].loadData(in);
        }
        for (int i = 0; i < OBJ_FRAME_SEARCH_MAX; i++) {
            TmScaleFrame[i] = new FRAME_POS();
            TmScaleFrame[i].loadData(in);
        }
        TmFrameCnt = in.readInt();
    }

    /**
     * 读取OBJ3D文件数据
     * 
     * @param skeleton
     */
    public void loadFile(LittleEndien in) throws IOException {

        Vertex = new Vertex[nVertex];
        for (int i = 0; i < nVertex; i++) {
            Vertex[i] = new Vertex();
            Vertex[i].loadData(in);
        }

        Face = new Face[nFace];
        for (int i = 0; i < nFace; i++) {
            Face[i] = new Face();
            Face[i].loadData(in);
        }

        TexLink = new TEXLINK[nTexLink];
        for (int i = 0; i < nTexLink; i++) {
            TexLink[i] = new TEXLINK();
            TexLink[i].loadData(in);
        }

        rotArray = new TransRotation[TmRotCnt];
        for (int i = 0; i < TmRotCnt; i++) {
            rotArray[i] = new TransRotation();
            rotArray[i].loadData(in);
        }

        posArray = new TransPosition[TmPosCnt];
        for (int i = 0; i < TmPosCnt; i++) {
            posArray[i] = new TransPosition();
            posArray[i].loadData(in);
        }

        scaleArray = new TransScale[TmScaleCnt];
        for (int i = 0; i < TmScaleCnt; i++) {
            scaleArray[i] = new TransScale();
            scaleArray[i].loadData(in);
        }
        
        // 统计最大帧数
        maxFrame = getMaxFrame();

        TmPrevRot = new Matrix4F[TmRotCnt];
        for (int i = 0; i < TmRotCnt; i++) {
            TmPrevRot[i] = new Matrix4F();
            TmPrevRot[i].loadData(in);
        }

        relinkFaceAndTex();

        // 绑定动画骨骼
        if (lpPhysuque != 0) {

            boneArray = new GeomObject[nVertex];

            boneNames = new String[nVertex];
            for (int i = 0; i < nVertex; i++) {
                boneNames[i] = getString(in, 32);
            }
        }
    }

    private void relinkFaceAndTex() {
        // 重新建立TexLink链表中的关联
        for (int i = 0; i < nTexLink; i++) {
            if (TexLink[i].lpNextTex != 0) {
                int index = (TexLink[i].lpNextTex - lpOldTexLink) / 32;
                TexLink[i].NextTex = TexLink[index];
            }
        }

        // 重新建立Face与TexLink之间的关联
        for (int i = 0; i < nFace; i++) {
            if (Face[i].lpTexLink != 0) {
                int index = (Face[i].lpTexLink - lpOldTexLink) / 32;
                Face[i].TexLink = TexLink[index];
            }
        }
    }

    /**
     * 绑定骨骼
     * @param skeleton
     */
    public boolean setSkeleton(PAT3D skeleton) {
        if (lpPhysuque != 0 && skeleton != null) {
            boneArray = new GeomObject[nVertex];
            for (int j = 0; j < nVertex; j++) {
                boneArray[j] = skeleton.getObj(boneNames[j]);
            }
            return true;
        }
        return false;
    }
    
    /**
     * 统计最大帧号，计算动画时常。
     */
    public int getMaxFrame() {
        if (TmRotCnt > 0) {
            if (rotArray[TmRotCnt - 1].frame > maxFrame) {
                maxFrame = rotArray[TmRotCnt - 1].frame;
            }
        }
        if (TmPosCnt > 0) {
            if (posArray[TmPosCnt - 1].frame > maxFrame) {
                maxFrame = posArray[TmPosCnt - 1].frame;
            }
        }
        if (TmScaleCnt > 0) {
            if (scaleArray[TmScaleCnt - 1].frame > maxFrame) {
                maxFrame = scaleArray[TmScaleCnt - 1].frame;
            }
        }
        return maxFrame;
    }
}