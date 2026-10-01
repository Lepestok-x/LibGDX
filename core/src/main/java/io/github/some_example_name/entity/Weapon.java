package io.github.some_example_name.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

public class Weapon {
    private float x;
    private float y;
    private float width;
    private float height;
    private Color color;
    private float angle;
    public Weapon(float x, float y, float width, float height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }
    public void update(ShapeRenderer renderer) {
        renderer.setColor(color);
        renderer.rect(x - width / 2, y - height / 2, width / 2, height / 2, 100, 10, 1.f, 1.f, angle);
    }
    public void mouseMoved(float mouseX, float mouseY) {
        float targetX = mouseX - (x + width / 2.f);
        float targetY = mouseY - (y + height / 2.f);
        angle = MathUtils.atan2(targetY, targetX) * MathUtils.radiansToDegrees;
    }
    public void moveXnY(float x, float y) {
        this.x = x;
        this.y = y;
    }
}
