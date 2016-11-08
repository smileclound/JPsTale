package org.pstale.app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import net.jmecn.asset.ItemInitilize;
import net.jmecn.asset.MonsterInitilize;
import net.jmecn.asset.chars.CharMonsterInfo;
import net.jmecn.asset.item.ItemInfo;

import org.apache.log4j.Logger;
import org.pstale.fields.Field;
import org.pstale.fields.NPC;
import org.pstale.fields.RespawnList;
import org.pstale.fields.StartPoint;
import org.pstale.loader.FieldLoader;
import org.pstale.loader.MonsterLoader;
import org.pstale.loader.NpcLoader;
import org.pstale.loader.SpawnLoader;

import com.jme3.app.Application;
import com.jme3.app.state.AppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.simsilica.lemur.ProgressBar;

/**
 * 进度条
 * 
 * @author yanmaoyuan
 * 
 */
public class LoadingAppState extends SubAppState {

	static Logger log = Logger.getLogger(LoadingAppState.class);

	protected ProgressBar progressBar;
	private Future<Data> future;
	private ScheduledThreadPoolExecutor excutor;
	private LoadingTask task;

	@Override
	protected void initialize(Application app) {

		Camera cam = app.getCamera();
		float h = cam.getHeight();
		float w = cam.getWidth();

		float scale = h / 720f;

		float width = 600f;
		float height = 30f;
		Vector3f size = new Vector3f(width, height, 1f);
		size.multLocal(scale);
		progressBar = new ProgressBar();
		progressBar.setPreferredSize(size);

		guiNode.attachChild(progressBar);

		float x = w * 0.5f - width * 0.5f * scale;
		float y = h * 0.5f - height * 0.5f * scale;
		progressBar.setLocalTranslation(x, y, 0);
	}

	@Override
	protected void cleanup(Application app) {
	}

	@Override
	protected void onEnable() {
		super.onEnable();
		excutor = new ScheduledThreadPoolExecutor(1);
		task = new LoadingTask();
		future = null;
	}

	@Override
	protected void onDisable() {
		super.onDisable();
		excutor.shutdown();
		task = null;
		future = null;
	}

	private float time = 0f;// 计时，看看加载到底花了多少时间。

	public void update(float tpf) {
		time += tpf;

		if (task != null && future == null) {
			future = excutor.submit(task);
			time = 0;
			log.info("开始载入数据");
		}

		if (future != null && !future.isDone()) {
			progressBar.setProgressPercent(task.value / 100f);
			progressBar.setMessage("进度: " + task.value + "% ... " + task.message);
		}

		if (future != null && future.isDone()) {
			try {
				Data data = future.get();
				initLoader(data);
			} catch (Exception e) {
				log.error("FIELD.txt数据加载失败", e);
				getApplication().stop();
			}

			task = null;
			future = null;

			log.info("载入用时" + time + "s");
		}
	}

	/**
	 * 初始化管理器所需的各种AppState
	 * 
	 * @param data
	 */
	private void initLoader(Data data) {
		AppState[] states = { new AxisAppState(),
				new DataState(data.serverRoot, data.allMonster, data.allNpc, data.allItem, data.fields),
				//new CursorState(),
				new HudState(),
				new LoaderAppState(),
				new CollisionState(data.fields.length),
				new MusicAppState(),
				new AmbientAppState(),
				new FieldgateAppState(),
				new WarpgateAppState(),
				new MonsterAppState(),
				new NpcAppState(),
				new LightState(),
				new PickingAppState()};

		AppStateManager stateManager = getStateManager();
		stateManager.attachAll(states);// 添加所需的AppStates
		stateManager.detach(this);// 移除LoadingAppState
	}

	/**
	 * 服务端的路径
	 */
	static boolean CHECK_SERVER = false;
	static String SERVER_ROOT;
	static String FIELD_DIR = "GameServer/Field";
	static String MONSTER_DIR = "GameServer/Monster";
	static String OPENITEM_DIR = "GameServer/OpenItem";
	static String NPC_DIR = "GameServer/NPC";


	/**
	 * 客户端的路径
	 */

	/**
	 * 这个类用于临时存储线程中解析的数据。在线程执行结束后，会从Future中获得最终的结果。
	 * @author yanmaoyuan
	 *
	 */
	private class Data {
		public String serverRoot = "";
		public List<CharMonsterInfo> allMonster;
		public List<CharMonsterInfo> allNpc;
		public List<ItemInfo> allItem;
		
		public Field[] fields;
	}

	class LoadingTask implements Callable<Data> {

		/**
		 * 进度：百分比
		 */
		public int value = 0;
		public String message = "";

		@Override
		public Data call() throws Exception {

			Data data = new Data();

			// 属性文件的路径
			data.serverRoot = SERVER_ROOT;

			value = 2;
			message = "Config..";
			
			// 解析服务端数据
			if (CHECK_SERVER && SERVER_ROOT != null) {
				// 所有怪物数据
				MonsterInitilize mi = new MonsterInitilize();
				mi.setFolder(SERVER_ROOT + "/" + MONSTER_DIR);
				mi.init();
				data.allMonster = mi.getList();
				
				value = 23;
				message = "Monster:" + data.allMonster.size();
				log.info(message);
				for(int i=0; i<data.allMonster.size(); i++) {
					CharMonsterInfo monster = data.allMonster.get(i);
					log.info("EnName:" + monster.enName + " LocalName:" + monster.szName +  " File:" + monster.File);
				}
				
				// 所有NPC数据
				mi.setFolder(SERVER_ROOT + "/" + NPC_DIR);
				mi.init();
				data.allNpc = mi.getList();
				
				value = 47;
				message = "NPC:" + data.allNpc.size();
				log.info(message);
				for(int i=0; i<data.allNpc.size(); i++) {
					CharMonsterInfo npc = data.allNpc.get(i);
					log.info("EnName:" + npc.enName + " LocalName:" + npc.szName + " File:" + npc.File);
				}
				
				// 所有装备数据
				ItemInitilize ii = new ItemInitilize();
				ii.setFolder(SERVER_ROOT + "/" + OPENITEM_DIR);
				ii.init();
				data.allItem = ii.getList();
				
				value = 78;
				message = "Item:" + data.allItem.size();
				log.info(message);
				
			} else {
				value = 78;
				message = "No server found";
				log.info(message);
			}

			Field[] fields = new FieldLoader().load();
			data.fields = fields;
			value = 78;
			message = "解析地区";

			SpawnLoader sppLoader = new SpawnLoader();
			MonsterLoader spmLoader = new MonsterLoader();
			NpcLoader spcLoader = new NpcLoader();
			int size = fields.length;
			for (int i = 0; i < size; i++) {
				Field field = fields[i];
				// 计算进度
				value = 78 + 22 * (i + 1) / (size + 1);
				message = "解析:" + field.getTitle();

				// 检查模型文件是否存在
				String model = field.getName();

				// 尝试加载服务端文件
				if (CHECK_SERVER && SERVER_ROOT != null) {
					int index = model.lastIndexOf("/") + 1;
					String name = model.substring(index);
					String spp = SERVER_ROOT + "/" + FIELD_DIR + "/" + name + ".spp";
					String spm = SERVER_ROOT + "/" + FIELD_DIR + "/" + name + ".spm";
					String spc = SERVER_ROOT + "/" + FIELD_DIR + "/" + name + ".spc";

					try {
						// 怪物刷新点
						StartPoint[] points = sppLoader.load(spp);
						field.setSpawnPoints(points);
					} catch (IOException e) {
						e.printStackTrace();
					}

					try {
						// 刷怪种类
						RespawnList monsters = spmLoader.load(spm);
						field.setRespawnList(monsters);
					} catch (IOException e) {
						e.printStackTrace();
					}

					try {
						// NPC信息
						ArrayList<NPC> npcs = spcLoader.load(spc);
						field.setNpcs(npcs);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

			value = 100;
			message = "完成";

			return data;
		}

		void calcRespawn(Field... lists) {
			int sum = 0;
			for (int i = 0; i < lists.length; i++) {
				Field f = lists[i];
				RespawnList rl = f.getRespawnList();
				if (rl != null) {
					sum += rl.LimitMax;

					log.info(f.getTitle() + " limit=" + rl.LimitMax);
				}
			}

			log.info("sum = " + sum);
		}

	}

}
