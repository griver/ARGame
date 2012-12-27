package arpong.graphics;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.Matrix;
import arpong.common.GameInterface;
import arpong.common.GraphicsInterface;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class PongGraphicsWrapper implements Renderer {
    private GraphicsInterface r;
    private float[] modelview  = new float[16];
    private float[] projection = new float[16];
    private float fieldMinX, fieldMinY, fieldMaxX, fieldMaxY;

    PongGraphicsWrapper(GraphicsInterface r, GameInterface game) {
        fieldMinX = game.getXMins();
        fieldMinY = game.getYMins();
        fieldMaxX = game.getXMaxs();
        fieldMaxY = game.getYMaxs();

        //updateModelview();

        this.r = r;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        r.onSurfaceCreated(gl, config);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        final float angle = 90.0f;
        final float aspect = (float)width / (float)height;

        GLES20.glViewport(0, 0, width, height);

        Matrix.setIdentityM(projection, 0);
        Matrix.perspectiveM(projection, 0, angle, aspect, 1.0f, 5000.0f);

        final float factorY = (float)Math.tan(angle * 0.5f);
        final float facrorX = factorY * aspect;

        final float ofs = Math.max((fieldMaxX - fieldMinX) / facrorX, (fieldMaxY - fieldMinY) / factorY);
        Matrix.setIdentityM(modelview, 0);
        Matrix.translateM(modelview, 0, 0.0f, 0.0f, -1.0f * ofs);
        Matrix.rotateM(modelview, 0, -30.0f, 1.0f, 0.0f, 0.0f);

        r.updateProjectionGlobal(modelview, projection);
        r.onSurfaceChanged(gl, width, height);
    }

    private void updateModelview() {
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        r.onDrawFrame(gl);
    }
}
