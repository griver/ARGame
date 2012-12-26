package argame;

public class GameObject {
    private BoudingBox boudingBox;
    private Vector position;
    private Vector velocity;

    public BoudingBox getBoudingBox() {
        return boudingBox;
    }

    public GameObject(BoudingBox box) {
        this.boudingBox = box;
    }

    boolean collidesWith(GameObject gameObject, Vector collisionPoint) {
        return false;
    }

    public void setVelocity(Vector vector) {
        this.velocity = vector;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }

    public Vector getPosition() {
        return position;
    }
}
