package io.github.some_example_name.configuration;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

public class Ground {
    private final Model model;
    private final ModelInstance modelGround;
    private final Vector3 position;
    public Ground(float scaleX, float scaleY, float scaleZ, ModelBuilder builder, Color color) {
        this.position = new Vector3(.0f, -35f, -35f);
        model = builder.createBox(scaleX, scaleY, scaleZ,
            new Material(ColorAttribute.createDiffuse(color)),
            VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
        modelGround = new ModelInstance(model);
    }
    public void update(ModelBatch batch, Environment environment) {
        batch.render(modelGround, environment);
        modelGround.transform.setToTranslation(position);
    }
    public void dispose() {
        if(model != null) model.dispose();
    }
    public Matrix4 matrix4() {
        return modelGround.transform;
    }
    public void setColor(Color color) {
       ColorAttribute attribute = (ColorAttribute) modelGround.materials.first().get(ColorAttribute.Diffuse);
       if(attribute != null) {
           attribute.color.set(color);
       }
    }
}
