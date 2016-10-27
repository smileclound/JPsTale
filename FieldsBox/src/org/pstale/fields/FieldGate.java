package org.pstale.fields;

import com.jme3.math.Vector3f;

public class FieldGate {
	private Vector3f position;
	private Field field;

	public FieldGate(Vector3f position, Field field) {
		this.position = position;
		this.field = field;
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}
}