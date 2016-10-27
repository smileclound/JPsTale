package org.pstale.fields;

import java.util.ArrayList;
import java.util.List;

import com.jme3.math.Vector3f;

public class WarpGate {
	private Vector3f position;
	private int height, size;
	private List<FieldGate> outGate;
	private int limitLevel;
	private int specialEffect;

	public WarpGate(Vector3f position, int height, int size) {
		this.position = position;
		this.height = height;
		this.size = size;
		
		outGate = new ArrayList<FieldGate>();
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public List<FieldGate> getOutGate() {
		return outGate;
	}

	public void setOutGate(List<FieldGate> outGate) {
		this.outGate = outGate;
	}

	public int getLimitLevel() {
		return limitLevel;
	}

	public void setLimitLevel(int limitLevel) {
		this.limitLevel = limitLevel;
	}

	public int getSpecialEffect() {
		return specialEffect;
	}

	public void setSpecialEffect(int specialEffect) {
		this.specialEffect = specialEffect;
	}

}
