package arpong.graphics;

import android.util.Log;

public class FPSCounter {
    private final long eachMS;
    private final String tag, text;
    private int tickCount = 0;
    private long lastFPS;

    public FPSCounter(long eachMS, String tag, String text) {
        this.eachMS = eachMS;
        this.tag = tag;
        this.text = text;
        lastFPS = System.currentTimeMillis();
    }

    public void update() {
        final long now = System.currentTimeMillis();

        ++tickCount;
        if (now - lastFPS > eachMS) {
            final int fps = (int)((float)tickCount / (now - lastFPS) * 1000.0f);
            Log.d(tag, text + fps);

            lastFPS = now;
            tickCount = 0;
        }
    }

}
