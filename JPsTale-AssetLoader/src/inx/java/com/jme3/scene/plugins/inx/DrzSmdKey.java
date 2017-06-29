package com.jme3.scene.plugins.inx;

import com.jme3.asset.ModelKey;
import com.jme3.scene.Node;
import com.jme3.scene.plugins.inx.anim.DrzAnimation;

/**
 * 用于在InxLoader和SmdLoader之间传参。
 */
public class DrzSmdKey extends ModelKey {
	DrzAnimation drzAnimation;
	Node rootNode;

	public DrzSmdKey(String name, DrzAnimation anim, Node rootNode) {
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
