package arpong.graphics;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import arpong.common.GameInterface;
import arpong.logic.ar.DumbRealityTracker;
import arpong.logic.game.PongGame;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Wrapper activity demonstrating the use of {@link android.opengl.GLSurfaceView}, a view that
 * uses OpenGL drawing into a dedicated surface.
 */
public class GLSurfaceViewActivity extends Activity {
    private RenderBase mCurrentRender = null;
    private GLSurfaceView mGLSurfaceView;
    private Timer gameTimer = new Timer();

    private GameInterface game;

    private class UpdateGameTask extends TimerTask {
        @Override
        public void run() {
            game.tick();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PongGraphics render = new PongGraphics(this);
        mCurrentRender = render;
        mGLSurfaceView = new GLSurfaceView(this);
        mGLSurfaceView.setEGLContextClientVersion(2);
        mGLSurfaceView.setRenderer(render);
        setContentView(mGLSurfaceView);

        setTitle("Hello!");

//        game = new FakeGame(render);
        game = new PongGame(render, new DumbRealityTracker());
        render.setGame(game);

        final int FPS = 100;
        TimerTask updateGameTask = new UpdateGameTask();
        gameTimer.scheduleAtFixedRate(updateGameTask, 0, 1000 / FPS);
    }

    @Override
    protected void onResume() {
        // Ideally a game should implement onResume() and onPause()
        // to take appropriate action when the activity looses focus
        super.onResume();
        mGLSurfaceView.onResume();
    }

    @Override
    protected void onPause() {
        // Ideally a game should implement onResume() and onPause()
        // to take appropriate action when the activity looses focus
        super.onPause();
        mGLSurfaceView.onPause();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mCurrentRender != null) {
            boolean bRet = mCurrentRender.handleTouchEvent(event);
            if (bRet) {
                return true;
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (mCurrentRender != null) {
            boolean bRet = mCurrentRender.handleKeyEvent(keyCode, event);
            if (bRet) {
                // mGLSurfaceView.requestRender();
                return true;
            }
        }
        return super.onKeyUp(keyCode, event);
    }
}
