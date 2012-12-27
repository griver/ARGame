package arpong.logic.ar;

import arpong.logic.primitives.Vector;

import java.util.*;

public class RandomRealityTracker implements RealityTracker {
    private Timer realityEventTimer = new Timer();

    public RandomRealityTracker() {
        TimerTask updatePositionsTask = new UpdatePositionsTask();
        realityEventTimer.scheduleAtFixedRate(updatePositionsTask, 3000, 400);
    }

    private class UpdatePositionsTask extends TimerTask {
        private Random rand = new Random();

        private float floatInRange(float min, float max) {
            return rand.nextFloat() * (max - min) + min;
        }
        @Override
        public void run() {
            for (TrackableObject object : trackableObjectById.values()) {
                float my = floatInRange(80f, 220f);
                Vector position = object.getPosition();
                updatePosition(object, new Vector(position.getX(),
                                                  my));
            }
        }
    }

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
