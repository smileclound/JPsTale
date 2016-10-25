package com.jme3.asset.maxase.app;

import java.io.File;

import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.animation.Skeleton;
import com.jme3.app.SimpleApplication;
import com.jme3.asset.maxase.AseKey;
import com.jme3.asset.maxase.AseLoader;
import com.jme3.asset.maxase.FileLocator;
import com.jme3.collision.CollisionResults;
import com.jme3.font.BitmapText;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.light.AmbientLight;
import com.jme3.material.MatParam;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Quaternion;
import com.jme3.math.Ray;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.debug.Arrow;
import com.jme3.scene.debug.Grid;
import com.jme3.scene.debug.SkeletonDebugger;

public class TestLoadMonster extends SimpleApplication {

	public static void main(String[] args) {
		TestLoadMonster a = new TestLoadMonster();
		a.start();
	}

	/** Declaring the "Shoot" action and mapping to its triggers. */
	private void initKeys() {
		inputManager.addMapping("Shoot", new MouseButtonTrigger(
				MouseInput.BUTTON_LEFT)); // trigger 2:
		inputManager.addListener(actionListener, "Shoot");
	}

	/** Defining the "Shoot" action: Determine what was hit and how to respond. */
	private ActionListener actionListener = new ActionListener() {

		public void onAction(String name, boolean keyPressed, float tpf) {
			if (name.equals("Shoot") && !keyPressed) {
				// 1. Reset results list.
				CollisionResults results = new CollisionResults();
				// 2. Aim the ray from cam loc to cam direction.
				Ray ray = new Ray(cam.getLocation(), cam.getDirection());
				// 3. Collect intersections between Ray and Shootables in
				// results list.
				rootNode.collideWith(ray, results);
				// 4. Print the results
				if (results.size() > 0) {
					Geometry geom = results.getCollision(0).getGeometry();
					
					// Print name & mat
					System.out.println(geom.getName());
					Material mat = geom.getMaterial();
					MatParam param = mat.getParam("DiffuseMap");
					System.out.println(param);
				}
			}
		}
	};

	/** A centred plus sign to help the player aim. */
	protected void initCrossHairs() {
		setDisplayStatView(false);
		guiFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
		BitmapText ch = new BitmapText(guiFont, false);
		ch.setSize(guiFont.getCharSet().getRenderedSize() * 2);
		ch.setText("+"); // crosshairs
		ch.setLocalTranslation(
				// center
				settings.getWidth() / 2 - ch.getLineWidth() / 2,
				settings.getHeight() / 2 + ch.getLineHeight() / 2, 0);
		guiNode.attachChild(ch);
	}

	@Override
	public void simpleInitApp() {
		this.setPauseOnLostFocus(false);

		cam.setLocation(new Vector3f(100, 80, 100));
		cam.lookAt(Vector3f.ZERO, cam.getUp());
		this.flyCam.setMoveSpeed(100f);

		assetManager.registerLoader(AseLoader.class, "ase");

		if (new File("D:/Priston Tale/0_匼第/Client").isDirectory()) {
			assetManager.registerLocator("D:/Priston Tale/0_匼第/Client",
					FileLocator.class);
		}
		if (new File("F:/1_DEVELOP/3_匼第").isDirectory()) {
			assetManager.registerLocator("F:/1_DEVELOP/3_匼第", FileLocator.class);
		}
		if (new File("models").isDirectory()) {
			assetManager.registerLocator("models", FileLocator.class);
		}

		Spatial flag = assetManager.loadAsset(new AseKey("char/Flag/wow.ASE"));
		rootNode.attachChild(flag);

		Spatial death_knight = assetManager.loadAsset(new AseKey("char/monster/death_knight/death_knight.ASE"));
		death_knight.move(0, 0, 300);
		rootNode.attachChild(death_knight);

		Spatial chaoscara = assetManager.loadAsset(new AseKey("char/monster/chaoscara/chaoscara.ASE"));
		chaoscara.move(0, 0, 150);
		rootNode.attachChild(chaoscara);
		
//		debugSke(death_knight);
//		debugSke(chaoscara);

		initAmbient();
		initKeys();
		initCrossHairs();

		viewPort.setBackgroundColor(ColorRGBA.LightGray);

		showNodeAxes(15);

	}

	private void initAmbient() {
		AmbientLight light = new AmbientLight();
		light.setColor(ColorRGBA.White);
		rootNode.addLight(light);
	}

	public void showNodeAxes(float axisLen) {
		Mesh mesh = new Grid(31, 31, 3.93701f);
		Geometry grid = new Geometry("Axis", mesh);
		Material gm = new Material(assetManager,
				"Common/MatDefs/Misc/Unshaded.j3md");
		gm.setColor("Color", ColorRGBA.White);
		gm.getAdditionalRenderState().setWireframe(true);
		grid.setMaterial(gm);
		grid.center().move(0, -0.1f, 0);

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

	public void debugSke(Spatial model) {
		final AnimControl ac = findAnimControl(model);

		try {
			// add a skeleton debugger to make bones visible
			final Skeleton skel = ac.getSkeleton();
			final SkeletonDebugger skeletonDebug = new SkeletonDebugger(
					"skeleton", skel);
			final Material mat = new Material(assetManager,
					"Common/MatDefs/Misc/Unshaded.j3md");
			mat.setColor("Color", ColorRGBA.Green);
			mat.getAdditionalRenderState().setDepthTest(false);
			skeletonDebug.setMaterial(mat);
			((Node) ac.getSpatial()).attachChild(skeletonDebug);

			// create a channel and start the walk animation
			final AnimChannel channel = ac.createChannel();
			channel.setAnim("Anim");
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to find the animation control, because it is not on the models
	 * root node.
	 * 
	 * @param parent
	 *            The spatial to search.
	 * @return The {@link AnimControl} or null if it does not exist.
	 */
	public AnimControl findAnimControl(final Spatial parent) {
		final AnimControl animControl = parent.getControl(AnimControl.class);
		if (animControl != null) {
			return animControl;
		}

		if (parent instanceof Node) {
			for (final Spatial s : ((Node) parent).getChildren()) {
				final AnimControl animControl2 = findAnimControl(s);
				if (animControl2 != null) {
					return animControl2;
				}
			}
		}

		return null;
	}
}
