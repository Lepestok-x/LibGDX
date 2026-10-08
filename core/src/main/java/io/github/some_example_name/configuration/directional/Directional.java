package io.github.some_example_name.configuration.directional;

public class Directional implements Rgba, DirPosition {
    private final float[] rgba;
    private final float[] position;
    private static final int MAX_INDEX_ARRAY = 4;
    public Directional(float[] rgba, float[] position) {
        if(rgba.length > MAX_INDEX_ARRAY || position.length > MAX_INDEX_ARRAY)
            throw new IndexOutOfBoundsException(String.format("Array index out: %s", rgba.length));
        this.rgba = rgba;
        this.position = position;
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

    @Override
    public float dirX() {
        return position[0];
    }

    @Override
    public float dirY() {
        return position[1];
    }

    @Override
    public float dirZ() {
        return position[2];
    }
}
