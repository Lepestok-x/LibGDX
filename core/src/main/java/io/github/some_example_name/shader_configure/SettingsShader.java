package io.github.some_example_name.shader_configure;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.graphics.g3d.utils.RenderContext;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class SettingsShader implements Shader {
    private static final FileHandle fragment = Gdx.files.internal("shaders/fragment.glsl");
    private static final FileHandle vertex = Gdx.files.internal("shaders/vertex.glsl");
    private ShaderProgram shaderProgram;
    private Camera camera;
    private RenderContext context;
    private int u_projViewTrans;
    private int u_projViewWorldTrans;
    private int u_projTime;
    private float u_time;
    @Override
    public void init() {
        ShaderProgram.pedantic = false;
        shaderProgram = new ShaderProgram(vertex, fragment);
        if (!shaderProgram.isCompiled()) {
            throw new GdxRuntimeException(String.format("Shader not initialized: %s", shaderProgram.getLog()));
        }
        u_projViewTrans = shaderProgram.getUniformLocation("u_projViewTrans");
        u_projViewWorldTrans = shaderProgram.getUniformLocation("u_projViewWorldTrans");
        u_projTime = shaderProgram.getUniformLocation("u_time");
    }

    @Override
    public void begin(Camera camera, RenderContext context) {
        this.camera = camera;
        this.context = context;
        shaderProgram.begin();
        shaderProgram.setUniformMatrix(u_projViewTrans, camera.combined);
        context.setDepthTest(GL20.GL_LEQUAL);
        context.setCullFace(GL20.GL_BACK);
    }

    @Override
    public void render(Renderable renderable) {
        u_time += Gdx.graphics.getDeltaTime();
        shaderProgram.setUniformMatrix(u_projViewWorldTrans, renderable.worldTransform);
        shaderProgram.setUniformf(u_projTime, u_time);
        renderable.meshPart.render(shaderProgram);
    }

    @Override
    public void end() {
        shaderProgram.end();
    }

    @Override
    public void dispose() {
        if (shaderProgram != null) shaderProgram.dispose();
    }

    @Override
    public int compareTo(Shader other) {
        return 0;
    }

    @Override
    public boolean canRender(Renderable instance) {
        return false;
    }
}
