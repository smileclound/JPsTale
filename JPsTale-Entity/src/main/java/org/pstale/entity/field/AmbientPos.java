package org.pstale.entity.field;

import com.jme3.math.Vector3f;

public class AmbientPos {
    private Vector3f position;
    private int round;
    private int AmbientNum;

    public AmbientPos(Vector3f position, int round, int ambientNum) {
        this.position = position;
        this.round = round;
        this.AmbientNum = ambientNum;
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public int getAmbientNum() {
        return AmbientNum;
    }

    public void setAmbientNum(int ambientNum) {
        AmbientNum = ambientNum;
    }

}
