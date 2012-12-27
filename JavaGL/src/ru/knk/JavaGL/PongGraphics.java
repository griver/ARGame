package ru.knk.JavaGL;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import arpong.logic.ar.VirtualRealityRenderer;
import ru.knk.JavaGL.Interfaces.GameInterface;
import ru.knk.JavaGL.Interfaces.GraphicsInterface;
import ru.knk.JavaGL.Models.FieldModel;
import ru.knk.JavaGL.Models.Sphere;
import ru.knk.JavaGL.Utils.Programs;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class PongGraphics extends RenderBase implements GraphicsInterface, VirtualRealityRenderer {
    private final float[] projectionMatrix = new float[16];
    private final float[] modelviewMatrix = new float[16];
    private int projectionMatrixId, modelviewMatrixId, colorId;
    private int programId;

    final private int[] bindings = new int[StaticModel.NUM_BUFFERS];
    private StaticModel fieldModel, ballModel;

    private Coord2D ballCoord = new Coord2D(0.0f, 0.0f);

    // Field parameters
    private float fieldMinX = 0.0f, fieldMaxX = 1.0f, fieldMinY = 0.0f, fieldMaxY = 1.0f;
    private float ballRadius = 0.05f;

    // FPS count
    private FPSCounter fpsCounter = new FPSCounter(5000, "JavaGL", "Render fps: ");

    private static final String kVertexShader =
            "precision mediump float; \n" +
                    "uniform mat4 projection; \n" +
                    "uniform mat4 modelview; \n" +
                    "attribute vec3 position; \n" +
                    "void main() { \n" +
                    "  gl_Position = projection * modelview * vec4(position, 1.0); \n" +
                    "}";

    private static final String kFragmentShader =
            "precision mediump float; \n" +
                    "uniform vec3 color; \n" +
                    "void main() { \n" +
                    "  gl_FragColor = vec4(color, 1.0); \n" +
                    "}";

    public PongGraphics(Context context) {
        super(context);
    }

    public void onSurfaceCreated(GL10 unused, EGLConfig config) {
        GLES20.glClearColor(0.0f, 0.0f, 0.5f, 1.0f);
        GLES20.glClearDepthf(1.0f);
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);
        GLES20.glDepthFunc(GLES20.GL_LEQUAL);

        programId = Programs.loadProgram(kVertexShader, kFragmentShader);
        bindings[StaticModel.BUFFER_VERTS] = GLES20.glGetAttribLocation(programId, "position");
        GLES20.glEnableVertexAttribArray(bindings[StaticModel.BUFFER_VERTS]);

        projectionMatrixId = GLES20.glGetUniformLocation(programId, "projection");
        modelviewMatrixId  = GLES20.glGetUniformLocation(programId, "modelview" );
        colorId = GLES20.glGetUniformLocation(programId, "color");

        fieldModel = new FieldModel();
        ballModel = new Sphere(8, 1.0f);
    }

    public void onDrawFrame(GL10 unused) {
        float ballX, ballY;
        synchronized (ballCoord) {
            ballX = ballCoord.x;
            ballY = ballCoord.y;
        }

        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
        GLES20.glUseProgram(programId);

        GLES20.glUniformMatrix4fv(projectionMatrixId, 1, false, projectionMatrix, 0);

        float[] modelview1;

        modelview1 = modelviewMatrix.clone();
        final float spanX = fieldMaxX - fieldMinX, spanY = fieldMaxY - fieldMinY;
        Matrix.translateM(modelview1, 0, -spanX * 0.5f, -spanY * 0.5f, 0.0f);
        Matrix.scaleM(modelview1, 0, spanX, spanY, 1.0f);
        GLES20.glUniformMatrix4fv(modelviewMatrixId, 1, false, modelview1, 0);

        GLES20.glUniform3f(colorId, 0.0f, 0.25f, 0.0f);
        fieldModel.draw(bindings);

        ballX -= fieldMinX;
        ballY -= fieldMinY;


        fpsCounter.update();
    }

    public void onSurfaceChanged(GL10 unused, int width, int height) {
        GLES20.glViewport(0, 0, width, height);

        Matrix.setIdentityM(projectionMatrix, 0);
        Matrix.perspectiveM(projectionMatrix, 0, 45.0f, (float)width / (float)height, 0.1f, 100.0f);

        final float ofs = Math.max(fieldMaxX - fieldMinX, fieldMaxY - fieldMinY);
        Matrix.setIdentityM(modelviewMatrix, 0);
        Matrix.translateM(modelviewMatrix, 0, 0.0f, 0.0f, -2.0f * ofs);
    }
    @Override
    public void updateProjectionGlobal(float[] modelview, float[] projection) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void updatePlayerPaddleLocal(int paddleId, float x, float y) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void updateBallLocal(float x, float y) {
        synchronized (ballCoord) {
            ballCoord.x = x;
            ballCoord.y = y;
        }
    }

    public void setGame(GameInterface game) {
        fieldMinX = game.getXMins();
        fieldMinY = game.getYMins();
        fieldMaxX = game.getXMaxs();
        fieldMaxY = game.getYMaxs();
        ballRadius = game.getBallRadius();
    }

}
