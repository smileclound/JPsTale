package org.pstale.entity.field;

import java.util.ArrayList;
import java.util.List;

import com.jme3.math.Vector3f;

public class WarpGate {
    private Vector3f position;// 注意，这里采用的是OpenGL的右手坐标系。
    private int height, size;
    private List<FieldGate> outGate;
    private int limitLevel;
    private int specialEffect;

    public WarpGate(Vector3f position, int height, int size, int limit, int effect) {
        this.position = position;
        this.height = height;
        this.size = size;
        this.limitLevel = limit;
        this.specialEffect = effect;

        outGate = new ArrayList<FieldGate>();
    }

    public Vector3f getPosition() {
        return position;
    }

    public int getHeight() {
        return height;
    }

    public int getSize() {
        return size;
    }

    public List<FieldGate> getOutGate() {
        return outGate;
    }

    public int getLimitLevel() {
        return limitLevel;
    }

    public int getSpecialEffect() {
        return specialEffect;
    }

    @Override
    public String toString() {
        return "WarpGate [position=" + position + ", height=" + height + ", size=" + size + ", outGate=" + outGate
                + ", limitLevel=" + limitLevel + ", specialEffect=" + specialEffect + "]";
    }

}
