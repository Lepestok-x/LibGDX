package io.github.some_example_name.configuration;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

public class Box {
    private final Model model;
    private final ModelInstance modelBox;
    private final Vector3 position;
    private final Vector3 oldPosition;
    private float angle;
    public Box(float scaleX, float scaleY, float scaleZ, ModelBuilder builder, Color color) {
        this.position = new Vector3(.0f, .0f, .0f);
        this.oldPosition = new Vector3();
        model = builder.createBox(scaleX, scaleY, scaleZ,
            new Material(ColorAttribute.createDiffuse(color)),
            VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
        modelBox = new ModelInstance(model);
    }
    public void angle(Vec vec, float speed, float time) {
        switch (vec) {
            case LEFT: angle += speed * time;
            break;
            case RIGHT: angle -= speed * time;
            break;
            default: throw new IllegalArgumentException(String.format("Vec not found: %s", vec));
        }
        modelBox.transform.setToRotation(Vector3.Y, angle);
        modelBox.transform.setTranslation(position);
    }
    public void normalPosition(Vector3 normal, float speed, float time) {
        position.z += normal.z * speed * time;
        position.x += normal.x * speed * time;
    }
    public void gravitate(float gravitate_v, float time) {
        oldPosition.set(position);
        position.y += gravitate_v * time;
        modelBox.transform.setTranslation(position);
    }
    public void rollback() {
        position.set(oldPosition);
        modelBox.transform.setTranslation(oldPosition);
    }
    public void update(ModelBatch batch, Environment environment) {
        batch.render(modelBox, environment);
        modelBox.transform.setTranslation(position);
    }
    public void dispose() {
        if(model != null) model.dispose();
    }
    public Vector3 getPosition() {
        return position;
    }
    public Matrix4 matrix4() {
        return modelBox.transform;
    }
}
