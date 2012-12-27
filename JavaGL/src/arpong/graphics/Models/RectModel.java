package arpong.graphics.Models;

import android.opengl.GLES20;
import arpong.graphics.Utils.Buffers;
import arpong.graphics.StaticModel;

import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

public class RectModel extends StaticModel {
    private static float coords[] = {
        -1.0f, -1.0f, 0.0f,
        1.0f, -1.0f, 0.0f,
        -1.0f, 1.0f, 0.0f,
        1.0f, 1.0f, 0.0f
    };

    private static short indices[] = {
        2, 0, 1,
        1, 3, 2
    };

    public RectModel() {
        super(indices.length);

        int[] buffers = getBuffers();

        FloatBuffer vertsBuffer = Buffers.createFloatBuffer(coords);
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, buffers[BUFFER_VERTS]);
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, coords.length * Float.SIZE / 8, vertsBuffer, GLES20.GL_STATIC_DRAW);

        ShortBuffer indicesBuffer = Buffers.createShortBuffer(indices);
        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, buffers[BUFFER_INDICES]);
        GLES20.glBufferData(GLES20.GL_ELEMENT_ARRAY_BUFFER, indices.length * Short.SIZE / 8, indicesBuffer, GLES20.GL_STATIC_DRAW);
    }
}
