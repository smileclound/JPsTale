package org.pstale.fields;

/**
 * 怪物的出生点
 * @author yanmaoyuan
 *
 */
class StartPoint {
	public int state;// 该出生点是否正在使用?
	public int x, z;// 出生点坐标

	public StartPoint() {
		state = x = z = 0;
	}

	public StartPoint(int state, int x, int z) {
		this.state = state;
		this.x = x;
		this.z = z;
	}
}