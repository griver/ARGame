package arpong.logic.ar;

public interface VirtualRealityRenderer {
    void updateBallLocal(float x, float y);
    void updatePlayerPaddleLocal(int paddleId, float x, float y);
}
