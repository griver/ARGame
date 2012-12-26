package arpong.logic.gameobjects;

import arpong.logic.ar.VirtualRealityRenderer;
import arpong.logic.primitives.BoudingBox;
import arpong.logic.primitives.Vector;

public class TennisTable extends GameObject {
    public TennisTable(VirtualRealityRenderer renderer) {
        super(new BoudingBox(new Vector(0, 0), new Vector(500, 300)));
    }

    public TableWall wallForPoint(Vector collisionPoint) {
        return TableWall.LEFT_WALL;
    }

    public void incrementSecondPlayerScore() {
        //To change body of created methods use File | Settings | File Templates.
    }

    public void incrementFirstPlayerScore() {
        //To change body of created methods use File | Settings | File Templates.
    }
}
