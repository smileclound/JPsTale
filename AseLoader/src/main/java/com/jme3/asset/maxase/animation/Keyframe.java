package com.jme3.asset.maxase.animation;

import java.io.Serializable;

import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;

public class Keyframe implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4309902941256315699L;
	public Vector3f translation = new Vector3f(0, 0, 0);
	public Quaternion rotation = new Quaternion(0, 0, 0, 1);
	public Vector3f scale = new Vector3f(1, 1, 1);

	public Keyframe() {
	}

	public Keyframe(Vector3f translation, Quaternion rotation, Vector3f scale) {
		this.translation = translation;
		this.rotation = rotation;
		this.scale = scale;
	}

	public boolean equals(Object b) {
		if (!(b instanceof Keyframe))
			return false;
		Keyframe k = (Keyframe) b;
		if ((k.translation == null) != (translation == null))
			return false;
		if ((k.rotation == null) != (rotation == null))
			return false;
		if ((k.scale == null) != (scale == null))
			return false;
		return (translation == null || translation.equals(k.translation))
				&& (rotation == null || rotation.equals(k.rotation))
				&& (scale == null || scale.equals(k.scale));
	}

	public int hashCode() {
		return (((translation == null) ? 0 : translation.hashCode()) << 1)
				^ (((rotation == null) ? 0 : rotation.hashCode()) << 0)
				^ (((scale == null) ? 0 : scale.hashCode()) << 2);
	}
}
