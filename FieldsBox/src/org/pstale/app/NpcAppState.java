package org.pstale.app;

import net.jmecn.asset.chars.CharMonsterInfo;

import org.apache.log4j.Logger;
import org.pstale.fields.NPC;

import com.jme3.animation.AnimControl;
import com.jme3.animation.Skeleton;
import com.jme3.app.Application;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.debug.SkeletonDebugger;

/**
 * NPC
 * @author yanmaoyuan
 *
 */
public class NpcAppState extends SubAppState {

	static Logger log = Logger.getLogger(NpcAppState.class);
	
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
			// 首先尝试直接读取NPC模型
			final Node model = (Node)ModelFactory.loadNPC(npc.getModel());
			
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
				
				ac.createChannel().setAnim("Anim");
			}
			
			model.scale(scale);
			model.setLocalTranslation(pos);
			getApplication().enqueue(new Runnable() {
				public void run() {
					rootNode.attachChild(model);
				}
			});
			
			DataState dataState = getStateManager().getState(DataState.class);
			if (dataState != null) {
				CharMonsterInfo cmNPC= dataState.findNPC(npc.getScript());
				if (cmNPC != null) {
					log.debug("找到NPC:" + cmNPC.szName);
				} else {
					log.debug("找不到NPC脚本:" + npc.getScript());
				}
			}
		}
	}
}
