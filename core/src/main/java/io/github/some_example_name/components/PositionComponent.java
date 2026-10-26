package io.github.some_example_name.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public class PositionComponent implements Component {
    public final Vector2 pos = new Vector2();
    public float width, height;
    public PositionComponent(float x, float y, float width, float height) {
        this.pos.set(x, y);
        this.width = width;
        this.height = height;
    }
}
