package io.github.some_example_name.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector3;

public class TransformComponent implements Component {
    public final Vector3 position = new Vector3();
    public final Vector3 prev_position = new Vector3();
    public final Vector3 velocity = new Vector3();
    public TransformComponent(Vector3 position) {
        this.position.set(position);
    }
}
