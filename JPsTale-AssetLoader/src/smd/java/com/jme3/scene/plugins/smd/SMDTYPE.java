package com.jme3.scene.plugins.smd;

/**
 * 精灵的3D数据类型
 * 
 * @author yanmaoyuan
 *
 */
public enum SMDTYPE {
    /**
     * 这种类型的smd文件保存了地图的数据。
     */
    STAGE3D,
    /**
     * 这种文件仅存储了PAT3D结构体中的骨骼动画，不包含任何材质。 后缀名为smb
     */
    PAT3D
}