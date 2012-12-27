package arpong.graphics;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.Matrix;
import arpong.graphics.Models.Sphere;
import arpong.graphics.Utils.Programs;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class Render02 extends RenderBase {
    private final float[] projectionMatrix = new float[16];
    private final float[] modelviewMatrix = new float[16];
    private int projectionMatrixId, modelviewMatrixId;
    private int programId;

    private StaticModel model;
    final private int[] bindings = new int[StaticModel.NUM_BUFFERS];

    private static final String kVertexShader =
        "precision mediump float; \n" +
        "uniform mat4 projection; \n" +
        "uniform mat4 modelview; \n" +
        "attribute vec3 position; \n" +
        "varying vec3 color; \n" +
        "void main() { \n" +
        "  gl_Position = projection * modelview * vec4(position, 1.0); \n" +
        "  color = vec3((position.x + 1.0) * 0.5, (position.y + 1.0) * 0.5, 0.0);\n" +
        "}";

    private static final String kFragmentShader =
        "precision mediump float; \n" +
        "varying vec3 color; \n" +
        "void main() { \n" +
        "  gl_FragColor = vec4(color, 1.0); \n" +
        "}";

    public Render02(Context context) {
        super(context);
    }

    public void onSurfaceCreated(GL10 unused, EGLConfig config) {
        GLES20.glClearColor(0.0f, 0.0f, 0.5f, 1.0f);
        GLES20.glClearDepthf(1.0f);
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);
        GLES20.glDepthFunc(GLES20.GL_LEQUAL);

        programId = Programs.loadProgram(kVertexShader, kFragmentShader);
        //GLES20.glBindAttribLocation(programId, StaticModel.BUFFER_VERTS, "position");
        bindings[StaticModel.BUFFER_VERTS] = GLES20.glGetAttribLocation(programId, "position");
        GLES20.glEnableVertexAttribArray(bindings[StaticModel.BUFFER_VERTS]);

        projectionMatrixId = GLES20.glGetUniformLocation(programId, "projection");
        modelviewMatrixId  = GLES20.glGetUniformLocation(programId, "modelview" );


        model = new Sphere(8, 1.0f);
    }

    public void onDrawFrame(GL10 unused) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
        GLES20.glUseProgram(programId);

        GLES20.glUniformMatrix4fv(projectionMatrixId, 1, false, projectionMatrix, 0);
        GLES20.glUniformMatrix4fv(modelviewMatrixId, 1, false, modelviewMatrix, 0);

        model.draw(bindings);
    }

    public void onSurfaceChanged(GL10 unused, int width, int height) {
        GLES20.glViewport(0, 0, width, height); // Reset The Current Viewport
        Matrix.setIdentityM(projectionMatrix, 0);
        Matrix.perspectiveM(projectionMatrix, 0, 45.0f, (float)width / (float)height, 0.1f, 100.0f);
        //Matrix.scaleM(projectionMatrix, 0, 1.0f, 1.0f, 1.0f);
        Matrix.setIdentityM(modelviewMatrix, 0);
        Matrix.translateM(modelviewMatrix, 0, 0.0f, 0.0f, -10.0f);
        //Matrix.setIdentityM(projectionMatrix, 0);
    }
}
