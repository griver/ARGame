package arpong.graphics.Models;

import android.opengl.GLES20;
import arpong.graphics.StaticModel;
import arpong.graphics.Utils.Buffers;

import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

public class PaddleModel extends StaticModel {
    private static final float box[] = {
        // FRONT
        -1.0f, -1.0f,  1.0f,
        1.0f, -1.0f,  1.0f,
        -1.0f,  1.0f,  1.0f,
        1.0f,  1.0f,  1.0f,
        // BACK
        -1.0f, -1.0f, -1.0f,
        -1.0f,  1.0f, -1.0f,
        1.0f, -1.0f, -1.0f,
        1.0f,  1.0f, -1.0f,
        // LEFT
        -1.0f, -1.0f,  1.0f,
        -1.0f,  1.0f,  1.0f,
        -1.0f, -1.0f, -1.0f,
        -1.0f,  1.0f, -1.0f,
        // RIGHT
        1.0f, -1.0f, -1.0f,
        1.0f,  1.0f, -1.0f,
        1.0f, -1.0f,  1.0f,
        1.0f,  1.0f,  1.0f,
        // TOP
        -1.0f,  1.0f,  1.0f,
        1.0f,  1.0f,  1.0f,
        -1.0f,  1.0f, -1.0f,
        1.0f,  1.0f, -1.0f,
        // BOTTOM
        -1.0f, -1.0f,  1.0f,
        -1.0f, -1.0f, -1.0f,
        1.0f, -1.0f,  1.0f,
        1.0f, -1.0f, -1.0f,
    };
    private static final short indicesBase[] = {
        2, 0, 1,
        1, 3, 2
    };

    public PaddleModel() {
        super(36);

        final short[] indices = new short[box.length];
        for (int side = 0; side < 6; ++side) {
            for (int index = 0; index < 6; ++index) {
                indices[side * 6 + index] = (short)(side * 4 + indicesBase[index]);
            }

            final int[] buffers = getBuffers();

            FloatBuffer vertsBuffer = Buffers.createFloatBuffer(box);
            GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, buffers[BUFFER_VERTS]);
            GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, box.length * Float.SIZE / 8, vertsBuffer, GLES20.GL_STATIC_DRAW);

            ShortBuffer indicesBuffer = Buffers.createShortBuffer(indices);
            GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, buffers[BUFFER_INDICES]);
            GLES20.glBufferData(GLES20.GL_ELEMENT_ARRAY_BUFFER, indices.length * Short.SIZE / 8, indicesBuffer, GLES20.GL_STATIC_DRAW);
        }
    }
}
