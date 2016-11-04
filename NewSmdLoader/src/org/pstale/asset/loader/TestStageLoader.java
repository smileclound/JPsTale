package org.pstale.asset.loader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.jme3.animation.AnimControl;
import com.jme3.animation.Skeleton;
import com.jme3.app.SimpleApplication;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.light.SpotLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.Node;
import com.jme3.scene.debug.SkeletonDebugger;

public class TestStageLoader extends SimpleApplication {

	float scale = 0.2f;
	
	public static void main(String[] args) {
		new TestStageLoader().start();
	}

	SpotLight spot;
	
	@Override
	public void simpleInitApp() {
		stateManager.attach(new AxisAppState());
		
		// 属性文件的路径
		Properties props = new Properties();
		try {
			props.load(new FileInputStream("config.properties"));
			String client = props.getProperty("CLIENT_ROOT");
			assetManager.registerLocator(client, FileLocator.class);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		assetManager.registerLoader(StageLoader.class, "inx", "smd", "smb");
		
		//solid("Field/forest/fore-3.smd");
		
		//stage("Field/dungeon/dun-1.smd");
		//stage("Field/dungeon/dun-5.smd");
		//stage("Field/forest/fore-3.smd");
		//stage("Field/forest/fore-2.smd");
		//stage("Field/forest/fore-1.smd");
		//stage("Field/Ruin/ruin-4.smd");
		//stage("Field/Ruin/ruin-3.smd");
		ruin2();
		//stage("Field/Ruin/ruin-1.smd");
		//stage("Field/Ice/ice1.smd");
		//iron2();
		//ricarden();
		//tn004();
		//dk();
		//d_ar();
		
		//viewPort.setBackgroundColor(new ColorRGBA(0.7f, 0.8f, 0.9f, 1));
		
		AmbientLight ambient = new AmbientLight();
		ambient.setColor(new ColorRGBA(0.225f, 0.225f, 0.225f, 1f));
		//rootNode.addLight(ambient);
		
		DirectionalLight sun = new DirectionalLight();
		sun.setColor(new ColorRGBA(0.1f, 0.1f, 0.1f, 1));
		sun.setDirection(new Vector3f(1, -1, -0.5f).normalizeLocal());
		rootNode.addLight(sun);
		
		flyCam.setMoveSpeed(50f);
		
		spot = new SpotLight();
		spot.setColor(new ColorRGBA(1, 1, 1, 1));
		spot.setSpotRange(1000f);
		spot.setSpotInnerAngle(FastMath.DEG_TO_RAD * 5);
		spot.setSpotOuterAngle(FastMath.DEG_TO_RAD * 30);
		rootNode.addLight(spot);
	}

	public void simpleUpdate(float tpf) {
		spot.setPosition(cam.getLocation());
		spot.setDirection(cam.getDirection());
	}
	void solid(String path) {
		Mesh mesh = (Mesh)assetManager.loadAsset(new SmdKey(path, SmdKey.SMDTYPE.STAGE3D_SOLID));
		Geometry geom = new Geometry("Solid", mesh);
		
		Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
		mat.setColor("Color", ColorRGBA.Red);
		mat.getAdditionalRenderState().setWireframe(true);
		geom.setMaterial(mat);
		
		rootNode.attachChild(geom);
		geom.scale(scale);
		
		cam.setLocation(geom.getWorldBound().getCenter());
	}
	
	void stage(String path) {
		// 主体
		Node model = (Node)assetManager.loadAsset(new SmdKey(path, SmdKey.SMDTYPE.STAGE3D));
		Node solid = (Node)model.getChild("SMMAT_STAT_CHECK_FACE");
		Node other = (Node)model.getChild("SMMAT_STAT_NOT_CHECK_FACE");
		rootNode.attachChild(solid);
		rootNode.attachChild(other);
		
		solid.scale(scale);
		other.scale(scale);
		
		// 灯光节点，bug用
		if (model.getChild("LIGHT3D") != null) {
			Node light = (Node)model.getChild("LIGHT3D");
			light.scale(scale);
			rootNode.attachChild(light);
		}
		
		cam.setLocation(solid.getWorldBound().getCenter());
	}
	
	void ricarden() {
		// 主体
		stage("Field/Ricarten/village-2.smd");
		
		/**
		for(int i=1; i<14; i++) {
			String file = String.format("Field/Ricarten/v-ani%02d.smd", i);
			Node ani = (Node)assetManager.loadAsset(new SmdKey(file, SmdKey.SMDTYPE.PAT3D));
			
			rootNode.attachChild(ani);
			ani.scale(scale);
		}
		*/
		
	}
	
	void ruin2() {
		// 主体
		stage("Field/Ruin/ruin-2.smd");
		// 动画模型
		Node ani = (Node)assetManager.loadAsset(new SmdKey("Field/ruin/ruin_ani01.smd", SmdKey.SMDTYPE.PAT3D));
		rootNode.attachChild(ani);
		ani.scale(scale);
		
	}
	
	void iron2() {
		// 主体
		stage("Field/Iron/iron-2.smd");
		// 动画模型
		Node ani = (Node)assetManager.loadAsset(new SmdKey("Field/iron/i2-bip01.smd", SmdKey.SMDTYPE.PAT3D));
		rootNode.attachChild(ani);
		ani.scale(scale);
		
		SmdKey key = new SmdKey("Field/iron/i2-bip04_ani.smd", SmdKey.SMDTYPE.PAT3D_BIP);
		ani = (Node)assetManager.loadAsset(key);
		
		rootNode.attachChild(ani);
		ani.scale(scale);
		
		// Debug skeleton
		final AnimControl ac = ani.getControl(AnimControl.class);
		if (ac != null) {
			final Skeleton skel = ac.getSkeleton();
			SkeletonDebugger skeletonDebug = new SkeletonDebugger("skeleton", skel);
			skeletonDebug.scale(scale);
			final Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
			mat.setColor("Color", ColorRGBA.Green);
			mat.getAdditionalRenderState().setDepthTest(false);
			skeletonDebug.setMaterial(mat);
			rootNode.attachChild(skeletonDebug);
			
		}
		
	}
	
	void tn004() {
		// 动画模型
		Node ani = (Node)assetManager.loadAsset(new SmdKey("char/npc/TN-004/TN-004.inx", SmdKey.SMDTYPE.INX));
		rootNode.attachChild(ani);
		ani.scale(scale);
		
		// Debug skeleton
		final AnimControl ac = ani.getControl(AnimControl.class);
		if (ac != null) {
			final Skeleton skel = ac.getSkeleton();
			SkeletonDebugger skeletonDebug = new SkeletonDebugger("skeleton", skel);
			skeletonDebug.scale(scale);
			final Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
			mat.setColor("Color", ColorRGBA.Red);
			mat.getAdditionalRenderState().setDepthTest(false);
			skeletonDebug.setMaterial(mat);
			rootNode.attachChild(skeletonDebug);
			
		}
		// 播放动画
		if (ac != null) {
			ac.createChannel().setAnim("Anim");;
		}
	}
	
	void dk() {
		// 动画模型
		Node ani = (Node)assetManager.loadAsset(new SmdKey("char/monster/d_ar/dar.smd", SmdKey.SMDTYPE.PAT3D));
		rootNode.attachChild(ani);
		ani.scale(scale);
		
		// Debug skeleton
		final AnimControl ac = ani.getControl(AnimControl.class);
		if (ac != null) {
			final Skeleton skel = ac.getSkeleton();
			SkeletonDebugger skeletonDebug = new SkeletonDebugger("skeleton", skel);
			skeletonDebug.scale(scale);
			final Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
			mat.setColor("Color", ColorRGBA.Red);
			mat.getAdditionalRenderState().setDepthTest(false);
			skeletonDebug.setMaterial(mat);
			rootNode.attachChild(skeletonDebug);
		}
	}
	
	void d_ar() {
		// 动画模型
		Node ani = (Node)assetManager.loadAsset(new SmdKey("char/monster/d_ar/dar.inx", SmdKey.SMDTYPE.INX));
		rootNode.attachChild(ani);
		ani.scale(scale);
		
		// Debug skeleton
		final AnimControl ac = ani.getControl(AnimControl.class);
		if (ac != null) {
			final Skeleton skel = ac.getSkeleton();
			SkeletonDebugger skeletonDebug = new SkeletonDebugger("skeleton", skel);
			skeletonDebug.scale(scale);
			final Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
			mat.setColor("Color", ColorRGBA.Red);
			mat.getAdditionalRenderState().setDepthTest(false);
			skeletonDebug.setMaterial(mat);
			rootNode.attachChild(skeletonDebug);
		}
	}
}
