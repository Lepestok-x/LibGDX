package io.github.some_example_name.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Color;

public class ColorComponent implements Component {
    public final Color color;
    public ColorComponent(Color color) {
        this.color = color;
    }
}
