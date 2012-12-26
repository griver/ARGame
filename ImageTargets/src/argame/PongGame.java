package argame;

public class PongGame {

    private TennisTable table;
    private Paddle firstPlayerPaddle;
    private Paddle secondPlayerPaddle;
    private Ball ball;

    private final RealityTracker realityTracker;
    private final VirtualRealityRenderer realityRenderer;

    private static final int firstPlayerPaddleId = 0;
    private static final int secondPlayerPaddleId = 1;

    PongGame(VirtualRealityRenderer renderer) {
        this.realityTracker = new DumbRealityTracker();
        this.realityRenderer = renderer;

        firstPlayerPaddle = new Paddle(this.realityRenderer, firstPlayerPaddleId);
        secondPlayerPaddle = new Paddle(this.realityRenderer, secondPlayerPaddleId);
        ball = new Ball(this.realityRenderer);
        table = new TennisTable(this.realityRenderer);

        this.realityTracker.register(firstPlayerPaddleId, firstPlayerPaddle);
        this.realityTracker.register(secondPlayerPaddleId, secondPlayerPaddle);
    }

    void tick() {
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
//        table.render();
        ball.render();
    }

    // To be called by Recognition
    void updateFirstPlayerPaddleLocal(float x, float y) {
        realityTracker.updatePosition(firstPlayerPaddleId, new Vector(x, y));
    }
    void updateSecondPlayerPaddleLocal(float x, float y) {
        realityTracker.updatePosition(secondPlayerPaddleId, new Vector(x, y));
    }

    // To be called by Graphics
    float getXMins() {
        return table.getBoudingBox().getLowerLeft().getX();
    }
    float getXMaxs() {
        return table.getBoudingBox().getUpperRight().getX();
    }
    float getYMins() {
        return table.getBoudingBox().getLowerLeft().getY();
    }
    float getYMaxs() {
        return table.getBoudingBox().getUpperRight().getY();
    }
    float getBallRadius() {
        Vector lowerLeft = ball.getBoudingBox().getLowerLeft();
        Vector upperRight = ball.getBoudingBox().getUpperRight();
        float width = Math.abs(upperRight.getX() - lowerLeft.getX());
        float height = Math.abs(upperRight.getY() - lowerLeft.getY());
        return Math.min(width, height) / 2;
    }
}
