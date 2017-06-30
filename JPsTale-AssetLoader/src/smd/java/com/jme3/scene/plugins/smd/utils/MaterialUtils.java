package com.jme3.scene.plugins.smd.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jme3.asset.AssetManager;
import com.jme3.asset.TextureKey;
import com.jme3.material.Material;
import com.jme3.material.RenderState;
import com.jme3.material.RenderState.BlendMode;
import com.jme3.material.RenderState.FaceCullMode;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.plugins.smd.material._Material;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture.WrapMode;

/**
 * 根据解析出来的数据，生成jME3的材质。
 * 
 * @author yanmaoyuan
 *
 */
public class MaterialUtils {

    static Logger logger = LoggerFactory.getLogger(MaterialUtils.class);
    
    private AssetManager assetManager;
    private String folder = "/";
    
    public MaterialUtils(AssetManager assetManager) {
        this.assetManager = assetManager;
    }
    
    /**
     * 设置资源目录
     * @param folder
     */
    public void setFolder(String folder) {
        this.folder = folder;
    }
    
    /**
     * 获得文件名
     * 
     * @param line
     * @return
     */
    private String changeName(String line) {
        line = line.replaceAll("\\\\", "/");
        int index = line.lastIndexOf("/");
        if (index != -1) {
            line = line.substring(index + 1);
        }
        return line;
    }
    
    /**
     * 改变文件名后缀。
     * 
     * @param orgin
     * @param ext
     * @return
     */
    public static String changeExt(final String orgin, final String ext) {
        String path = orgin;
        path = path.replaceAll("\\\\", "/");

        int idx = path.lastIndexOf(".") + 1;
        String dest = path.substring(0, idx) + ext;

        return dest;
    }
    
    /**
     * 创建纹理
     * 
     * @param name
     */
    public Texture createTexture(String name) {
        name = changeName(name);

        Texture texture = null;
        try {
            TextureKey texKey = new TextureKey(folder + name);
            texKey.setGenerateMips(true);
            texture = assetManager.loadTexture(texKey);
            texture.setWrap(WrapMode.Repeat);
            texture.setAnisotropicFilter(4);
        } catch (Exception ex) {
            texture = assetManager.loadTexture("Common/Textures/MissingTexture.png");
            texture.setWrap(WrapMode.EdgeClamp);
        }
        return texture;
    }

    /**
     * 创建感光材质
     * 
     * @param m
     * @return
     */
    public Material createLightMaterial(_Material m) {
        Material mat = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
        mat.setColor("Diffuse", new ColorRGBA(m.Diffuse.r, m.Diffuse.g, m.Diffuse.b, 1));
        mat.setColor("Ambient", new ColorRGBA(1f, 1f, 1f, 1f));
        mat.setColor("Specular", new ColorRGBA(0, 0, 0, 1));
        // mat.setBoolean("UseMaterialColors", true);

        // 设置贴图
        if (m.TextureCounter > 0) {
            mat.setTexture("DiffuseMap", createTexture(m.smTexture[0].Name));
        }
        if (m.TextureCounter > 1) {
            mat.setBoolean("SeparateTexCoord", true);
            mat.setTexture("LightMap", createTexture(m.smTexture[1].Name));
        }

        return mat;
    }

    /**
     * 创建一个忽略光源的材质。
     * 
     * @param m
     * @return
     */
    public Material createMiscMaterial(_Material m) {
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        // mat.setColor("Color", new ColorRGBA(m.Diffuse.r, m.Diffuse.g,
        // m.Diffuse.b, 1));
        mat.setColor("Color", ColorRGBA.White);

        // 设置贴图
        if (m.TextureCounter > 0) {
            mat.setTexture("ColorMap", createTexture(m.smTexture[0].Name));
        }
        if (m.TextureCounter > 1) {
            mat.setBoolean("SeparateTexCoord", true);
            mat.setTexture("LightMap", createTexture(m.smTexture[1].Name));
        }

        return mat;
    }

    /**
     * 创建一个匀速切换帧的材质。
     * 
     * @param m
     * @return
     */
    public Material createShiftMaterial(_Material m) {
        Material mat = new Material(assetManager, "Shader/Misc/Shift.j3md");

        // 画面的切换时间间隔
        float ShiftSpeed = (1 << m.Shift_FrameSpeed) / 1000f;
        mat.setFloat("ShiftSpeed", ShiftSpeed);

        // 设置贴图
        Texture tex;
        for (int i = 0; i < m.AnimTexCounter; i++) {
            tex = createTexture(m.smAnimTexture[i].Name);
            mat.setTexture("Tex" + (i + 1), tex);
        }

        return mat;
    }

    /**
     * 创建一个卷轴动画材质。
     * 
     * @param m
     * @return
     */
    public Material createScrollMaterial(_Material m) {
        Material mat = new Material(assetManager, "Shader/Misc/Scroll.j3md");

        // 画面的卷动速度
        float speed = 1f;

        int n = m.TextureFormState[0];
        if (n >= 6 && n <= 14) {
            speed = 15 - n;
        }

        if (n >= 15 && n <= 18) {
            int factor = 18 - n + 4;
            speed = (128 >> factor) / 256f;
        }

        mat.setFloat("Speed", speed);

        // 设置贴图
        Texture tex = createTexture(m.smTexture[0].Name);
        mat.setTexture("ColorMap", tex);

        if (m.TextureCounter > 1) {
            mat.setBoolean("SeparateTexCoord", true);
            mat.setTexture("LightMap", createTexture(m.smTexture[1].Name));
        }

        return mat;
    }

    /**
     * 创建一个原地转圈的动画材质。 Water动画专用
     * 
     * @param m
     * @return
     */
    public Material createRoundMaterial(_Material m) {
        Material mat = new Material(assetManager, "Shader/Misc/Round.j3md");

        // 设置贴图
        Texture tex = createTexture(m.smTexture[0].Name);
        mat.setTexture("ColorMap", tex);

        if (m.TextureCounter > 1) {
            mat.setBoolean("SeparateTexCoord", true);
            mat.setTexture("LightMap", createTexture(m.smTexture[1].Name));
        }

        return mat;
    }

    /**
     * 设置材质的RenderState
     * 
     * @param m
     * @param mat
     */
    public void setRenderState(_Material m, Material mat) {
        RenderState rs = mat.getAdditionalRenderState();

        switch (m.BlendType) {
        case 0:// SMMAT_BLEND_NONE
            rs.setBlendMode(BlendMode.Off);
            break;
        case 1:// SMMAT_BLEND_ALPHA
            rs.setBlendMode(BlendMode.Alpha);
            break;
        case 2:// SMMAT_BLEND_COLOR
            rs.setBlendMode(BlendMode.Color);
            break;
        case 3:// SMMAT_BLEND_SHADOW
            break;
        case 4:// SMMAT_BLEND_LAMP
            rs.setBlendMode(BlendMode.Additive);
            break;
        case 5:// SMMAT_BLEND_ADDCOLOR
            rs.setBlendMode(BlendMode.Additive);
            break;
        case 6:
            break;
        default:
            logger.info("Unknown BlendType=" + m.BlendType);
        }
        ;

        if (m.TwoSide == 1) {
            rs.setFaceCullMode(FaceCullMode.Off);
        }

        if (m.TextureType == 0x0001) {
            // 动画默认显示2面
            rs.setFaceCullMode(FaceCullMode.Off);
        }

        // 透明物体
        if (m.MapOpacity != 0 || m.Transparency != 0) {
            // 这个值设置得稍微大一些，这样草、花等图片的边缘就会因为透明度不够而过滤掉像素。
            mat.setFloat("AlphaDiscardThreshold", 0.75f);
            // 虽然已经过时，但是还是写上以防不测。
            // rs.setAlphaTest(true);
            // rs.setAlphaFallOff(0.6f);
            rs.setDepthWrite(true);
            rs.setDepthTest(true);
            rs.setColorWrite(true);

            // 透明物体不裁剪面
            rs.setFaceCullMode(FaceCullMode.Off);
        }
    }

}
