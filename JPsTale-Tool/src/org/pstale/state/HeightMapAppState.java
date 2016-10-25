package org.pstale.state;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.bounding.BoundingBox;
import com.jme3.collision.CollisionResults;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.Ray;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import com.jme3.system.JmeSystem;

public class HeightMapAppState extends AbstractAppState {

	public static final String CREATE_HEIGHT_MAP = "CreateHeightMap";// 生成高度图
	
	private Spatial terrain;// 地形节点
	
	private InputManager inputManager;
	
	/**
	 * 设置模型
	 * @param spatial
	 */
	public void setTerrain(Spatial spatial) {
		this.terrain = spatial;
	}
	
	@Override
	public void initialize(AppStateManager stateManager, Application app) {
		this.inputManager = app.getInputManager();
		
		inputManager.addMapping(CREATE_HEIGHT_MAP, new KeyTrigger(KeyInput.KEY_F6));
		inputManager.addListener(listener, CREATE_HEIGHT_MAP);
		super.initialize(stateManager, app);
	}
	
	@Override
	public void cleanup() {
		inputManager.deleteMapping(CREATE_HEIGHT_MAP);
		inputManager.removeListener(listener);
		super.cleanup();
	}

	
	/**
	 * 动作监听器
	 */
	ActionListener listener = new ActionListener() {
		@Override
		public void onAction(String name, boolean isPressed, float tpf) {
			if (isPressed) {
				if (name.equals(CREATE_HEIGHT_MAP)) {
					new MyThread().start();
				}
			}
		}
	};
	
	static int sample = 1024;
	static float factor = 1000;
	static boolean isRunning = false;

	/**
	 * 扫描地形+创建高度图的过程比较久，因此将利用一个线程来执行。
	 */
	private class MyThread extends Thread {
		public void run() {
			if (isRunning) {
				System.out.println("正在创建高度图...");
				return;
			}
			
			isRunning = true;
			
			if (terrain != null) {
				// 创建一个图片，获取像素
				ByteBuffer buffer = ByteBuffer.allocate(sample * sample * 4);
				
				System.out.println(buffer.limit() + " bytes allocated.");
				
				// 计算网格的范围
				BoundingBox box = (BoundingBox)terrain.getWorldBound();
				Vector3f extent = box.getExtent(null);
				Vector3f min = box.getMin(null);
				Vector3f max = box.getMax(null);
				
				factor = extent.y * 2;
				
				float stepX = (extent.x * 2) / sample;
				float stepZ = (extent.z * 2) / sample;
				
				float min_h = 999999999;
				float max_h = -999999999;
				float height;
				int picX = 0;
				
				// 定义一条方向向下的射线
				final Ray ray = new Ray();
				ray.setDirection(new Vector3f(0, -1, 0));
				ray.setLimit(factor + 1);
				
				for(float z = min.z; z<= max.z; z+=stepZ) {
					int picY = 0;
					for(float x = min.x; x<= max.x; x+=stepX) {
						
						// 射线检测
						ray.setOrigin(new Vector3f(x, max.y+1, z));
						CollisionResults results = new CollisionResults();
						terrain.collideWith(ray, results);
						
						height = 0;
						if (results.size() > 0) {
							Vector3f point = results.getClosestCollision().getContactPoint();
							if (max_h < point.y) max_h = point.y;
							if (min_h > point.y) min_h = point.y;
							
							height = point.y - min.y;
							if (height < 0) height = 0;
						}
						byte value = (byte)(256 * height / factor);
						
						// 指针
						int ptr  = (picY * sample + picX) * 4;
						buffer.put(ptr, value);// b
						buffer.put(ptr+1, value);// g
						buffer.put(ptr+2, value);// r
						buffer.put(ptr+3, (byte)0xFF);// a
						
						if (++picY == sample) break; 
					}
					if (++picX == sample) break;
				}
				
				System.out.println("max.y=" + max.y + " min.y=" + min.y + " max_h=" + max_h + " min_h=" + min_h);
				
				try {
					buffer.flip();
					JmeSystem.writeImageFile(new FileOutputStream("map.png"), "png", buffer, sample, sample);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println("done");
			}
		}
	}

}
