package arpong.graphics;

import android.opengl.GLES20;

public class StaticModel {
    public static final int BUFFER_INDICES = 0;
    public static final int BUFFER_VERTS = 1;
    public static final int NUM_BUFFERS = 2;

    private final int[] buffers = new int[NUM_BUFFERS];
    private int num_indices;

    protected StaticModel(int num_indices) {
        if (num_indices <= 0)
            throw new ZeroIndicesException();

        GLES20.glGenBuffers(NUM_BUFFERS, buffers, 0);
        this.num_indices = num_indices;
    }

    protected final int[] getBuffers() {
        return buffers;
    }

    public final void free() {
        if (num_indices == 0)
            throw new ZeroIndicesException();

        GLES20.glDeleteBuffers(NUM_BUFFERS, buffers, 0);
        for (int i = 0; i < NUM_BUFFERS; ++i)
            this.buffers[i] = 0;

        this.num_indices = 0;
    }

    public final void draw(int[] bindings) {
        if (num_indices == 0)
            throw new ZeroIndicesException();

        int buffer_index;

        buffer_index = BUFFER_VERTS;
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, buffers[buffer_index]);
        GLES20.glVertexAttribPointer(bindings[buffer_index], 3, GLES20.GL_FLOAT, false, 0, 0);

        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, buffers[BUFFER_INDICES]);
        GLES20.glDrawElements(GLES20.GL_TRIANGLES, num_indices, GLES20.GL_UNSIGNED_SHORT, 0);
    }

    public class ZeroIndicesException extends RuntimeException {
        public ZeroIndicesException() {
            super("Zero indices");
        }
    }

}
