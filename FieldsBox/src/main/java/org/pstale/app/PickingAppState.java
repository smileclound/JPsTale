package org.pstale.app;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.collision.CollisionResults;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.material.Material;
import com.jme3.material.RenderState;
import com.jme3.math.Ray;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;

/**
 * 拣选模块
 * 
 * @author yanmaoyuan
 * 
 */
public class PickingAppState extends AbstractAppState {

	static Logger log = Logger.getLogger(PickingAppState.class);
	
	public final static String PICKING = "mouse_picking";
	public final static String DELETE = "delete_current";

	private SimpleApplication app;
	private Node rootNode;

	private InputManager inputManager;
	private Camera cam;

	private ArrayList<Geometry> list;
	private Geometry cur;

	@Override
	public void initialize(AppStateManager stateManager, Application app) {
		super.initialize(stateManager, app);

		if (list == null) {
			list = new ArrayList<Geometry>();
		}
		if (this.app == null) {
			this.app = (SimpleApplication) app;
			rootNode = new Node("PickingRootNode");
		}
		inputManager = app.getInputManager();
		cam = app.getCamera();

		initKeys();
	}

	@Override
	public void cleanup() {
		super.cleanup();

		app.getRootNode().detachChild(rootNode);
		inputManager.removeListener(actionListener);
		inputManager.deleteMapping(PICKING);
		inputManager.deleteMapping(DELETE);
	}

	private void initKeys() {
		inputManager.addMapping(PICKING, new KeyTrigger(KeyInput.KEY_P));
		inputManager.addMapping(DELETE, new KeyTrigger(KeyInput.KEY_DELETE));
		inputManager.addListener(actionListener, PICKING, DELETE);
	}

	private ActionListener actionListener = new ActionListener() {

		public void onAction(String name, boolean keyPressed, float tpf) {
			if (name.equals(PICKING) && keyPressed) {

				Vector3f origin = cam.getWorldCoordinates(inputManager.getCursorPosition(), 0.0f);

				Vector3f direction = cam.getWorldCoordinates(inputManager.getCursorPosition(), 0.3f);
				direction.subtractLocal(origin).normalizeLocal();

				CollisionResults results = new CollisionResults();
				Ray ray = new Ray(origin, direction);
				app.getRootNode().collideWith(ray, results);
				if (results.size() > 0) {
					if (cur != null) {
						// cur.getMaterial().getAdditionalRenderState().setWireframe(false);
					}
					
					cur = results.getCollision(0).getGeometry();
					Material mat = cur.getMaterial();
					RenderState rs = mat.getAdditionalRenderState();
					
					if (list.contains(cur)) {
						list.remove(cur);
						rs.setWireframe(false);
					} else {
						list.add(cur);
						rs.setWireframe(true);
						
						log.debug("=========>" + cur.getName());
						if ( mat.getParam("ColorMap") != null)
							log.debug("ColorMap:" + mat.getParam("ColorMap"));
						if (mat.getParam("DiffuseMap") != null)
							log.debug("DiffuseMap:" + mat.getParam("DiffuseMap"));
						log.debug("RenderQueue.Bucket" + cur.getQueueBucket());
						log.debug("rs.BlendModel:" + rs.getBlendMode());
						
						log.debug("MeshState:" + cur.getUserData("MeshState"));
						log.debug("UseState:" + cur.getUserData("UseState"));
						log.debug("BlendType:" + cur.getUserData("BlendType"));
						log.debug("MapOpacity:" + cur.getUserData("MapOpacity"));
						log.debug("Transparency:" + cur.getUserData("Transparency"));
					}
				}
			} else if (name.equals(DELETE) && keyPressed) {
				if (list.size() > 0) {
					for(Geometry g : list) {
						g.getMaterial().getAdditionalRenderState().setWireframe(false);
						Node p = g.getParent();
						p.detachChild(g);
						rootNode.attachChild(g);
					}
					
					list.clear();
					
					app.getRootNode().updateModelBound();
				}
			}
		}
	};
}
