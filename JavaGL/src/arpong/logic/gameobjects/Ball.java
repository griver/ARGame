package arpong.logic.gameobjects;

import arpong.logic.ar.RenderableGameObject;
import arpong.logic.ar.VirtualRealityRenderer;
import arpong.logic.primitives.BoundingBox;
import arpong.logic.primitives.Vector;

public class Ball extends GameObject implements RenderableGameObject {
    private VirtualRealityRenderer renderer;

    public Ball(VirtualRealityRenderer renderer) {
        super(new BoundingBox(new Vector(0, 0), new Vector(10, 10)));
        this.renderer = renderer;
    }

    @Override
    public void render() {
        renderer.updateBallLocal(getPosition().getX(), getPosition().getY());
    }
}
