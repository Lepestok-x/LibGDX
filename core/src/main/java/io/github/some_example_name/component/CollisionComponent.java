package io.github.some_example_name.component;
import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;

public class CollisionComponent implements Component {
    public final BoundingBox v1;
    public final BoundingBox body;
    private float divide = 2.f;
    public CollisionComponent(Vector3 size_v1) {
        this.v1 = new BoundingBox();
        this.body = new BoundingBox(new Vector3(-size_v1.x / divide, -size_v1.y / divide, -size_v1.z / divide), new Vector3(size_v1.x / divide, size_v1.y / divide, size_v1.z / divide));
    }
}
