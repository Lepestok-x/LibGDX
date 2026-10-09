package io.github.some_example_name.configuration.boxs;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import io.github.some_example_name.configuration.Collision;

public class BoxTest {
    private final Model model;
    private final ModelInstance modelBox;
    private final Vector3 position;
    private final Vector3 oldPosition;
    public BoxTest(float scaleX, float scaleY, float scaleZ, ModelBuilder builder, Collision collision, Color color) {
        this.position = new Vector3(.0f, .0f, -25.0f);
        this.oldPosition = new Vector3();
        model = builder.createBox(scaleX, scaleY, scaleZ,
            new Material(ColorAttribute.createDiffuse(color)),
            VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
        modelBox = new ModelInstance(model);
    }
    public void movedBox(Vector3 normal, float speed, float time) {
        position.z += normal.z * speed * time;
        position.x += normal.x * speed * time;
    }

    public void gravitate(float gravitate_v, float time) {
        oldPosition.set(position);
        position.y += gravitate_v * time;
        if(position.y < -40.f) position.set(.0f, .0f, -25.0f); // Откат, туда куда был в начале спавн
        modelBox.transform.setTranslation(position);
    }
    public void rollback() {
        position.set(oldPosition);
        modelBox.transform.setTranslation(oldPosition);
    }
    public void update(ModelBatch batch, Environment environment) {
        batch.render(modelBox, environment);
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
