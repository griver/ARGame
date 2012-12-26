package arpong.logic.gameobjects;

import arpong.logic.ar.RenderableGameObject;
import arpong.logic.ar.TrackableObject;
import arpong.logic.ar.VirtualRealityRenderer;
import arpong.logic.primitives.BoudingBox;
import arpong.logic.primitives.Vector;

public class Paddle extends TrackableObject implements RenderableGameObject {
    private VirtualRealityRenderer renderer;
    int id;

    public Paddle(VirtualRealityRenderer renderer, int objectId) {
        super(new BoudingBox(new Vector(0, 0), new Vector(30, -80)));
        this.renderer = renderer;
        this.id = objectId;
    }

    @Override
    public void setPosition(Vector position) {
        super.setPosition(position);
        render();
    }

    @Override
    public void render() {
        renderer.updatePlayerPaddleLocal(id, getPosition().getX(), getPosition().getY());
    }
}
