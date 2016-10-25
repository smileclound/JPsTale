package org.pstale.state;

import org.pstale.app.LoaderApp;
import org.pstale.asset.anim.MotionControl;
import org.pstale.utils.FileLocator;

import com.jme3.animation.AnimControl;
import com.jme3.animation.Skeleton;
import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.asset.maxase.AseKey;
import com.jme3.bounding.BoundingVolume;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.SceneGraphVisitor;
import com.jme3.scene.Spatial;
import com.jme3.scene.debug.SkeletonDebugger;

/**
 * 加载模型
 * 
 * @author yanmaoyuan
 * 
 */
public class LoaderAppState extends AbstractAppState {

	private LoaderApp app;
	private Node rootNode;

	private InputManager inputManager;
	private AssetManager assetManager;

	float scale = 0.05f;
	Spatial mob = null;
	Spatial skin = null;
	Spatial skeletonDebug = null;

	boolean wireframe = false;
	
	public LoaderAppState() {
		rootNode = new Node("LoaderRootNode");
	}

	@Override
	public void initialize(AppStateManager stateManager, Application app) {
		super.initialize(stateManager, app);

		if (this.app == null) {
			this.app = (LoaderApp) app;
		}
		this.app.getRootNode().attachChild(rootNode);
		this.inputManager = app.getInputManager();
		this.assetManager = app.getAssetManager();

		initKeys();
	}
	
	public void setRootpath(String path) {
		assetManager.registerLocator(path, FileLocator.class);
	}
	
	public void wireframe() {
		if (mob == null)
			return;
		
		wireframe = !wireframe;
		
		mob.depthFirstTraversal(new SceneGraphVisitor() {
			@Override
			public void visit(Spatial spatial) {
				if (spatial instanceof Geometry) {
					Geometry geom = (Geometry) spatial;
					geom.getMaterial().getAdditionalRenderState().setWireframe(wireframe);
				}
			}
		});
	}
	public void loadModel(String path) {
		// 移除旧的模型
		if (mob != null) {
			rootNode.detachChild(mob);
			mob = null;
		}
		
		if (skeletonDebug != null) {
			rootNode.detachChild(skeletonDebug);
			skeletonDebug = null;
		}
		
		wireframe = false;
		
		AseKey key = new AseKey(path.replaceAll("\\\\", "/"));
		mob = (Spatial) assetManager.loadAsset(key);
		mob.scale(scale);
		rootNode.attachChild(mob);
		
		if (mob instanceof Node) {
			Node node = (Node) mob;
			node.detachChildNamed("BONES");
		}

		// Debug skeleton
		final AnimControl ac = mob.getControl(AnimControl.class);
		if (ac != null) {
			final Skeleton skel = ac.getSkeleton();
			skeletonDebug = new SkeletonDebugger("skeleton", skel);
			skeletonDebug.scale(scale);
			final Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
			mat.setColor("Color", ColorRGBA.Green);
			mat.getAdditionalRenderState().setDepthTest(false);
			skeletonDebug.setMaterial(mat);
			rootNode.attachChild(skeletonDebug);
		}
		
		// 准备高度图
		HeightMapAppState hm = app.getStateManager().getState(HeightMapAppState.class);
		if (hm != null) hm.setTerrain(mob);
		
		MotionControl c = mob.getControl(MotionControl.class);
		if (c != null){
			app.updateUI(c.getAnimationNames());
		} else if (ac != null){
			app.updateUI(ac.getAnimationNames());
		}
		
		// 移动摄像机
		BoundingVolume bounding = mob.getWorldBound();
		if (bounding != null) {
			Vector3f center = bounding.getCenter();
			Vector3f location = center.add(new Vector3f(100, 30, 0).mult(scale));
			app.getCamera().setLocation(location);
			app.getCamera().lookAt(center, new Vector3f(0, 1, 0));
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

	void initKeys() {
		inputManager.addMapping("Mesh", new KeyTrigger(KeyInput.KEY_F1));
		inputManager.addMapping("Bone", new KeyTrigger(KeyInput.KEY_F2));
		inputManager.addMapping("Wireframe", new KeyTrigger(KeyInput.KEY_F3));
		inputManager.addListener(new ActionListener() {
			@Override
			public void onAction(String name, boolean isPressed, float tpf) {
				if (isPressed) {
					if (name.equals("Bone")) {
						if (skeletonDebug == null)
							return;
						if (rootNode.hasChild(skeletonDebug)) {
							rootNode.detachChild(skeletonDebug);
						} else {
							rootNode.attachChild(skeletonDebug);
						}
					} else 
					if (name.equals("Mesh")) {
						if (mob == null)
							return;
						if (rootNode.hasChild(mob)) {
							rootNode.detachChild(mob);
						} else {
							rootNode.attachChild(mob);
						}
					} else if (name.equals("Wireframe")) {
						wireframe();
					}
				}
			}
		}, "Mesh", "Bone", "Wireframe");
	}
}
