package io.github.some_example_name.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import io.github.some_example_name.component.ModelComponent;
import io.github.some_example_name.component.TransformComponent;
import io.github.some_example_name.empty_tag.GlassTag;

public class GlassSystem extends IteratingSystem {
    private final ComponentMapper<ModelComponent> modelMapper = ComponentMapper.getFor(ModelComponent.class);
    private final ComponentMapper<TransformComponent> transformMapper = ComponentMapper.getFor(TransformComponent.class);
    public GlassSystem() {
        super(Family.all(GlassTag.class, ModelComponent.class, TransformComponent.class).get());
    }
    @Override
    protected void processEntity(Entity entity, float v) {
        ModelComponent glass = modelMapper.get(entity);
        TransformComponent transform = transformMapper.get(entity);
        glass.box.transform.setTranslation(transform.position);
    }
}
