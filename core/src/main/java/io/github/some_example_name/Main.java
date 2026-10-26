package io.github.some_example_name;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.github.some_example_name.components.ColorComponent;
import io.github.some_example_name.components.FrameComponent;
import io.github.some_example_name.components.PositionComponent;
import io.github.some_example_name.components.VelocityComponent;
import io.github.some_example_name.systems.PlayerInputSystem;
import io.github.some_example_name.systems.RenderSystem;
import io.github.some_example_name.systems.RobotSystem;
import io.github.some_example_name.tags.PlayerTag;
import io.github.some_example_name.tags.RobotTag;

public class Main extends ApplicationAdapter {
    private static final int screenWidth = 1000, screenHeight = 650;
    private OrthographicCamera camera;
    private ShapeRenderer renderer;
    private Viewport viewport;
    private Engine engine;
    @Override
    public void create() {
        camera = new OrthographicCamera(screenWidth, screenHeight);
        viewport = new FitViewport(screenWidth, screenHeight, camera);
        camera.position.set(screenWidth / 2.f, screenHeight / 2.f, .0f);
        camera.setToOrtho(true, screenWidth, screenHeight);
        renderer = new ShapeRenderer();
        engine = new Engine();
        Entity player = new Entity();
        player.add(new PlayerTag());
        player.add(new ColorComponent(Color.WHITE));
        player.add(new PositionComponent(450.f, 450.f, 100, 100));
        player.add(new VelocityComponent(300.f));
        engine.addEntity(player);
        engine.addSystem(new PlayerInputSystem());
        engine.addSystem(new RenderSystem(viewport, renderer));
        Entity robot = new Entity();
        robot.add(new RobotTag());
        robot.add(new ColorComponent(Color.GREEN));
        robot.add(new PositionComponent(300.f, 300.f, 100, 100));
        robot.add(new VelocityComponent(300.f));
        robot.add(new FrameComponent(screenWidth, screenHeight));
        engine.addEntity(robot);
        engine.addSystem(new RobotSystem());
        engine.addSystem(new RenderSystem(viewport, renderer));

    }

    @Override
    public void render() {
        camera.update(true);
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClearColor(.0f, .0f, .0f, 1.f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        final float deltaTime = Gdx.graphics.getDeltaTime();
        engine.update(deltaTime);
    }
    @Override
    public void resize(int width, int height) {
      viewport.update(width, height, true);
    }

    @Override
    public void dispose() {
        if(renderer != null) renderer.dispose();
    }
}
