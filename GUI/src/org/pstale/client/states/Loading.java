package org.pstale.client.states;

import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import org.pstale.client.Game;
import org.pstale.client.gui.hud.LoadingHUD;

import tonegod.gui.core.Screen;

import com.jme3.app.Application;
import com.jme3.app.state.AppStateManager;
import com.jme3.math.Vector2f;

/**
 * LoadingState，用于State初始化时的资源装载，显示装载进度。
 * @author Administrator
 *
 */
public class Loading extends BaseAppState {
	private LoadingHUD loadingHUD;
	
	public Loading(Game app, Screen screen) {
		super(app, screen);
	}
	
	// 任务
	private ScheduledThreadPoolExecutor exec = null;
	private Future<Void> loadFuture = null;
	private Task loadingTask = null;
	private Task endTask = null;
	
	@Override
	public void initialize(AppStateManager stateManager, Application app) {
		super.initialize(stateManager, app);
		exec = new ScheduledThreadPoolExecutor(1);
		initLoadingHUD();
	}
	
	private void initLoadingHUD() {
		if (loadingHUD == null) {
			loadingHUD = new LoadingHUD(screen, "LoadingBox",
				Vector2f.ZERO,
				new Vector2f(screen.getWidth(), screen.getHeight()));
		}
		loadingHUD.setBackground();
		screen.addElement(loadingHUD);
	}
	@Override
	public void update(float tpf) {
		if (!isEnabled())
			return;
		
		// 任务开始
		if (loadFuture == null) {
			if (loadingTask != null) {
				game.getGuiNode().detachChild(game.getCursor());
				loadFuture = exec.submit(loadingTask);
			}
		}
		
		// 任务中
		if (loadFuture != null && !loadFuture.isDone()) {
			// 更新进度条
			if (loadingTask != null) {
				loadingHUD.setProgress(loadingTask.pct, loadingTask.msg);
			}
		}
		
		// 任务结束
		if (loadFuture != null && loadFuture.isDone()) {
			loadingHUD.setProgress(1f, "");

			game.getStateManager().detach(this);
			
			if (endTask != null) {
				game.enqueue(endTask);
			}
			game.getGuiNode().attachChild(game.getCursor());
		}
	}
	
	@Override
	public void cleanup() {
		super.cleanup();
		
		if (exec != null)
			exec.shutdown();
		exec = null;
		loadFuture = null;
		loadingTask = null;
		endTask = null;
		
		screen.removeElement(loadingHUD);
	}
	
	public void setTask(Task loadingTask, Task endTask) {
		this.loadingTask = loadingTask;
		this.endTask = endTask;
	}
}