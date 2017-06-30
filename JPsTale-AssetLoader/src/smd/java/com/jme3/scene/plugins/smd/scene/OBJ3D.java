package com.jme3.scene.plugins.smd.scene;

import java.io.IOException;

import org.pstale.assets.Flyweight;

import com.jme3.scene.plugins.smd.animation.FRAME_POS;
import com.jme3.scene.plugins.smd.animation.PAT3D;
import com.jme3.scene.plugins.smd.animation.TransPosition;
import com.jme3.scene.plugins.smd.animation.TransRotation;
import com.jme3.scene.plugins.smd.animation.TransScale;
import com.jme3.scene.plugins.smd.material.TEXLINK;
import com.jme3.scene.plugins.smd.math.Matrix4F;
import com.jme3.scene.plugins.smd.math.Matrix4D;
import com.jme3.scene.plugins.smd.math.Vector3D;
import com.jme3.util.LittleEndien;

/**
 * size = 2236
 */
public class OBJ3D extends Flyweight {
    // DWORD Head;
    public Vertex[] Vertex;// 顶点
    public Face[] Face;// 面
    public TEXLINK[] TexLink;// 纹理坐标

    public OBJ3D[] Physique; // 各顶点的骨骼

    Vertex ZeroVertex; // 坷宏璃飘 吝居 滚咆胶 蔼

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
    public OBJ3D pParent; // 何葛 坷宏璃飘 器牢磐

    Matrix4D Tm; // 扁夯 TM 青纺
    public Matrix4D TmInvert; // 逆矩阵
    Matrix4F TmResult; // 局聪皋捞记 青纺
    Matrix4D TmRotate; // 扁夯利 雀傈 青纺

    Matrix4D mWorld; // 岿靛谅钎 函券 青纺
    Matrix4D mLocal; // 肺漠谅钎 函券 青纺

    int lFrame;// 没有实际作用

    public float qx, qy, qz, qw; // 雀傈 孽磐聪攫
    public float sx, sy, sz; // 胶纳老 谅钎
    public float px, py, pz; // 器瘤记 谅钎

    public TransRotation[] TmRot; // 橇饭烙喊 雀傈 局聪皋捞记
    public TransPosition[] TmPos; // 橇饭烙喊 器瘤记 局聪皋捞记
    public TransScale[] TmScale; // 橇饭烙喊 胶纳老 局聪皋捞记

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

    public OBJ3D() {
        NodeName = null;
        NodeParent = null;
        Tm = new Matrix4D();
        pParent = null;
        TmRot = null;
        TmPos = null;
        TmScale = null;
        TmRotCnt = 0;
        TmPosCnt = 0;
        TmScaleCnt = 0;
        TmPrevRot = null;
        Face = null;
        Vertex = null;
        TexLink = null;
        Physique = null;
    }

    public void loadData(LittleEndien in) throws IOException {
        in.readInt();// Head `DCB\0`
        in.readInt();// smVERTEX *Vertex;
        in.readInt();// smFACE *Face;
        lpOldTexLink = in.readInt();// smTEXLINK *TexLink;
        lpPhysuque = in.readInt();// smOBJ3D **Physique;

        ZeroVertex = new Vertex();
        ZeroVertex.loadData(in);

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

        Tm = new Matrix4D();
        Tm.loadData(in);

        TmInvert = new Matrix4D();
        TmInvert.loadData(in);

        TmResult = new Matrix4F();
        TmResult.loadData(in);

        TmRotate = new Matrix4D();
        TmRotate.loadData(in);

        mWorld = new Matrix4D();
        mWorld.loadData(in);

        mLocal = new Matrix4D();
        mLocal.loadData(in);

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
     * @param PatPhysique
     */
    public void loadFile(LittleEndien in, PAT3D PatPhysique) throws IOException {

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

        TmRot = new TransRotation[TmRotCnt];
        for (int i = 0; i < TmRotCnt; i++) {
            TmRot[i] = new TransRotation();
            TmRot[i].loadData(in);
        }

        TmPos = new TransPosition[TmPosCnt];
        for (int i = 0; i < TmPosCnt; i++) {
            TmPos[i] = new TransPosition();
            TmPos[i].loadData(in);
        }

        TmScale = new TransScale[TmScaleCnt];
        for (int i = 0; i < TmScaleCnt; i++) {
            TmScale[i] = new TransScale();
            TmScale[i].loadData(in);
        }

        TmPrevRot = new Matrix4F[TmRotCnt];
        for (int i = 0; i < TmRotCnt; i++) {
            TmPrevRot[i] = new Matrix4F();
            TmPrevRot[i].loadData(in);
        }

        relinkFaceAndTex();

        // 绑定动画骨骼
        if (lpPhysuque != 0 && PatPhysique != null) {

            Physique = new OBJ3D[nVertex];

            String[] names = new String[nVertex];
            for (int i = 0; i < nVertex; i++) {
                names[i] = getString(in, 32);
            }

            for (int i = 0; i < nVertex; i++) {
                Physique[i] = PatPhysique.getObjectFromName(names[i]);
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

}