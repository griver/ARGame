package arpong.logic.primitives;

public class BoundingBox {
    private Vector lowerLeft;
    private Vector upperRight;

    public BoundingBox(Vector lowerLeft, Vector upperRight) {
        this.lowerLeft = lowerLeft;
        this.upperRight = upperRight;
    }

    public Vector getLowerLeft() {
        return lowerLeft;
    }

    public Vector getUpperRight() {
        return upperRight;
    }

    public boolean isPointInside(Vector point) {
        float x = point.getX();
        float y = point.getY();

        boolean xIsInside = x >= getLowerLeft().getX() && x <= getUpperRight().getX();
        boolean yIsInside = y >= getLowerLeft().getY() && y <= getUpperRight().getY();
        if (xIsInside && yIsInside) {
            return true;
        }
        return false;
    }

    public Vector denormilizedDiagonal() {
        Vector t = getLowerLeft().minus(getUpperRight());
        return new Vector(Math.abs(t.getX()), Math.abs(t.getY()));
    }

    public float diagonal() {
        return Vector.abs(denormilizedDiagonal());
    }

    public boolean collidesWith(BoundingBox box) {
        Vector diag = denormilizedDiagonal();
        float x = getLowerLeft().getX();
        float y = getLowerLeft().getY();
        float oWidth = diag.getX();
        float oHeight = diag.getY();

        Vector boxDiag = box.denormilizedDiagonal();
        float xTwo = box.getLowerLeft().getX();
        float yTwo = box.getLowerLeft().getY();
        float oTwoWidth = boxDiag.getX();
        float oTwoHeight = boxDiag.getY();

        if (x + oWidth < xTwo || x > xTwo + oTwoWidth) {
            return false;
        }
        if (y + oHeight < yTwo || y > yTwo + oTwoHeight) {
            return false;
        }

        return true;
    }

    public Vector center() {
        Vector diag = denormilizedDiagonal();
        return new Vector(diag.getX() / 2.0f, diag.getY() / 2.0f);
    }
}
