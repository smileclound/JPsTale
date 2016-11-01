package org.pstale.app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

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
import org.pstale.utils.ImageDecoder;

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
				new DataState(data.serverRoot,
						data.allMonster, data.allNpc, data.allItem,
						data.clientRoot, data.fields),
				//new CursorState(),
				new HudState(),
				new LoaderAppState(),
				new MusicAppState(),
				new AmbientAppState(),
				new FieldgateAppState(),
				new WarpgateAppState(),
				new MonsterAppState(),
				new NpcAppState() };

		AppStateManager stateManager = getStateManager();
		stateManager.attachAll(states);// 添加所需的AppStates
		stateManager.detach(this);// 移除LoadingAppState
	}

	/**
	 * 服务端的路径
	 */
	static String SERVER_ROOT;
	static String FIELD_DIR = "Field";
	static String MONSTER_DIR = "Monster";
	static String OPENITEM_DIR = "OpenItem";
	static String NPC_DIR = "NPC";

	/**
	 * 客户端的路径
	 */
	static String CLIENT_ROOT;

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
		
		public String clientRoot = "/";
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

			check();

			// 属性文件的路径
			data.serverRoot = SERVER_ROOT;
			data.clientRoot = CLIENT_ROOT;

			value = 2;
			message = "Config..";
			
			// 解析服务端数据
			if (SERVER_ROOT != null) {
				// 所有怪物数据
				MonsterInitilize mi = new MonsterInitilize();
				mi.setFolder(SERVER_ROOT + "/" + MONSTER_DIR);
				mi.init();
				data.allMonster = mi.getList();
				
				value = 8;
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
				
				value = 11;
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
				
				value = 20;
				message = "Item:" + data.allItem.size();
				log.info(message);
				
			} else {
				value = 20;
				message = "No server found";
				log.info(message);
			}

			if (CLIENT_ROOT != null) {
				// 解码小地图
				imageDecode(CLIENT_ROOT + "/field/map");
				// 解码标题
				imageDecode(CLIENT_ROOT + "/field/title");
				// 解码鼠标图片
				imageDecode(CLIENT_ROOT + "/image/Sinimage/Cursor");
			}

			Field[] fields = new FieldLoader().load();
			data.fields = fields;
			value = 25;
			message = "解析地区";

			/**
			 * 检查所有图片是否已经是解密的，否则要使用ImageDecoder对其进行解密。由于有些地图的图片放在同一个文件夹内，
			 * 已经解码过的文件夹就不需要再次解码，因此用一个AraryList来保存这些已经解码过的文件夹，避免重复解码。
			 */
			ArrayList<String> folders = new ArrayList<String>();

			SpawnLoader sppLoader = new SpawnLoader();
			MonsterLoader spmLoader = new MonsterLoader();
			NpcLoader spcLoader = new NpcLoader();
			int size = fields.length;
			for (int i = 0; i < size; i++) {
				Field field = fields[i];
				// 计算进度
				value = 25 + 75 * (i + 1) / (size + 1);
				message = "解析:" + field.getTitle();

				// 检查模型文件是否存在
				String model = field.getName();

				/**
				 * 图片解码太耗时了，考虑暂时不在Loading时处理这个问题了。
				 * 
				 */
				// 使用ImageDecoder对地图的图片其进行解密。
				int idx = model.lastIndexOf("/");
				String folder = model.substring(0, idx);
				if (CLIENT_ROOT != null) {
					String path = CLIENT_ROOT + "/" + folder;
					if (!folders.contains(path)) {

						imageDecode(path);
						folders.add(path);
					}
				}

				// 尝试加载服务端文件
				if (SERVER_ROOT != null) {
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

						if (npcs != null && npcs.size() > 0) {
							for (int n = 0; n < npcs.size(); n++) {
								NPC npc = npcs.get(n);
								if (npc == null)
									continue;

								String npcModel = npc.getModel().replaceAll("\\\\", "/");
								// 使用ImageDecoder对地图的图片其进行解密。
								idx = npcModel.lastIndexOf("/");
								folder = npcModel.substring(0, idx);
								if (CLIENT_ROOT != null) {
									String path = CLIENT_ROOT + "/" + folder;
									if (!folders.contains(path)) {
										imageDecode(path);
										folders.add(path);
									}
								}
							}
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

			value = 100;
			message = "完成";

			return data;
		}

		/**
		 * 读取配置
		 */
		String profilepath = "config.properties";
		Properties props = new Properties();

		/**
		 * 将指定文件夹下所有bmp和tga图片解码。
		 * 
		 * @param folder
		 */
		void imageDecode(String folder) {
			File dir = new File(folder);

			// 判断文件夹是否存在
			if (dir.exists() && dir.isDirectory()) {

				// 遍历bmp文件
				File[] files = dir.listFiles(new FilenameFilter() {
					public boolean accept(File dir, String name) {
						String str = name.toLowerCase();
						return str.endsWith(".bmp");
					}
				});// 读取文件列表
				for (int i = 0; i < files.length; i++) {
					File file = files[i];
					if (file.isFile()) {

						try {
							byte[] buffer = new byte[16];
							RandomAccessFile raf = new RandomAccessFile(file, "rw");
							raf.seek(0);
							raf.readFully(buffer);

							// 解码
							if (buffer[0] == 0x41 && buffer[1] == 0x38) {
								log.info("Decode " + file.getAbsolutePath());
								ImageDecoder.convertBMP(buffer, true);
								raf.seek(0);
								raf.write(buffer);
							}

							raf.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}

				// 遍历tga文件
				files = dir.listFiles(new FilenameFilter() {
					public boolean accept(File dir, String name) {
						String str = name.toLowerCase();
						return str.endsWith(".tga");
					}
				});// 读取文件列表
				for (int i = 0; i < files.length; i++) {
					File file = files[i];
					if (file.isFile()) {
						try {
							byte[] buffer = new byte[18];
							RandomAccessFile raf = new RandomAccessFile(file, "rw");
							raf.seek(0);
							raf.readFully(buffer);

							// 解码
							if (buffer[0] == 0x47 && buffer[1] == 0x38) {
								log.info("Decode " + file.getAbsolutePath());
								ImageDecoder.convertTGA(buffer, true);
								raf.seek(0);
								raf.write(buffer);
							}

							raf.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
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

		// 文件选择器
		private JFileChooser chooser;

		private JFileChooser getChooser() {
			if (chooser == null) {
				chooser = new JFileChooser();
				chooser.setDialogType(JFileChooser.OPEN_DIALOG);
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooser.setDialogTitle("请选择文件夹");
			}
			return chooser;
		}

		/**
		 * 打开文件选择框，选择一个目录。若用户没有选择，返回null。
		 * 
		 * @return
		 */
		private File getFile() {
			if (getChooser().showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
				return null;
			}
			return chooser.getSelectedFile();
		}

		/**
		 * 检查配置文件是否正确存在。
		 * 
		 * @return
		 */
		public boolean check() {
			/**
			 * 首先检查配置文件是否存在，若不存在则自动生成一个配置文件，并提醒用户设置路径。
			 */

			if (!exist()) {
				return false;
			} else {
				// 检查服务端文件夹
				if (!checkServerRoot(SERVER_ROOT)) {
					int result = JOptionPane
							.showConfirmDialog(
									null,
									"尚未找到服务端文件夹的路径，无法读取服务器配置数据，请先指定服务端文件夹的位置。\n点击\"确定\"开始选择，点击\"取消\"则以后再设置。",
									"确认服务器路径", JOptionPane.OK_CANCEL_OPTION,
									JOptionPane.WARNING_MESSAGE);

					if (result == JOptionPane.OK_OPTION) {
						setupServer();
					}
				}

				// 检查客户端文件夹
				if (!checkClientRoot(CLIENT_ROOT)) {
					int result = JOptionPane
							.showConfirmDialog(
									null,
									"尚未找到客户端文件夹的路径，无法读取地图、音乐等数据，请先指定客户端文件夹的位置。\n点击\"确定\"开始选择，点击\"取消\"则以后再设置。",
									"确认客户端路径", JOptionPane.OK_CANCEL_OPTION,
									JOptionPane.WARNING_MESSAGE);

					if (result == JOptionPane.OK_OPTION) {
						setupClient();
					}
				}
			}

			return true;
		}

		/**
		 * 
		 * @return
		 */
		private boolean exist() {
			// 属性文件的路径
			try {
				props.load(new FileInputStream(profilepath));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			SERVER_ROOT = props.getProperty("SERVER_ROOT");
			if (SERVER_ROOT == null) {
				// 设置默认值
				SERVER_ROOT = "../Assets/assets/server";
				writeProperties("SERVER_ROOT", SERVER_ROOT);
			}

			CLIENT_ROOT = props.getProperty("CLIENT_ROOT");
			if (CLIENT_ROOT == null) {
				CLIENT_ROOT = "/";
				writeProperties("CLIENT_ROOT", CLIENT_ROOT);
			}

			return false;
		}

		private void setupServer() {
			boolean confirmServer = false;
			while (!checkServerRoot(SERVER_ROOT)) {

				File file = getFile();
				if (file != null) {
					SERVER_ROOT = file.getAbsolutePath();
					SERVER_ROOT = SERVER_ROOT.replaceAll("\\\\", "/");
				} else {

					int rt = JOptionPane.showConfirmDialog(null,
							"尚未找到服务端文件夹的路径，取消此次操作吗?", "确认服务器路径",
							JOptionPane.OK_CANCEL_OPTION,
							JOptionPane.WARNING_MESSAGE);
					if (rt == JOptionPane.OK_OPTION) {
						confirmServer = true;
						break;
					}
				}
			}
			if (!confirmServer) {
				writeProperties("SERVER_ROOT", SERVER_ROOT);
			} else {
				SERVER_ROOT = null;
			}
		}

		private void setupClient() {
			boolean confirmClient = false;
			while (!checkClientRoot(CLIENT_ROOT)) {

				File file = getFile();
				if (file != null) {
					CLIENT_ROOT = file.getAbsolutePath();
					CLIENT_ROOT = CLIENT_ROOT.replaceAll("\\\\", "/");
				} else {
					int rt = JOptionPane.showConfirmDialog(null,
							"尚未找到客户端的路径，取消此次操作吗?", "确认客户端路径",
							JOptionPane.OK_CANCEL_OPTION,
							JOptionPane.WARNING_MESSAGE);
					if (rt == JOptionPane.OK_OPTION) {
						confirmClient = true;
						break;
					}
				}
			}
			if (!confirmClient) {
				writeProperties("CLIENT_ROOT", CLIENT_ROOT);
			} else {
				CLIENT_ROOT = null;
			}
		}

		/**
		 * 更新（或插入）一对properties信息(主键及其键值) 如果该主键已经存在，更新该主键的值； 如果该主键不存在，则插件一对键值。
		 * 
		 * @param keyname
		 *            键名
		 * @param keyvalue
		 *            键值
		 */
		void writeProperties(String keyname, String keyvalue) {
			try {
				OutputStream fos = new FileOutputStream(profilepath);
				props.setProperty(keyname, keyvalue);
				props.store(fos, "Update '" + keyname + "' value");
			} catch (IOException e) {
				log.warn("属性文件更新错误", e);
			}
		}

		/**
		 * 检查客户端文件夹是否都存在
		 * 
		 * @param folder
		 * @return
		 */
		boolean checkClientRoot(String folder) {
			File file = new File(folder);
			if (!file.exists()) {
				return false;
			}

			String[] folders = { "effect", "field", "char", "wav", "sky" };
			for (String subFolder : folders) {
				if (!new File(folder + "/" + subFolder).exists()) {
					return false;
				}
			}

			return true;
		}

		/**
		 * 检查服务端文件夹是否都存在
		 * 
		 * @param folder
		 * @return
		 */
		boolean checkServerRoot(String folder) {
			File file = new File(folder);
			if (!file.exists()) {
				return false;
			}

			String[] folders = { "Field", "Monster", "NPC", "OpenItem" };
			for (String subFolder : folders) {
				if (!new File(folder + "/" + subFolder).exists()) {
					return false;
				}
			}
			return true;
		}
	}

}
