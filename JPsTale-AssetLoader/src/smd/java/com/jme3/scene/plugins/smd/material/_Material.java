package com.jme3.scene.plugins.smd.material;

import java.io.IOException;

import org.pstale.assets.Flyweight;

import com.jme3.scene.plugins.smd.math.ColorRGB3F;
import com.jme3.util.LittleEndien;

/**
 * 材质 size = 320
 * 
 * @author yanmaoyuan
 * 
 */
public class _Material extends Flyweight {
    /**
     * 判断这个面是否被使用。 实际上smd文件中存储的材质都是被用到的材质，否则是不会存储的。 因此判断这个变量并没有实际意义。
     */
    public int InUse;
    /**
     * 纹理的数量。
     */
    public int TextureCounter;
    /**
     * 纹理图片的名称。 对于STAGE3D来说，第1个纹理是DiffuseMap，第2个纹理应该是LightMap。 多余的纹理并不知道有什么用。
     */
    public TEXTURE[] smTexture = new TEXTURE[8];
    public int[] TextureStageState = new int[8];
    public int[] TextureFormState = new int[8];
    public int ReformTexture;

    /**
     * 是否透明 ( TRUE , FALSE )
     */
    public int MapOpacity;

    /**
     * 纹理类型：混色或动画
     * 
     * <pre>
     * #define SMTEX_TYPE_MULTIMIX		0x0000
     * #define SMTEX_TYPE_ANIMATION		0x0001
     * </pre>
     */
    public int TextureType;

    /**
     * 混色方式
     * 
     * <pre>
     * #define SMMAT_BLEND_NONE		0x00
     * #define SMMAT_BLEND_ALPHA		0x01
     * #define SMMAT_BLEND_COLOR		0x02
     * #define SMMAT_BLEND_SHADOW		0x03
     * #define SMMAT_BLEND_LAMP		0x04
     * #define SMMAT_BLEND_ADDCOLOR	0x05
     * #define SMMAT_BLEND_INVSHADOW	0x06
     * </pre>
     */
    public int BlendType;// SMMAT_BLEND_XXXX

    /**
     * TRUE or FALSE
     */
    public int Shade;
    /**
     * 是否两面都显示 TRUE or FALSE
     */
    public int TwoSide; // 剧搁 荤侩 咯何
    public int SerialNum; // 皋飘府倔俊 措茄 绊蜡 锅龋

    /**
     * 材质的颜色
     */
    public ColorRGB3F Diffuse;
    /**
     * 透明度，取值范围(0~1f)。若材质的透明度大于0.2，则这个模型不需要进行碰撞检测。
     */
    public float Transparency;
    /**
     * 发光程度
     */
    public float SelfIllum;

    public int TextureSwap; // 咆胶媚 胶客俏
    public int MatFrame; // 荤侩橇饭烙 ( 荤侩矫 悼扁甫 嘎眠扁 困窃 )
    public int TextureClip; // 胶客俏侩 咆胶媚 努赋蜡公 ( TRUE 搁 咆胶媚 努府俏 倾啊 )

    /**
     * 等于ASE模型中的ScriptState
     * 
     * <pre>
     * sMATS_SCRIPT_WIND
     * sMATS_SCRIPT_WINDX1 // 物体在x轴方向上周期性摆动
     * sMATS_SCRIPT_WINDX2 // 4倍距离
     * sMATS_SCRIPT_WINDZ1 // 物体在Z轴方向上周期性摆动
     * sMATS_SCRIPT_WINDZ2 // 4倍距离
     * sMATS_SCRIPT_WATER // 表面的uv坐标转圈圈
     * sMATS_SCRIPT_NOTPASS // 碰撞，但是不可见
     * sMATS_SCRIPT_PASS // 可以穿过
     * sMATS_SCRIPT_RENDLATTER -> MeshState |= sMATS_SCRIPT_RENDLATTER;
     * sMATS_SCRIPT_CHECK_ICE -> MeshState |= sMATS_SCRIPT_CHECK_ICE;
     * sMATS_SCRIPT_ORG_WATER -> MeshState = sMATS_SCRIPT_ORG_WATER;
     * </pre>
     */
    public int UseState;
    /**
     * 是否进行碰撞检测
     * 
     * <pre>
     * #define SMMAT_STAT_CHECK_FACE	0x00000001
     * </pre>
     */
    public int MeshState;

    public int WindMeshBottom;

    /**
     * 逐帧动画的纹理文件名
     */
    public TEXTURE[] smAnimTexture = new TEXTURE[32];
    /**
     * 动画有几张图NumTex
     */
    public int AnimTexCounter;
    public int FrameMask;// == AnimTexCounter - 1
    /**
     * 动画切换速度。
     */
    public int Shift_FrameSpeed;
    /**
     * SMTEX_AUTOANIMATION = 0x0100
     */
    public int AnimationFrame;

    /**
     * 读取MATERIAL数据结构
     */
    public void loadData(LittleEndien in) throws IOException {
        InUse = in.readInt(); // > 0 表示在使用
        TextureCounter = in.readInt();// 纹理的数量。动画纹理必然只有1张。
        for (int i = 0; i < 8; i++) {
            in.readInt();// *smTexture[8];
        }
        for (int i = 0; i < 8; i++) {
            TextureStageState[i] = in.readInt();
        }
        for (int i = 0; i < 8; i++) {
            TextureFormState[i] = in.readInt();
        }
        ReformTexture = in.readInt();

        MapOpacity = in.readInt();

        TextureType = in.readInt();

        BlendType = in.readInt();

        Shade = in.readInt();
        TwoSide = in.readInt();
        SerialNum = in.readInt();

        Diffuse = new ColorRGB3F();
        Diffuse.loadData(in);

        Transparency = in.readFloat();
        SelfIllum = in.readFloat(); //

        TextureSwap = in.readInt(); //
        MatFrame = in.readInt(); //
        TextureClip = in.readInt(); //

        UseState = in.readInt(); // ScriptState
        MeshState = in.readInt();

        WindMeshBottom = in.readInt();

        // 俊聪皋捞记 咆胶媚 加己
        for (int i = 0; i < 32; i++) {
            in.readInt();// *smAnimTexture[32]
        }
        AnimTexCounter = in.readInt();
        FrameMask = in.readInt(); // NumTex-1
        Shift_FrameSpeed = in.readInt();

        /**
         * 是否自动播放动画 #define SMTEX_AUTOANIMATION 0x100 为0时不自动播放
         */
        AnimationFrame = in.readInt();
    }
}