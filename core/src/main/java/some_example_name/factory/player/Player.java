package some_example_name.factory.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;

public class Player {
    private final Model model;
    private final ModelBuilder builder;
    private final ModelInstance player;
    private final Material material;
    private Vector3 position = new  Vector3();
    private static final long attribute = VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.ColorPacked;
    public Player(float x, float y, float z, float width, float height, float depth, Color color) {
        builder = new ModelBuilder();
        material = new Material(ColorAttribute.createDiffuse(color));
        model = builder.createBox(width, height, depth, material, attribute);
        player = new ModelInstance(model);
        player.transform.setTranslation(x, y, z);

    }
    public void update(float deltaTime) {

    }
    public void move(Vector3 normalize) {
        this.position.z += normalize.z;
        this.position.x += normalize.x;
        this.player.transform.setTranslation(position);
    }
    public void render(ModelBatch modelBatch) {
        modelBatch.render(player);
    }
    public void dispose() {
        if(model != null) model.dispose();
    }
    public ModelInstance player() {
        return player;
    }
    public Vector3 position() {
        return position;
    }
}
