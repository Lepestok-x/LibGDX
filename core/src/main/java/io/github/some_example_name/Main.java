package io.github.some_example_name;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.utils.Array;
import io.github.some_example_name.service_code.Init;

public class Main extends ApplicationAdapter {
    private static final int screenWidth = 1000, screedHeight = 650;
    private PerspectiveCamera camera;
    private ModelBatch modelBatch;
    private  Array<ModelBuilder> builders;
    private Init init;
    private final float distance = 25.f;
    @Override
    public void create() {
        camera = new PerspectiveCamera(67, screenWidth, screedHeight);
        camera.position.set(.0f, distance, distance);
        camera.lookAt(.0f, .0f, .0f);
        camera.up.set(.0f, 1.f, .0f);
        camera.near = 1.f;
        camera.far = 300.f;

        modelBatch = new ModelBatch();
        builders = new Array<>();
        builders.add(new ModelBuilder());
        builders.add(new ModelBuilder());
        init = new Init(builders, camera);
        Gdx.input.setCursorCatched(true);
    }

    @Override
    public void render() {
        camera.update(true);
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClearColor(.0f, .0f, .0f, 1.f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        modelBatch.begin(camera);
        init.update(modelBatch, distance);
        modelBatch.end();
    }
    @Override
    public void resize(int width, int height) {
      camera.viewportWidth = width;
      camera.viewportHeight = height;
      camera.update();
    }

    @Override
    public void dispose() {
       init.dispose();
    }
}
