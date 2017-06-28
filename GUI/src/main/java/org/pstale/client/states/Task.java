package org.pstale.client.states;

import java.util.concurrent.Callable;

public abstract class Task implements Callable<Void> {

	public float pct = 0f;// 进度百分比
	public String msg = "Loading..";// 参数
	
}
