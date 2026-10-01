package io.github.some_example_name.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
public class Bullet {
    private float x;
    private float y;
    private float angle;
    private float radius;
    private Color color;
    private BallOrdinary ordinary;
    public Bullet(BallOrdinary ordinary, float angle, float radius, Color color) {
        this.ordinary = ordinary;
        this.x = ordinary.getX();
        this.y = ordinary.getY();
        this.angle = angle;
        this.radius = radius;
        this.color = color;
    }
    public void update(ShapeRenderer renderer) {
        renderer.setColor(color);
        renderer.circle(x - radius / 2, y - radius / 2, radius);
    }
    public void angleBullet() {
        this.x += MathUtils.cos(angle) * 300 * Gdx.graphics.getDeltaTime();
        this.y += MathUtils.sin(angle) * 300 * Gdx.graphics.getDeltaTime();
    }
}
