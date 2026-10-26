package io.github.some_example_name.components;

import com.badlogic.ashley.core.Component;

public class FrameComponent implements Component {
    public int width, height;
    public FrameComponent(int width, int height) {
        this.width = width;
        this.height = height;
    }
}
