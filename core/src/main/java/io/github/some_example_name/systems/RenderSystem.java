package io.github.some_example_name.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.github.some_example_name.components.ColorComponent;
import io.github.some_example_name.components.PositionComponent;
import io.github.some_example_name.components.VelocityComponent;

public class RenderSystem extends IteratingSystem {
    private final ShapeRenderer renderer;
    private final Viewport viewport;
    private final ComponentMapper<PositionComponent> pos = ComponentMapper.getFor(PositionComponent.class);
    private final ComponentMapper<ColorComponent> col = ComponentMapper.getFor(ColorComponent.class);
    public RenderSystem(Viewport viewport, ShapeRenderer renderer) {
        super(Family.all(PositionComponent.class, VelocityComponent.class, ColorComponent.class).get());
        this.renderer = renderer;
        this.viewport = viewport;
    }
    @Override
    public void update(float deltaTime) {
        renderer.setProjectionMatrix(viewport.getCamera().combined);
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        super.update(deltaTime);
        renderer.end();
    }
    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent position = pos.get(entity);
        ColorComponent color = col.get(entity);
        renderer.setColor(color.color);
        renderer.rect(position.pos.x, position.pos.y, position.width, position.height);
    }
}
