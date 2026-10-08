package io.github.some_example_name.configuration;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;

public class Collision {
    private final BoundingBox boundingBox;
    private final BoundingBox boundingGround;
    private final BoundingBox v1;
    private final BoundingBox v2;
    public Collision() {
        this.v1 = new BoundingBox();
        this.v2 = new BoundingBox();
        this.boundingBox = new BoundingBox(new Vector3(-2.5f, -2.5f, -2.5f), new Vector3(2.5f, 2.5f, 2.5f));
        this.boundingGround = new BoundingBox(new Vector3(-50.f, -.05f, -50.f), new Vector3(50.f, .05f, 50.f));
    }

    public boolean collision(Box box, Ground ground) {
        v1.set(boundingBox).mul(box.matrix4());
        v2.set(boundingGround).mul(ground.matrix4());
        return v1.intersects(v2);
    }
}
