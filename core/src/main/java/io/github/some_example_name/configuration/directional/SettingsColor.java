package io.github.some_example_name.configuration.directional;

import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;

public class SettingsColor {
    private final ColorAttribute colorAttribute;
    // Код был переписан на более читаемую версию, так как старая версия была не критична.
    // Настройки окружения цвета.
    public SettingsColor(long ambient, float r, float g, float b, float a) {
        this.colorAttribute = new ColorAttribute(ambient, r, g, b, a);
    }
    public ColorAttribute colorAttribute() {
        return colorAttribute;
    }
}
