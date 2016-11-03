package org.pstale.asset.loader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.jme3.animation.AnimControl;
import com.jme3.animation.Skeleton;
import com.jme3.app.SimpleApplication;
import com.jme3.light.AmbientLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Node;
import com.jme3.scene.debug.SkeletonDebugger;

public class TestStageLoader extends SimpleApplication {

	float scale = 0.2f;
	
	public static void main(String[] args) {
		new TestStageLoader().start();
	}

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

		assetManager.registerLoader(StageLoader.class, "smd", "smb");
		
		dk();
		
		//viewPort.setBackgroundColor(new ColorRGBA(0.7f, 0.8f, 0.9f, 1));
		
		AmbientLight ambient = new AmbientLight();
		ambient.setColor(new ColorRGBA(0.625f, 0.625f, 0.625f, 1f));
		rootNode.addLight(ambient);
		
		flyCam.setMoveSpeed(50f);
	}

	void ricarden() {
		// 主体
		Node model = (Node)assetManager.loadAsset(new SmdKey("Field/Ricarten/village-2.smd", SmdKey.SMDTYPE.STAGE3D));
		Node solid = (Node)model.getChild("SMMAT_STAT_CHECK_FACE");
		Node other = (Node)model.getChild("SMMAT_STAT_NOT_CHECK_FACE");
		rootNode.attachChild(solid);
		rootNode.attachChild(other);
		
		solid.scale(scale);
		other.scale(scale);
		
		for(int i=1; i<4; i++) {
			String file = String.format("Field/Ricarten/v-ani%02d.smd", i);
			Node ani = (Node)assetManager.loadAsset(new SmdKey(file, SmdKey.SMDTYPE.STAGE_OBJ));
			
			rootNode.attachChild(ani);
			ani.scale(scale);
		}
		// 灯光节点，bug用
		if (model.getChild("LIGHT3D") != null) {
			Node light = (Node)model.getChild("LIGHT3D");
			light.scale(scale);
			rootNode.attachChild(light);
		}
		
		cam.setLocation(solid.getWorldBound().getCenter());
	}
	
	void ruin2() {
		// 主体
		Node model = (Node)assetManager.loadAsset(new SmdKey("Field/Ruin/ruin-2.smd", SmdKey.SMDTYPE.STAGE3D));
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
		// 动画模型
		Node ani = (Node)assetManager.loadAsset(new SmdKey("Field/ruin/ruin_ani01.smd", SmdKey.SMDTYPE.STAGE_OBJ));
		rootNode.attachChild(ani);
		ani.scale(scale);
		
		cam.setLocation(solid.getWorldBound().getCenter());
	}
	
	void iron2() {
		// 主体
		Node model = (Node)assetManager.loadAsset(new SmdKey("Field/Iron/iron-2.smd", SmdKey.SMDTYPE.STAGE3D));
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
		// 动画模型
		Node ani = (Node)assetManager.loadAsset(new SmdKey("Field/iron/i2-bip01.smd", SmdKey.SMDTYPE.STAGE_OBJ));
		rootNode.attachChild(ani);
		ani.scale(scale);
		
		ani = (Node)assetManager.loadAsset(new SmdKey("Field/iron/i2-bip04_ani.smd", SmdKey.SMDTYPE.STAGE_OBJ_BIP));
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
		cam.setLocation(solid.getWorldBound().getCenter());
		
	}
	
	void archer() {
		// 动画模型
		Node ani = (Node)assetManager.loadAsset(new SmdKey("char/npc/TN-004/TN-004.smd", SmdKey.SMDTYPE.STAGE_OBJ_BIP));
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
		Node ani = (Node)assetManager.loadAsset(new SmdKey("char/monster/d_ar/dar.smd", SmdKey.SMDTYPE.STAGE_OBJ_BIP));
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
