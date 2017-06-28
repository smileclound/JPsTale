package org.pstale.fields;

public class StageObject {
	private String name;
	private boolean bipAnimation;

	public StageObject(String name, boolean bipAnimation) {
		this.name = name;
		this.bipAnimation = bipAnimation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isBipAnimation() {
		return bipAnimation;
	}

	public void setBipAnimation(boolean bipAnimation) {
		this.bipAnimation = bipAnimation;
	}

}
