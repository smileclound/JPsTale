package org.pstale.app;

import static org.pstale.entity.item.ItemConstant.itemDataBase;

import org.pstale.entity.item.Item;
import org.pstale.utils.ModelFactory;

import com.jme3.material.RenderState.BlendMode;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Node;
import com.jme3.scene.control.AbstractControl;
import com.jme3.ui.Picture;

public class TestDropItem extends TestBase {

    @Override
    public void init() {
        String dorpItem = "image/Sinimage/Items/DropItem/it%s.smd";
        String sinImage = "image/Sinimage/Items/%s/it%s.bmp";

        Item item = itemDataBase[21];

        String cat = "PS104";

        // 查找装备
        for (int cnt = 0; cnt < itemDataBase.length; cnt++) {
            if (itemDataBase[cnt].category.equalsIgnoreCase(cat)) {
                item = itemDataBase[cnt];
                System.out.println(cnt);
                break;
            }
        }

        Node node = ModelFactory.loadStageObj(String.format(dorpItem, item.dorpItem), false);
        node.scale(1f);
        node.addControl(new AbstractControl() {
            @Override
            protected void controlUpdate(float tpf) {
                spatial.rotate(0, tpf, 0);
            }

            @Override
            protected void controlRender(RenderManager rm, ViewPort vp) {
            }
        });
        rootNode.attachChild(node);

        Picture picture = new Picture("sinImage");
        picture.setWidth(item.width);
        picture.setHeight(item.height);
        picture.setImage(assetManager, String.format(sinImage, item.folder, item.category), false);
        picture.getMaterial().getAdditionalRenderState().setBlendMode(BlendMode.Color);
        guiNode.attachChild(picture);

        // 居中
        float x = (cam.getWidth() - item.width) * 0.5f;
        float y = (cam.getHeight() - item.height) * 0.5f;
        picture.setLocalTranslation(x, y, 1);

        flyCam.setMoveSpeed(10f);
    }

    public static void main(String[] args) {
        new TestDropItem().start();
    }

}
