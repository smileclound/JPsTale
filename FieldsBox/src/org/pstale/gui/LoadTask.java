package org.pstale.gui;

import java.awt.Canvas;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.Callable;

import org.ho.yaml.Yaml;
import org.pstale.app.FieldApp;
import org.pstale.fields.Field;
import org.pstale.fields.NPC;
import org.pstale.fields.RespawnList;
import org.pstale.fields.StartPoint;
import org.pstale.loader.FieldLoader;
import org.pstale.loader.MonsterLoader;
import org.pstale.loader.NpcLoader;
import org.pstale.loader.SpawnLoader;
import org.pstale.utils.FolderChooser;

import com.jme3.app.SimpleApplication;
import com.jme3.system.AppSettings;
import com.jme3.system.JmeCanvasContext;

/**
 * 数据加载线程
 * 
 * @author yanmaoyuan
 * 
 */
public class LoadTask implements Callable<Void> {

	private FolderChooser folderChooser = new FolderChooser();
	
	/**
	 * 进度：百分比
	 */
	public int value = 0;

	/**
	 * 服务端的路径
	 */
	static String SERVER_ROOT;
	static String SERVER_FIELD_DIR = "Field";
	static String SERVER_MONSTER_DIR = "Monster";
	static String SERVER_OPENITEM_DIR = "OpenItem";
	static String SERVER_NPC_DIR = "NPC";

	/**
	 * 客户端的路径
	 */
	static String CLIENT_ROOT = "assets";

	@Override
	public Void call() throws Exception {

		config();
		value = 5;

		Field[] fields = new FieldLoader().load();
		value = 10;

		SpawnLoader sppLoader = new SpawnLoader();
		MonsterLoader spmLoader = new MonsterLoader();
		NpcLoader spcLoader = new NpcLoader();
		int size = fields.length;
		for (int i = 0; i < size; i++) {
			Field field = fields[i];
			// 计算进度
			value = 10 + 80 * (i + 1) / (size + 1);

			// 检查模型文件是否存在
			String model = field.getName();

			// 检查所有图片是否已经是解密的，否则要使用ImageDecodeer对其进行解密。
			String code = field.getCode();
			String map = "field/map/" + code + ".tga";
			String title = "field/title/" + code + "t.tga";

			// 尝试加载服务端文件
			if (SERVER_ROOT != null) {
				int index = model.lastIndexOf("/") + 1;
				String name = model.substring(index);
				String spp = SERVER_ROOT + "/" + SERVER_FIELD_DIR + "/" + name + ".spp";
				String spm = SERVER_ROOT + "/" + SERVER_FIELD_DIR + "/" + name + ".spm";
				String spc = SERVER_ROOT + "/" + SERVER_FIELD_DIR + "/" + name + ".spc";
	
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

		/**
		 * 创建jME3的Canvas
		 */
		AppSettings settings = new AppSettings(true);
		settings.setWidth(800);
		settings.setHeight(600);

		FieldApp app = new FieldApp(CLIENT_ROOT);
		app.setPauseOnLostFocus(false);
		app.setSettings(settings);
		app.createCanvas();
		app.startCanvas();

		JmeCanvasContext context = (JmeCanvasContext) app.getContext();
		Canvas canvas = context.getCanvas();
		canvas.setSize(settings.getWidth(), settings.getHeight());
		value = 95;

		// 启动主窗口
		new Main(fields, app, canvas);
		value = 100;

		return null;
	}

	/**
	 * 读取配置
	 */
	String profilepath = "config.properties";
	Properties props = new Properties();

	void config() {
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
			CLIENT_ROOT = "";
			writeProperties("CLIENT_ROOT", CLIENT_ROOT);
		}
		
		// 检查服务端文件夹
		boolean confirmServer = false;
		while (!checkServerRoot(SERVER_ROOT)) {
			File file = folderChooser.getFile();
			if (file != null) {
				SERVER_ROOT = file.getAbsolutePath();
				SERVER_ROOT = SERVER_ROOT.replaceAll("\\\\", "/");
			} else {
				confirmServer = true;
				break;
			}
		}
		if (!confirmServer) {
			writeProperties("SERVER_ROOT", SERVER_ROOT);
		} else {
			SERVER_ROOT = null;
		}
		
		// 检查客户端文件夹
		boolean confirmClient = false;
		while (!checkClientRoot(CLIENT_ROOT)) {
			File file = folderChooser.getFile();
			if (file != null) {
				CLIENT_ROOT = file.getAbsolutePath();
				CLIENT_ROOT = CLIENT_ROOT.replaceAll("\\\\", "/");
			} else {
				confirmClient = true;
				break;
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
	public void writeProperties(String keyname, String keyvalue) {
		try {
			OutputStream fos = new FileOutputStream(profilepath);
			props.setProperty(keyname, keyvalue);
			props.store(fos, "Update '" + keyname + "' value");
		} catch (IOException e) {
			System.err.println("属性文件更新错误");
		}
	}

	/**
	 * 检查客户端文件夹是否都存在
	 * @param folder
	 * @return
	 */
	boolean checkClientRoot(String folder) {
		System.out.println("Check client root : " + folder);
		File file = new File(folder);
		if (!file.exists()) {
			return false;
		}

		String[] folders = {"Effect", "Field", "char", "wav", "sky"};
		for(String subFolder : folders) {
			if (!new File(folder + "/" + subFolder).exists()) {
				System.out.println(subFolder + " is missing");
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * 检查服务端文件夹是否都存在
	 * @param folder
	 * @return
	 */
	boolean checkServerRoot(String folder) {
		System.out.println("Check server root : " + folder);
		
		File file = new File(folder);
		if (!file.exists()) {
			return false;
		}

		String[] folders = {"Field", "Monster", "NPC", "OpenItem"};
		for(String subFolder : folders) {
			if (!new File(folder + "/" + subFolder).exists()) {
				System.out.println(subFolder + " is missing");
				return false;
			}
		}
		return true;
	}
}
