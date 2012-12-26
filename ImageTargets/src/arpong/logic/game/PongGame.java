package arpong.logic.game;

import arpong.logic.ar.*;
import arpong.logic.gameobjects.Ball;
import arpong.logic.gameobjects.Paddle;
import arpong.logic.gameobjects.TableWall;
import arpong.logic.gameobjects.TennisTable;
import arpong.logic.primitives.Vector;

import java.util.HashMap;
import java.util.Map;

public class PongGame implements AgreedUponPongGameInterface {

    private TennisTable table;
    private Paddle firstPlayerPaddle;
    private Paddle secondPlayerPaddle;
    private Ball ball;

    private final RealityTracker realityTracker;
    private final VirtualRealityRenderer realityRenderer;

    private static final int firstPlayerPaddleId = 0;
    private static final int secondPlayerPaddleId = 1;

    public PongGame(VirtualRealityRenderer renderer) {
        this.realityTracker = new DumbRealityTracker();
        this.realityRenderer = renderer;

        firstPlayerPaddle = new Paddle(this.realityRenderer, firstPlayerPaddleId);
        secondPlayerPaddle = new Paddle(this.realityRenderer, secondPlayerPaddleId);
        ball = new Ball(this.realityRenderer);
        table = new TennisTable(this.realityRenderer);

        this.realityTracker.register(firstPlayerPaddleId, firstPlayerPaddle);
        this.realityTracker.register(secondPlayerPaddleId, secondPlayerPaddle);
    }

    public synchronized void tick() {
        //    ===================
        //(0) |    1   :   0  []| (2)
        //    |        :        |
        //    |[]      :        |
        //(1) |        :   *    | (3)
        //    ===================
        // [] --- paddle
        // * --- ball
        // 1, 0 --- score
        // () --- paddle steering button
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
//        table.render();
        ball.render();
    }

    // To be called by Recognition
    public synchronized void updatePlayerPaddleLocal(int paddleId, float x, float y) {
        realityTracker.updatePosition(paddleId, new Vector(x, y));
    }
    public synchronized void updateSteeringButton(int buttonId, boolean pressed) {
        Map<Integer, Integer> steerableButton = new HashMap<Integer, Integer>();
        steerableButton.put(0, firstPlayerPaddleId);
        steerableButton.put(1, firstPlayerPaddleId);
        steerableButton.put(2, secondPlayerPaddleId);
        steerableButton.put(3, secondPlayerPaddleId);

        SteeringDirection direction = SteeringDirection.NEUTRAL;
        // top button's ids have even values
        if (pressed && (buttonId % 2 == 0)) {
            direction = SteeringDirection.TOP;
        } else if (pressed) {
            direction = SteeringDirection.BOTTOM;
        }

        steerObject(steerableButton.get(buttonId), direction);
    }

    // To be called by Graphics
    public float getXMins() {
        return table.getBoudingBox().getLowerLeft().getX();
    }
    public float getXMaxs() {
        return table.getBoudingBox().getUpperRight().getX();
    }
    public float getYMins() {
        return table.getBoudingBox().getLowerLeft().getY();
    }
    public float getYMaxs() {
        return table.getBoudingBox().getUpperRight().getY();
    }
    public float getBallRadius() {
        Vector lowerLeft = ball.getBoudingBox().getLowerLeft();
        Vector upperRight = ball.getBoudingBox().getUpperRight();
        float width = Math.abs(upperRight.getX() - lowerLeft.getX());
        float height = Math.abs(upperRight.getY() - lowerLeft.getY());
        return Math.min(width, height) / 2;
    }

    private void steerObject(int objectId, SteeringDirection direction) {
        TrackableObject steeredObject = realityTracker.getObjectById(objectId);
        Vector position = steeredObject.getPosition();
        float dy = 0; // direction == neutral
        switch (direction) {
            case TOP:
                dy = 5;
                break;
            case BOTTOM:
                dy = -5;
                break;
        }
        realityTracker.updatePosition(steeredObject, new Vector(position.getX(),
                position.getY() + dy));
    }
}
