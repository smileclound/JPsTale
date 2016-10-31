package org.pstale.asset.loader;

import org.pstale.asset.anim.DrzAnimation;

import com.jme3.asset.ModelKey;
import com.jme3.scene.Node;

/**
 * 用于在InxLoader和SmdLoader之间传参。
 */
public class SmdKey extends ModelKey {
	DrzAnimation drzAnimation;
	Node rootNode;

	public SmdKey(String name, DrzAnimation anim, Node rootNode) {
		super(name);
		this.drzAnimation = anim;
		this.rootNode = rootNode;
	}

	public DrzAnimation getDrzAnimation() {
		return drzAnimation;
	}

	public Node getRootNode() {
		return rootNode;
	}

}
