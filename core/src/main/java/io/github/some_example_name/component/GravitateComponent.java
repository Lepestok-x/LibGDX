package io.github.some_example_name.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector3;

public class GravitateComponent implements Component {
    public final Vector3 gravitate = new Vector3();
    public GravitateComponent(Vector3 gravitate) {
        this.gravitate.set(gravitate);
    }
}
