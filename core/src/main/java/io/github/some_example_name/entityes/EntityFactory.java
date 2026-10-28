package io.github.some_example_name.entityes;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.math.Vector3;
import io.github.some_example_name.component.*;
import io.github.some_example_name.empty_tag.CameraTargetTag;
import io.github.some_example_name.empty_tag.GlassTag;
import io.github.some_example_name.empty_tag.LampTag;
import io.github.some_example_name.empty_tag.PlayerTag;
import io.github.some_example_name.factory_transform.Transform;

public class EntityFactory {
    private Entity player, glass, lamp;
    private static final long attribute_player = VertexAttributes.Usage.Position | VertexAttributes.Usage.TextureCoordinates | VertexAttributes.Usage.ColorPacked;
    private static final long attribute_glass = VertexAttributes.Usage.Position | VertexAttributes.Usage.TextureCoordinates | VertexAttributes.Usage.ColorPacked;
    public Entity player(float width, float height, float depth, Color color) {
        player = new Entity();
        player.add(new PlayerTag());
        player.add(new CameraTargetTag());
        player.add(new CollisionComponent(new Vector3(width, height, depth)));
        player.add(new ModelComponent(width, height, depth, attribute_player, color));
        player.add(new TransformComponent(Transform.player));
        player.add(new VelocityComponent(50.f));
        player.add(new GravitateComponent(Transform.gravitate));
        return player;
    }
    public Entity glass(float width, float height, float depth, Color color) {
        glass = new Entity();
        glass.add(new GlassTag());
        glass.add(new CollisionComponent(new Vector3(width, height, depth)));
        glass.add(new ModelComponent(width, height, depth, attribute_glass, color));
        glass.add(new TransformComponent(Transform.glass));
        return glass;
    }
    public Entity lamp(float width, float height, float depth, Color color) {
        lamp = new Entity();
        lamp.add(new LampTag());
        lamp.add(new CollisionComponent(new Vector3(width, height, depth)));
        lamp.add(new ModelComponent(width, height, depth, attribute_glass, color));
        lamp.add(new TransformComponent(Transform.lamp));
        lamp.add(new VelocityComponent(50.f));
        return lamp;
    }
}
