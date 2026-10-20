package io.github.some_example_name.service_code;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;
public class Ground extends Entity {

    public Ground(Vector3 position, Vector3 size, ModelBuilder builder) {
        super(position, size, Color.WHITE, builder);
    }
    public void drawGround(ModelBatch batch) {
        drawEntity(batch);
    }
    public void disposeGround() {
        dispose();
    }
}
