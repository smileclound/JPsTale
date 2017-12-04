package com.jme3.scene.plugins.smd.math;

import java.io.IOException;

import org.pstale.assets.Flyweight;

import com.jme3.math.Matrix4f;
import com.jme3.util.LittleEndien;

/**
 * size = 64
 */
public class Matrix4D extends Flyweight {
    public int _11, _12, _13, _14;
    public int _21, _22, _23, _24;
    public int _31, _32, _33, _34;
    public int _41, _42, _43, _44;

    public Matrix4f mat4;
    public Matrix4f mat4I;
    public Matrix4D() {
        _11 = 1;
        _12 = 0;
        _13 = 0;
        _14 = 0;
        _21 = 0;
        _22 = 1;
        _23 = 0;
        _24 = 0;
        _31 = 0;
        _32 = 0;
        _33 = 1;
        _34 = 0;
        _41 = 0;
        _42 = 0;
        _43 = 0;
        _44 = 1;
    }

    /**
     * 这个矩阵的特征值是256，所有元素除以256后的行列式是1。
     * 
     * @param init
     */
    public void loadData(LittleEndien in) throws IOException {
        _11 = in.readInt();
        _12 = in.readInt();
        _13 = in.readInt();
        _14 = in.readInt();
        _21 = in.readInt();
        _22 = in.readInt();
        _23 = in.readInt();
        _24 = in.readInt();
        _31 = in.readInt();
        _32 = in.readInt();
        _33 = in.readInt();
        _34 = in.readInt();
        _41 = in.readInt();
        _42 = in.readInt();
        _43 = in.readInt();
        _44 = in.readInt();
        
        float m00 = _11 / 256f, m01 = _21 / 256f, m02 = _31 / 256f, m03 = _41 / 256f;
        float m10 = _12 / 256f, m11 = _22 / 256f, m12 = _32 / 256f, m13 = _42 / 256f;
        float m20 = _13 / 256f, m21 = _23 / 256f, m22 = _33 / 256f, m23 = _43 / 256f;
        float m30 = _14 / 256f, m31 = _24 / 256f, m32 = _34 / 256f, m33 = _44 / 256f;
        mat4 = new Matrix4f(m00, m01, m02, m03, m10, m11, m12, m13, m20, m21, m22, m23, m30, m31, m32, m33);
        if (mat4.determinant() != 0)
            mat4I = mat4.invert();
    }
}