package some_example_name.factory.camera_engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
public class CameraEngine {
    private static final int screenWidth = 16, screenHeight = 9;
    private Vector3 position = new Vector3();
    private final PerspectiveCamera camera;
    private float angleX = .1f;
    private float angleY = 1.5f;
    private static final float intensity = .3f;
    private float distance = 35.f;
    public CameraEngine() {
        this.camera = new PerspectiveCamera(60.f, screenWidth, screenHeight);
        this.camera.position.set(.0f, distance, distance);
        this.camera.lookAt(Vector3.Zero);
        this.camera.near = .1f;
        this.camera.far = 300.f;

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean scrolled(float amountX, float amountY) {
                distance = (distance + (amountY * distance));
                distance = MathUtils.clamp(distance, 35.f, 300.f);
                return true;
            }
        });
    }

    public void update(float deltaTime) {
        float deltaX = Gdx.input.getDeltaX();
        float deltaY = Gdx.input.getDeltaY();
        this.angleX -= deltaX * intensity * deltaTime;
        this.angleY += deltaY * intensity * deltaTime;

        this.angleY = MathUtils.clamp(this.angleY, .0f, 1.5f);
        float offsetX = MathUtils.sin(angleX) * MathUtils.cos(angleY) * distance;
        float offsetY = MathUtils.sin(angleY) * distance;
        float offsetZ = MathUtils.cos(angleX) * MathUtils.cos(angleY) * distance;

        this.camera.position.set(position.x - offsetX, position.y + offsetY, position.z - offsetZ);
        this.camera.up.set(Vector3.Y);
        this.camera.lookAt(position);
        this.camera.update(true);
    }
    public void movedCamera(Vector3 position) {
        this.position = position;
    }
    public void resizeFrame(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.update(true);
    }

    public Camera camera() {
        return camera;
    }
}
