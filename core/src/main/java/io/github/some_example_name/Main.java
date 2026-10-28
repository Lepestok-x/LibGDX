package io.github.some_example_name;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.math.Vector3;
import io.github.some_example_name.engine.EngineFactory;

public class Main extends ApplicationAdapter {
    private static final int screenWidth = 1000, screenHeight = 650;
    private PerspectiveCamera camera;
    private EngineFactory factory;
    private ModelBatch batch;
    @Override
    public void create() {
        camera = new PerspectiveCamera(60.f, screenWidth, screenHeight);
        camera.position.set(.0f, 25.f, 25.f);
        camera.lookAt(Vector3.Zero);
        camera.up.set(Vector3.Y);
        camera.near = .1f;
        camera.far = 300.f;
        batch = new ModelBatch();
        factory = new EngineFactory(batch, camera);
    }
    @Override
    public void render() {
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClearColor(.0f, .0f, .0f, 1.f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        Gdx.gl.glEnable(GL20.GL_DEPTH_TEST);
        camera.update(true);
        final float deltaTime = Gdx.graphics.getDeltaTime();
        factory.render(deltaTime);

    }
    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.update(true);
    }

    @Override
    public void dispose() {
       if(factory != null) factory.dispose();
    }
}
