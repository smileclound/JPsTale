package org.pstale.fields;

import com.jme3.math.Vector3f;

/**
 * NPC数据
 * 
 * @author yanmaoyuan
 * 
 */
public class NPC {

	private String model;// 模型文件
	private String script;// 脚本
	private Vector3f position;// 位置
	private Vector3f rotation;// 绕轴旋转角度
	private int state;// 状态

	/**
	 * 仅用于序列化。
	 */
	public NPC() {}
	/**
	 * 初始化NPC
	 * @param model
	 * @param script
	 * @param position
	 * @param rotation
	 * @param state
	 */
	public NPC(String model, String script, Vector3f position,
			Vector3f rotation, int state) {
		this.model = model;
		this.script = script;
		this.position = position;
		this.rotation = rotation;
		this.state = state;
	}

	public String getModel() {
		return model;
	}

	public String getScript() {
		return script;
	}

	public Vector3f getPosition() {
		return position;
	}

	public Vector3f getRotation() {
		return rotation;
	}

	public int getState() {
		return state;
	}

	@Override
	public String toString() {
		return "NPC [model=" + model + ", script=" + script + ", position="
				+ position + ", rotation=" + rotation + ", state=" + state
				+ "]";
	}

}
