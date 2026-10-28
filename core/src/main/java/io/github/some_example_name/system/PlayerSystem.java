package io.github.some_example_name.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import io.github.some_example_name.component.GravitateComponent;
import io.github.some_example_name.component.ModelComponent;
import io.github.some_example_name.component.TransformComponent;
import io.github.some_example_name.component.VelocityComponent;
import io.github.some_example_name.empty_tag.PlayerTag;

import javax.swing.*;

public class PlayerSystem extends IteratingSystem {
    private final ComponentMapper<TransformComponent> transformMapper = ComponentMapper.getFor(TransformComponent.class);
    private final ComponentMapper<VelocityComponent> velocityMapper = ComponentMapper.getFor(VelocityComponent.class);
    public PlayerSystem() {
        super(Family.all(PlayerTag.class, ModelComponent.class, TransformComponent.class, VelocityComponent.class).get());
    }
    @Override
    protected void processEntity(Entity entity, float v) {
        TransformComponent transform = transformMapper.get(entity);
        VelocityComponent velocity = velocityMapper.get(entity);
        if(Gdx.input.isKeyPressed(Input.Keys.W)) transform.position.z -= velocity.velocity * v;
        if(Gdx.input.isKeyPressed(Input.Keys.S)) transform.position.z += velocity.velocity * v;
        if(Gdx.input.isKeyPressed(Input.Keys.A)) transform.position.x -= velocity.velocity * v;
        if(Gdx.input.isKeyPressed(Input.Keys.D)) transform.position.x += velocity.velocity * v;
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) Gdx.app.exit();


    }
}
