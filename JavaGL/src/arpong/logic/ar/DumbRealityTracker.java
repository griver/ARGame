package arpong.logic.ar;

import arpong.logic.primitives.Vector;

import java.util.HashMap;
import java.util.Map;

public class DumbRealityTracker implements RealityTracker {

    public void updatePosition(int realWorldObjectId, Vector position) {
        updatePosition(getObjectById(realWorldObjectId), position);
    }

    public void updatePosition(TrackableObject object, Vector position) {
        if (object != null) {
            object.setPosition(position);
        }
    }

    @Override
    public void register(int realWorldObjectId, TrackableObject object) {
        trackableObjectById.put(realWorldObjectId, object);
    }

    public TrackableObject getObjectById(int realWorldObjectId) {
        return trackableObjectById.get(realWorldObjectId);
    }

    private Map<Integer, TrackableObject> trackableObjectById = new HashMap<Integer, TrackableObject>();
}
