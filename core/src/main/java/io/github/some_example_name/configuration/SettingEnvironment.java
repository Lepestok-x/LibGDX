package io.github.some_example_name.configuration;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import io.github.some_example_name.configuration.directional.Directional;
import io.github.some_example_name.configuration.directional.SettingsColor;

public class SettingEnvironment {
    private final Environment environment;
    public SettingEnvironment(Environment environment) {
        this.environment = environment;
    }
    public void startLightingEnvironment(SettingsColor color, Directional directional) {
     environment.set(new ColorAttribute(color.getAttribute(), color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()));
     environment.add(new DirectionalLight()
         .set(directional.getRed(), directional.getGreen(), directional.getBlue(),
             directional.dirX(), directional.dirY(),directional.dirZ()));
    }
    public Environment getEnvironment() {
        return environment;
    }
}
