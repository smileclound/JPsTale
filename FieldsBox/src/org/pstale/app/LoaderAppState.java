package org.pstale.app;

import java.util.ArrayList;
import java.util.List;

import org.pstale.fields.Field;
import org.pstale.fields.Music;
import org.pstale.fields.StageObject;
import org.pstale.fields.StartPoint;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.asset.AssetManager;
import com.jme3.asset.maxase.FileLocator;
import com.jme3.bounding.BoundingVolume;
import com.jme3.collision.CollisionResults;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Ray;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.SceneGraphVisitor;
import com.jme3.scene.Spatial;
import com.jme3.ui.Picture;

/**
 * 加载模型
 * 
 * @author yanmaoyuan
 * 
 */
public class LoaderAppState extends SubAppState {

	private SimpleApplication app;

	private InputManager inputManager;
	private AssetManager assetManager;

	private boolean wireframe = false;
	
	@Override
	public void initialize(Application app) {
		this.inputManager = app.getInputManager();
		this.assetManager = app.getAssetManager();
		this.app = (SimpleApplication) app;
		
		initKeys();
	}
	
	public void setRootpath(String path) {
		assetManager.registerLocator(path, FileLocator.class);
	}
	
	public void wireframe() {
		
		wireframe = !wireframe;
		
		rootNode.depthFirstTraversal(new SceneGraphVisitor() {
			@Override
			public void visit(Spatial spatial) {
				if (spatial instanceof Geometry) {
					Geometry geom = (Geometry) spatial;
					geom.getMaterial().getAdditionalRenderState().setWireframe(wireframe);
				}
			}
		});
	}
	
	List<Field> fields = new ArrayList<Field>();
	/**
	 * 加载场景模型
	 * @param field
	 */
	public void loadModel(Field field) {
		if (field == null)
			return;
		
		// 移除旧的模型
		//rootNode.detachAllChildren();
		guiNode.detachAllChildren();
		
		try {
			Picture map = new Picture("map");
			map.setImage(assetManager, field.getNameMap(), true);
			map.setWidth(200);
			map.setHeight(200);
			guiNode.attachChild(map);
			
			Picture title = new Picture("title");
			title.setImage(assetManager, field.getNameTitle(), true);
			title.setWidth(200);
			title.setHeight(30);
			title.setLocalTranslation(0, 200, 0);
			guiNode.attachChild(title);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (fields.contains(field)) {
			
			// 播放背景音乐
			playBGM(field);
			
			// 移动摄像机
			moveCamera(field);
			
			return;
		} else {
			fields.add(field);
		}

	
		wireframe = false;
		
		/**
		 * 地图主模型
		 */
		Spatial mainModel = loadModel(field.getName());
		if (mainModel != null) {
			// 加载成功
			mainModel.scale(scale);
			rootNode.attachChild(mainModel);
			
			// 移动摄像机
	
			if (field.getCenter().length() == 0) {
				Vector3f center = calcBoundingCenter(mainModel);
				field.getCenter().set(center.x / scale, center.z / scale);
			} else {
				// 移动摄像机
				moveCamera(field);
			}
			
		} else {
			System.out.println("加载地图模型失败");
		}
		
		/**
		 * 地图的其他舞台物体
		 */
		List<StageObject> objs = field.getStageObject();
		if (objs.size() > 0) {
			for(int i=0; i<objs.size(); i++) {
				Spatial model = loadModel("Field/" + objs.get(i).getName());
				if (model != null) {
					// 加载成功
					model.scale(scale);
					rootNode.attachChild(mainModel);
				}
			}
		}
		
		/**
		 * 背景音乐
		 */
		playBGM(field);
		
		/**
		 * 环境音效
		 */
		AmbientAppState ambientAppState = getStateManager().getState(AmbientAppState.class);
		if (ambientAppState != null) {
			ambientAppState.load(field.getAmbientPos());
		}
		
		/**
		 * 门户
		 */
		FieldgateAppState fieldgateAppState = getStateManager().getState(FieldgateAppState.class);
		if (fieldgateAppState != null) {
			fieldgateAppState.load(field.getFieldGate());
		}
		
		/**
		 * 传送门
		 */
		WarpgateAppState warpgateAppState = getStateManager().getState(WarpgateAppState.class);
		if (warpgateAppState != null) {
			warpgateAppState.load(field.getWarpGate());
		}
		
		/**
		 * 刷怪点
		 */
		MonsterAppState monsterAppState = getStateManager().getState(MonsterAppState.class);
		if (monsterAppState != null) {
			StartPoint[] spawns = field.getSpawnPoints();
			if (spawns != null) {
				// 刷怪点的坐标只有X/Z坐标，而且有些刷怪点是无效的，需要重新计算。
				List<Vector3f> monsters = new ArrayList<Vector3f>();
				for(int i=0; i<spawns.length; i++) {
					StartPoint point = spawns[i];
					if (point != null && point.state != 0) {
						Vector3f pos = new Vector3f(point.x, 0, point.z);
						pos.multLocal(scale);
						pos.y = 1000;
						pos = getLocationOnField(pos);
						pos.y += 1;
						monsters.add(pos);
					}
				}
				
				monsterAppState.load(monsters.toArray(new Vector3f[]{}));
			}
		}
		
		/**
		 * NPC
		 */
		NpcAppState npcAppState = getStateManager().getState(NpcAppState.class);
		if (npcAppState != null) {
			npcAppState.load(field.getNpcs());
		}

	}
	
	/**
	 * 播放背景音乐
	 * @param field
	 */
	private void playBGM(Field field) {
		MusicAppState musicAppState = getStateManager().getState(MusicAppState.class);
		if (musicAppState != null) {
			int bgm = field.getBackMusicCode();
			Music BGM = Music.get(bgm);
			musicAppState.setSong(BGM.getFilename());
		}
	}
	/**
	 * 移动摄像机到地图的中心
	 * @param field
	 */
	private void moveCamera(Field field) {
		// 移动摄像机
		Vector2f center2f = field.getCenter();
		Vector3f center = new Vector3f(center2f.x, 0, center2f.y).multLocal(scale);
		center.y = 1000;
		center = getLocationOnField(center);
		center.y += 2;
		app.getCamera().setLocation(center);
	}
	
	/**
	 * 当地图没有自定义中心点的时候，就计算一个出来。
	 * @param model
	 * @return
	 */
	private Vector3f calcBoundingCenter(Spatial model) {
		Vector3f center = new Vector3f();
		BoundingVolume bounding = model.getWorldBound();
		if (bounding != null) {
			center = bounding.getCenter();
			center.y = 1000;
			center = getLocationOnField(center);
			center.y += 2;
		}
		
		app.getCamera().setLocation(center);
		return center;
	}
	
	/**
	 * 创建一个简单的材质
	 * @param color
	 * @return
	 */
	protected Material getMaterial(ColorRGBA color) {
		Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
		mat.setColor("Color", color);
		return mat;
	}
	
	/**
	 * 从地下发出一根射线与模型相交，取第一个焦点。
	 * @param po
	 * @return
	 */
	Vector3f down = new Vector3f(0, -1, 0);
	private Vector3f getLocationOnField(Vector3f pos) {
		Ray ray = new Ray(pos, down);
		CollisionResults results = new CollisionResults();
		rootNode.collideWith(ray, results);
		
		if (results.size() > 0) {
			Vector3f realPos = results.getClosestCollision().getContactPoint();
			return pos.set(realPos);
		} else {
			return pos;
		}
	}
	
	boolean isVisiavle = true;

	void initKeys() {
		inputManager.addMapping("Wireframe", new KeyTrigger(KeyInput.KEY_F3));
		inputManager.addListener(new ActionListener() {
			@Override
			public void onAction(String name, boolean isPressed, float tpf) {
				if (isPressed) {
					if (name.equals("Wireframe")) {
						wireframe();
					}
				}
			}
		}, "Wireframe");
	}

	public void play(String name) {
		
	}

}
