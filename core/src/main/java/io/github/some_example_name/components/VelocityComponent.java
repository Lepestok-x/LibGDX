package io.github.some_example_name.components;

import com.badlogic.ashley.core.Component;

public class VelocityComponent implements Component {
    public float velocity;
    public VelocityComponent(float velocity) {
        this.velocity = velocity;
    }
}
