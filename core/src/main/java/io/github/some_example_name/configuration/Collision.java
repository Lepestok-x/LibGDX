package io.github.some_example_name.configuration;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import io.github.some_example_name.configuration.boxs.Box;
import io.github.some_example_name.configuration.boxs.BoxTest;

public class Collision {
    private final BoundingBox boundingBoxTest;
    private final BoundingBox boundingBox;
    private final BoundingBox boundingGround;
    private final BoundingBox v1;
    private final BoundingBox v2;
    private final BoundingBox test_v1;
    private final BoundingBox test_v2;
    public Collision() {
        this.v1 = new BoundingBox();
        this.v2 = new BoundingBox();
        this.test_v1 = new BoundingBox();
        this.test_v2 = new BoundingBox();
        this.boundingBox = new BoundingBox(new Vector3(-2.5f, -2.5f, -2.5f), new Vector3(2.5f, 2.5f, 2.5f));
        this.boundingBoxTest = new BoundingBox(new Vector3(-2.5f, -2.5f, -2.5f), new Vector3(2.5f, 2.5f, 2.5f));
        this.boundingGround = new BoundingBox(new Vector3(-50.f, -.05f, -50.f), new Vector3(50.f, .05f, 50.f));
    }

    public boolean collision(Box box, Ground ground) {
        v1.set(boundingBox).mul(box.matrix4());
        v2.set(boundingGround).mul(ground.matrix4());
        return v1.intersects(v2);
    }
    public boolean collision_2(BoxTest boxTest, Ground ground) {
        test_v1.set(boundingBoxTest).mul(boxTest.matrix4());
        test_v2.set(boundingGround).mul(ground.matrix4());
        return test_v1.intersects(test_v2);
    }
    public boolean intersectionBox(Box box, BoxTest boxTest) {
        v1.set(boundingBox).mul(box.matrix4());
        test_v1.set(boundingBoxTest).mul(boxTest.matrix4());
        return v1.intersects(test_v1);
    }
}
