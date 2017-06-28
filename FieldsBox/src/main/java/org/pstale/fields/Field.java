package org.pstale.fields;

import java.util.ArrayList;
import java.util.List;

import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;

public class Field {

	private int id;// 地区编号
	private String title;// 地区名称
	private String code;// 地区的编码
	private String name; // 地区模型文件名称
	private String nameMap; // 地区地图图像文件
	private String nameTitle; // 地区名称映像文件
	private int state; // 地区属性
	private int[] backImageCode = { 0, 0, 0 }; // 基本背景天空号码
	private int backMusicCode; // 背景音乐代码
	private int fieldEvent; // 地区的活动

	private List<FieldGate> fieldGate; // 门（地区连接地区）

	protected int warpGateActiveNum; // 被激活的传送门编号
	private List<WarpGate> warpGate; // 传送门
	Vector3f posWarpOut; // 传送门的出口

	private List<AmbientPos> ambientPos; // 环境音效

	private int limitLevel; // 限制等级
	private int fieldSight; // 地区视野

	private List<StageObject> stgObj;// 舞台辅助物体

	private Vector2f center; // 地区中央

	private int fieldCode; // 地区的代码编号
	private int serverCode;

	private List<Vector2f> startPoint; // 地区出生点

	public Field() {
		stgObj = new ArrayList<StageObject>();
		ambientPos = new ArrayList<AmbientPos>();
		fieldGate = new ArrayList<FieldGate>();
		warpGate = new ArrayList<WarpGate>();
		posWarpOut = new Vector3f(0f, 0f, 0f);
		startPoint = new ArrayList<Vector2f>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	// 设置名称
	public void setName(String name) {
		this.name = name;
	}

	public String getNameMap() {
		return nameMap;
	}

	public void setNameMap(String nameMap) {
		this.nameMap = nameMap;
	}

	public String getNameTitle() {
		return nameTitle;
	}

	public void setNameTitle(String nameTitle) {
		this.nameTitle = nameTitle;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int[] getBackImageCode() {
		return backImageCode;
	}

	public void setBackImageCode(int[] backImageCode) {
		this.backImageCode = backImageCode;
	}

	public int getBackMusicCode() {
		return backMusicCode;
	}

	public void setBackMusicCode(int backMusicCode) {
		this.backMusicCode = backMusicCode;
	}

	public int getFieldEvent() {
		return fieldEvent;
	}

	public void setFieldEvent(int fieldEvent) {
		this.fieldEvent = fieldEvent;
	}

	// StageObject
	public List<StageObject> getStageObject() {
		return this.stgObj;
	}
	// 添加舞台背景物体
	public void addStageObject(StageObject stgObj) {
		this.stgObj.add(stgObj);
	}
	// 添加舞台背景物体
	public void addStageObject(String stgObjName, int bipAnimation) {
		this.stgObj.add(new StageObject(stgObjName, bipAnimation == 1));
	}

	// FieldGate
	public FieldGate[] getFieldGate() {
		return fieldGate.toArray(new FieldGate[]{});
	}
	
	/**
	 * 添加地区门户
	 * @param gate
	 */
	public void addGate(FieldGate gate) {
		fieldGate.add(gate);
	}
	
	// WarpGate
	public WarpGate[] getWarpGate() {
		return warpGate.toArray(new WarpGate[]{});
	}
	
	/**
	 * 添加传送门
	 * @param warp
	 */
	public void addWarp(WarpGate warp) {
		warpGate.add(warp);
	}
	
	public int CheckWarpGate(int x, int y, int z) {
		// TODO 检查传送门
		return -1;
	}
	public Vector3f getPosWarpOut() {
		return posWarpOut;
	}
	public void setPosWarpOut(Vector3f posWarpOut) {
		this.posWarpOut = posWarpOut;
	}

	// AmbientPos
	public AmbientPos[] getAmbientPos() {
		return ambientPos.toArray(new AmbientPos[]{});
	}
	
	// 添加环境音效
	public void addAmbientPos(AmbientPos ambient) {
		this.ambientPos.add(ambient);
	}
	// 添加环境音效
	public void addAmbientPos(int x, int y, int z, int round, int ambCode) {
		this.ambientPos.add(new AmbientPos(new Vector3f(x * 256, y * 256,
				z * 256), round, ambCode));
	}

	public int getLimitLevel() {
		return limitLevel;
	}

	public void setLimitLevel(int limitLevel) {
		this.limitLevel = limitLevel;
	}

	public int getFieldSight() {
		return fieldSight;
	}

	public void setFieldSight(int fieldSight) {
		this.fieldSight = fieldSight;
	}

	public Vector2f getCenter() {
		return this.center;
	}
	// 设置地区中心坐标
	public void setCenter(Vector2f center) {
		this.center = center;
	}
	// 设置地区中心坐标
	public void setCenter(float x, float z) {
		center = new Vector2f(x, z);
	}

	public int getFieldCode() {
		return fieldCode;
	}

	public void setFieldCode(int fieldCode) {
		this.fieldCode = fieldCode;
	}

	public int getServerCode() {
		return serverCode;
	}

	public void setServerCode(int serverCode) {
		this.serverCode = serverCode;
	}

	// StartPoint
	public List<Vector2f> getStartPoint() {
		return startPoint;
	}
	
	public void addStartPoint(Vector2f point) {
		startPoint.add(point);
	}

	/**
	 * 得到最近的出生点坐标
	 * @param x
	 * @param z
	 * @return
	 */
	public Vector2f getStartPoint(float x, float z) {
		Vector2f pos = new Vector2f(x, z);
		
		if (startPoint.size() == 0) {
			return center;
		} else {
			int p = 0;
			float min = Float.MAX_VALUE;
			for(int i=0; i<startPoint.size(); i++) {
				Vector2f vec2 = startPoint.get(i);
				
				float dist = vec2.distance(pos);
				if (dist < min) {
					min = dist;
					p = i;
				}
			}
			
			return startPoint.get(p).clone();
		}
		
	}

	// TODO 检查角色是否在出生点坐标上
	public void CheckStartPoint(int x, int z) {

	}

	@Override
	public String toString() {
		return "[" + id + "-" + this.title + "]";
	}
}
