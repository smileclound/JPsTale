package com.jme3.scene.plugins.smd.geom;

import java.io.IOException;

import org.pstale.assets.Flyweight;

import com.jme3.scene.plugins.smd.SmdFileHeader;
import com.jme3.scene.plugins.smd.SmdFileObjInfo;
import com.jme3.scene.plugins.smd.material.MaterialGroup;
import com.jme3.scene.plugins.smd.math.Vector3D;
import com.jme3.util.LittleEndien;

/**
 * size = 1228
 */
public class PAT3D extends Flyweight {
    // DWORD Head;
    public GeomObject[] objArray = new GeomObject[128];
    byte[] TmSort = new byte[128];

    public PAT3D skeleton;

    public MaterialGroup materialGroup;// 材质组

    int MaxFrame;
    int Frame;

    int SizeWidth, SizeHeight; // 臭捞 承捞 狼 弥措摹

    /**
     * Child count
     */
    public int objCount;
    // LPDIRECT3DTEXTURE2 *hD3DTexture;

    Vector3D Posi;
    Vector3D Angle;
    Vector3D CameraPosi;

    int dBound;
    int Bound;

    public FRAME_POS[] TmFrame = new FRAME_POS[OBJ_FRAME_SEARCH_MAX];
    public int TmFrameCnt;

    int TmLastFrame;
    Vector3D TmLastAngle;

    public PAT3D() {
        objCount = 0;
        // hD3DTexture = 0;
        skeleton = null;

        MaxFrame = 0;
        Frame = 0;

        SizeWidth = 0;
        SizeHeight = 0;

        Bound = 0;
        dBound = 0;

        TmFrameCnt = 0;

        TmLastFrame = -1;

        TmLastAngle = new Vector3D();
        TmLastAngle.x = -1;
        TmLastAngle.y = -1;
        TmLastAngle.z = -1;

        for (int i = 0; i < 128; i++) {
            TmSort[i] = (byte) i;
        }

        materialGroup = null;
    }

    @Override
    public void loadData(LittleEndien in) throws IOException {

        in.readInt();// Head
        for (int i = 0; i < 128; i++) {
            in.readInt();
        }
        in.readFully(TmSort);

        in.readInt();// smPAT3D *TmParent;

        in.readInt();// smMATERIAL_GROUP *smMaterialGroup; //皋飘府倔 弊缝

        MaxFrame = in.readInt();
        Frame = in.readInt();

        SizeWidth = in.readInt();
        SizeHeight = in.readInt();

        objCount = in.readInt();
        in.readInt();// LPDIRECT3DTEXTURE2 *hD3DTexture;

        Posi = new Vector3D();
        Posi.loadData(in);
        Angle = new Vector3D();
        Angle.loadData(in);
        CameraPosi = new Vector3D();
        CameraPosi.loadData(in);

        dBound = in.readInt();
        Bound = in.readInt();

        for (int i = 0; i < OBJ_FRAME_SEARCH_MAX; i++) {
            TmFrame[i] = new FRAME_POS();
            TmFrame[i].loadData(in);
        }
        TmFrameCnt = in.readInt();

        TmLastFrame = in.readInt();
        TmLastAngle = new Vector3D();
        TmLastAngle.loadData(in);

    }

    public void loadFile(LittleEndien in) throws IOException {

        SmdFileHeader header = new SmdFileHeader();
        header.loadData(in);

        // 读取Obj3D物体信息
        SmdFileObjInfo[] FileObjInfo = new SmdFileObjInfo[header.objCounter];
        for (int i = 0; i < header.objCounter; i++) {
            FileObjInfo[i] = new SmdFileObjInfo();
            FileObjInfo[i].loadData(in);
        }

        // 记录文件头中的动画的帧数，拷贝每帧的数据。
        TmFrameCnt = header.tmFrameCounter;
        for (int i = 0; i < 32; i++) {
            TmFrame[i] = header.TmFrame[i];
        }

        // 读取材质
        // 骨骼文件(.smb)中不包含材质，因此可能没有这一段数据。
        if (header.matCounter > 0) {
            materialGroup = new MaterialGroup();
            materialGroup.loadData(in);
        }

        /**
         * <pre>
         * if (NodeName != null) {
         *     log.debug("NodeName != null && NodeName == " + NodeName);
         *     // 加载指定名称的3D物体
         *     for (int i = 0; i < header.objCounter; i++) {
         *         if (NodeName.equals(FileObjInfo[i].NodeName)) {
         *             // 跳过i个OBJ3D，直接读取其中一个。
         *             int offset = i * 2236;
         *             in.skip(offset);
         * 
         *             GeomObject obj = new GeomObject();
         *             obj.loadData(in);
         *             obj.loadFile(in, BipPat);
         *             addObject(obj);
         *             break;
         *         }
         *     }
         * } else {
         * </pre>
         */

        // 读取全部3D对象
        for (int i = 0; i < header.objCounter; i++) {
            GeomObject obj = new GeomObject();
            // 读取OBJ3D对象，共2236字节
            obj.loadData(in);
            obj.loadFile(in);
            addObject(obj);
        }
        linkObject();

        /**
         * <pre>
         * }
         * </pre>
         */

        in.close();
    }

    public boolean addObject(GeomObject obj) {
        // 限制物体的数量，最多128个
        if (objCount < 128) {
            objArray[objCount] = obj;
            objCount++;

            // 统计动画帧数
            int frame = 0;
            if (obj.TmRotCnt > 0 && obj.rotArray != null)
                frame = obj.rotArray[obj.TmRotCnt - 1].frame;
            if (obj.TmPosCnt > 0 && obj.posArray != null)
                frame = obj.posArray[obj.TmPosCnt - 1].frame;
            if (MaxFrame < frame)
                MaxFrame = frame;

            // 农扁 承捞 汲沥
            if (SizeWidth < obj.maxX)
                SizeWidth = obj.maxX;
            if (SizeWidth < obj.maxZ)
                SizeWidth = obj.maxZ;
            if (SizeHeight < obj.maxY)
                SizeHeight = obj.maxY;

            // 官款爹 胶其绢 蔼
            if (Bound < obj.Bound) {
                Bound = obj.Bound;
                dBound = obj.dBound;
            }

            return true;
        }

        return false;
    }

    /**
     * 计算物体之间的父子关系。
     */
    public void linkObject() {
        for (int i = 0; i < objCount; i++) {
            if (objArray[i].NodeParent != null) {
                for (int k = 0; k < objCount; k++) {
                    if (objArray[i].NodeParent.equals(objArray[k].NodeName)) {
                        objArray[i].pParent = objArray[k];
                        break;
                    }
                }
            } else {
                logger.debug("j = 0");
            }
        }

        int NodeCnt = 0;

        // 清零
        for (int i = 0; i < 128; i++) {
            TmSort[i] = 0;
        }

        // 首先记录根节点
        for (int i = 0; i < objCount; i++) {
            if (objArray[i].pParent == null)
                TmSort[NodeCnt++] = (byte) i;
        }

        // 何葛俊 崔妨乐绰 磊侥阑 茫酒 鉴辑措肺 历厘
        for (int j = 0; j < objCount; j++) {
            for (int i = 0; i < objCount; i++) {
                if (objArray[i].pParent != null && objArray[TmSort[j]] == objArray[i].pParent) {
                    TmSort[NodeCnt++] = (byte) i;
                }
            }
        }
    }

    /**
     * 根据结点名称，查询Obj3D对象。
     * 
     * @param name
     * @return
     */
    public GeomObject getObj(String name) {
        for (int i = 0; i < objCount; i++) {
            if (objArray[i].NodeName.equals(name)) {
                return objArray[i];
            }
        }
        return null;
    }
    
    /**
     * 根据结点名称，查询对象的索引号。
     * 
     * @param name
     * @return
     */
    public int getObjIndex(String name) {
        for (int i = 0; i < objCount; i++) {
            if (objArray[i].NodeName.equals(name)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 设置骨骼
     * 
     * @param skeleton
     */
    public void setSkeleton(PAT3D skeleton) {
        this.skeleton = skeleton;
        for (int i = 0; i < objCount; i++) {
            GeomObject obj = objArray[i];
            obj.setSkeleton(skeleton);
        }
    }
}