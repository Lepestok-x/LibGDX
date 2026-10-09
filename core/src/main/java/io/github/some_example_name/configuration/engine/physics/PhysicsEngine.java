package io.github.some_example_name.configuration.engine.physics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import io.github.some_example_name.configuration.boxs.Box;
import io.github.some_example_name.configuration.Collision;
import io.github.some_example_name.configuration.Ground;
import io.github.some_example_name.configuration.boxs.BoxTest;
import io.github.some_example_name.configuration.listener.ClickListener;

public class PhysicsEngine {
    private final Box box;
    private final Ground ground;
    private final BoxTest boxTest;
    private final Collision collision;
    private final ClickListener clickListener;
    private static final float gravitate = -18.9f;
    private static float velocityGravitate;
    private static float velocityGravitateTest;
    public PhysicsEngine(Box box, Ground ground, BoxTest boxTest, ClickListener clickListener, Collision collision) {
        this.box = box;
        this.boxTest = boxTest;
        this.ground = ground;
        this.collision = collision;
        this.clickListener = clickListener;
    }
    public void update() {
        // Создаем гравитацию
        final float time = Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) velocityGravitate = -gravitate;
        if(Gdx.input.isKeyPressed(Input.Keys.Q)) velocityGravitate = gravitate * 2.f;
        if(Gdx.input.isKeyPressed(Input.Keys.E)) velocityGravitate = -gravitate;

        velocityGravitate += gravitate * time;
        velocityGravitateTest += gravitate * time;
        box.gravitate(velocityGravitate, time);
        boxTest.gravitate(velocityGravitateTest, time);

        boolean isIntersection = collision.collision(box, ground);
        ground.setColor((isIntersection) ? Color.GRAY : Color.WHITE);
        if(collision.intersectionBox(box, boxTest)) {
            box.rollback();
        }
        if(isIntersection) {
            box.rollback();
            velocityGravitate = 0.f;
        }
        if(collision.collision_2(boxTest, ground)) {
            boxTest.rollback();
            velocityGravitateTest = 0.f;
        }
    }
}
