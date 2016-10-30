package org.pstale.asset.loader;

import java.io.IOException;
import java.io.InputStream;

import org.pstale.asset.anim.DrzAnimation;
import org.pstale.asset.base.AbstractLoader;
import org.pstale.asset.mesh.DrzMesh;

import com.jme3.scene.Node;

public class SmdLoader extends AbstractLoader {
	protected Node rootNode;

	@Override
	public Object parse(InputStream inputStream) throws IOException {

		DrzMesh mesh = new DrzMesh();
		mesh.mAnimation = new DrzAnimation();

		Node node = null;
		
		try {
			// 根据文件长度来判断是舞台还是角色
			if (inputStream.available() > 262752) {// 舞台
				// 首先尝试用舞台Loader加载
				ImportStage importSmd = new ImportStage(this);
				importSmd.loadScene(inputStream);
				node = importSmd.rootNode;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return node;
	}
}
