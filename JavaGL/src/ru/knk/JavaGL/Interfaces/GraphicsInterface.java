package ru.knk.JavaGL.Interfaces;

public interface GraphicsInterface {
    void updateProjectionGlobal(float[] matrix);
    void updatePlayerPaddleLocal(int paddleId, float x, float y);
    void updateBallLocal(float x, float y);
}
