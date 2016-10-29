package org.pstale.app;

import org.pstale.fields.NPC;

import com.jme3.app.Application;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
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
			pos.y += 1;

			/**
			 * 创建一个NPC标记
			 * @param pos
			 */
			Box box = new Box(1, 1, 1);
			Geometry geom = new Geometry("NPCFlag", box);
			geom.setLocalTranslation(pos);
			geom.setMaterial(getMaterial(ColorRGBA.Green));
			rootNode.attachChild(geom);
		}
	}
	
}
