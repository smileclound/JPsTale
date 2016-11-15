package org.pstale.app;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import org.apache.log4j.Logger;
import org.pstale.asset.struct.chars.CharMonsterInfo;
import org.pstale.asset.struct.chars.TRNAS_PLAYERINFO;
import org.pstale.fields.Field;
import org.pstale.fields.Music;
import org.pstale.fields.StageObject;
import org.pstale.fields.StartPoint;

import com.jme3.animation.AnimControl;
import com.jme3.animation.Skeleton;
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.asset.AssetManager;
import com.jme3.collision.CollisionResults;
import com.jme3.input.controls.ActionListener;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Matrix4f;
import com.jme3.math.Ray;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Mesh;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.VertexBuffer.Type;
import com.jme3.scene.debug.SkeletonDebugger;
import com.jme3.texture.Texture;

/**
 * 加载模型
 * 
 * @author yanmaoyuan
 * 
 */
public class LoaderAppState extends SubAppState {

	static Logger log = Logger.getLogger(LoaderAppState.class);
	
	private SimpleApplication app;

	private AssetManager assetManager;

	private Future<Void> future;
	private ScheduledThreadPoolExecutor excutor;
	private Callable<Void> task;
	
	// 刷怪点标记
	private Spatial flag;
	private Spatial loadFlag;
	
	@Override
	public void initialize(Application app) {
		this.assetManager = app.getAssetManager();
		this.app = (SimpleApplication) app;
		
		excutor = new ScheduledThreadPoolExecutor(1);
		future = null;
		task = null;
		
		flag = ModelFactory.loadFlag();
		loadFlag = ModelFactory.getLoadingFlag();
		
		float width = app.getCamera().getWidth();
		float height = app.getCamera().getHeight();
		
		loadFlag.setLocalTranslation(width/2 - 40, height/2 - 10, 0);
	}
	
	@Override
	protected void cleanup(Application app) {
		excutor.shutdown();
		future = null;
		task = null;
	}


	List<Field> fields = new ArrayList<Field>();
	Vector3f center;
	
	Texture mapRes = null;
	Texture titleRes = null;
	Spatial mainModel = null;
	
	public void update(float tpf) {
		if (task != null && future == null) {
			future = excutor.submit(task);
			
			guiNode.attachChild(loadFlag);
		}
		
		if (task != null && future != null && future.isDone()) {
			future = null;
			task = null;
			field = null;
			
			loadFlag.removeFromParent();
		}
	}
	/**
	 * 加载场景模型
	 * @param field
	 */
	public void loadModel(Field field) {
		if (task != null) {
			return;
		}
		
		if (field == null) {
			return;
		}
		
		/**
		 * 判断缓存中是否已经有这个地图了。
		 */
		if (fields.contains(field)) {
			
			// 播放背景音乐
			playBGM(field);
			
			// 根据地图的出生点，设置当前的摄像机坐标
			Vector2f center2f = field.getCenter();
			center = new Vector3f(center2f.x, 0, center2f.y).multLocal(scale);
			center.y = 1000;
			setPhysicLocation(center);
			
			// 加载小地图
			setMiniMap(field);
			
		} else {
			this.field = field;
			task = loadTask;
		}
		
	}
	
	Field field = null;
	Callable<Void> loadTask = new Callable<Void>() {

		@Override
		public Void call() throws Exception {
			if (field == null)
				return null;
			
			/**
			 * 地图主模型
			 */
			final Spatial mainModel = ModelFactory.loadStage3D(field.getName());
			final Mesh mesh = ModelFactory.loadStage3DMesh(field.getName());
			
			if (mainModel == null) {
				log.debug("加载地图模型失败");
				return null;
			}
			
			// 加载成功
			mainModel.scale(scale);
			app.enqueue(new Runnable() {
				public void run() {
					rootNode.attachChild(mainModel);
				}
			});
			
			// 将网格缩小
			FloatBuffer fb = (FloatBuffer)mesh.getBuffer(Type.Position).getData();
			for(int i=0; i<fb.limit(); i++) {
				fb.put(i, fb.get(i) * scale);
			}
			mesh.updateBound();
			
			// 计算地图的中心点
			if (field.getCenter().length() == 0) {
				center = mesh.getBound().getCenter();
				field.getCenter().set(center.x, center.z);
			} else {
				Vector2f center2f = field.getCenter();
				center = new Vector3f(center2f.x, 0, center2f.y).multLocal(scale);
				center.y = 1000;
			}
			
			/**
			 * 地图的碰撞网格
			 */
			final CollisionState collisionState = getStateManager().getState(CollisionState.class);
			if (collisionState != null) {
				app.enqueue(new Runnable() {
					public void run() {
						collisionState.addMesh(mesh);
						collisionState.setPlayerLocation(center);
					}
				});
			}
			
			/**
			 * 地图的其他舞台物体
			 */
			//setStageObject(field);
			
			/**
			 * 小地图
			 */
			setMiniMap(field);
			
			/**
			 * 背景音乐
			 */
			playBGM(field);
			
			/**
			 * 环境音效
			 */
			setupAmbient(field);
			
			/**
			 * 门户
			 */
			final FieldgateAppState fieldgateAppState = getStateManager().getState(FieldgateAppState.class);
			if (fieldgateAppState != null) {
				app.enqueue(new Runnable() {
					public void run() {
						fieldgateAppState.load(field.getFieldGate());
					}
				});
			}
			
			/**
			 * 传送门
			 */
			final WarpgateAppState warpgateAppState = getStateManager().getState(WarpgateAppState.class);
			if (warpgateAppState != null) {
				app.enqueue(new Runnable() {
					public void run() {
						warpgateAppState.load(field.getWarpGate());
					}
				});
			}
			
			if (LoadingAppState.CHECK_SERVER) {
				/**
				 * 刷怪点
				 */
				setupSpawnPoints(field, mesh);
				
				/**
				 * NPC
				 */
				setupNpc(field);
			}
			
			// 设置为空
			fields.add(field);
			return null;
		}
		
	};
	
	private void setPhysicLocation(final Vector3f center) {
		final CollisionState collisionState = getStateManager().getState(CollisionState.class);
		if (collisionState != null) {
			app.enqueue(new Runnable() {
				public void run() {
					collisionState.setPlayerLocation(center);
				}
			});
		}
	}
	
	/**
	 * 加载舞台物体
	 * @param field
	 */
	protected void setStageObject(final Field field) {
		List<StageObject> objs = field.getStageObject();
		if (objs.size() > 0) {
			for(int i=0; i<objs.size(); i++) {
				final Spatial model;
				try {
					model = ModelFactory.loadStageObj("Field/" + objs.get(i).getName(), objs.get(i).isBipAnimation());
					// 加载成功
					model.scale(scale);
					app.enqueue(new Runnable() {
						public void run() {
							rootNode.attachChild(model);
						}
					});
				} catch (Exception e) {
					log.error("加载舞台物体失败", e);
				}
			}
		}
	}

	private void setupAmbient(final Field field) {
		/**
		 * 环境音效
		 */
		final AmbientAppState ambientAppState = getStateManager().getState(AmbientAppState.class);
		if (ambientAppState != null) {
			app.enqueue(new Runnable() {
				public void run() {
					ambientAppState.load(field.getAmbientPos());
				}
			});
		}
	}
	
	private void setupSpawnPoints(final Field field, Mesh mesh) {
		final MonsterAppState monsterAppState = getStateManager().getState(MonsterAppState.class);
		if (monsterAppState != null) {
			ArrayList<StartPoint> spawns = ModelFactory.loadSpp(field.getName());
			
			if (spawns != null) {
				// 刷怪点的坐标只有X/Z坐标，而且有些刷怪点是无效的，需要重新计算。
				int len = spawns.size();
				for(int i=0; i<len; i++) {
					StartPoint point = spawns.get(i);
					
					Vector3f pos = new Vector3f(point.x, 0, point.z);
					pos.multLocal(scale);
					pos.y = 1000;
					pos = getLocationOnField(pos, mesh);
					
					try {
						final Spatial model = flag.clone();
						model.scale(scale);
						model.setLocalTranslation(pos);
						app.enqueue(new Runnable() {
							public void run() {
								rootNode.attachChild(model);
							}
						});
					} catch (Exception e) {
						log.error("加载模型失败", e);
					}
				}
			}
		}
	}

	private void setupNpc(final Field field) {
		final NpcAppState npcAppState = getStateManager().getState(NpcAppState.class);
		if (npcAppState != null) {
			
			ArrayList<TRNAS_PLAYERINFO> npcs = ModelFactory.loadSpc(field.getName());
			if (npcs == null) {
				return;
			}
			
			int len = npcs.size();
			for(int i=0; i<len; i++) {
				TRNAS_PLAYERINFO npc = npcs.get(i);
				Vector3f pos = new Vector3f(npc.x, npc.y, npc.z);
				pos.multLocal(scale);

				/**
				 * 创建一个NPC模型
				 * @param pos
				 */
				// 首先尝试直接读取NPC模型
				final Node model = (Node)ModelFactory.loadNPC(npc.charInfo.szModelName);
				
				// Debug skeleton
				final AnimControl ac = model.getControl(AnimControl.class);
				if (ac != null) {
					final Skeleton skel = ac.getSkeleton();
					SkeletonDebugger skeletonDebug = new SkeletonDebugger("skeleton", skel);
					final Material mat = new Material(getApplication().getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
					mat.setColor("Color", ColorRGBA.Green);
					mat.getAdditionalRenderState().setDepthTest(false);
					skeletonDebug.setMaterial(mat);
					model.attachChild(skeletonDebug);
					
					// ac.createChannel().setAnim("Anim");
				}
				
				model.scale(scale);
				model.setLocalTranslation(pos);
				getApplication().enqueue(new Runnable() {
					public void run() {
						rootNode.attachChild(model);
					}
				});
				
				CharMonsterInfo cmNPC = ModelFactory.loadNpcScript(npc.charInfo.szModelName2);
				if (cmNPC != null) {
					log.debug("Found:" + cmNPC.szName);
				} else {
					log.debug("NPC not found:" + npc.charInfo.szModelName2);
				}
			}
			
		}
	}

	/**
	 * 设置小地图
	 * @param mapRes
	 * @param titleRes
	 */
	private void setMiniMap(final Field field) {
		final HudState hud = getStateManager().getState(HudState.class);
		if (hud != null) {
			final Texture mapRes;
			final Texture titleRes;
			try {
				
				String map = field.getNameMap();
				if (map != null && map.length() > 0) {
					mapRes = assetManager.loadTexture(field.getNameMap());
				} else {
					mapRes = null;
				}
				
				String title = field.getNameTitle();
				if (title != null && title.length() > 0) {
					titleRes = assetManager.loadTexture(field.getNameTitle());
				} else {
					titleRes = null;
				}
				
				app.enqueue(new Runnable() {
					public void run() {
						hud.setMiniMap(titleRes, mapRes);
					}
				});
			} catch (Exception e) {
				log.error("读取小地图失败", e);
			}
		}
	}
	/**
	 * 播放背景音乐
	 * @param field
	 */
	private void playBGM(final Field field) {
		final MusicAppState musicAppState = getStateManager().getState(MusicAppState.class);
		if (musicAppState != null) {
			int bgm = field.getBackMusicCode();
			final Music BGM = Music.get(bgm);
			app.enqueue(new Runnable() {
				public void run() {
					musicAppState.setSong(BGM.getFilename());
				}
			});
		}
	}
	
	/**
	 * 从地下发出一根射线与模型相交，取第一个焦点。
	 * @param po
	 * @return
	 */
	Vector3f down = new Vector3f(0, -1, 0);
	private Vector3f getLocationOnField(Vector3f pos, Mesh mesh) {
		Ray ray = new Ray(pos, down);
		CollisionResults results = new CollisionResults();
        mesh.collideWith(ray, new Matrix4f(), mesh.getBound(), results);
		if (results.size() > 0) {
			Vector3f realPos = results.getClosestCollision().getContactPoint();
			return pos.set(realPos);
		} else {
			return pos;
		}
	}
	
	ActionListener listener = new ActionListener() {
		@Override
		public void onAction(String name, boolean isPressed, float tpf) {
			
		}
	};
	
}
