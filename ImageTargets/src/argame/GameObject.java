package argame;

public class GameObject {
    private BoudingBox boudingBox;
    public BoudingBox getBoudingBox() {
        return boudingBox;
    }
    boolean collidesWith(GameObject gameObject, Vector collisionPoint) {
        return false;
    }

    public void setVelocity(Vector vector) {
        //To change body of created methods use File | Settings | File Templates.
    }

    public void update() {
        //To change body of created methods use File | Settings | File Templates.
    }
}
