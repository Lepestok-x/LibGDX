package io.github.some_example_name.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import io.github.some_example_name.components.PositionComponent;
import io.github.some_example_name.components.VelocityComponent;
import io.github.some_example_name.tags.PlayerTag;

public class PlayerInputSystem extends IteratingSystem {
    private final ComponentMapper<VelocityComponent> vel = ComponentMapper.getFor(VelocityComponent.class);
    private final ComponentMapper<PositionComponent> pos = ComponentMapper.getFor(PositionComponent.class);
    public PlayerInputSystem() {
        super(Family.all(PlayerTag.class, PositionComponent.class, VelocityComponent.class).get());
    }
    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        VelocityComponent velocity = vel.get(entity);
        PositionComponent position = pos.get(entity);
        float positionX = position.pos.x;
        float positionY = position.pos.y;
        if(Gdx.input.isKeyPressed(Input.Keys.W)) positionY -= velocity.velocity * deltaTime;
        if(Gdx.input.isKeyPressed(Input.Keys.S)) positionY += velocity.velocity * deltaTime;
        if(Gdx.input.isKeyPressed(Input.Keys.A)) positionX -= velocity.velocity * deltaTime;
        if(Gdx.input.isKeyPressed(Input.Keys.D)) positionX += velocity.velocity * deltaTime;
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) Gdx.app.exit();
        position.pos.set(positionX, positionY);
    }
}
