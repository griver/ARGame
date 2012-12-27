package arpong.common;

import android.opengl.GLSurfaceView;

public interface GraphicsInterface extends GLSurfaceView.Renderer {
    void updateProjectionGlobal(float[] modelview, float[] projection);
    void updatePlayerPaddleLocal(int paddleId, float x, float y);
    void updateBallLocal(float x, float y);
}
