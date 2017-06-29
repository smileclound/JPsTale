package com.jme3.scene.plugins.inx;

import com.jme3.asset.ModelKey;
import com.jme3.scene.plugins.inx.anim.DrzAnimation;

/**
 * 用于在InxLoader和SmbLoader之间传参。
 * @author yanmaoyuan
 *
 */
public class SmbKey extends ModelKey {

	DrzAnimation drzAnimation;
	
	public SmbKey(String name, DrzAnimation anim) {
		super(name);
		this.drzAnimation = anim;
	}

	public DrzAnimation getDrzAnimation() {
		return drzAnimation;
	}
	
}
