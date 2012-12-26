package arpong.logic.primitives;

public class BoudingBox {
    private Vector lowerLeft;
    private Vector upperRight;

    public BoudingBox(Vector lowerLeft, Vector upperRight) {
        this.lowerLeft = lowerLeft;
        this.upperRight = upperRight;
    }

    public Vector getLowerLeft() {
        return lowerLeft;
    }

    public Vector getUpperRight() {
        return upperRight;
    }
}
