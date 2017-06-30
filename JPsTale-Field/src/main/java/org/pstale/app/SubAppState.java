package org.pstale.app;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Node;

/**
 * 子场景状态机。这个AppState向游戏根节点添加了另外2个子节点，其子类可以直接使用。
 * 
 * @author yanmaoyuan
 *
 */
public abstract class SubAppState extends BaseAppState{

    protected Node rootNode;
    protected Node guiNode;

    public SubAppState() {
        rootNode = new Node("subRootNode" + System.currentTimeMillis());
        guiNode = new Node("subGuiNode" + System.currentTimeMillis());
    }

    @Override
    protected void cleanup(Application app) {
    }

    @Override
    protected void onEnable() {
        SimpleApplication simpleApp = (SimpleApplication) getApplication();
        simpleApp.getRootNode().attachChild(rootNode);
        simpleApp.getGuiNode().attachChild(guiNode);
    }

    @Override
    protected void onDisable() {
        rootNode.removeFromParent();
        guiNode.removeFromParent();
    }

    protected Material defaultMat;

    /**
     * 创建一个简单的材质
     * 
     * @param color
     * @return
     */
    protected Material getMaterial(ColorRGBA color) {
        if (defaultMat == null) {
            AssetManager assetManager = getApplication().getAssetManager();
            Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
            mat.setColor("Color", color);
            defaultMat = mat;
        }
        return defaultMat;
    }
}
