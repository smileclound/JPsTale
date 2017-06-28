package org.pstale.loader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.pstale.fields.AmbientPos;
import org.pstale.fields.Field;
import org.pstale.fields.FieldGate;
import org.pstale.fields.StageObject;
import org.pstale.fields.WarpGate;

import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;

public class FieldLoader {
	
	
	public Field[] load() {
		// 读取FIELD.txt文件中的数据
		// 该文件为JSON格式，记录了所有地图的数据
		String content = "";
		try {
			Scanner input = new Scanner(new InputStreamReader(new FileInputStream("assets/FIELD.txt"), "utf-8"));
			StringBuffer sb = new StringBuffer();
			while(input.hasNext()) {
				sb.append(input.nextLine()).append("\n");
			}
			input.close();
			content = sb.toString();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}

		// 解析JSON文件，逐一读取地图数据。
		JSONArray array = new JSONArray(content);
		int max = array.length();
		Field[] fieldAry = new Field[max];
		
		// 解析地图本身的数据信息
		for (int i=0; i<max; i++) {
			// 解析地图数据
			try {
				JSONObject obj = array.getJSONObject(i);
				fieldAry[i] = loadField(obj);
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return fieldAry;
	}
	
	/**
	 * 逐个解析Field对象
	 * @param obj
	 */
	protected Field loadField(JSONObject obj) {
		Field field = null;
		
		/**
		 * 解析地区的基本信息。若这些信息不完整，则此地区无效。
		 */
		try {
			/**
			 * 地图的id
			 */
			int id = obj.getInt("id");
			/**
			 * 地区的标题
			 */
			String title = obj.getString("Title");
			/**
			 * 地区的编码
			 */
			String code = obj.getString("Code");
			/**
			 * 地图模型文件名
			 */
			String name = obj.getString("Name");
			/**
			 * 小地图的文件名
			 */
			String nameMap = obj.getString("NameMap");
			/**
			 * 小地图标题的文件名
			 */
			String nameTitle = obj.getString("NameTitle");
			/**
			 * 地区的类型
			 */
			int state = obj.getInt("State");
			/**
			 * 地图的背景图片
			 */
			JSONArray backImageCode = obj.getJSONArray("BackImageCode");
			int[] backImage = { backImageCode.getInt(0), backImageCode.getInt(1), backImageCode.getInt(2)};
			/**
			 * 背景音乐编号
			 */
			int backMusicCode = obj.getInt("BackMusicCode");
			/**
			 * 服务端编码
			 */
			int serverCode = obj.getInt("ServerCode");
			/**
			 * 限制进入的等级
			 */
			int limitLevel = obj.getInt("LimitLevel");
			
			/**
			 * 地图的中心坐标(x, 0, z);
			 */
			JSONArray CenterPos = obj.getJSONArray("CenterPos");
			float z = -CenterPos.getInt(0);
			float x = -CenterPos.getInt(1);
			Vector2f pos = new Vector2f(x, z);
			
			/**
			 * 创建Field对象
			 */
			field = new Field();
			field.setId(id);
			field.setTitle(title);
			field.setCode(code);
			field.setName(name);
			field.setNameMap(nameMap);
			field.setNameTitle(nameTitle);
			field.setState(state);
			field.setBackImageCode(backImage);
			field.setBackMusicCode(backMusicCode);
			field.setServerCode(serverCode);
			field.setLimitLevel(limitLevel);
			field.setCenter(pos);
			
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
		/**
		 * 舞台物体
		 */
		try {
			JSONArray stageObjs = obj.getJSONArray("StageObject");
			for(int j=0; j<stageObjs.length(); j++) {
				JSONObject stageObj = stageObjs.getJSONObject(j);
				StageObject stgObj = loadStageObject(stageObj);
				field.addStageObject(stgObj);
			}
		} catch (JSONException e) {
		}
		
		/**
		 * 环境音效
		 */
		try {
			JSONArray ambients = obj.getJSONArray("Ambient");
			for(int j=0; j<ambients.length(); j++) {
				JSONObject ambient = ambients.getJSONObject(j);
				AmbientPos ambientPos = loadAmbient(ambient);
				field.addAmbientPos(ambientPos);
			}
		} catch (JSONException e) {
		}
		
		/**
		 * 解析地图中玩家复活点信息
		 */
		try {
			JSONArray StartPoints = obj.getJSONArray("StartPoint");
			for(int j=0; j<StartPoints.length(); j++) {
				JSONObject startPoint = StartPoints.getJSONObject(j);
				Vector2f point = getVec2(startPoint);
				field.addStartPoint(point);
			}
		} catch (JSONException e) {
		}
		
		/**
		 * 地图的门户
		 */
		try {
			JSONArray gates = obj.getJSONArray("FieldGate");
			for(int j=0; j<gates.length(); j++) {
				JSONObject fieldGate = gates.getJSONObject(j);
				FieldGate gate = loadFieldGate(fieldGate);
				if (gate != null) {
					field.addGate(gate);
				}
			}
		} catch(Exception e) {
		}
			
		/**
		 * 地区中的传送点
		 */
		try {
			JSONArray warpGates = obj.getJSONArray("WarpGate");
			for(int j=0; j<warpGates.length(); j++) {
				JSONObject warpGate = warpGates.getJSONObject(j);
				WarpGate warp = loadWarpGate(warpGate);
				if (warp != null) {
					field.addWarp(warp);
				}
			}
		} catch(Exception e) {
		}
		
		return field;
	}
	/**
	 * 解析场景物体数据。StageObject的数据结构如下：<pre>
	 * {"StgObjName":"forest/3ani-01.ASE", "BipAnimation":0}
	 * @param stageObject
	 * @return
	 */
	private StageObject loadStageObject(JSONObject stageObject) {
		String stgObjName = stageObject.getString("StgObjName");
		int bipAnim = stageObject.getInt("BipAnimation");
		
		StageObject obj = new StageObject(stgObjName, bipAnim == 1);
		return obj;
	}
	/**
	 * 解析环境音效数据。Ambient的数据结构如下：<pre>
	 * {"Position":[-13819, 226, -8950], "round":100, "AmbientNum":15}</pre>
	 * @param ambient
	 * @return
	 */
	private AmbientPos loadAmbient(JSONObject ambient) {
		Vector3f pos = getVec3(ambient);
		int round = ambient.getInt("round");
		int num = ambient.getInt("AmbientNum");
		
		AmbientPos ambientPos = new AmbientPos(pos, round, num);
		return ambientPos;
	}
	/**
	 * 解析FieldGate数据。FieldGate数据结构如下：<pre>
	 * {"Position":[-8508, 0, -10576], "ToField":2}</pre>
	 * @param obj
	 */
	protected FieldGate loadFieldGate(JSONObject gate) {
		FieldGate fieldGate = null;
		try {
			Vector3f position = getVec3(gate);
			int toField = gate.getInt("ToField");
			
			fieldGate = new FieldGate(toField, position);
		} catch(JSONException e) {
		}
		
		return fieldGate;
	}
	
	/**
	 * 解析传送门信息
	 */
	protected WarpGate loadWarpGate(JSONObject warpGate) {
		WarpGate warp = null;
		try {
			// position
			Vector3f position = getVec3(warpGate);
			
			// size
			int size = warpGate.getInt("size");
			
			// height
			int height = warpGate.getInt("height");
			
			// LimitLevel
			int LimitLevel = warpGate.getInt("LimitLevel");
			
			// SpecialEffect
			int SpecialEffect = warpGate.getInt("SpecialEffect");
			
			warp = new WarpGate(position, size, height, LimitLevel, SpecialEffect);
			
			// OutGate
			JSONArray gates = warpGate.getJSONArray("OutGate");
			for(int k=0; k<gates.length(); k++) {
				JSONObject gate = gates.getJSONObject(k);
				FieldGate fieldGate = loadFieldGate(gate);
				warp.getOutGate().add(fieldGate);
			}
			
		} catch(JSONException e) {
		}
		
		return warp;
	}
	
	/**
	 * 读取一个3维坐标。
	 * 注意：源文件中存储的是Direct3D坐标系，现在要读成OpenGL坐标系。
	 * @param obj
	 * @return
	 */
	private Vector3f getVec3(JSONObject obj) {
		JSONArray pos = obj.getJSONArray("Position");
		float z = -pos.getInt(0);
		float y = pos.getInt(1);
		float x = -pos.getInt(2);
		
		Vector3f vec3 = new Vector3f(x, y, z);
		return vec3;
	}
	
	/**
	 * 读取一个2维坐标。
	 * 注意：源文件中存储的是Direct3D坐标系，现在要读成OpenGL坐标系。
	 * @param obj
	 * @return
	 */
	private Vector2f getVec2(JSONObject obj) {
		JSONArray pos = obj.getJSONArray("Position");
		float z = -pos.getInt(0);
		float x = -pos.getInt(1);
		return new Vector2f(x, z);
	}
	
}
