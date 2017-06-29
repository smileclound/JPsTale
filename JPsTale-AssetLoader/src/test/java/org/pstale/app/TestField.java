package org.pstale.app;

import org.pstale.utils.ModelFactory;

import com.jme3.scene.Node;
import com.jme3.scene.plugins.smd.SMDTYPE;
import com.jme3.scene.plugins.smd.SmdKey;
import com.jme3.scene.plugins.smd.SmdLoader;

/**
 * 测试加载地图
 * 
 * @author yanmaoyuan
 *
 */
public class TestField extends TestBase {

    @Override
    public void init() {
        // 使用无光材质
        SmdLoader.USE_LIGHT = false;

        // 设置模型工厂
        String smd = ModelFactory.changeName("Field/forest/fore-1.ASE", "smd");

        // 加载地图
        Node model = (Node) assetManager.loadAsset(new SmdKey(smd, SMDTYPE.STAGE3D_VISUAL));
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
