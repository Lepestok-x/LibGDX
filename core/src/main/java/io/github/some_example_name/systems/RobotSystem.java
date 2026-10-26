package io.github.some_example_name.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import io.github.some_example_name.components.ColorComponent;
import io.github.some_example_name.components.FrameComponent;
import io.github.some_example_name.components.PositionComponent;
import io.github.some_example_name.components.VelocityComponent;
import io.github.some_example_name.tags.RobotTag;

public class RobotSystem extends IteratingSystem {
    private final ComponentMapper<PositionComponent> positionMapper = ComponentMapper.getFor(PositionComponent.class);
    private final ComponentMapper<VelocityComponent> velocityMapper = ComponentMapper.getFor(VelocityComponent.class);
    private final ComponentMapper<FrameComponent> frameMapper = ComponentMapper.getFor(FrameComponent.class);
    public RobotSystem() {
        super(Family.all(RobotTag.class, PositionComponent.class, VelocityComponent.class, ColorComponent.class).get());
    }
    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent pos = positionMapper.get(entity);
        VelocityComponent vel = velocityMapper.get(entity);
        FrameComponent frame = frameMapper.get(entity);
        pos.pos.x += vel.velocity * deltaTime;
        pos.pos.y += vel.velocity * deltaTime;
        frame.width = Gdx.graphics.getWidth();
        frame.height = Gdx.graphics.getHeight();
        if(pos.pos.x < 0 || pos.pos.x > (frame.width - pos.width)) vel.velocity = -vel.velocity;
        if(pos.pos.y < 0 || pos.pos.y > (frame.height - pos.height)) vel.velocity = -vel.velocity;
    }
}
