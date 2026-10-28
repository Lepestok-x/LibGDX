package io.github.some_example_name.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Camera;
import io.github.some_example_name.component.ModelComponent;
import io.github.some_example_name.component.TransformComponent;
import io.github.some_example_name.empty_tag.CameraTargetTag;

public class CameraSystem extends IteratingSystem {
    private final Camera camera;
    private final ComponentMapper<ModelComponent> modelMapper = ComponentMapper.getFor(ModelComponent.class);
    private final ComponentMapper<TransformComponent> transformMapper = ComponentMapper.getFor(TransformComponent.class);
    public CameraSystem(Camera camera) {
        super(Family.all(CameraTargetTag.class, ModelComponent.class, TransformComponent.class).get());
        this.camera = camera;
    }
    @Override
    protected void processEntity(Entity entity, float v) {
        TransformComponent transform = transformMapper.get(entity);
        ModelComponent model = modelMapper.get(entity);
        model.box.transform.setTranslation(transform.position);
        camera.position.set(transform.position.x, transform.position.y + 25.f, transform.position.z + 25.f);
        camera.lookAt(transform.position);
    }
}
