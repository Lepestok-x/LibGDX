package io.github.some_example_name.service_code;

import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;

public class BodyCollision {
    private BoundingBox v1;
    private BoundingBox v2;
    private BoundingBox body;
    private BoundingBox body2;
    public BodyCollision() {
        v1 = new BoundingBox();
        v2 = new BoundingBox();
    }

    public void initCenterEntity(Vector3 position, Vector3 size) {
        float divide = 2.f;
        body = new BoundingBox(new Vector3(-position.x / divide, -position.y / divide, -position.z / divide), new Vector3(size.x / divide, size.y / divide, size.z / divide));
    }
    public boolean collision(Matrix4 t_m1, Matrix4 t_m2) {
        v1.set(body).mul(t_m1);
        v2.set(body2).mul(t_m2);
        return v1.intersects(v2);
    }
}
