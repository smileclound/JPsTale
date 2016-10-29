package org.pstale.app;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.asset.AssetManager;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.debug.Arrow;
import com.jme3.scene.debug.Grid;

/**
 * 参考坐标系
 * 
 * @author yanmaoyuan
 * 
 */
public class AxisAppState extends SubAppState {
	public final static String TOGGLE_AXIS = "toggle_axis";

	private AssetManager assetManager;

	@Override
	public void initialize(Application app) {
		assetManager = app.getAssetManager();

		showNodeAxes(200);
		
		toggleAxis();
	}

	@Override
	protected void onEnable() {
		super.onEnable();
		
		InputManager inputManager = getApplication().getInputManager();
		inputManager.addMapping(TOGGLE_AXIS, new KeyTrigger(KeyInput.KEY_F4));
		inputManager.addListener(actionListener, TOGGLE_AXIS);
	}
	
	@Override
	protected void onDisable() {
		super.onDisable();
		
		InputManager inputManager = getApplication().getInputManager();
		inputManager.removeListener(actionListener);
		inputManager.deleteMapping(TOGGLE_AXIS);
	}

	private ActionListener actionListener = new ActionListener() {
		public void onAction(String name, boolean keyPressed, float tpf) {
			if (name.equals(TOGGLE_AXIS) && keyPressed) {
				toggleAxis();
			}
		}
	};
	
	/**
	 * 坐标轴开/关
	 * @return
	 */
	public boolean toggleAxis() {
		SimpleApplication simpleApp = (SimpleApplication) getApplication();
		if (simpleApp.getRootNode().hasChild(rootNode)) {
			simpleApp.getRootNode().detachChild(rootNode);
			return false;
		} else {
			simpleApp.getRootNode().attachChild(rootNode);
			return true;
		}
	}

	private void showNodeAxes(float axisLen) {
		int l = (int) (axisLen * 2) + 1;
		Mesh mesh = new Grid(l, l, 10);
		Geometry grid = new Geometry("Axis", mesh);
		Material gm = new Material(assetManager,
				"Common/MatDefs/Misc/Unshaded.j3md");
		gm.setColor("Color", ColorRGBA.White);
		gm.getAdditionalRenderState().setWireframe(true);
		grid.setMaterial(gm);
		grid.center().move(0, 0, 0);

		rootNode.attachChild(grid);

		//
		Vector3f v = new Vector3f(axisLen, 0, 0);
		Arrow a = new Arrow(v);
		Material mat = new Material(assetManager,
				"Common/MatDefs/Misc/Unshaded.j3md");
		mat.setColor("Color", ColorRGBA.Red);
		Geometry geom = new Geometry(rootNode.getName() + "XAxis", a);
		geom.setMaterial(mat);
		rootNode.attachChild(geom);

		//
		v = new Vector3f(0, axisLen, 0);
		a = new Arrow(v);
		mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
		mat.setColor("Color", ColorRGBA.Green);
		geom = new Geometry(rootNode.getName() + "YAxis", a);
		geom.setMaterial(mat);
		rootNode.attachChild(geom);

		//
		v = new Vector3f(0, 0, axisLen);
		a = new Arrow(v);
		mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
		mat.setColor("Color", ColorRGBA.Blue);
		geom = new Geometry(rootNode.getName() + "ZAxis", a);
		geom.setMaterial(mat);
		rootNode.attachChild(geom);
	}

}
