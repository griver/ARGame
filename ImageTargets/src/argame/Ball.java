package argame;

public class Ball extends GameObject implements RenderableGameObject {
    private VirtualRealityRenderer renderer;

    public Ball(VirtualRealityRenderer renderer) {
        super(new BoudingBox(new Vector(0,0), new Vector(10,10)));
        this.renderer = renderer;
    }

    @Override
    public void render() {
        renderer.updateBallLocal(getPosition().getX(), getPosition().getY());
    }
}
