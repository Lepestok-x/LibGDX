package some_example_name;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import some_example_name.factory.camera_engine.CameraEngine;
import some_example_name.logic.LogicEngine;

public class Main extends ApplicationAdapter {
    private ModelBatch modelBatch;
    private LogicEngine logicEngine;
    @Override
    public void create() {
        this.modelBatch = new ModelBatch();
        this.logicEngine = new LogicEngine();
        Gdx.input.setCursorCatched(true);
    }
    @Override
    public void render() {
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClearColor(.0f, .0f, .0f, 1.f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        Gdx.gl.glEnable(GL20.GL_DEPTH_TEST);
        float deltaTime = Gdx.graphics.getDeltaTime();
        logicEngine.update(deltaTime);
        logicEngine.render(modelBatch);
        modelBatch.end();
    }
    @Override
    public void resize(int width, int height) {
        logicEngine.resize(width, height);
    }

    @Override
    public void dispose() {
       logicEngine.dispose();
    }
}
