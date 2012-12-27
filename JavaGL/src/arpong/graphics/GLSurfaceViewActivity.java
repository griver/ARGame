package arpong.graphics;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.MotionEvent;
import arpong.common.GameInterface;
import arpong.logic.ar.DumbRealityTracker;
import arpong.logic.ar.RandomRealityTracker;
import arpong.logic.ar.RealityTracker;
import arpong.logic.game.PongGame;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Wrapper activity demonstrating the use of {@link android.opengl.GLSurfaceView}, a view that
 * uses OpenGL drawing into a dedicated surface.
 */
public class GLSurfaceViewActivity extends Activity {
    private GLSurfaceView.Renderer mCurrentRender = null;
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

        PongGraphics pongGraphics = new PongGraphics(this);
        // reality tracker for testing purposes
        RealityTracker realityTracker = new RandomRealityTracker();
        // reality tracker to be used with ar steering buttons
//        RealityTracker realityTracker = new DumbRealityTracker();
        final float tennisTableWidth = 500;
        final float tennisTableHeight = 500;
        //final float tennisTableHeight = tennisTableWidth / 1.667f;
        //final float tennisTableHeight = 800;

        game = new PongGame(tennisTableWidth, tennisTableHeight, pongGraphics, realityTracker);
        pongGraphics.setGame(game);

        PongGraphicsWrapper pongGraphicsWrapper = new PongGraphicsWrapper(pongGraphics, game);

        final int FPS = 100;
        TimerTask updateGameTask = new UpdateGameTask();
        gameTimer.scheduleAtFixedRate(updateGameTask, 0, 1000 / FPS);

        mCurrentRender = pongGraphicsWrapper;
        mGLSurfaceView = new GLSurfaceView(this);
        mGLSurfaceView.setEGLContextClientVersion(2);
        mGLSurfaceView.setRenderer(mCurrentRender);
        setContentView(mGLSurfaceView);

        setTitle("Random Pong!");
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
        /*if (mCurrentRender != null) {
            boolean bRet = mCurrentRender.handleTouchEvent(event);
            if (bRet) {
                return true;
            }
        }*/
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        /*if (mCurrentRender != null) {
            boolean bRet = mCurrentRender.handleKeyEvent(keyCode, event);
            if (bRet) {
                // mGLSurfaceView.requestRender();
                return true;
            }
        }*/
        return super.onKeyUp(keyCode, event);
    }
}
