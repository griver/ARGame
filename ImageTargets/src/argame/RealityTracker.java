package argame;

public interface RealityTracker {
    public void updatePosition(int realWorldObjectId, Vector position);
    public void register(int realWorldObjectId, TrackableObject object);
}
