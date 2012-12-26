package arpong.logic.game;

import arpong.logic.ar.RealityTracker;
import arpong.logic.ar.VirtualRealityRenderer;
import arpong.logic.gameobjects.Ball;
import arpong.logic.gameobjects.Paddle;
import arpong.logic.gameobjects.TableWall;
import arpong.logic.gameobjects.TennisTable;
import arpong.logic.primitives.Vector;

public class Game implements PongGameInterface {

    private final RealityTracker realityTracker;
    private final VirtualRealityRenderer realityRenderer;
    private static final int firstPlayerPaddleId = 0;
    private static final int secondPlayerPaddleId = 1;

    private Paddle firstPlayerPaddle;
    private Paddle secondPlayerPaddle;
    private Ball ball;
    private TennisTable table;

    private int firstPlayerScore;
    private int secondPlayerScore;

    Game(RealityTracker tracker, VirtualRealityRenderer renderer) {
        this.realityTracker = tracker;
        this.realityRenderer = renderer;

        firstPlayerPaddle = new Paddle(this.realityRenderer, firstPlayerPaddleId);
        secondPlayerPaddle = new Paddle(this.realityRenderer, secondPlayerPaddleId);
        ball = new Ball(this.realityRenderer);
        table = new TennisTable(this.realityRenderer);

        this.realityTracker.register(firstPlayerPaddleId, firstPlayerPaddle);
        this.realityTracker.register(secondPlayerPaddleId, secondPlayerPaddle);
    }

    public void tick() {
        //===================
        //|    1   :   0  []|
        //|        :        |
        //|[]      :        |
        //|        :   *    |
        //===================
        // [] --- paddle
        // * --- ball
        // 1, 0 --- score
        Vector collisionPoint = new Vector(0, 0);
        if (ball.collidesWith(firstPlayerPaddle, collisionPoint)) {
            // 1st player hit the ball - send it back to the 2nd player
            ball.setVelocity(new Vector(-128, 128));
        } else if (ball.collidesWith(secondPlayerPaddle, collisionPoint)) {
            // 2nd player hit the ball - send it back to the 1st player
            ball.setVelocity(new Vector(0, 0));
        } else if (ball.collidesWith(table, collisionPoint)) {
            // check what wall did the ball hit
            // probably, increment the score
            TableWall wall = table.wallForPoint(collisionPoint);
            if (wall == TableWall.LEFT_WALL) {
                table.incrementSecondPlayerScore();
            } else if (wall == TableWall.RIGHT_WALL) {
                table.incrementFirstPlayerScore();
            }
            // but change the velocity as usual
            ball.setVelocity(new Vector(-1, -1));
        }
//        table.update();
//        ball.update();
    }

}
