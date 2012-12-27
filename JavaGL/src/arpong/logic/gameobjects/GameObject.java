package arpong.logic.gameobjects;

import arpong.logic.primitives.BoundingBox;
import arpong.logic.primitives.Vector;

public class GameObject {
    private BoundingBox boundingBox;
    private Vector position;
    private Vector velocity;

    public BoundingBox getBoundingBox() {
        return boundingBox;
    }

    public GameObject(BoundingBox box) {
        this.boundingBox = box;
    }

    public Vector collidesWith(GameObject gameObject) {
        // TODO: desperately need better collision point detection
        if (getBoundingBox().collidesWith(gameObject.getBoundingBox())) {
            return gameObject.getPosition();
        }
        return null;
    }

    public void setVelocity(Vector vector) {
        this.velocity = vector;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }

    public Vector getVelocity() {
        return velocity;
    }

    public Vector getPosition() {
        return position;
    }

    public void move() {
        setPosition(getPosition().plus(getVelocity()));
    }
}
