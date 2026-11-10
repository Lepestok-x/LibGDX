package some_example_name.logic;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import some_example_name.factory.camera_engine.CameraEngine;
import some_example_name.factory.glass.Glass;
import some_example_name.factory.input_listener.InputListener;
import some_example_name.factory.lamp.Lamp;
import some_example_name.factory.player.Player;

public class LogicEngine {
    private final CameraEngine cameraEngine = new  CameraEngine();
    private final Player player = new Player(.0f, .0f, .0f, 2.5f, 2.5f, 2.5f, Color.BLUE);
    private final Glass glass = new Glass(.0f, -35.f, -35.f / 2.f, 100.f, .5f, 100.f, Color.GRAY);
    private final Lamp lamp = new Lamp(.0f, -25.f, -35.f / 2.f, 1.5f, 1.5f, 1.f, Color.WHITE);
    private final InputListener inputListener = new InputListener(player, cameraEngine);
    public void update(float deltaTime) {
      player.update(deltaTime);
      glass.update(deltaTime);
      inputListener.update(deltaTime);
      cameraEngine.update(deltaTime);
    }
    public void render(ModelBatch modelBatch) {
      modelBatch.begin(cameraEngine.camera());
      player.render(modelBatch);
      glass.render(modelBatch);
      lamp.render(modelBatch);
    }
    public void resize(int width, int height) {
        cameraEngine.resizeFrame(width, height);
    }
    public void dispose() {
      player.dispose();
      glass.dispose();
      lamp.dispose();
    }
}
