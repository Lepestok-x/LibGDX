package io.github.some_example_name.configuration.engine.physics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import io.github.some_example_name.configuration.Box;
import io.github.some_example_name.configuration.Collision;
import io.github.some_example_name.configuration.Ground;
public class PhysicsEngine {
    private final Box box;
    private final Ground ground;
    private final Collision collision;
    private static final float gravitate = -18.9f;
    private static float velocityGravitate;
    public PhysicsEngine(Box box, Ground ground, Collision collision) {
        this.box = box;
        this.ground = ground;
        this.collision = collision;
    }
    public void update() {
        // Создаем гравитацию
        final float time = Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) velocityGravitate = -gravitate;
        if(Gdx.input.isKeyPressed(Input.Keys.Q)) velocityGravitate = gravitate;
        if(Gdx.input.isKeyPressed(Input.Keys.E)) velocityGravitate = -gravitate;

        velocityGravitate += gravitate * time;
        box.gravitate(velocityGravitate, time);
        if(collision.collision(box, ground)) {
            box.rollback();
            velocityGravitate = 0.f;
            ground.setColor(Color.GRAY);
        } else {
            ground.setColor(Color.WHITE);
        }
    }
}
