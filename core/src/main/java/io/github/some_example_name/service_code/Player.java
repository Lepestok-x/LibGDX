package io.github.some_example_name.service_code;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;

public class Player extends Entity {
    private final float intensity = .403f;
    private float angleX = .0f;
    private float angleY = 45.f;
    public Player(Vector3 position, Vector3 size, ModelBuilder builder) {
        super(position, size, Color.RED, builder);
    }
    public void drawPlayer(ModelBatch batch) {
        drawEntity(batch);
    }
    public void cameraToPlayer(Camera camera, float distance) {
        Vector3 playerPos = this.getPosition();

        angleX -= Gdx.input.getDeltaX() * intensity;
        angleY += Gdx.input.getDeltaY() * intensity;
        angleY = MathUtils.clamp(angleY, 5.f, 85.f);
        float horizontal = distance * MathUtils.cosDeg(angleY);
        float vertical = distance * MathUtils.sinDeg(angleY);
        float offsetX = horizontal * MathUtils.sinDeg(angleX);
        float offsetZ = horizontal * MathUtils.cosDeg(angleX);

        camera.position.set(playerPos.x - offsetX, playerPos.y + vertical, playerPos.z - offsetZ);
        camera.up.set(.0f, 1.f, .0f);
        camera.lookAt(playerPos);
        matrix4().trn(playerPos);

    }
    public void disposePlayer() {
        dispose();
    }
}
