package org.pstale.asset.struct;

import java.io.IOException;

import com.jme3.util.LittleEndien;

/**
 * size = 1228
 */
public class PAT3D extends Flyweight {
	// DWORD Head;
	public OBJ3D[] obj3d = new OBJ3D[128];
	byte[] TmSort = new byte[128];

	public PAT3D TmParent;

	public MATERIAL_GROUP smMaterialGroup;// 材质组

	int MaxFrame;
	int Frame;

	int SizeWidth, SizeHeight; // 臭捞 承捞 狼 弥措摹

	public int nObj3d;
	// LPDIRECT3DTEXTURE2 *hD3DTexture;

	POINT3D Posi;
	POINT3D Angle;
	POINT3D CameraPosi;

	int dBound;
	int Bound;

	FRAME_POS[] TmFrame = new FRAME_POS[OBJ_FRAME_SEARCH_MAX];
	int TmFrameCnt;

	int TmLastFrame;
	POINT3D TmLastAngle;

	public PAT3D() {
		nObj3d = 0;
		// hD3DTexture = 0;
		TmParent = null;

		MaxFrame = 0;
		Frame = 0;

		SizeWidth = 0;
		SizeHeight = 0;

		Bound = 0;
		dBound = 0;

		TmFrameCnt = 0;

		TmLastFrame = -1;

		TmLastAngle = new POINT3D();
		TmLastAngle.x = -1;
		TmLastAngle.y = -1;
		TmLastAngle.z = -1;

		for (int i = 0; i < 128; i++) {
			TmSort[i] = (byte) i;
		}

		smMaterialGroup = null;
	}
	
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

		nObj3d = in.readInt();
		in.readInt();// LPDIRECT3DTEXTURE2 *hD3DTexture;

		Posi = new POINT3D();
		Posi.loadData(in);
		Angle = new POINT3D();
		Angle.loadData(in);
		CameraPosi = new POINT3D();
		CameraPosi.loadData(in);

		dBound = in.readInt();
		Bound = in.readInt();

		for (int i = 0; i < OBJ_FRAME_SEARCH_MAX; i++) {
			TmFrame[i] = new FRAME_POS();
			TmFrame[i].loadData(in);
		}
		TmFrameCnt = in.readInt();

		TmLastFrame = in.readInt();
		TmLastAngle = new POINT3D();
		TmLastAngle.loadData(in);

	}

	public void loadFile(LittleEndien in, String NodeName, PAT3D BipPat) throws IOException {

		FILE_HEADER header = new FILE_HEADER();
		header.loadData(in);

		// 读取Obj3D物体信息
		FILE_OBJINFO[] FileObjInfo = new FILE_OBJINFO[header.objCounter];
		for (int i = 0; i < header.objCounter; i++) {
			FileObjInfo[i] = new FILE_OBJINFO();
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
			smMaterialGroup = new MATERIAL_GROUP();
			smMaterialGroup.loadData(in);
		}

		if (NodeName != null) {
			log.debug("NodeName != null && NodeName == " + NodeName);
			// 加载指定名称的3D物体
			for (int i = 0; i < header.objCounter; i++) {
				if (NodeName.equals(FileObjInfo[i].NodeName)) {
					// 跳过i个OBJ3D，直接读取其中一个。
					int offset = i * 2236;
					in.skip(offset);
					
					OBJ3D obj = new OBJ3D();
					obj.loadData(in);
					obj.loadFile(in, BipPat);
					addObject(obj);
					break;
				}
			}
		} else {
			// 读取全部3D对象
			for (int i = 0; i < header.objCounter; i++) {
				OBJ3D obj = new OBJ3D();
				// 读取OBJ3D对象，共2236字节
				obj.loadData(in);
				obj.loadFile(in, BipPat);
				addObject(obj);
			}
			linkObject();
		}

		TmParent = BipPat;
		
		in.close();
	}

	boolean addObject(OBJ3D obj) {
		// 限制物体的数量，最多128个
		if (nObj3d < 128) {
			obj3d[nObj3d] = obj;
			nObj3d++;

			// 统计动画帧数
			int frame = 0;
			if (obj.TmRotCnt > 0 && obj.TmRot != null)
				frame = obj.TmRot[obj.TmRotCnt - 1].frame;
			if (obj.TmPosCnt > 0 && obj.TmPos != null)
				frame = obj.TmPos[obj.TmPosCnt - 1].frame;
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
	void linkObject() {
		for (int i = 0; i < nObj3d; i++) {
			if (obj3d[i].NodeParent != null) {
				for (int k = 0; k < nObj3d; k++) {
					if (obj3d[i].NodeParent.equals(obj3d[k].NodeName)) {
						obj3d[i].pParent = obj3d[k];
						break;
					}
				}
			} else {
				log.debug("j = 0");
			}
		}

		int NodeCnt = 0;

		// 清零
		for (int i = 0; i < 128; i++) {
			TmSort[i] = 0;
		}

		// 首先记录根节点
		for (int i = 0; i < nObj3d; i++) {
			if (obj3d[i].pParent == null)
				TmSort[NodeCnt++] = (byte) i;
		}

		// 何葛俊 崔妨乐绰 磊侥阑 茫酒 鉴辑措肺 历厘
		for (int j = 0; j < nObj3d; j++) {
			for (int i = 0; i < nObj3d; i++) {
				if (obj3d[i].pParent != null
						&& obj3d[TmSort[j]] == obj3d[i].pParent) {
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
	OBJ3D getObjectFromName(String name) {
		for (int i = 0; i < nObj3d; i++) {
			if (obj3d[i].NodeName.equals(name)) {
				return obj3d[i];
			}
		}
		return null;
	}

}