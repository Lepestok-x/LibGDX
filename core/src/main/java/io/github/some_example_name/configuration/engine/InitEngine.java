package io.github.some_example_name.configuration.engine;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;
import io.github.some_example_name.configuration.Box;
import io.github.some_example_name.configuration.Collision;
import io.github.some_example_name.configuration.Ground;
import io.github.some_example_name.configuration.SettingCamera;
import io.github.some_example_name.configuration.engine.physics.PhysicsEngine;
import io.github.some_example_name.configuration.listener.ClickListener;

public class InitEngine {
    private final SettingCamera camera;
    private final Box box;
    private final Ground ground;
    private final ClickListener clickListener;
    private final PhysicsEngine physicsEngine;
    private final Collision collision;
    public InitEngine(ModelBuilder builder) {
        camera = new SettingCamera();
        box = new Box(5.f, 5.f, 5.f, builder, Color.RED);
        ground = new Ground(100.f, .1f, 100.f, builder, Color.WHITE);
        collision = new Collision();
        clickListener = new ClickListener(box, camera);
        physicsEngine = new PhysicsEngine(box, ground, collision);
    }
    public void drawUpdate(ModelBatch batch, Environment environment) {
        box.update(batch, environment);
        ground.update(batch, environment);
        physicsEngine.update();
    }
    public void window(int width, int height) {
        camera.windowCamera(width, height);
    }
    public void endUpdate() {
        clickListener.updateListener();
        camera.update();
    }
    public void dispose() {
        box.dispose();
        ground.dispose();
    }
    public PerspectiveCamera respectiveCamera() {
        return camera;
    }
}
