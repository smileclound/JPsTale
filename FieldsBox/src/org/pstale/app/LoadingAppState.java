package org.pstale.app;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import org.apache.log4j.Logger;
import org.pstale.asset.struct.chars.CharMonsterInfo;
import org.pstale.asset.struct.item.ItemInfo;

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
				new DataState(data.serverRoot, data.allMonster, data.allNpc, data.allItem),
				//new CursorState(),
				new HudState(),
				new LoaderAppState(),
				new CollisionState(),
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
				File folder = new File(SERVER_ROOT + "/" + MONSTER_DIR);
				String[] files = folder.list();
				int len = files.length;
				data.allMonster = new ArrayList<CharMonsterInfo>(len);
				for(int i=0; i<len; i++) {
					String name = files[i];
					CharMonsterInfo m = ModelFactory.loadMonsterScript(name);
					if (m != null) {
						data.allMonster.add(m);
						System.out.print(m.szName + ",");
					}
					
					value = 30 * (i+1) / len;
					message = "Monster:" + (i+1) + "/" + len;
				}
				System.out.println();
				
				value = 30;
				message = "Monster:" + data.allMonster.size();
				log.info(message);
				
				// 所有装备数据
				folder = new File(SERVER_ROOT + "/" + OPENITEM_DIR);
				files = folder.list();
				len = files.length;
				data.allItem = new ArrayList<ItemInfo>(len);
				for(int i=0; i<len; i++) {
					String name = files[i];
					ItemInfo item = ModelFactory.loadItemScript(name);
					if (item != null) {
						data.allItem.add(item);
						System.out.print(item.localeName + ",");
					}
					
					value = 30 + 50 * (i+1) / len;
					message = "Item:" + (i+1) + "/" + len;
				}
				System.out.println();
				
				value = 80;
				message = "Item:" + data.allItem.size();
				log.info(message);
				
				// 所有NPC数据
				folder = new File(SERVER_ROOT + "/" + NPC_DIR);
				files = folder.list();
				len = files.length;
				for(int i=0; i<len; i++) {
					String name = files[i];
					CharMonsterInfo npc = ModelFactory.loadNpcScript(name);
					if (npc != null) {
						System.out.print(npc.szName + ",");
					}
					
					value = 80 + 20 * (i+1) / len;
					message = "NPC:" + (i+1) + "/" + len;
				}
				System.out.println();
				
				value = 100;
				message = "NPC:" + len;
				log.info(message);
				
			} else {
				value = 100;
				message = "No server found";
				log.info(message);
			}

			value = 100;
			message = "完成";

			return data;
		}

	}

}
