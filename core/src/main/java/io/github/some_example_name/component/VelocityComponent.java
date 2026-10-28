package io.github.some_example_name.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector3;

public class VelocityComponent implements Component {
    public Vector3 velocity_v = new Vector3();
    public float velocity;
    public VelocityComponent(float velocity) {
        this.velocity = velocity;
    }
    public VelocityComponent(Vector3 velocity_v) {
        this.velocity_v = velocity_v;
    }
}
