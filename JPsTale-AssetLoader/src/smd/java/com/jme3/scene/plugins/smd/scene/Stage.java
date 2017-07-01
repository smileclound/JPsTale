package com.jme3.scene.plugins.smd.scene;

import java.io.IOException;

import org.pstale.assets.Flyweight;

import com.jme3.math.Vector3f;
import com.jme3.scene.plugins.smd.SmdFileHeader;
import com.jme3.scene.plugins.smd.material._Material;
import com.jme3.scene.plugins.smd.material.MaterialGroup;
import com.jme3.scene.plugins.smd.material.TEXLINK;
import com.jme3.util.LittleEndien;

/**
 * Stage3D对象的属性，存储了一个完整的smSTAGE3D对象。 size = 262260
 * 其中的关键数据是nVertex/nFace/nTexLink/nLight这些。
 */
public class Stage extends Flyweight {
    // DWORD Head; 无用的头文件指针，4字节

    // WORD *StageArea[MAP_SIZE][MAP_SIZE];
    // 256 * 256个指针，共262144字节
    int[][] StageArea;

    // POINT *AreaList; 一个指针，等于是一个数组
    Vector3f[] AreaList;

    int AreaListCnt;

    int MemMode;

    int SumCount;
    int CalcSumCount;

    public StageVertex[] Vertex;
    public StageFace[] Face;
    public TEXLINK[] TexLink;
    public Light3D[] Light;

    // sizeof(smMaterialGroup) = 88
    public MaterialGroup materialGroup;
    
    // smSTAGE_OBJECT *StageObject;
    public _Material[] materials;

    public int nVertex = 0;// offset = 88 + = 262752
    public int nFace = 0;
    public int nTexLink = 0;// UvVertexNum
    public int nLight = 0;
    int nVertColor = 0;

    int Contrast = 0;
    int Bright = 0;

    Vector3f vectLight;

    // WORD *lpwAreaBuff;
    int wAreaSize;
    // RECT StageMapRect;// left top right bottom 4个整数
    public int left;
    public int top;
    public int right;
    public int bottom;

    // ////////////////
    // 这个整数用来记录TexLink在文件中的地址
    int lpOldTexLink;

    // ////////////////

    /**
     * 初始化Stage3D对象
     */
    public void loadData(LittleEndien in) throws IOException {

        // Head = FALSE;
        in.readInt();// Head
        in.readFully(new byte[262144]);// *StageArea[MAP_SIZE][MAP_SIZE]; 4 *
                                       // 256 * 256 = 262144
        in.readInt();// *AreaList;
        AreaListCnt = in.readInt();
        MemMode = in.readInt();
        SumCount = in.readInt();
        CalcSumCount = in.readInt();

        in.readInt();// *Vertex
        in.readInt();// *Face
        lpOldTexLink = in.readInt();// *TexLink
        in.readInt();// *smLight
        in.readInt();// *smMaterialGroup
        in.readInt();// *StageObject
        in.readInt();// *smMaterial

        nVertex = in.readInt();
        nFace = in.readInt();
        nTexLink = in.readInt();
        nLight = in.readInt();

        nVertColor = in.readInt();
        Contrast = in.readInt();
        Bright = in.readInt();

        // 灯光的方向
        vectLight = new Vector3f();
        vectLight.x = in.readInt();
        vectLight.y = in.readInt();
        vectLight.z = in.readInt();

        in.readInt();// *lpwAreaBuff
        wAreaSize = in.readInt();

        // 下列数值是地图的边缘，x,z平面的矩形。矩形的边长被放大了256倍
        // sizeof(RECT) == 16
        left = in.readInt();// minX
        top = in.readInt();// minY
        right = in.readInt();// maxX
        bottom = in.readInt();// maxY
    }

    /**
     * 加载舞台数据
     * 
     * @return
     */
    public void loadFile(LittleEndien in) throws IOException {
        SmdFileHeader header = new SmdFileHeader();
        header.loadData(in);

        this.loadData(in);

        // 读取MaterialGroup
        if (header.matCounter > 0) {
            // 读取MaterialGroup对象
            materialGroup = new MaterialGroup();
            materialGroup.loadData(in);
            materials = materialGroup.materials;
        }

        // 读取Vertex
        Vertex = new StageVertex[nVertex];
        for (int i = 0; i < nVertex; i++) {
            Vertex[i] = new StageVertex();
            Vertex[i].loadData(in);
        }

        // 读取Face
        Face = new StageFace[nFace];
        for (int i = 0; i < nFace; i++) {
            Face[i] = new StageFace();
            Face[i].loadData(in);
        }

        // 读取TEX_LINK(其实就是uv坐标)
        TexLink = new TEXLINK[nTexLink];
        for (int i = 0; i < nTexLink; i++) {
            TexLink[i] = new TEXLINK();
            TexLink[i].loadData(in);
        }

        // 读取灯光
        if (nLight > 0) {
            Light = new Light3D[nLight];
            for (int i = 0; i < nLight; i++) {
                Light[i] = new Light3D();
                Light[i].loadData(in);
            }
        }

        // 重新建立Face与TexLink之间的关联
        relinkFaceAndTex();

        in.close();
    }

    /**
     * 重新建立TexLink之间、Face与TexLink之间的关联。
     * 
     * TexLink是一个smTEXLINK数组，顺序存储，lpOldTexLink记录了其首地址。 由于
     * {@code sizeof(smTEXLINK) = 32}，所以：{@code 索引号=(原地址-lpOldTexLink)/32}
     */
    void relinkFaceAndTex() {
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