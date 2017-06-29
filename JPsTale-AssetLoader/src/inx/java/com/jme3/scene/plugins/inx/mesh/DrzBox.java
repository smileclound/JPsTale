package com.jme3.scene.plugins.inx.mesh;

import com.jme3.math.Vector3f;
public class DrzBox
{
    public Vector3f BoxMin;
    public Vector3f BoxMax;

    public DrzBox()
    {
         BoxMin = new Vector3f();
         BoxMax = new Vector3f();
    }

    public DrzBox(float _min_x, float _min_y, float _min_z, float _max_x, float _max_y, float _max_z)
    {
        BoxMin = new Vector3f(_min_x, _min_y, _min_z);
        BoxMax = new Vector3f(_max_x, _max_y, _max_z);
    }

    public DrzBox(Vector3f min, Vector3f max)
    {
        BoxMin = new Vector3f(min.x, min.y, min.z);
        BoxMax = new Vector3f(max.x, max.y, max.z);
    }

    public void ResizeBoxByValue(Vector3f CheckValue)
    {
        if (CheckValue.x < BoxMin.x) { BoxMin.x = CheckValue.x; }
        if (CheckValue.x > BoxMax.x) { BoxMax.x = CheckValue.x; }

        if (CheckValue.y < BoxMin.y) { BoxMin.y = CheckValue.y; }
        if (CheckValue.y > BoxMax.y) { BoxMax.y = CheckValue.y; }

        if (CheckValue.z < BoxMin.z) { BoxMin.z = CheckValue.z; }
        if (CheckValue.z > BoxMax.z) { BoxMax.z = CheckValue.z; }
    }

    public void SetMaxValues()
    {
        BoxMin.x = Float.MAX_VALUE;
        BoxMin.y = Float.MAX_VALUE;
        BoxMin.z = Float.MAX_VALUE;

        BoxMax.x = Float.MIN_NORMAL;
        BoxMax.y = Float.MIN_NORMAL;
        BoxMax.z = Float.MIN_NORMAL;
    }

    public boolean ContainPoint(Vector3f v)
    {
        if (
            ( (v.x - BoxMin.x) * (v.x - BoxMax.x) <= 0) &&
            ( (v.y - BoxMin.y) * (v.y - BoxMax.y) <= 0) && 
            ( (v.z - BoxMin.z) * (v.z - BoxMax.z) <= 0)
            )
            return true;

        return false;
    }

    public Vector3f GetBoxCenter()
    {
        return new Vector3f( (BoxMax.x - BoxMin.x) / 2.0f + BoxMin.x,
                            (BoxMax.y - BoxMin.y) / 2.0f + BoxMin.y,
                            (BoxMax.z - BoxMin.z) / 2.0f + BoxMin.z);
    }
}
