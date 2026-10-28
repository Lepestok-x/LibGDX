package io.github.some_example_name.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import io.github.some_example_name.component.ModelComponent;
import io.github.some_example_name.component.TransformComponent;
import io.github.some_example_name.component.VelocityComponent;
import io.github.some_example_name.empty_tag.LampTag;

public class LampSystem extends IteratingSystem {
    private final ComponentMapper<TransformComponent> transformMapper = ComponentMapper.getFor(TransformComponent.class);
    private final ComponentMapper<VelocityComponent> velocityMapper = ComponentMapper.getFor(VelocityComponent.class);
    private final ComponentMapper<ModelComponent> modelMapper = ComponentMapper.getFor(ModelComponent.class);
    public LampSystem() {
        super(Family.all(LampTag.class, ModelComponent.class, VelocityComponent.class, TransformComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float v) {
        ModelComponent model = modelMapper.get(entity);
        TransformComponent transform = transformMapper.get(entity);
        model.box.transform.setTranslation(transform.position);
    }
}
