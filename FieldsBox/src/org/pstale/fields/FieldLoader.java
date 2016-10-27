package org.pstale.fields;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.pstale.fields.Field;
import org.pstale.fields.FieldGate;
import org.pstale.fields.WarpGate;

import com.jme3.collision.CollisionResults;
import com.jme3.math.Ray;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;

public class FieldLoader {
	
	
	public static final float SCALE = 0.15f;
	private HashMap<Integer, Field> fields = new HashMap<Integer, Field>();
	private Field currentMap = null;

	public Field getCurrentMap() {
		return currentMap;
	}
	public void initFields() {
		// 读取FIELD.txt文件中的数据
		// 该文件为JSON格式，记录了所有地图的数据
		String content = "";
		try {
			Scanner input = new Scanner(new FileInputStream("data/FIELD.txt"));
			StringBuffer sb = new StringBuffer();
			while(input.hasNext()) {
				sb.append(input.nextLine()).append("\n");
			}
			input.close();
			content = sb.toString();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// 解析JSON文件，逐一读取地图数据。
		JSONArray array = new JSONArray(content);
		int max = array.length();
		
		// 解析地图本身的数据信息
		for (int i=0; i<max; i++) {
			try {
				// 解析地图数据
				JSONObject obj = array.getJSONObject(i);
				Field map = new Field();
				
				// 地图模型文件名
				String name = obj.getString("Name");
				int n = name.lastIndexOf(".");
				map.setName(name.substring(0, n) + ".smd");
				
				// 地图的id
				int id = obj.getInt("id");
				map.setId(id);
				// 地图的id
				int BackMusicCode = obj.getInt("BackMusicCode");
				map.setBackMusicCode(BackMusicCode);
				
				// 地图的中心坐标(x, 0, z);
				JSONArray CenterPos = obj.getJSONArray("CenterPos");
				float x = (float)CenterPos.getDouble(0);
				float y = (float)CenterPos.getDouble(1);
				Vector2f pos = new Vector2f(-y, -x);
				map.setCenter(pos);

				// 地图出生点
				try {
					JSONArray StartPoints = obj.getJSONArray("StartPoint");
					for(int j=0; j<StartPoints.length(); j++) {
						JSONObject position = StartPoints.getJSONObject(j);
						JSONArray subPos = position.getJSONArray("Position");
						float v1 = (float)subPos.getDouble(0);
						float v2 = (float)subPos.getDouble(1);
						map.addStartPoint(-v2, -v1);
					}
				} catch(JSONException e) {
				}
				
				fields.put(id, map);
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		// 在地图之间添加关联信息
		for (int i=0; i<max; i++) {
			try {
				// 解析地图数据
				JSONObject obj = array.getJSONObject(i);
				// 地图的id
				int id = obj.getInt("id");
				Field map = fields.get(id);
				
				// 地图的门
				try {
					JSONArray gates = obj.getJSONArray("FieldGate");
					for(int j=0; j<gates.length(); j++) {
						JSONObject gate = gates.getJSONObject(j);
						JSONArray pos = gate.getJSONArray("Position");
						float v1 = (float)pos.getDouble(0);
						float v2 = (float)pos.getDouble(1);
						float v3 = (float)pos.getDouble(2);
						int toField = gate.getInt("ToField");
						map.addGate(fields.get(toField), -v3, v2, -v1);
					}
				} catch(JSONException e) {
				}
				
				// 地图传送门
				try {
					JSONArray warpGates = obj.getJSONArray("WarpGate");
					for(int j=0; j<warpGates.length(); j++) {
						JSONObject warpGate = warpGates.getJSONObject(j);
						
						Vector3f location = new Vector3f();
						// position
						{
							JSONArray subPos = warpGate.getJSONArray("Position");
							float v1 = (float)subPos.getDouble(0);// z
							float v2 = (float)subPos.getDouble(1);// y
							float v3 = (float)subPos.getDouble(2);// x
							location.set(-v3, v2, -v1);
						}
						
						// size
						int size = warpGate.getInt("size");
						// height
						int height = warpGate.getInt("height");
						
						WarpGate warp = new WarpGate(location, size, height);
						
						// OutGate
						{
							// {"Position":[119112, 510, 26028], "ToField":25}
							JSONArray gates = obj.getJSONArray("OutGate");
							for(int k=0; k<gates.length(); k++) {
								JSONObject gate = gates.getJSONObject(k);
								JSONArray pos = gate.getJSONArray("Position");
								float v1 = (float)pos.getDouble(0);
								float v2 = (float)pos.getDouble(1);
								float v3 = (float)pos.getDouble(2);
								Vector3f vec3 = new Vector3f(-v3, v2, -v1);
								
								int toField = gate.getInt("ToField");
								FieldGate fieldGate = new FieldGate(vec3, fields.get(toField));
								warp.getOutGate().add(fieldGate);
							}
						}
						
						// LimitLevel
						int LimitLevel = warpGate.getInt("LimitLevel");
						warp.setLimitLevel(LimitLevel);
						// SpecialEffect
						int SpecialEffect = warpGate.getInt("SpecialEffect");
						warp.setSpecialEffect(SpecialEffect);
						
						map.getWarpGate().add(warp);
						
					}
				} catch(JSONException e) {
				}
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	LinkedList<Field> queue = new LinkedList<Field>();
	LinkedList<Node> nodeQueue = new LinkedList<Node>();
	public void getField(int id) {
		
		Field field = fields.get(id);
		if (field != null) {
			if (queue.contains(field)) {
			} else {
				try {
					// 加载新地图
					field.getName();

					queue.addLast(field);
					
					currentMap = field;
					// 播放背景音乐
					field.getBackMusicCode();
					
					// 删除旧地图数据
					if (queue.size() > 2) {

						Field f = queue.getFirst();
						queue.removeFirst();
						System.out.println("移除地图" + f.getId());

					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else {
		}
	}
	
	float height = 1800 * SCALE;
	float bodyHeight = 60 * SCALE;

	public Vector3f getCenterPos() {
		if (currentMap == null)
			return Vector3f.ZERO;
		
		Vector2f vec2 = currentMap.getStartPoint(
				currentMap.getCenter().x, 
				currentMap.getCenter().y);
		
		Vector2f startPos = new Vector2f(vec2);
		startPos.multLocal(SCALE);

		// 发出一条射线，找到地图上对应的点。
		Vector3f ori = new Vector3f(startPos.x, height, startPos.y);
		Ray ray = new Ray(ori, Vector3f.UNIT_Y.negate());
		
		CollisionResults results = new CollisionResults();
		// this.terrain.collideWith(ray, results);
		
		Vector3f pos = new Vector3f();
		if(results.size() > 0) {
			pos = results.getClosestCollision().getContactPoint();
			pos.setY(pos.y+bodyHeight);
		} else {
			pos = new Vector3f(startPos.x, height, startPos.y);
		}
		
		System.out.println(pos);
		return pos;
	}

	public Field getNext() {
		if (currentMap == null)
			return null;

		int id = currentMap.getId();
		id++;
		if (id > fields.size())
			id = 1;
		getField(id);

		return currentMap;
	}

	public Field getFront() {
		if (currentMap == null)
			return null;
		int id = currentMap.getId();
		id--;
		if (id < 1)
			id = fields.size();
		getField(id);

		return currentMap;
	}

	public int fieldSize() {
		return fields.size();
	}

	public void addField(int id, Field map) {
		fields.put(id, map);

	}
}
