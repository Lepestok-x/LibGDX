package io.github.some_example_name.configuration;

import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;

public class SettingCamera extends PerspectiveCamera {

    // Setting camera
    public SettingCamera() {
        this.position.set(.0f, 35.f, 35.f);
        this.lookAt(.0f, .0f, 0.f);
        this.up.set(.0f, 1.f, 0.f);
        this.fieldOfView = 60.f; // corner
        this.near = 1.f;
        this.far = 300.f;
    }
    public void positionToTarget(Vector3 position) {
       this.position.x = position.x;
       this.position.y = position.y + 35.f;
       this.position.z = position.z + 35.f;
       this.lookAt(position);
    }
    public void windowCamera(int width, int height) {
        viewportWidth = width;
        viewportHeight = height;
    }
}
