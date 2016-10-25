package org.pstale.client.states;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import org.pstale.client.Game;

import tonegod.gui.core.Element;
import tonegod.gui.core.Screen;

import com.jme3.app.Application;
import com.jme3.app.state.AppStateManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector4f;

/**
 * 开始界面，显示Logo。
 * ->UserLogin
 * 
 * @author yanmaoyuan
 * 
 */
public class Intro extends BaseAppState {

	private Element logo;
	
	public Intro(Game app, Screen screen) {
		super(app, screen);
	}

	@Override
	public void initialize(AppStateManager stateManager, Application app) {
		super.initialize(stateManager, app);

        app.getInputManager().addMapping("ESC", new KeyTrigger(KeyInput.KEY_ESCAPE));
        app.getInputManager().addListener(listener, "ESC");
        
		initLogo();
	}
	
	ActionListener listener = new ActionListener() {
    	public void onAction(String name, boolean pressed, float tpf) {
			if (pressed && name.equals("ESC")) {
                game.stop();
            }
		}
    };

	// 任务
	private ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);
	private Future<Void> loadFuture = null;
	private Callable<Void> task = new Callable<Void>() {
		@Override
		public Void call() throws Exception {
			Thread.sleep(1000);// 停留1秒
			return null;
		}
	};
	
	@Override
	public void update(float tpf) {
		if (loadFuture == null) {
			loadFuture = exec.submit(task);
		}
		if (loadFuture.isDone()) {
			loadFuture = null;
			task = null;
			game.login();
		}
	}

	@Override
	public void cleanup() {
		super.cleanup();
		
		if (exec != null) {
			// 关闭线程池，不关的话程序就不会结束。
			exec.shutdown();
		}
		exec = null;
		
		screen.removeElement(logo);
		
		game.getInputManager().deleteMapping("ESC");
		game.getInputManager().removeListener(listener);
	}

	private void initLogo() {
		if (logo == null) {
			float width = 473;
			float height = 136;
			float posX = (screen.getWidth() - width) / 2;
			float posY = (screen.getHeight() - height) / 2;

			// 初始化
			logo = new Element(screen, "LOGO", new Vector2f(posX, posY),
					new Vector2f(width, height), Vector4f.ZERO,
					"Textures/Logo/logo.png");
		}

		screen.addElement(logo);
	}
}
