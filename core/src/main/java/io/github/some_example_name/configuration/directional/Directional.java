package io.github.some_example_name.configuration.directional;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.math.Vector3;

public class Directional {
    private final DirectionalLight directionalLight;
    // Настройки направление света -> x, y, z
    public Directional(Color color, Vector3 position) {
      this.directionalLight = new DirectionalLight().set(color, position);
    }
    public DirectionalLight directionalLight() {
        return directionalLight;
    }
}
