package org.pstale.asset.loader;

import java.io.IOException;
import java.io.InputStream;

import org.pstale.asset.base.AbstractLoader;

import com.jme3.scene.Node;

/**
 * 精灵的inx索引文件导入插件
 * @author yanmaoyuan
 *
 */
public class InxLoader extends AbstractLoader {
	protected Node rootNode;

	public Object parse(InputStream inputStream) throws IOException {
		
		ImportInx importInx = new ImportInx(this);
		
		importInx.loadScene(inputStream);
		
		rootNode = importInx.rootNode;
		
		return rootNode;
	}
	
}
