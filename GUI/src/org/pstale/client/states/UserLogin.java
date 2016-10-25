package org.pstale.client.states;

import org.pstale.client.Game;

import tonegod.gui.controls.windows.LoginBox;
import tonegod.gui.controls.windows.Panel;
import tonegod.gui.core.Screen;

import com.jme3.app.Application;
import com.jme3.app.state.AppStateManager;
import com.jme3.input.event.MouseButtonEvent;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector4f;

/**
 * 选择角色界面
 * ->SelectHero
 * @author yanmaoyuan
 *
 */
public class UserLogin extends BaseAppState {

	Panel background;
	LoginBox loginWindow;

	public UserLogin(Game app, Screen screen) {
		super(app, screen);
	}

	@Override
	public void initialize(AppStateManager stateManager, Application app) {
		super.initialize(stateManager, app);
		initBackGround();
		initLoginWindow();
	}

	public void initBackGround() {
		if (background == null) {
			background = new Panel(screen, "background",
				Vector2f.ZERO, new Vector2f(screen.getWidth(), screen.getHeight()),
				Vector4f.ZERO,
				null);
			background.setIgnoreMouse(true);
		}
		
		String[] imgs = { "Textures/Login/login-screen-01.jpg",
				"Textures/Login/login-screen-02.jpg",
				"Textures/Login/login-screen-03.jpg",
				"Textures/Login/login-screen-04.jpg",
				"Textures/Login/login-screen-05.jpg",
				"Textures/Login/login-screen-06.jpg",
				"Textures/Login/login-screen-07.jpg",
				"Textures/Login/login-screen-08.jpg",
				"Textures/Login/login-screen-09.jpg" };
		int n = rand.nextInt(imgs.length);
		background.setColorMap(imgs[n]);
		screen.addElement(background);
	}

	public void initLoginWindow() {
		if (loginWindow == null) {
			loginWindow = new LoginBox(screen, "loginWindow",
				new Vector2f(screen.getWidth() / 2 - 175, screen.getHeight() / 2 - 125)) {
				@Override
				public void onButtonLoginPressed(MouseButtonEvent evt,
						boolean toggled) {
					// Some call to the server to log the client in
					finalizeUserLogin();
				}
	
				@Override
				public void onButtonCancelPressed(MouseButtonEvent evt,
						boolean toggled) {
					game.stop();
				}
			};
		}
		screen.addElement(loginWindow);
	}

	@Override
	public void cleanup() {
		super.cleanup();
		screen.removeElement(loginWindow);
		screen.removeElement(background);
	}

	private boolean ready = false;
	public void finalizeUserLogin() {
		loginWindow.setMsg("");
		
		game.getStateManager().detach(this);
		
		Task loadingTask = null;
		if (ready) {
			// 如果已经加载过一次，那么有一些公共的内容就不需要初始化了。
			loadingTask = new Task() {
				@Override
				public Void call() throws Exception {
					// 根据用户输入的登录信息，访问服务器获得角色列表等数据。
					pct = 0.8f;
					msg = "Loading Player Model.";
					//game.selectHero.initPlayModel();
					return null;
				}
			};
		} else {
			// 还没加载过SelectHero的资源，初始化GUI等基本资源。
			loadingTask = new Task() {
				@Override
				public Void call() throws Exception {
					
					pct = 0.6f;
					msg = "Loading Stand Plane.";
					//game.selectHero.initStandPlane();
					
					// 根据用户输入的登录信息，访问服务器获得角色列表等数据。
					pct = 0.8f;
					msg = "Loading Player Model.";
					//game.selectHero.initPlayModel();
					
					pct = 0.95f;
					msg = "Loading Light.";
					game.selectHero.initLight();
					return null;
				}
			};
		}
		
		Task endTask = new Task() {
			@Override
			public Void call() throws Exception {
				ready = true;
				// 跳转到SelectHero
				game.getStateManager().attach(game.selectHero);
				return null;
			}
		};
		game.loading(loadingTask, endTask);
	}
}
