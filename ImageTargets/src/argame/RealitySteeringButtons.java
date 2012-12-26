package argame;

public class RealitySteeringButtons extends DumbRealityTracker {
    void steerObject(TrackableObject steeredObject, SteeringDirection direction) {
        Vector position = steeredObject.getPosition();
        steeredObject.setPosition(new Vector(position.getX(), position.getY() + 5));
    }
}
