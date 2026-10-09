package io.github.some_example_name;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;
import io.github.some_example_name.configuration.SettingEnvironment;
import io.github.some_example_name.configuration.directional.Directional;
import io.github.some_example_name.configuration.directional.SettingsColor;
import io.github.some_example_name.configuration.engine.InitEngine;
/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms.
 */
public class Main extends ApplicationAdapter {
    private ModelBatch batch;
    private InitEngine engine;
    private SettingEnvironment settingEnvironment;
    private final Color color = new Color(.3f, .3f, .3f, 1.f);
    private final Vector3 positionColor = new Vector3(-0.8f, -1.0f, -.4f).nor();
    private SpriteBatch spriteBatch;
    private BitmapFont font;
    @Override
    public void create() {
        font = new BitmapFont();
        font.setColor(Color.GREEN);
        spriteBatch = new SpriteBatch();
        batch = new ModelBatch();
        ModelBuilder builder = new ModelBuilder();
        Environment environment = new Environment();
        SettingsColor settingsColor = new SettingsColor(ColorAttribute.AmbientLight, .8f, .8f, .8f, 1.f);
        Directional directional = new Directional(color, positionColor);
        settingEnvironment = new SettingEnvironment(environment);
        settingEnvironment.startLightingEnvironment(settingsColor, directional);
        engine = new InitEngine(builder);
        engine.endUpdate();
    }

    @Override
    public void render() {
        engine.endUpdate();
        Gdx.gl.glClearColor(.0f, 0.f, .0f, 1.f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        batch.begin(engine.respectiveCamera());
        engine.drawUpdate(batch, settingEnvironment.getEnvironment());
        batch.end();

        spriteBatch.begin();
        font.draw(spriteBatch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 10, Gdx.graphics.getHeight() - 10);
        spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {
        engine.window(width, height);
    }

    @Override
    public void dispose() {
       engine.dispose();
       if(spriteBatch != null) spriteBatch.dispose();
       if(font != null) font.dispose();;
    }
}
