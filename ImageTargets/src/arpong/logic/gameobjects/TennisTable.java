package arpong.logic.gameobjects;

import arpong.logic.ar.VirtualRealityRenderer;
import arpong.logic.primitives.BoundingBox;
import arpong.logic.primitives.Vector;

public class TennisTable extends GameObject {
    public TennisTable(VirtualRealityRenderer renderer) {
        super(new BoundingBox(new Vector(0, 0), new Vector(500, 300)));
    }

    public TableWall wallForPoint(Vector collisionPoint) {
        float x = collisionPoint.getX();
        float y = collisionPoint.getY();
        BoundingBox tableBox = getBoundingBox();

        TableWall wall = TableWall.LEFT_WALL;
        if (y >= tableBox.getUpperRight().getY()) {
            wall = TableWall.UPPER_WALL;
        } else if (x >= tableBox.getUpperRight().getX()) {
            wall = TableWall.RIGHT_WALL;
        } else if (y <= tableBox.getLowerLeft().getY()) {
            wall = TableWall.LOWER_WALL;
        } else if (x <= tableBox.getLowerLeft().getX()) {
            wall = TableWall.LEFT_WALL;
        }

        return wall;
    }

    public void incrementSecondPlayerScore() {
        //To change body of created methods use File | Settings | File Templates.
    }

    public void incrementFirstPlayerScore() {
        //To change body of created methods use File | Settings | File Templates.
    }
}
