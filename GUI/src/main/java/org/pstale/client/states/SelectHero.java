package org.pstale.client.states;

import org.pstale.client.Game;
import org.pstale.client.gui.hud.SelectHeroHUD;

import tonegod.gui.controls.buttons.ButtonAdapter;
import tonegod.gui.core.Screen;

import com.jme3.app.Application;
import com.jme3.app.state.AppStateManager;
import com.jme3.light.AmbientLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

/**
 * 选择角色界面 ->CreateHero ->InGame <-UserLogin
 * 
 * @author yanmaoyuan
 * 
 */
public class SelectHero extends BaseAppState {
	Node rootNode = new Node();

	Spatial scene = null;// 背景场景
	Spatial standPlane = null;// 角色模型站立的平台
	Spatial playModel = null;// 角色模型
	AmbientLight light = null;// 环境光

	ButtonAdapter startbtn;// 开始游戏
	ButtonAdapter deletebtn;// 删除角色
	ButtonAdapter createbtn;// 创建角色
	ButtonAdapter backbtn;// 返回

	protected SelectHeroHUD hud;

	public SelectHero(Game app, Screen screen) {
		super(app, screen);
	}

	protected void startGame() {
		game.getStateManager().detach(game.selectHero);
		game.ingame();
	}

	protected void back() {
		game.getStateManager().detach(game.selectHero);
		game.login();
	}

	// 3D scene
	void initLight() {
		if (light == null) {
			light = new AmbientLight();
			rootNode.addLight(light);
		}
	}

	@Override
	public void initialize(AppStateManager stateManager, Application app) {
		super.initialize(stateManager, app);

		// 设置镜头
		app.getCamera().setLocation(new Vector3f(100, 23, 0));
		app.getCamera().lookAt(new Vector3f(0, 23, 0), app.getCamera().getUp());
		app.getViewPort().setBackgroundColor(new ColorRGBA(0.6f, 0.8f, 1, 1));

		// 初始化hud
		hud = getHUD();
		hud.initialize();
		hud.resize(screen.getWidth(), screen.getHeight());

		// 创建3D场景
		game.getRootNode().attachChild(rootNode);
	}

	@Override
	public void update(float tpf) {
	}

	@Override
	public void cleanup() {
		super.cleanup();

		// 恢复镜头
		game.getViewPort().setBackgroundColor(ColorRGBA.Black);

		// 恢复场景
		game.getRootNode().detachChild(rootNode);

		// 恢复GUI
		hud.cleanup();
	}

	private SelectHeroHUD getHUD() {
		if (hud == null) {
			hud = new SelectHeroHUD(screen) {

				@Override
				public void onCreateButtonClick() {
					// TODO Auto-generated method stub

				}

				@Override
				public void onDeleteButtonClick() {
					// TODO Auto-generated method stub

				}

				@Override
				public void onStartButtonClick() {
					Task loadingTask = null;
					loadingTask = new Task() {
						@Override
						public Void call() throws Exception {
							game.getStateManager().detach(game.selectHero);
							// 加载游戏场景

							return null;
						}
					};

					Task endTask = new Task() {
						@Override
						public Void call() throws Exception {
							// 跳转到InGame
							game.getStateManager().attach(game.ingame);
							return null;
						}
					};
					game.loading(loadingTask, endTask);
				}

				@Override
				public void onBackButtonClick() {
					game.login();
				}
			};
		}
		return hud;
	}
}
