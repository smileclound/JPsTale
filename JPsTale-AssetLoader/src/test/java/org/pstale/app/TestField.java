package org.pstale.app;

import org.pstale.assets.AssetFactory;

import com.jme3.scene.Node;

/**
 * 测试加载地图
 * 
 * @author yanmaoyuan
 *
 */
public class TestField extends TestBase {

    @Override
    public void init() {
        // 加载地图
        Node model = AssetFactory.loadStage3D("Field/forest/fore-1.ASE");
        rootNode.attachChild(model);

        // 缩小并居中
        model.scale(0.01f);
        model.center().move(0, 0, 0);
    }

    public static void main(String[] args) {
        TestField app = new TestField();
        app.start();
    }

}
