package argame;

import java.util.Map;

public class DumbRealityTracker implements RealityTracker {

    public void updatePosition(int realWorldObjectId, Vector position) {
        TrackableObject object = getObjectById(realWorldObjectId);
        if (object != null) {
            object.setPosition(position);
        }
    }

    @Override
    public void register(int realWorldObjectId, TrackableObject object) {
        trackableObjectById.put(realWorldObjectId, object);
    }

    private TrackableObject getObjectById(int realWorldObjectId) {
        return trackableObjectById.get(realWorldObjectId);
    }

    private Map<Integer, TrackableObject> trackableObjectById;
}
