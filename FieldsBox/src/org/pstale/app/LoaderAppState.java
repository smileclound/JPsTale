package org.pstale.app;

import java.util.ArrayList;
import java.util.List;

import org.pstale.fields.Field;
import org.pstale.fields.Music;
import org.pstale.fields.NPC;
import org.pstale.fields.StageObject;
import org.pstale.fields.StartPoint;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.asset.AssetManager;
import com.jme3.asset.maxase.AseKey;
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
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.SceneGraphVisitor;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;

/**
 * 加载模型
 * 
 * @author yanmaoyuan
 * 
 */
public class LoaderAppState extends BaseAppState {

	private SimpleApplication app;
	private Node rootNode;

	private InputManager inputManager;
	private AssetManager assetManager;

	float scale = 0.05f;
	Node fieldNode = null;
	Spatial model = null;
	boolean wireframe = false;
	
	public LoaderAppState() {
		rootNode = new Node("LoaderRootNode");
		fieldNode = new Node("Field");
		rootNode.attachChild(fieldNode);
	}

	@Override
	public void initialize(Application app) {
			this.app = (SimpleApplication) app;
		this.inputManager = app.getInputManager();
		this.assetManager = app.getAssetManager();

		initKeys();
	}
	
	@Override
	protected void cleanup(Application app) {
	}

	@Override
	protected void onEnable() {
		this.app.getRootNode().attachChild(rootNode);
	}

	@Override
	protected void onDisable() {
		rootNode.removeFromParent();
	}
	
	public void setRootpath(String path) {
		assetManager.registerLocator(path, FileLocator.class);
	}
	
	public void wireframe() {
		if (model == null)
			return;
		
		wireframe = !wireframe;
		
		model.depthFirstTraversal(new SceneGraphVisitor() {
			@Override
			public void visit(Spatial spatial) {
				if (spatial instanceof Geometry) {
					Geometry geom = (Geometry) spatial;
					geom.getMaterial().getAdditionalRenderState().setWireframe(wireframe);
				}
			}
		});
	}
	
	/**
	 * 加载模型
	 * @param name
	 * @return
	 */
	public Spatial loadModel(final String name) {
		Spatial model = null;
		
		String path = name;
		if (path != null) {
			path = path.replaceAll("\\\\", "/");
		}
		
		int idx = path.lastIndexOf(".");
		String smd = path.substring(0, idx) + ".smd";
		try {
			// 首先尝试加载smd模型，如果存在smd文件，就不再查找ase文件。
			AseKey key = new AseKey(smd);
			model = (Spatial) assetManager.loadAsset(key);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("加载"+smd+"失败，尝试加载ase模型");
			try {
				// 如果加载失败，则尝试加载ase文件。
				AseKey key = new AseKey(path);
				model = (Spatial) assetManager.loadAsset(key);
			} catch (Exception e2) {
				e2.printStackTrace();
				System.out.println("加载" + path + "失败");
			}
		}
		
		return model;
	}
	
	/**
	 * 加载场景模型
	 * @param field
	 */
	public void loadModel(Field field) {
		if (field == null)
			return;

		// 移除旧的模型
		fieldNode.detachAllChildren();
		
		wireframe = false;
		
		/**
		 * 地图主模型
		 */
		Spatial mainModel = loadModel(field.getName());
		if (mainModel != null) {
			// 加载成功
			mainModel.scale(scale);
			fieldNode.attachChild(mainModel);
			
			// 移动摄像机
			BoundingVolume bounding = mainModel.getWorldBound();
			if (bounding != null) {
				Vector3f center = bounding.getCenter();
				Vector3f location = center.add(new Vector3f(100, 30, 0).mult(scale));
				app.getCamera().setLocation(location);
				app.getCamera().lookAt(center, new Vector3f(0, 1, 0));
				System.out.println("Cam Loc:" + location);
			}
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
					fieldNode.attachChild(mainModel);
				}
			}
		}
		
		/**
		 * 刷怪点
		 */
		StartPoint[] spawns = field.getSpawnPoints();
		if (spawns != null) {
			for(int i=0; i<spawns.length; i++) {
				StartPoint point = spawns[i];
				if (point != null && point.state != 0) {
					Vector3f pos = new Vector3f(point.x, 0, point.z);
					pos.multLocal(scale);
					
					pos = getLocationOnField(pos);
					pos.y += 1;
					createSpawnPoint(pos);
				}
			}
		}
		
		/**
		 * NPC
		 */
		ArrayList<NPC> npcs = field.getNpcs();
		if (npcs != null && npcs.size() > 0) {
			for(int i=0; i<npcs.size(); i++) {
				NPC npc = npcs.get(i);
				Vector3f pos = new Vector3f(npc.getPosition());
				pos.multLocal(scale);
				
				pos = getLocationOnField(pos);
				pos.y += 1;
				
				System.out.println("NPC Loc:" + pos);
				createNPC(pos);
			}
		}
		
		
		/**
		 * 播放背景音乐
		 */
		int bgm = field.getBackMusicCode();
		Music BGM = Music.get(bgm);
		BGM.getFilename();
		getStateManager().getState(MusicAppState.class).setSong(BGM.getFilename());
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
	private Vector3f getLocationOnField(Vector3f pos) {
		Ray ray = new Ray(pos, Vector3f.UNIT_Y);
		CollisionResults results = new CollisionResults();
		fieldNode.collideWith(ray, results);
		
		if (results.size() > 0) {
			return results.getClosestCollision().getContactPoint();
		} else {
			return pos;
		}
	}
	/**
	 * 创建一个刷怪点标记
	 * @param pos
	 */
	private void createSpawnPoint(Vector3f pos) {
		Box box = new Box(1, 1, 1);
		Geometry geom = new Geometry("", box);
		geom.setLocalTranslation(pos);
		geom.setMaterial(getMaterial(ColorRGBA.Red));
		fieldNode.attachChild(geom);
	}
	
	/**
	 * 创建一个NPC标记
	 * @param pos
	 */
	private void createNPC(Vector3f pos) {
		Box box = new Box(1, 1, 1);
		Geometry geom = new Geometry("", box);
		geom.setLocalTranslation(pos);
		geom.setMaterial(getMaterial(ColorRGBA.Green));
		fieldNode.attachChild(geom);
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
