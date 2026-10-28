package io.github.some_example_name.engine;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import io.github.some_example_name.entityes.EntityFactory;
import io.github.some_example_name.system.*;

public class EngineFactory {
    private final ModelBatch batch;
    private final Camera camera;
    private final Engine engine = new Engine();
    private final EntityFactory factory = new EntityFactory();
    public EngineFactory(ModelBatch batch, Camera camera) {
        this.batch = batch;
        this.camera = camera;
        Entity player = factory.player(3.f, 3.f, 3.f, Color.BLUE);
        engine.addEntity(player);

        Entity glass = factory.glass(100.f, .5f, 100.f, Color.GRAY);
        engine.addEntity(glass);

        Entity lamp = factory.lamp(2.5f, 2.5f, 2.5f, Color.WHITE);
        engine.addEntity(lamp);

        engine.addSystem(new RenderSystem(batch));
        engine.addSystem(new GlassSystem());
        engine.addSystem(new CameraSystem(camera));
        engine.addSystem(new PlayerSystem());
        engine.addSystem(new LampSystem());
        engine.addSystem(new GravitateSystem());
        engine.addSystem(new CollisionSystem());

    }
    public void render(float deltaTime) {
        batch.begin(camera);
        engine.update(deltaTime);
        batch.end();
    }
    public void dispose() {
        if(batch != null) batch.dispose();
    }
}
