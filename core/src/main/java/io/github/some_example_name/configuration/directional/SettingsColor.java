package io.github.some_example_name.configuration.directional;
public class SettingsColor implements Rgba {
    private final long attribute;
    private final float[] rgba;
    public SettingsColor(long attribute, float[] rgba) {
        if(rgba.length > 4) throw new IndexOutOfBoundsException(String.format("Array index out: %s", rgba.length));
        this.attribute = attribute;
        this.rgba = rgba;
    }
    // Отдаем из массива все цвета
    public long getAttribute() {
        return attribute;
    }
    @Override
    public float getRed() {
        return rgba[0];
    }

    @Override
    public float getGreen() {
        return rgba[1];
    }

    @Override
    public float getBlue() {
        return rgba[2];
    }

    @Override
    public float getAlpha() {
        return rgba[3];
    }
}
