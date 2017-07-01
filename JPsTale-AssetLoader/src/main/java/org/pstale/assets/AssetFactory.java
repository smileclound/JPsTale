package org.pstale.assets;

import static com.jme3.scene.plugins.smd.SMDTYPE.MODELINFO_MODEL;
import static com.jme3.scene.plugins.smd.SMDTYPE.PAT3D_BIP;
import static com.jme3.scene.plugins.smd.SMDTYPE.PAT3D_VISUAL;
import static org.pstale.constants.SceneConstants.scale;

import java.io.File;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.pstale.assets.utils.AssetNameUtils;
import org.pstale.assets.utils.SceneBuilder;
import org.pstale.entity.field.RespawnList;
import org.pstale.entity.field.StartPoint;
import org.pstale.entity.item.ItemInfo;
import org.pstale.utils.FileLocator;

import com.jme3.asset.AssetManager;
import com.jme3.asset.plugins.ClasspathLocator;
import com.jme3.audio.plugins.WAVLoader;
import com.jme3.material.Material;
import com.jme3.material.RenderState.FaceCullMode;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.Node;
import com.jme3.scene.SceneGraphVisitor;
import com.jme3.scene.Spatial;
import com.jme3.scene.plugins.inx.AnimateModel;
import com.jme3.scene.plugins.smd.SMDTYPE;
import com.jme3.scene.plugins.smd.SmdKey;
import com.jme3.scene.plugins.smd.SmdLoader;
import com.jme3.scene.plugins.smd.animation.PAT3D;
import com.jme3.scene.plugins.smd.stage.Stage;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Quad;
import com.jme3.script.plugins.character.CharInfoLoader;
import com.jme3.script.plugins.character.Monster;
import com.jme3.script.plugins.field.CharacterTransform;
import com.jme3.script.plugins.field.SpcLoader;
import com.jme3.script.plugins.field.SpmLoader;
import com.jme3.script.plugins.field.SppLoader;
import com.jme3.script.plugins.item.ItemLoader;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture.WrapMode;

/**
 * 模型工厂
 * 
 * @author yanmaoyuan
 *
 */
public class AssetFactory {

    static Logger log = Logger.getLogger(AssetFactory.class);

    static AssetManager assetManager;

    public static void setAssetManager(final AssetManager manager) {
        assetManager = manager;
        assetManager.registerLoader(SmdLoader.class, "smd", "smb", "inx");
        assetManager.registerLoader(WAVLoader.class, "bgm");
        assetManager.registerLoader(SpcLoader.class, "spc");
        assetManager.registerLoader(SpmLoader.class, "spm");
        assetManager.registerLoader(SppLoader.class, "spp");
        assetManager.registerLoader(CharInfoLoader.class, "inf", "npc");
        assetManager.registerLoader(ItemLoader.class, "txt");

        // 注册资源加载路径
        assetManager.registerLocator("/", ClasspathLocator.class);
        assetManager.registerLocator("/", FileLocator.class);
        if (new File("I:/game/PTCN-RPT1.0").exists()) {
            assetManager.registerLocator("I:/game/PTCN-RPT1.0", FileLocator.class);
        } else {
            assetManager.registerLocator("D:/Priston Tale/PTCN3550/PTCN3550", FileLocator.class);
        }
    }

    /**
     * 获得一个刷怪点标记
     * 
     * @return
     */
    public static Spatial loadFlag() {
        Spatial flag;
        try {
            Node wow = AssetFactory.loadStageObj("char/flag/wow.smd", false);
            wow.depthFirstTraversal(new SceneGraphVisitor() {
                @Override
                public void visit(Spatial spatial) {
                    if (spatial instanceof Geometry) {
                        Geometry geom = (Geometry) spatial;
                        geom.getMaterial().getAdditionalRenderState().setFaceCullMode(FaceCullMode.Off);
                    }
                }
            });

            flag = wow;
        } catch (Exception e) {
            log.debug("无法加载旗帜", e);
            flag = new Geometry("flag", new Box(1 / scale, 1 / scale, 1 / scale));
            Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
            mat.setColor("Color", ColorRGBA.Red);
            flag.setMaterial(mat);
        }

        return flag;
    }

    /**
     * 创建一个Loading标记
     * 
     * @return
     */
    public static Spatial getLoadingFlag() {
        Quad quad = new Quad(80, 20);
        Geometry geom = new Geometry("loading", quad);
        Material mat = new Material(assetManager, "Shader/Misc/Scroll.j3md");
        Texture tex = assetManager.loadTexture("Interface/loading.png");
        tex.setWrap(WrapMode.Repeat);
        mat.setTexture("ColorMap", tex);
        mat.setFloat("Speed", 2);
        mat.setColor("Color", ColorRGBA.Magenta);
        geom.setMaterial(mat);

        return geom;
    }

    /**
     * 加载模型的索引文件
     * 
     * @param name
     * @return
     */
    public static AnimateModel loadInx(final String name) {
        String inx = AssetNameUtils.changeExt(name, "inx");
        return (AnimateModel) assetManager.loadAsset(new SmdKey(inx, SMDTYPE.MODELINFO));
    }

    /**
     * 读取地形数据
     * 
     * @param name
     * @return
     */
    public static Stage loadSmdStage(final String name) {
        String smd = AssetNameUtils.changeExt(name, "smd");
        Stage stage = (Stage) assetManager.loadAsset(new SmdKey(smd, SMDTYPE.STAGE3D));
        return stage;
    }

    public static PAT3D loadSmb(final String name) {
        String smb = AssetNameUtils.changeExt(name, "smb");
        PAT3D skeleton = (PAT3D) assetManager.loadAsset(new SmdKey(smb, SMDTYPE.PAT3D));
        return skeleton;
    }

    /**
     * 加载地图
     * 
     * @param name
     * @return
     * @throws Exception
     */
    public static Node loadStage3D(final String name) {
        String smd = AssetNameUtils.changeExt(name, "smd");
        Stage stage = (Stage) assetManager.loadAsset(new SmdKey(smd, SMDTYPE.STAGE3D));
        SceneBuilder.setAssetManager(assetManager);
        return SceneBuilder.buildScene3D(stage, smd);
    }

    public static Mesh loadStage3DMesh(final String name) {
        String smd = AssetNameUtils.changeExt(name, "smd");
        Stage stage = (Stage) assetManager.loadAsset(new SmdKey(smd, SMDTYPE.STAGE3D));
        SceneBuilder.setAssetManager(assetManager);
        return SceneBuilder.buildCollisionMesh(stage);
    }

    public static Node loadStageObj(final String name, final boolean bip) {
        String smd = AssetNameUtils.changeExt(name, "smd");
        return (Node) assetManager.loadAsset(new SmdKey(smd, bip ? PAT3D_BIP : PAT3D_VISUAL));
    }

    public static Node loadNPC(final String name) {
        String inx = AssetNameUtils.changeExt(name, "inx");
        return (Node) assetManager.loadAsset(new SmdKey(inx, MODELINFO_MODEL));
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<StartPoint> loadSpp(final String name) {
        String path = String.format("GameServer/Field/%s.ase.spp", AssetNameUtils.getSimpleName(name));
        try {
            ArrayList<StartPoint> spp = (ArrayList<StartPoint>) assetManager.loadAsset(path);
            return spp;
        } catch (Exception e) {
            return null;
        }
    }

    public static RespawnList loadSpm(final String name) {
        String path = String.format("GameServer/Field/%s.ase.spm", AssetNameUtils.getSimpleName(name));
        try {
            RespawnList creatures = (RespawnList) assetManager.loadAsset(path);
            return creatures;
        } catch (Exception e) {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<CharacterTransform> loadSpc(final String name) {
        String path = String.format("GameServer/Field/%s.ase.spc", AssetNameUtils.getSimpleName(name));
        try {
            ArrayList<CharacterTransform> npcs = (ArrayList<CharacterTransform>) assetManager.loadAsset(path);
            return npcs;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 导入NPC的脚本文件
     * 
     * @param name
     * @return
     */
    public static Monster loadNpcScript(final String name) {
        String path = String.format("GameServer/NPC/%s.npc", AssetNameUtils.getSimpleName(name));

        try {
            Monster info = (Monster) assetManager.loadAsset(path);
            return info;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 导入怪物的脚本文件
     * 
     * @param name
     * @return
     */
    public static Monster loadMonsterScript(final String name) {
        String path = String.format("GameServer/Monster/%s.inf", AssetNameUtils.getSimpleName(name));

        try {
            Monster info = (Monster) assetManager.loadAsset(path);
            return info;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 导入装备的脚本
     * 
     * @param name
     * @return
     */
    public static ItemInfo loadItemScript(String name) {
        String path = String.format("GameServer/OpenItem/%s.txt", AssetNameUtils.getSimpleName(name));

        try {
            ItemInfo info = (ItemInfo) assetManager.loadAsset(path);
            return info;
        } catch (Exception e) {
            return null;
        }
    }

}
