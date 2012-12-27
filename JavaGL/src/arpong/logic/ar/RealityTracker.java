package arpong.logic.ar;

import arpong.logic.primitives.Vector;

public interface RealityTracker {
    public void updatePosition(int realWorldObjectId, Vector position);
    public void updatePosition(TrackableObject object, Vector position);
    public void register(int realWorldObjectId, TrackableObject object);

    // should it really be public on the interface level?
    public TrackableObject getObjectById(int realWorldObjectId);
}
