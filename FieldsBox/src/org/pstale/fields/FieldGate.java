package org.pstale.fields;

import com.jme3.math.Vector3f;

/**
 * 地区的门户
 * @author yanmaoyuan
 *
 */
public class FieldGate {
	/**
	 * 传送到的目标地区编号
	 */
	private int toField;
	/**
	 * 门户在大地图上的位置
	 */
	private Vector3f position;
	
	public FieldGate() {
		toField = -1;// 表示一个不存在的地区
		position = new Vector3f();
	}
	
	public FieldGate(int toField, Vector3f position) {
		super();
		this.toField = toField;
		this.position = position;
	}

	public int getToField() {
		return toField;
	}

	public Vector3f getPosition() {
		return position;
	}

	@Override
	public String toString() {
		return "FieldGate [toField=" + toField + ", position=" + position + "]";
	}
}