package org.pstale.state;

import org.pstale.app.LoaderApp;
import org.pstale.asset.anim.MotionControl;
import org.pstale.utils.FileLocator;

import com.jme3.animation.AnimControl;
import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.asset.maxase.AseKey;
import com.jme3.bounding.BoundingBox;
import com.jme3.bounding.BoundingVolume;
import com.jme3.math.FastMath;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

/**
 * 把模型加载到GuiNode，然后利用截屏来获得平面图。
 * 
 * @author yanmaoyuan
 * 
 */
public class GuiLoaderAppState extends AbstractAppState {

	private LoaderApp app;
	private Node guiNode;

	private AssetManager assetManager;

	float scale = 0.05f;
	Spatial mob = null;

	boolean wireframe = false;
	
	public GuiLoaderAppState() {
		guiNode = new Node("LoaderGuiNode");
	}

	@Override
	public void initialize(AppStateManager stateManager, Application app) {
		super.initialize(stateManager, app);

		if (this.app == null) {
			this.app = (LoaderApp) app;
		}
		this.app.getGuiNode().attachChild(guiNode);
		this.assetManager = app.getAssetManager();
	}
	
	public void setRootpath(String path) {
		assetManager.registerLocator(path, FileLocator.class);
	}
	
	public void loadModel(String path) {
		// 移除旧的模型
		if (mob != null) {
			guiNode.detachChild(mob);
			mob = null;
		}
		
		wireframe = false;
		
		AseKey key = new AseKey(path.replaceAll("\\\\", "/"));
		mob = (Spatial) assetManager.loadAsset(key);
		mob.scale(scale);
		mob.rotate(FastMath.HALF_PI, 0, -FastMath.HALF_PI);
		guiNode.attachChild(mob);
		
		// 移动摄像机
		BoundingVolume bounding = mob.getWorldBound();
		if (bounding != null && bounding instanceof BoundingBox) {
			BoundingBox bb = (BoundingBox) bounding;
			mob.center().move(bb.getXExtent(), bb.getYExtent(), 99);
		}
	}
	
	/**
	 * 播放动画
	 * @param name
	 */
	public void play(String name) {
		if (mob != null) {
			
			MotionControl c = mob.getControl(MotionControl.class);
			if (c != null) {
				int i = name.indexOf(" ");
				int id = Integer.parseInt(name.substring(0, i));
				c.SetAnimation(id);
			} else if (mob.getControl(AnimControl.class) != null) {
				AnimControl ac = mob.getControl(AnimControl.class);
				if (ac.getAnimationNames().contains(name)) {
					ac.createChannel().setAnim(name);
				}
			}
			
		}
	}
	
	public AnimControl getAnimControl() {
		if (mob == null) return null;
		return mob.getControl(AnimControl.class);
	}

	boolean isVisiavle = true;

}
