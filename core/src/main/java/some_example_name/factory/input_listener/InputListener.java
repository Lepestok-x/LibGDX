package some_example_name.factory.input_listener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;
import some_example_name.factory.camera_engine.CameraEngine;
import some_example_name.factory.player.Player;

public class InputListener {
    private final Player player;
    private final CameraEngine cameraEngine;
    private final Vector3 forward = new  Vector3();
    private final Vector3 right = new Vector3();
    private final Vector3 normalize = new Vector3();
    private float velocity = 50f;
    public InputListener(Player player, CameraEngine cameraEngine) {
        this.player = player;
        this.cameraEngine = cameraEngine;
    }
    public void update(float deltaTime) {
        forward.set(cameraEngine.camera().direction).set(forward.x, .0f, forward.z).nor();
        right.set(forward).crs(.0f, 1.f, .0f);
        float vertical = ((Gdx.input.isKeyPressed(Input.Keys.W)) ? 1.f : .0f) - ((Gdx.input.isKeyPressed(Input.Keys.S)) ? 1.f : .0f);
        float horizontal = ((Gdx.input.isKeyPressed(Input.Keys.D)) ? 1.f : .0f) - ((Gdx.input.isKeyPressed(Input.Keys.A)) ? 1.f : .0f);
        normalize.setZero().mulAdd(forward, vertical).mulAdd(right, horizontal);
        if(normalize.len2() > .0f) {
            player.move(normalize.scl(velocity * deltaTime).nor());
            cameraEngine.movedCamera(player.position());
        }
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) Gdx.app.exit();
    }
}
