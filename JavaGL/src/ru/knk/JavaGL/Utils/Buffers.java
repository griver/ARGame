package ru.knk.JavaGL.Utils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

public class Buffers {
    public static FloatBuffer createFloatBuffer(float data[]) {
        ByteBuffer vbb = ByteBuffer.allocateDirect(data.length * Float.SIZE / 8);
        vbb.order(ByteOrder.nativeOrder());
        FloatBuffer outBuffer = vbb.asFloatBuffer();
        outBuffer.put(data).position(0);
        return outBuffer;
    }

    public static ShortBuffer createShortBuffer(short data[]) {
        ByteBuffer vbb = ByteBuffer.allocateDirect(data.length * Short.SIZE / 8);
        vbb.order(ByteOrder.nativeOrder());
        ShortBuffer outBuffer = vbb.asShortBuffer();
        outBuffer.put(data).position(0);
        return outBuffer;
    }
}
