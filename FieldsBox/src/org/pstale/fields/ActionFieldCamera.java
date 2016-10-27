package org.pstale.fields;

import com.jme3.math.Vector3f;

public class ActionFieldCamera {
	private Vector3f fixPos;
	private int leftX, rightX;

	public Vector3f getFixPos() {
		return fixPos;
	}

	public void setFixPos(Vector3f fixPos) {
		this.fixPos = fixPos;
	}

	public int getLeftX() {
		return leftX;
	}

	public void setLeftX(int leftX) {
		this.leftX = leftX;
	}

	public int getRightX() {
		return rightX;
	}

	public void setRightX(int rightX) {
		this.rightX = rightX;
	}

}
