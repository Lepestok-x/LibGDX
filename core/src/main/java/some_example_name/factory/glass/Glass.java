package some_example_name.factory.glass;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
public class Glass {
    private final Model model;
    private final ModelBuilder builder;
    private final ModelInstance glass;
    private final Material material;
    private static final long attribute = VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.ColorPacked;
    public Glass(float x, float y, float z, float width, float height, float depth, Color color) {
        builder = new ModelBuilder();
        material = new Material(ColorAttribute.createDiffuse(color));
        model = builder.createBox(width, height, depth, material, attribute);
        glass = new ModelInstance(model);
        glass.transform.setTranslation(x, y, z);

    }
    public void update(float deltaTime) {

    }
    public void render(ModelBatch modelBatch) {
        modelBatch.render(glass);
    }
    public void dispose() {
        if(model != null) model.dispose();
    }
}
