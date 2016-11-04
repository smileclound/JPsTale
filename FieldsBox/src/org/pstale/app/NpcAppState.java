package org.pstale.app;

import net.jmecn.asset.chars.CharMonsterInfo;

import org.pstale.fields.NPC;

import com.jme3.animation.AnimControl;
import com.jme3.animation.Skeleton;
import com.jme3.app.Application;
import com.jme3.asset.AssetManager;
import com.jme3.asset.maxase.AseKey;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Matrix3f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.debug.SkeletonDebugger;
import com.jme3.scene.shape.Box;

/**
 * NPC
 * @author yanmaoyuan
 *
 */
public class NpcAppState extends SubAppState {

	@Override
	protected void initialize(Application app) {

	}
	
	/**
	 * 加载NPC
	 * @param npcs
	 */
	public void load(NPC ... npcs) {
		//rootNode.detachAllChildren();
		
		for(int i=0; i<npcs.length; i++) {
			NPC npc = npcs[i];
			Vector3f pos = new Vector3f(npc.getPosition());
			pos.multLocal(scale);

			/**
			 * 创建一个NPC模型
			 * @param pos
			 */
			try {
				// 首先尝试直接读取NPC模型
				Node model = (Node)ModelFactory.loadNPC(npc.getModel());
				
				// Debug skeleton
				final AnimControl ac = model.getControl(AnimControl.class);
				if (ac != null) {
					final Skeleton skel = ac.getSkeleton();
					SkeletonDebugger skeletonDebug = new SkeletonDebugger("skeleton", skel);
					final Material mat = new Material(getApplication().getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
					mat.setColor("Color", ColorRGBA.Green);
					mat.getAdditionalRenderState().setDepthTest(false);
					skeletonDebug.setMaterial(mat);
					model.attachChild(skeletonDebug);
				}
				
				model.scale(scale);
				model.setLocalTranslation(pos);
				rootNode.attachChild(model);
			} catch (Exception e) {
				// 加载失败，改为加载一个绿色方块代替NPC。
				Box box = new Box(1, 1, 1);
				Geometry geom = new Geometry("NPCFlag", box);
				pos.y += 1;
				geom.setLocalTranslation(pos);
				geom.setMaterial(getMaterial(ColorRGBA.Green));
				geom.setUserData("script", npc.getScript());
				rootNode.attachChild(geom);
			}
			
			DataState dataState = getStateManager().getState(DataState.class);
			if (dataState != null) {
				CharMonsterInfo cmNPC= dataState.findNPC(npc.getScript());
				if (cmNPC != null) {
					System.out.println(cmNPC.NpcMessage);
				} else {
					System.err.println("找不到NPC脚本:" + npc.getScript());
				}
			}
		}
	}
}
