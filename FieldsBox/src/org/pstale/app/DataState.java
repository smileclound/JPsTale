package org.pstale.app;

import org.pstale.fields.Field;

import com.jme3.app.Application;
import com.jme3.app.state.BaseAppState;
import com.jme3.asset.maxase.FileLocator;

/**
 * 这个状态机仅用于维持地区数据
 * 
 * @author yanmaoyuan
 * 
 */
public class DataState extends BaseAppState {

	private String serverRoot;
	private String clientRoot;
	private Field[] fields;

	public DataState(String serverRoot, String clientRoot, Field[] fields) {
		this.serverRoot = serverRoot;
		this.clientRoot = clientRoot;
		this.fields = fields;
	}

	@Override
	protected void initialize(Application app) {
		// 初始化客户端资源根目录
		if (clientRoot != null) {
			app.getAssetManager()
					.registerLocator(clientRoot, FileLocator.class);
		}
	}

	@Override
	protected void cleanup(Application app) {
	}

	@Override
	protected void onEnable() {
	}

	@Override
	protected void onDisable() {
	}

	public String getServerRoot() {
		return serverRoot;
	}

	public String getClientRoot() {
		return clientRoot;
	}

	public Field[] getFields() {
		return fields;
	}
}
