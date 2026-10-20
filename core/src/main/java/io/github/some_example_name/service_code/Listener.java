package io.github.some_example_name.service_code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;

public class Listener {
    private final Vector3 forward = new Vector3();
    private final Vector3 right = new Vector3();
    private final float velocity = 50.f;
    private float speed;
    private final Player player;
    public Listener(Player player) {
        this.player = player;
    }

    public void updateListener(Camera camera, float distance) {
        float time = Gdx.graphics.getDeltaTime();
        forward.set(camera.direction);
        forward.y = .0f;
        forward.nor();
        Vector3 playerPos = player.getPosition();
        right.set(forward).crs(.0f, 1.f, .0f);
        this.speed = velocity * time;
        if(Gdx.input.isKeyPressed(Input.Keys.W)) playerPos.add(forward.x * speed, .0f, forward.z * speed);
        if(Gdx.input.isKeyPressed(Input.Keys.S)) playerPos.sub(forward.x * speed, .0f, forward.z * speed);
        if(Gdx.input.isKeyPressed(Input.Keys.A)) playerPos.sub(right.x * speed, .0f, right.z * speed);
        if(Gdx.input.isKeyPressed(Input.Keys.D)) playerPos.add(right.x * speed, .0f, right.z * speed);
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) Gdx.app.exit();
        player.setPosition(playerPos);
        player.cameraToPlayer(camera, distance);
    }
}
