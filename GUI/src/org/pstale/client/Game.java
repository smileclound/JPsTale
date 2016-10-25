package org.pstale.client;

import org.pstale.client.gui.Cursor;
import org.pstale.client.states.CreateHero;
import org.pstale.client.states.InGame;
import org.pstale.client.states.Intro;
import org.pstale.client.states.Loading;
import org.pstale.client.states.SelectHero;
import org.pstale.client.states.Task;
import org.pstale.client.states.UserLogin;

import com.jme3.app.SimpleApplication;
import com.jme3.system.AppSettings;

import tonegod.gui.core.Screen;

/**
 * 游戏程序主类
 * 
 * @author yanmaoyuan
 */
public class Game extends SimpleApplication {
	private Screen screen;
	private Cursor cursor;

	public Intro intro;
	public Loading loading;
	public UserLogin userLogin;
	public SelectHero selectHero;
	public CreateHero createHero;
	public InGame ingame;
	
	public Game() {
		super(null);
	}
	
	@Override
	public void simpleInitApp() {
		// init Cursor
		cursor = new Cursor(inputManager, assetManager, settings);
		guiNode.attachChild(cursor);
		
		// init Screen Control
		screen = new Screen(this);
		guiNode.addControl(screen);
		
		// init States
		intro = new Intro(this, screen);
		loading = new Loading(this, screen);
		userLogin = new UserLogin(this, screen);
		selectHero = new SelectHero(this, screen);
		createHero = new CreateHero(this, screen);
		ingame = new InGame(this, screen);
		
		stateManager.attach(intro);
	}
	@Override
	public void simpleUpdate(float tpf) {

	}

	public AppSettings getSettings() {
		return settings;
	}
	

	public Cursor getCursor() {
		return cursor;
	}

	public void login() {
		stateManager.detach(intro);
		stateManager.detach(selectHero);
		
		stateManager.attach(userLogin);
	}
	
	public void loading(Task loadingTask, Task endTask) {
		loading.setTask(loadingTask, endTask);
		stateManager.attach(loading);
	}
	public void selectHero() {
		stateManager.detach(ingame);
		stateManager.attach(selectHero);
	}
	public void createHero() {
		stateManager.detach(selectHero);
		stateManager.attach(createHero);
	}
	public void ingame() {
		stateManager.detach(selectHero);
		stateManager.attach(ingame);
	}
}
