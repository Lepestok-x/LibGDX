package io.github.some_example_name.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
public class ModelComponent implements Component {
    private final Model model;
    private final ModelBuilder builder;
    private final Material material;
    public ModelInstance box;
    public ModelComponent(float width, float height, float depth, long attribute, Color color) {
      this.material = new Material(ColorAttribute.createDiffuse(color));
      this.builder = new ModelBuilder();
      this.model = builder.createBox(width, height, depth, material, attribute);
      this.box = new ModelInstance(model);
    }
    public ModelComponent(float width, float height, float depth, long attribute, Texture texture) {
        this.material = new Material(TextureAttribute.createDiffuse(texture));
        this.builder = new ModelBuilder();
        this.model = builder.createBox(width, height, depth, material, attribute);
        this.box = new ModelInstance(model);
    }
}
