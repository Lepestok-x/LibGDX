package io.github.some_example_name.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import io.github.some_example_name.component.ModelComponent;
public class RenderSystem extends IteratingSystem {
    private final ModelBatch batch;
    private final ComponentMapper<ModelComponent> modelMapper = ComponentMapper.getFor(ModelComponent.class);
    public RenderSystem(ModelBatch batch) {
        super(Family.all(ModelComponent.class).get());
        this.batch = batch;
    }

    @Override
    protected void processEntity(Entity entity, float v) {
        ModelComponent modelComponent = modelMapper.get(entity);
        batch.render(modelComponent.box);
    }
}
