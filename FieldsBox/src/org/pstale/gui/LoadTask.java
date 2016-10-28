package org.pstale.gui;

import java.awt.Canvas;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Callable;

import org.pstale.app.FieldApp;
import org.pstale.fields.Field;
import org.pstale.fields.NPC;
import org.pstale.fields.RespawnList;
import org.pstale.fields.StartPoint;
import org.pstale.loader.FieldLoader;
import org.pstale.loader.MonsterLoader;
import org.pstale.loader.NpcLoader;
import org.pstale.loader.SpawnLoader;

import com.jme3.app.SimpleApplication;
import com.jme3.system.AppSettings;
import com.jme3.system.JmeCanvasContext;

/**
 * 数据加载线程
 * @author yanmaoyuan
 *
 */
public class LoadTask implements Callable<Void> {

	/**
	 * 进度：百分比
	 */
	public int value = 0;
	
	/**
	 * 服务端的路径
	 */
	static String SERVER_ROOT = "../Assets/assets/";
	static String SERVER_FIELD_DIR = "server/field/";
	
	/**
	 * 客户端的路径
	 */
	static String CLIENT_ROOT = "assets";
	
	@Override
	public Void call() throws Exception {
		/**
		 * 创建jME3的Canvas
		 */
		AppSettings settings = new AppSettings(true);
		settings.setWidth(800);
		settings.setHeight(600);

		SimpleApplication app = new FieldApp();
		app.setPauseOnLostFocus(false);
		app.setSettings(settings);
		app.createCanvas();
		app.startCanvas();
		
		JmeCanvasContext context = (JmeCanvasContext) app.getContext();
		Canvas canvas = context.getCanvas();
		canvas.setSize(settings.getWidth(), settings.getHeight());
		
		value = 5;
		
		
		Field[] fields = new FieldLoader().load();
		value = 10;
		
		SpawnLoader sppLoader = new SpawnLoader();
		MonsterLoader spmLoader = new MonsterLoader();
		NpcLoader spcLoader = new NpcLoader();
		int size = fields.length;
		for(int i=0; i<size; i++) {
			Field field = fields[i];
			
			// 计算进度
			value = 10 + 80 * (i+1)/(size+1);

			// 检查模型文件是否存在
			String model = field.getName();
			
			// 检查所有图片是否已经是解密的，否则要使用ImageDecodeer对其进行解密。
			String code = field.getCode();
			String map = "field/map/" + code + ".tga";
			String title = "field/title/" + code + "t.tga";
			
			// 尝试加载服务端文件
			int index = model.lastIndexOf("/")+1;
			String name = model.substring(index);
			String spp = SERVER_ROOT + SERVER_FIELD_DIR + name + ".spp";
			String spm = SERVER_ROOT + SERVER_FIELD_DIR + name + ".spm";
			String spc = SERVER_ROOT + SERVER_FIELD_DIR + name + ".spc";
			
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
		value = 90;
		
		// 启动主窗口
		new Main(fields, app, canvas);
		value = 100;
		
		return null;
	}

}
