package ru.knk.JavaGL.Models;

import android.opengl.GLES20;
import ru.knk.JavaGL.Utils.Buffers;
import ru.knk.JavaGL.StaticModel;

import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

public class Sphere extends StaticModel {
    public Sphere(int segments, float radius) {
        super(getNumVerts(segments) * 6);

        final int numVerts = getNumVerts(segments);
        final int[] buffers = getBuffers();

        float[] verts = new float[numVerts * 3];
        loadVerts(segments, radius, verts);
        FloatBuffer vertsBuffer = Buffers.createFloatBuffer(verts);
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, buffers[BUFFER_VERTS]);
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, verts.length * Float.SIZE / 8, vertsBuffer, GLES20.GL_STATIC_DRAW);

        short[] indices = new short[numVerts * 6];
        loadIndices(segments, indices);
        ShortBuffer indicesBuffer = Buffers.createShortBuffer(indices);
        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, buffers[BUFFER_INDICES]);
        GLES20.glBufferData(GLES20.GL_ELEMENT_ARRAY_BUFFER, indices.length * Short.SIZE / 8, indicesBuffer, GLES20.GL_STATIC_DRAW);
    }

    private void loadVerts(int segments, float radius, float[] verts) {
        final float PI = (float)Math.PI;
        final float deltax = PI / segments;
        final float deltay = PI / segments;
        final float deltau = 1.0f / (segments * 2);
        final float deltav = 1.0f / segments;

        int index = 0;
        for (int y = 0; y <= segments; ++y) {
            final float angley = -PI / 2 + y * deltay;

            final float projection = (float)Math.cos(angley) * radius;
            float y_coord = (float)Math.sin(angley) * radius;

            //texcoord tc;
            //tc.v = 1.0f - y * deltav;

            for (int x = 0; x < segments * 2 + 1; ++x) {
                final float anglex = x * deltax;

                verts[index + 0] = (float)Math.sin(anglex) * projection;
                verts[index + 1] = y_coord;
                verts[index + 2] = (float)Math.cos(anglex) * projection;

                //tc.u = x * deltau;
                //texcoords.push_back(tc);

                index += 3;
            }
        }
    }

    private void loadIndices(int segments, short[] indices) {
        final int x_segments = segments * 2 + 1;

        int index = 0;
        for (int y = 1; y < segments + 1; ++y)
        {
            final int pos = x_segments * y;
            for (int x = 0; x < x_segments; ++x)
            {
                final int x1 = (x + 1) % x_segments;
                indices[index + 0] = (short)(pos + x);
                indices[index + 1] = (short)(pos + x  - x_segments);
                indices[index + 2] = (short)(pos + x1 - x_segments);

                indices[index + 3] = (short)(pos + x1 - x_segments);
                indices[index + 4] = (short)(pos + x1);
                indices[index + 5] = (short)(pos + x);

                index += 6;
            }
        }
    }

    private static int getNumVerts(int segments) {
        return (segments + 1) * (segments * 2 + 1);
    }

}
