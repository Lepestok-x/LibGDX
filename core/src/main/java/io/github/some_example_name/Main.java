package io.github.some_example_name;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.model.NodePart;
import com.badlogic.gdx.graphics.g3d.utils.DefaultTextureBinder;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.graphics.g3d.utils.RenderContext;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.some_example_name.shader_configure.SettingsShader;

import java.math.BigDecimal;

public class Main extends ApplicationAdapter {
    private static final int screenWidth = 1000, screedHeight = 650;
    private PerspectiveCamera camera;
    private Material material;
    private Model model;
    private ModelBatch batch;
    private ModelBuilder cube;
    private Shader shader;
    private Renderable renderable;
    private RenderContext renderContext;
    private BitmapFont font;
    private SpriteBatch batchFont;
    @Override
    public void create() {
        batchFont = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.GREEN);

        camera = new PerspectiveCamera(67.f, screenWidth, screedHeight);
        camera.position.set(.0f, 5.f, 5.f);
        camera.lookAt(.0f, .0f, .0f);
        camera.up.set(.0f, 1.f, 0.f);
        camera.near = 1.f;
        camera.far = 300.f;

        batch = new ModelBatch();
        cube = new ModelBuilder();
        material = new Material();
        long attribute = VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal |
            VertexAttributes.Usage.TextureCoordinates | VertexAttributes.Usage.ColorPacked;
        material.set(ColorAttribute.createDiffuse(Color.RED));
        model = cube.createBox(2.f, 2.f, 2.f, material, attribute);

        NodePart blockPart = model.nodes.first().parts.first();
        renderable = new Renderable();
        blockPart.setRenderable(renderable);
        renderable.environment = null;
        renderable.worldTransform.idt();
        renderContext = new RenderContext(new DefaultTextureBinder(DefaultTextureBinder.MAX_GLES_UNITS, 1));
        shader = new SettingsShader();
        shader.init();


    }

    @Override
    public void render() {
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClearColor(.0f, .0f, .0f, 1.f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        camera.update();
        renderContext.begin();
        shader.begin(camera, renderContext);
        shader.render(renderable);
        shader.end();
        renderContext.end();
        batchFont.begin();
        float screenHeight = Gdx.graphics.getHeight() - 10.f;
        font.draw(batchFont, "FPS: " + Gdx.graphics.getFramesPerSecond(), 10, screenHeight);
        batchFont.end();

    }
    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.update();
    }

    @Override
    public void dispose() {
      if(model != null) model.dispose();
      if(batch != null) batch.dispose();
      if(shader != null) shader.dispose();
    }
}
