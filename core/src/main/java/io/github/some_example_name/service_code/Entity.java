package io.github.some_example_name.service_code;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

public class Entity {
    private Model model;
    private ModelInstance entity;
    private Vector3 position;
    private Vector3 size;
    public Entity(Vector3 position, Vector3 size, Color color, ModelBuilder builder) {
        this.position = position;
        this.size = size;
        long attribute = VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.ColorPacked;
        model = builder.createBox(size.x, size.y, size.z,
            new Material(ColorAttribute.createDiffuse(color)), attribute);
        entity = new ModelInstance(model);
    }

    protected void drawEntity(ModelBatch batch) {
        entity.transform.setTranslation(position);
        batch.render(entity);
    }
    protected void dispose() {
        if(model != null) model.dispose();
    }
    protected Vector3 getPosition() {
        return position;
    }
    protected Vector3 getSize() {
        return size;
    }
    protected Matrix4 matrix4() {
        return entity.transform;
    }
    protected void setPosition(Vector3 position) {
        this.position = position;

    }
}
