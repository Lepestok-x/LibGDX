package io.github.some_example_name.configuration.listener;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector3;
import io.github.some_example_name.configuration.boxs.Box;
import io.github.some_example_name.configuration.SettingCamera;
import io.github.some_example_name.configuration.Vec;

public class ClickListener {
    private final Box box;
    private final SettingCamera camera;
    private final Vector3 normal;
    private float normalSpeed = 1.f;
    private static final float speed = 50.f;
    public ClickListener(Box box, SettingCamera camera) {
        this.box = box;
        this.camera = camera;
        normal = new Vector3(.0f, 0.f, .0f);
    }
    public void updateListener() {
        final float time = Gdx.graphics.getDeltaTime();
        normal.set(.0f, 0.f, .0f);
        if(Gdx.input.isKeyPressed(Input.Keys.W)) normal.z -= normalSpeed;
        if(Gdx.input.isKeyPressed(Input.Keys.S)) normal.z += normalSpeed;
        if(Gdx.input.isKeyPressed(Input.Keys.A)) normal.x -= normalSpeed;
        if(Gdx.input.isKeyPressed(Input.Keys.D)) normal.x += normalSpeed;
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) box.angle(Vec.LEFT, speed, time);
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) box.angle(Vec.RIGHT, speed, time);
        if(normal.z != 0 || normal.x != 0) {
            box.normalPosition(normal, speed, time);
        }
        camera.positionToTarget(box.getPosition());
    }
}
