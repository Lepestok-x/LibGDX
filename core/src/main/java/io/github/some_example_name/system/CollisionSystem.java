package io.github.some_example_name.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import io.github.some_example_name.component.CollisionComponent;

import io.github.some_example_name.component.GravitateComponent;
import io.github.some_example_name.component.ModelComponent;
import io.github.some_example_name.component.TransformComponent;
import io.github.some_example_name.empty_tag.PlayerTag;

public class CollisionSystem extends IteratingSystem {
    private final ComponentMapper<ModelComponent> modelMapper = ComponentMapper.getFor(ModelComponent.class);
    private final ComponentMapper<CollisionComponent> collisionMapper = ComponentMapper.getFor(CollisionComponent.class);
    private final ComponentMapper<TransformComponent> transformMapper = ComponentMapper.getFor(TransformComponent.class);
    private ImmutableArray<Entity> entities;
    public CollisionSystem() {
        super(Family.all(PlayerTag.class, ModelComponent.class, CollisionComponent.class).get());
    }

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);
        entities = engine.getEntitiesFor(Family.all(ModelComponent.class, CollisionComponent.class).get());
    }
    @Override
    protected void processEntity(Entity entity, float v) {
        CollisionComponent collision = collisionMapper.get(entity);
        ModelComponent model = modelMapper.get(entity);
        TransformComponent transform = transformMapper.get(entity);
        collision.v1.set(collision.body).mul(model.box.transform);
        for(int i = 0; i < entities.size(); i++) {
            Entity otherEntity = entities.get(i);
            if(entity == otherEntity) continue;
            CollisionComponent otherCollision = otherEntity.getComponent(CollisionComponent.class);
            ModelComponent otherModel = otherEntity.getComponent(ModelComponent.class);
            otherCollision.v1.set(otherCollision.body).mul(otherModel.box.transform);
            if(collision.v1.intersects(otherCollision.v1)) {
                transform.position.y = transform.prev_position.y;
                transform.velocity.y = .0f;
                System.out.println("Collision detected!");
            }
        }
    }
}
