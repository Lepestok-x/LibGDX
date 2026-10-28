package io.github.some_example_name.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import io.github.some_example_name.component.GravitateComponent;
import io.github.some_example_name.component.ModelComponent;
import io.github.some_example_name.component.TransformComponent;
import io.github.some_example_name.empty_tag.PlayerTag;

public class GravitateSystem extends IteratingSystem {
    private final ComponentMapper<TransformComponent> transformMapper = ComponentMapper.getFor(TransformComponent.class);
    private final ComponentMapper<GravitateComponent> gravitateMapper = ComponentMapper.getFor(GravitateComponent.class);
    public GravitateSystem() {
        super(Family.all(PlayerTag.class, TransformComponent.class, GravitateComponent.class).get());
    }
    @Override
    protected void processEntity(Entity entity, float v) {
        GravitateComponent gravitate = gravitateMapper.get(entity);
        TransformComponent transform = transformMapper.get(entity);
        transform.prev_position.y = transform.position.y;
        transform.velocity.y += gravitate.gravitate.y * v;
        transform.position.y +=  transform.velocity.y * v;
    }
}
